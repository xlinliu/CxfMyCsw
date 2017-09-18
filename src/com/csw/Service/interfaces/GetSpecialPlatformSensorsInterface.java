/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.PlatformandSensors;

/**
 *��Ŀ����:CxfMyCsw ����������ȡ�ƶ���ƽ̨�����еĴ���������Ϣ ������:Administrator ����ʱ��: 2013-8-23
 * ����01:53:10
 */
@WebService
public interface GetSpecialPlatformSensorsInterface {
	@WebMethod
	public List<PlatformandSensors> GetSepcialPlatfromSensors(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "platforms") List<String> platforms)
			throws ServiceException;

}
