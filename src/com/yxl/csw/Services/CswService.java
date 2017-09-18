package com.yxl.csw.Services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.xmlbeans.XmlException;
import com.csw.beans.LoginUserBean;
import com.csw.exceptions.DBObjectSaveException;
import com.csw.exceptions.EbrimNotFormatException;
import com.csw.exceptions.FileExistException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.SensorExistException;
import com.csw.exceptions.SensorIdNotEqualException;
import com.csw.exceptions.SensorNotExistException;
import com.csw.exceptions.ServiceException;
import com.csw.exceptions.TransactionProcessException;
import com.csw.utils.custometypes.MapAdapter;
import com.csw.utils.custometypes.SensorAttachmentMarkerType;
import com.csw.utils.custometypes.SensorAttachmentType;
import com.csw.utils.custometypes.SensorBBox;
import com.csw.model.ebXMLModel.SensorBasciForOracleType;
import com.csw.utils.custometypes.PlatformandSensors;
import com.csw.utils.custometypes.SensorBasicInfoType;
import com.csw.utils.custometypes.SensorCommunicationProValueType;
import com.csw.utils.custometypes.SensorComputeProValueType;
import com.csw.utils.custometypes.SensorConnPosType;
import com.csw.utils.custometypes.SensorEmailType;
import com.csw.utils.custometypes.SensorGeoType;
import com.csw.utils.custometypes.SensorInputInfoType;
import com.csw.utils.custometypes.SensorIntendAppForOracleType;
import com.csw.utils.custometypes.SensorKeywordType;
import com.csw.utils.custometypes.SensorLongNameType;
import com.csw.utils.custometypes.SensorMLDocumentType;
import com.csw.utils.custometypes.SensorMLType;
import com.csw.utils.custometypes.SensorMeasureProValueType;
import com.csw.utils.custometypes.SensorOperable;
import com.csw.utils.custometypes.SensorOrganType;
import com.csw.utils.custometypes.SensorOutputType;
import com.csw.utils.custometypes.SensorPhysicProType;
import com.csw.utils.custometypes.SensorPlatformPair;
import com.csw.utils.custometypes.SensorPosType;
import com.csw.utils.custometypes.SensorShareLevel;
import com.csw.utils.custometypes.SensorShortName;
import com.csw.utils.custometypes.SensorTimeType;
import com.csw.utils.custometypes.SensorTypeYXL;
import com.csw.utils.custometypes.SensorTypes;
import com.csw.utils.custometypes.SensorandBelongPlatform;

/**
 * @author yxliang
 * 
 */
@WebService
public interface CswService {

	/**
	 * ���ݻ�ȡ�Ĳ�ѯ���������в�ѯ֮���ȡ�����д��������ĵ�����ɵ�getRecords���ĵ�����Ϣ
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * 
	 * @param allownull
	 *            �Ƿ����и��ֶ�Ϊ�յĴ���������
	 * @param queryStr
	 *            �û���ѯ���ֶ�����ɵ��ַ�������������Լ��
	 * @return
	 * @throws ServiceException
	 *             ���ִ����򷵻ش�����Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getRecordsDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "queryStr") String queryStr,
			@WebParam(name = "allownull") boolean allownull)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ���д�������������Ϣ
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getAllSensorTypes(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡָ����������������Ϣ
	 * 
	 * @param username
	 * @param password
	 * @param sensorids
	 * @return
	 * @throws NullZeroException
	 * @throws ServiceException
	 */
	@WebMethod
	public List<String> getSensorTypes(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ����id�б������е�sensorml���ĵ�������
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param ids
	 *            �û���Ҫ��ȡ�����е�sensorml��id��ֵ����
	 * @param contenttype
	 *            ���ص����ݸ�ʽ ebrim��ʽ����sensorml��ʽ������
	 * @param type
	 *            �û��Ƿ���Ҫ��ȡ���е��û����ĵ���ֵΪtrue����������ĵ���ֵΪfalse��
	 * @return �������з���������sennsorml���ĵ�����Ϣ
	 * @throws NullZeroException
	 * @throws �����쳣
	 *             �������쳣��Ϣ
	 */
	@WebMethod
	public List<SensorMLDocumentType> getAllSensorMLDocumentByIdsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "ids") List<String> ids,
			@WebParam(name = "contenttype") String contenttype,
			@WebParam(name = "type") boolean type) throws ServiceException,
			NullZeroException;

	/**
	 * ��ȡָ���û�ָ���Ĵ�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password�û�����
	 * @param sensorid
	 *            ��ѯ�Ĵ������ı�ʶ��
	 * @return
	 * @throws NullZeroException
	 */
	@WebMethod
	public SensorBasciForOracleType getSingleSensorBasicInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�����������е�ƽ̨������
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getAllPlatfromType(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�����������е�ƽ̨
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getAllPlatfromId(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * ɾ��Transaction-Delete���ĵ��ƶ���ɾ����RegistryPacakge���ĵ�������,
	 * ��ȻҪɾ����RegistryPacakgeҲ�������Լ�ע���˵�RegistryPacakge����Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param transactioncontent
	 *            ɾ�����ļ�����
	 * @return ɾ���ɹ�����1
	 * @throws NullZeroException
	 */
	@WebMethod
	public int deleteSensorByTransactionDeleteMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "transactioncontent") String transactioncontent)
			throws ServiceException, NullZeroException;

	/**
	 * ɾ��ָ�����û�����Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return ɾ���ɹ�����1��ɾ��ʧ�ܷ���0��ɾ�������쳣����2
	 * @throws NullZeroException
	 */
	@WebMethod
	public int deleteUserInfo(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * �����û���д����Ϣ���ɼ򵥵�getRecords���ĵ�
	 * 
	 * @param username
	 *            �Ñ���
	 * @param password
	 *            ����
	 * @param startRecord
	 *            ��ʼ�Ĳ�ѯ��¼
	 * @param maximumRecord
	 *            ���ķ��صĲ�ѯ��¼��
	 * @param west
	 *            ���ߵ�ֵ
	 * @param east
	 *            ���ߵ�ֵ
	 * @param south
	 *            �ϱߵ�ֵ
	 * @param north
	 *            ���ߵ�ֵ
	 * @param startTime
	 *            ��ʼ��ֵ
	 * @param endTime
	 *            ������ʱ��
	 * @param requestType
	 *            ���������
	 * @param profileType
	 *            profile������
	 * @param title
	 *            ��ѯ�Ĺؼ� ����
	 * @param keyword
	 *            ��ѯ�Ĺؼ���
	 * @return ���ص�������getRecords���ĵ�������
	 * @throws NullZeroException
	 */
	@WebMethod
	public String createGetRecordsDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "startRecord") int startRecord,
			@WebParam(name = "maximumRecord") int maximumRecord,
			@WebParam(name = "west") String west,
			@WebParam(name = "east") String east,
			@WebParam(name = "south") String south,
			@WebParam(name = "north") String north,
			@WebParam(name = "startTime") String startTime,
			@WebParam(name = "endtime") String endTime,
			@WebParam(name = "requestType") String requestType,
			@WebParam(name = "profileType") String profileType,
			@WebParam(name = "title") String title,
			@WebParam(name = "keyword") String keyword)
			throws ServiceException, NullZeroException;

	/**
	 * ����record��idֵ����ȡ����records�Ĳ�ѯ����
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @param sensorid
	 *            sensorid��idֵ�������sensorml��id������ת��Ϊebrim��ʽ��sensor��id���в�ѯ
	 * @return �������ɵ�getRecords�Ĳ�ѯ�ĵ���ʧ���򷵻�null
	 * @throws NullZeroException
	 * @throws �����쳣��Ϣ
	 */
	@WebMethod
	public String createGetRecordByIdDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ����û����ݹ�����sensorml��id�Ƿ��Ѿ���ʹ��
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û����ݹ�����Ҫ����sensorml��idֵ ������sensorml��ʽ�ģ�Ҳ������ebrim��ʽ
	 * @return ���ظ�sensomrl�Ƿ��Ѿ����ڣ����ڷ���true�������ڷ���false
	 * @throws NullZeroException
	 * @throws �����쳣��Ϣ
	 */
	@WebMethod
	public boolean checkSensorMLIdExistMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ʵ�û����������Ƿ������ȷ
	 * 
	 * @param username
	 * @param password
	 * @return true ��ȷ��false ����ȷ
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean checkUserLogin(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * ��ѯ�û����Ƿ��Ѿ�����
	 * 
	 * @param username
	 *            ��Ҫ��ѯ���û���
	 * @return true��˵�����ڣ�false˵��������
	 * @throws NullZeroException
	 * @throws ServiceException
	 */
	@WebMethod
	public boolean checkUserName(@WebParam(name = "username") String username)
			throws ServiceException, NullZeroException;

	/**
	 * �����û��ṩ��sensorml�����ṩ�����ķ�ʽ
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            ��������idֵ
	 * 
	 * @param sensormlcontent
	 *            �û��ύ��sensorml������
	 * @return
	 * @throws NullZeroException
	 * @throws TransactionProcessException
	 * @throws FileExistException
	 * @throws SensorExistException
	 * @throws IOException
	 * @throws DBObjectSaveException
	 * @throws EbrimNotFormatException
	 * @throws XmlException
	 * @throws UnsupportedEncodingException
	 * @throws SensorIdNotEqualException
	 */
	@WebMethod
	public boolean saveSensorML(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensormlcontent") String sensormlcontent)
			throws ServiceException, NullZeroException,
			UnsupportedEncodingException, XmlException,
			EbrimNotFormatException, DBObjectSaveException, IOException,
			SensorExistException, FileExistException,
			TransactionProcessException, SensorIdNotEqualException;

	/**
	 * �����û��ṩ��sensorml�����ṩ�����ķ�ʽ
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            ��������idֵ
	 * 
	 * @param sensormlcontent
	 *            �û��ύ��sensorml������
	 * @return
	 * @throws NullZeroException
	 * @throws TransactionProcessException
	 * @throws FileExistException
	 * @throws SensorExistException
	 * @throws IOException
	 * @throws DBObjectSaveException
	 * @throws EbrimNotFormatException
	 * @throws XmlException
	 * @throws UnsupportedEncodingException
	 * @throws SensorIdNotEqualException
	 * @throws SensorNotExistException
	 */
	@WebMethod
	public boolean updateSensorML(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "sensormlcontent") String sensormlcontent)
			throws ServiceException, NullZeroException,
			UnsupportedEncodingException, XmlException,
			EbrimNotFormatException, DBObjectSaveException, IOException,
			SensorExistException, FileExistException,
			TransactionProcessException, SensorIdNotEqualException,
			SensorNotExistException;

	/**
	 * ��Ҫɾ�����ĵ���SensorML�ĸ�ʽ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ������id
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean deleteSensorML(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * �ж��Ƕ�Ӧ��sensorml�ĵ��Ƿ����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            ����
	 * @param sensorid
	 *            ��������id
	 * @return ���ش������true���ڣ�false������
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean isExistsSensorML(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�洢sensorml�ĵ�����Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ������id
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 * @throws SensorNotExistException
	 * @throws FileNotFoundException
	 */
	@WebMethod
	public String readSensorContent(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException,
			SensorNotExistException, FileNotFoundException;

	/**
	 * ��ȡ����������ʱ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            ����
	 * @param sensorid
	 *            ��������id
	 * @return ���ش������true���ڣ�false������
	 * @throws NullZeroException
	 * @throws SensorNotExistException
	 */
	@WebMethod
	public Date getSensorMLSaveTime(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException, SensorNotExistException;

	/**
	 * ��ȡָ�����û��洢��ȫ���Ĵ������ı�ʶ��
	 * 
	 * @param username
	 * @param passowrd
	 * @return
	 * @throws NullZeroException
	 * @throws ServiceException
	 */
	@WebMethod
	public List<String> getSensorMLIdsOfUser(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String passowrd)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�ƶ���������������Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ��������־��
	 * @return
	 * @throws NullZeroException
	 * @throws ServiceException
	 */
	@WebMethod
	public String getSensorType(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws NullZeroException, ServiceException;

	/**
	 * ��ȡָ���û���ȫ���Ĵ�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password�û�����
	 * @param all
	 *            �Ƿ��ȡ���еĴ���������Ϣ���������ˣ�
	 * @return
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorBasicInfoType> getAllSensorBasicInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "all") boolean all) throws ServiceException,
			NullZeroException;

	/**
	 * ��ȡ���е���UserName����������SensorML���ĵ����ݵļ���,���ص���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return ������������UserName��SensorML���ĵ����ݵļ���
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getOwnerAllSensorMLDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡָ�����û�����Ϣ(�����û���������ȡ��ӦȨ�����ܲ鿴��ȫ���Ĵ�������
	 * 
	 * @param owner
	 * @param all
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getRegistrySensorWithOwnerOptionMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "all") boolean all) throws ServiceException,
			NullZeroException;

	/**
	 * ��ȡָ�����͵Ĵ������ļ���
	 * 
	 * @param sensortype
	 *            ��Ҫ�ƶ�������������
	 *            ԭλ������InsituSensor��ֻ��Ҫ����,������Ƶ�ģ�����ģ�վ��ģ���ң�е�����RemoteSensorScanner
	 *            ���ƶ�������InsituSensor-Mobile����Ƶ������InsituSensor-Video
	 * @return
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getSameSensorTypeSensorGroup(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortype") SensorTypes sensortype)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�������Ƿ�ɲ��������ӿ�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ��������ʶ��
	 * @return ������ش������Ƿ�ɲ�������Ϣ
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public SensorOperable getSensorOperableMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�������Ƿ�ɲ������ϵķ����ӿ�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            ��������ʶ������
	 * @return ������ش������Ƿ�ɲ�������Ϣ ����
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	public List<SensorOperable> getSensorsOperableListMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡָ��������������ƽ̨��Ϣ
	 * 
	 * @param username
	 *            ע����������
	 * @param password
	 *            ע����������
	 * @param sensors
	 *            ����������
	 * @return �������д��������е���ϢSensorandBelongPlatformYXL����(��������ƽ̨��źʹ�������ŵĶ�Ӧ��ϵ
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorandBelongPlatform> getSensorsBelongPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") List<String> sensors)
			throws ServiceException, NullZeroException;

	/**
	 * �������ɹ����ѯ����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ��������ʶ��
	 * @return ������������Ϣ
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public SensorShareLevel getSensorShareLevelMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * �������ɹ����ѯ����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            ��������ʶ������
	 * @return ������������Ϣ����
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	public List<SensorShareLevel> getSensorShareLevelsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * 
	 * ��ȡ���ָ��ƽ̨�µĴ�����������
	 * 
	 * @param username
	 * @param password
	 * @param platforms
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<PlatformandSensors> getSepcialPlatfromSensors(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "platforms") List<String> platforms)
			throws ServiceException, NullZeroException;

	/**
	 * 
	 * ��ȡ����ָ��ƽ̨�µĴ�����������
	 * 
	 * @param username
	 * @param password
	 * @param platform
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public PlatformandSensors getSepcialPlatfromSensor(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "platform") String platform)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ��¼�û���Ϣ
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public LoginUserBean loginUserMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * ��������������sensorml
	 * 
	 * @param username
	 * @param password
	 * @param sensormlcontents
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws ServiceException
	 * @throws NullZeroException
	 * @throws XmlException
	 * @throws EbrimNotFormatException
	 * @throws DBObjectSaveException
	 * @throws IOException
	 * @throws SensorExistException
	 * @throws FileExistException
	 * @throws TransactionProcessException
	 * @throws SensorIdNotEqualException
	 */
	@WebMethod
	public boolean saveSensorMLs(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensormlcontents") List<String> sensormlcontents)
			throws UnsupportedEncodingException, ServiceException,
			NullZeroException, XmlException, EbrimNotFormatException,
			DBObjectSaveException, IOException, SensorExistException,
			FileExistException, TransactionProcessException,
			SensorIdNotEqualException;

	/**
	 * ������getRecords�Ĳ�ѯ�����ַ�����ȡ���ö��Ĳ�ѯ����
	 * 
	 * @param queryStr
	 *            ��ѯ������
	 * @return
	 * @throws NullZeroException
	 */
	@WebMethod
	public @XmlJavaTypeAdapter(MapAdapter.class)
	Map<String, String> parseQueryStrAndConditionsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "queryStr") String queryStr)
			throws ServiceException, NullZeroException;

	/**
	 * ��ѯ���д������Ĺ۲ⷶΧ�Ĵ�С
	 * 
	 * @param username
	 * @param password
	 * @param sensors
	 *            ���ȡֵΪnull����ȫ����ѯ
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorBBox> querySensorsBBOXMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") List<String> sensors)
			throws ServiceException, NullZeroException;

	/**
	 * ��ѯ���е��û�����Ϣ������ѯ���û���Ȩ�ޱ�����ϵͳ����Ա������û�
	 * 
	 * @param username
	 *            ϵͳ����Ա���û�����
	 * @param password
	 *            ϵͳ����Ա���û�����
	 * @return �������е��û������ϣ�
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<LoginUserBean> queryAllUserInfosMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡָ�����͵�ƽ̨��������ȫ���Ĵ�����������Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param platfromtype
	 *            ƽ̨������
	 * @return ���ص��Ǵ�������ƽ̨������صĴ��������б����ڶ�����ԡ�|������
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<PlatformandSensors> getPlatFormandSensors(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "platformtype") String platfromtype)
			throws ServiceException, NullZeroException;

	/**
	 * ע���û���Ϣ����ע���֮ǰ��Ҫ����һϵ�е��û�����֤�����û����û����Ƿ��Ѿ����ڵȵ�
	 * 
	 * @param address
	 * @param age
	 * @param emailAddress
	 * @param gender
	 * @param level
	 * @param password
	 * @param telephone
	 * @param username
	 * @param zhiye
	 * @return �ɹ�����1
	 */
	@WebMethod
	public int registryMethod(@WebParam(name = "address") String address,
			@WebParam(name = "age") int age,
			@WebParam(name = "emailAddress") String emailAddress,
			@WebParam(name = "gender") int gender,
			@WebParam(name = "level") int level,
			@WebParam(name = "password") String password,
			@WebParam(name = "telephone") String telephone,
			@WebParam(name = "username") String username,
			@WebParam(name = "zhiye") String zhiye) throws ServiceException;

	/**
	 * ���ݲ�ѯ��getrecordbyid���ĵ���ȡ��ѯ��id���ҽ���ѯ����registrypacakge�ĵ���������Ϣ���ظ��û�
	 * 
	 * @param username
	 * @param password
	 * @param getrecordByIdContent
	 * @return �ɹ�������Ӧ��id��registrypacakge���ĵ������ݣ�û���򷵻�null
	 * @throws NullZeroException
	 */
	@WebMethod
	public String searchGetRecordByIdDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "getrecordByIdContent") String getrecordByIdContent)
			throws ServiceException, NullZeroException;

	/**
	 * �ж�ĳ�������Ƿ�����ĳƽ̨
	 * 
	 * @param username
	 * @param password
	 * @param sensor
	 * @param platform
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean isSensorBelongToPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensor") String sensor,
			@WebParam(name = "platform") String platform)
			throws ServiceException, NullZeroException;

	/**
	 * �ж�ĳЩ��������ƽ̨�Ƿ����ڶ�Ӧ��ϵ
	 * 
	 * @param username
	 * @param password
	 * @param spfs
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorPlatformPair> isSensorsBelongToPlatforms(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "spfs") List<SensorPlatformPair> spfs)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡϵͳ���ṩCSW�Ĺ��ܽ���ģ����ĵ�����Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return �ɹ������ĵ�����Ϣ��ʧ�ܷ���null�������쳣����null
	 * @throws NullZeroException
	 */
	@WebMethod
	public String showCapabilitiesResponseMethod(
			@WebParam(name = "usernmae") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡSensor����Ϣ�ṩ���û�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            �û���ѯ��sensor��idֵ
	 * @return ���ص���һϵ�еļ򵥵������޸ĵ���Ϣ�����������ʵ����
	 * @throws NullZeroException
	 */
	@WebMethod
	SensorBasciForOracleType showSensorInfoForUpdateMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��sensormlת��Ϊebrim�Ĺ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorml
	 *            sensorml������
	 * @return �ɹ�����ebrim���ݣ�ʧ�ܷ���null���쳣����null
	 * @throws NullZeroException
	 */
	@WebMethod
	public String transactionSensorMLToeEbRIM(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorml") String sensorml)
			throws ServiceException, NullZeroException;

	/**
	 * �����û�����Ϣ
	 * 
	 * @param adminusername
	 *            �����û�Ȩ�޵Ĺ���Ա�û�������
	 * @param adminpassword
	 *            �����û�Ȩ�޵Ĺ���Ա�û�������
	 * @param username
	 *            ��Ҫ���µ��û������ƣ����ܸ���
	 * @param password
	 *            ��Ҫ���µ��û������룬���ܸ���
	 * @param level
	 *            ���Ը��ĵ��û��ļ��𣬿��Ը���
	 * @return ���³ɹ��򷵻�1������ʧ�ܷ���0�������쳣����2��
	 * @throws NullZeroException
	 */
	@WebMethod
	public int updateUserInfo(
			@WebParam(name = "adminusername") String adminusername,
			@WebParam(name = "adminpassword") String adminpassword,
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "newpassword") String newpassword,
			@WebParam(name = "level") int level) throws ServiceException,
			NullZeroException;

	/**
	 * ����Sensor����Ϣ
	 * 
	 * @param username
	 * @param password
	 * @param sensorid
	 * @param keywords
	 * @param inputs
	 * @param outputs
	 * @param southenv
	 * @param westenv
	 * @param northenv
	 * @param eastenv
	 * @param positionx
	 * @param positiony
	 * @return �ɹ����� 1��ʧ�ܷ���0�������쳣����2��
	 * @throws NullZeroException
	 */
	@WebMethod
	public int updateSomeSensorInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "keywords") String keywords,
			@WebParam(name = "inputs") String inputs,
			@WebParam(name = "outputs") String outputs,
			@WebParam(name = "southenv") String southenv,
			@WebParam(name = "westenv") String westenv,
			@WebParam(name = "northenv") String northenv,
			@WebParam(name = "eastenv") String eastenv,
			@WebParam(name = "positionx") String positionx,
			@WebParam(name = "positiony") String positiony)
			throws ServiceException, NullZeroException;

	/**
	 * �����û���Ϣ��ֻ�Ǹ����û����룬�û����ƣ��û��ֻ���
	 * 
	 * @param username
	 * @param password
	 * @param address
	 * @param telephone
	 * @return �ɹ�����1��ʧ�ܷ���0������һ������2��
	 * @throws NullZeroException
	 */
	@WebMethod
	public int updateUserInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "address") String address,
			@WebParam(name = "telephone") String telephone)
			throws ServiceException, NullZeroException;

	/**
	 * ���ݲ�ѯ���ֶ�����getRecords���ļ�����
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            �û�����
	 * @param queryStr
	 *            ��ѯ����������ɵģ�һ���Ĺ������
	 * @return �������ɵ�getRecords���ĵ�����
	 * @throws NullZeroException
	 * @throws ServiceException
	 */
	@WebMethod
	public String createGetReocdsDetailMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "queryStr") String queryStr)
			throws NullZeroException, ServiceException;

	/**
	 * ��ȡ����ָ���Ĵ�����������(���ش����������͵Ķ�Ӧ��ϵ��
	 * 
	 * @param sensors
	 *            ��������ʶ����
	 * @return
	 * @throws NullZeroException
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorTypeYXL> getMutiSensorTypeOfSensors(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") List<String> sensors)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�������Ĳ������
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���Ҫ��ѯ�������Ĵ�����
	 * @return ���ش������Ĳ���������Ϣ
	 * @throws ServiceException
	 *             ���ط����������Ĵ�����Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorBandCategoryMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�������Ĳ���������Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            �û���Ҫ��ѯ��ȡ�Ĳ���������Ϣ�Ĵ������ı�ʶ��
	 * @return ���ش������Ĳ���������Ϣ
	 * @throws ServiceException
	 *             ���ط������˵Ĵ�����Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorBandNumberMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ������������ȫ�����ĵ�������Ϣ
	 * 
	 * @param username
	 * @param password
	 * @param sensorid
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public SensorInputInfoType getChineseInputsInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ���д�������ȫ��������������Ϣ����
	 * 
	 * @param username
	 * @param password
	 * @param sensorids
	 * @return
	 * @throws ServiceException
	 */
	public List<SensorInputInfoType> getChineseInputsInfoList(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ���������������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ��������ʶ��
	 * @return
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public SensorOutputType getSensorChineseOutputInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ������������������
	 * 
	 * @param username
	 * @param password
	 * @param sensorids
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorOutputType> getSensorChineseOutputInfoList(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ѯ��������ͨ�������ض����Ե�����
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���ѯ�Ĵ�������ͨ����������һ����
	 * @return ���ش������Ĳ�ѯ��ĳһ�����ͨ����������ϸ����Ϣ
	 * @throws ServiceException
	 *             ���ش������еķ����Ĵ������Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public Object getSensorCommunicationCapabilityMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "capability") String capability)
			throws ServiceException, NullZeroException;

	/**
	 * ��ѯ��������ͨ��������
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            ��Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���ѯ�Ĵ�������ͨ����������һ����
	 * @return ���ش������Ĳ�ѯ��ĳһ�����ͨ����������ϸ����Ϣ
	 * @throws ServiceException
	 *             ���ش������еķ����Ĵ������Ϣ
	 */
	@WebMethod
	public List<SensorCommunicationProValueType> getSensorCommunicationCapabilityForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException, NullZeroException;

	/**
	 * ���ش������й��ڼ�����������Ϣ���ձ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���Ҫ��ѯ�Ĵ������ļ����������ķ��������
	 * 
	 * @return �����û�ָ���Ĵ������ļ���������capability���������
	 * @throws ServiceException
	 *             ���ط������˲���Ĵ�����Ϣ
	 */
	@WebMethod
	public Object getSensorComputeCapabilityMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "capability") String capability)
			throws ServiceException, NullZeroException;

	/**
	 * ���ش������й��ڼ�����������Ϣ���ձ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���Ҫ��ѯ�Ĵ������ļ����������ķ��������
	 * 
	 * @return �����û�ָ���Ĵ������ļ���������capability���������
	 * @throws ServiceException
	 *             ���ط������˲���Ĵ�����Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorComputeProValueType> getSensorComputeCapabilityForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ����������֯��λ�ľ����ַ����Ϣ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʶ��
	 * @return ��������Ҫ�Ĵ���������ĵ�λ�ľ���ĵ�ַ����Ϣ
	 * @throws ServiceException
	 *             ���س��ֵĴ������Ϣ
	 */
	@WebMethod
	public String getSensorConnectionEarthPosInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�������������֯��λ�ľ����ַ����Ϣ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʶ��
	 * @return ��������Ҫ�Ĵ���������ĵ�λ�ľ���ĵ�ַ����Ϣ
	 * @throws ServiceException
	 *             ���س��ֵĴ������Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorConnPosType> getSensorConnectionEarthPosInfosMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ��������ϵ��ʽ�е�email����Ϣ
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @return �������Ӧ�Ĵ������Ĺ���λ����ϵ��email�ĵ�ַ
	 * @throws ServiceException
	 *             ���س��ֵķ������� ��ϸ����Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorConnectionEmailMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�������ĵ���ռ���Ϣ�ķ������ռ䷶Χ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʶ��
	 * @return ���ش������ĵ���ռ䷶Χ����Ϣ,��ʽΪ��"����ϵͳ,�����ǵ㾭γ��,���Ͻǵ㾭γ��"
	 * @throws ServiceException
	 *             ���ط������˵Ĵ������Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorGeoInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�������ĵ���ռ���Ϣ�ķ������ռ䷶Χ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            �û���ѯ�Ĵ������ı�ʶ��
	 * @return ���ش������ĵ���ռ䷶Χ����Ϣ,��ʽΪ��"�����ǵ㾭γ��,���Ͻǵ㾭γ��"
	 * @throws ServiceException
	 *             ���ط������˵Ĵ������Ϣ
	 */
	@WebMethod
	public List<SensorGeoType> getSensorGeoInfosMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ��������Ԥ��Ӧ�õ���Ϣ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @return ���ش�������Ԥ��Ӧ�õ���Ϣ
	 * @throws ServiceException
	 *             ���ط������˵Ĵ�����Ϣ
	 */
	@WebMethod
	public List<SensorIntendAppForOracleType> getSensorIntendedApplicationMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ������������Ԥ��Ӧ�ü���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            ���������м���
	 * @return �������еĴ�����Ԥ��Ӧ��
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorIntendAppForOracleType> getSensorIntendedApplicationsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�������Ĺؼ���
	 * 
	 * @param sensorid
	 *            ��������ʶ��
	 * @param username
	 *            ��ע���û���
	 * @param password
	 *            ע����������
	 * @return ���ش�����������
	 * 
	 * @throws ServiceException
	 */
	@WebMethod
	public String getSensorKeyWordMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ���д������Ĺؼ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            �������ı�ŵļ���
	 * @return ���ش������ı�źʹ������Ĺؼ��ֵĶ���
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorKeywordType> getSensorKeyWordsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡָ����������ȫ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ��������ʶ��
	 * @return ���ش�������ȫ��
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorLongNameMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡָ���Ĵ�����ȫ����Ϣ����
	 * 
	 * @param username
	 * @param password
	 * @param sensorids
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorLongNameType> getSensorLongNamesMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ��������ĳһ����Ĳ�������
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���ѯ�Ĵ��������ķ��������
	 * @return �����ƶ��Ĵ������ڸ�capability�������������Ϣ
	 * @throws ServiceException
	 *             ���ط������˷����ķ���������Ϣ
	 */
	@WebMethod
	public String getSensorMeasureCapabilityMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "capability") String capability)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ��������ĳһ����Ĳ�������
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @param capability
	 *            �û���ѯ�Ĵ��������ķ��������
	 * @return �����ƶ��Ĵ������ڸ�capability�������������Ϣ
	 * @throws ServiceException
	 *             ���ط������˷����ķ���������Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorMeasureProValueType> getSensorMeasureCapabilitysMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ����������֯��Ϣ�ķ���
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @return ���ش���������֯��Ϣ���ַ�������ʽ��
	 * @throws ServiceException
	 *             ��������ʱ���ص���Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorOrganizationInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ����������֯��Ϣ�ķ���
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @param sensorids
	 *            ��Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @return ���ش���������֯��Ϣ���ַ�������ʽ��
	 * @throws ServiceException
	 *             ��������ʱ���ص���Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorOrganType> getSensorOrganizationInfosMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ���������������Ե��ձ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ��������������ԵĴ������ı�ʶ��
	 * @param physicalproperty
	 *            ��Ҫ��ѯ�Ĵ��������������Ե���Ϣ
	 * @return ���ش�������physicalproperty��������Ϣ
	 * @throws ServiceException
	 *             ���ط������з����ķ���������Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorPhysicalPropertyMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "physicalproperty") String physicalproperty)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ���������������Ե��ձ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���ѯ�Ĵ��������������ԵĴ������ı�ʶ��
	 * @param physicalproperty
	 *            ��Ҫ��ѯ�Ĵ��������������Ե���Ϣ
	 * @return ���ش�������physicalproperty��������Ϣ
	 * @throws ServiceException
	 *             ���ط������з����ķ���������Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorPhysicProType> getSensorPhysicalPropertysMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids,
			@WebParam(name = "physicalproperty") String physicalproperty)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡָ���Ĵ������Ķ�λ�������ձ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param infoface
	 *            �û���Ҫ��ѯ�Ķ�λ������ĳһ����
	 * @return ���ز�ѯ�Ľ��
	 * @throws ServiceException
	 *             ���ط�������ִ��ʱ���ֵĴ������Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorPositionInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡָ���Ĵ������Ķ�λ�������ձ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���Ҫ��ѯ�Ĵ������ı�ʾ��
	 * @param infoface
	 *            �û���Ҫ��ѯ�Ķ�λ������ĳһ����
	 * @return ���ز�ѯ�Ľ��
	 * @throws ServiceException
	 *             ���ط�������ִ��ʱ���ֵĴ������Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorPosType> getSensorPositionInfosMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�����������ͣ����״��ͣ���Ӱ�ͣ�����վ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ�������idֵ
	 * @return �������Ӧ�Ĵ����������ͣ����״��ͣ���Ӱ�ͣ�����վ���ͣ�ԭλ�������ȡ�
	 * @throws ServiceException
	 *             ����ʧ�ܵ���Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getSensorSensorTypeMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�����������ͣ����״��ͣ���Ӱ�ͣ�����վ���(���ϲ�β�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            ��������ʶ��
	 * @return ����
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorTypeYXL> getSensorSensorTypeYXLMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡָ���������ļ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            �û���ѯ�Ĵ�������idֵ
	 * @return ��ѯ��ָ���Ĵ������ļ��
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorShortNameMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡָ���������ļ�Ƽ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorids
	 *            �û���ѯ�Ĵ�������idֵ
	 * @return
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorShortName> getSensorShortNamesMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�������Ĺ�����ʼʱ����Ϣ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @return ���ش������Ĺ�����ʼʱ��
	 * @throws ServiceException
	 *             ���ط������˳��ֵĴ�����Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorWorkValidTimeBeginMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�����������й�����ʼʱ����Ϣ����
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���ѯ�Ĵ������ı�ʾ������
	 * @return ���ش������Ĺ�����ʼʱ�伯��
	 * @throws ServiceException
	 *             ���ط������˳��ֵĴ�����Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorTimeType> getSensorWorkValidTimeBeginsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�������Ĺ�������ʱ����Ϣ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @return ���ش������Ĺ�������ʱ��
	 * @throws ServiceException
	 *             ���ط������˳��ֵĴ�����Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorWorkValidTimeEndMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ�������Ĺ�������ʱ����Ϣ�ķ���
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorid
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @return ���ش������Ĺ�������ʱ��
	 * @throws ServiceException
	 *             ���ط������˳��ֵĴ�����Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorTimeType> getSensorWorkValidTimeEndsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ��������ϵ��ʽ�е�email����Ϣ
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param sensorids
	 *            �û���ѯ�Ĵ������ı�ʾ��
	 * @return �������Ӧ�Ĵ������Ĺ���λ����ϵ��email�ĵ�ַ
	 * @throws ServiceException
	 *             ���س��ֵķ������� ��ϸ����Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorEmailType> getSensorConnectionEmailsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * ���ڿͻ����ж�ϵͳ�Ƿ�����ӣ����������û��������벻��ƥ�䣬�����ݿ��в���ȷ�����ж�ϵͳ�������ӣ�����Ϳ�������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return ��������Ϊtrue������������Ϊfalse
	 * @throws ServiceException
	 */
	@WebMethod
	public boolean checkIsConnectMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;

	/**
	 * ɾ���û�ӵ�е�ȫ���������ĵ���Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param filenames
	 *            ��Ҫɾ�����ļ�����
	 * @return ɾ���ɹ����� 1
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean deleteAllSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * ��ȡ���û����������û��ϴ���sensorml�Ļ����ļ���Ϣ�����صĽ����ļ���id���ϴ�ʱ�����ļ���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param type
	 *            ������ȡ�����û������ǻ�ȡȫ���û���������ܽ�����Թ���Ա����Ĭ��Ϊfalse��true���ѯ�����û��ϴ����ļ�����
	 * @return ������Ҫ��Ϣ��List<String>��string���Ϊ id,�ϴ�ʱ��,�ļ�����Ϊ�գ�����null
	 * @throws NullZeroException
	 * @throws �����쳣
	 *             �������쳣��Ϣ
	 */
	@WebMethod
	public List<SensorMLType> getAllBasicInfoOfSensorMLMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "type") boolean type) throws ServiceException,
			NullZeroException;

	/**
	 * ���洫������Ӧ����������Ĳ������ڽ�ͼƬ�������ļ�·�����棬��ͼƬ��ַ���������ݿ��У�
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
	public int deleteSensorMLAttachmentMethod(
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
	public int deleteSensorMLAttchmentByWebFilePathMehtod(
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
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean isExistSensorAttchmentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "attchmentmarker") String attchmentmarker,
			@WebParam(name = "bol") boolean bol) throws ServiceException,
			NullZeroException;

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
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean isExistSensorAttchmentBySensorWebFilePathMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "filewebpath") String filewebpath,
			@WebParam(name = "bol") boolean bol) throws ServiceException,
			NullZeroException;

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
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> readSensorAttchmentPathFile(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "sensorattachmentmarker") String sensorattachmentmarker,
			@WebParam(name = "bol") boolean bol) throws ServiceException,
			NullZeroException;

	/**
	 * ��ѯָ���Ĵ��������кʹ���������ĸ�����ע����ȡ�������ĸ�����web��ַ
	 * 
	 * @param username
	 *            ע�������˻�
	 * @param password
	 *            ע����������
	 * @param samt
	 *            �����˶�Ӧ�Ĵ�������ʶ���ʹ�����������ʶҪ��[�ɿ�]�Ĵ�������Ϣ���ϣ�����Ҫ��д������������uri������������󷵻ظ��û�
	 *            ��
	 * @return ���ذ����˶�Ӧ�Ĵ������ı�ʶ�ʹ�����������ʶ������������url��ַ�Ĵ�������Ϣ����
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorAttachmentMarkerType> getSomeSensorAttachementPathsForSomeSensors(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "samt") List<SensorAttachmentMarkerType> samt,
			@WebParam(name = "bol") boolean bol) throws ServiceException,
			NullZeroException;

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
	public List<SensorAttachmentMarkerType> saveSomeSensorAttachementsWithMarkers(
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
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorAttachmentMarkerType> deleteSomeSensorsAttachmentsWithMarkers(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") List<SensorAttachmentMarkerType> sensors)
			throws ServiceException, NullZeroException;

	/**
	 * �������еİ����˴���������ͼƬ�Ĵ���������Ϣ
	 * 
	 * @param attachmentmarker
	 *            ����������,��SensorPicture
	 * @return ����������Ϣ
	 */
	@WebMethod
	public List<String> getAllSensorIdsWithSensorImage(
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
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean cleanAllSensorAttachment(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "owner") String owner) throws ServiceException,
			NullZeroException;

	/**
	 * ���ݲ�ѯ������ѯ��ȡ���еĴ�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param all
	 *            �Ƿ��ѯ�����û������Ĵ�������Ϣ
	 * @param allownull
	 *            �Ƿ����е���ѯ���ֶ�Ϊ��ʱ����Ϊ�ǿ��Է��صĴ������б�
	 * 
	 * @param conditions
	 *            ��ѯ������
	 * @return �����������������Ĵ�����������
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> querySensorsByConditionsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "condition") @XmlJavaTypeAdapter(MapAdapter.class) Map<String, String> conditions,
			@WebParam(name = "all") boolean all,
			@WebParam(name = "allownull") boolean allownull)
			throws ServiceException, NullZeroException;

	/**
	 * ͨ��getRecords���ĵ��Ļ�ȡgetRecordsResponse���ĵ�����Ϣ
	 * ���ĵ������Ƿ���getRecords�淶����Ϣ,����Ĳ�ѯֻ��Ҫ���û��Ϳ��ԣ����û�������Ȩ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param getrerecords
	 *            ��ȡ���ĵ������Ŷ�Ǹ�
	 * @param resultType
	 *            ���ص��ĵ�����Ϣ�����ͣ����֣��ֱ���full��summary��brief
	 * @return ��ѯ�ɹ�����getRecordsRepsonse���ĵ�����Ϣ
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getRecordsContent(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "getRecordContent") String getrerecords,
			@WebParam(name = "resultType") String resultType)
			throws ServiceException, NullZeroException;

	/**
	 * ͨ��transaction update���ĵ��������Ѿ�sensor��Ϣ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param transactioncontent
	 *            transactionupdate���ĵ�����
	 * @return �ɹ�����1��ʧ�ܷ���0�������쳣����2��
	 * @throws NullZeroException
	 */
	@WebMethod
	public int transactionUpdateContentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "transactioncontent") String transactioncontent)
			throws ServiceException, NullZeroException;

	@WebMethod
	public String getServiceParam();
}
