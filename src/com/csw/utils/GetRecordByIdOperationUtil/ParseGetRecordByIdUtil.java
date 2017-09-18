package com.csw.utils.GetRecordByIdOperationUtil;

import com.ebrim.model.csw.GetRecordByIdDocument;

public class ParseGetRecordByIdUtil {
	/**
	 * 解析getRecordByID文件，获取需要查询的Record的id值
	 * 
	 * @param getRecordByIdDocument
	 *            需要解析的getRecordById的文档内容
	 * @return 返回解析出的Record的id值，空则返回<b>null</b>
	 */
	public String ParseGetRecordByIdDocument(String getRecordByIdDocument) {
		String rpid = null;
		try {
			GetRecordByIdDocument grbidByIdDocument = GetRecordByIdDocument.Factory
					.parse(getRecordByIdDocument);
			if (grbidByIdDocument.getGetRecordById().getIdArray() != null) {
				rpid = grbidByIdDocument.getGetRecordById().getIdArray(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rpid;
	}
}
