package com.csw.Service.impls;

import com.csw.Service.interfaces.SearchGetRecordByIdDocumentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.GetRecordByIdOperationUtil.ParseGetRecordByIdUtil;
import com.csw.utils.SensorInfoUtil.CreateGetRecordByIdDocumentUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class SearchGetRecordByIdDocumentService implements
		SearchGetRecordByIdDocumentServiceInterface {
	public String SearchGetRecordByIdDocumentMethod(String username,
			String password, String getrecordByIdContent)
			throws ServiceException {
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil
				.checkStringIsNotNULLAndEmptyMethod(getrecordByIdContent)) {
			throw new ServiceException("����getrecordByIdContent����������ʵ !");
		}
		// �ڶ���������getRecordById�ĵ�����ȡ��ѯ��id
		ParseGetRecordByIdUtil parseGetRecordByIdUtil = new ParseGetRecordByIdUtil();
		String rpid = parseGetRecordByIdUtil
				.ParseGetRecordByIdDocument(getrecordByIdContent);
		if (rpid == null) {
			throw new ServiceException("�ϴ����ĵ�getrecordByIdContent���淶�����ʵ!");
		}
		String ebrimcontent;
		ebrimcontent = CreateGetRecordByIdDocumentUtil
				.CreateGetRecordByIdResponseDocumentMethod(rpid);
		ebrimcontent = FormatXmlUtil.formatXml(ebrimcontent);
		return ebrimcontent;
	}
}
