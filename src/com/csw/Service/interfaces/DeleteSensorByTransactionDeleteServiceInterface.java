package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface DeleteSensorByTransactionDeleteServiceInterface {
	/**
	 * ɾ��Transaction-Delete���ĵ��ƶ���ɾ����RegistryPacakge���ĵ�������,
	 * ��ȻҪɾ����RegistryPacakgeҲ�������Լ�ע���˵�RegistryPacakge����Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param transactioncontent
	 *            ɾ�����ļ�����
	 * @return ɾ���ɹ�����1
	 */
	@WebMethod
	public int DeleteSensorByTransactionDeleteMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "transactioncontent") String transactioncontent)throws ServiceException;
}
