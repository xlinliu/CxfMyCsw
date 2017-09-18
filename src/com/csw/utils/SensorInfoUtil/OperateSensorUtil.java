package com.csw.utils.SensorInfoUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import oracle.sql.CLOB;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;
import org.hibernate.lob.SerializableClob;

import com.csw.Service.impls.TransformSensorMLToebRIMService;
import com.csw.Service.interfaces.getSensorMLByIdsServiceInterface;
import com.csw.exceptions.DBObjectSaveException;
import com.csw.exceptions.FileExistException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.SensorNotExistException;
import com.csw.exceptions.ServiceException;
import com.csw.exceptions.TransactionProcessException;
import com.csw.interfaces.querypackage.impls.GetSensorLongNameService;
import com.csw.model.ebXMLModel.ClassificationNode;
import com.csw.model.ebXMLModel.LocalizedString;
import com.csw.model.ebXMLModel.MyebRIMSmlContents;
import com.csw.model.ebXMLModel.PostalAddress;
import com.csw.model.ebXMLModel.RegistryPackage;
import com.csw.model.ebXMLModel.SensorBasciForOracleType;
import com.csw.model.ebXMLModel.SensorML;
import com.csw.model.ebXMLModel.Slot;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.PlatformandSensors;
import com.csw.utils.custometypes.SensorBasicInfoType;
import com.csw.utils.custometypes.SensorExistBoolean;
import com.csw.utils.custometypes.SensorInfo;
import com.csw.utils.custometypes.SensorInputInfoType;
import com.csw.utils.custometypes.SensorMLType;
import com.csw.utils.custometypes.SensorOperable;
import com.csw.utils.custometypes.SensorOutputType;
import com.csw.utils.custometypes.SensorShareLevel;
import com.event.EventOperations.utils.QueryFieldEnum;
import com.sun.msv.util.LightStack;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.Static;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

/**
 *项目名称:CxfMyCsw 类描述： 此处 创建人:Administrator 创建时间: 2013-9-10 下午03:23:09
 */
@SuppressWarnings( { "deprecation", "unused" })
public class OperateSensorUtil {

	
	

	@SuppressWarnings({ "finally", "unchecked" })
	public static Object queryObject(String entryname, String fieldname,
			Object obj, QueryFieldEnum qfe) {
		Object object2 = null;
		Session session = null;
		try {
			String queryStr = "";
			if (qfe.equals(QueryFieldEnum.STRING)) {
				queryStr = "from " + entryname + " where " + fieldname + " ='"
						+ obj.toString() + "'";
			} else {
				queryStr = "from " + entryname + " where " + fieldname + " = "
						+ obj.toString();
			}
			session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List<Object> list = session.createQuery(queryStr)
					.setCacheable(true).list();
			if (list != null && list.size() == 1) {
				object2 = list.get(0);
			}
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DBObjectSaveException("保存对象出现错误：" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				GetSessionUtil.CloseSessionInstance(session);
			}
			return object2;
		}
	}
	/**
	 * 根据查询条件查询
	 * 
	 * @param queryStr
	 * @return
	 */
	public static Object queryCswElement(String queryStr) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Object object = session.createQuery(queryStr).setCacheable(true).list();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		return object;
	}

	/**
	 * 获取制定用户的全部的传感器的信息
	 * 
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getAllSensorByOwner(String username) {
		List<String> results = new ArrayList<String>();
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		results = session
				.createQuery(
						"select ex.id from ExtrinsicObject ex, SensorML sm where sm.sensorid like ex.id||':package' and sm.username='"
								+ username + "'").setCacheable(true).list();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		return results;
	}
	/**
	 * 判断是否存在指定的文档类型
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Boolean SensorIsExist(String sensorid) {
		if (sensorid == null || sensorid.trim().equals("")) {
			return false;
		}
		List<Object> resutls = (List<Object>) queryCswElement("from ExtrinsicObject where  id = '"
						+ sensorid.trim() + "'");
		if (resutls != null && resutls.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 保存对象
	 * 
	 * @param object
	 * @return
	 * @throws DBObjectSaveException
	 */
	@SuppressWarnings("finally")
	public static Object saveObject(Object object) throws DBObjectSaveException {
		Object object2 = null;
		Session session = null;
		try {
			session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			object2 = session.save(object);
			session.beginTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DBObjectSaveException("保存对象出现错误：" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				GetSessionUtil.CloseSessionInstance(session);
			}
			return object2;
		}
	}

	/**
	 * 删除指定的传感器的SensorML文档
	 * 
	 * @throws NullZeroException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static boolean DeleteSensorML(String username, String sensorid)
			throws NullZeroException {
		StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid, "sensorid");
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Long id;
		List list = session
				.createQuery(
						"select id from SensorML where sensorid='"
								+ sensorid.trim() + "' and username='"
								+ username + "'").setCacheable(true).list();
		if (list != null && list.size() > 0) {
			id = (Long) list.get(0);
			int nums = 0;
			try {
				session.createQuery(
						"update SensorML set sensorcontent=empty_clob() where id="
								+ id).setCacheable(true).executeUpdate();
				nums = session.createQuery("delete SensorML where id=" + id)
						.setCacheable(true).executeUpdate();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (nums == 1) {
				System.out.println("删除成功 ");
				return true;
			} else {
				System.out.println("删除失败");
				return false;
			}
		} else {
			GetSessionUtil.CloseSessionInstance(session);
			return true;// 没有则删除成功
		}
	}
	/**
	 * 获取单个snesomrl的文档的MyebRIMSmlContents对象
	 * 
	 * @param username
	 *            用户名称
	 * @param ebrimid
	 *            需要获取的sensorml的id值，特别注意的是这个的是ebrim格式的id值 ，级后面会加上:pacakge
	 * @param type
	 *            查询的范围，true，则查询这个文档库，false则查询指定用户的上传的的文档的集合
	 * @return 返回制定的MyebRIMSmlContents实例
	 */
	@SuppressWarnings("unchecked")
	public MyebRIMSmlContents GetSensorMLDocumentByIdMethod(String username,
			String ebrimid, boolean type) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			ebrimid = ebrimid.trim();
			ebrimid = StringUtil.AppendPacakgeStr(ebrimid);
			session.beginTransaction().begin();
			Query query;
			if (type) {
				query = session
						.createQuery(" from MyebRIMSmlContents where id='"
								+ ebrimid + "'");
			} else {
				query = session
						.createQuery(" from MyebRIMSmlContents where id='"
								+ ebrimid + "' and ower='" + username + "'");
			}
			List lists = query.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (lists != null && lists.size() > 0) {
				MyebRIMSmlContents mys = (MyebRIMSmlContents) lists.get(0);
				return mys;
			} else {
				return null;
			}
		} catch (Exception e) {
			GetSessionUtil.CloseSessionInstance(session);
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将system，component和processModel的SensorML转换为EbRIM格式内容
	 * 
	 * @param sensorml
	 *            system,component,processModel的SensorML内容
	 * @param sensormlfilepath
	 *            SensorML存储文件路径
	 * @param processmodelxsltpath
	 *            ProcessModelPath 转换文件路径
	 * @param xslfilepath
	 *            system or component 的转换文件路径
	 * @param ebrimfilepath
	 *            转换后的EbRIM内容文件路径
	 * @return 返回转换好的EbRIM格式内容
	 * @throws Exception
	 */
	public String TransformNonProcessChainToEbRIMMethod(String sensorml,
			String sensormlfilepath, String processmodelxsltpath,
			String xslfilepath, String ebrimfilepath) {
		FileOperationUtil.ReadFileContent(ebrimfilepath, "UTF-8");
		String ebrimcontent = "";
		FileOperationUtil.WriteToFileContent(sensorml.trim(), sensormlfilepath,
				"UTF-8");
		TransformerFactory tf = TransformerFactory.newInstance();
		Source source = null;
		if (sensorml.toLowerCase().contains("processmodel>")) {
			source = new StreamSource(new File(processmodelxsltpath));
		} else if (sensorml.toLowerCase().contains("system>")) {
			source = new StreamSource(new File(xslfilepath));
		}
		try {
			Source smlsources = new StreamSource(new FileInputStream(
					sensormlfilepath));
			StreamResult result = new StreamResult(new File(ebrimfilepath));
			tf.newTransformer(source).transform(smlsources, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ebrimcontent = FileOperationUtil
				.ReadFileContent(ebrimfilepath, "UTF-8");
		return ebrimcontent;
	}
	/**
	 * 获取查询的结构信息，一般是List类型
	 * 
	 * @param querystr
	 * @return
	 */
	public static Object queryObject(String querystr) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Object resultObject = session.createQuery(querystr).setCacheable(true)
				.list();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		return resultObject;
	}
	/**
	 * 判断是否存在某一SensorML文档
	 * 
	 * @param username
	 *            用户名称
	 * @param sensorid
	 *            传感器标识符
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean IsExistsSensorML(String sensorid) {
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		String queryStr = "from SensorML where sensorid='" + sensorid.trim()
				+ "'";
		List list = GetSessionUtil.GetSelectQueryInfo(queryStr);
		int nums = list.size();
		if (nums >= 1) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 获取指定用户的传感器标识符
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getSensorMLIdsOfUser(String username) {
		return (List<String>) queryObject("select sensorid from SensorML where username='"
				+ username + "'");
	}

	/**
	 * 检查sensorid的文档是否存在(全局）
	 * 
	 * @param sensormlid
	 *            用户查询的sensorml的id值,是ebrim格式的id
	 * @return true：存在，false： 不存在或者存储的sensormlcontent为空则也返回false
	 * @throws NullZeroException
	 */
	@SuppressWarnings("unchecked")
	public static Boolean CheckSensorMLExistFromXMLMethod(String sensormlid)
			throws NullZeroException {
		sensormlid = StringUtil.AppendPacakgeStr(sensormlid);
		List list = (List) queryObject("from SensorML where sensorid='"
				+ sensormlid + "'");

		if (list != null && list.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 对传递过来的sensorml进行某种操作
	 * 
	 * @param id
	 *            　文档的id，可以是sensorml格式的id，也可以是ebrim格式的id
	 * @param sensormlcontent
	 *            传递过来的sensorml的文档的内容
	 * @param filename
	 *            文件名称
	 * @param createtime
	 *            文件创建的时间
	 * @param operationtype
	 *            操作的方式，有 delete，update，insert三种方式
	 * @return 操作成功返回1，操作失败返回0，发生异常返回2；
	 */
	public int OperateSensorMLAndEbRim(String username, String password,
			String id, String sensormlcontent, String filename,
			String createtime, String operationtype) {
		if (null != StringUtil
				.checkStringIsNotNULLAndEmptyMethod(operationtype)) {
			if (operationtype.trim().toLowerCase().equals("delete")) {
				// 删除操作
				if (null != StringUtil.checkStringIsNotNULLAndEmptyMethod(id)) {
					// 如果传入的id为正确的
					if (OperateSensorUtil.CheckSensorMLExistMethod(username, id
							.trim())) {
						// 如果在数据库存在该条记录
						OperateSensorUtil
								.DeleteSensorML(username, password, id);
					}
					return 1;
				} else if (null != StringUtil
						.checkStringIsNotNULLAndEmptyMethod(sensormlcontent)) {
					// 通过sensorML文档获取id
					// 第二步获取传递过来的sensorML的id（RegistryPackage的id值），并进行检测数据库存在数据库
					String ebrimcontent = "";
					try {
						ebrimcontent = new TransformSensorMLToebRIMService()
								.TransactionSensorMLToeEbRIMMethod(username,
										password, sensormlcontent);
					} catch (ServiceException e) {
						e.printStackTrace();
						return 2;
					}
					String ebrimid = "";
					if (ebrimcontent != null && !ebrimcontent.isEmpty()) {
						ebrimid = GetRegistryRegistryInfoUtils
								.GetRegistryPackageIDByEbrimContent(ebrimcontent);
					}
					if (ebrimid != null && !ebrimid.trim().isEmpty()) {
						if (OperateSensorUtil.CheckSensorMLExistMethod(
								username, ebrimid)) {
							OperateSensorUtil.DeleteSensorML(username,
									password, ebrimid);
						}
						return 1;
					} else {
						return 0;
					}
				} else {
					return 0;
				}
			} else if (operationtype.trim().toLowerCase().equals("insert")
					|| operationtype.trim().toLowerCase().equals("update")) {
				// 插入更新 操作
				if (null != StringUtil
						.checkStringIsNotNULLAndEmptyMethod(sensormlcontent)) {
					// 如果 sensorMLContent内容不能为空，否则结束
					if (null != StringUtil
							.checkStringIsNotNULLAndEmptyMethod(id)) {
						// 如果用户提供的id为有效的id
						if (OperateSensorUtil.CheckSensorMLExistMethod(
								id.trim()).getIsExist()) {
							// ，如果用户提供的id在数据库存在，则 删除该id所代表的全部的该条记录
							if (!OperateSensorUtil.DeleteSensorML(username,
									password, id)) {
								return 2;
							}
						}
						if (OperateSensorUtil.SaveSensorML(username, password,
								id, sensormlcontent)) {
							return 1;
						} else {
							return 0;
						}

					} else {
						// 这里针对id不合法的处理结果
						// 获取传递过来的sensorml的id（registrypackage的id值），并进行检测数据库存在数据库
						String ebrimcontent = "";
						try {
							ebrimcontent = new TransformSensorMLToebRIMService()
									.TransactionSensorMLToeEbRIMMethod(
											username, password, sensormlcontent);
						} catch (ServiceException e1) {
							e1.printStackTrace();
						}
						String ebrimid = "";
						if (ebrimcontent != null && !ebrimcontent.isEmpty()) {
							ebrimid = GetRegistryRegistryInfoUtils
									.GetRegistryPackageIDByEbrimContent(ebrimcontent);
						}
						if (null != StringUtil
								.checkStringIsNotNULLAndEmptyMethod(ebrimid)) {
							// 删除存在的记录
							if (OperateSensorUtil.CheckSensorMLExistMethod(
									ebrimid).getIsExist()) {
								if (!OperateSensorUtil.DeleteSensorML(username,
										password, ebrimid)) {
									return 2;// 发生异常
								}
							}

							if (OperateSensorUtil.SaveSensorML(username,
									password, ebrimid, sensormlcontent)) {
								return 1;
							} else {
								return 2;
							}
						} else {
							return 2;
						}
					}

				} else {
					return 0;
				}

			} else {
				return 0;// 没有执行insert, delete ，udapte 三大操作，就不进行任何操作
			}
		} else {
			return 2;// 没有设置
		}
	}

	/**
	 * 将SensorML文档通过使用xslFile转换为EbRIM格式的内容
	 * 
	 * @param sensormlfile
	 *            需要转换的SensorML文件
	 * @param ebrimfile
	 *            最终转换到的EbRIM文件
	 * @param xslfile
	 *            转换需要的XSL文件
	 * @throws FileExistException
	 * @throws TransactionProcessException
	 * @throws UnsupportedEncodingException
	 */
	public static String TransformSensorMLToEbRIMMethod(String sensormlcontent)
			throws FileExistException, TransactionProcessException,
			UnsupportedEncodingException {
		TransformerFactory tf = TransformerFactory.newInstance();
		String basepath = new GetRealPathUtil().getWebInfPath();
		String xslfilepath = basepath + "transformxsls\\SensorMLToEbRIM.xsl";
		Source source = new StreamSource(new File(xslfilepath));
		try {
			Transformer t = tf.newTransformer(source);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream inputStream = new ByteArrayInputStream(sensormlcontent
					.getBytes("UTF-8"));
			Source smlSource = new StreamSource(inputStream);
			StreamResult result = new StreamResult(baos);
			t.transform(smlSource, result);
			return baos.toString("UTF-8");
		} catch (TransformerConfigurationException e) {
			throw new TransactionProcessException(
					"在SensorML转换为EbRIM过程中转换配置信息出现异常!");
		} catch (TransformerException e) {
			throw new TransactionProcessException("在SensorML转换为EbRIM过程出现异常!");
		}
	}

	/**
	 * 获取所有用户或者自身用户的文档的基本信息，包括id，上传时间，文件名称
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param type
	 *            用户需要的类型，true是查询所有的用户的文档的信息，false则是查询自身的信息
	 * @return 返回的是用户上传文件的基本信息，id，文件名，创建时间,没有则返回null来替代相应的字段
	 */
	public List<SensorMLType> GetAllBasicInfoOfSensorMLMethod(String username,
			String password, boolean type) {
		return OperateSensorUtil.GetAllSensorMLBasicInfo(OperateSensorUtil
				.GetAllSensorIdsMethod());
	}

	/**
	 * 获取单个传感器的sensorml
	 * 
	 * @param sensorid
	 * @return
	 */
	public static String GetSensorMLBySensorid(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		Clob clob = null;
		try {
			clob = (Clob) session.createQuery(
					"select sensorcontent from SensorML where sensorid='"
							+ sensorid.trim() + "'").iterate().next();
			String content = clob.getSubString(1, (int) clob.length());
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			clob = null;
			return content;
		} catch (SQLException e) {
			e.printStackTrace();
			GetSessionUtil.CloseSessionInstance(session);
			return null;
		}
	}

	/**
	 * 获取所有传感器的类型
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> GetAllSensorTypes() {
		List<String> results = new ArrayList<String>();
		String queryStr = "from Slot where  name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::SensorType'";
		List<Slot> slot = GetSessionUtil.GetSelectQueryInfo(queryStr);
		String sensortype = null;
		for (Slot s : slot) {
			sensortype = s.getValues().substring(0, s.getValues().length() - 1);
			if (!results.contains(sensortype)) {
				results.add(sensortype);
			}
		}
		System.gc();
		return results;
	}

	/**
	 * 检查指定的username和sensorid的文档是否存在
	 * 
	 * @param username
	 *            用户名称
	 * @param sensormlid
	 *            用户查询的sensorml的id值,是ebrim格式的id
	 * @return true：存在，false： 不存在或者存储的sensormlcontent为空则也返回false
	 */
	@SuppressWarnings("unchecked")
	public static Boolean CheckSensorMLExistMethod(String username,
			String sensormlid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensormlid)) {
			return false;
		}
		sensormlid = StringUtil.AppendPacakgeStr(sensormlid);
		String queryStr = "from RegistryPackage where ower='" + username + "'"
				+ " and id = '" + sensormlid + "'";
		List<RegistryPackage> lists = GetSessionUtil
				.GetSelectQueryInfo(queryStr);
		if (lists != null && lists.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查sensorid的文档是否存在(全局）
	 * 
	 * @param sensormlid
	 *            用户查询的sensorml的id值,是ebrim格式的id
	 * @return true：存在，false： 不存在或者存储的sensormlcontent为空则也返回false
	 */
	@SuppressWarnings("unchecked")
	public static SensorExistBoolean CheckSensorMLExistMethod(String sensormlid) {
		SensorExistBoolean seb = new SensorExistBoolean();
		seb.setSensorid(sensormlid);
		// 判断sensorML是否为null或者其值为空
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensormlid)) {
			return null;
		}
		sensormlid = StringUtil.AppendPacakgeStr(sensormlid);
		String queryStr = "from RegistryPackage where id ='"
				+ sensormlid.trim() + "'";
		List<RegistryPackage> lists = GetSessionUtil
				.GetSelectQueryInfo(queryStr);
		if (lists != null && lists.size() > 0) {
			RegistryPackage rp = lists.get(0);
			String owner = rp.getOwer();
			seb.setOwner(owner);
			seb.setIsExist(true);
		} else {
			seb.setIsExist(false);
		}
		lists = null;
		return seb;
	}

	/**
	 * 核实传感器标识符是否存在
	 * 
	 * @param sensorid
	 * @return
	 */
	public static boolean CheckSensorIdIsExistMethod(String sensorid) {
		return CheckSensorMLExistMethod(sensorid).getIsExist();
	}

	/**
	 * 获取所有的传感器的基本的信息
	 * 
	 * @param filename
	 *            需要查询的基本的字段的名称
	 * @param sensorid
	 *            需要查询的传感器的id值
	 * @return 返回查询所获取的信息
	 */
	public static String GetSensorBasicInfoByFieldNameMethod(String filename,
			String sensorid) {
		// 中文输入
		if (filename.equals("sensorBasicChineseIputs")) {
			SensorInputInfoType siit = OperateSensorUtil
					.GetSensorChineseInputInfo(sensorid);
			if (siit != null) {
				return siit.getChineseinputinfo();
			} else {
				return null;
			}
		}
		// 中文输出
		else if (filename.equals("sensorBasicChineseOutputs")) {
			SensorOutputType siit = OperateSensorUtil
					.GetSensorChineseOutputInfo(sensorid);
			if (siit != null) {
				return siit.getChineseoutputinfo();
			} else {
				return null;
			}
		}
		// 查询传感器观测范围
		else if (filename.equals("sensorBasicObservedRange")) {
			return OperateSensorUtil.GetSensorGeoInfo(sensorid);
		}
		// 查询传感器的位置信息
		else if (filename.equals("sensorBasicPosition")) {
			return OperateSensorUtil.GetSensorPosInfo(sensorid);
		}
		// 查询传感器的全称信息
		else if (filename.equals("sensorBasicLongName")) {
			return OperateSensorUtil.GetSensorLongName(sensorid);
		}
		// 查询传感器的简称信息
		else if (filename.equals("sensorBasicShortName")) {
			return OperateSensorUtil.GetSensorShortNameMethod(sensorid);
		}
		// 查询传感器的类型信息
		else if (filename.equals("sensorBasicSensorType")) {
			return OperateSensorUtil.GetSensorSensorTypeMethod(sensorid.trim());
		}
		// 查询传感器的关键字信息
		else if (filename.equals("sensorBasicKeywords")) {
			return OperateSensorUtil.GetSensorKeyWordInfo(sensorid);
		}
		// 查询传感器的预期应用
		else if (filename.equals("sensorBasicIntendApplication")) {
			return OperateSensorUtil.GetSensorIntendAppInfo(sensorid);
		}
		// 查询传感器的开始工作时间
		else if (filename.equals("sensorBasicValidTimeBegin")) {
			return OperateSensorUtil.GetSensorWorkBeginTime(sensorid);
		}
		// 查询传感器的结束工作时间
		else if (filename.equals("sensorBasicValidTimeEnd")) {
			return OperateSensorUtil.GetSensorWorkEndTime(sensorid);
		}
		// 查询获取传感器的输出
		else if (filename.equals("sensorBasicOutputs")) {
			return OperateSensorUtil.GetSensorOutputsInfo(sensorid);
		}
		// 查询获取传感器的输入
		else if (filename.equals("sensorBasicInputs")) {
			return OperateSensorUtil.GetSensorInputsInfo(sensorid);
		}
		// 查询平台搭载的传感器的标识名称
		else if (filename.equals("sensorBasicAssociatedSensorName")) {
			return OperateSensorUtil
					.GetSensorAssociatedSesnorNameInfo(sensorid);
		}
		// 查询传感器的型号
		else if (filename.equals("sensorBasicModelNumber")) {
			return OperateSensorUtil.GetSensorModelNumberInfo(sensorid);
		}
		// 查询传感器的制造厂商
		else if (filename.equals("sensorBasicManufacturer")) {
			return OperateSensorUtil.GetSensorManufacturerInfo(sensorid);
		}
		// 查询传感器的所属平台名称
		else if (filename.equals("sensorBaicParentSystemStandardName")) {
			return OperateSensorUtil
					.GetSensorParentSystemStandardNameInfo(sensorid);
		}
		// 获取传感器的扫描类型
		else if (filename.equals("sensorBasicScannerType")) {
			return OperateSensorUtil.GetSensorscannerTypeInfo(sensorid);
		}
		// 查询传感器的轨迹类型信息
		else if (filename.equals("sensorBasicOrbitType")) {
			return OperateSensorUtil.GetSensorOrbitTypeInfo(sensorid);
		}
		// 其他暂不查询
		else {
			// 查询的其他的则返回null
			return null;
		}
	}

	/**
	 * 获取传感器的物理属性的普遍模型方法
	 * 
	 * @param sensorid
	 *            需要查询的传感器的id
	 * @param capability
	 *            需要查询的在物理属性方面的指标
	 * @return 返回相对应的,如果没有，则返回null
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorPhysicalPropertyMethod(String sensorid,
			String physicalproperty) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		String resultStr = "";
		try {
			String prefix = "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::physicalProperties:property:";
			String physicalpropertylitter = physicalproperty.substring(0, 1)
					.toLowerCase().concat(physicalproperty.substring(1));
			String queryStr = "select values from Slot where ( name ='"
					+ prefix + physicalproperty + "' or name='" + prefix
					+ physicalpropertylitter + "') and  id like '"
					+ sensorid.trim() + "%" + "'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);

			if (values == null || values.size() == 0) {
				return null;
			}
			for (int i = 0; i < values.size(); i++) {
				resultStr += values.get(i).substring(0,
						values.get(i).length() - 1)
						+ " ,";
			}
			values = null;
			System.gc();
			return resultStr.substring(0, resultStr.length() - 1).trim();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询传感器的测量能力的模型方法
	 * 
	 * @param sensorid
	 *            查询的传感器的标识符
	 * @param capability
	 *            需要查询的传感器的能力
	 * @return 返回传感器该指标上的能力描述，没有则返回null
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorCommonCapabilityMethod(String sensorid,
			String capability) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		String resultStr = "";
		try {
			String prefix = "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::measurementCapabilities:";
			String capabilitylitter = capability.substring(0, 1).toLowerCase()
					.concat(capability.substring(1));
			String sqlStr = "select values from Slot where id like '"
					+ sensorid.trim() + "%'" + " and (name ='" + prefix
					+ capability + "' or name = '" + prefix + capabilitylitter
					+ "')";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(sqlStr);

			if (values == null || values.size() == 0) {
				return null;
			}
			String str = "";
			for (int i = 0; i < values.size(); i++) {
				str = values.get(i);
				resultStr += str.substring(0, str.length() - 1) + " ,";
			}
			return resultStr.substring(0, resultStr.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器所属组织信息普遍模型方法
	 * 
	 * @param sensorid
	 *            需要查询的传感器的id
	 * @param capability
	 *            需要查询的在物理属性方面的指标
	 * @return 返回相对应的,如果没有，则返回null
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorOrganizationInfoMethod(String sensorid) {
		try {
			String sqlStr = "select value from LocalizedString where id like '"
					+ sensorid.trim() + ":org:%:Name" + "'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(sqlStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			return values.get(0).trim();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器的计算能力的普遍模型方法
	 * 
	 * @param sensorid
	 *            需要查询的传感器的id
	 * @param capability
	 *            需要查询的在计算能力方面的指标
	 * @return 返回相对应的,如果没有，则返回null
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorComputeCapabilityMethod(String sensorid,
			String capability) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		String resultStr = "";
		try {
			String prefix = "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::computingCapability:";
			String capabilitylitter = capability.substring(0, 1).toLowerCase()
					.concat(capability.substring(1));
			String sqlStr = "select values from Slot where ( name ='" + prefix
					+ capability + "' or name='" + prefix + capabilitylitter
					+ "') and  id like '" + sensorid.trim() + "%'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(sqlStr);
			if (values == null || values.size() == 0) {
				return null;
			} else {
				for (int i = 0; i < values.size(); i++) {
					resultStr += values.get(i).substring(0,
							values.get(i).length() - 1)
							+ " ,";
				}
				return resultStr.substring(0, resultStr.length() - 1).trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器的通信能力的普遍模型方法
	 * 
	 * @param sensorid
	 *            需要查询的传感器的id
	 * @param capability
	 *            需要查询的在通信能力方面的指标
	 * @return 返回相对应的,如果没有，则返回null
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorCommunicationCapabilityMethod(
			String sensorid, String capability) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		String resultStr = "";
		try {
			session.beginTransaction().begin();
			String prefix = "urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::communicationCapabilities:";
			String capabilitylitter = capability.substring(0, 1).toLowerCase()
					.concat(capability.substring(1));
			List<String> values = (List<String>) session.createQuery(
					"select values from Slot where (name ='" + prefix
							+ capability + "' or name='" + prefix
							+ capabilitylitter + "' )and  id like '"
							+ sensorid.trim() + "%" + "'").list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (values == null || values.size() == 0) {
				return null;
			}
			for (int i = 0; i < values.size(); i++) {
				resultStr += values.get(i).substring(0,
						values.get(i).length() - 1)
						+ " ,";
			}
			return resultStr.substring(0, resultStr.length() - 1).trim();

		} catch (Exception e) {
			e.printStackTrace();
			GetSessionUtil.CloseSessionInstance(session);
			return null;
		}
	}

	/**
	 * 获取传感器的轨道类型
	 * 
	 * @param sensorid
	 *            传感器的标识符
	 * @return 返回传感器的轨道类型
	 */
	@SuppressWarnings( { "unchecked" })
	public static String GetSensorOrbitTypeInfo(String sensorid) {

		// 检验用户名与 密码在数据库中是否真正存在
		String resultStr = "";
		// 获取传感器的的传感器的类型
		// 步骤是连接到LocalizedString数据表中
		// 根据id值获得相应的value值
		// 返回该值
		// Session session = new
		// Configuration().configure().buildSessionFactory()
		// .openSession();
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			Criteria criteria = session
					.createCriteria(ClassificationNode.class);
			criteria.add(Expression.like("id", sensorid + "%"));
			criteria
					.createCriteria("parent")
					.add(
							Expression
									.eq("home",
											"urn:ogc:def:classificationScheme:OGC-CSW-ebRIM-Sensor::OritTypes"))
					.add(Expression.like("id", sensorid + "%"));
			List<ClassificationNode> cns = (List<ClassificationNode>) criteria
					.list();

			if (cns != null && cns.size() > 0) {
				for (int i = 0; i < cns.size(); i++) {
					Set<LocalizedString> ls = (Set<LocalizedString>) cns.get(i)
							.getName().getLocalizedStrings();
					if (ls != null && ls.size() > 0) {
						for (Iterator it = ls.iterator(); it.hasNext();) {
							resultStr += ((LocalizedString) it.next())
									.getValue().trim()
									+ " ,";
						}
					}
				}
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstance(session);
			} else {
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstance(session);
				return null;
			}
			return resultStr.substring(0, resultStr.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			GetSessionUtil.CloseSessionInstance(session);
			return null;
		}
	}

	/**
	 * 查询传感器型号
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorModelNumberInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();
			List<String> values = (List<String>) session
					.createQuery(
							"select values from Slot where name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::modelNumber' and id like '"
									+ sensorid.trim() + "%'").list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (values == null || values.size() == 0) {
				return null;
			}
			return values.get(0).toString().substring(0,
					values.get(0).trim().length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			GetSessionUtil.CloseSessionInstance(session);
			return null;

		}
	}

	/**
	 * 获取传感器扫描类型
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorscannerTypeInfo(String sensorid) {

		// 检验用户名与 密码在数据库中是否真正存在
		String resultStr = "";
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			Criteria criteria = session
					.createCriteria(ClassificationNode.class);
			criteria.add(Expression.like("id", sensorid + "%"));
			criteria
					.createCriteria("parent")
					.add(
							Expression
									.eq("home",
											"urn:ogc:def:classificationScheme:OGC-CSW-ebRIM-Sensor::scannerType"))
					.add(Expression.like("id", sensorid + "%"));
			List<ClassificationNode> cns = (List<ClassificationNode>) criteria
					.list();

			if (cns != null && cns.size() > 0) {
				for (int i = 0; i < cns.size(); i++) {
					Set<LocalizedString> ls = (Set<LocalizedString>) cns.get(i)
							.getName().getLocalizedStrings();
					if (ls != null && ls.size() > 0) {
						for (Iterator it = ls.iterator(); it.hasNext();) {
							resultStr += ((LocalizedString) it.next())
									.getValue().trim()
									+ " ,";
						}
					}
				}
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstance(session);
			} else {
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstance(session);
				return null;
			}
			return resultStr.substring(0, resultStr.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			GetSessionUtil.CloseSessionInstance(session);
			return null;
		}
	}

	/**
	 * 获取传感器的制造厂商信息
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorManufacturerInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();
			List<String> values = (List<String>) session
					.createQuery(
							"select values from Slot where name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::manufacturer' and id like '"
									+ sensorid.trim() + "%'").list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (values == null || values.size() == 0) {
				return null;
			}
			return values.get(0).toString().substring(0,
					values.get(0).trim().length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			GetSessionUtil.CloseSessionInstance(session);
			return null;

		}

	}

	/**
	 * 获取传感器的搭载的传感器的平台名称
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorParentSystemStandardNameInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();
			List<String> values = (List<String>) session
					.createQuery(
							"select values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::parentSystemStandardName' and id like '"
									+ sensorid.trim() + "%'").list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (values == null || values.size() == 0) {
				return null;
			}
			return values.get(0).substring(0, values.get(0).length() - 1)
					.toString();

		} catch (Exception e) {
			GetSessionUtil.CloseSessionInstance(session);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取所有传感器的标示符的信息，组成集合。
	 * 
	 * @return 返回所有的传感器的标识符的信息集合，如果没有的话，返回null
	 */
	@SuppressWarnings("unchecked")
	public static List<String> GetAllSensorIdsMethod() {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();
			List<String> values = (List<String>) session.createQuery(
					"select id from ExtrinsicObject").list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (values == null || values.size() == 0) {
				return null;
			} else {
				return values;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			GetSessionUtil.CloseSessionInstance(session);
		}
	}

	/**
	 * 获取所有传感器的类型的信息的方法
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> GetAllSensorSensorTypeMethod() {
		List<String> resultList = new ArrayList<String>();
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();
			List<String> values = (List<String>) session
					.createQuery(
							"select distinct values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::SensorType'")
					.list();
			if (values == null || values.size() == 0) {
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstance(session);
				return null;
			} else {
				session.beginTransaction().commit();
				for (String str : values) {
					resultList.add(str.trim().substring(0, str.length() - 1)
							.trim());
				}
				GetSessionUtil.CloseSessionInstance(session);
				return resultList;
			}

		} catch (Exception e) {
			e.printStackTrace();
			GetSessionUtil.CloseSessionInstance(session);
			return null;
		}
	}

	/**
	 * 获取特定传感器的类型的方法
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorSensorTypeMethod(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid.trim());
		String sensortype;
		try {
			String sqlStr = "select values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::SensorType' and id like '"
					+ sensorid.trim() + "%'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(sqlStr);
			if (values == null || values.size() == 0) {
				return null;
			} else {
				if (values.get(0).length() == 1) {
					return null;
				}
				sensortype = values.get(0).substring(0,
						values.get(0).length() - 1);
			}
			return sensortype;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取全部传感器平台标识符
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> GetAllPlatformIds(String platformtype) {
		List<String> platforms = new ArrayList<String>();
		String queryStr = "from Slot where name like '%associatedSensorUniqueID'";
		List<Slot> slot = GetSessionUtil.GetSelectQueryInfo(queryStr);
		for (Slot s : slot) {
			String platformid = s.getId().split(":ExtrinsicObject:")[0];
			// 防止出现重复标识符
			if (!platforms.contains(platformid)) {
				// 获取平台的类型
				String tableplatfromtype = GetSensorSensorTypeMethod(platformid);
				if (platformtype.equals("all")) {
					platforms.add(platformid);
				} else if (tableplatfromtype.equals(platformtype)) {
					platforms.add(platformid);
				}
			}
		}
		return platforms;
	}

	public static void main(String[] args) {
		// RemoteSensorPlatform
		// GetPlatFormandSensorsMethod("admin", "cswadmin", "all");
		List<String> strs = GetAllSensorIdsMethod();
		for (String s : strs) {
			System.out.println(s);
		}
	}

	/**
	 * 获取所有的站点和传感器的序列
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param platfromtype
	 *            平台类型
	 * @return 返回的站点序列
	 */
	public static List<PlatformandSensors> GetPlatFormandSensorsMethod(
			String username, String password, String platfromtype) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(platfromtype)) {
			return null;
		}
		// 获取所有指定平台类型中平台和传感器的标识符
		List<PlatformandSensors> tempallplatandsneosrs = OperateSensornewUtil
				.GetAllPlatformsandSensorIds(platfromtype);
		long pre = System.currentTimeMillis();
		// 获取全部的传感器的基本的信息
		List<SensorBasciForOracleType> sbfot = OperateSensornewUtil
				.GetAllSensorBasicInfo(false, false);
		long now = System.currentTimeMillis();
		// System.out.println(now - pre + "毫秒");
		if (sbfot.isEmpty()) {
			return null;
		}
		if (tempallplatandsneosrs != null) {
			String str = "";
			String[] temp = null;
			for (PlatformandSensors ps : tempallplatandsneosrs) {
				for (SensorBasciForOracleType s : sbfot) {
					if (s.getSENSORID().equals(ps.getPlatform().getSensor())) {
						ps.getPlatform().setSensorname(s.getSENSORLONGNAME());
						ps.getPlatform().setSensortype(s.getSENSORTYPE());
						try {
							str = s.getSENSORSOSURL();
							temp = str.split(",");
							if (temp.length == 2) {
								ps.getPlatform().setSensorsosurl(temp[0]);
								ps.getPlatform().setSensoroffering(temp[1]);
							} else {
								ps.getPlatform().setSensorsosurl(str);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				if (!ps.getSensors().isEmpty()) {
					for (SensorInfo si : ps.getSensors()) {
						for (SensorBasciForOracleType s : sbfot) {
							if (s.getSENSORID().equals(si.getSensor())) {
								si.setSensorname(s.getSENSORLONGNAME());
								si.setSensortype(s.getSENSORTYPE());
								try {
									str = s.getSENSORSOSURL();
									temp = str.split(",");
									if (temp.length == 2) {
										si.setSensorsosurl(temp[0]);
										si.setSensoroffering(temp[1]);
									} else {
										ps.getPlatform().setSensorsosurl(str);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
		return tempallplatandsneosrs;
	}

	/**
	 * 返回传感器的共享级别信息序列
	 * 
	 * @param sensorid
	 *            需要查询的传感器的标识符序列
	 * @return 返回传感器共享级别信息序列
	 */
	public static SensorShareLevel GetSensorShareLevel(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		SensorShareLevel ssl = new SensorShareLevel();
		ssl.setSensorid(sensorid);
		String shareStr = GetSensorClassificationNodeInfo(sensorid,
				"urn:ogc:def:classificationScheme:OGC-CSW-ebRIM-Sensor::sharingLevel");
		ssl.setSharelevel(shareStr);
		if (shareStr != null) {
			if (shareStr.trim().equals("公开")) {
				ssl.setIsShare(true);
			} else {
				ssl.setIsShare(false);
			}

		} else {
			ssl.setIsShare(false);
		}
		return ssl;
	}

	/**
	 * 返回传感器的共享级别信息
	 * 
	 * @param sensorid
	 *            需要查询的传感器的标识符序列
	 * @return 返回传感器共享级别信息序列
	 */
	public static List<SensorShareLevel> GetSensorShareLevels(
			List<String> sensorids) {
		List<SensorShareLevel> sss = new ArrayList<SensorShareLevel>();
		if (sensorids != null) {
			List<SensorShareLevel> tempssls = OperateSensornewUtil
					.GetAllSensorShareLevels();
			for (String sensorid : sensorids) {
				for (SensorShareLevel ssl : tempssls) {
					if (ssl.getSensorid().equals(sensorid)) {
						sss.add(ssl);
						continue;
					}
				}
			}
		}
		return sss;
	}

	/**
	 * 返回传感器是否可控信息，true，可控，false 不可控
	 * 
	 * @param sensorid
	 *            传感器标识符
	 * @return 返回传感器是否可控
	 */
	@SuppressWarnings("unchecked")
	public static SensorOperable GetSensorOperatable(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		SensorOperable so = new SensorOperable();
		so.setSensorid(sensorid);
		String queryStr = "select values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::physicalProperties:property:isOperable' and id like '"
				+ sensorid.trim() + "%'";
		List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
		if (values != null && values.size() != 0) {
			String value = values.get(0);
			String bolStr = value.substring(0, value.length() - 1);

			if (bolStr.equals("true")) {
				so.setIsOperable(true);
			} else {
				so.setIsOperable(false);
			}
		} else {
			so.setIsOperable(false);
		}
		return so;
	}

	/**
	 * 返回传感器序列是否可控信息，true，可控，false 不可控
	 * 
	 * @param sensorids
	 *            需要查询的传感器序列集合
	 * @return 返回传感器是否可控序列
	 */
	public static List<SensorOperable> GetSensorOperatables(
			List<String> sensorids) {
		List<SensorOperable> sos = new ArrayList<SensorOperable>();
		if (sensorids != null) {
			List<SensorBasciForOracleType> sbfot = OperateSensornewUtil
					.GetAllSensorBasicInfo(false, false);
			SensorOperable so = null;
			for (String sensorid : sensorids) {
				so = new SensorOperable();
				so.setSensorid(sensorid);
				for (SensorBasciForOracleType sbf : sbfot) {
					if (sbf.getSENSORID().equals(sensorid)) {
						if (sbf.getSENSOROPERABLE().equals("true")) {
							so.setIsOperable(true);
						} else {
							so.setIsOperable(false);
						}
						continue;
					}
				}
				sos.add(so);
			}
		}
		return sos;
	}

	/**
	 * 获取单个传感器的关键字
	 * 
	 * @param sensorid
	 *            传感器的标识符
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorKeyWordInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		try {
			String queryStr = "select values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::Keywords' and id like '"
					+ sensorid.trim() + "%'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values != null && values.size() != 0) {
				return values.get(0).substring(0, values.get(0).length() - 1);
			} else {
				return null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器的空间范围信息
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSneosrGeoInfoMethod(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		try {
			String queryStr = "select values from Slot where name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::BoundedBy' and id like '"
					+ sensorid.trim() + "%'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			return values.get(0).substring(1, values.get(0).length() - 2);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询指定传感器的全部输入参数
	 * 
	 * @param sensorids
	 * @return
	 */
	public static List<SensorInputInfoType> GetSensorChineseInputInfoList(
			List<String> sensorids) {
		if (sensorids == null || sensorids.size() == 0) {
			return null;
		}
		List<SensorInputInfoType> siits = new ArrayList<SensorInputInfoType>();
		for (String sensorid : sensorids) {
			SensorInputInfoType siit = GetSensorChineseInputInfo(sensorid);
			if (siit != null) {
				siits.add(siit);
			}
		}
		return siits;
	}

	/**
	 * 查询传感器的中文输入信息
	 * 
	 * @param sensorid
	 * @return 返回SensorInputInfoType
	 */
	@SuppressWarnings("unchecked")
	public static SensorInputInfoType GetSensorChineseInputInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		try {
			String queryStr = "select values from Slot where name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::ChineseInputs' and id like '"
					+ sensorid.trim() + "%'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			String tempstr = values.get(0);
			String valueString = tempstr.substring(0, tempstr.length() - 1);
			valueString = valueString.replace(",,", ",");
			SensorInputInfoType siType = new SensorInputInfoType();
			siType.setSensorid(sensorid);
			siType.setChineseinputinfo(valueString);
			return siType;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器的输入参数
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorInputsInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		try {
			String queryStr = "select values from Slot where name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::Inputs' and id like '"
					+ sensorid.trim() + "%'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			String tempstr = values.get(0);
			String valueString = tempstr.substring(0, tempstr.length() - 1);
			valueString = valueString.replace(",,", ",");
			return valueString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器的全称
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorLongName(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		// 获取传感器的的全名
		// 步骤是连接到LocalizedString数据表中
		// 根据id值获得相应的value值
		// 返回该值
		try {
			String queryStr = "select value from LocalizedString where id='"
					+ sensorid.trim() + ":LName" + "'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			return values.get(0).toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取多个传感器的中文输出信息
	 * 
	 * @param sensorids
	 * @return
	 */
	public static List<SensorOutputType> getSensorChineseOutputInfoList(
			List<String> sensorids) {
		if (sensorids == null || sensorids.size() == 0) {
			return null;
		}
		List<SensorOutputType> sots = new ArrayList<SensorOutputType>();
		String inStr = "";
		for (String sensorid : sensorids) {

			SensorOutputType sot = GetSensorChineseOutputInfo(sensorid);
			if (sot != null) {
				sots.add(sot);
			}
		}
		return sots;
	}

	/**
	 * 获取单个传感器的中文输入信息
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static SensorOutputType GetSensorChineseOutputInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		try {
			String queryStr = "select values from Slot where name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::ChineseOutputs' and id like '"
					+ sensorid.trim() + "%'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			String tempstr = values.get(0);
			String valueString = tempstr.substring(0, tempstr.length() - 1);
			valueString = valueString.replace(",,", ",");
			SensorOutputType soType = new SensorOutputType();
			soType.setSensorid(sensorid);
			soType.setChineseoutputinfo(valueString);
			return soType;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器的输出参数
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorOutputsInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		try {
			String queryStr = "select values from Slot where name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::Outputs' and id like '"
					+ sensorid.trim() + "%'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			String tempstr = values.get(0);
			String valueString = tempstr.substring(0, tempstr.length() - 2);

			valueString = valueString.replace(",,", ",");
			return valueString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 返回指定的传感器的关于在分类节点中某一方面如intendedapplication，sharelevel等信息
	 * 
	 * @param sensorid
	 *            需要查询的传感器标识符
	 * @param homeaspect
	 *            需要查询的方面，如intendedapplication,sharelevel
	 * @return 返回相关信息
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorClassificationNodeInfo(String sensorid,
			String homeaspect) {
		String resultStr = "";
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			Criteria criteria = session
					.createCriteria(ClassificationNode.class);
			criteria.add(Expression.like("id", sensorid + "%"));
			criteria.createCriteria("parent").add(
					Expression.like("id", sensorid + "%")).add(
					Expression.eq("home", homeaspect));
			List<ClassificationNode> cns = (List<ClassificationNode>) criteria
					.list();
			if (cns != null && cns.size() > 0) {
				for (int i = 0; i < cns.size(); i++) {
					Set<LocalizedString> ls = (Set<LocalizedString>) cns.get(i)
							.getName().getLocalizedStrings();
					if (ls != null && ls.size() > 0) {
						for (Iterator it = ls.iterator(); it.hasNext();) {
							resultStr += ((LocalizedString) it.next())
									.getValue().trim()
									+ " ,";
						}
					}
				}
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstanceByOneTime(session);
				return resultStr.substring(0, resultStr.length() - 1);
			} else {
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstanceByOneTime(session);
				return null;
			}
		} catch (Exception e) {
			GetSessionUtil.CloseSessionInstanceByOneTime(session);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取单个传感器预期应用
	 * 
	 * @param sensorid
	 *            传感器的标识符
	 * @return
	 */
	public static String GetSensorIntendAppInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		return GetSensorClassificationNodeInfo(sensorid,
				"urn:ogc:def:classificationScheme:OGC-CSW-ebRIM-Sensor::IntendedApplication");
	}

	/**
	 * 获取传感器的开始工作时间
	 * 
	 * @param sensorid
	 *            需要查询的传感器标识符
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorWorkBeginTime(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		try {
			String queryStr = "select values from Slot where name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::ValidTimeBegin' and id like '"
					+ sensorid.trim() + "%'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			String str = values.get(0);
			return str.toString().substring(0, str.trim().length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器结束工作时间
	 * 
	 * @param sensorid
	 *            传感器的标识符
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorWorkEndTime(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		try {
			String queryStr = "select values from Slot where name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::ValidTimeEnd' and id like '"
					+ sensorid.trim() + "%'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			String str = values.get(0);
			return str.toString().substring(0, str.trim().length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器简称信息
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorShortNameMethod(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		try {
			String queryStr = "select values from Slot where name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::ShortName' and id like '"
					+ sensorid.trim() + "%'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			return values.get(0).toString().substring(0,
					values.get(0).trim().length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	/**
	 * 获取传感器的位置信息
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorPosInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		String resultStr = "";
		try {
			String queryStr = "select values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::Location' and  id like '"
					+ sensorid.trim() + "%" + "'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			for (int i = 0; i < values.size(); i++) {
				String str = values.get(i);
				resultStr += str.substring(0, str.length() - 1) + " ,";
			}
			return resultStr.substring(0, resultStr.length() - 1).trim();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器的观测范围
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorGeoInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		try {
			String queryStr = "select values from Slot where name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::BoundedBy' and id like '"
					+ sensorid.trim() + "%'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			String str = values.get(0);
			return str.substring(1, str.length() - 2);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器组织信息
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorOrganInfo(String sensorid) {
		try {
			String queryStr = "select value from LocalizedString where id like '"
					+ sensorid.trim() + ":org:%:Name" + "'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			return values.get(0).trim();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器指定的测量属性
	 * 
	 * @param sensorid
	 * @param measureproperty
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorMeasurePropertyInfo(String sensorid,
			String measureproperty) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		String resultStr = "";
		String queryStr = "select values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::measurementCapabilities:"
				+ measureproperty + "' and  id like '" + sensorid + "%" + "'";
		List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
		if (values == null || values.size() == 0) {
			return null;
		}
		for (int i = 0; i < values.size(); i++) {
			String t = values.get(i);
			resultStr += t.substring(0, t.length() - 1) + " ,";
		}
		return resultStr.substring(0, resultStr.length() - 1);

	}

	/**
	 * 获取通信邮件
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetConnectionEmailInfo(String sensorid) {
		try {
			String queryStr = "select address from EmailAddress where id like '"
					+ sensorid.trim() + ":org:%" + "'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			return values.get(0).trim();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器的联系地址
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetConnectionEarthPosInfo(String sensorid) {
		try {
			String queryStr = "from PostalAddress where id like '"
					+ sensorid.trim() + "%" + "'";
			List<PostalAddress> values = GetSessionUtil
					.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			PostalAddress pa = values.get(0);
			String valueStr = "country: " + pa.getCountry() + ",City: "
					+ pa.getCity() + ",PostalCode:" + pa.getPostalCode()
					+ ",StateOrProvince:" + pa.getStateOrProvince()
					+ ",Street:" + pa.getStreet() + ",StreetNumber:"
					+ pa.getStreetNumber();

			return valueStr.trim();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器的计算能力属性
	 * 
	 * @param sensorid
	 * @param capability
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetComputePropertyInfo(String sensorid,
			String capability) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		String resultStr = "";
		try {
			String queryStr = "select values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::computingCapability:"
					+ capability
					+ "' and  id like '"
					+ sensorid.trim()
					+ "%"
					+ "'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			for (int i = 0; i < values.size(); i++) {
				resultStr += values.get(i).substring(0,
						values.get(i).length() - 1)
						+ " ,";
			}
			return resultStr.substring(0, resultStr.length() - 1).trim();
		} catch (Exception e) {
			e.printStackTrace();
			throw null;
		}
	}

	/**
	 *获取通信能力
	 * 
	 * @param sensorid
	 * @param capability
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetCommunicationPropertyInfo(String sensorid,
			String capability) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		String resultStr = "";
		try {
			String queryStr = "select values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::communicationCapabilities:"
					+ capability
					+ "' and  id like '"
					+ sensorid.trim()
					+ "%"
					+ "'";
			List<String> values = GetSessionUtil.GetSelectQueryInfo(queryStr);
			if (values == null || values.size() == 0) {
				return null;
			}
			for (int i = 0; i < values.size(); i++) {
				resultStr += values.get(i).substring(0,
						values.get(i).length() - 1)
						+ " ,";
			}
			return resultStr.substring(0, resultStr.length() - 1).trim();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器是否属于某一平台
	 * 
	 * @param sensorid
	 * @param platform
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean GetSensorIsBelongPlatform(String sensorid,
			String platform) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return false;
		}
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(platform)) {
			return false;
		}
		platform = StringUtil.DeletePackageStr(platform);
		String queryStr = "from Slot where id like '"
				+ platform
				+ "%' and name like '%associatedSensorUniqueID' and values like '"
				+ sensorid + "%'";
		List<Slot> slot = GetSessionUtil.GetSelectQueryInfo(queryStr);
		if (slot != null && slot.size() >= 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 保存传感器信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            保存的传感器标识符
	 * @param sensormlcontent
	 *            保存的sensorml的文档的内容
	 * @return 保存成功返回true，失败返回false
	 */
	public static boolean SaveSensorML(String username, String password,
			String sensorid, String sensormlcontent) {
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		SensorML sensorML = new SensorML();
		sensorML.setUsername(username);
		sensorML.setSensorid(sensorid);
		sensorML.setSavetime(new Date());// 设置时间
		sensorML.setSensorcontent(Hibernate.createClob(" "));
		session.save(sensorML);
		session.flush();
		session.refresh(sensorML, LockMode.UPGRADE);
		SerializableClob slob = (SerializableClob) sensorML.getSensorcontent();
		Clob wrapclob = slob.getWrappedClob();
		CLOB clob = (CLOB) wrapclob;
		Writer pw;
		try {
			pw = clob.getCharacterOutputStream();
			pw.write(sensormlcontent);
			pw.close();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除指定的传感器的SensorML文档
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static boolean DeleteSensorML(String username, String password,
			String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return false;
		}
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Long id;
		List list = session.createQuery(
				"select id from SensorML where sensorid='" + sensorid.trim()
						+ "' and username='" + username + "'").list();
		if (list != null && list.size() > 0) {
			id = (Long) list.get(0);
			int nums = 0;
			try {
				session.createQuery(
						"update SensorML set sensorcontent=empty_clob() where id="
								+ id).executeUpdate();
				nums = session.createQuery("delete SensorML where id=" + id)
						.executeUpdate();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (nums == 1) {
				System.out.println("删除成功 ");
				return true;
			} else {
				System.out.println("删除失败");
				return false;
			}
		} else {
			GetSessionUtil.CloseSessionInstance(session);
			return true;// 没有则删除成功
		}

	}

	/**
	 * 读取指定传感器的SensorML文档
	 * 
	 */
	public static String ReadSensorML(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Clob clob = null;
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		try {
			clob = (Clob) session.createQuery(
					"select sensorcontent from SensorML where sensorid='"
							+ sensorid.trim() + "'").iterate().next();
		} catch (Exception e) {
			e.printStackTrace();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return null;
		}
		try {
			String content = clob.getSubString(1, (int) clob.length());
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return content;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感器描述文档SensorML的基本信息
	 * 
	 * @param sensorids
	 *            传感器标识符集合
	 * @return
	 */
	public static List<SensorMLType> GetAllSensorMLBasicInfo(
			List<String> sensorids) {
		List<SensorMLType> smts = new ArrayList<SensorMLType>();
		if (sensorids != null) {
			for (String sensorid : sensorids) {
				SensorMLType smt = GetSensorMLBasicInfo(sensorid);
				if (smt != null) {
					smts.add(smt);
				}
			}
			return smts;
		} else {
			return null;
		}
	}

	/**
	 * 获取传感器描述文档SensorML的基本信息
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static SensorMLType GetSensorMLBasicInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		String queryStr = "from SensorML where sensorid='" + sensorid.trim()
				+ "'";
		List<SensorML> smList = GetSessionUtil.GetSelectQueryInfo(queryStr);
		if (smList != null && smList.size() > 0) {
			SensorMLType smType = new SensorMLType();
			SensorML sml = smList.get(0);
			smType.setSensorid(sml.getSensorid());
			smType.setOwner(sml.getUsername());
			smType.setSavetime(sml.getSavetime());
			return smType;
		} else {
			return null;
		}

	}
	/**
	 * 获取传感器的保存时间
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Date GetSensorMLSavedTime(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		if (sensorid != null && !sensorid.isEmpty()) {
			String queryStr = "from SensorML where sensorid='"
					+ sensorid.trim() + "'";
			List<SensorML> sensors = GetSessionUtil
					.GetSelectQueryInfo(queryStr);
			Date savetime = null;
			if (sensors != null) {
				savetime = sensors.get(0).getSavetime();
			}
			return savetime;
		} else {
			return null;
		}
	}

	/**
	 * 判断是否存在某一SensorML文档
	 * 
	 * @param username
	 *            用户名称
	 * @param sensorid
	 *            传感器标识符
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean IsExistsSensorML(String username, String sensorid) {
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		String queryStr = "from SensorML where sensorid='" + sensorid.trim()
				+ "' and username ='" + username + "'";
		List list = GetSessionUtil.GetSelectQueryInfo(queryStr);
		int nums = list.size();
		if (nums >= 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取传感器的搭载的标识符信息
	 * 
	 * @param sensorid
	 *            传感器的标识符
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorAssociatedSesnorNameInfo(String sensorid) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			return null;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		try {
			List<String> values = GetSessionUtil
					.GetSelectQueryInfo("select values from Slot where name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::associatedSensorName' and id like '"
							+ sensorid.trim() + "%'");
			if (values == null || values.size() == 0) {
				return null;
			}
			return values.get(0).toString().substring(0,
					values.get(0).trim().length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取用户上传的全部的传感器基本信息
	 * 
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SensorMLType> GetAllSensorMLInfoWithOwner(String username) {
		List<SensorMLType> smts = new ArrayList<SensorMLType>();
		String queryStr = "from SensorML where username ='" + username + "'";
		List<SensorML> sms = GetSessionUtil.GetSelectQueryInfo(queryStr);
		if (sms != null) {
			for (SensorML sm : sms) {
				SensorMLType smt = new SensorMLType();
				smt.setSavetime(sm.getSavetime());
				smt.setOwner(smt.getOwner());
				smt.setSensorid(smt.getSensorid());
				smts.add(smt);
			}
			return smts;
		} else {
			return null;
		}
	}

	/**
	 * 更新指定的传感器的sensorml
	 * 
	 * @param owner
	 * @param sensorid
	 * @param sensorml
	 * @return
	 */
	public static boolean UpdateSensorML(String owner, String password,
			String sensorid, String sensorml) {
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		if (sensorid != null) {
			SensorMLType smType = GetSensorMLBasicInfo(sensorid);
			if (smType != null) {
				if (smType.getOwner().equals(owner)) {
					// 第一步，删除
					if (!DeleteSensorML(owner, password, sensorid)) {
						return false;
					}
					// 第二步，插入
					if (SaveSensorML(owner, password, sensorid, sensorml)) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * 判断某传感器是否属于某用户
	 * 
	 * @param sensorid
	 *            传感器名称
	 * @param owner
	 *            用户
	 * @return true 属于用户，false 不属于用户
	 */
	@SuppressWarnings("unchecked")
	public static boolean CheckSensorIdIsBelongOwner(String sensorid,
			String owner) {
		if (sensorid == null) {
			return false;
		}
		sensorid = StringUtil.DeletePackageStr(sensorid);
		try {
			String queryStr = "from RegistryPackage where id='"
					+ sensorid.trim() + "' and  ower ='" + owner + "'";
			List list = GetSessionUtil.GetSelectQueryInfo(queryStr);
			int num = list.size();
			if (num > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
