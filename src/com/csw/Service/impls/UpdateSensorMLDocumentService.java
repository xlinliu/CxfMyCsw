package com.csw.Service.impls;

import com.csw.Service.interfaces.UpdateSensorMLDocumentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class UpdateSensorMLDocumentService implements
		UpdateSensorMLDocumentServiceInterface {

	public int UpdateSensorMLDocumentMethod(String username, String password,
			String sensormlcontent, String filename, String createtime)
			throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(sensormlcontent) == null) {
			throw new ServiceException("请输入规范的sensorml文档内容");
		}
		// 第二步获取传递过来的sensorML的id（RegistyPackage的id值），并进行检测数据库存在数据库
		String ebrimcontent = new TransformSensorMLToebRIMService()
				.TransactionSensorMLToeEbRIMMethod(username, password,
						sensormlcontent);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(ebrimcontent) == null) {
			throw new ServiceException("sensorml文档的内容不符合规范，请核实snesomrl文档的内容!");
		}
		String ebrimid = GetRegistryRegistryInfoUtils
				.GetRegistryPackageIDByEbrimContent(ebrimcontent);
		if (OperateSensorUtil.CheckSensorMLExistMethod(username, ebrimid)) {
			if (OperateSensorUtil.UpdateSensorML(username, password, ebrimid,
					sensormlcontent)) {
				return 1;
			} else {
				throw new ServiceException("sensorml文档更新失败，如有问题，请联系服务端!");
			}
		} else {
			if (OperateSensorUtil.SaveSensorML(username, password, ebrimid,
					sensormlcontent)) {
				return 1;
			} else {
				return 0;
			}
		}

	}
}
