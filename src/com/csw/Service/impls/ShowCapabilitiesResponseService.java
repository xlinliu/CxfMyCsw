package com.csw.Service.impls;

import com.csw.Service.interfaces.ShowCapabilitiesResponseServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.GetCapabilitiesOperationUtil.ShowCapabilitiesUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class ShowCapabilitiesResponseService implements
		ShowCapabilitiesResponseServiceInterface {
	public String ShowCapabilitiesResponseMethod(String username,
			String password) throws ServiceException {
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);

		// ��ȡgetCapabilities���ĵ�������
		ShowCapabilitiesUtil scu = new ShowCapabilitiesUtil();
		// ���ظ�ʽ���˵�GetCapabilities���ĵ�������
		String resultcontent = FormatXmlUtil.formatXml(scu
				.CreateGetCapabilitiesResponseDocument());
		return resultcontent;
	}
}
