package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorMLType;

@WebService
public interface GetAllBasicInfoOfSensorMLServiceInterface {
	/**
	 * ��ȡ���û����������û��ϴ���sensorml�Ļ����ļ���Ϣ�����صĽ����ļ���id���ϴ�ʱ�����ļ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param type
	 *            ������ȡ�����û������ǻ�ȡȫ���û���������ܽ�����Թ���Ա����Ĭ��Ϊfalse��true���ѯ�����û��ϴ����ļ�����
	 * @return ������Ҫ��Ϣ��List<String>��string���Ϊ id,�ϴ�ʱ��,�ļ�����Ϊ�գ�����null
	 * @throws �����쳣
	 *             �������쳣��Ϣ
	 */
	@WebMethod
	public List<SensorMLType> GetAllBasicInfoOfSensorMLMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "type") boolean type) throws ServiceException;
}
