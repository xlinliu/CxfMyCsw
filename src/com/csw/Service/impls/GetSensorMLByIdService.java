package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.Service.interfaces.GetSensorMLByIdServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.MyebRIMSmlContents;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class GetSensorMLByIdService implements GetSensorMLByIdServiceInterface {
	/**
	 * 获取单个的sensorml文档的内容，这个需要提供的参数有用户名，密码，与sensorml的id或者ebrim的id值
	 */
	public List<String> GetSensorMLByIdServiceMehtod(String username,
			String password, String docId) throws ServiceException {
		// 第一步验证用户名的真实性
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(docId)) {
			throw new ServiceException("参数docId输入错误，请核实!");
		}
		List<String> lists = new ArrayList<String>();
		String ebrimid = StringUtil.AppendPacakgeStr(docId);
		// 第三步则是查找数据库获取相应的sensorml文档的内容
		OperateSensorUtil gsmbiu = new OperateSensorUtil();
		MyebRIMSmlContents mys = gsmbiu.GetSensorMLDocumentByIdMethod(username,
				ebrimid, false);
		// 第四步则是将查找的sensorml的文档的内容返回
		if (mys != null) {
			if (mys.getFilename() == null) {
				lists.add("");
			} else if (mys.getFilename().trim().equals("")) {
				lists.add("");
			} else {
				lists.add(mys.getFilename().trim());
			}
			if (mys.getSensormlContent() == null) {
				lists.add("");
			} else if (mys.getSensormlContent().trim().equals("")) {
				lists.add("");
			} else {
				lists.add(FormatXmlUtil.formatXml(mys.getSensormlContent()
						.trim()));
			}
			return lists;
		} else {
			throw new ServiceException("用户自身上传的文档并不包含这个文档[" + docId + "]");
		}
	}
}
