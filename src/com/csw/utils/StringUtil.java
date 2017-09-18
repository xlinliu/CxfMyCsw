package com.csw.utils;

import java.util.ArrayList;
import java.util.List;

import com.csw.exceptions.NullZeroException;

public class StringUtil {
	/**
	 * �����ַ��Ƿ�Ϊ�ջ򲻴��ڣ�����Ƿ���null�����򷵻� ԭ����string
	 * 
	 * @param str
	 * @return
	 */
	public static String checkStringIsNotNULLAndEmptyMethod(String str,
			String strname) throws NullZeroException {
		if (str == null || str.trim().length() == 0)
			throw new NullZeroException("����" + strname + "Ϊnull�򳤶�Ϊ0");
		return str.trim();
	}

	/**
	 * ���������ַ��Ƿ�Ϊ�ջ򲻴��ڣ�ֻҪ��һ��������Ҫ���򷵻�null
	 * 
	 * @param strs
	 * @return fanh
	 * @throws NullZeroException
	 */
	public static String[] checkStrsIsNotNULLAndEmptyMethod(String[] strs,
			String strname) throws NullZeroException {
		if (strs == null || strs.length == 0) {
			throw new NullZeroException("����" + strname + "����Ϊ��");
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
	 * ����������ʶ������:package
	 * 
	 * @param sensorid
	 *            ��������ʶ��
	 * @return �������Ӻ�Ĵ�������ʶ��
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
	 * ȥ����������ʶ���еİ�����:package
	 * 
	 * @param sensorid
	 *            ��������ʶ��
	 * @return ����ȥ����:package�Ĵ�������ʶ��
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
	 * ������������Ԫ�����Ϣ������subject ,property ,object ��˳�򱣴���list��
	 */
	public static List<String> ParseNTFileLineStr(String line) {
		String subject = "";
		String object = "";
		String property = "";
		if (line.split("> <").length == 3) {
			// ˵������ȷ��<subject> <property> <object>
			subject = (line.split("> <")[0] + ">").trim();
			property = ("<" + line.split("> <")[1] + ">").trim();
			object = ("<" + line.split("> <")[2]).trim();
		} else if (line.split("> <").length == 2) {
			// ˵����<subject> <property> "literal"
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
	 * ��һ���ַ����ֽ�Ϊ�ַ����飬����"a b c d "
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
	 * ���ַ��������е�ÿ���ַ���ת��Ϊdouble����
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
	 * �����ַ��Ƿ�Ϊ�ջ򲻴��ڣ�����Ƿ���null�����򷵻� ԭ����string
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
	 * ���������ַ��Ƿ�Ϊ�ջ򲻴��ڣ�ֻҪ��һ��������Ҫ���򷵻�null
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
