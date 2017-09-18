package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface SearchGetRecordByIdDocumentServiceInterface2 {
	/**
	 * g���ݲ�ѯ��getrecordbyid���ĵ���ȡ��ѯ��id���ҽ���ѯ����registrypacakge�ĵ���������Ϣ���ظ��û�
	 * 
	 * @param username
	 *            ��ѯ���û���
	 * @param password
	 *            ��ѯ���û�����
	 * @param getrecordByIdContent
	 *            ��ѯ��getRecordById���ĵ�����
	 * @return �ɹ�������Ӧ��id��registrypacakge���ĵ������ݣ�ʧ���򷵻�null���쳣�򷵻�null
	 */
	@WebMethod
	public String SearchGetRecordByIdDocumentMethod(
			@WebParam(name = "username", header = true) String username,
			@WebParam(name = "password", header = true) String password,
			@WebParam(name = "getrecordByIdContent") String getrecordByIdContent)
			throws ServiceException;
}
