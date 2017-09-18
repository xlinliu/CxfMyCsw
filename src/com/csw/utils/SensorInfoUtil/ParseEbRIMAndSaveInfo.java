package com.csw.utils.SensorInfoUtil;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import javax.xml.namespace.QName;
import net.opengis.gml.EnvelopeType;
import net.opengis.gml.PointType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.hibernate.Session;
import org.w3c.dom.NodeList;
import com.csw.model.ebXMLModel.Association;
import com.csw.model.ebXMLModel.Classification;
import com.csw.model.ebXMLModel.ClassificationNode;
import com.csw.model.ebXMLModel.EmailAddress;
import com.csw.model.ebXMLModel.ExternalIdentifier;
import com.csw.model.ebXMLModel.ExtrinsicObject;
import com.csw.model.ebXMLModel.Federation;
import com.csw.model.ebXMLModel.Identifiable;
import com.csw.model.ebXMLModel.InternationalString;
import com.csw.model.ebXMLModel.LocalizedString;
import com.csw.model.ebXMLModel.Notification;
import com.csw.model.ebXMLModel.ObjectRef;
import com.csw.model.ebXMLModel.Organization;
import com.csw.model.ebXMLModel.Person;
import com.csw.model.ebXMLModel.PersonName;
import com.csw.model.ebXMLModel.PostalAddress;
import com.csw.model.ebXMLModel.Registry;
import com.csw.model.ebXMLModel.RegistryPackage;
import com.csw.model.ebXMLModel.Service;
import com.csw.model.ebXMLModel.ServiceBinding;
import com.csw.model.ebXMLModel.Slot;
import com.csw.model.ebXMLModel.SpecificationLink;
import com.csw.model.ebXMLModel.TelephoneNumber;
import com.csw.model.ebXMLModel.User;
import com.csw.model.ebXMLModel.VersionInfo;
import com.csw.utils.GetSessionUtil;
import com.ebrim.model.rim.AssociationType1;
import com.ebrim.model.rim.ClassificationNodeType;
import com.ebrim.model.rim.ClassificationType;
import com.ebrim.model.rim.EmailAddressType;
import com.ebrim.model.rim.ExternalIdentifierType;
import com.ebrim.model.rim.ExtrinsicObjectType;
import com.ebrim.model.rim.FederationType;
import com.ebrim.model.rim.IdentifiableType;
import com.ebrim.model.rim.InternationalStringDocument;
import com.ebrim.model.rim.InternationalStringType;
import com.ebrim.model.rim.LocalizedStringDocument;
import com.ebrim.model.rim.LocalizedStringType;
import com.ebrim.model.rim.NotificationType;
import com.ebrim.model.rim.ObjectRefType;
import com.ebrim.model.rim.OrganizationType;
import com.ebrim.model.rim.PersonNameType;
import com.ebrim.model.rim.PersonType;
import com.ebrim.model.rim.PostalAddressType;
import com.ebrim.model.rim.RegistryPackageDocument;
import com.ebrim.model.rim.RegistryPackageType;
import com.ebrim.model.rim.RegistryType;
import com.ebrim.model.rim.ServiceBindingType;
import com.ebrim.model.rim.ServiceType;
import com.ebrim.model.rim.SlotType1;
import com.ebrim.model.rim.SpecificationLinkType;
import com.ebrim.model.rim.TelephoneNumberType;
import com.ebrim.model.rim.UserType;
import com.ebrim.model.rim.VersionInfoType;
import com.ebrim.model.wrs.ValueListType;

public class ParseEbRIMAndSaveInfo {
	/**
	 * �����ĵ�������xml�ĵ�
	 * 
	 * @param content
	 * @param ower
	 * @param leibei
	 * @return
	 */
	public boolean ParseAndSaveXMLDocumentByContent(String content,
			String ower, String leibei) {
		boolean bol = false;
		if (content == null) {
			return bol;
		}
		content = content.toString().trim();
		/*
		 * �������ݿ�����ĳ������
		 */
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		RegistryPackage registryPackage = new RegistryPackage();
		try {
			RegistryPackageDocument rpd = RegistryPackageDocument.Factory
					.parse(content);
			/*
			 * ��ø�Ԫ��RegistryPackage����
			 */
			RegistryPackageType rpt = rpd.getRegistryPackage();
			// ȷ�����������ݿ��RegistryPacakge��idֵ��Ψһֵ
			if (!OperateSensorUtil.CheckSensorIdIsExistMethod(rpt.getId()
					.trim())) {
				ParseRegistryPackageType(rpt, ower, session, registryPackage);
				session.save(registryPackage);
			}
			bol = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		}
		return bol;
	}

	/**
	 * �÷���Ϊ�ܵķ��������ڽ����ĵ�����ȡ��Ԫ��RegistryPackage�����ҽ���RegistryPackage
	 * 
	 * @param file
	 *            XML�ĵ�����
	 * @param ower
	 *            content��ӵ����
	 * @return ������������RegistryPackage
	 */
	public RegistryPackage parseAndSaveXMLDocumentByContent(String content,
			String ower) {
		content = content.toString().trim();
		RegistryPackage registryPackage = new RegistryPackage();
		try {
			RegistryPackageDocument rpd = RegistryPackageDocument.Factory
					.parse(content);
			/*
			 * ��ø�Ԫ��RegistryPackage����
			 */
			RegistryPackageType rpt = rpd.getRegistryPackage();
			// ȷ�����������ݿ��RegistryPacakge��idֵ��Ψһֵ
			if (!OperateSensorUtil.CheckSensorIdIsExistMethod(rpt.getId()
					.trim())) {
				Session session = GetSessionUtil
						.GetSessionInstance(GetSessionUtil.SENSORTYPE);
				session.beginTransaction().begin();
				ParseRegistryPackageType(rpt, ower, session, registryPackage);
				session.save(registryPackage);
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstance(session);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registryPackage;
	}

	/**
	 * �÷���Ϊ�ܵķ��������ڽ����ĵ�����ȡ��Ԫ��RegistryPackage�����ҽ���RegistryPackage
	 * 
	 * @param file
	 *            XML�ĵ�
	 * @return ������������RegistryPackage
	 */
	public RegistryPackage ParseXMLDocumentByFile(File file, String ower) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		RegistryPackage registryPackage = new RegistryPackage();
		try {
			RegistryPackageDocument rpd = RegistryPackageDocument.Factory
					.parse(file);
			RegistryPackageType rpt = rpd.getRegistryPackage();
			ParseRegistryPackageType(rpt, ower, session, registryPackage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		session.save(registryPackage);
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		return registryPackage;
	}

	/**
	 * �÷���Ϊ�ܵķ��������ڽ����ĵ�����ȡ��Ԫ��RegistryPackage�����ҽ���RegistryPackage
	 * 
	 * @param filepath
	 *            XML�ĵ� ��·��
	 * @return ������������RegistryPackage
	 */
	public RegistryPackage ParseXMLDocument(String filepath, String ower) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		RegistryPackage registryPackage = new RegistryPackage();
		try {
			RegistryPackageDocument rpd = RegistryPackageDocument.Factory
					.parse(new File(filepath));
			RegistryPackageType rpt = rpd.getRegistryPackage();
			ParseRegistryPackageType(rpt, ower, session, registryPackage);
			// ��ʾ����
			rpd = null;
			rpt = null;
			System.gc();
			session.save(registryPackage);
			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			GetSessionUtil.CloseSessionInstance(session);
		}
		return registryPackage;
	}

	/**
	 * ����Ԫ��RegistryPackage
	 * 
	 * @param rpt
	 * @return
	 */
	public void ParseRegistryPackageType(RegistryPackageType rpt, String ower,
			Session session, RegistryPackage registryPackage) {
		// ����RegistryPackage��id����
		if (rpt.getId() != null) {
			registryPackage.setId(rpt.getId());
		}
		// ����RegistryPackage��home����
		if (rpt.getHome() != null) {
			registryPackage.setHome(rpt.getHome());
		}
		// ����RegistryPackage��lid����
		if (rpt.getLid() != null) {
			registryPackage.setLid(rpt.getLid());
		}
		// ����registrypackage��ӵ����
		if (ower != null) {
			registryPackage.setOwer(ower);
		}
		// ����ReigstryPackage��name����
		if (rpt.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(rpt.getName(), rpt.getId() + ":Name",
					session, ins);
			registryPackage.setName(ins);
			ins = null;
		}
		// ����RegistryPackage��description����
		if (rpt.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(rpt.getDescription(), rpt.getId()
					+ ":Description", session, ins);
			registryPackage.setDescription(ins);
			ins = null;
		}
		// ����RegistryPackage��ObjectType����,�������id����objectRef��
		if (rpt.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(rpt.getId() + ":ObjectRef", rpt.getObjectType(),
					null, null, session, objectRef);
			registryPackage.setObjectType(objectRef);
			objectRef = null;
		}

		// ����RegistryPackage��ExternalIdentifier����
		if (rpt.getExternalIdentifierArray() != null) {
			if (rpt.getExternalIdentifierArray().length > 0) {
				for (ExternalIdentifierType eit : rpt
						.getExternalIdentifierArray()) {
					registryPackage.getExternalIdentifiers().add(
							ParseExternalIdentifierType(eit, session));

				}
			}
		}
		// ����RegistryPackage��VersionInfo����
		if (rpt.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(rpt.getVersionInfo(), rpt.getId(), session,
					vin);
			registryPackage.setVersionInfo(vin);
			vin = null;
		}
		// ����RegistryPackage��classifications����
		if (rpt.getClassificationArray() != null) {
			if (rpt.getClassificationArray().length > 0) {
				Classification cf = new Classification();
				for (ClassificationType clt : rpt.getClassificationArray()) {
					parseClassificationType(clt, session, cf);
					registryPackage.getClassifications().add(cf);
				}
				cf = null;
			}
		}
		// ����RegistryPackage��status����
		if (rpt.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(rpt.getId() + ":Status", rpt.getStatus(), null,
					null, session, objectRef);
			registryPackage.setStatus(objectRef);
			objectRef = null;
		}
		// ����RegistryPackage��Slots����
		if (rpt.getSlotArray() != null) {
			if (rpt.getSlotArray().length > 0) {
				int i = 1;
				for (SlotType1 slotType1 : rpt.getSlotArray()) {

					registryPackage.getSlots().add(
							parseSlotType(slotType1, rpt.getId()
									+ ":RegistryPackage:Slot:" + i, session));
					i++;
				}
			}
		}
		// ����RegistryPackage��Identifier���ԣ��������
		if (rpt.getRegistryObjectList() != null) {
			if (rpt.getRegistryObjectList().getIdentifiableArray() != null) {
				for (IdentifiableType ift : rpt.getRegistryObjectList()
						.getIdentifiableArray()) {
					// ����ExtrinsicObjectTypeImpl����
					if (ift.getClass().getName().equals(
							"com.ebrim.model.wrs.impl.ExtrinsicObjectTypeImpl")) {
						ExtrinsicObjectType eotType = (ExtrinsicObjectType) ift;
						/*
						 * ���ڴ������⣬��ʱ�������
						 */
						Identifiable idens = new Identifiable();
						ExtrinsicObject extrinsicObject = ParseExtrinsicObjectType(
								eotType, session);
						idens.setId(extrinsicObject.getId());
						/*
						 * ���ں���Ӳ�ͬ�ı��ж�ȡ����
						 */
						idens
								.setHome("com.ebrim.model.wrs.impl.ExtrinsicObjectTypeImpl");
						session.save(idens);
						registryPackage.getIdentifiables().add(idens);
						// registryPackage.getIdentifiables().add(
						// ParseExtrinsicObjectType(eotType, session));
					}
					// ����ClassificationNodeTypeImpl
					if (ift
							.getClass()
							.getName()
							.equals(
									"com.ebrim.model.rim.impl.ClassificationNodeTypeImpl")) {
						ClassificationNodeType cntType = (ClassificationNodeType) ift;
						Identifiable identifiable = new Identifiable();
						ClassificationNode idens = ParseClassificationNodeType(
								cntType, session);
						identifiable.setId(idens.getId());
						identifiable
								.setHome("com.ebrim.model.rim.impl.ClassificationNodeTypeImpl");
						session.save(idens);

						registryPackage.getIdentifiables().add(identifiable);
						// registryPackage.getIdentifiables().add(
						// ParseClassificationNodeType(cntType, session));

					}
					// ����AssociationTypeImpl
					if (ift.getClass().getName().equals(
							"com.ebrim.model.rim.impl.AssociationType1Impl")) {
						AssociationType1 at1 = (AssociationType1) ift;
						Identifiable ident = new Identifiable();
						Association association = ParseAssociationType(at1,
								session);
						ident.setId(association.getId());
						ident
								.setHome("com.ebrim.model.rim.impl.AssociationType1Impl");
						session.save(ident);
						registryPackage.getIdentifiables().add(ident);
						// registryPackage.getIdentifiables().add(
						// ParseAssociationType(at1, session));

					}
					// ����ServiceTypeImpl
					if (ift.getClass().getName().equals(
							"com.ebrim.model.rim.impl.ServiceTypeImpl")) {
						ServiceType serviceType = (ServiceType) ift;
						Identifiable identifiable = new Identifiable();
						Service service = ParseServiceType(serviceType, session);
						identifiable.setId(service.getId());
						identifiable
								.setHome("com.ebrim.model.rim.impl.ServiceTypeImpl");
						session.save(identifiable);
						registryPackage.getIdentifiables().add(identifiable);
						// registryPackage.getIdentifiables().add(
						// ParseServiceType(serviceType, session));
					}
					// ����OrganizationTypeImpl
					if (ift.getClass().getName().equals(
							"com.ebrim.model.rim.impl.OrganizationTypeImpl")) {
						OrganizationType organizationType = (OrganizationType) ift;
						Identifiable identifiable = new Identifiable();
						Organization organization = ParseOrganizationType(
								organizationType, session);
						identifiable.setId(organization.getId());
						identifiable
								.setHome("com.ebrim.model.rim.impl.OrganizationTypeImpl");
						session.save(identifiable);
						registryPackage.getIdentifiables().add(identifiable);
						// registryPackage.getIdentifiables()
						// .add(
						// ParseOrganizationType(organizationType,
						// session));
					}
					// ����PersonTypeImpl
					if (ift.getClass().getName().equals(
							"com.ebrim.model.rim.impl.PersonTypeImpl")) {
						PersonType pt = (PersonType) ift;
						Identifiable identifiable = new Identifiable();
						Person person = ParsePersonType(pt, session);

						identifiable.setId(person.getId());
						identifiable
								.setHome("com.ebrim.model.rim.impl.PersonTypeImpl");
						session.save(identifiable);
						registryPackage.getIdentifiables().add(identifiable);
						// registryPackage.getIdentifiables().add(
						// ParsePersonType(pt, session));

					}
					// ����FederationTypeImpl
					if (ift.getClass().getName().equals(
							"com.ebrim.model.rim.impl.FederationTypeImpl")) {
						FederationType ft = (FederationType) ift;
						Identifiable identifiable = new Identifiable();
						Federation federation = ParseFederationType(ft, session);
						identifiable.setId(federation.getId());
						identifiable
								.setHome("com.ebrim.model.rim.impl.FederationTypeImpl");
						session.save(identifiable);
						registryPackage.getIdentifiables().add(identifiable);
						// registryPackage.getIdentifiables().add(
						// ParseFederationType(ft, session));
					}
					// ����UserTypeImpl
					if (ift.getClass().getName().equals(
							"com.ebrim.model.rim.impl.UserTypeImpl")) {
						UserType userType = (UserType) ift;
						Identifiable identifiable = new Identifiable();
						User user = ParseUserType(userType, session);
						identifiable.setId(user.getId());
						identifiable
								.setHome("com.ebrim.model.rim.impl.UserTypeImpl");
						session.save(identifiable);
						registryPackage.getIdentifiables().add(identifiable);
						// registryPackage.getIdentifiables().add(
						// ParseUserType(userType, session));
					}
					// ����NotificationTypeImpl
					if (ift.getClass().getName().equals(
							"com.ebrim.model.rim.impl.NotificationTypeImpl")) {
						NotificationType nt = (NotificationType) ift;
						Identifiable identifiable = new Identifiable();
						Notification notification = new Notification();
						ParseNotificationType(nt, session, notification);
						identifiable.setId(notification.getId());
						identifiable
								.setHome("com.ebrim.model.rim.impl.NotificationTypeImpl");
						session.save(identifiable);
						registryPackage.getIdentifiables().add(identifiable);
						// registryPackage.getIdentifiables().add(
						// ParseNotificationType(nt, session));
					}
					// ����ObjectRefTypeImpl
					if (ift.getClass().getName().equals(
							"com.ebrim.model.rim.impl.ObjectRefTypeImpl")) {
						ObjectRefType ort = (ObjectRefType) ift;
						Identifiable identifiable = new Identifiable();
						ObjectRef objectRef = new ObjectRef();
						ParseObjectRefType(ort, session, objectRef);
						identifiable.setId(objectRef.getId());
						identifiable
								.setHome("com.ebrim.model.rim.impl.ObjectRefTypeImpl");

						session.save(identifiable);
						registryPackage.getIdentifiables().add(identifiable);
						// registryPackage.getIdentifiables().add(
						// ParseObjectRefType(ort, session));
					}
					// ����RegistryTypeImpl
					if (ift.getClass().getName().equals(
							"com.ebrim.model.rim.impl.RegistryTypeImpl")) {
						RegistryType rt = (RegistryType) ift;
						Identifiable identifiable = new Identifiable();
						Registry registry = new Registry();
						ParseRegistryType(rt, session, registry);
						identifiable.setId(registry.getId());
						identifiable
								.setHome("com.ebrim.model.rim.impl.RegistryTypeImpl");
						session.save(identifiable);
						registryPackage.getIdentifiables().add(identifiable);
						// registryPackage.getIdentifiables().add(
						// ParseRegistryType(rt, session));
					}
				}
			}
		}
		session.save(registryPackage);
	}

	/**
	 * ����registry����
	 * 
	 * @param rt
	 * @return
	 */
	public void ParseRegistryType(RegistryType ft, Session session, Registry f) {
		// ����classifications����
		if (ft.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType cft : ft.getClassificationArray()) {
				parseClassificationType(cft, session, cf);
				f.getClassifications().add(cf);
			}
		}
		// ����description����
		if (ft.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ft.getDescription(), ft.getId()
					+ ":Description", session, ins);
			f.setDescription(ins);
			ins = null;
		}
		// ����externalIdentifiers����
		if (ft.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eif : ft.getExternalIdentifierArray()) {
				f.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eif, session));
			}
		}
		// ����home����
		if (ft.getHome() != null) {
			f.setHome(ft.getHome());
		}
		// ����id����
		if (ft.getId() != null) {
			f.setId(ft.getId());
		}
		// ����lid����
		if (ft.getLid() != null) {
			f.setLid(ft.getLid());
		}
		// ����name����
		if (ft.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ft.getName(), ft.getId() + ":Name",
					session, ins);
			f.setName(ins);
			ins = null;
		}
		// ����objectType����
		if (ft.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":ObjectType", ft.getObjectType(),
					null, null, session, objectRef);
			f.setObjectType(objectRef);
			objectRef = null;
		}
		// ����slots����
		if (ft.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : ft.getSlotArray()) {

				f.getSlots().add(
						parseSlotType(st, ft.getId() + ":RegistryPackage:Slot:"
								+ i, session));
				i++;
			}
		}
		// ����status����
		if (ft.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":Status", ft.getStatus(), null,
					null, session, objectRef);
			f.setStatus(objectRef);
			objectRef = null;
		}
		// ����versionInfo����
		if (ft.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(ft.getVersionInfo(), ft.getId(), session, vin);
			f.setVersionInfo(vin);
			vin = null;
		}
		// ����cataloginLatency����
		if (ft.getCatalogingLatency() != null) {
			f.setCatalogingLatency(ft.getCatalogingLatency().toString());
		}
		// ����conformanceProfil����
		if (ft.getConformanceProfile() != null) {
			f.setConformanceProfile(ft.getConformanceProfile().toString());
		}
		// ����operator����
		if (ft.getOperator() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":Operator", ft.getOperator(), null,
					null, session, objectRef);
			f.setOperator(objectRef);
			objectRef = null;
		}
		// ����replicationSynLatency����
		if (ft.getReplicationSyncLatency() != null) {
			f.setReplicationSynLatency(ft.getReplicationSyncLatency()
					.toString());
		}
		// ����specificationVersion����
		if (ft.getSpecificationVersion() != null) {
			f.setSpecificationVersion(ft.getSpecificationVersion());
		}
		/*
		 * ����Registry����
		 */
		session.save(f);
	}

	/**
	 * ����objectRef����
	 * 
	 * @param ort
	 * @return
	 */
	public void ParseObjectRefType(ObjectRefType ort, Session session,
			ObjectRef obr) {
		// ����id����
		if (ort.getId() != null) {
			obr.setId(ort.getId());
		}
		// ����home����
		if (ort.getHome() != null) {
			obr.setHome(ort.getHome());
		}
		// ����slots����
		if (ort.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : ort.getSlotArray()) {

				obr.getSlots().add(
						parseSlotType(st, ort.getId() + ":ObjectRef:Slot:" + i,
								session));
				i++;
			}
		}
		obr.setCreateReplica(ort.getCreateReplica());
		/*
		 * ����ObjectRef
		 */
		session.save(obr);
	}

	/**
	 * ����NotificationType����
	 * 
	 * @param ntType
	 * @return
	 */
	public void ParseNotificationType(NotificationType ft, Session session,
			Notification f) {
		// ����classifications����
		if (ft.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType cft : ft.getClassificationArray()) {
				parseClassificationType(cft, session, cf);
				f.getClassifications().add(cf);
			}
			cf = null;
		}
		// ����description����
		if (ft.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ft.getDescription(), ft.getId()
					+ ":Description", session, ins);
			f.setDescription(ins);
			ins = null;
		}
		// ����externalIdentifiers����
		if (ft.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eif : ft.getExternalIdentifierArray()) {
				f.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eif, session));
			}
		}
		// ����home����
		if (ft.getHome() != null) {
			f.setHome(ft.getHome());
		}
		// ����id����
		if (ft.getId() != null) {
			f.setId(ft.getId());
		}
		// ����lid����
		if (ft.getLid() != null) {
			f.setLid(ft.getLid());
		}
		// ����name����
		if (ft.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ft.getName(), ft.getId() + ":Name",
					session, ins);
			f.setName(ins);
			ins = null;
		}
		// ����objectType����
		if (ft.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":ObjectType", ft.getObjectType(),
					null, null, session, objectRef);
			f.setObjectType(objectRef);
			objectRef = null;
		}
		// ����slots����
		if (ft.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : ft.getSlotArray()) {

				f.getSlots().add(
						parseSlotType(st, ft.getId() + ":Notification:Slot:"
								+ i, session));
				i++;
			}
		}
		// ����status����
		if (ft.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":Status", ft.getStatus(), null,
					null, session, objectRef);
			f.setStatus(objectRef);
			objectRef = null;
		}
		// ����versionInfo����
		if (ft.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(ft.getVersionInfo(), ft.getId(), session, vin);
			f.setVersionInfo(vin);
			vin = null;
		}
		// ����registryobjectList����
		if (ft.getRegistryObjectList() != null) {
			for (IdentifiableType objectType : ft.getRegistryObjectList()
					.getIdentifiableArray()) {
				f.getRegistryObjectList().add(
						ParseIdentifiableType(objectType, session));
			}
		}
		// ����subscription����
		if (ft.getSubscription() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":Subscription",
					ft.getSubscription(), null, null, session, objectRef);
			f.setSubscription(objectRef);
			objectRef = null;
		}
		/*
		 * ����Notification ����
		 */
		session.save(f);
	}

	public Identifiable ParseIdentifiableType(IdentifiableType it,
			Session session) {
		Identifiable ifable = new Identifiable();
		if (it.getClass().getName().equals(
				"com.ebrim.model.wrs.impl.ExtrinsicObjectTypeImpl")) {
			ifable = ParseExtrinsicObjectType((ExtrinsicObjectType) it, session);
		}
		session.save(ifable);
		return ifable;
	}

	/**
	 * ����UserType����(��person���ƣ�
	 * 
	 * @param userType
	 * 
	 * @return
	 */
	public User ParseUserType(UserType pt, Session session) {
		User person = new User();
		// ����Addresses����
		if (pt.getAddressArray() != null) {
			if (pt.getAddressArray().length > 0) {
				for (PostalAddressType postalAddress : pt.getAddressArray()) {
					person.getAddresses().add(
							ParsePostalAddressType(postalAddress, pt.getId(),
									session));
				}
			}
		}
		// ����classifications����
		if (pt.getClassificationArray() != null) {
			if (pt.getAddressArray().length > 0) {
				Classification cf = new Classification();
				for (ClassificationType ct : pt.getClassificationArray()) {
					parseClassificationType(ct, session, cf);
					person.getClassifications().add(cf);
				}
				cf = null;
			}
		}
		// ����Description����
		if (pt.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(pt.getDescription(), pt.getId()
					+ ":Description", session, ins);
			person.setDescription(ins);
			ins = null;
		}
		// ����EmailAddressArray����
		if (pt.getEmailAddressArray() != null) {
			if (pt.getEmailAddressArray().length > 0) {
				for (EmailAddressType eat : pt.getEmailAddressArray()) {
					person.getEmailAddresses().add(
							ParseEmailAddressType(eat, pt.getId(), session));
				}
			}
		}
		// ����Home����
		if (pt.getHome() != null) {
			person.setHome(pt.getHome());
		}
		// ����Id����
		if (pt.getId() != null) {
			person.setId(pt.getId());
		}
		// ����Lid����
		if (pt.getLid() != null) {
			person.setLid(pt.getLid());
		}
		// ����name����
		if (pt.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(pt.getName(), pt.getId() + ":Name",
					session, ins);
			person.setName(ins);
			ins = null;
		}
		// ����ObjectType����,��������id���ݸ�objectRef��Idʹ��
		if (pt.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(pt.getId() + ":ObjectType", pt.getObjectType(),
					null, null, session, objectRef);
			person.setObjectType(objectRef);
			objectRef = null;
		}
		// ����PersonName����,��Person��id���ݸ�PersonName��Ϊʹ��
		if (pt.getPersonName() != null) {
			person.setPersonName(ParsePersonNameType(pt.getPersonName(), pt
					.getId()));
		}
		// ����slots����
		if (pt.getSlotArray() != null) {
			if (pt.getSlotArray().length > 0) {
				int i = 1;
				for (SlotType1 st : pt.getSlotArray()) {

					person.getSlots().add(
							parseSlotType(st, pt.getId() + ":User:Slot:" + i,
									session));
					i++;
				}
			}
		}
		// ����status���ԣ���pt��id���ݸ�status�е�idʹ��
		if (pt.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(pt.getId() + ":Status", pt.getStatus(), null,
					null, session, objectRef);
			person.setStatus(objectRef);
			objectRef = null;
		}
		// ����telephoneNumber����
		if (pt.getTelephoneNumberArray() != null) {
			if (pt.getTelephoneNumberArray().length > 0) {
				for (TelephoneNumberType tnt : pt.getTelephoneNumberArray()) {
					person.getTelephoneNumbers().add(
							ParseTelephoneNumberType(tnt, pt.getId(), session));
				}
			}
		}
		/*
		 * ����User����
		 */
		session.save(person);
		return person;
	}

	/**
	 * ����federationType����
	 * 
	 * @param ft
	 * @return
	 */
	public Federation ParseFederationType(FederationType ft, Session session) {
		Federation f = new Federation();
		// ����classifications����
		if (ft.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType cft : ft.getClassificationArray()) {
				parseClassificationType(cft, session, cf);
				f.getClassifications().add(cf);
			}
			cf = null;
		}
		// ����description����
		if (ft.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ft.getDescription(), ft.getId()
					+ "Description", session, ins);
			f.setDescription(ins);
			ins = null;
		}
		// ����externalIdentifiers����
		if (ft.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eif : ft.getExternalIdentifierArray()) {
				f.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eif, session));
			}
		}
		// ����home����
		if (ft.getHome() != null) {
			f.setHome(ft.getHome());
		}
		// ����id����
		if (ft.getId() != null) {
			f.setId(ft.getId());
		}
		// ����lid����
		if (ft.getLid() != null) {
			f.setLid(ft.getLid());
		}
		// ����name����
		if (ft.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ft.getName(), ft.getId() + ":Name",
					session, ins);
			f.setName(ins);
			ins = null;
		}
		// ����objectType����
		if (ft.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":ObjectType", ft.getObjectType(),
					null, null, session, objectRef);
			f.setObjectType(objectRef);
			objectRef = null;
		}
		// ����slots����
		if (ft.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : ft.getSlotArray()) {

				f.getSlots().add(
						parseSlotType(st, ft.getId() + ":Feduration:Slot:" + i,
								session));
				i++;
			}
		}
		// ����status����
		if (ft.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":Status", ft.getStatus(), null,
					null, session, objectRef);
			f.setStatus(objectRef);
			objectRef = null;
		}
		// ����versionInfo����
		if (ft.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(ft.getVersionInfo(), ft.getId(), session, vin);
			f.setVersionInfo(vin);
			vin = null;
		}
		// ����replicationSynLatency����
		if (ft.getReplicationSyncLatency() != null) {
			f.setReplicationSyncLatency(ft.getReplicationSyncLatency()
					.toString());
		}
		/*
		 * ����Federation����
		 */
		session.save(f);
		return f;
	}

	/**
	 * ����PersonType����
	 * 
	 * @param pt
	 *            type:PersonType
	 * @return
	 */
	public Person ParsePersonType(PersonType pt, Session session) {
		Person person = new Person();
		// ����Addresses����
		if (pt.getAddressArray() != null) {
			for (PostalAddressType postalAddress : pt.getAddressArray()) {
				person.getAddresses().add(
						ParsePostalAddressType(postalAddress, pt.getId(),
								session));
			}
		}
		// ����classifications����
		if (pt.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType ct : pt.getClassificationArray()) {
				parseClassificationType(ct, session, cf);
				person.getClassifications().add(cf);
			}
			cf = null;
		}
		// ����Description����
		if (pt.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(pt.getDescription(), pt.getId()
					+ ":Description", session, ins);
			person.setDescription(ins);
			ins = null;
		}
		// ����EmailAddressArray����
		if (pt.getEmailAddressArray() != null) {
			for (EmailAddressType eat : pt.getEmailAddressArray()) {
				person.getEmailAddresses().add(
						ParseEmailAddressType(eat, pt.getId(), session));
			}
		}
		// ����home����
		if (pt.getHome() != null) {
			person.setHome(pt.getHome());
		}
		// ����id����
		if (pt.getId() != null) {
			person.setId(pt.getId());
		}
		// ����lid����
		if (pt.getLid() != null) {
			person.setLid(pt.getLid());
		}
		// ����name����
		if (pt.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(pt.getName(), pt.getId() + ":Name",
					session, ins);
			person.setName(ins);
			ins = null;
		}
		// ����ObjectType����
		if (pt.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(pt.getId() + ":ObjectType", pt.getObjectType(),
					null, null, session, objectRef);
			person.setObjectType(objectRef);
			objectRef = null;
		}
		// ����PersonName����
		if (pt.getPersonName() != null) {
			person.setPersonName(ParsePersonNameType(pt.getPersonName(), pt
					.getId()));
		}
		// ����slots����
		if (pt.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : pt.getSlotArray()) {

				person.getSlots().add(
						parseSlotType(st, pt.getId() + ":Person:Slot:" + i,
								session));
				i++;
			}
		}
		// ����status����
		if (pt.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(pt.getId() + ":ObjectRef", pt.getStatus(), null,
					null, session, objectRef);
			person.setStatus(objectRef);
			objectRef = null;
		}
		// ����telephoneNumber����
		if (pt.getTelephoneNumberArray() != null) {
			for (TelephoneNumberType tnt : pt.getTelephoneNumberArray()) {
				person.getTelephoneNumbers().add(
						ParseTelephoneNumberType(tnt, pt.getId(), session));
			}
		}
		/*
		 * ����person����
		 */
		session.save(person);
		return person;
	}

	/**
	 * ����TelephoneNumber
	 * 
	 * @param tnt
	 * @return
	 */
	public TelephoneNumber ParseTelephoneNumberType(TelephoneNumberType tnt,
			String id, Session session) {
		TelephoneNumber tn = new TelephoneNumber();
		// ����id������id
		if (id != null) {
			tn.setId(id);
		}
		// ����AreaCode��������
		if (tnt.getAreaCode() != null) {
			tn.setAreaCode(tnt.getAreaCode());
		}
		// ����CountryCode��������
		if (tnt.getCountryCode() != null) {
			tn.setCountryCode(tnt.getCountryCode());
		}
		// ����Extension��������
		if (tnt.getExtension() != null) {
			tn.setExtersion(tnt.getExtension());
		}
		// ����number��������
		if (tnt.getNumber() != null) {
			tn.setNumber(tnt.getNumber());
		}
		// ����PhoneType��������
		if (tnt.getPhoneType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(id, tnt.getPhoneType(), null, null, session,
					objectRef);
			tn.setPhoneType(objectRef);
			objectRef = null;
		}
		/*
		 * ����TelephoneNumber����
		 */
		session.save(tn);
		return tn;
	}

	/**
	 * ����PersonNameType����
	 * 
	 * @param pt
	 * @return
	 */
	public PersonName ParsePersonNameType(PersonNameType pt, String id) {
		PersonName pn = new PersonName();
		// ����id����
		if (id != null) {
			pn.setId(id);
		}
		// ����FirstName����
		if (pt.getFirstName() != null) {
			pn.setFirstName(pt.getFirstName());
		}
		// ����LastName����
		if (pt.getLastName() != null) {
			pn.setLastName(pt.getLastName());
		}
		// ����middleName����
		if (pt.getMiddleName() != null) {
			pn.setMiddleName(pt.getMiddleName());
		}
		return pn;
	}

	/**
	 * ����organizationType����
	 * 
	 * @param organizationType
	 * @return
	 */
	public Organization ParseOrganizationType(OrganizationType otType,
			Session session) {
		Organization organ = new Organization();

		// ����addresses����
		if (otType.getAddressArray() != null) {
			for (PostalAddressType addressType : otType.getAddressArray()) {
				organ.getAddresses().add(
						ParsePostalAddressType(addressType, otType.getId(),
								session));
			}
		}
		// ����classification����
		if (otType.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType cft : otType.getClassificationArray()) {

				parseClassificationType(cft, session, cf);
				organ.getClassifications().add(cf);
			}
			cf = null;
		}
		// ����description����
		if (otType.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(otType.getDescription(), otType.getId()
					+ ":Description", session, ins);
			organ.setDescription(ins);
			ins = null;
		}
		// ����EmailAddress����
		if (otType.getEmailAddressArray() != null) {
			for (EmailAddressType eat : otType.getEmailAddressArray()) {
				organ.getEmailAddresses().add(
						ParseEmailAddressType(eat, otType.getId(), session));
			}
		}
		// ����ExternalIdentifier����
		if (otType.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eit : otType
					.getExternalIdentifierArray()) {
				organ.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eit, session));
			}
		}
		// ����Home����
		if (otType.getHome() != null) {
			organ.setHome(otType.getHome());
		}
		// ����id����
		if (otType.getId() != null) {
			organ.setId(otType.getId());
		}
		// ����lid����
		if (otType.getLid() != null) {
			organ.setLid(otType.getLid());
		}
		// ����name����
		if (otType.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(otType.getName(),
					otType.getId() + ":Name", session, ins);
			organ.setName(ins);
			ins = null;
		}
		// ����objectType����
		if (otType.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(otType.getId() + ":ObjectType", otType
					.getObjectType(), null, null, session, objectRef);
			organ.setObjectType(objectRef);
			objectRef = null;
		}
		// ����parent����
		if (otType.getParent() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(otType.getId() + ":Parent", otType.getParent(),
					null, null, session, objectRef);
			organ.setParent(objectRef);
			objectRef = null;
		}
		// ����primaryContact����
		if (otType.getPrimaryContact() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(otType.getId() + ":PrimaryContact", otType
					.getPrimaryContact(), null, null, session, objectRef);
			organ.setPrimaryContact(objectRef);
			objectRef = null;
		}
		// ����slots����
		if (otType.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : otType.getSlotArray()) {

				organ.getSlots().add(
						parseSlotType(st, otType.getId() + ":Organization:Slot"
								+ i, session));
				i++;
			}
		}
		// ����status����
		if (otType.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(otType.getId() + ":Status", otType.getStatus(),
					null, null, session, objectRef);
			organ.setStatus(objectRef);
			objectRef = null;
		}
		// ����VersionInfo����
		if (otType.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(otType.getVersionInfo(), otType.getId(),
					session, vin);
			organ.setVersionInfo(vin);
			vin = null;
		}
		if (otType.getTelephoneNumberArray() != null) {
			for (TelephoneNumberType tnt : otType.getTelephoneNumberArray()) {
				organ.getTelephoneNumbers().add(
						ParseTelephoneNumberType(tnt, otType.getId()
								+ ":TelephoneNumber", session));
			}
		}
		/*
		 * �洢Organization����
		 */
		session.save(organ);
		return organ;
	}

	/**
	 * ����EmailAddressType����
	 * 
	 * @param et
	 * @param id
	 * @return
	 */
	public EmailAddress ParseEmailAddressType(EmailAddressType et, String id,
			Session session) {
		EmailAddress eas = new EmailAddress();
		// 1:����address����
		if (et.getAddress() != null) {
			// System.out.println(et.getAddress().trim());
			eas.setAddress(et.getAddress().toString().trim());
		}
		// 2������type����
		if (et.getType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(id, et.getType(), null, null, session, objectRef);
			eas.setType(objectRef);
			objectRef = null;
		}
		// 3:����id����
		if (id != null) {
			eas.setId(id);
		}
		/*
		 * ����EmailAddress����
		 */
		session.save(eas);
		return eas;

	}

	/**
	 * ����PostalAddress����
	 * 
	 * @param pat
	 * @return
	 */
	public PostalAddress ParsePostalAddressType(PostalAddressType pat,
			String id, Session session) {
		PostalAddress pa = new PostalAddress();
		// 1:����city����
		if (pat.getCity() != null) {
			pa.setCity(pat.getCity());
		}
		// 2:����country����
		if (pat.getCountry() != null) {
			pa.setCountry(pat.getCountry());
		}
		// 3������postalCode����
		if (pat.getPostalCode() != null) {
			pa.setPostalCode(pat.getPostalCode());
		}
		// 4������id����
		pa.setId(id);
		// 5������stateOrProvince����
		if (pat.getStateOrProvince() != null) {
			pa.setStateOrProvince(pat.getStateOrProvince());
		}
		// 6������street����
		if (pat.getStreet() != null) {
			pa.setStreet(pat.getStreet());
		}
		// 7:����streetNumber����
		if (pat.getStreetNumber() != null) {
			pa.setStreetNumber(pat.getStreetNumber());
		}
		/*
		 * ����postalAddress
		 */
		session.save(pa);
		return pa;
	}

	/**
	 * ����serviceType����
	 * 
	 * @param st
	 *            :��Ҫת����serviceType
	 * @return serice����
	 */
	public Service ParseServiceType(ServiceType st, Session session) {
		Service service = new Service();

		// ����id����
		if (st.getId() != null) {
			service.setId(st.getId());
		}
		// ����classifications����
		if (st.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType ct : st.getClassificationArray()) {
				parseClassificationType(ct, session, cf);
				service.getClassifications().add(cf);
			}
			cf = null;
		}
		// ����description����
		if (st.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(st.getDescription(), st.getId()
					+ ":Description", session, ins);
			service.setDescription(ins);
			ins = null;
		}
		// ����ExternalIdentifiers����
		if (st.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType sit : st.getExternalIdentifierArray()) {
				service.getExternalIdentifiers().add(
						ParseExternalIdentifierType(sit, session));
			}
		}
		// ����home����
		if (st.getHome() != null) {
			service.setHome(st.getHome());
		}
		// ����lid����
		if (st.getLid() != null) {
			service.setLid(st.getLid());
		}
		// ����name����
		if (st.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(st.getName(), st.getId() + ":Name",
					session, ins);
			service.setName(ins);
			ins = null;
		}
		// ����objectType����
		if (st.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(st.getId() + ":ObjectType", st.getObjectType(),
					null, null, session, objectRef);
			service.setObjectType(objectRef);
			objectRef = null;
		}
		// ������serviceBinding����
		if (st.getServiceBindingArray() != null) {
			for (ServiceBindingType sbt : st.getServiceBindingArray()) {
				service.getServiceBindings().add(
						ParseServiceBindingType(sbt, session));
			}
		}
		// ����slots����
		if (st.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 sm : st.getSlotArray()) {

				service.getSlots().add(
						parseSlotType(sm, st.getId() + ":Service:Slot" + i,
								session));
				i++;
			}
		}
		// ����status����
		if (st.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(st.getId() + ":Status", st.getStatus(), null,
					null, session, objectRef);
			service.setStatus(objectRef);
			objectRef = null;
		}
		// ����verionsInfo����
		if (st.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(st.getVersionInfo(), st.getId(), session, vin);
			service.setVersionInfo(vin);
			vin = null;
		}
		/*
		 * ����Service����
		 */
		session.save(service);
		return service;
	}

	/**
	 * ����serivceBindingType����
	 * 
	 * @param sbt
	 * @return
	 */
	public ServiceBinding ParseServiceBindingType(ServiceBindingType sbt,
			Session session) {
		ServiceBinding sb = new ServiceBinding();
		// ����accessURI����
		if (sbt.getAccessURI() != null) {
			sb.setAccessURI(sbt.getAccessURI());
		}
		// ����classifications����
		if (sbt.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType cft : sbt.getClassificationArray()) {
				parseClassificationType(cft, session, cf);
				sb.getClassifications().add(cf);
			}
			cf = null;
		}
		// ����description����
		if (sbt.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(sbt.getDescription(), sbt.getId()
					+ ":Description", session, ins);
			sb.setDescription(ins);
			ins = null;
		}
		// ����ExternalIdentifiers����
		if (sbt.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eit : sbt.getExternalIdentifierArray()) {
				sb.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eit, session));
			}
		}
		// ����Home����
		if (sbt.getHome() != null) {
			sb.setHome(sbt.getHome());
		}

		// ����id����
		if (sbt.getId() != null) {
			sb.setId(sbt.getId());
		}
		// ����lid����
		if (sbt.getLid() != null) {
			sb.setLid(sbt.getLid());
		}
		// ����name����
		if (sbt.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(sbt.getName(), sbt.getId() + ":Name",
					session, ins);
			sb.setName(ins);
			ins = null;
		}
		// ����objectType����
		if (sbt.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sbt.getId() + ":ObjectType", sbt.getObjectType(),
					null, null, session, objectRef);
			sb.setObjectType(objectRef);
			objectRef = null;
		}
		// ����Service����
		if (sbt.getService() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sbt.getId() + ":Service", sbt.getService(), null,
					null, session, objectRef);
			sb.setService(objectRef);
			objectRef = null;
		}
		// ����slots����
		if (sbt.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : sbt.getSlotArray()) {

				sb.getSlots().add(
						parseSlotType(st, sbt.getId() + ":ServiceBinding:Slot"
								+ i, session));
				i++;
			}
		}
		// ����specificationLinks����
		if (sbt.getSpecificationLinkArray() != null) {
			for (SpecificationLinkType sfla : sbt.getSpecificationLinkArray()) {
				sb.getSpecificationLinks().add(
						ParseSpecificationLinkType(sfla, session));
			}
		}
		// ����status����
		if (sbt.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sbt.getId() + ":Status", sbt.getStatus(), null,
					null, session, objectRef);
			sb.setStatus(objectRef);
			objectRef = null;
		}
		// ����targetBinding����
		if (sbt.getTargetBinding() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sbt.getId() + ":TaretBinding", sbt
					.getTargetBinding(), null, null, session, objectRef);
			sb.setTargetBinding(objectRef);
			objectRef = null;
		}
		// ����versionInfo����
		if (sbt.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(sbt.getVersionInfo(), sbt.getId(), session,
					vin);
			sb.setVersionInfo(vin);
			vin = null;
		}
		/*
		 * ���� ServiceBinding����
		 */
		session.save(sb);
		return sb;
	}

	/**
	 * ����SpecificationLink���ԣ�����������
	 * 
	 * @param sflaLinkType
	 * @return
	 */
	public SpecificationLink ParseSpecificationLinkType(
			SpecificationLinkType sflType, Session session) {
		SpecificationLink sfl = new SpecificationLink();
		// ����classification����
		if (sflType.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType sft : sflType.getClassificationArray()) {

				parseClassificationType(sft, session, cf);
				sfl.getClassifications().add(cf);
			}
			cf = null;
		}
		// ����Description����
		if (sflType.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(sflType.getDescription(), sflType.getId()
					+ ":Description", session, ins);
			sfl.setDescription(ins);
			ins = null;
		}
		// ����ExternalIdentifiers����
		if (sflType.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eif : sflType
					.getExternalIdentifierArray()) {
				sfl.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eif, session));
			}
		}
		// ����home����
		if (sflType.getHome() != null) {
			sfl.setHome(sflType.getHome());
		}
		// ����id����
		if (sflType.getId() != null) {
			sfl.setId(sflType.getId());
		}
		// ����lid����
		if (sflType.getLid() != null) {
			sfl.setLid(sflType.getLid());
		}
		// ����Name����
		if (sflType.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(sflType.getName(), sflType.getId()
					+ ":Name", session, ins);
			sfl.setName(ins);
			ins = null;
		}
		// ����ObjectType����
		if (sflType.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sflType.getId() + ":ObjectType", sflType
					.getObjectType(), null, null, session, objectRef);
			sfl.setObjectType(objectRef);
			objectRef = null;
		}
		// ����ServiceBinding����
		if (sflType.getServiceBinding() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sflType.getId() + ":ServiceBinding", sflType
					.getServiceBinding(), null, null, session, objectRef);
			sfl.setServiceBinding(objectRef);
			objectRef = null;
		}
		// ����slots����
		if (sflType.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : sflType.getSlotArray()) {

				sfl.getSlots().add(
						parseSlotType(st, sflType.getId()
								+ ":SpecificationLink:Slot" + i, session));
				i++;
			}
		}
		// ����specificationObject����
		if (sflType.getSpecificationObject() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sflType.getId() + ":SpecificationObject", sflType
					.getSpecificationObject(), null, null, session, objectRef);
			sfl.setSpecificationObject(objectRef);
			objectRef = null;
		}
		// ����status����
		if (sflType.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sflType.getId() + ":Status", sflType.getStatus(),
					null, null, session, objectRef);
			sfl.setStatus(objectRef);
			objectRef = null;
		}
		// ����usageDescription����
		if (sflType.getUsageDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(sflType.getUsageDescription(), sflType
					.getId()
					+ ":UsageDescription", session, ins);
			sfl.setUsageDescription(ins);
			ins = null;
		}
		// ����usageParameters����
		if (sflType.getUsageParameterArray() != null) {
			String allValu = "";
			for (String userparameter : sflType.getUsageParameterArray()) {

				allValu += userparameter + ",";
			}
			sfl.setUsageParameters(allValu);
		}
		// ����versionInfo����
		if (sflType.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(sflType.getVersionInfo(), sflType.getId(),
					session, vin);
			sfl.setVersionInfo(vin);
			vin = null;
		}
		session.save(sfl);
		return sfl;
	}

	/**
	 * ����associationType����
	 * 
	 * @param ast
	 * @return
	 */
	public Association ParseAssociationType(AssociationType1 ast,
			Session session) {
		Association ass = new Association();
		// ����Id����
		if (ast.getId() != null) {
			ass.setId(ast.getId());
		}
		// ����name����
		if (ast.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ast.getName(), ast.getId() + ":Name",
					session, ins);
			ass.setName(ins);
			ins = null;
		}
		// ����home����
		if (ast.getHome() != null) {
			ass.setHome(ast.getHome());
		}
		// ����slot����
		if (ast.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : ast.getSlotArray()) {

				ass.getSlots()
						.add(
								parseSlotType(st, ast.getId()
										+ ":Association:Slot" + i, session));
				i++;
			}
		}
		// ����AssociationType����
		if (ast.getAssociationType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ast.getId() + ":AssociationType", ast
					.getAssociationType(), null, null, session, objectRef);
			ass.setAssociationType(objectRef);
			objectRef = null;
		}
		// ����classifications����
		if (ast.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType cft : ast.getClassificationArray()) {
				parseClassificationType(cft, session, cf);
				ass.getClassifications().add(cf);
			}
			cf = null;
		}
		// ����Description����
		if (ast.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ast.getDescription(), ast.getId()
					+ ":Descrption", session, ins);
			ass.setDescription(ins);
			ins = null;
		}
		// ����Lid����
		if (ast.getLid() != null) {
			ass.setLid(ast.getLid());
		}
		// ������sourceObject����
		if (ast.getSourceObject() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ast.getId() + ":SourceObject", ast
					.getSourceObject(), null, null, session, objectRef);
			ass.setSourceObject(objectRef);
			objectRef = null;
		}
		// ������targetObject����
		if (ast.getTargetObject() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ast.getId() + ":TargetObject", ast
					.getTargetObject(), null, null, session, objectRef);
			ass.setTargetObject(objectRef);
			objectRef = null;
		}
		// ������VersionInfo����
		if (ast.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(ast.getVersionInfo(), ast.getId(), session,
					vin);
			ass.setVersionInfo(vin);
			vin = null;
		}
		// ������ObjectType����
		if (ast.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ast.getId() + ":ObjectType", ast.getObjectType(),
					null, null, session, objectRef);
			ass.setObjectType(objectRef);
			objectRef = null;
		}
		// ������ExternalIdentifier����
		if (ast.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eit : ast.getExternalIdentifierArray()) {
				ass.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eit, session));
			}
		}
		// ������status����
		if (ast.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ast.getId() + ":Status", ast.getStatus(), null,
					null, session, objectRef);
			ass.setStatus(objectRef);
			objectRef = null;
		}
		/*
		 * ���� Association����
		 */
		session.save(ass);
		return ass;

	}

	/**
	 * ����ClassificationNodeType �������Ա�����ClassificationNode��
	 * 
	 * @param cnt
	 * @return
	 */
	public ClassificationNode ParseClassificationNodeType(
			ClassificationNodeType cnt, Session session) {
		ClassificationNode cfn = new ClassificationNode();
		// ������ id����
		if (cnt.getId() != null) {
			cfn.setId(cnt.getId());
		}
		// ������home����
		if (cnt.getHome() != null) {
			cfn.setHome(cnt.getHome());
		}
		// ������Lid����
		if (cnt.getLid() != null) {
			cfn.setLid(cnt.getLid());
		}
		// ������Description����
		if (cnt.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(cnt.getDescription(), cnt.getId()
					+ ":ClassificationNode", session, ins);
			cfn.setDescription(ins);
			ins = null;
		}
		// ������ObjectType����
		if (cnt.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(cnt.getId() + ":ObjectType", cnt.getObjectType(),
					null, null, session, objectRef);
			cfn.setObjectType(objectRef);
			objectRef = null;
		}
		// ������parent����
		if (cnt.getParent() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(cnt.getId() + ":Parent", cnt.getParent(), null,
					null, session, objectRef);
			cfn.setParent(objectRef);
			objectRef = null;
		}
		// ������code����
		if (cnt.getCode() != null) {
			cfn.setCode(cnt.getCode());
		}
		// ������Path����
		if (cnt.getPath() != null) {
			cfn.setPath(cnt.getPath());
		}
		// ������Slot����
		if (cnt.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : cnt.getSlotArray()) {

				cfn.getSlots().add(
						parseSlotType(st, cnt.getId()
								+ ":ClassificationNode:Slot" + i, session));
				i++;
			}
		}
		// ������VersionInfo����
		if (cnt.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(cnt.getVersionInfo(), cnt.getId(), session,
					vin);
			cfn.setVersionInfo(vin);
			vin = null;
		}
		// ������ExternalIdentifier����
		if (cnt.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eit : cnt.getExternalIdentifierArray()) {
				cfn.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eit, session));
			}
		}
		// 12:������ classificationNode����
		if (cnt.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType ct : cnt.getClassificationArray()) {
				parseClassificationType(ct, session, cf);
				cfn.getClassifications().add(cf);
			}
			cf = null;
		}
		// ������status����
		if (cnt.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(cnt.getId() + ":Status", cnt.getStatus(), null,
					null, session, objectRef);
			cfn.setStatus(objectRef);
			objectRef = null;
		}
		// ������Name����
		if (cnt.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(cnt.getName(), cnt.getId() + ":Name",
					session, ins);
			cfn.setName(ins);
			ins = null;
		}
		/*
		 * ����ClassificationNode����
		 */
		session.save(cfn);
		return cfn;
	}

	/**
	 * ����ExtrinsicObjectType�������Ա�����ExtrisicObject��
	 * 
	 * @param eot
	 * @return
	 */
	public ExtrinsicObject ParseExtrinsicObjectType(ExtrinsicObjectType eot,
			Session session) {
		ExtrinsicObject exObject = new ExtrinsicObject();
		// ������ExtrinsicObject�е�id����
		if (eot.getId() != null) {
			exObject.setId(eot.getId());
		}
		// ������ExtrinsicObject�е�Lid����
		if (eot.getLid() != null) {
			exObject.setLid(eot.getLid());
		}
		// ������ExtrinsicObject�е�Home����
		if (eot.getHome() != null) {
			exObject.setHome(eot.getHome());
		}
		// ������ExtrinsicObject�е�Opaque����
		exObject.setIsOpaque(eot.getIsOpaque());

		// ������ExtrinsicObject�е�VersionInfo����
		if (eot.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(eot.getVersionInfo(), eot.getId(), session,
					vin);
			exObject.setVersionInfo(vin);
			vin = null;
		}
		// ������ExtrinsicObject�е�ObjecType����
		if (eot.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(eot.getId() + ":ObjectType", eot.getObjectType(),
					null, null, session, objectRef);
			exObject.setObjectType(objectRef);
			objectRef = null;
		}
		// ������ExtrinsicObject�е�Name����
		XmlCursor xmlCursor = eot.newCursor();
		Boolean bs = xmlCursor.toChild(new QName(
				"urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0", "Name", "rim"));
		if (bs) {
			try {
				LocalizedStringType los = LocalizedStringDocument.Factory
						.parse(xmlCursor.getObject().toString())
						.getLocalizedString();
				// System.out.println(eot.getId());
				LocalizedString ls = new LocalizedString();
				parseLocalizedStringType(los, eot.getId() + ":LName", session,
						ls);
				exObject.setLname(ls);
				ls = null;
			} catch (XmlException e) {
				try {
					InternationalStringType iStringType = InternationalStringDocument.Factory
							.parse(xmlCursor.getObject().toString())
							.getInternationalString();
					LocalizedStringType lst = iStringType
							.getLocalizedStringArray(0);
					LocalizedString ls = new LocalizedString();
					parseLocalizedStringType(lst, eot.getId() + ":LName",
							session, ls);
					exObject.setLname(ls);
					ls = null;

				} catch (XmlException e1) {
					e1.printStackTrace();
				}
			}
		}
		// TODO: handle exception

		// exObject.setName(parseInternationalString(eot.getName(), eot
		// .getId(), session));

		// ������ExtrinsicObject�е�MimeType����
		if (eot.getMimeType() != null) {
			exObject.setMimeType(eot.getMimeType());
		}
		// ������ExtrinsicObject�е�ExtrernalIdentifiers����
		if (eot.getExternalIdentifierArray() != null) {
			ExternalIdentifierType[] externalIdentifierTypes = eot
					.getExternalIdentifierArray();
			for (ExternalIdentifierType externalIdentifierType : externalIdentifierTypes) {
				exObject.getExternalIdentifiers().add(
						ParseExternalIdentifierType(externalIdentifierType,
								session));
			}
		}
		// ������ExtrinsicObject�е�Description����
		// if (eot.getDescription() != null) {
		// try {
		// exObject.setDescription(parseInternationalString(eot
		// .getDescription(), eot.getId() + ":Description",
		// session));
		// } catch (Exception e) {
		// try {
		// } catch (Exception e2) {
		// e2.printStackTrace();
		// }
		// }
		//
		// }
		// ������ExtrinsicObject�е�Classification����
		if (eot.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType clt : eot.getClassificationArray()) {

				parseClassificationType(clt, session, cf);
				exObject.getClassifications().add(cf);
			}
			cf = null;
		}
		// ������ExtrinsicObject�е�Slot����
		if (eot.getSlotArray() != null) {
			/*
			 * System.out.println("eot.getSlotArray():" +
			 * eot.getSlotArray().length);
			 */int i = 1;
			for (SlotType1 st : eot.getSlotArray()) {
				exObject.getSlots().add(
						parseSlotType(st, eot.getId() + ":ExtrinsicObject:Slot"
								+ i, session));
				i++;
			}
		}
		// ������ExtrisicObject�е�VersionInfo����
		if (eot.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(eot.getVersionInfo(), eot.getId(), session,
					vin);
			exObject.setVersionInfo(vin);
			vin = null;
		}
		// ������ExtrisicObject�е�status����
		if (eot.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(eot.getId() + ":Status", eot.getStatus(), null,
					null, session, objectRef);
			exObject.setStatus(objectRef);
			objectRef = null;
		}
		/*
		 * ����extrisicObject����
		 */
		session.save(exObject);
		return exObject;
	}

	/**
	 * ��������ClassificationType���ͣ��������������ݴ洢��classification����
	 * 
	 * @param clt
	 * @return
	 */
	public void parseClassificationType(ClassificationType clt,
			Session session, Classification cf) {
		if (clt != null) {
			// ������Id
			if (clt.getId() != null) {
				cf.setId(clt.getId());
			}
			// ������Lid
			if (clt.getLid() != null) {
				cf.setLid(clt.getLid());
			}
			// ����description
			if (clt.getDescription() != null) {
				InternationalString ins = new InternationalString();
				parseInternationalString(clt.getDescription(), clt.getId()
						+ ":Description", session, ins);
				cf.setDescription(ins);
				ins = null;
			}
			// ������home
			if (clt.getHome() != null) {
				cf.setHome(clt.getHome());
			}
			// ������name
			if (clt.getName() != null) {
				InternationalString ins = new InternationalString();
				parseInternationalString(clt.getName(), clt.getId() + ":Name",
						session, ins);
				cf.setName(ins);
				ins = null;
			}
			// ������classificationNode��ObjectRef���ͣ�
			if (clt.getClassificationNode() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(clt.getId() + ":ClassificationNode", clt
						.getClassificationNode(), null, null, session,
						objectRef);
				cf.setClassificationNode(objectRef);
				objectRef = null;
			}
			// ����status
			if (clt.getStatus() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(clt.getId() + ":Status", clt.getStatus(),
						null, null, session, objectRef);
				cf.setStatus(objectRef);
				objectRef = null;
			}
			// ����classifiedObject
			if (clt.getClassifiedObject() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(clt.getId() + ":ClassifiedObject", clt
						.getClassifiedObject(), null, null, session, objectRef);
				cf.setClassifiedObject(objectRef);
				objectRef = null;
			}
			// ����objectType����
			if (clt.getObjectType() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(clt.getId() + ":ObjectType", clt
						.getObjectType(), null, null, session, objectRef);
				cf.setObjectType(objectRef);
				objectRef = null;
			}
			// ����nodeRepresentationType����
			if (clt.getNodeRepresentation() != null) {
				cf.setNodeRepresentation(clt.getNodeRepresentation());
			}
			// ����VersionInfoType����
			if (clt.getVersionInfo() != null) {
				VersionInfo vin = new VersionInfo();
				ParseVersionInfoType(clt.getVersionInfo(), clt.getId(),
						session, vin);
				cf.setVersionInfo(vin);
				vin = null;
			}

			// ����classificationSchemeType����
			if (clt.getClassificationScheme() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(clt.getId() + ":ClassificationScheme", clt
						.getClassificationScheme(), null, null, session,
						objectRef);
				cf.setClassificationScheme(objectRef);
				objectRef = null;
			}
			// ����ExternalIdentifierType����
			if (clt.getExternalIdentifierArray() != null) {
				for (ExternalIdentifierType externalIdentifiertType : clt
						.getExternalIdentifierArray()) {
					cf.getExternalIdentifiers().add(
							ParseExternalIdentifierType(
									externalIdentifiertType, session));
				}
			}
			// ����slots����
			if (clt.getSlotArray() != null) {
				for (SlotType1 slotType1 : clt.getSlotArray()) {
					cf.getSlots().add(
							parseSlotType(slotType1, clt.getId(), session));
				}
			}
			/*
			 * ����classification����
			 */
			session.save(cf);
		}
	}

	/**
	 * ����slottype1����
	 * 
	 * @param slotType1
	 * @param id
	 * @return
	 */
	public Slot parseSlotType(SlotType1 slotType1, String id, Session session) {
		Slot slot = new Slot();
		// ����idֵ
		if (id != null) {
			slot.setId(id);
		}
		// ����nameֵ
		if (slotType1.getName() != null) {
			slot.setName(slotType1.getName());
		}
		// slotType���ͺܶ࣬��Ҫͳһ����,ͨ��SlotType���ֱ�Բ�ͬ��slot���н���
		if (slotType1.getSlotType() != null) {
			// ����SlotTypeֵ��������slot��
			slot.setSlotType(slotType1.getSlotType());

			String slotType = slotType1.getSlotType();
			if (slotType
					.equals("urn:oasis:names:tc:ebxml-regrep:DataType:DateTime")) {
				// ����String������
				// �ؼ��Ե�slotType��valueֵ
				String[] slotvalues = slotType1.getValueList().getValueArray();
				String allValue = "";
				// �Զ���","����
				for (String slotvalue : slotvalues) {

					allValue += slotvalue.trim() + ",";

				}
				slot.setValues(allValue);
			}
			// ��SlotTypeΪString���͵�ʱ��
			if (slotType
					.equals("urn:oasis:names:tc:ebxml-regrep:DataType:String")) {
				// �ؼ��Ե�slotType��valueֵ
				String[] slotvalues = slotType1.getValueList().getValueArray();
				String allValue = "";
				// �Զ���","����
				for (String slotvalue : slotvalues) {
					allValue += slotvalue.trim() + ",";
				}
				// System.out.println(allValue);
				/*
				 * ʵ������ <valuelist> <value>value1</value> <value> value2</value>
				 * </valuelist> ��������allvalueֵΪallValue="value1,value2";
				 */

				slot.setValues(allValue);
			}
			/*
			 * ������ʱ����xmlCursor��toChild(i)��ʱ�����ںܴ����⣬���ܻ�ȡ�Լ���Ҫ����������
			 */
			// ��SlotTypeΪGM_Envelope����
			if (slotType
					.equals("urn:ogc:def:dataType:ISO-19107:2003:GM_Envelope")) {
				String allValue = "";
				String lowervalue = "";
				String uppervalue = "";
				String sysStr = "";
				for (int i = 0; i < ((ValueListType) slotType1.getValueList())
						.sizeOfAnyValueArray(); i++) {
					/*
					 * xmlCursor=slotType1.getValueList().newCursor();���ܷ���forѭ��ǰ��
					 * �� ��Ϊһ��������ǰ�棬xmlCurosr.toChild(i)ֻ�ܷ���AnyValueһ��
					 */
					XmlCursor xmlCursor = slotType1.getValueList().newCursor();
					xmlCursor.toChild(i);
					if (xmlCursor.getObject().getDomNode().hasChildNodes()) {
						NodeList nodelist = xmlCursor.getObject().getDomNode()
								.getChildNodes();
						for (int j = 0; j < nodelist.getLength(); j++) {
							sysStr = nodelist.item(j).getAttributes()
									.getNamedItem("srsName").getNodeValue();
							NodeList concerlist = nodelist.item(j)
									.getChildNodes();
							for (int k = 0; k < concerlist.getLength(); k++) {
								if (concerlist.item(k).getNodeName()
										.toLowerCase().indexOf("lower") >= 0) {
									lowervalue = concerlist.item(k)
											.getFirstChild().getNodeValue();
								}
								if (concerlist.item(k).getNodeName()
										.toLowerCase().indexOf("upper") >= 0) {
									uppervalue = concerlist.item(k)
											.getFirstChild().getNodeValue();
								}
							}
						}
					}

				}
				allValue = "[" + sysStr + "," + lowervalue + "," + uppervalue
						+ ",]";
				slot.setValues(allValue);
			}
			if (slotType.equals("urn:ogc:def:dataType:ISO-19107:2003:GM_Point")) {

				String allvalue = "";
				String sysStr = "";
				String locationStr = "";
				for (int i = 0; i < ((ValueListType) slotType1.getValueList())
						.sizeOfAnyValueArray(); i++) {
					/*
					 * xmlCursor=slotType1.getValueList().newCursor();���ܷ���forѭ��ǰ��
					 * �� ��Ϊһ��������ǰ�棬xmlCurosr.toChild(i)ֻ�ܷ���AnyValueһ��
					 */
					XmlCursor xmlCursor = slotType1.getValueList().newCursor();
					xmlCursor.toChild(i);
					if (xmlCursor.getObject().getDomNode().hasChildNodes()) {
						NodeList nodelist = xmlCursor.getObject().getDomNode()
								.getChildNodes();
						for (int j = 0; j < nodelist.getLength(); j++) {
							sysStr = nodelist.item(j).getAttributes()
									.getNamedItem("srsName").getNodeValue();
							NodeList concerlist = nodelist.item(j)
									.getChildNodes();
							for (int k = 0; k < concerlist.getLength(); k++) {
								if (concerlist.item(k).getNodeName()
										.toLowerCase().indexOf("pos") >= 0) {
									locationStr = concerlist.item(k)
											.getFirstChild().getNodeValue()
											.split(" ")[0]
											+ ","
											+ concerlist.item(k)
													.getFirstChild()
													.getNodeValue().split(" ")[1];
								}
							}
						}
					}
				}
				allvalue = "[" + sysStr + "," + locationStr + ",]";
				slot.setValues(allvalue);
			}

		} else {
			// ���������SlotType���ͣ�����XmlBeans���н���
			/*
			 * ����String���ͽ��н���
			 */
			// �ؼ��Ե�slotType��valueֵ
			String[] slotvalues = slotType1.getValueList().getValueArray();
			String allValue = "";
			// �Զ���","����
			for (String slotvalue : slotvalues) {
				allValue += slotvalue.trim() + ",";
			}
			slot.setValues(allValue);
		}
		/*
		 * ����slot����
		 */
		session.save(slot);
		return slot;
	}

	/**
	 * ��PointType�е��������Զ����������������е����԰���һ����˳�����Ϊ�ַ����洢
	 * 
	 * @param pot
	 * @return
	 */
	public String ParsePointType(PointType pot) {
		String allValue = "";
		// Point��������Ե�˳��ΪId,SrsName,pos.....
		// �����������������"null" �洢
		allValue = AddStringFun(allValue, pot.getId());
		allValue = AddStringFun(allValue, pot.getSrsName());
		allValue = AddStringFun(allValue, pot.getPos().getListValue()
				.toString());

		// ������������Ҫ�洢������

		return allValue;
	}

	// pointCswType
	public String pointCswType(com.csw.model.gml.PointType pot) {
		String allValue = "";
		// Point��������Ե�˳��ΪId,SrsName,pos.....
		// �����������������"null" �洢
		allValue = AddStringFun(allValue, pot.getId());
		allValue = AddStringFun(allValue, pot.getSrsName());
		allValue = AddStringFun(allValue, pot.getPos().getListValue()
				.toString());

		// ������������Ҫ�洢������

		return allValue;
	}

	/**
	 * ��envelopeType�е����е����Զ����������������е����԰���һ����˳��洢Ϊ���ַ������Դ洢
	 * 
	 * @param env
	 * @return
	 */
	public String ParseEnvelopeType(EnvelopeType et) {
		String allValue = "";
		// envelope���������˳��Ϊ"srsName,LowerCorner,UpperCorner..."
		// ��������ڵ���������"null"���
		allValue = AddStringFun(allValue, et.getSrsName());

		allValue = AddStringFun(allValue, et.getLowerCorner().getListValue()
				.toString());

		allValue = AddStringFun(allValue, et.getUpperCorner().getListValue()
				.toString());
		return allValue;
	}

	/**
	 * 
	 * @param et
	 * @return
	 */
	public String ParseCswEnvelopeType(com.csw.model.gml.EnvelopeType et) {
		String allValue = "";
		// envelope���������˳��Ϊ"srsName,LowerCorner,UpperCorner..."
		// ��������ڵ���������"null"���
		allValue = AddStringFun(allValue, et.getSrsName());

		allValue = AddStringFun(allValue, et.getLowerCorner().getListValue()
				.toString());

		allValue = AddStringFun(allValue, et.getUpperCorner().getListValue()
				.toString());
		return allValue;
	}

	/**
	 * �����е�String��������
	 * 
	 * @param str
	 * @param AllValue
	 * @return
	 */
	public String AddStringFun(String AllValue, String str) {
		if (str != null) {
			AllValue += str + ",";
		} else {
			AllValue += "null,";
		}
		return AllValue;
	}

	/**
	 * ����ExternalIdentifierType���ͣ����ҽ�������ת����ExternalIdentifier���͡�
	 * 
	 * @param eit
	 * @return
	 */
	public ExternalIdentifier ParseExternalIdentifierType(
			ExternalIdentifierType eit, Session session) {
		ExternalIdentifier eif = new ExternalIdentifier();
		if (eit != null) {
			// 1������externalIdentifierType����Id
			if (eit.getId() != null) {
				eif.setId(eit.getId());
			}
			// 2������externalIdentifierType����Lid;
			if (eit.getLid() != null) {
				eif.setLid(eit.getLid());
			}
			// 3������externalIdentifierType����home
			if (eit.getHome() != null) {
				eif.setHome(eit.getHome());
			}
			// 4������externalIdentifierType����ObjectRef
			if (eit.getObjectType() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(eit.getId() + ":ObjectType", eit
						.getObjectType(), null, null, session, objectRef);
				eif.setObjectType(objectRef);
				objectRef = null;
			}
			// 5������externalIdenfigierType����RegistryObject(ObjectRef����)
			if (eit.getRegistryObject() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(eit.getId() + ":RegistryObject", eit
						.getRegistryObject(), null, null, session, objectRef);
				eif.setRegistryObject(objectRef);
				objectRef = null;
			}

			// 6������externalIdentifierType����versionInfo
			if (eit.getVersionInfo() != null) {
				VersionInfo vin = new VersionInfo();
				ParseVersionInfoType(eit.getVersionInfo(), eit.getId(),
						session, vin);
				eif.setVersionInfo(vin);
				vin = null;
			}
			// 7������externalIdentifierType����Description
			if (eit.getDescription() != null) {
				InternationalString ins = new InternationalString();
				parseInternationalString(eit.getDescription(), eit.getId()
						+ ":Description", session, ins);
				eif.setDescription(ins);
				ins = null;
			}
			// 8������externalIdentifierType����IdentificationScheme
			if (eit.getIdentificationScheme() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(eit.getId() + ":IdentificationScheme", eit
						.getIdentificationScheme(), null, null, session,
						objectRef);
				eif.setIdentificationScheme(objectRef);
				objectRef = null;
			}
			// 9�� ����externalIdentifierType����value
			if (eit.getValue() != null) {
				eif.setValue(eit.getValue());
			}
			// 10: ����externalIdentifierType����Status
			if (eit.getStatus() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(eit.getId() + ":Status", eit.getStatus(),
						null, null, session, objectRef);
				eif.setStatus(objectRef);
				objectRef = null;
			}
			// 11:����externalIdentifierType����classifications
			if (eit.getClassificationArray() != null) {
				ClassificationType[] classificationTypes = eit
						.getClassificationArray();
				if (classificationTypes.length > 0) {
					Classification cf = new Classification();
					for (ClassificationType ct : classificationTypes) {
						parseClassificationType(ct, session, cf);
						eif.getClassifications().add(cf);
					}
					cf = null;
				}
			}
			// 12:����externalIdentifier����externalIdentifier(���ڵݹ�ĵ������⣩
			if (eit.getExternalIdentifierArray() != null) {
				if (eit.getExternalIdentifierArray().length > 0) {
					for (ExternalIdentifierType eiff : eit
							.getExternalIdentifierArray()) {
						eif.getExternalIdentifiers().add(
								ParseExternalIdentifierType(eiff, session));
					}
				}
			}
			// 13������externalIdentifier����slots
			if (eit.getSlotArray() != null) {
				if (eit.getSlotArray().length > 0) {
					for (SlotType1 slotType1 : eit.getSlotArray()) {
						eif.getSlots().add(
								parseSlotType(slotType1, eit.getId(), session));
					}
				}
			}
			// 14������externalIdentifier����Name
			if (eit.getName() != null) {
				InternationalString ins = new InternationalString();
				parseInternationalString(eit.getName(), session, ins);
				eif.setName(ins);
				ins = null;
			}
			/*
			 * ����externalIdentifier����
			 */
			session.save(eif);
		}
		return eif;
	}

	/**
	 * ����versioninfoType�������ڴ洢��ʱ����Ҫһ��id��id���ⲿ����
	 * 
	 * @param versionInfoType
	 * @param id
	 * @return ����versionInfo����
	 */
	public void ParseVersionInfoType(VersionInfoType versionInfoType,
			String id, Session session, VersionInfo versionInfo) {
		if (id != null) {
			versionInfo.setId(id);
		}
		if (versionInfoType != null) {
			if (versionInfoType.getVersionName() != null) {
				versionInfo.setVersionName(versionInfoType.getVersionName());
			}
			if (versionInfoType.getComment() != null) {
				versionInfo.setConmment(versionInfoType.getComment());
			}
		}
		/*
		 * ����versionInfo����
		 */
		session.save(versionInfo);
	}

	/**
	 * ����������Բ��ֶ����ڲ���ObjectRef�������֮��ΪString
	 * �����޷��������ݿ��У����´������⣬���������ģ����ı�String����������ɱ�����ObjectRef����
	 * 
	 * @param id
	 * @param home
	 * @param createReplica
	 * @param slots
	 * @return
	 */
	public void WrapperObjectRef(String id, String home, Boolean createReplica,
			Set<Slot> slots, Session session, ObjectRef objectRef) {
		if (id != null) {
			objectRef.setId(id);
		}
		if (home != null) {
			objectRef.setHome(home);
		}
		if (createReplica != null) {
			objectRef.setCreateReplica(createReplica);
		}
		if (slots != null) {
			objectRef.setSlots(slots);
		}
		/*
		 * ����slot����
		 */
		if (slots != null) {
			// ��set��ȡ�����е�set��Ԫ�أ�����������
			for (Slot slot : slots) {
				session.save(slot);
			}
		}
		/*
		 * ����objectRef����
		 */
		session.save(objectRef);
	}

	// �˺�����Ҫ�޸�
	/**
	 * ����internationalStringType���ͣ����Ҵ��뵽��Ӧ��internationString��
	 * ������Ҫ�޸�hibernate�еģ����������е�localizedString��id����һ���ģ������������⡣��Ҫ�޸ģ���������
	 * 
	 * @param name
	 * @return
	 */
	public void parseInternationalString(InternationalStringType name,
			Session session, InternationalString ins) {
		if (name != null) {
			Set<LocalizedString> localizedStrings = new HashSet<LocalizedString>();
			if (name.getLocalizedStringArray().length > 0) {
				LocalizedString lls = new LocalizedString();
				for (LocalizedStringType ls : name.getLocalizedStringArray()) {
					parseLocalizedStringType(ls, ins.getId(), session, lls);
					localizedStrings.add(lls);

				}
				lls = null;
				ins.setLocalizedStrings(localizedStrings);
			}
		}
		session.save(ins);
	}

	/**
	 * ����InternationalStringType���󣬲����浽���ݿ���
	 * 
	 * @param name
	 * @param id
	 * @param session
	 * @return
	 */
	public void parseInternationalString(InternationalStringType name,
			String id, Session session, InternationalString ins) {
		if (name != null) {
			Set<LocalizedString> localizedStrings = new HashSet<LocalizedString>();
			if (name.getLocalizedStringArray().length > 0) {
				LocalizedString lls = new LocalizedString();
				for (LocalizedStringType ls : name.getLocalizedStringArray()) {
					parseLocalizedStringType(ls, id, session, lls);
					localizedStrings.add(lls);
				}
				lls = null;
				ins.setLocalizedStrings(localizedStrings);
			}
		}
		ins.setId(id);
		session.save(ins);
	}

	/**
	 * ����localizedStringType���ͣ������뵽��Ӧ��localizedString��,
	 * ����localizedStringType�в��ṩID���ԣ���Ҫ�ⲿ����id����
	 * 
	 * @return
	 */
	public void parseLocalizedStringType(LocalizedStringType lst, String id,
			Session session, LocalizedString ls) {
		ls.setId(id);

		if (id != null) {
			ls.setId(id);
		}
		if (lst.getLang() != null) {
			ls.setLang(lst.getLang());
		}
		if (lst.getValue() != null) {
			ls.setValue(lst.getValue());
		}
		if (lst.getCharset().toString() != null) {
			ls.setCharset(lst.getCharset().getStringValue());
		}
		session.save(ls);
	}
}
