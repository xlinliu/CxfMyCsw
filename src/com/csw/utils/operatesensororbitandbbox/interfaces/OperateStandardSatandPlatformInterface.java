/**
 * 
 */
package com.csw.utils.operatesensororbitandbbox.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.StandSatSensorPlatformPair;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-9-18 ����02:57:43
 */
@WebService
public interface OperateStandardSatandPlatformInterface {
	/**
	 * �����׼�����ǵ����ƺͶ�Ӧ��ƽ̨
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param satname
	 *            ���Ǵ���������
	 * @param satplatform
	 *            ����ƽ̨
	 * @return ����true ����ɹ���false����ʧ��
	 * @throws ServiceException
	 */
	@WebMethod
	public Boolean SaveStandardSatAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sspp") StandSatSensorPlatformPair sspp)
			throws ServiceException;

	/**
	 * ����ȫ���Ĵ�����������
	 * 
	 * @param username
	 * @param password
	 * @param sspps
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public Boolean SaveStandardSatAndPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sspps") List<StandSatSensorPlatformPair> sspps)
			throws ServiceException;

	/**
	 * ���±�׼�����ǵ����ƺͶ�Ӧ��ƽ̨����Ϣ
	 * 
	 * @param username
	 * @param password
	 * @param sspp
	 * @return
	 */
	@WebMethod
	public Boolean UpdateStandardSatAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sspp") StandSatSensorPlatformPair sspp)
			throws ServiceException;

	/**
	 * ����ȫ���ı�׼�����ǵ����ƺͶ�Ӧ��ƽ̨����Ϣ
	 * 
	 * @param username
	 * @param password
	 * @param sspps
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public Boolean UpdateStandardSatAndPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sspps") List<StandSatSensorPlatformPair> sspps)
			throws ServiceException;

	/**
	 * ɾ����׼�����ǵ����ƺͶ�Ӧ��ƽ̨����Ϣ
	 * 
	 * @param username
	 * @param password
	 * @param sspp
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public Boolean DeleteStandardSatAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sspp") StandSatSensorPlatformPair sspp)
			throws ServiceException;

	/**
	 * ɾ��ȫ���ı�׼�������ƺ�ƽ̨
	 * 
	 * @param username
	 * @param passowrd
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public Boolean DeleteAllStandardSatAndPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;

	/**
	 * ��ȡ���еı�׼���������ƺͶ�Ӧ��ƽ̨��Ϣ
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<StandSatSensorPlatformPair> ReadStandSatAndPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;

	/**
	 * �ж��Ƿ����
	 * 
	 * @param username
	 * @param password
	 * @param sp
	 * @return
	 */
	@WebMethod
	public Boolean IsExist(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorname") String sensorname,
			@WebParam(name = "satplat") String satplat) throws ServiceException;
}
