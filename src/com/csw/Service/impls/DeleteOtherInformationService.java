package com.csw.Service.impls;

import javax.jws.WebService;
import com.csw.Service.interfaces.DeleteOtherInformationServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.Userutils.UserInfoUtil;

@WebService
public class DeleteOtherInformationService implements
		DeleteOtherInformationServiceInterface {
	public int DeleteUserInfoMethod(String username, String password)
			throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		if (UserInfoUtil.DeleteUserByUserName(username)) {
			return 1;
		} else {
			throw new ServiceException("�����ڸ��û�!");
		}
	}
}
