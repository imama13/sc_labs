from datetime import datetime
from typing import List, Set

class Tweet:
    def __init__(self, id: int, author: str, text: str, timestamp: datetime):
        self.id = id
        self.author = author
        self.text = text
        self.timestamp = timestamp

    def get_timestamp(self):
        return self.timestamp


class Timespan:
    def __init__(self, start: datetime, end: datetime):
        self.start = start
        self.end = end

    def get_start(self):
        return self.start

    def get_end(self):
        return self.end


class Extract:
    @staticmethod
    def get_timespan(tweets: List[Tweet]) -> Timespan:
        if not tweets:
            now = datetime.now()
            return Timespan(now, now)

        start = tweets[0].get_timestamp()
        end = start

        for tweet in tweets:
            timestamp = tweet.get_timestamp()
            if timestamp < start:
                start = timestamp
            if timestamp > end:
                end = timestamp

        return Timespan(start, end)

    @staticmethod
    def get_mentioned_users(tweets: List[Tweet]) -> Set[str]:
        mentioned_users = set()

        for tweet in tweets:
            words = tweet.text.split()

            for word in words:
                if word.startswith("@") and len(word) > 1:
                    username = word[1:].lower()
                    mentioned_users.add(username)

        return mentioned_users
