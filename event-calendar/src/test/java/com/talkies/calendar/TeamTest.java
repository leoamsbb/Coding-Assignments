package com.talkies.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class TeamTest {

    @Test
    @DisplayName("Should be able to create a team of users")
    void givenListOfUsers_thenCreateATeam() {
        final var a = new User("10am", "7pm");
        final var b = new User("9am", "7pm");

        final var team = new Team(List.of(a, b));
    }
}
