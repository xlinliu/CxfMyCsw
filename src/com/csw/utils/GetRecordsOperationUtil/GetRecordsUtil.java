package com.csw.utils.GetRecordsOperationUtil;

import java.math.BigInteger;

import org.apache.xmlbeans.XmlCursor;

import com.csw.utils.FileUtil.FileOperationUtil;
import com.ebrim.model.csw.GetRecordsDocument;
import com.ebrim.model.csw.QueryType;
import com.ebrim.model.ogc.AndDocument;
import com.ebrim.model.ogc.BinaryLogicOpType;
import com.ebrim.model.ogc.LogicOpsType;

public class GetRecordsUtil {
	/**
	 * 根据模板文件生成符合自己的查询条件的getRecords文档内容
	 * 
	 * @param modelfilepath
	 *            GetRecords的模板文件
	 * @return 返回 生成的满足自己查询的文件
	 */
	public static String CreateGetRecordsDocument(String modelfilepath,
			int maximumRecord, int startRecord, String requestType,
			String startTime, String endtime, String keyword, String title,
			String east, String north, String south, String west) {
		String xmlContent = FileOperationUtil.ReadFileContent(modelfilepath,
				"UTF-8");
		try {
			GetRecordsDocument grtDocument = GetRecordsDocument.Factory
					.parse(xmlContent);
			grtDocument.getGetRecords().setMaxRecords(
					new BigInteger(maximumRecord + ""));
			grtDocument.getGetRecords().setStartPosition(
					new BigInteger(startRecord + ""));
			grtDocument.getGetRecords().setService("CSW");
			((QueryType) grtDocument.getGetRecords().getAbstractQuery())
					.getElementSetName().setStringValue(requestType);
			LogicOpsType lotType = ((QueryType) grtDocument.getGetRecords()
					.getAbstractQuery()).getConstraint().getFilter()
					.getLogicOps();
			BinaryLogicOpType blotType = AndDocument.Factory.newInstance()
					.getAnd();
			blotType = (BinaryLogicOpType) lotType;
			XmlCursor xmlCursor = blotType.getComparisonOpsArray(0).newCursor();
			xmlCursor.toChild(1);
			xmlCursor.setTextValue(title);
			xmlCursor = blotType.getComparisonOpsArray(1).newCursor();
			xmlCursor.toChild(1);
			xmlCursor.setTextValue(keyword);
			xmlCursor = blotType.getComparisonOpsArray(2).newCursor();
			xmlCursor.toChild(1);
			xmlCursor.setTextValue(startTime);
			xmlCursor = blotType.getComparisonOpsArray(3).newCursor();
			xmlCursor.toChild(1);
			xmlCursor.setTextValue(endtime);
			xmlCursor = blotType.getSpatialOpsArray(0).newCursor();
			xmlCursor.toChild(1);
			xmlCursor.toChild(0);
			xmlCursor.setTextValue(east + " " + north);
			xmlCursor = blotType.getSpatialOpsArray(0).newCursor();
			xmlCursor.toChild(1);
			xmlCursor.toChild(1);
			xmlCursor.setTextValue(west + " " + south);
			xmlContent = grtDocument.xmlText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlContent;
	}
}
