package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorConnectionEarthPosInfoInterface {
	/**
	 * ��ȡ����������֯��λ�ľ����ַ����Ϣ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʶ��
	 * @return ��������Ҫ�Ĵ���������ĵ�λ�ľ���ĵ�ַ����Ϣ
	 * @throws ServiceException
	 *             ���س��ֵĴ������Ϣ
	 */
	@WebMethod
	public String GetSensorConnectionEarthPosInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡ����������֯��λ�ľ����ַ����Ϣ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʶ��
	 * @return ��������Ҫ�Ĵ���������ĵ�λ�ľ���ĵ�ַ����Ϣ
	 * @throws ServiceException
	 *             ���س��ֵĴ������Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorConnectionEarthPosInfoFroMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
			throws ServiceException;

}
