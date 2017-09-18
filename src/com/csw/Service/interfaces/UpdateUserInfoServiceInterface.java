package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface UpdateUserInfoServiceInterface {
	/**
	 * �����û���Ϣ��ֻ�Ǹ����û����룬�û����ƣ��û��ֻ���
	 * 
	 * @param username
	 * @param password
	 * @param address
	 * @param telephone
	 * @return �ɹ�����1��ʧ�ܷ���0������һ������2��
	 */
	@WebMethod
	public int UpdateUserInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "address") String address,
			@WebParam(name = "telephone") String telephone)
			throws ServiceException;
}
