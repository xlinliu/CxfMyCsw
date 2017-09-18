package com.csw.utils.SensorInfoUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import com.csw.model.sml.IoComponentPropertyType;
import com.csw.model.sml.ProcessChainType;
import com.csw.model.sml.ProcessModelDocument;
import com.csw.model.sml.ProcessModelType;
import com.csw.model.sml.SensorMLDocument;
import com.csw.model.sml.ComponentsDocument.Components.ComponentList.Component;
import com.csw.model.sml.ConnectionDocument.Connection;
import com.csw.model.sml.ConnectionsDocument.Connections.ConnectionList;
import com.csw.model.sml.InputsDocument.Inputs.InputList;
import com.csw.model.sml.OutputsDocument.Outputs.OutputList;
import com.csw.model.sml.ParametersDocument.Parameters.ParameterList;
import com.csw.model.sml.SensorMLDocument.SensorML;
import com.csw.model.sml.SensorMLDocument.SensorML.Member;
import com.csw.model.swe.DataComponentPropertyType;
import com.csw.utils.FormatXmlUtil;
import com.ebrim.model.rim.AssociationDocument;
import com.ebrim.model.rim.AssociationType1;
import com.ebrim.model.rim.ExtrinsicObjectType;
import com.ebrim.model.rim.InternationalStringDocument;
import com.ebrim.model.rim.InternationalStringType;
import com.ebrim.model.rim.LocalizedStringType;
import com.ebrim.model.rim.RegistryObjectListDocument;
import com.ebrim.model.rim.RegistryPackageDocument;
import com.ebrim.model.rim.RegistryPackageType;
import com.ebrim.model.rim.SlotDocument;
import com.ebrim.model.rim.SlotType1;
import com.ebrim.model.rim.ValueListType;
import com.ebrim.model.wrs.ExtrinsicObjectDocument;

public class ProcessChainUtil {
	private static String registryPackagePrefix = "urn:ogc:def:role:OGC:ProcessChain:";
	private static String processModelprefix = "urn:ogc:object:feature:ProcessModel:";
	private static String associationConnectionStr = "";
	private static String componentAssociationStr = "";

	/**
	 * ����processChain�ļ� ������ ��װΪ���Դ�������ݿ�����Ϣ
	 * 
	 * @param fileconent
	 *            processChain�ļ�����
	 */
	public Map<String, Map<String, String>> ParseProcessChainOperation(
			String fileconent) {
		associationConnectionStr = "";
		componentAssociationStr = "";
		// �������е�component��sensorML���ĵ������ݺ�main��ebRIM���ĵ�������
		Map<String, Map<String, String>> resultStrMapsMap = new HashMap<String, Map<String, String>>();
		// �������е�component��sensorML���ĵ����ݼ���
		Map<String, String> componentsMap = new HashMap<String, String>();
		Map<String, String> mainMap = new HashMap<String, String>();

		RegistryPackageDocument registryPackageDocument = RegistryPackageDocument.Factory
				.newInstance();
		RegistryPackageType rpttype = registryPackageDocument
				.addNewRegistryPackage();
		RegistryObjectListDocument rolDocument = RegistryObjectListDocument.Factory
				.newInstance();
		rolDocument.addNewRegistryObjectList();
		ExtrinsicObjectDocument eodocument = ExtrinsicObjectDocument.Factory
				.newInstance();
		ExtrinsicObjectType eotype = eodocument.addNewExtrinsicObject2();
		try {
			SensorMLDocument sensorMLDocument = SensorMLDocument.Factory
					.parse(fileconent);
			SensorML sensorml = sensorMLDocument.getSensorML();
			for (Member member : sensorml.getMemberArray()) {
				if (member.getRole().trim().contains("processChain".trim())) {
					ProcessChainType processChainType = (ProcessChainType) member
							.getProcess();
					// ����RegistryPacakge��idֵ
					if (processChainType.getId() != null) {
						rpttype.setId(registryPackagePrefix
								+ processChainType.getId() + ":pacakge");
					}
					// ����ExtrinsicObject��idֵ
					if (processChainType.getId() != null) {
						eotype.setId(registryPackagePrefix
								+ processChainType.getId());
					}
					// ����ExtrinsicObject��nameֵ
					if (processChainType.getNameArray() != null) {
						if (processChainType.getNameArray().length > 0) {
							for (int i = 0; i < processChainType.getNameArray().length; i++) {
								eotype.setName(WrapInternationalString(
										processChainType.getNameArray(i)
												.getStringValue())
										.getInternationalString());
							}
						}
					}
					// ����ExtrinsicObject��descriptionֵ
					if (processChainType.getDescription() != null) {
						eotype.setDescription(WrapInternationalString(
								processChainType.getDescription()
										.getStringValue())
								.getInternationalString());
						// System.out.println(eotype.xmlText());
					}
					// ���涼��slot�Ĵ洢��������
					/*
					 * System.out
					 * .println("===================outputs======================"
					 * );
					 */
					// ����processChain�е�outputs����

					// System.out.println(processChainType.getOutputs().xmlText());
					OutputList oplList = processChainType.getOutputs()
							.getOutputList();
					if (oplList.getOutputArray() != null) {
						if (oplList.getOutputArray().length > 0) {
							String[] values = new String[oplList
									.getOutputArray().length];
							int i = 0;
							for (IoComponentPropertyType icopt : oplList
									.getOutputArray()) {
								values[i] = icopt.getName();
								i++;
							}
							SlotType1 slotType1 = eotype.addNewSlot();
							slotType1 = wrapSlotDocument(
									"urn:oasis:names:tc:ebxml-regrep:DataType:String",
									"urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::outputs",
									values).getSlot();
							eotype
									.setSlotArray(
											eotype.getSlotArray().length - 1,
											slotType1);
						}
					}
					/*
					 * System.out
					 * .println("===================inputs======================"
					 * );
					 */
					// ����processChain�е�Inputs����
					// System.out.println(processChainType.getInputs().xmlText());
					InputList inputlist = processChainType.getInputs()
							.getInputList();
					if (inputlist.getInputArray() != null) {
						if (inputlist.getInputArray().length > 0) {
							String[] values = new String[inputlist
									.getInputArray().length];
							int i = 0;
							for (IoComponentPropertyType icopt : inputlist
									.getInputArray()) {
								values[i] = icopt.getName();
								i++;
							}
							SlotType1 slotType1 = eotype.addNewSlot();
							slotType1 = wrapSlotDocument(
									"urn:oasis:names:tc:ebxml-regrep:DataType:String",
									"urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::inputs",
									values).getSlot();
							eotype
									.setSlotArray(
											eotype.getSlotArray().length - 1,
											slotType1);
						}
					}

					/*
					 * System.out.println(
					 * "===================parameters======================");
					 */
					// ����processChain�е�Parameters����
					// System.out.println(processChainType.getParameters()
					// .xmlText());

					ParameterList pl = processChainType.getParameters()
							.getParameterList();
					if (pl.getParameterArray() != null) {
						if (pl.getParameterArray().length > 0) {
							String[] values = new String[pl.getParameterArray().length];
							int i = 0;
							for (DataComponentPropertyType dcpt : pl
									.getParameterArray()) {
								values[i] = dcpt.getName();
							}
							SlotType1 slotType1 = eotype.addNewSlot();
							slotType1 = wrapSlotDocument(
									"urn:oasis:names:tc:ebxml-regrep:DataType:String",
									"urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::parameters",
									values).getSlot();
							eotype
									.setSlotArray(
											eotype.getSlotArray().length - 1,
											slotType1);
						}
					}
					// System.out.println(FormatXmlUtil
					// .formatXml(eotype.xmlText()));
					/*
					 * System.out
					 * .println("=====================components================"
					 * );
					 */

					// ����ЩExtrinsicObject��ΪcomponentOf��ϵ������
					// ����processChain�е�Components����
					// �����е����ݰ�װ��processModel�ĵ�������processModel���д���
					// System.out.println(processChainType.getComponents()
					// .xmlText());

					if (processChainType.getComponents().getComponentList() != null) {
						if (processChainType.getComponents().getComponentList()
								.getComponentArray() != null) {
							if (processChainType.getComponents()
									.getComponentList().getComponentArray().length > 0) {
								// ֮������������ļ��ĸ��������ṩname��id��ƥ��
								for (int i = 0; i < processChainType
										.getComponents().getComponentList()
										.getComponentArray().length; i++) {
									Component component = processChainType
											.getComponents().getComponentList()
											.getComponentArray(i);
									if (component.getRole().contains("process")) {
										// ��ȡ���������
										// System.out.println(component.getName());
										ProcessModelDocument pmd = ProcessModelDocument.Factory
												.newInstance();
										ProcessModelType pmType = pmd
												.addNewProcessModel();
										pmType = (ProcessModelType) component
												.getProcess();
										pmType.setId(component.getName());
										pmd.setProcessModel(pmType);
										SensorMLDocument smdocument = SensorMLDocument.Factory
												.newInstance();
										com.csw.model.sml.SensorMLDocument.SensorML sensorML2 = smdocument
												.addNewSensorML();
										sensorML2.addNewMember();
										String prefixStr = smdocument.xmlText();
										String pre1 = prefixStr.substring(0,
												prefixStr.lastIndexOf("/>"))
												+ ">";
										String pre2 = prefixStr
												.substring(prefixStr
														.lastIndexOf("</"));
										String processModel = pre1
												+ pmd.xmlText()
												+ "</ns:member>" + pre2;
										// System.out.println(processModel);
										componentsMap.put("component" + i,
												processModel);
										// ����ת��Ϊ��Ӧ��ebrim�ĸ�ʽ
										// pmd.xmlText()�е�������ΪprocessModel�����ݽ��н�������ȡ
										pmd.setProcessModel(pmType);
										// ֮��Ĺ������ǽ���pmd[����ļ���porcessModel���ļ�]
										AssociationDocument associationDocument = AssociationDocument.Factory
												.newInstance();
										AssociationType1 associationType1 = associationDocument
												.addNewAssociation();

										associationType1
												.setId(registryPackagePrefix
														+ processChainType
																.getId()
														+ ":component:association:N"
														+ i);
										associationType1
												.setAssociationType("urn:ogc:def:associationType::"
														+ processModelprefix
														+ processChainType
																.getId()
														+ "::ComposedOf");
										associationType1
												.setSourceObject(registryPackagePrefix
														+ processChainType
																.getId());
										associationType1
												.setTargetObject(processModelprefix
														+ component
																.getProcess()
																.getId());
										componentAssociationStr += associationDocument
												.xmlText();
										// System.out.println(associationDocument
										// .xmlText());
									}
									// System.out.println(eoType.getId());
								}
							}
						}
					}
					/*
					 * System.out
					 * .println("===================connection=================="
					 * );
					 */

					// ����ֵ�� ���ݴ洢��process��
					ConnectionList connectionList = processChainType
							.getConnections().getConnectionList();
					if (connectionList.getConnectionArray() != null) {
						if (connectionList.getConnectionArray().length > 0) {
							int i = 0;
							for (Connection connection : connectionList
									.getConnectionArray()) {
								// ��ȡconnection�е�nameֵ
								// System.out.println(connection.getName());
								// ��ȡlink�е�source�е�refֵ
								String source = connection.getLink()
										.getSource().getRef();
								// System.out.println(connection.getLink()
								// .getSource().getRef());
								// ��ȡlink�е�destination�е�refֵ
								String target = connection.getLink()
										.getDestination().getRef();
								// System.out.println(connection.getLink()
								// .getDestination().getRef());
								AssociationDocument associationDocument = AssociationDocument.Factory
										.newInstance();
								AssociationType1 ass = associationDocument
										.addNewAssociation();
								ass.setId(registryPackagePrefix
										+ processChainType.getId()
										+ ":connection:association:N" + i);
								ass.setAssociationType(PanduanAssociationFun(
										target, source));
								if (source.split("/")[0].equals("this")
										|| source.split("/")[0]
												.equals(processChainType
														.getId())) {
									ass.setSourceObject(registryPackagePrefix
											+ processChainType.getId());
								} else {
									// ���������������processMethod
									ass.setSourceObject(processModelprefix
											+ source.split("/")[0]);
								}
								if (target.split("/")[0].equals("this")
										|| target.split("/")[0]
												.equals(processChainType
														.getId())) {
									ass.setTargetObject(registryPackagePrefix
											+ processChainType.getId());
								} else {
									ass.setTargetObject(processModelprefix
											+ target.split("/")[0]);
								}
								SlotType1 slt1 = ass.addNewSlot();
								slt1.setName(connection.getName());
								ValueListType vltListType = slt1
										.addNewValueList();
								String[] values = new String[2];
								values[0] = source;
								values[1] = target;
								vltListType.setValueArray(values);
								i++;
								associationConnectionStr += associationDocument
										.xmlText();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String registrypackageXmlContent = registryPackageDocument.xmlText();
		String resultStr = "";
		resultStr = registrypackageXmlContent.substring(0,
				registrypackageXmlContent.indexOf("/>"))
				+ "><urn:RegistryObjectList>"
				+ eodocument.xmlText()
				+ associationConnectionStr
				+ componentAssociationStr
				+ "</urn:RegistryObjectList></urn:RegistryPackage>";
		resultStr = resultStr.replaceAll("<urn", "<rim");
		resultStr = resultStr.replaceAll("</urn", "</rim");
		resultStr = resultStr.replace("xmlns:urn", "xmlns:rim");
		resultStr = resultStr.replaceAll("<ns", "<wrs");
		resultStr = resultStr.replaceAll("</ns", "</wrs");
		resultStr = resultStr.replace("xmlns:ns", "xmlns:wrs");
		try {
			System.out.println(FormatXmlUtil.formatXml(resultStr));
		} catch (Exception e) {
			e.printStackTrace();
		}

		mainMap.put("main1", resultStr);
		/*
		 * System.out
		 * .println("================processChain description====================="
		 * );
		 */
		// �����л�ȡ��component��main���ĵ����ݷ�����һ��
		resultStrMapsMap.put("component", componentsMap);
		resultStrMapsMap.put("main", mainMap);
		return resultStrMapsMap;
	}

	/**
	 * �ж���������target��source֮��Ĺ�ϵ��inputassociaiton��ooutputassociaiton��
	 * parameterassociation
	 * 
	 * @param target
	 * @param source
	 * @return
	 */
	public String PanduanAssociationFun(String target, String source) {
		String associationtype = "";
		if (target.contains("inputs") && source.contains("inputs")) {
			associationtype = "urn:ogc:def:associationType::OGC-CSW-ebRIM-Sensor::inputconnection";
		} else if (target.contains("outputs") && source.contains("outputs")) {
			associationtype = "urn:ogc:def:associationType::OGC-CSW-ebRIM-Sensor::outputconnection";
		} else if (target.contains("parameters") && source.contains("outputs")) {
			associationtype = "urn:ogc:def:associationType::OGC-CSW-ebRIM-Sensor::outputparameterconnection";
		} else if (target.contains("parameters")
				&& source.contains("parameters")) {
			associationtype = "urn:ogc:def:associationType::OGC-CSW-ebRIM-Sensor::parameterstoparametersconnection";
		}
		return associationtype;
	}

	/**
	 * ����slot��ģ��
	 * 
	 * @param slottype
	 * @param slotName
	 * @param values
	 * @return
	 */
	public SlotDocument wrapSlotDocument(String slottype, String slotName,
			String[] values) {
		SlotDocument slotDocument = SlotDocument.Factory.newInstance();
		SlotType1 slottype1 = slotDocument.addNewSlot();
		slottype1.setName(slotName);
		slottype1.setSlotType(slottype);
		ValueListType vlt = slottype1.addNewValueList();
		vlt.setValueArray(values);
		slotDocument.setSlot(slottype1);
		return slotDocument;
	}

	/**
	 * ������������InternationalStringDocument
	 * 
	 * @param name
	 * @return
	 */
	public InternationalStringDocument WrapInternationalString(String name) {
		InternationalStringDocument isd = InternationalStringDocument.Factory
				.newInstance();
		InternationalStringType ist = isd.addNewInternationalString();
		LocalizedStringType lst = ist.addNewLocalizedString();
		lst.setValue(name.trim());
		lst.setLang("en-US");
		return isd;
	}

	/**
	 * ��ȡ�ļ�����
	 * 
	 * @param filepath
	 * @return
	 * @throws Exception
	 */
	public String ReadFileContent(String filepath) throws Exception {
		File file = new File(filepath);
		InputStreamReader isreader = new InputStreamReader(new FileInputStream(
				file), "UTF-8");
		BufferedReader br = new BufferedReader(isreader);
		String fileconent = "";
		String tempStr = "";
		while ((tempStr = br.readLine()) != null) {
			fileconent += tempStr;
		}
		return fileconent;
	}
}
