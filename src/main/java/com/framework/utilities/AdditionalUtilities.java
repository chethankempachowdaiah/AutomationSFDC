package com.framework.utilities;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class AdditionalUtilities {

	public static String formatString(String str) {
		String text = str.trim().replaceAll("\u0000.*", "").replaceAll("[^a-zA-Z0-9 ]", "").replaceAll("\\s", "");
		return text;
	}

	public static String generateRandomDigits(int n) {
		int m = (int) Math.pow(10, n - 1);
		return String.valueOf(m + new Random().nextInt(9 * m));
	}
	
	public static String generateRandomDigit(int n) {
		Random rand=new Random();
		int num1=(int) Math.pow(10, n);
		int m = (int) Math.pow(10, n - 1);
		return String.valueOf(m + new Random().nextInt(9 * m));
	}
	
	public static double roundOffDouble2DecimalPlaces(double inputDouble) {
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.parseDouble(df.format(inputDouble));
	}

	public static String getYesterdaysDate() throws Exception {
		String yestDate = null;
		try {
			yestDate = DateTimeUtilities.currentSystemDateMinus(1, "MM/dd/yyyy");
			return yestDate;
		} catch (Exception e) {
			e.printStackTrace();
			return yestDate;
		}
	}

	public static String CurrentSystemDate_Time() {
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		df.setTimeZone(TimeZone.getTimeZone("EST"));
		String dateTimeString = df.format(new Date());
		return dateTimeString;

	}

	public static String currentSystemTime(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		return currentDate;
	}

	public static String currentSystemTimePlus(int mins) {
		String currentDate = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Date date = new Date();
			currentDate = dateFormat.format(date);
			Calendar c = Calendar.getInstance();
			c.setTime(dateFormat.parse(currentDate));
			c.add(Calendar.MINUTE, mins); // number of days to add
			currentDate = dateFormat.format(c.getTime());
			return currentDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentDate;
	}

	public static String currentSystemTimeMinus(int mins) {
		String currentDate = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Date date = new Date();
			currentDate = dateFormat.format(date);
			Calendar c = Calendar.getInstance();
			c.setTime(dateFormat.parse(currentDate));
			c.add(Calendar.MINUTE, -mins); // number of days to add
			currentDate = dateFormat.format(c.getTime());
			return currentDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentDate;
	}

	public static String getTodaysDate() throws Exception {
		String day, month, year;
		day = Integer.toString(DateTimeUtilities.getTodaysDay());
		month = Integer.toString(DateTimeUtilities.getTodaysMonth());
		year = Integer.toString(DateTimeUtilities.getTodaysYear());
		String finalDate = month + "/" + day + "/" + year;
		return dateFormatter(finalDate);
	}

	public static String dateFormatter(String date) {
		String dutyDate = date;
		String[] splDutyDate = dutyDate.split("/");
		for (int i = 0; i < (splDutyDate.length - 1); i++) {
			if (splDutyDate[i].length() == 1) {
				splDutyDate[i] = "0" + splDutyDate[i];
			}
		}
		if (splDutyDate[2].length() == 2) {
			splDutyDate[2] = "20" + splDutyDate[2];
		}
		System.out.println(splDutyDate[1] + "/" + splDutyDate[0] + "/" + splDutyDate[2]);
		return splDutyDate[0] + "/" + splDutyDate[1] + "/" + splDutyDate[2];
	}

	public static <T> ArrayList<String> convertSetToList(Set<T> set) {

		ArrayList<String> list = new ArrayList<String>();
		for (T t : set)
			list.add((String) t);
		return list;
	}

	public static String generateRandomCharacters(int n) {
		return RandomStringUtils.random(n, true, false);
	}
	
	public static String generateRandomNumbers(int n) {
		return RandomStringUtils.random(n, false, true);
	}

	public static String getFileName(String filePath) {
		return FilenameUtils.getName(filePath);
	}

	public static String getAsString(List<String> list) {
		return Arrays.toString(list.toArray());
	}

	public static String formatAsPhoneNumber(String input) {
		input = input.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
		return input;
	}

}
