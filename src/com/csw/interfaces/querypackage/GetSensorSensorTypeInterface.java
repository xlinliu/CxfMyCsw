/**
 * 
 */
package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

/**
 * @author Administrator
 * 
 */
@WebService
public interface GetSensorSensorTypeInterface {
	/**
	 * ��ȡ�����������ͣ����״��ͣ���Ӱ�ͣ�����վ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ�������idֵ
	 * @return �������Ӧ�Ĵ����������ͣ����״��ͣ���Ӱ�ͣ�����վ���ͣ�ԭλ�������ȡ�
	 * @throws ServiceException
	 *             ����ʧ�ܵ���Ϣ
	 */
	@WebMethod
	public String GetSensorSensorTypeMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡ�����������ͣ����״��ͣ���Ӱ�ͣ�����վ���(���ϲ�β�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            ��������ʶ��
	 * @return ����
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorSensorTypeForMultiSensorMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
