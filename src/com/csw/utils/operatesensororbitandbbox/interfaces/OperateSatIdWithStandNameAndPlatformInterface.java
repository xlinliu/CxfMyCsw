/**
 * 
 */
package com.csw.utils.operatesensororbitandbbox.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.SatOrbitJiSuanType;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-9-18 ����04:52:32
 */
@WebService
public interface OperateSatIdWithStandNameAndPlatformInterface {
	/**
	 * ���洫������Ӧ�ı�׼�Ĵ�������������ƽ̨��
	 * 
	 * @param username
	 * @param password
	 * @param sojst
	 * @return
	 */
	@WebMethod
	public Boolean SaveSatWithStandardNameAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satorbitJisuantype") SatOrbitJiSuanType sojst)
			throws ServiceException;

	/**
	 * ���洫������Ӧ�ı�׼�Ĵ�������������ƽ̨������
	 * 
	 * @param username
	 * @param password
	 * @param sojst
	 * @return
	 */
	@WebMethod
	public Boolean SaveSatWithStandardNameAndPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satorbitJisuantypes") List<SatOrbitJiSuanType> sojst)
			throws ServiceException;

	/**
	 * ���´�������Ӧ�ı�׼�Ĵ�������������ƽ̨��
	 * 
	 * @param username
	 * @param password
	 * @param sojst
	 * @return
	 */
	@WebMethod
	public Boolean UpdateSatWithStandardNameAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satorbitJisuantype") SatOrbitJiSuanType sojst)
			throws ServiceException;

	/**
	 * ���´�������Ӧ�ı�׼�Ĵ�������������ƽ̨������
	 * 
	 * @param username
	 * @param password
	 * @param sojst
	 * @return
	 */
	@WebMethod
	public Boolean UpdateSatWithStandardNameAndPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satorbitJisuantype") List<SatOrbitJiSuanType> sojst)
			throws ServiceException;

	/**
	 * ɾ����������Ӧ�ı�׼�Ĵ�������������ƽ̨��
	 * 
	 * @param username
	 * @param password
	 * @param satid
	 * @return
	 */
	public Boolean DeleteSatWithStandardNameAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satid") String satid) throws ServiceException;

	/**
	 * ɾ����������Ӧ�ı�׼�Ĵ�������������ƽ̨������
	 * 
	 * @param username
	 * @param password
	 * @param satids
	 * @return
	 */
	public Boolean DeleteSatWithStandardNameAndPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satid") List<String> satids)
			throws ServiceException;

	/**
	 * �ж�ָ���Ĵ������ı�׼�Ĵ����������Ƿ����
	 * 
	 * @param username
	 * @param password
	 * @param satid
	 * @return
	 * @throws ServiceException
	 */
	public Boolean IsExistWithStandNameAndPaltOfSatId(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satid") String satid) throws ServiceException;

	/**
	 * ��ȡָ���Ĵ���������Ϣ
	 * 
	 * @param username
	 * @param password
	 * @param satid
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public SatOrbitJiSuanType GetSatStandardNameAndPlatformInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satid") String satid) throws ServiceException;

	/**
	 * ��ȡȫ������������Ϣ
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SatOrbitJiSuanType> getAllSatOrbitJiSuanTypes(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;

	/**
	 * ��ȡָ���Ĵ���������Ϣ����
	 * 
	 * @param username
	 * @param password
	 * @param satids
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SatOrbitJiSuanType> GetSatStandardNameAndPlatformInfos(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "satids") List<String> satid)
			throws ServiceException;
}
