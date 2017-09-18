package com.csw.Service.impls;

import com.csw.Service.interfaces.SaveOnlySensorMLServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.SaveSensorMLAndEbRIMUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class SaveOnlySensorMLService implements
		SaveOnlySensorMLServiceInterface {

	public int SaveOnlySensorMLMethod(String username, String password,
			String sensorml, String sensorid, String filename, String createtime)
			throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("参数sensorid不能为空 ，请核实!");
		}
		// 判断sensorid是否为空
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorml)) {
			throw new ServiceException("参数sensorid不能为空 ，请核实!");
		}
		// 第一步生成sensorml所对应的ebRIM的文档内容
		String ebrimcontent = new TransformSensorMLToebRIMService()
				.TransactionSensorMLToeEbRIMMethod(username, password, sensorml);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(ebrimcontent)) {
			throw new ServiceException("参数sensorml中的文档内容不符合规范，请核实!");
		}
		// 第二步根据生成的ebRIM的文档内容获取相关的ebRIM的id值
		String ebrimid = GetRegistryRegistryInfoUtils
				.GetRegistryPackageIDByEbrimContent(ebrimcontent);
		// 判断是否已经存在该 id的sensorml的文档
		if (!OperateSensorUtil.CheckSensorMLExistMethod(ebrimid).getIsExist()) {
			SaveSensorMLAndEbRIMUtil.SaveDBmyebrimsmlcontentsMethod(username,
					password, sensorml, null, ebrimid, filename, createtime,
					true);
			return 1;
		} else {
			throw new ServiceException("该文档已经存在");
		}
	}
}
