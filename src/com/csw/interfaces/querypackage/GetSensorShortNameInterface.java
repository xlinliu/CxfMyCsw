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
public interface GetSensorShortNameInterface {
	/**
	 * ��ȡָ���������ļ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            �û���ѯ�Ĵ�������idֵ
	 * @return ��ѯ��ָ���Ĵ������ļ��
	 */
	@WebMethod
	public String GetSensorShortNameMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡָ�����������ϵļ�Ƽ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            �û���ѯ�Ĵ�������idֵ
	 * @return
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorShortNameforMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
