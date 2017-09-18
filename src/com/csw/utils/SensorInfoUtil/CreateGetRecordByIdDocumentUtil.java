package com.csw.utils.SensorInfoUtil;

import com.csw.utils.GetRecordsOperationUtil.GetRecordsOperation;
import com.ebrim.model.csw.ElementSetNameType;
import com.ebrim.model.csw.GetRecordByIdDocument;
import com.ebrim.model.csw.GetRecordByIdResponseDocument;
import com.ebrim.model.csw.GetRecordByIdType;
import com.ebrim.model.rim.AssociationDocument;
import com.ebrim.model.rim.AssociationType1;
import com.ebrim.model.rim.ClassificationNodeDocument;
import com.ebrim.model.rim.ClassificationNodeType;
import com.ebrim.model.rim.ExtrinsicObjectDocument;
import com.ebrim.model.rim.ExtrinsicObjectType;
import com.ebrim.model.rim.IdentifiableType;
import com.ebrim.model.rim.OrganizationDocument;
import com.ebrim.model.rim.OrganizationType;
import com.ebrim.model.rim.RegistryPackageDocument;
import com.ebrim.model.rim.RegistryPackageType;
import com.ebrim.model.rim.ServiceDocument;
import com.ebrim.model.rim.ServiceType;

public class CreateGetRecordByIdDocumentUtil {
	/**
	 * 根据SensorId来生成GetRecordByIdDocument内容
	 * 
	 * @param sensorid
	 *            需要生成GetRecordByIdDocument的sensor id
	 * @param elementSet
	 *            full,summary,brief
	 * @return
	 */
	public static GetRecordByIdDocument createGetRecordByIdDocumentMehtod(
			String sensorid, String elementSet) {
		GetRecordByIdDocument gRBDocument = GetRecordByIdDocument.Factory
				.newInstance();
		GetRecordByIdType grbt = gRBDocument.addNewGetRecordById();
		grbt.addId(sensorid);
		grbt.setVersion("2.0.2");
		grbt.setService("CSW");
		ElementSetNameType esnt = grbt.addNewElementSetName();
		esnt.setStringValue(elementSet);
		return gRBDocument;
	}

	/**
	 * 生成 getRecordByID的响应文档
	 * 
	 * @param recordId
	 *            需要返回getRecordByID的响应文档的id值
	 * @return 
	 *         返回getRecordById的Response的文档内容，空则返回不包含任何的RegistryPackage的getRecords的文档内容
	 */
	public static String CreateGetRecordByIdResponseDocumentMethod(
			String recordId) {
		GetRecordByIdResponseDocument grbiresponsedocument = GetRecordByIdResponseDocument.Factory
				.newInstance();
		grbiresponsedocument.addNewGetRecordByIdResponse();
		/* 生成需要的RegistryPackage的文档的内容 */
		String all = GetRegistryPackageContent(recordId);
		int num = grbiresponsedocument.xmlText().lastIndexOf("/>");
		String returnStr = grbiresponsedocument.xmlText().substring(0, num)
				+ ">" + all + grbiresponsedocument.xmlText().substring(num + 2)
				+ "</ns:GetRecordByIdResponse>";
		return returnStr;
	}

	public static String RegsitryPackageCoreContentMehotd(
			RegistryPackageType rpt) {
		String allcontent = null;
		String extrinsicocontent = "";
		String organizationcontent = "";
		String servicecontent = "";
		String Classificationcontent = "";
		String associationcontent = "";
		for (IdentifiableType iden : rpt.getRegistryObjectList()
				.getIdentifiableArray()) {
			if (iden.schemaType().getFullJavaName().equals(
					"com.ebrim.model.rim.ExtrinsicObjectType")) {
				ExtrinsicObjectDocument eoDocument = ExtrinsicObjectDocument.Factory
						.newInstance();
				ExtrinsicObjectType eotype = eoDocument.addNewExtrinsicObject();
				eotype = (ExtrinsicObjectType) iden;
				eoDocument.setExtrinsicObject(eotype);
				extrinsicocontent += eoDocument.xmlText();
			}
			if (iden.schemaType().getFullJavaName().equals(
					"com.ebrim.model.rim.ClassificationNodeType")) {
				ClassificationNodeDocument cndoment = ClassificationNodeDocument.Factory
						.newInstance();
				ClassificationNodeType cntype = cndoment
						.addNewClassificationNode();
				cntype = (ClassificationNodeType) iden;
				cndoment.setClassificationNode(cntype);
				Classificationcontent += cndoment.xmlText();
			}
			if (iden.schemaType().getFullJavaName().equals(
					"com.ebrim.model.rim.ServiceType")) {
				ServiceDocument sdoDocument = ServiceDocument.Factory
						.newInstance();
				ServiceType sType = sdoDocument.addNewService();
				sType = (ServiceType) iden;
				sdoDocument.setService(sType);
				servicecontent += sdoDocument.xmlText();
			}
			if (iden.schemaType().getFullJavaName().equals(
					"com.ebrim.model.rim.AssociationType1")) {
				AssociationDocument ass = AssociationDocument.Factory
						.newInstance();
				AssociationType1 at1 = ass.addNewAssociation();
				at1 = (AssociationType1) iden;
				ass.setAssociation(at1);
				associationcontent += ass.xmlText();
			}
			if (iden.schemaType().getFullJavaName().equals(
					"com.ebrim.model.rim.OrganizationType")) {
				OrganizationDocument organizationDocument = OrganizationDocument.Factory
						.newInstance();
				OrganizationType ottype = organizationDocument
						.addNewOrganization();
				ottype = (OrganizationType) iden;
				organizationDocument.setOrganization(ottype);
				organizationcontent += organizationDocument.xmlText();
			}
		}
		// 此处确保了是按照extrinsicobject，service，association，classification，organization的顺序生成文档内容
		allcontent = extrinsicocontent + servicecontent + associationcontent
				+ Classificationcontent + organizationcontent;
		return allcontent;
	}

	/**
	 * 根据RegistryPackage的id值获取器文档的内容
	 * 
	 * @param recordId
	 *            : 查询的registrypackage的id值
	 * @return 返回的RegistryPacakge的id值
	 */
	public static String GetRegistryPackageContent(String recordId) {
		RegistryPackageDocument rpDocument = RegistryPackageDocument.Factory
				.newInstance();
		RegistryPackageType rpt = rpDocument.addNewRegistryPackage();
		rpt = GetRecordsOperation.GetRegistryPackageTypeById(recordId, "full");
		rpDocument.setRegistryPackage(rpt);
		String allcontent = RegsitryPackageCoreContentMehotd(rpt);
		rpt.getRegistryObjectList().setIdentifiableArray(null);
		rpDocument.setRegistryPackage(rpt);
		String all = "";
		if (rpDocument.xmlText().contains("</urn:RegistryObjectList>")) {
			int nu = rpDocument.xmlText().indexOf("</urn:RegistryObjectList>");
			all = rpDocument.xmlText().substring(0, nu) + allcontent
					+ rpDocument.xmlText().substring(nu);
		} else {
			int nu = rpDocument.xmlText().lastIndexOf("/>");
			all = rpDocument.xmlText().substring(0, nu) + ">" + allcontent
					+ "  </urn:RegistryObjectList></urn:RegistryPackage>";

		}
		return all;
	}
}
