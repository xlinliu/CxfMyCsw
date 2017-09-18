package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface InsertSomeInfoTransactionServiceInterface {
	/**
	 * 将用户简单输入的信息全部转换相应的信息进行存储
	 * 
	 * @param id
	 * @param keywords
	 * @param inputs
	 * @param outputs
	 * @param northenv
	 * @param westenv
	 * @param eastenv
	 * @param southenv
	 * @param pointx
	 * @param pointy
	 * @param filepath
	 * @param intendedApplication
	 * @param processType
	 * @param serviceType
	 * @return 插入成功返回 1
	 */
	@WebMethod
	public int InsertSomeInfoTransactionMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "id") String id,
			@WebParam(name = "keywords") String keywords,
			@WebParam(name = "inputs") String inputs,
			@WebParam(name = "outputs") String outputs,
			@WebParam(name = "northenv") String northenv,
			@WebParam(name = "westenv") String westenv,
			@WebParam(name = "eastenv") String eastenv,
			@WebParam(name = "southenv") String southenv,
			@WebParam(name = "pointx") String pointx,
			@WebParam(name = "pointy") String pointy,
			@WebParam(name = "intendedApplication") String intendedApplication,
			@WebParam(name = "processType") String processType,
			@WebParam(name = "serviceType") String serviceType)
			throws ServiceException;
}
