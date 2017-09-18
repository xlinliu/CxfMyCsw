package com.csw.utils;

import java.util.ArrayList;
import java.util.List;

import com.csw.exceptions.NullZeroException;

public class StringUtil {
	/**
	 * 检验字符是否为空或不存在，如果是返回null，否则返回 原本的string
	 * 
	 * @param str
	 * @return
	 */
	public static String checkStringIsNotNULLAndEmptyMethod(String str,
			String strname) throws NullZeroException {
		if (str == null || str.trim().length() == 0)
			throw new NullZeroException("参数" + strname + "为null或长度为0");
		return str.trim();
	}

	/**
	 * 检验所有字符是否为空或不存在，只要有一个不符合要求则返回null
	 * 
	 * @param strs
	 * @return fanh
	 * @throws NullZeroException
	 */
	public static String[] checkStrsIsNotNULLAndEmptyMethod(String[] strs,
			String strname) throws NullZeroException {
		if (strs == null || strs.length == 0) {
			throw new NullZeroException("参数" + strname + "不能为空");
		}
		String[] results = new String[strs.length];
		for (int i = 0; i < strs.length; i++) {
			String result = checkStringIsNotNULLAndEmptyMethod(strs[i], "strs["
					+ i + "]");
			results[i] = result;
		}
		return results;
	}
	/**
	 * 给传感器标识符增加:package
	 * 
	 * @param sensorid
	 *            传感器标识符
	 * @return 返回增加后的传感器标识符
	 */
	public static String AppendPacakgeStr(String sensorid) {
		if (sensorid != null && sensorid.trim().length() > 0)
			sensorid = sensorid.trim();
		if (!sensorid.endsWith(":package")) {
			sensorid = sensorid + ":package";
		}
		return sensorid;
	}

	/**
	 * 去除传感器标识符中的包含的:package
	 * 
	 * @param sensorid
	 *            传感器标识符
	 * @return 返回去除了:package的传感器标识符
	 */
	public static String DeletePackageStr(String sensorid) {
		if (sensorid != null) {
			sensorid = sensorid.trim();
			if (sensorid.endsWith(":package")) {
				sensorid = sensorid.substring(0, sensorid.length()
						- ":package".length());
			}
		}
		return sensorid;
	}

	/**
	 * 解析出所有三元组的信息，按照subject ,property ,object 的顺序保存在list中
	 */
	public static List<String> ParseNTFileLineStr(String line) {
		String subject = "";
		String object = "";
		String property = "";
		if (line.split("> <").length == 3) {
			// 说明是正确的<subject> <property> <object>
			subject = (line.split("> <")[0] + ">").trim();
			property = ("<" + line.split("> <")[1] + ">").trim();
			object = ("<" + line.split("> <")[2]).trim();
		} else if (line.split("> <").length == 2) {
			// 说明是<subject> <property> "literal"
			if (line.split("> <")[1].split("> \"").length == 2) {
				subject = (line.split("> <")[0] + ">").trim();
				property = ("<" + line.split("> <")[1].split("> \"")[0] + ">")
						.trim();
				object = ("\"" + line.split("> <")[1].split("> \"")[1]).trim();
			}
		}
		List<String> queryStr = new ArrayList<String>();
		queryStr.add(subject);
		queryStr.add(property);
		queryStr.add(object);
		return queryStr;
	}

	/**
	 * 将一段字符串分解为字符数组，例如"a b c d "
	 * 
	 * @param fileids
	 * @return
	 */
	public static String[] GetFilesIDCollection(String fileids) {
		String[] fileidarray = {};
		fileidarray = fileids.split(" ");
		return fileidarray;
	}

	/**
	 * 将字符串数组中的每个字符串转换为double类型
	 * 
	 * @param strValues
	 * @return
	 */
	public static Double[] ParseStringsToDoubles(String[] strValues) {
		Double[] doubles = new Double[4];
		for (int i = 0; i < strValues.length; i++) {
			doubles[i] = Double.parseDouble(strValues[i]);
		}
		return doubles;
	}

	/**
	 * 检验字符是否为空或不存在，如果是返回null，否则返回 原本的string
	 * 
	 * @param str
	 * @return
	 */
	public static String checkStringIsNotNULLAndEmptyMethod(String str) {
		if (str == null || str.trim().length() == 0) {
			return null;
		} else {
			return str.trim();
		}
	}

	/**
	 * 检验所有字符是否为空或不存在，只要有一个不符合要求则返回null
	 * 
	 * @param strs
	 * @return fanh
	 */
	public static String[] checkStrsIsNotNULLAndEmptyMethod(String[] strs) {
		if (strs == null || strs.length == 0) {
			return null;
		}
		String[] results = new String[strs.length];
		for (int i = 0; i < strs.length; i++) {
			String result = checkStringIsNotNULLAndEmptyMethod(strs[i]);
			if (result == null) {
				return null;
			}
			results[i] = result;

		}
		return results;
	}
}
