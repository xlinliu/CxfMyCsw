package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;

import com.csw.Service.interfaces.AddRegistryPackageServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.Userutils.UserInfoUtil;

public class AddRegistryPackageService implements
		AddRegistryPackageServiceInterface {
	/**
	 * 获取自身注册的所有的RegistryPackage
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param operation
	 *            用户的操作,取值为transaction
	 * @return 返回用户自身所注册的全部的RegistryPackage的id值，是以list的方式返回，如果 没有成功则返回null
	 */
	public List<String> GetOwnRegistryPackageMetod(String username,
			String password) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> rpl = new ArrayList<String>();
		try {
			rpl = GetRegistryRegistryInfoUtils.GetRegistryPackageList(username,
					true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("获取用户自身拥有的文档的信息失败，可以联系服务端!");
		}
		return rpl;
	}
}