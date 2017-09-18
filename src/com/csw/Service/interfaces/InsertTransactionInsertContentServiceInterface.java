package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface InsertTransactionInsertContentServiceInterface {
	/**
	 * 将Transaction-insert的信息插入到数据库中
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @param content
	 *            信息内容级transaction-insert的信息
	 * @param processType
	 *            process 的类型
	 * @param SerivceType
	 *            service的服务类型
	 * @param intendedApplication
	 *            process的预期应用
	 * @return 成功返回1
	 */
	@WebMethod
	public int InsertTransactionInsertCotentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "content") String content,
			@WebParam(name = "processType") String processType,
			@WebParam(name = "SerivceType") String SerivceType,
			@WebParam(name = "intendedApplication") String intendedApplication)
			throws ServiceException;
}
