package com.csw.utils.custometypes;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.csw.model.ebXMLModel.SensorTypeRule;
import com.csw.utils.GetSessionUtil;

/**
 *项目名称:CxfMyCsw 类描述：常用的对sensortyperule规则进行处理的 创建人:Administrator 创建时间: 2013-9-17
 * 上午10:34:19
 */
public class SensortypeRuleOperation {
	/**
	 * 更新传递过来的传感器类型规则信息
	 * 
	 * @param stmc
	 *            用户提交的传感器的信息
	 * @param username
	 *            提交的用户名称
	 * @return 返回true成功更新，返回false 失败
	 */
	public static Boolean UpdateSensorTYpeInfo(SensorTypeRule stmc)
			throws Exception {
		// 更新出传感器
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();
			session.update(stmc);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return true;
		} catch (HibernateException e) {
			GetSessionUtil.CloseSessionInstance(session);
			e.printStackTrace();
			throw new Exception("更新传感器类型规则信息失败");
		}
	}

	/**
	 * 更具获取的指定传感器类型关键字来生成规则
	 * 
	 * @param sensorkeyword
	 *            传感器类型的关键字
	 * @param username
	 *            用户名称
	 * @return 返回生成的传感器的类型的定义
	 */
	@SuppressWarnings("unchecked")
	public static List<SensorTypeRule> getSensorTypeRuleInfoByKeywords(
			String sensorkeyword, String username) throws Exception {

		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();
			List<SensorTypeRule> strs = session.createQuery(
					"from SensorTypeRule where maker='" + username
							+ "' and sensorkeywords  like '%" + sensorkeyword
							+ "%'").list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return strs;
		} catch (Exception e) {
			e.printStackTrace();
			GetSessionUtil.CloseSessionInstance(session);
			throw new Exception(" 更具获取的指定传感器类型关键字来生成规则");
		}
	}

	/**
	 * 获取指定的传感器类型的规则
	 * 
	 * @param sensortypename
	 *            传感器类型英文名称
	 * @param username
	 *            传感器的规则定义
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static SensorTypeRule GetSensorTypeRuleInfo(String sensortypename,
			String username) throws Exception {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {

			session.beginTransaction().begin();
			List<SensorTypeRule> strs = session
					.createQuery(
							"from SensorTypeRule where sensortypename='"
									+ sensortypename + "' and maker='"
									+ username + "'").list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (strs != null && strs.size() > 0) {
				return strs.get(0);
			} else {
				return null;
			}
		} catch (HibernateException e) {
			GetSessionUtil.CloseSessionInstance(session);
			e.printStackTrace();
			throw new Exception("更新传感器类型规则信息失败");
		}
	}

	/**
	 * 保存传递过来的传感器的信息
	 * 
	 * @param sensorTypeMarkerClient
	 * @return
	 */
	public static Boolean SaveSensorTyepInfo(SensorTypeRule str) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();
			session.save(str);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			GetSessionUtil.CloseSessionInstance(session);
			return false;
		}
	}

	/**
	 * 删除指定用户中的某一指定的传感器类型信息
	 * 
	 * @param SensorTypeStr
	 *            需要删除的传感器的类型字段
	 * @param username
	 *            传感器类型所有者的名称
	 * @return 返回是否删除成功，true成功，false 不成功
	 */
	public static Boolean DeleteSensorTypeInfo(String SensorTypeStr,
			String username) throws Exception {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();

			int num = session.createQuery(
					"Delete from SensorTypeRule where sensortypename ='"
							+ SensorTypeStr + "' and  maker ='" + username
							+ "'").executeUpdate();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (num > 0) {
				return true;
			} else {
				return false;
			}
		} catch (HibernateException e) {
			GetSessionUtil.CloseSessionInstance(session);
			e.printStackTrace();
			throw new java.lang.Exception("传感器类型规则表删除失败!");
		}
	}

	/**
	 * 将SensorTypeMarkerClient中的信息转换到SensorTypeRule中
	 * 
	 * @param sensorTypeMarkerClient
	 * @param username
	 * @return
	 */
	public static SensorTypeRule TranslateSensorTypeMarkerClientToSensorTypeRule(
			SensorTypeMarkerClient sensorTypeMarkerClient, String username) {
		SensorTypeRule str = new SensorTypeRule();
		str.setSensortypename(sensorTypeMarkerClient.getSensortype());
		str.setMaker(username);
		str.setSaveziduan(sensorTypeMarkerClient.getSaveziduan());
		List<String> sensorkeywords = new ArrayList<String>();
		String sensorkeyword = sensorTypeMarkerClient.getSensorkeyword();
		String[] results = null;
		if (sensorkeyword != null && sensorkeyword.length() != 0) {
			results = sensorkeyword.split(",");
		}
		if (results != null) {
			for (String s : results) {
				sensorkeywords.add(s);
			}
		}
		String sensorkeywordStr = "";
		if (sensorkeywords != null) {
			for (String sensorkeyword1 : sensorkeywords) {
				sensorkeywordStr = sensorkeywordStr
						.concat(sensorkeyword1 + ",");
			}
			sensorkeywordStr = sensorkeywordStr.substring(0, sensorkeywordStr
					.length() - 1);
		}
		str.setSensorkeywords(sensorkeywordStr);
		str.setSensortypechinesename(sensorTypeMarkerClient
				.getSensorchinesetype());
		return str;
	}

	/**
	 * 将sensortypemarkerclient转换为SensorTypeRule实例
	 * 
	 * @param sensortypemarkers
	 * @param username
	 * @return 返回sensortypemarker实例
	 */
	public static List<SensorTypeRule> TranslateSensorTypeMarkerClientToSensorTypeRules(
			List<SensorTypeMarkerClient> sensortypemarkers, String username) {
		List<SensorTypeRule> strSensorTypeRules = new ArrayList<SensorTypeRule>();
		for (SensorTypeMarkerClient stmc : sensortypemarkers) {
			strSensorTypeRules
					.add(TranslateSensorTypeMarkerClientToSensorTypeRule(stmc,
							username));
		}
		return strSensorTypeRules;
	}

	/**
	 * 将sensortyperule转换为sensortypemarker实例
	 * 
	 * @param str
	 * @return 返回sensortypemarker实例
	 */
	public static SensorTypeMarker TranslateSensorTypeRuleToSensorTypeMarker(
			SensorTypeRule str) {
		SensorTypeMarker stMarker = new SensorTypeMarker();
		stMarker.setMaker(str.getMaker());
		stMarker.setSaveziduan(str.getSaveziduan());
		stMarker.setSensorchinesetype(str.getSensortypechinesename());
		List<String> sensorkeywords = new ArrayList<String>();
		if (str.getSensorkeywords() != null
				&& str.getSensorkeywords().length() > 0) {
			String[] keywords = str.getSensorkeywords().split(",");
			for (String keyword : keywords) {
				sensorkeywords.add(keyword);
			}
		}
		stMarker.setSensorkeyword(sensorkeywords);
		stMarker.setSensortype(str.getSensortypename());
		return stMarker;
	}

	/**
	 * 将多个sensortyperule转换为sensortypemarker
	 * 
	 * @param strs
	 * @return
	 */
	public static List<SensorTypeMarker> TranslateSensorTypeRuleToSensorTypeMarkers(
			List<SensorTypeRule> strs) {
		List<SensorTypeMarker> strSensorTypeMarkers = new ArrayList<SensorTypeMarker>();
		for (SensorTypeRule str : strs) {
			strSensorTypeMarkers
					.add(TranslateSensorTypeRuleToSensorTypeMarker(str));
		}
		return strSensorTypeMarkers;
	}

	/**
	 * 删除全部的指定用户的传感器类型规则
	 * 
	 * @param username
	 *            指定的用户
	 * @return
	 */
	public static Boolean DeleteAllSensorTypeRules(String username) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		int num = session.createQuery(
				"delete from SensorTypeRule where  maker ='" + username + "'")
				.executeUpdate();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取全部的传感器的信息
	 * 
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SensorTypeRule> GetAllSensorTypes(String username) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		List<SensorTypeRule> strs;
		try {
			session.beginTransaction().begin();
			strs = session.createQuery(
					"from SensorTypeRule where  maker ='" + username + "'")
					.list();

			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return strs;
		} catch (HibernateException e) {
			e.printStackTrace();
			GetSessionUtil.CloseSessionInstance(session);
			return null;
		}

	}
}
