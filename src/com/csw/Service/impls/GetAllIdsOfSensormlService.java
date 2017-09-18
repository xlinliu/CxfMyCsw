package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.csw.Service.interfaces.GetAllIdsOfSensormlServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class GetAllIdsOfSensormlService implements
		GetAllIdsOfSensormlServiceInterface {

	public List<String> GetAllIdsOfSensormlMethod(String username,
			String password, String type) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(type)) {
			throw new ServiceException("参数type内容错误!");
		}
		// 第二步则读取数据库的所有的指定的username所存储的sensorml的id值得集合
		List<String> lists = GetIdsOfUsers(username, password);
		// 第三步则是将结果返回
		if (lists != null && lists.size() > 0) {
			if (type.equals("sensorml")) {
				List<String> results = new ArrayList<String>();
				for (int i = 0; i < lists.size(); i++) {
					results.add(StringUtil.DeletePackageStr(lists.get(i)));
				}
				return results;
			}
			if (type.equals("ebrim")) {
				return lists;
			}
		} else {
			return null;
		}
		// 用户没有自己的文档，则返回0
		return null;
	}

	/**
	 * 返回指定的用户所拥有的全部的文档的id,调用之前需要进行用户名与密码的检验
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @param type
	 *            返回的id的类型，两种，sensorml与ebrim
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> GetIdsOfUsers(String username, String password) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Query query = session
				.createQuery("select id from MyebRIMSmlContents where ower='"
						+ username + "'");
		List<String> lists = (List<String>) query.list();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		return lists;
	}
}
