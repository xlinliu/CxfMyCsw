package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface DeleteOtherInformationServiceInterface {
	/**
	 * ɾ��ָ�����û�����Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return ɾ���ɹ�����1��ɾ��ʧ�ܷ���0��ɾ�������쳣����2
	 */
	@WebMethod
	public int DeleteUserInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
