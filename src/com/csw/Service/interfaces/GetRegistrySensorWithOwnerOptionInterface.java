package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

/**
 *��Ŀ����:CxfMyCsw ��������������Ҫ��Ϊ�˻�ȡ��Ӧ�Ĵ���������Ϣ ������:Administrator ����ʱ��: 2013-10-17
 * ����10:25:29
 */
@WebService
public interface GetRegistrySensorWithOwnerOptionInterface {
	/**
	 * ��ȡָ�����û�����Ϣ
	 * 
	 * @param owner
	 * @param all
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<String> GetRegistrySensorWithOwnerOptionMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "all") boolean all) throws ServiceException;
}
