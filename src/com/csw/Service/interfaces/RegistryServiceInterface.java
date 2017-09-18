package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface RegistryServiceInterface {
	/**
	 * ע���û���Ϣ����ע���֮ǰ��Ҫ����һϵ�е��û�����֤�����û����û����Ƿ��Ѿ����ڵȵ�
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
	 * @return �ɹ�����1
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
