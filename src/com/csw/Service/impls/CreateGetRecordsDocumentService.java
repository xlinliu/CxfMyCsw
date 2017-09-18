package com.csw.Service.impls;

import com.csw.Service.interfaces.CreateGetRecordsDocumentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.GetRecordsOperationUtil.GetRecordsUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class CreateGetRecordsDocumentService implements
		CreateGetRecordsDocumentServiceInterface {
	/**
	 * 根据用户填写的信息生成简单的getRecords的文档
	 * 
	 * @param username
	 *            用戶名
	 * @param password
	 *            密码
	 * @param startRecord
	 *            开始的查询记录
	 * @param maximumRecord
	 *            最大的返回的查询记录数
	 * @param west
	 *            西边的值
	 * @param east
	 *            东边的值
	 * @param south
	 *            南边的值
	 * @param north
	 *            北边的值
	 * @param startTime
	 *            开始的值
	 * @param endTime
	 *            结束的时间
	 * @param requestType
	 *            请求的类型
	 * @param profileType
	 *            profile的类型
	 * @param title
	 *            查询的关键 名称
	 * @param keyword
	 *            查询的关键子
	 * @return 返回的是生成getRecords的文档的内容,否则返回null;
	 */
	public String CreateGetRecofdsDocumentMethod(String username,
			String password, int startRecord, int maximumRecord, String west,
			String east, String south, String north, String startTime,
			String endTime, String requestType, String profileType,
			String title, String keyword) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		// 第二步，获取getRecords查询文档的模板
		try {
			String basepath = new GetRealPathUtil().getWebInfPath();
			String getRecordModelFilePath = basepath
					+ "\\operations\\getRecords.xml";
			// 第三步，则是生成相对应的getRecords的查询文档，并返回
			String result = GetRecordsUtil.CreateGetRecordsDocument(
					getRecordModelFilePath, maximumRecord, startRecord,
					requestType, startTime, endTime, keyword, title, east,
					north, south, west);
			result = FormatXmlUtil.formatXml(result);
			return result;
		} catch (Exception e) {
			throw new ServiceException(
					"生成getRecords.xml文档的内容没能正常的生成，如未能解决，请联系服务端!");
		}
	}
}
