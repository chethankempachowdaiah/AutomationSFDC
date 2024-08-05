package com.framework.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtilities {

	/**
	 * @param format
	 * @return currentdate in the given format
	 * 
	 *         This Function Will take the given fromat as input and return the
	 *         System date in given fomat
	 */
	public static String currentSystemDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		return currentDate;
	}

	public static String getFormattedStringDate(String currentFormat, String newFormat, String date)
			throws ParseException {
		DateFormat to = new SimpleDateFormat(newFormat); // new format
		DateFormat from = new SimpleDateFormat(currentFormat); // current format
		String formattedDate = to.format(from.parse(date));
		return formattedDate;
	}

	/**
	 * @param days
	 * @param format
	 * @return the Plused date with current system date in the given format
	 * 
	 *         For Eaxmaple, if todays date 10/October/2019, Then we want to add 4
	 *         more days to todays date , Then this function will return
	 *         14/October/2019
	 *         
	 *         dd/MM/yyyy : represent 14/10/2019
	 *         dd/MMM/yyyy: represent 14/Oct/2019
	 */ 
	public static String currentSystemDatePlus(int days, String format) {
		String currentDate = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(format); // dd/MM/yyyy
			Date date = new Date();
			currentDate = dateFormat.format(date);
			Calendar c = Calendar.getInstance();
			c.setTime(dateFormat.parse(currentDate));
			c.add(Calendar.DATE, days); // number of days to add
			currentDate = dateFormat.format(c.getTime());
			return currentDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentDate;
	}

	/**
	 * @param days
	 * @param format
	 * @return the Minused date with current system date in the given format
	 * 
	 *         For Eaxmaple, if todays date 10/October/2019, Then we want to go back
	 *         to 4 days back wrt todays date , Then this function will return
	 *         06/October/2019
	 */
	public static String currentSystemDateMinus(int days, String format) {
		String currentDate = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(format); // dd/MM/yyyy
			Date date = new Date();
			currentDate = dateFormat.format(date);
			Calendar c = Calendar.getInstance();
			c.setTime(dateFormat.parse(currentDate));
			c.add(Calendar.DATE, -days); // number of days to add
			currentDate = dateFormat.format(c.getTime());
			return currentDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentDate;
	}

	/**
	 * For Exmaple: If today is 02/march/2019, then This function will return 2
	 * 
	 * @return the day of month
	 */
	public static int getTodaysDay() {
		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		return dayOfMonth;
	}

	/**
	 * For Exmaple: If today is 02/march/2019, then This function will return 3
	 * 
	 * @return the current month
	 */
	public static int getTodaysMonth() {
		Calendar cal = Calendar.getInstance();
		int month = (cal.get(Calendar.MONTH) + 1);
		return month;
	}

	/**
	 * For Exmaple: If today is 02/march/2019, then This function will return 2019
	 * 
	 * @return the current Year
	 */
	public static int getTodaysYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	/**
	 * @param start
	 * @param end
	 * @param dateFormat
	 * @return
	 * 
	 *         HH converts hour in 24 hours format (0-23), day calculation
	 * 
	 *         Calculate the time difference between 2 given timestamps
	 * 
	 */
	public static String timeDuration(String start, String end, String dateFormat) {
		SimpleDateFormat sf = new SimpleDateFormat(dateFormat);

		Date d1 = null;
		Date d2 = null;

		long diffSeconds = 0;
		long diffMinutes = 0;
		long diffHours = 0;
		long diffDays = 0;

		try {
			d1 = sf.parse(start);
			d2 = sf.parse(end);

			// in milliseconds
			long diff = d2.getTime() - d1.getTime();

			diffSeconds = diff / 1000 % 60;
			diffMinutes = diff / (60 * 1000) % 60;
			diffHours = diff / (60 * 60 * 1000) % 24;
			diffDays = diff / (24 * 60 * 60 * 1000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return diffDays + " days_" + diffHours + " hours_" + diffMinutes + " minutes_" + diffSeconds + " seconds";

	}

}
