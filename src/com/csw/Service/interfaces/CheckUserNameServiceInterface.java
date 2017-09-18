package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface CheckUserNameServiceInterface {
	/**
	 * ��ѯ�û����Ƿ��Ѿ�����
	 * 
	 * @param username
	 *            ��Ҫ��ѯ���û���
	 * @return true��˵�����ڣ�false˵��������
	 * @throws �����쳣��Ϣ
	 */
	@WebMethod
	public boolean CheckUserName(@WebParam(name = "username") String username)
			throws ServiceException;
}