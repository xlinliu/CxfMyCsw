package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface DeleteManyFilesBatchServiceInterface {
	/**
	 * 删除所有的需要删除的文档的内容信息，需要注意的是，先要判断所有的filenames中的文件名称必须属于username才可以被删除
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param filenames
	 *            需要删除的文件名称
	 * @return 删除成功返回 1 
	 */
	@WebMethod
	public int DeleteManyFileContentsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "filenames") String filenames)
			throws ServiceException;
}
