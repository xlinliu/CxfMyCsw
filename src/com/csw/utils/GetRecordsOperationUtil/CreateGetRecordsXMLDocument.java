package com.csw.utils.GetRecordsOperationUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.TransactionsUtil.ParseInputQueryStrUtil;
import com.csw.utils.TransactionsUtil.QuerySensorInfoBasePatternUtil;

public class CreateGetRecordsXMLDocument {
	// 将查询的字符串传感器过来
	public String CreateGetReocrdsXmlDocumentMethod(String filepath,
			String queryStr) {
		// 读取前部文档
		String filebeginname = "\\files\\getrecordsbegin.txt";
		String documentbegin = FileOperationUtil.ReadFileContent(filepath
				+ filebeginname, "UTF-8");
		String middleDoc = "";
		if (queryStr == null || queryStr.length() == 0) {
			// 空就不处理
		} else {
			// 读取中间文档
			middleDoc = CreateMidDocByFields(queryStr);
		}
		// 读取后面文档
		String filenameend = "\\files\\getrecordsend.txt";
		String docuemntEnd = FileOperationUtil.ReadFileContent(filepath
				+ filenameend, "UTF-8");
		// 组合返回
		return CombineThreeDocument(documentbegin, middleDoc, docuemntEnd);
	}

	/**
	 * 根据查询的条件生成中间的部分
	 * 
	 * @param queryStr
	 *            查询的字段
	 * @return 返回完整的查询的中间文档
	 */
	public String CreateMidDocByFields(String queryStr) {
		String AllStr = "";
		// 第一步，还是将查询的条件与值获取出来
		Map<String, String> fieldsmap = ParseInputQueryStrUtil
				.ParseInputQueryStrIntoListStrMethod(queryStr);
		// 第第二步，则是将查询的条件与值进行分类
		List<Map<String, String>> maplists = QuerySensorInfoBasePatternUtil
				.OrderQueryFieldsUtil(fieldsmap);
		if (maplists == null) {
			return null;
		}
		for (int i = 0; i < maplists.size(); i++) {
			Map<String, String> tempMap = maplists.get(i);
			for (Iterator<String> iterator = maplists.get(i).keySet()
					.iterator(); iterator.hasNext();) {
				// 查询的字段
				String fieldname = iterator.next();
				String fieldvalue = tempMap.get(fieldname);
				// 生成各个片段
				String resultStr = CreateFieldDocument(fieldname, fieldvalue);
				// 将各个片段组织起来
				AllStr += resultStr;
			}
		}
		return AllStr;
	}

	/**
	 * 根据字段和字段值生成相对应的字段的查询的片段（这里不指定,关系，按照默认的来），后面在增加
	 * 
	 * @param fieldname
	 *            查询的字段的名称
	 * @param fieldvalue
	 *            查询的字段的值
	 * @return 返回片段
	 */
	public String CreateFieldDocument(String fieldname, String fieldvalue) {
		String resultStr = "";
		// 如果是测量能力，精确相等
		if (fieldname.indexOf("sensorMeasure") >= 0) {
			resultStr = CreatePresiceEqualPatchStringMethod(fieldname
					.substring("sensorMeasure".length()), fieldvalue);
		}
		// 如果是组织信息，精确相等
		else if (fieldname.indexOf("sensorOrgan") >= 0) {
			resultStr = CreatePresiceEqualPatchStringMethod(fieldname
					.substring("sensorOrgan".length()), fieldvalue);
		}
		// 如果是计算能力，精确相等
		else if (fieldname.indexOf("sensorComputing") >= 0) {
			resultStr = CreatePresiceEqualPatchStringMethod(fieldname
					.substring("sensorComputing".length()), fieldvalue);
		}
		// 如果是通信能力，精确相等
		else if (fieldname.indexOf("sensorCommunication") >= 0) {
			resultStr = CreatePresiceEqualPatchStringMethod(fieldname
					.substring("sensorCommunication".length()), fieldvalue);
		}
		// 如果是物理属性，精确相等
		else if (fieldname.indexOf("sensorPhysical") >= 0) {
			resultStr = CreatePresiceEqualPatchStringMethod(fieldname
					.substring("sensorPhysical".length()), fieldvalue);
		}
		// 如果基本信息，则具体分析
		else if (fieldname.indexOf("sensorBasic") >= 0) {
			resultStr = ProcessBasicSensorInfoMethod(fieldname, fieldvalue);
		}
		return resultStr.trim();
	}

	/**
	 * 处理传感器基本信息的方法
	 * 
	 * @param fieldname
	 *            基本信息中的名称
	 * @param fieldvalue
	 *            基本信息的值
	 * @return
	 */
	public String ProcessBasicSensorInfoMethod(String fieldname,
			String fieldvalue) {
		String resultStr = "";
		// 处理全称
		if (fieldname.equals("sensorBasicLongName")) {
			resultStr = CreatePropertyIsLikePatchStringMethod(fieldname
					.substring("sesnorBasic".length()), fieldvalue);
		}
		// 处理简称
		else if (fieldname.equals("sensorBasicShortName")) {
			resultStr = CreatePropertyIsLikePatchStringMethod(fieldname
					.substring("sesnorBasic".length()), fieldvalue);
		}
		// 处理开始时间
		else if (fieldname.equals("sensorBasicValidTimeBegin")) {
			resultStr = CreateEqualOrLargePatchStringMethod(fieldname
					.substring("sensorBasic".length()), fieldvalue);
		}
		// 处理结束时间
		else if (fieldname.equals("sensorBasicValidTimeEnd")) {
			resultStr = CreateEqualOrLitterPatchStringMethod(fieldname
					.substring("sensorBasic".length()), fieldvalue);
		}
		// 处理关键字信息
		else if (fieldname.equals("sensorBasicKeywords")) {
			resultStr = CreatePropertyIsLikePatchStringMethod(fieldname
					.substring("sensorBasic".length()), fieldvalue);
		}
		// 处理传感器类型
		else if (fieldname.equals("sensorBasicSensorType")) {
			resultStr = CreatePropertyIsLikePatchStringMethod(fieldname
					.substring("sesnorBasic".length()), fieldvalue);
		}
		// 处理预期应用
		else if (fieldname.equals("sensorBasicIntendApplication")) {
			resultStr = CreatePropertyIsLikePatchStringMethod(fieldname
					.substring("sensorBasic".length()), fieldvalue);
		}
		// 处理观察范围
		else if (fieldname.equals("sensorBasicObservedRange")) {
			resultStr = CreateBBOXPatchStringMethod(fieldname
					.substring("sensorBasic".length()), fieldvalue);
		}
		return resultStr;
	}

	/**
	 * 生成范围的查询片段
	 * 
	 * @param fieldname
	 *            查询的字段
	 * @param fieldvalue
	 *            查询的值 顺序为北，西 ，南，东
	 * @return 返回查询的字符串
	 */
	public String CreateBBOXPatchStringMethod(String fieldname,
			String fieldvalue) {
		String queryname = "";
		String resultStr = "";
		try {
			String oneStr = "<ogc:Contains><ogc:PropertyName>";
			String twoStr = "</ogc:PropertyName><gml:Envelope><gml:lowerCorner>";
			String threeStr = "</gml:lowerCorner><gml:upperCorner>";
			String fourStr = "</gml:upperCorner></gml:Envelope></ogc:Contains>";
			if (fieldname.equals("ObservedRange")) {
				queryname = "ows:BoundingBox";
				String[] fields = fieldvalue.trim().split(",");
				String firstconcer = fields[0].trim().concat(
						" " + fields[1].trim());
				String twoconcer = fields[2].trim().concat(
						" " + fields[3].trim());
				resultStr = oneStr.concat(queryname).concat(twoStr).concat(
						firstconcer).concat(threeStr).concat(twoconcer).concat(
						fourStr).trim();
			}
			return resultStr;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 生成大于或者等于的查询片段
	 * 
	 * @param fieldname
	 *            查询的字段
	 * @param fieldvalue
	 *            查询的字段值
	 * @return 返回结果
	 */
	public String CreateEqualOrLargePatchStringMethod(String fieldname,
			String fieldvalue) {
		String queryname = "";
		try {
			String oneStr = "<ogc:PropertyIsGreaterThanOrEqualTo><ogc:PropertyName>";
			String twoStr = "</ogc:PropertyName><ogc:Literal>";
			String threeStr = "</ogc:Literal></ogc:PropertyIsGreaterThanOrEqualTo>";
			if (fieldname.equals("ValidTimeBegin")) {
				queryname = "dc:start";
			} else {
				queryname = "dc:".concat(fieldname);
			}
			return oneStr.concat(queryname).concat(twoStr).concat(
					fieldvalue.trim()).concat(threeStr);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 *生成小于或者等于的查询片段
	 * 
	 * @param fieldname
	 *            查询的字段
	 * @param fieldvalue
	 *            查询的字段值
	 * @return 返回查询的片段
	 */
	public String CreateEqualOrLitterPatchStringMethod(String fieldname,
			String fieldvalue) {
		String queryname = "";
		try {
			String oneStr = "<ogc:PropertyIsLessThanOrEqualTo><ogc:PropertyName>";
			String twoStr = "</ogc:PropertyName><ogc:Literal>";
			String threeStr = "</ogc:Literal></ogc:PropertyIsLessThanOrEqualTo>";
			if (fieldname.equals("ValidTimeEnd")) {
				queryname = "dc:end";
			} else {
				queryname = "dc:".concat(fieldname);
			}
			return oneStr.concat(queryname).concat(twoStr).concat(
					fieldvalue.trim()).concat(threeStr);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 生成类似的查询片断
	 * 
	 * @param fieldname
	 * @param fieldvalue
	 * @return
	 */
	public String CreatePropertyIsLikePatchStringMethod(String fieldname,
			String fieldvalue) {
		String queryname = "";
		try {
			String oneStr = "<ogc:PropertyIsLike escapeChar=\"\\\" singleChar=\"?\" wildCard=\"*\"><ogc:PropertyName>";
			String twoStr = "</ogc:PropertyName><ogc:Literal>";
			String threeStr = "</ogc:Literal></ogc:PropertyIsLike>";
			if (fieldname.equals("LongName")) {
				queryname = "dc:title";
			} else if (fieldname.equals("Keywords")) {
				queryname = "dc:keywords";
			} else {
				queryname = "dc:".concat(fieldname);
			}
			String result = oneStr.concat(queryname).concat(twoStr).concat(
					fieldvalue.trim()).concat(threeStr);
			// System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 生成精确的查询的片段
	 * 
	 * @param fieldname
	 * @param fieldvalue
	 * @return
	 */
	public String CreatePresiceEqualPatchStringMethod(String fieldname,
			String fieldvalue) {
		String queryname = "";
		try {
			String oneStr = "<ogc:PropertyIsEqualTo><ogc:PropertyName>";
			String twoStr = "</ogc:PropertyName><ogc:Literal>";
			String threeStr = "</ogc:Literal></ogc:PropertyIsEqualTo>";
			if (fieldname.equals("SensorType")) {
				queryname = "dc:type";
			} else {
				queryname = "dc:".concat(fieldname.trim());
			}
			return oneStr.concat(queryname).concat(twoStr).concat(
					fieldvalue.trim()).concat(threeStr);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 组合文档
	 * 
	 * @param startdoc
	 *            开始的文档部分
	 * @param middledoc
	 *            中间的文档部分
	 * @param enddoc
	 *            结尾的文档部分
	 * @return 返回完整的文档部分
	 */
	public String CombineThreeDocument(String startdoc, String middledoc,
			String enddoc) {
		try {
			return FormatXmlUtil.formatXml(startdoc.concat(middledoc).concat(
					enddoc).trim());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 测试函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String filepath = "";
		filepath = new GetRealPathUtil().getWebInfPath();
		System.out.println(filepath);
		System.out.println(new CreateGetRecordsXMLDocument()
				.CreateGetReocrdsXmlDocumentMethod(filepath,
						"sensorOrganOgranizationName 1 |"));
	}
}
