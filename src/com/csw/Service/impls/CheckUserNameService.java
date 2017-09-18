package com.csw.Service.impls;

import javax.jws.WebService;
import com.csw.Service.interfaces.CheckUserNameServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.Userutils.UserInfoUtil;

@WebService
public class CheckUserNameService implements CheckUserNameServiceInterface {

	/**
	 * ��ѯ�û����Ƿ��Ѿ�����
	 * 
	 * @param username
	 *            ��Ҫ��ѯ���û���
	 * @return true��˵�������ڣ�false˵������
	 */
	public boolean CheckUserName(String username) throws ServiceException {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(username)) {
			throw new ServiceException("�û�������Ϊ��!");
		}
		return UserInfoUtil.CheckUserNameIsExist(username);
	}

	public static void main(String[] args) throws ServiceException {
		CheckUserNameService cuns = new CheckUserNameService();
		Boolean bol = cuns.CheckUserName("admin");
		System.out.println(bol + "");
	}
}
