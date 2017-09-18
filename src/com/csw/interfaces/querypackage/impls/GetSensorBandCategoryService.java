package com.csw.interfaces.querypackage.impls;

import java.util.List;
import org.hibernate.Session;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorBandCategoryInterface;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class GetSensorBandCategoryService implements
		GetSensorBandCategoryInterface {

	@SuppressWarnings("unchecked")
	public String GetSensorBandCategoryMethod(String username, String password,
			String sensorid) throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名或密码!");
		}
		// 检验用户名与 密码在数据库中是否真正存在
		int result = UserInfoUtil.CheckUserInfo(username, password);
		if (result == 1) {
			// 获取传感器的的全名
			// 步骤是连接到LocalizedString数据表中
			// 根据id值获得相应的value值
			// 返回该值
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			try {
				session.beginTransaction().begin();
				List<String> values = (List<String>) session
						.createQuery(
								"select values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::measurementCapabilities:bandsCategory' and  id like '"
										+ sensorid.trim() + "%" + "'").list();
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstance(session);
				if (values == null || values.size() == 0) {
					throw new ServiceException("传感器标识符为[" + sensorid
							+ "]的波段类型不存在!");
				}
				return values.get(0).substring(0, values.get(0).length() - 1);

			} catch (Exception e) {
				e.printStackTrace();
				GetSessionUtil.CloseSessionInstance(session);
				throw new ServiceException("服务器出现问题，请联系客服，谢谢!");
			}

		} else {
			throw new ServiceException("用户名与密码错误!");
		}
	}

}
