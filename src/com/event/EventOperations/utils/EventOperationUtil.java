package com.event.EventOperations.utils;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.csw.exceptions.DBObjectQueryException;
import com.csw.exceptions.DBObjectSaveException;
import com.csw.exceptions.EventExistsException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.PrecationExistException;
import com.csw.exceptions.PreparationExistException;
import com.csw.exceptions.RecoveryExistException;
import com.csw.exceptions.ResponseExistException;
import com.csw.utils.GetSessionUtil;
import com.event.EventOperations.QueryEventOperation;
import com.event.InnerEntities.EventMLClass;
import com.event.InnerEntities.EventMLDescriptionInfo;
import com.event.JavaBeans.EventIdentificationJBean;

public class EventOperationUtil {
	/**
	 * �ж��Ƿ���Ҫ�����¼�ģ��
	 * 
	 * @param eml
	 *            �¼�ģ��Java����
	 * @return
	 * @throws EventExistsException
	 * @throws PrecationExistException
	 * @throws PreparationExistException
	 * @throws ResponseExistException
	 * @throws RecoveryExistException
	 * @throws DBObjectSaveException
	 * @throws DBObjectQueryException
	 */
	public static String CheckSaveEventMLFun(EventMLClass eml)
			throws PrecationExistException, PreparationExistException,
			ResponseExistException, RecoveryExistException,
			DBObjectQueryException {
		String result = "0";
		String eventid = eml.getEsd().getEif().getEventid();
		// �Ƿ��Ѿ�ע���˸��¼������ǲ��Եģ�
		EventIdentificationJBean emc = QueryEventOperation
				.getEventIdentificationById(eventid);
		if (emc != null) {
			result = "1";
		} else {
			return "0";
		}
		// ��ȡ��ע����¼������׶�
		String phases = emc.getEventPhases();
		if (eml.getEmdi().getPrecationInfo() != null) {
			if (phases.charAt(0) == '1') {
				throw new PrecationExistException("���Ϊ:" + eventid
						+ "���¼��Ѿ�����precation������Ϣ");
			}
		}
		if (eml.getEmdi().getPreparationInfo() != null) {
			if (phases.charAt(1) == '1') {
				throw new PreparationExistException("���Ϊ:" + eventid
						+ "���¼��Ѿ�����preparation������Ϣ ");
			}
		}
		if (eml.getEmdi().getResponseInfo() != null) {
			if (phases.charAt(2) == '1') {
				throw new ResponseExistException("���Ϊ:" + eventid
						+ "���¼��Ѿ�����response������Ϣ");
			}
		}
		if (eml.getEmdi().getRecoveryInfo() != null) {
			if (phases.charAt(3) == '1') {
				throw new RecoveryExistException("���Ϊ:" + eventid
						+ "���¼��Ѿ�����recovery������Ϣ");
			}
		}
		return result;
	}

	/**
	 * ������������ݿ��У����������ɵ�����ֵ
	 * 
	 * @param obj
	 * @return
	 * @throws DBObjectSaveException
	 */
	public static Serializable SaveObjectToDB(Object obj)
			throws DBObjectSaveException {
		System.out.println("�������Ϊ:" + obj);
		Serializable resultObject;
		Session session = null;
		try {
			session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			resultObject = session.save(obj);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DBObjectSaveException("����������!");
		} finally {
			if (session != null && session.isOpen()) {
				GetSessionUtil.CloseSessionInstance(session);
			}
		}
		return resultObject;
	}

	/**
	 * ��ȡ�¼�������Ϣ�������������ͣ���precation��preparation��response��recovery��
	 * 
	 * @param emi
	 *            �洢�˸����¼�������Ϣ��ʵ�����
	 * @return ���ؾ�����Ӧ������Ϣ���ַ����б�
	 * @throws NullZeroException
	 */
	public static String getEventDescriptionType(EventMLDescriptionInfo emdi)
			throws NullZeroException {
		if (emdi == null) {
			throw new NullZeroException("����emdi����Ϊ��!");
		}
		String result = "";
		if (emdi.getPrecationInfo() == null) {
			result += "0";
		} else {
			result += "1";
		}
		if (emdi.getPreparationInfo() == null) {
			result += "0";
		} else {
			result += "1";
		}
		if (emdi.getResponseInfo() == null) {
			result += "0";
		} else {
			result += "1";
		}
		if (emdi.getRecoveryInfo() == null) {
			result += "0";
		} else {
			result += "1";
		}
		return result;
	}
}
