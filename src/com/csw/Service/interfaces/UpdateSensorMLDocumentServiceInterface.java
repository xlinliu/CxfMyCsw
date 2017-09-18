package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface UpdateSensorMLDocumentServiceInterface {
	/**
	 * 更新制定的sensorml的id的文档的内容，如果没有的话，就当做插入操作进行处理
	 * 
	 * @param username
	 *            操作的額用戶名，必須存在
	 * @param passowrd
	 *            操作的用戶密碼，必须存在，且必须与username和数据库中的记录匹配
	 * @param sensormlcontent
	 *            需要更新的或者插入的sensorml的文檔的內容
	 * @param filename
	 *            上传的filename的名称，可以为空
	 * @return 1：更新成功，0 更新失败，2更新出现异常
	 * @throws ServiceException
	 */
	@WebMethod
	public int UpdateSensorMLDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String passowrd,
			@WebParam(name = "sensormlcontent") String sensormlcontent,
			@WebParam(name = "filename") String filename,
			@WebParam(name = "createtime") String createtime) throws ServiceException;
}
