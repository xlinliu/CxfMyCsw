package com.csw.utils.SensorInfoUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import com.csw.model.ebXMLModel.ExtrinsicObject;
import com.csw.model.ebXMLModel.Slot;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.StringUtil;

@SuppressWarnings("deprecation")
public class QueryProcessByConditionUtil {
	/**
	 * ͨ����ѯ�ֶκͲ�ѯֵ��ȷ��extrinsicObject��idֵ
	 * 
	 * @param chaxunziduan
	 *            ��ѯ�ֶ�
	 * @param chaxunVlaue
	 *            ��ѯֵ
	 * @return �������������ѯ�����ļ�¼��idֵ
	 */
	public static Set<String> GetExtrinsicObjects(String chaxunziduan,
			String chaxunVlaue) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);

		Set<String> results = new HashSet<String>();
		session.beginTransaction().begin();
		if (chaxunziduan.equals("Intersects")) {
			results.addAll(QueryIntersectsMethod(session, chaxunziduan,
					chaxunVlaue));
		}
		if (chaxunziduan.equals("PropertyIsEqualTo")) {
			results.addAll(QueryPropertyIsEqualToMethod(session, chaxunziduan,
					chaxunVlaue));
		}
		if (chaxunziduan.equals("PropertyIsLike")) {
			results.addAll(QueryPropertyIsLikeMethod(session, chaxunziduan,
					chaxunVlaue));
		}
		if (chaxunziduan.equals("PropertyIsLessThanOrEqualTo")) {
			results.addAll(QueryPropertyIsLessThanOrEqualToMethod(session,
					chaxunziduan, chaxunVlaue));
		}
		if (chaxunziduan.equals("PropertyIsGreaterThanOrEqualTo")) {
			results.addAll(QueryPropertyIsGreaterThanOrEqualToMethod(session,
					chaxunziduan, chaxunVlaue));
		}
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		return results;
	}

	/**
	 * ���ݲ�ѯ�ֶκͲ�ѯֵ����ѯIntersects�ĺ�������
	 * 
	 * @param session
	 * @param chaxunziduan
	 * @param chaxunzhi
	 * @return ������������������Ĳ�ѯֵ
	 */
	@SuppressWarnings("unchecked")
	public static Set<String> QueryIntersectsMethod(Session session,
			String chaxunziduan, String chaxunVlaue) {
		Set<String> results = new HashSet<String>();
		String value1 = chaxunVlaue.split("\\|")[0];
		String value2 = chaxunVlaue.split("\\|")[1];
		Criteria criteria = null;
		String[] values = value2.split(" ");
		if (value1.endsWith("BoundingBox")) {
			criteria = session.createCriteria(ExtrinsicObject.class);
			List<ExtrinsicObject> eoList = (List<ExtrinsicObject>) criteria
					.list();
			for (ExtrinsicObject eo : eoList) {
				for (Slot slot : eo.getSlots()) {
					if (slot.getName().endsWith("BoundedBy")
							|| slot.getName().endsWith("BBOX")) {
						String[] savedValues = new String[4];
						try {
							String longvalue = slot.getValues().substring(1,
									slot.getValues().length() - 2);
							savedValues[0] = longvalue.split(",")[1].split(" ")[0];
							savedValues[1] = longvalue.split(",")[1].split(" ")[1];
							savedValues[2] = longvalue.split(",")[2].split(" ")[0];
							savedValues[3] = longvalue.split(",")[2].split(" ")[1];

							Double[] inputvalues = StringUtil
									.ParseStringsToDoubles(values);
							Double[] queryvalues = StringUtil
									.ParseStringsToDoubles(savedValues);
							Boolean bolean = GetIntersectsResults(inputvalues,
									queryvalues);
							if (bolean) {
								results.add(eo.getId());
							}
							break;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		System.out
				.println("----------------------------insert bbox---------------");
		System.out.println(results.toString());
		System.out
				.println("----------------------------insert bbox---------------");
		return results;

	}

	/**
	 * ���ݲ�ѯ�ֶκͲ�ѯֵ����ѯPropertyIsEqualTo�ĺ�������
	 * 
	 * @param session
	 * @param chaxunziduan
	 * @param chaxunzhi
	 * @return ������������������Ĳ�ѯֵ
	 */
	@SuppressWarnings( { "unchecked" })
	public static Set<String> QueryPropertyIsEqualToMethod(Session session,
			String chaxunziduan, String chaxunVlaue) {
		Set<String> results = new HashSet<String>();
		// sample: dc:title
		String value1 = chaxunVlaue.split("\\|")[0];
		// sample:System
		String value2 = chaxunVlaue.split("\\|")[1];
		Criteria criteria = null;
		if (value1.trim().endsWith("Format".trim())) {
			criteria = session.createCriteria(ExtrinsicObject.class);
			criteria.add(Expression.eq("mimetype", value2));
			List<ExtrinsicObject> eoList = (List<ExtrinsicObject>) criteria
					.list();
			for (ExtrinsicObject eo : eoList) {
				results.add(eo.getId());
			}
		}
		// ��������ܴ������⣬Ӧ���ǹ̶������system
		if (value1.trim().endsWith("type".trim())) {
			System.out.println("------------------------------------");
			Query query = session
					.createQuery("from ExtrinsicObject where objectType.home like '%"
							+ value2 + "'");
			List<ExtrinsicObject> eoList = (List<ExtrinsicObject>) query.list();
			for (ExtrinsicObject eo : eoList) {
				results.add(eo.getId());
				System.out.println(eo.getId());
				System.out.println(eo.getObjectType().getHome());
			}
			System.out.println("------------------------------------");
		}
		// ��ѯ�ֶε�����id
		if (value1.trim().endsWith("identifier".trim())) {
			criteria = session.createCriteria(ExtrinsicObject.class);
			criteria.add(Expression.eq("id", value2));
			if (criteria.list().size() > 0) {
				ExtrinsicObject eo = (ExtrinsicObject) criteria.list().get(0);
				results.add(eo.getId());
			}
		}
		return results;
	}

	/**
	 * ���ݲ�ѯ�ֶκͲ�ѯֵ����ѯPropertyIsLike�ĺ�������
	 * 
	 * @param session
	 * @param chaxunziduan
	 * @param chaxunzhi
	 * @return ������������������Ĳ�ѯֵ
	 */
	@SuppressWarnings("unchecked")
	public static Set<String> QueryPropertyIsLikeMethod(Session session,
			String chaxunziduan, String chaxunVlaue) {
		Set<String> results = new HashSet<String>();
		String value1 = chaxunVlaue.split("\\|")[0];
		String value2 = chaxunVlaue.split("\\|")[1];
		Criteria criteria = null;
		if (value1.trim().endsWith("title".trim())) {
			Query query = session
					.createQuery("from ExtrinsicObject where lname.value like '%"
							+ value2 + "%'");
			List<ExtrinsicObject> eos = (List<ExtrinsicObject>) query.list();
			System.out.println("------------------------------");
			for (ExtrinsicObject eo : eos) {
				results.add(eo.getId());
				System.out.println(eo.getId());
				System.out.println(eo.getLname().getValue());
			}
			System.out.println("------------------------------");

		}
		if (value1.trim().endsWith("subject".trim())) {
			criteria = session.createCriteria(ExtrinsicObject.class);
			List<ExtrinsicObject> eos = (List<ExtrinsicObject>) criteria.list();
			for (ExtrinsicObject eo : eos) {
				for (Slot slot : eo.getSlots()) {
					if (slot.getName().endsWith("Keywords")) {
						if (slot.getValues().contains(value2)) {
							results.add(eo.getId());
						}
					}
				}
			}
		}
		return results;
	}

	/**
	 * ���ݲ�ѯ�ֶκͲ�ѯֵ����ѯPropertyIsLessThanOrEqualTo�ĺ�������
	 * 
	 * @param session
	 * @param chaxunziduan
	 * @param chaxunzhi
	 * @return ������������������Ĳ�ѯֵ
	 */
	@SuppressWarnings("unchecked")
	public static Set<String> QueryPropertyIsLessThanOrEqualToMethod(
			Session session, String chaxunziduan, String chaxunVlaue) {
		Set<String> results = new HashSet<String>();
		String value1 = chaxunVlaue.split("\\|")[0];
		String value2 = chaxunVlaue.split("\\|")[1];
		Criteria criteria = null;
		if (value1.trim().endsWith("end".trim())) {
			criteria = session.createCriteria(ExtrinsicObject.class);
			List<ExtrinsicObject> eos = (List<ExtrinsicObject>) criteria.list();
			System.out.println("-------------------------------------");
			for (ExtrinsicObject eo : eos) {
				for (Slot slot : eo.getSlots()) {
					if (slot.getName().endsWith("ValidTimeEnd")) {
						try {
							String valueStr = slot.getValues().substring(0,
									slot.getValues().length() - 1);
							if (valueStr.contains("Z")
									|| valueStr.contains("T")) {
								// ����2011-01-01T00:00:00.0Z
								valueStr = valueStr.substring(0, valueStr
										.indexOf("T"))
										+ " "
										+ valueStr.substring(valueStr
												.indexOf("T") + 1, valueStr
												.indexOf("."));
							}
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd hh:mm:ss");
							if (valueStr != null
									&& valueStr.trim().length() > 0) {
								try {
									Date date = sdf.parse(valueStr);
									// �û����������
									Date dateshuru = sdf.parse(value2);
									// ����û���������ڱȴ洢���������������Ϊȷ����ֵ
									if (date.after(dateshuru)
											|| date.equals(dateshuru)) {
										System.out.println(eo.getId());
										System.out.println(slot.getValues());
										results.add(eo.getId());
									}
								} catch (Exception e) {
									SimpleDateFormat sdf2 = new SimpleDateFormat(
											"yyyy-MM-dd");
									try {

										Date date = sdf2.parse(valueStr);
										// �û��������Ϣ
										Date dateshuru2;
										if (value2 != null
												&& value2.trim() != "") {
											dateshuru2 = sdf2.parse(value2);
											if (dateshuru2 != null) {
												if (date.before(dateshuru2)
														|| date
																.equals(dateshuru2)) {
													results.add(eo.getId());
													System.out.println(eo
															.getId());
													System.out.println(slot
															.getValues());
													System.out.println();
												}
											}
										}

									} catch (Exception e2) {
										e.printStackTrace();
									}
								}
							}

						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("������ʱ�����Կ���Ϊ��!");
						}
					}
				}

			}
			System.out.println("-------------------------------------");
		}

		return results;
	}

	/**
	 * ���ݲ�ѯ�ֶκͲ�ѯֵ����ѯPropertyIsGreaterThanOrEqualTo�ĺ�������
	 * 
	 * @param session
	 * @param chaxunziduan
	 * @param chaxunzhi
	 * @return ������������������Ĳ�ѯֵ
	 */
	@SuppressWarnings( { "unchecked" })
	public static Set<String> QueryPropertyIsGreaterThanOrEqualToMethod(
			Session session, String chaxunziduan, String chaxunVlaue) {
		Set<String> results = new HashSet<String>();
		String value1 = chaxunVlaue.split("\\|")[0];
		String value2 = chaxunVlaue.split("\\|")[1];
		Criteria criteria = null;
		if (value1.trim().toLowerCase().endsWith("start".trim())) {
			criteria = session.createCriteria(ExtrinsicObject.class);
			List<ExtrinsicObject> eos = (List<ExtrinsicObject>) criteria.list();
			System.out.println("---------------------------------------");
			for (ExtrinsicObject eo : eos) {
				for (Slot slot : eo.getSlots()) {
					if (slot.getName().endsWith("ValidTimeBegin")) {
						try {
							String valueStr = slot.getValues().substring(0,
									slot.getValues().length() - 1);
							if (valueStr.contains("Z")
									|| valueStr.contains("T")) {
								// ��������2011-01-01T00:00:00.0Z
								valueStr = valueStr.substring(0, valueStr
										.indexOf("T"))
										+ " "
										+ valueStr.substring(valueStr
												.indexOf("T") + 1, valueStr
												.indexOf("."));
							}
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							try {
								Date date = sdf.parse(valueStr);

								Date dateshuru = sdf.parse(value2);

								if (date.after(dateshuru)
										|| date.equals(dateshuru)) {
									results.add(eo.getId());
								}

							} catch (ParseException e) {
								// �������� 2011-01-01
								SimpleDateFormat sdf2 = new SimpleDateFormat(
										"yyyy-MM-dd");
								try {
									Date date = sdf2.parse(valueStr);
									Date dateshuru2 = sdf2.parse(value2);
									if (date.after(dateshuru2)
											|| date.equals(dateshuru2)) {
										results.add(eo.getId());
									}
								} catch (Exception e2) {
									e2.printStackTrace();
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						System.out.println(slot.getValues());
					}
				}
			}
			System.out.println("---------------------------------------");
		}
		if (value1.trim().toLowerCase().endsWith("date".trim())) {
			criteria = session.createCriteria(ExtrinsicObject.class);
			List<ExtrinsicObject> eos = (List<ExtrinsicObject>) criteria.list();
			for (ExtrinsicObject eo : eos) {
				for (Slot slot : eo.getSlots()) {
					if (slot.getName().endsWith("lastUpdate")) {
						try {
							String valueStr = slot.getValues().substring(0,
									slot.getValues().length());
							if (valueStr.contains("Z")
									|| valueStr.contains("T")) {
								// 2011-01-01T00:00:00.0Z
								valueStr = valueStr.substring(0, valueStr
										.indexOf("T"))
										+ valueStr.substring(valueStr
												.indexOf("T") + 1, valueStr
												.indexOf("."));
							}
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							try {
								Date date = sdf.parse(valueStr);

								Date dateshuru = sdf.parse(value2);

								if (date.after(dateshuru)) {
									results.add(eo.getId());
								}

							} catch (ParseException e) {
								SimpleDateFormat sdf2 = new SimpleDateFormat(
										"yyyy-MM-dd");
								try {
									Date date = sdf2.parse(valueStr);
									Date dateshuru2 = sdf2.parse(value2);
									if (date.before(dateshuru2)) {
										results.add(eo.getId());
									}
								} catch (Exception e2) {
									e2.printStackTrace();
								}
							}
						} catch (Exception e) {
						}
					}
				}
			}
		}
		return results;
	}

	/**
	 * �����ж������Ƿ��ཻ
	 * 
	 * @param ass1
	 * @param ass2
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
}
