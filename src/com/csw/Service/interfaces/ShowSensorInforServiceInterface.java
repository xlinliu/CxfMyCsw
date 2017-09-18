package com.csw.Service.interfaces;

import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.MapAdapter;

@WebService
public interface ShowSensorInforServiceInterface {

	/**
	 * ��ȡSESNOR����Ϣ�û��ṩ�����һ���Ե��û������޸�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            �û���ѯ��sensor��idֵ
	 * @return ���ص���һϵ�еļ򵥵������޸ĵ���Ϣ�����������ʵ����
	 */
	@WebMethod
	public @XmlJavaTypeAdapter(MapAdapter.class)
	Map<String, String> ShowSensorInfoForUpdateMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;
}
