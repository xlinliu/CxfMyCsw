package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "checkusernameinterface")
public interface ChcekUserNameServiceInterface2 {
	/**
	 * 查询用户名是否已经存在
	 * 
	 * @param username
	 *            需要查询的用户名
	 * @return true则说明存在，false说明不存在
	 */
	@WebMethod
	public boolean CheckUserName(
			@WebParam(name = "username", header = true) String username);
}