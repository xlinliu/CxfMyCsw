package com.csw.Service.interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface CreateGetRcordByIdDocumentServiceInterface {
	/**
	 * 根据record的id值来获取生成records的查询服务
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param recordId
	 *            record的id值，如果是sensorml的id，可以转换为ebrim格式的sensor的id进行查询
	 * @return 返回生成的getRecords的查询文档，失败则返回null
	 * @throws 返回异常信息
	 */
	public String CreateGetRecordByIdDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "recordId") String recordId)
			throws ServiceException;
}
