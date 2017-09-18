package com.csw.Service.impls;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.activation.DataHandler;
import com.csw.Service.interfaces.SaveSensorMLAndEbRimContentByAttachementServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.SaveSensorMLAndEbRIMUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.CxfFile;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.StringUtil;

public class SaveSensorMLAndEbRimContentBySAttachementService implements
		SaveSensorMLAndEbRimContentByAttachementServiceInterface {

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
		if (ebrimcontent == null) {
			throw new ServiceException("参数sensorml格式内容不正确");
		}
		// 第二步根据生成的ebRIM的文档内容获取相关的ebRIM的id值
		String ebrimid = GetRegistryRegistryInfoUtils
				.GetRegistryPackageIDByEbrimContent(ebrimcontent);
		// 获取该sensorml是否已经被存储
		// 判断是否已经存在該 id的sensorml的文檔
		if (!OperateSensorUtil.CheckSensorMLExistMethod(ebrimid).getIsExist()) {
			SaveSensorMLAndEbRIMUtil.SaveDBmyebrimsmlcontentsMethod(username,
					password, sensorml, ebrimcontent, ebrimid, filename,
					createtime, true);
			return 1;
		} else {
			throw new ServiceException("该文档内容已经存在!");
		}
	}

	/**
	 * 保存需要存储的sensorml的id值和其他的内容
	 */
	public int SaveSensorMLAndEbRimContentMethod(String username,
			String password, CxfFile myfile, String createtime)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		DataHandler handler = myfile.getResume();
		try {
			String temppath = new GetRealPathUtil().getWebInfPath()
					+ "transformxsls\\middleresults\\"
					+ myfile.getCandidateName() + "."
					+ myfile.getResultFileType();
			InputStream is = handler.getInputStream();
			OutputStream os = new FileOutputStream(new File(temppath));
			byte[] b = new byte[5 * 1000 * 1024];// 设置附件最大限制
			int bytesRead = 0;
			while ((bytesRead = is.read(b)) != -1) {
				os.write(b, 0, bytesRead);
			}
			os.flush();
			os.close();
			is.close();
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					new File(temppath)));
			BufferedReader br = new BufferedReader(isr);
			String content = "";
			String tempcontent = "";
			while ((tempcontent = br.readLine()) != null) {
				content += tempcontent;
			}
			String sensorml = content;
			br.close();
			// System.out.println(FormatXmlUtil.formatXml(sensorml.trim()));
			FileOperationUtil.DeleteFile(temppath);
			String filename = myfile.getCandidateName().trim();
			SaveSensorMLAndEbRimContentMethod(username, password, sensorml,
					filename, createtime);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException("出现了问题，详情如下:[" + e.getLocalizedMessage()
					+ "]");
		}
	}
}
