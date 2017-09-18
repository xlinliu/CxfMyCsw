package com.csw.Service.impls;

import javax.jws.WebService;
import com.csw.Service.interfaces.CheckUserNameServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.Userutils.UserInfoUtil;

@WebService
public class CheckUserNameService implements CheckUserNameServiceInterface {

	/**
	 * 查询用户名是否已经存在
	 * 
	 * @param username
	 *            需要查询的用户名
	 * @return true则说明不存在，false说明存在
	 */
	public boolean CheckUserName(String username) throws ServiceException {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(username)) {
			throw new ServiceException("用户名不能为空!");
		}
		return UserInfoUtil.CheckUserNameIsExist(username);
	}

	public static void main(String[] args) throws ServiceException {
		CheckUserNameService cuns = new CheckUserNameService();
		Boolean bol = cuns.CheckUserName("admin");
		System.out.println(bol + "");
	}
}
