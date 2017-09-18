package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorPhysicalPropertyInterface {
	/**
	 * ��ȡ���������������Ե��ձ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ��������������ԵĴ������ı�ʶ��
	 * @param physicalproperty
	 *            ��Ҫ��ѯ�Ĵ��������������Ե���Ϣ
	 * @return ���ش�������physicalproperty��������Ϣ
	 * @throws ServiceException
	 *             ���ط������з����ķ���������Ϣ
	 */
	@WebMethod
	public String GetSensorPhysicalPropertyMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "physicalproperty") String physicalproperty)
			throws ServiceException;

	/**
	 * ��ȡ���������������Ե��ձ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ��������������ԵĴ������ı�ʶ��
	 * @param physicalproperty
	 *            ��Ҫ��ѯ�Ĵ��������������Ե���Ϣ
	 * @return ���ش�������physicalproperty��������Ϣ
	 * @throws ServiceException
	 *             ���ط������з����ķ���������Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorPhysicalPropertyForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids,
			@WebParam(name = "physicalproperty") String physicalproperty)
			throws ServiceException;
}
