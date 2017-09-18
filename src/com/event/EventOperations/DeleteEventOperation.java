package com.event.EventOperations;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.csw.exceptions.DBObjectQueryException;
import com.csw.exceptions.DBObjectUpdateException;
import com.csw.exceptions.DBObjectDeleteException;
import com.csw.utils.GetSessionUtil;
import com.event.EventOperations.utils.QueryFieldEnum;

public class DeleteEventOperation {
	public static void main(String[] args) throws DBObjectDeleteException,
			DBObjectQueryException, DBObjectUpdateException {
		deleteEventMLClass("urn:ogc:feature:event:WuHanUrbanFlooding20130705");
	}

	/**
	 * ɾ��ָ����ע����¼�ģ�͵Ķ���(ȫ��ɾ����
	 * 
	 * @param eventid
	 *            �¼�ģ�Ͷ���ı�ʶ��
	 * @return true�ɹ�ɾ����false ɾ��ʧ��
	 * @throws DBObjectDeleteException
	 * @throws DBObjectQueryException
	 * @throws DBObjectUpdateException
	 */
	public static Boolean deleteEventMLClass(String eventid)
			throws DBObjectDeleteException, DBObjectQueryException,
			DBObjectUpdateException {
		deleteEventIdentificationInfo(eventid);
		deleteEventClassificationInfo(eventid);
		deleteEventSpaceTime(eventid);
		deleteEventPrecationInfo(eventid);
		deleteEventPreparationInfo(eventid);
		deleteEventRecoveryInfo(eventid);
		deleteEventDenagedRoadInfo(eventid);
		deleteEventResponseInfo(eventid);
		deleteEventAdministrationInfo(eventid);
		return true;
	}

	/**
	 * ɾ��ȫ��������Ա��Ϣ
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectDeleteException
	 */
	public static Boolean deleteEventAdministrationInfo(String eventid)
			throws DBObjectDeleteException {
		return deleteObject("EventAdministrationInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}

	/**
	 * ɾ��ָ��event�ķ�����Ϣ
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectDeleteException
	 */
	public static Boolean deleteEventClassificationInfo(String eventid)
			throws DBObjectDeleteException {
		return deleteObject("EventClassificationJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}

	/**
	 * ɾ��ָ������ٵ����ķ������Ϣ
	 * 
	 * @return
	 * @throws DBObjectDeleteException
	 */
	public static Boolean deleteEventDenagedRoadInfo(String eventid)
			throws DBObjectDeleteException {
		return deleteObject("EventDenagedRoadJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}

	/**
	 * ɾ���¼���ʶ����Ϣ
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectDeleteException
	 */
	public static Boolean deleteEventIdentificationInfo(String eventid)
			throws DBObjectDeleteException {
		return deleteObject("EventIdentificationJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}

	/**
	 * ɾ���¼��е�Ԥ���׶ε���Ϣ
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectDeleteException
	 */
	public static Boolean deleteEventPrecationInfo(String eventid)
			throws DBObjectDeleteException {
		return deleteObject("EventPrecationInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}

	/**
	 * ɾ���¼��е�׼���׶ε���Ϣ
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectDeleteException
	 */
	public static Boolean deleteEventPreparationInfo(String eventid)
			throws DBObjectDeleteException {
		return deleteObject("EventPreparationInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}

	/**
	 * ɾ��ʵ���е�Ԥ�ڵ���Ϣ
	 * 
	 * @param predicationid
	 * @return
	 * @throws DBObjectDeleteException
	 */
	public static Boolean deleteEventPredictionInfo(Integer predicationid)
			throws DBObjectDeleteException {
		return deleteObject("EventPredicationInfoJBean", "predicationid",
				predicationid, QueryFieldEnum.NUMBER);
	}

	/**
	 * ɾ���¼��еĻָ��׶ε���Ϣ
	 * 
	 * @param eventid
	 *            �¼�����Ϣ
	 * @return
	 * @throws DBObjectDeleteException
	 */
	public static Boolean deleteEventRecoveryInfo(String eventid)
			throws DBObjectDeleteException {
		return deleteObject("EventRecoveryInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}

	/**
	 * ɾ���¼��е���Ӧ�׶ε���Ϣ
	 * 
	 * @param eventid
	 *            �¼�����Ϣ
	 * @return
	 * @throws DBObjectDeleteException
	 */
	public static Boolean deleteEventResponseInfo(String eventid)
			throws DBObjectDeleteException {
		return deleteObject("EventResponseInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}

	public static Boolean deleteEventSpaceTime(String eventid)
			throws DBObjectDeleteException {
		return deleteObject("EventSpaceTimeJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}

	/**
	 * ɾ��������ʵ��������Ϣ
	 * 
	 * @param entryname
	 *            ʵ���������
	 * @param fieldname
	 *            ����������
	 * @param identifiervalue
	 *            �����Ķ����ֵ
	 * @param qfe
	 *            ��������ֵ�����ͣ�string��number������date��
	 * @return
	 * @throws DBObjectDeleteException
	 */
	private static Boolean deleteObject(String entryname, String fieldname,
			Object identifiervalue, QueryFieldEnum qfe)
			throws DBObjectDeleteException {
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			session.beginTransaction().commit();
			String queryStr = "";
			if (qfe.equals(QueryFieldEnum.STRING)) {
				queryStr = "delete from " + entryname + " where " + fieldname
						+ "='" + identifiervalue.toString() + "'";
			} else {
				queryStr = "delete from " + entryname + " where " + fieldname
						+ "=" + identifiervalue.toString();
			}
			Query query = session.createQuery(queryStr).setCacheable(true);
			Integer integer = query.executeUpdate();
			System.out.println(integer);
			GetSessionUtil.CloseSessionInstance(session);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DBObjectDeleteException(e.getMessage());
		}
		return true;
	}
}
