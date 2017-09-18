package com.event.EventOperations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.csw.exceptions.DBObjectQueryException;
import com.csw.exceptions.DBObjectSaveException;
import com.csw.exceptions.EventBBOXTypeNotFormatException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.TimePeroidNotFormatException;
import com.csw.utils.GetSessionUtil;
import com.event.EventOperations.utils.EventSpaceCompareEnum;
import com.event.EventOperations.utils.EventTimeCompareEnum;
import com.event.EventOperations.utils.QueryFieldEnum;
import com.event.InnerEntities.AlterInfo;
import com.event.InnerEntities.ContactResponsibleParty;
import com.event.InnerEntities.EventBBOXType;
import com.event.InnerEntities.EventCasualty;
import com.event.InnerEntities.EventEconomicLoss;
import com.event.InnerEntities.EventIdenfiticationInfo;
import com.event.InnerEntities.EventOtherInfluence;
import com.event.InnerEntities.PrecationInfo;
import com.event.InnerEntities.ServiceInfo;
import com.event.InnerEntities.TimePeroid;
import com.event.JavaBeans.EventAdministrationInfoJBean;
import com.event.JavaBeans.EventClassificationJBean;
import com.event.JavaBeans.EventDenagedRoadJBean;
import com.event.JavaBeans.EventIdentificationJBean;
import com.event.JavaBeans.EventPrecationInfoJBean;
import com.event.JavaBeans.EventPredicationInfoJBean;
import com.event.JavaBeans.EventPreparationInfoJBean;
import com.event.JavaBeans.EventRecoveryInfoJBean;
import com.event.JavaBeans.EventResponseInfoJBean;
import com.event.JavaBeans.EventSpaceTimeJBean;
import com.event.commonutils.EventElementCheckUtil;

public class QueryEventOperation {
	public static void main(String[] args) throws DBObjectQueryException,
			NullZeroException, TimePeroidNotFormatException,
			EventBBOXTypeNotFormatException {
		// TimePeroid tpPeroid = new TimePeroid(new Date(2001, 1, 1, 11, 11,
		// 11),
		// new Date(2021, 1, 1, 11, 11, 11));
		// EventBBOXType ebbt = new EventBBOXType();
		// ebbt.setXhigh(180);
		// ebbt.setXlow(-180);
		// ebbt.setYhigh(90);
		// ebbt.setYlow(-90);
		// System.out.println(getEventIdBySpaceBBox(ebbt,
		// EventSpaceCompareEnum.CONTAIN).size());
		// System.out.println(getEventIdBySpaceTime(ebbt,
		// EventSpaceCompareEnum.CONTAIN, tpPeroid,
		// EventTimeCompareEnum.CONTAIN).get(0));
		// System.out.println(getEventIdByServiceName("Flood_SES").get(0));
		// System.out.println(getEventIdByServiceType("SES").get(0));
		// System.out.println(getEventIdByOragnName("武汉市水务局").get(0));
		// System.out.println(getAllEventServiceType().get(0));
		// System.out.println(getEventIdByOraganResponser("左绍斌").get(0));
		// System.out.println(getAllEventClassificationeventPattern().get(0));
		// System.out.println(getEventAdministrationInfo(
		// "urn:ogc:feature:event:WuHanUrbanFlooding20130705")
		// .getAdministrationid());
		// System.out.println(getEventPrecautionObservations(
		// "urn:ogc:feature:event:WuHanUrbanFlooding20130705")
		// .getGroundData());
		System.out
				.println(EventIsExist("urn:ogc:feature:event:WuHanUrbanFlooding20130705"));
		// System.out.println(getPreparationAlert(
		// "urn:ogc:feature:event:WuHanUrbanFlooding20130705")
		// .getAlertArea().getAdministrativeArea());
	}

	/**
	 * 获取事件描述模型中包含的事件阶段集
	 * 
	 * @param eventid
	 *            事件模型的标识符
	 * @return 返回事件中所有的事件阶段,如果不存在返回 ""
	 * @throws DBObjectQueryException
	 */
	public static String getEventPhases(String eventid)
			throws DBObjectQueryException {
		EventIdentificationJBean eij = getEventIdentificationById(eventid);
		if (eij != null) {
			return eij.getEventPhases();
		}
		return "";
	}

	/**
	 * 验证指定编号的事件是否存在
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static Boolean EventIsExist(String eventid)
			throws DBObjectQueryException {
		return ExistObjectByIdentifier("EventIdentificationJBean", "eventid",
				eventid, QueryFieldEnum.STRING);
	}

	/**
	 * 获取指定事件的受损道路
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 */
	@SuppressWarnings("unchecked")
	public static Set<EventDenagedRoadJBean> getEventDenagedRoadJBeans(
			String eventid) throws DBObjectQueryException {
		Set<EventDenagedRoadJBean> resutlsBeans = new HashSet<EventDenagedRoadJBean>();
		Session session = null;
		try {
			session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List<EventDenagedRoadJBean> temp = session.createQuery(
					"from EventDenagedRoadJBean where eventid='" + eventid
							+ "'").setCacheable(true).list();
			if (temp != null) {
				for (EventDenagedRoadJBean edrj : temp) {
					resutlsBeans.add(edrj);
				}
			}
			session.beginTransaction().commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DBObjectQueryException(e.getMessage());
		} finally {
			if (session != null && !session.isOpen()) {
				GetSessionUtil.CloseSessionInstance(session);
			}
		}
		return resutlsBeans;
	}

	/**
	 * 获取事件响应阶段的信息
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static EventResponseInfoJBean getResponseInfo(String eventid)
			throws DBObjectQueryException {
		EventResponseInfoJBean epij = (EventResponseInfoJBean) getObjectByIdentifier(
				"EventResponseInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
		if (epij != null) {
			return epij;
		}
		return null;
	}

	/**
	 * 获取事件在recovery后的其他影响情况
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static EventOtherInfluence getRecoveryOtherInfluenceInfo(
			String eventid) throws DBObjectQueryException {

		EventRecoveryInfoJBean epij = (EventRecoveryInfoJBean) getObjectByIdentifier(
				"EventRecoveryInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
		if (epij != null) {
			return epij.getOif();
		}
		return null;
	}

	/**
	 * 获取事件在recovery后的经济损失情况
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static EventEconomicLoss getRecoveryEconomicLossInfo(String eventid)
			throws DBObjectQueryException {
		EventRecoveryInfoJBean epij = (EventRecoveryInfoJBean) getObjectByIdentifier(
				"EventRecoveryInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
		if (epij != null) {
			return epij.getEel();
		}
		return null;
	}

	/**
	 * 获取事件在recovery后的人员伤亡情况
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static EventCasualty getRecoveryCasulatyInfo(String eventid)
			throws DBObjectQueryException {

		EventRecoveryInfoJBean epij = (EventRecoveryInfoJBean) getObjectByIdentifier(
				"EventRecoveryInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
		if (epij != null) {
			return epij.getEc();
		}
		return null;

	}

	/**
	 * 获取事件可能发生的地点与事件信息
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static EventPredicationInfoJBean getPreparationPredictionInfo(
			String eventid) throws DBObjectQueryException {
		EventPreparationInfoJBean epij = (EventPreparationInfoJBean) getObjectByIdentifier(
				"EventPreparationInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
		if (epij != null) {
			return epij.getPi();
		}
		return null;
	}

	/**
	 * 获取事件预警信息
	 * 
	 * @param eventid
	 *            事件的标识符
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static AlterInfo getPreparationAlert(String eventid)
			throws DBObjectQueryException {
		EventPreparationInfoJBean epij = (EventPreparationInfoJBean) getObjectByIdentifier(
				"EventPreparationInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
		if (epij != null) {
			return epij.getAi();
		}
		return null;
	}

	/**
	 * 获取事件突发准备阶段的观测数据url
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static PrecationInfo EventPreparationObservations(String eventid)
			throws DBObjectQueryException {
		EventPreparationInfoJBean epij = (EventPreparationInfoJBean) getObjectByIdentifier(
				"EventPreparationInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
		if (epij != null) {
			return epij.getpInfo();
		}
		return null;
	}

	/**
	 * 获取事件预警阶段的事件观测数据url地址
	 * 
	 * @param eventid
	 * @throws DBObjectQueryException
	 */
	public static EventPrecationInfoJBean getEventPrecautionObservations(
			String eventid) throws DBObjectQueryException {
		return (EventPrecationInfoJBean) getObjectByIdentifier(
				"EventPrecationInfoJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}

	/**
	 * 获取事件的一些基本的信息，包括事件的名称，描述，上传时间等
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static EventIdenfiticationInfo getEventIdentificationInfo(
			String eventid) throws DBObjectQueryException {
		EventIdentificationJBean eaij = (EventIdentificationJBean) getObjectByIdentifier(
				"EventSpaceTimeJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
		EventIdenfiticationInfo result = new EventIdenfiticationInfo();
		result.setUserid(eaij.getUserid());
		result.setEventdesc(eaij.getEventdesc());
		result.setEventid(eventid);
		result.setEventname(eaij.getEventname());
		result.setUploadtime(eaij.getUploadtime());
		return result;
	}

	/**
	 * 根据事件的编号获取事件发生的地址
	 * 
	 * @param eventid
	 *            事件的编号
	 * @throws DBObjectQueryException
	 * @retun 返回事件发生的时间与空间信息
	 */
	@SuppressWarnings("unchecked")
	public static EventSpaceTimeJBean getEventLocation(String eventid)
			throws DBObjectQueryException {
		EventSpaceTimeJBean eaij = null;
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List<EventSpaceTimeJBean> eaijs = session.createQuery(
					"from EventSpaceTimeJBean where eventid='" + eventid + "'")
					.setCacheable(true).list();
			if (eaijs != null && eaijs.size() != 0) {
				eaij = eaijs.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DBObjectQueryException(e.getMessage());
		}
		return eaij;
	}

	/**
	 * 获取在事件时可联系的负责的第三方机构或人员
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static ContactResponsibleParty getEventCantactResponsibleParty(
			String eventid) throws DBObjectQueryException {
		ContactResponsibleParty siInfo = new ContactResponsibleParty();
		EventAdministrationInfoJBean eaij = getEventAdministrationInfo(eventid);
		if (eaij != null) {
			siInfo = eaij.getCrp();
		}
		return siInfo;
	}

	/**
	 * 获取事件服务信息
	 * 
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static ServiceInfo getEventServiceInfo(String eventid)
			throws DBObjectQueryException {
		ServiceInfo siInfo = new ServiceInfo();
		EventAdministrationInfoJBean eaij = getEventAdministrationInfo(eventid);
		if (eaij != null) {
			siInfo = eaij.getSiInfo();
		}
		return siInfo;
	}

	/**
	 * 根据事件编号获取事件管理信息
	 * 
	 * @param id
	 * @return
	 * @throws DBObjectQueryException
	 */
	@SuppressWarnings("unchecked")
	public static EventAdministrationInfoJBean getEventAdministrationInfo(
			String eventid) throws DBObjectQueryException {
		EventAdministrationInfoJBean eaij = null;
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List<EventAdministrationInfoJBean> eaijs = session.createQuery(
					"from EventAdministrationInfoJBean where eventid='"
							+ eventid + "'").setCacheable(true).list();
			if (eaijs != null && eaijs.size() != 0) {
				eaij = eaijs.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DBObjectQueryException(e.getMessage());
		}
		return eaij;
	}

	/**
	 * 获取事件的可信度用词全集
	 * 
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static List<String> getAllEventClassificationeventCertainty()
			throws DBObjectQueryException {
		List<String> reusltsList = new ArrayList<String>();
		List<Object> objects = getDistinctObjectFieldByField(
				"EventClassificationJBean", "eventCertainty");
		if (objects != null && objects.size() != 0) {
			for (Object o : objects) {
				reusltsList.add(o.toString());
			}
		}
		return reusltsList;
	}

	/**
	 * 获取事件的严重程度
	 * 
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static List<String> getAllEventClassificationeventSeverity()
			throws DBObjectQueryException {
		List<String> reusltsList = new ArrayList<String>();
		List<Object> objects = getDistinctObjectFieldByField(
				"EventClassificationJBean", "eventSeverity");
		if (objects != null && objects.size() != 0) {
			for (Object o : objects) {
				reusltsList.add(o.toString());
			}
		}
		return reusltsList;
	}

	/**
	 * 获取事件所有可继承的继承类型
	 * 
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static List<String> getAllEventClassificationeventInheritance()
			throws DBObjectQueryException {
		List<String> reusltsList = new ArrayList<String>();
		List<Object> objects = getDistinctObjectFieldByField(
				"EventClassificationJBean", "eventInheritance");
		if (objects != null && objects.size() != 0) {
			for (Object o : objects) {
				reusltsList.add(o.toString());
			}
		}
		return reusltsList;
	}

	/**
	 * 获取事件所有的分类模式
	 * 
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static List<String> getAllEventClassificationeventPattern()
			throws DBObjectQueryException {
		List<String> reusltsList = new ArrayList<String>();
		List<Object> objects = getDistinctObjectFieldByField(
				"EventClassificationJBean", "eventPattern");
		if (objects != null && objects.size() != 0) {
			for (Object o : objects) {
				reusltsList.add(o.toString());
			}
		}
		return reusltsList;
	}

	/**
	 * 获取事件所有的分类类别
	 * 
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static List<String> getAllEventClassificationCategory()
			throws DBObjectQueryException {
		List<String> reusltsList = new ArrayList<String>();
		List<Object> objects = getDistinctObjectFieldByField(
				"EventClassificationJBean", "eventCatagory");
		if (objects != null && objects.size() != 0) {
			for (Object o : objects) {
				reusltsList.add(o.toString());
			}
		}
		return reusltsList;
	}

	/**
	 * 获取所有的事件服务类型的集合
	 * 
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static List<String> getAllEventServiceType()
			throws DBObjectQueryException {
		List<String> reusltsList = new ArrayList<String>();
		List<Object> objects = getDistinctObjectFieldByField(
				"EventAdministrationInfoJBean", "siInfo.servicetype");
		if (objects != null && objects.size() != 0) {
			for (Object o : objects) {
				reusltsList.add(o.toString());
			}
		}
		return reusltsList;

	}

	/**
	 * 获取所有的事件管理单位的名称
	 * 
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static List<String> getAllEventServiceOrganName()
			throws DBObjectQueryException {
		List<String> reusltsList = new ArrayList<String>();
		List<Object> objects = getDistinctObjectFieldByField(
				"EventAdministrationInfoJBean", "siInfo.servicename");
		if (objects != null && objects.size() != 0) {
			for (Object o : objects) {
				reusltsList.add(o.toString());
			}
		}
		return reusltsList;
	}

	/**
	 * 根据事件的处理单位的负责人进行查询
	 * 
	 * @param personame
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static List<String> getEventIdByOraganResponser(String personame)
			throws DBObjectQueryException {

		List<String> reusltsList = new ArrayList<String>();
		List<Object> res = getObjectFieldByNumber(
				"EventAdministrationInfoJBean", "crp.individualName",
				personame, "eventid", QueryFieldEnum.STRING);
		if (res != null && res.size() != 0) {
			for (Object o : res) {
				reusltsList.add(o.toString());
			}
		}
		return reusltsList;
	}

	/**
	 * 根据事件管理单位的名称进行查询
	 * 
	 * @param oraganname
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static List<String> getEventIdByOragnName(String oraganname)
			throws DBObjectQueryException {
		List<String> reusltsList = new ArrayList<String>();
		List<Object> res = getObjectFieldByNumber(
				"EventAdministrationInfoJBean", "organization_name",
				oraganname, "eventid", QueryFieldEnum.STRING);
		if (res != null && res.size() != 0) {
			for (Object o : res) {
				reusltsList.add(o.toString());
			}
		}
		return reusltsList;
	}

	/**
	 * 根据事件的服务的名称获取事件的标志符的集合
	 * 
	 * @param servicename
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static List<String> getEventIdByServiceName(String servicename)
			throws DBObjectQueryException {
		List<String> reusltsList = new ArrayList<String>();
		List<Object> res = getObjectFieldByNumber(
				"EventAdministrationInfoJBean", "service_name", servicename,
				"eventid", QueryFieldEnum.STRING);
		if (res != null && res.size() != 0) {
			for (Object o : res) {
				reusltsList.add(o.toString());
			}
		}
		return reusltsList;
	}

	/**
	 * 根据事件的服务的名称获取事件的标志符的集合
	 * 
	 * @param servicename
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static List<String> getEventIdByServiceType(String servicetype)
			throws DBObjectQueryException {
		List<String> reusltsList = new ArrayList<String>();
		List<Object> res = getObjectFieldByNumber(
				"EventAdministrationInfoJBean", "service_type", servicetype,
				"eventid", QueryFieldEnum.STRING);
		if (res != null && res.size() != 0) {
			for (Object o : res) {
				reusltsList.add(o.toString());
			}
		}
		return reusltsList;
	}

	/**
	 * 根据时空范围对事件进行查询
	 * 
	 * @param ebt
	 * @param esce
	 * @param tp
	 * @param etce
	 * @return
	 * @throws EventBBOXTypeNotFormatException
	 * @throws NullZeroException
	 * @throws TimePeroidNotFormatException
	 * @throws DBObjectQueryException
	 */
	public static List<String> getEventIdBySpaceTime(EventBBOXType ebt,
			EventSpaceCompareEnum esce, TimePeroid tp, EventTimeCompareEnum etce)
			throws NullZeroException, EventBBOXTypeNotFormatException,
			TimePeroidNotFormatException, DBObjectQueryException {
		List<String> results = new ArrayList<String>();
		List<String> spaceids = getEventIdBySpaceBBox(ebt, esce);
		List<String> periodids = getEventIdByTimePeroid(tp, etce);
		for (String spaceid : spaceids) {
			for (String peroidid : periodids) {
				if (spaceid.equals(peroidid)) {
					results.add(spaceid);
				}
			}
		}
		return results;
	}

	/**
	 * 根据空间范围进行事件的查询
	 * 
	 * @param ebt
	 *            需要检索的空间过滤得包围盒
	 * @param esce
	 *            进行空间检索的过滤得条件的设置，CONTAIN 表示包围盒需要包含事件点，NOTCONTAIN 表示查询包围盒外部的事件点
	 * @return 返回符合空间查询的事件的标志符号
	 * @throws DBObjectQueryException
	 * @throws NullZeroException
	 * @throws EventBBOXTypeNotFormatException
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getEventIdBySpaceBBox(EventBBOXType ebt,
			EventSpaceCompareEnum esce) throws DBObjectQueryException,
			NullZeroException, EventBBOXTypeNotFormatException {
		List<String> eventidList = new ArrayList<String>();
		EventElementCheckUtil.CheckEventBBOXType(ebt);
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List<String> it = null;
			if (EventSpaceCompareEnum.CONTAIN.equals(esce)) {
				Query query = session
						.createQuery("select eventid from EventSpaceTimeJBean where pointx<"
								+ ebt.getXhigh()
								+ " and pointx>"
								+ ebt.getXlow()
								+ " and pointy >"
								+ ebt.getYlow()
								+ " and pointy< "
								+ ebt.getYhigh());
				it = query.setCacheable(true).list();
			} else if (EventSpaceCompareEnum.NOTCONTAIN.equals(esce)) {
				Query query = session
						.createQuery("select eventid from EventSpaceTimeJBean where (pointx>"
								+ ebt.getXhigh()
								+ " or pointx>"
								+ ebt.getXlow()
								+ ") and (pointy <"
								+ ebt.getYlow()
								+ " or  pointy>"
								+ ebt.getYhigh() + ")");
				it = query.setCacheable(true).list();
			}
			if (it != null && it.size() != 0) {
				for (String estj : it) {
					eventidList.add(estj);
				}
			}
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		} catch (HibernateException e) {
			throw new DBObjectQueryException("数据库操作错误!");
		}
		return eventidList;
	}

	/**
	 * 根据查询的时间和事件的时间是否关联而进行的处理
	 * 
	 * @param tp
	 * @param etce
	 *            进行时间比较的方式，INTERSECT
	 *            表示查询的时间与事件的时间只要存在重叠即可，CONTAINED：表示查询的时间需要能包含事件的时间
	 *            ，CONTAINED：表示查询的时间需要被包含于事件的时间
	 * @return
	 * @throws TimePeroidNotFormatException
	 * @throws NullZeroException
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getEventIdByTimePeroid(TimePeroid tp,
			EventTimeCompareEnum etce) throws DBObjectQueryException,
			NullZeroException, TimePeroidNotFormatException {
		EventElementCheckUtil.CheckTimePeroid(tp);
		List<String> eventidList = new ArrayList<String>();
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			Query query = session.createQuery("from EventSpaceTimeJBean")
					.setCacheable(true);
			List<EventSpaceTimeJBean> it = query.list();
			if (it != null && it.size() != 0) {
				if (EventTimeCompareEnum.INTERSECT.equals(etce)) {
					for (EventSpaceTimeJBean estj : it) {
						if (estj.getTp().getEndtime().before(tp.getStartime())
								|| estj.getTp().getStartime().after(
										tp.getEndtime())) {
						} else {
							eventidList.add(estj.getEventid());
						}
					}
				} else if (EventTimeCompareEnum.CONTAINED.equals(etce)) {
					for (EventSpaceTimeJBean estj : it) {
						if (estj.getTp().getEndtime().after(tp.getEndtime())
								|| estj.getTp().getStartime().before(
										tp.getStartime())) {
							eventidList.add(estj.getEventid());
						}
					}
				} else if (EventTimeCompareEnum.CONTAIN.equals(etce)) {
					for (EventSpaceTimeJBean estj : it) {
						if (tp.getEndtime().after(estj.getTp().getEndtime())
								|| tp.getStartime().before(
										estj.getTp().getStartime())) {
							eventidList.add(estj.getEventid());
						}
					}
				}
			}
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DBObjectQueryException("数据库操作错误!");
		}
		return eventidList;
	}

	/**
	 * 根据分类类别获取事件标识符
	 * 
	 * @param classificationcategory
	 * @return
	 * @throws DBObjectSaveException
	 */
	public static List<String> getEventIdByeventPattern(
			String classificationeventPattern) throws DBObjectQueryException {
		List<String> eventidList = new ArrayList<String>();
		List<Object> list = getObjectFieldByNumber("EventClassificationJBean",
				"eventPattern", classificationeventPattern, "eventid",
				QueryFieldEnum.STRING);
		if (list != null && list.size() != 0) {
			for (Object obj : list) {
				eventidList.add(obj.toString());
			}
		}
		return eventidList;
	}

	/**
	 * 根据事件可信度获取事件标识符
	 * 
	 * @param classificationeventCertainty
	 * @return
	 * @throws DBObjectSaveException
	 */
	public static List<String> getEventIdByeventCertainty(
			String classificationeventCertainty) throws DBObjectQueryException {
		List<String> eventidList = new ArrayList<String>();
		List<Object> list = getObjectFieldByNumber("EventClassificationJBean",
				"eventCertainty", classificationeventCertainty, "eventid",
				QueryFieldEnum.STRING);
		if (list != null && list.size() != 0) {
			for (Object obj : list) {
				eventidList.add(obj.toString());
			}
		}
		return eventidList;
	}

	/**
	 * 根据事件严重程度获取事件标识符
	 * 
	 * @param classificationeventSeverity
	 * @return
	 * @throws DBObjectSaveException
	 */
	public static List<String> getEventIdByeventSeverity(
			String classificationeventSeverity) throws DBObjectQueryException {
		List<String> eventidList = new ArrayList<String>();
		List<Object> list = getObjectFieldByNumber("EventClassificationJBean",
				"eventSeverity", classificationeventSeverity, "eventid",
				QueryFieldEnum.STRING);
		if (list != null && list.size() != 0) {
			for (Object obj : list) {
				eventidList.add(obj.toString());
			}
		}
		return eventidList;
	}

	/**
	 * 根据事件紧急程度获取事件标识符
	 * 
	 * @param classificationcategory
	 * @return
	 * @throws DBObjectSaveException
	 */
	public static List<String> getEventIdByeventUrgency(
			String classificationeventUrgency) throws DBObjectQueryException {
		List<String> eventidList = new ArrayList<String>();
		List<Object> list = getObjectFieldByNumber("EventClassificationJBean",
				"eventUrgency", classificationeventUrgency, "eventid",
				QueryFieldEnum.STRING);
		if (list != null && list.size() != 0) {
			for (Object obj : list) {
				eventidList.add(obj.toString());
			}
		}
		return eventidList;
	}

	/**
	 * 根据分类分类的父类名称获取事件标识符
	 * 
	 * @param classificationcategory
	 * @return
	 * @throws DBObjectSaveException
	 */
	public static List<String> getEventIdByeventInheritance(
			String eventInheritance) throws DBObjectQueryException {
		List<String> eventidList = new ArrayList<String>();
		List<Object> list = getObjectFieldByNumber("EventClassificationJBean",
				"eventInheritance", eventInheritance, "eventid",
				QueryFieldEnum.STRING);
		if (list != null && list.size() != 0) {
			for (Object obj : list) {
				eventidList.add(obj.toString());
			}
		}
		return eventidList;
	}

	/**
	 * 根据分类类别获取事件标识符
	 * 
	 * @param classificationcategory
	 * @return
	 * @throws DBObjectSaveException
	 */
	public static List<String> getEventIdByCategory(
			String classificationcategory) throws DBObjectQueryException {
		List<String> eventidList = new ArrayList<String>();
		List<Object> list = getObjectFieldByNumber("EventClassificationJBean",
				"eventCatagory", classificationcategory, "eventid",
				QueryFieldEnum.STRING);
		if (list != null && list.size() != 0) {
			for (Object obj : list) {
				eventidList.add(obj.toString());
			}
		}
		return eventidList;
	}

	/**
	 * 根据事件分类标志符获得事件分类信息
	 * 
	 * @param classificationid
	 *            查询所需的事件分类信息标识符
	 * @return 返回获取的事件分类对象信息
	 * @throws DBObjectSaveException
	 */
	public static EventClassificationJBean getECByClaId(Integer classificationid)
			throws DBObjectQueryException {
		return (EventClassificationJBean) getObjectByNumber(
				"EventClassificationJBean", "classificationid",
				classificationid, QueryFieldEnum.NUMBER);
	}

	/**
	 * 根据事件时空信息标志符获得事件时空信息
	 * 
	 * @param saveEventSpaceTime
	 * @return
	 * @throws DBObjectSaveException
	 */
	public static EventSpaceTimeJBean getESTBySTId(Integer stid)
			throws DBObjectQueryException {
		return (EventSpaceTimeJBean) getObjectByNumber("EventSpaceTimeJBean",
				"spacetimeid", stid, QueryFieldEnum.NUMBER);
	}

	/**
	 * 根据都查询的字段，返回的字段，查询的表和查询的值返回满足条件的数据库数据（这里仅仅是相当）
	 * 
	 * @param entryname
	 *            需要查询的表的名称
	 * @param queryfield
	 *            需要查询过滤的字段
	 * @param obj
	 *            queryfield过滤字段设置的值
	 * @param qfe
	 *            设置obj是否需要增加单引号或双引号
	 * @return
	 * @throws DBObjectQueryException
	 */
	@SuppressWarnings("unchecked")
	public static Object getObjectByNumber(String entryname, String queryfield,
			Object obj, QueryFieldEnum qfe) throws DBObjectQueryException {

		Object object = null;
		String stid = "";
		if (qfe.equals(QueryFieldEnum.STRING)) {
			stid = "'" + obj.toString() + "'";
		} else {
			stid = obj.toString();
		}
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			Query query = session.createQuery(
					"from " + entryname + " where " + queryfield + "=" + stid)
					.setCacheable(true);
			List it = query.list();
			if (it != null && it.size() == 1) {
				object = it.get(0);
			}
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return object;
		} catch (HibernateException e) {
			throw new DBObjectQueryException(e.getMessage());
		}
	}

	/**
	 * 根据主键获取指定实体对象中的单个对象的实体
	 * 
	 * @param entryname
	 *            需要查询的实体的名称
	 * @param fieldname
	 *            主键的字段
	 * @param identifier
	 *            主键的值
	 * @param qfe
	 *            主键的值的类型，如字符串，数值或日期等
	 * @return 返回当个的一条记录
	 * @throws DBObjectQueryException
	 */
	@SuppressWarnings("unchecked")
	public static Object getObjectByIdentifier(String entryname,
			String fieldname, Object identifier, QueryFieldEnum qfe)
			throws DBObjectQueryException {
		try {
			Object object = null;
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List<Object> eaijs = null;
			if (qfe.equals(QueryFieldEnum.STRING)) {
				eaijs = session.createQuery(
						"from " + entryname + " where " + fieldname + "='"
								+ identifier + "'").setCacheable(true).list();
			} else {
				eaijs = session.createQuery(
						"from " + entryname + " where " + fieldname + "="
								+ identifier).setCacheable(true).list();
			}
			if (eaijs != null && eaijs.size() != 0) {
				object = eaijs.get(0);
			}
			return object;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DBObjectQueryException(e.getMessage());
		}
	}

	/**
	 * 验证某记录是否存在
	 * 
	 * @param entryname
	 * @param fieldname
	 * @param identifier
	 * @param qfe
	 * @return
	 * @throws DBObjectQueryException
	 */
	public static Boolean ExistObjectByIdentifier(String entryname,
			String fieldname, Object identifier, QueryFieldEnum qfe)
			throws DBObjectQueryException {
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			Long count = 0L;
			if (qfe.equals(QueryFieldEnum.STRING)) {
				count = (Long) session.createQuery(
						"select count(*) from " + entryname + " where "
								+ fieldname + "='" + identifier + "'")
						.setCacheable(true).uniqueResult();
			} else {
				count = (Long) session.createQuery(
						"select count(*)  from " + entryname + " where "
								+ fieldname + "=" + identifier).setCacheable(
						true).uniqueResult();
			}
			System.out.println(count + ": count");
			if (count > 0L) {
				return true;
			}
			return false;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DBObjectQueryException(e.getMessage());
		}
	}

	/**
	 * 根据都查询的字段，返回的字段，查询的表和查询的值返回满足条件的数据库数据（这里仅仅是相当）
	 * 
	 * @param entryname
	 *            需要查询的表的名称
	 * @param queryfield
	 *            需要查询过滤的字段
	 * @param obj
	 *            queryfield过滤字段设置的值
	 * @param returnfield
	 *            需要返回的字段
	 * @param qfe
	 *            设置obj是否需要增加单引号或双引号
	 * @return
	 * @throws DBObjectQueryException
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getObjectFieldByNumber(String entryname,
			String queryfield, Object obj, String returnfield,
			QueryFieldEnum qfe) throws DBObjectQueryException {
		String stid = "";
		if (qfe.equals(QueryFieldEnum.STRING)) {
			stid = "'" + obj.toString() + "'";
		} else {
			stid = obj.toString();
		}
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			Query query = session.createQuery(
					"select " + returnfield + " from " + entryname + " where "
							+ queryfield + "=" + stid).setCacheable(true);
			List it = query.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return it;
		} catch (HibernateException e) {
			throw new DBObjectQueryException(e.getMessage());
		}
	}

	/**
	 * 获取指定的数据库表中的某一字段的全部的值（都是唯一的）
	 * 
	 * @param entryname
	 *            数据库表的实体对象的名称
	 * @param returnfield
	 *            需要返回的的字段的名称
	 * @return
	 * @throws DBObjectQueryException
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getDistinctObjectFieldByField(String entryname,
			String returnfield) throws DBObjectQueryException {
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			String queryStr = "select distinct " + returnfield + " from "
					+ entryname;
			Query query = session.createQuery(queryStr).setCacheable(true);
			List<Object> it = query.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return it;
		} catch (HibernateException e) {
			throw new DBObjectQueryException(e.getMessage());
		}
	}

	/**
	 * 根据事件编号获取事件标志符对象信息
	 * 
	 * @param eventid
	 *            事件标志符
	 * @return 返回事件标志符
	 * @throws DBObjectSaveException
	 */
	public static EventIdentificationJBean getEventIdentificationById(
			String eventid) throws DBObjectQueryException {
		return (EventIdentificationJBean) getObjectByNumber(
				"EventIdentificationJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}
}
