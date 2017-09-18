/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

/**
 *��Ŀ����:CxfMyCsw ����������ȡ���е�ƽ̨�����ͼ��� ������:Administrator ����ʱ��: 2013-8-23 ����02:15:52
 */
@WebService
public interface GetAllPlatfromTypesInterface {
	@WebMethod
	public List<String> getAllPlatfromType(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
