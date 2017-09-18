package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetRecordsServiceInterface {

	/**
	 * 根据获取的查询的条件进行查询之后获取的所有传感器的文档所组成的getRecords的文档的信息
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * 
	 * @param allownull
	 *            是否运行该字段为空的传感器返回
	 * @param queryStr
	 *            用户查询的字段所组成的字符串，有条件的约束
	 * @return
	 * @throws ServiceException
	 *             出现错误则返回错误信息
	 */
	@WebMethod
	public String GetRecordsDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "queryStr") String queryStr,
			@WebParam(name = "allownull") boolean allownull

	) throws ServiceException;
}
