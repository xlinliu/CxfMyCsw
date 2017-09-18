package com.csw.Service.impls;

import java.io.File;
import java.io.FileInputStream;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import com.csw.Service.interfaces.TransformSensorMLToebRIMServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;
import com.googlecode.jsonplugin.JSONResult;

public class TransformSensorMLToebRIMService implements
		TransformSensorMLToebRIMServiceInterface {

	/**
	 * 将system，component和processModel的SensorML转换为EbRIM格式内容
	 * 
	 * @param sensorml
	 *            system,component,processModel的SensorML内容
	 * @return 返回转换好的EbRIM格式内容
	 * @throws Exception
	 */
	public String TransformNonProcessChainToEbRIMMethod(String sensorml)
			throws Exception {
		String basepath = new GetRealPathUtil().getWebInfPath();
		if (basepath.endsWith("\\") || basepath.endsWith("/")) {
		} else {
			basepath += "\\";
		}
		String sensormlfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "sensorml");
		String processmodelxsltpath = basepath + "transformxsls\\Process.xsl";
		String xslfilepath = basepath + "transformxsls\\SensorMLToEbRIM.xsl";
		String ebrimfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "ebrim");
		String ebrimcontent = "";
		basepath = new GetRealPathUtil().getWebInfPath();
		// System.out.println(sensormlfilepath);
		FileOperationUtil.WriteToFileContent(sensorml.trim(), sensormlfilepath,
				"UTF-8");
		TransformerFactory tf = TransformerFactory.newInstance();
		Source source = null;
		if (sensorml.toLowerCase().contains("ProcessModel>".toLowerCase())) {
			source = new StreamSource(new File(processmodelxsltpath));
		} else if (sensorml.toLowerCase().contains("System>".toLowerCase())) {
			source = new StreamSource(new File(xslfilepath));
		}
		@SuppressWarnings("unused")
		Transformer tfomerTransformer = tf.newTransformer(source);
		Source smlsources = new StreamSource(new FileInputStream(
				sensormlfilepath));
		StreamResult result = new StreamResult(new File(ebrimfilepath));
		@SuppressWarnings("unused")
		JSONResult jsonResult = new JSONResult();
		tf.newTransformer(source).transform(smlsources, result);
		ebrimcontent = FileOperationUtil
				.ReadFileContent(ebrimfilepath, "UTF-8");
		return ebrimcontent;
	}

	/**
	 * 将sensorml转换为ebrim个格式的文档
	 */
	public String TransactionSensorMLToeEbRIMMethod(String username,
			String password, String sensorml) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorml) == null) {
			throw new ServiceException("参数sensorml不能为空!");
		}
		String basepath = new GetRealPathUtil().getWebInfPath();
		String sensormlfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "sensorml");
		String ebrimfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "ebrim1");
		String processmodelxsltpath = basepath + "transformxsls\\Process.xsl";
		String xslfilepath = basepath + "transformxsls\\SensorMLToEbRIM.xsl";

		String ebrim = TransactionOperation.ParseAndSaveAllTypesProcessMethod(
				sensorml, sensormlfilepath, processmodelxsltpath, xslfilepath,
				ebrimfilepath, null, null, null, false, username);
		if (ebrim.equals("")) {
			throw new ServiceException("参数ebrim内容错误，请输入规范文档内容");
		}
		FileOperationUtil.DeleteFile(sensormlfilepath);
		FileOperationUtil.DeleteFile(ebrimfilepath);
		return ebrim;
	}
}
