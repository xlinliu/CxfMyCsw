package com.csw.interfaces.querypackage.impls;

import com.csw.interfaces.querypackage.CreateGetRecordsDetailInterface;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.GetRecordsOperationUtil.CreateGetRecordsXMLDocument;

public class CreateGetRecordsDetailService implements
		CreateGetRecordsDetailInterface {

	public String CreateGetReocdsDetailMethod(String username, String password,
			String queryStr) {

		String basepath = new GetRealPathUtil().getWebInfPath();
		String str = new CreateGetRecordsXMLDocument()
				.CreateGetReocrdsXmlDocumentMethod(basepath, queryStr);
		if (str != null) {
			str = FormatXmlUtil.formatXml(str);
		}
		return str;
	}
}
