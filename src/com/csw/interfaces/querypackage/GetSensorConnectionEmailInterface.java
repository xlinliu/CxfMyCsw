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
public interface GetSensorConnectionEmailInterface {
	/**
	 * ��ȡ��������ϵ��ʽ�е�email����Ϣ
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @return �������Ӧ�Ĵ������Ĺ���λ����ϵ��email�ĵ�ַ
	 * @throws ServiceException
	 *             ���س��ֵķ������� ��ϸ����Ϣ
	 */
	@WebMethod
	public String GetSensorConnectionEmailMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡ��������ϵ��ʽ�е�email����Ϣ
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @return �������Ӧ�Ĵ������Ĺ���λ����ϵ��email�ĵ�ַ
	 * @throws ServiceException
	 *             ���س��ֵķ������� ��ϸ����Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorConnectionEmailForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
			throws ServiceException;
}
