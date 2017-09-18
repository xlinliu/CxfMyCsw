/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-29 ����05:08:44
 */
@WebService
public interface GetAllSensorBasicInfoInterface {
	/**
	 * ��ȡ���д������Ļ�����Ϣ���������ǣ�����û��λ����Ϣ
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            �û�����
	 * @param all
	 *            �Ƿ��ѯ������Ϣ
	 * @return
	 */
	public List<SensorBasicInfoType> getAllSatelliteSensorBasicInfo(
			String username, String password, boolean all)
			throws ServiceException;

	/**
	 * ��ȡָ���û���ȫ���Ĵ�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password�û�����
	 * @param all
	 *            �Ƿ��ȡ���еĴ���������Ϣ���������ˣ�
	 * @return
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetAllSensorBasicInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "all") boolean all) throws ServiceException;

	/**
	 * ��ȡָ���û�ָ���Ĵ�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password�û�����
	 * @param sensorid
	 *            ��ѯ�Ĵ������ı�ʶ��
	 * @return
	 */
	@WebMethod
	public SensorBasicInfoType GetSingleSensorBasicInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;
}
