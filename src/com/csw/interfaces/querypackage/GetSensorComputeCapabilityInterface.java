package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorComputeCapabilityInterface {
	/**
	 * ���ش������й��ڼ�����������Ϣ���ձ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���Ҫ��ѯ�Ĵ������ļ����������ķ��������
	 * 
	 * @return �����û�ָ���Ĵ������ļ���������capability���������
	 * @throws ServiceException
	 *             ���ط������˲���Ĵ�����Ϣ
	 */
	@WebMethod
	public String GetSensorComputeCapabilityMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "capability") String capability)
			throws ServiceException;

	/**
	 * ���ش������й��ڼ�����������Ϣ���ձ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���Ҫ��ѯ�Ĵ������ļ����������ķ��������
	 * 
	 * @return �����û�ָ���Ĵ������ļ���������capability���������
	 * @throws ServiceException
	 *             ���ط������˲���Ĵ�����Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorComputeCapabilityForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException;
}
