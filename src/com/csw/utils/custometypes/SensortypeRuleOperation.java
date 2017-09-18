package com.csw.utils.custometypes;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.csw.model.ebXMLModel.SensorTypeRule;
import com.csw.utils.GetSessionUtil;

/**
 *��Ŀ����:CxfMyCsw �����������õĶ�sensortyperule������д���� ������:Administrator ����ʱ��: 2013-9-17
 * ����10:34:19
 */
public class SensortypeRuleOperation {
	/**
	 * ���´��ݹ����Ĵ��������͹�����Ϣ
	 * 
	 * @param stmc
	 *            �û��ύ�Ĵ���������Ϣ
	 * @param username
	 *            �ύ���û�����
	 * @return ����true�ɹ����£�����false ʧ��
	 */
	public static Boolean UpdateSensorTYpeInfo(SensorTypeRule stmc)
			throws Exception {
		// ���³�������
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
			throw new Exception("���´��������͹�����Ϣʧ��");
		}
	}

	/**
	 * ���߻�ȡ��ָ�����������͹ؼ��������ɹ���
	 * 
	 * @param sensorkeyword
	 *            ���������͵Ĺؼ���
	 * @param username
	 *            �û�����
	 * @return �������ɵĴ����������͵Ķ���
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
			throw new Exception(" ���߻�ȡ��ָ�����������͹ؼ��������ɹ���");
		}
	}

	/**
	 * ��ȡָ���Ĵ��������͵Ĺ���
	 * 
	 * @param sensortypename
	 *            ����������Ӣ������
	 * @param username
	 *            �������Ĺ�����
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
			throw new Exception("���´��������͹�����Ϣʧ��");
		}
	}

	/**
	 * ���洫�ݹ����Ĵ���������Ϣ
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
	 * ɾ��ָ���û��е�ĳһָ���Ĵ�����������Ϣ
	 * 
	 * @param SensorTypeStr
	 *            ��Ҫɾ���Ĵ������������ֶ�
	 * @param username
	 *            ���������������ߵ�����
	 * @return �����Ƿ�ɾ���ɹ���true�ɹ���false ���ɹ�
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
			throw new java.lang.Exception("���������͹����ɾ��ʧ��!");
		}
	}

	/**
	 * ��SensorTypeMarkerClient�е���Ϣת����SensorTypeRule��
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
	 * ��sensortypemarkerclientת��ΪSensorTypeRuleʵ��
	 * 
	 * @param sensortypemarkers
	 * @param username
	 * @return ����sensortypemarkerʵ��
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
	 * ��sensortyperuleת��Ϊsensortypemarkerʵ��
	 * 
	 * @param str
	 * @return ����sensortypemarkerʵ��
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
	 * �����sensortyperuleת��Ϊsensortypemarker
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
	 * ɾ��ȫ����ָ���û��Ĵ��������͹���
	 * 
	 * @param username
	 *            ָ�����û�
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
	 * ��ȡȫ���Ĵ���������Ϣ
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
