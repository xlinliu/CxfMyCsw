/**
 * 
 */
package com.csw.utils.operatesensororbitandbbox.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.SatOrbitJiSuanType;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-18 下午04:52:32
 */
@WebService
public interface OperateSatIdWithStandNameAndPlatformInterface {
	/**
	 * 保存传感器对应的标准的传感器名和卫星平台名
	 * 
	 * @param username
	 * @param password
	 * @param sojst
	 * @return
	 */
	@WebMethod
	public Boolean SaveSatWithStandardNameAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satorbitJisuantype") SatOrbitJiSuanType sojst)
			throws ServiceException;

	/**
	 * 保存传感器对应的标准的传感器名和卫星平台名序列
	 * 
	 * @param username
	 * @param password
	 * @param sojst
	 * @return
	 */
	@WebMethod
	public Boolean SaveSatWithStandardNameAndPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satorbitJisuantypes") List<SatOrbitJiSuanType> sojst)
			throws ServiceException;

	/**
	 * 更新传感器对应的标准的传感器名和卫星平台名
	 * 
	 * @param username
	 * @param password
	 * @param sojst
	 * @return
	 */
	@WebMethod
	public Boolean UpdateSatWithStandardNameAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satorbitJisuantype") SatOrbitJiSuanType sojst)
			throws ServiceException;

	/**
	 * 更新传感器对应的标准的传感器名和卫星平台名序列
	 * 
	 * @param username
	 * @param password
	 * @param sojst
	 * @return
	 */
	@WebMethod
	public Boolean UpdateSatWithStandardNameAndPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satorbitJisuantype") List<SatOrbitJiSuanType> sojst)
			throws ServiceException;

	/**
	 * 删除传感器对应的标准的传感器名和卫星平台名
	 * 
	 * @param username
	 * @param password
	 * @param satid
	 * @return
	 */
	public Boolean DeleteSatWithStandardNameAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satid") String satid) throws ServiceException;

	/**
	 * 删除传感器对应的标准的传感器名和卫星平台名序列
	 * 
	 * @param username
	 * @param password
	 * @param satids
	 * @return
	 */
	public Boolean DeleteSatWithStandardNameAndPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satid") List<String> satids)
			throws ServiceException;

	/**
	 * 判断指定的传感器的标准的传感器名和是否存在
	 * 
	 * @param username
	 * @param password
	 * @param satid
	 * @return
	 * @throws ServiceException
	 */
	public Boolean IsExistWithStandNameAndPaltOfSatId(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satid") String satid) throws ServiceException;

	/**
	 * 读取指定的传感器的信息
	 * 
	 * @param username
	 * @param password
	 * @param satid
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public SatOrbitJiSuanType GetSatStandardNameAndPlatformInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satid") String satid) throws ServiceException;

	/**
	 * 读取全部传感器的信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SatOrbitJiSuanType> getAllSatOrbitJiSuanTypes(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;

	/**
	 * 读取指定的传感器的信息序列
	 * 
	 * @param username
	 * @param password
	 * @param satids
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SatOrbitJiSuanType> GetSatStandardNameAndPlatformInfos(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satids") List<String> satid)
			throws ServiceException;
}
