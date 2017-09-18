package com.csw.utils.TransactionsUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 该类主要是将所有的查询的进行查询来完成查询的操作
 * 
 * @author Administrator
 * 
 */
public class QueryAllQueryConditionUtil {
	/**
	 * 根据查询条件组成的map对象进行查询，在方法的内部主要将调用四个子方法， 主要是针对传感器的基本的信息的查询的方法，传感器的物理属性的查询的方法，
	 * 传感器的测量能力的查询的方法 ，和传感器的计算和通信的能力的查询的方法。其中，四个子方法都是返回符合自身的需求传感器的id集合。
	 * 当然，也提供两种的查询的方法，一种是从指定的传感器中查找含符合条件的查询方法，一种是不指定传感器集合， 而是从数据库中全部的进行检索来查询。
	 * 
	 * @param maps
	 *            查询的条件组成的查询条件集，格式为<String1,String2>:
	 *            其中，String1标识查询的字段，String2标识查询的字段的值
	 * @return 返回的是符合所有的查询条件的id值
	 */
	public static List<String> QueryAllConditionMethod(
			Map<String, String> maps, List<String> initialsensors,
			boolean allownull) {
		// resultSensorIds存放结果的满足查询条件的sensor集合
		// 通过调用 GetAllSensorIdsUtil.GetAllSensorIdsMethod()方法获取所有在数据库存储了的id
		if (maps == null || maps.size() == 0) {
			// 没有查询条件则返回全部
			return initialsensors;
		}
		// 需要查询的所有的传感器
		// System.out.println(resultSensorIds.toString());
		// 如果数据库中不存在传感器
		if (initialsensors == null || initialsensors.size() == 0) {
			return null;
		}
		// 如果数据库中存在传感器
		else {
			// 将查询的所有的字段集合分离开来，就可以按照顺序进行查询
			List<Map<String, String>> ordermaps = QuerySensorInfoBasePatternUtil
					.OrderQueryFieldsUtil(maps);
			// 分别进行查询操作
			for (int i = 0; i < ordermaps.size(); i++) {
				// 获取子集合的主键信息集
				if (ordermaps.get(i) != null && ordermaps.get(i).size() > 0) {
					Set<String> mapkeysets = ordermaps.get(i).keySet();
					// 如果 子集合中包含元素
					if (mapkeysets != null && mapkeysets.size() > 0) {
						// 将子集合中的所有的map对象转换为对应的fieldname[]和fieldvalue[]
						List<String> fieldnames = new ArrayList<String>();
						List<String> fieldvalues = new ArrayList<String>();
						Iterator<String> iterator = mapkeysets.iterator();
						while (iterator.hasNext()) {
							String fieldname = iterator.next();
							fieldnames.add(fieldname);
							fieldvalues.add(ordermaps.get(i).get(fieldname));
						}
						// 查询获取所有满足条件的集合
						initialsensors = QuerySensorInfoBasePatternUtil
								.GetSensorIdsThroughFieldsAndRangeByList(
										fieldnames, fieldvalues,
										initialsensors, allownull);
					}
				}
			}
		}
		return initialsensors;
	}
}
