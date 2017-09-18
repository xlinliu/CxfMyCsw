package com.csw.Service.impls;

import java.util.List;

import com.csw.Service.interfaces.GetRegistrySensorWithOwnerOptionInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-17 ����10:28:27
 */
public class GetRegistrySensorWithOwnerOptionService implements
		GetRegistrySensorWithOwnerOptionInterface {
	public List<String> GetRegistrySensorWithOwnerOptionMethod(String username,
			String password, boolean all) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		return GetRegistryRegistryInfoUtils.GetRegistryPackageList(username,
				all);
	}
}
