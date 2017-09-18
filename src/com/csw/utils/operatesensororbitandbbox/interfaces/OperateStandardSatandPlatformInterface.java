/**
 * 
 */
package com.csw.utils.operatesensororbitandbbox.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.StandSatSensorPlatformPair;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-18 下午02:57:43
 */
@WebService
public interface OperateStandardSatandPlatformInterface {
	/**
	 * 保存标准的卫星的名称和对应的平台
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param satname
	 *            卫星传感器名称
	 * @param satplatform
	 *            卫星平台
	 * @return 返回true 保存成功，false保存失败
	 * @throws ServiceException
	 */
	@WebMethod
	public Boolean SaveStandardSatAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sspp") StandSatSensorPlatformPair sspp)
			throws ServiceException;

	/**
	 * 保存全部的传感器的名称
	 * 
	 * @param username
	 * @param password
	 * @param sspps
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public Boolean SaveStandardSatAndPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sspps") List<StandSatSensorPlatformPair> sspps)
			throws ServiceException;

	/**
	 * 更新标准的卫星的名称和对应的平台的信息
	 * 
	 * @param username
	 * @param password
	 * @param sspp
	 * @return
	 */
	@WebMethod
	public Boolean UpdateStandardSatAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sspp") StandSatSensorPlatformPair sspp)
			throws ServiceException;

	/**
	 * 更新全部的标准的卫星的名称和对应的平台的信息
	 * 
	 * @param username
	 * @param password
	 * @param sspps
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public Boolean UpdateStandardSatAndPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sspps") List<StandSatSensorPlatformPair> sspps)
			throws ServiceException;

	/**
	 * 删除标准的卫星的名称和对应的平台的信息
	 * 
	 * @param username
	 * @param password
	 * @param sspp
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public Boolean DeleteStandardSatAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sspp") StandSatSensorPlatformPair sspp)
			throws ServiceException;

	/**
	 * 删除全部的标准卫星名称和平台
	 * 
	 * @param username
	 * @param passowrd
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public Boolean DeleteAllStandardSatAndPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;

	/**
	 * 读取所有的标准的卫星名称和对应的平台信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<StandSatSensorPlatformPair> ReadStandSatAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;

	/**
	 * 判断是否存在
	 * 
	 * @param username
	 * @param password
	 * @param sp
	 * @return
	 */
	@WebMethod
	public Boolean IsExist(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorname") String sensorname,
			@WebParam(name = "satplat") String satplat) throws ServiceException;
}
