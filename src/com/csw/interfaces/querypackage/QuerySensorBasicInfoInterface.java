package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface QuerySensorBasicInfoInterface {
	/**
	 * ��ȡָ���Ĵ��������м��ϵ�ȫ�Ƽ���
	 * 
	 * @param username
	 *            ע����������
	 * @param password
	 *            ע����������
	 * @param sensorids
	 *            ע��Ĵ���������
	 * @return ���ش�����������
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorLongNamesMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorid)
			throws ServiceException;

	/**
	 * ��ȡ������������Ԥ��Ӧ�ü���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            ���������м���
	 * @return �������еĴ�����Ԥ��Ӧ��
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorIntendedApplicationForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
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

	/**
	 * ��ȡ�����������й�����ʼʱ����Ϣ����
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���ѯ�Ĵ������ı�ʾ������
	 * @return ���ش������Ĺ�����ʼʱ�伯��
	 * @throws ServiceException
	 *             ���ط������˳��ֵĴ�����Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorWorkValidTimeBeginForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

	/**
	 * ��ȡ�������Ĺ�������ʱ����Ϣ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @return ���ش������Ĺ�������ʱ��
	 * @throws ServiceException
	 *             ���ط������˳��ֵĴ�����Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorWorkValidTimeEndForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

	/**
	 * ��ȡָ���Ĵ������Ķ�λ����
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param infoface
	 *            �û���Ҫ��ѯ�Ķ�λ������ĳһ����
	 * @return ���ز�ѯ�Ľ��
	 * @throws ServiceException
	 *             ���ط�������ִ��ʱ���ֵĴ������Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorPositionInfoForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
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

	/**
	 * ��ȡ��������ĳһ����Ĳ�������
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���ѯ�Ĵ��������ķ��������
	 * @return �����ƶ��Ĵ������ڸ�capability�������������Ϣ
	 * @throws ServiceException
	 *             ���ط������˷����ķ���������Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorMeasureCapabilitiesForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException;

	/**
	 * ��ȡ���������������Ե��ձ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ��������������ԵĴ������ı�ʶ��
	 * @param physicalproperty
	 *            ��Ҫ��ѯ�Ĵ��������������Ե���Ϣ
	 * @return ���ش�������physicalproperty��������Ϣ
	 * @throws ServiceException
	 *             ���ط������з����ķ���������Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorPhysicalPropertyForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids,
			@WebParam(name = "physicalproperty") String physicalproperty)
			throws ServiceException;

	/**
	 * ��ȡ����������֯��λ�ľ����ַ����Ϣ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʶ��
	 * @return ��������Ҫ�Ĵ���������ĵ�λ�ľ���ĵ�ַ����Ϣ
	 * @throws ServiceException
	 *             ���س��ֵĴ������Ϣ
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorConnectionEarthPosInfoFroMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
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
