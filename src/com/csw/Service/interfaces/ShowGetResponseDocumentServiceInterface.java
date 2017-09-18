package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface ShowGetResponseDocumentServiceInterface {
	/**
	 * ͨ��getRecords���ĵ��Ļ�ȡgetRecordsResponse���ĵ�����Ϣ
	 * ���ĵ������Ƿ���getRecords�淶����Ϣ,����Ĳ�ѯֻ��Ҫ���û��Ϳ��ԣ����û�������Ȩ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param getrerecords
	 *            ��ȡ���ĵ������Ŷ�Ǹ�
	 * @param resultType
	 *            ���ص��ĵ�����Ϣ�����ͣ����֣��ֱ���full��summary��brief
	 * @return ��ѯ�ɹ�����getRecordsRepsonse���ĵ�����Ϣ
	 */
	@WebMethod
	public String GetRecordsContent(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "getRecordContent") String getrerecords,
			@WebParam(name = "resultType") String resultType)
			throws ServiceException;
}
