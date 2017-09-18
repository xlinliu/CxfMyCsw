package com.event.commonutils;

import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 * 核准用户信息
 * 
 * @author yxliang
 * 
 */
public class EventCheckUserUtil {
	/**
	 * 核准用户信息
	 * 
	 * @param username
	 *            用户提供的名称
	 * @param password
	 *            用户提供的密码
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	public static int CheckUserLogin(String username, String password)
			throws ServiceException, NullZeroException {
		return UserInfoUtil.CheckUserLogin(username, password);
	}

	/**
	 * 核实用户信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static int CheckUserInfo(String username, String password) {
		return UserInfoUtil.CheckUserInfo(username, password);
	}
}
