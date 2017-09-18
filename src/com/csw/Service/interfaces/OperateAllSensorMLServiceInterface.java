package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface OperateAllSensorMLServiceInterface {
	/**
	 * 提供进行批量的操作的处理,用户需要提供三种操作方式"delete"
	 * ,"update","insert"中其中的一种，在进行delete操作的时候，
	 * 可以只提sensormlid，不提供sensorml的内容,可以将空字符串提供
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @param ids
	 *            需要操作的sensorml的id集合
	 * @param sensormlContents
	 *            需要操作的sensorml的操作的文档内容
	 * @param stamps
	 *            需要操作的sensorml的文档的创建时间集合
	 * @param operation
	 *            需要对所有sensorml进行操作的方式，只有三个"delete","update","insert"
	 * @return 成功返回1
	 */
	@WebMethod
	public int OperateAllSensorMLMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "ids") List<String> ids,
			@WebParam(name = "sensormlContents") List<String> sensormlContents,
			@WebParam(name = "filename") List<String> filenames,
			@WebParam(name = "stamps") List<String> stamps,
			@WebParam(name = "operation") String operation)
			throws ServiceException;

	/**
	 * 提供进行批量的操作的处理
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @param ids
	 *            需要操作的sensorml的id集合
	 * @param sensormlContents
	 *            需要操作的sensorml的操作的文档内容
	 * @param stamps
	 *            需要操作的sensorml的文档的创建时间集合
	 * @param operations
	 *            需要对所有每个文档sensorml进行操作的方式的集合，只有三个"delete","update","insert"
	 * @return 成功返回1
	 */
	@WebMethod
	public int OperateAllSensorMLOneByOneMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "ids") List<String> ids,
			@WebParam(name = "sensormlContents") List<String> sensormlContents,
			@WebParam(name = "filename") List<String> filenames,
			@WebParam(name = "stamps") List<String> stamps,
			@WebParam(name = "operations") List<String> operations)
			throws ServiceException;
}
