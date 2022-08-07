package com.talkies.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.stream.Stream;

import static com.talkies.calendar.TimeParser.getTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TimeParserTest {

    @ParameterizedTest(name = "[{index}] - for {0}")
    @MethodSource("getAllFormats_whenHoursAndMinutesAreBothPresent_AM")
    @DisplayName("AM - should parse time correctly when the input string contains hours and minutes")
    void givenBothHoursAndMinutes_whenTimesAreProvided_thenShouldCreateCorrectLocalTime_AM(final String input, final int expectedHours, final int expectedMins) {
        verifyTime(getTime(input), expectedHours, expectedMins);
    }

    private static Stream<Arguments> getAllFormats_whenHoursAndMinutesAreBothPresent_AM() {
        return Stream.of(
                arguments("10:30 am", 10, 30),
                arguments("10:30 AM", 10, 30),
                arguments("10:30am", 10, 30),
                arguments("10:30AM", 10, 30),
                arguments("1:25 am", 1, 25),
                arguments("1:25 AM", 1, 25),
                arguments("1:25am", 1, 25),
                arguments("1:25AM", 1, 25)
        );
    }

    @ParameterizedTest(name = "[{index}] - for {0}")
    @MethodSource("getAllFormats_whenHoursAndMinutesAreBothPresent_PM")
    @DisplayName("PM - should parse time correctly when the input string contains hours and minutes")
    void givenBothHoursAndMinutes_whenTimesAreProvided_thenShouldCreateCorrectLocalTime_PM(final String input, final int expectedHours, final int expectedMins) {
        verifyTime(getTime(input), expectedHours, expectedMins);
    }

    private static Stream<Arguments> getAllFormats_whenHoursAndMinutesAreBothPresent_PM() {
        return Stream.of(
                arguments("10:30 pm", 22, 30),
                arguments("10:30 PM", 22, 30),
                arguments("10:30pm", 22, 30),
                arguments("10:30PM", 22, 30),
                arguments("1:25 pm", 13, 25),
                arguments("1:25 PM", 13, 25),
                arguments("1:25pm", 13, 25),
                arguments("1:25PM", 13, 25)
        );
    }

    @ParameterizedTest(name = "[{index}] for input - {0}")
    @MethodSource("getAllFormatsFor_DoubleDigitHours_AM")
    @DisplayName("AM - should parse time correctly when no minutes are present and hours in double digits")
    void whenNoMinutesArePresentInInput_thenReturnCorrectTime_AM(final String input, final int expectedHours) {
        verifyTime(getTime(input), expectedHours);
    }

    private static Stream<Arguments> getAllFormatsFor_DoubleDigitHours_AM() {
        return Stream.of(
                arguments("10am", 10),
                arguments("10AM", 10),
                arguments("10 am", 10),
                arguments("10 AM", 10)
        );
    }

    @ParameterizedTest(name = "[{index}] for input - {0}")
    @MethodSource("getAllFormatsFor_DoubleDigitHours_PM")
    @DisplayName("PM - should parse time correctly when no minutes are present and hours in double digits")
    void whenNoMinutesArePresentInInput_thenReturnCorrectTime_PM(final String input, final int expectedHours) {
        verifyTime(getTime(input), expectedHours);
    }

    private static Stream<Arguments> getAllFormatsFor_DoubleDigitHours_PM() {
        return Stream.of(
                arguments("10 pm", 22),
                arguments("10 PM", 22),
                arguments("10pm", 22),
                arguments("10PM", 22)
        );
    }


    @ParameterizedTest(name = "[{index}] for input - {0}")
    @MethodSource("getAllFormatsFor_SingleDigitHours_AM")
    @DisplayName("AM - should parse time correctly when no minutes are present and hours in single digits")
    void givenNoMinutesArePresentInInput_whenHoursInSingleDigit_thenReturnCorrectTime_AM(final String input, final int expectedHours) {
        verifyTime(getTime(input), expectedHours);
    }

    private static Stream<Arguments> getAllFormatsFor_SingleDigitHours_AM() {
        return Stream.of(
                arguments("1am", 1),
                arguments("1AM", 1),
                arguments("1 am", 1),
                arguments("1 AM", 1)
        );
    }

    @ParameterizedTest(name = "[{index}] for input - {0}")
    @MethodSource("getAllFormatsFor_SingleDigitHours_PM")
    @DisplayName("PM - should parse time correctly when no minutes are present and hours in single digits")
    void givenNoMinutesArePresentInInput_whenHoursInSingleDigit_thenReturnCorrectTime_PM(final String input, final int expectedHours) {
        verifyTime(getTime(input), expectedHours);
    }

    private static Stream<Arguments> getAllFormatsFor_SingleDigitHours_PM() {
        return Stream.of(
                arguments("1 pm", 13),
                arguments("1 PM", 13),
                arguments("1pm", 13),
                arguments("1PM", 13)
        );
    }

    private void verifyTime(LocalTime time, int expectedHours) {
        assertThat(time.getHour()).isEqualTo(expectedHours);
        assertThat(time.getMinute()).isZero();
        assertThat(time.getSecond()).isZero();
        assertThat(time.getNano()).isZero();
    }

    private void verifyTime(LocalTime time, int expectedHours, int expectedMins) {
        assertThat(time.getHour()).isEqualTo(expectedHours);
        assertThat(time.getMinute()).isEqualTo(expectedMins);
        assertThat(time.getSecond()).isZero();
        assertThat(time.getNano()).isZero();
    }
}
