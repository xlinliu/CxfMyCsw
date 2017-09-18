package com.csw.interfaces.querypackage;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CreateGetRecordsDetailInterface {
	/**
	 * 根据查询的字段生成getRecords的文件内容
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @param queryStr
	 *            查询的条件所组成的，一定的规则组成
	 * @return 返回生成的getRecords的文档内容
	 */
	@WebMethod
	public String CreateGetReocdsDetailMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "queryStr") String queryStr);
}
