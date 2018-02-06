package com.example.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



public class DateUtil {

	private static final int[] dayArray = new int[] { 31, 28, 31, 30, 31, 30,
			31, 31, 30, 31, 30, 31 };

	private static SimpleDateFormat sdf = new SimpleDateFormat();

	public static final long SECOND = 1000;

	public static final long MINUTE = 60 * SECOND;

	public static final long HOUR = 60 * MINUTE;

	public static final long DAY = 24 * HOUR;

	public static final long WEEK = 7 * DAY;

	/**
	 * ����1970��1��1���ж�����
	 * 
	 * @return long
	 */
	public static long dayMill() {
		// System.out.print(System.currentTimeMillis());
		return (System.currentTimeMillis()) / (DAY);
	}

	/**
	 * ����1970��1��1������
	 * 
	 * @return long
	 */
	public static long weekMill() {

		//System.out.print(dayMill() / 7);
		return dayMill() / 7;
	}

	public static synchronized String getDateHour(java.util.Date date) {
		String pattern = "HH";
		return getDateFormat(date, pattern);
	}

	public static synchronized String getDateMinute(java.util.Date date) {
		String pattern = "mm";
		return getDateFormat(date, pattern);
	}

	public static synchronized String getDateSecond(java.util.Date date) {
		String pattern = "ss";
		return getDateFormat(date, pattern);
	}

	public static synchronized Calendar getCalendar() {
		return GregorianCalendar.getInstance();
	}

	public static Date getCurrentTime() {
		Calendar ca = Calendar.getInstance();
		Date a = new Date();
		a.setTime(ca.getTimeInMillis());
		return a;
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateMilliFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateMilliFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateMilliFormat(java.util.Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateMilliFormat(java.util.Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return getDateFormat(date, pattern);
	}
	
	public static synchronized String getDateMinuteFormat100(java.util.Date date) {
		String pattern = "yyMMddHHmmss";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDatePathFormat(java.util.Date date) {
		String pattern = "yyyy-MM-dd-HH-mm-ss-SSS";
		return getDateFormat(date, pattern);
	}
	
	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDatePathFormatWithRandom(java.util.Date date) {
		String pattern = "yyyyMMddHHmmss";
		String rs = getDateFormat(date, pattern);
		rs += "_"+GenerateRandomStr.generateRandomString(5);
		return rs;
	}
	
	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDatePathFormatWithOutRandom(java.util.Date date) {
		String pattern = "yyyyMMddHHmmss";
		String rs = getDateFormat(date, pattern);
		return rs;
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarMilliFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateMilliFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseSQLDate(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";

		return parseDateFormat(strDate, pattern);
	}

	public static synchronized String getDateSecondFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateSecondFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateSecondFormat(java.util.Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateSecondFormat(java.util.Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(date, pattern);
	}

	// ���Ŷ�ʱ¼���ʱ���ʽ��YYYY-MM-DD-W-HH-MM������W����0���
	public static synchronized String getDateSecondTeleFormat(
			java.util.Date date) {
		String pattern = "yyyy-MM-dd-0-HH-mm";
		return getDateFormat(date, pattern);
	}

	public static synchronized String getDate(java.util.Date date) {
		String pattern = "yyyy-MM-dd";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarSecondFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateSecondFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseDateFormat(strDate, pattern);
	}
	
	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateSecondFormat2(String strDate) {
		String pattern = "yyyyMMddHHmmss";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateMinuteFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateMinuteFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateMinuteFormat(java.util.Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateMinuteFormat(java.util.Date date) {
		String pattern = "yyyy-MM-dd HH:mm";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarMinuteFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm";
		return parseCalendarFormat(strDate, pattern);
	}

	public static Calendar DateToCalendar(Date d) {
		//System.out.println("Date::" + d);
		Calendar ca = Calendar.getInstance();
		ca.setTime(d);
		//System.out.println("Calendar::" + ca.getTime());
		return ca;
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateMinuteFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateDayFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateDayFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateDayFormat(java.util.Calendar cal) {
		String pattern = "yyyy-MM-dd";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateDayFormat(java.util.Date date) {
		String pattern = "yyyy-MM-dd";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarDayFormat(String strDate) {
		String pattern = "yyyy-MM-dd";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateDayFormat(String strDate) {
		String pattern = "yyyy-MM-dd";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateFileFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateFileFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateFileFormat(java.util.Calendar cal) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateFileFormat(java.util.Date date) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateFileFormatStringPU(
			java.util.Date date) {
		String pattern = "yyyy-MM-dd-HH-mm-ss";
		return getDateFormat(date, pattern);
	}

	public static synchronized String getDateFileFormatStringPUtiming(
			java.util.Date date) {
		String pattern = "yyyy-MM-dd-0-HH-mm";
		return getDateFormat(date, pattern);
	}

	public static synchronized String getDateFileFormatString(
			java.util.Date date) {
		String pattern = "HH-mm-ss";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarFileFormat(String strDate) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateFileFormat(String strDate) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return parseDateFormat(strDate, pattern);
	}

	public static synchronized Date parseDateFileFormatPU(String strDate) {
		String pattern = "yyyy-MM-dd-HH-mm-ss";
		return parseDateFormat(strDate, pattern);
	}

	public static synchronized Date parseDateFileFormatPUtiming(String strDate) {
		String pattern = "yyyy-MM-dd-0-HH-mm";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateFileFormatString(String strDate) {
		String pattern = "HH-mm-ss";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateW3CFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateW3CFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateW3CFormat(java.util.Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateW3CFormat(java.util.Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarW3CFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateW3CFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateFormat(java.util.Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateFormat(java.util.Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @param cal
	 * @param pattern
	 * @return String
	 */
	public static synchronized String getDateFormat(java.util.Calendar cal,
			String pattern) {
		return getDateFormat(cal.getTime(), pattern);
	}

	/**
	 * @param date
	 * @param pattern
	 * @return String
	 */
	public static synchronized String getDateFormat(java.util.Date date,
			String pattern) {
		synchronized (sdf) {
			String str = null;
			sdf.applyPattern(pattern);
			str = sdf.format(date);
			return str;
		}
	}

	/**
	 * @param strDate
	 * @param pattern
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarFormat(String strDate,
			String pattern) {
		synchronized (sdf) {
			Calendar cal = null;
			sdf.applyPattern(pattern);
			try {
				sdf.parse(strDate);
				cal = sdf.getCalendar();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cal;
		}
	}

	/**
	 * @param strDate
	 * @param pattern
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateFormat(String strDate,
			String pattern) {
		synchronized (sdf) {
			Date date = null;
			sdf.applyPattern(pattern);
			try {
				date = sdf.parse(strDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return date;
		}
	}

	public static synchronized int getLastDayOfMonth(int month) {
		if (month < 1 || month > 12) {
			return -1;
		}
		int retn = 0;
		if (month == 2) {
			if (isLeapYear()) {
				retn = 29;
			} else {
				retn = dayArray[month - 1];
			}
		} else {
			retn = dayArray[month - 1];
		}
		return retn;
	}

	public static synchronized int getLastDayOfMonth(int year, int month) {
		if (month < 1 || month > 12) {
			return -1;
		}
		int retn = 0;
		if (month == 2) {
			if (isLeapYear(year)) {
				retn = 29;
			} else {
				retn = dayArray[month - 1];
			}
		} else {
			retn = dayArray[month - 1];
		}
		return retn;
	}

	public static synchronized boolean isLeapYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return isLeapYear(year);
	}

	public static synchronized boolean isLeapYear(int year) {
		/**
		 * ��ϸ��ƣ� 1.��400���������꣬���� 2.���ܱ�4������������ 3.�ܱ�4����ͬʱ���ܱ�100������������
		 * 3.�ܱ�4����ͬʱ�ܱ�100������������
		 */
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * �ж�ָ�����ڵ�����Ƿ�������
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return �Ƿ�����
	 */
	public static synchronized boolean isLeapYear(java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.��400���������꣬���� 2.���ܱ�4������������ 3.�ܱ�4����ͬʱ���ܱ�100������������
		 * 3.�ܱ�4����ͬʱ�ܱ�100������������
		 */
		// int year = date.getYear();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		int year = gc.get(Calendar.YEAR);
		return isLeapYear(year);
	}

	public static synchronized boolean isLeapYear(java.util.Calendar gc) {
		/**
		 * ��ϸ��ƣ� 1.��400���������꣬���� 2.���ܱ�4������������ 3.�ܱ�4����ͬʱ���ܱ�100������������
		 * 3.�ܱ�4����ͬʱ�ܱ�100������������
		 */
		int year = gc.get(Calendar.YEAR);
		return isLeapYear(year);
	}

	/**
	 * �õ�ָ�����ڵ�ǰһ��������
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ�ǰһ��������
	 */
	public static synchronized java.util.Date getPreviousWeekDay(
			java.util.Date date) {
		{
			/**
			 * ��ϸ��ƣ� 1.���date�������գ����3�� 2.���date�������������2�� 3.�����1��
			 */
			GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
			gc.setTime(date);
			return getPreviousWeekDay(gc);
			// switch ( gc.get( Calendar.DAY_OF_WEEK ) )
			// {
			// case ( Calendar.MONDAY ):
			// gc.add( Calendar.DATE, -3 );
			// break;
			// case ( Calendar.SUNDAY ):
			// gc.add( Calendar.DATE, -2 );
			// break;
			// default:
			// gc.add( Calendar.DATE, -1 );
			// break;
			// }
			// return gc.getTime();
		}
	}

	public static synchronized java.util.Date getPreviousWeekDay(
			java.util.Calendar gc) {
		{
			/**
			 * ��ϸ��ƣ� 1.���date�������գ����3�� 2.���date�������������2�� 3.�����1��
			 */
			switch (gc.get(Calendar.DAY_OF_WEEK)) {
			case (Calendar.MONDAY):
				gc.add(Calendar.DATE, -3);
				break;
			case (Calendar.SUNDAY):
				gc.add(Calendar.DATE, -2);
				break;
			default:
				gc.add(Calendar.DATE, -1);
				break;
			}
			return gc.getTime();
		}
	}

	/**
	 * �õ�ָ�����ڵĺ�һ��������
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵĺ�һ��������
	 */
	public static synchronized java.util.Date getNextWeekDay(java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.���date�������壬���3�� 2.���date�������������2�� 3.�����1��
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, 2);
			break;
		default:
			gc.add(Calendar.DATE, 1);
			break;
		}
		return gc.getTime();
	}

	public static synchronized java.util.Calendar getNextWeekDay(
			java.util.Calendar gc) {
		/**
		 * ��ϸ��ƣ� 1.���date�������壬���3�� 2.���date�������������2�� 3.�����1��
		 */
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, 2);
			break;
		default:
			gc.add(Calendar.DATE, 1);
			break;
		}
		return gc;
	}

	/**
	 * ȡ��ָ�����ڵ���һ���µ����һ��
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ���һ���µ����һ��
	 */
	public static synchronized java.util.Date getLastDayOfNextMonth(
			java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.����getNextMonth���õ�ǰʱ�� 2.��1Ϊ����������getLastDayOfMonth
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(DateUtil.getNextMonth(gc.getTime()));
		gc.setTime(DateUtil.getLastDayOfMonth(gc.getTime()));
		return gc.getTime();
	}

	/**
	 * ȡ��ָ�����ڵ���һ�����ڵ����һ��
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ���һ�����ڵ����һ��
	 */
	public static synchronized java.util.Date getLastDayOfNextWeek(
			java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.����getNextWeek���õ�ǰʱ�� 2.��1Ϊ����������getLastDayOfWeek
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(DateUtil.getNextWeek(gc.getTime()));
		gc.setTime(DateUtil.getLastDayOfWeek(gc.getTime()));
		return gc.getTime();
	}

	/**
	 * ȡ��ָ�����ڵ���һ���µĵ�һ��
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ���һ���µĵ�һ��
	 */
	public static synchronized java.util.Date getFirstDayOfNextMonth(
			java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.����getNextMonth���õ�ǰʱ�� 2.��1Ϊ����������getFirstDayOfMonth
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(DateUtil.getNextMonth(gc.getTime()));
		gc.setTime(DateUtil.getFirstDayOfMonth(gc.getTime()));
		return gc.getTime();
	}

	public static synchronized java.util.Calendar getFirstDayOfNextMonth(
			java.util.Calendar gc) {
		/**
		 * ��ϸ��ƣ� 1.����getNextMonth���õ�ǰʱ�� 2.��1Ϊ����������getFirstDayOfMonth
		 */
		gc.setTime(DateUtil.getNextMonth(gc.getTime()));
		gc.setTime(DateUtil.getFirstDayOfMonth(gc.getTime()));
		return gc;
	}

	/**
	 * ȡ��ָ�����ڵ���һ�����ڵĵ�һ��
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ���һ�����ڵĵ�һ��
	 */
	public static synchronized java.util.Date getFirstDayOfNextWeek(
			java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.����getNextWeek���õ�ǰʱ�� 2.��1Ϊ����������getFirstDayOfWeek
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(DateUtil.getNextWeek(gc.getTime()));
		gc.setTime(DateUtil.getFirstDayOfWeek(gc.getTime()));
		return gc.getTime();
	}

	public static synchronized java.util.Calendar getFirstDayOfNextWeek(
			java.util.Calendar gc) {
		/**
		 * ��ϸ��ƣ� 1.����getNextWeek���õ�ǰʱ�� 2.��1Ϊ����������getFirstDayOfWeek
		 */
		gc.setTime(DateUtil.getNextWeek(gc.getTime()));
		gc.setTime(DateUtil.getFirstDayOfWeek(gc.getTime()));
		return gc;
	}

	/**
	 * ȡ��ָ�����ڵ���һ����
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ���һ����
	 */
	public static synchronized java.util.Date getNextMonth(java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.ָ�����ڵ��·ݼ�1
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.MONTH, 1);
		return gc.getTime();
	}

	public static synchronized java.util.Calendar getNextMonth(
			java.util.Calendar gc) {
		/**
		 * ��ϸ��ƣ� 1.ָ�����ڵ��·ݼ�1
		 */
		gc.add(Calendar.MONTH, 1);
		return gc;
	}

	/**
	 * ȡ��ָ�����ڵ���һ��
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ���һ��
	 */
	public static synchronized java.util.Date getNextDay(java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.ָ�����ڼ�1��
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 1);
		return gc.getTime();
	}

	/**
	 * ȡ��ָ��ʱ�����һСʱ
	 * 
	 * @param date ָ�����ڡ�
	 * @author LuoChao         
	 * @return ָ��ʱ�����һСʱ
	 */
	public static synchronized java.util.Date getNextHour(java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.ָ�����ڼ�1��
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.HOUR_OF_DAY, 1);
		return gc.getTime();
	}

	public static synchronized java.util.Calendar getNextDay(
			java.util.Calendar gc) {
		/**
		 * ��ϸ��ƣ� 1.ָ�����ڼ�1��
		 */
		gc.add(Calendar.DATE, 1);
		return gc;
	}

	/**
	 * ȡ��ָ�����ڵ���һ������
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ���һ������
	 */
	public static synchronized java.util.Date getNextWeek(java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.ָ�����ڼ�7��
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 7);
		return gc.getTime();
	}

	public static synchronized java.util.Calendar getNextWeek(
			java.util.Calendar gc) {
		/**
		 * ��ϸ��ƣ� 1.ָ�����ڼ�7��
		 */
		gc.add(Calendar.DATE, 7);
		return gc;
	}

	/**
	 * ȡ��ָ�����ڵ��������ڵ����һ��
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ��������ڵ����һ��
	 */
	public static synchronized java.util.Date getLastDayOfWeek(
			java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.���date�������գ����6�� 2.���date������һ�����5�� 3.���date�����ڶ������4��
		 * 4.���date�������������3�� 5.���date�������ģ����2�� 6.���date�������壬���1��
		 * 7.���date�������������0��
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 6);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, 5);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, 4);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, 2);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, 1);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, 0);
			break;
		}
		return gc.getTime();
	}

	/**
	 * ȡ��ָ�����ڵ��������ڵĵ�һ��
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ��������ڵĵ�һ��
	 */
	public static synchronized java.util.Date getFirstDayOfWeek(
			java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.���date�������գ����0�� 2.���date������һ�����1�� 3.���date�����ڶ������2��
		 * 4.���date�������������3�� 5.���date�������ģ����4�� 6.���date�������壬���5��
		 * 7.���date�������������6��
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 0);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, -1);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, -2);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, -4);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, -5);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, -6);
			break;
		}
		return gc.getTime();
	}

	public static synchronized java.util.Calendar getFirstDayOfWeek(
			java.util.Calendar gc) {
		/**
		 * ��ϸ��ƣ� 1.���date�������գ����0�� 2.���date������һ�����1�� 3.���date�����ڶ������2��
		 * 4.���date�������������3�� 5.���date�������ģ����4�� 6.���date�������壬���5��
		 * 7.���date�������������6��
		 */
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 0);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, -1);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, -2);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, -4);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, -5);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, -6);
			break;
		}
		return gc;
	}

	/*
	 * �õ��������ڵ����һ��
	 */

	public static synchronized java.util.Calendar getLastDayOfWeek1(
			java.util.Date d) {
		/**
		 * ��ϸ��ƣ� 1.���date�������գ����0�� 2.���date������һ�����1�� 3.���date�����ڶ������2��
		 * 4.���date�������������3�� 5.���date�������ģ����4�� 6.���date�������壬���5��
		 * 7.���date�������������6��
		 */

		java.util.Calendar gc = DateToCalendar(d);

		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 6);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, 5);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, 4);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, 2);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, 1);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, 0);
			break;
		}

		return gc;
	}

	/**
	 * ȡ��ָ�����ڵ������·ݵ����һ��
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ������·ݵ����һ��
	 */
	public static synchronized java.util.Date getLastDayOfMonth(
			java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.���date��1�£���Ϊ31�� 2.���date��2�£���Ϊ28�� 3.���date��3�£���Ϊ31��
		 * 4.���date��4�£���Ϊ30�� 5.���date��5�£���Ϊ31�� 6.���date��6�£���Ϊ30��
		 * 7.���date��7�£���Ϊ31�� 8.���date��8�£���Ϊ31�� 9.���date��9�£���Ϊ30��
		 * 10.���date��10�£���Ϊ31�� 11.���date��11�£���Ϊ30�� 12.���date��12�£���Ϊ31��
		 * 1.���date�������2�£���Ϊ29��
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.MONTH)) {
		case 0:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 1:
			gc.set(Calendar.DAY_OF_MONTH, 28);
			break;
		case 2:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 3:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 4:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 5:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 6:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 7:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 8:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 9:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 10:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 11:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		}
		// �������
		if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY)
				&& (isLeapYear(gc.get(Calendar.YEAR)))) {
			gc.set(Calendar.DAY_OF_MONTH, 29);
		}
		return gc.getTime();
	}

	public static synchronized java.util.Calendar getLastDayOfMonth(
			java.util.Calendar gc) {
		/**
		 * ��ϸ��ƣ� 1.���date��1�£���Ϊ31�� 2.���date��2�£���Ϊ28�� 3.���date��3�£���Ϊ31��
		 * 4.���date��4�£���Ϊ30�� 5.���date��5�£���Ϊ31�� 6.���date��6�£���Ϊ30��
		 * 7.���date��7�£���Ϊ31�� 8.���date��8�£���Ϊ31�� 9.���date��9�£���Ϊ30��
		 * 10.���date��10�£���Ϊ31�� 11.���date��11�£���Ϊ30�� 12.���date��12�£���Ϊ31��
		 * 1.���date�������2�£���Ϊ29��
		 */
		switch (gc.get(Calendar.MONTH)) {
		case 0:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 1:
			gc.set(Calendar.DAY_OF_MONTH, 28);
			break;
		case 2:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 3:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 4:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 5:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 6:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 7:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 8:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 9:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 10:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 11:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		}
		// �������
		if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY)
				&& (isLeapYear(gc.get(Calendar.YEAR)))) {
			gc.set(Calendar.DAY_OF_MONTH, 29);
		}
		return gc;
	}

	/**
	 * ȡ��ָ�����ڵ������·ݵĵ�һ��
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ������·ݵĵ�һ��
	 */
	public static synchronized java.util.Date getFirstDayOfMonth(
			java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.����Ϊ1��
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.set(Calendar.DAY_OF_MONTH, 1);
		return gc.getTime();
	}

	public static synchronized java.util.Calendar getFirstDayOfMonth(
			java.util.Calendar gc) {
		/**
		 * ��ϸ��ƣ� 1.����Ϊ1��
		 */
		gc.set(Calendar.DAY_OF_MONTH, 1);
		return gc;
	}

	/***************************************************************************
	 * �����ڶ���ת����Ϊָ��ORA���ڡ�ʱ���ʽ���ַ�����ʽ��������ڶ���Ϊ�գ����� һ�����ַ������󣬶�����һ���ն���
	 * 
	 * @param theDate
	 *            ��Ҫת��Ϊ�ַ��������ڶ���
	 * @param hasTime
	 *            ������ص��ַ�����ʱ����Ϊtrue
	 * @return ת���Ľ��
	 **************************************************************************/
	public static synchronized String toOraString(Date theDate, boolean hasTime) {
		/**
		 * ��ϸ��ƣ� 1.�����ʱ�䣬�����ø�ʽΪgetOraDateTimeFormat()�ķ���ֵ
		 * 2.�������ø�ʽΪgetOraDateFormat()�ķ���ֵ 3.����toString(Date theDate, DateFormat
		 * theDateFormat)
		 */
		DateFormat theFormat;
		if (hasTime) {
			theFormat = getOraDateTimeFormat();
		} else {
			theFormat = getOraDateFormat();
		}
		return toString(theDate, theFormat);
	}

	/**
	 * �����ڶ���ת����Ϊָ�����ڡ�ʱ���ʽ���ַ�����ʽ��������ڶ���Ϊ�գ����� һ�����ַ������󣬶�����һ���ն��� ��Ҫת��Ϊ�ַ��������ڶ���
	 * ������ص��ַ�����ʱ����Ϊtrue
	 * 
	 * @return ת���Ľ��
	 */
	public static synchronized String toString(Date theDate, boolean hasTime) {
		/**
		 * ��ϸ��ƣ� 1.�����ʱ�䣬�����ø�ʽΪgetDateTimeFormat�ķ���ֵ 2.�������ø�ʽΪgetDateFormat�ķ���ֵ
		 * 3.����toString(Date theDate, DateFormat theDateFormat)
		 */
		DateFormat theFormat;
		if (hasTime) {
			theFormat = getDateTimeFormat();
		} else {
			theFormat = getDateFormat();
		}
		return toString(theDate, theFormat);
	}

	/**
	 * ��׼���ڸ�ʽ
	 */
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"MM/dd/yyyy");

	/**
	 * ��׼ʱ���ʽ
	 */
	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm");

	/**
	 * ��ʱ����ı�׼ʱ���ʽ
	 */
	// private static final SimpleDateFormat DATE_TIME_EXTENDED_FORMAT = new
	// SimpleDateFormat(
	// "MM/dd/yyyy HH:mm:ss" );
	/**
	 * ORA��׼���ڸ�ʽ
	 */
	private static final SimpleDateFormat ORA_DATE_FORMAT = new SimpleDateFormat(
			"yyyyMMdd");

	/**
	 * ORA��׼ʱ���ʽ
	 */
	private static final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmm");

	/**
	 * ��ʱ�����ORA��׼ʱ���ʽ
	 */
	// private static final SimpleDateFormat ORA_DATE_TIME_EXTENDED_FORMAT = new
	// SimpleDateFormat(
	// "yyyyMMddHHmmss" );
	/**
	 * ����һ����׼���ڸ�ʽ�Ŀ�¡
	 * 
	 * @return ��׼���ڸ�ʽ�Ŀ�¡
	 */
	public static synchronized DateFormat getDateFormat() {
		/**
		 * ��ϸ��ƣ� 1.����DATE_FORMAT
		 */
		SimpleDateFormat theDateFormat = (SimpleDateFormat) DATE_FORMAT.clone();
		theDateFormat.setLenient(false);
		return theDateFormat;
	}

	/**
	 * ����һ����׼ʱ���ʽ�Ŀ�¡
	 * 
	 * @return ��׼ʱ���ʽ�Ŀ�¡
	 */
	public static synchronized DateFormat getDateTimeFormat() {
		/**
		 * ��ϸ��ƣ� 1.����DATE_TIME_FORMAT
		 */
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) DATE_TIME_FORMAT
				.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	/**
	 * ����һ����׼ORA���ڸ�ʽ�Ŀ�¡
	 * 
	 * @return ��׼ORA���ڸ�ʽ�Ŀ�¡
	 */
	public static synchronized DateFormat getOraDateFormat() {
		/**
		 * ��ϸ��ƣ� 1.����ORA_DATE_FORMAT
		 */
		SimpleDateFormat theDateFormat = (SimpleDateFormat) ORA_DATE_FORMAT
				.clone();
		theDateFormat.setLenient(false);
		return theDateFormat;
	}

	/**
	 * ����һ����׼ORAʱ���ʽ�Ŀ�¡
	 * 
	 * @return ��׼ORAʱ���ʽ�Ŀ�¡
	 */
	public static synchronized DateFormat getOraDateTimeFormat() {

		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_FORMAT
				.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	/**
	 * ��һ�����ڶ���ת����Ϊָ�����ڡ�ʱ���ʽ���ַ����� ������ڶ���Ϊ�գ�����һ�����ַ�����������һ���ն���
	 * 
	 * @param theDate
	 *            Ҫת�������ڶ���
	 * @param theDateFormat
	 *            ���ص������ַ����ĸ�ʽ
	 * @return ת�����
	 */
	public static synchronized String toString(Date theDate,
			DateFormat theDateFormat) {
		/**
		 * ��ϸ��ƣ� 1.theDateΪ�գ��򷵻�"" 2.����ʹ��theDateFormat��ʽ��
		 */
		if (theDate == null)
			return "";
		return theDateFormat.format(theDate);
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateName() {
		Calendar ca = Calendar.getInstance();
		String Date = DateUtil.getDateSecondFormat(ca);
		Date = Date.replaceAll("-", "");
		Date = Date.replaceAll(":", "");
		Date = Date.replaceAll(" ", "");
		return Date.trim();
	}
	
	/**
	 * �ж�ʱ��εĿ��
	 */
	public static synchronized int isOneDay(String startTime,String endTime) {
		int timeFlag = 0;
		Date sDate = DateUtil.parseDateSecondFormat(startTime);
		Date eDate = DateUtil.parseDateSecondFormat(endTime);
		
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DATE);
		String currentStr = year + "-" + month + "-" + day + " " + "00:00:00";
		Date date = DateUtil.parseDateSecondFormat(currentStr);
		if(sDate.getTime()<=eDate.getTime()){  
            if((sDate.getTime()<date.getTime()) && (eDate.getTime()>date.getTime())){//ʱ��ο����0��
            	timeFlag = 2;
            }else if((sDate.getTime()<date.getTime()) && (eDate.getTime()<date.getTime())){//ʱ����ڽ���֮ǰ
            	timeFlag = 3;
            }else{//ʱ����ڽ���֮�ڻ���֮��
            	timeFlag = 1;
            }
        }
		return timeFlag;
	}
	
	/**
	 * �ж�ʱ��εĿ��
	 */
	public static synchronized int isOneDay(Date date) {
		int timeFlag = 0;
		
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DATE);
		String currentStr = year + "-" + month + "-" + day + " " + "00:00:00";
		Date today = DateUtil.parseDateSecondFormat(currentStr);
		if(date.getTime()<today.getTime()){ //����֮ǰ
            timeFlag = 3;
        }else{
        	timeFlag = 1;//�ڽ�����߽���֮��
        }
		return timeFlag;
	}
	
	/**
	 * �������������������,�������������ͬһ���£��򷵻�1 
	 */
	@SuppressWarnings("deprecation")
	public static int countMonth(Date beginDate, Date endDate) {
		int beginYear = beginDate.getYear();
		int beginMonth = beginDate.getMonth();
		int endYear = endDate.getYear();
		int endMonth = endDate.getMonth();
		int difMonth = (endYear-beginYear)*12+(endMonth-beginMonth)+1;
		
		return difMonth; 
	}
	
	/**
	 * �����������ڼ���������
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Integer countDay(Date beginDate,Date endDate) {
		
		Integer difDay = 0;
		
		Long count = endDate.getTime() - beginDate.getTime();
		
		difDay = (int)(count/1000/60/60/24);
		
		return difDay;
	}
	
	/**
	 * �����������ڼ���������
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Integer countDay(String beginDate,String endDate) {
		
		try {
			return countDay( parseDateFormat(beginDate), parseDateFormat(endDate));
		} catch (Exception e) {
			return 0;
		}
		
	}
	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateMilliOnlyNumber(java.util.Date date) {
		String pattern = "yyyyMMddHHmmssSSS";
		return getDateFormat(date, pattern);
	}
	
	/**
	 * �ж�һ���ַ����ǲ����ƶ���ʽ������
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static  boolean isThisTimePattern(String dateStr,String pattern){
		Date date=null;
		try {
			date= parseDateFormat(dateStr,pattern);
		} catch (Exception e) {
			return false;
		}
		
		if(date==null)   return false;
		return true;
	}
	
	/**
	 * ��һ�� �ַ�����ʽ�����ڣ��õ����� ��ǰ�ܣ�ȥ�꣬����Ԫ��1�գ�ȥ��Ԫ��1��
	 * @param args
	 */
	public static String  parsePreWeek(String dateStr){
		Calendar c = parseCalendarDayFormat(dateStr);
		c.add(Calendar.DATE, -7);
		return getDateDayFormat(c);
	}
	
	public  static String  parsePre2Week(String dateStr){
		Calendar c = parseCalendarDayFormat(dateStr);
		c.add(Calendar.DATE, -14);
		return getDateDayFormat(c);
	}
	
	public  static String  parsePreYear(String dateStr){
		Calendar c = parseCalendarDayFormat(dateStr);
		c.add(Calendar.YEAR, -1);
		return getDateDayFormat(c);
	}
	
	
	public  static String  parseFirstDayThisYear(String dateStr){
		Calendar c = parseCalendarDayFormat(dateStr);
		c.set(Calendar.DAY_OF_YEAR, 1);
		return getDateDayFormat(c);
	}
	
	public  static String  parseFirstDayLastYear(String dateStr){
		Calendar c = parseCalendarDayFormat(dateStr);
		c.add(Calendar.YEAR, -1);
		c.set(Calendar.DAY_OF_YEAR, 1);
		return getDateDayFormat(c);
	}
	
	//����������Ϊ��ȥ��12��21�յ�now
	public  static String  parseFirstDayThisYear2(String dateStr){
		Calendar c = parseCalendarDayFormat(dateStr);
		c.add(Calendar.YEAR, -1);
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DAY_OF_MONTH, 21);
		return getDateDayFormat(c);
	}
	
	public  static String  parseFirstDayLastYear2(String dateStr){
		Calendar c = parseCalendarDayFormat(dateStr);
		c.add(Calendar.YEAR, -2);
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DAY_OF_MONTH, 21);
		return getDateDayFormat(c);
	}

	public static void main(String[] args) {
		
		//System.out.println(getDateMilliFormat(new Date()));
		//System.out.println(DateUtil.getDateFormat(new Date(),"yyyyMMddHHmmssSS").substring(0,DateUtil.getDateFormat(new Date(),"yyyyMMddHHmmssSS").length() -1));
		
		//System.out.println(countDay(parseDateFormat("2014-09-10 09:00:00"),parseDateFormat("2014-09-10 12:00:00")));
		
		//System.out.println(DateUtil.getDatePathFormatWithRandom(new Date()));
		//System.out.println(DateUtil.getDatePathFormatWithOutRandom(new Date()));
		
		System.out.println(parseFirstDayLastYear("2017-12-30"));
	}
}
