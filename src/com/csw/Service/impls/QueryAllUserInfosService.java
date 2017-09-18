package com.csw.Service.impls;

import java.util.List;
import com.csw.Service.interfaces.QueryAllUserInfosServiceInterface;
import com.csw.beans.LoginUserBean;
import com.csw.exceptions.ServiceException;
import com.csw.utils.Userutils.UserInfoUtil;

public class QueryAllUserInfosService implements
		QueryAllUserInfosServiceInterface {
	public List<LoginUserBean> QueryAllUserInfosMethod(String username,
			String password) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return UserInfoUtil.GetAllUserLoginBeans();
	}
}
