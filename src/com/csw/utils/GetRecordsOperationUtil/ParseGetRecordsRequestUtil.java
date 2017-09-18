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
	// 用于表示A-i-j中的i
	private static int i = 0;
	// 用来表示prefix中的值
	private static String prefixinit = "";
	// 存储maps的值，第一个string表示的是所有的层次关系，第二个string表示的是存储的关系值
	private static Map<String, String> maps = new HashMap<String, String>();
	// 这个的resultMaps的作用在于将末尾为:And-i-j或者Or-i-j的逻辑判断的空的其产生
	// 的ExtrinsicObject的id集合存储起来，用于参与上一层的And或者Or的操作，其目的也是在于利用此进行递归运算
	private static Map<String, Set<String>> resultMaps = new HashMap<String, Set<String>>();
	Configuration conf = new Configuration().configure();

	public static Map<String, Set<String>> getResultMaps() {
		return resultMaps;
	}

	public static void setResultMaps(Map<String, Set<String>> resultMaps) {
		ParseGetRecordsRequestUtil.resultMaps = resultMaps;
	}

	/**
	 * 解析条件集合，获取符合条件的ExtrinsicObject的集合
	 * 
	 * @param mapss
	 *            条件集合(最初的最全的集合）
	 * @return 返回符合条件的extrinsicObject的ID集合
	 */
	public void GetExtrinsicObjects(Map<String, String> mapss) {
		Set<String> extrinsicobjects = new HashSet<String>();
		// 获取集合中的条件层次的最大值
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
	 * 该函数是用于处理不同层次的条件集合，并且需要将获取的结果存储到上面的结果中并删除掉原始map中的相关的判断[递归函数]
	 * 
	 * @param maplength
	 *            这个是指已经处理到哪个递归的层次了是从后面向前面传递,并且也需要上一层的关系来判断两者是否属于同一层次的
	 * @param tempmap
	 *            这个是根据在这一层次上的条件集合
	 * @param exobjects
	 *            这个是根据上一层查询获得extrinsicObject的集合来查询出的extrinsicObject
	 * @return 返回符合条件的extrinsicObject的id值集
	 */
	public void GetExtrinsicObjects(int maxlength, Map<String, String> tempmap,
			Set<String> exobjects) {
		System.out.println("此时的集合中的条件层次为:" + maxlength);
		// 存储此时需要的的查询条件，在这个层次上的所有的查询条件
		Map<String, String> maps = new HashMap<String, String>();
		System.out.println("=============================================");
		for (String str : tempmap.keySet()) {
			if (str.split(":").length <= maxlength) {
				maps.put(str, tempmap.get(str));
				System.out.println(str + " : " + tempmap.get(str));
			}
		}
		System.out.println("需要判断的条件集合中的条件有 ： " + maps.size() + "个");
		System.out.println("此时结果集合中存储了" + resultMaps.size() + "条临时记录");
		System.out.println("=============================================");
		// 将这个使用过的tempmap中进行了处理的东西删除
		Set<String> eoset = new HashSet<String>();
		int i = 0;
		// 用于存储所有的部分的信息，作用是将这些信息通过and 或者or进行合并起来,这些最终每一步还是存储在resultMap中
		Map<String, Set<String>> tempMaps = new HashMap<String, Set<String>>();
		for (String str : maps.keySet()) {
			// maxlength的值决定了是否遍历完毕，如果是1则遍历完毕，大于1则没有遍历完毕
			if (maxlength == 1) {
				// 遍历完成，这个时候str应该是and-0-0或者or-0-0
				if (resultMaps.containsKey(str)) {
					tempMaps.put(str, resultMaps.get(str));
					System.out.println("最终获取的符合条件的传感器的个数为"
							+ resultMaps.get(str).size() + "个");
					System.out
							.println("===============所有满足条件的ExtrinsicObject对象======================");
					for (String str1 : resultMaps.get(str)) {
						System.out.println(str1);
					}
					System.out
							.println("=====================================================================");
				}

			} else if (str.split(":").length == maxlength) {
				// 设定当maplength不为1的时候的情况处理
				// 获取查询的逻辑条件 ，值为innervalue,值为valValue
				// 之所以判断or-或者and-是因为有些判断的字段里面含有or或者and
				if ((str.substring(str.lastIndexOf(":")).toLowerCase()
						.contains("and-") || str
						.substring(str.lastIndexOf(":")).toLowerCase()
						.contains("or-"))) {
					// 表明结果集中是否包含这个条件的查询后的结果
					if (resultMaps.containsKey(str)) {
						// 将上一步获取的查询结果统一到这个临时结果集上，主要是为了和下面的else中产生的数据进行合并处理
						tempMaps.put(str, resultMaps.get(str));
						// 移除结果集合中查询的结果
						resultMaps.remove(str);
					}
				} else {

					// 解决的是查询符合该条件的sensor集合
					String[] values = str.split(":");
					String lastvalue = values[values.length - 1];
					String[] innervalue = lastvalue.split("-");
					// 需要查询的逻辑判断条件如PropertyIsGreaterThanOrEqualTo，PropertyIsLessThanOrEqualTo等
					String inervalue = innervalue[0];
					// 获取如 dct:end|2012-01-01 23:59:59等值
					String valValue = maps.get(str);
					// tempMaps存储返回的复合条件的ExtrinsicObject的id集合
					tempMaps.putAll(ResetMapStringSetStringUtil(str.substring(
							0, str.lastIndexOf(":"))
							+ ":" + i, inervalue, valValue));
					i++;
				}
			}
		}
		/**
		 * 查看每层中进行获取的临时的满足条件的ExtrinsicObject对象
		 */
		for (String str : tempMaps.keySet()) {
			System.out.println(str);
			System.out.println(tempMaps.get(str).size());
		}
		// 这里应该是有多少个查询字段就应该有多少个查询的次数
		System.out.println("tempMaps:" + tempMaps.size());
		// panduanStr适用于存储所有不同的前缀，如And-0-0:Or-1-0:Or-2-0:...并不包括最后一个名称
		String panduanStr = "";
		if (maxlength == 1) {
			// 将仅仅只有一个临时记录and-0-0或者Or-0-0
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
		// 这里已经到了上一层
		int k = 1;
		if (maxlength == 1) {
		} else {
			panduanStr = panduanStr.substring(0, panduanStr.length() - 1);
			String tempStr = panduanStr;
			String[] keyStrings = tempStr.split("\\|");
			k = keyStrings.length;
		}
		// strs用于存储panduanStr中的所有的字符串
		String[] strs = new String[k];
		if (k == 1) {
			strs[0] = panduanStr;
		} else {
			for (int m = 0; m < k; m++) {
				strs[m] = panduanStr.split("\\|")[m];
			}
		}
		if (maxlength != 1) {
			// 遍历strs中存储的字符串
			for (String strname : strs) {
				String prefixStrName = strname.substring(strname
						.lastIndexOf(":") + 1);
				System.out.println(prefixStrName);
				// 将数据合并
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
					System.out.println("And合并后的resultMaps中" + strname + "的个数为:"
							+ resultMaps.get(strname).size() + "个");
					System.out
							.println("分别为:===================================");
					for (String str : resultMaps.get(strname)) {
						System.out.println(str);
					}
					System.out.println("===================================");
				} else if (prefixStrName.toLowerCase().contains("or-")) {
					// 充分利用SET不允许存在重复的元素的特性
					for (String tempStr : tempMaps.keySet()) {
						if (tempStr.contains(strname)) {
							eoset.addAll(tempMaps.get(tempStr));
						}
					}
					resultMaps.put(strname, eoset);
					System.out.println("Or合并后的resultMaps中" + strname + "的个数为:"
							+ resultMaps.get(strname).size() + "个");
					System.out
							.println("分别为:===================================");
					for (String str : resultMaps.get(strname)) {
						System.out.println(str);
					}
					System.out.println("===================================");
				}
			}
		}
		// 这是确保在第一层到达的时候停止迭代循环
		if (--maxlength > 0) {
			GetExtrinsicObjects(maxlength, maps, exobjects);
		}
	}

	/**
	 * 获取list出现次数为count的元素集合
	 * 
	 * @param listsList
	 *            要查询的list的元素集合
	 * @param count
	 *            查询的元素出现的次数
	 * @return 返回出现了count次的元素的集合
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
	 * 根据制定的条件查询相关的ExternalObject对象，并将查询的对象相对应的存储起来
	 * 
	 * @param loujiguanxi
	 *            应该为上一层次的关系and-i-j 或者or-i-j.
	 * @param chaxunziduan
	 *            查询的字段如dct：modified等
	 * @param chaxunValue
	 *            查询的值，这个不同的查询字段相同，它的数据的结果在之前获取条件集合的时候已经确认
	 * @return 返回的是根据这所确定的满足的ExtrinsicObject对象
	 */
	public Map<String, Set<String>> ResetMapStringSetStringUtil(
			String loujiguanxi, String chaxunziduan, String chaxunValue) {
		System.out.println("loujiguanxi:" + loujiguanxi + " chaxunziduan: "
				+ chaxunziduan + " chaxunValue:" + chaxunValue);
		Map<String, Set<String>> partmapMap = new HashMap<String, Set<String>>();
		// 【获取该条件下的所有符合条件的ExtrinsicObject集】
		Set<String> idvalueSet = QueryProcessByConditionUtil
				.GetExtrinsicObjects(chaxunziduan, chaxunValue);
		partmapMap.put(loujiguanxi, idvalueSet);
		return partmapMap;

	}

	/**
	 * 根据getRecordsXmlContent的内容解析出and,or ,not之间的层次关系 解析出的string 应该是具有
	 * A-1-0:A-2-0:A-3-0:...也可以为A-1-0:O-2-0:O-3-0... 或者为A-1-0:O-2-1:
	 * 以A-1-0为例：A代表and，1代表为第一层，0代表为第一层中的第一个And子层
	 * 
	 * 
	 * 
	 * @param getRecordsXmlContent
	 *            需要解析的getRecords的内容
	 * @return map<string,string>
	 *         map中的第一个string为其中的各个层次之间的关系，第二个string是指其中的XML片段来加以处理
	 *         ，对于所含有的and，or，not的XML片段需要去除
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
	 * 获取OrDocument中的各种运算规则
	 * 
	 * @param orDocument
	 *            需要解析OrDocument
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
	 * 获取AndDocument中的各种运算规则
	 * 
	 * @param andDocument
	 *            需要解析AndDocument
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
	 * 解析BinaryLogicOpType中的各种运算规则
	 * 
	 * @param blotType
	 *            需要解析的BinaryLogicOpType
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
	 * 解析PorpertyIsLikeDocument对象
	 * 
	 * @param pildocument
	 *            需要解析的PropertyIsLikeDocument对象
	 * @param prefix
	 *            需要在前面添加的前缀，这个是为了后面的搜索使用
	 * @param i
	 *            A-i-j中的i
	 * @param j
	 *            A-i-j中的j
	 * @return 返回需要的propertyIsLike的格式内容String[0]:指引其的递归层次
	 *         ,String[1]：propertyName,String[2]:literal
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
	 * 解析PropertyIsBetweenDocument对象
	 * 
	 * @param pibdocument
	 *            需要解析的PropertyIsBetweenDocument的对象
	 * @param prefix
	 *            需要在前面的添加的千醉，这是为了后面的搜索使用
	 * @param i
	 *            A-i-j中的i
	 * @param j
	 *            A-i-j中的j
	 * @return 返回需要的propertyIsLike的格式内容String[0]:指引其的递归层次
	 *         ,String[1]：propertyName,String[2]:literal
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
	 * 解析PropertyIsGreaterThanOrEqualToDocument对象
	 * 
	 * @param pibdocument
	 *            需要解析的PropertyIsGreaterThanOrEqualToDocument的对象
	 * @param prefix
	 *            需要在前面的添加的前缀，这是为了后面的搜索使用
	 * @param i
	 *            A-i-j中的i
	 * @param j
	 *            A-i-j中的j
	 * @return 返回需要的propertyIsLike的格式内容String[0]:指引其的递归层次
	 *         ,String[1]：propertyName,String[2]:literal
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
	 * 解析PropertyIsEqualToDocument对象
	 * 
	 * @param pibdocument
	 *            需要解析的PropertyIsEqualToDocument的对象
	 * @param prefix
	 *            需要在前面的添加的前缀，这是为了后面的搜索使用
	 * @param i
	 *            A-i-j中的i
	 * @param j
	 *            A-i-j中的j
	 * @return 返回需要的propertyIsLike的格式内容String[0]:指引其的递归层次
	 *         ,String[1]：propertyName,String[2]:literal
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
	 * 解析BinaryComparisonOpType对象
	 * 
	 * @param bcotype
	 *            需要解析的BinaryComparisonOpType的对象
	 * @return 返回解析出来的对象内容results[0]为PropertyName，results[1]为Literal值
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
	 * 根据getRecords的文件内容生成GetRecordsDocument对象
	 * 
	 * @param getRecordsContent
	 *            getRecords的文件内容
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
