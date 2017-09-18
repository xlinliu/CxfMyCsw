package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface SearchGetRecordByIdDocumentServiceInterface {
	/**
	 * g根据查询的getrecordbyid的文档获取查询的id并且将查询到的registrypacakge文档的内容信息返回给用户
	 * 
	 * @param username
	 * @param password
	 * @param getrecordByIdContent
	 * @return 成功返回相应的id的registrypacakge的文档的内容，没有则返回null
	 */
	@WebMethod
	public String SearchGetRecordByIdDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "getrecordByIdContent") String getrecordByIdContent)
			throws ServiceException;
}
