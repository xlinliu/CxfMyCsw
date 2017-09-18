package com.csw.Service.impls;

import javax.jws.WebService;

import com.csw.Service.interfaces.ChcekUserNameServiceInterface2;
import com.csw.utils.Userutils.UserInfoUtil;

@WebService
public class CheckUserNameService2 implements ChcekUserNameServiceInterface2 {

	/**
	 * 查询用户名是否已经存在
	 * 
	 * @param username
	 *            需要查询的用户名
	 * @return true则说明存在，false说明不存在
	 */
	public boolean CheckUserName(String username) {
		boolean bol = UserInfoUtil.CheckUserNameIsExist(username);
		return bol;
	}
}
