package com.csw.utils.TransactionsUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.SpaceTimeInfoRelationUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;

public class QuerySensorInfoBasePatternUtil {
	/**
	 * ���Գ���
	 * 
	 * @param args
	 *            ������
	 */
	public static void main(String[] args) {
		// QuerySensorInfoBasePatternUtil q = new
		// QuerySensorInfoBasePatternUtil();
		// System.out.println("�����" + q.RemovePrefixOfField("sdsdsdsdsdsd",
		// "sd"));

		// QuerySensorInfoBasePatternUtil qipu = new
		// QuerySensorInfoBasePatternUtil();
		/*
		 * List<String> strs = qipu.GetSensorIdThroughField(
		 * "sensorMeasureswathRange", "2800 2800");
		 */
		/*
		 * if (strs.size() > 0) { for (int i = 0; i < strs.size(); i++) {
		 * System.out.println(strs.get(i)); } }
		 */

	}

	/**
	 * �Բ�ѯ���ֶεĽ���������Ҫ�ǽ�����ͬ�Ĳ�ѯ�ֶη�����һ��
	 * 
	 * @param queryFields
	 *            ������Ҫ��������Ĳ�ѯ���ֶκ���ֵ�����
	 * @return �����ź����е��ֶμ�List<Map<String,String>>�ļ���
	 */
	public static List<Map<String, String>> OrderQueryFieldsUtil(
			Map<String, String> queryFields) {
		if (queryFields == null || queryFields.size() == 0) {
			return null;
		}
		List<Map<String, String>> listmaps = new ArrayList<Map<String, String>>();
		// ��ʱ�洢���������Ĳ�ѯ�ֶ�
		Map<String, String> measuremaps = new HashMap<String, String>();
		// ��ʱ����������Ĳ�ѯ�ֶ�
		Map<String, String> computingmaps = new HashMap<String, String>();
		// ��ʱ��ͨ�������Ĳ�ѯ�ֶ�
		Map<String, String> communicationmaps = new HashMap<String, String>();
		// ��ʱ���������ԵĲ�ѯ�ֶ�
		Map<String, String> physicalmaps = new HashMap<String, String>();
		// ��ʱ��������֯�Ĳ�ѯ�ֶ�
		Map<String, String> organmaps = new HashMap<String, String>();
		// ��ʱ��������ԵĲ�ѯ�ֶ�
		Map<String, String> basicmaps = new HashMap<String, String>();
		for (Iterator<String> iterator = queryFields.keySet().iterator(); iterator
				.hasNext();) {
			String nex = iterator.next();
			String querystr = queryFields.get(nex);
			if (nex.indexOf("sensorMeasure") >= 0) {
				measuremaps.put(nex, querystr);
			} else if (nex.indexOf("sensorPhysical") >= 0) {
				physicalmaps.put(nex, querystr);
			} else if (nex.indexOf("sensorCommunication") >= 0) {
				communicationmaps.put(nex, querystr);
			} else if (nex.indexOf("sensorComputing") >= 0) {
				computingmaps.put(nex, querystr);
			} else if (nex.indexOf("sensorOrgan") >= 0) {
				organmaps.put(nex, querystr);
			} else {
				basicmaps.put(nex, querystr);
			}

		}
		// �����һ����˳�������°�װ����,��˳����Ըı�
		listmaps.add(basicmaps);
		listmaps.add(measuremaps);
		listmaps.add(organmaps);
		listmaps.add(physicalmaps);
		listmaps.add(communicationmaps);
		listmaps.add(computingmaps);
		return listmaps;
	}

	/**
	 * �Ƚ������ַ�����������Χ���������ֵȵ�����,�����Ҫ�ǱȽϴ�Slot���ж�ȡ������ֵ
	 * �������������ض����ֶν���������ض��ıȽϷ�ʽ�������ƣ��ǣ��ȵ�
	 * 
	 * @param fieldname
	 *            �Ƚϵ��ֶ�
	 * @param StrFromTable
	 *            �ӱ��ж�ȡ�������ֶε�ֵ
	 * @param fieldvalue
	 *            ���еıȽ�ֵ
	 * @return �����������������򷵻�true�����򷵻�false
	 */
	public static Boolean CompareCondition(String fieldname,
			String StrFromTable, String fieldvalue, boolean allownull) {

		// �����ǽ����еĿ��ܶ������ų�����ֻҪ��һ��Ϊ�գ���Ϊfiedlvalue ��Ϊ�գ�����Ϊ���ַ���
		// �����ڱȽϴ���������������ʱ����Ҫ���ǵ����Ƿ�strFromTable��ֵΪ��
		if (allownull) {
			if (StrFromTable == null || StrFromTable.equals("null")
					|| StrFromTable.trim().length() == 0) {
				return true;
			}
		}
		if (StrFromTable == null || fieldvalue == null
				|| StrFromTable.length() == 0 || fieldvalue.length() == 0
				|| StrFromTable.equals("null")) {
			return false;
		} else {
			// �Ա�֮ǰ���ǽ��ַ�����ǰ��ո�ȥ��
			fieldname = fieldname.trim().toLowerCase();
			StrFromTable = StrFromTable.trim();
			// ��������ȫ�� ���ƣ��ͱ��еİ����˲�ѯ��
			if (fieldname.equals("sensorBasicLongName".toLowerCase())) {
				if (StrFromTable.toLowerCase().indexOf(
						fieldvalue.toLowerCase().trim()) >= 0) {
					return true;
				} else {
					return false;
				}
			}
			// �������Ĺؼ��ֵĲ�ѯ sensorBasicKeywords sensorBasicShortName
			else if (fieldname.equals("sensorBasicKeywords".toLowerCase())) {
				// ����ѯ�Ĺؼ��ֽ��з���,��һ�����ֲ���Ϊ��
				if (fieldvalue.indexOf(",") > 0) {
					String[] values = fieldvalue.trim().split(",");
					for (int i = 0; i < values.length; i++) {
						if (StrFromTable.indexOf(values[i]) >= 0) {
							return true;
						}
					}
					return false;
				} else {
					if (StrFromTable.indexOf(fieldvalue) >= 0) {
						return true;
					} else {
						return false;
					}
				}
			}
			// �������ļ�ƵĲ�ѯ �����ƣ��������еİ����˲�ѯ���ֶ�
			else if (fieldname.equals("sensorBasicShortName".toLowerCase())) {
				if (StrFromTable.toLowerCase().indexOf(
						fieldvalue.toLowerCase().trim()) >= 0) {
					return true;
				} else {
					return false;
				}
			}
			// ������ʼʱ��ıȽ�
			else if (fieldname
					.equals("sensorBasicValidTimeBegin".toLowerCase())) {
				if (SpaceTimeInfoRelationUtil.CompareTime(fieldvalue.trim(),
						StrFromTable.trim())) {
					// �����ѯ��ʱ��ȴ�������ʱ��������true
					return true;
				} else {
					return false;
				}
			}
			// ��������ʱ��ıȽ�
			else if (fieldname.equals("sensorBasicValidTimeEnd".toLowerCase())) {
				if (StrFromTable.isEmpty()) {
					// ���ɰ��ո�ʽ���ĵ�
					Date currentTime = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String dateString = formatter.format(currentTime);
					StrFromTable = dateString;
				}
				if (SpaceTimeInfoRelationUtil.CompareTime(fieldvalue.trim(),
						StrFromTable.trim())) {
					// �����ѯ��ʱ��ȴ�������ʱ��������true
					return false;

				} else {
					return true;
				}
			} else if (fieldname.equals("sensorBasicIntendApplication"
					.toLowerCase())) {
				// ����Ԥ��Ӧ�õĴ�������
				if (StrFromTable.toLowerCase().indexOf(
						fieldvalue.toLowerCase().trim()) >= 0) {
					return true;
				} else {
					return false;
				}
			}
			// ����Ǿ�γ�ȷ�Χ ֻҪ�ཻ�Ϳ���
			else if (fieldname.equals("sensorBasicObservedRange".toLowerCase())) {
				// System.out.println("���뾭γ�Ȳ�ѯ");
				// ���е����ݻ������coordinate syste, north west,south east
				String[] tableValues = StrFromTable.split(",");
				String northValue = tableValues[1].split(" ")[0].trim();
				String westValue = tableValues[1].split(" ")[1].trim();
				String southValue = tableValues[2].split(" ")[0].trim();
				String eastValue = tableValues[2].split(" ")[1].trim();
				// ���Ǵӱ��л�ȡ��
				String[] litterspace = { northValue, westValue, southValue,
						eastValue };
				// ��ѯ��ֵΪ���� north,west,south,east
				String[] queryValues = fieldvalue.split(",");
				// �ж��ǲ���ԭλ������
				if (northValue.equals(southValue)
						&& westValue.equals(eastValue)) {
					// System.out.println("ԭλ��");
					boolean bol = SpaceTimeInfoRelationUtil.GetContainResult(
							queryValues, litterspace);
					// System.out.println(bol);
					return bol;
				} else {
					// ��������Χ֮��İ�����ѯ
					Boolean bol = false;
					// ֻҪ�ཻ�Ϳ���
					try {
						Double[] bigSpace = new Double[4];
						Double[] litterSpace = new Double[4];
						for (int i = 0; i < litterspace.length; i++) {
							bigSpace[i] = Double.parseDouble(queryValues[i]
									.trim());
							litterSpace[i] = Double.parseDouble(litterspace[i]
									.trim());
						}
						bol = SpaceTimeInfoRelationUtil.GetIntersectsResults(
								bigSpace, litterSpace);

						return bol;
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return bol;
					}
				}
			}
			// ��ֵ�ͱȽ� �洢����
			else if (fieldname.equals("sensorComputingStorage".toLowerCase())) {

				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ��ֵ�ͱȽ�dataRate��������
			else if (fieldname.equals("sensorComputingDataRate".toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// �۲����ڱȽ�measurementInterval
			else if (fieldname.equals("sensorMeasureMeasurementInterval"
					.toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ��Ӧʱ��Ƚ�ResponseTime
			else if (fieldname
					.equals("sensorMeasureResponseTime".toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ����ʱ��ıȽ�epochTime,�Ƚ�ʱ�䣬��ѯ���Ǵ����������ʱ��Ҫ��
			else if (fieldname.equals("sensorPhysicalEpochTime".toLowerCase())) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date inputtimeDate = sdf.parse(fieldvalue);
					Date tableDate = sdf.parse(StrFromTable);
					if (inputtimeDate.after(tableDate)) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ���ǹ���߶ȱȽ�
			else if (fieldname.equals("sensorPhysicalLaverageOrbitHeight"
					.toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ������ڱȽ�
			else if (fieldname.equals("sensorPhysicalOrbitCycle".toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// �������ʱȽ� downlinkRate
			else if (fieldname.equals("sensorCommunicationDownlinkRate"
					.toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ������BandsNumber
			else if (fieldname.equals("sensorMeasureBandsNumber".toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ���η�ΧbandswidthRange
			else if (fieldname.equals("sensorMeasureBandswidthRange"
					.toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(StrFromTable.split("-")[0]);
					Double value2 = Double
							.parseDouble(StrFromTable.split("-")[1]);
					Double d = Double.parseDouble(fieldvalue);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ������BandsNumber
			else if (fieldname.equals("sensorMeasureFov".toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ���ӽ�sideSwingAngle
			else if (fieldname.equals("sensorMeasureSideSwingAngle"
					.toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ����ֱ��ʷ�Χ
			else if (fieldname.equals("sensorMeasureSpatialResolutionRange"
					.toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(StrFromTable.split("-")[0]);
					Double value2 = Double
							.parseDouble(StrFromTable.split("-")[1]);
					Double d = Double.parseDouble(fieldvalue);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ���µ�ֱ���
			else if (fieldname.equals("sensorMeasureNadirSpatialResolution"
					.toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ˲ʱ�ӳ���
			else if (fieldname.equals("sensorMeasureNadirIFOV".toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// �����ȼ�
			else if (fieldname.equals("sensorMeasureQuantizationLevel"
					.toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// �ط�����
			else if (fieldname.equals("sensorMeasureRevisitingPeriod"
					.toLowerCase())) {
				try {
					Double value1 = Double
							.parseDouble(fieldvalue.split("-")[0]);
					Double value2 = Double
							.parseDouble(fieldvalue.split("-")[1]);
					Double d = Double.parseDouble(StrFromTable);
					if ((value1 - d) * (value2 - d) <= 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			// ������λ����Ϣ�Ƚ�
			else if (fieldname.equals("sensorBasicPosition")) {
				// ���Ǵ��û���ߴ��ݹ�����
				Double lat = Double.parseDouble(StrFromTable.split(",")[1]);
				Double lon = Double.parseDouble(StrFromTable.split(",")[2]);
				Double inputlat = Double.parseDouble(fieldvalue.split(",")[0]);
				Double inputlon = Double.parseDouble(fieldvalue.split(",")[1]);
				Double wucha = Double.parseDouble(fieldvalue.split(",")[2]);
				if (Math.sqrt((lat - inputlat) * (lat - inputlat)
						+ (lon - inputlon) * (lon - inputlon)) < wucha) {
					return true;
				} else {
					return false;
				}
			}

			else {
				// System.out.println("here" + StrFromTable + " : " +
				// fieldvalue);
				// �����Ĵ���
				if (StrFromTable.contains(fieldvalue)) {
					// System.out.println("contain");
					return true;
				} else {
					return false;
				}
			}

		}

	}

	/**
	 * ��ѯ����һ����������������ָ����ѯ�ķ�Χ����ֻ�ܲ�ѯ��Ե����ֶ����ֶ�ֵ
	 * 
	 * @param fieldname
	 *            ��ѯ�ֶ�
	 * @param fieldvalue
	 *            ��ѯ�ֶ�ֵ
	 * @return ���ز�ѯ֮����������Ĵ�����
	 */
	@SuppressWarnings("unchecked")
	public List<String> GetSensorIdThroughField(String fieldname,
			String fieldvalue) {
		// ��Ҫ���ص������ѯ�����Ĵ�������idֵ
		List<String> sensorids = new ArrayList<String>();

		// ��ȡ�������ĵ�ȫ��
		// ���������ӵ�LocalizedString���ݱ���
		// ����idֵ�����Ӧ��valueֵ
		// ���ظ�ֵ
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		if (fieldname.indexOf("sensorMeasure") >= 0) {
			// ��ѯ�������Ĳ���������Ϣ
			// ��sesnorMesageȥ����ȡ�������ֶΣ�������ĸ�����Ǵ�дҲ������Сд
			String nameprefix = "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::measurementCapabilities:";
			String namevalue = fieldname.substring("sensorMeausre".length());
			String namevaluelitter = namevalue.substring(0, 1).toLowerCase()
					.concat(namevalue.substring(1));
			Query query = session
					.createQuery("select id from Slot where ( name = '"
							+ nameprefix + namevaluelitter + "' or name = '"
							+ nameprefix + namevalue + "' )and values = '"
							+ fieldvalue.trim() + ",'");
			// ���ز�ѯ�����Ϣ
			sensorids = (List<String>) query.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		} else if (fieldname.indexOf("sensorPhysical") >= 0) {
			// ��ѯ������������������Ϣ
			String nameprefix = "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::physicalProperties:property:";
			String namevalue = fieldname.substring("sensorPhysical".length());
			String namevaluelitter = namevalue.substring(0, 1).toLowerCase()
					.concat(namevalue.substring(1));
			Query query = session
					.createQuery("select id from Slot where ( name = '"
							+ nameprefix + namevaluelitter + "' or name = '"
							+ nameprefix + namevalue + "' )and values = '"
							+ fieldvalue.trim() + ",'");
			// ���ز�ѯ�����Ϣ
			sensorids = (List<String>) query.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		} else if (fieldname.indexOf("sensorComputing") >= 0) {
			// ��ѯ�������ļ���������Ϣ

			String nameprefix = "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::computingCapability:";
			String namevalue = fieldname.substring("sensorComputing".length());
			String namevaluelitter = namevalue.substring(0, 1).toLowerCase()
					.concat(namevalue.substring(1));
			Query query = session
					.createQuery("select id from Slot where ( name = '"
							+ nameprefix + namevaluelitter + "' or name = '"
							+ nameprefix + namevalue + "' )and values = '"
							+ fieldvalue.trim() + ",'");
			// ���ز�ѯ�����Ϣ
			sensorids = (List<String>) query.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		} else if (fieldname.indexOf("sensorCommunication") >= 0) {
			// ��ѯ��������ͨ��������Ϣ
			String nameprefix = "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::communicationCapabilities:";
			String namevalue = fieldname.substring("sensorCommunication"
					.length());
			String namevaluelitter = namevalue.substring(0, 1).toLowerCase()
					.concat(namevalue.substring(1));
			Query query = session
					.createQuery("select id from Slot where ( name = '"
							+ nameprefix + namevaluelitter + "' or name = '"
							+ nameprefix + namevalue + "' )and values = '"
							+ fieldvalue.trim() + ",'");
			// ���ز�ѯ�����Ϣ
			sensorids = (List<String>) query.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);

		} else {
			// ��ѯ�������Ļ�����Ϣ
			// �ص�����
			GetSessionUtil.CloseSessionInstance(session);
		}
		List<String> sensors = new ArrayList<String>();
		for (String slotid : sensorids) {
			sensors.add(GetSensorIdFromSlotId(slotid));
		}
		return sensors;
	}

	/**
	 * �ӵ�������ȡ���Ĵ�������Slot��id��Ϣ�л�ȡ��������id��Ϣ
	 * 
	 * @param slotid
	 *            �����ݿ���ȡ������Slot��id����Ϣ
	 * @return ���ص��Ƕ�Ӧ�Ĵ������ı�ʶ������Ϣ��������:package
	 */
	public static String GetSensorIdFromSlotId(String slotid) {
		String queryStr = slotid.trim();
		if (queryStr.indexOf("ExtrinsicObject") >= 0) {
			String panduanStr = "ExtrinsicObject";
			// �����slot��ExtrinsicObject����Ԫ�أ���ô����ɾ��:ExtrinsicObject֮�����Ϣ
			queryStr = queryStr.substring(0, queryStr.indexOf(panduanStr) - 1)
					.trim();
		} else if (queryStr.indexOf(":association:N") >= 0) {
			String panduanStr = ":association:N";
			queryStr = queryStr.substring(0, queryStr.indexOf(panduanStr))
					.trim();
		}
		return queryStr;
	}

	/**
	 * ��ѯ����һ����������������ָ����ѯ�ķ�Χ������ѯ������Զ���ֶ����ֶ�ֵ,�����ֶ����ֶ�ֵ��Ҫƥ�� ��And��ѯ��
	 * 
	 * @param queryStr
	 *            ��ѯ�ֶ�ֵ
	 * @param allownull
	 *            �Ƿ������ظ��ֶβ�����ֵ�Ĵ������б�
	 * @return ���ز�ѯ֮����������Ĵ�����
	 */
	public List<String> GetSensorIdsThroughFields(Map<String, String> queryStr,
			boolean allownull) {
		// ��ֵ���������ڵ�һ�ο��Ը��ṩ����������һ�����������е�sensor�ı�ʾ����
		// ֮��Ϳ�������Щsensor���ڽ��в�ѯ������and����
		int i = 0;
		List<String> sensorids = new ArrayList<String>();
		for (Iterator<String> iterator = queryStr.keySet().iterator(); iterator
				.hasNext();) {
			String rename = iterator.next();
			if (i == 0) {
				i++;
				QuerySensorInfoBasePatternUtil qibpu = new QuerySensorInfoBasePatternUtil();
				sensorids = qibpu.GetSensorIdThroughField(rename, queryStr
						.get(rename));
				if (sensorids.size() == 0) {
					return null;
				}
			} else {
				// �Ϳ��Դ���Щsensor��id���л�ȡ���������Ĵ���������Щ�����Ϳ���ʹ��id�����б���
				for (int j = 0; j < sensorids.size(); j++) {
					sensorids = GetSensorIdsThroughFieldAndRange(rename.trim(),
							queryStr.get(rename.trim()), sensorids, allownull);
				}
			}
		}
		return null;
	}

	/**
	 * Ϊ��ѯ�ֶ�ȥ��ǰ׺,���ǲ�����ȥ��ǰ׺��һ�����ʵĴ�Сд����
	 * 
	 * @param fieldname
	 *            ��ѯ���ֶΣ�����ǰ׺��
	 * @param fieldPrefix
	 *            ��Ҫȥ����ǰ׺
	 * @return ���ز�����ǰ׺�Ĳ�ѯ�ֶ���
	 */
	public static String RemovePrefixOfField(String fieldname,
			String fieldPrefix) {
		return fieldname = fieldname.trim().replaceFirst(fieldPrefix, "")
				.trim();
	}

	/**
	 * ��ѯ����һ��������������ͬʱָ����ѯ�ķ�Χ����ֻ�ܲ�ѯ��Ե����ֶ����ֶ�ֵ�� �÷���Ч�ʻ�Ƚϵͣ�֮������һ�ָ�����Ч�ʵķ���
	 * 
	 * @param fieldname
	 *            ��ѯ���ֶ�,������ǰ׺����sensorMeasure
	 * @param fieldvalue
	 *            ��ѯ���ֶε�ֵ
	 * @param sensoridsforquery
	 *            ��ѯ�ķ�Χ����������id���ϣ�������:package
	 * @return ���ز�ѯ֮����������Ĵ�����
	 */
	public static List<String> GetSensorIdsThroughFieldAndRange(
			String fieldname, String fieldvalue,
			List<String> sensoridsforquery, boolean allownull) {
		// System.out.println("��ѯ�Ĵ���������Ϊ:" + sensoridsforquery.size());
		// �����Ѿ�����Ĳ�ѯ�ֶ�
		String originFieldname = fieldname;

		// ���������idΪ�գ��򲻲�ѯ��ֱ�ӷ���null
		if (sensoridsforquery == null || sensoridsforquery.size() == 0) {
			return null;
		}
		// ��Ų�ѯ�����������Ĵ������ı�ʶ���ļ���
		List<String> sensorids = new ArrayList<String>();
		sensorids.addAll(sensoridsforquery);
		for (int i = 0; i < sensoridsforquery.size(); i++) {
			if (sensorids == null || sensorids.size() == 0) {
				return null;
			}
			fieldname = originFieldname;
			// System.out.println("sensorids length��" + sensorids.size());
			// System.out.println("��" + (i + 1) + "��");
			// System.out.println(sensoridsforquery.size() + "-----------");
			// System.out.println("��ѯ���ֶ�Ϊ��" + fieldname);
			// System.out.println("��ѯ���ֶ�ֵΪ:" + fieldvalue);
			// System.out.println("��ѯ�Ĵ�������ʶ��Ϊ��" + sensoridsforquery.get(i));
			// System.out.println();
			// ���д�������id��Ҫ����ƥ��
			// ����Ҫ��ѯ�Ĵ������ı�ʾ�����д���
			String querysensorid = sensoridsforquery.get(i).trim();
			if (fieldname.indexOf("sensorMeasure") >= 0) {
				// ��ѯ��������
				// Ϊ��ѯ�ֶ�ȥ��ǰ׺,���迼�����ִ�Сд���ڲ�ѯ������Ѿ����ǣ�
				fieldname = RemovePrefixOfField(fieldname, "sensorMeasure");
				// ��ѯ��������
				String resultStr = OperateSensorUtil
						.GetSensorCommonCapabilityMethod(querysensorid,
								fieldname.trim());
				if (!allownull) {
					if (null == StringUtil
							.checkStringIsNotNULLAndEmptyMethod(resultStr)
							|| resultStr.equals("null")) {
						sensorids.remove(querysensorid);
						// System.out.println("�ֶ�ֵ������ɾ��");
						continue;
					}
				}
				// �����������Ҫ�����µ��жϹ��򣨻��еĹ�����������Ҫ����������
				if (!CompareCondition(fieldname, resultStr, fieldvalue,
						allownull)) {
					// ��������ڣ��򽫴������е����Ӧ�Ĵ�������idֵɾ��
					sensorids.remove(querysensorid);
				}
				fieldname = originFieldname;
			} else if (fieldname.indexOf("sensorComputing") >= 0) {
				// ��ѯ��������
				// Ϊ��ѯ�ֶ�ȥ��ǰ׺,���迼�����ִ�Сд���ڲ�ѯ������Ѿ����ǣ�
				fieldname = RemovePrefixOfField(fieldname, "sensorComputing");
				String resultStr = OperateSensorUtil
						.GetSensorComputeCapabilityMethod(querysensorid,
								fieldname.trim());
				if (!allownull) {
					if (null == StringUtil
							.checkStringIsNotNULLAndEmptyMethod(resultStr)
							|| resultStr.equals("null")) {
						sensorids.remove(querysensorid);
						// System.out.println("�ֶ�ֵ������ɾ��");
						continue;
					}
				}
				// �����������Ҫ�����µ��жϹ��򣬻��еĹ�����������Ҫ����������
				if (!CompareCondition(fieldname, resultStr, fieldvalue,
						allownull)) {
					// ��������ڣ��򽫴����������Ӧ��������idֵɾ��
					sensorids.remove(querysensorid);
				}
				fieldname = originFieldname;
			} else if (fieldname.indexOf("sensorCommunication") >= 0) {
				// ��ѯͨ������
				// Ϊ��ѯ�ֶ�ȥ��ǰ׺,���迼�����ִ�Сд���ڲ�ѯ������Ѿ����ǣ�
				fieldname = RemovePrefixOfField(fieldname,
						"sensorCommunication");
				String resultStr = OperateSensorUtil
						.GetSensorCommunicationCapabilityMethod(querysensorid,
								fieldname.trim());
				if (!allownull) {
					if (null == StringUtil
							.checkStringIsNotNULLAndEmptyMethod(resultStr)
							|| resultStr.equals("null")) {
						sensorids.remove(querysensorid);
						// System.out.println("�ֶ�ֵ������ɾ��");
						continue;
					}
				}
				if (!CompareCondition(fieldname, resultStr, fieldvalue,
						allownull)) {
					// ��������ڣ��򽫴����������Ӧ��������idֵɾ��
					sensorids.remove(querysensorid);
				}
				fieldname = originFieldname;
			} else if (fieldname.indexOf("sensorPhysical") >= 0) {
				// ��ѯ����������Ϣ
				// Ϊ��ѯ�ֶ�ȥ��ǰ׺,���迼�����ִ�Сд���ڲ�ѯ������Ѿ����ǣ�
				fieldname = RemovePrefixOfField(fieldname, "sensorPhysical");
				String resultStr = OperateSensorUtil
						.GetSensorPhysicalPropertyMethod(querysensorid,
								fieldname.trim());
				if (!allownull) {
					if (null == StringUtil
							.checkStringIsNotNULLAndEmptyMethod(resultStr)
							|| resultStr.equals("null")) {
						sensorids.remove(querysensorid);
						// System.out.println("�ֶ�ֵ������ɾ��");
						continue;
					}
				}
				if (resultStr != null) {
					resultStr = resultStr.trim();
				}
				if (!CompareCondition(fieldname, resultStr, fieldvalue,
						allownull)) {
					// ��������ڣ��򽫴����������Ӧ��������iֵɾ��
					sensorids.remove(querysensorid);
					// System.out.println("ɾ���ɹ�!");
					// System.out.println();
				}
				fieldname = originFieldname;
			} else if (fieldname.indexOf("sensorOrgan") >= 0) {
				// ��ѯ������������֯��Ϣ
				// Ϊ��ѯ�ֶ�ȥ��ǰ׺,���迼�����ִ�Сд���ڲ�ѯ������Ѿ����ǣ�
				fieldname = RemovePrefixOfField(fieldname, "sensorOrgan");
				String resultStr = OperateSensorUtil
						.GetSensorOrganizationInfoMethod(querysensorid);
				if (!allownull) {
					if (null == StringUtil
							.checkStringIsNotNULLAndEmptyMethod(resultStr)
							|| resultStr.equals("null")) {
						sensorids.remove(querysensorid);
						// System.out.println("�ֶ�ֵ������ɾ��");
						continue;
					}
				}
				if (!CompareCondition(fieldname, resultStr, fieldvalue,
						allownull)) {
					// ��������ڣ��򽫴����������Ӧ��������iֵɾ��
					sensorids.remove(querysensorid);
				}
				fieldname = originFieldname;
			} else if (fieldname.indexOf("sensorBasic") >= 0) {
				if (fieldname.equals("sensorBasicSensorID")) {
					List<String> temsensors = new ArrayList<String>();
					temsensors.addAll(sensorids);
					for (String sensor : temsensors) {
						if (!sensor.contains(fieldvalue)) {
							// �����е��Ƴ�
							sensorids.remove(sensor);
						}
					}
					continue;
				}
				// ���ڴ������Ļ�����Ϣ�Ĳ�ѯ
				String resultStr = OperateSensorUtil
						.GetSensorBasicInfoByFieldNameMethod(fieldname,
								querysensorid);
				if (!allownull) {
					if (!fieldname.equals("sensorBasicValidTimeEnd")
							&& (null == StringUtil
									.checkStringIsNotNULLAndEmptyMethod(resultStr) || resultStr
									.equals("null"))) {
						sensorids.remove(querysensorid);
						System.out.println("�ֶ�ֵ������ɾ��");
						continue;
					}
				}
				if (fieldname.equals("sensorBasicValidTimeEnd")) {
					if (resultStr.isEmpty() || null == resultStr) {
						Date currentTime = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						resultStr = formatter.format(currentTime);
					}
				}
				if (!CompareCondition(fieldname, resultStr, fieldvalue,
						allownull)) {
					// ��������ڣ��򽫴����������Ӧ��������iֵɾ��
					sensorids.remove(querysensorid);
				}
			}
		}
		return sensorids;
	}

	/**
	 * ��ѯ����һ��������������ͬʱָ����ѯ�ķ�Χ������ѯ������Զ���ֶ����ֶ�ֵ,�����ֶ����ֶ�ֵ��Ҫƥ�䣬��ѯ�Ĵ������ķ�Χ��
	 * Ӧ������ÿһ�εĲ�ѯ���ı䣻�÷������ֵĵ���GetSensorIdsThroughFieldAndRange�����ṩ�Ĺ���
	 * 
	 * @param fieldnames
	 *            ��ѯ���ֶ�
	 * @param fieldvalues
	 *            ��ѯ���ֶε�ֵ(��fieldnames�е��ֶε�˳���Ӧ)
	 * @param sensoridsforquery
	 *            ��ѯ�ķ�Χ����������id���ϣ�
	 * @param allownull
	 *            �Ƿ����з��ز����ڸ��ֶ�ֵ�Ĵ�������ʶ��
	 * @return ���ز�ѯ֮����������Ĵ�����
	 */
	public List<String> GetSensorIdsThroughFieldsAndRange(String[] fieldnames,
			String[] fieldvalues, List<String> sensoridsforquery,
			boolean allownull) {
		// �洢�������в�ѯ�����Ĵ������ı�ʶ��
		List<String> resultSensorIds = new ArrayList<String>();
		resultSensorIds = sensoridsforquery;
		if (fieldnames == null || fieldnames.length == 0) {
			// �����ѯ����Ϊ�գ��򲻴��ڣ���ֱ�ӷ��ز�ѯ�ķ�Χ
			return sensoridsforquery;
		}
		for (int i = 0; i < fieldnames.length; i++) {
			if (resultSensorIds.size() == 0) {
				// ����û�з��������Ĵ�����
				return null;
			} else {
				resultSensorIds = GetSensorIdsThroughFieldAndRange(
						fieldnames[i].trim(), fieldvalues[i].trim(),
						resultSensorIds, allownull);
			}
		}
		return resultSensorIds;
	}

	/**
	 * ��ѯ����һ��������������ͬʱָ����ѯ�ķ�Χ������ѯ������Զ���ֶ����ֶ�ֵ,�����ֶ����ֶ�ֵ��Ҫƥ�䣬��ѯ�Ĵ������ķ�Χ��
	 * Ӧ������ÿһ�εĲ�ѯ���ı䣻�÷������ֵĵ���GetSensorIdsThroughFieldAndRange�����ṩ�Ĺ���
	 * 
	 * @param fieldnames
	 *            ��ѯ���ֶ�
	 * @param fieldvalues
	 *            ��ѯ���ֶε�ֵ(��fieldnames�е��ֶε�˳���Ӧ)
	 * @param sensoridsforquery
	 *            ��ѯ�ķ�Χ����������id���ϣ�
	 * @return ���ز�ѯ֮����������Ĵ�����
	 */
	public static List<String> GetSensorIdsThroughFieldsAndRangeByList(
			List<String> fieldnames, List<String> fieldvalues,
			List<String> sensoridsforquery, boolean allownull) {
		if (fieldnames == null || fieldnames.size() == 0) {
			// �����ѯ����Ϊ�գ��򲻴��ڣ���ֱ�ӷ��ز�ѯ�ķ�Χ
			return sensoridsforquery;
		}
		// �洢�������в�ѯ�����Ĵ������ı�ʶ��
		List<String> resultSensorIds = sensoridsforquery;
		if (resultSensorIds == null || resultSensorIds.size() == 0) {
			// ����û�з��������Ĵ�����
			return null;
		}
		// for (int i = 0; i < fieldnames.size(); i++) {
		// System.out.println(fieldnames.get(i) + " : " + fieldvalues.get(i));
		// }

		for (int i = 0; i < fieldnames.size(); i++) {
			resultSensorIds = GetSensorIdsThroughFieldAndRange(fieldnames
					.get(i).trim(), fieldvalues.get(i).trim(), resultSensorIds,
					allownull);
		}
		return resultSensorIds;
	}
}
