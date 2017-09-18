package com.csw.utils;

import java.util.ArrayList;
import java.util.List;

import com.csw.exceptions.ListException;

public class ListUtil {
	/**
	 * 将大的list分解为均等的几等分
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
					"需均分的list对象不符合要求，1：list必须不为空，2：list包含的个数必须大于 均分的个数");
		}
	}
}
