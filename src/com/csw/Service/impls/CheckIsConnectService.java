package com.csw.Service.impls;

import com.csw.Service.interfaces.CheckIsConnectServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.Userutils.UserInfoUtil;

public class CheckIsConnectService implements CheckIsConnectServiceInterface {

	public boolean CheckIsConnectMethod(String username, String password)
			throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("用户名与密码不能为空!");
		}
		if (UserInfoUtil.CheckUserInfo(username, password) == 1) {
			return true;
		} else {
			return false;
		}
	}
}
