package com.csw.interfaces.querypackage;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CreateGetRecordsDetailInterface {
	/**
	 * ���ݲ�ѯ���ֶ�����getRecords���ļ�����
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            �û�����
	 * @param queryStr
	 *            ��ѯ����������ɵģ�һ���Ĺ������
	 * @return �������ɵ�getRecords���ĵ�����
	 */
	@WebMethod
	public String CreateGetReocdsDetailMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "queryStr") String queryStr);
}
