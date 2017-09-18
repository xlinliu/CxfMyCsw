package com.csw.utils.SensorInfoUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import com.csw.Service.impls.TransformSensorMLToebRIMService;
import com.csw.beans.ProcessBasicInfo;
import com.csw.model.ebXMLModel.Association;
import com.csw.model.ebXMLModel.ClassificationNode;
import com.csw.model.ebXMLModel.ExtrinsicObject;
import com.csw.model.ebXMLModel.Identifiable;
import com.csw.model.ebXMLModel.Organization;
import com.csw.model.ebXMLModel.RegistryPackage;
import com.csw.model.ebXMLModel.Service;
import com.csw.model.ebXMLModel.Slot;
import com.csw.model.gml.DirectPositionType;
import com.csw.model.gml.EnvelopeDocument;
import com.csw.model.gml.EnvelopeType;
import com.csw.model.gml.PointDocument;
import com.csw.model.gml.PointType;
import com.csw.model.sml.SensorMLDocument;
import com.csw.model.sml.IdentificationDocument.Identification;
import com.csw.model.sml.IdentificationDocument.Identification.IdentifierList.Identifier;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.GetRecordsOperationUtil.GetRecordsOperation;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorShareLevel;
import com.ebrim.model.rim.AssociationDocument;
import com.ebrim.model.rim.AssociationType1;
import com.ebrim.model.rim.ClassificationNodeDocument;
import com.ebrim.model.rim.ClassificationNodeType;
import com.ebrim.model.rim.ExtrinsicObjectDocument;
import com.ebrim.model.rim.ExtrinsicObjectType;
import com.ebrim.model.rim.LongName;
import com.ebrim.model.rim.OrganizationDocument;
import com.ebrim.model.rim.OrganizationType;
import com.ebrim.model.rim.RegistryObjectListType;
import com.ebrim.model.rim.RegistryPackageDocument;
import com.ebrim.model.rim.RegistryPackageType;
import com.ebrim.model.rim.ServiceDocument;
import com.ebrim.model.rim.ServiceType;
import com.ebrim.model.rim.SlotType1;
import com.ebrim.model.rim.ValueListType;
import com.ebrim.model.wrs.AnyValueType;
import com.ebrim.model.wrs.ValueListDocument;

@SuppressWarnings("deprecation")
public class GetRegistryRegistryInfoUtils {
	/**
	 * 解析EbRIM获取EbRIM的RegistryPacakge的id值，
	 * 
	 * @param ebrimcontent
	 *            ：可 以是EbRIM文件内容，文件路径，文件字符流，文件reader，甚至可以是URL
	 * @return 返回EbRIM中对应的RegistryPackage的ID值
	 */
	public static String GetRegistryPackageIDByEbrimContent(String ebrimcontent) {
		String rpid = "";
		try {
			// 解析文件路径
			RegistryPackageDocument rpdDocument = RegistryPackageDocument.Factory
					.parse(new File(ebrimcontent));
			if (rpdDocument.getRegistryPackage() != null) {
				rpid = rpdDocument.getRegistryPackage().getId();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			try {
				// 解析文件内容或者文件内容
				RegistryPackageDocument rpdDocument = RegistryPackageDocument.Factory
						.parse(ebrimcontent);
				if (rpdDocument.getRegistryPackage() != null) {
					rpid = rpdDocument.getRegistryPackage().getId();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return rpid;
	}

	/**
	 * 将registryPackage转换为RegistryPacakgeType的方法
	 * 
	 * @param id
	 *            获取的RegistryPackage的id值
	 */
	public static void RegistryPackageToRegistryPackageTypeMethod(String id) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Criteria criteria = session.createCriteria(RegistryPackage.class);
		criteria.add(Expression.eq("id", id));
		if (criteria.list() != null) {
			RegistryPackage rp = (RegistryPackage) criteria.list().get(0);
			RegistryPackageDocument rpdoment = RegistryPackageDocument.Factory
					.newInstance();
			RegistryPackageType rpType = rpdoment.addNewRegistryPackage();
			rpType.setId(rp.getId());
			for (Identifiable iden : rp.getIdentifiables()) {
				if (iden.getHome().equals(
						"com.ebrim.model.wrs.impl.ExtrinsicObjectTypeImpl")) {
					Criteria criteria2 = session
							.createCriteria(ExtrinsicObject.class);
					criteria2.add(Expression.eq("id", iden.getId()));
					ExtrinsicObject eo = (ExtrinsicObject) criteria2.list()
							.get(0);
					ExtrinsicObjectDocument eodDocument = ExtrinsicObjectDocument.Factory
							.newInstance();
					ExtrinsicObjectType eottType = eodDocument
							.addNewExtrinsicObject();
					eottType = GetRecordsOperation
							.ParseExtrinsicObjectToExtrinsicObjectType(
									eottType, eo);
					eodDocument.setExtrinsicObject(eottType);
				}
				if (iden.getHome().equals(
						"com.ebrim.model.rim.impl.AssociationType1Impl")) {
					Criteria criteria2 = session
							.createCriteria(Association.class);
					criteria2.add(Expression.eq("id", iden.getId()));
					Association eo = (Association) criteria2.list().get(0);
					AssociationDocument associationDocument = AssociationDocument.Factory
							.newInstance();
					AssociationType1 asstype1 = associationDocument
							.addNewAssociation();
					asstype1 = GetRecordsOperation
							.ParseAssociationToAssociationType(asstype1, eo);
					associationDocument.setAssociation(asstype1);
				}
				if (iden.getHome().equals(
						"com.ebrim.model.rim.impl.ServiceTypeImpl")) {
					Criteria criteria2 = session.createCriteria(Service.class);
					criteria2.add(Expression.eq("id", iden.getId()));
					Service eo = (Service) criteria2.list().get(0);
					ServiceDocument servicedoc = ServiceDocument.Factory
							.newInstance();
					ServiceType servicetype = servicedoc.addNewService();
					servicetype = GetRecordsOperation
							.ParseServiceToServiceType(servicetype, eo);
					servicedoc.setService(servicetype);
				}
				if (iden.getHome().equals(
						"com.ebrim.model.rim.impl.OrganizationTypeImpl")) {
					Criteria criteria2 = session
							.createCriteria(Organization.class);
					criteria2.add(Expression.eq("id", iden.getId()));
					Organization eo = (Organization) criteria2.list().get(0);
					OrganizationDocument organizationDocument = OrganizationDocument.Factory
							.newInstance();
					OrganizationType organizationtype = organizationDocument
							.addNewOrganization();
					GetRecordsOperation.ParseOrganizationToOrganizationType(
							organizationtype, eo);
					organizationDocument.setOrganization(organizationtype);
				}
				if (iden.getHome().equals(
						"com.ebrim.model.rim.impl.ClassificationNodeTypeImpl")) {
					Criteria criteria2 = session
							.createCriteria(ClassificationNode.class);
					criteria2.add(Expression.eq("id", iden.getId()));
					ClassificationNode eo = (ClassificationNode) criteria2
							.list().get(0);
					ClassificationNodeDocument clnodDocument = ClassificationNodeDocument.Factory
							.newInstance();
					ClassificationNodeType clnode = clnodDocument
							.addNewClassificationNode();
					clnode = GetRecordsOperation
							.ParseClassificationNodeToClassificationNodType(
									clnode, eo);
					clnodDocument.setClassificationNode(clnode);
				}
			}

		}
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
	}

	/**
	 * 根据部分的信息产生RegistryPackage的ebRIM模型文件
	 * 
	 * @param id
	 * @param keywords
	 * @param outputs
	 * @param inputs
	 * @param northenv
	 * @param westenv
	 * @param southenv
	 * @param eastenv
	 * @param pointx
	 * @param pointy
	 * @return
	 */
	public static RegistryPackageDocument CreateSimpleRegistryPackage(
			String id, String keywords, String outputs, String inputs,
			String northenv, String westenv, String southenv, String eastenv,
			String pointx, String pointy) {
		RegistryPackageDocument rpdocument = RegistryPackageDocument.Factory
				.newInstance();
		RegistryPackageType rPType = rpdocument.addNewRegistryPackage();
		rPType.setId(id + ":package");
		RegistryObjectListType reglisttype = rPType.addNewRegistryObjectList();
		com.ebrim.model.wrs.ExtrinsicObjectDocument eoDocument = com.ebrim.model.wrs.ExtrinsicObjectDocument.Factory
				.newInstance();
		// ExtrinsicObjectType obtype = eoDocument.addNewExtrinsicObject2();
		com.ebrim.model.wrs.ExtrinsicObjectType obtype = eoDocument
				.addNewExtrinsicObject2();
		obtype.setMimeType("application/xml");
		obtype
				.setObjectType("urn:ogc:def:objectType:OGC-CSW-ebRIM-Sensor::System");
		obtype.setId(id);
		obtype.setLid(id);
		SlotType1 slot1 = obtype.addNewSlot();
		ValueListType vltlist = slot1.addNewValueList();
		if (keywords != null) {
			String[] keywordss = keywords.split(",");
			for (String keyword : keywordss) {
				LongName ln = vltlist.addNewValue();
				ln.setStringValue(keyword);
			}
			slot1
					.setSlotType("urn:oasis:names:tc:ebxml-regrep:DataType:String");
			slot1.setName("urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::Keywords");
		}
		SlotType1 slot2 = obtype.addNewSlot();
		ValueListType vltlist2 = slot2.addNewValueList();
		if (inputs != null) {
			String[] inputss = inputs.split(",");
			for (String input : inputss) {
				LongName ln = vltlist2.addNewValue();
				ln.setStringValue(input);
			}
			slot2
					.setSlotType("urn:oasis:names:tc:ebxml-regrep:DataType:String");
			slot2.setName("urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::Inputs");
		}
		SlotType1 slot3 = obtype.addNewSlot();
		ValueListType vltlist3 = slot3.addNewValueList();
		if (outputs != null) {
			String[] outputss = outputs.split(",");
			for (String output : outputss) {
				LongName ln = vltlist3.addNewValue();
				ln.setStringValue(output);
			}
			slot3
					.setSlotType("urn:oasis:names:tc:ebxml-regrep:DataType:String");
			slot3.setName("urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::Outputs");
		}
		SlotType1 slotEnv = obtype.addNewSlot();
		slotEnv.addNewValueList();
		com.ebrim.model.wrs.ValueListType valueListEnv = ValueListDocument.Factory
				.newInstance().addNewValueList2();
		AnyValueType avt = valueListEnv.addNewAnyValue();
		EnvelopeDocument ed = EnvelopeDocument.Factory.newInstance();
		EnvelopeType ent = ed.addNewEnvelope();
		DirectPositionType dptlower = ent.addNewLowerCorner();
		dptlower.setStringValue(southenv + " " + westenv);
		DirectPositionType dptup = ent.addNewUpperCorner();
		ent.setSrsName("urn:ogc:def:crs:EPSG:4326");
		dptup.setStringValue(northenv + " " + eastenv);
		avt.set(ed);
		slotEnv.setValueList(valueListEnv);
		slotEnv.setName("urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::BoundedBy");
		slotEnv.setSlotType("urn:ogc:def:dataType:ISO-19107:2003:GM_Envelope");
		SlotType1 slotPos = obtype.addNewSlot();
		slotPos.addNewValueList();
		com.ebrim.model.wrs.ValueListType valueListPos = com.ebrim.model.wrs.ValueListDocument.Factory
				.newInstance().addNewValueList2();
		AnyValueType avtpos = valueListPos.addNewAnyValue();
		PointDocument pointDocument = PointDocument.Factory.newInstance();
		PointType pt = pointDocument.addNewPoint();
		DirectPositionType dptPos = pt.addNewPos();
		pt.setId(" ");
		pt.setSrsName("urn:ogc:def:crs:EPSG:4329");
		dptPos.setStringValue(pointy + " " + pointx);
		avtpos.set(pointDocument);
		slotPos.setValueList(valueListPos);
		slotPos.setName("urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::Location");
		slotPos.setSlotType("urn:ogc:def:dataType:ISO-19107:2003:GM_Point");

		reglisttype.set(eoDocument);
		rPType.setRegistryObjectList(reglisttype);
		return rpdocument;
	}

	/**
	 * 保存相关的process中诸如intendedapplication，processtype，serviceType等信息
	 * 
	 * @param id
	 *            process的id值
	 * @param intendedApplication
	 *            process的预期应用
	 * @param processType
	 *            process的类型
	 * @param serviceType
	 *            process的应用类型
	 */
	public static void SaveProcessBasicInfo(String id,
			String intendedApplication, String processType, String serviceType) {
		if (id != null) {
			if (!(intendedApplication == null) || !(processType == null)
					|| !(serviceType == null)) {
				ProcessBasicInfo pbinfo = new ProcessBasicInfo();
				if (!id.trim().toLowerCase().endsWith(":package")) {
					pbinfo.setProcessId(id.trim() + ":package");
				} else {
					pbinfo.setProcessId(id.trim());
				}
				pbinfo.setProcessType(processType.trim());
				pbinfo.setServiceType(serviceType.trim());
				pbinfo.setIntendedApplication(intendedApplication.trim());
				Session session = GetSessionUtil
						.GetSessionInstance(GetSessionUtil.SENSORTYPE);
				session.beginTransaction().begin();
				session.save(pbinfo);
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstance(session);
			}
		}
	}

	public static void main1(String[] args) {
		Map<String, String> results = GetRegistryPacakgeBasicInfos("urn:ogc:feature:remotesensor:EO-1:package");
		Iterator<String> str = results.keySet().iterator();
		while (str.hasNext()) {
			System.out.println(results.get(str.next()));
		}
	}

	/**
	 * 获取id值为RegistryPackage中的 基本信息，如keyword，inputs，outputs，location envelope等信息
	 * 
	 * @param processId
	 *            要查詢的RegistryPackage的id值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> GetRegistryPacakgeBasicInfos(
			String processId) {
		try {

			Map<String, String> maps = new HashMap<String, String>();
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			Criteria criteria = session.createCriteria(RegistryPackage.class);
			processId = StringUtil.AppendPacakgeStr(processId);
			criteria.add(Expression.eq("id", processId));
			List list = criteria.list();
			if (list != null && list.size() >= 1) {
				RegistryPackage registryPackage = (RegistryPackage) list.get(0);
				if (registryPackage != null) {
					for (Identifiable identifiable : registryPackage
							.getIdentifiables()) {
						if (identifiable
								.getHome()
								.equals(
										"com.ebrim.model.wrs.impl.ExtrinsicObjectTypeImpl")) {
							Criteria criteria2 = session
									.createCriteria(ExtrinsicObject.class);
							criteria2.add(Expression.eq("id", identifiable
									.getId()));
							if (criteria2.list() != null) {
								ExtrinsicObject eo = (ExtrinsicObject) criteria2
										.list().get(0);
								if (eo.getSlots() != null) {
									for (Slot slot : eo.getSlots()) {
										if (slot.getName().endsWith("Keywords")) {
											String value = slot.getValues();
											if (value != null) {
												if (value.trim().length() > 0) {
													String keywords = value
															.substring(
																	0,
																	value
																			.length() - 1);
													maps.put("keywords",
															keywords);
												}
											}

										}
										if (slot.getName().endsWith("Inputs")) {
											String value = slot.getValues();
											if (value != null) {
												if (value.trim().length() > 0) {
													String inputs = value
															.substring(
																	0,
																	value
																			.length() - 1);
													maps.put("inputs", inputs);
												}
											}

										}
										if (slot.getName().endsWith("Outputs")) {
											String value = slot.getValues();
											if (value != null) {
												if (value.trim().length() > 0) {
													String outputs = value
															.substring(
																	0,
																	value
																			.length() - 1);
													maps
															.put("outputs",
																	outputs);
												}
											}
										}
										if (slot.getName()
												.endsWith("BoundedBy")) {
											String value = slot.getValues();
											if (value != null) {
												if (value.trim().length() > 0) {
													String envelope = slot
															.getValues()
															.substring(
																	1,
																	value
																			.length() - 2);
													maps.put("envelope",
															envelope);
												}
											}

										}
										if (slot.getName().endsWith("Location")) {
											String value = slot.getValues();
											if (value != null) {
												if (value.trim().length() > 0) {
													String location = value
															.substring(
																	1,
																	value
																			.length() - 2);
													maps.put("location",
															location);
												}
											}

										}
									}
								}
							}

						}
					}
				}
			}
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return maps;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		long previous = System.currentTimeMillis();
		List<String> sensorids = GetRegistryPackageList("admin002", true);
		System.out.println(System.currentTimeMillis() - previous + "毫秒");
		if (sensorids != null) {
			for (String str : sensorids) {
				System.out.println(str);
			}
		}
		System.out.println("main finish....");
	}

	/**
	 * 获取所有的传感器的标识符
	 * 
	 * @param ower
	 *            用户名称
	 * @param all
	 *            是否显示其他用户注册公开传感器标识符[只真对一般数据者,对于游客而言，如果为真则查询所有的共有的，否则游客不能返回任何的信息]
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> GetRegistryPackageList(String ower, boolean all) {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(ower)) {
			return null;
		}
		List<String> lists = new ArrayList<String>();
		int level = UserInfoUtil.GetUserLevel(ower);
		List<RegistryPackage> rp = new ArrayList<RegistryPackage>();
		if (level == 1) {
			// 级别 1 为超级管理员则获取全部的传感器的信息（其他用户的不管是公开的，还是不公开的）
			rp
					.addAll((List<RegistryPackage>) GetSensorIdsBySqlStr("from RegistryPackage"));
			// 获取全部的传感器的id
			if (rp != null) {
				for (RegistryPackage r : rp) {
					lists.add(StringUtil.DeletePackageStr(r.getId()));
				}
			}
		} else if (level == 2) {
			// 获取所有传感器的共享信息
			List<SensorShareLevel> ssls = OperateSensornewUtil
					.GetAllSensorShareLevels();
			// 级别为普通管理员（获取自身和其他人员公开的）
			if (all) {
				if (!ssls.isEmpty()) {
					for (SensorShareLevel ssl : ssls) {
						if (ssl.getIsShare()) {
							// 如果是公开的，那么就返回
							lists.add(ssl.getSensorid());
						}
					}
				}
			} else {
				if (!ssls.isEmpty()) {
					for (SensorShareLevel ssl : ssls) {
						if (ssl.getIsShare()
								&& ssl.getSensorowner().equals(ower)) {
							// 如果是公开的，那么就返回
							lists.add(ssl.getSensorid());
						}
					}
				}
			}
		} else {
			if (!all) {
				// 不能读取公开的，那么就返回null
				return null;
			} else {
				// 返回所有公开的
				// 获取所有传感器的共享信息
				List<SensorShareLevel> ssls = OperateSensornewUtil
						.GetAllSensorShareLevels();
				if (!ssls.isEmpty()) {
					for (SensorShareLevel ssl : ssls) {
						if (ssl.getIsShare()) {
							lists.add(ssl.getSensorid());
						}
					}
				}
			}
		}
		return lists;
	}

	/**
	 * 根据sql语句获取关于传感器标识符信息，主要是迎合GetRegistryPackageList中调用
	 * 
	 * @param sqlStr
	 *            指定的查询的SQL语句
	 * @return 返回相关的对象的列表
	 */
	@SuppressWarnings("unchecked")
	public static List GetSensorIdsBySqlStr(String sqlStr) {
		if (sqlStr != null) {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List sensorids = session.createQuery(sqlStr).list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return sensorids;
		} else {
			return null;
		}

	}

	/**
	 * 根据ebRIM中的RegistryPackage来生成对应的id值
	 * 
	 * @param ebrimfilepath
	 *            查询的ebrim的文件路径
	 * @param tstRimAction
	 *            调用的TransformSensorMLToebRIMAction，但是再调用该方法之前，
	 *            必须先调用TransformSensorMLToebRIMAction.FuzhiVariables()方法
	 * @return 返回查询到的registrypackage的id值
	 */
	public static String GetRegistryPackageIDByebRIM(
			TransformSensorMLToebRIMService tstRimAction) {
		String rpid = "no  according repacakge id";
		try {
			String basepath = new GetRealPathUtil().getWebInfPath();
			String ebrimfilepath = FileOperationUtil.CreateFilePathOperation(
					basepath, "ebrim");
			RegistryPackageDocument rpdocument = RegistryPackageDocument.Factory
					.parse(new File(ebrimfilepath));
			if (rpdocument.getRegistryPackage() != null) {
				rpid = rpdocument.getRegistryPackage().getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rpid;
	}

	/**
	 * 通过sensormlFile路径来获取RegistryPacakge的id值
	 * 
	 * @param sensormlFilepath
	 *            sensorml的文件路径
	 * @return 返回获取的registrypackage的id值，但是没有添加：package
	 */
	public static String GetRegistryPackageID(String sensormlFilepath) {
		String rpid = "NO RegistryPackageID";
		try {
			SensorMLDocument sensorMLDocument = SensorMLDocument.Factory
					.parse(new File(sensormlFilepath));
			if (sensorMLDocument.getSensorML().getIdentificationArray() != null) {
				// System.out.println(sensorMLDocument.getSensorML()
				// .getIdentificationArray().length);
				for (Identification iden : sensorMLDocument.getSensorML()
						.getIdentificationArray()) {
					// System.out.println("getIdentificationArray()");
					if (iden.getIdentifierList() != null) {
						// System.out.println("getIdentifierList()");
						if (iden.getIdentifierList().getIdentifierArray() != null) {
							// System.out.println("getIdentifierArray()");
							for (Identifier identifier : iden
									.getIdentifierList().getIdentifierArray()) {
								// System.out.println(identifier.getName());
								if (identifier.getTerm().getDefinition()
										.toLowerCase().endsWith(
												"uniqueID".toLowerCase())) {
									rpid = identifier.getTerm().getValue();
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rpid;
	}
}
