package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "checkusernameinterface")
public interface ChcekUserNameServiceInterface2 {
	/**
	 * ��ѯ�û����Ƿ��Ѿ�����
	 * 
	 * @param username
	 *            ��Ҫ��ѯ���û���
	 * @return true��˵�����ڣ�false˵��������
	 */
	@WebMethod
	public boolean CheckUserName(
			@WebParam(name = "username", header = true) String username);
}