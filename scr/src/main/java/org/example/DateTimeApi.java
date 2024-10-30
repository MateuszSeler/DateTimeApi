package org.example;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeApi {

  /**
   * Return the current date as a String depending on a query.
   * <p>
   * The query can be passed for the whole date or for it's part:
   * - FULL - current date as a whole: year, month, day of month
   * formatted as `YYYY-MM-DD` (a default return value);
   * - YEAR - current year;
   * - MONTH - name of the current month;
   * - DAY - current day of month;
   * In any other case throw DateTimeException.
   **/
  public String todayDate(DateTimePart datePart) {
    LocalDate currentDate = LocalDate.now();
    String year = "" + currentDate.getYear();
    String month = "" + currentDate.getMonth();
    String day = "" + currentDate.getDayOfMonth();


    switch (datePart) {
      case FULL:
        return currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      
      case YEAR:
        return year;

      case MONTH:
        return month;

      case DAY:
        return day;
      
      default:
        throw new DateTimeException("Date Time Exception");

    }
  }

  /**
   * Given an Array of 3 elements, where
   * - 1-st element is a `year`;
   * - 2-nd element is s `month`;
   * - 3-rd element is a `day of month`;
   * <p>
   * Return LocalDate built from these elements. If Array contains more than 3 elements - throw DateTimeException.
   */
  public LocalDate getDate(Integer[] dateParams) {
    if (dateParams != null && dateParams.length == 3) {
      LocalDate currentDate = LocalDate.of(dateParams[0], dateParams[1], dateParams[2]);
      return currentDate;

    } else {
      throw new DateTimeException("Date Time Exception");
    }
  }

  /**
   * Given the time and the number of hours to add, return the changed time.
   */
  public LocalTime addHours(LocalTime localTime, Integer hoursToAdd) {
    return localTime.plusHours(hoursToAdd);
  }

  /**
   * Given the time and the number of minutes to add, return the changed time.
   */
  public LocalTime addMinutes(LocalTime localTime, Integer minutesToAdd) {
    return localTime.plusMinutes(minutesToAdd);
  }

  /**
   * Given the time and the number of seconds to add, return the changed time.
   */
  public LocalTime addSeconds(LocalTime localTime, Integer secondsToAdd) {
    return localTime.plusSeconds(secondsToAdd);
  }

  /**
   * Given the date and the number of weeks to add, return the changed date.
   */
  public LocalDate addWeeks(LocalDate localDate, Integer numberOfWeeks) {
    return localDate.plusWeeks(numberOfWeeks);
  }

  /**
   * Given a random `someDate` date, return one of the following Strings:
   * - "`someDate` is after `currentDate`"
   * if `someDate` is in the future relating to the `current date`;
   * - "`someDate` is before `currentDate`"
   * if `someDate` is in the past relating to the `current date`;
   * - "`someDate` is today"
   * if `someDate` is today;
   */
  public String beforeOrAfter(LocalDate someDate) {
    if (someDate.isAfter(LocalDate.now())) {
      return someDate + " is after " + LocalDate.now();
    } else if (someDate.isBefore(LocalDate.now())) {
      return someDate + " is before " + LocalDate.now();
    } else if (someDate.isEqual(LocalDate.now())) {
      return someDate + " is today";
    }

    throw new RuntimeException("something goes wrong with someDate input");
  }

  /**
   * Given a String representation of a date and some timezone,
   * return LocalDateTime in this timezone.
   */
  public LocalDateTime getDateInSpecificTimeZone(String dateInString, String zone) {
    return Instant.parse(dateInString).atZone(ZoneId.of(zone)).toLocalDateTime();
  }

  /**
   * Given some LocalDateTime, return an OffsetDateTime with the local time offset applied
   * (`+02:00` for Ukraine). Note that for Ukraine, the offset could be `+02:00` or `+03:00`, depending on whether daylight saving time is in effect for the provided date and time. So for this task, we assume that in Ukraine we always are using `+02:00` timezone
   * <p>
   * Example: we receive a LocalDateTime with a value `2019-09-06T13:17`.
   * We should return the OffsetDateTime with a value `2019-09-06T13:17+02:00`,
   * where `+02:00` is the offset for our local timezone.
   * <p>
   * OffsetDateTime is recommended to use for storing date values in a database.
   */
  public OffsetDateTime offsetDateTime(LocalDateTime localTime) {
    return ZonedDateTime.of(localTime, ZoneId.systemDefault()).toOffsetDateTime();
  }

  /**
   * Given a String formatted as `yyyyMMdd`,
   * return LocalDate object built from this String.
   */
  public LocalDate parseDate(String date) {
    int year = Integer.parseInt(date.substring(0, 4));
    Month mouth = Month.of(Integer.parseInt(date.substring(4, 6)));
    int day = Integer.parseInt(date.substring(6));

    return LocalDate.of(year, mouth,day);
  }

  /**
   * Given a String formatted as `d MMM yyyy` (MMM - Sep, Oct, etc),
   * return LocalDate object built from this String.
   */
  public LocalDate customParseDate(String date) {
    String[] dateArray = date.split(" ");

    int mouthNumber = dateArray[1].equals("Jan") ? mouthNumber = 1
            : dateArray[1].equals("Feb") ? mouthNumber = 2
            : dateArray[1].equals("Mar") ? mouthNumber = 3
            : dateArray[1].equals("Apr") ? mouthNumber = 4
            : dateArray[1].equals("May") ? mouthNumber = 5
            : dateArray[1].equals("Jun") ? mouthNumber = 6
            : dateArray[1].equals("Jul") ? mouthNumber = 7
            : dateArray[1].equals("Aug") ? mouthNumber = 8
            : dateArray[1].equals("Sep") ? mouthNumber = 9
            : dateArray[1].equals("Oct") ? mouthNumber = 10
            : dateArray[1].equals("Nov") ? mouthNumber = 11
            : 12;

    int year = Integer.parseInt(dateArray[2]);
    int mouth = mouthNumber;
    int day = Integer.parseInt(dateArray[0]);

    return LocalDate.of(year, mouth, day);
  }

  /**
   * Given some LocalDateTime, return a String formatted as
   * `day(2-digit) month(full name in English) year(4-digit) hours(24-hour format):minutes`.
   * <p>
   * Example: "01 January 2000 18:00".
   */
  public String formatDate(LocalDateTime dateTime) {

    return dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"));
  }
}
 