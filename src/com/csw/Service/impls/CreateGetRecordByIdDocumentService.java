package com.csw.Service.impls;

import com.csw.Service.interfaces.CreateGetRcordByIdDocumentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.CreateGetRecordByIdDocumentUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.StringUtil;

public class CreateGetRecordByIdDocumentService implements
		CreateGetRcordByIdDocumentServiceInterface {
	/**
	 * 根据record的id值来获取生成records的查询服务
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param recordId
	 *            record的id值
	 * @return 返回生成的getRecords的查询文档的内容，失败返回null
	 * @throws 返回异常信息
	 */
	public String CreateGetRecordByIdDocumentMethod(String username,
			String password, String recordId) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(recordId)) {
			throw new ServiceException("参数recordId内容不正确!");
		}
		String getRecordIdDocument = "";
		// 第二步，如果是sensorML的格式的id，可以转换为ebRIM的格式的id
		recordId = StringUtil.AppendPacakgeStr(recordId);
		// 第三步，生成GetRecordById文档内容，并格式化该文档的内容
		try {
			getRecordIdDocument = FormatXmlUtil
					.formatXml(CreateGetRecordByIdDocumentUtil
							.createGetRecordByIdDocumentMehtod(recordId, "full")
							.xmlText());
			// 第四步，返回该文档的内容
			return getRecordIdDocument;
		} catch (Exception e) {
			throw new ServiceException("getRecordById文档生成内容生成错误，如未能解决，请联系服务端!");
		}
	}
}
