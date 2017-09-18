package com.csw.Service.impls;

import com.csw.Service.interfaces.UpdateUserInfoServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class UpdateUserInfoService implements UpdateUserInfoServiceInterface {
	public int UpdateUserInfoMethod(String username, String password,
			String address, String telephone) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(address) == null) {
			throw new ServiceException("����address����Ϊ��!");
		}
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(telephone) == null) {
			throw new ServiceException("����telephone����Ϊ��!");
		}
		boolean bol = UserInfoUtil.UpdateUserInfo(username, password, address,
				telephone);
		if (bol) {
			return 1;
		} else {
			throw new ServiceException("�����û���Ϣʧ��!");
		}
	}
}
