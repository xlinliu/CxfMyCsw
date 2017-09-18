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
	 * 获取SESNOR的信息用户提供给相对一股脑的用户进行修改
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            用户查询的sensor的id值
	 * @return 返回的是一系列的简单的所需修改的信息，具体见具体实现类
	 */
	@WebMethod
	public @XmlJavaTypeAdapter(MapAdapter.class)
	Map<String, String> ShowSensorInfoForUpdateMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;
}
