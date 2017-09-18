package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorGeoInfoInterface {
	/**
	 * ��ȡ�������ĵ���ռ���Ϣ�ķ������ռ䷶Χ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʶ��
	 * @return ���ش������ĵ���ռ䷶Χ����Ϣ,��ʽΪ��"����ϵͳ,�����ǵ㾭γ��,���Ͻǵ㾭γ��"
	 * @throws ServiceException
	 *             ���ط������˵Ĵ������Ϣ
	 */
	@WebMethod
	public String GetSensorGeoInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ��ȡ�������ĵ���ռ���Ϣ�ķ������ռ䷶Χ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʶ��
	 * @return ���ش������ĵ���ռ䷶Χ����Ϣ,��ʽΪ��"����ϵͳ,�����ǵ㾭γ��,���Ͻǵ㾭γ��"
	 * @throws ServiceException
	 *             ���ط������˵Ĵ������Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorGetoInfoForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
