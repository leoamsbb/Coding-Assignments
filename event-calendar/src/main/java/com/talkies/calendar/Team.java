package com.talkies.calendar;

import java.util.List;

public class Team implements Participant {
    private List<User> teamMembers;

    public Team(List<User> members) {
        this.teamMembers = members;
    }

    public Team(User... users) {
        this(List.of(users));
    }

    public boolean isAvailable(final Duration duration, final int numberOfRepresentatives) {
        long numberOfUsersAvailable = teamMembers.stream()
                .filter(user -> user.getAvailability(duration))
                .count();
        return numberOfUsersAvailable >= numberOfRepresentatives;
    }
}
