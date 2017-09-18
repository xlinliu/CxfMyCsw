package com.csw.Service.impls;

import com.csw.Service.interfaces.SearchGetRecordByIdDocumentServiceInterface2;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.GetRecordByIdOperationUtil.ParseGetRecordByIdUtil;
import com.csw.utils.SensorInfoUtil.CreateGetRecordByIdDocumentUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class SearchGetRecordByIdDocumentService2 implements
		SearchGetRecordByIdDocumentServiceInterface2 {
	public String SearchGetRecordByIdDocumentMethod(String username,
			String password, String getrecordByIdContent)
			throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		try {
			ParseGetRecordByIdUtil parseGetRecordByIdUtil = new ParseGetRecordByIdUtil();
			String rpid = parseGetRecordByIdUtil
					.ParseGetRecordByIdDocument(getrecordByIdContent);
			String ebrimcontent = CreateGetRecordByIdDocumentUtil
					.CreateGetRecordByIdResponseDocumentMethod(rpid);
			ebrimcontent = FormatXmlUtil.formatXml(ebrimcontent);
			return ebrimcontent;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
