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
 *项目名称:CxfMyCsw 类描述：查询注册中心中的所有的平台和其附属的传感器的列表 创建人:Administrator 创建时间: 2013-8-23
 * 上午09:10:37
 */
@WebService
public interface QueryPlatFormandSensorsInterface {
	/**
	 * 获取系统中存储的所有传感器的平台和其附属的传感器的标识符号列表
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param platfromtype
	 *            平台的名称
	 * @return 返回的是传感器的平台和其搭载的传感器的列表，存在多个则以“|”隔开
	 */
	@WebMethod
	public List<PlatformandSensors> GetPlatFormandSensors(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "platformtype") String platfromtype)
			throws ServiceException;
}
