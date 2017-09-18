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
	 * 解析文档并保存xml文档
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
		 * 调用数据库操作的程序入口
		 */
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		RegistryPackage registryPackage = new RegistryPackage();
		try {
			RegistryPackageDocument rpd = RegistryPackageDocument.Factory
					.parse(content);
			/*
			 * 获得根元素RegistryPackage对象
			 */
			RegistryPackageType rpt = rpd.getRegistryPackage();
			// 确保解析的数据库的RegistryPacakge的id值是唯一值
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
	 * 该方法为总的方法，用于解析文档，获取根元素RegistryPackage，并且解析RegistryPackage
	 * 
	 * @param file
	 *            XML文档内容
	 * @param ower
	 *            content的拥有者
	 * @return 解析后的所获得RegistryPackage
	 */
	public RegistryPackage parseAndSaveXMLDocumentByContent(String content,
			String ower) {
		content = content.toString().trim();
		RegistryPackage registryPackage = new RegistryPackage();
		try {
			RegistryPackageDocument rpd = RegistryPackageDocument.Factory
					.parse(content);
			/*
			 * 获得根元素RegistryPackage对象
			 */
			RegistryPackageType rpt = rpd.getRegistryPackage();
			// 确保解析的数据库的RegistryPacakge的id值是唯一值
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
	 * 该方法为总的方法，用于解析文档，获取根元素RegistryPackage，并且解析RegistryPackage
	 * 
	 * @param file
	 *            XML文档
	 * @return 解析后的所获得RegistryPackage
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
	 * 该方法为总的方法，用于解析文档，获取根元素RegistryPackage，并且解析RegistryPackage
	 * 
	 * @param filepath
	 *            XML文档 的路径
	 * @return 解析后的所获得RegistryPackage
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
			// 显示回收
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
	 * 解析元素RegistryPackage
	 * 
	 * @param rpt
	 * @return
	 */
	public void ParseRegistryPackageType(RegistryPackageType rpt, String ower,
			Session session, RegistryPackage registryPackage) {
		// 解析RegistryPackage的id属性
		if (rpt.getId() != null) {
			registryPackage.setId(rpt.getId());
		}
		// 解析RegistryPackage的home属性
		if (rpt.getHome() != null) {
			registryPackage.setHome(rpt.getHome());
		}
		// 解析RegistryPackage的lid属性
		if (rpt.getLid() != null) {
			registryPackage.setLid(rpt.getLid());
		}
		// 设置registrypackage的拥有者
		if (ower != null) {
			registryPackage.setOwer(ower);
		}
		// 解析ReigstryPackage的name属性
		if (rpt.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(rpt.getName(), rpt.getId() + ":Name",
					session, ins);
			registryPackage.setName(ins);
			ins = null;
		}
		// 解析RegistryPackage的description属性
		if (rpt.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(rpt.getDescription(), rpt.getId()
					+ ":Description", session, ins);
			registryPackage.setDescription(ins);
			ins = null;
		}
		// 解析RegistryPackage的ObjectType属性,将父类的id赋给objectRef类
		if (rpt.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(rpt.getId() + ":ObjectRef", rpt.getObjectType(),
					null, null, session, objectRef);
			registryPackage.setObjectType(objectRef);
			objectRef = null;
		}

		// 解析RegistryPackage的ExternalIdentifier属性
		if (rpt.getExternalIdentifierArray() != null) {
			if (rpt.getExternalIdentifierArray().length > 0) {
				for (ExternalIdentifierType eit : rpt
						.getExternalIdentifierArray()) {
					registryPackage.getExternalIdentifiers().add(
							ParseExternalIdentifierType(eit, session));

				}
			}
		}
		// 解析RegistryPackage的VersionInfo属性
		if (rpt.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(rpt.getVersionInfo(), rpt.getId(), session,
					vin);
			registryPackage.setVersionInfo(vin);
			vin = null;
		}
		// 解析RegistryPackage的classifications属性
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
		// 解析RegistryPackage的status属性
		if (rpt.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(rpt.getId() + ":Status", rpt.getStatus(), null,
					null, session, objectRef);
			registryPackage.setStatus(objectRef);
			objectRef = null;
		}
		// 解析RegistryPackage的Slots属性
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
		// 解析RegistryPackage的Identifier属性：五种情况
		if (rpt.getRegistryObjectList() != null) {
			if (rpt.getRegistryObjectList().getIdentifiableArray() != null) {
				for (IdentifiableType ift : rpt.getRegistryObjectList()
						.getIdentifiableArray()) {
					// 解析ExtrinsicObjectTypeImpl类型
					if (ift.getClass().getName().equals(
							"com.ebrim.model.wrs.impl.ExtrinsicObjectTypeImpl")) {
						ExtrinsicObjectType eotType = (ExtrinsicObjectType) ift;
						/*
						 * 由于存在问题，暂时这样解决
						 */
						Identifiable idens = new Identifiable();
						ExtrinsicObject extrinsicObject = ParseExtrinsicObjectType(
								eotType, session);
						idens.setId(extrinsicObject.getId());
						/*
						 * 用于后面从不同的表中读取数据
						 */
						idens
								.setHome("com.ebrim.model.wrs.impl.ExtrinsicObjectTypeImpl");
						session.save(idens);
						registryPackage.getIdentifiables().add(idens);
						// registryPackage.getIdentifiables().add(
						// ParseExtrinsicObjectType(eotType, session));
					}
					// 解析ClassificationNodeTypeImpl
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
					// 解析AssociationTypeImpl
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
					// 解析ServiceTypeImpl
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
					// 解析OrganizationTypeImpl
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
					// 解析PersonTypeImpl
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
					// 解析FederationTypeImpl
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
					// 解析UserTypeImpl
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
					// 解析NotificationTypeImpl
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
					// 解析ObjectRefTypeImpl
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
					// 解析RegistryTypeImpl
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
	 * 解析registry对象
	 * 
	 * @param rt
	 * @return
	 */
	public void ParseRegistryType(RegistryType ft, Session session, Registry f) {
		// 解析classifications属性
		if (ft.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType cft : ft.getClassificationArray()) {
				parseClassificationType(cft, session, cf);
				f.getClassifications().add(cf);
			}
		}
		// 解析description属性
		if (ft.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ft.getDescription(), ft.getId()
					+ ":Description", session, ins);
			f.setDescription(ins);
			ins = null;
		}
		// 解析externalIdentifiers属性
		if (ft.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eif : ft.getExternalIdentifierArray()) {
				f.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eif, session));
			}
		}
		// 解析home属性
		if (ft.getHome() != null) {
			f.setHome(ft.getHome());
		}
		// 解析id属性
		if (ft.getId() != null) {
			f.setId(ft.getId());
		}
		// 解析lid属性
		if (ft.getLid() != null) {
			f.setLid(ft.getLid());
		}
		// 解析name属性
		if (ft.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ft.getName(), ft.getId() + ":Name",
					session, ins);
			f.setName(ins);
			ins = null;
		}
		// 解析objectType属性
		if (ft.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":ObjectType", ft.getObjectType(),
					null, null, session, objectRef);
			f.setObjectType(objectRef);
			objectRef = null;
		}
		// 解析slots属性
		if (ft.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : ft.getSlotArray()) {

				f.getSlots().add(
						parseSlotType(st, ft.getId() + ":RegistryPackage:Slot:"
								+ i, session));
				i++;
			}
		}
		// 解析status属性
		if (ft.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":Status", ft.getStatus(), null,
					null, session, objectRef);
			f.setStatus(objectRef);
			objectRef = null;
		}
		// 解析versionInfo属性
		if (ft.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(ft.getVersionInfo(), ft.getId(), session, vin);
			f.setVersionInfo(vin);
			vin = null;
		}
		// 解析cataloginLatency属性
		if (ft.getCatalogingLatency() != null) {
			f.setCatalogingLatency(ft.getCatalogingLatency().toString());
		}
		// 解析conformanceProfil属性
		if (ft.getConformanceProfile() != null) {
			f.setConformanceProfile(ft.getConformanceProfile().toString());
		}
		// 解析operator属性
		if (ft.getOperator() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":Operator", ft.getOperator(), null,
					null, session, objectRef);
			f.setOperator(objectRef);
			objectRef = null;
		}
		// 解析replicationSynLatency属性
		if (ft.getReplicationSyncLatency() != null) {
			f.setReplicationSynLatency(ft.getReplicationSyncLatency()
					.toString());
		}
		// 解析specificationVersion属性
		if (ft.getSpecificationVersion() != null) {
			f.setSpecificationVersion(ft.getSpecificationVersion());
		}
		/*
		 * 保存Registry对象
		 */
		session.save(f);
	}

	/**
	 * 解析objectRef对象
	 * 
	 * @param ort
	 * @return
	 */
	public void ParseObjectRefType(ObjectRefType ort, Session session,
			ObjectRef obr) {
		// 解析id属性
		if (ort.getId() != null) {
			obr.setId(ort.getId());
		}
		// 解析home属性
		if (ort.getHome() != null) {
			obr.setHome(ort.getHome());
		}
		// 解析slots属性
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
		 * 保存ObjectRef
		 */
		session.save(obr);
	}

	/**
	 * 解析NotificationType对象
	 * 
	 * @param ntType
	 * @return
	 */
	public void ParseNotificationType(NotificationType ft, Session session,
			Notification f) {
		// 解析classifications属性
		if (ft.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType cft : ft.getClassificationArray()) {
				parseClassificationType(cft, session, cf);
				f.getClassifications().add(cf);
			}
			cf = null;
		}
		// 解析description属性
		if (ft.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ft.getDescription(), ft.getId()
					+ ":Description", session, ins);
			f.setDescription(ins);
			ins = null;
		}
		// 解析externalIdentifiers属性
		if (ft.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eif : ft.getExternalIdentifierArray()) {
				f.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eif, session));
			}
		}
		// 解析home属性
		if (ft.getHome() != null) {
			f.setHome(ft.getHome());
		}
		// 解析id属性
		if (ft.getId() != null) {
			f.setId(ft.getId());
		}
		// 解析lid属性
		if (ft.getLid() != null) {
			f.setLid(ft.getLid());
		}
		// 解析name属性
		if (ft.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ft.getName(), ft.getId() + ":Name",
					session, ins);
			f.setName(ins);
			ins = null;
		}
		// 解析objectType属性
		if (ft.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":ObjectType", ft.getObjectType(),
					null, null, session, objectRef);
			f.setObjectType(objectRef);
			objectRef = null;
		}
		// 解析slots属性
		if (ft.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : ft.getSlotArray()) {

				f.getSlots().add(
						parseSlotType(st, ft.getId() + ":Notification:Slot:"
								+ i, session));
				i++;
			}
		}
		// 解析status属性
		if (ft.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":Status", ft.getStatus(), null,
					null, session, objectRef);
			f.setStatus(objectRef);
			objectRef = null;
		}
		// 解析versionInfo属性
		if (ft.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(ft.getVersionInfo(), ft.getId(), session, vin);
			f.setVersionInfo(vin);
			vin = null;
		}
		// 解析registryobjectList属性
		if (ft.getRegistryObjectList() != null) {
			for (IdentifiableType objectType : ft.getRegistryObjectList()
					.getIdentifiableArray()) {
				f.getRegistryObjectList().add(
						ParseIdentifiableType(objectType, session));
			}
		}
		// 解析subscription属性
		if (ft.getSubscription() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":Subscription",
					ft.getSubscription(), null, null, session, objectRef);
			f.setSubscription(objectRef);
			objectRef = null;
		}
		/*
		 * 保存Notification 对象
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
	 * 解析UserType对象(与person类似）
	 * 
	 * @param userType
	 * 
	 * @return
	 */
	public User ParseUserType(UserType pt, Session session) {
		User person = new User();
		// 解析Addresses属性
		if (pt.getAddressArray() != null) {
			if (pt.getAddressArray().length > 0) {
				for (PostalAddressType postalAddress : pt.getAddressArray()) {
					person.getAddresses().add(
							ParsePostalAddressType(postalAddress, pt.getId(),
									session));
				}
			}
		}
		// 解析classifications属性
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
		// 解析Description属性
		if (pt.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(pt.getDescription(), pt.getId()
					+ ":Description", session, ins);
			person.setDescription(ins);
			ins = null;
		}
		// 解析EmailAddressArray属性
		if (pt.getEmailAddressArray() != null) {
			if (pt.getEmailAddressArray().length > 0) {
				for (EmailAddressType eat : pt.getEmailAddressArray()) {
					person.getEmailAddresses().add(
							ParseEmailAddressType(eat, pt.getId(), session));
				}
			}
		}
		// 解析Home属性
		if (pt.getHome() != null) {
			person.setHome(pt.getHome());
		}
		// 解析Id属性
		if (pt.getId() != null) {
			person.setId(pt.getId());
		}
		// 解析Lid属性
		if (pt.getLid() != null) {
			person.setLid(pt.getLid());
		}
		// 解析name属性
		if (pt.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(pt.getName(), pt.getId() + ":Name",
					session, ins);
			person.setName(ins);
			ins = null;
		}
		// 解析ObjectType属性,将父级的id传递给objectRef的Id使用
		if (pt.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(pt.getId() + ":ObjectType", pt.getObjectType(),
					null, null, session, objectRef);
			person.setObjectType(objectRef);
			objectRef = null;
		}
		// 解析PersonName属性,将Person的id传递给PersonName作为使用
		if (pt.getPersonName() != null) {
			person.setPersonName(ParsePersonNameType(pt.getPersonName(), pt
					.getId()));
		}
		// 解析slots属性
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
		// 解析status属性，将pt的id传递给status中的id使用
		if (pt.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(pt.getId() + ":Status", pt.getStatus(), null,
					null, session, objectRef);
			person.setStatus(objectRef);
			objectRef = null;
		}
		// 解析telephoneNumber属性
		if (pt.getTelephoneNumberArray() != null) {
			if (pt.getTelephoneNumberArray().length > 0) {
				for (TelephoneNumberType tnt : pt.getTelephoneNumberArray()) {
					person.getTelephoneNumbers().add(
							ParseTelephoneNumberType(tnt, pt.getId(), session));
				}
			}
		}
		/*
		 * 返回User对象
		 */
		session.save(person);
		return person;
	}

	/**
	 * 解析federationType对象
	 * 
	 * @param ft
	 * @return
	 */
	public Federation ParseFederationType(FederationType ft, Session session) {
		Federation f = new Federation();
		// 解析classifications属性
		if (ft.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType cft : ft.getClassificationArray()) {
				parseClassificationType(cft, session, cf);
				f.getClassifications().add(cf);
			}
			cf = null;
		}
		// 解析description属性
		if (ft.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ft.getDescription(), ft.getId()
					+ "Description", session, ins);
			f.setDescription(ins);
			ins = null;
		}
		// 解析externalIdentifiers属性
		if (ft.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eif : ft.getExternalIdentifierArray()) {
				f.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eif, session));
			}
		}
		// 解析home属性
		if (ft.getHome() != null) {
			f.setHome(ft.getHome());
		}
		// 解析id属性
		if (ft.getId() != null) {
			f.setId(ft.getId());
		}
		// 解析lid属性
		if (ft.getLid() != null) {
			f.setLid(ft.getLid());
		}
		// 解析name属性
		if (ft.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ft.getName(), ft.getId() + ":Name",
					session, ins);
			f.setName(ins);
			ins = null;
		}
		// 解析objectType属性
		if (ft.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":ObjectType", ft.getObjectType(),
					null, null, session, objectRef);
			f.setObjectType(objectRef);
			objectRef = null;
		}
		// 解析slots属性
		if (ft.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : ft.getSlotArray()) {

				f.getSlots().add(
						parseSlotType(st, ft.getId() + ":Feduration:Slot:" + i,
								session));
				i++;
			}
		}
		// 解析status属性
		if (ft.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ft.getId() + ":Status", ft.getStatus(), null,
					null, session, objectRef);
			f.setStatus(objectRef);
			objectRef = null;
		}
		// 解析versionInfo属性
		if (ft.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(ft.getVersionInfo(), ft.getId(), session, vin);
			f.setVersionInfo(vin);
			vin = null;
		}
		// 解析replicationSynLatency属性
		if (ft.getReplicationSyncLatency() != null) {
			f.setReplicationSyncLatency(ft.getReplicationSyncLatency()
					.toString());
		}
		/*
		 * 保存Federation对象
		 */
		session.save(f);
		return f;
	}

	/**
	 * 解析PersonType对象
	 * 
	 * @param pt
	 *            type:PersonType
	 * @return
	 */
	public Person ParsePersonType(PersonType pt, Session session) {
		Person person = new Person();
		// 解析Addresses属性
		if (pt.getAddressArray() != null) {
			for (PostalAddressType postalAddress : pt.getAddressArray()) {
				person.getAddresses().add(
						ParsePostalAddressType(postalAddress, pt.getId(),
								session));
			}
		}
		// 解析classifications属性
		if (pt.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType ct : pt.getClassificationArray()) {
				parseClassificationType(ct, session, cf);
				person.getClassifications().add(cf);
			}
			cf = null;
		}
		// 解析Description属性
		if (pt.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(pt.getDescription(), pt.getId()
					+ ":Description", session, ins);
			person.setDescription(ins);
			ins = null;
		}
		// 解析EmailAddressArray属性
		if (pt.getEmailAddressArray() != null) {
			for (EmailAddressType eat : pt.getEmailAddressArray()) {
				person.getEmailAddresses().add(
						ParseEmailAddressType(eat, pt.getId(), session));
			}
		}
		// 解析home属性
		if (pt.getHome() != null) {
			person.setHome(pt.getHome());
		}
		// 解析id属性
		if (pt.getId() != null) {
			person.setId(pt.getId());
		}
		// 解析lid属性
		if (pt.getLid() != null) {
			person.setLid(pt.getLid());
		}
		// 解析name属性
		if (pt.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(pt.getName(), pt.getId() + ":Name",
					session, ins);
			person.setName(ins);
			ins = null;
		}
		// 解析ObjectType属性
		if (pt.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(pt.getId() + ":ObjectType", pt.getObjectType(),
					null, null, session, objectRef);
			person.setObjectType(objectRef);
			objectRef = null;
		}
		// 解析PersonName属性
		if (pt.getPersonName() != null) {
			person.setPersonName(ParsePersonNameType(pt.getPersonName(), pt
					.getId()));
		}
		// 解析slots属性
		if (pt.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : pt.getSlotArray()) {

				person.getSlots().add(
						parseSlotType(st, pt.getId() + ":Person:Slot:" + i,
								session));
				i++;
			}
		}
		// 解析status属性
		if (pt.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(pt.getId() + ":ObjectRef", pt.getStatus(), null,
					null, session, objectRef);
			person.setStatus(objectRef);
			objectRef = null;
		}
		// 解析telephoneNumber属性
		if (pt.getTelephoneNumberArray() != null) {
			for (TelephoneNumberType tnt : pt.getTelephoneNumberArray()) {
				person.getTelephoneNumbers().add(
						ParseTelephoneNumberType(tnt, pt.getId(), session));
			}
		}
		/*
		 * 保存person对象
		 */
		session.save(person);
		return person;
	}

	/**
	 * 解析TelephoneNumber
	 * 
	 * @param tnt
	 * @return
	 */
	public TelephoneNumber ParseTelephoneNumberType(TelephoneNumberType tnt,
			String id, Session session) {
		TelephoneNumber tn = new TelephoneNumber();
		// 解析id并存入id
		if (id != null) {
			tn.setId(id);
		}
		// 解析AreaCode，并存入
		if (tnt.getAreaCode() != null) {
			tn.setAreaCode(tnt.getAreaCode());
		}
		// 解析CountryCode，并存入
		if (tnt.getCountryCode() != null) {
			tn.setCountryCode(tnt.getCountryCode());
		}
		// 解析Extension，并存入
		if (tnt.getExtension() != null) {
			tn.setExtersion(tnt.getExtension());
		}
		// 解析number，并存入
		if (tnt.getNumber() != null) {
			tn.setNumber(tnt.getNumber());
		}
		// 解析PhoneType，并存入
		if (tnt.getPhoneType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(id, tnt.getPhoneType(), null, null, session,
					objectRef);
			tn.setPhoneType(objectRef);
			objectRef = null;
		}
		/*
		 * 保存TelephoneNumber对象
		 */
		session.save(tn);
		return tn;
	}

	/**
	 * 解析PersonNameType属性
	 * 
	 * @param pt
	 * @return
	 */
	public PersonName ParsePersonNameType(PersonNameType pt, String id) {
		PersonName pn = new PersonName();
		// 解析id属性
		if (id != null) {
			pn.setId(id);
		}
		// 解析FirstName属性
		if (pt.getFirstName() != null) {
			pn.setFirstName(pt.getFirstName());
		}
		// 解析LastName属性
		if (pt.getLastName() != null) {
			pn.setLastName(pt.getLastName());
		}
		// 解析middleName属性
		if (pt.getMiddleName() != null) {
			pn.setMiddleName(pt.getMiddleName());
		}
		return pn;
	}

	/**
	 * 解析organizationType属性
	 * 
	 * @param organizationType
	 * @return
	 */
	public Organization ParseOrganizationType(OrganizationType otType,
			Session session) {
		Organization organ = new Organization();

		// 解析addresses属性
		if (otType.getAddressArray() != null) {
			for (PostalAddressType addressType : otType.getAddressArray()) {
				organ.getAddresses().add(
						ParsePostalAddressType(addressType, otType.getId(),
								session));
			}
		}
		// 解析classification属性
		if (otType.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType cft : otType.getClassificationArray()) {

				parseClassificationType(cft, session, cf);
				organ.getClassifications().add(cf);
			}
			cf = null;
		}
		// 解析description属性
		if (otType.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(otType.getDescription(), otType.getId()
					+ ":Description", session, ins);
			organ.setDescription(ins);
			ins = null;
		}
		// 解析EmailAddress属性
		if (otType.getEmailAddressArray() != null) {
			for (EmailAddressType eat : otType.getEmailAddressArray()) {
				organ.getEmailAddresses().add(
						ParseEmailAddressType(eat, otType.getId(), session));
			}
		}
		// 解析ExternalIdentifier属性
		if (otType.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eit : otType
					.getExternalIdentifierArray()) {
				organ.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eit, session));
			}
		}
		// 解析Home属性
		if (otType.getHome() != null) {
			organ.setHome(otType.getHome());
		}
		// 解析id属性
		if (otType.getId() != null) {
			organ.setId(otType.getId());
		}
		// 解析lid属性
		if (otType.getLid() != null) {
			organ.setLid(otType.getLid());
		}
		// 解析name属性
		if (otType.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(otType.getName(),
					otType.getId() + ":Name", session, ins);
			organ.setName(ins);
			ins = null;
		}
		// 解析objectType属性
		if (otType.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(otType.getId() + ":ObjectType", otType
					.getObjectType(), null, null, session, objectRef);
			organ.setObjectType(objectRef);
			objectRef = null;
		}
		// 解析parent属性
		if (otType.getParent() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(otType.getId() + ":Parent", otType.getParent(),
					null, null, session, objectRef);
			organ.setParent(objectRef);
			objectRef = null;
		}
		// 解析primaryContact属性
		if (otType.getPrimaryContact() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(otType.getId() + ":PrimaryContact", otType
					.getPrimaryContact(), null, null, session, objectRef);
			organ.setPrimaryContact(objectRef);
			objectRef = null;
		}
		// 解析slots属性
		if (otType.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : otType.getSlotArray()) {

				organ.getSlots().add(
						parseSlotType(st, otType.getId() + ":Organization:Slot"
								+ i, session));
				i++;
			}
		}
		// 解析status属性
		if (otType.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(otType.getId() + ":Status", otType.getStatus(),
					null, null, session, objectRef);
			organ.setStatus(objectRef);
			objectRef = null;
		}
		// 解析VersionInfo属性
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
		 * 存储Organization对象
		 */
		session.save(organ);
		return organ;
	}

	/**
	 * 解析EmailAddressType属性
	 * 
	 * @param et
	 * @param id
	 * @return
	 */
	public EmailAddress ParseEmailAddressType(EmailAddressType et, String id,
			Session session) {
		EmailAddress eas = new EmailAddress();
		// 1:解析address属性
		if (et.getAddress() != null) {
			// System.out.println(et.getAddress().trim());
			eas.setAddress(et.getAddress().toString().trim());
		}
		// 2：解析type属性
		if (et.getType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(id, et.getType(), null, null, session, objectRef);
			eas.setType(objectRef);
			objectRef = null;
		}
		// 3:解析id属性
		if (id != null) {
			eas.setId(id);
		}
		/*
		 * 保存EmailAddress对象
		 */
		session.save(eas);
		return eas;

	}

	/**
	 * 解析PostalAddress属性
	 * 
	 * @param pat
	 * @return
	 */
	public PostalAddress ParsePostalAddressType(PostalAddressType pat,
			String id, Session session) {
		PostalAddress pa = new PostalAddress();
		// 1:解析city属性
		if (pat.getCity() != null) {
			pa.setCity(pat.getCity());
		}
		// 2:解析country属性
		if (pat.getCountry() != null) {
			pa.setCountry(pat.getCountry());
		}
		// 3：解析postalCode属性
		if (pat.getPostalCode() != null) {
			pa.setPostalCode(pat.getPostalCode());
		}
		// 4：解析id属性
		pa.setId(id);
		// 5：解析stateOrProvince属性
		if (pat.getStateOrProvince() != null) {
			pa.setStateOrProvince(pat.getStateOrProvince());
		}
		// 6：解析street属性
		if (pat.getStreet() != null) {
			pa.setStreet(pat.getStreet());
		}
		// 7:解析streetNumber属性
		if (pat.getStreetNumber() != null) {
			pa.setStreetNumber(pat.getStreetNumber());
		}
		/*
		 * 保存postalAddress
		 */
		session.save(pa);
		return pa;
	}

	/**
	 * 解析serviceType属性
	 * 
	 * @param st
	 *            :需要转换的serviceType
	 * @return serice类型
	 */
	public Service ParseServiceType(ServiceType st, Session session) {
		Service service = new Service();

		// 解析id属性
		if (st.getId() != null) {
			service.setId(st.getId());
		}
		// 解析classifications属性
		if (st.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType ct : st.getClassificationArray()) {
				parseClassificationType(ct, session, cf);
				service.getClassifications().add(cf);
			}
			cf = null;
		}
		// 解析description属性
		if (st.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(st.getDescription(), st.getId()
					+ ":Description", session, ins);
			service.setDescription(ins);
			ins = null;
		}
		// 解析ExternalIdentifiers属性
		if (st.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType sit : st.getExternalIdentifierArray()) {
				service.getExternalIdentifiers().add(
						ParseExternalIdentifierType(sit, session));
			}
		}
		// 解析home属性
		if (st.getHome() != null) {
			service.setHome(st.getHome());
		}
		// 解析lid属性
		if (st.getLid() != null) {
			service.setLid(st.getLid());
		}
		// 解析name属性
		if (st.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(st.getName(), st.getId() + ":Name",
					session, ins);
			service.setName(ins);
			ins = null;
		}
		// 解析objectType属性
		if (st.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(st.getId() + ":ObjectType", st.getObjectType(),
					null, null, session, objectRef);
			service.setObjectType(objectRef);
			objectRef = null;
		}
		// 解析出serviceBinding属性
		if (st.getServiceBindingArray() != null) {
			for (ServiceBindingType sbt : st.getServiceBindingArray()) {
				service.getServiceBindings().add(
						ParseServiceBindingType(sbt, session));
			}
		}
		// 解析slots属性
		if (st.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 sm : st.getSlotArray()) {

				service.getSlots().add(
						parseSlotType(sm, st.getId() + ":Service:Slot" + i,
								session));
				i++;
			}
		}
		// 解析status属性
		if (st.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(st.getId() + ":Status", st.getStatus(), null,
					null, session, objectRef);
			service.setStatus(objectRef);
			objectRef = null;
		}
		// 解析verionsInfo属性
		if (st.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(st.getVersionInfo(), st.getId(), session, vin);
			service.setVersionInfo(vin);
			vin = null;
		}
		/*
		 * 保存Service对象
		 */
		session.save(service);
		return service;
	}

	/**
	 * 解析serivceBindingType属性
	 * 
	 * @param sbt
	 * @return
	 */
	public ServiceBinding ParseServiceBindingType(ServiceBindingType sbt,
			Session session) {
		ServiceBinding sb = new ServiceBinding();
		// 解析accessURI属性
		if (sbt.getAccessURI() != null) {
			sb.setAccessURI(sbt.getAccessURI());
		}
		// 解析classifications属性
		if (sbt.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType cft : sbt.getClassificationArray()) {
				parseClassificationType(cft, session, cf);
				sb.getClassifications().add(cf);
			}
			cf = null;
		}
		// 解析description属性
		if (sbt.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(sbt.getDescription(), sbt.getId()
					+ ":Description", session, ins);
			sb.setDescription(ins);
			ins = null;
		}
		// 解析ExternalIdentifiers属性
		if (sbt.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eit : sbt.getExternalIdentifierArray()) {
				sb.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eit, session));
			}
		}
		// 解析Home属性
		if (sbt.getHome() != null) {
			sb.setHome(sbt.getHome());
		}

		// 解析id属性
		if (sbt.getId() != null) {
			sb.setId(sbt.getId());
		}
		// 解析lid属性
		if (sbt.getLid() != null) {
			sb.setLid(sbt.getLid());
		}
		// 解析name属性
		if (sbt.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(sbt.getName(), sbt.getId() + ":Name",
					session, ins);
			sb.setName(ins);
			ins = null;
		}
		// 解析objectType属性
		if (sbt.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sbt.getId() + ":ObjectType", sbt.getObjectType(),
					null, null, session, objectRef);
			sb.setObjectType(objectRef);
			objectRef = null;
		}
		// 解析Service属性
		if (sbt.getService() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sbt.getId() + ":Service", sbt.getService(), null,
					null, session, objectRef);
			sb.setService(objectRef);
			objectRef = null;
		}
		// 解析slots属性
		if (sbt.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : sbt.getSlotArray()) {

				sb.getSlots().add(
						parseSlotType(st, sbt.getId() + ":ServiceBinding:Slot"
								+ i, session));
				i++;
			}
		}
		// 解析specificationLinks属性
		if (sbt.getSpecificationLinkArray() != null) {
			for (SpecificationLinkType sfla : sbt.getSpecificationLinkArray()) {
				sb.getSpecificationLinks().add(
						ParseSpecificationLinkType(sfla, session));
			}
		}
		// 解析status属性
		if (sbt.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sbt.getId() + ":Status", sbt.getStatus(), null,
					null, session, objectRef);
			sb.setStatus(objectRef);
			objectRef = null;
		}
		// 解析targetBinding属性
		if (sbt.getTargetBinding() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sbt.getId() + ":TaretBinding", sbt
					.getTargetBinding(), null, null, session, objectRef);
			sb.setTargetBinding(objectRef);
			objectRef = null;
		}
		// 解析versionInfo属性
		if (sbt.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(sbt.getVersionInfo(), sbt.getId(), session,
					vin);
			sb.setVersionInfo(vin);
			vin = null;
		}
		/*
		 * 保存 ServiceBinding对象
		 */
		session.save(sb);
		return sb;
	}

	/**
	 * 解析SpecificationLink属性，并保存起来
	 * 
	 * @param sflaLinkType
	 * @return
	 */
	public SpecificationLink ParseSpecificationLinkType(
			SpecificationLinkType sflType, Session session) {
		SpecificationLink sfl = new SpecificationLink();
		// 解析classification属性
		if (sflType.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType sft : sflType.getClassificationArray()) {

				parseClassificationType(sft, session, cf);
				sfl.getClassifications().add(cf);
			}
			cf = null;
		}
		// 解析Description属性
		if (sflType.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(sflType.getDescription(), sflType.getId()
					+ ":Description", session, ins);
			sfl.setDescription(ins);
			ins = null;
		}
		// 解析ExternalIdentifiers属性
		if (sflType.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eif : sflType
					.getExternalIdentifierArray()) {
				sfl.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eif, session));
			}
		}
		// 解析home属性
		if (sflType.getHome() != null) {
			sfl.setHome(sflType.getHome());
		}
		// 解析id属性
		if (sflType.getId() != null) {
			sfl.setId(sflType.getId());
		}
		// 解析lid属性
		if (sflType.getLid() != null) {
			sfl.setLid(sflType.getLid());
		}
		// 解析Name属性
		if (sflType.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(sflType.getName(), sflType.getId()
					+ ":Name", session, ins);
			sfl.setName(ins);
			ins = null;
		}
		// 解析ObjectType属性
		if (sflType.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sflType.getId() + ":ObjectType", sflType
					.getObjectType(), null, null, session, objectRef);
			sfl.setObjectType(objectRef);
			objectRef = null;
		}
		// 解析ServiceBinding属性
		if (sflType.getServiceBinding() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sflType.getId() + ":ServiceBinding", sflType
					.getServiceBinding(), null, null, session, objectRef);
			sfl.setServiceBinding(objectRef);
			objectRef = null;
		}
		// 解析slots属性
		if (sflType.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : sflType.getSlotArray()) {

				sfl.getSlots().add(
						parseSlotType(st, sflType.getId()
								+ ":SpecificationLink:Slot" + i, session));
				i++;
			}
		}
		// 解析specificationObject属性
		if (sflType.getSpecificationObject() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sflType.getId() + ":SpecificationObject", sflType
					.getSpecificationObject(), null, null, session, objectRef);
			sfl.setSpecificationObject(objectRef);
			objectRef = null;
		}
		// 解析status属性
		if (sflType.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(sflType.getId() + ":Status", sflType.getStatus(),
					null, null, session, objectRef);
			sfl.setStatus(objectRef);
			objectRef = null;
		}
		// 解析usageDescription属性
		if (sflType.getUsageDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(sflType.getUsageDescription(), sflType
					.getId()
					+ ":UsageDescription", session, ins);
			sfl.setUsageDescription(ins);
			ins = null;
		}
		// 解析usageParameters属性
		if (sflType.getUsageParameterArray() != null) {
			String allValu = "";
			for (String userparameter : sflType.getUsageParameterArray()) {

				allValu += userparameter + ",";
			}
			sfl.setUsageParameters(allValu);
		}
		// 解析versionInfo属性
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
	 * 解析associationType属性
	 * 
	 * @param ast
	 * @return
	 */
	public Association ParseAssociationType(AssociationType1 ast,
			Session session) {
		Association ass = new Association();
		// 解析Id属性
		if (ast.getId() != null) {
			ass.setId(ast.getId());
		}
		// 解析name属性
		if (ast.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ast.getName(), ast.getId() + ":Name",
					session, ins);
			ass.setName(ins);
			ins = null;
		}
		// 解析home属性
		if (ast.getHome() != null) {
			ass.setHome(ast.getHome());
		}
		// 解析slot属性
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
		// 解析AssociationType属性
		if (ast.getAssociationType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ast.getId() + ":AssociationType", ast
					.getAssociationType(), null, null, session, objectRef);
			ass.setAssociationType(objectRef);
			objectRef = null;
		}
		// 解析classifications属性
		if (ast.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType cft : ast.getClassificationArray()) {
				parseClassificationType(cft, session, cf);
				ass.getClassifications().add(cf);
			}
			cf = null;
		}
		// 解析Description属性
		if (ast.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(ast.getDescription(), ast.getId()
					+ ":Descrption", session, ins);
			ass.setDescription(ins);
			ins = null;
		}
		// 解析Lid属性
		if (ast.getLid() != null) {
			ass.setLid(ast.getLid());
		}
		// 解析出sourceObject属性
		if (ast.getSourceObject() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ast.getId() + ":SourceObject", ast
					.getSourceObject(), null, null, session, objectRef);
			ass.setSourceObject(objectRef);
			objectRef = null;
		}
		// 解析出targetObject属性
		if (ast.getTargetObject() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ast.getId() + ":TargetObject", ast
					.getTargetObject(), null, null, session, objectRef);
			ass.setTargetObject(objectRef);
			objectRef = null;
		}
		// 解析出VersionInfo属性
		if (ast.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(ast.getVersionInfo(), ast.getId(), session,
					vin);
			ass.setVersionInfo(vin);
			vin = null;
		}
		// 解析出ObjectType属性
		if (ast.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ast.getId() + ":ObjectType", ast.getObjectType(),
					null, null, session, objectRef);
			ass.setObjectType(objectRef);
			objectRef = null;
		}
		// 解析出ExternalIdentifier属性
		if (ast.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eit : ast.getExternalIdentifierArray()) {
				ass.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eit, session));
			}
		}
		// 解析出status属性
		if (ast.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(ast.getId() + ":Status", ast.getStatus(), null,
					null, session, objectRef);
			ass.setStatus(objectRef);
			objectRef = null;
		}
		/*
		 * 保存 Association对象
		 */
		session.save(ass);
		return ass;

	}

	/**
	 * 解析ClassificationNodeType 并将属性保存在ClassificationNode中
	 * 
	 * @param cnt
	 * @return
	 */
	public ClassificationNode ParseClassificationNodeType(
			ClassificationNodeType cnt, Session session) {
		ClassificationNode cfn = new ClassificationNode();
		// 解析出 id属性
		if (cnt.getId() != null) {
			cfn.setId(cnt.getId());
		}
		// 解析出home属性
		if (cnt.getHome() != null) {
			cfn.setHome(cnt.getHome());
		}
		// 解析出Lid属性
		if (cnt.getLid() != null) {
			cfn.setLid(cnt.getLid());
		}
		// 解析出Description属性
		if (cnt.getDescription() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(cnt.getDescription(), cnt.getId()
					+ ":ClassificationNode", session, ins);
			cfn.setDescription(ins);
			ins = null;
		}
		// 解析出ObjectType属性
		if (cnt.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(cnt.getId() + ":ObjectType", cnt.getObjectType(),
					null, null, session, objectRef);
			cfn.setObjectType(objectRef);
			objectRef = null;
		}
		// 解析出parent属性
		if (cnt.getParent() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(cnt.getId() + ":Parent", cnt.getParent(), null,
					null, session, objectRef);
			cfn.setParent(objectRef);
			objectRef = null;
		}
		// 解析出code属性
		if (cnt.getCode() != null) {
			cfn.setCode(cnt.getCode());
		}
		// 解析出Path属性
		if (cnt.getPath() != null) {
			cfn.setPath(cnt.getPath());
		}
		// 解析出Slot属性
		if (cnt.getSlotArray() != null) {
			int i = 1;
			for (SlotType1 st : cnt.getSlotArray()) {

				cfn.getSlots().add(
						parseSlotType(st, cnt.getId()
								+ ":ClassificationNode:Slot" + i, session));
				i++;
			}
		}
		// 解析出VersionInfo属性
		if (cnt.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(cnt.getVersionInfo(), cnt.getId(), session,
					vin);
			cfn.setVersionInfo(vin);
			vin = null;
		}
		// 解析出ExternalIdentifier属性
		if (cnt.getExternalIdentifierArray() != null) {
			for (ExternalIdentifierType eit : cnt.getExternalIdentifierArray()) {
				cfn.getExternalIdentifiers().add(
						ParseExternalIdentifierType(eit, session));
			}
		}
		// 12:解析出 classificationNode属性
		if (cnt.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType ct : cnt.getClassificationArray()) {
				parseClassificationType(ct, session, cf);
				cfn.getClassifications().add(cf);
			}
			cf = null;
		}
		// 解析出status属性
		if (cnt.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(cnt.getId() + ":Status", cnt.getStatus(), null,
					null, session, objectRef);
			cfn.setStatus(objectRef);
			objectRef = null;
		}
		// 解析出Name属性
		if (cnt.getName() != null) {
			InternationalString ins = new InternationalString();
			parseInternationalString(cnt.getName(), cnt.getId() + ":Name",
					session, ins);
			cfn.setName(ins);
			ins = null;
		}
		/*
		 * 保存ClassificationNode对象
		 */
		session.save(cfn);
		return cfn;
	}

	/**
	 * 解析ExtrinsicObjectType并将属性保存在ExtrisicObject中
	 * 
	 * @param eot
	 * @return
	 */
	public ExtrinsicObject ParseExtrinsicObjectType(ExtrinsicObjectType eot,
			Session session) {
		ExtrinsicObject exObject = new ExtrinsicObject();
		// 解析出ExtrinsicObject中的id属性
		if (eot.getId() != null) {
			exObject.setId(eot.getId());
		}
		// 解析出ExtrinsicObject中的Lid属性
		if (eot.getLid() != null) {
			exObject.setLid(eot.getLid());
		}
		// 解析出ExtrinsicObject中的Home属性
		if (eot.getHome() != null) {
			exObject.setHome(eot.getHome());
		}
		// 解析出ExtrinsicObject中的Opaque属性
		exObject.setIsOpaque(eot.getIsOpaque());

		// 解析出ExtrinsicObject中的VersionInfo属性
		if (eot.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(eot.getVersionInfo(), eot.getId(), session,
					vin);
			exObject.setVersionInfo(vin);
			vin = null;
		}
		// 解析出ExtrinsicObject中的ObjecType属性
		if (eot.getObjectType() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(eot.getId() + ":ObjectType", eot.getObjectType(),
					null, null, session, objectRef);
			exObject.setObjectType(objectRef);
			objectRef = null;
		}
		// 解析出ExtrinsicObject中的Name属性
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

		// 解析出ExtrinsicObject中的MimeType属性
		if (eot.getMimeType() != null) {
			exObject.setMimeType(eot.getMimeType());
		}
		// 解析出ExtrinsicObject中的ExtrernalIdentifiers属性
		if (eot.getExternalIdentifierArray() != null) {
			ExternalIdentifierType[] externalIdentifierTypes = eot
					.getExternalIdentifierArray();
			for (ExternalIdentifierType externalIdentifierType : externalIdentifierTypes) {
				exObject.getExternalIdentifiers().add(
						ParseExternalIdentifierType(externalIdentifierType,
								session));
			}
		}
		// 解析出ExtrinsicObject中的Description属性
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
		// 解析出ExtrinsicObject中的Classification属性
		if (eot.getClassificationArray() != null) {
			Classification cf = new Classification();
			for (ClassificationType clt : eot.getClassificationArray()) {

				parseClassificationType(clt, session, cf);
				exObject.getClassifications().add(cf);
			}
			cf = null;
		}
		// 解析出ExtrinsicObject中的Slot属性
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
		// 解析出ExtrisicObject中的VersionInfo属性
		if (eot.getVersionInfo() != null) {
			VersionInfo vin = new VersionInfo();
			ParseVersionInfoType(eot.getVersionInfo(), eot.getId(), session,
					vin);
			exObject.setVersionInfo(vin);
			vin = null;
		}
		// 解析出ExtrisicObject中的status属性
		if (eot.getStatus() != null) {
			ObjectRef objectRef = new ObjectRef();
			WrapperObjectRef(eot.getId() + ":Status", eot.getStatus(), null,
					null, session, objectRef);
			exObject.setStatus(objectRef);
			objectRef = null;
		}
		/*
		 * 保存extrisicObject对象
		 */
		session.save(exObject);
		return exObject;
	}

	/**
	 * 解析各种ClassificationType类型，并将解析的内容存储到classification类中
	 * 
	 * @param clt
	 * @return
	 */
	public void parseClassificationType(ClassificationType clt,
			Session session, Classification cf) {
		if (clt != null) {
			// 解析出Id
			if (clt.getId() != null) {
				cf.setId(clt.getId());
			}
			// 解析出Lid
			if (clt.getLid() != null) {
				cf.setLid(clt.getLid());
			}
			// 解析description
			if (clt.getDescription() != null) {
				InternationalString ins = new InternationalString();
				parseInternationalString(clt.getDescription(), clt.getId()
						+ ":Description", session, ins);
				cf.setDescription(ins);
				ins = null;
			}
			// 解析出home
			if (clt.getHome() != null) {
				cf.setHome(clt.getHome());
			}
			// 解析出name
			if (clt.getName() != null) {
				InternationalString ins = new InternationalString();
				parseInternationalString(clt.getName(), clt.getId() + ":Name",
						session, ins);
				cf.setName(ins);
				ins = null;
			}
			// 解析出classificationNode（ObjectRef类型）
			if (clt.getClassificationNode() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(clt.getId() + ":ClassificationNode", clt
						.getClassificationNode(), null, null, session,
						objectRef);
				cf.setClassificationNode(objectRef);
				objectRef = null;
			}
			// 解析status
			if (clt.getStatus() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(clt.getId() + ":Status", clt.getStatus(),
						null, null, session, objectRef);
				cf.setStatus(objectRef);
				objectRef = null;
			}
			// 解析classifiedObject
			if (clt.getClassifiedObject() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(clt.getId() + ":ClassifiedObject", clt
						.getClassifiedObject(), null, null, session, objectRef);
				cf.setClassifiedObject(objectRef);
				objectRef = null;
			}
			// 解析objectType类型
			if (clt.getObjectType() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(clt.getId() + ":ObjectType", clt
						.getObjectType(), null, null, session, objectRef);
				cf.setObjectType(objectRef);
				objectRef = null;
			}
			// 解析nodeRepresentationType类型
			if (clt.getNodeRepresentation() != null) {
				cf.setNodeRepresentation(clt.getNodeRepresentation());
			}
			// 解析VersionInfoType类型
			if (clt.getVersionInfo() != null) {
				VersionInfo vin = new VersionInfo();
				ParseVersionInfoType(clt.getVersionInfo(), clt.getId(),
						session, vin);
				cf.setVersionInfo(vin);
				vin = null;
			}

			// 解析classificationSchemeType类型
			if (clt.getClassificationScheme() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(clt.getId() + ":ClassificationScheme", clt
						.getClassificationScheme(), null, null, session,
						objectRef);
				cf.setClassificationScheme(objectRef);
				objectRef = null;
			}
			// 解析ExternalIdentifierType类型
			if (clt.getExternalIdentifierArray() != null) {
				for (ExternalIdentifierType externalIdentifiertType : clt
						.getExternalIdentifierArray()) {
					cf.getExternalIdentifiers().add(
							ParseExternalIdentifierType(
									externalIdentifiertType, session));
				}
			}
			// 解析slots类型
			if (clt.getSlotArray() != null) {
				for (SlotType1 slotType1 : clt.getSlotArray()) {
					cf.getSlots().add(
							parseSlotType(slotType1, clt.getId(), session));
				}
			}
			/*
			 * 保存classification对象
			 */
			session.save(cf);
		}
	}

	/**
	 * 解析slottype1类型
	 * 
	 * @param slotType1
	 * @param id
	 * @return
	 */
	public Slot parseSlotType(SlotType1 slotType1, String id, Session session) {
		Slot slot = new Slot();
		// 存入id值
		if (id != null) {
			slot.setId(id);
		}
		// 解析name值
		if (slotType1.getName() != null) {
			slot.setName(slotType1.getName());
		}
		// slotType类型很多，需要统一处理,通过SlotType来分别对不同的slot进行解析
		if (slotType1.getSlotType() != null) {
			// 解析SlotType值，并存入slot中
			slot.setSlotType(slotType1.getSlotType());

			String slotType = slotType1.getSlotType();
			if (slotType
					.equals("urn:oasis:names:tc:ebxml-regrep:DataType:DateTime")) {
				// 当作String来处理
				// 关键性的slotType的value值
				String[] slotvalues = slotType1.getValueList().getValueArray();
				String allValue = "";
				// 以逗号","隔开
				for (String slotvalue : slotvalues) {

					allValue += slotvalue.trim() + ",";

				}
				slot.setValues(allValue);
			}
			// 当SlotType为String类型的时候
			if (slotType
					.equals("urn:oasis:names:tc:ebxml-regrep:DataType:String")) {
				// 关键性的slotType的value值
				String[] slotvalues = slotType1.getValueList().getValueArray();
				String allValue = "";
				// 以逗号","隔开
				for (String slotvalue : slotvalues) {
					allValue += slotvalue.trim() + ",";
				}
				// System.out.println(allValue);
				/*
				 * 实例：如 <valuelist> <value>value1</value> <value> value2</value>
				 * </valuelist> 则解析后的allvalue值为allValue="value1,value2";
				 */

				slot.setValues(allValue);
			}
			/*
			 * 解析的时候，用xmlCursor的toChild(i)的时候会存在很大问题，不能获取自己想要的数据类型
			 */
			// 当SlotType为GM_Envelope类型
			if (slotType
					.equals("urn:ogc:def:dataType:ISO-19107:2003:GM_Envelope")) {
				String allValue = "";
				String lowervalue = "";
				String uppervalue = "";
				String sysStr = "";
				for (int i = 0; i < ((ValueListType) slotType1.getValueList())
						.sizeOfAnyValueArray(); i++) {
					/*
					 * xmlCursor=slotType1.getValueList().newCursor();不能放在for循环前面
					 * ， 因为一旦放置在前面，xmlCurosr.toChild(i)只能访问AnyValue一次
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
					 * xmlCursor=slotType1.getValueList().newCursor();不能放在for循环前面
					 * ， 因为一旦放置在前面，xmlCurosr.toChild(i)只能访问AnyValue一次
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
			// 如果不存在SlotType类型，则用XmlBeans进行解析
			/*
			 * 按照String类型进行解析
			 */
			// 关键性的slotType的value值
			String[] slotvalues = slotType1.getValueList().getValueArray();
			String allValue = "";
			// 以逗号","隔开
			for (String slotvalue : slotvalues) {
				allValue += slotvalue.trim() + ",";
			}
			slot.setValues(allValue);
		}
		/*
		 * 保存slot类型
		 */
		session.save(slot);
		return slot;
	}

	/**
	 * 将PointType中的所有属性都检测出来，并且所有的属性按照一定的顺序组合为字符串存储
	 * 
	 * @param pot
	 * @return
	 */
	public String ParsePointType(PointType pot) {
		String allValue = "";
		// Point存入的属性的顺序为Id,SrsName,pos.....
		// 如果不存在属性则以"null" 存储
		allValue = AddStringFun(allValue, pot.getId());
		allValue = AddStringFun(allValue, pot.getSrsName());
		allValue = AddStringFun(allValue, pot.getPos().getListValue()
				.toString());

		// 这里可以添加需要存储的属性

		return allValue;
	}

	// pointCswType
	public String pointCswType(com.csw.model.gml.PointType pot) {
		String allValue = "";
		// Point存入的属性的顺序为Id,SrsName,pos.....
		// 如果不存在属性则以"null" 存储
		allValue = AddStringFun(allValue, pot.getId());
		allValue = AddStringFun(allValue, pot.getSrsName());
		allValue = AddStringFun(allValue, pot.getPos().getListValue()
				.toString());

		// 这里可以添加需要存储的属性

		return allValue;
	}

	/**
	 * 将envelopeType中的所有的属性都检测出来，并且所有的属性按照一定的顺序存储为以字符串加以存储
	 * 
	 * @param env
	 * @return
	 */
	public String ParseEnvelopeType(EnvelopeType et) {
		String allValue = "";
		// envelope存入的属性顺序为"srsName,LowerCorner,UpperCorner..."
		// 如果不存在的属性则以"null"添加
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
		// envelope存入的属性顺序为"srsName,LowerCorner,UpperCorner..."
		// 如果不存在的属性则以"null"添加
		allValue = AddStringFun(allValue, et.getSrsName());

		allValue = AddStringFun(allValue, et.getLowerCorner().getListValue()
				.toString());

		allValue = AddStringFun(allValue, et.getUpperCorner().getListValue()
				.toString());
		return allValue;
	}

	/**
	 * 将所有的String类型增加
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
	 * 解析ExternalIdentifierType类型，并且将该类型转换成ExternalIdentifier类型。
	 * 
	 * @param eit
	 * @return
	 */
	public ExternalIdentifier ParseExternalIdentifierType(
			ExternalIdentifierType eit, Session session) {
		ExternalIdentifier eif = new ExternalIdentifier();
		if (eit != null) {
			// 1：解析externalIdentifierType属性Id
			if (eit.getId() != null) {
				eif.setId(eit.getId());
			}
			// 2：解析externalIdentifierType属性Lid;
			if (eit.getLid() != null) {
				eif.setLid(eit.getLid());
			}
			// 3：解析externalIdentifierType属性home
			if (eit.getHome() != null) {
				eif.setHome(eit.getHome());
			}
			// 4：解析externalIdentifierType属性ObjectRef
			if (eit.getObjectType() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(eit.getId() + ":ObjectType", eit
						.getObjectType(), null, null, session, objectRef);
				eif.setObjectType(objectRef);
				objectRef = null;
			}
			// 5：解析externalIdenfigierType属性RegistryObject(ObjectRef类型)
			if (eit.getRegistryObject() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(eit.getId() + ":RegistryObject", eit
						.getRegistryObject(), null, null, session, objectRef);
				eif.setRegistryObject(objectRef);
				objectRef = null;
			}

			// 6：解析externalIdentifierType属性versionInfo
			if (eit.getVersionInfo() != null) {
				VersionInfo vin = new VersionInfo();
				ParseVersionInfoType(eit.getVersionInfo(), eit.getId(),
						session, vin);
				eif.setVersionInfo(vin);
				vin = null;
			}
			// 7：解析externalIdentifierType属性Description
			if (eit.getDescription() != null) {
				InternationalString ins = new InternationalString();
				parseInternationalString(eit.getDescription(), eit.getId()
						+ ":Description", session, ins);
				eif.setDescription(ins);
				ins = null;
			}
			// 8：解析externalIdentifierType属性IdentificationScheme
			if (eit.getIdentificationScheme() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(eit.getId() + ":IdentificationScheme", eit
						.getIdentificationScheme(), null, null, session,
						objectRef);
				eif.setIdentificationScheme(objectRef);
				objectRef = null;
			}
			// 9： 解析externalIdentifierType属性value
			if (eit.getValue() != null) {
				eif.setValue(eit.getValue());
			}
			// 10: 解析externalIdentifierType属性Status
			if (eit.getStatus() != null) {
				ObjectRef objectRef = new ObjectRef();
				WrapperObjectRef(eit.getId() + ":Status", eit.getStatus(),
						null, null, session, objectRef);
				eif.setStatus(objectRef);
				objectRef = null;
			}
			// 11:解析externalIdentifierType属性classifications
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
			// 12:解析externalIdentifier属性externalIdentifier(存在递归的调用问题）
			if (eit.getExternalIdentifierArray() != null) {
				if (eit.getExternalIdentifierArray().length > 0) {
					for (ExternalIdentifierType eiff : eit
							.getExternalIdentifierArray()) {
						eif.getExternalIdentifiers().add(
								ParseExternalIdentifierType(eiff, session));
					}
				}
			}
			// 13：解析externalIdentifier属性slots
			if (eit.getSlotArray() != null) {
				if (eit.getSlotArray().length > 0) {
					for (SlotType1 slotType1 : eit.getSlotArray()) {
						eif.getSlots().add(
								parseSlotType(slotType1, eit.getId(), session));
					}
				}
			}
			// 14：解析externalIdentifier属性Name
			if (eit.getName() != null) {
				InternationalString ins = new InternationalString();
				parseInternationalString(eit.getName(), session, ins);
				eif.setName(ins);
				ins = null;
			}
			/*
			 * 保存externalIdentifier对象
			 */
			session.save(eif);
		}
		return eif;
	}

	/**
	 * 解析versioninfoType，由于在存储的时候需要一个id，id由外部给与
	 * 
	 * @param versionInfoType
	 * @param id
	 * @return 返回versionInfo对象
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
		 * 保存versionInfo对象
		 */
		session.save(versionInfo);
	}

	/**
	 * 本函数是针对部分对象内部将ObjectRef提起出来之后为String
	 * ，而无法存入数据库中，导致存在问题，根据上下文，与文本String，补充和生成比完整ObjectRef对象
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
		 * 保存slot对象
		 */
		if (slots != null) {
			// 从set中取出所有的set中元素，并保存起来
			for (Slot slot : slots) {
				session.save(slot);
			}
		}
		/*
		 * 保存objectRef对象
		 */
		session.save(objectRef);
	}

	// 此函数需要修改
	/**
	 * 解析internationalStringType类型，并且存入到相应的internationString中
	 * 可能需要修改hibernate中的，在这里所有的localizedString的id都是一样的，这样存在问题。需要修改！！！！！
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
	 * 解析InternationalStringType对象，并保存到数据库中
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
	 * 解析localizedStringType类型，并存入到相应的localizedString中,
	 * 由于localizedStringType中不提供ID属性，需要外部加入id属性
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
