package com.csw.Service.impls;

import org.apache.xmlbeans.XmlException;
import com.csw.Service.interfaces.UpdateAllEbRIMSensorInfoByEbRIMServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;
import com.ebrim.model.rim.RegistryPackageDocument;

public class UpdateAllEbRIMSensorInfoByEbRIMService implements
		UpdateAllEbRIMSensorInfoByEbRIMServiceInterface {

	public String UpdateAllEbRIMSensorInfoByEbRIMMethod(String username,
			String password, String sensorml) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorml) == null) {
			throw new ServiceException("参数sensorml不能为空!");
		}
		String basepath = new GetRealPathUtil().getWebInfPath();
		String ebrimfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "ebrim");
		String sensormlfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "sensorml");
		String xslfilepath = basepath + "transformxsls\\SensorMLToEbRIM.xsl";
		String processmodelxsltpath = basepath + "transformxsls\\Process.xsl";
		// 解析获取ebrim文档的id
		String ebrimcontent = new TransformSensorMLToebRIMService()
				.TransactionSensorMLToeEbRIMMethod(username, password, sensorml);
		// 获取传感器的id，ebrim结束的
		RegistryPackageDocument rpd;
		String rpid = "";
		SaveSensorMLService sss = new SaveSensorMLService();
		try {
			rpd = RegistryPackageDocument.Factory.parse(ebrimcontent);
			rpid = rpd.getRegistryPackage().getId().trim();
			if (new CheckSensorIsExitService().CheckSensorIsExitMehtod(
					username, password, rpid)) {
				// 删除注册的传感器的信息
				new DeleteSensorMLByIdService().DeleteSensorMLByIdMethod(
						username, password, rpid, "deletesensorml");
				if (sss.IsExistsSensorML(username, password, rpid)) {
					// 删除存在的文档的内容
					sss.DeleteSensorML(username, password, rpid);
				}
			}
		} catch (XmlException e) {
			throw new ServiceException("参数sensorml内容错误，请输入规范文档内容");
		}
		// 解析并保存传递进来的所有的信息
		String ebrim = TransactionOperation.ParseAndSaveAllTypesProcessMethod(
				sensorml.trim(), sensormlfilepath, processmodelxsltpath,
				xslfilepath, ebrimfilepath, null, null, null, true, username);
		// 保存sensorml的文档的内容
		// 保存新的sensorml文档的内容
		sss.SaveSensorML(username, password, rpid, sensorml.trim());
		if (ebrim.equals("")) {
			throw new ServiceException("参数sensorml内容错误，请输入规范文档内容");
		}
		FileOperationUtil.DeleteFile(sensormlfilepath);
		FileOperationUtil.DeleteFile(ebrimfilepath);
		return ebrim;
	}
}
