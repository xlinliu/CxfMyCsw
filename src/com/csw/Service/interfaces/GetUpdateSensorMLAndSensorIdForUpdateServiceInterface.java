package com.csw.Service.interfaces;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.MapAdapter;

@WebService
public interface GetUpdateSensorMLAndSensorIdForUpdateServiceInterface {
	/**
	 * ��ȡ���е���Ҫ���µ�sensorml��id��sensorml�����ݣ�ǰ�����Ѿ�֪����Щsensormlid��Ҫ����
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensormlids
	 *            �ض���Ҫ���µ�sensorml��idֵ
	 * @param resultidType
	 *            ��Ҫ���ص�resultType�����ͣ���"sensorml"��ʾ���ص�id��sensorml��id��"ebrim"��ʾ���ص���ebrim��id
	 * @return �������е���Ҫ���µ�sensor��id���ĵ���sensorml���ĵ������ݣ����û�У��򷵻�null
	 */
	@WebMethod
	public @XmlJavaTypeAdapter(MapAdapter.class)
	Map<String, String> GetUpdateSesnorMLAndSensorIdForUpdateByIdsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensormlids") List<String> sensormlids,
			@WebParam(name = "resultidType") String resultidType)
			throws ServiceException;

	/**
	 * ��ȡ���е���Ҫ���µ�sensorml��id��sensorml�����ݣ�ǰ���ǲ���֪����Щsensormlids��һ����Ҫ���µ�
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            ��Ҫ���µ�sensorml��idֵ����
	 * @param stamps
	 *            ��Ҫ���µ�sensoriml��stamps��ֵ ����
	 * @param resultidType
	 *            ��Ҫ���ص�resultType�����ͣ���"sensorml"��ʾ���ص�id��sensorml��id��"ebrim"��ʾ���ص���ebrim��id
	 * 
	 * @return �������е���Ҫ���µ�sensor��id���ĵ���sensorml���ĵ������ݣ����û�У��򷵻�null
	 */
	@WebMethod
	public @XmlJavaTypeAdapter(MapAdapter.class)
	Map<String, String> GetUpdateSesnorMLAndSensorIdForUpdateMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids,
			@WebParam(name = "stamps") List<String> stamps,
			@WebParam(name = "resultidType") String resultidType)
			throws ServiceException;
}
