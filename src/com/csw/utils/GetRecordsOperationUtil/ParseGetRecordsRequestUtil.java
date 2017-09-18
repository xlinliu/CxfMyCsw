package com.csw.utils.GetRecordsOperationUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.aspectj.weaver.patterns.IfPointcut.IfFalsePointcut;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.w3.x1999.xlink.FromAttribute;

import com.csw.model.ebXMLModel.ExtrinsicObject;
import com.csw.model.ebXMLModel.Slot;
import com.csw.model.swe.ConditionalValueType.Data;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.SensorInfoUtil.QueryProcessByConditionUtil;
import com.csw.utils.StringUtil;
import com.ebrim.model.csw.GetRecordsDocument;
import com.ebrim.model.csw.GetRecordsType;
import com.ebrim.model.csw.QueryType;
import com.ebrim.model.ogc.AndDocument;
import com.ebrim.model.ogc.BBOXDocument;
import com.ebrim.model.ogc.BinaryComparisonOpType;
import com.ebrim.model.ogc.BinaryLogicOpType;
import com.ebrim.model.ogc.ComparisonOpsType;
import com.ebrim.model.ogc.FilterType;
import com.ebrim.model.ogc.IntersectsDocument;
import com.ebrim.model.ogc.LiteralType;
import com.ebrim.model.ogc.LogicOpsType;
import com.ebrim.model.ogc.OrDocument;
import com.ebrim.model.ogc.PropertyIsBetweenDocument;
import com.ebrim.model.ogc.PropertyIsBetweenType;
import com.ebrim.model.ogc.PropertyIsEqualToDocument;
import com.ebrim.model.ogc.PropertyIsGreaterThanOrEqualToDocument;
import com.ebrim.model.ogc.PropertyIsLessThanOrEqualToDocument;
import com.ebrim.model.ogc.PropertyIsLikeDocument;
import com.ebrim.model.ogc.PropertyIsLikeType;
import com.ebrim.model.ogc.PropertyNameType;
import com.ebrim.model.ogc.SpatialOpsType;
import com.ebrim.model.ogc.impl.PropertyIsLessThanOrEqualToDocumentImpl;
import com.sun.corba.se.spi.orb.StringPair;
import com.sun.jndi.url.dns.dnsURLContext;
import com.sun.org.apache.commons.collections.StaticBucketMap;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

@SuppressWarnings( { "unused", "deprecation" })
public class ParseGetRecordsRequestUtil {
	// ���ڱ�ʾA-i-j�е�i
	private static int i = 0;
	// ������ʾprefix�е�ֵ
	private static String prefixinit = "";
	// �洢maps��ֵ����һ��string��ʾ�������еĲ�ι�ϵ���ڶ���string��ʾ���Ǵ洢�Ĺ�ϵֵ
	private static Map<String, String> maps = new HashMap<String, String>();
	// �����resultMaps���������ڽ�ĩβΪ:And-i-j����Or-i-j���߼��жϵĿյ������
	// ��ExtrinsicObject��id���ϴ洢���������ڲ�����һ���And����Or�Ĳ�������Ŀ��Ҳ���������ô˽��еݹ�����
	private static Map<String, Set<String>> resultMaps = new HashMap<String, Set<String>>();
	Configuration conf = new Configuration().configure();

	public static Map<String, Set<String>> getResultMaps() {
		return resultMaps;
	}

	public static void setResultMaps(Map<String, Set<String>> resultMaps) {
		ParseGetRecordsRequestUtil.resultMaps = resultMaps;
	}

	/**
	 * �����������ϣ���ȡ����������ExtrinsicObject�ļ���
	 * 
	 * @param mapss
	 *            ��������(�������ȫ�ļ��ϣ�
	 * @return ���ط���������extrinsicObject��ID����
	 */
	public void GetExtrinsicObjects(Map<String, String> mapss) {
		Set<String> extrinsicobjects = new HashSet<String>();
		// ��ȡ�����е�������ε����ֵ
		int maxlength = 0;
		for (String str : mapss.keySet()) {
			String[] resultStrs = str.split(":");
			if (resultStrs.length >= maxlength) {
				maxlength = resultStrs.length;
			}
		}
		GetExtrinsicObjects(maxlength, mapss, null);
	}

	/**
	 * �ú��������ڴ���ͬ��ε��������ϣ�������Ҫ����ȡ�Ľ���洢������Ľ���в�ɾ����ԭʼmap�е���ص��ж�[�ݹ麯��]
	 * 
	 * @param maplength
	 *            �����ָ�Ѿ������ĸ��ݹ�Ĳ�����ǴӺ�����ǰ�洫��,����Ҳ��Ҫ��һ��Ĺ�ϵ���ж������Ƿ�����ͬһ��ε�
	 * @param tempmap
	 *            ����Ǹ�������һ����ϵ���������
	 * @param exobjects
	 *            ����Ǹ�����һ���ѯ���extrinsicObject�ļ�������ѯ����extrinsicObject
	 * @return ���ط���������extrinsicObject��idֵ��
	 */
	public void GetExtrinsicObjects(int maxlength, Map<String, String> tempmap,
			Set<String> exobjects) {
		System.out.println("��ʱ�ļ����е��������Ϊ:" + maxlength);
		// �洢��ʱ��Ҫ�ĵĲ�ѯ���������������ϵ����еĲ�ѯ����
		Map<String, String> maps = new HashMap<String, String>();
		System.out.println("=============================================");
		for (String str : tempmap.keySet()) {
			if (str.split(":").length <= maxlength) {
				maps.put(str, tempmap.get(str));
				System.out.println(str + " : " + tempmap.get(str));
			}
		}
		System.out.println("��Ҫ�жϵ����������е������� �� " + maps.size() + "��");
		System.out.println("��ʱ��������д洢��" + resultMaps.size() + "����ʱ��¼");
		System.out.println("=============================================");
		// �����ʹ�ù���tempmap�н����˴���Ķ���ɾ��
		Set<String> eoset = new HashSet<String>();
		int i = 0;
		// ���ڴ洢���еĲ��ֵ���Ϣ�������ǽ���Щ��Ϣͨ��and ����or���кϲ�����,��Щ����ÿһ�����Ǵ洢��resultMap��
		Map<String, Set<String>> tempMaps = new HashMap<String, Set<String>>();
		for (String str : maps.keySet()) {
			// maxlength��ֵ�������Ƿ������ϣ������1�������ϣ�����1��û�б������
			if (maxlength == 1) {
				// ������ɣ����ʱ��strӦ����and-0-0����or-0-0
				if (resultMaps.containsKey(str)) {
					tempMaps.put(str, resultMaps.get(str));
					System.out.println("���ջ�ȡ�ķ��������Ĵ������ĸ���Ϊ"
							+ resultMaps.get(str).size() + "��");
					System.out
							.println("===============��������������ExtrinsicObject����======================");
					for (String str1 : resultMaps.get(str)) {
						System.out.println(str1);
					}
					System.out
							.println("=====================================================================");
				}

			} else if (str.split(":").length == maxlength) {
				// �趨��maplength��Ϊ1��ʱ����������
				// ��ȡ��ѯ���߼����� ��ֵΪinnervalue,ֵΪvalValue
				// ֮�����ж�or-����and-����Ϊ��Щ�жϵ��ֶ����溬��or����and
				if ((str.substring(str.lastIndexOf(":")).toLowerCase()
						.contains("and-") || str
						.substring(str.lastIndexOf(":")).toLowerCase()
						.contains("or-"))) {
					// ������������Ƿ������������Ĳ�ѯ��Ľ��
					if (resultMaps.containsKey(str)) {
						// ����һ����ȡ�Ĳ�ѯ���ͳһ�������ʱ������ϣ���Ҫ��Ϊ�˺������else�в��������ݽ��кϲ�����
						tempMaps.put(str, resultMaps.get(str));
						// �Ƴ���������в�ѯ�Ľ��
						resultMaps.remove(str);
					}
				} else {

					// ������ǲ�ѯ���ϸ�������sensor����
					String[] values = str.split(":");
					String lastvalue = values[values.length - 1];
					String[] innervalue = lastvalue.split("-");
					// ��Ҫ��ѯ���߼��ж�������PropertyIsGreaterThanOrEqualTo��PropertyIsLessThanOrEqualTo��
					String inervalue = innervalue[0];
					// ��ȡ�� dct:end|2012-01-01 23:59:59��ֵ
					String valValue = maps.get(str);
					// tempMaps�洢���صĸ���������ExtrinsicObject��id����
					tempMaps.putAll(ResetMapStringSetStringUtil(str.substring(
							0, str.lastIndexOf(":"))
							+ ":" + i, inervalue, valValue));
					i++;
				}
			}
		}
		/**
		 * �鿴ÿ���н��л�ȡ����ʱ������������ExtrinsicObject����
		 */
		for (String str : tempMaps.keySet()) {
			System.out.println(str);
			System.out.println(tempMaps.get(str).size());
		}
		// ����Ӧ�����ж��ٸ���ѯ�ֶξ�Ӧ���ж��ٸ���ѯ�Ĵ���
		System.out.println("tempMaps:" + tempMaps.size());
		// panduanStr�����ڴ洢���в�ͬ��ǰ׺����And-0-0:Or-1-0:Or-2-0:...�����������һ������
		String panduanStr = "";
		if (maxlength == 1) {
			// ������ֻ��һ����ʱ��¼and-0-0����Or-0-0
			panduanStr = tempMaps.keySet().iterator().next();
		} else {
			for (String str : tempMaps.keySet()) {
				str = str.substring(0, str.lastIndexOf(":"));
				if (panduanStr.contains(str)) {
				} else {
					panduanStr += str + "|";
				}
			}
		}
		System.out.println("panduanStr:" + panduanStr);
		// �����Ѿ�������һ��
		int k = 1;
		if (maxlength == 1) {
		} else {
			panduanStr = panduanStr.substring(0, panduanStr.length() - 1);
			String tempStr = panduanStr;
			String[] keyStrings = tempStr.split("\\|");
			k = keyStrings.length;
		}
		// strs���ڴ洢panduanStr�е����е��ַ���
		String[] strs = new String[k];
		if (k == 1) {
			strs[0] = panduanStr;
		} else {
			for (int m = 0; m < k; m++) {
				strs[m] = panduanStr.split("\\|")[m];
			}
		}
		if (maxlength != 1) {
			// ����strs�д洢���ַ���
			for (String strname : strs) {
				String prefixStrName = strname.substring(strname
						.lastIndexOf(":") + 1);
				System.out.println(prefixStrName);
				// �����ݺϲ�
				if (prefixStrName.toLowerCase().contains("and-")) {
					List<String> strsets = new ArrayList<String>();
					int kk = 0;
					for (String tempStr : tempMaps.keySet()) {
						if (tempStr.contains(strname)) {
							Set<String> eoids = tempMaps.get(tempStr);
							strsets.addAll(eoids);
							kk++;
						}
					}
					eoset = FindMostExternisicObjectIds(strsets, kk);
					resultMaps.put(strname, eoset);
					System.out.println("And�ϲ����resultMaps��" + strname + "�ĸ���Ϊ:"
							+ resultMaps.get(strname).size() + "��");
					System.out
							.println("�ֱ�Ϊ:===================================");
					for (String str : resultMaps.get(strname)) {
						System.out.println(str);
					}
					System.out.println("===================================");
				} else if (prefixStrName.toLowerCase().contains("or-")) {
					// �������SET����������ظ���Ԫ�ص�����
					for (String tempStr : tempMaps.keySet()) {
						if (tempStr.contains(strname)) {
							eoset.addAll(tempMaps.get(tempStr));
						}
					}
					resultMaps.put(strname, eoset);
					System.out.println("Or�ϲ����resultMaps��" + strname + "�ĸ���Ϊ:"
							+ resultMaps.get(strname).size() + "��");
					System.out
							.println("�ֱ�Ϊ:===================================");
					for (String str : resultMaps.get(strname)) {
						System.out.println(str);
					}
					System.out.println("===================================");
				}
			}
		}
		// ����ȷ���ڵ�һ�㵽���ʱ��ֹͣ����ѭ��
		if (--maxlength > 0) {
			GetExtrinsicObjects(maxlength, maps, exobjects);
		}
	}

	/**
	 * ��ȡlist���ִ���Ϊcount��Ԫ�ؼ���
	 * 
	 * @param listsList
	 *            Ҫ��ѯ��list��Ԫ�ؼ���
	 * @param count
	 *            ��ѯ��Ԫ�س��ֵĴ���
	 * @return ���س�����count�ε�Ԫ�صļ���
	 */
	public Set<String> FindMostExternisicObjectIds(List<String> listsList,
			int count) {
		Set<String> resultSet = new HashSet<String>();
		String testStr = listsList.toString();
		// System.out.println(testStr);
		for (String str : listsList) {
			String[] ss = testStr.split(str);
			int spitcount = ss.length;
			if (spitcount == (count + 1)) {
				resultSet.add(str);
			}
		}
		return resultSet;
	}

	/**
	 * �����ƶ���������ѯ��ص�ExternalObject���󣬲�����ѯ�Ķ������Ӧ�Ĵ洢����
	 * 
	 * @param loujiguanxi
	 *            Ӧ��Ϊ��һ��εĹ�ϵand-i-j ����or-i-j.
	 * @param chaxunziduan
	 *            ��ѯ���ֶ���dct��modified��
	 * @param chaxunValue
	 *            ��ѯ��ֵ�������ͬ�Ĳ�ѯ�ֶ���ͬ���������ݵĽ����֮ǰ��ȡ�������ϵ�ʱ���Ѿ�ȷ��
	 * @return ���ص��Ǹ�������ȷ���������ExtrinsicObject����
	 */
	public Map<String, Set<String>> ResetMapStringSetStringUtil(
			String loujiguanxi, String chaxunziduan, String chaxunValue) {
		System.out.println("loujiguanxi:" + loujiguanxi + " chaxunziduan: "
				+ chaxunziduan + " chaxunValue:" + chaxunValue);
		Map<String, Set<String>> partmapMap = new HashMap<String, Set<String>>();
		// ����ȡ�������µ����з���������ExtrinsicObject����
		Set<String> idvalueSet = QueryProcessByConditionUtil
				.GetExtrinsicObjects(chaxunziduan, chaxunValue);
		partmapMap.put(loujiguanxi, idvalueSet);
		return partmapMap;

	}

	/**
	 * ����getRecordsXmlContent�����ݽ�����and,or ,not֮��Ĳ�ι�ϵ ��������string Ӧ���Ǿ���
	 * A-1-0:A-2-0:A-3-0:...Ҳ����ΪA-1-0:O-2-0:O-3-0... ����ΪA-1-0:O-2-1:
	 * ��A-1-0Ϊ����A����and��1����Ϊ��һ�㣬0����Ϊ��һ���еĵ�һ��And�Ӳ�
	 * 
	 * 
	 * 
	 * @param getRecordsXmlContent
	 *            ��Ҫ������getRecords������
	 * @return map<string,string>
	 *         map�еĵ�һ��stringΪ���еĸ������֮��Ĺ�ϵ���ڶ���string��ָ���е�XMLƬ�������Դ���
	 *         �����������е�and��or��not��XMLƬ����Ҫȥ��
	 */
	public Map<String, String> GetAndOrNotCengciGuanXiMap(
			String getRecordsXmlContent) {
		GetRecordsDocument grdocument = GetGetRecordsDocument(getRecordsXmlContent);
		GetRecordsType grtype = grdocument.getGetRecords();
		QueryType aqtype = (QueryType) grtype.getAbstractQuery();
		FilterType ftype = aqtype.getConstraint().getFilter();

		XmlCursor xmlCursor = ftype.newCursor();
		xmlCursor.toChild(0);
		try {
			OrDocument orDocument = OrDocument.Factory.parse(xmlCursor
					.xmlText());
			ParseOrDocument(orDocument, prefixinit, i, 0);
		} catch (Exception e) {
			try {
				AndDocument andDocument = AndDocument.Factory.parse(xmlCursor
						.xmlText());
				ParseAndDocument(andDocument, prefixinit, i, 0);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return maps;
	}

	/**
	 * ��ȡOrDocument�еĸ����������
	 * 
	 * @param orDocument
	 *            ��Ҫ����OrDocument
	 */
	public void ParseOrDocument(OrDocument orDocument, String prefix, int i,
			int j) throws Exception {
		prefix = prefix + ":Or-" + i + "-" + j;
		if (prefix.substring(0, 1).equals(":")) {
			prefix = prefix.substring(1, prefix.length());
		}
		maps.put(prefix, " ");
		BinaryLogicOpType blottype = orDocument.getOr();
		this.ParseBinaryLogicOpType(blottype, prefix, ++i, ++j);
	}

	/**
	 * ��ȡAndDocument�еĸ����������
	 * 
	 * @param andDocument
	 *            ��Ҫ����AndDocument
	 */
	public void ParseAndDocument(AndDocument andDocument, String prefix, int i,
			int j) throws Exception {
		int jj = j;
		prefix = prefix + ":And-" + i + "-" + jj;
		if (prefix.substring(0, 1).equals(":")) {
			prefix = prefix.substring(1, prefix.length());
		}
		maps.put(prefix, " ");
		prefix = prefix.trim();
		BinaryLogicOpType blottype = andDocument.getAnd();
		this.ParseBinaryLogicOpType(blottype, prefix, ++i, jj);
	}

	/**
	 * ����BinaryLogicOpType�еĸ����������
	 * 
	 * @param blotType
	 *            ��Ҫ������BinaryLogicOpType
	 */
	public void ParseBinaryLogicOpType(BinaryLogicOpType blottype,
			String prefix, int i, int j) {
		int tempi = i;
		String tempPrefix = prefix;
		int propertyislike = 0;
		int propertyisequalto = 0;
		int propertyisgreaterthanorequalto = 0;
		int propertyIntersects = 0;
		int propertybbox = 0;
		int andpro = 0;
		int orpro = 0;

		if (blottype.getSpatialOpsArray().length > 0) {
			for (SpatialOpsType soptype : blottype.getSpatialOpsArray()) {
				XmlCursor xmlCursor = soptype.newCursor();
				String localpartname = xmlCursor.getName().getLocalPart();
				if (localpartname.equals("Intersects")) {
					String content = soptype.xmlText().replace("xml-fragment",
							"ogc:Intersects");
					prefix = tempPrefix + ":Intersects-" + tempi + "-"
							+ propertyIntersects;
					propertyIntersects++;
					String result = "";
					try {
						IntersectsDocument itDocument = IntersectsDocument.Factory
								.parse(content);
						String propertyName = "";
						if (itDocument.getIntersects().getPropertyNameArray().length > 0) {
							XmlCursor xmlCursor2 = itDocument.getIntersects()
									.getPropertyNameArray(0).newCursor();
							propertyName = xmlCursor2.getTextValue().trim();
						}
						result = propertyName
								+ "|"
								+ itDocument.getIntersects().getEnvelope()
										.getLowerCorner().getStringValue()
										.trim()
								+ " "
								+ itDocument.getIntersects().getEnvelope()
										.getUpperCorner().getStringValue()
										.trim();
					} catch (Exception e) {
						e.printStackTrace();
					}
					// System.out.println(prefix);
					// System.out.println(result);
					maps.put(prefix.trim(), result.trim());
				}
				if (localpartname.equals("BBOX")) {
					String content = soptype.xmlText().replace("xml-fragment",
							"ogc:BBOX");
					prefix = tempPrefix + ":BBOX-" + tempi + "-" + propertybbox;
					propertybbox++;
					String result = "";
					String propertyName = "";
					try {
						BBOXDocument bboxDocument = BBOXDocument.Factory
								.parse(content);

						XmlCursor xmlCursor2 = bboxDocument.getBBOX()
								.getPropertyName().newCursor();
						propertyName = xmlCursor2.getTextValue().trim();
						result = propertyName
								+ "|"
								+ bboxDocument.getBBOX().getEnvelope()
										.getLowerCorner().getListValue().get(0)
								+ " "
								+ bboxDocument.getBBOX().getEnvelope()
										.getLowerCorner().getListValue().get(1)

								+ " "
								+ bboxDocument.getBBOX().getEnvelope()
										.getUpperCorner().getListValue().get(0)
								+ " "
								+ bboxDocument.getBBOX().getEnvelope()
										.getUpperCorner().getListValue().get(1);
					} catch (Exception e) {
						e.printStackTrace();
					}
					maps.put(propertyName.trim(), result.trim());

				}
			}
		}

		if (blottype.getComparisonOpsArray().length > 0) {
			for (ComparisonOpsType cot : blottype.getComparisonOpsArray()) {
				XmlCursor xmlCursor = cot.newCursor();
				String localpartname = xmlCursor.getName().getLocalPart();

				if (localpartname.equals("PropertyIsLike")) {
					String content = cot.xmlText().replace("xml-fragment",
							"ogc:PropertyIsLike");
					prefix = tempPrefix + ":PropertyIsLike-" + tempi + "-"
							+ propertyislike;
					propertyislike++;
					String temppropertyname = "";
					String result = "";
					try {
						PropertyIsLikeDocument pilDocument = PropertyIsLikeDocument.Factory
								.parse(content);
						XmlCursor xmlCursor2 = pilDocument.getPropertyIsLike()
								.getPropertyName().newCursor();
						temppropertyname = xmlCursor2.getTextValue().trim();
						xmlCursor2 = pilDocument.getPropertyIsLike()
								.getLiteral().newCursor();
						String templit = xmlCursor2.getTextValue();

						result = temppropertyname + "|" + templit;

					} catch (XmlException e) {
						e.printStackTrace();
					}
					maps.put(prefix.trim(), result.trim());
				}
				if (localpartname.equals("PropertyIsEqualTo")) {
					String content = cot.xmlText().replace("xml-fragment",
							"ogc:PropertyIsEqualTo");
					prefix = tempPrefix + ":PropertyIsEqualTo-" + tempi + "-"
							+ propertyisequalto;
					propertyisequalto++;
					String result = "";
					try {
						PropertyIsEqualToDocument rsetdocument = PropertyIsEqualToDocument.Factory
								.parse(content);
						XmlCursor xmlCursor2 = rsetdocument
								.getPropertyIsEqualTo().newCursor();
						xmlCursor2.toChild(0);
						String temproertyname = xmlCursor2.getTextValue()
								.trim();
						xmlCursor2 = rsetdocument.getPropertyIsEqualTo()
								.newCursor();
						xmlCursor2.toChild(1);
						String templiteral = xmlCursor2.getTextValue();
						result = temproertyname + "|" + templiteral;

					} catch (Exception e) {
						e.printStackTrace();
					}
					maps.put(prefix.trim(), result.trim());
				}
				if (localpartname.equals("PropertyIsGreaterThanOrEqualTo")) {
					String content = cot.xmlText().replace("xml-fragment",
							"ogc:PropertyIsGreaterThanOrEqualTo");
					prefix = tempPrefix + ":PropertyIsGreaterThanOrEqualTo-"
							+ tempi + "-" + propertyisgreaterthanorequalto;
					propertyisgreaterthanorequalto++;
					String result = "";
					try {
						PropertyIsGreaterThanOrEqualToDocument pigtoetdocument = PropertyIsGreaterThanOrEqualToDocument.Factory
								.parse(content);
						XmlCursor xmlCursor2 = pigtoetdocument
								.getPropertyIsGreaterThanOrEqualTo()
								.newCursor();
						xmlCursor2.toChild(0);
						String tempropertyname = xmlCursor2.getTextValue()
								.trim();
						xmlCursor2 = pigtoetdocument
								.getPropertyIsGreaterThanOrEqualTo()
								.newCursor();
						xmlCursor2.toChild(1);
						String templiteral = xmlCursor2.getTextValue();
						result = tempropertyname + "|" + templiteral;
						maps.put(prefix.trim(), result.trim());

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (localpartname.equals("PropertyIsLessThanOrEqualTo")) {
					String content = cot.xmlText().replace("xml-fragment",
							"ogc:PropertyIsLessThanOrEqualTo");
					prefix = tempPrefix + ":PropertyIsLessThanOrEqualTo-"
							+ tempi + "-" + propertyisgreaterthanorequalto;
					propertyisgreaterthanorequalto++;
					String result = "";
					try {
						PropertyIsLessThanOrEqualToDocument pigtoetdocument = PropertyIsLessThanOrEqualToDocument.Factory
								.parse(content);
						XmlCursor xmlCursor2 = pigtoetdocument
								.getPropertyIsLessThanOrEqualTo().newCursor();
						xmlCursor2.toChild(0);
						String tempropertyname = xmlCursor2.getTextValue()
								.trim();
						xmlCursor2 = pigtoetdocument
								.getPropertyIsLessThanOrEqualTo().newCursor();
						xmlCursor2.toChild(1);
						String templiteral = xmlCursor2.getTextValue();
						result = tempropertyname + "|" + templiteral;
						maps.put(prefix.trim(), result.trim());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}
		if (blottype.getLogicOpsArray().length > 0) {
			for (LogicOpsType lot : blottype.getLogicOpsArray()) {
				String localname = lot.getDomNode().getLocalName();
				if (localname.equals("And")) {
					try {
						String result = lot.xmlText().replace("xml-fragment",
								"ogc:And");

						AndDocument andDocument = AndDocument.Factory
								.parse(result);
						ParseAndDocument(andDocument, tempPrefix, i, andpro);
						andpro++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (localname.equals("Or")) {
					try {
						String result = lot.xmlText().replace("xml-fragment",
								"ogc:Or");
						OrDocument orDocument = OrDocument.Factory
								.parse(result);
						ParseOrDocument(orDocument, tempPrefix, i, orpro);
						orpro++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * ����PorpertyIsLikeDocument����
	 * 
	 * @param pildocument
	 *            ��Ҫ������PropertyIsLikeDocument����
	 * @param prefix
	 *            ��Ҫ��ǰ����ӵ�ǰ׺�������Ϊ�˺��������ʹ��
	 * @param i
	 *            A-i-j�е�i
	 * @param j
	 *            A-i-j�е�j
	 * @return ������Ҫ��propertyIsLike�ĸ�ʽ����String[0]:ָ����ĵݹ���
	 *         ,String[1]��propertyName,String[2]:literal
	 */
	public String[] ParsePropertyIsLikeDocument(
			PropertyIsLikeDocument pildocument, String prefix, int i, int j) {
		String[] results = {};
		results[0] = prefix + ":PropertyIsLike-" + i + "-" + j;
		PropertyIsLikeType piltype = pildocument.getPropertyIsLike();
		PropertyNameType pntype = piltype.addNewPropertyName();
		XmlCursor xmlCursor = pntype.newCursor();
		results[1] = xmlCursor.getTextValue();
		LiteralType ltype = pildocument.getPropertyIsLike().getLiteral();
		String literresult = ltype.getDomNode().getChildNodes().item(0)
				.getNodeValue();
		results[2] = literresult;
		return results;
	}

	/**
	 * ����PropertyIsBetweenDocument����
	 * 
	 * @param pibdocument
	 *            ��Ҫ������PropertyIsBetweenDocument�Ķ���
	 * @param prefix
	 *            ��Ҫ��ǰ�����ӵ�ǧ������Ϊ�˺��������ʹ��
	 * @param i
	 *            A-i-j�е�i
	 * @param j
	 *            A-i-j�е�j
	 * @return ������Ҫ��propertyIsLike�ĸ�ʽ����String[0]:ָ����ĵݹ���
	 *         ,String[1]��propertyName,String[2]:literal
	 */
	public String[] ParsePropertyIsBetweenDocument(
			PropertyIsBetweenDocument pibdocument, String prefix, int i, int j) {
		String[] results = {};
		results[0] = prefix + ":PropertyIsBetween-" + i + "-" + j;
		PropertyIsBetweenType bcotype = pibdocument.getPropertyIsBetween();
		XmlCursor xmlCursor = bcotype.newCursor();
		xmlCursor.toChild(new javax.xml.namespace.QName("PropertyName"));
		results[0] = xmlCursor.getTextValue();
		return results;
	}

	/**
	 * ����PropertyIsGreaterThanOrEqualToDocument����
	 * 
	 * @param pibdocument
	 *            ��Ҫ������PropertyIsGreaterThanOrEqualToDocument�Ķ���
	 * @param prefix
	 *            ��Ҫ��ǰ�����ӵ�ǰ׺������Ϊ�˺��������ʹ��
	 * @param i
	 *            A-i-j�е�i
	 * @param j
	 *            A-i-j�е�j
	 * @return ������Ҫ��propertyIsLike�ĸ�ʽ����String[0]:ָ����ĵݹ���
	 *         ,String[1]��propertyName,String[2]:literal
	 */
	public String[] ParsePropertyIsGreaterThanOrEqualToDocument(
			PropertyIsGreaterThanOrEqualToDocument pigtoetdocument,
			String prefix, int i, int j) {
		String[] results = {};
		results[0] = prefix + ":PropertyIsGreaterThanOrEqualTo-" + i + "-" + j;
		BinaryComparisonOpType bcotype = pigtoetdocument
				.getPropertyIsGreaterThanOrEqualTo();
		results[1] = ParseBinaryComparisonOpType(bcotype)[0];
		results[2] = ParseBinaryComparisonOpType(bcotype)[1];
		return results;

	}

	/**
	 * ����PropertyIsEqualToDocument����
	 * 
	 * @param pibdocument
	 *            ��Ҫ������PropertyIsEqualToDocument�Ķ���
	 * @param prefix
	 *            ��Ҫ��ǰ�����ӵ�ǰ׺������Ϊ�˺��������ʹ��
	 * @param i
	 *            A-i-j�е�i
	 * @param j
	 *            A-i-j�е�j
	 * @return ������Ҫ��propertyIsLike�ĸ�ʽ����String[0]:ָ����ĵݹ���
	 *         ,String[1]��propertyName,String[2]:literal
	 */
	public String[] ParsePropertyIsEqualToDocument(
			PropertyIsEqualToDocument pietdocument, String prefix, int i, int j) {
		String[] results = {};
		results[0] = prefix + ":PropertyIsEqualTo-" + i + "-" + j;
		BinaryComparisonOpType bcotype = pietdocument.getPropertyIsEqualTo();
		results[1] = ParseBinaryComparisonOpType(bcotype)[0];
		results[2] = ParseBinaryComparisonOpType(bcotype)[1];
		return results;
	}

	/**
	 * ����BinaryComparisonOpType����
	 * 
	 * @param bcotype
	 *            ��Ҫ������BinaryComparisonOpType�Ķ���
	 * @return ���ؽ��������Ķ�������results[0]ΪPropertyName��results[1]ΪLiteralֵ
	 */
	public String[] ParseBinaryComparisonOpType(BinaryComparisonOpType bcotype) {
		String[] results = {};
		XmlCursor xmlCursor = bcotype.newCursor();
		xmlCursor.toFirstChild();
		results[0] = xmlCursor.getTextValue();
		xmlCursor.toLastChild();
		results[1] = xmlCursor.getTextValue();
		return results;
	}

	/**
	 * ����getRecords���ļ���������GetRecordsDocument����
	 * 
	 * @param getRecordsContent
	 *            getRecords���ļ�����
	 * @return
	 */
	public GetRecordsDocument GetGetRecordsDocument(String getRecordsContent) {
		GetRecordsDocument grdocument;
		try {
			grdocument = GetRecordsDocument.Factory.parse(getRecordsContent);
			return grdocument;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
