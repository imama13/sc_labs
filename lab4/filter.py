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
    def writtenBy(tweets: List[Tweet], username: str) -> List[Tweet]:
        return [tweet for tweet in tweets if tweet.get_author().lower() == username.lower()]

    @staticmethod
    def inTimespan(tweets: List[Tweet], timespan: Timespan) -> List[Tweet]:
        return [tweet for tweet in tweets if timespan.get_start() <= tweet.get_timestamp() <= timespan.get_end()]

    @staticmethod
    def containing(tweets: List[Tweet], words: List[str]) -> List[Tweet]:
        lower_words = {word.lower() for word in words}
        return [tweet for tweet in tweets if any(word.lower() in tweet.text.lower().split() for word in lower_words)]
