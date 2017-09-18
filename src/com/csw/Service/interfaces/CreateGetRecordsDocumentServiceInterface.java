package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface CreateGetRecordsDocumentServiceInterface {
	/**
	 * 根据用户填写的信息生成简单的getRecords的文档
	 * 
	 * @param username
	 *            用戶名
	 * @param password
	 *            密码
	 * @param startRecord
	 *            开始的查询记录
	 * @param maximumRecord
	 *            最大的返回的查询记录数
	 * @param west
	 *            西边的值
	 * @param east
	 *            东边的值
	 * @param south
	 *            南边的值
	 * @param north
	 *            北边的值
	 * @param startTime
	 *            开始的值
	 * @param endTime
	 *            结束的时间
	 * @param requestType
	 *            请求的类型
	 * @param profileType
	 *            profile的类型
	 * @param title
	 *            查询的关键 名称
	 * @param keyword
	 *            查询的关键子
	 * @return 返回的是生成getRecords的文档的内容
	 */
	@WebMethod
	public String CreateGetRecofdsDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "startRecord") int startRecord,
			@WebParam(name = "maximumRecord") int maximumRecord,
			@WebParam(name = "west") String west,
			@WebParam(name = "east") String east,
			@WebParam(name = "south") String south,
			@WebParam(name = "north") String north,
			@WebParam(name = "startTime") String startTime,
			@WebParam(name = "endtime") String endTime,
			@WebParam(name = "requestType") String requestType,
			@WebParam(name = "profileType") String profileType,
			@WebParam(name = "title") String title,
			@WebParam(name = "keyword") String keyword) throws ServiceException;
}
