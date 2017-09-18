package com.csw.Service.impls;

import com.csw.Service.interfaces.ShowGetResponseDocumentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.GetRecordsOperationUtil.GetRecordsOperation;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.StringUtil;

public class ShowGetResponseDocumentService implements
		ShowGetResponseDocumentServiceInterface {

	public String GetRecordsContent(String username, String password,
			String getrerecords, String resultType) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(getrerecords) == null) {
			throw new ServiceException("����getrerecords����Ϊ��!");
		}
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(resultType) == null) {
			throw new ServiceException("����resultType����Ϊ��!");
		}
		if (!resultType.trim().toLowerCase().equals("ebrim")
				|| !resultType.trim().toLowerCase().equals("sensorml")) {
			throw new ServiceException("����resultTypeΪ�գ�����ȡֵ����ȷ�����ʵ!");
		}
		String basepath = new GetRealPathUtil().getWebInfPath();
		// ����������Ӧ���ļ���·��
		String getRecordFilePath = FileOperationUtil.CreateFilePathOperation(
				basepath, "getrecords");
		// �洢���յ�getRecordResponse���ĵ�·��
		String getResponseResponsepath = FileOperationUtil
				.CreateFilePathOperation(basepath, "getrecordsRes");
		// �洢��ʱ��ѯ�ļ�
		String getrrfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "getrecordsSome");
		// ������д��getRecord���ļ���
		FileOperationUtil.WriteToFileContent(getrerecords.trim(),
				getRecordFilePath, "UTF-8");
		// ���ô��Ǻ��ġ�����getRecords���ĵ����ݣ�������ѯ����ķ��ص��ļ�����GetRecordsResponse���ĵ�����
		GetRecordsOperation.CreateGetRecordsResponseDocument(resultType.trim(),
				getRecordFilePath, getResponseResponsepath, getrrfilepath);
		String result = FormatXmlUtil.formatXml(FileOperationUtil
				.ReadFileContent(getResponseResponsepath, "UTF-8"));
		FileOperationUtil.DeleteFile(getRecordFilePath);
		FileOperationUtil.DeleteFile(getResponseResponsepath);
		FileOperationUtil.DeleteFile(getrrfilepath);
		return result;
	}
}
