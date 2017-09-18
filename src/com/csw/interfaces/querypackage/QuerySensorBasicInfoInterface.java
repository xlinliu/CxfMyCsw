package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface QuerySensorBasicInfoInterface {
	/**
	 * 获取指定的传感器序列集合的全称集合
	 * 
	 * @param username
	 *            注册中心名称
	 * @param password
	 *            注册中心密码
	 * @param sensorids
	 *            注册的传感器序列
	 * @return 返回传感器的序列
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorLongNamesMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorid)
			throws ServiceException;

	/**
	 * 获取传感器集合中预期应用集合
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            传感器序列集合
	 * @return 返回序列的传感器预期应用
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorIntendedApplicationForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

	/**
	 * 获取指定传感器集合的简称集合
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            用户查询的传感器的id值
	 * @return
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorShortNameforMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

	/**
	 * 获取传感器的类型，如雷达型，摄影型，地面站点等(集合层次操作）
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            传感器标识符
	 * @return 返回
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorSensorTypeForMultiSensorMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

	/**
	 * 获取传感器集合中工作开始时间信息集合
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户查询的传感器的标示符集合
	 * @return 返回传感器的工作开始时间集合
	 * @throws ServiceException
	 *             返回服务器端出现的错误信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorWorkValidTimeBeginForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

	/**
	 * 获取传感器的工作结束时间信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的标示符
	 * @return 返回传感器的工作结束时间
	 * @throws ServiceException
	 *             返回服务器端出现的错误信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorWorkValidTimeEndForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

	/**
	 * 获取指定的传感器的定位能力
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户需要查询的传感器的标示符
	 * @param infoface
	 *            用户需要查询的定位能力的某一方面
	 * @return 返回查询的结果
	 * @throws ServiceException
	 *             返回服务器端执行时出现的错误的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorPositionInfoForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

	/**
	 * 获取传感器的地理空间信息的方法（空间范围）
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            用户查询的传感器的标识符
	 * @return 返回传感器的地理空间范围的信息,格式为："坐标系统,西北角点经纬度,东南角点经纬度"
	 * @throws ServiceException
	 *             返回服务器端的错误的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorGetoInfoForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

	/**
	 * 获取传感器的组织信息的方法
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param sensorid
	 *            需要查询的传感器的标示符
	 * @return 返回传感器的组织信息（字符串的形式）
	 * @throws ServiceException
	 *             出现问题时返回的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorOrganizationInfoForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

	/**
	 * 获取传感器的某一方面的测量能力
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户查询的传感器的标示符
	 * @param capability
	 *            用户查询的传感器的哪方面的能力
	 * @return 返回制定的传感器在该capability方面的能力的信息
	 * @throws ServiceException
	 *             返回服务器端发生的服务错误的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorMeasureCapabilitiesForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException;

	/**
	 * 获取传感器的物理属性的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的物理属性的传感器的标识符
	 * @param physicalproperty
	 *            需要查询的传感器的物理属性的信息
	 * @return 返回传感器的physicalproperty的属性信息
	 * @throws ServiceException
	 *             返回服务器中发生的服务错误的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorPhysicalPropertyForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids,
			@WebParam(name = "physicalproperty") String physicalproperty)
			throws ServiceException;

	/**
	 * 获取传感器的组织单位的具体地址的信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户需要查询的传感器的标识符
	 * @return 返回所需要的传感器管理的单位的具体的地址的信息
	 * @throws ServiceException
	 *             返回出现的错误的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorConnectionEarthPosInfoFroMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
			throws ServiceException;

	/**
	 * 返回传感器中关于计算能力的信息的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户需要查询的传感器的标示符
	 * @param capability
	 *            用户需要查询的传感器的计算能力的哪方面的能力
	 * 
	 * @return 返回用户指定的传感器的计算能力在capability方面的能力
	 * @throws ServiceException
	 *             返回服务器端缠身的错误信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorComputeCapabilityForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException;

	/**
	 * 查询传感器中通信能力的
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            需要查询的传感器的标示符
	 * @param capability
	 *            用户查询的传感器的通信能力的哪一方面
	 * @return 返回传感器的查询的某一方面的通信能力的详细的信息
	 * @throws ServiceException
	 *             返回传感器中的发生的错误的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorCommunicationCapabilityForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException;

}
