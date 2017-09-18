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
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		String[] s = { adminusername, adminpassword };
		if (StringUtil.checkStrsIsNotNULLAndEmptyMethod(s) == null) {
			throw new ServiceException("���������Ա�����û���������!");
		}
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(newpassword) == null) {
			throw new ServiceException("���� newpassword������󣬲�Ϊ�գ����ȱ��벻С��6");
		}
		if (UserInfoUtil.GetLevelOfUser(adminusername, adminpassword) == 1) {
			boolean bol = UserInfoUtil
					.UpdateUserInfo("update LoginUserBean set password='"
							+ newpassword + "',level='" + level
							+ "' where username='" + username + "'");
			if (bol) {
				return 1;
			} else {
				throw new ServiceException("�û�[" + username + "]�ļ������ʧ��!");
			}
		}
		return 0;
	}
}
