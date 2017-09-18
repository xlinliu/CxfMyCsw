package com.csw.utils.OperateSensorTypeRuleUtil.Interface;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorTypeMarker;
import com.csw.utils.custometypes.SensorTypeMarkerClient;

/**
 *��Ŀ����:CxfMyCsw ���������Դ��������Ͷ���Ĺ����ȷ�� ������:Administrator ����ʱ��: 2013-9-16 ����10:31:38
 */
@WebService
public interface OperateSensorTypeRuleInterface {
	/**
	 * �����û��Զ���Ĵ���������������ѯ�����������а����Ĺؼ�������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensortypes
	 *            ��Ҫ��ѯ�Ĵ���������
	 * @return ���ذ����˴��������ͺ����Ӧ�Ĵ����������͵Ĺؼ�������
	 */
	@WebMethod
	public List<SensorTypeMarker> GetAllSensorTypeRule(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortypes") List<String> sensortypes)
			throws ServiceException;

	/**
	 * ��ȡָ�����͵Ĵ������Ĺؼ�������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensortype
	 *            ���������͵Ĺؼ���
	 * @return
	 */
	@WebMethod
	public SensorTypeMarker GetSensorTypeRule(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortype") String sensortype)
			throws ServiceException;

	/**
	 * �����û�ָ���Ĵ������Ĺؼ�������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param str
	 *            ��Ҫ���õĴ���������Ϣ
	 * @return SensorTypeMarker
	 */
	@WebMethod
	public Boolean UpdateSensorTypeRule(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "str") SensorTypeMarkerClient str)
			throws ServiceException;

	/**
	 * �����û�ָ���Ĵ��������еĹؼ�������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param str
	 *            ��Ҫ���µĴ���������Ϣ����
	 * @return ����SensorTypeMarker����
	 */
	@WebMethod
	public List<Boolean> UpdateSensorTypeRuleWithMultiEnties(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "str") List<SensorTypeMarkerClient> str)
			throws ServiceException;

	/**
	 * ɾ��ָ���Ĵ���������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensortype
	 *            ��Ҫɾ���Ĵ�����������
	 * @return ���� SensorTypeMarker
	 */
	@WebMethod
	public SensorTypeMarker DeleteSensorTypeRuleEntry(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortype") String sensortype)
			throws ServiceException;

	/**
	 *ɾ��ָ���Ĵ��������͵�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensortypes
	 *            ��Ҫɾ���Ĵ���������
	 * @param maker
	 *            ���������͵�������
	 * @return
	 */
	@WebMethod
	public List<SensorTypeMarker> DeleteSensorTypeRuleEntries(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortypes") List<String> sensortypes)
			throws ServiceException;

	/**
	 * �����ƶ��Ĵ��������͵�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param strs
	 *            ���õĴ�����������Ϣ����
	 * @return ���ض�Ӧ�Ĵ���������Ϣ����Ҫ�ǲ鿴isexist�ֶΣ�
	 */
	@WebMethod
	public List<Boolean> SaveSensorTypeRuleEntries(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "strs") List<SensorTypeMarkerClient> strs)
			throws ServiceException;

	/**
	 * �����ƶ��Ĵ���������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param str
	 *            �鿴��Ҫ����Ĵ�����������Ϣ
	 * @return ���ض�Ӧ�Ĵ���������Ϣ����Ҫ�ǲ鿴isexist�ֶΣ�
	 */
	@WebMethod
	public Boolean SaveSensorTypeRuleEntry(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "str") SensorTypeMarkerClient str)
			throws ServiceException;

	/**
	 * ���ݹؼ�������ȡ����Ӧ�Ĵ�����������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorkeyword
	 *            ��Ҫ�鿴�Ĺؼ���
	 * @return ���ض�Ӧ�Ĵ���������Ϣ
	 * 
	 */
	@WebMethod
	public List<SensorTypeMarker> GetSensorKeyBelongSensorType(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorkeyword") String sensorkeyword)
			throws ServiceException;

	/**
	 * ���ݹؼ�����������ȡ��Ӧ�Ĵ���������������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorkeywords
	 *            ��Ҫ��ѯ�Ĵ������Ĺؼ�������
	 * @return ���ض�Ӧ�Ĵ���������Ϣ����
	 */
	@WebMethod
	public List<SensorTypeMarker> GetSensorKeyBelongSensorTypes(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorkeywords") List<String> sensorkeywords)
			throws ServiceException;

	/**
	 * �ж��Ƿ������صĴ��������͵Ķ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensortype
	 * @return
	 */
	@WebMethod
	public Boolean IsExistOfSensorType(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortype") String sensortype)
			throws ServiceException;

	/**
	 * �ж��Ƿ������صĴ��������͵Ķ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return
	 */
	@WebMethod
	public Boolean DeleteAllSensorTypeRule(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
