/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.*;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
	public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
	    Map<String, Set<String>> followsGraph = new HashMap<>(); // Initialize the follows graph as an empty map
	    
	    // Loop through each tweet in the provided list
	    for (Tweet tweet : tweets) {
	        String author = tweet.getAuthor().toLowerCase(); // Get the author's username (case insensitive)
	        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet)); // Extract mentioned users from the tweet
	        
	        // Only update the follows graph if there are mentioned users
	        if (!mentionedUsers.isEmpty()) {
	            followsGraph.putIfAbsent(author, new HashSet<>()); // Add the author to the graph if not already present
	            followsGraph.get(author).addAll(mentionedUsers); // Add mentioned users to the author's follow list
	        }
	    }
	    
	    return followsGraph; // Return the completed follows graph
	}


    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
	public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        Map<String, Integer> followerCount = new HashMap<>(); // Map to track follower counts for each user

        // Loop through each user's follow list to count followers
        for (Set<String> followers : followsGraph.values()) {
            for (String user : followers) {
                // Increment the follower count for each user
                followerCount.put(user, followerCount.getOrDefault(user, 0) + 1);
            }
        }

        // Create a list of influencers from the follower counts
        List<String> influencers = new ArrayList<>(followerCount.keySet());
        // Sort the influencers based on follower counts in descending order
        influencers.sort((user1, user2) -> followerCount.get(user2) - followerCount.get(user1));

        return influencers; // Return the sorted list of influencers
    }

}