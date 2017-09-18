package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface OperateAllSensorMLServiceInterface {
	/**
	 * �ṩ���������Ĳ����Ĵ���,�û���Ҫ�ṩ���ֲ�����ʽ"delete"
	 * ,"update","insert"�����е�һ�֣��ڽ���delete������ʱ��
	 * ����ֻ��sensormlid�����ṩsensorml������,���Խ����ַ����ṩ
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            �û�����
	 * @param ids
	 *            ��Ҫ������sensorml��id����
	 * @param sensormlContents
	 *            ��Ҫ������sensorml�Ĳ������ĵ�����
	 * @param stamps
	 *            ��Ҫ������sensorml���ĵ��Ĵ���ʱ�伯��
	 * @param operation
	 *            ��Ҫ������sensorml���в����ķ�ʽ��ֻ������"delete","update","insert"
	 * @return �ɹ�����1
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
	 * �ṩ���������Ĳ����Ĵ���
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            �û�����
	 * @param ids
	 *            ��Ҫ������sensorml��id����
	 * @param sensormlContents
	 *            ��Ҫ������sensorml�Ĳ������ĵ�����
	 * @param stamps
	 *            ��Ҫ������sensorml���ĵ��Ĵ���ʱ�伯��
	 * @param operations
	 *            ��Ҫ������ÿ���ĵ�sensorml���в����ķ�ʽ�ļ��ϣ�ֻ������"delete","update","insert"
	 * @return �ɹ�����1
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
