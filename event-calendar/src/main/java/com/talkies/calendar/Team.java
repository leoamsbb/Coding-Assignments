package com.talkies.calendar;

import java.util.List;

public class Team implements Participant{
    private List<User> teamMembers;

    public Team(List<User> members) {
        this.teamMembers = members;
    }

}
