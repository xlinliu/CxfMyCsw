package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.CxfFile;

@WebService
public interface SaveSensorMLAndEbRimContentByAttachementServiceInterface {
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
			@WebParam(name = "myfile") CxfFile myfile,
			@WebParam(name = "createtime") String createtime)
			throws ServiceException;
}
