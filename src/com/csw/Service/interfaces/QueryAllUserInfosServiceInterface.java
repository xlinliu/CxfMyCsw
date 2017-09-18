package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.beans.LoginUserBean;
import com.csw.exceptions.ServiceException;

@WebService
public interface QueryAllUserInfosServiceInterface {
	/**
	 * 查询所有的用户的信息，而查询的用户的权限必须是系统管理员级别的用户
	 * 
	 * @param username
	 *            系统管理员的用户名称
	 * @param password
	 *            系统管理员的用户密码
	 * @return 返回所有的用户的资料，
	 */
	@WebMethod
	public List<LoginUserBean> QueryAllUserInfosMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
