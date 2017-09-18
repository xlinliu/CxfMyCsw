package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorLongNameInterface {
	/**
	 * ��ȡָ����������ȫ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ��������ʶ��
	 * @return ���ش�������ȫ��
	 */
	@WebMethod(operationName = "GetSensorLongNameMethod")
	public String GetSensorLongNameMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡָ���Ĵ��������еļ���
	 * 
	 * @param username
	 * @param password
	 * @param sensorid
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorLongNamesMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorid)
			throws ServiceException;
}
