from datetime import datetime
from typing import List, Set


class Tweet:
    def __init__(self, id: int, author: str, text: str, timestamp: datetime):
        self.id = id
        self.author = author
        self.text = text
        self.timestamp = timestamp

    def get_author(self):
        return self.author

    def get_timestamp(self):
        return self.timestamp

    def __repr__(self):
        return f'Tweet({self.id}, {self.author}, {self.text}, {self.timestamp})'


class Timespan:
    def __init__(self, start: datetime, end: datetime):
        self.start = start
        self.end = end

    def get_start(self):
        return self.start

    def get_end(self):
        return self.end


class Filter:
    @staticmethod
    def writtenBy(tweets: List[Tweet], author: str) -> List[Tweet]:
        # Legal variation: Return tweets by the specified author in reverse chronological order
        result = [tweet for tweet in tweets if tweet.author.lower() == author.lower()]
        return sorted(result, key=lambda t: t.get_timestamp(), reverse=True)

    @staticmethod
    def inTimespan(tweets: List[Tweet], timespan: Timespan) -> List[Tweet]:
        # Legal variation: This checks inclusively, ensuring tweets with timestamps exactly equal
        # to the start or end of the timespan are also included.
        return [tweet for tweet in tweets if timespan.start <= tweet.get_timestamp() <= timespan.end]


    @staticmethod
    def containing(tweets: List[Tweet], words: List[str]) -> List[Tweet]:
        lower_words = {word.lower() for word in words}
        return [tweet for tweet in tweets if any(word.lower() in tweet.text.lower().split() for word in lower_words)]
