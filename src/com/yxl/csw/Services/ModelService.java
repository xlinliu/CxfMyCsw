package com.yxl.csw.Services;

import java.io.FileNotFoundException;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.model.customTypes.ModelBasicInfo;
import com.model.customTypes.ModelParamNotFormException;
import com.model.customTypes.ModelQueryParamList;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.ModelNotExistException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;

@WebService
public interface ModelService {
	/**
	 * 根据提供的条件进行组合查询
	 * @param username 用户名称
	 * @param password 用户密码
	 * @param mpl 查询条件 
	 * @return
	 * @throws ModelParamNotFormException 
	 * @throws NullZeroException 
	 * @throws ServiceException 
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelByCondition(@WebParam(name="username")String username,@WebParam(name="password")String password,@WebParam(name="mpl")ModelQueryParamList mpl) throws ModelParamNotFormException, ServiceException, NullZeroException;
	/**
	 * 根据工作单位进行查询
	 * @param username 用户名称
	 * @param password 用户密码
	 * @param workspace 联系人
	 * @return
	 * @throws NullZeroException 
	 * @throws ServiceException 
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByWorkSpace(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="workspace")String workspace) throws ServiceException, NullZeroException;
	/**
	 * 根据联系人进行查询
	 * @param username 用户名称
	 * @param password 用户密码
	 * @param personname 联系人
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByPersonName(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="personname")String personname)throws ServiceException, NullZeroException;
	/**
	 * 根据功能进行查询
	 * @param username 用户名称
	 * @param password 用户密码
	 * @param application 预期应用
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByFunction(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="function")String function)throws ServiceException, NullZeroException;
	/**
	 * 根据預预期应用 进行查询
	 * @param username 用户名称
	 * @param password 用户密码
	 * @param application 预期应用
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByApplication(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="application")String application)throws ServiceException, NullZeroException;
	/**
	 * 根据模型简称进行查询
	 * @param username 用户名称
	 * @param password 用户密码
	 * @param longName 全程
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByLongName(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="modelid")String longName)throws ServiceException, NullZeroException;
	/**
	 * 根据模型简称进行查询
	 * @param username 用户名称
	 * @param password 用户密码
	 * @param shortName 简称
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByShortName(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="modelid")String shortName)throws ServiceException, NullZeroException;
	/**
	 * 根据模型标识符进行查询
	 * @param username
	 * @param password
	 * @param modelid
	 * @return
	 * @throws ModelNotExistException 
	 */
	@WebMethod
	public ModelBasicInfo getModelBasicInfoById(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="modelid")String modelid)throws ServiceException, NullZeroException, ModelNotExistException;
	/**
	 * 根据关键字进行查询
	 * @param username 用户名称
	 * @param password 用户密码
	 * @param keyword 关键字 
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByKeyword(
			@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="keyword")String keyword)throws ServiceException, NullZeroException;
	/**
	 * 获取所有的模型的标识符
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getModelIds(@WebParam(name="username")String username,@WebParam(name="password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * 获取所有模型基本信息
	 * 
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getAllModelBasicInfo();

	/**
	 * 获取指定的名称的文档的内容
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
	 */
	@WebMethod
	public boolean uploadDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "xmlcontent") String xmlcontent,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException,
			XMLDocumentNotExistException, XMLDocumentException;

	/**
	 * 删除注册中心 指定的文档内容
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
	 * @throws DocumentnotExistException
	 */
	public boolean updateDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "xmlcontent") String xmlcontent,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException,
			XMLDocumentNotExistException, XMLDocumentException,
			DocumentnotExistException;

	/**
	 * 获取所有包含指定关键字中全部或部分信息的模型文档id号
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param keywords
	 *            用户关键字
	 * @param type
	 *            "all"表示满足全部关键字信息要求，"part"只需满足一个和一个以上即可
	 * @return 返回满足条件的文档的标志符
	 * @throws DatabaseException
	 * @throws FileNotFoundException
	 */
	public List<String> getAllDocumentForKeywords(String username,
			String password, List<String> keywords, String type)
			throws FileNotFoundException;

	/**
	 * 获取所有全程中包含指定查询部分的全部文档的id号
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param fullNameStr
	 *            查询的模型名称
	 * @return 返回满足条件的文档的标志符
	 */
	public List<String> getAllDocumentForfullName(String username,
			String password, String fullNameStr);

	/**
	 * 获取所有全程中包含指定查询部分的全部文档的标志符
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param shortNameStr
	 *            查询的模型简称
	 * @return 返回满足条件的文档的标志符
	 */
	public List<String> getAllDocumentForshortName(String username,
			String password, String shortNameStr);

	/**
	 * 获取注册中心包含指定查询部分的全部文档的标志符
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param modelTypeStr
	 *            查询的模型简称
	 * @return 返回满足条件的文档的标志符
	 */
	public List<String> getAllDocumentFormodelType(String username,
			String password, String modelTypeStr);

	/**
	 * 获取注册中心包含指定查询部分的全部文档的标志符
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param modelSubTypeStr
	 *            模型子类
	 * @return
	 */
	public List<String> getAllDocumentFormodelSubType(String username,
			String password, String modelSubTypeStr);

	/**
	 * 获取注册中心包含指定查询部分的全部文档的标志符
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param domain
	 *            模型应用领域
	 * @return
	 */
	public List<String> getAllDocumentForDomain(String username,
			String password, String domain);
}
