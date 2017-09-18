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
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorml) == null) {
			throw new ServiceException("����sensorml����Ϊ��!");
		}
		String basepath = new GetRealPathUtil().getWebInfPath();
		String ebrimfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "ebrim");
		String sensormlfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "sensorml");
		String xslfilepath = basepath + "transformxsls\\SensorMLToEbRIM.xsl";
		String processmodelxsltpath = basepath + "transformxsls\\Process.xsl";
		// ������ȡebrim�ĵ���id
		String ebrimcontent = new TransformSensorMLToebRIMService()
				.TransactionSensorMLToeEbRIMMethod(username, password, sensorml);
		// ��ȡ��������id��ebrim������
		RegistryPackageDocument rpd;
		String rpid = "";
		SaveSensorMLService sss = new SaveSensorMLService();
		try {
			rpd = RegistryPackageDocument.Factory.parse(ebrimcontent);
			rpid = rpd.getRegistryPackage().getId().trim();
			if (new CheckSensorIsExitService().CheckSensorIsExitMehtod(
					username, password, rpid)) {
				// ɾ��ע��Ĵ���������Ϣ
				new DeleteSensorMLByIdService().DeleteSensorMLByIdMethod(
						username, password, rpid, "deletesensorml");
				if (sss.IsExistsSensorML(username, password, rpid)) {
					// ɾ�����ڵ��ĵ�������
					sss.DeleteSensorML(username, password, rpid);
				}
			}
		} catch (XmlException e) {
			throw new ServiceException("����sensorml���ݴ���������淶�ĵ�����");
		}
		// ���������洫�ݽ��������е���Ϣ
		String ebrim = TransactionOperation.ParseAndSaveAllTypesProcessMethod(
				sensorml.trim(), sensormlfilepath, processmodelxsltpath,
				xslfilepath, ebrimfilepath, null, null, null, true, username);
		// ����sensorml���ĵ�������
		// �����µ�sensorml�ĵ�������
		sss.SaveSensorML(username, password, rpid, sensorml.trim());
		if (ebrim.equals("")) {
			throw new ServiceException("����sensorml���ݴ���������淶�ĵ�����");
		}
		FileOperationUtil.DeleteFile(sensormlfilepath);
		FileOperationUtil.DeleteFile(ebrimfilepath);
		return ebrim;
	}
}
