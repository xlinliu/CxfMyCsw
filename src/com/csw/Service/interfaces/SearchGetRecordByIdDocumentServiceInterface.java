package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface SearchGetRecordByIdDocumentServiceInterface {
	/**
	 * g���ݲ�ѯ��getrecordbyid���ĵ���ȡ��ѯ��id���ҽ���ѯ����registrypacakge�ĵ���������Ϣ���ظ��û�
	 * 
	 * @param username
	 * @param password
	 * @param getrecordByIdContent
	 * @return �ɹ�������Ӧ��id��registrypacakge���ĵ������ݣ�û���򷵻�null
	 */
	@WebMethod
	public String SearchGetRecordByIdDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "getrecordByIdContent") String getrecordByIdContent)
			throws ServiceException;
}
