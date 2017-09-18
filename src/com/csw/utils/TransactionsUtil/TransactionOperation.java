package com.csw.utils.TransactionsUtil;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import com.csw.Service.impls.TransformSensorMLToebRIMService;
import com.csw.beans.ProcessBasicInfo;
import com.csw.model.ebXMLModel.Association;
import com.csw.model.ebXMLModel.ClassificationNode;
import com.csw.model.ebXMLModel.ExtrinsicObject;
import com.csw.model.ebXMLModel.Identifiable;
import com.csw.model.ebXMLModel.MyebRIMSmlContents;
import com.csw.model.ebXMLModel.Organization;
import com.csw.model.ebXMLModel.Person;
import com.csw.model.ebXMLModel.RegistryPackage;
import com.csw.model.ebXMLModel.Service;
import com.csw.model.ebXMLModel.Slot;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.SensorInfoUtil.ParseEbRIMAndSaveInfo;
import com.csw.utils.SensorInfoUtil.ProcessChainUtil;
import com.ebrim.model.csw.DeleteType;
import com.ebrim.model.csw.InsertType;
import com.ebrim.model.csw.TransactionDocument;
import com.ebrim.model.csw.TransactionType;
import com.ebrim.model.csw.UpdateType;

@SuppressWarnings("deprecation")
public class TransactionOperation {

	/**
	 * transaction中的insert的操作 插入操作
	 * 
	 * @param transactionxmlcontent
	 *            传入进来的transaction insert的文档内容
	 * @return 返回操作的结果，id
	 */
	@SuppressWarnings("unused")
	public static String InsertTransaction(String transactionxmlpath,
			String ower) {
		String resultStr = "";
		ParseEbRIMAndSaveInfo pe = new ParseEbRIMAndSaveInfo();
		try {
			TransactionDocument tdomentDocument = TransactionDocument.Factory
					.parse(new File(transactionxmlpath));
			TransactionType transactionType = tdomentDocument.getTransaction();
			tdomentDocument = null;
			if (transactionType.getInsertArray() != null) {
				for (InsertType insertType : transactionType.getInsertArray()) {
					if (OperateSensorUtil
							.CheckSensorIdIsExistMethod(GetRegistryRegistryInfoUtils
									.GetRegistryPackageIDByEbrimContent(insertType
											.xmlText().trim()))) {
						TransactionOperation
								.DeleteRegistryPackageById(GetRegistryRegistryInfoUtils
										.GetRegistryPackageIDByEbrimContent(insertType
												.xmlText().trim()));
					}
					RegistryPackage pp = pe.parseAndSaveXMLDocumentByContent(
							insertType.xmlText().trim(), ower);
					pp = null;

					resultStr = GetRegistryRegistryInfoUtils
							.GetRegistryPackageIDByEbrimContent(insertType
									.xmlText().trim());
				}
			}
			transactionType = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultStr;
	}

	/**
	 * 根据identifiable中的id删除在RegistryPackage中存储的RegistryPackageList中的RegistryObject中的各个数据
	 * 
	 * @param valueid
	 *            identifiable中的id值，
	 * @param valuehome
	 *            identifiable中的value值
	 */
	public static void DeleteIdenfiriablesUtils(String valueid, String valuehome) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		if (valuehome
				.equals("com.ebrim.model.wrs.impl.ExtrinsicObjectTypeImpl")) {
			Criteria criteria = session.createCriteria(ExtrinsicObject.class);
			criteria.add(Expression.eq("id", valueid));
			criteria.setCacheable(true);
			for (int i = 0; i < criteria.list().size(); i++) {
				ExtrinsicObject eo = (ExtrinsicObject) criteria.list().get(0);
				session.delete(eo);
			}
		}

		if (valuehome.equals("com.ebrim.model.rim.impl.AssociationType1Impl")) {
			Criteria criteria = session.createCriteria(Association.class);
			criteria.add((Expression.eq("id", valueid)));
			criteria.setCacheable(true);
			for (int i = 0; i < criteria.list().size(); i++) {
				Association association = (Association) criteria.list().get(i);
				session.delete(association);
			}

		}
		if (valuehome.equals("com.ebrim.model.rim.impl.ServiceTypeImpl")) {
			Criteria criteria = session.createCriteria(Service.class);
			criteria.add(Expression.eq("id", valueid));
			criteria.setCacheable(true);
			for (int i = 0; i < criteria.list().size(); i++) {
				Service service = (Service) criteria.list().get(0);
				session.delete(service);
			}
		}

		if (valuehome.equals("com.ebrim.model.rim.impl.OrganizationTypeImpl")) {
			Criteria criteria = session.createCriteria(Organization.class);
			criteria.add(Expression.eq("id", valueid));
			criteria.setCacheable(true);
			for (int i = 0; i < criteria.list().size(); i++) {
				Organization organization = (Organization) criteria.list().get(
						0);
				session.delete(organization);
			}

		}
		if (valuehome.equals("com.ebrim.model.rim.impl.PersonTypeImpl")) {
			Criteria criteria = session.createCriteria(Person.class);
			criteria.add(Expression.eq("id", valueid));
			criteria.setCacheable(true);
			for (int i = 0; i < criteria.list().size(); i++) {
				Person person = (Person) criteria.list().get(0);
				session.delete(person);
			}
		}

		if (valuehome
				.equals("com.ebrim.model.rim.impl.ClassificationNodeTypeImpl")) {
			Criteria criteria = session
					.createCriteria(ClassificationNode.class);
			criteria.add(Expression.eq("id", valueid));
			criteria.setCacheable(true);
			for (int i = 0; i < criteria.list().size(); i++) {
				ClassificationNode classificationNode = (ClassificationNode) criteria
						.list().get(0);
				session.delete(classificationNode);
			}
		}
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
	}

	public static void main(String[] args) {
		DeleteRegistryPackageById("urn:ogc:object:feature:Sensor:2222");
		System.out.println("here");
	}

	/**
	 * 根据id删除RegistryPackage实例
	 * 
	 * @param id
	 *            需要刪除的RegistryPackage的id值
	 * @return 删除是否成功 ，failed删除失败；success 删除成功
	 */
	@SuppressWarnings("unchecked")
	public static String DeleteRegistryPackageById(String id) {
		id = StringUtil.AppendPacakgeStr(id);
		String resultType = "failed";
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			Criteria criteria = session.createCriteria(RegistryPackage.class);
			criteria.add((Expression.eq("id", id)));
			List list = criteria.list();
			if (list != null && list.size() > 0) {
				RegistryPackage rpackage = (RegistryPackage) list.get(0);
				Set<Identifiable> idfs = rpackage.getIdentifiables();
				for (Identifiable identifiable : idfs) {
					identifiable.getId();
					identifiable.getHome();
				}
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstance(session);
				if (idfs != null) {
					for (Identifiable identifiable : idfs) {
						String valueid = identifiable.getId();
						String valuehome = identifiable.getHome();
						DeleteIdenfiriablesUtils(valueid, valuehome);
					}
				}
			}
			session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			Criteria criteria2 = session.createCriteria(RegistryPackage.class);
			criteria2.add(Expression.eq("id", id));
			criteria2.setCacheable(true);
			List list2 = criteria2.list();
			if (list2 != null && list2.size() > 0) {
				RegistryPackage rp = (RegistryPackage) list2.get(0);
				session.delete(rp);
				resultType = "success";
			} else {
				resultType = "success";
			}
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultType;
	}

	/**
	 * 解析Transaction-Delete的内容解析出所有的要删除的RegistryPackage
	 * 
	 * @param transactionDeleteContent
	 *            Transaction-Delete的XML文档的内容
	 * @return 返回所有的RegistryPackage的String内容的数组
	 */
	public static Set<String> ParseTransactionDeleteContent(
			String transactionDeleteContent) {
		Set<String> rpcontent = new HashSet<String>();
		transactionDeleteContent = transactionDeleteContent.trim();
		try {
			TransactionDocument transactionDocument = TransactionDocument.Factory
					.parse(transactionDeleteContent);
			if (transactionDocument.getTransaction().getDeleteArray() != null) {
				for (DeleteType deleteType : transactionDocument
						.getTransaction().getDeleteArray()) {
					XmlCursor xmlCursor = deleteType.newCursor();
					xmlCursor.toChild(0);
					rpcontent.add(xmlCursor.xmlText());
				}
			}
		} catch (XmlException e) {
			System.out.println("调用 ParseTransactionDeleteContent方法时出现了问题");
		}
		return rpcontent;
	}

	/**
	 * 该函数不仅可以更新，也可以插入数据更新相对应的RegistryPackage的内容
	 * 
	 * @param transactionUpdatexml
	 *            需要更新或者插入的数据
	 */
	public static void ParseTransactionUpdateXmlDocument(
			String transactionUpdatexml, String ower) {
		ParseEbRIMAndSaveInfo pe = new ParseEbRIMAndSaveInfo();
		try {
			TransactionDocument transactionDocument = TransactionDocument.Factory
					.parse(transactionUpdatexml);
			if (transactionDocument.getTransaction().getUpdateArray() != null) {
				for (UpdateType updateType : transactionDocument
						.getTransaction().getUpdateArray()) {
					XmlCursor xmlCursor = updateType.newCursor();
					xmlCursor.toChild(0);
					if (GetRegistryRegistryInfoUtils
							.GetRegistryPackageIDByEbrimContent(xmlCursor
									.xmlText().trim()) != null) {

						String rpid = GetRegistryRegistryInfoUtils
								.GetRegistryPackageIDByEbrimContent(xmlCursor
										.xmlText().trim());
						TransactionOperation.DeleteRegistryPackageById(rpid);
					}
					pe.parseAndSaveXMLDocumentByContent(xmlCursor.xmlText()
							.trim(), ower);
				}
			}
		} catch (XmlException e) {
			e.printStackTrace();
		}
		pe = null;
		System.gc();
	}

	/**
	 * 保存所有的格式的sensorml格式的文档内容，如system，component，processmodel，processchain，
	 * procesMethod等， 并保存相关的processbasicinfo的信息
	 * 
	 * @param sensorml
	 *            需要解析的sensorml文档
	 * @param sensormlfilepath
	 *            上面的sensorml文档存储的位置
	 * @param processmodelxsltpath
	 *            processmodel的转换文件的位置
	 * @param xslfilepath
	 *            system或者processmodel转换文件的存储位置
	 * @param ebrimfilepath
	 *            转换后的ebrim文档内容的存储路径
	 * @param processType
	 *            process的类型
	 * @param intendedApplication
	 *            process的预期应用
	 * @param serviceType
	 *            process的服务类型
	 * @param save
	 *            是否将转换好的EbRIM存入到数据库中,为false，只是获取ebRIM格式的内容，true则是保存数据
	 * @return 返回已经转换好的ebRIM文档的内容
	 */
	public static String ParseAndSaveAllTypesProcessMethod(String sensorml,
			String sensormlfilepath, String processmodelxsltpath,
			String xslfilepath, String ebrimfilepath, String processType,
			String intendedApplication, String serviceType, boolean save,
			String ower) {
		String ebrim = "";
		if (sensorml.trim().toLowerCase().contains(
				"<processchain".toLowerCase())) {
			// 解析并保存ProcessChain的文档的信息
			System.out.println("processchain分支");
			ebrim = TransactionOperation.ParseAndSaveProcessChainMethod(
					sensorml, sensormlfilepath, xslfilepath,
					processmodelxsltpath, ebrimfilepath, save, ower);
			if (save) {
				ProcessChainUtil pct = new ProcessChainUtil();
				Map<String, Map<String, String>> maps = pct
						.ParseProcessChainOperation(sensorml);
				String mainebrim = maps.get("main").get("main1");
				String rpgid = GetRegistryRegistryInfoUtils
						.GetRegistryPackageIDByEbrimContent(mainebrim.trim());
				if (intendedApplication != null && ebrim != null
						&& processType != null && serviceType != null) {
					GetRegistryRegistryInfoUtils.SaveProcessBasicInfo(rpgid,
							processType, intendedApplication, serviceType);
				}
			}
		} else {
			System.out.println("system,component processmodel的分支");
			System.out.println("ebrimfilepath:" + ebrimfilepath);
			ebrim = new TransactionOperation()
					.ParseAndSaveNoProcessChainMethod(ower, sensorml,
							sensormlfilepath, processmodelxsltpath,
							xslfilepath, ebrimfilepath, processType,
							intendedApplication, serviceType, save, ower);
		}
		return ebrim;
	}

	/**
	 * 解析和保存SensorML格式的非ProcessChain的文档的信息,并保存起基本信息，如果不需要，则可以全部为null
	 * 
	 * @param sensorml
	 *            需要解析的SensorML文档
	 * @param sensormlfilepath
	 *            上面的SensorML文档存储的位置
	 * @param processmodelxsltpath
	 *            ProcessModel的转换文件的位置
	 * @param xslfilepath
	 *            system或者ProcessModel转换文件的存储位置
	 * @param ebrimfilepath
	 *            转换后的EbRIM文档内容的存储路径
	 * @param processType
	 *            process的类型
	 * @param intendedApplication
	 *            process的预期应用
	 * @param serviceType
	 *            process的服务类型
	 * @param save
	 *            是否将转换好的EbRIM存入到数据库中,为false，只是获取ebrim格式的内容，true则是保存数据
	 * @return 返回生成的EbRIM的内容
	 */
	public String ParseAndSaveNoProcessChainMethod(String username,
			String sensorml, String sensormlfilepath,
			String processmodelxsltpath, String xslfilepath,
			String ebrimfilepath, String processType,
			String intendedApplication, String serviceType, boolean save,
			String ower) {
		ParseEbRIMAndSaveInfo pe = new ParseEbRIMAndSaveInfo();
		String ebrim = new OperateSensorUtil()
				.TransformNonProcessChainToEbRIMMethod(sensorml,
						sensormlfilepath, processmodelxsltpath, xslfilepath,
						ebrimfilepath).trim();
		System.out.println("created ebrim : " + ebrim);
		if (save) {
			String rpid = GetRegistryRegistryInfoUtils
					.GetRegistryPackageIDByEbrimContent(ebrim);
			if (OperateSensorUtil.CheckSensorIdIsExistMethod(rpid)) {
				TransactionOperation.DeleteRegistryPackageById(rpid);
				TransactionOperation.DeleteProcessBasicInfoMethod(rpid);
			}
			// 保存RegistryPackage内容
			pe.parseAndSaveXMLDocumentByContent(ebrim, ower);
		}
		return FormatXmlUtil.formatXml(ebrim.trim());
	}

	/**
	 * 解析并保存processChain文档内容,返回生成的EbRIM格式的ProcessChain文档内容，之后要及时删除使用的了临时的文件
	 * 
	 * @param sensorml
	 *            需要解析的processChain文档
	 * @param sensormlfilepath
	 *            用于存储SensorML的文件路径
	 * @param xslfilepath
	 *            system 或者component的转换XSLT文件
	 * @param processchainfilepath
	 *            ProcessChain的转换文件
	 * @param savepc
	 *            是否将转换好的EbRIM存入到数据库中,为false，只是获取ebrim格式的内容，true则是保存数据
	 * @return <a>返回生成的processChain的EbRIM格式的内容
	 */
	public static String ParseAndSaveProcessChainMethod(String sensorml,
			String sensormlfilepath, String xslfilepath,
			String processmodelxsltpath, String ebrimfilepath, boolean savepc,
			String ower) {
		ParseEbRIMAndSaveInfo pe = new ParseEbRIMAndSaveInfo();
		String ebrim = "";
		ProcessChainUtil pct = new ProcessChainUtil();
		Map<String, Map<String, String>> maps = pct
				.ParseProcessChainOperation(sensorml.trim());
		for (String str : maps.keySet()) {
			if (str.equals("main")) {
				String ebrimcontent = "";
				ebrimcontent = maps.get("main").get("main1");
				ebrim += "【The ProcessChain File Context ：】\n";
				ebrim += FormatXmlUtil.formatXml(ebrimcontent) + "\n";
				if (savepc) {
					String rpid = GetRegistryRegistryInfoUtils
							.GetRegistryPackageIDByEbrimContent(ebrimcontent
									.trim());
					if (OperateSensorUtil.CheckSensorIdIsExistMethod(rpid)) {
						TransactionOperation.DeleteRegistryPackageById(rpid);
						TransactionOperation.DeleteProcessBasicInfoMethod(rpid);
					}
					pe.parseAndSaveXMLDocumentByContent(ebrimcontent, ower);
				}
			}
		}
		for (String str : maps.keySet()) {
			if (str.equals("component")) {
				int k = 0;
				for (String item : maps.get(str).keySet()) {
					String sensormlcontent = maps.get(str).get(item).trim();
					String ebrimcontent = "";
					try {
						ebrimcontent = new OperateSensorUtil()
								.TransformNonProcessChainToEbRIMMethod(
										sensormlcontent, sensormlfilepath,
										processmodelxsltpath, xslfilepath,
										ebrimfilepath);
						ebrim += "【The " + (k + 1)
								+ " Component File Context ：】\n";
						ebrim += FormatXmlUtil.formatXml(ebrimcontent) + "\n";
						if (savepc) {
							String rpid = GetRegistryRegistryInfoUtils
									.GetRegistryPackageIDByEbrimContent(ebrimcontent
											.trim());
							if (OperateSensorUtil
									.CheckSensorIdIsExistMethod(rpid)) {
								TransactionOperation
										.DeleteRegistryPackageById(rpid);
								TransactionOperation
										.DeleteProcessBasicInfoMethod(rpid);
							}
							pe.parseAndSaveXMLDocumentByContent(ebrimcontent,
									ower);
						}
						k++;
					} catch (Exception e) {
						System.out
								.println("TransactionOperation.ParseAndSaveProcessChainMethod方法调用失败！");
					}
				}
			}
		}
		pe = null;
		System.gc();
		return ebrim;
	}

	/**
	 * 根据相关的processChain的信息获取器主system或者processmodel的id值
	 * 
	 * @param sensorml
	 *            processchain的文档内容
	 * @return
	 */
	public static String ParseProcessChainAndGetRegistryPacakgeIdMethod(
			String sensorml, String ower) {
		ParseEbRIMAndSaveInfo pe = new ParseEbRIMAndSaveInfo();
		String registrypacakgeid = "";
		if (sensorml.trim().toLowerCase().contains(
				"<processchain".toLowerCase())) {
			ProcessChainUtil pct = new ProcessChainUtil();
			Map<String, Map<String, String>> maps = pct
					.ParseProcessChainOperation(sensorml.trim());
			for (String str : maps.keySet()) {
				if (str.equals("main")) {
					RegistryPackage rpa = new RegistryPackage();
					String ebrimcontent = maps.get("main").get("main1");
					rpa = pe.parseAndSaveXMLDocumentByContent(ebrimcontent,
							ower);
					registrypacakgeid = rpa.getId();
				}
			}
			maps = null;
		}
		pe = null;
		System.gc();
		return registrypacakgeid;
	}

	public static void DeleteMyEbRIMSmlContentsMethod(String rpid) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();
			Criteria criteria = session
					.createCriteria(MyebRIMSmlContents.class);
			criteria.add(Expression.eq("id", rpid));
			if (criteria.list() != null || criteria.list().size() > 0) {
				for (int i = 0; i < criteria.list().size(); i++) {
					MyebRIMSmlContents mrcs = (MyebRIMSmlContents) criteria
							.list().get(i);
					session.delete(mrcs);
					mrcs = null;
				}
			}
			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			GetSessionUtil.CloseSessionInstance(session);
		}
	}

	/**
	 * 删除制定的processbasicinfo数据表中的数据
	 * 
	 * @param rpid
	 *            制定的需要删除的processbasicinfo的id值
	 */
	@SuppressWarnings("unchecked")
	public static void DeleteProcessBasicInfoMethod(String rpid) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();
			Criteria criteria = session.createCriteria(ProcessBasicInfo.class);
			criteria.add(Expression.eq("processId", rpid));
			List<ProcessBasicInfo> lists = (List<ProcessBasicInfo>) criteria
					.list();
			if (!lists.isEmpty()) {
				for (ProcessBasicInfo pbis : lists) {
					session.delete(pbis);
				}
			}
			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			GetSessionUtil.CloseSessionInstance(session);
		}

	}

	/**
	 * 将非ProcessChain的SensorML转换为EbRIM格式
	 * 
	 * @param sensorml
	 *            需要转换的SensorML文档
	 * @param tstRimAction
	 *            调用的TransformSensorMLToebRIMAction，但是再调用该方法之前，
	 *            必须先调用TransformSensorMLToebRIMAction.FuzhiVariables()方法
	 * @return
	 * @throws Exception
	 */
	public static String GetEbrimContent(String sensorml,
			TransformSensorMLToebRIMService tstRimAction) throws Exception {
		String ebrimcontent;
		ebrimcontent = tstRimAction
				.TransformNonProcessChainToEbRIMMethod(sensorml);
		return ebrimcontent;
	}

	/**
	 * 跟新部分process（主要是system和component的文档内容）
	 * 
	 * @param sensorid
	 *            process的id值
	 * @param keywords
	 *            propcess的关键字
	 * @param inputs
	 *            process的输入参数
	 * @param outputs
	 *            process的输出参数
	 * @param southenv
	 *            矩形框的南
	 * @param westenv
	 *            矩形框的西
	 * @param northenv
	 *            矩形框的北
	 * @param eastenv
	 *            矩形框的东
	 * @param positionx
	 *            坐标点的X
	 * @param positiony
	 *            坐标点的Y
	 */
	@SuppressWarnings("unchecked")
	public static void UpdateSampleProcessInfoMethod(String sensorid,
			String keywords, String inputs, String outputs, String southenv,
			String westenv, String northenv, String eastenv, String positionx,
			String positiony) {
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Criteria criteria = session.createCriteria(RegistryPackage.class);
		criteria.add(Expression.eq("id", sensorid));
		List list = criteria.list();
		if (list != null) {
			RegistryPackage rp = (RegistryPackage) list.get(0);
			if (rp.getExternalIdentifiers() != null) {
				for (Identifiable ide : rp.getIdentifiables()) {
					if (ide.getHome().equals(
							"com.ebrim.model.wrs.impl.ExtrinsicObjectTypeImpl")) {
						criteria = null;
						criteria = session
								.createCriteria(ExtrinsicObject.class);
						criteria.add(Expression.eq("id", ide.getId()));
						ExtrinsicObject eo = (ExtrinsicObject) criteria.list()
								.get(0);
						if (eo.getSlots() != null) {
							for (Slot slot : eo.getSlots()) {
								if (slot.getName().endsWith("Keywords")) {
									session.createSQLQuery(
											"update slot SET valuess = '"
													+ keywords + ","
													+ "' where outid = "
													+ slot.getOutid())
											.executeUpdate();

								}
								if (slot.getName().endsWith("BoundedBy")) {
									String slotvalue = slot
											.getValues()
											.substring(
													1,
													slot.getValues().length() - 2);
									String[] values = slotvalue.split(",");
									String slotnewvalue = "[" + values[0] + ","
											+ southenv + " " + southenv + ","
											+ northenv + " " + eastenv + ",]";
									session.createSQLQuery(
											"update slot SET valuess = '"
													+ slotnewvalue + ","
													+ "' where outid = "
													+ slot.getOutid())
											.executeUpdate();

								}
								if (slot.getName().endsWith("Location")) {
									String slotvalue = slot
											.getValues()
											.substring(
													1,
													slot.getValues().length() - 2);
									String[] values = slotvalue.split(",");
									String slotnewvalue = "[" + values[0] + ","
											+ positiony + "," + positionx
											+ ",]";
									session.createSQLQuery(
											"update slot SET valuess = '"
													+ slotnewvalue
													+ "' where outid = "
													+ slot.getOutid())
											.executeUpdate();

								}
								if (slot.getName().endsWith("Outputs")) {
									session.createSQLQuery(
											"update slot SET valuess = '"
													+ outputs + ","
													+ "' where outid = "
													+ slot.getOutid())
											.executeUpdate();
								}
								if (slot.getName().endsWith("Inputs")) {
									session.createSQLQuery(
											"update slot SET valuess = '"
													+ inputs + ","
													+ "' where outid = "
													+ slot.getOutid())
											.executeUpdate();
								}
							}
						}
					}
				}
			}
		}
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
	}
}
