package com.csw.Service.impls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.csw.Service.interfaces.GetAllSensorMLDocumentByIdsServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.MyebRIMSmlContents;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class GetAllSensorMLDocumentByIdsService implements
		GetAllSensorMLDocumentByIdsServiceInterface {

	public Map<String, String> GetAllSensorMLDocumentByIdsMethod(
			String username, String password, List<String> ids,
			String contenttype, boolean type) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		Map<String, String> maps = new HashMap<String, String>();
		// 读取不到sensorML的文档的内容
		String errorids = "";
		if (ids == null || ids.size() <= 0) {
			throw new ServiceException("参数ids输入错误，请核实!");
		}
		if (contenttype == null
				|| (!contenttype.toLowerCase().trim().equals("sensorml") && !contenttype
						.toLowerCase().trim().equals("ebrim"))) {
			throw new ServiceException("参数contenttype输入错误，请核实!");
		}
		// 第二步，检查用户的权限
		if (type) {
			// 查询所有的用户的文档
			if (UserInfoUtil.GetLevelOfUser(username, password) == 1) {
				// 是系统管理员级别
				if (ids != null && ids.size() > 0) {
					for (int i = 0; i < ids.size(); i++) {
						MyebRIMSmlContents My = new OperateSensorUtil()
								.GetSensorMLDocumentByIdMethod(username, ids
										.get(i).trim(), true);
						if (My != null) {
							String str = "";
							if (contenttype == null) {
								str = My.getSensormlContent().trim();
							} else {
								if (contenttype == null) {
									str = My.getSensormlContent().trim();
								} else {
									if (contenttype.trim().toLowerCase()
											.equals("sensorml")) {
										str = My.getSensormlContent().trim();
									} else if (contenttype.trim().toLowerCase()
											.equals("ebrim")) {
										str = My.getEbrimContent().trim();
									} else {
										str = My.getSensormlContent().trim();
									}
								}
							}
							if (str != null && !str.trim().equals("")) {
								maps.put(ids.get(i).trim(), FormatXmlUtil
										.formatXml(str.trim()));
							} else {
								// 如果没有与id相对应的sensorML文档的时候，就以null来代替
								maps.put(ids.get(i).trim(), "null");
							}
						} else {
							// 这个说明根本就不存在这个sensorML的id
							errorids += ids.get(i).trim() + " ";
						}
					}
					if (errorids.equals("")) {
						// 如果没有id是错误的则可以是将所有的信息传递过去
						return maps;
					} else {
						// 如果有问题，则需要就这个异常进行处理
						throw new ServiceException("这些ids [" + errorids.trim()
								+ "]输入错误，请检验后重新输入!");
					}
				} else {
					throw new ServiceException("用户[" + username
							+ "]需要查询的sensorml文档的id输入错误");
				}
			}
		} else {
			// 只是查询自己的文档
			if (ids != null && ids.size() > 0) {
				String nullids = "";
				for (int i = 0; i < ids.size(); i++) {
					MyebRIMSmlContents My = new OperateSensorUtil()
							.GetSensorMLDocumentByIdMethod(username, ids.get(i)
									.trim(), false);
					if (My != null) {
						String str = "";
						if (contenttype == null) {
							str = My.getSensormlContent().trim();
						} else {
							if (contenttype == null) {
								str = My.getSensormlContent().trim();
							} else {
								if (contenttype.trim().toLowerCase().equals(
										"sensorml")) {
									str = My.getSensormlContent().trim();
								} else if (contenttype.trim().toLowerCase()
										.equals("ebrim")) {
									str = My.getEbrimContent().trim();
								} else {
									str = My.getSensormlContent().trim();
								}
							}
						}
						if (str != null && !str.trim().equals("")) {
							maps.put(ids.get(i).trim(), FormatXmlUtil
									.formatXml(str.trim()));
						} else {
							// 保存数据库中为空的ids
							maps.put(ids.get(i).trim(), "null");
						}
					} else {
						// 保存数据库中没有保存的sensor的id
						nullids += ids.get(i).trim() + " ";
					}
				}
				if (nullids.equals("")) {
					// 没有为空的或者sensorml的conent的存储为空的记录，则返回所有的查询到的sensorml的文档的内容
					return maps;
				} else {
					throw new ServiceException("用户[" + username + "]输入的ids["
							+ nullids + "]错误!");
				}
			} else {
				throw new ServiceException("用户[" + username
						+ "]需要查询的sensorml文档的id输入错误!");
			}
		}
		return null;
	}
}
