package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface AddRegistryPackageServiceInterface {
	/**
	 * 获取自身注册的所有的RegistryPackage
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @return 返回用户自身所注册的全部的RegistryPackage的id值，是以list的方式返回，如果 没有成功则返回null
	 * @throws 返回异常信息
	 */
	public List<String> GetOwnRegistryPackageMetod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
