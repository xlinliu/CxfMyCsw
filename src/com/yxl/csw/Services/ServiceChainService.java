package com.yxl.csw.Services;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.dom4j.DocumentException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.service.customTypes.ServiceChainInfo;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;

@WebService
public interface ServiceChainService {
	/**
	 * ��ȡ�����¼�������
	 * @return
	 */
	@WebMethod
	public List<String> getAllEventTypes();
	/**
	 * ��ȡ�����¼��Ľ׶�
	 * @return
	 */
	@WebMethod
	public List<String> getAllEventPhase();
	/**
	 * �����¼��Ľ׶κ����ͽ��з�������ȡ
	 * @param userame
	 * @param password
	 * @param enventtype
	 * @param eventphase
	 * @return
	 * @throws NullZeroException 
	 * @throws ServiceException 
	 */
	@WebMethod
	public List<ServiceChainInfo> getServiceChainInfosByEvent(
			@WebParam(name="username")String userame,
			@WebParam(name="password")String password, 
			@WebParam(name="enventtype")String eventtype, 
			@WebParam(name="eventstage")String eventstage) throws ServiceException, NullZeroException;

	/**
	 * ����ָ�������־����ȡ��һ��������Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param servicechainId
	 *            ��������־��
	 * @return
	 * @throws NullZeroException
	 * @throws ServiceException
	 */
	@WebMethod
	public ServiceChainInfo getServiceChainInfoById(@WebParam(name="username")String username,
			@WebParam(name="password")String password, @WebParam(name="servicechainId")String servicechainId) throws ServiceException,
			NullZeroException;

	/**
	 * ��ȡ���з�������־��
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
	public List<String> getServiceChainIds(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ��������Ԫ���ݻ�����Ϣ
	 * 
	 * @return
	 */
	@WebMethod
	public List<ServiceChainInfo> getAllServiceChainInfo();

	/**
	 * ��ȡָ�������Ƶ��ĵ�����
	 * 
	 * @param username
	 * @param password
	 * @param docname
	 * @return
	 * @throws NullZeroException
	 * @throws ServiceException
	 * @throws XMLDocumentException
	 * @throws DocumentnotExistException
	 */
	@WebMethod
	public String getDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException, XMLDocumentException,
			DocumentnotExistException;

	/**
	 * �ϴ��ĵ���ע������
	 * 
	 * @param xmlcontent
	 * @param docname
	 * @return
	 * @throws NullZeroException
	 * @throws ServiceException
	 * @throws XMLDocumentException
	 * @throws XMLDocumentNotExistException
	 * @throws XmlException
	 * @throws DocumentException
	 */
	@WebMethod
	public boolean uploadDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "xmlcontent") String xmlcontent,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException,
			XMLDocumentNotExistException, XMLDocumentException,
			DocumentException;

	/**
	 * ɾ��ע������ָ�����ĵ�����
	 * 
	 * @param username
	 * @param password
	 * @param docname
	 * @return
	 * @throws XMLDocumentNotExistException
	 * @throws DocumentnotExistException
	 */
	@WebMethod
	public boolean deleteDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException,
			XMLDocumentNotExistException, DocumentnotExistException;

	/**
	 * ����ע��������ָ�����ĵ�����
	 * 
	 * @param username
	 * @param password
	 * @param xmlcontent
	 * @param docname
	 * @return
	 * @throws XMLDocumentException
	 * @throws XMLDocumentNotExistException
	 * @throws XmlException
	 * @throws DocumentException
	 * @throws DocumentnotExistException
	 */
	public boolean updateDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "xmlcontent") String xmlcontent,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException,
			XMLDocumentNotExistException, XMLDocumentException,
			DocumentException, DocumentnotExistException;

}
