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
	 * 测试程序
	 * 
	 * @param args
	 *            主函数
	 */
	public static void main(String[] args) {
		// QuerySensorInfoBasePatternUtil q = new
		// QuerySensorInfoBasePatternUtil();
		// System.out.println("结果：" + q.RemovePrefixOfField("sdsdsdsdsdsd",
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
	 * 对查询的字段的进行排序，主要是将类相同的查询字段放置在一起
	 * 
	 * @param queryFields
	 *            所有需要进行排序的查询的字段和其值的组合
	 * @return 返回排好序列的字段集List<Map<String,String>>的集合
	 */
	public static List<Map<String, String>> OrderQueryFieldsUtil(
			Map<String, String> queryFields) {
		if (queryFields == null || queryFields.size() == 0) {
			return null;
		}
		List<Map<String, String>> listmaps = new ArrayList<Map<String, String>>();
		// 临时存储测量能力的查询字段
		Map<String, String> measuremaps = new HashMap<String, String>();
		// 临时存计算能力的查询字段
		Map<String, String> computingmaps = new HashMap<String, String>();
		// 临时存通信能力的查询字段
		Map<String, String> communicationmaps = new HashMap<String, String>();
		// 临时存物理属性的查询字段
		Map<String, String> physicalmaps = new HashMap<String, String>();
		// 临时存所属组织的查询字段
		Map<String, String> organmaps = new HashMap<String, String>();
		// 临时存基本属性的查询字段
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
		// 最后按照一定的顺序将其重新包装起来,该顺序可以改变
		listmaps.add(basicmaps);
		listmaps.add(measuremaps);
		listmaps.add(organmaps);
		listmaps.add(physicalmaps);
		listmaps.add(communicationmaps);
		listmaps.add(computingmaps);
		return listmaps;
	}

	/**
	 * 比较两个字符串，两个范围，两个数字等的区别,这个主要是比较从Slot表中读取出来的值
	 * 在这里可以针对特定的字段进行任意的特定的比较方式，如类似，非，等等
	 * 
	 * @param fieldname
	 *            比较的字段
	 * @param StrFromTable
	 *            从表中读取出来的字段的值
	 * @param fieldvalue
	 *            现有的比较值
	 * @return 如果满足符合条件，则返回true；否则返回false
	 */
	public static Boolean CompareCondition(String fieldname,
			String StrFromTable, String fieldvalue, boolean allownull) {

		// 这里是将所有的可能都加以排除，如只要有一个为空（因为fiedlvalue 不为空，或者为空字符串
		// 但是在比较传感器结束工作的时候需要考虑的是是否strFromTable的值为空
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
			// 对比之前还是将字符串的前后空格去除
			fieldname = fieldname.trim().toLowerCase();
			StrFromTable = StrFromTable.trim();
			// 传感器的全称 相似，就表中的包含了查询的
			if (fieldname.equals("sensorBasicLongName".toLowerCase())) {
				if (StrFromTable.toLowerCase().indexOf(
						fieldvalue.toLowerCase().trim()) >= 0) {
					return true;
				} else {
					return false;
				}
			}
			// 传感器的关键字的查询 sensorBasicKeywords sensorBasicShortName
			else if (fieldname.equals("sensorBasicKeywords".toLowerCase())) {
				// 将查询的关键字进行分类,第一个数字不能为空
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
			// 传感器的简称的查询 ，相似，即，表中的包含了查询的字段
			else if (fieldname.equals("sensorBasicShortName".toLowerCase())) {
				if (StrFromTable.toLowerCase().indexOf(
						fieldvalue.toLowerCase().trim()) >= 0) {
					return true;
				} else {
					return false;
				}
			}
			// 工作开始时间的比较
			else if (fieldname
					.equals("sensorBasicValidTimeBegin".toLowerCase())) {
				if (SpaceTimeInfoRelationUtil.CompareTime(fieldvalue.trim(),
						StrFromTable.trim())) {
					// 如果查询的时间比传感器的时间晚，返回true
					return true;
				} else {
					return false;
				}
			}
			// 工作结束时间的比较
			else if (fieldname.equals("sensorBasicValidTimeEnd".toLowerCase())) {
				if (StrFromTable.isEmpty()) {
					// 生成按照格式的文档
					Date currentTime = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String dateString = formatter.format(currentTime);
					StrFromTable = dateString;
				}
				if (SpaceTimeInfoRelationUtil.CompareTime(fieldvalue.trim(),
						StrFromTable.trim())) {
					// 如果查询的时间比传感器的时间晚，返回true
					return false;

				} else {
					return true;
				}
			} else if (fieldname.equals("sensorBasicIntendApplication"
					.toLowerCase())) {
				// 对于预期应用的处理，包含
				if (StrFromTable.toLowerCase().indexOf(
						fieldvalue.toLowerCase().trim()) >= 0) {
					return true;
				} else {
					return false;
				}
			}
			// 如果是经纬度范围 只要相交就可以
			else if (fieldname.equals("sensorBasicObservedRange".toLowerCase())) {
				// System.out.println("进入经纬度查询");
				// 表中的数据会产生如coordinate syste, north west,south east
				String[] tableValues = StrFromTable.split(",");
				String northValue = tableValues[1].split(" ")[0].trim();
				String westValue = tableValues[1].split(" ")[1].trim();
				String southValue = tableValues[2].split(" ")[0].trim();
				String eastValue = tableValues[2].split(" ")[1].trim();
				// 这是从表中获取的
				String[] litterspace = { northValue, westValue, southValue,
						eastValue };
				// 查询的值为产生 north,west,south,east
				String[] queryValues = fieldvalue.split(",");
				// 判断是不是原位传感器
				if (northValue.equals(southValue)
						&& westValue.equals(eastValue)) {
					// System.out.println("原位的");
					boolean bol = SpaceTimeInfoRelationUtil.GetContainResult(
							queryValues, litterspace);
					// System.out.println(bol);
					return bol;
				} else {
					// 就两个范围之间的包含查询
					Boolean bol = false;
					// 只要相交就可以
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
			// 数值型比较 存储容量
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
			// 数值型比较dataRate数据速率
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
			// 观测周期比较measurementInterval
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
			// 响应时间比较ResponseTime
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
			// 发射时间的比较epochTime,比较时间，查询的是传感器发射的时间要比
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
			// 卫星轨道高度比较
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
			// 轨道周期比较
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
			// 下行速率比较 downlinkRate
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
			// 波段数BandsNumber
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
			// 波段范围bandswidthRange
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
			// 波段数BandsNumber
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
			// 侧视角sideSwingAngle
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
			// 地面分辨率范围
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
			// 星下点分辨率
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
			// 瞬时视场角
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
			// 量化等级
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
			// 重返周期
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
			// 传感器位置信息比较
			else if (fieldname.equals("sensorBasicPosition")) {
				// 这是从用户这边传递过来的
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
				// 包含的处理
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
	 * 查询满足一定条件传感器（不指定查询的范围），只能查询针对单个字段与字段值
	 * 
	 * @param fieldname
	 *            查询字段
	 * @param fieldvalue
	 *            查询字段值
	 * @return 返回查询之后符合条件的传感器
	 */
	@SuppressWarnings("unchecked")
	public List<String> GetSensorIdThroughField(String fieldname,
			String fieldvalue) {
		// 需要返回的满足查询条件的传感器的id值
		List<String> sensorids = new ArrayList<String>();

		// 获取传感器的的全名
		// 步骤是连接到LocalizedString数据表中
		// 根据id值获得相应的value值
		// 返回该值
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		if (fieldname.indexOf("sensorMeasure") >= 0) {
			// 查询传感器的测量能力信息
			// 将sesnorMesage去除获取真正的字段，其首字母可以是大写也可以是小写
			String nameprefix = "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::measurementCapabilities:";
			String namevalue = fieldname.substring("sensorMeausre".length());
			String namevaluelitter = namevalue.substring(0, 1).toLowerCase()
					.concat(namevalue.substring(1));
			Query query = session
					.createQuery("select id from Slot where ( name = '"
							+ nameprefix + namevaluelitter + "' or name = '"
							+ nameprefix + namevalue + "' )and values = '"
							+ fieldvalue.trim() + ",'");
			// 返回查询获得信息
			sensorids = (List<String>) query.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		} else if (fieldname.indexOf("sensorPhysical") >= 0) {
			// 查询传感器的物理属性信息
			String nameprefix = "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::physicalProperties:property:";
			String namevalue = fieldname.substring("sensorPhysical".length());
			String namevaluelitter = namevalue.substring(0, 1).toLowerCase()
					.concat(namevalue.substring(1));
			Query query = session
					.createQuery("select id from Slot where ( name = '"
							+ nameprefix + namevaluelitter + "' or name = '"
							+ nameprefix + namevalue + "' )and values = '"
							+ fieldvalue.trim() + ",'");
			// 返回查询获得信息
			sensorids = (List<String>) query.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		} else if (fieldname.indexOf("sensorComputing") >= 0) {
			// 查询传感器的计算能力信息

			String nameprefix = "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::computingCapability:";
			String namevalue = fieldname.substring("sensorComputing".length());
			String namevaluelitter = namevalue.substring(0, 1).toLowerCase()
					.concat(namevalue.substring(1));
			Query query = session
					.createQuery("select id from Slot where ( name = '"
							+ nameprefix + namevaluelitter + "' or name = '"
							+ nameprefix + namevalue + "' )and values = '"
							+ fieldvalue.trim() + ",'");
			// 返回查询获得信息
			sensorids = (List<String>) query.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		} else if (fieldname.indexOf("sensorCommunication") >= 0) {
			// 查询传感器的通信能力信息
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
			// 返回查询获得信息
			sensorids = (List<String>) query.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);

		} else {
			// 查询传感器的基本信息
			// 关掉链接
			GetSessionUtil.CloseSessionInstance(session);
		}
		List<String> sensors = new ArrayList<String>();
		for (String slotid : sensorids) {
			sensors.add(GetSensorIdFromSlotId(slotid));
		}
		return sensors;
	}

	/**
	 * 从单个的提取到的传感器的Slot的id信息中获取传感器的id信息
	 * 
	 * @param slotid
	 *            从数据库提取出来的Slot的id的信息
	 * @return 返回的是对应的传感器的标识符的信息，不包含:package
	 */
	public static String GetSensorIdFromSlotId(String slotid) {
		String queryStr = slotid.trim();
		if (queryStr.indexOf("ExtrinsicObject") >= 0) {
			String panduanStr = "ExtrinsicObject";
			// 如果该slot是ExtrinsicObject的子元素，那么，就删掉:ExtrinsicObject之后的信息
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
	 * 查询满足一定条件传感器（不指定查询的范围），查询可以针对多个字段与字段值,其中字段与字段值需要匹配 （And查询）
	 * 
	 * @param queryStr
	 *            查询字段值
	 * @param allownull
	 *            是否允许返回该字段不存在值的传感器列表
	 * @return 返回查询之后符合条件的传感器
	 */
	public List<String> GetSensorIdsThroughFields(Map<String, String> queryStr,
			boolean allownull) {
		// 该值的作用在于第一次可以给提供最初所满足第一个条件的所有的sensor的标示符；
		// 之后就可以在这些sensor中在进行查询，这是and操作
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
				// 就可以从这些sensor的id当中获取符合条件的传感器，这些操作就可以使以id来进行遍历
				for (int j = 0; j < sensorids.size(); j++) {
					sensorids = GetSensorIdsThroughFieldAndRange(rename.trim(),
							queryStr.get(rename.trim()), sensorids, allownull);
				}
			}
		}
		return null;
	}

	/**
	 * 为查询字段去除前缀,但是不考虑去除前缀第一个单词的大小写问题
	 * 
	 * @param fieldname
	 *            查询的字段（包含前缀）
	 * @param fieldPrefix
	 *            需要去除的前缀
	 * @return 返回不包含前缀的查询字段名
	 */
	public static String RemovePrefixOfField(String fieldname,
			String fieldPrefix) {
		return fieldname = fieldname.trim().replaceFirst(fieldPrefix, "")
				.trim();
	}

	/**
	 * 查询满足一定条件传感器（同时指定查询的范围），只能查询针对单个字段与字段值， 该方法效率会比较低，之后会采用一种更加有效率的方法
	 * 
	 * @param fieldname
	 *            查询的字段,包含了前缀的如sensorMeasure
	 * @param fieldvalue
	 *            查询的字段的值
	 * @param sensoridsforquery
	 *            查询的范围（传感器的id集合）不包含:package
	 * @return 返回查询之后符合条件的传感器
	 */
	public static List<String> GetSensorIdsThroughFieldAndRange(
			String fieldname, String fieldvalue,
			List<String> sensoridsforquery, boolean allownull) {
		// System.out.println("查询的传感器个数为:" + sensoridsforquery.size());
		// 保存已经存入的查询字段
		String originFieldname = fieldname;

		// 如果传感器id为空，则不查询，直接返回null
		if (sensoridsforquery == null || sensoridsforquery.size() == 0) {
			return null;
		}
		// 存放查询后满足条件的传感器的标识符的集合
		List<String> sensorids = new ArrayList<String>();
		sensorids.addAll(sensoridsforquery);
		for (int i = 0; i < sensoridsforquery.size(); i++) {
			if (sensorids == null || sensorids.size() == 0) {
				return null;
			}
			fieldname = originFieldname;
			// System.out.println("sensorids length：" + sensorids.size());
			// System.out.println("第" + (i + 1) + "次");
			// System.out.println(sensoridsforquery.size() + "-----------");
			// System.out.println("查询的字段为：" + fieldname);
			// System.out.println("查询的字段值为:" + fieldvalue);
			// System.out.println("查询的传感器标识符为：" + sensoridsforquery.get(i));
			// System.out.println();
			// 还有传感器的id需要进行匹配
			// 对需要查询的传感器的标示符进行处理
			String querysensorid = sensoridsforquery.get(i).trim();
			if (fieldname.indexOf("sensorMeasure") >= 0) {
				// 查询测量能力
				// 为查询字段去除前缀,无需考虑区分大小写（在查询语句中已经考虑）
				fieldname = RemovePrefixOfField(fieldname, "sensorMeasure");
				// 查询测量能力
				String resultStr = OperateSensorUtil
						.GetSensorCommonCapabilityMethod(querysensorid,
								fieldname.trim());
				if (!allownull) {
					if (null == StringUtil
							.checkStringIsNotNULLAndEmptyMethod(resultStr)
							|| resultStr.equals("null")) {
						sensorids.remove(querysensorid);
						// System.out.println("字段值不存在删除");
						continue;
					}
				}
				// 在这里，可能需要增加新的判断规则（还有的工作可能是需要进行推理工作
				if (!CompareCondition(fieldname, resultStr, fieldvalue,
						allownull)) {
					// 如果不存在，则将传感器中的相对应的传感器的id值删除
					sensorids.remove(querysensorid);
				}
				fieldname = originFieldname;
			} else if (fieldname.indexOf("sensorComputing") >= 0) {
				// 查询计算能力
				// 为查询字段去除前缀,无需考虑区分大小写（在查询语句中已经考虑）
				fieldname = RemovePrefixOfField(fieldname, "sensorComputing");
				String resultStr = OperateSensorUtil
						.GetSensorComputeCapabilityMethod(querysensorid,
								fieldname.trim());
				if (!allownull) {
					if (null == StringUtil
							.checkStringIsNotNULLAndEmptyMethod(resultStr)
							|| resultStr.equals("null")) {
						sensorids.remove(querysensorid);
						// System.out.println("字段值不存在删除");
						continue;
					}
				}
				// 在这里，可能需要增加新的判断规则，还有的工作可能是需要进行推理工作
				if (!CompareCondition(fieldname, resultStr, fieldvalue,
						allownull)) {
					// 如果不存在，则将传感器的相对应传感器的id值删除
					sensorids.remove(querysensorid);
				}
				fieldname = originFieldname;
			} else if (fieldname.indexOf("sensorCommunication") >= 0) {
				// 查询通信能力
				// 为查询字段去除前缀,无需考虑区分大小写（在查询语句中已经考虑）
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
						// System.out.println("字段值不存在删除");
						continue;
					}
				}
				if (!CompareCondition(fieldname, resultStr, fieldvalue,
						allownull)) {
					// 如果不存在，则将传感器的相对应传感器的id值删除
					sensorids.remove(querysensorid);
				}
				fieldname = originFieldname;
			} else if (fieldname.indexOf("sensorPhysical") >= 0) {
				// 查询物理属性信息
				// 为查询字段去除前缀,无需考虑区分大小写（在查询语句中已经考虑）
				fieldname = RemovePrefixOfField(fieldname, "sensorPhysical");
				String resultStr = OperateSensorUtil
						.GetSensorPhysicalPropertyMethod(querysensorid,
								fieldname.trim());
				if (!allownull) {
					if (null == StringUtil
							.checkStringIsNotNULLAndEmptyMethod(resultStr)
							|| resultStr.equals("null")) {
						sensorids.remove(querysensorid);
						// System.out.println("字段值不存在删除");
						continue;
					}
				}
				if (resultStr != null) {
					resultStr = resultStr.trim();
				}
				if (!CompareCondition(fieldname, resultStr, fieldvalue,
						allownull)) {
					// 如果不存在，则将传感器的相对应传感器的i值删除
					sensorids.remove(querysensorid);
					// System.out.println("删除成功!");
					// System.out.println();
				}
				fieldname = originFieldname;
			} else if (fieldname.indexOf("sensorOrgan") >= 0) {
				// 查询传感器所属组织信息
				// 为查询字段去除前缀,无需考虑区分大小写（在查询语句中已经考虑）
				fieldname = RemovePrefixOfField(fieldname, "sensorOrgan");
				String resultStr = OperateSensorUtil
						.GetSensorOrganizationInfoMethod(querysensorid);
				if (!allownull) {
					if (null == StringUtil
							.checkStringIsNotNULLAndEmptyMethod(resultStr)
							|| resultStr.equals("null")) {
						sensorids.remove(querysensorid);
						// System.out.println("字段值不存在删除");
						continue;
					}
				}
				if (!CompareCondition(fieldname, resultStr, fieldvalue,
						allownull)) {
					// 如果不存在，则将传感器的相对应传感器的i值删除
					sensorids.remove(querysensorid);
				}
				fieldname = originFieldname;
			} else if (fieldname.indexOf("sensorBasic") >= 0) {
				if (fieldname.equals("sensorBasicSensorID")) {
					List<String> temsensors = new ArrayList<String>();
					temsensors.addAll(sensorids);
					for (String sensor : temsensors) {
						if (!sensor.contains(fieldvalue)) {
							// 将其中的移除
							sensorids.remove(sensor);
						}
					}
					continue;
				}
				// 对于传感器的基本信息的查询
				String resultStr = OperateSensorUtil
						.GetSensorBasicInfoByFieldNameMethod(fieldname,
								querysensorid);
				if (!allownull) {
					if (!fieldname.equals("sensorBasicValidTimeEnd")
							&& (null == StringUtil
									.checkStringIsNotNULLAndEmptyMethod(resultStr) || resultStr
									.equals("null"))) {
						sensorids.remove(querysensorid);
						System.out.println("字段值不存在删除");
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
					// 如果不存在，则将传感器的相对应传感器的i值删除
					sensorids.remove(querysensorid);
				}
			}
		}
		return sensorids;
	}

	/**
	 * 查询满足一定条件传感器（同时指定查询的范围），查询可以针对多个字段与字段值,其中字段与字段值需要匹配，查询的传感器的范围，
	 * 应该随着每一次的查询而改变；该方法会充分的调用GetSensorIdsThroughFieldAndRange方法提供的功能
	 * 
	 * @param fieldnames
	 *            查询的字段
	 * @param fieldvalues
	 *            查询的字段的值(与fieldnames中的字段的顺序对应)
	 * @param sensoridsforquery
	 *            查询的范围（传感器的id集合）
	 * @param allownull
	 *            是否运行返回不存在该字段值的传感器标识符
	 * @return 返回查询之后符合条件的传感器
	 */
	public List<String> GetSensorIdsThroughFieldsAndRange(String[] fieldnames,
			String[] fieldvalues, List<String> sensoridsforquery,
			boolean allownull) {
		// 存储满足所有查询条件的传感器的标识符
		List<String> resultSensorIds = new ArrayList<String>();
		resultSensorIds = sensoridsforquery;
		if (fieldnames == null || fieldnames.length == 0) {
			// 如果查询条件为空，或不存在，则直接返回查询的范围
			return sensoridsforquery;
		}
		for (int i = 0; i < fieldnames.length; i++) {
			if (resultSensorIds.size() == 0) {
				// 表明没有符合条件的传感器
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
	 * 查询满足一定条件传感器（同时指定查询的范围），查询可以针对多个字段与字段值,其中字段与字段值需要匹配，查询的传感器的范围，
	 * 应该随着每一次的查询而改变；该方法会充分的调用GetSensorIdsThroughFieldAndRange方法提供的功能
	 * 
	 * @param fieldnames
	 *            查询的字段
	 * @param fieldvalues
	 *            查询的字段的值(与fieldnames中的字段的顺序对应)
	 * @param sensoridsforquery
	 *            查询的范围（传感器的id集合）
	 * @return 返回查询之后符合条件的传感器
	 */
	public static List<String> GetSensorIdsThroughFieldsAndRangeByList(
			List<String> fieldnames, List<String> fieldvalues,
			List<String> sensoridsforquery, boolean allownull) {
		if (fieldnames == null || fieldnames.size() == 0) {
			// 如果查询条件为空，或不存在，则直接返回查询的范围
			return sensoridsforquery;
		}
		// 存储满足所有查询条件的传感器的标识符
		List<String> resultSensorIds = sensoridsforquery;
		if (resultSensorIds == null || resultSensorIds.size() == 0) {
			// 表明没有符合条件的传感器
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
