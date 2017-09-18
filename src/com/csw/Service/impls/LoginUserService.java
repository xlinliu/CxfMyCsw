package com.csw.Service.impls;

import javax.jws.WebService;
import com.csw.Service.interfaces.LoginUserInterface;
import com.csw.beans.LoginUserBean;
import com.csw.exceptions.ServiceException;
import com.csw.utils.Userutils.UserInfoUtil;

@WebService
public class LoginUserService implements LoginUserInterface {

	public LoginUserBean LoginUserMethod(String username, String password)
			throws ServiceException {
		return UserInfoUtil.UserLoginMethod(username, password);
	}
}
