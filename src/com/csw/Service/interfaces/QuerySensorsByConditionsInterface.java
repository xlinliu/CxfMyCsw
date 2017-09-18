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
public interface QuerySensorsByConditionsInterface {
	/**
	 * 根据查询条件查询获取所有的传感器
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param all
	 *            是否查询其他用户公开的传感器信息
	 * @param allownull
	 *            是否运行当查询的字段为空时，认为是可以返回的传感器列表
	 * 
	 * @param conditions
	 *            查询的条件
	 * @return 返回所有满足条件的传感器的序列
	 */
	@WebMethod
	public List<String> QuerySensorsByConditionsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "condition") @XmlJavaTypeAdapter(MapAdapter.class) Map<String, String> conditions,
			@WebParam(name = "all") boolean all,
			@WebParam(name = "allownull") boolean allownull)
			throws ServiceException;

}
