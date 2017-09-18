package com.csw.utils.TransactionsUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ������Ҫ�ǽ����еĲ�ѯ�Ľ��в�ѯ����ɲ�ѯ�Ĳ���
 * 
 * @author Administrator
 * 
 */
public class QueryAllQueryConditionUtil {
	/**
	 * ���ݲ�ѯ������ɵ�map������в�ѯ���ڷ������ڲ���Ҫ�������ĸ��ӷ����� ��Ҫ����Դ������Ļ�������Ϣ�Ĳ�ѯ�ķ��������������������ԵĲ�ѯ�ķ�����
	 * �������Ĳ��������Ĳ�ѯ�ķ��� ���ʹ������ļ����ͨ�ŵ������Ĳ�ѯ�ķ��������У��ĸ��ӷ������Ƿ��ط�����������󴫸�����id���ϡ�
	 * ��Ȼ��Ҳ�ṩ���ֵĲ�ѯ�ķ�����һ���Ǵ�ָ���Ĵ������в��Һ����������Ĳ�ѯ������һ���ǲ�ָ�����������ϣ� ���Ǵ����ݿ���ȫ���Ľ��м�������ѯ��
	 * 
	 * @param maps
	 *            ��ѯ��������ɵĲ�ѯ����������ʽΪ<String1,String2>:
	 *            ���У�String1��ʶ��ѯ���ֶΣ�String2��ʶ��ѯ���ֶε�ֵ
	 * @return ���ص��Ƿ������еĲ�ѯ������idֵ
	 */
	public static List<String> QueryAllConditionMethod(
			Map<String, String> maps, List<String> initialsensors,
			boolean allownull) {
		// resultSensorIds��Ž���������ѯ������sensor����
		// ͨ������ GetAllSensorIdsUtil.GetAllSensorIdsMethod()������ȡ���������ݿ�洢�˵�id
		if (maps == null || maps.size() == 0) {
			// û�в�ѯ�����򷵻�ȫ��
			return initialsensors;
		}
		// ��Ҫ��ѯ�����еĴ�����
		// System.out.println(resultSensorIds.toString());
		// ������ݿ��в����ڴ�����
		if (initialsensors == null || initialsensors.size() == 0) {
			return null;
		}
		// ������ݿ��д��ڴ�����
		else {
			// ����ѯ�����е��ֶμ��Ϸ��뿪�����Ϳ��԰���˳����в�ѯ
			List<Map<String, String>> ordermaps = QuerySensorInfoBasePatternUtil
					.OrderQueryFieldsUtil(maps);
			// �ֱ���в�ѯ����
			for (int i = 0; i < ordermaps.size(); i++) {
				// ��ȡ�Ӽ��ϵ�������Ϣ��
				if (ordermaps.get(i) != null && ordermaps.get(i).size() > 0) {
					Set<String> mapkeysets = ordermaps.get(i).keySet();
					// ��� �Ӽ����а���Ԫ��
					if (mapkeysets != null && mapkeysets.size() > 0) {
						// ���Ӽ����е����е�map����ת��Ϊ��Ӧ��fieldname[]��fieldvalue[]
						List<String> fieldnames = new ArrayList<String>();
						List<String> fieldvalues = new ArrayList<String>();
						Iterator<String> iterator = mapkeysets.iterator();
						while (iterator.hasNext()) {
							String fieldname = iterator.next();
							fieldnames.add(fieldname);
							fieldvalues.add(ordermaps.get(i).get(fieldname));
						}
						// ��ѯ��ȡ�������������ļ���
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
