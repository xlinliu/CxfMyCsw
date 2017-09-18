package com.yxl.csw.Services;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.yxl.mobile.common.types.MobileEventBasicInfo;

/**
 * ��Ҫ���ṩ���ƶ���ʹ��
 * 
 * @author yxliang
 * 
 */
@WebService
public interface AndroidAppService {
	/**
	 * ���������¼��Ĺ�����ȫ������Ϣ
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<MobileEventBasicInfo> getAllMobileEventBasicInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;
	/**
	 * ��ȡ����ʱ�䣬�ռ���˵��¼� 
	 * @param username �û���
	 * @param password �û�����
	 * @param starttime ��ʼʱ�� 
	 * @param endTime ����ʱ��
	 * @param polygon ��ѯ�Ŀռ䷶Χ 
	 * @return
	 * @throws NullZeroException 
	 * @throws ServiceException 
	 */
	@WebMethod
	public List<MobileEventBasicInfo> getMobileEventBasicInfo(@WebParam(name="username")String username,
			@WebParam(name="password")String password, @WebParam(name="starttime")Date starttime, @WebParam(name="endTime")Date endTime, @WebParam(name="polygon")String polygon) throws ServiceException, NullZeroException;
}
