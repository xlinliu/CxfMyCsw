/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorPlatformPair;

/**
 *项目名称:CxfMyCsw 类描述：查询某一传感器是否属于某一平台 创建人:Administrator 创建时间: 2013-8-23
 * 下午02:37:27
 */
@WebService
public interface SensorIsBelongPlatfromInterface {

	@WebMethod
	public boolean IsSensorBelongToPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensor") String sensor,
			@WebParam(name = "platform") String platform)
			throws ServiceException;

	@WebMethod
	public List<SensorPlatformPair> IsSensorsBelongToPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "spfs") List<SensorPlatformPair> spfs)
			throws ServiceException;
}
