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
public interface GetSensorOrganizationInfoInterface {
	/**
	 * ��ȡ����������֯��Ϣ�ķ���
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @return ���ش���������֯��Ϣ���ַ�������ʽ��
	 * @throws ServiceException
	 *             ��������ʱ���ص���Ϣ
	 */
	@WebMethod
	public String GetSensorOrganizationInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡ����������֯��Ϣ�ķ���
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @return ���ش���������֯��Ϣ���ַ�������ʽ��
	 * @throws ServiceException
	 *             ��������ʱ���ص���Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorOrganizationInfoForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

}
