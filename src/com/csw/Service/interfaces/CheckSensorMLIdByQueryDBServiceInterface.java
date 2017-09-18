package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorExistBoolean;

@WebService
public interface CheckSensorMLIdByQueryDBServiceInterface {
	/**
	 * ����û����ݹ�����sensorml��id�Ƿ��Ѿ���ʹ��
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û����ݹ�����Ҫ����sensorml��idֵ ������sensorml��ʽ�ģ�Ҳ������ebrim��ʽ
	 * @return ���ظ�sensomrl�Ƿ��Ѿ����ڣ����ڷ���true�������ڷ���false
	 * @throws �����쳣��Ϣ
	 */
	@WebMethod
	public boolean CheckSensorMLIdByQueryDBMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * ����û����ݹ�����sensorml��id�����Ƿ��Ѿ���ʹ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            �û����ݹ�������Ҫ����sensorml��id���У�����ʹsensorml��ʽ�ģ�Ҳ����ebrim��ʽ��
	 * @return ���ظ�sensorml�ı�ʶ���Ƿ��Ѿ�����
	 * @throws ServiceException
	 */
	public List<SensorExistBoolean> CheckSensorMLIdsByQueryDBMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
