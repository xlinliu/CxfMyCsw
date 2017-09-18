package com.event.commonutils;

import java.util.List;
import com.csw.exceptions.DirectoryNotExistException;
import com.csw.exceptions.XMLnotFormatException;
import com.event.customTypes.EventBasicInfo;

/**
 * ��ϵͳ�е��¼���Ϣ��ȫд�뵽���õ��ࡾEventInfoConfig����
 * 
 * @author yxliang
 * 
 */
public class EventInfoWriter {
	private static EventXMLDBUtil mxb = EventXMLDBUtil.getInstance();

	/**
	 * ɾ��ָ��berkeley�е�docname��Ӧ��modelbasicinfo��Ϣ
	 * 
	 * @param docname
	 */
	public static void deleteDocumentFromEventInfo(String docname) {
		for (EventBasicInfo mbi : EventInfoConfig.getEbis()) {
			if (mbi.getDocname().equals(docname)) {
				EventInfoConfig.getEbis().remove(mbi);
				EventInfoConfig.getDocnames().remove(docname);
				break;
			}
		}
	}

	/**
	 * ���������ĵ��Ļ�����Ϣ���뵽EventBasicInfo��
	 * 
	 * @param docname
	 * @throws XMLnotFormatException
	 */
	public static void writeDocument2EventBasicInfo(String docname)
			throws XMLnotFormatException {
		EventBasicInfo mbi = new EventBasicInfo();
		mbi.setDocname(docname);
		writeFirstPart2EventInfo(mxb, mbi, docname);
		writeSecondPart2EventInfo(mxb, mbi, docname);
		writeThirdPart2EventInfo(mxb, mbi, docname);
		writeFourPart2EventInfo(mxb, mbi, docname);
		EventInfoConfig.getEbis().add(mbi);
	}

	@SuppressWarnings("unchecked")
	private static void writeFourPart2EventInfo(EventXMLDBUtil mxb2,
			EventBasicInfo mbi, String docname) throws XMLnotFormatException {
		// ����������������Ԫ
		List<String> list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_totalCasualties_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setTotalcasualties_unit(list.get(0));
		}
		// ��������������
		list = mxb2.queryDocumentOfEvent("four_event_casualty_totalCasualties",
				docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEcli_Recovery().setTotalcasualties(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ��������������Ԫ
		list = mxb2.queryDocumentOfEvent("four_event_casualty_Death_unit",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setDeathNumber_unit(list.get(0));
		}
		// ��������������
		list = mxb2.queryDocumentOfEvent("four_event_casualty_totalCasualties",
				docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEcli_Recovery().setDeathNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ��������������ϸ������ַ
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_death_detailedDeathInfoURL", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setDetailedDeathInfoURL(list.get(0));
		}
		// ��������������Ԫ
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_totalInjuredNumber_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setTotalInjuredNumber_unit(list.get(0));
		}
		// ������������
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_totalInjuredNumber", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEcli_Recovery().setTotalInjuredNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ������������������Ԫ
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_seriouslyInjuredNumber_unit",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setSeriouslyInjuredNumber_unit(list.get(0));
		}
		// ����������������
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_seriouslyInjuredNumber", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEcli_Recovery().setSeriouslyInjuredNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ����������������Ԫ
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_minorInjuredNumber_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setMinorInjuredNumber_unit(list.get(0));
		}
		// ��������������
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_minorInjuredNumber", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEcli_Recovery().setMinorInjuredNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ��������������ϸ������ַ
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_detailedInjuriesInfoURL", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setDetailedInjuriesInfoURL(list.get(0));
		}
		// ����ʧ��������Ԫ
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Missing_missingNumber_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setMissingNumber_unit(list.get(0));
		}
		// ����ʧ������
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Missing_missingNumber", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEcli_Recovery().setMissingNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ����ʧ��������ϸ������ַ
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Missing_detailedMissingInfoURL", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setDetailedMissingInfoURL(list.get(0));
		}
		// �ܾ�����ʧ��ʧ��Ԫ
		list = mxb2
				.queryDocumentOfEvent(
						"four_event_economicLoss_EconomicLossInfo_totalLossesNumber_unit",
						docname);
		if (list != null && list.size() != 0) {
			mbi.getEemli_Recovery().setTotalLossesNumber_unit(list.get(0));
		}
		// �ܾ�����ʧ��ʧ
		list = mxb2.queryDocumentOfEvent(
				"four_event_economicLoss_EconomicLossInfo_totalLossesNumber",
				docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEemli_Recovery().setTotalLossesNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ֱ�Ӿ�����ʧ��ʧ��Ԫ
		list = mxb2.queryDocumentOfEvent(
				"four_event_economicLoss_EconomicLossInfo_DirectLoss_unit",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEemli_Recovery().setDirectLossesNumber_unit(list.get(0));
		}
		// ֱ�Ӿ�����ʧ��ʧ
		list = mxb2.queryDocumentOfEvent(
				"four_event_economicLoss_EconomicLossInfo_DirectLoss", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEemli_Recovery().setDirectLossesNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ֱ�Ӿ�����ʧurl
		list = mxb2
				.queryDocumentOfEvent(
						"four_event_economicLoss_EconomicLossInfo_DirectLoss_detailedDirectLossesInfoURL",
						docname);
		if (list != null && list.size() != 0) {
			mbi.getEemli_Recovery().setDetailedDirectLossesInfoURL(list.get(0));
		}
		// ��Ӿ�����ʧ��ʧ��Ԫ
		list = mxb2
				.queryDocumentOfEvent(
						"four_event_economicLoss_EconomicLossInfo_indirectLossesNumber_unit",
						docname);
		if (list != null && list.size() != 0) {
			mbi.getEemli_Recovery().setIndirectLossesNumber_unit(list.get(0));
		}
		// ��Ӿ�����ʧ��ʧ
		list = mxb2
				.queryDocumentOfEvent(
						"four_event_economicLoss_EconomicLossInfo_indirectLossesNumber",
						docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEemli_Recovery().setIndirectLossesNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ��Ӿ�����ʧurl
		list = mxb2
				.queryDocumentOfEvent(
						"four_event_economicLoss_EconomicLossInfo_indirectLosses_detailedIndirectLossesInfoURL",
						docname);
		if (list != null && list.size() != 0) {
			mbi.getEemli_Recovery().setDetailedIndirectLossesInfoURL(
					list.get(0));
		}
		//
		list = mxb2
				.queryDocumentOfEvent(
						"four_event_otherInfluence_InvisibleEffect_publicPsychologyChanges",
						docname);
		if (list != null && list.size() != 0) {
			mbi.getEifi_Recovery().setPublicPsychologyChanges(list.get(0));
		}
	}

	/**
	 * �Ե�������
	 * 
	 * @param mxb2
	 * @param mbi
	 * @param eat
	 */
	@SuppressWarnings("unchecked")
	private static void writeThirdPart2EventInfo(EventXMLDBUtil mxb2,
			EventBasicInfo mbi, String docname) {
		// ���õ����׶ε�rescuerTeam
		List<String> list = mxb2.queryDocumentOfEvent(
				"third_event_resources_rescuerTeam", docname);
		if (list != null && list.size() != 0) {
			mbi.getEri_Response().setEvent_rescurTeam(list.get(0));
		}
		// ���õ����׶ε��߼���ַ
		list = mxb2.queryDocumentOfEvent(
				"third_event_resources_LogisticsPoint", docname);
		if (list != null && list.size() != 0) {
			mbi.getEri_Response().setLogisticsPoint(list.get(0));
		}
		// ���õ����׶��޸���·URL
		list = mxb2.queryDocumentOfEvent(
				"third_event_stateReport_repairedRoadsURL", docname);
		if (list != null && list.size() != 0) {
			mbi.getEsri_Response().setRepairedRoadsURL(list.get(0));
		}
		// ���õ����׶���Ѱ����URL
		list = mxb2.queryDocumentOfEvent(
				"third_event_stateReport_searchedRegionsURL", docname);
		if (list != null && list.size() != 0) {
			mbi.getEsri_Response().setSearchedRegionsURL(list.get(0));
		}
		// ���õ����׶�repairedFortificationsURL
		list = mxb2.queryDocumentOfEvent(
				"third_event_stateReport_repairedFortificationsURL", docname);
		if (list != null && list.size() != 0) {
			mbi.getEsri_Response().setRepairedFortificationsURL(list.get(0));
		}
		// ���õ����׶�factorsEstimation
		list = mxb2.queryDocumentOfEvent(
				"third_event_trendPrediction_factorsEstimation", docname);
		if (list != null && list.size() != 0) {
			mbi.getEtpt_Response().setFactorsEstimation(list.get(0));
		}
		// ���õ����׶�situationPrediction
		list = mxb2.queryDocumentOfEvent(
				"third_event_trendPrediction_situationPrediction", docname);
		if (list != null && list.size() != 0) {
			mbi.getEtpt_Response().setSituationPredication(list.get(0));
		}
	}

	/**
	 * �Եڶ�����
	 * 
	 * @param mxb2
	 * @param mbi
	 * @param eat
	 * @throws XMLnotFormatException
	 */
	@SuppressWarnings("unchecked")
	private static void writeSecondPart2EventInfo(EventXMLDBUtil mxb2,
			EventBasicInfo mbi, String docname) throws XMLnotFormatException {

		// ���õڶ��׶εĿ�ʼʱ��
		List<String> list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_begintime", docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation().setEvent_begintime(list.get(0));
		}
		// ���õڶ��׶εĽ���ʱ��
		list = mxb2.queryDocumentOfEvent("second_event_prediction_endtime",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation().setEvent_endtime(list.get(0));
		}
		// �����¼���δ֪�Ƿ��ǹ̶���
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_positionfixed", docname);
		if (list != null && list.size() != 0) {
			if (list.get(0).equals("true")) {
				mbi.getEpi_Preparation().setEventlocationfixed(true);
			} else {
				mbi.getEpi_Preparation().setEventlocationfixed(false);
			}
		}
		// �����¼��Ĳο�����ϵͳ
		list = mxb2
				.queryDocumentOfEvent(
						"second_event_prediction_eventlocation_referenceFrame",
						docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation()
					.setEventlocationreferenceFrame(list.get(0));
		}
		// ��ȡpointy�ĵ�λ
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_pointy_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation().setEventposition_y_unit(list.get(0));
		}
		// ��ȡpointy
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_pointy", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEpi_Preparation().setEventposition_y(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ��ȡpointx�ĵ�λ
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_pointx_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation().setEventposition_x_unit(list.get(0));
		}
		// ��ȡpointx
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_pointx", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEpi_Preparation().setEventposition_x(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ��ȡpointz�ĵ�λ
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_pointz_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation().setEventposition_z_unit(list.get(0));
		}
		// ��ȡpointz
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_pointz", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEpi_Preparation().setEventposition_z(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// �����¼��Ŀ���������
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_possibileSeverity", docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation().setEventpossibleSeverity(list.get(0));
		}

		// �����¼�Ԥ����ʱ��
		list = mxb2.queryDocumentOfEvent("second_event_alert_timeposition",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_time(list.get(0));
		}
		// �����¼�Ԥ����״̬
		list = mxb2.queryDocumentOfEvent("second_event_alert_alertStatus",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_status(list.get(0));
		}
		// �����¼�Ԥ��������
		list = mxb2.queryDocumentOfEvent("second_event_alert_alertMessageType",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_messageType(list.get(0));
		}
		// �����¼�Ԥ���ķ�Χ
		list = mxb2.queryDocumentOfEvent("second_event_alert_alertScope",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_scope(list.get(0));
		}
		// �����¼�Ԥ����ϵ��ϸ��ַ
		list = mxb2.queryDocumentOfEvent(
				"second_event_alert_alertArea_deliveryPoint", docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_area_deliverypoint(list.get(0));
		}
		// �����¼�Ԥ����������
		list = mxb2.queryDocumentOfEvent(
				"second_event_alert_alertArea_administrativeArea", docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_area_administrativeArea(
					list.get(0));
		}
		// �����¼�Ԥ���ĳ���
		list = mxb2.queryDocumentOfEvent("second_event_alert_alertArea_city",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_area_city(list.get(0));
		}
		// �����¼�Ԥ����ʡ��
		list = mxb2.queryDocumentOfEvent(
				"second_event_alert_alertArea_province", docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_area_province(list.get(0));
		}
		// �����¼�Ԥ���Ĺ���
		list = mxb2.queryDocumentOfEvent(
				"second_event_alert_alertArea_country", docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_area_country(list.get(0));
		}
		// �����¼�Ԥ������Ϣ
		list = mxb2.queryDocumentOfEvent("second_event_alert_alertMessage",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_message(list.get(0));
		}
	}

	/**
	 * ���¼���һ����д�����
	 * 
	 * @param mxb2
	 * @param mbi
	 * @param eat
	 * @throws XMLnotFormatException
	 */
	@SuppressWarnings("unchecked")
	private static void writeFirstPart2EventInfo(EventXMLDBUtil mxb2,
			EventBasicInfo mbi, String docname) throws XMLnotFormatException {
		// �����¼��Ŀ�ʼʱ��
		List<String> list = mxb2.queryDocumentOfEvent(
				"first_event_eventtime_begin", docname);
		if (list != null && list.size() != 0) {
			mbi.setEventBeginPosition_First(list.get(0));
		}
		// �����¼��Ľ���ʱ��
		list = mxb2.queryDocumentOfEvent("first_event_eventtime_end", docname);
		if (list != null && list.size() != 0) {
			mbi.setEventEndPosition_First(list.get(0));
		}
		// �����¼��Ĺ۲������ļ�
		list = mxb2.queryDocumentOfEvent("first_event_causalVector_leaf",
				docname);
		mbi.getEvent_causalVectorLeaf_First().addAll(list);
		// �����¼���identification����
		writeEventAttributeIdentification2EventInfo(mxb2, mbi, docname);
		// �����¼���eventlocation����
		writeEventAttributeLocation2EventInfo(mxb2, mbi, docname);
		// �����¼���eventclassification����
		writeEventClassification2EventInfo(mxb2, mbi, docname);
		// �����¼���contact����
		writeEventContact2EventInfo(mxb2, mbi, docname);
		writeEventService2EventInfo(mxb2, mbi, docname);
	}

	@SuppressWarnings("unchecked")
	private static void writeEventService2EventInfo(EventXMLDBUtil mxb2,
			EventBasicInfo mbi, String docname) {
		// �����¼������ķ��������
		List<String> list = mxb2.queryDocumentOfEvent(
				"first_event_service_servicename", docname);
		if (list != null && list.size() != 0) {
			mbi.getEsi_First().setEventServiceName(list.get(0));
		}
		// �����¼������ķ��������
		list = mxb2.queryDocumentOfEvent("first_event_service_serviceType",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEsi_First().setEventServiceType(list.get(0));
		}
		// �����¼������ķ���ĵ�ַ
		list = mxb2.queryDocumentOfEvent("first_event_service_serviceAddress",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEsi_First().setEventserviceAddress(list.get(0));
		}
		// �����¼������ķ���ĸ�����Ϣ
		list = mxb2.queryDocumentOfEvent("first_event_service_moreInfo",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEsi_First().setEventMoreInfo(list.get(0));
		}
	}

	@SuppressWarnings("unchecked")
	private static void writeEventContact2EventInfo(EventXMLDBUtil mxb2,
			EventBasicInfo mbi, String docname) {
		// �����¼���ϵ�˵ĸ�������
		List<String> list = mxb2.queryDocumentOfEvent(
				"first_event_contact_individualName", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setIndividualname(list.get(0));
		}
		// �����¼���ϵ��λ����
		list = mxb2.queryDocumentOfEvent(
				"first_event_contact_organizationName", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setOrganizationname(list.get(0));
		}
		// �����¼���ϵ��λ�绰
		list = mxb2.queryDocumentOfEvent("first_event_contact_phone_voice",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setVoice(list.get(0));
		}
		// �����¼���ϵ��λ����
		list = mxb2.queryDocumentOfEvent("first_event_contact_phone_fascimile",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setFacsimile(list.get(0));
		}
		// �����¼���ϵ��λ�����ַ
		list = mxb2.queryDocumentOfEvent(
				"first_event_contact_phone_address_deliverypoint", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setDeliverypoint(list.get(0));
		}
		// �����¼���ϵ��λ���ڳ���
		list = mxb2.queryDocumentOfEvent(
				"first_event_contact_phone_address_city", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setCity(list.get(0));
		}
		// �����¼���ϵ��λ����������
		list = mxb2
				.queryDocumentOfEvent(
						"first_event_contact_phone_address_administrativeArea",
						docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setAdministrativeArea(list.get(0));
		}
		// �����¼���ϵ��λ������������
		list = mxb2.queryDocumentOfEvent(
				"first_event_contact_phone_address_postalCode", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setPostalCode(list.get(0));
		}
		// �����¼���ϵ��λ�Ĺ���
		list = mxb2.queryDocumentOfEvent(
				"first_event_contact_phone_address_country", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setCountry(list.get(0));
		}
		// �����¼���ϵ��λ�Ĺ���
		list = mxb2.queryDocumentOfEvent(
				"first_event_contact_phone_address_electronicMailAddress",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setElectronicMailAddress(list.get(0));
		}
	}

	@SuppressWarnings("unchecked")
	private static void writeEventClassification2EventInfo(EventXMLDBUtil mxb2,
			EventBasicInfo mbi, String docname) {
		// �����¼��ķ���
		List<String> list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_classification_eventcategory", docname);
		if (list != null && list.size() != 0) {
			mbi.getEci_First().setEventcategory(list.get(0));
		}
		// �����¼����¼�ģʽ
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_classification_eventPattern", docname);
		if (list != null && list.size() != 0) {
			mbi.getEci_First().setEventPattern(list.get(0));
		}
		// �����¼��Ĵ�������
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_classification_eventInheritance",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEci_First().setEventInheritance(list.get(0));
		}
		// �����¼��Ľ����̶�
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_classification_eventUrgency", docname);
		if (list != null && list.size() != 0) {
			mbi.getEci_First().setEventUrgency(list.get(0));
		}
		// �����¼������س̶�
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_classification_eventSeverity", docname);
		if (list != null && list.size() != 0) {
			mbi.getEci_First().setEventSeverity(list.get(0));
		}
		// �����¼���ȷ����
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_classification_eventCertainty", docname);
		if (list != null && list.size() != 0) {
			mbi.getEci_First().setEventCertainty(list.get(0));
		}
	}

	@SuppressWarnings("unchecked")
	private static void writeEventAttributeLocation2EventInfo(
			EventXMLDBUtil mxb2, EventBasicInfo mbi, String docname)
			throws XMLnotFormatException {
		// �����¼���δ֪�Ƿ��ǹ̶���
		List<String> list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_positionfixed", docname);
		if (list != null && list.size() != 0) {
			if (list.get(0).equals("true")) {
				mbi.getEli_First().setEventlocationfixed(true);
			} else {
				mbi.getEli_First().setEventlocationfixed(false);
			}
		}
		// �����¼��Ĳο�����ϵͳ
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_referenceFrame", docname);
		if (list != null && list.size() != 0) {
			mbi.getEli_First().setEventlocationreferenceFrame(list.get(0));
		}
		// ��ȡpointy�ĵ�λ
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_pointy_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEli_First().setEventposition_y_unit(list.get(0));
		}
		// ��ȡpointy
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_pointy", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEli_First().setEventposition_y(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ��ȡpointx�ĵ�λ
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_pointx_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEli_First().setEventposition_x_unit(list.get(0));
		}
		// ��ȡpointy
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_pointx", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEli_First().setEventposition_x(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
		// ��ȡpointz�ĵ�λ
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_pointz_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEli_First().setEventposition_z_unit(list.get(0));
		}
		// ��ȡpointz
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_pointz", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEli_First().setEventposition_z(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event�ļ��е�λ�ø�ʽ����ȷ���޷�ת��ΪDouble����");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void writeEventAttributeIdentification2EventInfo(
			EventXMLDBUtil mxb2, EventBasicInfo mbi, String docname) {
		// �����¼��ı�ʶ��
		List<String> list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_identification_eventid", docname);
		if (list != null && list.size() != 0) {
			mbi.getEfinfo_First().setEventID(list.get(0));
		}
		// �����¼�������
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_identification_name", docname);
		if (list != null && list.size() != 0) {
			mbi.getEfinfo_First().setEventname(list.get(0));
		}
		// �����¼���period
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_identification_eventperoid", docname);
		if (list != null && list.size() != 0) {
			mbi.getEfinfo_First().setEventPeroid(list.get(0));
		}
		// �����¼���description
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_identification_description", docname);
		if (list != null && list.size() != 0) {
			mbi.getEfinfo_First().setEventDescription(list.get(0));
		}
		// �����¼��Ĵ���״̬
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_identification_processStatus", docname);
		if (list != null && list.size() != 0) {
			mbi.getEfinfo_First().setProcessStatus(list.get(0));
		}
	}

	/**
	 * ����Ϣȫ��д�뵽ModelBasicInfo��
	 * 
	 * @throws DirectoryNotExistException
	 * 
	 * @throws XMLnotFormatException
	 */
	public static void write2EventInfo() throws DirectoryNotExistException,
			XMLnotFormatException {
		for (String document : mxb.getAllDocumentName()) {
			writeDocument2EventBasicInfo(document);
		}
	}

	/**
	 * ���ĵ���ȫ������Ϣд�뵽modelinfo��
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2EventName() throws DirectoryNotExistException {
		EventInfoConfig.getDocnames().addAll(mxb.getAllDocumentName());
	}
}
