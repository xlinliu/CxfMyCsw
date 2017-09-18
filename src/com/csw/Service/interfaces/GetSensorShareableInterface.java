/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorShareLevel;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-15 ����04:46:07
 */
@WebService
public interface GetSensorShareableInterface {
	/**
	 * �������ɹ����ѯ����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ��������ʶ��
	 * @return ������������Ϣ
	 * @throws ServiceException
	 */
	@WebMethod
	public SensorShareLevel GetSensorShareLevelMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * �������ɹ����ѯ����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            ��������ʶ������
	 * @return ������������Ϣ����
	 * @throws ServiceException
	 */
	public List<SensorShareLevel> GetSensorShareLevelsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
