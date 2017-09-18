/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

/**
 *项目名称:CxfMyCsw 类描述：获取所有的平台的类型集合 创建人:Administrator 创建时间: 2013-8-23 下午02:15:52
 */
@WebService
public interface GetAllPlatfromTypesInterface {
	@WebMethod
	public List<String> getAllPlatfromType(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
