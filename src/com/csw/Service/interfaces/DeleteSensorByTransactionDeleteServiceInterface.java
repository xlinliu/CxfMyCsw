package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface DeleteSensorByTransactionDeleteServiceInterface {
	/**
	 * 删除Transaction-Delete的文档制定的删除的RegistryPacakge的文档的内容,
	 * 当然要删除的RegistryPacakge也必须是自己注册了的RegistryPacakge的信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param transactioncontent
	 *            删除的文件内容
	 * @return 删除成功返回1
	 */
	@WebMethod
	public int DeleteSensorByTransactionDeleteMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "transactioncontent") String transactioncontent)throws ServiceException;
}
