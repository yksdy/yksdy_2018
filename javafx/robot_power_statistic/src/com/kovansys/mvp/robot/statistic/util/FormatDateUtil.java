package com.kovansys.mvp.robot.statistic.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDateUtil {
	public static String formatDate2yMdHmsS(String times) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String currentTime;
		long time = Long.parseLong(times);
		Date date = new Date(time);
		currentTime = sdf.format(date);
		return currentTime;
	}
	public static void main(String[] args) {
		System.out.println(formatDate2yMdHmsS("1334404801000"));
		System.out.println(formatDate2yMdHmsS("9000000000000"));
		System.out.println(formatDate2yMdHmsS("9900000000000"));
	}
}
