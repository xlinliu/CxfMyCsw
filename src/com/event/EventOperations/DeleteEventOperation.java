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
	 * 删除指定的注册的事件模型的对象(全部删除）
	 * 
	 * @param eventid
	 *            事件模型对象的标识符
	 * @return true成功删除，false 删除失败
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
	 * 删除全部管理人员信息
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
	 * 删除指定event的分类信息
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
	 * 删除指定的损毁导读的分类的信息
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
	 * 删除事件标识符信息
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
	 * 删除事件中的预警阶段的信息
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
	 * 删除事件中的准备阶段的信息
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
	 * 删除实践中的预期的信息
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
	 * 删除事件中的恢复阶段的信息
	 * 
	 * @param eventid
	 *            事件的信息
	 * @return
	 * @throws DBObjectDeleteException
	 */
	public static Boolean deleteEventRecoveryInfo(String eventid)
			throws DBObjectDeleteException {
		return deleteObject("EventRecoveryInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}

	/**
	 * 删除事件中的响应阶段的信息
	 * 
	 * @param eventid
	 *            事件的信息
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
	 * 删除单独的实体对象的信息
	 * 
	 * @param entryname
	 *            实体对象名称
	 * @param fieldname
	 *            主键的名称
	 * @param identifiervalue
	 *            主键的对象的值
	 * @param qfe
	 *            主键对象值的类型，string，number，或者date等
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
