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
	 * 获取所有事件的类型
	 * @return
	 */
	@WebMethod
	public List<String> getAllEventTypes();
	/**
	 * 获取所有事件的阶段
	 * @return
	 */
	@WebMethod
	public List<String> getAllEventPhase();
	/**
	 * 根据事件的阶段和类型进行服务链获取
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
	 * 根据指定服务标志符获取单一服务链信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param servicechainId
	 *            服务链标志符
	 * @return
	 * @throws NullZeroException
	 * @throws ServiceException
	 */
	@WebMethod
	public ServiceChainInfo getServiceChainInfoById(@WebParam(name="username")String username,
			@WebParam(name="password")String password, @WebParam(name="servicechainId")String servicechainId) throws ServiceException,
			NullZeroException;

	/**
	 * 获取所有服务链标志符
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
	public List<String> getServiceChainIds(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * 获取所有数据元数据基本信息
	 * 
	 * @return
	 */
	@WebMethod
	public List<ServiceChainInfo> getAllServiceChainInfo();

	/**
	 * 获取指定的名称的文档内容
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
	 * 上传文档到注册中心
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
	 * 删除注册中心指定的文档内容
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
	 * 更新注册中心中指定的文档内容
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
