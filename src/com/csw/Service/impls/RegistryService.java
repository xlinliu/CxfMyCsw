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
				throw new ServiceException("�û��޷�ע�ᣬ��Ϊ���û����Ѿ���ע�ᣬ������û���!");
			}
		} catch (Exception e) {
			throw new ServiceException("ע��ʧ��!��������[" + e.getLocalizedMessage()
					+ "]");
		}
	}
}
