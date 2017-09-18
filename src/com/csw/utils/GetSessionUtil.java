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
 *��Ŀ����:CxfMyCsw ������GetSessionUtil �������� ������:Administrator ����ʱ��: 2013-4-15
 * ����10:51:07 �޸���Administrator �޸�ʱ��:2013-4-15����10:51:07 �޸ı�ע :
 * 
 * @version2013-4-15
 */
public class GetSessionUtil {
	public final static String SENSORTYPE = "sensor";
	public final static String OMTYPE = "om";
	public final static String EVENTTYPE = "event";
	private final static String[] types = { "sensor", "om", "event" };

	/**
	 * ��ȡsessionFactory
	 * 
	 * @return
	 */
	public static SessionFactory getSessionFactoryInstance(
			String configurationfilename) {
		return getConfiguration(configurationfilename).buildSessionFactory();
	}

	/**
	 * ͨ��ԭ��̬��sql�����в�ѯ
	 * 
	 * @param sqlStr
	 *            ��ѯ��sql���
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
	 * ��ȡ���еĴ���������֯��λ��Ϣ�ʹ������Ĺ�����Ϣ
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
	 * ��ȡȫ���Ĵ���������֯��λ��Ϣ
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
	 * ��ȡ���е�ƽ̨վ��ʹ�������ʶ��
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
			// �Ѿ�����
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
			// �������ڣ��������
			if (!bol) {
				results.add(pa);
			}
		}
		session.beginTransaction().commit();
		CloseSessionInstance(session);
		return results;
	}

	/**
	 * ��ȡ������ָ���Ļ�����Ϣ
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
	 * ��ȡ���д������Ĺ�������
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
	 * ��ȡ���д�������Ԥ��Ӧ��
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
	 * ���ݲ�ѯ��������ȡȫ������Ϣ
	 * 
	 * @param sqlStr
	 *            ����select �����
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
	 * ����ɾ���������������ݿ�Ĳ���
	 * 
	 * @param sqlStr
	 *            ��update��delete�Ȳ���Ҫ���ؼ�����Ϣ�Ĳ���
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
	 * ��ȡָ����configuration.xml��sessionFactory
	 */
	public static Configuration getConfiguration(String configurationfilename) {
		if (configurationfilename == null
				|| configurationfilename.trim().equals("")) {
			return new Configuration().configure();
		}
		return new Configuration().configure(configurationfilename);
	}

	/**
	 * ��ȡsessionʵ��
	 * 
	 * @param hibernatetype
	 *            ��Ҫ��ȡ���ֵ������ļ���Ĭ�ϵ���sensor��om��event
	 * @return
	 */
	public static Session GetSessionInstance(String hibernatetype) {
		return HibernateSessionFactory.getSession();
	}

	/**
	 * �����������ڽ��б���ebrim��ʱ����õ�session����
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
	 * ���ǵ���session������ebirm�ķ�ʽ��session�Ĺرյķ�ʽ
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
	 * �ر�session
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
