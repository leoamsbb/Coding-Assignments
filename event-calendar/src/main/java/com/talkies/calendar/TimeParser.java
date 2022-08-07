package com.talkies.calendar;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

public class TimeParser {

    private static final List<DateTimeFormatter> timeFormatters = List.of(
            getTimeFormatter("hh:mma"),
            getTimeFormatter("h:mma"),
            getTimeFormatter("hha"),
            getTimeFormatter("ha")
    );

    private static DateTimeFormatter getTimeFormatter(final String pattern) {
        return new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern(pattern)
                .toFormatter();
    }
    public static LocalTime getTime(String strTime) {
        var replacedAllSpaces = strTime.replace(" ", ""); // to effectively parse time with single pattern
        return timeFormatters.stream()
                .flatMap(formatter -> {
                    try {
                        return Optional.of(LocalTime.parse(replacedAllSpaces, formatter)).stream();
                    } catch (DateTimeParseException dateTimeParseException) {
                        return Optional.<LocalTime>empty().stream();
                    }
                })
                .findFirst()
                .orElseThrow(() -> new DateTimeException("Invalid Input, format not supported yet"));
    }


}
