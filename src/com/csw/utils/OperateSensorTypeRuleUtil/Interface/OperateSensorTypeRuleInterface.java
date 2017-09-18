package com.csw.utils.OperateSensorTypeRuleUtil.Interface;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorTypeMarker;
import com.csw.utils.custometypes.SensorTypeMarkerClient;

/**
 *项目名称:CxfMyCsw 类描述：对传感器类型定义的规则的确定 创建人:Administrator 创建时间: 2013-9-16 下午10:31:38
 */
@WebService
public interface OperateSensorTypeRuleInterface {
	/**
	 * 根据用户自定义的传感器的类型来查询传感器类型中包含的关键字序列
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensortypes
	 *            需要查询的传感器类型
	 * @return 返回包含了传感器类型和其对应的传感器的类型的关键字序列
	 */
	@WebMethod
	public List<SensorTypeMarker> GetAllSensorTypeRule(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortypes") List<String> sensortypes)
			throws ServiceException;

	/**
	 * 获取指定类型的传感器的关键字序列
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensortype
	 *            传感器类型的关键字
	 * @return
	 */
	@WebMethod
	public SensorTypeMarker GetSensorTypeRule(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortype") String sensortype)
			throws ServiceException;

	/**
	 * 更新用户指定的传感器的关键字序列
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param str
	 *            需要设置的传感器的信息
	 * @return SensorTypeMarker
	 */
	@WebMethod
	public Boolean UpdateSensorTypeRule(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "str") SensorTypeMarkerClient str)
			throws ServiceException;

	/**
	 * 更新用户指定的传感器序列的关键字序列
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param str
	 *            需要更新的传感器的信息集合
	 * @return 返回SensorTypeMarker序列
	 */
	@WebMethod
	public List<Boolean> UpdateSensorTypeRuleWithMultiEnties(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "str") List<SensorTypeMarkerClient> str)
			throws ServiceException;

	/**
	 * 删除指定的传感器类型
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensortype
	 *            需要删除的传感器的类型
	 * @return 返回 SensorTypeMarker
	 */
	@WebMethod
	public SensorTypeMarker DeleteSensorTypeRuleEntry(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortype") String sensortype)
			throws ServiceException;

	/**
	 *删除指定的传感器类型的序列
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensortypes
	 *            需要删除的传感器类型
	 * @param maker
	 *            传感器类型的所有者
	 * @return
	 */
	@WebMethod
	public List<SensorTypeMarker> DeleteSensorTypeRuleEntries(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortypes") List<String> sensortypes)
			throws ServiceException;

	/**
	 * 保存制定的传感器类型的序列
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param strs
	 *            设置的传感器基本信息序列
	 * @return 返回对应的传感器的信息（主要是查看isexist字段）
	 */
	@WebMethod
	public List<Boolean> SaveSensorTypeRuleEntries(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "strs") List<SensorTypeMarkerClient> strs)
			throws ServiceException;

	/**
	 * 保存制定的传感器类型
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param str
	 *            查看需要保存的传感器类型信息
	 * @return 返回对应的传感器的信息（主要是查看isexist字段）
	 */
	@WebMethod
	public Boolean SaveSensorTypeRuleEntry(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "str") SensorTypeMarkerClient str)
			throws ServiceException;

	/**
	 * 根据关键字来获取所对应的传感器的类型
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorkeyword
	 *            需要查看的关键字
	 * @return 返回对应的传感器的信息
	 * 
	 */
	@WebMethod
	public List<SensorTypeMarker> GetSensorKeyBelongSensorType(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorkeyword") String sensorkeyword)
			throws ServiceException;

	/**
	 * 根据关键字序列来获取对应的传感器的类型序列
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorkeywords
	 *            需要查询的传感器的关键字序列
	 * @return 返回对应的传感器的信息序列
	 */
	@WebMethod
	public List<SensorTypeMarker> GetSensorKeyBelongSensorTypes(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorkeywords") List<String> sensorkeywords)
			throws ServiceException;

	/**
	 * 判断是否存在相关的传感器类型的定义
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensortype
	 * @return
	 */
	@WebMethod
	public Boolean IsExistOfSensorType(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortype") String sensortype)
			throws ServiceException;

	/**
	 * 判断是否存在相关的传感器类型的定义
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return
	 */
	@WebMethod
	public Boolean DeleteAllSensorTypeRule(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
