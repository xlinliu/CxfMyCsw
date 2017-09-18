/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorAttachmentMarkerType;
import com.csw.utils.custometypes.SensorAttachmentType;

/**
 *��Ŀ����:CxfMyCsw �����������ڱ����SensorMLͬʱ�����������ļ� ������:Administrator ����ʱ��: 2013-9-6
 * ����08:48:37
 */
@WebService
public interface OperateSensorMLImageServiceInterface {
	/**
	 *���洫������Ӧ����������Ĳ������ڽ�ͼƬ�������ļ�·�����棬��ͼƬ��ַ���������ݿ��У�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ��Ӧ�Ĵ�����id
	 * @param attchment
	 *            �û��ϴ����ļ�
	 * @param attachmentmarker
	 *            ����˵����ʶ������ļ������ڸ�����ͼƬ��������Ӧ��˵���������Ĺ���˵���ȡ�
	 * @param owner
	 *            ����������
	 * @return �����Ƿ�ɹ���1���ɹ���2��ʧ��
	 */
	@WebMethod
	public int saveSensorMLAttachmentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "attchment") SensorAttachmentType image,
			@WebParam(name = "attachmentmarker") String attachmentmarker,
			@WebParam(name = "owner") String owner) throws ServiceException;

	/**
	 * ɾ����������Ӧ�ĸ����������ĳһ������ע���Ͷ��ԣ�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ������id
	 * @param sensorattachmentmarker
	 *            ��Ҫɾ���Ĵ������ı�ʶ
	 * @return �����Ƿ�ɹ�������1�ɹ���2 ʧ��
	 * @throws ServiceException
	 */
	@WebMethod
	public int DeleteSensorMLAttachmentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "sensorattachmentmarker") String sensorattachmentmarker)
			throws ServiceException;

	/**
	 * ���������ĳһ��������ָ���Ĵ�������web·���ĸ�������ɾ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ������id
	 * @param attchmentwebpath
	 *            ��Ҫɾ���Ĵ�����������web·��
	 * @return 1 ɾ���ɹ���2ɾ��ʧ��
	 * @throws ServiceException
	 */
	@WebMethod
	public int DeleteSensorMLAttchmentByWebFilePathMehtod(
			@WebParam(name = "username") String username,
			@WebParam(name = "passowrd") String passowrd, String sensorid,
			@WebParam(name = "attchmentwebpath") String attchmentwebpath)
			throws ServiceException;

	/**
	 * �ж�ָ���Ĵ������Ƿ���и�������������ǿɲ�ѯĳһ��������ĳһ��ע�Ĵ������ĵĸ����Ƿ���ڣ����Դ���һ�����߶�������Բ����ڣ���������ڣ�
	 * ����false�����ڷ���true
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ��������ʶ��
	 * @param attchmentmarker
	 *            ������������ʶ
	 * @param bol
	 *            �Ƿ������ļ�ϵͳ�д��� true ���⣬false �����
	 * @return ���ڷ���true�������ڷ���false
	 */
	@WebMethod
	public boolean IsExistSensorAttchmentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "attchmentmarker") String attchmentmarker,
			@WebParam(name = "bol") boolean bol) throws ServiceException;

	/**
	 * �ж�ָ���Ĵ������Ƿ���и�������������ǿɲ�ѯĳһ��������ĳһ��ע�Ĵ������ĵĸ����Ƿ���� �������� ����false�����ڷ���true
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ��������ʶ��
	 * @param filewebpath
	 *            ������������web�ļ�·��
	 * @param bol
	 *            �Ƿ������ļ�ϵͳ�д��� true ���⣬false �����
	 * @return ���ڷ���true�������ڷ���false
	 */
	@WebMethod
	public boolean IsExistSensorAttchmentBySensorWebFilePathMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "filewebpath") String filewebpath,
			@WebParam(name = "bol") boolean bol) throws ServiceException;

	/**
	 * ��ȡָ���Ĵ������ͱ�ʶ�Ĵ������ĸ�����ַ
	 * 
	 * @param username
	 *            ע�������û�
	 * @param password
	 *            ע�������û�����
	 * @param sensorid
	 *            ��ѯ�Ĵ������ı�ʶ��
	 * @param sensorattachmentmarker
	 *            �������ĸ����ı�ʶ
	 * @param bol
	 *            �Ƿ�ɾ�����ļ�ϵͳ�в����ڵĴ�������¼�����ݿ��б�����ļ�·����¼
	 * @return ���ض�ȡ���Ĵ��ڵĴ�������·������
	 * @throws ServiceException
	 */
	@WebMethod
	public List<String> ReadSensorAttchmentPathFile(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "sensorattachmentmarker") String sensorattachmentmarker,
			@WebParam(name = "bol") boolean bol) throws ServiceException;

	/**
	 * ��ѯָ���Ĵ��������кʹ���������ĸ�����ע����ȡ�������ĸ�����web��ַ
	 * 
	 * @param username
	 *            ע�������˻�
	 * @param password
	 *            ע����������
	 * @param samt
	 *            �����˶�Ӧ�Ĵ�������ʶ���ʹ�����������ʶҪ��[�ɿ�]�Ĵ�������Ϣ���ϣ�����Ҫ��д������������uri������������󷵻ظ��û���
	 * @return ���ذ����˶�Ӧ�Ĵ������ı�ʶ�ʹ�����������ʶ������������url��ַ�Ĵ�������Ϣ����
	 */
	@WebMethod
	public List<SensorAttachmentMarkerType> GetSomeSensorAttachementPathsForSomeSensors(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "samt") List<SensorAttachmentMarkerType> samt,
			@WebParam(name = "bol") boolean bol) throws ServiceException;

	/**
	 * ���洫�����ĸ��������ͬʱ���棩Ҫ�����ÿ���������������봫������ʶ���������������͸�����׺��
	 * 
	 * @param username
	 *            ע�������˻�
	 * @param password
	 *            ע����������
	 * @param sensors
	 *            ��Ҫע��Ĵ������ĸ����ͱ�ʶ��
	 * @param owner
	 *            ����������
	 * @return ����ע��֮�����ش���������Ϣ����Ҫȥ��ͼƬ���ݣ��������紫�为��
	 */
	@WebMethod
	public List<SensorAttachmentMarkerType> SaveSomeSensorAttachementsWithMarkers(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") List<SensorAttachmentMarkerType> sensors,
			@WebParam(name = "owner") String owner) throws ServiceException;

	/**
	 * ɾ��ָ���˸���˵���ʹ�������ʶ���Ĵ���������
	 * 
	 * @param username
	 *            ע�������˻�
	 * @param password
	 *            ע����������
	 * @param sensors
	 *            ��Ҫɾ���Ĵ������ĸ����ͱ�ʶ��
	 * @return ����ע��֮�����ش���������Ϣ����Ҫȥ��ͼƬ���ݣ��������紫�为��,���������Ϊ�գ�˵��ɾ���ɹ���������Ϊû��ɾ���ĸ���
	 */
	@WebMethod
	public List<SensorAttachmentMarkerType> DeleteSomeSensorsAttachmentsWithMarkers(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") List<SensorAttachmentMarkerType> sensors)
			throws ServiceException;

	/**
	 * �������еİ����˴���������ͼƬ�Ĵ���������Ϣ
	 * 
	 * @param attachmentmarker
	 *            ����������,��SensorPicture
	 * @return ����������Ϣ
	 */
	@WebMethod
	public List<String> GetAllSensorIdsWithSensorImage(
			@WebParam(name = "attachmentmarker") String attachmentmarker);

	/**
	 * ���ĳһ�ṩ�ߵ�ȫ���Ĵ���������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param owner
	 *            ����������
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	@WebMethod
	public boolean CleanAllSensorAttachment(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "owner") String owner) throws ServiceException;
}