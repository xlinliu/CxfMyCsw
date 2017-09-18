/**
 * 
 */
package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetMutiSensorTypeOfSensorsInterface;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-8-22 上午09:30:09
 */
public class GetMutiSensorTypeOfSensorsService implements
		GetMutiSensorTypeOfSensorsInterface {
	@SuppressWarnings("unchecked")
	public List<String> GetMutiSensorTypeOfSensors(String username,
			String password, String sensors) throws ServiceException {
		List<String> sensortypes = new ArrayList<String>();
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名或密码!");
		}
		// 检验用户名与 密码在数据库中是否真正存在
		int result = UserInfoUtil.CheckUserInfo(username, password);
		if (result == 1) {
			// 获取传感器的的传感器的类型
			// 步骤是连接到LocalizedString数据表中
			// 根据id值获得相应的value值
			// 返回该值
			String[] tempsensors = sensors.trim().split(",");
			for (String sensorid : tempsensors) {
				Session session = GetSessionUtil
						.GetSessionInstance(GetSessionUtil.SENSORTYPE);
				try {
					session.beginTransaction().begin();
					List<String> values = (List<String>) session
							.createQuery(
									"select values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::SensorType' and id like '"
											+ sensorid.trim() + "%" + "'")
							.list();
					session.beginTransaction().commit();
					GetSessionUtil.CloseSessionInstance(session);
					if (values == null || values.size() == 0) {
						new ServiceException("传感器标识符为[" + sensorid
								+ "]的类型信息不存在!");
					}
					sensortypes.add(values.get(0).substring(0,
							values.get(0).length() - 1));

				} catch (Exception e) {
					GetSessionUtil.CloseSessionInstance(session);
					e.printStackTrace();
					throw new ServiceException("服务器出现问题，请联系客服，谢谢!");
				}
			}
		}
		return sensortypes;
	}

}
