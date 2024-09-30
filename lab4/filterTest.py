import unittest
from datetime import datetime
from filter import Tweet, Timespan, Filter


class TestFilter(unittest.TestCase):

    def setUp(self):
        self.d1 = datetime(2016, 2, 17, 10, 0, 0)
        self.d2 = datetime(2016, 2, 17, 11, 0, 0)
        self.d3 = datetime(2016, 2, 17, 12, 0, 0)
        
        self.tweet1 = Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", self.d1)
        self.tweet2 = Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", self.d2)

    def test_writtenBy_multipleTweets_singleResult(self):
        result = Filter.writtenBy([self.tweet1, self.tweet2], "alyssa")
        self.assertEqual(len(result), 1)
        self.assertIn(self.tweet1, result)

    def test_writtenBy_noResult(self):
        result = Filter.writtenBy([self.tweet1, self.tweet2], "charlie")
        self.assertEqual(len(result), 0)

    def test_inTimespan_multipleTweets_multipleResults(self):
        timespan = Timespan(datetime(2016, 2, 17, 9, 0, 0), datetime(2016, 2, 17, 12, 0, 0))
        result = Filter.inTimespan([self.tweet1, self.tweet2], timespan)
        self.assertEqual(len(result), 2)
        self.assertIn(self.tweet1, result)
        self.assertIn(self.tweet2, result)

    def test_inTimespan_noResult(self):
        timespan = Timespan(datetime(2016, 2, 17, 12, 1, 0), datetime(2016, 2, 17, 13, 0, 0))
        result = Filter.inTimespan([self.tweet1, self.tweet2], timespan)
        self.assertEqual(len(result), 0)

    def test_containing_multipleTweets_multipleResults(self):
        result = Filter.containing([self.tweet1, self.tweet2], ["talk"])
        self.assertEqual(len(result), 2)
        self.assertIn(self.tweet1, result)
        self.assertIn(self.tweet2, result)

    def test_containing_caseInsensitive(self):
        result = Filter.containing([self.tweet1, self.tweet2], ["TALK"])
        self.assertEqual(len(result), 2)

    def test_containing_noResult(self):
        result = Filter.containing([self.tweet1, self.tweet2], ["pizza"])
        self.assertEqual(len(result), 0)


if __name__ == "__main__":
    unittest.main()
