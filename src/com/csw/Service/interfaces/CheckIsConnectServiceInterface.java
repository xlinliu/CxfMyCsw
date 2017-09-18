package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface CheckIsConnectServiceInterface {
	/**
	 * ���ڿͻ����ж�ϵͳ�Ƿ�����ӣ����������û��������벻��ƥ�䣬�����ݿ��в���ȷ�����ж�ϵͳ�������ӣ�����Ϳ�������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return ��������Ϊtrue������������Ϊfalse
	 * @throws ServiceException
	 */
	@WebMethod
	public boolean CheckIsConnectMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
