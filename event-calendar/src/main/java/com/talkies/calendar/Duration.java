package com.talkies.calendar;

import java.time.LocalTime;

import static com.talkies.calendar.TimeParser.getTime;

public class Duration {
    private String from;
    private String to;

    private LocalTime fromTime;
    private LocalTime toTime;

    public Duration(String from, String to) {
        this.from = from;
        this.to = to;

        this.fromTime = getTime(from);
        this.toTime = getTime(to);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public boolean within(final Duration otherDuration) {
        return beforeOrEqual(otherDuration.fromTime, this.fromTime) &&
                afterOrEqual(otherDuration.toTime, this.toTime);
    }

    public boolean overlaps(Duration otherDuration) {
        return !disconnected(otherDuration) ||
                within(this.fromTime, otherDuration) ||
                within(otherDuration.fromTime, this);
    }

    private boolean disconnected(Duration otherDuration) {
        return beforeOrEqual(this.toTime, otherDuration.fromTime) ||
                beforeOrEqual(otherDuration.toTime, this.fromTime);
    }

    private static boolean beforeOrEqual(LocalTime thisTime, LocalTime otherTime) {
        return thisTime.isBefore(otherTime) || thisTime.equals(otherTime);
    }

    private static boolean afterOrEqual(LocalTime thisTime, LocalTime otherTime) {
        return thisTime.isAfter(otherTime) || thisTime.equals(otherTime);
    }

    private static boolean within(final LocalTime time, final Duration otherDuration) {
        return time.isAfter(otherDuration.fromTime) &&
                time.isBefore(otherDuration.toTime);
    }
}
