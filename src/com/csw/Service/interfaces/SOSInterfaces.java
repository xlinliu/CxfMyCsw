package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;

@WebService
public interface SOSInterfaces {
	/**
	 * ��ȡsos��ĳһ�����������µĹ۲�ʱ��
	 * 
	 * @param sensorid
	 *            ��������id
	 * @param obervationPro
	 *            �۲�����
	 * @param offingId
	 *            ��ʶ��
	 * @param sosadd
	 *            sos�ķ���˵�ַ
	 * @return �������µĹ۲��ʱ�䣬���û���򷵻ص�ǰ��ʱ��
	 */
	@WebMethod
	public String GetLastObservation(
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "obervationPro") String obervationPro,
			@WebParam(name = "offingId") String offingId,
			@WebParam(name = "sosadd") String sosadd) throws ServiceException;
}
