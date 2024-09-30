import unittest
from datetime import datetime
from extract import Tweet, Timespan, Extract


class TestExtract(unittest.TestCase):

    def setUp(self):
        self.d1 = datetime(2016, 2, 17, 10, 0, 0)
        self.d2 = datetime(2016, 2, 17, 11, 0, 0)
        self.tweet1 = Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", self.d1)
        self.tweet2 = Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", self.d2)

    def test_get_timespan_two_tweets(self):
        timespan = Extract.get_timespan([self.tweet1, self.tweet2])
        self.assertEqual(timespan.get_start(), self.d1, "expected start to match tweet1 timestamp")
        self.assertEqual(timespan.get_end(), self.d2, "expected end to match tweet2 timestamp")

    def test_get_timespan_empty_list(self):
        timespan = Extract.get_timespan([])
        now = datetime.now()
        self.assertAlmostEqual(timespan.get_start(), now, delta=1, msg="expected start to be current time")
        self.assertAlmostEqual(timespan.get_end(), now, delta=1, msg="expected end to be current time")

    def test_get_mentioned_users_no_mention(self):
        mentioned_users = Extract.get_mentioned_users([self.tweet1])
        self.assertTrue(len(mentioned_users) == 0, "expected empty set of mentioned users")

    def test_get_mentioned_users_with_mentions(self):
        tweet_with_mentions = Tweet(3, "alyssa", "Hey @bob have you talked to @alice", self.d1)
        mentioned_users = Extract.get_mentioned_users([tweet_with_mentions])
        print(mentioned_users)
        self.assertTrue("bob" in mentioned_users and "alice" in mentioned_users, 
                        "expected both bob and alice to be mentioned")

    def test_get_mentioned_users_with_invalid_mentions(self):
        tweet_with_invalid_mentions = Tweet(4, "alyssa", "Hey @!user, email me at user@example.com", self.d1)
        mentioned_users = Extract.get_mentioned_users([tweet_with_invalid_mentions])
        self.assertTrue(len(mentioned_users) == 0, "expected no valid mentions")

    def test_get_mentioned_users_with_punctuation(self):
        tweet_with_punctuation = Tweet(5, "alyssa", "Check this out @bob! and @alice.", self.d1)
        mentioned_users = Extract.get_mentioned_users([tweet_with_punctuation])
        self.assertTrue("bob" in mentioned_users and "alice" in mentioned_users, 
                        "expected both bob and alice to be mentioned")
        

if __name__ == "__main__":
    unittest.main()
