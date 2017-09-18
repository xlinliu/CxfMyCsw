package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorWorkBeginTimeInterface {
	/**
	 * ��ȡ�������Ĺ�����ʼʱ����Ϣ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @return ���ش������Ĺ�����ʼʱ��
	 * @throws ServiceException
	 *             ���ط������˳��ֵĴ�����Ϣ
	 */
	@WebMethod
	public String GetSensorWorkValidTimeBeginMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡ�����������й�����ʼʱ����Ϣ����
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���ѯ�Ĵ������ı�ʾ������
	 * @return ���ش������Ĺ�����ʼʱ�伯��
	 * @throws ServiceException
	 *             ���ط������˳��ֵĴ�����Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorWorkValidTimeBeginForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
