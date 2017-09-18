package com.csw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 各种空间关系的处理
 * 
 * @author Administrator
 * 
 */
public class SpaceTimeInfoRelationUtil {

	/**
	 * 用于判断两个区域之间是否存在交集
	 * 
	 * @param assest1
	 *            区域1
	 * @param ass2
	 *            区域2
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
	 * 比较两个时间的先后顺序，如果querydatatime比sensordatatime晚，则，返回true；否则返回false
	 * 
	 * @param querydatatime
	 *            查询的时间
	 * @param sensordatatime
	 *            数据库中存储的传感器的时间
	 * @return true 查询的时间比传感器的时间晚，false 查询的时间比传感器的时间早
	 */
	public static boolean CompareTime(String querydatatime,
			String sensordatatime) {
		// 先规范化处理两个的时间
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
	 * 规范化时间字符串
	 * 
	 * @param dataTime
	 *            需要规范化的时间2000-11-21T00:00:00.0Z,
	 * @return 规范后的时间应该是yyyy-MM-dd hh:mm:ss 的格式，如 2012-09-09 12:22:22
	 */
	public static String FormatDateTimeString(String dataTime) {
		dataTime = dataTime.trim();
		// 说明就是2000-11-21T00:00:00.0Z这种规范，需要修改
		if (dataTime.indexOf("T") >= 0) {
			dataTime = dataTime.replace("T", " ");
		}
		if (dataTime.indexOf("Z") >= 0) {
			dataTime = dataTime.replace("Z", "");
		}
		return dataTime.trim();
	}

	/**
	 * 判断空间strs是否相包含空间becontainStr测试成功
	 * 
	 * @param strs
	 *            希望是较大的空间，这些字符串都必须能转换为数值
	 * @param becontainStr希望是较小的空间
	 * @return 相包含的结果，包含为true ，不包含为false
	 */
	public static boolean GetContainResult(String[] strs, String[] becontainStr) {
		try {
			Double[] bigSpace = new Double[4];
			Double[] litterSpace = new Double[4];
			for (int i = 0; i < strs.length; i++) {
				bigSpace[i] = Double.parseDouble(strs[i].trim());
				litterSpace[i] = Double.parseDouble(becontainStr[i].trim());
			}
			// 获取较大区间的bigSpace在横纵的最大值
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
			// 只要小的在这里大的范围之内即可以
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
