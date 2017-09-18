package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface TransactionUpdateContentServiceInterface {
	/**
	 *ͨ��transactionupdate���ĵ��������Ѿ��洢��registrypacakge����Ϣ��
	 * ���û�д洢���Ӧ��registrypacakge����Ϣ��Ҳ����ֱ�Ӳ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param transactioncontent
	 *            transactionupdate���ĵ�����
	 * @return �ɹ�����1��ʧ�ܷ���0�������쳣����2��
	 */
	@WebMethod
	public int TransactionUpdateContentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "transactioncontent") String transactioncontent)
			throws ServiceException;
}
