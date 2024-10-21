/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // Ensure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        // Test case: no tweets provided; expects an empty follows graph
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        assertTrue("expected empty graph", followsGraph.isEmpty()); // Assert that the graph is empty
    }
    
    @Test
    public void testInfluencersEmpty() {
        // Test case: empty follows graph; expects an empty list of influencers
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("expected empty list", influencers.isEmpty()); // Assert that the influencers list is empty
    }

    @Test
    public void testGuessFollowsGraphNoMentions() {
        // Test case: no mentions in tweets; expects an empty follows graph
        List<Tweet> tweets = Arrays.asList(
            new Tweet(1, "user1", "Hello world", Instant.now()),
            new Tweet(2, "user2", "Another tweet", Instant.now())
        );
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        System.out.println(followsGraph); // Debug: print the follows graph
        assertTrue("expected empty graph", followsGraph.isEmpty()); // Assert that the graph is empty
    }

    @Test
    public void testGuessFollowsGraphSingleMention() {
        // Test case: single mention in a tweet; expects user1 to follow user2
        List<Tweet> tweets = Arrays.asList(
            new Tweet(1, "user1", "Hello @user2", Instant.now())
        );
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        assertTrue("expected user1 to follow user2", followsGraph.get("user1").contains("user2")); // Assert the follow relationship
    }

    @Test
    public void testGuessFollowsGraphMultipleMentions() {
        // Test case: multiple mentions in a tweet; expects user1 to follow both user2 and user3
        List<Tweet> tweets = Arrays.asList(
            new Tweet(1, "user1", "Hello @user2 @user3", Instant.now())
        );
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        assertTrue("expected user1 to follow user2 and user3", followsGraph.get("user1").containsAll(Arrays.asList("user2", "user3"))); // Assert follow relationships
    }

    @Test
    public void testGuessFollowsGraphMultipleTweets() {
        // Test case: multiple tweets from the same author; expects user1 to follow user2 and user3
        List<Tweet> tweets = Arrays.asList(
            new Tweet(1, "user1", "Hello @user2", Instant.now()),
            new Tweet(2, "user1", "Hi @user3", Instant.now())
        );
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        assertTrue("expected user1 to follow user2 and user3", followsGraph.get("user1").containsAll(Arrays.asList("user2", "user3"))); // Assert follow relationships
    }

    @Test
    public void testInfluencersSingleUserNoFollowers() {
        // Test case: single user with no followers; expects an empty list of influencers
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user1", new HashSet<>());
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("expected empty list", influencers.isEmpty()); // Assert that the influencers list is empty
    }

    @Test
    public void testInfluencersSingleUserWithFollowers() {
        // Test case: single user with one follower; expects user2 to be the influencer
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user1", new HashSet<>(Arrays.asList("user2")));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertEquals("expected user1 to be the influencer", "user2", influencers.get(0)); // Assert the influencer
    }

    @Test
    public void testInfluencersMultipleUsers() {
        // Test case: multiple users with the same follower; expects user2 to be the top influencer
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user1", new HashSet<>(Arrays.asList("user2")));
        followsGraph.put("user3", new HashSet<>(Arrays.asList("user2")));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertEquals("expected user2 to be the top influencer", "user2", influencers.get(0)); // Assert the top influencer
    }

    @Test
    public void testInfluencersEqualFollowers() {
        // Test case: multiple users with the same number of followers; expects user2 and user4 to be influencers
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user1", new HashSet<>(Arrays.asList("user2")));
        followsGraph.put("user3", new HashSet<>(Arrays.asList("user4")));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("expected user2 and user4 to be influencers", influencers.containsAll(Arrays.asList("user2", "user4"))); // Assert influencers
    }



    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}