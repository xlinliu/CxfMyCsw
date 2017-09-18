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
 * 此接口是对外的服务接口列表
 * 
 * @author yxliang
 * 
 */
@WebService
public interface EventOperationInterfaces {
	/**
	 * 注册事件模型
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param userid
	 *            事件的所有者
	 * @param eventmlcontent
	 *            事件模型内容
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
	 * 更新事件模型
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param eventmlcontent
	 *            事件模型内容
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
	 * 删除事件模型
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param eventid
	 *            事件模型的标识符
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
	 * 获取已注册的全部服务机构或人的名称
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventServiceOraganName(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * 获取已注册的全部服务类别
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventServiceType(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * 获取已经注册的全部事件的事件可信级别
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventClassificationeventCertainty(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * 获取已经注册的全部事件的分类
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventClassificationCategory(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * 获取事件所有可继承的继承类型
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventClassificationInheritance(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * 获取事件所有的所有模式
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventClassificatoinEventPattern(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * 获取事件的严重程度
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return
	 * @throws DBObjectQueryException
	 */
	@WebMethod
	public List<String> getAllEventClassificationEventSeverity(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws DBObjectQueryException;

	/**
	 * 根据事件编号获取事件管理信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param eventid
	 *            事件标识符
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
	 * 获取在事件时可联系的负责的第三方机构或人员
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param eventid
	 *            事件标识符
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
	 * 获取指定事件的受损道路
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param eventid
	 *            事件标识符
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
	 * 根据分类类别获取事件标识符
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 根据事件可信度获取事件标识符
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 根据分类分类的父类名称获取事件标识符
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 根据分类类别获取事件标识符
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 根据事件严重程度获取事件标识符
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 根据事件紧急程度获取事件标识符
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 根据事件的处理单位的负责人进行查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 根据事件管理单位的名称进行查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 根据事件的服务的名称获取事件的标志符的集合
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 根据事件的服务的名称获取事件的标志符的集合
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 根据空间范围进行事件的查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param ebt
	 *            需要检索的空间过滤得包围盒
	 * @param esce
	 *            进行空间检索的过滤得条件的设置，CONTAIN 表示包围盒需要包含事件点，NOTCONTAIN 表示查询包围盒外部的事件点
	 * @return 返回符合空间查询的事件的标志符号
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
	 * 根据时空范围对事件进行查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 根据查询的时间和事件的时间是否关联而进行的处理
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param tp
	 * @param etce
	 *            进行时间比较的方式，INTERSECT
	 *            表示查询的时间与事件的时间只要存在重叠即可，CONTAINED：表示查询的时间需要能包含事件的时间
	 *            ，CONTAINED：表示查询的时间需要被包含于事件的时间
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
	 * 根据事件编号获取事件标志符对象信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param eventid
	 *            事件标志符
	 * @return 返回事件标志符
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
	 * 获取事件的一些基本的信息，包括事件的名称，描述，上传时间等
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 根据事件的编号获取事件发生的地址
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param eventid
	 *            事件的编号
	 * @throws DBObjectQueryException
	 * @throws DBObjectQueryException
	 * @retun 返回事件发生的时间与空间信息
	 */
	@WebMethod
	public EventSpaceTimeJBean getEventLocation(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws DBObjectQueryException;

	/**
	 * 获取事件预警阶段的事件观测数据url地址
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 获取事件服务信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 获取事件预警信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param eventid
	 *            事件的标识符
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
	 * 获取事件可能发生的地点与事件信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 获取事件在recovery后的人员伤亡情况
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 获取事件在recovery后的经济损失情况
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 获取事件在recovery后的其他影响情况
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
	 * 获取事件响应阶段的信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
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
