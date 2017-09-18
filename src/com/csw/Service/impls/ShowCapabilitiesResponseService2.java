package com.csw.Service.impls;

import com.csw.Service.interfaces.ShowCapabilitiesResponseServiceInterface2;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.GetCapabilitiesOperationUtil.ShowCapabilitiesUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class ShowCapabilitiesResponseService2 implements
		ShowCapabilitiesResponseServiceInterface2 {
	public String ShowCapabilitiesResponseMethod(String username,
			String password) throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		try {
			int num = UserInfoUtil.CheckUserInfo(username, password);
			if (num == 1) {
				ShowCapabilitiesUtil scu = new ShowCapabilitiesUtil();
				String resultcontent = FormatXmlUtil.formatXml(scu
						.CreateGetCapabilitiesResponseDocument());
				return resultcontent;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
