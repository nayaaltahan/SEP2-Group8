package shared;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyDate implements Serializable {
	private int second;
	private int minute;
	private int hour;
	private int day;
	private int month;
	private int year;

	public MyDate(int second, int minute, int hour, int day, int month, int year) {
		this.second = second;
		this.minute = minute;
		this.hour = hour;
		this.day = day;
		this.month = month;
		this.year = year;

	}

	public static MyDate getCurrentTime() {
		GregorianCalendar gc = new GregorianCalendar();
		return new MyDate(gc.get(Calendar.SECOND), gc.get(Calendar.MINUTE), gc.get(Calendar.HOUR),
				gc.get(Calendar.DAY_OF_MONTH), gc.get(Calendar.MONTH), gc.get(Calendar.YEAR));
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String toString() {
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
	}

	/**
	 * parses a String argument and returns a MyDate object, where the string should
	 * be formatted as following "yyyy-mm-dd hh:mm:ss"
	 * 
	 * @param sDate
	 *            of type String
	 * @return MyDate object.
	 */
	public static MyDate parse(String sDate) {
		String sYear = "";
		for (int i = 0; i < 4; i++) {
			sYear += sDate.charAt(i);
		}
		String sMonth = sDate.charAt(5) + "" + sDate.charAt(6);
		String sDay = sDate.charAt(8) + "" + sDate.charAt(9);
		String sHour = sDate.charAt(11) + "" + sDate.charAt(12);
		String sMinute = sDate.charAt(14) + "" + sDate.charAt(15);
		String sSecond = sDate.charAt(17) + "" + sDate.charAt(18);
		MyDate date = new MyDate(Integer.parseInt(sSecond), Integer.parseInt(sMinute), Integer.parseInt(sHour),
				Integer.parseInt(sDay), Integer.parseInt(sMonth), Integer.parseInt(sYear));
		return date;
	}

	public boolean isBefore(MyDate date2) {
		if (year < date2.getYear()) {
			return true;
		} else if (year > date2.getYear()) {
			return false;
		} else if (month < date2.getMonth()) {
			return true;
		} else if (month > date2.getMonth()) {
			return false;
		} else {
			if (day < date2.getDay()) {
				return true;
			} else if (day > date2.getDay()) {
				return false;
			} else {
				if (hour < date2.getHour()) {
					return true;
				} else if (hour > date2.getHour()) {
					return false;
				} else {
					if (minute < date2.getMinute()) {
						return true;
					} else if (minute > date2.getMinute()) {
						return false;
					} else
						return true;

				}
			}
		}

	}

}
