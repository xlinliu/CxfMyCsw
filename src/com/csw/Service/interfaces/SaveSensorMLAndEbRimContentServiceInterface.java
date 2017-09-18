package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface SaveSensorMLAndEbRimContentServiceInterface {
	/**
	 * 保存SensorML，并通过将SensorML解析出ebRIM，获取ebRIM的RegistryPacakge的id值，来获取ebRIM的值
	 * 
	 * @param username
	 *            上传SensorML的用户名
	 * @param password
	 *            上传SensorML的用户密码
	 * @param sensormlcontent
	 *            上传的SensorML的文件内容
	 * @param filename
	 *            上传的sensorml的文件的名称，不包含后缀名，如.xml,.txt等
	 * @return 返回1，成功；0，失败
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
