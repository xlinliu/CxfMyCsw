package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorCommunicationCapabilityInterface {
	/**
	 * ��ѯ��������ͨ��������
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���ѯ�Ĵ�������ͨ����������һ����
	 * @return ���ش������Ĳ�ѯ��ĳһ�����ͨ����������ϸ����Ϣ
	 * @throws ServiceException
	 *             ���ش������еķ����Ĵ������Ϣ
	 */
	@WebMethod
	public String GetSensorCommunicationCapabilityMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "capability") String capability)
			throws ServiceException;

	/**
	 * ��ѯ��������ͨ��������
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            ��Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���ѯ�Ĵ�������ͨ����������һ����
	 * @return ���ش������Ĳ�ѯ��ĳһ�����ͨ����������ϸ����Ϣ
	 * @throws ServiceException
	 *             ���ش������еķ����Ĵ������Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorCommunicationCapabilityForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException;
}
