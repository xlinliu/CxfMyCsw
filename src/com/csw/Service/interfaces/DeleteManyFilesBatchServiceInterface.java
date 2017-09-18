package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface DeleteManyFilesBatchServiceInterface {
	/**
	 * ɾ�����е���Ҫɾ�����ĵ���������Ϣ����Ҫע����ǣ���Ҫ�ж����е�filenames�е��ļ����Ʊ�������username�ſ��Ա�ɾ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param filenames
	 *            ��Ҫɾ�����ļ�����
	 * @return ɾ���ɹ����� 1 
	 */
	@WebMethod
	public int DeleteManyFileContentsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "filenames") String filenames)
			throws ServiceException;
}
