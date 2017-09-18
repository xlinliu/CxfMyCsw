package com.csw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.csw.exceptions.NullZeroException;

public class DateOperationUtil {
	/**
	 * ���ַ���ת��Ϊ����
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
	 * ��ȡ��ǰϵͳ��ʱ����ַ�������ʽΪyyyy-MM-dd HH:mm:ss ��2012-11-20 20:38:42
	 * 
	 * @return ����ָ���˸�ʽ��ʱ�䣬��2012-11-20 20:38:42
	 */
	public static String getSystemTime() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**
	 * ����Ӧ�ĸ�ʽ��ʱ����ַ���ת��Ϊ���Ӧ��ʱ�䣬Ŀǰ�����ֻ֧��yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetimeStr
	 *            ʱ����ַ���������һ���ĸ�ʽ��
	 * @return ������Ӧ��datetime��ʽ������,��������쳣���򷵻�null���ɹ��򷵻�
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
	 * ��������ʱ��ı�ֵ
	 * 
	 * @param onedate
	 *            date����
	 * @param twodate
	 *            date����
	 * @return onedate before ����1��onedate equal ����2��onedate after ����3,�����򷵻�4
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
