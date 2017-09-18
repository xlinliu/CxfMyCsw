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
	 * 获取该用户或者所有用户上传的sensorml的基本文件信息，返回的将是文件的id，上传时间与文件名
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param type
	 *            仅仅获取自身用户，还是获取全部用户，这个功能仅仅针对管理员级别，默认为false，true则查询所有用户上传的文件内容
	 * @return 返回需要信息，List<String>中string组成为 id,上传时间,文件名。为空，返回null
	 * @throws 发生异常
	 *             ，返回异常信息
	 */
	@WebMethod
	public List<SensorMLType> GetAllBasicInfoOfSensorMLMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "type") boolean type) throws ServiceException;
}
