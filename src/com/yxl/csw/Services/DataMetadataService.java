package com.yxl.csw.Services;

import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.datametedia.customTypes.MetadataInfo;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.MetadataInfoException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;

@WebService
public interface DataMetadataService {
	/**
	 * 根据元数据创建时间查询(比较时间必须在starttime和endtime之间)
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
	 * @throws NullZeroException
	 * @throws ServiceException
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByCreatedTime(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "starttime") Date starttime,
			@WebParam(name = "endtime") Date endtime) throws ServiceException,
			NullZeroException;

	/**
	 * 根据元数据的联系单位查询(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param connOrganization
	 *            联系单位
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByconnOrganization(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "connOrganization") String connOrganization)
			throws ServiceException, NullZeroException;

	/**
	 * 根据元数据的数据质量查询(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param datequality
	 *            数据质量
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoBydatequality(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "datequality") String datequality)
			throws ServiceException, NullZeroException;

	/**
	 * 根据元数据的传输链接方式查询(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param OrganName
	 *            传输链接格式
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoBylinkage(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "linkage") String linkage)
			throws ServiceException, NullZeroException;

	/**
	 * 根据元数据的协议类型查询(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param OrganName
	 *            协议类型
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByprotocol(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "protocol") String protocol)
			throws ServiceException, NullZeroException;

	/**
	 * 根据元数据的分发单位名称查询(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param OrganName
	 *            分发单位
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByDistributionOrganName(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "OrganName") String OrganName)
			throws ServiceException, NullZeroException;

	/**
	 * 根据元数据的分发格式查询(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param formatname分发格式名称
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByDistributionFormatName(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "formatname") String formatname)
			throws ServiceException, NullZeroException;

	/**
	 * 根据元数据的起始时间和结束时间查询(元数据的开始和结束时间必须都在查询时间之内)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param starttime
	 *            查询起始时间
	 * @param endtime
	 *            查询结束时间
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByTimePeroid(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "starttime") Date starttime,
			@WebParam(name = "endtime") Date endtime) throws ServiceException,
			NullZeroException;

	/**
	 * 根据采样间隔查询（查询的采样间隔必须在min和max值之间）
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param minExtension_Distance
	 *            采样间隔minExtension_Distance
	 * @param maxExtension_Distance
	 *            采样间隔maxExtension_Distance
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByExtension_Distance(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "minExtension_Distance") double minExtension_Distance,
			@WebParam(name = "maxExtension_Distance") double maxExtension_Distance)
			throws ServiceException, NullZeroException;

	/**
	 * 根据等效比例尺分母查询（查询的采样间隔必须在min和max值之间）
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param minEquivalentScale
	 *            等效比例尺分母(最小值）
	 * @param maxEquivalentScale
	 *            等效比例尺分母(最大值）
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByEquivalentScale(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "minEquivalentScale") double minEquivalentScale,
			@WebParam(name = "maxEquivalentScale") double maxEquivalentScale)
			throws ServiceException, NullZeroException;

	/**
	 * 根据坐标系统查询(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param coordinate
	 *            坐标系统名称
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByCoordinateSystem(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "coordinate") String coordinate)
			throws ServiceException, NullZeroException;

	/**
	 * 根据范围查询
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param boundary
	 *            取值必须为POLYGON((x1 y1,x2 y2,x3 y3,...,xn yn,x1 y1))
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByBoundary(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "boundary") String boundary)
			throws ServiceException, NullZeroException;

	/**
	 * 根据数据集目的查询(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param category
	 *            分类
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByCategory(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "categorys") List<String> categorys)
			throws ServiceException, NullZeroException;

	/**
	 * 根据数据集关键字查询(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param keywords
	 *            关键字
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByKeywords(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "keywords") List<String> keywords)
			throws ServiceException, NullZeroException;

	/**
	 * 根据数据集意图查询(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param purpose
	 *            数据集的目的
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByPurpose(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "purpose") String purpose)
			throws ServiceException, NullZeroException;

	/**
	 * 根据格式名称查询(只有部分匹配即可)
	 * 
	 * @param username
	 * @param password
	 * @param formatname
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByFormatName(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "formatname") String formatname)
			throws ServiceException, NullZeroException;

	/**
	 * 根据联系单位查询(只有部分匹配即可)
	 * 
	 * @param username
	 * @param password
	 * @param organname
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByOrganName(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "organname") String organname)
			throws ServiceException, NullZeroException;

	/**
	 * 提供摘要查询(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param contentabstract
	 *            摘要
	 * @return
	 */
	public List<MetadataInfo> getMetadataInfoByAbstract(String username,
			String password, String contentabstract) throws ServiceException,
			NullZeroException;

	/**
	 * 根据名称查询(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param title
	 *            名称
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByTitle(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "title") String title) throws ServiceException,
			NullZeroException;

	/**
	 * 获取指定标识符的元数据信息(只有部分匹配即可)
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param datameteid
	 *            元数据标识符
	 * @return
	 * @throws NullZeroException
	 * @throws ServiceException
	 * @throws MetadataInfoException
	 */
	@WebMethod
	public MetadataInfo getMetadataInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "datameteid") String datameteid)
			throws ServiceException, NullZeroException, MetadataInfoException;

	/**
	 * 获取所有数据元数据基本信息
	 * 
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getAllMetadataInfo();
	/**
	 * 获取所有注册的数据元数据信息 
	 * @param username 用户名 
	 * @param password 用户密码
	 * @return
	 * @throws NullZeroException 
	 * @throws ServiceException 
	 */
	@WebMethod
	public List<String> getAllMetadataIds(@WebParam(name="username")String username,@WebParam(name="password") String password) throws ServiceException, NullZeroException;

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
	 */
	@WebMethod
	public boolean uploadDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "xmlcontent") String xmlcontent,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException,
			XMLDocumentNotExistException, XMLDocumentException;

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
	 * @throws DocumentnotExistException
	 */
	public boolean updateDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "xmlcontent") String xmlcontent,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException,
			XMLDocumentNotExistException, XMLDocumentException,
			DocumentnotExistException;

}
