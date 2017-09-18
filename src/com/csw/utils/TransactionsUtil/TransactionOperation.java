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
	 * transaction�е�insert�Ĳ��� �������
	 * 
	 * @param transactionxmlcontent
	 *            ���������transaction insert���ĵ�����
	 * @return ���ز����Ľ����id
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
	 * ����identifiable�е�idɾ����RegistryPackage�д洢��RegistryPackageList�е�RegistryObject�еĸ�������
	 * 
	 * @param valueid
	 *            identifiable�е�idֵ��
	 * @param valuehome
	 *            identifiable�е�valueֵ
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
	 * ����idɾ��RegistryPackageʵ��
	 * 
	 * @param id
	 *            ��Ҫ�h����RegistryPackage��idֵ
	 * @return ɾ���Ƿ�ɹ� ��failedɾ��ʧ�ܣ�success ɾ���ɹ�
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
	 * ����Transaction-Delete�����ݽ��������е�Ҫɾ����RegistryPackage
	 * 
	 * @param transactionDeleteContent
	 *            Transaction-Delete��XML�ĵ�������
	 * @return �������е�RegistryPackage��String���ݵ�����
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
			System.out.println("���� ParseTransactionDeleteContent����ʱ����������");
		}
		return rpcontent;
	}

	/**
	 * �ú����������Ը��£�Ҳ���Բ������ݸ������Ӧ��RegistryPackage������
	 * 
	 * @param transactionUpdatexml
	 *            ��Ҫ���»��߲��������
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
	 * �������еĸ�ʽ��sensorml��ʽ���ĵ����ݣ���system��component��processmodel��processchain��
	 * procesMethod�ȣ� ��������ص�processbasicinfo����Ϣ
	 * 
	 * @param sensorml
	 *            ��Ҫ������sensorml�ĵ�
	 * @param sensormlfilepath
	 *            �����sensorml�ĵ��洢��λ��
	 * @param processmodelxsltpath
	 *            processmodel��ת���ļ���λ��
	 * @param xslfilepath
	 *            system����processmodelת���ļ��Ĵ洢λ��
	 * @param ebrimfilepath
	 *            ת�����ebrim�ĵ����ݵĴ洢·��
	 * @param processType
	 *            process������
	 * @param intendedApplication
	 *            process��Ԥ��Ӧ��
	 * @param serviceType
	 *            process�ķ�������
	 * @param save
	 *            �Ƿ�ת���õ�EbRIM���뵽���ݿ���,Ϊfalse��ֻ�ǻ�ȡebRIM��ʽ�����ݣ�true���Ǳ�������
	 * @return �����Ѿ�ת���õ�ebRIM�ĵ�������
	 */
	public static String ParseAndSaveAllTypesProcessMethod(String sensorml,
			String sensormlfilepath, String processmodelxsltpath,
			String xslfilepath, String ebrimfilepath, String processType,
			String intendedApplication, String serviceType, boolean save,
			String ower) {
		String ebrim = "";
		if (sensorml.trim().toLowerCase().contains(
				"<processchain".toLowerCase())) {
			// ����������ProcessChain���ĵ�����Ϣ
			System.out.println("processchain��֧");
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
			System.out.println("system,component processmodel�ķ�֧");
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
	 * �����ͱ���SensorML��ʽ�ķ�ProcessChain���ĵ�����Ϣ,�������������Ϣ���������Ҫ�������ȫ��Ϊnull
	 * 
	 * @param sensorml
	 *            ��Ҫ������SensorML�ĵ�
	 * @param sensormlfilepath
	 *            �����SensorML�ĵ��洢��λ��
	 * @param processmodelxsltpath
	 *            ProcessModel��ת���ļ���λ��
	 * @param xslfilepath
	 *            system����ProcessModelת���ļ��Ĵ洢λ��
	 * @param ebrimfilepath
	 *            ת�����EbRIM�ĵ����ݵĴ洢·��
	 * @param processType
	 *            process������
	 * @param intendedApplication
	 *            process��Ԥ��Ӧ��
	 * @param serviceType
	 *            process�ķ�������
	 * @param save
	 *            �Ƿ�ת���õ�EbRIM���뵽���ݿ���,Ϊfalse��ֻ�ǻ�ȡebrim��ʽ�����ݣ�true���Ǳ�������
	 * @return �������ɵ�EbRIM������
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
			// ����RegistryPackage����
			pe.parseAndSaveXMLDocumentByContent(ebrim, ower);
		}
		return FormatXmlUtil.formatXml(ebrim.trim());
	}

	/**
	 * ����������processChain�ĵ�����,�������ɵ�EbRIM��ʽ��ProcessChain�ĵ����ݣ�֮��Ҫ��ʱɾ��ʹ�õ�����ʱ���ļ�
	 * 
	 * @param sensorml
	 *            ��Ҫ������processChain�ĵ�
	 * @param sensormlfilepath
	 *            ���ڴ洢SensorML���ļ�·��
	 * @param xslfilepath
	 *            system ����component��ת��XSLT�ļ�
	 * @param processchainfilepath
	 *            ProcessChain��ת���ļ�
	 * @param savepc
	 *            �Ƿ�ת���õ�EbRIM���뵽���ݿ���,Ϊfalse��ֻ�ǻ�ȡebrim��ʽ�����ݣ�true���Ǳ�������
	 * @return <a>�������ɵ�processChain��EbRIM��ʽ������
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
				ebrim += "��The ProcessChain File Context ����\n";
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
						ebrim += "��The " + (k + 1)
								+ " Component File Context ����\n";
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
								.println("TransactionOperation.ParseAndSaveProcessChainMethod��������ʧ�ܣ�");
					}
				}
			}
		}
		pe = null;
		System.gc();
		return ebrim;
	}

	/**
	 * ������ص�processChain����Ϣ��ȡ����system����processmodel��idֵ
	 * 
	 * @param sensorml
	 *            processchain���ĵ�����
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
	 * ɾ���ƶ���processbasicinfo���ݱ��е�����
	 * 
	 * @param rpid
	 *            �ƶ�����Ҫɾ����processbasicinfo��idֵ
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
	 * ����ProcessChain��SensorMLת��ΪEbRIM��ʽ
	 * 
	 * @param sensorml
	 *            ��Ҫת����SensorML�ĵ�
	 * @param tstRimAction
	 *            ���õ�TransformSensorMLToebRIMAction�������ٵ��ø÷���֮ǰ��
	 *            �����ȵ���TransformSensorMLToebRIMAction.FuzhiVariables()����
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
	 * ���²���process����Ҫ��system��component���ĵ����ݣ�
	 * 
	 * @param sensorid
	 *            process��idֵ
	 * @param keywords
	 *            propcess�Ĺؼ���
	 * @param inputs
	 *            process���������
	 * @param outputs
	 *            process���������
	 * @param southenv
	 *            ���ο����
	 * @param westenv
	 *            ���ο����
	 * @param northenv
	 *            ���ο�ı�
	 * @param eastenv
	 *            ���ο�Ķ�
	 * @param positionx
	 *            ������X
	 * @param positiony
	 *            ������Y
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
