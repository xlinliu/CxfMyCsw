package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetSensorMLNeedToUpdateServiceInterface {
	/**
	 * Ѱ����Ҫ���µ�sensorml��id����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            �û�����Ҫ�鿴�ĸ���sensorml��id����
	 * @param stamps
	 *            �û���Ҫ�鿴�ĸ��µ�sensorml��stamp�ļ��ϣ���Ҫ��sensorml��id����һһ��Ӧ
	 * @param resulttype
	 *            �û�ϣ�����ص�id�����ͣ���"sensorml","ebrim"������֮һ
	 * @return ����������Ҫ���µ�sensorML��idֵ���� 
	 */
	@WebMethod
	public List<String> GetSensorMLNeedToUpdateMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "idtimes") List<String> sensorids,
			@WebParam(name = "sensorstamps") List<String> stamps,
			@WebParam(name = "resulttype") String resulttype)
			throws ServiceException;
	// MapAdapter.class
}
