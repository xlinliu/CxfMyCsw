package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.Service.interfaces.GetOwnerAllSensorMLDocumentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorMLType;

public class GetOwnerAllSensorMLDocumentService implements
		GetOwnerAllSensorMLDocumentServiceInterface {

	public List<String> GetOwnerAllSensorMLDocumentMethod(String username,
			String password) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		try {
			List<SensorMLType> smTypes = OperateSensorUtil
					.GetAllSensorMLInfoWithOwner(username);
			List<String> lists = new ArrayList<String>();
			if (smTypes != null) {
				for (SensorMLType smt : smTypes) {
					lists.add(smt.getSensorid());
				}
			}
			List<String> results = new ArrayList<String>();
			// 第三步个格式化
			if (lists != null && lists.size() > 0) {
				for (int i = 0; i < lists.size(); i++) {
					results.add(FormatXmlUtil.formatXml(lists.get(i).trim()));
				}
			}
			return lists;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("获取用户所有的上传文档时失败，请核实，详细信息如下["
					+ e.getLocalizedMessage() + "]");
		}
	}
}
