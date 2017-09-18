/**
 * 
 */
package com.csw.Service.impls;

import com.csw.Service.interfaces.CheckUserInfoInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-14 下午10:34:32
 */
public class CheckUserInfoService implements CheckUserInfoInterface {

	public int CheckUserInfoMethod(String username, String password)
			throws ServiceException {
		new OperateSensornewUtil();
		return UserInfoUtil.CheckUserLogin(username, password);
	}
}
