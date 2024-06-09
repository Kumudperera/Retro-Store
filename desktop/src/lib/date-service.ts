import moment, { min } from "moment";
import { Moment } from "moment";

const defaultDateFormat = "DD/MM/YYYY";
const defaultDateTimeFormat = "DD/MM/YYYY HH:mm:ss";
const litaralDateTimeMeridiemFormat = "MMMM Do YYYY, h:mm:ss a";
const litaralDateFormat = "MMMM Do YYYY";

export function getCalendarMaxDate(): Moment {
  return moment("9999-12-31");
}

export function getCalendarMinDate(): Moment {
  return moment("1900-01-01");
}

export function getDefaultFormattedDate(date: Moment): string {
  return moment(date).format(defaultDateFormat);
}

export function getDefaultFormattedDateTime(date: Moment): string {
  return moment(date).format(defaultDateTimeFormat);
}

export function getMomentDateFromLitaralDateStr(dateStr: string): string {
  return moment(dateStr).format(litaralDateFormat);
}

export function getMomentDateFromDateStr(dateStr: string): Moment {
  return moment(dateStr, defaultDateFormat);
}

export function getMomentDateFromDateTimeStr(dateStr: string): Moment {
  return moment(dateStr, defaultDateTimeFormat);
}

export function getMonthStartDate(date: Moment): Moment {
  return moment(date).startOf("month");
}

export function getMonthEndDateStr(date: Moment): string {
  return moment(date).endOf("month").format(defaultDateFormat);
}

export function getMonthStartDateStr(date: Moment): string {
  return moment(date).startOf("month").format(defaultDateFormat);
}

export function getMonthEndDate(date: Moment): Moment {
  return moment(date).endOf("month");
}

export function plusYearForTheDate(date: Moment): Moment {
  return moment(date).add(12, "months");
}

export function addYearsForTheDate(date: Moment, yearCount: number): Moment {
  return moment(date).add(yearCount, "years");
}

export function diffBetweenTwoDays(fromDate: Moment, toDate: Moment): any {
  return moment(fromDate).diff(toDate);
}

export function getJsDate(date: string) {
  return getMomentDateFromDateStr(date).toDate();
}

export function getMonthNameWithYear(date: Moment) {
  return moment(date).format("MMMM YYYY");
}

export function getNexMonthStartDateMoment(date: Moment, noOfMonths: number) {
  return moment(date).startOf("month").add(noOfMonths, "months");
}

export function getJSDateDifference(date1: Date, date2: Date) {
  return moment(date2).diff(moment(date1), "days");
}

export function getMomentDateFromJSDate(date: Date) {
  return moment(date);
}

export function getDefaultFormattedStringFromJSDate(date: Date) {
  return moment(date).format(defaultDateFormat);
}

export function getNow(): Moment {
  return moment();
}

export function isSameOrBefore(fromDate: string, toDate: string) {
  return getMomentDateFromDateStr(fromDate).isSameOrBefore(
    getMomentDateFromDateStr(toDate),
  );
}

export function getDateRangeArray(
  fromDateStr: string,
  toDateStr: string,
): Array<string> {
  let result = [];
  let fromDate = getMomentDateFromDateStr(fromDateStr);
  let toDate = getMomentDateFromDateStr(toDateStr);
  let dateDiff = toDate.diff(fromDate, "days");

  for (let i = 0; i < dateDiff; i++) {
    let nextDate = moment(fromDate).add(i, "days");
    result.push(getDefaultFormattedDate(nextDate));
  }
  result.push(toDateStr);

  return result;
}

export function isFutureDate(date: string) {
  const momentDate: Moment = moment(date);
  return momentDate.isAfter(moment());
}

export function isPastDate(
  date: string,
  granularity: "year" | "month" | "day" | "second" | undefined,
) {
  const momentDate: Moment = moment(date);
  return momentDate.isBefore(moment(), granularity);
}

export function isAfterDate(date1: string, date2: string) {
  const momentDate1: Moment = moment(date1);
  const momentDate2: Moment = moment(date2);
  return momentDate2.isAfter(momentDate1);
}
