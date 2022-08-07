package com.talkies.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    @DisplayName("Should return the list of participants")
    void getParticipantsShouldReturnListOfParticipants() {
        List<Participant> participants = List.of(new Participant() {
        });
        Event event = new Event(participants, "10:00", "11:00", 1);

        List<Participant> result = event.getParticipants();

        assertEquals(participants, result);
    }

    @Test
    @DisplayName("Should create an event on given details")
    void givenEventDetails_thenCreateAnEvent() {
        final var a = new User("10am", "7pm");

        final var b = new User("10am", "7pm");
        final var c = new User("9am", "7pm");

        final var team = new Team(List.of(c, b));

        final var participants = List.of(a, team);

        final var event = new Event(participants, "2PM", "3PM", 2);

        assertThat(event.getParticipants()).hasSize(2);

        assertThat(event.getParticipants()).containsExactlyInAnyOrderElementsOf(participants);

        assertThat(event.getStartTime()).isEqualTo("2PM");
        assertThat(event.getEndTime()).isEqualTo("3PM");

        assertThat(event.getRepresentatives()).isEqualTo(2);
    }
}
