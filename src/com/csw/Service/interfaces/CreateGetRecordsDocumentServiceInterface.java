package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface CreateGetRecordsDocumentServiceInterface {
	/**
	 * �����û���д����Ϣ���ɼ򵥵�getRecords���ĵ�
	 * 
	 * @param username
	 *            �Ñ���
	 * @param password
	 *            ����
	 * @param startRecord
	 *            ��ʼ�Ĳ�ѯ��¼
	 * @param maximumRecord
	 *            ���ķ��صĲ�ѯ��¼��
	 * @param west
	 *            ���ߵ�ֵ
	 * @param east
	 *            ���ߵ�ֵ
	 * @param south
	 *            �ϱߵ�ֵ
	 * @param north
	 *            ���ߵ�ֵ
	 * @param startTime
	 *            ��ʼ��ֵ
	 * @param endTime
	 *            ������ʱ��
	 * @param requestType
	 *            ���������
	 * @param profileType
	 *            profile������
	 * @param title
	 *            ��ѯ�Ĺؼ� ����
	 * @param keyword
	 *            ��ѯ�Ĺؼ���
	 * @return ���ص�������getRecords���ĵ�������
	 */
	@WebMethod
	public String CreateGetRecofdsDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "startRecord") int startRecord,
			@WebParam(name = "maximumRecord") int maximumRecord,
			@WebParam(name = "west") String west,
			@WebParam(name = "east") String east,
			@WebParam(name = "south") String south,
			@WebParam(name = "north") String north,
			@WebParam(name = "startTime") String startTime,
			@WebParam(name = "endtime") String endTime,
			@WebParam(name = "requestType") String requestType,
			@WebParam(name = "profileType") String profileType,
			@WebParam(name = "title") String title,
			@WebParam(name = "keyword") String keyword) throws ServiceException;
}
