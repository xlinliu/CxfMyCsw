package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface SearchGetRecordByIdDocumentServiceInterface2 {
	/**
	 * g根据查询的getrecordbyid的文档获取查询的id并且将查询到的registrypacakge文档的内容信息返回给用户
	 * 
	 * @param username
	 *            查询的用户名
	 * @param password
	 *            查询的用户密码
	 * @param getrecordByIdContent
	 *            查询的getRecordById的文档内容
	 * @return 成功返回相应的id的registrypacakge的文档的内容，失败则返回null，异常则返回null
	 */
	@WebMethod
	public String SearchGetRecordByIdDocumentMethod(
			@WebParam(name = "username", header = true) String username,
			@WebParam(name = "password", header = true) String password,
			@WebParam(name = "getrecordByIdContent") String getrecordByIdContent)
			throws ServiceException;
}
