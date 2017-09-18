package com.event.EventOperations;

import java.util.List;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebParam;
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

/**
 * �˽ӿ��Ƕ���ķ���ӿ��б�
 * 
 * @author yxliang
 * 
 */
@WebService
public interface EventOperationInterfaces {
	/**
	 * ע���¼�ģ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param userid
	 *            �¼���������
	 * @param eventmlcontent
	 *            �¼�ģ������
	 * @return
	 * @throws DocumentException
	 * @throws EventMLNotFormatException
	 * @throws DBObjectQueryException
	 * @throws RecoveryExistException
	 * @throws ResponseExistException
	 * @throws PreparationExistException
	 * @throws PrecationExistException
	 * @throws EventExistsException
	 * @throws DBObjectSaveException
	 * @throws NullZeroException
	 */
	@WebMethod
	public Boolean registerEventML(@WebParam(name = "usename") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventmlcontent") String eventmlcontent,
			@WebParam(name = "userid") String userid) throws NullZeroException,
			DBObjectSaveException, EventExistsException,
			PrecationExistException, PreparationExistException,
			ResponseExistException, RecoveryExistException,
			DBObjectQueryException, EventMLNotFormatException,
			DocumentException;

	/**
	 * �����¼�ģ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventmlcontent
	 *            �¼�ģ������
	 * @return
	 * @throws DocumentException
	 * @throws EventMLNotFormatException
	 * @throws EventUpdatePhasesNotExistException
	 * @throws EventNotExistsException
	 * @throws RecoveryExistException
	 * @throws ResponseExistException
	 * @throws PreparationExistException
	 * @throws PrecationExistException
	 * @throws EventExistsException
	 * @throws DBObjectSaveException
	 * @throws NullZeroException
	 * @throws DBObjectUpdateException
	 * @throws DBObjectQueryException
	 * @throws DBObjectDeleteException
	 */
	@WebMethod
	public Boolean updateEventML(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventmlcontent") String eventmlcontent,
			@WebParam(name = "userid") String userid)
			throws DBObjectDeleteException, DBObjectQueryException,
			DBObjectUpdateException, NullZeroException, DBObjectSaveException,
			EventExistsException, PrecationExistException,
			PreparationExistException, ResponseExistException,
			RecoveryExistException, EventNotExistsException,
			EventUpdatePhasesNotExistException, EventMLNotFormatException,
			DocumentException;

	/**
	 * ɾ���¼�ģ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 *            �¼�ģ�͵ı�ʶ��
	 * @return
	 * @throws DBObjectUpdateException
	 * @throws DBObjectQueryException
	 * @throws DBObjectDeleteException
	 */
	@WebMethod
	public Boolean deleteEventML(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectDeleteException, DBObjectQueryException,
			DBObjectUpdateException;

	/**
	 * ��ȡ��ע���ȫ������������˵�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventServiceOraganName(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * ��ȡ��ע���ȫ���������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventServiceType(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�Ѿ�ע���ȫ���¼����¼����ż���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventClassificationeventCertainty(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�Ѿ�ע���ȫ���¼��ķ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventClassificationCategory(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�¼����пɼ̳еļ̳�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventClassificationInheritance(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�¼����е�����ģʽ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventClassificatoinEventPattern(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�¼������س̶�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventClassificationEventSeverity(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * �����¼���Ż�ȡ�¼�������Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 *            �¼���ʶ��
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public EventAdministrationInfoJBean getEventAdministrationInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * ��ȡ���¼�ʱ����ϵ�ĸ���ĵ�������������Ա
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 *            �¼���ʶ��
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public ContactResponsibleParty getEventCantactResponsibleParty(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * ��ȡָ���¼��������·
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 *            �¼���ʶ��
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public Set<EventDenagedRoadJBean> getEventDenagedRoadJBeans(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * ���ݷ�������ȡ�¼���ʶ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param classificationcategory
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getEventIdByCategory(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "classificationcategory") String classificationcategory)
			throws DBObjectQueryException;

	/**
	 * �����¼����ŶȻ�ȡ�¼���ʶ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param classificationeventCertainty
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectSaveException
	 */
	@WebMethod
	public List<String> getEventIdByeventCertainty(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "classificationeventCertainty") String classificationeventCertainty)
			throws DBObjectQueryException;

	/**
	 * ���ݷ������ĸ������ƻ�ȡ�¼���ʶ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param classificationcategory
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectSaveException
	 */
	@WebMethod
	public List<String> getEventIdByeventInheritance(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventInheritance") String eventInheritance)
			throws DBObjectQueryException;

	/**
	 * ���ݷ�������ȡ�¼���ʶ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param classificationcategory
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectSaveException
	 */
	@WebMethod
	public List<String> getEventIdByeventPattern(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "classificationeventPattern") String classificationeventPattern)
			throws DBObjectQueryException;

	/**
	 * �����¼����س̶Ȼ�ȡ�¼���ʶ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param classificationeventSeverity
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectSaveException
	 */
	@WebMethod
	public List<String> getEventIdByeventSeverity(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "classificationeventSeverity") String classificationeventSeverity)
			throws DBObjectQueryException;

	/**
	 * �����¼������̶Ȼ�ȡ�¼���ʶ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param classificationcategory
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectSaveException
	 */
	@WebMethod
	public List<String> getEventIdByeventUrgency(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "classificationeventUrgency") String classificationeventUrgency)
			throws DBObjectQueryException;

	/**
	 * �����¼��Ĵ���λ�ĸ����˽��в�ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param personame
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getEventIdByOraganResponser(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "personame") String personame)
			throws DBObjectQueryException;

	/**
	 * �����¼�����λ�����ƽ��в�ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param oraganname
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getEventIdByOragnName(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "oraganname") String oraganname)
			throws DBObjectQueryException;

	/**
	 * �����¼��ķ�������ƻ�ȡ�¼��ı�־���ļ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param servicename
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getEventIdByServiceName(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "servicename") String servicename)
			throws DBObjectQueryException;

	/**
	 * �����¼��ķ�������ƻ�ȡ�¼��ı�־���ļ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param servicename
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getEventIdByServiceType(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "servicetype") String servicetype)
			throws DBObjectQueryException;

	/**
	 * ���ݿռ䷶Χ�����¼��Ĳ�ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param ebt
	 *            ��Ҫ�����Ŀռ���˵ð�Χ��
	 * @param esce
	 *            ���пռ�����Ĺ��˵����������ã�CONTAIN ��ʾ��Χ����Ҫ�����¼��㣬NOTCONTAIN ��ʾ��ѯ��Χ���ⲿ���¼���
	 * @return ���ط��Ͽռ��ѯ���¼��ı�־����
	 * @throws EventBBOXTypeNotFormatException
	 * @throws NullZeroException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getEventIdBySpaceBBox(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "ebt") EventBBOXType ebt,
			@WebParam(name = "esce") EventSpaceCompareEnum esce)
			throws DBObjectQueryException, NullZeroException,
			EventBBOXTypeNotFormatException;

	/**
	 * ����ʱ�շ�Χ���¼����в�ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param ebt
	 * @param esce
	 * @param tp
	 * @param etce
	 * @return
	 * @throws DBObjectQueryException
	 * @throws TimePeroidNotFormatException
	 * @throws EventBBOXTypeNotFormatException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getEventIdBySpaceTime(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "ebt") EventBBOXType ebt,
			@WebParam(name = "esce") EventSpaceCompareEnum esce,
			@WebParam(name = "tp") TimePeroid tp,
			@WebParam(name = "etce") EventTimeCompareEnum etce)
			throws NullZeroException, EventBBOXTypeNotFormatException,
			TimePeroidNotFormatException, DBObjectQueryException;

	/**
	 * ���ݲ�ѯ��ʱ����¼���ʱ���Ƿ���������еĴ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param tp
	 * @param etce
	 *            ����ʱ��Ƚϵķ�ʽ��INTERSECT
	 *            ��ʾ��ѯ��ʱ�����¼���ʱ��ֻҪ�����ص����ɣ�CONTAINED����ʾ��ѯ��ʱ����Ҫ�ܰ����¼���ʱ��
	 *            ��CONTAINED����ʾ��ѯ��ʱ����Ҫ���������¼���ʱ��
	 * @return
	 * @throws TimePeroidNotFormatException
	 * @throws NullZeroException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getEventIdByTimePeroid(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "tp") TimePeroid tp,
			@WebParam(name = "etce") EventTimeCompareEnum etce)
			throws DBObjectQueryException, NullZeroException,
			TimePeroidNotFormatException;

	/**
	 * �����¼���Ż�ȡ�¼���־��������Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 *            �¼���־��
	 * @return �����¼���־��
	 * @throws DBObjectQueryException
	 * @throws DBObjectSaveException
	 */
	@WebMethod
	public EventIdentificationJBean getEventIdentificationById(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�¼���һЩ��������Ϣ�������¼������ƣ��������ϴ�ʱ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public EventIdenfiticationInfo getEventIdentificationInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * �����¼��ı�Ż�ȡ�¼������ĵ�ַ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 *            �¼��ı��
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 * @retun �����¼�������ʱ����ռ���Ϣ
	 */
	@WebMethod
	public EventSpaceTimeJBean getEventLocation(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�¼�Ԥ���׶ε��¼��۲�����url��ַ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public EventPrecationInfoJBean getEventPrecautionObservations(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�¼�������Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public ServiceInfo getEventServiceInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�¼�Ԥ����Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 *            �¼��ı�ʶ��
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public AlterInfo getPreparationAlert(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�¼����ܷ����ĵص����¼���Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public EventPredicationInfoJBean getPreparationPredictionInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�¼���recovery�����Ա�������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public EventCasualty getRecoveryCasulatyInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�¼���recovery��ľ�����ʧ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public EventEconomicLoss getRecoveryEconomicLossInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�¼���recovery�������Ӱ�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	public EventOtherInfluence getRecoveryOtherInfluenceInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * ��ȡ�¼���Ӧ�׶ε���Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 * @return
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public EventResponseInfoJBean getResponseInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;
}
