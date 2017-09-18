package com.csw.Service.interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface CreateGetRcordByIdDocumentServiceInterface {
	/**
	 * ����record��idֵ����ȡ����records�Ĳ�ѯ����
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @param recordId
	 *            record��idֵ�������sensorml��id������ת��Ϊebrim��ʽ��sensor��id���в�ѯ
	 * @return �������ɵ�getRecords�Ĳ�ѯ�ĵ���ʧ���򷵻�null
	 * @throws �����쳣��Ϣ
	 */
	public String CreateGetRecordByIdDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "recordId") String recordId)
			throws ServiceException;
}
