package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface TransactionUpdateContentServiceInterface {
	/**
	 *通过transactionupdate的文档将更新已经存储的registrypacakge的信息，
	 * 如果没有存储相对应的registrypacakge的信息，也可以直接插入
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param transactioncontent
	 *            transactionupdate的文档内容
	 * @return 成功返回1，失败返回0，生成异常返回2；
	 */
	@WebMethod
	public int TransactionUpdateContentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "transactioncontent") String transactioncontent)
			throws ServiceException;
}
