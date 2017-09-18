package com.csw.utils.GetRecordsOperationUtil;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import net.opengis.gml.DirectPositionType;
import net.opengis.gml.EnvelopeType;
import net.opengis.gml.MultiSolidDocument;

import org.apache.xmlbeans.XmlCursor;

import com.csw.model.gml.LinearRingDocument;
import com.csw.model.gml.LinearRingType;
import com.csw.utils.FormatXmlUtil;
import com.ebrim.model.csw.ElementSetNameType;
import com.ebrim.model.csw.GetRecordsDocument;
import com.ebrim.model.csw.GetRecordsType;
import com.ebrim.model.csw.QueryConstraintType;
import com.ebrim.model.csw.QueryDocument;
import com.ebrim.model.csw.QueryType;
import com.ebrim.model.ogc.AndDocument;
import com.ebrim.model.ogc.BBOXDocument;
import com.ebrim.model.ogc.BBOXType;
import com.ebrim.model.ogc.BeyondDocument;
import com.ebrim.model.ogc.BinaryLogicOpType;
import com.ebrim.model.ogc.BinarySpatialOpType;
import com.ebrim.model.ogc.CrossesDocument;
import com.ebrim.model.ogc.DWithinDocument;
import com.ebrim.model.ogc.DistanceBufferType;
import com.ebrim.model.ogc.DistanceType;
import com.ebrim.model.ogc.EqualsDocument;
import com.ebrim.model.ogc.FilterType;
import com.ebrim.model.ogc.IntersectsDocument;
import com.ebrim.model.ogc.LiteralDocument;
import com.ebrim.model.ogc.LiteralType;
import com.ebrim.model.ogc.OrDocument;
import com.ebrim.model.ogc.OverlapsDocument;
import com.ebrim.model.ogc.PropertyIsBetweenDocument;
import com.ebrim.model.ogc.PropertyIsEqualToDocument;
import com.ebrim.model.ogc.PropertyIsGreaterThanDocument;
import com.ebrim.model.ogc.PropertyIsLessThanDocument;
import com.ebrim.model.ogc.PropertyIsLessThanOrEqualToDocument;
import com.ebrim.model.ogc.PropertyIsLikeDocument;
import com.ebrim.model.ogc.PropertyIsLikeType;
import com.ebrim.model.ogc.PropertyIsNotEqualToDocument;
import com.ebrim.model.ogc.PropertyIsNullDocument;
import com.ebrim.model.ogc.PropertyNameDocument;
import com.ebrim.model.ogc.PropertyNameType;
import com.ebrim.model.ogc.TouchesDocument;
import com.ebrim.model.ogc.WithinDocument;

public class TestGetRecords {
	public String CreateGetRecordsDocuments() {
		String resultStr = "";

		GetRecordsDocument grdocument = GetRecordsDocument.Factory
				.newInstance();
		GetRecordsType getrecordsType = grdocument.addNewGetRecords();
		QueryDocument queryDocument = QueryDocument.Factory.newInstance();
		QueryType queryType = queryDocument.addNewQuery();
		List<QName> list = new ArrayList<QName>();
		list.add(new QName("http://www.opengis.net/cat/csw/2.0.2", "Records",
				"csw"));
		queryType.setTypeNames(list);
		ElementSetNameType esnt = queryType.addNewElementSetName();
		esnt.setTypeNames(list);
		esnt.setStringValue("full");
		list = null;
		QueryConstraintType qctType = queryType.addNewConstraint();
		qctType.setVersion("1.1.0");
		FilterType ftType = qctType.addNewFilter();
		AndDocument adocument = AndDocument.Factory.newInstance();
		BinaryLogicOpType andtype = adocument.addNewAnd();

		AndDocument adAndDocument = AndDocument.Factory.newInstance();
		BinaryLogicOpType bloType = adAndDocument.addNewAnd();
		adAndDocument.setAnd(bloType);

		AndDocument andDocument1 = AndDocument.Factory.newInstance();
		BinaryLogicOpType blotype3 = andDocument1.addNewAnd();
		andDocument1.setAnd(blotype3);

		OrDocument orDocument2 = OrDocument.Factory.newInstance();
		BinaryLogicOpType bloType4 = orDocument2.addNewOr();
		orDocument2.setOr(bloType4);

		OrDocument orDocument3 = OrDocument.Factory.newInstance();
		BinaryLogicOpType blottype5 = orDocument3.addNewOr();
		orDocument3.setOr(blottype5);

		blottype5.addNewComparisonOps();
		PropertyIsLikeDocument pislikedocument = PropertyIsLikeDocument.Factory
				.newInstance();
		PropertyIsLikeType pitype = pislikedocument.addNewPropertyIsLike();
		PropertyNameType pnttype = pitype.addNewPropertyName();
		XmlCursor xmlCursor = pnttype.newCursor();
		xmlCursor.setTextValue("asdfjk");
		LiteralType ltyType = pitype.addNewLiteral();
		XmlCursor xmlCursor2 = ltyType.newCursor();
		xmlCursor2.setTextValue("sajkfj");

		PropertyIsEqualToDocument pietdocument = PropertyIsEqualToDocument.Factory
				.newInstance();
		pietdocument.addNewPropertyIsEqualTo();
		blottype5.set(pislikedocument);
		bloType4.set(orDocument3);
		blotype3.set(orDocument2);
		bloType.set(andDocument1);
		andtype.set(adAndDocument);
		adocument.setAnd(andtype);
		ftType.set(adocument);
		getrecordsType.set(queryDocument);
		resultStr = grdocument.xmlText();
		return resultStr;
	}

	public static void main(String[] args) throws Exception {
		TestGetRecords tgrGetRecords = new TestGetRecords();
		System.out.println(tgrGetRecords.CreatePropertyIsLikeContent("name",
				"value")
				+ tgrGetRecords.CreatePropertyIsEqualContent("name", "value"));
		System.out.println(FormatXmlUtil.formatXml(tgrGetRecords
				.CreatePropertyIsBetweenContent("name", new String[] {
						"value1", "value2" })));
		System.out.println(FormatXmlUtil.formatXml(tgrGetRecords
				.CreateCrossesContent("name", new String[] { "12", "12" },
						new String[] { "12", "12" })));
		System.out.println(FormatXmlUtil.formatXml(tgrGetRecords
				.CreateDWithinContent("name", "m", 1.2)));
		System.out.println(FormatXmlUtil.formatXml(tgrGetRecords
				.CreateTouchesContent("name", new Double[] { 1.0, 2.0 },
						new Double[] { 1.0, 2.0 })));
	}

	/**
	 * PropertyIsEqual PropertyIsLessThan PropertyIsLike
	 * PropertyIsGreaterThanOrEqualTo PropertyIsLessThanOrEqualTo
	 * PropertyIsGreaterThan PropertyIsNotEqualTo PropertyIsNull
	 * PropertyIsBetween
	 * 
	 * Crosses Intersects DWithin overlaps BBOX Within Equals Beyond
	 */

	/**
	 * ����Beyond������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param values
	 *            literals upper lower ����Ϊ�����ַ���
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreateBeyondContent(String name, String unit,
			Double distance, Double[] poses) {
		String resultStr = "";
		BeyondDocument beyondDocument = BeyondDocument.Factory.newInstance();
		DistanceBufferType dbftype = beyondDocument.addNewBeyond();
		dbftype.addNewGeometry();
		LinearRingDocument lrd = LinearRingDocument.Factory.newInstance();
		LinearRingType lrt = lrd.addNewLinearRing();
		com.csw.model.gml.DirectPositionType lplt = lrt.addNewPos();
		List<Double> listdoublgs = new ArrayList<Double>();
		listdoublgs.add(3.1);
		listdoublgs.add(3.1);
		lplt.setListValue(listdoublgs);
		com.csw.model.gml.DirectPositionType lplt1 = lrt.addNewPos();
		List<Double> listdoublgs1 = new ArrayList<Double>();
		listdoublgs1.add(3.1);
		listdoublgs1.add(3.1);
		lplt1.setListValue(listdoublgs1);
		com.csw.model.gml.DirectPositionType lplt3 = lrt.addNewPos();
		List<Double> listdoublgs4 = new ArrayList<Double>();
		listdoublgs4.add(3.1);
		listdoublgs4.add(3.1);
		lplt3.setListValue(listdoublgs4);
		com.csw.model.gml.DirectPositionType lplt2 = lrt.addNewPos();
		List<Double> listdoublgs5 = new ArrayList<Double>();
		listdoublgs5.add(3.1);
		listdoublgs5.add(3.1);
		lplt2.setListValue(listdoublgs5);

		dbftype.set(lrd);

		DistanceType dtype = dbftype.addNewDistance();
		dtype.setUnits(unit);
		dtype.setDoubleValue(distance);

		resultStr = beyondDocument.xmlText();
		int pos = resultStr.indexOf(">");
		resultStr = resultStr.substring(0, pos + 1) + "<ogc:PropertyName>"
				+ name + "</ogc:PropertyName>" + resultStr.substring(pos + 1);
		return resultStr;
	}

	/**
	 * ����Touches������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param values
	 *            literals upper lower ����Ϊ�����ַ���
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreateTouchesContent(String name, Double[] lowers,
			Double[] uppers) {
		String resultStr = "";
		TouchesDocument touchesDocument = TouchesDocument.Factory.newInstance();
		BinarySpatialOpType binarySpatialOpType = touchesDocument
				.addNewTouches();
		EnvelopeType envelopeType = binarySpatialOpType.addNewEnvelope();
		DirectPositionType dptyTypelc = envelopeType.addNewLowerCorner();
		dptyTypelc.setStringValue(lowers[0] + " " + lowers[1]);
		DirectPositionType dptypeue = envelopeType.addNewUpperCorner();
		dptypeue.setStringValue(uppers[0] + " " + uppers[1]);
		resultStr = touchesDocument.xmlText();
		int pos = resultStr.indexOf(">");
		resultStr = resultStr.substring(0, pos + 1) + "<ogc:PropertyName>"
				+ name + "</ogc:PropertyName>" + resultStr.substring(pos + 1);
		return resultStr;

	}

	/**
	 * ����Equals������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param values
	 *            literals upper lower ����Ϊ�����ַ���
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreateEqualsConent(String name, Double[] lowers,
			Double[] uppers) {
		String resultStr = "";
		EqualsDocument withinDocument = EqualsDocument.Factory.newInstance();
		BinarySpatialOpType binarySpatialOpType = withinDocument.addNewEquals();
		EnvelopeType envelopeType = binarySpatialOpType.addNewEnvelope();
		DirectPositionType dptyTypelc = envelopeType.addNewLowerCorner();
		dptyTypelc.setStringValue(lowers[0] + " " + lowers[1]);
		DirectPositionType dptypeue = envelopeType.addNewUpperCorner();
		dptypeue.setStringValue(uppers[0] + " " + uppers[1]);
		resultStr = withinDocument.xmlText();
		int pos = resultStr.indexOf(">");
		resultStr = resultStr.substring(0, pos + 1) + "<ogc:PropertyName>"
				+ name + "</ogc:PropertyName>" + resultStr.substring(pos + 1);
		return resultStr;
	}

	/**
	 * ����Within������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param values
	 *            literals upper lower ����Ϊ�����ַ���
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreateWithinContent(String name, Double[] lowers,
			Double[] uppers) {
		String resultStr = "";
		WithinDocument withinDocument = WithinDocument.Factory.newInstance();
		BinarySpatialOpType binarySpatialOpType = withinDocument.addNewWithin();
		EnvelopeType envelopeType = binarySpatialOpType.addNewEnvelope();
		DirectPositionType dptyTypelc = envelopeType.addNewLowerCorner();
		dptyTypelc.setStringValue(lowers[0] + " " + lowers[1]);
		DirectPositionType dptypeue = envelopeType.addNewUpperCorner();
		dptypeue.setStringValue(uppers[0] + " " + uppers[1]);
		resultStr = withinDocument.xmlText();
		int pos = resultStr.indexOf(">");
		resultStr = resultStr.substring(0, pos + 1) + "<ogc:PropertyName>"
				+ name + "</ogc:PropertyName>" + resultStr.substring(pos + 1);
		return resultStr;
	}

	/**
	 * ����BBOX������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param values
	 *            literals upper lower ����Ϊ�����ַ���
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreateBBOXContent(String name, Double[] lowers,
			Double[] uppers) {
		String resultStr = "";
		BBOXDocument bdocument = BBOXDocument.Factory.newInstance();
		BBOXType botype = bdocument.addNewBBOX();
		EnvelopeType envelopeType = botype.addNewEnvelope();
		DirectPositionType dptyTypelc = envelopeType.addNewLowerCorner();
		dptyTypelc.setStringValue(lowers[0] + " " + lowers[1]);
		DirectPositionType dptypeue = envelopeType.addNewUpperCorner();
		dptypeue.setStringValue(uppers[0] + " " + uppers[1]);
		resultStr = bdocument.xmlText();
		int pos = resultStr.indexOf(">");
		resultStr = resultStr.substring(0, pos + 1) + "<ogc:PropertyName>"
				+ name + "</ogc:PropertyName>" + resultStr.substring(pos + 1);
		return resultStr;

	}

	/**
	 * ����Overlaps������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param values
	 *            literals upper lower ����Ϊ�����ַ���
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreateOverlapsContent(String name, Double[] lowers,
			Double[] uppers) {
		String resultStr = "";
		OverlapsDocument oldocument = OverlapsDocument.Factory.newInstance();
		BinarySpatialOpType bsotypeType = oldocument.addNewOverlaps();
		EnvelopeType etype = bsotypeType.addNewEnvelope();
		DirectPositionType dptyTypelc = etype.addNewLowerCorner();
		dptyTypelc.setStringValue(lowers[0] + " " + lowers[1]);
		DirectPositionType dptypeue = etype.addNewUpperCorner();
		dptypeue.setStringValue(uppers[0] + " " + uppers[1]);
		resultStr = oldocument.xmlText();
		int pos = resultStr.indexOf(">");
		resultStr = resultStr.substring(0, pos + 1) + "<ogc:PropertyName>"
				+ name + "</ogc:PropertyName>" + resultStr.substring(pos + 1);
		return resultStr;

	}

	/**
	 * ����Intersects������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param values
	 *            literals upper lower ����Ϊ�����ַ���
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreateIntersectsContent(String name, Double[] lowers,
			Double[] uppers) {
		String resultStr = "";
		IntersectsDocument intersectsDocument = IntersectsDocument.Factory
				.newInstance();
		BinarySpatialOpType bsoType = intersectsDocument.addNewIntersects();

		EnvelopeType entype = bsoType.addNewEnvelope();
		DirectPositionType dptypelc = entype.addNewLowerCorner();
		dptypelc.setStringValue(lowers[0] + " " + lowers[1]);
		DirectPositionType dptypeuc = entype.addNewUpperCorner();
		dptypeuc.setStringValue(uppers[0] + " " + lowers[1]);
		resultStr = intersectsDocument.xmlText();
		int pos = resultStr.indexOf(">");
		resultStr = resultStr.substring(0, pos + 1) + "<ogc:PropertyName>"
				+ name + "</ogc:PropertyName>" + resultStr.substring(pos + 1);
		return resultStr;
	}

	/**
	 * ����DWithin������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param values
	 *            literals upper lower ����Ϊ�����ַ���
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreateDWithinContent(String name, String units,
			Double distance) {
		String resultStr = "";
		DWithinDocument dwdocument = DWithinDocument.Factory.newInstance();
		DistanceBufferType dbtype = dwdocument.addNewDWithin();
		dbtype.addNewGeometry();
		MultiSolidDocument msdocument = MultiSolidDocument.Factory
				.newInstance();
		msdocument.addNewMultiSolid();
		dbtype.set(msdocument);
		DistanceType ditype = dbtype.addNewDistance();
		ditype.setUnits(units);
		ditype.setDoubleValue(distance);
		resultStr = dwdocument.xmlText();
		int pos = resultStr.indexOf(">");
		resultStr = resultStr.substring(0, pos + 1) + "<ogc:PropertyName>"
				+ name + "</ogc:PropertyName>" + resultStr.substring(pos + 1);

		return resultStr;
	}

	/**
	 * ����Crosses������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param values
	 *            literals upper lower ����Ϊ�����ַ���
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreateCrossesContent(String name, String[] lowers,
			String[] uppers) {
		String resultStr = "";
		CrossesDocument cdocument = CrossesDocument.Factory.newInstance();
		BinarySpatialOpType bsotype = cdocument.addNewCrosses();
		EnvelopeType etType = bsotype.addNewEnvelope();
		DirectPositionType dptypelc = etType.addNewLowerCorner();
		dptypelc.setStringValue(lowers[0] + " " + lowers[1]);
		DirectPositionType dptypeuc = etType.addNewUpperCorner();
		dptypeuc.setStringValue(uppers[0] + " " + uppers[1]);
		String result = cdocument.xmlText();

		int pos = result.indexOf(">");
		result = result.substring(0, pos + 1) + "<ogc:PropertyName>" + name
				+ "</ogc:PropertyName>" + result.substring(pos + 1);

		resultStr = result;
		return resultStr;
	}

	/**
	 * ����PropertyIsNotEqualTo������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param values
	 *            literals upper lower ����Ϊ�����ַ���
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreatePropertyIsBetweenContent(String name, String[] values) {
		String resultStr = "";
		TestGetRecords tgr = new TestGetRecords();
		PropertyIsBetweenDocument pibdocument = PropertyIsBetweenDocument.Factory
				.newInstance();
		pibdocument.addNewPropertyIsBetween();
		resultStr = tgr.ChangeTests(pibdocument.xmlText());
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreatePropertyNameDocument(name).xmlText(), name);
		resultStr = tgr.CreatePropertyIsBetWeen(resultStr, values);
		return resultStr;
	}

	/**
	 * ��lower��upper��������
	 * 
	 * @param resultStr
	 * @param values
	 * @return
	 */
	public String CreatePropertyIsBetWeen(String resultStr, String[] values) {
		String result = "";
		if (values != null) {
			if (values.length != 2) {
				result = "";
			} else {
				result = "<ogc:LowerBoundary><ogc:Literal>"
						+ values[0]
						+ "</ogc:Literal></ogc:LowerBoundary><ogc:UpperBoundary><ogc:Literal>"
						+ values[1] + "</ogc:Literal></ogc:UpperBoundary>";
			}
		}
		int pos = resultStr.lastIndexOf("</");
		System.out.println(pos);
		resultStr = resultStr.substring(0, pos) + result
				+ resultStr.substring(pos);
		return resultStr;
	}

	/**
	 * ����PropertyIsNotEqualTo������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param value
	 *            literal
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreatePropertyIsNotEqualToContent(String name, String value) {
		TestGetRecords tgr = new TestGetRecords();
		String resultStr = "";
		PropertyIsNotEqualToDocument tiltoetdocument = PropertyIsNotEqualToDocument.Factory
				.newInstance();
		tiltoetdocument.addNewPropertyIsNotEqualTo();
		resultStr = tgr.ChangeTests(tiltoetdocument.xmlText());
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreatePropertyNameDocument(name).xmlText(), name);
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreateLiteralDocument(value).xmlText(), value);
		return resultStr;
	}

	/**
	 * ����PropertyIsGreaterThan������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param value
	 *            literal
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreatePropertyIsGreaterThanContent(String name, String value) {
		String resultStr = "";
		TestGetRecords tgr = new TestGetRecords();
		PropertyIsGreaterThanDocument pigtdocument = PropertyIsGreaterThanDocument.Factory
				.newInstance();
		pigtdocument.addNewPropertyIsGreaterThan();
		resultStr = tgr.ChangeTests(pigtdocument.xmlText());
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreatePropertyNameDocument(name).xmlText(), name);
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreateLiteralDocument(value).xmlText(), value);
		return resultStr;
	}

	/**
	 * ����PropertyIsNull������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param value
	 *            literal
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreatePropertyIsNullContent(String name) {
		String resultStr = "";
		TestGetRecords tgr = new TestGetRecords();
		PropertyIsNullDocument pidDocument = PropertyIsNullDocument.Factory
				.newInstance();
		pidDocument.addNewPropertyIsNull();
		resultStr = tgr.ChangeTests(pidDocument.xmlText());
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreatePropertyNameDocument(name).xmlText(), name);
		// resultStr = tgr.CreateEntireContent(resultStr, tgr
		// .CreateLiteralDocument(value).xmlText(), value);
		return resultStr;
	}

	/**
	 * ����PropertyIsLessThanOrEqualTo������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param value
	 *            literal
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreatePropertyIsLessThanOrEqualToContent(String name,
			String value) {
		TestGetRecords tgr = new TestGetRecords();
		String resultStr = "";
		PropertyIsLessThanOrEqualToDocument tiltoetdocument = PropertyIsLessThanOrEqualToDocument.Factory
				.newInstance();
		tiltoetdocument.addNewPropertyIsLessThanOrEqualTo();
		resultStr = tgr.ChangeTests(tiltoetdocument.xmlText());
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreatePropertyNameDocument(name).xmlText(), name);
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreateLiteralDocument(value).xmlText(), value);
		return resultStr;
	}

	/**
	 * ����PropertyIsBetWeen������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param value
	 *            literal
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreatePropertyIsBetWeenContent(String name, String value) {
		String resultStr = "";
		TestGetRecords tgr = new TestGetRecords();
		PropertyIsBetweenDocument pibdocument = PropertyIsBetweenDocument.Factory
				.newInstance();
		pibdocument.addNewPropertyIsBetween();
		resultStr = tgr.ChangeTests(pibdocument.xmlText());
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreatePropertyNameDocument(name).xmlText(), name);
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreateLiteralDocument(value).xmlText(), value);
		return resultStr;
	}

	/**
	 * ����PropertyIsEqualContent������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param value
	 *            literal
	 * @return ���� PropertyIsEqualContent������
	 */
	public String CreatePropertyIsEqualContent(String name, String value) {
		TestGetRecords tgr = new TestGetRecords();
		String resultStr = "";
		PropertyIsEqualToDocument pietdocument = PropertyIsEqualToDocument.Factory
				.newInstance();
		pietdocument.addNewPropertyIsEqualTo();
		// ���յ�<.../>��Ϊ<...></...>
		resultStr = tgr.ChangeTests(pietdocument.xmlText());
		// ��propertynameDocument�����ݼ��뵽�����������
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreatePropertyNameDocument(name).xmlText(), name);
		// ��literalDocument�����ݼ��뵽�����������
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreateLiteralDocument(value).xmlText(), value);

		// ����������һ���жϵ�����
		return resultStr;
	}

	/**
	 * ����PropertyIsLessThan������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param value
	 *            literal
	 * @return ����PorpertyIsLikeConent������
	 */
	public String CreatePropertyIsLessThanContent(String name, String value) {
		String resultStr = "";
		TestGetRecords tgr = new TestGetRecords();
		PropertyIsLessThanDocument pitDocument = PropertyIsLessThanDocument.Factory
				.newInstance();
		pitDocument.addNewPropertyIsLessThan();
		resultStr = tgr.ChangeTests(pitDocument.xmlText());
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreatePropertyNameDocument(name).xmlText(), name);
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreateLiteralDocument(value).xmlText(), value);
		return resultStr;
	}

	/**
	 * ����PropertyIsLike������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param value
	 *            literal
	 * @return ����PorpertyIsLikeConent������
	 */
	public String CreatePropertyIsLikeContent(String name, String value) {
		String resultStr = "";
		TestGetRecords tgr = new TestGetRecords();
		PropertyIsLikeDocument pilDocument = PropertyIsLikeDocument.Factory
				.newInstance();
		pilDocument.addNewPropertyIsLike();
		resultStr = tgr.ChangeTests(pilDocument.xmlText());
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreatePropertyNameDocument(name).xmlText(), name);
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreateLiteralDocument(value).xmlText(), value);
		return resultStr;
	}

	/**
	 * ����PropertyIsGreaterThanOrEqualToContent������ֵ
	 * 
	 * @param name
	 *            propertyName
	 * @param value
	 *            literal
	 * @return ����PorpertyIsLikeConent������
	 */
	public String CreatePropertyIsGreaterThanOrEqualToContent(String name,
			String value) {
		String resultStr = "";
		TestGetRecords tgr = new TestGetRecords();
		PropertyIsGreaterThanDocument document = PropertyIsGreaterThanDocument.Factory
				.newInstance();
		document.addNewPropertyIsGreaterThan();
		resultStr = tgr.ChangeTests(document.xmlText());
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreatePropertyNameDocument(name).xmlText(), name);
		resultStr = tgr.CreateEntireContent(resultStr, tgr
				.CreateLiteralDocument(value).xmlText(), value);

		return resultStr;
	}

	/**
	 * �����ֲ�����PropertyName ��Literal�������
	 * 
	 * @param resultStr
	 *            �����ģ���������PropertyName��
	 * @param re
	 *            ��Ҫ��ӵ�resultStr�е�����
	 * @param value
	 *            ��Ҫ��䵽�����е�����
	 * @return ���ص������֮�������String����
	 */
	public String CreateEntireContent(String resultStr, String re, String value) {
		String lastStr = resultStr.substring(resultStr.lastIndexOf("</"));
		resultStr = resultStr.substring(0, resultStr.lastIndexOf("</"));
		String ldocuemntxmltext = re;
		int ld = ldocuemntxmltext.lastIndexOf("</");
		String result1 = "<" + ldocuemntxmltext.substring(ld + 2) + value
				+ ldocuemntxmltext.substring(ld);
		resultStr += result1 + lastStr;
		return resultStr;
	}

	/**
	 * ����name������PropertyNameDocument
	 * 
	 * @param name
	 *            ��Ҫ��䵽PropertyNameDocument�е�����
	 * @return ����PropertYnameDocument
	 */
	public PropertyNameDocument CreatePropertyNameDocument(String name) {
		PropertyNameDocument pndocument = PropertyNameDocument.Factory
				.newInstance();
		PropertyNameType pntype = pndocument.addNewPropertyName();
		XmlCursor xmlCursor = pntype.newCursor();
		xmlCursor.setTextValue(name);
		return pndocument;
	}

	/**
	 * ����value ������LiteralDocument
	 * 
	 * @param value
	 *            ��Ҫ��䵽LiteralDocument�е�����
	 * @return ����LiteralDocument
	 */
	public LiteralDocument CreateLiteralDocument(String value) {
		LiteralDocument literalDocument = LiteralDocument.Factory.newInstance();
		LiteralType literalType = literalDocument.addNewLiteral();
		XmlCursor xmlCursor = literalType.newCursor();
		xmlCursor.setTextValue(value);
		return literalDocument;
	}

	/**
	 * ��<..../>��Ϊ <...></...>�����ݵķ���
	 * 
	 * @param xmlcontent
	 *            <..../>
	 * @return ���� <...></...>������
	 */
	public String ChangeTests(String xmlcontent) {
		int firstpos = xmlcontent.indexOf(" ");
		String firstcontent = xmlcontent.substring(0, firstpos);
		firstcontent = firstcontent.substring(1, firstcontent.length());
		xmlcontent = xmlcontent.substring(0, xmlcontent.length() - 2) + "></"
				+ firstcontent + ">";

		return xmlcontent;
	}
}
