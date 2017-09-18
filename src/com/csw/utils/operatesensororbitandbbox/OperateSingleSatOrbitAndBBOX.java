package com.csw.utils.operatesensororbitandbbox;

import java.util.List;
import org.hibernate.Session;
import com.csw.model.ebXMLModel.SatOrbitJiSuanType;
import com.csw.utils.GetSessionUtil;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-9-18 ����03:15:11
 */
public class OperateSingleSatOrbitAndBBOX {
	/**
	 * ���洫������׼��������ƽ̨�Ĵ�������Ϣ
	 * 
	 * @param sojst
	 * @return
	 */
	public static Boolean SaveSatWithStandardNameAndPlatform(
			SatOrbitJiSuanType sojst) {
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			session.save(sojst);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ��ȡ���еı���İ����˴���������׼������ƽ̨�Ĵ�������Ϣ
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SatOrbitJiSuanType> getAllJiSuanTypes() {

		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List<SatOrbitJiSuanType> sos = session.createQuery(
					"from SatOrbitJiSuanType").list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return sos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * ����
	 * 
	 * @param sojst
	 * @return
	 */
	public static Boolean UpdateSatWithStandardNameAndPlatform(
			SatOrbitJiSuanType sojst) {
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			session.update(sojst);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ɾ��
	 * 
	 * @param sojst
	 * @return
	 */
	public static Boolean DeleteSatWithStandardNameAndPlatform(
			SatOrbitJiSuanType sojst) {
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			session.delete(sojst);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ��ȡ
	 * 
	 * @param sojst
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static SatOrbitJiSuanType ReadSatWithStandardNameAndPlatform(
			String satid) {
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List<SatOrbitJiSuanType> sojst = session.createQuery(
					" from  SatOrbitJiSuanType where satid='" + satid + "'")
					.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (sojst != null && sojst.size() != 0) {
				return sojst.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
