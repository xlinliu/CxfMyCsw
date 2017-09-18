package com.csw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ֿռ��ϵ�Ĵ���
 * 
 * @author Administrator
 * 
 */
public class SpaceTimeInfoRelationUtil {

	/**
	 * �����ж���������֮���Ƿ���ڽ���
	 * 
	 * @param assest1
	 *            ����1
	 * @param ass2
	 *            ����2
	 * @return
	 */
	public static boolean GetIntersectsResults(Double[] ass1, Double[] ass2) {
		if (ass1[0] == ass2[0] && ass1[1] == ass2[1] && ass1[2] == ass2[2]
				&& ass1[3] == ass2[3]) {
			return true;
		} else if (ass1[0] < ass2[2] && ass2[0] < ass1[2]) {
			if (ass1[1] < ass2[3] && ass2[1] < ass2[3]) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * �Ƚ�����ʱ����Ⱥ�˳�����querydatatime��sensordatatime���򣬷���true�����򷵻�false
	 * 
	 * @param querydatatime
	 *            ��ѯ��ʱ��
	 * @param sensordatatime
	 *            ���ݿ��д洢�Ĵ�������ʱ��
	 * @return true ��ѯ��ʱ��ȴ�������ʱ����false ��ѯ��ʱ��ȴ�������ʱ����
	 */
	public static boolean CompareTime(String querydatatime,
			String sensordatatime) {
		// �ȹ淶������������ʱ��
		querydatatime = FormatDateTimeString(querydatatime);
		sensordatatime = FormatDateTimeString(sensordatatime);
		SimpleDateFormat formatDate = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		try {
			Date querytime = formatDate.parse(querydatatime);
			Date sensortime = formatDate.parse(sensordatatime);
			if (querytime.before(sensortime)) {
				return false;
			} else {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		Double[] d1 = new Double[] { 0.0, 0.0, 1.1, 1.1 };
		Double[] d2 = new Double[] { 1.1, 1.2, 1.4, 1.5 };
		System.out.println(GetIntersectsResults(d1, d2));
	}

	/**
	 * �淶��ʱ���ַ���
	 * 
	 * @param dataTime
	 *            ��Ҫ�淶����ʱ��2000-11-21T00:00:00.0Z,
	 * @return �淶���ʱ��Ӧ����yyyy-MM-dd hh:mm:ss �ĸ�ʽ���� 2012-09-09 12:22:22
	 */
	public static String FormatDateTimeString(String dataTime) {
		dataTime = dataTime.trim();
		// ˵������2000-11-21T00:00:00.0Z���ֹ淶����Ҫ�޸�
		if (dataTime.indexOf("T") >= 0) {
			dataTime = dataTime.replace("T", " ");
		}
		if (dataTime.indexOf("Z") >= 0) {
			dataTime = dataTime.replace("Z", "");
		}
		return dataTime.trim();
	}

	/**
	 * �жϿռ�strs�Ƿ�������ռ�becontainStr���Գɹ�
	 * 
	 * @param strs
	 *            ϣ���ǽϴ�Ŀռ䣬��Щ�ַ�����������ת��Ϊ��ֵ
	 * @param becontainStrϣ���ǽ�С�Ŀռ�
	 * @return ������Ľ��������Ϊtrue ��������Ϊfalse
	 */
	public static boolean GetContainResult(String[] strs, String[] becontainStr) {
		try {
			Double[] bigSpace = new Double[4];
			Double[] litterSpace = new Double[4];
			for (int i = 0; i < strs.length; i++) {
				bigSpace[i] = Double.parseDouble(strs[i].trim());
				litterSpace[i] = Double.parseDouble(becontainStr[i].trim());
			}
			// ��ȡ�ϴ������bigSpace�ں��ݵ����ֵ
			Double maxL = 0.0;
			Double minL = 0.0;
			Double maxA = 0.0;
			Double minA = 0.0;
			if (bigSpace[0] < bigSpace[2]) {
				maxL = bigSpace[2];
				minL = bigSpace[0];
			} else {
				maxL = bigSpace[0];
				minL = bigSpace[2];
			}
			if (bigSpace[1] < bigSpace[3]) {
				maxA = bigSpace[3];
				minA = bigSpace[1];
			} else {
				maxA = bigSpace[1];
				minA = bigSpace[3];
			}
			// ֻҪС���������ķ�Χ֮�ڼ�����
			if (litterSpace[0] <= maxL && litterSpace[0] >= minL
					&& litterSpace[2] <= maxL && litterSpace[2] >= minL) {
				if (litterSpace[1] <= maxA && litterSpace[1] >= minA
						&& litterSpace[3] <= maxA && litterSpace[3] >= minA) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
