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
 *��Ŀ����:CxfMyCsw �������� �˴� ������:Administrator ����ʱ��: 2013-9-10 ����03:23:09
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
			throw new DBObjectSaveException("���������ִ���" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				GetSessionUtil.CloseSessionInstance(session);
			}
			return object2;
		}
	}
	/**
	 * ���ݲ�ѯ������ѯ
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
	 * ��ȡ�ƶ��û���ȫ���Ĵ���������Ϣ
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
	 * �ж��Ƿ����ָ�����ĵ�����
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
	 * �������
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
			throw new DBObjectSaveException("���������ִ���" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				GetSessionUtil.CloseSessionInstance(session);
			}
			return object2;
		}
	}

	/**
	 * ɾ��ָ���Ĵ�������SensorML�ĵ�
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
				System.out.println("ɾ���ɹ� ");
				return true;
			} else {
				System.out.println("ɾ��ʧ��");
				return false;
			}
		} else {
			GetSessionUtil.CloseSessionInstance(session);
			return true;// û����ɾ���ɹ�
		}
	}
	/**
	 * ��ȡ����snesomrl���ĵ���MyebRIMSmlContents����
	 * 
	 * @param username
	 *            �û�����
	 * @param ebrimid
	 *            ��Ҫ��ȡ��sensorml��idֵ���ر�ע������������ebrim��ʽ��idֵ ������������:pacakge
	 * @param type
	 *            ��ѯ�ķ�Χ��true�����ѯ����ĵ��⣬false���ѯָ���û����ϴ��ĵ��ĵ��ļ���
	 * @return �����ƶ���MyebRIMSmlContentsʵ��
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
	 * ��system��component��processModel��SensorMLת��ΪEbRIM��ʽ����
	 * 
	 * @param sensorml
	 *            system,component,processModel��SensorML����
	 * @param sensormlfilepath
	 *            SensorML�洢�ļ�·��
	 * @param processmodelxsltpath
	 *            ProcessModelPath ת���ļ�·��
	 * @param xslfilepath
	 *            system or component ��ת���ļ�·��
	 * @param ebrimfilepath
	 *            ת�����EbRIM�����ļ�·��
	 * @return ����ת���õ�EbRIM��ʽ����
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
	 * ��ȡ��ѯ�Ľṹ��Ϣ��һ����List����
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
	 * �ж��Ƿ����ĳһSensorML�ĵ�
	 * 
	 * @param username
	 *            �û�����
	 * @param sensorid
	 *            ��������ʶ��
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
	 * ��ȡָ���û��Ĵ�������ʶ��
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getSensorMLIdsOfUser(String username) {
		return (List<String>) queryObject("select sensorid from SensorML where username='"
				+ username + "'");
	}

	/**
	 * ���sensorid���ĵ��Ƿ����(ȫ�֣�
	 * 
	 * @param sensormlid
	 *            �û���ѯ��sensorml��idֵ,��ebrim��ʽ��id
	 * @return true�����ڣ�false�� �����ڻ��ߴ洢��sensormlcontentΪ����Ҳ����false
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
	 * �Դ��ݹ�����sensorml����ĳ�ֲ���
	 * 
	 * @param id
	 *            ���ĵ���id��������sensorml��ʽ��id��Ҳ������ebrim��ʽ��id
	 * @param sensormlcontent
	 *            ���ݹ�����sensorml���ĵ�������
	 * @param filename
	 *            �ļ�����
	 * @param createtime
	 *            �ļ�������ʱ��
	 * @param operationtype
	 *            �����ķ�ʽ���� delete��update��insert���ַ�ʽ
	 * @return �����ɹ�����1������ʧ�ܷ���0�������쳣����2��
	 */
	public int OperateSensorMLAndEbRim(String username, String password,
			String id, String sensormlcontent, String filename,
			String createtime, String operationtype) {
		if (null != StringUtil
				.checkStringIsNotNULLAndEmptyMethod(operationtype)) {
			if (operationtype.trim().toLowerCase().equals("delete")) {
				// ɾ������
				if (null != StringUtil.checkStringIsNotNULLAndEmptyMethod(id)) {
					// ��������idΪ��ȷ��
					if (OperateSensorUtil.CheckSensorMLExistMethod(username, id
							.trim())) {
						// ��������ݿ���ڸ�����¼
						OperateSensorUtil
								.DeleteSensorML(username, password, id);
					}
					return 1;
				} else if (null != StringUtil
						.checkStringIsNotNULLAndEmptyMethod(sensormlcontent)) {
					// ͨ��sensorML�ĵ���ȡid
					// �ڶ�����ȡ���ݹ�����sensorML��id��RegistryPackage��idֵ���������м�����ݿ�������ݿ�
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
				// ������� ����
				if (null != StringUtil
						.checkStringIsNotNULLAndEmptyMethod(sensormlcontent)) {
					// ��� sensorMLContent���ݲ���Ϊ�գ��������
					if (null != StringUtil
							.checkStringIsNotNULLAndEmptyMethod(id)) {
						// ����û��ṩ��idΪ��Ч��id
						if (OperateSensorUtil.CheckSensorMLExistMethod(
								id.trim()).getIsExist()) {
							// ������û��ṩ��id�����ݿ���ڣ��� ɾ����id�������ȫ���ĸ�����¼
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
						// �������id���Ϸ��Ĵ�����
						// ��ȡ���ݹ�����sensorml��id��registrypackage��idֵ���������м�����ݿ�������ݿ�
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
							// ɾ�����ڵļ�¼
							if (OperateSensorUtil.CheckSensorMLExistMethod(
									ebrimid).getIsExist()) {
								if (!OperateSensorUtil.DeleteSensorML(username,
										password, ebrimid)) {
									return 2;// �����쳣
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
				return 0;// û��ִ��insert, delete ��udapte ����������Ͳ������κβ���
			}
		} else {
			return 2;// û������
		}
	}

	/**
	 * ��SensorML�ĵ�ͨ��ʹ��xslFileת��ΪEbRIM��ʽ������
	 * 
	 * @param sensormlfile
	 *            ��Ҫת����SensorML�ļ�
	 * @param ebrimfile
	 *            ����ת������EbRIM�ļ�
	 * @param xslfile
	 *            ת����Ҫ��XSL�ļ�
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
					"��SensorMLת��ΪEbRIM������ת��������Ϣ�����쳣!");
		} catch (TransformerException e) {
			throw new TransactionProcessException("��SensorMLת��ΪEbRIM���̳����쳣!");
		}
	}

	/**
	 * ��ȡ�����û����������û����ĵ��Ļ�����Ϣ������id���ϴ�ʱ�䣬�ļ�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param type
	 *            �û���Ҫ�����ͣ�true�ǲ�ѯ���е��û����ĵ�����Ϣ��false���ǲ�ѯ�������Ϣ
	 * @return ���ص����û��ϴ��ļ��Ļ�����Ϣ��id���ļ���������ʱ��,û���򷵻�null�������Ӧ���ֶ�
	 */
	public List<SensorMLType> GetAllBasicInfoOfSensorMLMethod(String username,
			String password, boolean type) {
		return OperateSensorUtil.GetAllSensorMLBasicInfo(OperateSensorUtil
				.GetAllSensorIdsMethod());
	}

	/**
	 * ��ȡ������������sensorml
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
	 * ��ȡ���д�����������
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
	 * ���ָ����username��sensorid���ĵ��Ƿ����
	 * 
	 * @param username
	 *            �û�����
	 * @param sensormlid
	 *            �û���ѯ��sensorml��idֵ,��ebrim��ʽ��id
	 * @return true�����ڣ�false�� �����ڻ��ߴ洢��sensormlcontentΪ����Ҳ����false
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
	 * ���sensorid���ĵ��Ƿ����(ȫ�֣�
	 * 
	 * @param sensormlid
	 *            �û���ѯ��sensorml��idֵ,��ebrim��ʽ��id
	 * @return true�����ڣ�false�� �����ڻ��ߴ洢��sensormlcontentΪ����Ҳ����false
	 */
	@SuppressWarnings("unchecked")
	public static SensorExistBoolean CheckSensorMLExistMethod(String sensormlid) {
		SensorExistBoolean seb = new SensorExistBoolean();
		seb.setSensorid(sensormlid);
		// �ж�sensorML�Ƿ�Ϊnull������ֵΪ��
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
	 * ��ʵ��������ʶ���Ƿ����
	 * 
	 * @param sensorid
	 * @return
	 */
	public static boolean CheckSensorIdIsExistMethod(String sensorid) {
		return CheckSensorMLExistMethod(sensorid).getIsExist();
	}

	/**
	 * ��ȡ���еĴ������Ļ�������Ϣ
	 * 
	 * @param filename
	 *            ��Ҫ��ѯ�Ļ������ֶε�����
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ�������idֵ
	 * @return ���ز�ѯ����ȡ����Ϣ
	 */
	public static String GetSensorBasicInfoByFieldNameMethod(String filename,
			String sensorid) {
		// ��������
		if (filename.equals("sensorBasicChineseIputs")) {
			SensorInputInfoType siit = OperateSensorUtil
					.GetSensorChineseInputInfo(sensorid);
			if (siit != null) {
				return siit.getChineseinputinfo();
			} else {
				return null;
			}
		}
		// �������
		else if (filename.equals("sensorBasicChineseOutputs")) {
			SensorOutputType siit = OperateSensorUtil
					.GetSensorChineseOutputInfo(sensorid);
			if (siit != null) {
				return siit.getChineseoutputinfo();
			} else {
				return null;
			}
		}
		// ��ѯ�������۲ⷶΧ
		else if (filename.equals("sensorBasicObservedRange")) {
			return OperateSensorUtil.GetSensorGeoInfo(sensorid);
		}
		// ��ѯ��������λ����Ϣ
		else if (filename.equals("sensorBasicPosition")) {
			return OperateSensorUtil.GetSensorPosInfo(sensorid);
		}
		// ��ѯ��������ȫ����Ϣ
		else if (filename.equals("sensorBasicLongName")) {
			return OperateSensorUtil.GetSensorLongName(sensorid);
		}
		// ��ѯ�������ļ����Ϣ
		else if (filename.equals("sensorBasicShortName")) {
			return OperateSensorUtil.GetSensorShortNameMethod(sensorid);
		}
		// ��ѯ��������������Ϣ
		else if (filename.equals("sensorBasicSensorType")) {
			return OperateSensorUtil.GetSensorSensorTypeMethod(sensorid.trim());
		}
		// ��ѯ�������Ĺؼ�����Ϣ
		else if (filename.equals("sensorBasicKeywords")) {
			return OperateSensorUtil.GetSensorKeyWordInfo(sensorid);
		}
		// ��ѯ��������Ԥ��Ӧ��
		else if (filename.equals("sensorBasicIntendApplication")) {
			return OperateSensorUtil.GetSensorIntendAppInfo(sensorid);
		}
		// ��ѯ�������Ŀ�ʼ����ʱ��
		else if (filename.equals("sensorBasicValidTimeBegin")) {
			return OperateSensorUtil.GetSensorWorkBeginTime(sensorid);
		}
		// ��ѯ�������Ľ�������ʱ��
		else if (filename.equals("sensorBasicValidTimeEnd")) {
			return OperateSensorUtil.GetSensorWorkEndTime(sensorid);
		}
		// ��ѯ��ȡ�����������
		else if (filename.equals("sensorBasicOutputs")) {
			return OperateSensorUtil.GetSensorOutputsInfo(sensorid);
		}
		// ��ѯ��ȡ������������
		else if (filename.equals("sensorBasicInputs")) {
			return OperateSensorUtil.GetSensorInputsInfo(sensorid);
		}
		// ��ѯƽ̨���صĴ������ı�ʶ����
		else if (filename.equals("sensorBasicAssociatedSensorName")) {
			return OperateSensorUtil
					.GetSensorAssociatedSesnorNameInfo(sensorid);
		}
		// ��ѯ���������ͺ�
		else if (filename.equals("sensorBasicModelNumber")) {
			return OperateSensorUtil.GetSensorModelNumberInfo(sensorid);
		}
		// ��ѯ�����������쳧��
		else if (filename.equals("sensorBasicManufacturer")) {
			return OperateSensorUtil.GetSensorManufacturerInfo(sensorid);
		}
		// ��ѯ������������ƽ̨����
		else if (filename.equals("sensorBaicParentSystemStandardName")) {
			return OperateSensorUtil
					.GetSensorParentSystemStandardNameInfo(sensorid);
		}
		// ��ȡ��������ɨ������
		else if (filename.equals("sensorBasicScannerType")) {
			return OperateSensorUtil.GetSensorscannerTypeInfo(sensorid);
		}
		// ��ѯ�������Ĺ켣������Ϣ
		else if (filename.equals("sensorBasicOrbitType")) {
			return OperateSensorUtil.GetSensorOrbitTypeInfo(sensorid);
		}
		// �����ݲ���ѯ
		else {
			// ��ѯ���������򷵻�null
			return null;
		}
	}

	/**
	 * ��ȡ���������������Ե��ձ�ģ�ͷ���
	 * 
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ�������id
	 * @param capability
	 *            ��Ҫ��ѯ�����������Է����ָ��
	 * @return �������Ӧ��,���û�У��򷵻�null
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
	 * ��ѯ�������Ĳ���������ģ�ͷ���
	 * 
	 * @param sensorid
	 *            ��ѯ�Ĵ������ı�ʶ��
	 * @param capability
	 *            ��Ҫ��ѯ�Ĵ�����������
	 * @return ���ش�������ָ���ϵ�����������û���򷵻�null
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
	 * ��ȡ������������֯��Ϣ�ձ�ģ�ͷ���
	 * 
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ�������id
	 * @param capability
	 *            ��Ҫ��ѯ�����������Է����ָ��
	 * @return �������Ӧ��,���û�У��򷵻�null
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
	 * ��ȡ�������ļ����������ձ�ģ�ͷ���
	 * 
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ�������id
	 * @param capability
	 *            ��Ҫ��ѯ���ڼ������������ָ��
	 * @return �������Ӧ��,���û�У��򷵻�null
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
	 * ��ȡ��������ͨ���������ձ�ģ�ͷ���
	 * 
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ�������id
	 * @param capability
	 *            ��Ҫ��ѯ����ͨ�����������ָ��
	 * @return �������Ӧ��,���û�У��򷵻�null
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
	 * ��ȡ�������Ĺ������
	 * 
	 * @param sensorid
	 *            �������ı�ʶ��
	 * @return ���ش������Ĺ������
	 */
	@SuppressWarnings( { "unchecked" })
	public static String GetSensorOrbitTypeInfo(String sensorid) {

		// �����û����� ���������ݿ����Ƿ���������
		String resultStr = "";
		// ��ȡ�������ĵĴ�����������
		// ���������ӵ�LocalizedString���ݱ���
		// ����idֵ�����Ӧ��valueֵ
		// ���ظ�ֵ
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
	 * ��ѯ�������ͺ�
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
	 * ��ȡ������ɨ������
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorscannerTypeInfo(String sensorid) {

		// �����û����� ���������ݿ����Ƿ���������
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
	 * ��ȡ�����������쳧����Ϣ
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
	 * ��ȡ�������Ĵ��صĴ�������ƽ̨����
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
	 * ��ȡ���д������ı�ʾ������Ϣ����ɼ��ϡ�
	 * 
	 * @return �������еĴ������ı�ʶ������Ϣ���ϣ����û�еĻ�������null
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
	 * ��ȡ���д����������͵���Ϣ�ķ���
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
	 * ��ȡ�ض������������͵ķ���
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
	 * ��ȡȫ��������ƽ̨��ʶ��
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
			// ��ֹ�����ظ���ʶ��
			if (!platforms.contains(platformid)) {
				// ��ȡƽ̨������
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
	 * ��ȡ���е�վ��ʹ�����������
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param platfromtype
	 *            ƽ̨����
	 * @return ���ص�վ������
	 */
	public static List<PlatformandSensors> GetPlatFormandSensorsMethod(
			String username, String password, String platfromtype) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(platfromtype)) {
			return null;
		}
		// ��ȡ����ָ��ƽ̨������ƽ̨�ʹ������ı�ʶ��
		List<PlatformandSensors> tempallplatandsneosrs = OperateSensornewUtil
				.GetAllPlatformsandSensorIds(platfromtype);
		long pre = System.currentTimeMillis();
		// ��ȡȫ���Ĵ������Ļ�������Ϣ
		List<SensorBasciForOracleType> sbfot = OperateSensornewUtil
				.GetAllSensorBasicInfo(false, false);
		long now = System.currentTimeMillis();
		// System.out.println(now - pre + "����");
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
	 * ���ش������Ĺ�������Ϣ����
	 * 
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ������ı�ʶ������
	 * @return ���ش�������������Ϣ����
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
			if (shareStr.trim().equals("����")) {
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
	 * ���ش������Ĺ�������Ϣ
	 * 
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ������ı�ʶ������
	 * @return ���ش�������������Ϣ����
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
	 * ���ش������Ƿ�ɿ���Ϣ��true���ɿأ�false ���ɿ�
	 * 
	 * @param sensorid
	 *            ��������ʶ��
	 * @return ���ش������Ƿ�ɿ�
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
	 * ���ش����������Ƿ�ɿ���Ϣ��true���ɿأ�false ���ɿ�
	 * 
	 * @param sensorids
	 *            ��Ҫ��ѯ�Ĵ��������м���
	 * @return ���ش������Ƿ�ɿ�����
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
	 * ��ȡ�����������Ĺؼ���
	 * 
	 * @param sensorid
	 *            �������ı�ʶ��
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
	 * ��ȡ�������Ŀռ䷶Χ��Ϣ
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
	 * ��ѯָ����������ȫ���������
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
	 * ��ѯ������������������Ϣ
	 * 
	 * @param sensorid
	 * @return ����SensorInputInfoType
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
	 * ��ȡ���������������
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
	 * ��ȡ��������ȫ��
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
		// ��ȡ�������ĵ�ȫ��
		// ���������ӵ�LocalizedString���ݱ���
		// ����idֵ�����Ӧ��valueֵ
		// ���ظ�ֵ
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
	 * ��ȡ��������������������Ϣ
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
	 * ��ȡ����������������������Ϣ
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
	 * ��ȡ���������������
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
	 * ����ָ���Ĵ������Ĺ����ڷ���ڵ���ĳһ������intendedapplication��sharelevel����Ϣ
	 * 
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ�������ʶ��
	 * @param homeaspect
	 *            ��Ҫ��ѯ�ķ��棬��intendedapplication,sharelevel
	 * @return ���������Ϣ
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
	 * ��ȡ����������Ԥ��Ӧ��
	 * 
	 * @param sensorid
	 *            �������ı�ʶ��
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
	 * ��ȡ�������Ŀ�ʼ����ʱ��
	 * 
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ�������ʶ��
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
	 * ��ȡ��������������ʱ��
	 * 
	 * @param sensorid
	 *            �������ı�ʶ��
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
	 * ��ȡ�����������Ϣ
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
	 * ��ȡ��������λ����Ϣ
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
	 * ��ȡ�������Ĺ۲ⷶΧ
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
	 * ��ȡ��������֯��Ϣ
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
	 * ��ȡ������ָ���Ĳ�������
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
	 * ��ȡͨ���ʼ�
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
	 * ��ȡ����������ϵ��ַ
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
	 * ��ȡ�������ļ�����������
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
	 *��ȡͨ������
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
	 * ��ȡ�������Ƿ�����ĳһƽ̨
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
	 * ���洫������Ϣ
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param sensorid
	 *            ����Ĵ�������ʶ��
	 * @param sensormlcontent
	 *            �����sensorml���ĵ�������
	 * @return ����ɹ�����true��ʧ�ܷ���false
	 */
	public static boolean SaveSensorML(String username, String password,
			String sensorid, String sensormlcontent) {
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		SensorML sensorML = new SensorML();
		sensorML.setUsername(username);
		sensorML.setSensorid(sensorid);
		sensorML.setSavetime(new Date());// ����ʱ��
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
	 * ɾ��ָ���Ĵ�������SensorML�ĵ�
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
				System.out.println("ɾ���ɹ� ");
				return true;
			} else {
				System.out.println("ɾ��ʧ��");
				return false;
			}
		} else {
			GetSessionUtil.CloseSessionInstance(session);
			return true;// û����ɾ���ɹ�
		}

	}

	/**
	 * ��ȡָ����������SensorML�ĵ�
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
	 * ��ȡ�����������ĵ�SensorML�Ļ�����Ϣ
	 * 
	 * @param sensorids
	 *            ��������ʶ������
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
	 * ��ȡ�����������ĵ�SensorML�Ļ�����Ϣ
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
	 * ��ȡ�������ı���ʱ��
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
	 * �ж��Ƿ����ĳһSensorML�ĵ�
	 * 
	 * @param username
	 *            �û�����
	 * @param sensorid
	 *            ��������ʶ��
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
	 * ��ȡ�������Ĵ��صı�ʶ����Ϣ
	 * 
	 * @param sensorid
	 *            �������ı�ʶ��
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
	 * ��ȡ�û��ϴ���ȫ���Ĵ�����������Ϣ
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
	 * ����ָ���Ĵ�������sensorml
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
					// ��һ����ɾ��
					if (!DeleteSensorML(owner, password, sensorid)) {
						return false;
					}
					// �ڶ���������
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
	 * �ж�ĳ�������Ƿ�����ĳ�û�
	 * 
	 * @param sensorid
	 *            ����������
	 * @param owner
	 *            �û�
	 * @return true �����û���false �������û�
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
