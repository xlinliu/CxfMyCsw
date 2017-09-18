package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorMeasureCapabilityInterface {

	/**
	 * ��ȡ��������ĳһ����Ĳ�������
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���ѯ�Ĵ��������ķ��������
	 * @return �����ƶ��Ĵ������ڸ�capability�������������Ϣ
	 * @throws ServiceException
	 *             ���ط������˷����ķ���������Ϣ
	 */
	@WebMethod
	public String GetSensorMeasureCapabilityMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "capability") String capability)
			throws ServiceException;

	/**
	 * ��ȡ��������ĳһ����Ĳ�������
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���ѯ�Ĵ��������ķ��������
	 * @return �����ƶ��Ĵ������ڸ�capability�������������Ϣ
	 * @throws ServiceException
	 *             ���ط������˷����ķ���������Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorMeasureCapabilitiesForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException;
}
