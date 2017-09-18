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
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);

		// 获取getCapabilities的文档的内容
		ShowCapabilitiesUtil scu = new ShowCapabilitiesUtil();
		// 返回格式化了的GetCapabilities的文档的内容
		String resultcontent = FormatXmlUtil.formatXml(scu
				.CreateGetCapabilitiesResponseDocument());
		return resultcontent;
	}
}
