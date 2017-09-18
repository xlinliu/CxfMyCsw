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
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorml)) {
			throw new ServiceException("����sensorml����������ʵ!");
		}
		// ��һ������sensorml����Ӧ��ebRIM���ĵ�����
		String ebrimcontent = new TransformSensorMLToebRIMService()
				.TransactionSensorMLToeEbRIMMethod(username, password, sensorml);
		if (ebrimcontent == null) {
			throw new ServiceException("����sensorml��ʽ���ݲ���ȷ");
		}
		// �ڶ����������ɵ�ebRIM���ĵ����ݻ�ȡ��ص�ebRIM��idֵ
		String ebrimid = GetRegistryRegistryInfoUtils
				.GetRegistryPackageIDByEbrimContent(ebrimcontent);
		// ��ȡ��sensorml�Ƿ��Ѿ����洢
		// �ж��Ƿ��Ѿ�����ԓ id��sensorml���ęn
		if (!OperateSensorUtil.CheckSensorMLExistMethod(ebrimid).getIsExist()) {
			SaveSensorMLAndEbRIMUtil.SaveDBmyebrimsmlcontentsMethod(username,
					password, sensorml, ebrimcontent, ebrimid, filename,
					createtime, true);
			return 1;
		} else {
			throw new ServiceException("���ĵ������Ѿ�����!");
		}
	}

	/**
	 * ������Ҫ�洢��sensorml��idֵ������������
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
			byte[] b = new byte[5 * 1000 * 1024];// ���ø����������
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
			throw new ServiceException("���������⣬��������:[" + e.getLocalizedMessage()
					+ "]");
		}
	}
}
