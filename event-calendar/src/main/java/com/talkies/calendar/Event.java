package com.talkies.calendar;

import java.util.List;

public class Event {
    private List<Participant> participants;
    private String startTime;
    private String endTime;
    private int representatives;

    public Event(List<Participant> participantList, String start, String end, int representatives){
        this.participants = participantList;
        this.startTime = start;
        this.endTime = end;
        this.representatives = representatives;

    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public int getRepresentatives() {
        return representatives;
    }
}
