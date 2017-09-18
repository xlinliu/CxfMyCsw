package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface CheckIsConnectServiceInterface {
	/**
	 * 用于客户端判断系统是否可连接，如果输入的用户名与密码不能匹配，在数据库中不正确，则判断系统不可链接，否则就可以链接
	 * 
	 * @param username
	 *            用户姓名
	 * @param password
	 *            用户密码
	 * @return 能连接则为true，不能连接则为false
	 * @throws ServiceException
	 */
	@WebMethod
	public boolean CheckIsConnectMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
