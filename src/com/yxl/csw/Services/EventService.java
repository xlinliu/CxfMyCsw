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
	 * �ṩ������ϲ�ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eqParams
	 *            ��ѯ����
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
	 * ��ȡ �����¼�������Ϣ
	 * 
	 * @return
	 */
	@WebMethod
	public List<EventBasicInfo> getAllEventBasicInfo();

	/**
	 * ��ȡ�ĵ�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param docname
	 *            ע������
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
	 * ע���ĵ�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param xmlcontent
	 *            �ĵ�����
	 * @param docname
	 *            �ĵ�����
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
	 * ɾ���ĵ�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param docname
	 *            �ĵ����ƣ���Ҫɾ����
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
	 * �����ĵ�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param xmlcontent
	 *            �ĵ�����
	 * @param docname
	 *            �ĵ�����
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
	 * ��ȡ�����¼���ʶ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
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
	 * �@ȡ�����¼�״̬��Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
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
	 * �����¼���ʶ����ȡʱ�䴦��״̬
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 *            �¼���ʶ��
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
	 * �����¼�����״̬
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param eventid
	 *            �¼���ʶ��
	 * @param processStatus
	 *            �¼�����״̬
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
	 * ģ����ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param mohuword
	 *            ģ����
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
	 * ����ʱ��β�ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param starttime
	 *            ��ʼʱ��
	 * @param endtime
	 *            ����ʱ��
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
	 * ���ݲ�ѯ��Χ���в�ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param polygon
	 *            ��ѯֵ��������POLYGON((0 0,12 12,12 0,0 0))
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
	 * ����ָ���������в�ѯ
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
	 * �����¼�ģʽ���в�ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param pattern
	 *            �¼�ģʽ
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByPattern(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "pattern") String pattern)
			throws ServiceException, NullZeroException;

	/**
	 * �����¼��Ľ����̶Ƚ��в�ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param urgency
	 *            �¼������̶�
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByUrgency(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "urgency") String urgency)
			throws ServiceException, NullZeroException;

	/**
	 * �����¼��������Բ�ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param severity
	 *            �¼���������
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdBySeverity(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "severity") String severity)
			throws ServiceException, NullZeroException;

	/**
	 * �����¼���ȷ���Խ��в�ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param certainty
	 *            ȷ����
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByCertainty(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "certainty") String certainty)
			throws ServiceException, NullZeroException;

	/**
	 * �����¼��ļ̳й�ϵ���в�ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param inheritance
	 *            �û��̳й�ϵ
	 * @return
	 */
	@WebMethod
	public List<String> getEventIdByInheritance(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "inheritance") String inheritance)
			throws ServiceException, NullZeroException;

	/**
	 * ���������������в�ѯ
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
	 * ���������������в�ѯ
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
	 * ����ʧ���������в�ѯ
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
	 * ���ݾ�����ʧ���в�ѯ
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
	 * ���ݾ���ֱ����ʧ���в�ѯ
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
	 * ���ݾ��ü����ʧ���в�ѯ
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