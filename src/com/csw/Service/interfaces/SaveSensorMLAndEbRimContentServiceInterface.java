package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface SaveSensorMLAndEbRimContentServiceInterface {
	/**
	 * ����SensorML����ͨ����SensorML������ebRIM����ȡebRIM��RegistryPacakge��idֵ������ȡebRIM��ֵ
	 * 
	 * @param username
	 *            �ϴ�SensorML���û���
	 * @param password
	 *            �ϴ�SensorML���û�����
	 * @param sensormlcontent
	 *            �ϴ���SensorML���ļ�����
	 * @param filename
	 *            �ϴ���sensorml���ļ������ƣ���������׺������.xml,.txt��
	 * @return ����1���ɹ���0��ʧ��
	 */
	@WebMethod
	public int SaveSensorMLAndEbRimContentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensormlcontent") String sensormlcontent,
			@WebParam(name = "filename") String filename,
			@WebParam(name = "createtime") String createtime)
			throws ServiceException;
}
