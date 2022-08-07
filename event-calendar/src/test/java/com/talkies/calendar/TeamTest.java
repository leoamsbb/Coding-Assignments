package com.talkies.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TeamTest {

    @Test
    @DisplayName("Should be able to check availability of N given members of the team")
    void givenATimeSlot_whenANumberNIsInput_thenCheckWhetherAtLeastNMembersAreAvailable() {
        final var team_T2 = getTestTeam_T2();

        assertThat(team_T2.isAvailable(new Duration("10am", "11am"), 2)).isTrue();
        assertThat(team_T2.isAvailable(new Duration("9:30am", "11am"), 2)).isFalse();
    }

    private Team getTestTeam_T2() {
        return new Team(
                new User("9:30am", "5:30pm"),
                new User("11am", "7:30pm"),
                new User("10am", "6pm")
        );
    }
}
