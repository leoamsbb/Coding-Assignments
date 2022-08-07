package com.talkies.calendar;

import java.util.ArrayList;
import java.util.List;

public class User implements Participant {
    private Duration workingHours;
    private List<Duration> myEvents;

    public User(String from, String to) {
        this.workingHours = new Duration(from, to);
        this.myEvents = new ArrayList<>();
    }

    public User(Duration workingHours) {
        this(workingHours.getFrom(), workingHours.getTo());
    }

    public String getFrom() {
        return this.workingHours.getFrom();
    }

    public String getTo() {
        return this.workingHours.getTo();
    }

    public boolean getAvailability(Duration eventDuration) {
        return myEvents.stream().noneMatch(eventDuration::overlaps);
    }

    public void blockCalendar(Duration eventDuration) {
        if (getAvailability(eventDuration)) {
            this.myEvents.add(eventDuration);
        }
    }
}
