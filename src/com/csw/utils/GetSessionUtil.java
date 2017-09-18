package com.csw.utils;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;
import com.csw.model.ebXMLModel.SensorBasciForOracleType;
import com.csw.utils.custometypes.PlatformAndSensorIdsForOracleType;
import com.csw.utils.custometypes.SensorIntendAppForOracleType;
import com.csw.utils.custometypes.SensorOrganAndShareLevelForOracleType;
import com.csw.utils.custometypes.SensorOrganForOracleType;
import com.csw.utils.custometypes.SensorShareForOracleLevel;

/**
 * 
 *项目名称:CxfMyCsw 类名称GetSessionUtil 类描述： 创建人:Administrator 创建时间: 2013-4-15
 * 上午10:51:07 修改人Administrator 修改时间:2013-4-15上午10:51:07 修改备注 :
 * 
 * @version2013-4-15
 */
public class GetSessionUtil {
	public final static String SENSORTYPE = "sensor";
	public final static String OMTYPE = "om";
	public final static String EVENTTYPE = "event";
	private final static String[] types = { "sensor", "om", "event" };

	/**
	 * 获取sessionFactory
	 * 
	 * @return
	 */
	public static SessionFactory getSessionFactoryInstance(
			String configurationfilename) {
		return getConfiguration(configurationfilename).buildSessionFactory();
	}

	/**
	 * 通过原生态的sql语句进行查询
	 * 
	 * @param sqlStr
	 *            查询的sql语句
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List getSelectQueryInfoByNativeSQL(String sqlStr) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		List list = session.createSQLQuery(sqlStr).list();
		session.beginTransaction().commit();
		CloseSessionInstance(session);
		return list;
	}

	/**
	 * 获取所有的传感器的组织单位信息和传感器的公开信息
	 * 
	 * @param queryStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SensorOrganAndShareLevelForOracleType> getSensorOrganAndShareLevels(
			String queryStr) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		List<SensorOrganAndShareLevelForOracleType> list = session
				.createSQLQuery(queryStr)
				.setResultTransformer(
						Transformers
								.aliasToBean(SensorOrganAndShareLevelForOracleType.class))
				.list();
		session.beginTransaction().commit();
		CloseSessionInstance(session);
		return list;
	}

	/**
	 * 获取全部的传感器的组织单位信息
	 * 
	 * @param queryStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SensorOrganForOracleType> getSensorOrganInfo(
			String queryStr) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		List<SensorOrganForOracleType> list = session.createSQLQuery(queryStr)
				.setResultTransformer(
						Transformers
								.aliasToBean(SensorOrganForOracleType.class))
				.list();
		session.beginTransaction().commit();
		CloseSessionInstance(session);
		return list;
	}

	/**
	 * 获取所有的平台站点和传感器标识符
	 * 
	 * @param queryStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<PlatformAndSensorIdsForOracleType> GetAllPlatformAndSensorIds(
			String queryStr) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		List<PlatformAndSensorIdsForOracleType> list = session.createSQLQuery(
				queryStr).setResultTransformer(
				Transformers
						.aliasToBean(PlatformAndSensorIdsForOracleType.class))
				.list();
		List<PlatformAndSensorIdsForOracleType> results = new ArrayList<PlatformAndSensorIdsForOracleType>();
		Boolean bol = false;
		for (PlatformAndSensorIdsForOracleType pa : list) {
			bol = false;
			// 已经存在
			for (PlatformAndSensorIdsForOracleType p : results) {
				if (pa.getPLATFORMID().equals(p.getPLATFORMID())) {
					if (p.getSUBSENSORS() == null) {
						p.setSUBSENSORS(pa.getSUBSENSORS());
					} else {
						p.setSUBSENSORS(p.getSUBSENSORS() + ","
								+ pa.getSUBSENSORS());
					}
					bol = true;
					break;
				}
			}
			// 并不存在，则导入进来
			if (!bol) {
				results.add(pa);
			}
		}
		session.beginTransaction().commit();
		CloseSessionInstance(session);
		return results;
	}

	/**
	 * 获取传感器指定的基本信息
	 * 
	 * @param sqlStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SensorBasciForOracleType> getSensorBasicInfo(
			String sqlStr) {
		// System.out.println(sqlStr);
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		// System.out.println(sqlStr);
		List<SensorBasciForOracleType> list = session.createSQLQuery(sqlStr)
				.setResultTransformer(
						Transformers
								.aliasToBean(SensorBasciForOracleType.class))
				.list();
		session.beginTransaction().commit();
		CloseSessionInstance(session);
		return list;
	}

	/**
	 * 获取所有传感器的公开级别
	 * 
	 * @param sqlStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SensorShareForOracleLevel> GetAllSensorShareLevelInfo(
			String sqlStr) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		List<SensorShareForOracleLevel> list = session.createSQLQuery(sqlStr)
				.setResultTransformer(
						Transformers
								.aliasToBean(SensorShareForOracleLevel.class))
				.list();
		session.beginTransaction().commit();
		CloseSessionInstance(session);
		return list;
	}

	/**
	 * 获取所有传感器的预期应用
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SensorIntendAppForOracleType> GetAllSensorIntendInfo(
			String sqlStr) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		List<SensorIntendAppForOracleType> list = session
				.createSQLQuery(sqlStr)
				.setResultTransformer(
						Transformers
								.aliasToBean(SensorIntendAppForOracleType.class))
				.list();
		session.beginTransaction().commit();
		CloseSessionInstance(session);
		return list;
	}

	/**
	 * 根据查询的条件获取全部的信息
	 * 
	 * @param sqlStr
	 *            比如select 开后的
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List GetSelectQueryInfo(String sqlStr) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		List list = session.createQuery(sqlStr).list();
		session.beginTransaction().commit();
		CloseSessionInstance(session);
		return list;
	}

	/**
	 * 根据删除的条件进行数据库的操作
	 * 
	 * @param sqlStr
	 *            如update，delete等不需要返回集合信息的操作
	 * @return
	 */
	public static boolean GetDeleteQueryInfo(String sqlStr) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		int num = session.createQuery(sqlStr).executeUpdate();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取指定的configuration.xml的sessionFactory
	 */
	public static Configuration getConfiguration(String configurationfilename) {
		if (configurationfilename == null
				|| configurationfilename.trim().equals("")) {
			return new Configuration().configure();
		}
		return new Configuration().configure(configurationfilename);
	}

	/**
	 * 获取session实例
	 * 
	 * @param hibernatetype
	 *            需要获取何种的配置文件，默认的有sensor，om，event
	 * @return
	 */
	public static Session GetSessionInstance(String hibernatetype) {
		return HibernateSessionFactory.getSession();
	}

	/**
	 * 这里是用于在进行保存ebrim的时候调用的session生成
	 * 
	 * @param hibernatetype
	 * @return
	 */
	public Session GetSessionInstanceByOneTime(String hibernatetype) {
		if (hibernatetype.equals(types[0])) {
			return getSessionFactoryInstance(null).openSession();
		} else if (hibernatetype.equals(types[1])) {
			return getSessionFactoryInstance("hibernate_om.cfg.xml")
					.openSession();
		} else if (hibernatetype.equals(types[2])) {
			return getSessionFactoryInstance("hibernate_event.cfg.xml")
					.openSession();
		} else {
			return getSessionFactoryInstance(null).openSession();
		}

	}

	/**
	 * 这是调用session来保存ebirm的方式的session的关闭的方式
	 * 
	 * @param session
	 */
	public static void CloseSessionInstanceByOneTime(Session session) {
		if (session != null) {
			if (session.isConnected()) {
				session.disconnect();
				session.flush();
				session.close();
			}
			if (session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	/**
	 * 关闭session
	 * 
	 * @param session
	 */
	public static void CloseSessionInstance(Session session) {
		if (session != null) {
			if (session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}
}
