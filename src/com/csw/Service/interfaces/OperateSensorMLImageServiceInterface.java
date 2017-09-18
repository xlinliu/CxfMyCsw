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
 *项目名称:CxfMyCsw 类描述：用于保存和SensorML同时传递上来的文件 创建人:Administrator 创建时间: 2013-9-6
 * 上午08:48:37
 */
@WebService
public interface OperateSensorMLImageServiceInterface {
	/**
	 *保存传感器对应附件（保存的策略在于将图片保存在文件路径里面，将图片地址保存在数据库中）
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
	public int DeleteSensorMLAttachmentMethod(
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
	public int DeleteSensorMLAttchmentByWebFilePathMehtod(
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
	 */
	@WebMethod
	public boolean IsExistSensorAttchmentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "attchmentmarker") String attchmentmarker,
			@WebParam(name = "bol") boolean bol) throws ServiceException;

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
	 */
	@WebMethod
	public boolean IsExistSensorAttchmentBySensorWebFilePathMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "filewebpath") String filewebpath,
			@WebParam(name = "bol") boolean bol) throws ServiceException;

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
	 */
	@WebMethod
	public List<String> ReadSensorAttchmentPathFile(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "sensorattachmentmarker") String sensorattachmentmarker,
			@WebParam(name = "bol") boolean bol) throws ServiceException;

	/**
	 * 查询指定的传感器序列和传感器所需的附件标注来获取传感器的附件的web地址
	 * 
	 * @param username
	 *            注册中心账户
	 * @param password
	 *            注册中心密码
	 * @param samt
	 *            包含了对应的传感器标识符和传感器附件标识要求[可空]的传感器信息集合（不需要填写传感器附件的uri，这个是在填充后返回给用户）
	 * @return 返回包含了对应的传感器的标识和传感器附件标识，传感器附件url地址的传感器信息集合
	 */
	@WebMethod
	public List<SensorAttachmentMarkerType> GetSomeSensorAttachementPathsForSomeSensors(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "samt") List<SensorAttachmentMarkerType> samt,
			@WebParam(name = "bol") boolean bol) throws ServiceException;

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
	public List<SensorAttachmentMarkerType> SaveSomeSensorAttachementsWithMarkers(
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
	 */
	@WebMethod
	public List<SensorAttachmentMarkerType> DeleteSomeSensorsAttachmentsWithMarkers(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") List<SensorAttachmentMarkerType> sensors)
			throws ServiceException;

	/**
	 * 返回所有的包含了传感器附件图片的传感器的信息
	 * 
	 * @param attachmentmarker
	 *            附件的类型,如SensorPicture
	 * @return 传感器的信息
	 */
	@WebMethod
	public List<String> GetAllSensorIdsWithSensorImage(
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
	 */
	@WebMethod
	public boolean CleanAllSensorAttachment(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "owner") String owner) throws ServiceException;
}