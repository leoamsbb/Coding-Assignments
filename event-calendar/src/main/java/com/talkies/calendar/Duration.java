package com.talkies.calendar;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

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

    public boolean overlaps(Duration otherDuration) {
        return beforeOrEqual(this.fromTime, otherDuration.fromTime) &&
                afterOrEqual(this.toTime, otherDuration.toTime);
    }

    private boolean beforeOrEqual(LocalTime thisTime, LocalTime otherTime) {
        return thisTime.isBefore(otherTime) || thisTime.equals(otherTime);
    }

    private boolean afterOrEqual(LocalTime thisTime, LocalTime otherTime) {
        return thisTime.isAfter(otherTime) || thisTime.equals(otherTime);
    }

}
