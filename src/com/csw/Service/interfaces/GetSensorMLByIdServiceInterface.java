package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetSensorMLByIdServiceInterface {
	/**
	 * ��ȡ��ȡ��sensor��xml�ĵ������ݣ�������ض����ϴ����û����ṩ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param docId
	 *            ��Ҫ��ѯ��sensorml��idֵ�����ֵ������sensorml��idֵҲ������ת�����ebrim��idֵ
	 * 
	 * @return ���ص��� �ض����û����ض���id��sensorml���ĵ�������
	 */
	@WebMethod
	public List<String> GetSensorMLByIdServiceMehtod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "docId") String docId) throws ServiceException;
}
