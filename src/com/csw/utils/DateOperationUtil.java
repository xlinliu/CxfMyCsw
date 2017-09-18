package com.csw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.csw.exceptions.NullZeroException;

public class DateOperationUtil {
	/**
	 * 将字符串转换为日期
	 * 
	 * @param dateStr
	 * @param datepattern
	 * @return
	 * @throws NullZeroException
	 * @throws ParseException
	 */
	public static Date StringToDate(String dateStr, String datepattern)
			throws NullZeroException, ParseException {
		StringUtil.checkStringIsNotNULLAndEmptyMethod(dateStr, "dateStr");
		SimpleDateFormat sDateFormat = new SimpleDateFormat(datepattern);
		return sDateFormat.parse(dateStr);
	}
	/**
	 * 获取当前系统的时间的字符串，格式为yyyy-MM-dd HH:mm:ss 如2012-11-20 20:38:42
	 * 
	 * @return 返回指定了格式的时间，如2012-11-20 20:38:42
	 */
	public static String getSystemTime() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**
	 * 将相应的格式的时间的字符串转换为相对应的时间，目前，这个只支持yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetimeStr
	 *            时间的字符串，按照一定的格式，
	 * @return 返回相应的datetime格式的内容,如果出现异常，则返回null，成功则返回
	 */
	public static Date ParseStrToTime(String datetimeStr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = df.parse(datetimeStr.trim());
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 返回两个时间的比值
	 * 
	 * @param onedate
	 *            date类型
	 * @param twodate
	 *            date类型
	 * @return onedate before 返回1，onedate equal 返回2，onedate after 返回3,其他则返回4
	 */
	public static int CompareDates(Date onedate, Date twodate) {
		if (onedate.before(twodate)) {
			return 1;
		} else if (onedate.equals(twodate)) {
			return 2;
		} else if (onedate.after(twodate)) {
			return 3;
		}
		return 4;
	}

	public static void main(String[] args) {
		ParseStrToTime("2012-02-02 22:22:22");
		System.out.println(getSystemTime());
	}
}
