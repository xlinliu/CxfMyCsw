package com.yxl.csw.Services;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.yxl.mobile.common.types.MobileEventBasicInfo;

/**
 * 主要是提供给移动段使用
 * 
 * @author yxliang
 * 
 */
@WebService
public interface AndroidAppService {
	/**
	 * 返回所有事件的关联的全部的信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<MobileEventBasicInfo> getAllMobileEventBasicInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;
	/**
	 * 获取经过时间，空间过滤的事件 
	 * @param username 用户名
	 * @param password 用户密码
	 * @param starttime 开始时间 
	 * @param endTime 结束时间
	 * @param polygon 查询的空间范围 
	 * @return
	 * @throws NullZeroException 
	 * @throws ServiceException 
	 */
	@WebMethod
	public List<MobileEventBasicInfo> getMobileEventBasicInfo(@WebParam(name="username")String username,
			@WebParam(name="password")String password, @WebParam(name="starttime")Date starttime, @WebParam(name="endTime")Date endTime, @WebParam(name="polygon")String polygon) throws ServiceException, NullZeroException;
}
