package com.event.dom4j.parsers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.csw.exceptions.DBObjectQueryException;
import com.csw.exceptions.DBObjectSaveException;
import com.csw.exceptions.DBObjectUpdateException;
import com.csw.exceptions.EventExistsException;
import com.csw.exceptions.EventMLNotFormatException;
import com.csw.exceptions.EventNotExistsException;
import com.csw.exceptions.EventUpdatePhasesNotExistException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.PrecationExistException;
import com.csw.exceptions.PreparationExistException;
import com.csw.exceptions.RecoveryExistException;
import com.csw.exceptions.ResponseExistException;
import com.csw.exceptions.DBObjectDeleteException;
import com.csw.utils.CreateTable;
import com.csw.utils.DateOperationUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.event.EventOperations.SaveEventOperation;
import com.event.InnerEntities.AdministrationInfo;
import com.event.InnerEntities.AlterInfo;
import com.event.InnerEntities.ContactAddress;
import com.event.InnerEntities.ContactInfo;
import com.event.InnerEntities.ContactPhone;
import com.event.InnerEntities.ContactResponsibleParty;
import com.event.InnerEntities.DetailAddress;
import com.event.InnerEntities.EventCasualty;
import com.event.InnerEntities.EventClassification;
import com.event.InnerEntities.EventEconomicLoss;
import com.event.InnerEntities.EventIdentification;
import com.event.InnerEntities.EventMLClass;
import com.event.InnerEntities.EventMLDescriptionInfo;
import com.event.InnerEntities.EventOtherInfluence;
import com.event.InnerEntities.EventRecoveryInfo;
import com.event.InnerEntities.EventResponseInfo;
import com.event.InnerEntities.EventResponseObservation;
import com.event.InnerEntities.EventResponseResource;
import com.event.InnerEntities.EventResponseStateReport;
import com.event.InnerEntities.EventResponseTrendPredition;
import com.event.InnerEntities.EventSelfDescription;
import com.event.InnerEntities.EventSpaceTime;
import com.event.InnerEntities.LatLongPiar;
import com.event.InnerEntities.PrecationInfo;
import com.event.InnerEntities.PreparationInfo;
import com.event.InnerEntities.RecoveryInjuryInfo;
import com.event.InnerEntities.RecoveryMissingInfo;
import com.event.InnerEntities.RecoverybaseInfo;
import com.event.InnerEntities.ServiceInfo;
import com.event.InnerEntities.TimePeroid;
import com.event.JavaBeans.EventDenagedRoadJBean;
import com.event.JavaBeans.EventPredicationInfoJBean;
import com.event.content.config.SystemConfig;

public class Dom4JParser implements EventParseInterface {
	public static void main(String[] args) throws DocumentException,
			NullZeroException, EventMLNotFormatException,
			DBObjectSaveException, EventExistsException,
			PrecationExistException, PreparationExistException,
			ResponseExistException, RecoveryExistException,
			DBObjectQueryException, DBObjectDeleteException,
			DBObjectUpdateException, EventNotExistsException,
			EventUpdatePhasesNotExistException {
		CreateTable.main(null);
		File file = new File("f:\\Precaution template11.xml");
		// File file = new File("f:\\Preparedness template11.xml");
		// File file = new File("f:\\Recovery template11.xml");
		// File file = new File("f:\\Response template11.xml");
		Dom4JParser dom4jParser = new Dom4JParser();
		EventMLClass emc = dom4jParser.ParseEventML(file);
		SaveEventOperation.saveEventMLClass(emc, "yxliang");
		System.out.println("更新 !");
		// DeleteEventOperation.deleteEventMLClass(emc.getEsd().getEif()
		// .getEventid());
		// UpdateEventOperation.UpdateEventMLClass(emc, "yxliang");
		System.out.println("over");
	}

	/**
	 * 解析
	 * 
	 * @param eventmlcontent
	 * @return
	 * @throws EventMLNotFormatException
	 * @throws DocumentException
	 * @throws NullZeroException
	 */
	@SuppressWarnings("unchecked")
	public EventMLClass parseEventML(String eventmlcontent)
			throws EventMLNotFormatException, DocumentException,
			NullZeroException {
		if (eventmlcontent == null || eventmlcontent.trim().equals("")) {
			throw new NullZeroException("事件注册模型文档不能为空!");
		}
		EventMLClass emc = new EventMLClass();
		SAXReader reader = new SAXReader();
		InputStream iStream = new ByteArrayInputStream(eventmlcontent
				.getBytes());
		Document document = reader.read(iStream);
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		if (childElements == null || childElements.size() != 1
				|| !childElements.get(0).getName().equals("Content")) {
			ThrowEventMLNotFormatException();
		}
		Element content = childElements.get(0);
		List<Element> contentElements = content.elements();
		if (contentElements == null || contentElements.size() != 3) {
			ThrowEventMLNotFormatException();
		}
		int selfnum = 0;
		int descnum = 0;
		int adminum = 0;
		for (Element element : contentElements) {
			if (element.getName().equals("SelfDescription")) {
				// 解析selfDescription,防止出现多个
				if (selfnum >= 1) {
					ThrowEventMLNotFormatException();
				}
				selfnum++;
				emc.setEsd(ParseSelfDescription(element, ""));
			} else if (element.getName().equals("DescriptionInfo")) {
				// 解析Description,防止出现多个
				if (descnum >= 1) {
					ThrowEventMLNotFormatException();
				}
				descnum++;
				emc.setEmdi(parseEventDescription(element, ""));
			} else if (element.getName().equals("AdministrationInfo")) {
				// 解析AdministrationInfo,防止出现多个
				if (adminum >= 1) {
					ThrowEventMLNotFormatException();
				}
				adminum++;
				emc.setAi(parseAdministrationInfo(element, ""));
			} else {
				// 异常
				ThrowEventMLNotFormatException();
			}
		}
		return emc;
	}

	/**
	 * 解析eventml文档,从根节点开始解析
	 * 
	 * @param file
	 * @throws NullZeroException
	 * @throws DocumentException
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	public EventMLClass ParseEventML(File file) throws NullZeroException,
			DocumentException, EventMLNotFormatException {
		EventMLClass emc = new EventMLClass();
		FileOperationUtil.FileExist(file);
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		if (childElements == null || childElements.size() != 1
				|| !childElements.get(0).getName().equals("Content")) {
			ThrowEventMLNotFormatException(file);
		}
		Element content = childElements.get(0);
		List<Element> contentElements = content.elements();
		if (contentElements == null || contentElements.size() != 3) {
			ThrowEventMLNotFormatException(file);
		}
		int selfnum = 0;
		int descnum = 0;
		int adminum = 0;
		for (Element element : contentElements) {
			if (element.getName().equals("SelfDescription")) {
				// 解析selfDescription,防止出现多个
				if (selfnum >= 1) {
					ThrowEventMLNotFormatException(file);
				}
				selfnum++;
				emc
						.setEsd(ParseSelfDescription(element, file
								.getAbsolutePath()));
			} else if (element.getName().equals("DescriptionInfo")) {
				// 解析Description,防止出现多个
				if (descnum >= 1) {
					ThrowEventMLNotFormatException(file);
				}
				descnum++;
				emc.setEmdi(parseEventDescription(element, file
						.getAbsolutePath()));
			} else if (element.getName().equals("AdministrationInfo")) {
				// 解析AdministrationInfo,防止出现多个
				if (adminum >= 1) {
					ThrowEventMLNotFormatException(file);
				}
				adminum++;
				emc.setAi(parseAdministrationInfo(element, file
						.getAbsolutePath()));
			} else {
				// 异常
				ThrowEventMLNotFormatException(file);
			}
		}
		return emc;
	}

	@SuppressWarnings("unchecked")
	private EventMLDescriptionInfo parseEventDescription(Element element,
			String absolutePath) throws EventMLNotFormatException {
		EventMLDescriptionInfo emd = new EventMLDescriptionInfo();
		List<Element> elements = element.elements();
		if (elements != null && elements.size() >= 1) {
			int precatuionnum = 0;
			int preparationnum = 0;
			int responsenum = 0;
			int recoverynum = 0;
			for (Element element2 : elements) {
				if (element2.getName().equals("Precaution")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (precatuionnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					precatuionnum++;
					emd
							.setPrecationInfo(parsePrecaution(element2,
									absolutePath));
				} else if (element2.getName().equals("Preparation")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (preparationnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					preparationnum++;
					emd.setPreparationInfo(parsePreparation(element2,
							absolutePath));
				} else if (element2.getName().equals("Recovery")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (responsenum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					responsenum++;
					emd.setRecoveryInfo(parseRecovery(element2, absolutePath));
				} else if (element2.getName().equals("Response")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (recoverynum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					recoverynum++;
					emd.setResponseInfo(parseResponse(element2, absolutePath));
				} else {
					ThrowEventMLNotFormatException(absolutePath, element
							.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}
		return emd;
	}

	/**
	 * 解析事件响应阶段
	 * 
	 * @param element
	 * @param absolutePath
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private EventResponseInfo parseResponse(Element element, String absolutePath)
			throws EventMLNotFormatException {
		EventResponseInfo eri = new EventResponseInfo();
		System.out.println("---------------------response====================");
		System.out.println(element.asXML());
		List<Element> elements = element.elements();
		if (elements != null && elements.size() == 4) {
			int Observationsnum = 0;
			int Resourcesnum = 0;
			int StateReportnum = 0;
			int TrendPredictionnum = 0;
			for (Element element2 : elements) {
				if (element2.getName().equals("Observations")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (Observationsnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element2
								.getName());
					}
					Observationsnum++;
					eri.setErop(parseResponseObservations(element2,
							absolutePath));
				} else if (element2.getName().equals("Resources")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (Resourcesnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element2
								.getName());
					}
					Resourcesnum++;
					eri.setEpr(parseResponseResource(element2, absolutePath));
				} else if (element2.getName().equals("StateReport")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (StateReportnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element2
								.getName());
					}
					Observationsnum++;
					eri
							.setErsr(parseResponseStateReport(element2,
									absolutePath));
				} else if (element2.getName().equals("TrendPrediction")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (TrendPredictionnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element2
								.getName());
					}
					TrendPredictionnum++;
					eri.setErtp(parseResponseTrendPrediction(element2,
							absolutePath));
				} else {
					ThrowEventMLNotFormatException(absolutePath, element2
							.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}
		System.out.println("---------------------response====================");
		return eri;
	}

	@SuppressWarnings("unchecked")
	private EventResponseTrendPredition parseResponseTrendPrediction(
			Element element, String absolutePath)
			throws EventMLNotFormatException {
		List<Element> elements = element.elements();
		EventResponseTrendPredition etp = new EventResponseTrendPredition();
		if (elements != null && elements.size() == 2) {
			int factorsEstimationnum = 0, situationPredictionnum = 0;
			for (Element element2 : elements) {
				if (element2.getName().equals("factorsEstimation")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (factorsEstimationnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					factorsEstimationnum++;
					String valueString = element2.getData() + "";
					if (valueString.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, element2
								.getName());
					}
					etp.setFactorsEstimation(valueString);
				} else if (element2.getName().equals("situationPrediction")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (situationPredictionnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					situationPredictionnum++;
					String valueString = element2.getData() + "";
					if (valueString.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, element2
								.getName());
					}
					etp.setSituationPrediction(valueString);
				} else {
					ThrowEventMLNotFormatException(absolutePath, element
							.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}
		return etp;
	}

	@SuppressWarnings("unchecked")
	private EventResponseStateReport parseResponseStateReport(Element element,
			String absolutePath) throws EventMLNotFormatException {
		List<Element> elements = element.elements();
		EventResponseStateReport etp = new EventResponseStateReport();
		if (elements != null && elements.size() == 3) {
			int repairedRoadsURLnum = 0, searchedRegionsURLnum = 0, repairedFortificationsURLNUM = 0;
			for (Element element2 : elements) {
				if (element2.getName().equals("repairedRoadsURL")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (repairedRoadsURLnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					repairedRoadsURLnum++;
					String valueString = element2.getData() + "";
					if (valueString.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, element2
								.getName());
					}
					etp.setRepairedRoadsUrl(valueString);
				} else if (element2.getName().equals("searchedRegionsURL")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (searchedRegionsURLnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					searchedRegionsURLnum++;
					String valueString = element2.getData() + "";
					if (valueString.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, element2
								.getName());
					}
					etp.setSearchedRegionsURL(valueString);
				} else if (element2.getName().equals(
						"repairedFortificationsURL")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (repairedFortificationsURLNUM == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					repairedFortificationsURLNUM++;
					String valueString = element2.getData() + "";
					if (valueString.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, element2
								.getName());
					}
					etp.setRepairedFortificationURL(valueString);
				} else {
					ThrowEventMLNotFormatException(absolutePath, element
							.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}
		return etp;
	}

	@SuppressWarnings("unchecked")
	private EventResponseResource parseResponseResource(Element element,
			String absolutePath) throws EventMLNotFormatException {
		List<Element> elements = element.elements();
		EventResponseResource etp = new EventResponseResource();
		if (elements != null && elements.size() == 2) {
			int rescuerTeamnum = 0, LogisticsPointnum = 0;
			for (Element element2 : elements) {
				if (element2.getName().equals("rescuerTeam")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (rescuerTeamnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					rescuerTeamnum++;
					String valueString = element2.getData() + "";
					if (valueString.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, element2
								.getName());
					}
					etp.setResourceTeam(valueString);
				} else if (element2.getName().equals("LogisticsPoint")
						&& element2.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (LogisticsPointnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					LogisticsPointnum++;
					String valueString = element2.getData() + "";
					if (valueString.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, element2
								.getName());
					}
					etp.setLgoinsticspoints(valueString);
				} else {
					ThrowEventMLNotFormatException(absolutePath, element
							.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}
		return etp;
	}

	/**
	 * 解析response中的事件描述中的observation的信息
	 * 
	 * @param element2
	 * @param absolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private EventResponseObservation parseResponseObservations(Element element,
			String absolutePath) throws EventMLNotFormatException {
		EventResponseObservation ero = new EventResponseObservation();
		System.out
				.println("=====================response observation================");
		System.out.println(element.asXML());
		System.out
				.println("=====================response observation================");
		List<Element> elements = element.elements();
		if (elements != null
				&& elements.size() == 1
				&& elements.get(0).getName().equals("ObservationList")
				&& elements.get(0).getNamespaceURI()
						.equals(SystemConfig.EMLURL)) {
			int DamagedRoadnum = 0;
			int TrappedPersonnum = 0;
			int CurrentStatenum = 0;
			Element element2 = elements.get(0);
			List<Element> elements2 = element2.elements();
			if (elements2 != null && elements2.size() == 3) {
				for (Element e : elements2) {
					if (e.getName().equals("DamagedRoad")
							&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
						if (DamagedRoadnum == 1) {
							ThrowEventMLNotFormatException(absolutePath, e
									.getName());
						}
						DamagedRoadnum++;
						ero.setRodsDemagedRoads(parseDamagedRoad(e,
								absolutePath));
					} else if (e.getName().equals("TrappedPerson")
							&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
						if (TrappedPersonnum == 1) {
							ThrowEventMLNotFormatException(absolutePath, e
									.getName());
						}
						TrappedPersonnum++;
						List<Element> leElements = e.elements();
						if (leElements != null
								&& leElements.size() == 1
								&& leElements.get(0).getName().equals(
										"locations")
								&& leElements.get(0).getNamespaceURI().equals(
										SystemConfig.EMLURL)) {
							String value = leElements.get(0).getData() + "";
							if (value.trim().equals("")) {
								ThrowEventMLNotFormatException(absolutePath,
										leElements.get(0).getName());
							}
							ero.setTrappedPerson(value.trim());
						} else {
							ThrowEventMLNotFormatException(absolutePath, e
									.getName());
						}

					} else if (e.getName().equals("CurrentState")
							&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
						if (CurrentStatenum == 1) {
							ThrowEventMLNotFormatException(absolutePath, e
									.getName());
						}
						CurrentStatenum++;
						ero.setpInfo(parseCurrentState(e, absolutePath));
					} else {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}

		return ero;
	}

	@SuppressWarnings("unchecked")
	private PrecationInfo parseCurrentState(Element element, String absolutePath)
			throws EventMLNotFormatException {
		PrecationInfo pi = new PrecationInfo();
		List<Element> obserelements = element.elements();
		if (obserelements != null && obserelements.size() == 3) {
			int SpaceObservationnum = 0;
			int AerialObservationnum = 0;
			int GroundObservationnum = 0;
			for (Element e : obserelements) {
				if (e.getName().equals("SpaceObservation")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (SpaceObservationnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					SpaceObservationnum++;
					List<Element> elements3 = e.elements();
					if (elements3 != null
							&& elements3.size() == 1
							&& elements3.get(0).getName().equals(
									"satelliteData")
							&& elements3.get(0).getNamespaceURI().equals(
									SystemConfig.EMLURL)) {
						String value = elements3.get(0).getData() + "";
						if (value.trim().equals("")) {
							ThrowEventMLNotFormatException(absolutePath,
									elements3.get(0).getName());
						}
						pi.setSatelliteData(value);

					} else {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
				} else if (e.getName().equals("AerialObservation")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (AerialObservationnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					AerialObservationnum++;
					List<Element> elements3 = e.elements();
					if (elements3 != null
							&& elements3.size() == 1
							&& elements3.get(0).getName().equals("UAVData")
							&& elements3.get(0).getNamespaceURI().equals(
									SystemConfig.EMLURL)) {
						String value = elements3.get(0).getData() + "";
						if (value.trim().equals("")) {
							ThrowEventMLNotFormatException(absolutePath,
									elements3.get(0).getName());
						}
						pi.setUAVData(value);

					} else {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
				} else if (e.getName().equals("GroundObservation")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (GroundObservationnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					GroundObservationnum++;
					List<Element> elements3 = e.elements();
					if (elements3 != null
							&& elements3.size() == 1
							&& elements3.get(0).getName().equals("groundData")
							&& elements3.get(0).getNamespaceURI().equals(
									SystemConfig.EMLURL)) {
						String value = elements3.get(0).getData() + "";
						if (value.trim().equals("")) {
							ThrowEventMLNotFormatException(absolutePath,
									elements3.get(0).getName());
						}
						pi.setGroundData(value);

					} else {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
				} else {
					ThrowEventMLNotFormatException(absolutePath, element
							.getName());
				}

			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}
		return pi;
	}

	@SuppressWarnings("unchecked")
	private Set<EventDenagedRoadJBean> parseDamagedRoad(Element e,
			String absolutePath) throws EventMLNotFormatException {
		Set<EventDenagedRoadJBean> results = new HashSet<EventDenagedRoadJBean>();
		System.out
				.println("---------------------demagedroad--------------------");
		System.out.println(e.asXML());
		List<Element> elements = e.elements();
		if (elements != null
				&& elements.size() == 1
				&& elements.get(0).getName().equals("DamagedRoadList")
				&& elements.get(0).getNamespaceURI()
						.equals(SystemConfig.EMLURL)) {
			Element element = elements.get(0);
			List<Element> elements2 = element.elements();
			if (elements2 != null && elements2.size() > 0) {
				for (Element e1 : elements2) {
					EventDenagedRoadJBean rod = new EventDenagedRoadJBean();
					if (e1.getName().equals("DamagedRoadInfo")
							&& e1.getNamespaceURI().equals(SystemConfig.EMLURL)) {
						List<Element> elements3 = e1.elements();
						if (elements3 != null && elements3.size() == 3) {
							int namenum = 0;
							int breakPointLocationsnum = 0;
							int conditionDescriptionnum = 0;
							for (Element elem : elements3) {
								if (elem.getName().equals("name")
										&& elem.getNamespaceURI().equals(
												SystemConfig.EMLURL)) {
									if (namenum == 1) {
										ThrowEventMLNotFormatException(
												absolutePath, elem.getName());
									}
									namenum++;
									List<Element> elements4 = elem.elements();
									if (elements4 != null
											&& elements4.size() == 1
											&& elements4.get(0).getName()
													.equals("Text")
											&& elements4
													.get(0)
													.getNamespaceURI()
													.equals(SystemConfig.SWEURL)) {
										Element TextElement = elements4.get(0);
										List<Element> elements5 = TextElement
												.elements();
										if (elements5 != null
												&& elements5.size() == 1
												&& elements5.get(0).getName()
														.equals("value")
												&& elements5
														.get(0)
														.getNamespaceURI()
														.equals(
																SystemConfig.SWEURL)) {
											String valueString = elements5.get(
													0).getData()
													+ "";
											if (valueString.trim().equals("")) {
												ThrowEventMLNotFormatException(
														absolutePath, elements5
																.get(0)
																.getName());
											}
											rod.setDamagedRoadInfo(valueString);

										} else {
											ThrowEventMLNotFormatException(
													absolutePath, elements5
															.get(0).getName());
										}
									} else {
										ThrowEventMLNotFormatException(
												absolutePath, elem.getName());
									}
								} else if (elem.getName().equals(
										"breakPointLocations")
										&& elem.getNamespaceURI().equals(
												SystemConfig.EMLURL)) {
									if (breakPointLocationsnum == 1) {
										ThrowEventMLNotFormatException(
												absolutePath, elem.getName());
									}
									breakPointLocationsnum++;
									String valueString = elem.getData() + "";
									if (valueString.trim().equals("")) {
										ThrowEventMLNotFormatException(
												absolutePath, elem.getName());
									}
									rod.setBreakPointLocation(valueString);

								} else if (elem.getName().equals(
										"conditionDescription")
										&& elem.getNamespaceURI().equals(
												SystemConfig.EMLURL)) {
									if (conditionDescriptionnum == 1) {
										ThrowEventMLNotFormatException(
												absolutePath, elem.getName());
									}
									conditionDescriptionnum++;
									List<Element> elements4 = elem.elements();
									if (elements4 != null
											&& elements4.size() == 1
											&& elements4.get(0).getName()
													.equals("Text")
											&& elements4
													.get(0)
													.getNamespaceURI()
													.equals(SystemConfig.SWEURL)) {
										Element TextElement = elements4.get(0);
										List<Element> elements5 = TextElement
												.elements();
										if (elements5 != null
												&& elements5.size() == 1
												&& elements5.get(0).getName()
														.equals("value")
												&& elements5
														.get(0)
														.getNamespaceURI()
														.equals(
																SystemConfig.SWEURL)) {
											String valueString = elements5.get(
													0).getData()
													+ "";
											if (valueString.trim().equals("")) {
												ThrowEventMLNotFormatException(
														absolutePath, elements5
																.get(0)
																.getName());
											}
											rod
													.setConditionDescription(valueString);

										} else {
											ThrowEventMLNotFormatException(
													absolutePath, elements5
															.get(0).getName());
										}
									} else {
										ThrowEventMLNotFormatException(
												absolutePath, elem.getName());
									}
								}

								else {
									ThrowEventMLNotFormatException(
											absolutePath, elem.getName());
								}
							}
						} else {
							ThrowEventMLNotFormatException(absolutePath, e1
									.getName());
						}

					} else {
						ThrowEventMLNotFormatException(absolutePath, e1
								.getName());
					}
					results.add(rod);
				}
			} else {
				ThrowEventMLNotFormatException(absolutePath, element.getName());
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, e.getName());
		}
		System.out
				.println("---------------------demagedroad--------------------");

		return results;
	}

	/**
	 * 解析事件复原阶段
	 * 
	 * @param element
	 * @param absolutePath
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private EventRecoveryInfo parseRecovery(Element element, String absolutePath)
			throws EventMLNotFormatException {
		System.out
				.println("==============================recover======================");
		System.out.println(element.asXML());
		System.out
				.println("==============================recover======================");
		EventRecoveryInfo eri = new EventRecoveryInfo();
		List<Element> elements = element.elements();
		if (elements != null && elements.size() == 3) {
			int casualtynum = 0;
			int economicLossnum = 0;
			int otherinfluencenum = 0;
			for (Element e : elements) {
				if (e.getName().equals("Casualty")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (casualtynum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					casualtynum++;
					eri.setEc(parseCasualty(e, absolutePath));
				} else if (e.getName().equals("EconomicLoss")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (economicLossnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					economicLossnum++;
					eri.setEel(parseEconomicLoss(e, absolutePath));
				} else if (e.getName().equals("OtherInfluence")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (otherinfluencenum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					otherinfluencenum++;
					eri.setOif(parseOtherInfluence(e, absolutePath));
				} else {
					ThrowEventMLNotFormatException(absolutePath, e.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}
		return eri;
	}

	/**
	 * 解析其他影响
	 * 
	 * @param e
	 * @param absolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private EventOtherInfluence parseOtherInfluence(Element e,
			String absolutePath) throws EventMLNotFormatException {
		System.out
				.println("==============================parseOtherInfluence======================");
		System.out.println(e.asXML());
		System.out
				.println("==============================parseOtherInfluence======================");
		List<Element> elements = e.elements();
		EventOtherInfluence fof = new EventOtherInfluence();
		if (elements != null
				&& elements.size() == 1
				&& elements.get(0).getName().equals("InvisibleEffect")
				&& elements.get(0).getNamespaceURI()
						.equals(SystemConfig.EMLURL)) {
			Element element = elements.get(0);
			List<Element> elements2 = element.elements();
			if (elements2 != null
					&& elements2.size() == 1
					&& elements2.get(0).getName().equals(
							"publicPsychologyChanges")
					&& elements2.get(0).getNamespaceURI().equals(
							SystemConfig.EMLURL)) {
				Element element4 = elements2.get(0);
				List<Element> elements22 = element4.elements();
				if (elements22 == null || elements22.size() != 1) {
					ThrowEventMLNotFormatException(absolutePath, element
							.getName());
				}
				Element element2 = elements22.get(0);
				if (element2.getName().equals("Text")
						&& element2.getNamespaceURI().equals(
								SystemConfig.SWEURL)) {
					Element element3 = (Element) element2.elements().get(0);
					if (element3.getName().equals("value")
							&& element3.getNamespaceURI().equals(
									SystemConfig.SWEURL)) {
						String vaString = element3.getData() + "";
						if (vaString.trim().equals("")) {
							ThrowEventMLNotFormatException(absolutePath,
									element3.getName());
						}
						fof.setpPsyChange(vaString);
					} else {
						ThrowEventMLNotFormatException(absolutePath, element3
								.getName());
					}
				} else {
					ThrowEventMLNotFormatException(absolutePath, elements2.get(
							0).getName());
				}
			} else {
				ThrowEventMLNotFormatException(absolutePath, elements2.get(0)
						.getName());
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, e.getName());
		}
		System.out.println(fof.getpPsyChange());
		return fof;
	}

	/**
	 * 解析经济损失
	 * 
	 * @param e
	 * @param absolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private EventEconomicLoss parseEconomicLoss(Element e, String absolutePath)
			throws EventMLNotFormatException {
		System.out.println("=================经济损失===============");
		System.out.println(e.asXML());
		System.out.println("=================经济损失===============");
		EventEconomicLoss eeLoss = new EventEconomicLoss();
		List<Element> elements = e.elements();
		if (elements != null && elements.size() == 3) {
			int totalLossNum = 0;
			int directlossnum = 0;
			int indrectlossnum = 0;
			for (Element s : elements) {
				if (s.getName().equals("totalLossNumber")
						&& s.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (totalLossNum == 1) {
						ThrowEventMLNotFormatException(absolutePath, s
								.getName());
					}
					totalLossNum++;
					eeLoss.setTotalLossNumber(parseQuantityNum(s, absolutePath,
							"urn:ogc:def:property:totalLossesNumber", "￥"));
				} else if (s.getName().equals("DirectLoss")
						&& s.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (directlossnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, s
								.getName());
					}
					directlossnum++;
					eeLoss.setDirectlossInfo(parseDirectLossInfo(s,
							absolutePath));
				} else if (s.getName().equals("IndirectLoss")
						&& s.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (indrectlossnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, s
								.getName());
					}
					indrectlossnum++;
					eeLoss.setIndirectlossInfo(parseIndirectLossInfo(s,
							absolutePath));
				} else {
					ThrowEventMLNotFormatException(absolutePath, s.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, e.getName());
		}
		return eeLoss;
	}

	@SuppressWarnings("unchecked")
	private RecoverybaseInfo parseIndirectLossInfo(Element s,
			String absolutePath) throws EventMLNotFormatException {
		RecoverybaseInfo rbInfo = new RecoverybaseInfo();
		List<Element> elements = s.elements();
		if (elements != null && elements.size() == 2) {
			int lossnum = 0;
			int urlnum = 0;
			for (Element e : elements) {
				if (e.getName().equals("indirectLossesNumber")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (lossnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					lossnum++;
					rbInfo.setDeathnum(parseQuantityNum(e, absolutePath,
							"urn:ogc:def:property:indirectLossesNumber", "人"));
				} else if (e.getName().equals("detailedIndirectLossesURL")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (urlnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					urlnum++;
					String valueString = e.getData() + "";
					if (valueString.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					rbInfo.setDescurl(valueString);
				} else {
					ThrowEventMLNotFormatException(absolutePath, e.getName());
				}
			}

		} else {
			ThrowEventMLNotFormatException(absolutePath, s.getName());
		}
		return rbInfo;
	}

	@SuppressWarnings("unchecked")
	private RecoverybaseInfo parseDirectLossInfo(Element s, String absolutePath)
			throws EventMLNotFormatException {
		RecoverybaseInfo rbInfo = new RecoverybaseInfo();
		List<Element> elements = s.elements();
		if (elements != null && elements.size() == 2) {
			int lossnum = 0;
			int urlnum = 0;
			for (Element e : elements) {
				if (e.getName().equals("directLossesNumber")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (lossnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					lossnum++;
					rbInfo.setDeathnum(parseQuantityNum(e, absolutePath,
							"urn:ogc:def:property:directLossesNumber", "人"));
				} else if (e.getName().equals("detailedDirectLossesURL")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (urlnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					urlnum++;
					String valueString = e.getData() + "";
					if (valueString.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					rbInfo.setDescurl(valueString);
				} else {
					ThrowEventMLNotFormatException(absolutePath, e.getName());
				}
			}

		} else {
			ThrowEventMLNotFormatException(absolutePath, s.getName());
		}
		return rbInfo;
	}

	/**
	 * 解析人员伤亡
	 * 
	 * @param e
	 * @param absolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private EventCasualty parseCasualty(Element e, String absolutePath)
			throws EventMLNotFormatException {
		System.out.println("=================人员伤亡===============");
		System.out.println(e.asXML());
		System.out.println("=================人员伤亡===============");
		EventCasualty eCasualty = new EventCasualty();
		List<Element> elements = e.elements();
		if (elements != null && elements.size() == 4) {
			int totalnum = 0;
			int deathnum = 0;
			int injurenum = 0;
			int missingnum = 0;
			for (Element sub : elements) {
				if (sub.getName().equals("totalCasualties")
						&& sub.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (totalnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, sub
								.getName());
					}
					totalnum++;
					eCasualty.setTotalcasualties(parseTotalCasualties(sub,
							absolutePath));
				} else if (sub.getName().equals("Death")
						&& sub.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (deathnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, sub
								.getName());
					}
					deathnum++;
					eCasualty.setDeathrdi(parseTotalDeath(sub, absolutePath));
				} else if (sub.getName().equals("Injury")
						&& sub.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (injurenum == 1) {
						ThrowEventMLNotFormatException(absolutePath, sub
								.getName());
					}
					injurenum++;
					eCasualty.setRiji(parseTotalInjury(sub, absolutePath));
				} else if (sub.getName().equals("Missing")
						&& sub.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (missingnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, sub
								.getName());
					}
					missingnum++;
					eCasualty.setRmi(parseMissing(sub, absolutePath));
				} else {
					ThrowEventMLNotFormatException(absolutePath, sub.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, e.getName());
		}
		return eCasualty;
	}

	/**
	 * 解析失踪人员
	 * 
	 * @param sub
	 * @param absolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private RecoveryMissingInfo parseMissing(Element sub, String absolutePath)
			throws EventMLNotFormatException {
		System.out.println("--------------parseMissing------------------");
		System.out.println(sub.asXML());
		System.out.println("--------------parseMissing------------------");
		RecoveryMissingInfo rmiInfo = new RecoveryMissingInfo();
		List<Element> elements = sub.elements();
		if (elements != null && elements.size() == 2) {
			int missingnum = 0;
			int detailurlnum = 0;
			for (Element s : elements) {
				if (s.getName().equals("totalMissingNumber")
						&& s.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (missingnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, s
								.getName());
					}
					missingnum++;
					rmiInfo.setMissingnum(parseQuantityNum(s, absolutePath,
							"urn:ogc:def:property:missingNumber", "人"));
				} else if (s.getName().equals("detailedMissingInfoURL")
						&& s.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (detailurlnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, s
								.getName());
					}
					detailurlnum++;
					String valString = s.getData() + "";
					if (valString.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, s
								.getName());
					}
					rmiInfo.setDetailurl(valString);
				} else {
					ThrowEventMLNotFormatException(absolutePath, s.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, sub.getName());
		}
		return rmiInfo;
	}

	/**
	 * 解析受伤人数
	 * 
	 * @param sub
	 * @param absolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private RecoveryInjuryInfo parseTotalInjury(Element sub, String absolutePath)
			throws EventMLNotFormatException {
		System.out.println("--------------parseTotalInjury------------------");
		System.out.println(sub.asXML());
		System.out.println("--------------parseTotalInjury------------------");
		RecoveryInjuryInfo rii = new RecoveryInjuryInfo();
		List<Element> elements = sub.elements();
		if (elements != null && elements.size() == 4) {
			int totoalinjurenum = 0;
			int serioulyinjurenum = 0;
			int minorinjurenum = 0;
			int detailedinnum = 0;
			for (Element e : elements) {
				if (e.getName().equals("totalInjuredNumber")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (totoalinjurenum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					totoalinjurenum++;
					rii.setInjurenum(parseQuantityNum(e, absolutePath,
							"urn:ogc:def:property:injuredNumber", "人"));
				} else if (e.getName().equals("seriouslyInjuredNumber")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (serioulyinjurenum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					serioulyinjurenum++;
					rii
							.setSeriousinjurenum(parseQuantityNum(
									e,
									absolutePath,
									"urn:ogc:def:property:seriouslyInjuredNumber",
									"人"));
				} else if (e.getName().equals("minorInjuredNumber")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (minorinjurenum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					minorinjurenum++;
					rii.setMinorinjurenum(parseQuantityNum(e, absolutePath,
							"urn:ogc:def:property:minorInjuredNumber", "人"));
				} else if (e.getName().equals("detailedInjuriesInfoURL")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (detailedinnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					detailedinnum++;
					String valueString = e.getData() + "";
					if (valueString.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
					rii.setDetailurl(valueString);

				} else {
					ThrowEventMLNotFormatException(absolutePath, e.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, sub.getName());
		}
		return rii;
	}

	@SuppressWarnings("unchecked")
	private RecoverybaseInfo parseTotalDeath(Element sub, String absolutePath)
			throws EventMLNotFormatException {
		RecoverybaseInfo rb = new RecoverybaseInfo();
		System.out.println("--------------parseTotalDeath------------------");
		System.out.println(sub.asXML());
		System.out.println("--------------parseTotalDeath------------------");
		List<Element> elements = sub.elements();
		if (elements != null && elements.size() == 2) {
			int deathnum = 0;
			int detailurlnum = 0;
			for (Element s : elements) {
				if (s.getName().equals("deathNumber")
						&& s.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (deathnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, s
								.getName());
					}
					deathnum++;
					rb.setDeathnum(parseQuantityNum(s, absolutePath,
							"urn:ogc:def:property:deathNumber", "人"));
				} else if (s.getName().equals("detailedDeathInfoURL")
						&& s.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (detailurlnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, s
								.getName());
					}
					detailurlnum++;
					String valString = s.getData() + "";
					if (valString.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, s
								.getName());
					}
					rb.setDescurl(valString);
				} else {
					ThrowEventMLNotFormatException(absolutePath, s.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, sub.getName());
		}

		return rb;
	}

	/**
	 * 获取总的伤亡人数
	 * 
	 * @param sub
	 * @param absolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	private int parseTotalCasualties(Element sub, String absolutePath)
			throws EventMLNotFormatException {
		return parseQuantityNum(sub, absolutePath,
				"urn:ogc:def:property:totalCasualties", "人");
	}

	@SuppressWarnings("unchecked")
	private int parseQuantityNum(Element sub, String absolutePath,
			String definitionvalue, String uomcode)
			throws EventMLNotFormatException {
		int num = 0;
		List<Element> elements = sub.elements();
		if (elements != null
				&& elements.size() == 1
				&& elements.get(0).getName().equals("Quantity")
				&& elements.get(0).getNamespaceURI()
						.equals(SystemConfig.SWEURL)
				&& elements.get(0).attribute("definition") != null
				&& elements.get(0).attribute("definition").getData().toString()
						.equals(definitionvalue)) {
			Element element = elements.get(0);
			List<Element> elements2 = element.elements();
			if (elements2 != null && elements2.size() == 2) {
				int uomnum = 0;
				int valuenum = 0;
				for (Element ee : elements2) {
					if (ee.getName().equals("uom")
							&& ee.getNamespaceURI().equals(SystemConfig.SWEURL)
							&& ee.attribute("code") != null
							&& ee.attribute("code").getData().toString()
									.equals(uomcode)) {
						if (uomnum == 1) {
							ThrowEventMLNotFormatException(absolutePath, ee
									.getName());
						}
						uomnum++;
					} else if (ee.getName().equals("value")
							&& ee.getNamespaceURI().equals(SystemConfig.SWEURL)) {
						if (valuenum == 1) {
							ThrowEventMLNotFormatException(absolutePath, ee
									.getName());
						}
						valuenum++;
						String valueString = ee.getData() + "";
						try {
							num = Integer.parseInt(valueString);
						} catch (NumberFormatException e) {
							e.printStackTrace();
							ThrowEventMLNotFormatException(absolutePath, ee
									.getName());
						}
					} else {
						ThrowEventMLNotFormatException(absolutePath, ee
								.getName());
					}
				}
			} else {
				ThrowEventMLNotFormatException(absolutePath, element.getName());
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, sub.getName());
		}
		return num;
	}

	/**
	 * 解析事件预处理阶段
	 * 
	 * @param element
	 * @param absolutePath
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private PreparationInfo parsePreparation(Element element,
			String absolutePath) throws EventMLNotFormatException {
		PreparationInfo pi = new PreparationInfo();
		List<Element> elements = element.elements();
		if (elements != null && elements.size() == 3) {
			int observationnum = 0;
			int predictionnum = 0;
			int alertnum = 0;
			for (Element e : elements) {
				if (e.getName().equals("Observations")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (observationnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					observationnum++;
					pi.setpInfo(parseObservations(e, absolutePath));
				} else if (e.getName().equals("Prediction")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (predictionnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					predictionnum++;
					pi.setPi(parsePrediction(e, absolutePath));
				} else if (e.getName().equals("Alert")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (alertnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					alertnum++;
					pi.setAi(parseAlert(e, absolutePath));
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}
		return pi;
	}

	/**
	 * 解析告警信息
	 * 
	 * @param e
	 * @param absolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private AlterInfo parseAlert(Element e, String absolutePath)
			throws EventMLNotFormatException {
		AlterInfo aInfo = new AlterInfo();
		List<Element> elements = e.elements();
		if (elements != null && elements.size() == 6) {
			int alertTimenum = 0, alertStatusnum = 0, alertMessageTypenum = 0, alertScopenum = 0, alertAreanum = 0, alertMessage = 0;
			for (Element element : elements) {
				if (element.getName().equals("alertTime")
						&& element.getNamespaceURI()
								.equals(SystemConfig.EMLURL)) {
					if (alertTimenum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					alertTimenum++;
					aInfo.setAlerttime(parseAlertTime(element, absolutePath));
				} else if (element.getName().equals("alertStatus")
						&& element.getNamespaceURI()
								.equals(SystemConfig.EMLURL)) {
					if (alertStatusnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					alertStatusnum++;
					String str = element.getData() + "";
					if (str.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					aInfo.setAlertstatus(str.trim());
				} else if (element.getName().equals("alertMessageType")
						&& element.getNamespaceURI()
								.equals(SystemConfig.EMLURL)) {
					if (alertMessageTypenum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					alertMessageTypenum++;
					String str = element.getData() + "";
					if (str.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					aInfo.setAlertmessagetype(str.trim());
				} else if (element.getName().equals("alertScope")
						&& element.getNamespaceURI()
								.equals(SystemConfig.EMLURL)) {
					if (alertScopenum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					alertScopenum++;
					String str = element.getData() + "";
					if (str.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					aInfo.setAlertscope(str.trim());
				} else if (element.getName().equals("alertMessage")
						&& element.getNamespaceURI()
								.equals(SystemConfig.EMLURL)) {
					if (alertMessage == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					alertMessage++;
					String str = element.getData() + "";
					if (str.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					aInfo.setAlertMessage(str.trim());
				} else if (element.getName().equals("alertArea")
						&& element.getNamespaceURI()
								.equals(SystemConfig.EMLURL)) {
					if (alertAreanum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					alertAreanum++;
					List<Element> elements2 = element.elements();
					if (elements2 != null
							&& elements2.size() == 1
							&& elements2.get(0).getName().equals(
									"AdministrativeLocation")
							&& elements2.get(0).getNamespaceURI().equals(
									SystemConfig.EMLURL)) {
						aInfo.setAlertArea(parseGeolocationAddress(elements2
								.get(0), absolutePath));
					} else {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
				}
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, e.getName());
		}
		return aInfo;
	}

	@SuppressWarnings("unchecked")
	private Date parseAlertTime(Element element, String absolutePath)
			throws EventMLNotFormatException {
		Date date = null;
		System.out.println("----------alerttime----------");
		System.out.println(element.asXML());
		List<Element> elements = element.elements();
		if (elements != null
				&& elements.size() == 1
				&& elements.get(0).getName().equals("TimeInstant")
				&& elements.get(0).getNamespaceURI()
						.equals(SystemConfig.GMLURL)) {
			List<Element> elements2 = elements.get(0).elements();
			if (elements2 != null
					&& elements2.size() == 1
					&& elements2.get(0).getName().equals("timePosition")
					&& elements2.get(0).getNamespaceURI().equals(
							SystemConfig.GMLURL)) {
				String valueString = elements2.get(0).getData() + "";
				try {
					date = DateOperationUtil.StringToDate(valueString,
							SystemConfig.TIMEGMLFORMAT);
				} catch (NullZeroException e) {
					ThrowEventMLNotFormatException(absolutePath, element
							.getName());
				} catch (ParseException e) {
					ThrowEventMLNotFormatException(absolutePath, element
							.getName());
				}

			} else {
				ThrowEventMLNotFormatException(absolutePath, element.getName());
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}
		System.out.println("----------alerttime----------");
		return date;
	}

	/**
	 * 解析预处理信息
	 * 
	 * @param e
	 * @param absolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private EventPredicationInfoJBean parsePrediction(Element e,
			String absolutePath) throws EventMLNotFormatException {
		EventPredicationInfoJBean pif = new EventPredicationInfoJBean();
		List<Element> elements = e.elements();
		if (elements != null && elements.size() == 3) {
			int pOTnum = 0;
			int pOLnum = 0;
			int pSnum = 0;
			for (Element element : elements) {
				if (element.getName().equals("possibleOccurrenceTime")
						&& element.getNamespaceURI()
								.equals(SystemConfig.EMLURL)) {
					if (pOTnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					pOTnum++;
					pif.setPossibleTime(parsePossibleOccurrenceTime(element,
							absolutePath));

				} else if (element.getName().equals(
						"possibleOccurrenceLocation")
						&& element.getNamespaceURI()
								.equals(SystemConfig.EMLURL)) {
					if (pOLnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					pOLnum++;
					pif.setPossibleLocation(parsePossibleOccurrenceLocation(
							element, absolutePath));
				} else if (element.getName().equals("possibleSeverity")
						&& element.getNamespaceURI()
								.equals(SystemConfig.EMLURL)) {
					if (pSnum == 1) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					pSnum++;
					String value = element.getData() + "";
					if (value.trim().equals("")) {
						ThrowEventMLNotFormatException(absolutePath, element
								.getName());
					}
					pif.setPossibleSeverity(value);
				} else {
					ThrowEventMLNotFormatException(absolutePath, element
							.getName());
				}
			}

		} else {
			ThrowEventMLNotFormatException(absolutePath, e.getName());
		}
		return pif;
	}

	/**
	 * 解析事件发生的地点
	 * 
	 * @param element
	 * @param absolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private LatLongPiar parsePossibleOccurrenceLocation(Element element,
			String absolutePath) throws EventMLNotFormatException {
		LatLongPiar llp = new LatLongPiar();
		List<Element> elements = element.elements();
		if (elements != null
				&& elements.size() == 1
				&& elements.get(0).getName().equals("Position")
				&& elements.get(0).getNamespaceURI()
						.equals(SystemConfig.SWEURL)) {
			Element llpElement = elements.get(0);
			llp = parseGeolocationXY(llpElement, absolutePath);
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}
		return llp;
	}

	/**
	 * 解析事件发生的时间段
	 * 
	 * @param element
	 * @param absolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private TimePeroid parsePossibleOccurrenceTime(Element element,
			String absolutePath) throws EventMLNotFormatException {
		TimePeroid tPeroid = new TimePeroid(null, null);
		List<Element> elements = element.elements();
		if (elements != null
				&& elements.size() == 1
				&& elements.get(0).getName().equals("TimePeriod")
				&& elements.get(0).getNamespaceURI()
						.equals(SystemConfig.GMLURL)) {
			Element element2 = elements.get(0);
			List<Element> elements2 = element2.elements();
			if (elements2 != null && elements2.size() == 2) {
				int beginnum = 0;
				int endnum = 0;

				for (Element e : elements2) {
					if (e.getName().equals("beginPosition")
							&& e.getNamespaceURI().equals(SystemConfig.GMLURL)) {
						if (beginnum == 1) {
							ThrowEventMLNotFormatException(absolutePath, e
									.getName());
						}
						beginnum++;
						String value = e.getData() + "";
						Date begin = null;
						try {
							begin = DateOperationUtil.StringToDate(value,
									SystemConfig.TIMEGMLFORMAT);
						} catch (NullZeroException e1) {
							ThrowEventMLNotFormatException(absolutePath, e
									.getName());
						} catch (ParseException e1) {
							ThrowEventMLNotFormatException(absolutePath, e
									.getName());
						}
						tPeroid.setStartime(begin);
					} else if (e.getName().equals("endPosition")
							&& e.getNamespaceURI().equals(SystemConfig.GMLURL)) {
						if (endnum == 1) {
							ThrowEventMLNotFormatException(absolutePath, e
									.getName());
						}
						endnum++;
						String value = e.getData() + "";
						Date end = null;
						try {
							end = DateOperationUtil.StringToDate(value,
									SystemConfig.TIMEGMLFORMAT);
						} catch (NullZeroException e1) {
							ThrowEventMLNotFormatException(absolutePath, e
									.getName());
						} catch (ParseException e1) {
							ThrowEventMLNotFormatException(absolutePath, e
									.getName());
						}
						tPeroid.setEndtime(end);
					} else {
						ThrowEventMLNotFormatException(absolutePath, e
								.getName());
					}
				}
			} else {
				ThrowEventMLNotFormatException(absolutePath, element2.getName());
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}
		return tPeroid;
	}

	@SuppressWarnings("unchecked")
	private PrecationInfo parseObservations(Element Observationselement,
			String absolutePath) throws EventMLNotFormatException {
		PrecationInfo pi = new PrecationInfo();
		if (Observationselement.getName().equals("Observations")
				&& Observationselement.getNamespaceURI().equals(
						SystemConfig.EMLURL)) {
			Element element2 = Observationselement;
			List<Element> elements2 = element2.elements();
			if (elements2 != null
					&& elements2.size() == 1
					&& elements2.get(0).getName().equals("ObservationList")
					&& elements2.get(0).getNamespaceURI().equals(
							SystemConfig.EMLURL)) {
				Element observationListElement = elements2.get(0);
				List<Element> obserelements = observationListElement.elements();
				if (obserelements != null && obserelements.size() == 3) {
					int SpaceObservationnum = 0;
					int AerialObservationnum = 0;
					int GroundObservationnum = 0;
					for (Element e : obserelements) {
						if (e.getName().equals("SpaceObservation")
								&& e.getNamespaceURI().equals(
										SystemConfig.EMLURL)) {
							if (SpaceObservationnum == 1) {
								ThrowEventMLNotFormatException(absolutePath, e
										.getName());
							}
							SpaceObservationnum++;
							List<Element> elements3 = e.elements();
							if (elements3 != null
									&& elements3.size() == 1
									&& elements3.get(0).getName().equals(
											"satelliteData")
									&& elements3.get(0).getNamespaceURI()
											.equals(SystemConfig.EMLURL)) {
								String value = elements3.get(0).getData() + "";
								if (value.trim().equals("")) {
									ThrowEventMLNotFormatException(
											absolutePath, elements3.get(0)
													.getName());
								}
								pi.setSatelliteData(value);

							} else {
								ThrowEventMLNotFormatException(absolutePath, e
										.getName());
							}
						} else if (e.getName().equals("AerialObservation")
								&& e.getNamespaceURI().equals(
										SystemConfig.EMLURL)) {
							if (AerialObservationnum == 1) {
								ThrowEventMLNotFormatException(absolutePath, e
										.getName());
							}
							AerialObservationnum++;
							List<Element> elements3 = e.elements();
							if (elements3 != null
									&& elements3.size() == 1
									&& elements3.get(0).getName().equals(
											"UAVData")
									&& elements3.get(0).getNamespaceURI()
											.equals(SystemConfig.EMLURL)) {
								String value = elements3.get(0).getData() + "";
								if (value.trim().equals("")) {
									ThrowEventMLNotFormatException(
											absolutePath, elements3.get(0)
													.getName());
								}
								pi.setUAVData(value);

							} else {
								ThrowEventMLNotFormatException(absolutePath, e
										.getName());
							}
						} else if (e.getName().equals("GroundObservation")
								&& e.getNamespaceURI().equals(
										SystemConfig.EMLURL)) {
							if (GroundObservationnum == 1) {
								ThrowEventMLNotFormatException(absolutePath, e
										.getName());
							}
							GroundObservationnum++;
							List<Element> elements3 = e.elements();
							if (elements3 != null
									&& elements3.size() == 1
									&& elements3.get(0).getName().equals(
											"groundData")
									&& elements3.get(0).getNamespaceURI()
											.equals(SystemConfig.EMLURL)) {
								String value = elements3.get(0).getData() + "";
								if (value.trim().equals("")) {
									ThrowEventMLNotFormatException(
											absolutePath, elements3.get(0)
													.getName());
								}
								pi.setGroundData(value);
							} else {
								ThrowEventMLNotFormatException(absolutePath, e
										.getName());
							}
						} else {
							ThrowEventMLNotFormatException(absolutePath,
									observationListElement.getName());
						}

					}
				} else {
					ThrowEventMLNotFormatException(absolutePath,
							observationListElement.getName());
				}
			} else {
				ThrowEventMLNotFormatException(absolutePath, elements2.get(0)
						.getName());
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, Observationselement
					.getName());
		}
		return pi;
	}

	/**
	 * 解析预警阶段
	 * 
	 * @param element
	 * @param absolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private PrecationInfo parsePrecaution(Element element, String absolutePath)
			throws EventMLNotFormatException {
		PrecationInfo pi = new PrecationInfo();
		List<Element> elements = element.elements();
		if (elements != null && elements.size() == 1) {
			if (elements.get(0).getName().equals("Observations")
					&& elements.get(0).getNamespaceURI().equals(
							SystemConfig.EMLURL)) {
				pi = parseObservations(elements.get(0), absolutePath);
			} else {
				ThrowEventMLNotFormatException(absolutePath, element.getName());
			}
		} else {
			ThrowEventMLNotFormatException(absolutePath, element.getName());
		}
		return pi;
	}

	/**
	 * 解析EventML中AdministrationInfo部分
	 * 
	 * @param element
	 * @param absolutePath
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private AdministrationInfo parseAdministrationInfo(Element element,
			String fileabsolutePath) throws EventMLNotFormatException {
		AdministrationInfo ai = new AdministrationInfo();
		List<Element> elements = element.elements();
		if (elements != null && elements.size() == 2) {
			int contactnum = 0;
			int servicenum = 0;
			for (Element e : elements) {
				if (e.getName().equals("Contact")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (contactnum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					contactnum++;
					ai.setCrp(parseContact(e, fileabsolutePath));
				} else if (e.getName().equals("Service")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (servicenum == 1) {

					}
					servicenum++;
					ai.setSiInfo(parseService(e, fileabsolutePath));

				} else {
					ThrowEventMLNotFormatException(fileabsolutePath, e
							.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(fileabsolutePath, element.getName());
		}
		return ai;
	}

	/**
	 * 解析service部分
	 * 
	 * @param element
	 * @param fileabsolutePath
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private ServiceInfo parseService(Element element, String fileabsolutePath)
			throws EventMLNotFormatException {
		ServiceInfo siInfo = new ServiceInfo();
		List<Element> elements = element.elements();
		if (elements != null && elements.size() == 4) {
			int servicenamenum = 0, servicetypenum = 0, serviceaddressnum = 0, moreInfonum = 0;
			for (Element e : elements) {
				if (e.getName().equals("serviceName")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (servicenamenum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					servicenamenum++;
					String string = e.getData() + "";
					if (string.trim().equals("")) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					siInfo.setServicename(string);
				} else if (e.getName().equals("serviceType")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (servicetypenum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					servicetypenum++;
					String string = e.getData() + "";
					if (string.trim().equals("")) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					siInfo.setServicetype(string);
				} else if (e.getName().equals("serviceAddress")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (serviceaddressnum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					serviceaddressnum++;
					String string = e.getData() + "";
					if (string.trim().equals("")) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					siInfo.setServiceaddress(string);
				} else if (e.getName().equals("moreInfo")
						&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
					if (moreInfonum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					moreInfonum++;
				} else {
					ThrowEventMLNotFormatException(fileabsolutePath, e
							.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(fileabsolutePath, element.getName());
		}
		return siInfo;
	}

	/**
	 * 解析Contact部分
	 * 
	 * @param element
	 * @param fileabsolutePath
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private ContactResponsibleParty parseContact(Element element,
			String fileabsolutePath) throws EventMLNotFormatException {
		ContactResponsibleParty crp = new ContactResponsibleParty();
		List<Element> contactElements = element.elements();
		if (contactElements != null
				&& contactElements.size() == 1
				&& contactElements.get(0).getName().equals("ResponsibleParty")
				&& contactElements.get(0).getNamespaceURI().equals(
						SystemConfig.SMLURL)) {
			List<Element> responsiblePartyElements = contactElements.get(0)
					.elements();
			if (responsiblePartyElements != null
					&& responsiblePartyElements.size() == 3) {
				int individualnum = 0;
				int organizationnum = 0;
				int contactinfonum = 0;
				for (Element element2 : responsiblePartyElements) {
					if (element2.getName().equals("individualName")
							&& element2.getNamespaceURI().equals(
									SystemConfig.SMLURL)) {
						if (individualnum == 1) {
							ThrowEventMLNotFormatException(fileabsolutePath,
									element2.getName());
						}
						individualnum++;
						String str = element2.getData() + "";
						if (str.trim() == "") {
							ThrowEventMLNotFormatException(fileabsolutePath,
									element2.getName());
						}
						crp.setIndividualName(str);
					} else if (element2.getName().equals("organizationName")
							&& element2.getNamespaceURI().equals(
									SystemConfig.SMLURL)) {
						if (organizationnum == 1) {
							ThrowEventMLNotFormatException(fileabsolutePath,
									element2.getName());
						}
						organizationnum++;
						String str = element2.getData() + "";
						if (str.trim() == "") {
							ThrowEventMLNotFormatException(fileabsolutePath,
									element2.getName());
						}
						crp.setOrganizationName(str);
					} else if (element2.getName().equals("contactInfo")
							&& element2.getNamespaceURI().equals(
									SystemConfig.SMLURL)) {
						if (contactinfonum == 1) {
							ThrowEventMLNotFormatException(fileabsolutePath,
									element2.getName());
						}
						individualnum++;
						crp.setContactInfo(parseContactInfo(element2,
								fileabsolutePath));
					}
				}
			} else {
				ThrowEventMLNotFormatException(fileabsolutePath,
						contactElements.get(0).getName());
			}

		} else {
			ThrowEventMLNotFormatException(fileabsolutePath, element.getName());
		}
		return crp;
	}

	/**
	 * 解析contactinfo
	 * 
	 * @param element2
	 * @param fileabsolutePath
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private ContactInfo parseContactInfo(Element element,
			String fileabsolutePath) throws EventMLNotFormatException {
		ContactInfo cInfo = new ContactInfo();
		// System.out.println(element.asXML());
		List<Element> elements = element.elements();
		if (elements != null && elements.size() == 2) {
			int phonenum = 0;
			int addressnum = 0;
			for (Element element2 : elements) {
				if (element2.getName().equals("phone")
						&& element2.getNamespaceURI().equals(
								SystemConfig.SMLURL)) {
					if (phonenum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath,
								element2.getName());
					}
					phonenum++;
					cInfo.setContactPhone(parseContactPhone(element2,
							fileabsolutePath));
				} else if (element2.getName().equals("address")
						&& element2.getNamespaceURI().equals(
								SystemConfig.SMLURL)) {
					if (addressnum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath,
								element2.getName());
					}
					addressnum++;
					cInfo.setContactAddress(parseContactAddress(element2,
							fileabsolutePath));
				} else {
					ThrowEventMLNotFormatException(fileabsolutePath, element
							.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(fileabsolutePath, element.getName());
		}

		return cInfo;

	}

	@SuppressWarnings("unchecked")
	private ContactAddress parseContactAddress(Element element,
			String fileabsolutePath) throws EventMLNotFormatException {
		ContactAddress cAddress = new ContactAddress();
		System.out.println(element.asXML());
		String requiredstr = SystemConfig.ContactAddressRequireStr;
		int strsize = requiredstr.split(",").length;
		int delivernum = 0, citynum = 0, administrativeareanum = 0, postalcodenum = 0, countrynum = 0, electronicnum = 0;
		List<Element> elements = element.elements();
		if (elements != null && elements.size() == strsize) {
			for (Element e : elements) {
				if (e.getName().equals("deliveryPoint")
						&& e.getNamespaceURI().equals(SystemConfig.SMLURL)) {
					if (delivernum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					delivernum++;
					String stri = "" + e.getData();
					if (stri.trim().equals("")) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					cAddress.setDeliveryPoint(stri);
				} else if (e.getName().equals("city")
						&& e.getNamespaceURI().equals(SystemConfig.SMLURL)) {
					if (citynum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					citynum++;
					String stri = "" + e.getData();
					if (stri.trim().equals("")) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					cAddress.setCity(stri);
				} else if (e.getName().equals("administrativeArea")
						&& e.getNamespaceURI().equals(SystemConfig.SMLURL)) {
					if (administrativeareanum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					administrativeareanum++;
					String stri = "" + e.getData();
					if (stri.trim().equals("")) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					cAddress.setAdministrativeArea(stri);
				} else if (e.getName().equals("postalCode")
						&& e.getNamespaceURI().equals(SystemConfig.SMLURL)) {
					if (postalcodenum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					postalcodenum++;
					String stri = "" + e.getData();
					if (stri.trim().equals("")) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					cAddress.setPostalCode(stri);
				} else if (e.getName().equals("country")
						&& e.getNamespaceURI().equals(SystemConfig.SMLURL)) {
					if (countrynum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					countrynum++;
					String stri = "" + e.getData();
					if (stri.trim().equals("")) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					cAddress.setCountry(stri);
				} else if (e.getName().equals("electronicMailAddress")
						&& e.getNamespaceURI().equals(SystemConfig.SMLURL)) {
					if (electronicnum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					electronicnum++;
					String stri = "" + e.getData();
					if (stri.trim().equals("")) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					cAddress.setElectronicMailAddress(stri);
				} else {
					ThrowEventMLNotFormatException(fileabsolutePath, element
							.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(fileabsolutePath, element.getName());
		}
		return cAddress;
	}

	@SuppressWarnings("unchecked")
	private ContactPhone parseContactPhone(Element element,
			String fileabsolutePath) throws EventMLNotFormatException {
		ContactPhone cPhone = new ContactPhone();
		List<Element> elements = element.elements();
		if (elements != null && elements.size() == 2) {
			int voicenum = 0;
			int facsimilenum = 0;
			for (Element e : elements) {
				if (e.getName().equals("voice")
						&& e.getNamespaceURI().equals(SystemConfig.SMLURL)) {
					if (voicenum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					voicenum++;
					String str = e.getData() + "";
					if (str.trim().equals("")) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					cPhone.setVoice(str);

				} else if (e.getName().equals("facsimile")
						&& e.getNamespaceURI().equals(SystemConfig.SMLURL)) {
					if (facsimilenum == 1) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					facsimilenum++;
					String str = e.getData() + "";
					if (str.trim().equals("")) {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
					cPhone.setFacsimile(str);
				} else {
					ThrowEventMLNotFormatException(fileabsolutePath, element
							.getName());
				}
			}
		} else {
			ThrowEventMLNotFormatException(fileabsolutePath, element.getName());
		}
		return cPhone;

	}

	/**
	 * 解析EventML的SelfDescription部分
	 * 
	 * @param element
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private EventSelfDescription ParseSelfDescription(Element element,
			String fileabsolutePath) throws EventMLNotFormatException {
		EventSelfDescription esd = new EventSelfDescription();
		EventIdentification eif = new EventIdentification();
		EventSpaceTime est = new EventSpaceTime();
		EventClassification ecf = new EventClassification();
		if (!element.getName().equals("SelfDescription")
				|| !element.getNamespaceURI().equals(SystemConfig.EMLURL)) {
			ThrowEventMLNotFormatException(fileabsolutePath, element.getName());
		}
		List<Element> elements = element.elements();
		if (elements == null || elements.size() != 3) {
			ThrowEventMLNotFormatException(fileabsolutePath, element.getName());
		}
		int idennum = 0;
		int stnum = 0;
		int glnum = 0;
		for (Element subele : elements) {
			if (subele.getName() == "Identification"
					&& subele.getNamespaceURI().equals(SystemConfig.EMLURL)) {
				// 则进行Identification的解析
				if (idennum == 1) {
					ThrowEventMLNotFormatException(fileabsolutePath, subele
							.getName());
				}
				idennum++;
				eif = ParseIdentification(subele, fileabsolutePath);

			} else if (subele.getName() == "Space-Time"
					&& subele.getNamespaceURI().equals(SystemConfig.EMLURL)) {
				// 则进行Space-Time的解析
				if (stnum == 1) {
					ThrowEventMLNotFormatException(fileabsolutePath, subele
							.getName());
				}
				stnum++;
				est = ParseSpaceTime(subele, fileabsolutePath);

			} else if (subele.getName() == "Classification"
					&& subele.getNamespaceURI().equals(SystemConfig.EMLURL)) {
				// 则进行Geolocation的解析
				if (glnum == 1) {
					ThrowEventMLNotFormatException(fileabsolutePath, subele
							.getName());
				}
				glnum++;
				ecf = ParseClassification(subele, fileabsolutePath);
			} else {
				ThrowEventMLNotFormatException(fileabsolutePath, subele
						.getName());
			}
		}
		esd.setEcf(ecf);
		esd.setEif(eif);
		esd.setEst(est);
		return esd;
	}

	/**
	 * 解析Classification对象
	 * 
	 * @param subele
	 * @param fileabsolutePath
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private EventClassification ParseClassification(Element subele,
			String fileabsolutePath) throws EventMLNotFormatException {
		EventClassification eClassification = new EventClassification();
		List<Element> elements = subele.elements();
		String requiredelement = SystemConfig.ClassificationRequireStr;
		String[] requiredelements = requiredelement.split(",");
		int elementsnum = requiredelements.length;
		int eventCertaintynum = 0;
		int eventSeverityNum = 0;
		int eventUrgencyNum = 0;
		int eventInheritanceNum = 0;
		int eventPatternNum = 0;
		int eventCategoryNum = 0;
		if (elements != null && elements.size() == 1) {
			Element element = elements.get(0);
			List<Element> elements2 = element.elements();
			if (elements2 != null) {
				if (elements2.size() < requiredelements.length) {
					ThrowEventMLNotFormatException(fileabsolutePath, element
							.getName());
				}
				for (Element e : elements2) {
					if (e.getName().equals("eventCertainty")
							&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
						if (eventCertaintynum == 1) {
							ThrowEventMLNotFormatException(fileabsolutePath, e
									.getName());
						}
						eventCertaintynum++;
						if (requiredelement.contains("eventCertainty")) {
							elementsnum--;
							String value = e.getData() + "";
							if (value.trim().equals("")) {
								ThrowEventMLNotFormatException(
										fileabsolutePath, e.getName());
							}
							eClassification.setEventCertainty(value);
						}
					} else if (e.getName().equals("eventSeverity")
							&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
						if (eventSeverityNum == 1) {
							ThrowEventMLNotFormatException(fileabsolutePath, e
									.getName());
						}
						eventSeverityNum++;
						if (requiredelement.contains("eventSeverity")) {
							elementsnum--;
							String value = e.getData() + "";
							if (value.trim().equals("")) {
								ThrowEventMLNotFormatException(
										fileabsolutePath, e.getName());
							}
							eClassification.setEventSeverity(value);
						}
					} else if (e.getName().equals("eventUrgency")
							&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
						if (eventUrgencyNum == 1) {
							ThrowEventMLNotFormatException(fileabsolutePath, e
									.getName());
						}
						eventUrgencyNum++;
						if (requiredelement.contains("eventUrgency")) {
							elementsnum--;
							String value = e.getData() + "";
							if (value.trim().equals("")) {
								ThrowEventMLNotFormatException(
										fileabsolutePath, e.getName());
							}
							eClassification.setEventUrgency(value);
						}
					} else if (e.getName().equals("eventInheritance")
							&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
						if (eventInheritanceNum == 1) {
							ThrowEventMLNotFormatException(fileabsolutePath, e
									.getName());
						}
						eventInheritanceNum++;
						if (requiredelement.contains("eventInheritance")) {
							elementsnum--;
							String value = e.getData() + "";
							if (value.trim().equals("")) {
								ThrowEventMLNotFormatException(
										fileabsolutePath, e.getName());
							}
							eClassification.setEventInheritance(value);
						}
					} else if (e.getName().equals("eventPattern")
							&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
						if (eventPatternNum == 1) {
							ThrowEventMLNotFormatException(fileabsolutePath, e
									.getName());
						}
						eventPatternNum++;
						if (requiredelement.contains("eventPattern")) {
							elementsnum--;
							String value = e.getData() + "";
							if (value.trim().equals("")) {
								ThrowEventMLNotFormatException(
										fileabsolutePath, e.getName());
							}
							eClassification.setEventPattern(value);
						}
					} else if (e.getName().equals("eventCategory")
							&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
						if (eventCategoryNum == 1) {
							ThrowEventMLNotFormatException(fileabsolutePath, e
									.getName());
						}
						eventCategoryNum++;
						if (requiredelement.contains("eventCategory")) {
							elementsnum--;
							String value = e.getData() + "";
							if (value.trim().equals("")) {
								ThrowEventMLNotFormatException(
										fileabsolutePath, e.getName());
							}
							eClassification.setEventCatagory(value);
						}
					} else {
						ThrowEventMLNotFormatException(fileabsolutePath, e
								.getName());
					}
				}
			}
		} else {
			ThrowEventMLNotFormatException(fileabsolutePath, subele.getName());
		}
		return eClassification;
	}

	/**
	 * 解析事件发生地址信息的信息，这里包括了xy的坐标或者是具体的行政的区划的信息
	 * 
	 * @param element
	 * @param fileabsolutepath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private EventSpaceTime ParsespaceTimeGeolocation(Element element,
			String fileabsolutepath) throws EventMLNotFormatException {
		EventSpaceTime sptEventSpaceTime = new EventSpaceTime();
		List<Element> elements = element.elements();
		if (elements == null || (elements.size() != 1 && elements.size() != 2)) {
			ThrowEventMLNotFormatException(fileabsolutepath, element.getName());
		}
		int admininum = 0;// 记录行政区的个数
		int geoxyhnum = 0;// 记录xyz的个数
		for (Element element2 : elements) {
			if (element2.getName().equals("EventLocation")
					&& element2.getNamespaceURI().equals(SystemConfig.EMLURL)) {
				List<Element> elements2 = element2.elements();
				if (elements2 == null || elements2.size() != 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element2
							.getName());
				}
				Element element3 = elements2.get(0);
				if (element3.getName().equals("AdministrativeLocation")
						&& element3.getNamespaceURI().equals(
								SystemConfig.EMLURL)) {
					if (admininum == 1) {
						ThrowEventMLNotFormatException(fileabsolutepath,
								element3.getName());
					}
					admininum++;
					// 解析行政区的位置信息
					sptEventSpaceTime.setDa(parseGeolocationAddress(element3,
							fileabsolutepath));
					sptEventSpaceTime.setType(1 + sptEventSpaceTime.getType());

				} else if (element3.getName().equals("Position")
						&& element3.getNamespaceURI().equals(
								SystemConfig.SWEURL)) {
					if (geoxyhnum == 1) {
						ThrowEventMLNotFormatException(fileabsolutepath,
								element3.getName());
					}
					geoxyhnum++;
					// 解析地址位置坐标的信息
					sptEventSpaceTime.setLlp(parseGeolocationXY(element3,
							fileabsolutepath));
					sptEventSpaceTime.setType(2 + sptEventSpaceTime.getType());

				}

			} else {
				ThrowEventMLNotFormatException(fileabsolutepath, element2
						.getName());
			}
		}
		return sptEventSpaceTime;
	}

	/**
	 * 解析事件的时空信息
	 * 
	 * @param element
	 * @param fileabsolutepath
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private EventSpaceTime ParseSpaceTime(Element element,
			String fileabsolutepath) throws EventMLNotFormatException {
		TimePeroid tPeroid = new TimePeroid(null, null);
		EventSpaceTime pst = new EventSpaceTime();
		List<Element> elements = element.elements();
		if (elements.size() != 2 && elements.size() != 1) {
			ThrowEventMLNotFormatException(fileabsolutepath, element.getName()
					+ "不规范");
		}
		int eventtimenum = 0;
		int eventspacenum = 0;
		for (Element element2 : elements) {
			if (element2.getName().equals("EventTime")
					&& element2.getNamespaceURI().equals(SystemConfig.EMLURL)) {
				if (eventtimenum == 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element
							.getName()
							+ "中EventTime不规范");
				}
				eventtimenum++;
				// 解析时间段的信息
				tPeroid = ParseSpaceTimeTimePeroid(element2, fileabsolutepath);
			} else if (element2.getName().equals("Geolocation")
					&& element2.getNamespaceURI().equals(SystemConfig.EMLURL)) {
				if (eventspacenum == 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element
							.getName()
							+ "中Geolocation不规范");
				}
				eventspacenum++;
				pst = ParsespaceTimeGeolocation(element2, fileabsolutepath);
			}
		}
		pst.setTp(tPeroid);
		return pst;
	}

	/**
	 * 解析包含经度纬度的事件发生的位置
	 * 
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private LatLongPiar parseGeolocationXY(Element element,
			String fileabsolutepath) throws EventMLNotFormatException {
		LatLongPiar llp = new LatLongPiar();
		List<Element> elements = element.elements();
		if (elements == null || elements.size() != 1) {
			ThrowEventMLNotFormatException(fileabsolutepath, element.getName()
					+ "不规范");
		}
		Element element2 = elements.get(0);
		if (element2.getName().equals("location")
				&& element2.getNamespaceURI().equals(SystemConfig.SWEURL)) {
			List<Element> elements2 = element2.elements();
			if (elements2 != null
					&& elements2.size() == 1
					&& elements2.get(0).getName().equals("Vector")
					&& elements2.get(0).getNamespaceURI().equals(
							SystemConfig.SWEURL)
					&& elements2.get(0).attribute("definition").getData()
							.toString().equals(
									"urn:ogc:def:property:OGC:location")) {
				Element element3 = elements2.get(0);
				List<Element> elements3 = element3.elements();
				if (elements3 != null && elements3.size() == 3) {
					int latnum = 0;
					int lonnum = 0;
					int heinum = 0;

					for (Element e : elements3) {
						System.out.println(e.asXML());
						if (e.getName().equals("coordinate")
								&& e.getNamespaceURI().equals(
										SystemConfig.SWEURL)) {
							if (e.attribute("name").getData().toString()
									.equals("latitude")) {
								if (latnum == 1) {
									ThrowEventMLNotFormatException(
											fileabsolutepath, e.getName()
													+ "不规范");
								}
								latnum++;
								llp.setPointy(parseCoordinate(e, "latitude",
										"y",
										"urn:ogc:def:property:OGC:latitude",
										"deg", fileabsolutepath));
							} else if (e.attribute("name").getData().toString()
									.equals("longitude")) {
								if (lonnum == 1) {
									ThrowEventMLNotFormatException(
											fileabsolutepath, e.getName()
													+ "不规范");
								}
								lonnum++;
								llp.setPointx(parseCoordinate(e, "longitude",
										"x",
										"urn:ogc:def:property:OGC:longitude",
										"deg", fileabsolutepath));
							} else if (e.attribute("name").getData().toString()
									.equals("altitude")) {
								if (heinum == 1) {
									ThrowEventMLNotFormatException(
											fileabsolutepath, e.getName()
													+ "不规范");
								}
								heinum++;
								llp.setGaodu(parseCoordinate(e, "altitude",
										"z",
										"urn:ogc:def:property:OGC:altitude",
										"km", fileabsolutepath));

							} else {
								ThrowEventMLNotFormatException(
										fileabsolutepath, e.getName() + "不规范");
							}

						} else {
							ThrowEventMLNotFormatException(fileabsolutepath, e
									.getName()
									+ "不规范");
						}
					}
				} else {
					ThrowEventMLNotFormatException(fileabsolutepath, element3
							.getName()
							+ "不规范");
				}
			} else {
				ThrowEventMLNotFormatException(fileabsolutepath, element2
						.getName()
						+ "不规范");
			}
		} else {
			ThrowEventMLNotFormatException(fileabsolutepath, element2.getName()
					+ "不规范");
		}
		System.out.println(llp.getGaodu());
		System.out.println(llp.getPointx());
		System.out.println(llp.getPointy());
		return llp;
	}

	/**
	 * 解析坐标位置中的各个位置信息
	 * 
	 * @param element
	 * @param nameproperty
	 * @param axisIDname
	 * @param definitionName
	 * @param codeName
	 * @param fileabsolutePath
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private Double parseCoordinate(Element e, String nameproperty,
			String axisIDname, String definitionName, String codeName,
			String fileabsolutepath) throws EventMLNotFormatException {
		if (e.getName().equals("coordinate")
				&& e.getNamespaceURI().equals(SystemConfig.SWEURL)
				&& e.attribute("name").getData().toString()
						.equals(nameproperty)) {
			List<Element> elements4 = e.elements();
			if (elements4 != null
					&& elements4.size() == 1
					&& elements4.get(0).getName().equals("Quantity")
					&& elements4.get(0).getNamespaceURI().equals(
							SystemConfig.SWEURL)
					&& elements4.get(0).attribute("axisID").getData()
							.toString().equals(axisIDname)
					&& elements4.get(0).attribute("definition").getData()
							.toString().equals(definitionName)) {
				List<Element> elements5 = elements4.get(0).elements();
				if (elements5 != null && elements5.size() == 2) {
					int unitnum = 0;
					int valuenum = 0;
					for (Element e2 : elements5) {
						if (e2.getName().equals("uom")
								&& e2.getNamespaceURI().equals(
										SystemConfig.SWEURL)
								&& e2.attribute("code").getData().toString()
										.equals(codeName)) {
							if (unitnum == 1) {
								ThrowEventMLNotFormatException(
										fileabsolutepath, e2.getName() + "不规范");
							}
							unitnum++;
						} else if (e2.getName().equals("value")
								&& e2.getNamespaceURI().equals(
										SystemConfig.SWEURL)) {
							if (valuenum == 1) {
								ThrowEventMLNotFormatException(
										fileabsolutepath, e2.getName() + "不规范");
							}
							valuenum++;
							String st = e2.getData() + "";
							try {
								return Double.parseDouble(st);
							} catch (NumberFormatException e1) {
								ThrowEventMLNotFormatException(
										fileabsolutepath, e2.getName() + "不规范");
							}
						} else {
							ThrowEventMLNotFormatException(fileabsolutepath, e2
									.getName()
									+ "不规范");
						}
					}
				} else {
					ThrowEventMLNotFormatException(fileabsolutepath, elements4
							.get(0).getName()
							+ "不规范");
				}
			} else {
				ThrowEventMLNotFormatException(fileabsolutepath, e.getName()
						+ "不规范");
			}
		} else {
			ThrowEventMLNotFormatException(fileabsolutepath, e.getName()
					+ "不规范");
		}
		return null;
	}

	/**
	 * 解析包含了具体行政区划的事件发生的地址
	 * 
	 * @return
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private DetailAddress parseGeolocationAddress(Element element,
			String fileabsolutepath) throws EventMLNotFormatException {
		DetailAddress dAddress = new DetailAddress();
		List<Element> elements = element.elements();
		if (elements == null || elements.size() != 5) {
			ThrowEventMLNotFormatException(fileabsolutepath, element.getName()
					+ "不规范");
		}
		int x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0;
		for (Element e : elements) {
			if (e.getName().equals("deliveryPoint")
					&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
				if (x1 == 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element
							.getName()
							+ "中deliveryPoint不规范");
				}
				x1++;
				String value = (String) e.getData();
				try {
					StringUtil.checkStringIsNotNULLAndEmptyMethod(value,
							"deliveryPoint");
				} catch (NullZeroException e1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element
							.getName()
							+ "中deliveryPoint取值不规范");
				}
				dAddress.setDeliveryPoint(value);

			} else if (e.getName().equals("administrativeArea")
					&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
				if (x2 == 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element
							.getName()
							+ "中administrativeArea不规范");
				}
				x2++;
				String value = (String) e.getData();
				try {
					StringUtil.checkStringIsNotNULLAndEmptyMethod(value,
							"administrativeArea");
				} catch (NullZeroException e1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element
							.getName()
							+ "中administrativeArea取值不规范");
				}
				dAddress.setAdministrativeArea(value);
			} else if (e.getName().equals("city")
					&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
				if (x3 == 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element
							.getName()
							+ "中city不规范");
				}
				x3++;
				String value = (String) e.getData();
				try {
					StringUtil
							.checkStringIsNotNULLAndEmptyMethod(value, "city");
				} catch (NullZeroException e1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element
							.getName()
							+ "中city取值不规范");
				}
				dAddress.setCity(value);
			} else if (e.getName().equals("province")
					&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
				if (x4 == 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element
							.getName()
							+ "中province不规范");
				}
				x4++;
				String value = (String) e.getData();
				try {
					StringUtil.checkStringIsNotNULLAndEmptyMethod(value,
							"province");
				} catch (NullZeroException e1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element
							.getName()
							+ "中province取值不规范");
				}
				dAddress.setProvince(value);
			} else if (e.getName().equals("country")
					&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
				if (x5 == 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element
							.getName()
							+ "中country不规范");
				}
				x5++;
				String value = (String) e.getData();
				try {
					StringUtil.checkStringIsNotNULLAndEmptyMethod(value,
							"country");
				} catch (NullZeroException e1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element
							.getName()
							+ "中country取值不规范");
				}
				dAddress.setCountry(value);
			} else {
				// 异常信息
				ThrowEventMLNotFormatException(fileabsolutepath, element
						.getName()
						+ "不规范");
			}
		}
		return dAddress;
	}

	/**
	 * 解析SpaceTime中的事件时间段信息
	 * 
	 * @param element
	 * @param fileabsolutepath
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private TimePeroid ParseSpaceTimeTimePeroid(Element element,
			String fileabsolutepath) throws EventMLNotFormatException {
		TimePeroid tPeroid = new TimePeroid(null, null);
		List<Element> elements = element.elements();
		if (elements == null || elements.size() != 1) {
			ThrowEventMLNotFormatException(fileabsolutepath, element.getName()
					+ "不规范");
		}
		Element element2 = elements.get(0);
		if (!element2.getName().equals("TimePeriod")
				|| !element2.getNamespaceURI().equals(SystemConfig.GMLURL)) {
			ThrowEventMLNotFormatException(fileabsolutepath, element2.getName()
					+ "不规范");
		}
		List<Element> element3 = element2.elements();
		if (element3 == null || element3.size() != 2) {
			ThrowEventMLNotFormatException(fileabsolutepath, element2.getName()
					+ "不规范");
		}
		int beginnum = 0;
		int endnum = 0;
		for (Element e : element3) {
			if (e.getName().equals("beginPosition")
					&& e.getNamespaceURI().equals(SystemConfig.GMLURL)) {
				if (beginnum == 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element2
							.getName()
							+ "不规范");
				}
				beginnum++;
				// 字符串转日期转换代码
				Date startime = null;
				try {
					startime = DateOperationUtil.StringToDate(e.getData() + "",
							"yyyy/mm/dd hh:mm:ss");
					tPeroid.setStartime(startime);
				} catch (Exception e1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element2
							.getName()
							+ "中开始时间取值不规范");
				}

			} else if (e.getName().equals("endPosition")
					&& e.getNamespaceURI().equals(SystemConfig.GMLURL)) {
				if (endnum == 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element2
							.getName()
							+ "不规范");
				}
				endnum++;
				// 字符串转日期转换代码
				Date startime = null;
				try {
					startime = DateOperationUtil.StringToDate(e.getData() + "",
							"yyyy/mm/dd hh:mm:ss");
					tPeroid.setEndtime(startime);
				} catch (Exception e1) {
					ThrowEventMLNotFormatException(fileabsolutepath, element2
							.getName()
							+ "中结束时间取值不规范");
				}
			} else {
				ThrowEventMLNotFormatException(fileabsolutepath, element2
						.getName()
						+ "不规范");
			}
		}
		return tPeroid;

	}

	/**
	 * 解析identification信息,并将提取到的信息全部保存到传感器中去
	 * 
	 * @param ideElement
	 * @throws EventMLNotFormatException
	 */
	@SuppressWarnings("unchecked")
	private EventIdentification ParseIdentification(Element ideElement,
			String fileabsolutepath) throws EventMLNotFormatException {
		EventIdentification eventIdentification = new EventIdentification();
		List<Element> elements = ideElement.elements();
		System.out.println(ideElement.asXML());
		if (elements == null || elements.size() != 3) {
			ThrowEventMLNotFormatException(fileabsolutepath, ideElement
					.getName()
					+ "不规范");

		}
		int idnum = 0;
		int namenum = 0;
		int descnum = 0;
		for (Element e : elements) {
			if (e.getName().equals("eventID")
					&& e.getNamespaceURI().equals(SystemConfig.EMLURL)) {
				if (idnum == 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, ideElement
							.getName()
							+ "不规范");
				}
				idnum++;
				String eventid = e.getData().toString() + "";
				if (eventid.trim().equals("")) {
					ThrowEventMLNotFormatException(fileabsolutepath, ideElement
							.getName()
							+ "中eventID取值不规范");
				}
				eventIdentification.setEventid(eventid.trim());
			} else if (e.getName().equals("name")
					&& e.getNamespaceURI().equals(SystemConfig.GMLURL)) {
				if (namenum == 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, ideElement
							.getName()
							+ "不规范");
				}
				namenum++;
				String name = e.getData().toString();
				if (name == null || name.trim().equals("")) {
					ThrowEventMLNotFormatException(fileabsolutepath, ideElement
							.getName()
							+ "中name取值不规范");
				}
				eventIdentification.setEventname(name.trim());
			} else if (e.getName().equals("description")
					&& e.getNamespaceURI().equals(SystemConfig.GMLURL)) {
				if (descnum == 1) {
					ThrowEventMLNotFormatException(fileabsolutepath, ideElement
							.getName()
							+ "不规范");
				}
				descnum++;
				String description = e.getData().toString();
				if (description == null || description.trim().equals("")) {
					ThrowEventMLNotFormatException(fileabsolutepath, ideElement
							.getName()
							+ "中description取值不规范");
				}
				eventIdentification.setEventdesc(description.trim());
			}
		}
		return eventIdentification;

	}

	/**
	 * 抛出eventml不规范
	 * 
	 * @param file
	 * @throws EventMLNotFormatException
	 */
	private void ThrowEventMLNotFormatException(File file)
			throws EventMLNotFormatException {
		throw new EventMLNotFormatException("文档" + file.getAbsolutePath()
				+ "不规范");
	}

	/**
	 * 抛出eventml不规范
	 * 
	 * @param file
	 * @throws EventMLNotFormatException
	 */
	private void ThrowEventMLNotFormatException()
			throws EventMLNotFormatException {
		throw new EventMLNotFormatException("文档不规范");
	}

	/**
	 * 抛出eventml不规范
	 * 
	 * @param filepath
	 * @param causeStr
	 * @throws EventMLNotFormatException
	 */
	private void ThrowEventMLNotFormatException(String filepath, String causeStr)
			throws EventMLNotFormatException {
		throw new EventMLNotFormatException("文档" + filepath + "不规范: "
				+ causeStr);
	}
}
