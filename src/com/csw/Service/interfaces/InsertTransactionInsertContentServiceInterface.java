package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface InsertTransactionInsertContentServiceInterface {
	/**
	 * ��Transaction-insert����Ϣ���뵽���ݿ���
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            �û�����
	 * @param content
	 *            ��Ϣ���ݼ�transaction-insert����Ϣ
	 * @param processType
	 *            process ������
	 * @param SerivceType
	 *            service�ķ�������
	 * @param intendedApplication
	 *            process��Ԥ��Ӧ��
	 * @return �ɹ�����1
	 */
	@WebMethod
	public int InsertTransactionInsertCotentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "content") String content,
			@WebParam(name = "processType") String processType,
			@WebParam(name = "SerivceType") String SerivceType,
			@WebParam(name = "intendedApplication") String intendedApplication)
			throws ServiceException;
}
