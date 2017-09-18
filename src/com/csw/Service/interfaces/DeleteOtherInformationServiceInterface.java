package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface DeleteOtherInformationServiceInterface {
	/**
	 * 删除指定的用户的信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return 删除成功返回1，删除失败返回0，删除出现异常返回2
	 */
	@WebMethod
	public int DeleteUserInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
