package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface AddRegistryPackageServiceInterface {
	/**
	 * ��ȡ����ע������е�RegistryPackage
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @return �����û�������ע���ȫ����RegistryPackage��idֵ������list�ķ�ʽ���أ���� û�гɹ��򷵻�null
	 * @throws �����쳣��Ϣ
	 */
	public List<String> GetOwnRegistryPackageMetod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
