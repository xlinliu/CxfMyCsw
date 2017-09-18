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
 *��Ŀ����:CxfMyCsw �����������ݴ�������id�б��ȡ��������SensorML�ĵ� ������:Administrator ����ʱ��: 2013-9-5
 * ����02:39:38
 */
@WebService
public interface getSensorMLByIdsServiceInterface {
	// ��ȡָ��������id�����е�ȫ�������������ĵ�
	@WebMethod
	public List<String> getSensorMLsByIdsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
