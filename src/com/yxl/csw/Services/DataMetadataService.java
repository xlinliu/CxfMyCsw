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
	 * ����Ԫ���ݴ���ʱ���ѯ(�Ƚ�ʱ�������starttime��endtime֮��)
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
	 * ����Ԫ���ݵ���ϵ��λ��ѯ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param connOrganization
	 *            ��ϵ��λ
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByconnOrganization(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "connOrganization") String connOrganization)
			throws ServiceException, NullZeroException;

	/**
	 * ����Ԫ���ݵ�����������ѯ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param datequality
	 *            ��������
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoBydatequality(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "datequality") String datequality)
			throws ServiceException, NullZeroException;

	/**
	 * ����Ԫ���ݵĴ������ӷ�ʽ��ѯ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param OrganName
	 *            �������Ӹ�ʽ
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoBylinkage(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "linkage") String linkage)
			throws ServiceException, NullZeroException;

	/**
	 * ����Ԫ���ݵ�Э�����Ͳ�ѯ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param OrganName
	 *            Э������
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByprotocol(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "protocol") String protocol)
			throws ServiceException, NullZeroException;

	/**
	 * ����Ԫ���ݵķַ���λ���Ʋ�ѯ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param OrganName
	 *            �ַ���λ
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByDistributionOrganName(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "OrganName") String OrganName)
			throws ServiceException, NullZeroException;

	/**
	 * ����Ԫ���ݵķַ���ʽ��ѯ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param formatname�ַ���ʽ����
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByDistributionFormatName(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "formatname") String formatname)
			throws ServiceException, NullZeroException;

	/**
	 * ����Ԫ���ݵ���ʼʱ��ͽ���ʱ���ѯ(Ԫ���ݵĿ�ʼ�ͽ���ʱ����붼�ڲ�ѯʱ��֮��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param starttime
	 *            ��ѯ��ʼʱ��
	 * @param endtime
	 *            ��ѯ����ʱ��
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
	 * ���ݲ��������ѯ����ѯ�Ĳ������������min��maxֵ֮�䣩
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param minExtension_Distance
	 *            �������minExtension_Distance
	 * @param maxExtension_Distance
	 *            �������maxExtension_Distance
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
	 * ���ݵ�Ч�����߷�ĸ��ѯ����ѯ�Ĳ������������min��maxֵ֮�䣩
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param minEquivalentScale
	 *            ��Ч�����߷�ĸ(��Сֵ��
	 * @param maxEquivalentScale
	 *            ��Ч�����߷�ĸ(���ֵ��
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
	 * ��������ϵͳ��ѯ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param coordinate
	 *            ����ϵͳ����
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByCoordinateSystem(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "coordinate") String coordinate)
			throws ServiceException, NullZeroException;

	/**
	 * ���ݷ�Χ��ѯ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param boundary
	 *            ȡֵ����ΪPOLYGON((x1 y1,x2 y2,x3 y3,...,xn yn,x1 y1))
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByBoundary(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "boundary") String boundary)
			throws ServiceException, NullZeroException;

	/**
	 * �������ݼ�Ŀ�Ĳ�ѯ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param category
	 *            ����
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByCategory(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "categorys") List<String> categorys)
			throws ServiceException, NullZeroException;

	/**
	 * �������ݼ��ؼ��ֲ�ѯ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param keywords
	 *            �ؼ���
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByKeywords(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "keywords") List<String> keywords)
			throws ServiceException, NullZeroException;

	/**
	 * �������ݼ���ͼ��ѯ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param purpose
	 *            ���ݼ���Ŀ��
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByPurpose(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "purpose") String purpose)
			throws ServiceException, NullZeroException;

	/**
	 * ���ݸ�ʽ���Ʋ�ѯ(ֻ�в���ƥ�伴��)
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
	 * ������ϵ��λ��ѯ(ֻ�в���ƥ�伴��)
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
	 * �ṩժҪ��ѯ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param contentabstract
	 *            ժҪ
	 * @return
	 */
	public List<MetadataInfo> getMetadataInfoByAbstract(String username,
			String password, String contentabstract) throws ServiceException,
			NullZeroException;

	/**
	 * �������Ʋ�ѯ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param title
	 *            ����
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getMetadataInfoByTitle(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "title") String title) throws ServiceException,
			NullZeroException;

	/**
	 * ��ȡָ����ʶ����Ԫ������Ϣ(ֻ�в���ƥ�伴��)
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param datameteid
	 *            Ԫ���ݱ�ʶ��
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
	 * ��ȡ��������Ԫ���ݻ�����Ϣ
	 * 
	 * @return
	 */
	@WebMethod
	public List<MetadataInfo> getAllMetadataInfo();
	/**
	 * ��ȡ����ע�������Ԫ������Ϣ 
	 * @param username �û��� 
	 * @param password �û�����
	 * @return
	 * @throws NullZeroException 
	 * @throws ServiceException 
	 */
	@WebMethod
	public List<String> getAllMetadataIds(@WebParam(name="username")String username,@WebParam(name="password") String password) throws ServiceException, NullZeroException;

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
	 */
	@WebMethod
	public boolean uploadDocument(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "xmlcontent") String xmlcontent,
			@WebParam(name = "docname") String docname)
			throws ServiceException, NullZeroException,
			XMLDocumentNotExistException, XMLDocumentException;

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
