package com.talkies.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    @DisplayName("Should return true when given duration is within working hours and user does not have any event scheduled")
    void givenUserDoesNotHaveAnyEventsScheduled_whenProvidedDurationWithinWorkingHours_thenUserAvailabilityIsTrue() {
        User user = getUser("10am", "7pm");
        var eventDuration = new Duration("11am", "12pm");
        assertThat(user.getAvailability(eventDuration)).isTrue();
    }

    @Test
    @DisplayName("Should return true when given duration is within working hours and user does not have any event scheduled within that duration")
    void givenUserHasSomeEventsScheduledButInDifferentDuration_whenProvidedDurationWithinWorkingHours_thenUserAvailabilityIsTrue() {
        User user = getUser("10am", "7pm");
        user.blockCalendar(new Duration("2pm", "3pm"));
        var eventDuration = new Duration("11am", "12pm");
        assertThat(user.getAvailability(eventDuration)).isTrue();
    }

    @Test
    @DisplayName("Should return false when given duration is within working hours but user have event scheduled on given duration")
    void givenUserHasAnEventsScheduled_whenProvidedDurationWithinWorkingHoursButOverlappingWithAnotherEvent_thenUserAvailabilityIsFalse() {
        var user = getUser("10am", "7pm");
        user.blockCalendar(new Duration("11am", "12pm"));
        assertThat(user.getAvailability(new Duration("11:30am", "12:30pm"))).isFalse();
    }

    @Test
    @DisplayName("Should return false when user does not event scheduled on given duration but is outside his/her working hours")
    void givenUserDoesNotHaveAnyEvent_whenProvidedDurationOutsideWorkingHours_thenUserAvailablityIsFalse() {
        var user = getUser("10am", "7pm");
        var eventDuration = new Duration("9am", "11am");
        assertThat(user.getAvailability(eventDuration)).isFalse();
    }


    private User getUser(String from, String to) {
        var duration = new Duration(from, to);
        return new User(duration);
    }


}
