package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface ShowCapabilitiesResponseServiceInterface2 {
	/**
	 * ��ȡϵͳ���ṩCSW�Ĺ��ܽ���ģ����ĵ�����Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return �ɹ������ĵ�����Ϣ��ʧ�ܷ���null�������쳣����null
	 */
	@WebMethod
	public String ShowCapabilitiesResponseMethod(
			@WebParam(name = "usernmae", header = true) String username,
			@WebParam(name = "password", header = true) String password)
			throws ServiceException;
}
