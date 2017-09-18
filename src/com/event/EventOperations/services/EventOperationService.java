package com.event.EventOperations.services;

import java.util.List;
import java.util.Set;

import javax.jws.WebService;

import org.dom4j.DocumentException;

import com.csw.exceptions.DBObjectQueryException;
import com.csw.exceptions.DBObjectSaveException;
import com.csw.exceptions.DBObjectUpdateException;
import com.csw.exceptions.EventBBOXTypeNotFormatException;
import com.csw.exceptions.EventExistsException;
import com.csw.exceptions.EventMLNotFormatException;
import com.csw.exceptions.EventNotExistsException;
import com.csw.exceptions.EventUpdatePhasesNotExistException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.PrecationExistException;
import com.csw.exceptions.PreparationExistException;
import com.csw.exceptions.RecoveryExistException;
import com.csw.exceptions.ResponseExistException;
import com.csw.exceptions.TimePeroidNotFormatException;
import com.csw.exceptions.DBObjectDeleteException;
import com.event.EventOperations.DeleteEventOperation;
import com.event.EventOperations.EventOperationInterfaces;
import com.event.EventOperations.QueryEventOperation;
import com.event.EventOperations.SaveEventOperation;
import com.event.EventOperations.UpdateEventOperation;
import com.event.EventOperations.utils.EventSpaceCompareEnum;
import com.event.EventOperations.utils.EventTimeCompareEnum;
import com.event.InnerEntities.AlterInfo;
import com.event.InnerEntities.ContactResponsibleParty;
import com.event.InnerEntities.EventBBOXType;
import com.event.InnerEntities.EventCasualty;
import com.event.InnerEntities.EventEconomicLoss;
import com.event.InnerEntities.EventIdenfiticationInfo;
import com.event.InnerEntities.EventOtherInfluence;
import com.event.InnerEntities.ServiceInfo;
import com.event.InnerEntities.TimePeroid;
import com.event.JavaBeans.EventAdministrationInfoJBean;
import com.event.JavaBeans.EventDenagedRoadJBean;
import com.event.JavaBeans.EventIdentificationJBean;
import com.event.JavaBeans.EventPrecationInfoJBean;
import com.event.JavaBeans.EventPredicationInfoJBean;
import com.event.JavaBeans.EventResponseInfoJBean;
import com.event.JavaBeans.EventSpaceTimeJBean;
import com.event.commonutils.EventCheckUserUtil;
import com.event.dom4j.parsers.Dom4JParser;

@WebService
public class EventOperationService implements EventOperationInterfaces {

	public Boolean deleteEventML(String username, String password,
			String eventid) throws DBObjectDeleteException,
			DBObjectQueryException, DBObjectUpdateException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return DeleteEventOperation.deleteEventMLClass(eventid);
	}

	public List<String> getAllEventClassificationCategory(String username,
			String password) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getAllEventClassificationCategory();
	}

	public List<String> getAllEventClassificationEventSeverity(String username,
			String password) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getAllEventClassificationeventSeverity();
	}

	public List<String> getAllEventClassificationInheritance(String username,
			String password) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getAllEventClassificationeventInheritance();
	}

	public List<String> getAllEventClassificationeventCertainty(
			String username, String password) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getAllEventClassificationeventCertainty();
	}

	public List<String> getAllEventClassificatoinEventPattern(String username,
			String password) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getAllEventClassificationeventPattern();
	}

	public List<String> getAllEventServiceOraganName(String username,
			String password) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getAllEventServiceOrganName();
	}

	public List<String> getAllEventServiceType(String username, String password)
			throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getAllEventServiceType();
	}

	public EventAdministrationInfoJBean getEventAdministrationInfo(
			String username, String password, String eventid)
			throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventAdministrationInfo(eventid);
	}

	public ContactResponsibleParty getEventCantactResponsibleParty(
			String username, String password, String eventid)
			throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventCantactResponsibleParty(eventid);
	}

	public Set<EventDenagedRoadJBean> getEventDenagedRoadJBeans(
			String username, String password, String eventid)
			throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventDenagedRoadJBeans(eventid);
	}

	public List<String> getEventIdByCategory(String username, String password,
			String classificationcategory) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventIdByCategory(classificationcategory);
	}

	public List<String> getEventIdByOraganResponser(String username,
			String password, String personame) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventIdByOraganResponser(personame);
	}

	public List<String> getEventIdByOragnName(String username, String password,
			String oraganname) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventIdByOragnName(oraganname);
	}

	public List<String> getEventIdByServiceName(String username,
			String password, String servicename) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventIdByServiceName(servicename);
	}

	public List<String> getEventIdByServiceType(String username,
			String password, String servicetype) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventIdByServiceType(servicetype);
	}

	public List<String> getEventIdBySpaceBBox(String username, String password,
			EventBBOXType ebt, EventSpaceCompareEnum esce)
			throws DBObjectQueryException, NullZeroException,
			EventBBOXTypeNotFormatException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventIdBySpaceBBox(ebt, esce);
	}

	public List<String> getEventIdBySpaceTime(String username, String password,
			EventBBOXType ebt, EventSpaceCompareEnum esce, TimePeroid tp,
			EventTimeCompareEnum etce) throws NullZeroException,
			EventBBOXTypeNotFormatException, TimePeroidNotFormatException,
			DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventIdBySpaceTime(ebt, esce, tp, etce);
	}

	public List<String> getEventIdByTimePeroid(String username,
			String password, TimePeroid tp, EventTimeCompareEnum etce)
			throws DBObjectQueryException, NullZeroException,
			TimePeroidNotFormatException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventIdByTimePeroid(tp, etce);
	}

	public List<String> getEventIdByeventCertainty(String username,
			String password, String classificationeventCertainty)
			throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation
				.getEventIdByeventCertainty(classificationeventCertainty);
	}

	public List<String> getEventIdByeventInheritance(String username,
			String password, String eventInheritance)
			throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation
				.getEventIdByeventInheritance(eventInheritance);
	}

	public List<String> getEventIdByeventPattern(String username,
			String password, String classificationeventPattern)
			throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation
				.getEventIdByeventPattern(classificationeventPattern);
	}

	public List<String> getEventIdByeventSeverity(String username,
			String password, String classificationeventSeverity)
			throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation
				.getEventIdByeventSeverity(classificationeventSeverity);
	}

	public List<String> getEventIdByeventUrgency(String username,
			String password, String classificationeventUrgency)
			throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation
				.getEventIdByeventUrgency(classificationeventUrgency);
	}

	public EventIdentificationJBean getEventIdentificationById(String username,
			String password, String eventid) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventIdentificationById(eventid);
	}

	public EventIdenfiticationInfo getEventIdentificationInfo(String username,
			String password, String eventid) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventIdentificationInfo(eventid);
	}

	public EventSpaceTimeJBean getEventLocation(String username,
			String password, String eventid) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventLocation(eventid);
	}

	public EventPrecationInfoJBean getEventPrecautionObservations(
			String username, String password, String eventid)
			throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventPrecautionObservations(eventid);
	}

	public ServiceInfo getEventServiceInfo(String username, String password,
			String eventid) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getEventServiceInfo(eventid);
	}

	public AlterInfo getPreparationAlert(String username, String password,
			String eventid) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getPreparationAlert(eventid);
	}

	public EventPredicationInfoJBean getPreparationPredictionInfo(
			String username, String password, String eventid)
			throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getPreparationPredictionInfo(eventid);
	}

	public EventCasualty getRecoveryCasulatyInfo(String username,
			String password, String eventid) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getRecoveryCasulatyInfo(eventid);
	}

	public EventEconomicLoss getRecoveryEconomicLossInfo(String username,
			String password, String eventid) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getRecoveryEconomicLossInfo(eventid);
	}

	public EventOtherInfluence getRecoveryOtherInfluenceInfo(String username,
			String password, String eventid) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getRecoveryOtherInfluenceInfo(eventid);
	}

	public EventResponseInfoJBean getResponseInfo(String username,
			String password, String eventid) throws DBObjectQueryException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		return QueryEventOperation.getResponseInfo(eventid);
	}

	public Boolean registerEventML(String username, String password,
			String eventmlcontent, String userid) throws NullZeroException,
			DBObjectSaveException, EventExistsException,
			PrecationExistException, PreparationExistException,
			ResponseExistException, RecoveryExistException,
			DBObjectQueryException, EventMLNotFormatException,
			DocumentException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		// œ÷Ω‚Œˆ
		Dom4JParser dParser = new Dom4JParser();
		return SaveEventOperation.saveEventMLClass(dParser
				.parseEventML(eventmlcontent), userid);
	}

	public Boolean updateEventML(String username, String password,
			String eventmlcontent, String userid)
			throws DBObjectDeleteException, DBObjectQueryException,
			DBObjectUpdateException, NullZeroException, DBObjectSaveException,
			EventExistsException, PrecationExistException,
			PreparationExistException, ResponseExistException,
			RecoveryExistException, EventNotExistsException,
			EventUpdatePhasesNotExistException, EventMLNotFormatException,
			DocumentException {
		EventCheckUserUtil.CheckUserInfo(username, password);
		Dom4JParser dParser = new Dom4JParser();
		return UpdateEventOperation.UpdateEventMLClass(dParser
				.parseEventML(eventmlcontent), userid);
	}

}
