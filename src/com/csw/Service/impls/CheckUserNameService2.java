package com.csw.Service.impls;

import javax.jws.WebService;

import com.csw.Service.interfaces.ChcekUserNameServiceInterface2;
import com.csw.utils.Userutils.UserInfoUtil;

@WebService
public class CheckUserNameService2 implements ChcekUserNameServiceInterface2 {

	/**
	 * ��ѯ�û����Ƿ��Ѿ�����
	 * 
	 * @param username
	 *            ��Ҫ��ѯ���û���
	 * @return true��˵�����ڣ�false˵��������
	 */
	public boolean CheckUserName(String username) {
		boolean bol = UserInfoUtil.CheckUserNameIsExist(username);
		return bol;
	}
}
