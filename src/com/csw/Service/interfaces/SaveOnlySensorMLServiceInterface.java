package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface SaveOnlySensorMLServiceInterface {
	/**
	 * 仅仅将用户上传的sensorml上传，而不保存转换的ebrim信息的方法
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensormlcontent
	 *            用户传递的过来的sensorml的文档的内容
	 * @param sensorid
	 *            用户传递过来的sensorml的id值
	 * @param filename
	 *            用户传递过来的sensorml的文档的名称
	 * @param createtime
	 *            文档的创建时间
	 * @return 成功返回 1，失败 返回0；
	 */
	@WebMethod
	public int SaveOnlySensorMLMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensormlcontent") String sensormlcontent,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "filename") String filename,
			@WebParam(name = "createtime") String createtime)
			throws ServiceException;
}
