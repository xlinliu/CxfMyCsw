package com.csw.utils.GetRecordsOperationUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.w3c.dom.Node;
import com.csw.model.ebXMLModel.Association;
import com.csw.model.ebXMLModel.Classification;
import com.csw.model.ebXMLModel.ClassificationNode;
import com.csw.model.ebXMLModel.EmailAddress;
import com.csw.model.ebXMLModel.ExternalIdentifier;
import com.csw.model.ebXMLModel.ExtrinsicObject;
import com.csw.model.ebXMLModel.Identifiable;
import com.csw.model.ebXMLModel.InternationalString;
import com.csw.model.ebXMLModel.LocalizedString;
import com.csw.model.ebXMLModel.ObjectRef;
import com.csw.model.ebXMLModel.Organization;
import com.csw.model.ebXMLModel.Person;
import com.csw.model.ebXMLModel.PersonName;
import com.csw.model.ebXMLModel.PostalAddress;
import com.csw.model.ebXMLModel.RegistryPackage;
import com.csw.model.ebXMLModel.Service;
import com.csw.model.ebXMLModel.ServiceBinding;
import com.csw.model.ebXMLModel.Slot;
import com.csw.model.ebXMLModel.SpecificationLink;
import com.csw.model.ebXMLModel.TelephoneNumber;
import com.csw.model.ebXMLModel.VersionInfo;
import com.csw.model.gml.DirectPositionType;
import com.csw.model.gml.EnvelopeDocument;
import com.csw.model.gml.EnvelopeType;
import com.csw.model.gml.PointDocument;
import com.csw.model.gml.PointType;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.GetSessionUtil;
import com.ebrim.model.csw.GetRecordsDocument;
import com.ebrim.model.csw.GetRecordsResponseDocument;
import com.ebrim.model.csw.GetRecordsResponseType;
import com.ebrim.model.csw.GetRecordsType;
import com.ebrim.model.csw.QueryConstraintType;
import com.ebrim.model.csw.QueryType;
import com.ebrim.model.ogc.AndDocument;
import com.ebrim.model.ogc.BinaryComparisonOpType;
import com.ebrim.model.ogc.BinaryLogicOpType;
import com.ebrim.model.ogc.BinarySpatialOpType;
import com.ebrim.model.ogc.ComparisonOpsType;
import com.ebrim.model.ogc.FilterType;
import com.ebrim.model.ogc.PropertyIsLikeType;
import com.ebrim.model.ogc.PropertyIsNullType;
import com.ebrim.model.ogc.SpatialOpsType;
import com.ebrim.model.rim.AssociationDocument;
import com.ebrim.model.rim.AssociationType1;
import com.ebrim.model.rim.ClassificationNodeDocument;
import com.ebrim.model.rim.ClassificationNodeType;
import com.ebrim.model.rim.ClassificationType;
import com.ebrim.model.rim.EmailAddressType;
import com.ebrim.model.rim.ExternalIdentifierType;
import com.ebrim.model.rim.ExtrinsicObjectDocument;
import com.ebrim.model.rim.ExtrinsicObjectType;
import com.ebrim.model.rim.IdentifiableType;
import com.ebrim.model.rim.InternationalStringDocument;
import com.ebrim.model.rim.InternationalStringType;
import com.ebrim.model.rim.LocalizedStringType;
import com.ebrim.model.rim.OrganizationDocument;
import com.ebrim.model.rim.OrganizationType;
import com.ebrim.model.rim.PersonNameDocument;
import com.ebrim.model.rim.PersonNameType;
import com.ebrim.model.rim.PersonType;
import com.ebrim.model.rim.PostalAddressType;
import com.ebrim.model.rim.RegistryObjectListType;
import com.ebrim.model.rim.RegistryPackageDocument;
import com.ebrim.model.rim.RegistryPackageType;
import com.ebrim.model.rim.ServiceBindingType;
import com.ebrim.model.rim.ServiceDocument;
import com.ebrim.model.rim.ServiceType;
import com.ebrim.model.rim.SlotType1;
import com.ebrim.model.rim.SpecificationLinkType;
import com.ebrim.model.rim.TelephoneNumberType;
import com.ebrim.model.rim.ValueListType;
import com.ebrim.model.rim.VersionInfoType;
import com.ebrim.model.wrs.AnyValueType;
import com.ebrim.model.wrs.ValueListDocument;

@SuppressWarnings("deprecation")
public class GetRecordsOperation {

	// ʹ�ø����Խ����������extrinsicObject��idȫ���洢������
	private static List<String> extrinsicObjectIDs = new ArrayList<String>();
	// BigInteger���Ա�ʾ�����С����������ʧȥ�κεľ���
	private static BigInteger maxRecordNum = BigInteger.valueOf(1);
	@SuppressWarnings("unused")
	private static BigInteger startRecord = BigInteger.valueOf(1);
	@SuppressWarnings("unused")
	private static String outputSchema = "";
	private static String resultType = "";
	@SuppressWarnings("unused")
	private static List<String> typenames = new ArrayList<String>();
	@SuppressWarnings("unused")
	private static List<String> elementSetNameTypeNames = new ArrayList<String>();
	private static QueryConstraintType queryConstraint;
	// private static String queryConstraintVersion;
	// �����ԣ���Ҫ���洢��getRecords�е����в�ѯ�����Ժ�ֵ
	private static Map<String, String[]> properties = new HashMap<String, String[]>();
	private static List<String> registrypackageIdsList = new ArrayList<String>();

	/**
	 * ����RegistryPackage��idֵ����ȡ ���Ӧ��EbRIM�ĵ�
	 * 
	 * @param rpid
	 * @return
	 */
	public static RegistryPackageDocument getRegistryPackageDocumentById(
			String rpid) {
		RegistryPackageDocument rpdDocument = RegistryPackageDocument.Factory
				.newInstance();
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		RegistryPackageType rpt;
		try {
			rpt = GetRegistryPackageTypeById(rpid, "full");
			rpdDocument.setRegistryPackage(rpt);
			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			GetSessionUtil.CloseSessionInstance(session);
		}
		return rpdDocument;
	}

	/**
	 * �˺������������ڽ�RegistryPackage�е�RegistryObjectLists�����ʹ�<identifiable>��Ϊ��Ӧ�õ�����
	 * 
	 * @param rpt
	 */
	public static void ChangeRegistryObjectListTypes(RegistryPackageType rpt) {

	}

	public static void CreateRealRegistryPacakgeType(RegistryPackageType rpt) {

	}

	/**
	 * ���ݸ����Ĵ�������id������������һ��Ĵ������ĵ��ĵ�
	 * 
	 * @param list
	 *            �������ı�ʶ������
	 * @param fileRppath
	 *            �洢�����ĵ���·��,����
	 * @param resultType
	 *            ���ص�����full summary,brief
	 * @return �������д�������ebrim�ĵ�����
	 */
	@SuppressWarnings("unchecked")
	public static String CreateSensorDescriptionDocumentMethod(
			List<String> list, String fileRppath, String resultType) {
		if (list != null && list.size() != 0) {
			String allRecordsStrs = "";
			// ����ExtrinsicObject��idֵ����ѯ����SensorID �ĸ���RegistryPackage��ֵ
			List<String> registrypackageIdsList = getRegistryPackageUtil(list);
			int returncount = 0;
			returncount = registrypackageIdsList.size();
			// }
			for (int i = 0; i < returncount; i++) {
				// System.out.println("ÿһ�׶εĽ��������ʾ��");
				// System.out.println("��" + i + "��" + allRecordsStrs);
				// System.out
				// .println("================================================");
				RegistryPackageDocument rptdocument = RegistryPackageDocument.Factory
						.newInstance();
				RegistryPackageType rpt = rptdocument.addNewRegistryPackage();
				rpt = GetRegistryPackageTypeById(registrypackageIdsList.get(i),
						resultType);
				rptdocument.setRegistryPackage(rpt);
				int extricobjectCount = 0;
				int associationCount = 0;
				int organizationCount = 0;
				int personCount = 0;
				int serviceCount = 0;
				int classificationNodeCount = 0;
				if (rpt.getRegistryObjectList() == null) {
					allRecordsStrs += rptdocument.xmlText();
				}
				if (rpt.getRegistryObjectList() != null) {
					if (rpt.getRegistryObjectList().getIdentifiableArray() != null) {
						for (IdentifiableType it : rpt.getRegistryObjectList()
								.getIdentifiableArray()) {
							Session session = GetSessionUtil
									.GetSessionInstance(GetSessionUtil.SENSORTYPE);
							session.beginTransaction().begin();
							SQLQuery sl = session
									.createSQLQuery("select *from identifiable where id = '"
											+ it.getId() + "'");
							List iliest = sl.addEntity(Identifiable.class)
									.list();
							List<Identifiable> criteria = (List<Identifiable>) iliest;
							Identifiable ide = (Identifiable) criteria.get(0);
							session.beginTransaction().commit();
							GetSessionUtil.CloseSessionInstance(session);
							if (ide
									.getHome()
									.equals(
											"com.ebrim.model.wrs.impl.ExtrinsicObjectTypeImpl")) {
								extricobjectCount++;
							}
							if (ide
									.getHome()
									.equals(
											"com.ebrim.model.rim.impl.AssociationType1Impl")) {
								associationCount++;
							}
							if (ide
									.getHome()
									.equals(
											"com.ebrim.model.rim.impl.OrganizationTypeImpl")) {
								organizationCount++;
							}
							if (ide.getHome().equals(
									"com.ebrim.model.rim.impl.PersonTypeImpl")) {
								personCount++;
							}
							if (ide.getHome().equals(
									"com.ebrim.model.rim.impl.ServiceTypeImpl")) {
								serviceCount++;
							}
							if (ide
									.getHome()
									.equals(
											"com.ebrim.model.rim.impl.ClassificationNodeTypeImpl")) {
								classificationNodeCount++;
							}
						}
						ExtrinsicObjectDocument[] eoDocuments = new ExtrinsicObjectDocument[extricobjectCount];
						AssociationDocument[] atDocuments = new AssociationDocument[associationCount];
						OrganizationDocument[] organDocuments = new OrganizationDocument[organizationCount];
						com.ebrim.model.rim.PersonDocument[] personDocuments = new com.ebrim.model.rim.PersonDocument[personCount];
						ServiceDocument[] serviceDocuments = new ServiceDocument[serviceCount];
						ClassificationNodeDocument[] cndDocuments = new ClassificationNodeDocument[classificationNodeCount];
						int y = 0;
						int m = 0;
						int u = 0;
						int n = 0;
						int f = 0;
						int v = 0;
						for (IdentifiableType it : rpt.getRegistryObjectList()
								.getIdentifiableArray()) {
							Session session = GetSessionUtil
									.GetSessionInstance(GetSessionUtil.SENSORTYPE);
							session.beginTransaction().begin();
							SQLQuery sl = session
									.createSQLQuery("select *from identifiable where id = '"
											+ it.getId() + "'");
							List iliest = sl.addEntity(Identifiable.class)
									.list();
							List<Identifiable> criteria = (List<Identifiable>) iliest;
							Identifiable ide = (Identifiable) criteria.get(0);
							if (ide
									.getHome()
									.equals(
											"com.ebrim.model.wrs.impl.ExtrinsicObjectTypeImpl")) {
								eoDocuments[y] = ExtrinsicObjectDocument.Factory
										.newInstance();
								ExtrinsicObjectType eot = eoDocuments[y]
										.addNewExtrinsicObject();
								eot = (ExtrinsicObjectType) it;
								eoDocuments[y].setExtrinsicObject(eot);
								y++;
							}
							if (ide
									.getHome()
									.equals(
											"com.ebrim.model.rim.impl.AssociationType1Impl")) {
								atDocuments[m] = AssociationDocument.Factory
										.newInstance();
								AssociationType1 at = atDocuments[m]
										.addNewAssociation();
								at = (AssociationType1) it;
								atDocuments[m].setAssociation(at);
								m++;
							}
							if (ide
									.getHome()
									.equals(
											"com.ebrim.model.rim.impl.OrganizationTypeImpl")) {
								organDocuments[u] = OrganizationDocument.Factory
										.newInstance();
								OrganizationType ot = organDocuments[u]
										.addNewOrganization();
								ot = (OrganizationType) it;
								organDocuments[u].setOrganization(ot);
								u++;
							}
							if (ide.getHome().equals(
									"com.ebrim.model.rim.impl.PersonTypeImpl")) {
								personDocuments[n] = com.ebrim.model.rim.PersonDocument.Factory
										.newInstance();
								PersonType pt = personDocuments[n]
										.addNewPerson();
								pt = (PersonType) it;
								personDocuments[n].setPerson(pt);
								n++;
							}
							if (ide.getHome().equals(
									"com.ebrim.model.rim.impl.ServiceTypeImpl")) {
								serviceDocuments[f] = ServiceDocument.Factory
										.newInstance();
								ServiceType st = serviceDocuments[f]
										.addNewService();
								st = (ServiceType) it;
								serviceDocuments[f].setService(st);
								f++;
							}
							if (ide
									.getHome()
									.equals(
											"com.ebrim.model.rim.impl.ClassificationNodeTypeImpl")) {
								cndDocuments[v] = ClassificationNodeDocument.Factory
										.newInstance();
								ClassificationNodeType cnt = cndDocuments[v]
										.addNewClassificationNode();
								cnt = (ClassificationNodeType) it;
								cndDocuments[v].setClassificationNode(cnt);
								v++;
							}
							session.beginTransaction().commit();
							GetSessionUtil.CloseSessionInstance(session);
						}
						// �˲���ǳ���Ҫ���������ֺܶ಻��Ҫ����Ϣ
						rpt.getRegistryObjectList().setIdentifiableArray(null);
						RegistryObjectListType rolt = rpt
								.getRegistryObjectList();
						if (eoDocuments != null) {
							for (ExtrinsicObjectDocument eod : eoDocuments) {
								// �˲������������㣬1��ͨ��addNewIdentifiable()�Ĵ�����Ի�ȡ<identifiable>�ı�ǩ
								// ֮����ͨ��set<eod>����eod�Ķ������ݸ�����ı�
								// �������ϢҲ�����Ƶ�
								rolt.addNewIdentifiable().set(eod);
							}
						}
						if (serviceDocuments != null) {
							for (ServiceDocument sd : serviceDocuments) {
								rolt.addNewIdentifiable().set(sd);
							}
						}
						if (atDocuments != null) {
							for (AssociationDocument ad : atDocuments) {
								rolt.addNewIdentifiable().set(ad);
							}
						}
						if (cndDocuments != null) {
							for (ClassificationNodeDocument cnd : cndDocuments) {
								rolt.addNewIdentifiable().set(cnd);
							}
						}
						if (organDocuments != null) {
							for (OrganizationDocument od : organDocuments) {
								rolt.addNewIdentifiable().set(od);
							}
						}
						if (personDocuments != null) {
							for (com.ebrim.model.rim.PersonDocument pn : personDocuments) {
								rolt.addNewIdentifiable().set(pn);
							}
						}
						rpt.setRegistryObjectList(rolt);
						rptdocument.setRegistryPackage(rpt);
						allRecordsStrs += rptdocument.xmlText();

						String st1 = allRecordsStrs.replace(
								"<urn:Identifiable>", "");
						allRecordsStrs = st1.replace("</urn:Identifiable>", "");
					}

				} else {
					// System.out.println("û�з��������ļ�¼");
				}
			}
			return allRecordsStrs;

		} else {
			return "";
		}

	}

	/**
	 * ���ݻ�õ�RegistryPackage��Id��ȡRegistryPackageType���������еļ�¼��ϵ��һ����String���ʹ��ݳ�ȥ
	 * 
	 * @param resultType
	 *            full summary brief ��������֮һ
	 * @param getRecordsFilePath
	 *            �Ǵ��ݹ�����getRecords���ĵ�
	 * @author xunliangYang
	 * @return ���ؼ�¼���ַ�����RegistryPackage�����͵ġ�
	 */
	public static String ProduceRegistryPackageTypes(String resultType,
			String getRecordsFilePath, String fileRppath) {
		String allRecordsStrs = "";
		// ��ȡ��ѯʱ������Ҫ���������� ReadRequestGetRecordsDocument(String
		// filePath),������Щ�����洢��properties��
		// ���Խ�����任�Ǹ����ܵĹ�ʽ
		// ReadRequestGetRecordsDocument(getRecordsFilePath);
		ParseGetRecordsRequestUtil pgrrutil = new ParseGetRecordsRequestUtil();
		Map<String, String> mapssMap = pgrrutil
				.GetAndOrNotCengciGuanXiMap(FileOperationUtil.ReadFileContent(
						getRecordsFilePath, "UTF-8"));
		// System.out.println("==============��ʾ��Ҫ������ж�=========================");
		/*
		 * for (String str : mapssMap.keySet()) { System.out.println(str +
		 * "  :  " + mapssMap.get(str)); } System.out.println("����" +
		 * mapssMap.size() + "���ж�"); for (String str : mapssMap.keySet()) {
		 * System.out.println(str + " : " + mapssMap.get(str)); }
		 * System.out.println
		 * ("==============/��ʾ��Ҫ������ж�========================");
		 */
		// �����������ϣ���ȡ����������ExtrinsicObject�ļ���
		pgrrutil.GetExtrinsicObjects(mapssMap);
		mapssMap = null;
		// getConformInformation����ȡ��Щ���������������ķ���������id��Ϣ����
		List<String> list = new ArrayList<String>();
		if (ParseGetRecordsRequestUtil.getResultMaps().values().size() > 0) {
			for (String str : ParseGetRecordsRequestUtil.getResultMaps()
					.keySet()) {
				for (String strresult : ParseGetRecordsRequestUtil
						.getResultMaps().get(str)) {
					list.add(strresult);
				}
			}
		}
		// ��ResultMaps����Ϊ��
		ParseGetRecordsRequestUtil
				.setResultMaps(new HashMap<String, Set<String>>());
		// Ϊ���������е�session
		pgrrutil = null;
		// System.out.println(list.size());
		// List<String> list = GetConformInfomation();
		if (list != null) {
			allRecordsStrs = CreateSensorDescriptionDocumentMethod(list,
					fileRppath, resultType);
		}
		return allRecordsStrs;
	}

	/**
	 * �ַ����������
	 * 
	 * @param strSource
	 * @param strFrom
	 * @param strTo
	 * @return
	 */
	public static String replace(String strSource, String strFrom, String strTo) {
		if (strSource == null) {
			return null;
		}
		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strTo.toCharArray();
			int len = strFrom.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strFrom, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return strSource;
	}

	/**
	 * ����ȡ�ĸ��������ĸ������ĵ���ǰ׺�ĵ����й�������
	 * 
	 * @param sensorsStr
	 *            ��ȡ�����д������������ĵ�Ƭ����Ϣ
	 * @param filepath
	 *            �������ĵ��Ĵ洢��λ��
	 * @param resultType
	 *            ��Ҫ������
	 * @param maxRecordNum
	 *            ���ص����ֵ
	 * @param sensornums
	 *            ���صĴ������ĸ���
	 * @return �ɹ�����getRecordsResponse�ĵ���ʧ�ܷ��ؿ��ַ���
	 */
	public static String ConnectGetRecordsResponseDocument(String sensorsStr,
			String filepath, String resultType, int sensornums) {
		// ����ȡ����Ϣ�洢Ϊһ��GetRecordsResponseType����
		GetRecordsResponseDocument getRecordsResponseDocument = GetRecordsResponseDocument.Factory
				.newInstance();
		GetRecordsResponseType grt = getRecordsResponseDocument
				.addNewGetRecordsResponse();
		// ���ð汾
		grt.setVersion("1.0");
		// ���������״̬,������Բ����ã�Ŀǰ���Ժ��������
		// com.ebrim.model.csw.RequestStatusType requestStatusType = grt
		// .addNewSearchStatus();
		// ��������Ľ����״̬
		com.ebrim.model.csw.SearchResultsType searchResultsType = grt
				.addNewSearchResults();
		// ����setNumberOfRecordsMatched
		searchResultsType.setNumberOfRecordsMatched(BigInteger
				.valueOf(sensornums));
		// ����XML�ĵ�,Ҳ��������һ��outputStream����
		try {
			String allStr = getRecordsResponseDocument.xmlText();
			String lastString = "/></ns:GetRecordsResponse>";
			String subAllStr = allStr.substring(0, allStr.length()
					- lastString.length());
			// System.out.println(subAllStr);
			subAllStr += ">";
			// ���ݻ�õ�RegistryPackage��Id��ȡRegistryPackageType��
			// �������еļ�¼��ϵ��һ����String���ʹ��ݳ�ȥ
			subAllStr += sensorsStr.trim();
			subAllStr += "</ns:SearchResults></ns:GetRecordsResponse>";
			// FileOutputStream fos = new FileOutputStream(file);
			// fos.write(subAllStr.getBytes("UTF-8"));
			// fos.flush();
			// fos.close();
			return subAllStr;
			// System.out.println(allStr);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("������getResonse�ĵ���ʱ�����������!");
			return "";
		}

	}

	/**
	 * ���ݻ�ȡ�Ĳ�ѯ��Ϣ������GetRecordsResponse�ĵ�
	 * 
	 * @param resultType
	 *            �жϷ��غ������͵��ĵ���full summary brief��ѡһ
	 */
	public static void CreateGetRecordsResponseDocument(String resultType,
			String getRecordsFilePath, String filepath, String fileRppath) {
		// ����ȡ����Ϣ�洢Ϊһ��GetRecordsResponseType����
		GetRecordsResponseDocument getRecordsResponseDocument = GetRecordsResponseDocument.Factory
				.newInstance();
		GetRecordsResponseType grt = getRecordsResponseDocument
				.addNewGetRecordsResponse();
		// ���ð汾
		grt.setVersion("1.0");
		// ���������״̬,������Բ����ã�Ŀǰ���Ժ��������
		// com.ebrim.model.csw.RequestStatusType requestStatusType = grt
		// .addNewSearchStatus();
		// ��������Ľ����״̬
		com.ebrim.model.csw.SearchResultsType searchResultsType = grt
				.addNewSearchResults();
		String bigs = "" + registrypackageIdsList.size();
		BigInteger big = new BigInteger(bigs);
		// ����setNumberOfRecordsMatched
		if (!bigs.equals("0")) {
			searchResultsType.setNumberOfRecordsMatched(big);
		}
		// setNumberOfRecordsReturend
		if (maxRecordNum != null) {
			if (maxRecordNum.compareTo(big) == 1) {
				searchResultsType.setNumberOfRecordsReturned(big);
			} else if (maxRecordNum.compareTo(big) == -1) {
				searchResultsType.setNumberOfRecordsReturned(maxRecordNum);
			} else {
				searchResultsType.setNumberOfRecordsReturned(big);
			}
		} else {
			searchResultsType.setNumberOfRecordsReturned(big);
		}
		// ����XML�ĵ�,Ҳ��������һ��outputStream����
		try {
			File file = new File(filepath);
			getRecordsResponseDocument.save(file);
			getRecordsResponseDocument = null;
			BufferedReader br = new BufferedReader(new FileReader(file));
			String allStr = "";
			String readLineStr = "";
			while ((readLineStr = br.readLine()) != null) {
				allStr += readLineStr;
			}
			br.close();
			readLineStr = null;
			br = null;
			String lastString = "/></ns:GetRecordsResponse>";
			String subAllStr = allStr.substring(0, allStr.length()
					- lastString.length());
			// System.out.println(subAllStr);
			subAllStr += ">";
			// ���ݻ�õ�RegistryPackage��Id��ȡRegistryPackageType��
			// �������еļ�¼��ϵ��һ����String���ʹ��ݳ�ȥ
			subAllStr += ProduceRegistryPackageTypes(resultType,
					getRecordsFilePath, fileRppath);
			subAllStr += "</ns:SearchResults></ns:GetRecordsResponse>";
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(subAllStr.getBytes("UTF-8"));
			fos.flush();
			fos.close();

			// System.out.println(allStr);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("������getResonse�ĵ���ʱ�����������!");
		}
	}

	public static void CreateGetRecordsResponseDocument(List<String> sensors,
			String filepath) {
		// ����ȡ����Ϣ�洢Ϊһ��GetRecordsResponseType����
		GetRecordsResponseDocument getRecordsResponseDocument = GetRecordsResponseDocument.Factory
				.newInstance();
		GetRecordsResponseType grt = getRecordsResponseDocument
				.addNewGetRecordsResponse();
		// ���ð汾
		grt.setVersion("1.0");
		// ���������״̬,������Բ����ã�Ŀǰ���Ժ��������
		// com.ebrim.model.csw.RequestStatusType requestStatusType = grt
		// .addNewSearchStatus();
		// ��������Ľ����״̬
		com.ebrim.model.csw.SearchResultsType searchResultsType = grt
				.addNewSearchResults();
		String bigs = "" + registrypackageIdsList.size();
		BigInteger big = new BigInteger(bigs);
		// ����setNumberOfRecordsMatched
		if (!bigs.equals("0")) {
			searchResultsType.setNumberOfRecordsMatched(big);
		}
		// setNumberOfRecordsReturend
		if (maxRecordNum != null) {
			if (maxRecordNum.compareTo(big) == 1) {
				searchResultsType.setNumberOfRecordsReturned(big);
			} else if (maxRecordNum.compareTo(big) == -1) {
				searchResultsType.setNumberOfRecordsReturned(maxRecordNum);
			} else {
				searchResultsType.setNumberOfRecordsReturned(big);
			}
		} else {
			searchResultsType.setNumberOfRecordsReturned(big);
		}
		// ����XML�ĵ�,Ҳ��������һ��outputStream����
		try {
			File file = new File(filepath);
			getRecordsResponseDocument.save(file);
			getRecordsResponseDocument = null;
			BufferedReader br = new BufferedReader(new FileReader(file));
			String allStr = "";
			String readLineStr = "";
			while ((readLineStr = br.readLine()) != null) {
				allStr += readLineStr;
			}
			br.close();
			readLineStr = null;
			br = null;
			String lastString = "/></ns:GetRecordsResponse>";
			String subAllStr = allStr.substring(0, allStr.length()
					- lastString.length());
			// System.out.println(subAllStr);
			subAllStr += ">";
			// ���ݻ�õ�RegistryPackage��Id��ȡRegistryPackageType��
			// �������еļ�¼��ϵ��һ����String���ʹ��ݳ�ȥ
			subAllStr += CreateSensorDescriptionDocumentMethod(sensors,
					filepath, resultType);
			subAllStr += "</ns:SearchResults></ns:GetRecordsResponse>";
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(subAllStr.getBytes("UTF-8"));
			fos.flush();
			fos.close();

			// System.out.println(allStr);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("������getResonse�ĵ���ʱ�����������!");
		}
	}

	/**
	 * ����RegistryPackage��idֵ����ȡRegistryPackageType�����ͣ���������
	 * ���ҷ��ظ�CreateGetRecordsResponseDocument����
	 * 
	 * @param registrypackageId
	 *            ��Ҫ��ѯ��registrypackageIdֵ
	 * @return ��Ҫ���ص���RegistryPackageType�����û�У�����null
	 */
	@SuppressWarnings( { "unchecked" })
	public static RegistryPackageType GetRegistryPackageTypeById(
			String registrypackageId, String resultType) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		try {
			Criteria criteria = session.createCriteria(RegistryPackage.class);
			criteria.add(Expression.eq("id", registrypackageId));
			List<RegistryPackage> lists = (List<RegistryPackage>) criteria
					.list();
			RegistryPackage rp = lists.get(0);
			RegistryPackageType rpType = ParseRegistryPackageToRegistryPackageType(
					rp, session, resultType);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return rpType;
		} catch (Exception e) {
			e.printStackTrace();
			GetSessionUtil.CloseSessionInstance(session);
			return null;
		}
	}

	/**
	 * ����RegistryPackage����Ϣ����RegistryPackageType����
	 * 
	 * @param rp
	 * @return ����RegistrypackageType���ͷ���
	 */
	@SuppressWarnings("unchecked")
	public static RegistryPackageType ParseRegistryPackageToRegistryPackageType(
			RegistryPackage rp, Session session, String resultType) {
		RegistryPackageDocument rpdocument = RegistryPackageDocument.Factory
				.newInstance();
		RegistryPackageType rpt = rpdocument.addNewRegistryPackage();
		rpdocument = null;
		if (rp.getId() != null) {
			rpt.setId(rp.getId());
		}
		if (rp.getObjectType() != null) {
			rpt.setObjectType(parseObject(rp.getObjectType()));
		}
		// ����brief��ʽ
		if (resultType.equals("brief")) {
			if (rp.getStatus() != null) {
				rpt.setStatus(parseObject(rp.getStatus()));
			}
			if (rp.getLid() != null) {
				rpt.setLid(rp.getLid());
			}
			if (rp.getVersionInfo() != null) {
				rpt.setVersionInfo(ParseVersionInfoToVersionInfoType(rpt
						.getVersionInfo(), rp.getVersionInfo()));
			}
		} else if (resultType.equals("summary")) {
			// ����summary��ʽ
			if (rp.getObjectType() != null) {
				rpt.setObjectType(parseObject(rp.getObjectType()));
			}
			if (rp.getStatus() != null) {
				rpt.setStatus(parseObject(rp.getStatus()));
			}
			if (rp.getLid() != null) {
				rpt.setLid(rp.getLid());
			}
			if (rp.getVersionInfo() != null) {
				rpt.setVersionInfo(ParseVersionInfoToVersionInfoType(rpt
						.getVersionInfo(), rp.getVersionInfo()));
			}
			if (rp.getSlots() != null) {
				SlotType1[] sts = new SlotType1[rp.getSlots().size()];
				int i = 0;
				for (Slot slot : rp.getSlots()) {
					SlotType1 st1 = rpt.addNewSlot();
					sts[i] = ParseSlotToSlotType1(st1, slot);
					i++;
				}
				rpt.setSlotArray(sts);
			}
			if (rp.getName() != null) {
				rpt.setName(ParseInternationStringToInternationStringType(rpt
						.getName(), rp.getName()));
			}
			if (rp.getDescription() != null) {
				rpt
						.setDescription(ParseInternationStringToInternationStringType(
								rpt.getDescription(), rp.getDescription()));
			}

		} else if (resultType.equals("full")) {
			// ����full���
			if (rp.getHome() != null) {
				rpt.setHome(rp.getHome());
			}
			if (rp.getName() != null) {
				rpt.setName(ParseInternationStringToInternationStringType(rpt
						.addNewName(), rp.getName()));
			}
			if (rp.getObjectType() != null) {
				rpt.setObjectType(parseObject(rp.getObjectType()));
			}
			if (rp.getVersionInfo() != null) {
				rpt.setVersionInfo(ParseVersionInfoToVersionInfoType(rpt
						.addNewVersionInfo(), rp.getVersionInfo()));
			}
			if (rp.getClassifications() != null) {
				Set<ClassificationType> cls = new HashSet<ClassificationType>();
				for (Classification c : rp.getClassifications()) {
					cls.add(ParseClassificationToClassificationType(rpt
							.addNewClassification(), c));
				}
				rpt.setClassificationArray(cls
						.toArray(new ClassificationType[rp.getClassifications()
								.size()]));
			}
			if (rp.getDescription() != null) {
				rpt
						.setDescription(ParseInternationStringToInternationStringType(
								rpt.addNewDescription(), rp.getDescription()));
			}
			if (rp.getExternalIdentifiers() != null) {
				Set<ExternalIdentifierType> eit = new HashSet<ExternalIdentifierType>();
				for (ExternalIdentifier eif : rp.getExternalIdentifiers()) {
					eit.add(ParseExternalIdentifierToExternalIdentifierType(rpt
							.addNewExternalIdentifier(), eif));
				}
				rpt.setExternalIdentifierArray(eit
						.toArray(new ExternalIdentifierType[rp
								.getExternalIdentifiers().size()]));
			}
			if (rp.getSlots() != null) {
				Set<SlotType1> sts = new HashSet<SlotType1>();
				for (Slot slot : rp.getSlots()) {
					sts.add(ParseSlotToSlotType1(rpt.addNewSlot(), slot));
				}
				rpt.setSlotArray(sts
						.toArray(new SlotType1[rp.getSlots().size()]));
			}
			if (rp.getIdentifiables() != null) {
				RegistryObjectListType rolt = rpt.addNewRegistryObjectList();
				Set<IdentifiableType> idts = new HashSet<IdentifiableType>();
				for (Identifiable idf : rp.getIdentifiables()) {
					if (idf.getHome().equals(
							"com.ebrim.model.wrs.impl.ExtrinsicObjectTypeImpl")) {
						String extrinsicObjectId = idf.getId();
						SQLQuery sqlQuery = session
								.createSQLQuery("select * from extrinsicobject where id = '"
										+ extrinsicObjectId + "'");
						List list = sqlQuery.addEntity(ExtrinsicObject.class)
								.list();
						ExtrinsicObject eo = (ExtrinsicObject) list.get(0);
						ExtrinsicObjectDocument eodDocument = ExtrinsicObjectDocument.Factory
								.newInstance();
						ExtrinsicObjectType eot = eodDocument
								.addNewExtrinsicObject();
						ExtrinsicObjectType eotType = ParseExtrinsicObjectToExtrinsicObjectType(
								eot, eo);
						eodDocument.setExtrinsicObject(eotType);
						IdentifiableType idt = rolt.addNewIdentifiable();
						idt = (IdentifiableType) eotType;
						idts.add(idt);
					}
					if (idf.getHome().equals(
							"com.ebrim.model.rim.impl.ServiceTypeImpl")) {
						String serviceId = idf.getId();
						SQLQuery sqlQuery = session
								.createSQLQuery("select * from service where id ='"
										+ serviceId + "'");
						Service service = (Service) sqlQuery.addEntity(
								Service.class).list().get(0);
						ServiceType st = ServiceDocument.Factory.newInstance()
								.addNewService();
						ServiceType sType = ParseServiceToServiceType(st,
								service);
						IdentifiableType idt = rolt.addNewIdentifiable();
						idt = (IdentifiableType) sType;
						idts.add(idt);
					}
					if (idf
							.getHome()
							.equals(
									"com.ebrim.model.rim.impl.ClassificationNodeTypeImpl")) {
						String classificationNodeId = idf.getId();
						SQLQuery sqlQuery = session
								.createSQLQuery("select * from classificationnode where id ='"
										+ classificationNodeId + "'");
						ClassificationNode cfn = (ClassificationNode) sqlQuery
								.addEntity(ClassificationNode.class).list()
								.get(0);
						ClassificationNodeType cfnt = ClassificationNodeDocument.Factory
								.newInstance().addNewClassificationNode();
						ClassificationNodeType cntType = ParseClassificationNodeToClassificationNodType(
								cfnt, cfn);
						IdentifiableType idt = rolt.addNewIdentifiable();
						idt = (IdentifiableType) cntType;
						idts.add(idt);

					}
					if (idf.getHome().equals(
							"com.ebrim.model.rim.impl.AssociationType1Impl")) {
						String associationId = idf.getId();
						SQLQuery sqlQuery = session
								.createSQLQuery("select * from association where id ='"
										+ associationId + "'");
						Association association = (Association) sqlQuery
								.addEntity(Association.class).list().get(0);
						AssociationType1 at = AssociationDocument.Factory
								.newInstance().addNewAssociation();
						AssociationType1 ast = ParseAssociationToAssociationType(
								at, association);
						IdentifiableType ift = rolt.addNewIdentifiable();
						ift = (IdentifiableType) ast;
						idts.add(ift);
					}
					if (idf.getHome().equals(
							"com.ebrim.model.rim.impl.OrganizationTypeImpl")) {
						String organizatonId = idf.getId();
						Organization organ = (Organization) session
								.createSQLQuery(
										"select * from organization where id = '"
												+ organizatonId + "'")
								.addEntity(Organization.class).list().get(0);
						OrganizationType ot = OrganizationDocument.Factory
								.newInstance().addNewOrganization();
						OrganizationType oztType = ParseOrganizationToOrganizationType(
								ot, organ);
						// rolt.set(oztType);
						IdentifiableType ift = rolt.addNewIdentifiable();
						ift = (IdentifiableType) oztType;
						idts.add(ift);
					}
					if (idf.getHome().equals(
							"com.ebrim.model.rim.impl.PersonTypeImpl")) {
						String personId = idf.getId();
						Person person = (Person) session.createSQLQuery(
								"select * from person where id = '" + personId
										+ "'").addEntity(Person.class).list()
								.get(0);
						PersonType personTyp = com.ebrim.model.rim.PersonDocument.Factory
								.newInstance().addNewPerson();
						PersonType pt = ParsePersonToPersonType(personTyp,
								person);
						IdentifiableType ift = rolt.addNewIdentifiable();
						ift = (IdentifiableType) pt;
						idts.add(ift);

					}
				}
				rolt.setIdentifiableArray(idts
						.toArray(new IdentifiableType[idts.size()]));
				rpt.setRegistryObjectList(rolt);
				rolt = null;
			}
		}
		System.gc();
		return rpt;
	}

	/**
	 * ����person�����洢ΪpersonType����
	 * 
	 * @param pt
	 * @param person
	 */
	@SuppressWarnings("null")
	public static PersonType ParsePersonToPersonType(PersonType ptt,
			Person person) {
		PersonType pt = ptt;
		if (person.getId() != null) {
			pt.setId(person.getId());
		}
		if (person.getLid() != null) {
			pt.setLid(person.getLid());
		}
		if (person.getHome() != null) {
			pt.setHome(person.getHome());
		}
		if (person.getName() != null) {
			InternationalStringType ist = pt.addNewName();
			pt.setName(ParseInternationStringToInternationStringType(ist,
					person.getName()));
		}
		if (person.getDescription() != null) {
			InternationalStringType ist = pt.addNewDescription();
			pt.setDescription(ParseInternationStringToInternationStringType(
					ist, person.getDescription()));
		}
		if (person.getSlots() != null) {
			Set<SlotType1> stsSet = new HashSet<SlotType1>();
			for (Slot st1 : person.getSlots()) {
				stsSet.add(ParseSlotToSlotType1(pt.addNewSlot(), st1));
			}
			pt.setSlotArray(stsSet.toArray(new SlotType1[person.getSlots()
					.size()]));

		}
		if (person.getObjectType() != null) {
			pt.setObjectType(parseObject(person.getObjectType()));
		}
		if (person.getVersionInfo() != null) {
			VersionInfoType vif = pt.addNewVersionInfo();
			pt.setVersionInfo(ParseVersionInfoToVersionInfoType(vif, person
					.getVersionInfo()));
		}
		if (person.getTelephoneNumbers() != null) {
			TelephoneNumberType[] tnts = null;
			int i = 0;
			for (TelephoneNumber tn : person.getTelephoneNumbers()) {

				TelephoneNumberType tnt = pt.addNewTelephoneNumber();
				tnts[i] = ParseTelephoneNumberToTelephoneNumberType(tnt, tn);
				i++;
			}
			pt.setTelephoneNumberArray(tnts);

		}
		if (person.getStatus() != null) {
			pt.setStatus(parseObject(person.getStatus()));
		}
		if (person.getPersonName() != null) {
			PersonNameType pnt = pt.addNewPersonName();
			pt.setPersonName(ParsePersonNameToPersonNameType(pnt, person
					.getPersonName()));
		}
		if (person.getExternalIdentifiers() != null) {
			ExternalIdentifierType[] eift = null;
			int i = 0;
			for (ExternalIdentifier eif : person.getExternalIdentifiers()) {
				ExternalIdentifierType eit = pt.addNewExternalIdentifier();
				eift[i] = ParseExternalIdentifierToExternalIdentifierType(eit,
						eif);
				i++;
			}
			pt.setExternalIdentifierArray(eift);
		}
		if (person.getEmailAddresses() != null) {
			EmailAddressType[] eats = null;
			int i = 0;
			for (EmailAddress ea : person.getEmailAddresses()) {
				EmailAddressType eat = pt.addNewEmailAddress();
				eats[i] = ParseEmailAddressToEmailAddressType(eat, ea);
				i++;
			}
			pt.setEmailAddressArray(eats);
		}
		if (person.getClassifications() != null) {
			Set<ClassificationType> cts = new HashSet<ClassificationType>();
			for (Classification cft : person.getClassifications()) {
				cts.add(ParseClassificationToClassificationType(pt
						.addNewClassification(), cft));
			}
			pt.setClassificationArray(cts.toArray(new ClassificationType[person
					.getClassifications().size()]));
		}
		if (person.getAddresses() != null) {
			Set<PostalAddressType> pats = new HashSet<PostalAddressType>();
			for (PostalAddress pa : person.getAddresses()) {
				pats.add(ParsePostalAddressToPostalAddressType(pt
						.addNewAddress(), pa));
			}
			pt.setAddressArray(pats.toArray(new PostalAddressType[person
					.getAddresses().size()]));
		}
		return pt;
	}

	/**
	 * ����postalAddress����
	 * 
	 * @param pat
	 * @param pa
	 * @return
	 */
	private static PostalAddressType ParsePostalAddressToPostalAddressType(
			PostalAddressType pat, PostalAddress pa) {
		PostalAddressType patType = pat;
		if (pa.getCity() != null) {
			patType.setCity(pa.getCity().trim());
		}
		if (pa.getCountry() != null) {
			patType.setCountry(pa.getCountry().trim());
		}
		if (pa.getPostalCode() != null) {
			patType.setPostalCode(pa.getPostalCode().trim());
		}
		if (pa.getSlots() != null) {
		}
		if (pa.getStateOrProvince() != null) {
			patType.setStateOrProvince(pa.getStateOrProvince().trim());
		}
		if (pa.getStreet() != null) {
			patType.setStreet(pa.getStreet().trim());
		}
		if (pa.getStreetNumber() != null) {
			patType.setStreetNumber(pa.getStreetNumber().trim());
		}
		return patType;
	}

	/**
	 * ����EmailAddress����
	 * 
	 * @param eat
	 * @param ea
	 * @return
	 */
	public static EmailAddressType ParseEmailAddressToEmailAddressType(
			EmailAddressType eat, EmailAddress ea) {
		EmailAddressType eatType = eat;

		if (ea.getAddress() != null) {
			eatType.setAddress(ea.getAddress().trim());
		}
		if (ea.getType() != null) {
			eatType.setType(parseObject(ea.getType()).trim());
		}

		return eatType;
	}

	/**
	 * ����PersonName����
	 * 
	 * @param pnt
	 * @param pn
	 * @return
	 */
	public static PersonNameType ParsePersonNameToPersonNameType(
			PersonNameType pnt, PersonName pn) {
		PersonNameDocument pndDocument = PersonNameDocument.Factory
				.newInstance();
		PersonNameType pntType = pndDocument.addNewPersonName();
		if (pn.getFirstName() != null) {
			pntType.setFirstName(pn.getFirstName().trim());
		}
		if (pn.getMiddleName() != null) {
			pntType.setMiddleName(pn.getMiddleName().trim());
		}
		if (pn.getLastName() != null) {
			pntType.setLastName(pn.getLastName().trim());
		}
		return pntType;
	}

	/**
	 * ����TelPhoneNumber��������TelphoneNumbrType����
	 * 
	 */
	public static TelephoneNumberType ParseTelephoneNumberToTelephoneNumberType(
			TelephoneNumberType tnt, TelephoneNumber tn) {
		TelephoneNumberType tntType = tnt;
		if (tn.getAreaCode() != null) {
			tntType.setAreaCode(tn.getAreaCode().trim());
		}
		if (tn.getCountryCode() != null) {
			tntType.setCountryCode(tn.getCountryCode().trim());
		}
		if (tn.getExtersion() != null) {
			tntType.setExtension(tn.getExtersion().trim());
		}
		if (tn.getNumber() != null) {
			tntType.setNumber(tn.getNumber().trim());
		}
		if (tn.getPhoneType() != null) {
			tntType.setPhoneType(parseObject(tn.getPhoneType()).trim());
		}
		return tntType;
	}

	/**
	 * ����versionInfo��������VersionInfoType����
	 * 
	 * @param vif
	 * @param vi
	 * @return
	 */
	public static VersionInfoType ParseVersionInfoToVersionInfoType(
			VersionInfoType vif, VersionInfo vi) {
		VersionInfoType vifType = vif;
		if (vi.getConmment() != null) {
			vifType.setComment(vif.getComment());
		}
		if (vi.getVersionName() != null) {
			vifType.setVersionName(vi.getVersionName());
		}
		return vifType;
	}

	/**
	 * ��ȡ��object�е�������Ϣ
	 * 
	 * @return
	 */
	public static String parseObject(ObjectRef ort) {

		return ort.getHome();
	}

	/**
	 * ����һ��internationalString��InternationalStringType���͵ĺ���
	 * 
	 * @param is
	 * @return
	 */
	public static InternationalStringType ParseInternationStringToInternationStringType(
			InternationalStringType ist, InternationalString is) {
		for (LocalizedString ls : is.getLocalizedStrings()) {
			LocalizedStringType lst = ist.addNewLocalizedString();
			if (ls.getLang() != null) {
				lst.setLang(ls.getLang());
			}
			if (ls.getCharset() != null) {
				XmlAnySimpleType sat = XmlAnySimpleType.Factory.newInstance();
				sat.setStringValue(ls.getCharset());
				lst.setCharset(sat);
			}
			if (ls.getValue() != null) {
				lst.setValue(ls.getValue());
			}
		}
		return ist;
	}

	/**
	 * ����organization�����洢ΪorganizationType����
	 * 
	 * @param ot
	 * @param organ
	 */
	public static OrganizationType ParseOrganizationToOrganizationType(
			OrganizationType ot, Organization organ) {
		OrganizationType otype = ot;
		if (organ.getAddresses() != null) {
			Set<PostalAddressType> pat = new HashSet<PostalAddressType>();
			for (PostalAddress pa : organ.getAddresses()) {
				pat.add(ParsePostalAddressToPostalAddressType(otype
						.addNewAddress(), pa));
			}
			otype.setAddressArray(pat.toArray(new PostalAddressType[organ
					.getAddresses().size()]));

		}
		if (organ.getClassifications() != null) {
			Set<ClassificationType> cfts = new HashSet<ClassificationType>();
			for (Classification cla : organ.getClassifications()) {
				cfts.add(ParseClassificationToClassificationType(otype
						.addNewClassification(), cla));
			}
			otype.setClassificationArray(cfts
					.toArray(new ClassificationType[organ.getClassifications()
							.size()]));
		}
		if (organ.getDescription() != null) {
			otype.setDescription(ParseInternationStringToInternationStringType(
					otype.addNewDescription(), organ.getDescription()));
		}
		if (organ.getEmailAddresses() != null) {
			Set<EmailAddressType> eatSet = new HashSet<EmailAddressType>();
			for (EmailAddress ea : organ.getEmailAddresses()) {
				eatSet.add(ParseEmailAddressToEmailAddressType(otype
						.addNewEmailAddress(), ea));
			}
			otype.setEmailAddressArray(eatSet
					.toArray(new EmailAddressType[organ.getEmailAddresses()
							.size()]));
		}
		if (organ.getExternalIdentifiers() != null) {
			Set<ExternalIdentifierType> eitf = new HashSet<ExternalIdentifierType>();
			for (ExternalIdentifier ei : organ.getExternalIdentifiers()) {
				eitf.add(ParseExternalIdentifierToExternalIdentifierType(otype
						.addNewExternalIdentifier(), ei));
			}
			otype.setExternalIdentifierArray(eitf
					.toArray(new ExternalIdentifierType[organ
							.getExternalIdentifiers().size()]));
		}
		if (organ.getHome() != null) {
			otype.setHome(organ.getHome());
		}
		if (organ.getId() != null) {
			otype.setId(organ.getId());
		}
		if (organ.getLid() != null) {
			otype.setLid(organ.getLid());
		}
		if (organ.getName() != null) {
			otype.setName(ParseInternationStringToInternationStringType(otype
					.addNewName(), organ.getName()));
		}
		if (organ.getObjectType() != null) {
			otype.setObjectType(parseObject(organ.getObjectType()));
		}
		if (organ.getParent() != null) {
			otype.setParent(parseObject(organ.getParent()));
		}
		if (organ.getPrimaryContact() != null) {
			otype.setPrimaryContact(parseObject(organ.getPrimaryContact()));
		}
		if (organ.getSlots() != null) {
			Set<SlotType1> stsSet = new HashSet<SlotType1>();
			for (Slot slot : organ.getSlots()) {
				stsSet.add(ParseSlotToSlotType1(otype.addNewSlot(), slot));
			}
			otype.setSlotArray(stsSet.toArray(new SlotType1[organ.getSlots()
					.size()]));
		}
		if (organ.getStatus() != null) {
			otype.setStatus(parseObject(organ.getStatus()));
		}
		if (organ.getVersionInfo() != null) {
			otype.setVersionInfo(ParseVersionInfoToVersionInfoType(otype
					.addNewVersionInfo(), organ.getVersionInfo()));
		}
		return otype;
	}

	/**
	 *����Associationֵ�����洢ΪAssociationType����
	 * 
	 * @param at
	 * @param association
	 */
	public static AssociationType1 ParseAssociationToAssociationType(
			AssociationType1 at, Association ass) {
		AssociationType1 atType = at;
		if (ass.getAssociationType() != null) {
			atType.setAssociationType(parseObject(ass.getAssociationType()));
		}
		if (ass.getClassifications() != null) {
			Set<ClassificationType> cts = new HashSet<ClassificationType>();
			for (Classification c : ass.getClassifications()) {
				cts.add(ParseClassificationToClassificationType(atType
						.addNewClassification(), c));
			}
			atType.setClassificationArray(cts
					.toArray(new ClassificationType[ass.getClassifications()
							.size()]));
		}
		if (ass.getDescription() != null) {
			atType
					.setDescription(ParseInternationStringToInternationStringType(
							atType.addNewDescription(), ass.getDescription()));
		}
		if (ass.getExternalIdentifiers() != null) {
			Set<ExternalIdentifierType> eifSet = new HashSet<ExternalIdentifierType>();
			for (ExternalIdentifier ei : ass.getExternalIdentifiers()) {
				eifSet.add(ParseExternalIdentifierToExternalIdentifierType(
						atType.addNewExternalIdentifier(), ei));
			}
			atType.setExternalIdentifierArray(eifSet
					.toArray(new ExternalIdentifierType[ass
							.getExternalIdentifiers().size()]));
		}
		if (ass.getHome() != null) {
			atType.setHome(ass.getHome());
		}
		if (ass.getId() != null) {
			atType.setId(ass.getId());
		}
		if (ass.getLid() != null) {
			atType.setLid(ass.getLid());
		}
		if (ass.getName() != null) {
			atType.setName(ParseInternationStringToInternationStringType(atType
					.addNewName(), ass.getName()));
		}
		if (ass.getObjectType() != null) {
			atType.setObjectType(parseObject(ass.getObjectType()));
		}
		if (ass.getSlots() != null) {
			Set<SlotType1> st1Set = new HashSet<SlotType1>();
			for (Slot slot : ass.getSlots()) {
				st1Set.add(ParseSlotToSlotType1(atType.addNewSlot(), slot));
			}
			atType.setSlotArray(st1Set.toArray(new SlotType1[ass.getSlots()
					.size()]));
		}
		if (ass.getSourceObject() != null) {
			atType.setSourceObject(parseObject(ass.getSourceObject()));
		}
		if (ass.getStatus() != null) {
			atType.setStatus(parseObject(ass.getStatus()));
		}
		if (ass.getTargetObject() != null) {
			atType.setTargetObject(parseObject(ass.getTargetObject()));
		}
		if (ass.getVersionInfo() != null) {
			atType.setVersionInfo(ParseVersionInfoToVersionInfoType(atType
					.addNewVersionInfo(), ass.getVersionInfo()));
		}
		return atType;
	}

	/**
	 * ����ClassificatinNodeֵ�����洢ΪClassificatinNodeType����
	 * 
	 * @param cfnt
	 * @param cfn
	 */
	public static ClassificationNodeType ParseClassificationNodeToClassificationNodType(
			ClassificationNodeType cfnt, ClassificationNode cfn) {
		ClassificationNodeType cfntype = cfnt;
		if (cfn.getClassifications() != null) {
			Set<ClassificationType> cftSet = new HashSet<ClassificationType>();
			for (Classification cla : cfn.getClassifications()) {
				cftSet.add(ParseClassificationToClassificationType(cfntype
						.addNewClassification(), cla));
			}
			cfntype.setClassificationArray(cftSet
					.toArray(new ClassificationType[cfn.getClassifications()
							.size()]));
		}
		if (cfn.getCode() != null) {
			cfntype.setCode(cfn.getCode());
		}
		if (cfn.getDescription() != null) {
			cfntype
					.setDescription(ParseInternationStringToInternationStringType(
							cfntype.addNewDescription(), cfn.getDescription()));
		}
		if (cfn.getExternalIdentifiers() != null) {
			Set<ExternalIdentifierType> eifSet = new HashSet<ExternalIdentifierType>();
			for (ExternalIdentifier ei : cfn.getExternalIdentifiers()) {
				eifSet.add(ParseExternalIdentifierToExternalIdentifierType(
						cfntype.addNewExternalIdentifier(), ei));
			}
			cfntype.setExternalIdentifierArray(eifSet
					.toArray(new ExternalIdentifierType[cfn
							.getExternalIdentifiers().size()]));
		}
		if (cfn.getHome() != null) {
			cfntype.setHome(cfn.getHome());
		}
		if (cfn.getId() != null) {
			cfntype.setId(cfn.getId());
		}
		if (cfn.getLid() != null) {
			cfntype.setLid(cfn.getLid());
		}
		if (cfn.getName() != null) {
			cfntype.setName(ParseInternationStringToInternationStringType(
					cfntype.addNewName(), cfn.getName()));
		}
		if (cfn.getObjectType() != null) {
			cfntype.setObjectType(parseObject(cfn.getObjectType()));
		}
		if (cfn.getParent() != null) {
			cfntype.setParent(parseObject(cfn.getParent()));
		}
		if (cfn.getPath() != null) {
			cfntype.setPath(cfn.getPath());
		}
		if (cfn.getSlots() != null) {
			Set<SlotType1> stsSet = new HashSet<SlotType1>();
			for (Slot slot : cfn.getSlots()) {
				stsSet.add(ParseSlotToSlotType1(cfntype.addNewSlot(), slot));
			}
			cfntype.setSlotArray(stsSet.toArray(new SlotType1[cfn.getSlots()
					.size()]));
		}
		if (cfn.getStatus() != null) {
			cfntype.setStatus(parseObject(cfn.getStatus()));
		}
		if (cfn.getVersionInfo() != null) {
			cfntype.setVersionInfo(ParseVersionInfoToVersionInfoType(cfntype
					.addNewVersionInfo(), cfn.getVersionInfo()));
		}
		return cfntype;
	}

	/**
	 * ���� serviceֵ�����洢ΪServiceType����
	 * 
	 * @param st
	 * @param service
	 */
	public static ServiceType ParseServiceToServiceType(ServiceType st,
			Service service) {
		ServiceType stType = st;
		if (service.getClassifications() != null) {
			Set<ClassificationType> cts = new HashSet<ClassificationType>();
			for (Classification cf : service.getClassifications()) {
				cts.add(ParseClassificationToClassificationType(stType
						.addNewClassification(), cf));
			}
			stType.setClassificationArray(cts
					.toArray(new ClassificationType[service
							.getClassifications().size()]));
		}
		if (service.getDescription() != null) {
			stType
					.setDescription(ParseInternationStringToInternationStringType(
							stType.addNewDescription(), service
									.getDescription()));
		}
		if (service.getExternalIdentifiers() != null) {
			Set<ExternalIdentifierType> eitSet = new HashSet<ExternalIdentifierType>();
			for (ExternalIdentifier ei : service.getExternalIdentifiers()) {
				eitSet.add(ParseExternalIdentifierToExternalIdentifierType(
						stType.addNewExternalIdentifier(), ei));
			}
			stType.setExternalIdentifierArray(eitSet
					.toArray(new ExternalIdentifierType[service
							.getExternalIdentifiers().size()]));
		}
		if (service.getHome() != null) {
			stType.setHome(service.getHome());
		}
		if (service.getId() != null) {
			stType.setId(service.getId());
		}
		if (service.getLid() != null) {
			stType.setLid(service.getLid());
		}
		if (service.getName() != null) {
			stType.setName(ParseInternationStringToInternationStringType(stType
					.addNewName(), service.getName()));
		}
		if (service.getObjectType() != null) {
			stType.setObjectType(parseObject(service.getObjectType()));
		}
		if (service.getServiceBindings() != null) {
			Set<ServiceBindingType> sbTypes = new HashSet<ServiceBindingType>();
			for (ServiceBinding sb : service.getServiceBindings()) {
				sbTypes.add(ParseServiceBindingToServiceBindingType(stType
						.addNewServiceBinding(), sb));
			}
			stType.setServiceBindingArray(sbTypes
					.toArray(new ServiceBindingType[service
							.getServiceBindings().size()]));
		}
		if (service.getSlots() != null) {
			Set<SlotType1> slotType1s = new HashSet<SlotType1>();
			for (Slot slot : service.getSlots()) {
				slotType1s.add(ParseSlotToSlotType1(stType.addNewSlot(), slot));
			}
			stType.setSlotArray(slotType1s.toArray(new SlotType1[service
					.getSlots().size()]));
		}
		if (service.getStatus() != null) {
			stType.setStatus(parseObject(service.getStatus()));
		}
		if (service.getVersionInfo() != null) {
			stType.setVersionInfo(ParseVersionInfoToVersionInfoType(stType
					.addNewVersionInfo(), service.getVersionInfo()));
		}
		return stType;
	}

	/**
	 * ����ServiceBindingֵ�����洢ΪserviceBindingType����
	 * 
	 * @param sbt
	 * @param sb
	 * @return
	 */
	public static ServiceBindingType ParseServiceBindingToServiceBindingType(
			ServiceBindingType sbt, ServiceBinding sb) {
		ServiceBindingType sbtType = sbt;
		if (sb.getAccessURI() != null) {
			sbtType.setAccessURI(sb.getAccessURI());
		}
		if (sb.getClassifications() != null) {
			Set<ClassificationType> cftTypes = new HashSet<ClassificationType>();
			for (Classification cla : sb.getClassifications()) {
				cftTypes.add(ParseClassificationToClassificationType(sbtType
						.addNewClassification(), cla));
			}
			sbtType.setClassificationArray(cftTypes
					.toArray(new ClassificationType[sb.getClassifications()
							.size()]));
		}
		if (sb.getDescription() != null) {
			sbtType
					.setDescription(ParseInternationStringToInternationStringType(
							sbtType.addNewDescription(), sb.getDescription()));
		}
		if (sb.getExternalIdentifiers() != null) {
			Set<ExternalIdentifierType> eifTypes = new HashSet<ExternalIdentifierType>();
			for (ExternalIdentifier ei : sb.getExternalIdentifiers()) {
				eifTypes.add(ParseExternalIdentifierToExternalIdentifierType(
						sbtType.addNewExternalIdentifier(), ei));
			}
			sbtType.setExternalIdentifierArray(eifTypes
					.toArray(new ExternalIdentifierType[sb
							.getExternalIdentifiers().size()]));
		}
		if (sb.getHome() != null) {
			sbtType.setHome(sb.getHome());
		}
		if (sb.getId() != null) {
			sbtType.setId(sb.getId());
		}
		if (sb.getLid() != null) {
			sbtType.setLid(sb.getLid());
		}
		if (sb.getName() != null) {
			sbtType.setName(ParseInternationStringToInternationStringType(
					sbtType.addNewName(), sb.getName()));
		}
		if (sb.getObjectType() != null) {
			sbtType.setObjectType(parseObject(sb.getObjectType()));
		}
		if (sb.getService() != null) {
			sbtType.setService(parseObject(sb.getService()));
		}
		if (sb.getSlots() != null) {
			Set<SlotType1> st1Set = new HashSet<SlotType1>();
			for (Slot slot : sb.getSlots()) {
				st1Set.add(ParseSlotToSlotType1(sbtType.addNewSlot(), slot));
			}
			sbtType.setSlotArray(st1Set.toArray(new SlotType1[sb.getSlots()
					.size()]));
		}
		if (sb.getSpecificationLinks() != null) {
			Set<SpecificationLinkType> ssfltSet = new HashSet<SpecificationLinkType>();
			for (SpecificationLink spl : sb.getSpecificationLinks()) {
				ssfltSet.add(parseSpecificationLinkToSpecificationLinkType(
						sbtType.addNewSpecificationLink(), spl));
			}
			sbtType.setSpecificationLinkArray(ssfltSet
					.toArray(new SpecificationLinkType[sb
							.getSpecificationLinks().size()]));
		}
		if (sb.getStatus() != null) {
			sbtType.setStatus(parseObject(sb.getStatus()));
		}
		if (sb.getTargetBinding() != null) {
			sbtType.setTargetBinding(parseObject(sb.getTargetBinding()));
		}
		if (sb.getVersionInfo() != null) {
			sbtType.setVersionInfo(ParseVersionInfoToVersionInfoType(sbtType
					.addNewVersionInfo(), sb.getVersionInfo()));
		}
		return sbtType;
	}

	/**
	 * 
	 * @param addNewSpecificationLink
	 * @param spl
	 * @return
	 */
	private static SpecificationLinkType parseSpecificationLinkToSpecificationLinkType(
			SpecificationLinkType slk, SpecificationLink spl) {
		SpecificationLinkType slkType = slk;
		if (spl.getClassifications() != null) {
			Set<ClassificationType> splkTypes = new HashSet<ClassificationType>();
			for (Classification cspl : spl.getClassifications()) {
				splkTypes.add(ParseClassificationToClassificationType(slk
						.addNewClassification(), cspl));
			}
			slk.setClassificationArray(splkTypes
					.toArray(new ClassificationType[spl.getClassifications()
							.size()]));
		}
		if (spl.getDescription() != null) {
			slkType
					.setDescription(ParseInternationStringToInternationStringType(
							slkType.addNewDescription(), spl.getDescription()));
		}
		if (spl.getExternalIdentifiers() != null) {
			Set<ExternalIdentifierType> eitfsTypes = new HashSet<ExternalIdentifierType>();
			for (ExternalIdentifier ei : spl.getExternalIdentifiers()) {
				eitfsTypes.add(ParseExternalIdentifierToExternalIdentifierType(
						slk.addNewExternalIdentifier(), ei));
			}
			slk.setExternalIdentifierArray(eitfsTypes
					.toArray(new ExternalIdentifierType[spl
							.getExternalIdentifiers().size()]));
		}
		if (spl.getHome() != null) {
			slkType.setHome(spl.getHome());
		}
		if (spl.getId() != null) {
			slkType.setId(spl.getId());
		}
		if (spl.getLid() != null) {
			slkType.setLid(spl.getLid());
		}
		if (spl.getName() != null) {
			slkType.setName(ParseInternationStringToInternationStringType(
					slkType.addNewName(), spl.getName()));
		}
		if (spl.getObjectType() != null) {
			slkType.setObjectType(parseObject(spl.getObjectType()));
		}
		if (spl.getServiceBinding() != null) {
			slkType.setServiceBinding(parseObject(spl.getServiceBinding()));
		}
		if (spl.getSlots() != null) {
			Set<SlotType1> sts = new HashSet<SlotType1>();
			for (Slot slot : spl.getSlots()) {
				sts.add(ParseSlotToSlotType1(slk.addNewSlot(), slot));
			}
			slk.setSlotArray(sts.toArray(new SlotType1[spl.getSlots().size()]));
		}
		if (spl.getSpecificationObject() != null) {
			slkType.setSpecificationObject(parseObject(spl
					.getSpecificationObject()));
		}
		if (spl.getStatus() != null) {
			slkType.setStatus(parseObject(spl.getStatus()));
		}
		if (spl.getUsageDescription() != null) {
			slkType
					.setUsageDescription(ParseInternationStringToInternationStringType(
							slkType.addNewUsageDescription(), spl
									.getUsageDescription()));
		}
		// ��Ҫ����Щ�����ֵ�ֽ�
		if (spl.getUsageParameters() != null) {
		}
		if (spl.getVersionInfo() != null) {
			slkType.setVersionInfo(ParseVersionInfoToVersionInfoType(slkType
					.addNewVersionInfo(), spl.getVersionInfo()));
		}
		return slkType;
	}

	/**
	 * ����ExtrinsicObject�����ұ���ΪExrinsicObjectType����
	 */
	public static ExtrinsicObjectType ParseExtrinsicObjectToExtrinsicObjectType(
			ExtrinsicObjectType eot, ExtrinsicObject eo) {
		ExtrinsicObjectType eotType = eot;
		if (eo.getIsOpaque() == true) {
			eotType.setIsOpaque(true);
		} else {
			eotType.setIsOpaque(false);
		}
		if (eo.getClassifications() != null) {
			Set<ClassificationType> cts = new HashSet<ClassificationType>();
			for (Classification cla : eo.getClassifications()) {
				cts.add(ParseClassificationToClassificationType(eotType
						.addNewClassification(), cla));
			}
			eotType.setClassificationArray(cts
					.toArray(new ClassificationType[eo.getClassifications()
							.size()]));
		}
		if (eo.getContentVersionInfo() != null) {
			eotType.setContentVersionInfo(ParseVersionInfoToVersionInfoType(
					eotType.addNewContentVersionInfo(), eo
							.getContentVersionInfo()));
		}
		if (eo.getDescription() != null) {
			eotType
					.setDescription(ParseInternationStringToInternationStringType(
							eotType.addNewDescription(), eo.getDescription()));
		}
		if (eo.getExternalIdentifiers() != null) {
			Set<ExternalIdentifierType> eift = new HashSet<ExternalIdentifierType>();
			for (ExternalIdentifier eif : eo.getExternalIdentifiers()) {
				eift.add(ParseExternalIdentifierToExternalIdentifierType(
						eotType.addNewExternalIdentifier(), eif));
			}
			eotType.setExternalIdentifierArray(eift
					.toArray(new ExternalIdentifierType[eo
							.getExternalIdentifiers().size()]));
		}
		if (eo.getHome() != null) {
			eotType.setHome(eo.getHome());
		}
		if (eo.getId() != null) {
			eotType.setId(eo.getId());
		}
		if (eo.getLid() != null) {
			eotType.setLid(eo.getLid());
		}
		if (eo.getLname() != null) {
			InternationalStringType ist = InternationalStringDocument.Factory
					.newInstance().addNewInternationalString();
			ist.setLocalizedStringArray(0,
					parseLocalizedStringToLocalizedStringType(ist
							.addNewLocalizedString(), eo.getLname()));
			eotType.setName(ist);
		}
		if (eo.getName() != null) {
			// �����κεĴ���
		}
		if (eo.getMimeType() != null) {
			eotType.setMimeType(eo.getMimeType());
		}
		if (eo.getObjectType() != null) {
			eotType.setObjectType(parseObject(eo.getObjectType()));
		}
		if (eo.getSlots() != null) {
			Set<SlotType1> sts = new HashSet<SlotType1>();
			for (Slot slot : eo.getSlots()) {
				sts.add(ParseSlotToSlotType1(eotType.addNewSlot(), slot));
			}
			eotType.setSlotArray(sts
					.toArray(new SlotType1[eo.getSlots().size()]));
		}
		if (eo.getStatus() != null) {
			eotType.setStatus(parseObject(eo.getStatus()));
		}
		if (eo.getVersionInfo() != null) {
			eotType.setVersionInfo(ParseVersionInfoToVersionInfoType(eotType
					.addNewVersionInfo(), eo.getVersionInfo()));
		}
		return eotType;
	}

	public static LocalizedStringType parseLocalizedStringToLocalizedStringType(
			LocalizedStringType lst, LocalizedString ls) {
		LocalizedStringType lstType = lst;
		if (ls.getLang() != null) {
			lstType.setLang(ls.getLang());
		}
		if (ls.getValue() != null) {
			lstType.setValue(ls.getValue());
		}
		return lstType;
	}

	/**
	 * ����Slot���洢ΪSlotType1����
	 * 
	 * @param st
	 * @param slot
	 */
	public static SlotType1 ParseSlotToSlotType1(SlotType1 st, Slot slot) {
		SlotType1 slotType1 = st;
		if (slot.getName() != null) {
			slotType1.setName(slot.getName());
		}
		if (slot.getSlotType() != null) {
			slotType1.setSlotType(slot.getSlotType());
		}
		if (slot.getValues() != null) {
			if (slot.getSlotType() != null) {
				if (slot.getSlotType().equals(
						"urn:ogc:def:dataType:ISO-19107:2003:GM_Envelope")) {
					String initialStr = slot.getValues();
					initialStr = initialStr.substring(1,
							initialStr.length() - 2);
					String[] SlotStrs = initialStr.split(",");
					EnvelopeDocument envelopeDocument = EnvelopeDocument.Factory
							.newInstance();
					EnvelopeType envelopeType = envelopeDocument
							.addNewEnvelope();
					envelopeType.setSrsName(SlotStrs[0]);
					DirectPositionType ditlower = envelopeType
							.addNewLowerCorner();
					DirectPositionType ditupper = envelopeType
							.addNewUpperCorner();
					ditlower.setStringValue(SlotStrs[1]);
					ditupper.setStringValue(SlotStrs[2]);
					envelopeType.setLowerCorner(ditlower);
					envelopeType.setUpperCorner(ditupper);
					envelopeDocument.setEnvelope(envelopeType);
					slotType1.addNewValueList();
					ValueListDocument vldDocument = ValueListDocument.Factory
							.newInstance();
					com.ebrim.model.wrs.ValueListType vltw = vldDocument
							.addNewValueList2();
					slotType1.setValueList(vltw);
					AnyValueType avt = vltw.addNewAnyValue();
					avt.set(envelopeType);
					slotType1.setValueList(vltw);

				} else if (slot.getSlotType().equals(
						"urn:oasis:names:tc:ebxml-regrep:DataType:String")) {
					if (slot.getName().contains("Process")) {
						ValueListType vlt = slotType1.addNewValueList();
						vlt.addNewValue().setStringValue(
								slot.getValues().trim());
						slotType1.setValueList(vlt);
					} else if (slot.getName().endsWith("components")) {
						ValueListType vlt = slotType1.addNewValueList();
						vlt.addNewValue().setStringValue(
								slot.getValues().trim());
						slotType1.setValueList(vlt);

					} else {

						ValueListType vlt = slotType1.addNewValueList();
						String initialStr = slot.getValues();
						if (initialStr != null && initialStr.trim() != ""
								&& initialStr.trim().length() > 0) {
							String[] slotStrs = initialStr.substring(0,
									initialStr.length() - 1).split(",");
							for (String ss : slotStrs) {
								vlt.addNewValue().setStringValue(ss.trim());
							}
							slotType1.setValueList(vlt);
						}

					}
				} else if (slot.getSlotType().equals(
						"urn:oasis:names:tc:ebxml-regrep:DataType:DateTime")) {
					// ����string�Ĵ�����
					ValueListType vlt = slotType1.addNewValueList();
					String initialStr = slot.getValues();
					String[] slotStrs = initialStr.substring(0,
							initialStr.length() - 1).split(",");
					for (String ss : slotStrs) {
						vlt.addNewValue().setStringValue(ss.trim());
					}
					slotType1.setValueList(vlt);
				} else if (slot.getSlotType().equals(
						"urn:ogc:def:dataType:ISO-19107:2003:GM_Point")) {
					String initivalStr = slot.getValues();
					initivalStr = initivalStr.substring(1,
							initivalStr.length() - 2);
					String[] strs = initivalStr.split(",");
					PointDocument pd = PointDocument.Factory.newInstance();
					PointType pt = pd.addNewPoint();
					DirectPositionType dpt = pt.addNewPos();
					pt.setId(strs[0]);
					dpt.setStringValue(strs[1] + " " + strs[2]);
					pt.setPos(dpt);
					pd.setPoint(pt);
					slotType1.addNewValueList();
					ValueListDocument vld = ValueListDocument.Factory
							.newInstance();
					com.ebrim.model.wrs.ValueListType vlt = vld
							.addNewValueList2();
					AnyValueType avt = vlt.addNewAnyValue();
					avt.set(pt);
					vlt.set(avt);
					slotType1.setValueList(vlt);
				}
			} else {
				// ���������ʱ�������
				// System.out.println(slot.getValues());
				ValueListType vlt = slotType1.addNewValueList();
				String initialStr = slot.getValues();
				String[] slotStrs = initialStr.substring(0,
						initialStr.length() - 1).split(",");
				for (String ss : slotStrs) {
					vlt.addNewValue().setStringValue(ss.trim());
				}
				slotType1.setValueList(vlt);
			}

		}
		return slotType1;
	}

	/**
	 * ����ExternalIdentifier���洢ΪExternalIdentifierType����
	 * 
	 * @param eift
	 * @param eif
	 */
	public static ExternalIdentifierType ParseExternalIdentifierToExternalIdentifierType(
			ExternalIdentifierType eift, ExternalIdentifier eif) {
		ExternalIdentifierType eifType = eift;
		if (eif.getClassifications() != null) {
			int i = 0;
			for (Classification c : eif.getClassifications()) {
				eifType.getClassificationArray()[i] = ParseClassificationToClassificationType(
						eifType.addNewClassification(), c);
				i++;
			}
		}
		if (eif.getDescription() != null) {
			eifType
					.setDescription(ParseInternationStringToInternationStringType(
							eifType.addNewDescription(), eif.getDescription()));
		}
		if (eif.getExternalIdentifiers() != null) {
			int i = 0;
			for (ExternalIdentifier ei : eif.getExternalIdentifiers()) {
				eifType.getExternalIdentifierArray()[i] = ParseExternalIdentifierToExternalIdentifierType(
						eifType.addNewExternalIdentifier(), ei);
				i++;
			}
		}
		if (eif.getHome() != null) {
			eifType.setHome(eif.getHome());
		}
		if (eif.getId() != null) {
			eifType.setId(eif.getId());
		}
		if (eif.getName() != null) {
			eifType.setName(ParseInternationStringToInternationStringType(
					eifType.addNewName(), eif.getName()));
		}
		if (eif.getObjectType() != null) {
			eifType.setObjectType(parseObject(eif.getObjectType()));
		}
		if (eif.getRegistryObject() != null) {
			eifType.setRegistryObject(parseObject(eif.getRegistryObject()));
		}
		if (eif.getSlots() != null) {
			int i = 0;
			for (Slot slot : eif.getSlots()) {
				eifType.getSlotArray()[i] = ParseSlotToSlotType1(eifType
						.addNewSlot(), slot);
				i++;
			}
		}
		if (eif.getStatus() != null) {
			eifType.setStatus(parseObject(eif.getStatus()));
		}
		if (eif.getValue() != null) {
			eifType.setValue(eif.getValue());
		}
		if (eif.getVersionInfo() != null) {
			eifType.setVersionInfo(ParseVersionInfoToVersionInfoType(eifType
					.addNewVersionInfo(), eif.getVersionInfo()));
		}
		return eifType;

	}

	/**
	 * ��Classification�е�������䵽ClassificationType��
	 * 
	 * @param cft
	 * @param cf
	 */
	public static ClassificationType ParseClassificationToClassificationType(
			ClassificationType cft, Classification cf) {
		ClassificationType cftType = cft;

		if (cf.getClassificationNode() != null) {
			cftType.setObjectType(parseObject(cf.getClassificationNode()));
		}
		if (cf.getClassifications() != null) {
			Set<ClassificationType> classificationTypes = new HashSet<ClassificationType>();
			for (Classification cftion : cf.getClassifications()) {
				classificationTypes
						.add(ParseClassificationToClassificationType(cftType,
								cftion));
			}
			cftType.setClassificationArray(classificationTypes
					.toArray(new ClassificationType[cf.getClassifications()
							.size()]));
		}
		if (cf.getClassificationNode() != null) {
			cftType.setClassificationNode(parseObject(cf
					.getClassificationNode()));
		}
		if (cf.getClassificationScheme() != null) {
			cftType.setClassificationScheme(parseObject(cf
					.getClassificationScheme()));
		}
		if (cf.getClassifiedObject() != null) {
			cftType.setClassifiedObject(parseObject(cf.getClassifiedObject()));
		}
		if (cf.getDescription() != null) {
			cftType
					.setDescription(ParseInternationStringToInternationStringType(
							cftType.addNewDescription(), cf.getDescription()));
		}
		if (cf.getExternalIdentifiers() != null) {
			Set<ExternalIdentifierType> eiftSet = new HashSet<ExternalIdentifierType>();
			for (ExternalIdentifier eif : cf.getExternalIdentifiers()) {
				eiftSet.add(ParseExternalIdentifierToExternalIdentifierType(
						cftType.addNewExternalIdentifier(), eif));
			}
			cftType.setExternalIdentifierArray(eiftSet
					.toArray(new ExternalIdentifierType[cf
							.getExternalIdentifiers().size()]));
		}
		if (cf.getHome() != null) {
			cftType.setHome(cf.getHome());
		}
		if (cf.getId() != null) {
			cftType.setId(cf.getId());
		}
		if (cf.getLid() != null) {
			cftType.setLid(cf.getLid());
		}
		if (cf.getName() != null) {
			cftType.setName(ParseInternationStringToInternationStringType(
					cftType.addNewName(), cf.getName()));
		}
		if (cf.getNodeRepresentation() != null) {
			cftType.setNodeRepresentation(cf.getNodeRepresentation());
		}
		if (cf.getObjectType() != null) {
			cftType.setObjectType(parseObject(cf.getObjectType()));
		}
		if (cf.getSlots() != null) {
			Set<SlotType1> sts = new HashSet<SlotType1>();
			for (Slot slot : cf.getSlots()) {
				sts.add(ParseSlotToSlotType1(cftType.addNewSlot(), slot));
			}
			cftType.setSlotArray(sts
					.toArray(new SlotType1[cf.getSlots().size()]));
		}
		if (cf.getStatus() != null) {
			cftType.setStatus(parseObject(cf.getStatus()));
		}
		if (cf.getVersionInfo() != null) {
			cftType.setVersionInfo(ParseVersionInfoToVersionInfoType(cftType
					.addNewVersionInfo(), cf.getVersionInfo()));
		}

		return cftType;
	}

	/**
	 * ����extrisicObject�����idֵ��ȡ���еĿ��ܵ�RegistryPackage��Id����
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getRegistryPackageUtil(List<String> list) {
		List<String> registrypackageids = new ArrayList<String>();
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();
			Criteria criteria = session.createCriteria(RegistryPackage.class);
			List<RegistryPackage> lists = (List<RegistryPackage>) criteria
					.list();
			session.beginTransaction().commit();
			for (RegistryPackage rp : lists) {
				Iterator<Identifiable> itfs = rp.getIdentifiables().iterator();
				while (itfs.hasNext()) {
					Identifiable identifiable = itfs.next();
					for (String idt : list) {
						if (identifiable.getId().equals(idt)) {
							registrypackageids.add(rp.getId());
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			GetSessionUtil.CloseSessionInstance(session);
		}
		return registrypackageids;
	}

	/**
	 * �����ݿ��л�ȡ�����¼��exterinalObject��Id���� ������ [1]�����Ƚ�DC������ת��ΪebRIM��Ӧ�Ŀɲ�ѯ������
	 * [2]���������Ӧ�Ĳ�ѯ���Ի�ȡ��Ϊǰ��Ŀɷ��ص����� [3]������full��summary��brief�����������Ӧ�ķ�������
	 */
	@SuppressWarnings( { "unchecked" })
	public static List<String> GetConformInfomation() {
		extrinsicObjectIDs = new ArrayList<String>();
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		String bjectName = properties.get("PropertyIsLike")[0];
		if (bjectName != null) {
			if (bjectName.equals("dc:title")) {
				Criteria criteria = session
						.createCriteria(ExtrinsicObject.class);
				Criteria lnameCriteria = criteria.createCriteria("lname");
				lnameCriteria.add(Expression.like("value", properties
						.get("PropertyIsLike")[1], MatchMode.ANYWHERE));
				// System.out.println(properties.get("PropertyIsLike")[0]);
				List<ExtrinsicObject> list = (List<ExtrinsicObject>) criteria
						.list();
				for (ExtrinsicObject eo : list) {
					// �������ExtrinsicObject�洢����
					extrinsicObjectIDs.add(eo.getId());
				}
			}
		}
		// �������ж��Ƿ���ڽ������Ϊ���û������ļ�¼��������Ĳ���
		if (extrinsicObjectIDs.size() == 0) {
			// �ύ��ɾ��
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return null;
		}
		bjectName = properties.get("PropertyIsEqualTo")[0];
		if (bjectName != null) {
			if (bjectName.equals("dc:type")) {
				List<String> otList = new ArrayList<String>();
				// �˴����ڼ���ǰ��������������Ľ������
				for (String id : extrinsicObjectIDs) {
					Criteria criteria = session
							.createCriteria(ExtrinsicObject.class);
					criteria.add(Expression.eq("id", id));
					Criteria oCriteria = criteria.createCriteria("objectType");
					oCriteria.add(Expression.like("home", properties
							.get("PropertyIsEqualTo")[1], MatchMode.END));
					List<ExtrinsicObject> lists = (List<ExtrinsicObject>) criteria
							.list();
					for (ExtrinsicObject eo : lists) {
						otList.add(eo.getId());
					}
				}
				extrinsicObjectIDs.clear();
				extrinsicObjectIDs = otList;
			}
		}
		if (extrinsicObjectIDs.size() == 0) {
			// �ύ��ɾ��
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return null;
		}
		bjectName = properties.get("PropertyIsGreaterThanOrEqualTo")[0];
		if (bjectName != null) {
			if (bjectName.equals("dct:start")) {
				List<String> otlist = new ArrayList<String>();
				java.util.Date date = new java.util.Date();
				java.util.Date slotDate = new java.util.Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					date = sdf.parse(properties
							.get("PropertyIsGreaterThanOrEqualTo")[1]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				for (String id : extrinsicObjectIDs) {
					Criteria criteria = session
							.createCriteria(ExtrinsicObject.class);
					criteria.add(Expression.eq("id", id));
					Criteria slotCriteria = criteria.createCriteria("slots");
					slotCriteria.add(Expression.like("name", "ValidTimeEnd",
							MatchMode.END));
					List<ExtrinsicObject> lists = (List<ExtrinsicObject>) criteria
							.list();
					for (ExtrinsicObject eo : lists) {
						for (Slot slot : eo.getSlots()) {
							if (slot.getSlotType() != null) {
								if (slot.getSlotType().endsWith("DateTime")) {
									// System.out.println(slot.getValues());
									if (slot.getName().endsWith("ValidTimeEnd")) {
										try {
											slotDate = sdf
													.parse(slot
															.getValues()
															.substring(
																	0,
																	slot
																			.getValues()
																			.length() - 1));
											if (slotDate.after(date)) {
												otlist.add(eo.getId());
											}
										} catch (ParseException e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
					}
				}
				extrinsicObjectIDs.clear();
				extrinsicObjectIDs = otlist;
			}
		}
		if (extrinsicObjectIDs.size() == 0) {
			// �ύ��ɾ��
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return null;
		}
		bjectName = properties.get("Intersects")[0];
		if (bjectName.equals("ows:BoundingBox")) {
			List<String> otlist = new ArrayList<String>();
			for (String id : extrinsicObjectIDs) {
				Criteria criteria = session
						.createCriteria(ExtrinsicObject.class);
				criteria.add(Expression.eq("id", id));
				Criteria slotCriteria = criteria.createCriteria("slots");
				slotCriteria.add(Expression.eq("slotType",
						"urn:ogc:def:dataType:ISO-19107:2003:GM_Envelope"));
				List<ExtrinsicObject> lists = (List<ExtrinsicObject>) criteria
						.list();
				Double[] asserts1 = new Double[4];
				asserts1[0] = Double
						.parseDouble(properties.get("Intersects")[1]);
				asserts1[1] = Double
						.parseDouble(properties.get("Intersects")[2]);
				asserts1[2] = Double
						.parseDouble(properties.get("Intersects")[3]);
				asserts1[3] = Double
						.parseDouble(properties.get("Intersects")[4]);
				Double[] asserts2 = new Double[4];
				for (ExtrinsicObject eo : lists) {

					for (Slot slot : eo.getSlots()) {
						if (slot.getSlotType() != null) {
							if (slot.getSlotType().endsWith("GM_Envelope")) {
								String slotEnvelope = slot.getValues();
								asserts2 = ParseSlotGM_Envelope(slotEnvelope);
								if (asserts2.length > 0 && asserts1.length > 0) {
									@SuppressWarnings("unused")
									Boolean booleans = GetIntersectsResults(
											asserts2, asserts1);
									if (true) {
										otlist.add(eo.getId());
									}
								}
							}
						}
					}
				}
			}
			extrinsicObjectIDs.clear();
			extrinsicObjectIDs = otlist;
		}
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		return extrinsicObjectIDs;
	}

	/**
	 * ����Slot��SlotType����ΪGM_Envelope��
	 * 
	 * @return ���ص���һ��Envelope�ĶԽ����ϵĵ�������ֵ
	 */
	public static Double[] ParseSlotGM_Envelope(String slotValue) {
		Double[] asserts2 = new Double[4];
		String slotEnvelope = slotValue;
		slotEnvelope = slotEnvelope.substring(1, slotEnvelope.length() - 2);
		String[] strings = slotEnvelope.split(",");
		String firstPoint = strings[1];
		String secondPoint = strings[2];
		asserts2[0] = Double.parseDouble(firstPoint.split(" ")[0]);
		asserts2[1] = Double.parseDouble(firstPoint.split(" ")[1]);
		asserts2[2] = Double.parseDouble(secondPoint.split(" ")[0]);
		asserts2[3] = Double.parseDouble(secondPoint.split(" ")[1]);
		return asserts2;
	}

	/**
	 * �����ж���������֮���Ƿ���ڽ���
	 * 
	 * @param assest1
	 *            ����1
	 * @param ass2
	 *            ����2
	 * @return
	 */
	public static boolean GetIntersectsResults(Double[] ass1, Double[] ass2) {
		if (ass1[0] == ass2[0] && ass1[1] == ass2[1] && ass1[2] == ass2[2]
				&& ass1[3] == ass2[3]) {
			return true;
		} else if (ass1[0] < ass2[2] && ass2[0] < ass1[2]) {
			if (ass1[1] < ass2[3] && ass2[1] < ass2[3]) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * ��ȡgetRecords.xml�ĵ������ݣ���������Ĺ������ڻ�ȡgetRecords.xml�������õĸ���������Ϣ
	 * ���ó��Ľ�����Щ������Ϣ�ļ���
	 * 
	 * @param filepath
	 *            getRecords.xml�ĵ���·��
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void ReadRequestGetRecordsDocument(String filepath) {
		File file = new File(filepath);
		try {
			GetRecordsDocument getRecordsDocument = GetRecordsDocument.Factory
					.parse(file);
			GetRecordsType getRecordsType = getRecordsDocument.getGetRecords();
			outputSchema = getRecordsType.getOutputSchema();
			maxRecordNum = getRecordsType.getMaxRecords();
			startRecord = getRecordsType.getStartPosition();
			QueryType queryType = (QueryType) getRecordsType.getAbstractQuery();
			typenames = (List<String>) queryType.getTypeNames();
			resultType = queryType.getElementSetName().getStringValue();
			elementSetNameTypeNames = queryType.getElementSetName()
					.getTypeNames();
			queryConstraint = queryType.getConstraint();
			FilterType filterType = queryConstraint.getFilter();
			AndDocument andDocument = AndDocument.Factory.parse(filterType
					.xmlText());
			BinaryLogicOpType and = andDocument.getAnd();
			for (ComparisonOpsType cot : and.getComparisonOpsArray()) {
				if (cot instanceof PropertyIsLikeType) {
					PropertyIsLikeType pcot = (PropertyIsLikeType) cot;
					String PropertyNameValue = pcot.getPropertyName()
							.getDomNode().getChildNodes().item(0)
							.getNodeValue();
					// ����Ӧ�ÿ���һ��PropertyIsLike�е�valueΪ�գ���δ����������������һ�㲻����֣�
					if (PropertyNameValue != null) {
						String LitervalValue = pcot.getLiteral().getDomNode()
								.getChildNodes().item(0).getNodeValue();
						String[] valueStrings = { PropertyNameValue,
								LitervalValue };
						properties.put("PropertyIsLike", valueStrings);
					}
				}
				if (cot instanceof PropertyIsNullType) {
					@SuppressWarnings("unused")
					PropertyIsNullType isNullType = (PropertyIsNullType) cot;
				}
				if (cot instanceof BinaryComparisonOpType) {
					BinaryComparisonOpType bco = (BinaryComparisonOpType) cot;
					if (bco.getDomNode().getLocalName().equals(
							"PropertyIsEqualTo")) {
						// String pit = bco.getDomNode().getLocalName();
						// System.out.println(pit);
						String pnresult = "";
						String llresult = "";
						for (int i = 0; i < bco.getDomNode().getChildNodes()
								.getLength(); i++) {
							if (bco.getDomNode().getChildNodes().item(i)
									.getNodeType() == Node.ELEMENT_NODE) {
								if (bco.getDomNode().getChildNodes().item(i)
										.getLocalName().equals("PropertyName")) {
									pnresult = bco.getDomNode().getChildNodes()
											.item(i).getChildNodes().item(0)
											.getNodeValue();
								}
								if (bco.getDomNode().getChildNodes().item(i)
										.getLocalName().equals("Literal")) {
									llresult = bco.getDomNode().getChildNodes()
											.item(i).getChildNodes().item(0)
											.getNodeValue();
								}
							}
						}
						String[] valueStrings = { pnresult, llresult };
						properties.put("PropertyIsEqualTo", valueStrings);
					}
					if (bco.getDomNode().getLocalName().equals(
							"PropertyIsGreaterThanOrEqualTo")) {
						@SuppressWarnings("unused")
						String pit = bco.getDomNode().getLocalName();
						String pnresult = "";
						String llresult = "";
						for (int i = 0; i < bco.getDomNode().getChildNodes()
								.getLength(); i++) {
							if (bco.getDomNode().getChildNodes().item(i)
									.getNodeType() == Node.ELEMENT_NODE) {
								if (bco.getDomNode().getChildNodes().item(i)
										.getLocalName().equals("PropertyName")) {
									pnresult = bco.getDomNode().getChildNodes()
											.item(i).getChildNodes().item(0)
											.getNodeValue();
								}
								if (bco.getDomNode().getChildNodes().item(i)
										.getLocalName().equals("Literal")) {
									llresult = bco.getDomNode().getChildNodes()
											.item(i).getChildNodes().item(0)
											.getNodeValue();
								}
							}
						}
						String[] valuesStrings = { pnresult, llresult };
						properties.put("PropertyIsGreaterThanOrEqualTo",
								valuesStrings);
					}
				}
			}
			// // �����������߼�����������Ĵ���
			// for (@SuppressWarnings("unused")
			// LogicOpsType logicOps : and.getLogicOpsArray()) {
			// }
			// ��������Կռ����������Ĵ���
			for (SpatialOpsType spatialOps : and.getSpatialOpsArray()) {
				if (spatialOps instanceof BinarySpatialOpType) {
					BinarySpatialOpType bspatialOps = (BinarySpatialOpType) spatialOps;
					if (bspatialOps.getDomNode().getLocalName().equals(
							"Intersects")) {
						String[] vaStrings = {
								bspatialOps.getPropertyNameArray(0)
										.getDomNode().getChildNodes().item(0)
										.getNodeValue(),
								bspatialOps.getEnvelope().getLowerCorner()
										.getListValue().get(0).toString(),
								bspatialOps.getEnvelope().getLowerCorner()
										.getListValue().get(1).toString(),
								bspatialOps.getEnvelope().getUpperCorner()
										.getListValue().get(0).toString(),
								bspatialOps.getEnvelope().getUpperCorner()
										.getListValue().get(1).toString() };
						properties.put("Intersects", vaStrings);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
