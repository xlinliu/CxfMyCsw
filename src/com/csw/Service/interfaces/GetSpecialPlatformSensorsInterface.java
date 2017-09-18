/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.PlatformandSensors;

/**
 *项目名称:CxfMyCsw 类描述：获取制定的平台下所有的传感器的信息 创建人:Administrator 创建时间: 2013-8-23
 * 下午01:53:10
 */
@WebService
public interface GetSpecialPlatformSensorsInterface {
	@WebMethod
	public List<PlatformandSensors> GetSepcialPlatfromSensors(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "platforms") List<String> platforms)
			throws ServiceException;

}
