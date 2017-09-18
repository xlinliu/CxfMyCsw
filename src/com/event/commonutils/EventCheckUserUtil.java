package com.event.commonutils;

import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 * ��׼�û���Ϣ
 * 
 * @author yxliang
 * 
 */
public class EventCheckUserUtil {
	/**
	 * ��׼�û���Ϣ
	 * 
	 * @param username
	 *            �û��ṩ������
	 * @param password
	 *            �û��ṩ������
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	public static int CheckUserLogin(String username, String password)
			throws ServiceException, NullZeroException {
		return UserInfoUtil.CheckUserLogin(username, password);
	}

	/**
	 * ��ʵ�û���Ϣ
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static int CheckUserInfo(String username, String password) {
		return UserInfoUtil.CheckUserInfo(username, password);
	}
}
