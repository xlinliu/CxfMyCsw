package com.csw.Service.impls;

import com.csw.Service.interfaces.UpdateUserInfoServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class UpdateUserInfoService implements UpdateUserInfoServiceInterface {
	public int UpdateUserInfoMethod(String username, String password,
			String address, String telephone) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(address) == null) {
			throw new ServiceException("参数address不能为空!");
		}
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(telephone) == null) {
			throw new ServiceException("参数telephone不能为空!");
		}
		boolean bol = UserInfoUtil.UpdateUserInfo(username, password, address,
				telephone);
		if (bol) {
			return 1;
		} else {
			throw new ServiceException("更新用户信息失败!");
		}
	}
}
