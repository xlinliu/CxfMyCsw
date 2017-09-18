package com.yxl.csw.Services;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.dom4j.DocumentException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.serviceresult.customTypes.ServiceResultInfo;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;

@WebService
public interface ServiceResultService {
	public List<String> getServiceResultIds(String username, String password)
			throws ServiceException, NullZeroException;

	/**
	 * 获取所有数据元数据基本信息
	 * 
	 * @return
	 */
	@WebMethod
	public List<ServiceResultInfo> getAllServiceResultInfo();

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
	/**
	 * 根据docname获取服务链完全信息 
	 * @param docname
	 * @return
	 */
	public ServiceResultInfo  getServiceResultInfoByFileName(String docname);

}
