package com.csw.Service.impls;

import com.csw.Service.interfaces.SearchGetRecordByIdDocumentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.GetRecordByIdOperationUtil.ParseGetRecordByIdUtil;
import com.csw.utils.SensorInfoUtil.CreateGetRecordByIdDocumentUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class SearchGetRecordByIdDocumentService implements
		SearchGetRecordByIdDocumentServiceInterface {
	public String SearchGetRecordByIdDocumentMethod(String username,
			String password, String getrecordByIdContent)
			throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil
				.checkStringIsNotNULLAndEmptyMethod(getrecordByIdContent)) {
			throw new ServiceException("参数getrecordByIdContent输入错误，请核实 !");
		}
		// 第二步，解析getRecordById文档，获取查询的id
		ParseGetRecordByIdUtil parseGetRecordByIdUtil = new ParseGetRecordByIdUtil();
		String rpid = parseGetRecordByIdUtil
				.ParseGetRecordByIdDocument(getrecordByIdContent);
		if (rpid == null) {
			throw new ServiceException("上传的文档getrecordByIdContent不规范，请核实!");
		}
		String ebrimcontent;
		ebrimcontent = CreateGetRecordByIdDocumentUtil
				.CreateGetRecordByIdResponseDocumentMethod(rpid);
		ebrimcontent = FormatXmlUtil.formatXml(ebrimcontent);
		return ebrimcontent;
	}
}
