/**
 * 
 */
package com.csw.Service.impls;

import com.csw.Service.interfaces.CheckUserInfoInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-14 ����10:34:32
 */
public class CheckUserInfoService implements CheckUserInfoInterface {

	public int CheckUserInfoMethod(String username, String password)
			throws ServiceException {
		new OperateSensornewUtil();
		return UserInfoUtil.CheckUserLogin(username, password);
	}
}
