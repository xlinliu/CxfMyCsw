package com.csw.utils.GetRecordByIdOperationUtil;

import com.ebrim.model.csw.GetRecordByIdDocument;

public class ParseGetRecordByIdUtil {
	/**
	 * ����getRecordByID�ļ�����ȡ��Ҫ��ѯ��Record��idֵ
	 * 
	 * @param getRecordByIdDocument
	 *            ��Ҫ������getRecordById���ĵ�����
	 * @return ���ؽ�������Record��idֵ�����򷵻�<b>null</b>
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
