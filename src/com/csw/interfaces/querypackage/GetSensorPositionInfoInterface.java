package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorPositionInfoInterface {
	/**
	 * ��ȡָ���Ĵ������Ķ�λ�������ձ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param infoface
	 *            �û���Ҫ��ѯ�Ķ�λ������ĳһ����
	 * @return ���ز�ѯ�Ľ��
	 * @throws ServiceException
	 *             ���ط�������ִ��ʱ���ֵĴ������Ϣ
	 */
	@WebMethod
	public String GetSensorPositionInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡָ���Ĵ������Ķ�λ����
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param infoface
	 *            �û���Ҫ��ѯ�Ķ�λ������ĳһ����
	 * @return ���ز�ѯ�Ľ��
	 * @throws ServiceException
	 *             ���ط�������ִ��ʱ���ֵĴ������Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorPositionInfoForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
