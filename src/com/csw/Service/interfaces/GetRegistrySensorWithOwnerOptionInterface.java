package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

/**
 *项目名称:CxfMyCsw 类描述：该类主要是为了获取对应的传感器的信息 创建人:Administrator 创建时间: 2013-10-17
 * 上午10:25:29
 */
@WebService
public interface GetRegistrySensorWithOwnerOptionInterface {
	/**
	 * 获取指定的用户的信息
	 * 
	 * @param owner
	 * @param all
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<String> GetRegistrySensorWithOwnerOptionMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "all") boolean all) throws ServiceException;
}
