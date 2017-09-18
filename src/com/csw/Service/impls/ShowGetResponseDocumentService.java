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
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(getrerecords) == null) {
			throw new ServiceException("参数getrerecords不能为空!");
		}
		if (StringUtil.checkStringIsNotNULLAndEmptyMethod(resultType) == null) {
			throw new ServiceException("参数resultType不能为空!");
		}
		if (!resultType.trim().toLowerCase().equals("ebrim")
				|| !resultType.trim().toLowerCase().equals("sensorml")) {
			throw new ServiceException("参数resultType为空，或者取值不正确，请核实!");
		}
		String basepath = new GetRealPathUtil().getWebInfPath();
		// 用于生成相应的文件的路径
		String getRecordFilePath = FileOperationUtil.CreateFilePathOperation(
				basepath, "getrecords");
		// 存储最终的getRecordResponse的文档路径
		String getResponseResponsepath = FileOperationUtil
				.CreateFilePathOperation(basepath, "getrecordsRes");
		// 存储临时查询文件
		String getrrfilepath = FileOperationUtil.CreateFilePathOperation(
				basepath, "getrecordsSome");
		// 将内容写入getRecord的文件中
		FileOperationUtil.WriteToFileContent(getrerecords.trim(),
				getRecordFilePath, "UTF-8");
		// 【该处是核心】根据getRecords的文档内容，创建查询结果的返回的文件生成GetRecordsResponse的文档内容
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
