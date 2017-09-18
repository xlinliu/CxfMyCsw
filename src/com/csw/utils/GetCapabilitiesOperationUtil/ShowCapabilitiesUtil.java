package com.csw.utils.GetCapabilitiesOperationUtil;

import javax.xml.namespace.QName;

import net.opengis.ows.AddressType;
import net.opengis.ows.CodeType;
import net.opengis.ows.ContactType;
import net.opengis.ows.DomainType;
import net.opengis.ows.KeywordsType;
import net.opengis.ows.OperationsMetadataDocument;
import net.opengis.ows.RequestMethodType;
import net.opengis.ows.ResponsiblePartySubsetType;
import net.opengis.ows.ServiceIdentificationDocument;
import net.opengis.ows.ServiceProviderDocument;
import net.opengis.ows.DCPDocument.DCP;
import net.opengis.ows.HTTPDocument.HTTP;
import net.opengis.ows.OperationDocument.Operation;
import net.opengis.ows.OperationsMetadataDocument.OperationsMetadata;
import net.opengis.ows.ServiceIdentificationDocument.ServiceIdentification;
import net.opengis.ows.ServiceProviderDocument.ServiceProvider;

import com.ebrim.model.csw.CapabilitiesDocument;
import com.ebrim.model.csw.CapabilitiesType;
import com.ebrim.model.ogc.FilterCapabilitiesDocument;
import com.ebrim.model.ogc.GeometryOperandsType;
import com.ebrim.model.ogc.IdCapabilitiesType;
import com.ebrim.model.ogc.SpatialCapabilitiesType;
import com.ebrim.model.ogc.SpatialOperatorsType;
import com.ebrim.model.ogc.FilterCapabilitiesDocument.FilterCapabilities;
import com.ebrim.model.ogc.SpatialOperatorNameType.Enum;

public class ShowCapabilitiesUtil {
	/**
	 * 生成ServiceIndentification文档，并返回ServiceIdentification文档
	 * 
	 * @param rpgId
	 *            需要查询的RegistryPacakge的id
	 * @return
	 */
	public ServiceIdentification getServiceIdentification() {
		ServiceIdentification sideIdentification = ServiceIdentificationDocument.Factory
				.newInstance().addNewServiceIdentification();
		sideIdentification.setTitle("LIESMARS CSW");
		sideIdentification
				.setAbstract("LIESMARS CSW provides facilities for retrieving, storing and managing process description under a Sensor Web Environment");
		KeywordsType kttypes = sideIdentification.addNewKeywords();
		kttypes.addKeyword("ebRIM");
		kttypes.addKeyword("Registry");
		kttypes.addKeyword("Discovery");
		kttypes.addKeyword("Porcess");
		CodeType ct = sideIdentification.addNewServiceType();
		ct.setCodeSpace("http://www.opengis.net/cat/csw");
		ct.setStringValue("CSW");
		sideIdentification.addServiceTypeVersion("1.0");
		return sideIdentification;
	}

	/**
	 * 生成FilterCapabilites的文档，并将FilterCapabilities对象返回
	 * 
	 * @param rpgId
	 *            需要查询的RegistryPacakge的id
	 * @return
	 */
	public FilterCapabilities GetFilterCapabilities() {
		FilterCapabilities fCapabilities = FilterCapabilitiesDocument.Factory
				.newInstance().addNewFilterCapabilities();
		IdCapabilitiesType idcapabilitiesType = fCapabilities
				.addNewIdCapabilities();
		idcapabilitiesType.addNewFID();
		idcapabilitiesType.addNewEID();

		SpatialCapabilitiesType sctType = fCapabilities
				.addNewSpatialCapabilities();
		SpatialOperatorsType spatialoptType = sctType.addNewSpatialOperators();

		String[] spatialoperators = { "BBOX", "Beyond", "Contains", "Crosses",
				"Disjoint", "DWithin", "Equals", "Intersects", "Overlaps",
				"Touches", "Within" };
		for (String spatialopr : spatialoperators) {
			spatialoptType.addNewSpatialOperator().setName(
					Enum.forString(spatialopr));
		}
		GeometryOperandsType geometryOperandsType = sctType
				.addNewGeometryOperands();
		String[] geometryoperandsvalue = { "gml:Envelope", "gml:Point",
				"gml:LineString", "gml:Polygon" };
		for (String value : geometryoperandsvalue) {
			geometryOperandsType.addGeometryOperand(new QName(value));
		}
		return fCapabilities;
	}

	/**
	 * 生成对应的OperationMetadata的XML文档内容，并返回OperationMetadata内容
	 * 
	 * @param rpgId
	 *            需要查询的RegistryPacakge的id
	 * @return
	 */
	public OperationsMetadata getOperationsMetadata() {
		String base = "http://localhost:8080/MyCswPro/";
		String getRecordspath = base + "GetRecordsAction";
		String getRecordByIdpath = base + "GetRecordByIdAction";
		// String describeRecordPath = base + "DescribeRecordAction";
		// String getDomainPath = base + "GetDomainAction";
		String transactionPath = base + "TransactionAction";
		// String harvestPath = base + "Harvest";
		OperationsMetadata omMetadata = OperationsMetadataDocument.Factory
				.newInstance().addNewOperationsMetadata();
		// getRecords
		Operation operation = omMetadata.addNewOperation();
		operation.setName("GetRecords");
		DCP dcp = operation.addNewDCP();
		HTTP http = dcp.addNewHTTP();
		RequestMethodType rmt = http.addNewPost();
		rmt.setHref(getRecordspath);
		DomainType domainType1 = operation.addNewParameter();
		domainType1.setName("typeName");
		domainType1.addValue("rim:registrypackage");
		domainType1.addValue("csw:record");

		DomainType domainType2 = operation.addNewParameter();
		domainType2.setName("outputFormat");
		domainType2.addValue("text/xml");
		domainType2.addValue("application/xml");

		DomainType domainType3 = operation.addNewParameter();
		domainType3.setName("outputSchema");
		domainType3.addValue("http://www.opengis.net/cat/csw/2.0.2");
		domainType3.addValue("urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0");

		DomainType domainType4 = operation.addNewParameter();
		domainType4.setName("resultType");
		domainType4.addValue("hits");
		domainType4.addValue("results");
		domainType4.addValue("validate");

		DomainType domainType5 = operation.addNewParameter();
		domainType5.setName("ElementSetName");
		domainType5.addValue("brief");
		domainType5.addValue("summary");
		domainType5.addValue("full");

		DomainType domainType6 = operation.addNewParameter();
		domainType6.setName("CONSTRAINTLANGUAGE");
		domainType6.addValue("Filter");
		domainType6.addValue("CQL");

		DomainType domainType7 = operation.addNewConstraint();
		domainType7.setName("SupportedISOQueryables");
		String[] values = { "RevisionDate", "AlternateTitle", "CreationDate",
				"PublicationDate", "OrganisationName",
				"HasSecurityConstraints", "Language", "ResourceIdentifier",
				"ParentIdentifier", "KeywordType", "TopicCategory",
				"ResourceLanguage", "GeographicDescriptionCode",
				"DistanceValue", "DistanceUOM", "TempExtent_begin",
				"TempExtent_end", "ServiceType", "ServiceTypeVersion",
				"Operation", "CouplingType", "OperatesOn", "Denominator",
				"OperatesOnIdentifier", "OperatesOnWithOpName" };
		for (String value : values) {
			domainType7.addValue(value);
		}
		DomainType domainType8 = operation.addNewConstraint();
		domainType8.setName("AdditionalQueryables");
		domainType8.addValue("HierarchyLevelName");

		// GetRecordById
		Operation getRecordIdOperation = omMetadata.addNewOperation();
		getRecordIdOperation.setName("GetRecordById");
		DCP dcpgetRecordById1 = getRecordIdOperation.addNewDCP();
		dcpgetRecordById1.addNewHTTP().addNewPost().setHref(getRecordByIdpath);
		DomainType dtGetRecordById = getRecordIdOperation.addNewParameter();
		dtGetRecordById.setName("ElementSetName");
		dtGetRecordById.addValue("brief");
		dtGetRecordById.addValue("summary");
		dtGetRecordById.addValue("full");

		// describeRecord
		// Operation describeRecordOperation = omMetadata.addNewOperation();
		// describeRecordOperation.setName("DescribeRecord");
		// describeRecordOperation.addNewDCP().addNewHTTP().addNewPost().setHref(
		// describeRecordPath);
		// getDomain
		// Operation GetDomainOperation = omMetadata.addNewOperation();
		// GetDomainOperation.setName("GetDomain");
		// GetDomainOperation.addNewDCP().addNewHTTP().addNewPost().setHref(
		// getDomainPath);
		// Transaction
		Operation TransactionOperation = omMetadata.addNewOperation();
		TransactionOperation.setName("Transaction");
		TransactionOperation.addNewDCP().addNewHTTP().addNewPost().setHref(
				transactionPath);
		// Harvest
		// Operation HarvestOperation = omMetadata.addNewOperation();
		// HarvestOperation.setName("Harvest");
		// HarvestOperation.addNewDCP().addNewHTTP().addNewPost().setHref(
		// harvestPath);
		return omMetadata;
	}

	/**
	 * 生成对应的ServiceProviderType的xml文档的内容，并返回 serviceProviderType内容
	 * 
	 * @param rpgId
	 *            需要查询的RegistryPackage的id值
	 * @return 返回生成的serviceProviderType元素
	 */
	public ServiceProvider GetServiceProviderTypeContent() {
		ServiceProvider sprovider = ServiceProviderDocument.Factory
				.newInstance().addNewServiceProvider();
		ResponsiblePartySubsetType scontact = sprovider.addNewServiceContact();
		scontact.setIndividualName("杨训亮");
		ContactType contactType = scontact.addNewContactInfo();
		AddressType attType = contactType.addNewAddress();
		attType.setCity("武汉市");
		attType.setCountry("中国");
		attType.setPostalCode("430079");
		sprovider.setProviderName("武汉大学测绘遥感信息工程国家重点实验室");

		return sprovider;
	}

	/**
	 * 用于 生成和获取GetCapabilitiesResponseDocument的文档的String信息
	 * 
	 * @param rpgId
	 *            需要查询的RegistryPacakge的id
	 * @return <b>返回需要的GetCapabilitiesRsponseDocument的文档String信息</b>
	 */
	public String CreateGetCapabilitiesResponseDocument() {
		String getCapabilitesDocument = null;
		CapabilitiesDocument cdDocument = CapabilitiesDocument.Factory
				.newInstance();
		CapabilitiesType ctType = cdDocument.addNewCapabilities();
		ctType.setVersion("2.0.2");

		ServiceIdentification siIdentification = ctType
				.addNewServiceIdentification();
		siIdentification = getServiceIdentification();
		ctType.setServiceIdentification(siIdentification);

		ServiceProvider sprovider = ctType.addNewServiceProvider();
		sprovider = GetServiceProviderTypeContent();
		ctType.setServiceProvider(sprovider);

		OperationsMetadata omMetadata = ctType.addNewOperationsMetadata();
		omMetadata = getOperationsMetadata();
		ctType.setOperationsMetadata(omMetadata);

		FilterCapabilities filterCapabilities = ctType
				.addNewFilterCapabilities();
		filterCapabilities = GetFilterCapabilities();
		ctType.setFilterCapabilities(filterCapabilities);

		getCapabilitesDocument = cdDocument.xmlText();
		// System.out.println(getCapabilitesDocument.toString());
		return getCapabilitesDocument;
	}

}
