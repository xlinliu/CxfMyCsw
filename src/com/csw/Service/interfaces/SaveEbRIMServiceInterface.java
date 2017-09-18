package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface SaveEbRIMServiceInterface {
	/**
	 * 保存以sensorml格式存储于进来的文件的内容，并保留其中的processType,SERVICEtYPE,
	 * intendedAPplic等信息on 等信息
	 * 
	 * @param intendedApplication
	 * @param processType
	 * @param sensorml
	 * @param serviceType
	 * @return int 类型 保存成功则为1
	 */
	@WebMethod
	public int SaveEbRIMMethod(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "itnededApplication") String intendedApplication,
			@WebParam(name = "processType") String processType,
			@WebParam(name = "sensorml") String sensorml,
			@WebParam(name = "serviceType") String serviceType)
			throws ServiceException;

}
