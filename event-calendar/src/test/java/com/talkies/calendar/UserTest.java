package com.talkies.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @Test
    @DisplayName("Should be able to create user with working duration")
    void whenProvidedWorkingHours_thenCreateUserWithWorkingHours() {
        var user = getUser("10am", "7pm");

        assertThat(user.getFrom()).isEqualTo("10am");
        assertThat(user.getTo()).isEqualTo("7pm");
    }

    @Test
    @DisplayName("Should return true when user does not have any event scheduled")
    void whenProvidedDuration_thenGiveUserAvailability() {
        User user = getUser("10am", "7pm");
        var eventDuration = new Duration("11am", "12pm");
        assertThat(user.getAvailability(eventDuration)).isTrue();
    }

    @Test
    @DisplayName("Should return false when user have event scheduled on given duration")
    void givenUserAlreadyHasAnEvent_whenProvidedDuration_thenGiveUserAvailability() {
        var user = getUser("10am", "7pm");
        var eventDuration = new Duration("11am", "12pm");
        user.blockCalendar(eventDuration);
        assertThat(user.getAvailability(eventDuration)).isFalse();
    }

    private User getUser(String from, String to) {
        var duration = new Duration(from, to);
        return new User(duration);
    }


}
