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
 *项目名称:CxfMyCsw 类描述：根据传感器的id列表获取传感器的SensorML文档 创建人:Administrator 创建时间: 2013-9-5
 * 下午02:39:38
 */
@WebService
public interface getSensorMLByIdsServiceInterface {
	// 获取指定传感器id集合中的全部传感器描述文档
	@WebMethod
	public List<String> getSensorMLsByIdsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
