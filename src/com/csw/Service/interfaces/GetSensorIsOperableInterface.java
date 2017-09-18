/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorOperable;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-15 ����04:36:57
 */
@WebService
public interface GetSensorIsOperableInterface {
	/**
	 * ��ȡ�������Ƿ�ɲ��������ӿ�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ��������ʶ��
	 * @return ������ش������Ƿ�ɲ�������Ϣ
	 * @throws ServiceException
	 */
	@WebMethod
	public SensorOperable GetSensorOperableMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡ�������Ƿ�ɲ������ϵķ����ӿ�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            ��������ʶ������
	 * @return ������ش������Ƿ�ɲ�������Ϣ ����
	 * @throws ServiceException
	 */
	public List<SensorOperable> GetSensorsOperableListMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

}
