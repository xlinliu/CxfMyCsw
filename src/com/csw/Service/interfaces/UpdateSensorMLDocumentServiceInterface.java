package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface UpdateSensorMLDocumentServiceInterface {
	/**
	 * �����ƶ���sensorml��id���ĵ������ݣ����û�еĻ����͵�������������д���
	 * 
	 * @param username
	 *            �������~�Ñ�������횴���
	 * @param passowrd
	 *            �������Ñ��ܴa��������ڣ��ұ�����username�����ݿ��еļ�¼ƥ��
	 * @param sensormlcontent
	 *            ��Ҫ���µĻ��߲����sensorml���ęn�ă���
	 * @param filename
	 *            �ϴ���filename�����ƣ�����Ϊ��
	 * @return 1�����³ɹ���0 ����ʧ�ܣ�2���³����쳣
	 * @throws ServiceException
	 */
	@WebMethod
	public int UpdateSensorMLDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String passowrd,
			@WebParam(name = "sensormlcontent") String sensormlcontent,
			@WebParam(name = "filename") String filename,
			@WebParam(name = "createtime") String createtime) throws ServiceException;
}
