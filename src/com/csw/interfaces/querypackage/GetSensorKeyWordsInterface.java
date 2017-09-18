package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-8-2 ����06:23:04
 */
@WebService
public interface GetSensorKeyWordsInterface {
	/**
	 * ��ȡ�������Ĺؼ���
	 * 
	 * @param sensorid
	 *            ��������ʶ��
	 * @param username
	 *            ��ע���û���
	 * @param password
	 *            ע����������
	 * @return ���ش�����������
	 * 
	 * @throws ServiceException
	 */
	@WebMethod
	public String GetSensorKeyWordsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡ���д������Ĺؼ���
	 * 
	 * @param username
	 * @param password
	 * @param sensorids
	 * @return
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorKeyWordsForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

}
