package test;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.MyDate;

public class MyDateTest {
	public static void main(String[] args) {
		System.out.println(MyDate.getCurrentTime());
		System.out.println(MyDate.parse("1999-12-11 16:32:12"));
		MyDate date = new MyDate(00, 43, 12, 3, 1, 2000);
		MyDate date2 = new MyDate(00, 44, 12, 3, 1, 2000);
		System.out.println(date.isBefore(date2));
	}
}
