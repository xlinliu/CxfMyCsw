package com.csw.Service.impls;

import com.csw.Service.interfaces.DeleteSensorMLByIdServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class DeleteSensorMLByIdService implements
		DeleteSensorMLByIdServiceInterface {
	public static void main(String[] args) throws ServiceException {
		DeleteSensorMLByIdService dsms=new DeleteSensorMLByIdService();
		dsms.DeleteSensorMLByIdMethod("admin", "cswadmin", "urn:liesmars:insitusensor:BusTAIYUAN-A81757-BDS", "deleteall");
		dsms.DeleteSensorMLByIdMethod("admin", "cswadmin", "urn:liesmars:insitusensor:platform:BusTAIYUAN-A81757", "deleteall");
		System.out.println("over");
	}
	public int DeleteSensorMLByIdMethod(String username, String password,
			String sensorid, String deleteType) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
//		if (deleteType == null || deleteType.trim().equals("")
//				|| !deleteType.trim().toLowerCase().equals("deleteall")
//				|| !deleteType.trim().toLowerCase().equals("deletesensorml")) {
//			throw new ServiceException("参数deleteType不正确!");
//		}
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("参数输入sensor的id值");
		} //
		// 第二步：,转换好sensorml的id，检查是否存在指定的sensorml文档
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		// 检查相应的sensorML的id是否存在
		boolean bol = OperateSensorUtil.CheckSensorMLExistMethod(username,
				sensorid);
		// 第三步：删除制定的sensorML文档
		if (bol) {
			boolean nums = OperateSensorUtil.DeleteSensorML(username, password,
					sensorid);
			if (nums) {
				return 1;
			} else {
				throw new ServiceException("删除失败，如果还有问题，请联系服务端!");
			}
		} else {
			// 没有该文档，则返回异常
			throw new ServiceException("该文档不存在或者该用户没有权限修改该文档");
		}
	}
}
