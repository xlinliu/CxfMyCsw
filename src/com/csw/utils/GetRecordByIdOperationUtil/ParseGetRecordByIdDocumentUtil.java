package com.csw.utils.GetRecordByIdOperationUtil;

import org.apache.xmlbeans.XmlException;

import com.csw.utils.GetRecordsOperationUtil.GetRecordsOperation;
import com.ebrim.model.csw.GetRecordByIdDocument;
import com.ebrim.model.csw.GetRecordByIdResponseDocument;
import com.ebrim.model.csw.GetRecordByIdResponseType;
import com.ebrim.model.rim.RegistryPackageType;

public class ParseGetRecordByIdDocumentUtil {

	/**
	 * 将获取的信息包装成GetRecordByIdResponseDocument信息，并返回
	 * 
	 * @param id
	 *            需要返回的RegistryPackage的id值
	 * @return
	 */
	public static String WrapGetRecordByIdResponseDocumentMethod(String id) {
		String grbirdocument = "";

		RegistryPackageType rpt = GetRecordsOperation
				.GetRegistryPackageTypeById(id, "full");
		GetRecordByIdResponseDocument gbird = GetRecordByIdResponseDocument.Factory
				.newInstance();
		GetRecordByIdResponseType gbirt = gbird.addNewGetRecordByIdResponse();
		gbirt.set(rpt);
		return grbirdocument;
	}

	public static void main(String[] args) {
		WrapGetRecordByIdResponseDocumentMethod("urn:ogc:feature:insitusensor:Humidity_WE600:package");
	}

	/**
	 * 解析getRecordByIDContent的内容，并返回id值
	 * 
	 * @param getRecordByIDDocument
	 * @return
	 */
	public String GetIdOfGetRecordByIdContent(String getRecordByIDDocument) {
		String getrecordid = "";
		try {
			GetRecordByIdDocument gbiDocument = GetRecordByIdDocument.Factory
					.parse(getRecordByIDDocument);
			if (gbiDocument.getGetRecordById().getIdArray() != null
					&& gbiDocument.getGetRecordById().getIdArray().length > 0) {
				getrecordid = gbiDocument.getGetRecordById().getIdArray(0);
			}
		} catch (XmlException e) {
			e.printStackTrace();
		}
		return getrecordid;
	}
}
