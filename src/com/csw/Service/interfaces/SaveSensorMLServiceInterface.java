/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;

/**
 *��Ŀ����:CxfMyCsw ���������������ݿ��е�sensorml���ĵ������� ������:Administrator ����ʱ��: 2013-7-26
 * ����09:55:19
 */
@WebService(name = "SaveSensorMLServiceInterface")
public interface SaveSensorMLServiceInterface {
	/**
	 * �����û��ṩ��sensorml
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            ��������idֵ����ebrim��ʽ�Ĵ�������id
	 * 
	 * @param sensormlcontent
	 *            �û��ύ��sensorml������
	 * @return
	 */
	@WebMethod(operationName = "SaveSensorML")
	public boolean SaveSensorML(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "sensormlcontent") String sensormlcontent)
			throws ServiceException;

	/**
	 * ��Ҫɾ�����ĵ���SensorML�ĸ�ʽ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ������id
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod(operationName = "DeleteSensorML")
	public boolean DeleteSensorML(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡ�洢���ĵ�����Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ������id
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod(operationName = "ReadSensorContent")
	public String ReadSensorContent(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * �ж��Ƕ�Ӧ��sensorml�ĵ��Ƿ����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            ����
	 * @param sensorid
	 *            ��������id
	 * @return ���ش������true���ڣ�false������
	 */
	@WebMethod(operationName = "IsExistsSensorML")
	public boolean IsExistsSensorML(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡ����������ʱ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            ����
	 * @param sensorid
	 *            ��������id
	 * @return ���ش������true���ڣ�false������
	 */
	public Date GetSensorMLSaveTime(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;
}
