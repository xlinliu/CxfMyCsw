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
	 * �����ṩ������������ϲ�ѯ
	 * @param username �û�����
	 * @param password �û�����
	 * @param mpl ��ѯ���� 
	 * @return
	 * @throws ModelParamNotFormException 
	 * @throws NullZeroException 
	 * @throws ServiceException 
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelByCondition(@WebParam(name="username")String username,@WebParam(name="password")String password,@WebParam(name="mpl")ModelQueryParamList mpl) throws ModelParamNotFormException, ServiceException, NullZeroException;
	/**
	 * ���ݹ�����λ���в�ѯ
	 * @param username �û�����
	 * @param password �û�����
	 * @param workspace ��ϵ��
	 * @return
	 * @throws NullZeroException 
	 * @throws ServiceException 
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByWorkSpace(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="workspace")String workspace) throws ServiceException, NullZeroException;
	/**
	 * ������ϵ�˽��в�ѯ
	 * @param username �û�����
	 * @param password �û�����
	 * @param personname ��ϵ��
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByPersonName(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="personname")String personname)throws ServiceException, NullZeroException;
	/**
	 * ���ݹ��ܽ��в�ѯ
	 * @param username �û�����
	 * @param password �û�����
	 * @param application Ԥ��Ӧ��
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByFunction(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="function")String function)throws ServiceException, NullZeroException;
	/**
	 * �����AԤ��Ӧ�� ���в�ѯ
	 * @param username �û�����
	 * @param password �û�����
	 * @param application Ԥ��Ӧ��
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByApplication(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="application")String application)throws ServiceException, NullZeroException;
	/**
	 * ����ģ�ͼ�ƽ��в�ѯ
	 * @param username �û�����
	 * @param password �û�����
	 * @param longName ȫ��
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByLongName(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="modelid")String longName)throws ServiceException, NullZeroException;
	/**
	 * ����ģ�ͼ�ƽ��в�ѯ
	 * @param username �û�����
	 * @param password �û�����
	 * @param shortName ���
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByShortName(@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="modelid")String shortName)throws ServiceException, NullZeroException;
	/**
	 * ����ģ�ͱ�ʶ�����в�ѯ
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
	 * ���ݹؼ��ֽ��в�ѯ
	 * @param username �û�����
	 * @param password �û�����
	 * @param keyword �ؼ��� 
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getModelBasicInfoByKeyword(
			@WebParam(name="username")String username,
			@WebParam(name="password")String password,
			@WebParam(name="keyword")String keyword)throws ServiceException, NullZeroException;
	/**
	 * ��ȡ���е�ģ�͵ı�ʶ��
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
	 * ��ȡ����ģ�ͻ�����Ϣ
	 * 
	 * @return
	 */
	@WebMethod
	public List<ModelBasicInfo> getAllModelBasicInfo();

	/**
	 * ��ȡָ�������Ƶ��ĵ�������
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
	 * ɾ��ע������ ָ�����ĵ�����
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

	/**
	 * ��ȡ���а���ָ���ؼ�����ȫ���򲿷���Ϣ��ģ���ĵ�id��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param keywords
	 *            �û��ؼ���
	 * @param type
	 *            "all"��ʾ����ȫ���ؼ�����ϢҪ��"part"ֻ������һ����һ�����ϼ���
	 * @return ���������������ĵ��ı�־��
	 * @throws DatabaseException
	 * @throws FileNotFoundException
	 */
	public List<String> getAllDocumentForKeywords(String username,
			String password, List<String> keywords, String type)
			throws FileNotFoundException;

	/**
	 * ��ȡ����ȫ���а���ָ����ѯ���ֵ�ȫ���ĵ���id��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param fullNameStr
	 *            ��ѯ��ģ������
	 * @return ���������������ĵ��ı�־��
	 */
	public List<String> getAllDocumentForfullName(String username,
			String password, String fullNameStr);

	/**
	 * ��ȡ����ȫ���а���ָ����ѯ���ֵ�ȫ���ĵ��ı�־��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param shortNameStr
	 *            ��ѯ��ģ�ͼ��
	 * @return ���������������ĵ��ı�־��
	 */
	public List<String> getAllDocumentForshortName(String username,
			String password, String shortNameStr);

	/**
	 * ��ȡע�����İ���ָ����ѯ���ֵ�ȫ���ĵ��ı�־��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param modelTypeStr
	 *            ��ѯ��ģ�ͼ��
	 * @return ���������������ĵ��ı�־��
	 */
	public List<String> getAllDocumentFormodelType(String username,
			String password, String modelTypeStr);

	/**
	 * ��ȡע�����İ���ָ����ѯ���ֵ�ȫ���ĵ��ı�־��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param modelSubTypeStr
	 *            ģ������
	 * @return
	 */
	public List<String> getAllDocumentFormodelSubType(String username,
			String password, String modelSubTypeStr);

	/**
	 * ��ȡע�����İ���ָ����ѯ���ֵ�ȫ���ĵ��ı�־��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param domain
	 *            ģ��Ӧ������
	 * @return
	 */
	public List<String> getAllDocumentForDomain(String username,
			String password, String domain);
}
