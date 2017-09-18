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
public interface GetAllSensorMLDocumentByIdsServiceInterface {
	/**
	 * 根据id列表返回所有的sensorml的文档的内容
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param ids
	 *            用户需要获取的所有的sensorml的id的值集合
	 * @param contenttype
	 *            返回的内容格式 ebrim格式或者sensorml格式的内容
	 * @param type
	 *            用户是否需要获取所有的用户的文档（值为true），自身的文档（值为false）
	 * @return 返回所有符合条件的sennsorml的文档的信息
	 * @throws 发生异常
	 *             ，返回异常信息
	 */
	@WebMethod
	public @XmlJavaTypeAdapter(MapAdapter.class)
	Map<String, String> GetAllSensorMLDocumentByIdsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "ids") List<String> ids,
			@WebParam(name = "contenttype") String contenttype,
			@WebParam(name = "type") boolean type) throws ServiceException;
}
