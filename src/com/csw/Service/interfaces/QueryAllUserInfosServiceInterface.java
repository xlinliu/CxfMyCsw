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
	 * ��ѯ���е��û�����Ϣ������ѯ���û���Ȩ�ޱ�����ϵͳ����Ա������û�
	 * 
	 * @param username
	 *            ϵͳ����Ա���û�����
	 * @param password
	 *            ϵͳ����Ա���û�����
	 * @return �������е��û������ϣ�
	 */
	@WebMethod
	public List<LoginUserBean> QueryAllUserInfosMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
