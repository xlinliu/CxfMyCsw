package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorInputInfoType;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-23 ����08:52:36
 */
@WebService
public interface GetSensorChineseInputsInterface {
	/**
	 * ��ȡ������������ȫ�����ĵ�������Ϣ
	 * 
	 * @param username
	 * @param password
	 * @param sensorid
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public SensorInputInfoType GetChineseInputsInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡ���д�������ȫ��������������Ϣ����
	 * 
	 * @param username
	 * @param password
	 * @param sensorids
	 * @return
	 * @throws ServiceException
	 */
	public List<SensorInputInfoType> GetChineseInputsInfoList(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
			throws ServiceException;

}
