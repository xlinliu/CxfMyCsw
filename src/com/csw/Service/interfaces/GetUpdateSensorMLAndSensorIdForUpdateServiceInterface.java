package com.csw.Service.interfaces;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.MapAdapter;

@WebService
public interface GetUpdateSensorMLAndSensorIdForUpdateServiceInterface {
	/**
	 * 获取所有的需要更新的sensorml的id和sensorml的内容，前提是已经知道哪些sensormlid需要更新
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensormlids
	 *            必定需要更新的sensorml的id值
	 * @param resultidType
	 *            需要返回的resultType的类型，如"sensorml"表示返回的id是sensorml的id，"ebrim"表示返回的是ebrim的id
	 * @return 返回所有的需要更新的sensor的id和文档的sensorml的文档的内容，如果没有，则返回null
	 */
	@WebMethod
	public @XmlJavaTypeAdapter(MapAdapter.class)
	Map<String, String> GetUpdateSesnorMLAndSensorIdForUpdateByIdsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensormlids") List<String> sensormlids,
			@WebParam(name = "resultidType") String resultidType)
			throws ServiceException;

	/**
	 * 获取所有的需要更新的sensorml的id和sensorml的内容，前提是并不知道哪些sensormlids是一定需要更新的
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            需要更新的sensorml的id值集合
	 * @param stamps
	 *            需要更新的sensoriml的stamps的值 集合
	 * @param resultidType
	 *            需要返回的resultType的类型，如"sensorml"表示返回的id是sensorml的id，"ebrim"表示返回的是ebrim的id
	 * 
	 * @return 返回所有的需要更新的sensor的id和文档的sensorml的文档的内容，如果没有，则返回null
	 */
	@WebMethod
	public @XmlJavaTypeAdapter(MapAdapter.class)
	Map<String, String> GetUpdateSesnorMLAndSensorIdForUpdateMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids,
			@WebParam(name = "stamps") List<String> stamps,
			@WebParam(name = "resultidType") String resultidType)
			throws ServiceException;
}
