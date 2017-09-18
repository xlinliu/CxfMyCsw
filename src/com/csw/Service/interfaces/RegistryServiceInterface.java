package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface RegistryServiceInterface {
	/**
	 * 注册用户信息，在注册的之前需要进行一系列的用户的验证，如用户的用户名是否已经存在等等
	 * 
	 * @param address
	 * @param age
	 * @param emailAddress
	 * @param gender
	 * @param level
	 * @param password
	 * @param telephone
	 * @param username
	 * @param zhiye
	 * @return 成功返回1
	 */
	@WebMethod
	public int RegistryMethod(@WebParam(name = "address") String address,
			@WebParam(name = "age") int age,
			@WebParam(name = "emailAddress") String emailAddress,
			@WebParam(name = "gender") int gender,
			@WebParam(name = "level") int level,
			@WebParam(name = "password") String password,
			@WebParam(name = "telephone") String telephone,
			@WebParam(name = "username") String username,
			@WebParam(name = "zhiye") String zhiye) throws ServiceException;
}
