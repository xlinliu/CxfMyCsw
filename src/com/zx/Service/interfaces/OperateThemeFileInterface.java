package com.zx.Service.interfaces;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;

/**
 *项目名称:CxfMyCsw 类描述：用于保存和SensorML同时传递上来的文件 创建人:Administrator 创建时间: 2013-9-6
 * 上午08:48:37
 */
@WebService
public interface OperateThemeFileInterface {
	/**
	 *保存专题图文件
	 * 
	 * @param filename
	 *            专题图文件名称
	 * @param attchment
	 *            用户上传的文件
	 * @return 返回是否成功，1：成功，2：失败
	 */
	@WebMethod
	public int saveThemeFileMethod(
			@WebParam(name = "attchment") ThemeFileType image)
			throws ServiceException;

	/**
	 * 保存专题图文件集合
	 * 
	 * @param files
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public int saveAllThemeFileMethod(
			@WebParam(name = "files") List<ThemeFileType> files)
			throws ServiceException;
}