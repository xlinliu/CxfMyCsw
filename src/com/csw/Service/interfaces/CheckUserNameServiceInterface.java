package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface CheckUserNameServiceInterface {
	/**
	 * 查询用户名是否已经存在
	 * 
	 * @param username
	 *            需要查询的用户名
	 * @return true则说明存在，false说明不存在
	 * @throws 返回异常信息
	 */
	@WebMethod
	public boolean CheckUserName(@WebParam(name = "username") String username)
			throws ServiceException;
}