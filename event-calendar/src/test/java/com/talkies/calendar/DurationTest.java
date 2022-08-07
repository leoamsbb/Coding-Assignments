package com.talkies.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DurationTest {

    Duration testDuration = new Duration("10am", "2pm");

    @ParameterizedTest(name = "[{index}] for {0}")
    @MethodSource("getOverlappingDurations")
    @DisplayName("Should return true if duration overlaps")
    void whenProvidedDuration_thenReturnTrueIfOverlaps(String testName, Duration otherDuration) {
        assertThat(testDuration.overlaps(otherDuration)).isTrue();
    }

    private static Stream<Arguments> getOverlappingDurations() {
        return Stream.of(
                arguments("1pm - 2pm", new Duration("1pm", "2pm")),
                arguments("10:30am - 2:30pm", new Duration("10:30am", "2:30pm")),
                arguments("10am - 2pm", new Duration("10am", "2pm")),
                arguments("10am - 11am", new Duration("10am", "11am")),
                arguments("9am - 5pm", new Duration("9am", "5pm")),
                arguments("10am - 5pm", new Duration("10am", "5pm"))
        );
    }

    @ParameterizedTest(name = "[{index}] for {0}")
    @MethodSource("getNonOverlappingDurations")
    @DisplayName("Should return false if duration does not overlap")
    void whenProvidedDuration_thenReturnFalseIfOverlaps(String testName, Duration otherDuration) {
        assertThat(testDuration.overlaps(otherDuration)).isFalse();
    }

    private static Stream<Arguments> getNonOverlappingDurations() {
        return Stream.of(
                arguments("9am - 10am", new Duration("9am", "10am")),
                arguments("2pm - 5pm", new Duration("2pm", "5pm")),
                arguments("3pm - 5pm", new Duration("3pm", "5pm"))
        );
    }

    @Test
    @DisplayName("Should return true if given duration is within this duration")
    void whenGivenDurationIsWithinThisDuration_thenReturnTrue() {
        assertThat(testDuration.within(new Duration("9am", "3pm"))).isTrue();
        assertThat(testDuration.within(new Duration("11am", "1pm"))).isFalse();
    }
}