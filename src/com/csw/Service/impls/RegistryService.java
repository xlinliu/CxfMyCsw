package com.csw.Service.impls;

import com.csw.Service.interfaces.RegistryServiceInterface;
import com.csw.beans.LoginUserBean;
import com.csw.exceptions.ServiceException;
import com.csw.utils.Userutils.UserInfoUtil;

public class RegistryService implements RegistryServiceInterface {

	public int RegistryMethod(String address, int age, String emailAddress,
			int gender, int level, String password, String telephone,
			String username, String zhiye) throws ServiceException {
		try {
			if (UserInfoUtil.CheckUserNameIsExist(username)) {
				LoginUserBean lub = new LoginUserBean();
				lub.setUsername(username);
				lub.setPassword(password);
				lub.setTelephone(telephone);
				lub.setZhiye(zhiye);
				lub.setGender(gender);
				lub.setAge(age);
				lub.setAddress(address);
				lub.setLevel(level);
				UserInfoUtil.SaveUser(lub);
				return 1;
			} else {
				throw new ServiceException("用户无法注册，因为该用户名已经被注册，请更换用户名!");
			}
		} catch (Exception e) {
			throw new ServiceException("注册失败!详情如下[" + e.getLocalizedMessage()
					+ "]");
		}
	}
}
