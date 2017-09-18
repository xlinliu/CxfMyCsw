package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface UpdateUserInfoServiceInterface {
	/**
	 * 更新用户信息，只是更新用户密码，用户抵制，用户手机号
	 * 
	 * @param username
	 * @param password
	 * @param address
	 * @param telephone
	 * @return 成功返回1，失败返回0，发生一场返回2；
	 */
	@WebMethod
	public int UpdateUserInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "address") String address,
			@WebParam(name = "telephone") String telephone)
			throws ServiceException;
}
