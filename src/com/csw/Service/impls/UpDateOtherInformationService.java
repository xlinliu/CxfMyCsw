package com.csw.Service.impls;

import com.csw.Service.interfaces.UpDateOtherInformationServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class UpDateOtherInformationService implements
		UpDateOtherInformationServiceInterface {

	public int UpdateOtherInformationMethod(String adminusername,
			String adminpassword, String username, String password,
			String newpassword, int level) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		String[] s = { adminusername, adminpassword };
		if (StringUtil.checkStrsIsNotNULLAndEmptyMethod(s) == null) {
			throw new ServiceException("请输入管理员级别用户名或密码!");
		}
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(newpassword) == null) {
			throw new ServiceException("参数 newpassword输入错误，不为空，长度必须不小于6");
		}
		if (UserInfoUtil.GetLevelOfUser(adminusername, adminpassword) == 1) {
			boolean bol = UserInfoUtil
					.UpdateUserInfo("update LoginUserBean set password='"
							+ newpassword + "',level='" + level
							+ "' where username='" + username + "'");
			if (bol) {
				return 1;
			} else {
				throw new ServiceException("用户[" + username + "]的级别更新失败!");
			}
		}
		return 0;
	}
}
