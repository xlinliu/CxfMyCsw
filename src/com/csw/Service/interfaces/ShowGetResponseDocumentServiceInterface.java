package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface ShowGetResponseDocumentServiceInterface {
	/**
	 * 通过getRecords的文档的获取getRecordsResponse的文档的信息
	 * ，文档必须是符合getRecords规范的信息,这里的查询只需要是用户就可以，即用户不设置权限
	 * 
	 * @param username
	 *            用户姓名
	 * @param password
	 *            用户密码
	 * @param getrerecords
	 *            获取的文档的你饿哦那个
	 * @param resultType
	 *            返回的文档的信息的类型，三种，分别是full，summary和brief
	 * @return 查询成功返回getRecordsRepsonse的文档的信息
	 */
	@WebMethod
	public String GetRecordsContent(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "getRecordContent") String getrerecords,
			@WebParam(name = "resultType") String resultType)
			throws ServiceException;
}
