package twitter;

import java.time.Instant;

public class Timespan {
    private final Instant start;
    private final Instant end;

    public Timespan(Instant start, Instant end) {
        this.start = start;
        this.end = end;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }
}
