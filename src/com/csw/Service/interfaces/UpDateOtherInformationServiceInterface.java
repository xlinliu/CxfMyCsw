package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface UpDateOtherInformationServiceInterface {
	/**
	 * �����û�����Ϣ
	 * 
	 * @param adminusername
	 *            �����û�Ȩ�޵Ĺ���Ա�û�������
	 * @param adminpassword
	 *            �����û�Ȩ�޵Ĺ���Ա�û�������
	 * @param username
	 *            ��Ҫ���µ��û������ƣ����ܸ���
	 * @param password
	 *            ��Ҫ���µ��û������룬���ܸ���
	 * @param level
	 *            ���Ը��ĵ��û��ļ��𣬿��Ը���
	 * @return ���³ɹ��򷵻�1������ʧ�ܷ���0�������쳣����2��
	 */
	@WebMethod
	public int UpdateOtherInformationMethod(
			@WebParam(name = "adminusername") String adminusername,
			@WebParam(name = "adminpassword") String adminpassword,
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "newpassword") String newpassword,
			@WebParam(name = "level") int level) throws ServiceException;
}
