package com.csw.Service.impls;

import com.csw.Service.interfaces.SaveSensorMLAndEbRimContentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.SaveSensorMLAndEbRIMUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class SaveSensorMLAndEbRimContentService implements
		SaveSensorMLAndEbRimContentServiceInterface {

	public int SaveSensorMLAndEbRimContentMethod(String username,
			String password, String sensorml, String filename, String createtime)
			throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);

		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorml)) {
			throw new ServiceException("参数sensorml输入错误，请核实!");
		}
		// 第一步生成sensorml所对应的ebRIM的文档内容
		String ebrimcontent = new TransformSensorMLToebRIMService()
				.TransactionSensorMLToeEbRIMMethod(username, password, sensorml);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(ebrimcontent)) {
			throw new ServiceException("sensorml文档内容格式不正确，请核实sensorml文档的内容!");
		}
		// 第二步根据生成的ebRIM的文档内容获取相关的ebRIM的id值
		String ebrimid = GetRegistryRegistryInfoUtils
				.GetRegistryPackageIDByEbrimContent(ebrimcontent);
		// 获取该sensorml是否已经被存储
		// 判断是否已经存在该 id的sensorml的文档
		if (!OperateSensorUtil.CheckSensorMLExistMethod(ebrimid).getIsExist()) {
			SaveSensorMLAndEbRIMUtil.SaveDBmyebrimsmlcontentsMethod(username,
					password, sensorml, ebrimcontent, ebrimid, filename,
					createtime, true);
			return 1;
		} else {
			throw new ServiceException("上传的文档已经存在，请确定是否需要自己存入!");
		}
	}
}
