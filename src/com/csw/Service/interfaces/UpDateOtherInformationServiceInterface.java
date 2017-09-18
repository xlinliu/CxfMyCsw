package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface UpDateOtherInformationServiceInterface {
	/**
	 * 更改用户的信息
	 * 
	 * @param adminusername
	 *            更新用户权限的管理员用户的名称
	 * @param adminpassword
	 *            更新用户权限的管理员用户的密码
	 * @param username
	 *            需要更新的用户的名称，不能更改
	 * @param password
	 *            需要更新的用户的密码，不能更改
	 * @param level
	 *            可以更改的用户的级别，可以更改
	 * @return 更新成功则返回1，更新失败返回0，发生异常返回2；
	 */
	@WebMethod
	public int UpdateOtherInformationMethod(
			@WebParam(name = "adminusername") String adminusername,
			@WebParam(name = "adminpassword") String adminpassword,
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "newpassword") String newpassword,
			@WebParam(name = "level") int level) throws ServiceException;
}
