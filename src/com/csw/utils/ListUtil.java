package com.csw.utils;

import java.util.ArrayList;
import java.util.List;

import com.csw.exceptions.ListException;

public class ListUtil {
	/**
	 * �����list�ֽ�Ϊ���ȵļ��ȷ�
	 * 
	 * @param list
	 * @param num
	 * @throws ListException
	 */
	@SuppressWarnings("unchecked")
	public static List<List> splitList(List list, int num) throws ListException {
		if (list != null && list.size() > num) {
			List<List> resultsList = new ArrayList<List>();
			int listlength = list.size();
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					resultsList.add(list.subList(i * listlength / num, (i + 1)
							* listlength / num));
				} else {
					resultsList.add(list.subList(i * listlength / num,
							listlength - 1));
				}
			}
			return resultsList;
		} else {
			throw new ListException(
					"����ֵ�list���󲻷���Ҫ��1��list���벻Ϊ�գ�2��list�����ĸ���������� ���ֵĸ���");
		}
	}
}
