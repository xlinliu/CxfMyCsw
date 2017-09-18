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
	// ����ѯ���ַ�������������
	public String CreateGetReocrdsXmlDocumentMethod(String filepath,
			String queryStr) {
		// ��ȡǰ���ĵ�
		String filebeginname = "\\files\\getrecordsbegin.txt";
		String documentbegin = FileOperationUtil.ReadFileContent(filepath
				+ filebeginname, "UTF-8");
		String middleDoc = "";
		if (queryStr == null || queryStr.length() == 0) {
			// �վͲ�����
		} else {
			// ��ȡ�м��ĵ�
			middleDoc = CreateMidDocByFields(queryStr);
		}
		// ��ȡ�����ĵ�
		String filenameend = "\\files\\getrecordsend.txt";
		String docuemntEnd = FileOperationUtil.ReadFileContent(filepath
				+ filenameend, "UTF-8");
		// ��Ϸ���
		return CombineThreeDocument(documentbegin, middleDoc, docuemntEnd);
	}

	/**
	 * ���ݲ�ѯ�����������м�Ĳ���
	 * 
	 * @param queryStr
	 *            ��ѯ���ֶ�
	 * @return ���������Ĳ�ѯ���м��ĵ�
	 */
	public String CreateMidDocByFields(String queryStr) {
		String AllStr = "";
		// ��һ�������ǽ���ѯ��������ֵ��ȡ����
		Map<String, String> fieldsmap = ParseInputQueryStrUtil
				.ParseInputQueryStrIntoListStrMethod(queryStr);
		// �ڵڶ��������ǽ���ѯ��������ֵ���з���
		List<Map<String, String>> maplists = QuerySensorInfoBasePatternUtil
				.OrderQueryFieldsUtil(fieldsmap);
		if (maplists == null) {
			return null;
		}
		for (int i = 0; i < maplists.size(); i++) {
			Map<String, String> tempMap = maplists.get(i);
			for (Iterator<String> iterator = maplists.get(i).keySet()
					.iterator(); iterator.hasNext();) {
				// ��ѯ���ֶ�
				String fieldname = iterator.next();
				String fieldvalue = tempMap.get(fieldname);
				// ���ɸ���Ƭ��
				String resultStr = CreateFieldDocument(fieldname, fieldvalue);
				// ������Ƭ����֯����
				AllStr += resultStr;
			}
		}
		return AllStr;
	}

	/**
	 * �����ֶκ��ֶ�ֵ�������Ӧ���ֶεĲ�ѯ��Ƭ�Σ����ﲻָ��,��ϵ������Ĭ�ϵ�����������������
	 * 
	 * @param fieldname
	 *            ��ѯ���ֶε�����
	 * @param fieldvalue
	 *            ��ѯ���ֶε�ֵ
	 * @return ����Ƭ��
	 */
	public String CreateFieldDocument(String fieldname, String fieldvalue) {
		String resultStr = "";
		// ����ǲ�����������ȷ���
		if (fieldname.indexOf("sensorMeasure") >= 0) {
			resultStr = CreatePresiceEqualPatchStringMethod(fieldname
					.substring("sensorMeasure".length()), fieldvalue);
		}
		// �������֯��Ϣ����ȷ���
		else if (fieldname.indexOf("sensorOrgan") >= 0) {
			resultStr = CreatePresiceEqualPatchStringMethod(fieldname
					.substring("sensorOrgan".length()), fieldvalue);
		}
		// ����Ǽ�����������ȷ���
		else if (fieldname.indexOf("sensorComputing") >= 0) {
			resultStr = CreatePresiceEqualPatchStringMethod(fieldname
					.substring("sensorComputing".length()), fieldvalue);
		}
		// �����ͨ����������ȷ���
		else if (fieldname.indexOf("sensorCommunication") >= 0) {
			resultStr = CreatePresiceEqualPatchStringMethod(fieldname
					.substring("sensorCommunication".length()), fieldvalue);
		}
		// ������������ԣ���ȷ���
		else if (fieldname.indexOf("sensorPhysical") >= 0) {
			resultStr = CreatePresiceEqualPatchStringMethod(fieldname
					.substring("sensorPhysical".length()), fieldvalue);
		}
		// ���������Ϣ����������
		else if (fieldname.indexOf("sensorBasic") >= 0) {
			resultStr = ProcessBasicSensorInfoMethod(fieldname, fieldvalue);
		}
		return resultStr.trim();
	}

	/**
	 * ��������������Ϣ�ķ���
	 * 
	 * @param fieldname
	 *            ������Ϣ�е�����
	 * @param fieldvalue
	 *            ������Ϣ��ֵ
	 * @return
	 */
	public String ProcessBasicSensorInfoMethod(String fieldname,
			String fieldvalue) {
		String resultStr = "";
		// ����ȫ��
		if (fieldname.equals("sensorBasicLongName")) {
			resultStr = CreatePropertyIsLikePatchStringMethod(fieldname
					.substring("sesnorBasic".length()), fieldvalue);
		}
		// ������
		else if (fieldname.equals("sensorBasicShortName")) {
			resultStr = CreatePropertyIsLikePatchStringMethod(fieldname
					.substring("sesnorBasic".length()), fieldvalue);
		}
		// ����ʼʱ��
		else if (fieldname.equals("sensorBasicValidTimeBegin")) {
			resultStr = CreateEqualOrLargePatchStringMethod(fieldname
					.substring("sensorBasic".length()), fieldvalue);
		}
		// �������ʱ��
		else if (fieldname.equals("sensorBasicValidTimeEnd")) {
			resultStr = CreateEqualOrLitterPatchStringMethod(fieldname
					.substring("sensorBasic".length()), fieldvalue);
		}
		// ����ؼ�����Ϣ
		else if (fieldname.equals("sensorBasicKeywords")) {
			resultStr = CreatePropertyIsLikePatchStringMethod(fieldname
					.substring("sensorBasic".length()), fieldvalue);
		}
		// ������������
		else if (fieldname.equals("sensorBasicSensorType")) {
			resultStr = CreatePropertyIsLikePatchStringMethod(fieldname
					.substring("sesnorBasic".length()), fieldvalue);
		}
		// ����Ԥ��Ӧ��
		else if (fieldname.equals("sensorBasicIntendApplication")) {
			resultStr = CreatePropertyIsLikePatchStringMethod(fieldname
					.substring("sensorBasic".length()), fieldvalue);
		}
		// ����۲췶Χ
		else if (fieldname.equals("sensorBasicObservedRange")) {
			resultStr = CreateBBOXPatchStringMethod(fieldname
					.substring("sensorBasic".length()), fieldvalue);
		}
		return resultStr;
	}

	/**
	 * ���ɷ�Χ�Ĳ�ѯƬ��
	 * 
	 * @param fieldname
	 *            ��ѯ���ֶ�
	 * @param fieldvalue
	 *            ��ѯ��ֵ ˳��Ϊ������ ���ϣ���
	 * @return ���ز�ѯ���ַ���
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
	 * ���ɴ��ڻ��ߵ��ڵĲ�ѯƬ��
	 * 
	 * @param fieldname
	 *            ��ѯ���ֶ�
	 * @param fieldvalue
	 *            ��ѯ���ֶ�ֵ
	 * @return ���ؽ��
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
	 *����С�ڻ��ߵ��ڵĲ�ѯƬ��
	 * 
	 * @param fieldname
	 *            ��ѯ���ֶ�
	 * @param fieldvalue
	 *            ��ѯ���ֶ�ֵ
	 * @return ���ز�ѯ��Ƭ��
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
	 * �������ƵĲ�ѯƬ��
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
	 * ���ɾ�ȷ�Ĳ�ѯ��Ƭ��
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
	 * ����ĵ�
	 * 
	 * @param startdoc
	 *            ��ʼ���ĵ�����
	 * @param middledoc
	 *            �м���ĵ�����
	 * @param enddoc
	 *            ��β���ĵ�����
	 * @return �����������ĵ�����
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
	 * ���Ժ���
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
