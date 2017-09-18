package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface SaveOnlySensorMLServiceInterface {
	/**
	 * �������û��ϴ���sensorml�ϴ�����������ת����ebrim��Ϣ�ķ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensormlcontent
	 *            �û����ݵĹ�����sensorml���ĵ�������
	 * @param sensorid
	 *            �û����ݹ�����sensorml��idֵ
	 * @param filename
	 *            �û����ݹ�����sensorml���ĵ�������
	 * @param createtime
	 *            �ĵ��Ĵ���ʱ��
	 * @return �ɹ����� 1��ʧ�� ����0��
	 */
	@WebMethod
	public int SaveOnlySensorMLMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensormlcontent") String sensormlcontent,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "filename") String filename,
			@WebParam(name = "createtime") String createtime)
			throws ServiceException;
}
