package com.yxl.csw.Services;

import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.dom4j.DocumentException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.EventBeginException;
import com.csw.exceptions.EventParamNotFormException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.exceptions.XMLnotFormatException;
import com.event.customTypes.EventAndProcessStatus;
import com.event.customTypes.EventBasicInfo;
import com.event.customTypes.EventQueryParamList;

@WebService
public interface EventService {
	/**
	 * 提供各种组合查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param eqParams
	 *            查询条件
	 * @return
	 * @throws NullZeroException
	 * @throws ServiceException
	 * @throws EventParamNotFormException
	 */
	@WebMethod
	public List<String> getEventIdByCondition(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eqParams") EventQueryParamList eqParams)
			throws ServiceException, NullZeroException,
			EventParamNotFormException;

	/**
	 * 获取 所有事件基本信息
	 * 
	 * @return
	 */
	@WebMethod
	public List<EventBasicInfo> getAllEventBasicInfo();

	/**
	 * 获取文档
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param docname
	 *            注册名称
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 * @throws DocumentnotExistException
	 */
	@WebMethod
	public String getDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException,
			DocumentnotExistException;

	/**
	 * 注册文档
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param xmlcontent
	 *            文档内容
	 * @param docname
	 *            文档名称
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 * @throws DocumentnotExistException
	 * @throws XMLnotFormatException
	 * @throws XMLDocumentException
	 * @throws DocumentException
	 * @throws EventBeginException
	 */
	@WebMethod
	public boolean uploadDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "xmlcontent") String xmlcontent,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException,
			DocumentnotExistException, XMLnotFormatException,
			XMLDocumentException, DocumentException, EventBeginException;

	/**
	 * 删除文档
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param docname
	 *            文档名称（需要删除）
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 * @throws DocumentnotExistException
	 * @throws XMLDocumentNotExistException
	 */
	@WebMethod
	public boolean deleteDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException,
			DocumentnotExistException, XMLDocumentNotExistException;

	/**
	 * 更新文档
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param xmlcontent
	 *            文档内容
	 * @param docname
	 *            文档名称
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 * @throws DocumentnotExistException
	 * @throws XMLnotFormatException
	 * @throws XMLDocumentException
	 * @throws DocumentException
	 * @throws EventBeginException
	 * @throws XMLDocumentNotExistException
	 */
	@WebMethod
	public boolean updateDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "xmlcontent") String xmlcontent,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException,
			DocumentnotExistException, XMLnotFormatException,
			XMLDocumentException, DocumentException, EventBeginException,
			XMLDocumentNotExistException;

	/**
	 * 获取所有事件标识符
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getEventIds(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * 獲取所有事件状态信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<EventAndProcessStatus> getAllEventProcessStatus(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * 根据事件标识符获取时间处理状态
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param eventid
	 *            事件标识符
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getEventProcessStatusByEventId(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid)
			throws ServiceException, NullZeroException;

	/**
	 * 更新事件处理状态
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param eventid
	 *            事件标识符
	 * @param processStatus
	 *            事件处理状态
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean updateEventProcessStatus(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "eventid") String eventid,
			@WebParam(name = "processStatus") String processStatus)
			throws ServiceException, NullZeroException;

	/**
	 * 模糊查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param mohuword
	 *            模糊词
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getEventIdByMohuQuery(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "mohuword") String mohuword)
			throws ServiceException, NullZeroException;

	/**
	 * 根据时间段查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param starttime
	 *            开始时间
	 * @param endtime
	 *            结束时间
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getEventIdsByTime(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "starttime") Date starttime,
			@WebParam(name = "endtime") Date endtime) throws ServiceException,
			NullZeroException;

	/**
	 * 根据查询范围进行查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param polygon
	 *            查询值，必须是POLYGON((0 0,12 12,12 0,0 0))
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getEventIdsByBBOX(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "polygon") String polygon)
			throws ServiceException, NullZeroException;

	/**
	 * 根据指定的类别进行查询
	 * 
	 * @param username
	 * @param password
	 * @param category
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByCategory(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "category") String category)
			throws ServiceException, NullZeroException;

	/**
	 * 根据事件模式进行查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param pattern
	 *            事件模式
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByPattern(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "pattern") String pattern)
			throws ServiceException, NullZeroException;

	/**
	 * 根据事件的紧急程度进行查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param urgency
	 *            事件紧急程度
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByUrgency(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "urgency") String urgency)
			throws ServiceException, NullZeroException;

	/**
	 * 根据事件的严重性查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param severity
	 *            事件的严重性
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdBySeverity(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "severity") String severity)
			throws ServiceException, NullZeroException;

	/**
	 * 根据事件的确定性进行查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param certainty
	 *            确定性
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByCertainty(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "certainty") String certainty)
			throws ServiceException, NullZeroException;

	/**
	 * 根据事件的继承关系进行查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param inheritance
	 *            用户继承关系
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByInheritance(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "inheritance") String inheritance)
			throws ServiceException, NullZeroException;

	/**
	 * 根据死亡人数进行查询
	 * 
	 * @param username
	 * @param password
	 * @param min
	 * @param max
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByDeath(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "min") double min,
			@WebParam(name = "max") double max) throws ServiceException,
			NullZeroException;

	/**
	 * 根据受伤人数进行查询
	 * 
	 * @param username
	 * @param password
	 * @param min
	 * @param max
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByInjury(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "min") double min,
			@WebParam(name = "max") double max) throws ServiceException,
			NullZeroException;

	/**
	 * 根据失踪人数进行查询
	 * 
	 * @param username
	 * @param password
	 * @param min
	 * @param max
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByMisssing(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "min") double min,
			@WebParam(name = "max") double max) throws ServiceException,
			NullZeroException;

	/**
	 * 根据经济损失进行查询
	 * 
	 * @param username
	 * @param password
	 * @param min
	 * @param max
	 * @return
	 */
	public List<String> getEventIdByEconomicLossing(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "min") double min,
			@WebParam(name = "max") double max) throws ServiceException,
			NullZeroException;

	/**
	 * 根据经济直接损失进行查询
	 * 
	 * @param username
	 * @param password
	 * @param min
	 * @param max
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdBydirectLossesNumber(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "min") double min,
			@WebParam(name = "max") double max) throws ServiceException,
			NullZeroException;

	/**
	 * 根据经济间接损失进行查询
	 * 
	 * @param username
	 * @param password
	 * @param min
	 * @param max
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByIndirectLoss(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "min") double min,
			@WebParam(name = "max") double max) throws ServiceException,
			NullZeroException;

}