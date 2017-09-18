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
		// System.out.println(getEventIdByOragnName("�人��ˮ���").get(0));
		// System.out.println(getAllEventServiceType().get(0));
		// System.out.println(getEventIdByOraganResponser("���ܱ�").get(0));
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
	 * ��ȡ�¼�����ģ���а������¼��׶μ�
	 * 
	 * @param eventid
	 *            �¼�ģ�͵ı�ʶ��
	 * @return �����¼������е��¼��׶�,��������ڷ��� ""
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
	 * ��ָ֤����ŵ��¼��Ƿ����
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
	 * ��ȡָ���¼��������·
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
	 * ��ȡ�¼���Ӧ�׶ε���Ϣ
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
	 * ��ȡ�¼���recovery�������Ӱ�����
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
	 * ��ȡ�¼���recovery��ľ�����ʧ���
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
	 * ��ȡ�¼���recovery�����Ա�������
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
	 * ��ȡ�¼����ܷ����ĵص����¼���Ϣ
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
	 * ��ȡ�¼�Ԥ����Ϣ
	 * 
	 * @param eventid
	 *            �¼��ı�ʶ��
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
	 * ��ȡ�¼�ͻ��׼���׶εĹ۲�����url
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
	 * ��ȡ�¼�Ԥ���׶ε��¼��۲�����url��ַ
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
	 * ��ȡ�¼���һЩ��������Ϣ�������¼������ƣ��������ϴ�ʱ���
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
	 * �����¼��ı�Ż�ȡ�¼������ĵ�ַ
	 * 
	 * @param eventid
	 *            �¼��ı��
	 * @throws DBObjectQueryException
	 * @retun �����¼�������ʱ����ռ���Ϣ
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
	 * ��ȡ���¼�ʱ����ϵ�ĸ���ĵ�������������Ա
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
	 * ��ȡ�¼�������Ϣ
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
	 * �����¼���Ż�ȡ�¼�������Ϣ
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
	 * ��ȡ�¼��Ŀ��Ŷ��ô�ȫ��
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
	 * ��ȡ�¼������س̶�
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
	 * ��ȡ�¼����пɼ̳еļ̳�����
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
	 * ��ȡ�¼����еķ���ģʽ
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
	 * ��ȡ�¼����еķ������
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
	 * ��ȡ���е��¼��������͵ļ���
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
	 * ��ȡ���е��¼�����λ������
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
	 * �����¼��Ĵ���λ�ĸ����˽��в�ѯ
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
	 * �����¼�����λ�����ƽ��в�ѯ
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
	 * �����¼��ķ�������ƻ�ȡ�¼��ı�־���ļ���
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
	 * �����¼��ķ�������ƻ�ȡ�¼��ı�־���ļ���
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
	 * ����ʱ�շ�Χ���¼����в�ѯ
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
	 * ���ݿռ䷶Χ�����¼��Ĳ�ѯ
	 * 
	 * @param ebt
	 *            ��Ҫ�����Ŀռ���˵ð�Χ��
	 * @param esce
	 *            ���пռ�����Ĺ��˵����������ã�CONTAIN ��ʾ��Χ����Ҫ�����¼��㣬NOTCONTAIN ��ʾ��ѯ��Χ���ⲿ���¼���
	 * @return ���ط��Ͽռ��ѯ���¼��ı�־����
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
			throw new DBObjectQueryException("���ݿ��������!");
		}
		return eventidList;
	}

	/**
	 * ���ݲ�ѯ��ʱ����¼���ʱ���Ƿ���������еĴ���
	 * 
	 * @param tp
	 * @param etce
	 *            ����ʱ��Ƚϵķ�ʽ��INTERSECT
	 *            ��ʾ��ѯ��ʱ�����¼���ʱ��ֻҪ�����ص����ɣ�CONTAINED����ʾ��ѯ��ʱ����Ҫ�ܰ����¼���ʱ��
	 *            ��CONTAINED����ʾ��ѯ��ʱ����Ҫ���������¼���ʱ��
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
			throw new DBObjectQueryException("���ݿ��������!");
		}
		return eventidList;
	}

	/**
	 * ���ݷ�������ȡ�¼���ʶ��
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
	 * �����¼����ŶȻ�ȡ�¼���ʶ��
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
	 * �����¼����س̶Ȼ�ȡ�¼���ʶ��
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
	 * �����¼������̶Ȼ�ȡ�¼���ʶ��
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
	 * ���ݷ������ĸ������ƻ�ȡ�¼���ʶ��
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
	 * ���ݷ�������ȡ�¼���ʶ��
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
	 * �����¼������־������¼�������Ϣ
	 * 
	 * @param classificationid
	 *            ��ѯ������¼�������Ϣ��ʶ��
	 * @return ���ػ�ȡ���¼����������Ϣ
	 * @throws DBObjectSaveException
	 */
	public static EventClassificationJBean getECByClaId(Integer classificationid)
			throws DBObjectQueryException {
		return (EventClassificationJBean) getObjectByNumber(
				"EventClassificationJBean", "classificationid",
				classificationid, QueryFieldEnum.NUMBER);
	}

	/**
	 * �����¼�ʱ����Ϣ��־������¼�ʱ����Ϣ
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
	 * ���ݶ���ѯ���ֶΣ����ص��ֶΣ���ѯ�ı�Ͳ�ѯ��ֵ�����������������ݿ����ݣ�����������൱��
	 * 
	 * @param entryname
	 *            ��Ҫ��ѯ�ı������
	 * @param queryfield
	 *            ��Ҫ��ѯ���˵��ֶ�
	 * @param obj
	 *            queryfield�����ֶ����õ�ֵ
	 * @param qfe
	 *            ����obj�Ƿ���Ҫ���ӵ����Ż�˫����
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
	 * ����������ȡָ��ʵ������еĵ��������ʵ��
	 * 
	 * @param entryname
	 *            ��Ҫ��ѯ��ʵ�������
	 * @param fieldname
	 *            �������ֶ�
	 * @param identifier
	 *            ������ֵ
	 * @param qfe
	 *            ������ֵ�����ͣ����ַ�������ֵ�����ڵ�
	 * @return ���ص�����һ����¼
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
	 * ��֤ĳ��¼�Ƿ����
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
	 * ���ݶ���ѯ���ֶΣ����ص��ֶΣ���ѯ�ı�Ͳ�ѯ��ֵ�����������������ݿ����ݣ�����������൱��
	 * 
	 * @param entryname
	 *            ��Ҫ��ѯ�ı������
	 * @param queryfield
	 *            ��Ҫ��ѯ���˵��ֶ�
	 * @param obj
	 *            queryfield�����ֶ����õ�ֵ
	 * @param returnfield
	 *            ��Ҫ���ص��ֶ�
	 * @param qfe
	 *            ����obj�Ƿ���Ҫ���ӵ����Ż�˫����
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
	 * ��ȡָ�������ݿ���е�ĳһ�ֶε�ȫ����ֵ������Ψһ�ģ�
	 * 
	 * @param entryname
	 *            ���ݿ���ʵ����������
	 * @param returnfield
	 *            ��Ҫ���صĵ��ֶε�����
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
	 * �����¼���Ż�ȡ�¼���־��������Ϣ
	 * 
	 * @param eventid
	 *            �¼���־��
	 * @return �����¼���־��
	 * @throws DBObjectSaveException
	 */
	public static EventIdentificationJBean getEventIdentificationById(
			String eventid) throws DBObjectQueryException {
		return (EventIdentificationJBean) getObjectByNumber(
				"EventIdentificationJBean", "eventid", eventid,
				QueryFieldEnum.STRING);
	}
}
