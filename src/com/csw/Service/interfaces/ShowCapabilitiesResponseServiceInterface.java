package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface ShowCapabilitiesResponseServiceInterface {
	/**
	 * 获取系统的提供CSW的功能介绍模块的文档的信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return 成功返回文档的信息，失败返回null，出现异常返回null
	 */
	@WebMethod
	public String ShowCapabilitiesResponseMethod(
			@WebParam(name = "usernmae") String username,
			@WebParam(name = "password") String password)throws ServiceException;
}
