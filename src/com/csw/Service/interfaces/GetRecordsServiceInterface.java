package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetRecordsServiceInterface {

	/**
	 * ���ݻ�ȡ�Ĳ�ѯ���������в�ѯ֮���ȡ�����д��������ĵ�����ɵ�getRecords���ĵ�����Ϣ
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * 
	 * @param allownull
	 *            �Ƿ����и��ֶ�Ϊ�յĴ���������
	 * @param queryStr
	 *            �û���ѯ���ֶ�����ɵ��ַ�������������Լ��
	 * @return
	 * @throws ServiceException
	 *             ���ִ����򷵻ش�����Ϣ
	 */
	@WebMethod
	public String GetRecordsDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "queryStr") String queryStr,
			@WebParam(name = "allownull") boolean allownull

	) throws ServiceException;
}
