package com.csw.Service.impls;

import java.util.List;

import com.csw.Service.interfaces.GetRegistrySensorWithOwnerOptionInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-17 上午10:28:27
 */
public class GetRegistrySensorWithOwnerOptionService implements
		GetRegistrySensorWithOwnerOptionInterface {
	public List<String> GetRegistrySensorWithOwnerOptionMethod(String username,
			String password, boolean all) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		return GetRegistryRegistryInfoUtils.GetRegistryPackageList(username,
				all);
	}
}
