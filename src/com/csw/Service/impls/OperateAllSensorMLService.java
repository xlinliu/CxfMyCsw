package com.csw.Service.impls;

import java.util.List;
import com.csw.Service.interfaces.OperateAllSensorMLServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class OperateAllSensorMLService implements
		OperateAllSensorMLServiceInterface {
	public int OperateAllSensorMLMethod(String username, String password,
			List<String> ids, List<String> sensormlContents,
			List<String> filenames, List<String> stamps, String operation)
			throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		if (ids == null || ids.size() == 0) {
			throw new ServiceException("参数ids输入错误，请核实!");
		}
		if (sensormlContents == null || sensormlContents.size() == 0) {
			throw new ServiceException("参数sensormlContents输入错误，请核实!");
		}
		if (filenames == null || filenames.size() == 0) {
			throw new ServiceException("参数filenames输入错误，请核实!");
		}
		if (stamps == null || stamps.size() == 0) {
			throw new ServiceException("参数stamps输入错误，请核实!");
		}
		if (sensormlContents.size() == filenames.size()
				&& sensormlContents.size() == stamps.size()) {
			throw new ServiceException(
					"参数：[sensormlContents,filenames,sensormlContents]输入错误，请核实 !");
		}
		if (operation == null || operation.trim().equals("")
				|| !operation.trim().toLowerCase().equals("insert")
				|| !operation.trim().toLowerCase().equals("update")
				|| !operation.trim().toLowerCase().equals("delete")) {
			throw new ServiceException("参数operation输入错误，请核实!");
		}
		if (ids != null
				&& sensormlContents != null
				&& filenames != null
				&& stamps != null
				&& (ids.size() == sensormlContents.size()
						&& ids.size() == filenames.size() && ids.size() == stamps
						.size())) {
			// 分别将所有传递过来的sensorML进行处理
			for (int i = 0; i < ids.size(); i++) {
				if (new OperateSensorUtil().OperateSensorMLAndEbRim(
						username, password, ids.get(i),
						sensormlContents.get(i), filenames.get(i), stamps
								.get(i), operation) != 1) {
					// 只要其中有一个不满足要求，则退出，並且删掉已经存在的id
					int k = i;
					if (k != 0) {
						for (int j = 0; j < k; j++) {
							new OperateSensorUtil()
									.OperateSensorMLAndEbRim(username,
											password, ids.get(i),
											sensormlContents.get(i), filenames
													.get(i), stamps.get(i),
											"delete");
						}
					}
					throw new ServiceException(
							"对传递过来的sensorml进行某种操作时出现了问题，如能确保客户端无误，请联系客户端");
				}
			}
			return 1;
		}
		return 1;
	}

	public int OperateAllSensorMLOneByOneMethod(String username,
			String password, List<String> ids, List<String> sensormlContents,
			List<String> filenames, List<String> stamps, List<String> operations)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		if (ids == null || ids.size() == 0) {
			throw new ServiceException("参数ids输入错误，请核实!");
		}
		if (sensormlContents == null || sensormlContents.size() == 0) {
			throw new ServiceException("参数sensormlContents输入错误，请核实!");
		}
		if (filenames == null || filenames.size() == 0) {
			throw new ServiceException("参数filenames输入错误，请核实!");
		}
		if (stamps == null || stamps.size() == 0) {
			throw new ServiceException("参数stamps输入错误，请核实!");
		}
		if (sensormlContents.size() == filenames.size()
				&& sensormlContents.size() == stamps.size()) {
			throw new ServiceException(
					"参数：[sensormlContents,filenames,sensormlContents]输入错误，请核实 !");
		}
		if (operations == null || operations.size() == 0) {
			throw new ServiceException("参数operation输入错误，请核实!");
		}
		// 第二步：判断用户的输入的操作参数是否正确，判断是否不唯恐，而且所有的list的大小一样
		if (ids != null
				&& sensormlContents != null
				&& filenames != null
				&& stamps != null
				&& operations != null
				&& (ids.size() == sensormlContents.size()
						&& ids.size() == filenames.size()
						&& ids.size() == stamps.size() && ids.size() == operations
						.size())) {
			// 分别将所有传递过来的sensorML进行处理
			for (int i = 0; i < ids.size(); i++) {
				if (new OperateSensorUtil().OperateSensorMLAndEbRim(
						username, password, ids.get(i),
						sensormlContents.get(i), filenames.get(i), stamps
								.get(i), operations.get(i)) != 1) {
					// 只要其中有一个不满足要求，则退出，並且删掉已经存在的id
					int k = i;
					if (k != 0) {
						for (int j = 0; j < k; j++) {
							new OperateSensorUtil()
									.OperateSensorMLAndEbRim(username,
											password, ids.get(i),
											sensormlContents.get(i), filenames
													.get(i), stamps.get(i),
											"delete");
						}
					}
					throw new ServiceException("操作 失败 [" + ids.get(i) + "]");
				}
			}
			return 1;
		} else {
			throw new ServiceException("操作失败，参数错误，请核实!");
		}
	}
}
