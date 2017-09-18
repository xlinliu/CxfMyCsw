/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorPlatformPair;

/**
 *��Ŀ����:CxfMyCsw ����������ѯĳһ�������Ƿ�����ĳһƽ̨ ������:Administrator ����ʱ��: 2013-8-23
 * ����02:37:27
 */
@WebService
public interface SensorIsBelongPlatfromInterface {

	@WebMethod
	public boolean IsSensorBelongToPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensor") String sensor,
			@WebParam(name = "platform") String platform)
			throws ServiceException;

	@WebMethod
	public List<SensorPlatformPair> IsSensorsBelongToPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "spfs") List<SensorPlatformPair> spfs)
			throws ServiceException;
}
