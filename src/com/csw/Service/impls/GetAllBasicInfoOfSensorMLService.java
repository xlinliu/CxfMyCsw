package com.csw.Service.impls;

import java.util.List;

import com.csw.Service.interfaces.GetAllBasicInfoOfSensorMLServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorMLType;

public class GetAllBasicInfoOfSensorMLService implements
		GetAllBasicInfoOfSensorMLServiceInterface {

	public List<SensorMLType> GetAllBasicInfoOfSensorMLMethod(String username,
			String password, boolean type) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		try {
			// 如果获取全部的文档的信息，则需要是管理员级别的信息
			if (type) {
				// 第二步,检验用户的级别
				if ((UserInfoUtil.GetLevelOfUser(username, password)) != 1)
					throw new ServiceException("用户[" + username + "]的权限不够");
			}
			// 获取所有用户或者自身文档信息
			// 第三步，获取所有或者自身用户上传的文件的信息，id，上传时间，文件名
			return new OperateSensorUtil()
					.GetAllBasicInfoOfSensorMLMethod(username, password, type);
		} catch (Exception e) {
			throw new ServiceException("获取所有的用户的基本信息发生问题，详情请见["
					+ e.getLocalizedMessage() + "]");
		}
	}
}
