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
	 * 根据获取的查询的条件进行查询之后获取的所有传感器的文档所组成的getRecords的文档的信息
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * 
	 * @param allownull
	 *            是否运行该字段为空的传感器返回
	 * @param queryStr
	 *            用户查询的字段所组成的字符串，有条件的约束
	 * @return
	 * @throws ServiceException
	 *             出现错误则返回错误信息
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
	 * 获取所有传感器的类型信息
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
	 * 获取指定传感器的类型信息
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
	 * 根据id列表返回所有的sensorml的文档的内容
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param ids
	 *            用户需要获取的所有的sensorml的id的值集合
	 * @param contenttype
	 *            返回的内容格式 ebrim格式或者sensorml格式的内容
	 * @param type
	 *            用户是否需要获取所有的用户的文档（值为true），自身的文档（值为false）
	 * @return 返回所有符合条件的sennsorml的文档的信息
	 * @throws NullZeroException
	 * @throws 发生异常
	 *             ，返回异常信息
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
	 * 获取指定用户指定的传感器
	 * 
	 * @param username
	 *            用户名称
	 * @param password用户密码
	 * @param sensorid
	 *            查询的传感器的标识符
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
	 * 获取传感器下所有的平台的类型
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
	 * 获取传感器下所有的平台
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
	 * 删除Transaction-Delete的文档制定的删除的RegistryPacakge的文档的内容,
	 * 当然要删除的RegistryPacakge也必须是自己注册了的RegistryPacakge的信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param transactioncontent
	 *            删除的文件内容
	 * @return 删除成功返回1
	 * @throws NullZeroException
	 */
	@WebMethod
	public int deleteSensorByTransactionDeleteMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "transactioncontent") String transactioncontent)
			throws ServiceException, NullZeroException;

	/**
	 * 删除指定的用户的信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return 删除成功返回1，删除失败返回0，删除出现异常返回2
	 * @throws NullZeroException
	 */
	@WebMethod
	public int deleteUserInfo(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * 根据用户填写的信息生成简单的getRecords的文档
	 * 
	 * @param username
	 *            用戶名
	 * @param password
	 *            密码
	 * @param startRecord
	 *            开始的查询记录
	 * @param maximumRecord
	 *            最大的返回的查询记录数
	 * @param west
	 *            西边的值
	 * @param east
	 *            东边的值
	 * @param south
	 *            南边的值
	 * @param north
	 *            北边的值
	 * @param startTime
	 *            开始的值
	 * @param endTime
	 *            结束的时间
	 * @param requestType
	 *            请求的类型
	 * @param profileType
	 *            profile的类型
	 * @param title
	 *            查询的关键 名称
	 * @param keyword
	 *            查询的关键子
	 * @return 返回的是生成getRecords的文档的内容
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
	 * 根据record的id值来获取生成records的查询服务
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param sensorid
	 *            sensorid的id值，如果是sensorml的id，可以转换为ebrim格式的sensor的id进行查询
	 * @return 返回生成的getRecords的查询文档，失败则返回null
	 * @throws NullZeroException
	 * @throws 返回异常信息
	 */
	@WebMethod
	public String createGetRecordByIdDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 检测用户传递过来的sensorml的id是否已经被使用
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户传递过来需要检测的sensorml的id值 可以是sensorml格式的，也可以是ebrim格式
	 * @return 返回该sensomrl是否已经存在，存在返回true，不存在返回false
	 * @throws NullZeroException
	 * @throws 返回异常信息
	 */
	@WebMethod
	public boolean checkSensorMLIdExistMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 核实用户名与密码是否配对正确
	 * 
	 * @param username
	 * @param password
	 * @return true 正确，false 不正确
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean checkUserLogin(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * 查询用户名是否已经存在
	 * 
	 * @param username
	 *            需要查询的用户名
	 * @return true则说明存在，false说明不存在
	 * @throws NullZeroException
	 * @throws ServiceException
	 */
	@WebMethod
	public boolean checkUserName(@WebParam(name = "username") String username)
			throws ServiceException, NullZeroException;

	/**
	 * 保存用户提供的sensorml，并提供解析的方式
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            传感器的id值
	 * 
	 * @param sensormlcontent
	 *            用户提交的sensorml的内容
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
	 * 保存用户提供的sensorml，并提供解析的方式
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            传感器的id值
	 * 
	 * @param sensormlcontent
	 *            用户提交的sensorml的内容
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
	 * 需要删除的文档的SensorML的格式
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器id
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
	 * 判断是对应的sensorml文档是否存在
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            密码
	 * @param sensorid
	 *            传感器的id
	 * @return 返回存在与否，true存在，false不存在
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean isExistsSensorML(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 读取存储sensorml文档的信息
	 * 
	 * @param username
	 *            用户名成
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器id
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
	 * 获取传感器保存时间
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            密码
	 * @param sensorid
	 *            传感器的id
	 * @return 返回存在与否，true存在，false不存在
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
	 * 获取指定的用户存储的全部的传感器的标识符
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
	 * 获取制定传感器的类型信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器标志符
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
	 * 获取指定用户的全部的传感器
	 * 
	 * @param username
	 *            用户名称
	 * @param password用户密码
	 * @param all
	 *            是否获取所有的传感器的信息（包括别人）
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
	 * 获取所有的属UserName传递上来的SensorML的文档内容的集合,返回的是
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return 返回所有属于UserName的SensorML的文档内容的集合
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getOwnerAllSensorMLDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * 获取指定的用户的信息(根据用户级别来获取相应权限所能查看的全部的传感器）
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
	 * 获取指定类型的传感器的集合
	 * 
	 * @param sensortype
	 *            需要制定传感器的类型
	 *            原位的则是InsituSensor（只需要输入,包含视频的，气象的，站点的），遥感的则是RemoteSensorScanner
	 *            ，移动的则是InsituSensor-Mobile，视频的则是InsituSensor-Video
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
	 * 获取传感器是否可操作方法接口
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器标识符
	 * @return 返回相关传感器是否可操作的信息
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
	 * 获取传感器是否可操作集合的方法接口
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            传感器标识符集合
	 * @return 返回相关传感器是否可操作的信息 集合
	 * @throws ServiceException
	 * @throws NullZeroException
	 */
	public List<SensorOperable> getSensorsOperableListMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * 获取指定传感器的所属平台信息
	 * 
	 * @param username
	 *            注册中心名称
	 * @param password
	 *            注册中心密码
	 * @param sensors
	 *            传感器集合
	 * @return 返回序列传感器序列的信息SensorandBelongPlatformYXL集合(仅仅包含平台编号和传感器编号的对应关系
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
	 * 传感器可共享查询方法
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器标识符
	 * @return 传感器共享信息
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
	 * 传感器可共享查询方法
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            传感器标识符集合
	 * @return 传感器共享信息集合
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
	 * 获取多个指定平台下的传感器的序列
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
	 * 获取单个指定平台下的传感器的序列
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
	 * 获取登录用户信息
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
	 * 保存多个传感器的sensorml
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
	 * 解析并getRecords的查询条件字符串获取设置定的查询条件
	 * 
	 * @param queryStr
	 *            查询的条件
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
	 * 查询所有传感器的观测范围的大小
	 * 
	 * @param username
	 * @param password
	 * @param sensors
	 *            如果取值为null，则全部查询
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
	 * 查询所有的用户的信息，而查询的用户的权限必须是系统管理员级别的用户
	 * 
	 * @param username
	 *            系统管理员的用户名称
	 * @param password
	 *            系统管理员的用户密码
	 * @return 返回所有的用户的资料，
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<LoginUserBean> queryAllUserInfosMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * 获取指定类型的平台所包含的全部的传感器序列信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param platfromtype
	 *            平台的类型
	 * @return 返回的是传感器的平台和其搭载的传感器的列表，存在多个则以“|”隔开
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<PlatformandSensors> getPlatFormandSensors(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "platformtype") String platfromtype)
			throws ServiceException, NullZeroException;

	/**
	 * 注册用户信息，在注册的之前需要进行一系列的用户的验证，如用户的用户名是否已经存在等等
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
	 * @return 成功返回1
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
	 * 根据查询的getrecordbyid的文档获取查询的id并且将查询到的registrypacakge文档的内容信息返回给用户
	 * 
	 * @param username
	 * @param password
	 * @param getrecordByIdContent
	 * @return 成功返回相应的id的registrypacakge的文档的内容，没有则返回null
	 * @throws NullZeroException
	 */
	@WebMethod
	public String searchGetRecordByIdDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "getrecordByIdContent") String getrecordByIdContent)
			throws ServiceException, NullZeroException;

	/**
	 * 判断某传感器是否属于某平台
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
	 * 判断某些传感器与平台是否属于对应关系
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
	 * 获取系统的提供CSW的功能介绍模块的文档的信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return 成功返回文档的信息，失败返回null，出现异常返回null
	 * @throws NullZeroException
	 */
	@WebMethod
	public String showCapabilitiesResponseMethod(
			@WebParam(name = "usernmae") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * 获取Sensor的信息提供给用户
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            用户查询的sensor的id值
	 * @return 返回的是一系列的简单的所需修改的信息，具体见具体实现类
	 * @throws NullZeroException
	 */
	@WebMethod
	SensorBasciForOracleType showSensorInfoForUpdateMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 将sensorml转换为ebrim的功能
	 * 
	 * @param username
	 *            用户姓名
	 * @param password
	 *            用户密码
	 * @param sensorml
	 *            sensorml的内容
	 * @return 成功返回ebrim内容，失败返回null，异常返回null
	 * @throws NullZeroException
	 */
	@WebMethod
	public String transactionSensorMLToeEbRIM(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorml") String sensorml)
			throws ServiceException, NullZeroException;

	/**
	 * 更改用户的信息
	 * 
	 * @param adminusername
	 *            更新用户权限的管理员用户的名称
	 * @param adminpassword
	 *            更新用户权限的管理员用户的密码
	 * @param username
	 *            需要更新的用户的名称，不能更改
	 * @param password
	 *            需要更新的用户的密码，不能更改
	 * @param level
	 *            可以更改的用户的级别，可以更改
	 * @return 更新成功则返回1，更新失败返回0，发生异常返回2；
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
	 * 更新Sensor的信息
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
	 * @return 成功返回 1，失败返回0，出现异常返回2；
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
	 * 更新用户信息，只是更新用户密码，用户抵制，用户手机号
	 * 
	 * @param username
	 * @param password
	 * @param address
	 * @param telephone
	 * @return 成功返回1，失败返回0，发生一场返回2；
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
	 * 根据查询的字段生成getRecords的文件内容
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @param queryStr
	 *            查询的条件所组成的，一定的规则组成
	 * @return 返回生成的getRecords的文档内容
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
	 * 获取所有指定的传感器的类型(返回传感器与类型的对应关系）
	 * 
	 * @param sensors
	 *            传感器标识符集
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
	 * 获取传感器的波段类别
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户需要查询波段类别的传感器
	 * @return 返回传感器的波段类别的信息
	 * @throws ServiceException
	 *             返回服务器产生的错误信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorBandCategoryMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器的波段数的信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            用户需要查询获取的波段数的信息的传感器的标识符
	 * @return 返回传感器的波段数的信息
	 * @throws ServiceException
	 *             返回服务器端的错误信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorBandNumberMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取单个传感器的全部中文的输入信息
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
	 * 获取序列创安琪的全部的中文输入信息集合
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
	 * 获取传感器的中文输出
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器标识符
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
	 * 获取多个传感器的中文输出
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
	 * 查询传感器中通信能力特定属性的能力
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            需要查询的传感器的标示符
	 * @param capability
	 *            用户查询的传感器的通信能力的哪一方面
	 * @return 返回传感器的查询的某一方面的通信能力的详细的信息
	 * @throws ServiceException
	 *             返回传感器中的发生的错误的信息
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
	 * 查询传感器中通信能力的
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            需要查询的传感器的标示符
	 * @param capability
	 *            用户查询的传感器的通信能力的哪一方面
	 * @return 返回传感器的查询的某一方面的通信能力的详细的信息
	 * @throws ServiceException
	 *             返回传感器中的发生的错误的信息
	 */
	@WebMethod
	public List<SensorCommunicationProValueType> getSensorCommunicationCapabilityForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException, NullZeroException;

	/**
	 * 返回传感器中关于计算能力的信息的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户需要查询的传感器的标示符
	 * @param capability
	 *            用户需要查询的传感器的计算能力的哪方面的能力
	 * 
	 * @return 返回用户指定的传感器的计算能力在capability方面的能力
	 * @throws ServiceException
	 *             返回服务器端缠身的错误信息
	 */
	@WebMethod
	public Object getSensorComputeCapabilityMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "capability") String capability)
			throws ServiceException, NullZeroException;

	/**
	 * 返回传感器中关于计算能力的信息的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户需要查询的传感器的标示符
	 * @param capability
	 *            用户需要查询的传感器的计算能力的哪方面的能力
	 * 
	 * @return 返回用户指定的传感器的计算能力在capability方面的能力
	 * @throws ServiceException
	 *             返回服务器端缠身的错误信息
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
	 * 获取传感器的组织单位的具体地址的信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户需要查询的传感器的标识符
	 * @return 返回所需要的传感器管理的单位的具体的地址的信息
	 * @throws ServiceException
	 *             返回出现的错误的信息
	 */
	@WebMethod
	public String getSensorConnectionEarthPosInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取多个传感器的组织单位的具体地址的信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户需要查询的传感器的标识符
	 * @return 返回所需要的传感器管理的单位的具体的地址的信息
	 * @throws ServiceException
	 *             返回出现的错误的信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorConnPosType> getSensorConnectionEarthPosInfosMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器联系方式中的email的信息
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的标示符
	 * @return 返回相对应的传感器的管理单位的联系的email的地址
	 * @throws ServiceException
	 *             返回出现的服务问题 详细的信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorConnectionEmailMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器的地理空间信息的方法（空间范围）
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            用户查询的传感器的标识符
	 * @return 返回传感器的地理空间范围的信息,格式为："坐标系统,西北角点经纬度,东南角点经纬度"
	 * @throws ServiceException
	 *             返回服务器端的错误的信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorGeoInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器的地理空间信息的方法（空间范围）
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            用户查询的传感器的标识符
	 * @return 返回传感器的地理空间范围的信息,格式为："西北角点经纬度,东南角点经纬度"
	 * @throws ServiceException
	 *             返回服务器端的错误的信息
	 */
	@WebMethod
	public List<SensorGeoType> getSensorGeoInfosMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器的预期应用的信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的标示符
	 * @return 返回传感器的预期应用的信息
	 * @throws ServiceException
	 *             返回服务器端的错误信息
	 */
	@WebMethod
	public List<SensorIntendAppForOracleType> getSensorIntendedApplicationMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器集合中预期应用集合
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            传感器序列集合
	 * @return 返回序列的传感器预期应用
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
	 * 获取传感器的关键字
	 * 
	 * @param sensorid
	 *            传感器标识符
	 * @param username
	 *            　注册用户名
	 * @param password
	 *            注册中心密码
	 * @return 返回传感器的序列
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
	 * 获取序列传感器的关键字
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            传感器的编号的集合
	 * @return 返回传感器的编号和传感器的关键字的对象
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorKeywordType> getSensorKeyWordsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * 获取指定传感器的全称
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器标识符
	 * @return 返回传感器的全称
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorLongNameMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取指定的传感器全称信息集合
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
	 * 获取传感器的某一方面的测量能力
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的标示符
	 * @param capability
	 *            用户查询的传感器的哪方面的能力
	 * @return 返回制定的传感器在该capability方面的能力的信息
	 * @throws ServiceException
	 *             返回服务器端发生的服务错误的信息
	 */
	@WebMethod
	public String getSensorMeasureCapabilityMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "capability") String capability)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器的某一方面的测量能力
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户查询的传感器的标示符
	 * @param capability
	 *            用户查询的传感器的哪方面的能力
	 * @return 返回制定的传感器在该capability方面的能力的信息
	 * @throws ServiceException
	 *             返回服务器端发生的服务错误的信息
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
	 * 获取传感器的组织信息的方法
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param sensorid
	 *            需要查询的传感器的标示符
	 * @return 返回传感器的组织信息（字符串的形式）
	 * @throws ServiceException
	 *             出现问题时返回的信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorOrganizationInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器的组织信息的方法
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param sensorids
	 *            需要查询的传感器的标示符
	 * @return 返回传感器的组织信息（字符串的形式）
	 * @throws ServiceException
	 *             出现问题时返回的信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorOrganType> getSensorOrganizationInfosMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器的物理属性的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的物理属性的传感器的标识符
	 * @param physicalproperty
	 *            需要查询的传感器的物理属性的信息
	 * @return 返回传感器的physicalproperty的属性信息
	 * @throws ServiceException
	 *             返回服务器中发生的服务错误的信息
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
	 * 获取传感器的物理属性的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户查询的传感器的物理属性的传感器的标识符
	 * @param physicalproperty
	 *            需要查询的传感器的物理属性的信息
	 * @return 返回传感器的physicalproperty的属性信息
	 * @throws ServiceException
	 *             返回服务器中发生的服务错误的信息
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
	 * 获取指定的传感器的定位能力的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户需要查询的传感器的标示符
	 * @param infoface
	 *            用户需要查询的定位能力的某一方面
	 * @return 返回查询的结果
	 * @throws ServiceException
	 *             返回服务器端执行时出现的错误的信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorPositionInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取指定的传感器的定位能力的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户需要查询的传感器的标示符
	 * @param infoface
	 *            用户需要查询的定位能力的某一方面
	 * @return 返回查询的结果
	 * @throws ServiceException
	 *             返回服务器端执行时出现的错误的信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorPosType> getSensorPositionInfosMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器的类型，如雷达型，摄影型，地面站点等
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的id值
	 * @return 返回相对应的传感器的类型，如雷达型，摄影型，地面站典型，原位传感器等。
	 * @throws ServiceException
	 *             返回失败的消息
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<String> getSensorSensorTypeMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器的类型，如雷达型，摄影型，地面站点等(集合层次操作）
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            传感器标识符
	 * @return 返回
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
	 * 获取指定传感器的简称
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            用户查询的传感器的id值
	 * @return 查询的指定的传感器的简称
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorShortNameMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取指定传感器的简称集合
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            用户查询的传感器的id值
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
	 * 获取传感器的工作开始时间信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的标示符
	 * @return 返回传感器的工作开始时间
	 * @throws ServiceException
	 *             返回服务器端出现的错误信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorWorkValidTimeBeginMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器集合中工作开始时间信息集合
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户查询的传感器的标示符集合
	 * @return 返回传感器的工作开始时间集合
	 * @throws ServiceException
	 *             返回服务器端出现的错误信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorTimeType> getSensorWorkValidTimeBeginsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器的工作结束时间信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的标示符
	 * @return 返回传感器的工作结束时间
	 * @throws ServiceException
	 *             返回服务器端出现的错误信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public String getSensorWorkValidTimeEndMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器的工作结束时间信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的标示符
	 * @return 返回传感器的工作结束时间
	 * @throws ServiceException
	 *             返回服务器端出现的错误信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorTimeType> getSensorWorkValidTimeEndsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * 获取传感器联系方式中的email的信息
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户查询的传感器的标示符
	 * @return 返回相对应的传感器的管理单位的联系的email的地址
	 * @throws ServiceException
	 *             返回出现的服务问题 详细的信息
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorEmailType> getSensorConnectionEmailsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
			throws ServiceException, NullZeroException;

	/**
	 * 用于客户端判断系统是否可连接，如果输入的用户名与密码不能匹配，在数据库中不正确，则判断系统不可链接，否则就可以链接
	 * 
	 * @param username
	 *            用户姓名
	 * @param password
	 *            用户密码
	 * @return 能连接则为true，不能连接则为false
	 * @throws ServiceException
	 */
	@WebMethod
	public boolean checkIsConnectMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;

	/**
	 * 删除用户拥有的全部传感器文档信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param filenames
	 *            需要删除的文件名称
	 * @return 删除成功返回 1
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean deleteAllSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException, NullZeroException;

	/**
	 * 获取该用户或者所有用户上传的sensorml的基本文件信息，返回的将是文件的id，上传时间与文件名
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param type
	 *            仅仅获取自身用户，还是获取全部用户，这个功能仅仅针对管理员级别，默认为false，true则查询所有用户上传的文件内容
	 * @return 返回需要信息，List<String>中string组成为 id,上传时间,文件名。为空，返回null
	 * @throws NullZeroException
	 * @throws 发生异常
	 *             ，返回异常信息
	 */
	@WebMethod
	public List<SensorMLType> getAllBasicInfoOfSensorMLMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "type") boolean type) throws ServiceException,
			NullZeroException;

	/**
	 * 保存传感器对应附件（保存的策略在于将图片保存在文件路径里面，将图片地址保存在数据库中）
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            对应的传感器id
	 * @param attchment
	 *            用户上传的文件
	 * @param attachmentmarker
	 *            附件说明标识：如该文件是属于附件的图片，附件的应用说明，附件的规则说明等。
	 * @param owner
	 *            附件所有者
	 * @return 返回是否成功，1：成功，2：失败
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
	 * 删除传感器对应的附件（是针对某一附件标注类型而言）
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器id
	 * @param sensorattachmentmarker
	 *            需要删除的传感器的标识
	 * @return 返回是否成功，返回1成功，2 失败
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
	 * 这是是针对某一传感器和指定的传感器的web路径的附件进行删除
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器id
	 * @param attchmentwebpath
	 *            需要删除的传感器附件的web路径
	 * @return 1 删除成功，2删除失败
	 * @throws ServiceException
	 */
	@WebMethod
	public int deleteSensorMLAttchmentByWebFilePathMehtod(
			@WebParam(name = "username") String username,
			@WebParam(name = "passowrd") String passowrd, String sensorid,
			@WebParam(name = "attchmentwebpath") String attchmentwebpath)
			throws ServiceException;

	/**
	 * 判断指定的传感器是否存有附件，这里仅仅是可查询某一传感器的某一标注的传感器的的附件是否存在，可以存在一个或者多个，可以不存在，如果不存在，
	 * 返回false，存在返回true
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器标识符
	 * @param attchmentmarker
	 *            传感器附件标识
	 * @param bol
	 *            是否检测在文件系统中存在 true 需检测，false 不检测
	 * @return 存在返回true，不存在返回false
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
	 * 判断指定的传感器是否存有附件，这里仅仅是可查询某一传感器的某一标注的传感器的的附件是否存在 ，不存在 返回false，存在返回true
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器标识符
	 * @param filewebpath
	 *            传感器附件的web文件路径
	 * @param bol
	 *            是否检测在文件系统中存在 true 需检测，false 不检测
	 * @return 存在返回true，不存在返回false
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
	 * 读取指定的传感器和标识的传感器的附件地址
	 * 
	 * @param username
	 *            注册中心用户
	 * @param password
	 *            注册中心用户密码
	 * @param sensorid
	 *            查询的传感器的标识符
	 * @param sensorattachmentmarker
	 *            传感器的附件的标识
	 * @param bol
	 *            是否删除在文件系统中不存在的传感器记录在数据库中保存的文件路径记录
	 * @return 返回读取到的存在的传感器的路径名称
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
	 * 查询指定的传感器序列和传感器所需的附件标注来获取传感器的附件的web地址
	 * 
	 * @param username
	 *            注册中心账户
	 * @param password
	 *            注册中心密码
	 * @param samt
	 *            包含了对应的传感器标识符和传感器附件标识要求[可空]的传感器信息集合（不需要填写传感器附件的uri，这个是在填充后返回给用户
	 *            ）
	 * @return 返回包含了对应的传感器的标识和传感器附件标识，传感器附件url地址的传感器信息集合
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
	 * 保存传感器的附件（多个同时保存）要求针对每个附件，必须输入传感器标识符，传感器附件和附件后缀名
	 * 
	 * @param username
	 *            注册中心账户
	 * @param password
	 *            注册中心密码
	 * @param sensors
	 *            需要注册的传感器的附件和标识符
	 * @param owner
	 *            附件所有者
	 * @return 返回注册之后的相关传感器的信息，需要去除图片内容，减少网络传输负担
	 */
	@WebMethod
	public List<SensorAttachmentMarkerType> saveSomeSensorAttachementsWithMarkers(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") List<SensorAttachmentMarkerType> sensors,
			@WebParam(name = "owner") String owner) throws ServiceException;

	/**
	 * 删除指定了附加说明和传感器标识符的传感器附件
	 * 
	 * @param username
	 *            注册中心账户
	 * @param password
	 *            注册中心密码
	 * @param sensors
	 *            需要删除的传感器的附件和标识符
	 * @return 返回注册之后的相关传感器的信息，需要去除图片内容，减少网络传输负担,如果输出结果为空，说明删除成功，输出结果为没有删除的附件
	 * @throws NullZeroException
	 */
	@WebMethod
	public List<SensorAttachmentMarkerType> deleteSomeSensorsAttachmentsWithMarkers(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") List<SensorAttachmentMarkerType> sensors)
			throws ServiceException, NullZeroException;

	/**
	 * 返回所有的包含了传感器附件图片的传感器的信息
	 * 
	 * @param attachmentmarker
	 *            附件的类型,如SensorPicture
	 * @return 传感器的信息
	 */
	@WebMethod
	public List<String> getAllSensorIdsWithSensorImage(
			@WebParam(name = "attachmentmarker") String attachmentmarker);

	/**
	 * 清除某一提供者的全部的传感器附件
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param owner
	 *            附件所属者
	 * @return 成功返回true，失败返回false
	 * @throws NullZeroException
	 */
	@WebMethod
	public boolean cleanAllSensorAttachment(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "owner") String owner) throws ServiceException,
			NullZeroException;

	/**
	 * 根据查询条件查询获取所有的传感器
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param all
	 *            是否查询其他用户公开的传感器信息
	 * @param allownull
	 *            是否运行当查询的字段为空时，认为是可以返回的传感器列表
	 * 
	 * @param conditions
	 *            查询的条件
	 * @return 返回所有满足条件的传感器的序列
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
	 * 通过getRecords的文档的获取getRecordsResponse的文档的信息
	 * ，文档必须是符合getRecords规范的信息,这里的查询只需要是用户就可以，即用户不设置权限
	 * 
	 * @param username
	 *            用户姓名
	 * @param password
	 *            用户密码
	 * @param getrerecords
	 *            获取的文档的你饿哦那个
	 * @param resultType
	 *            返回的文档的信息的类型，三种，分别是full，summary和brief
	 * @return 查询成功返回getRecordsRepsonse的文档的信息
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
	 * 通过transaction update的文档将更新已经sensor信息，
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param transactioncontent
	 *            transactionupdate的文档内容
	 * @return 成功返回1，失败返回0，生成异常返回2；
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
