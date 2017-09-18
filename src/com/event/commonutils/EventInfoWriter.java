package com.event.commonutils;

import java.util.List;
import com.csw.exceptions.DirectoryNotExistException;
import com.csw.exceptions.XMLnotFormatException;
import com.event.customTypes.EventBasicInfo;

/**
 * 将系统中的事件信息完全写入到配置的类【EventInfoConfig】中
 * 
 * @author yxliang
 * 
 */
public class EventInfoWriter {
	private static EventXMLDBUtil mxb = EventXMLDBUtil.getInstance();

	/**
	 * 删除指定berkeley中的docname对应的modelbasicinfo信息
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
	 * 将新增的文档的基本信息加入到EventBasicInfo中
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
		// 设置总伤亡人数单元
		List<String> list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_totalCasualties_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setTotalcasualties_unit(list.get(0));
		}
		// 设置总伤亡人数
		list = mxb2.queryDocumentOfEvent("four_event_casualty_totalCasualties",
				docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEcli_Recovery().setTotalcasualties(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 设置死亡人数单元
		list = mxb2.queryDocumentOfEvent("four_event_casualty_Death_unit",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setDeathNumber_unit(list.get(0));
		}
		// 设置总伤亡人数
		list = mxb2.queryDocumentOfEvent("four_event_casualty_totalCasualties",
				docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEcli_Recovery().setDeathNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 设置死亡人数详细报道地址
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_death_detailedDeathInfoURL", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setDetailedDeathInfoURL(list.get(0));
		}
		// 设置受伤人数单元
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_totalInjuredNumber_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setTotalInjuredNumber_unit(list.get(0));
		}
		// 设置受伤人数
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_totalInjuredNumber", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEcli_Recovery().setTotalInjuredNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 设置严重受伤人数单元
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_seriouslyInjuredNumber_unit",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setSeriouslyInjuredNumber_unit(list.get(0));
		}
		// 设置严重受伤人数
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_seriouslyInjuredNumber", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEcli_Recovery().setSeriouslyInjuredNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 设置轻受伤人数单元
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_minorInjuredNumber_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setMinorInjuredNumber_unit(list.get(0));
		}
		// 设置轻受伤人数
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_minorInjuredNumber", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEcli_Recovery().setMinorInjuredNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 设置受伤人数详细报道地址
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Injury_detailedInjuriesInfoURL", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setDetailedInjuriesInfoURL(list.get(0));
		}
		// 设置失踪人数单元
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Missing_missingNumber_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setMissingNumber_unit(list.get(0));
		}
		// 设置失踪人数
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Missing_missingNumber", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEcli_Recovery().setMissingNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 设置失踪人数详细报道地址
		list = mxb2.queryDocumentOfEvent(
				"four_event_casualty_Missing_detailedMissingInfoURL", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcli_Recovery().setDetailedMissingInfoURL(list.get(0));
		}
		// 总经济损失损失单元
		list = mxb2
				.queryDocumentOfEvent(
						"four_event_economicLoss_EconomicLossInfo_totalLossesNumber_unit",
						docname);
		if (list != null && list.size() != 0) {
			mbi.getEemli_Recovery().setTotalLossesNumber_unit(list.get(0));
		}
		// 总经济损失损失
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
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 直接经济损失损失单元
		list = mxb2.queryDocumentOfEvent(
				"four_event_economicLoss_EconomicLossInfo_DirectLoss_unit",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEemli_Recovery().setDirectLossesNumber_unit(list.get(0));
		}
		// 直接经济损失损失
		list = mxb2.queryDocumentOfEvent(
				"four_event_economicLoss_EconomicLossInfo_DirectLoss", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEemli_Recovery().setDirectLossesNumber(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 直接经济损失url
		list = mxb2
				.queryDocumentOfEvent(
						"four_event_economicLoss_EconomicLossInfo_DirectLoss_detailedDirectLossesInfoURL",
						docname);
		if (list != null && list.size() != 0) {
			mbi.getEemli_Recovery().setDetailedDirectLossesInfoURL(list.get(0));
		}
		// 间接经济损失损失单元
		list = mxb2
				.queryDocumentOfEvent(
						"four_event_economicLoss_EconomicLossInfo_indirectLossesNumber_unit",
						docname);
		if (list != null && list.size() != 0) {
			mbi.getEemli_Recovery().setIndirectLossesNumber_unit(list.get(0));
		}
		// 间接经济损失损失
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
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 间接经济损失url
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
	 * 对第三部分
	 * 
	 * @param mxb2
	 * @param mbi
	 * @param eat
	 */
	@SuppressWarnings("unchecked")
	private static void writeThirdPart2EventInfo(EventXMLDBUtil mxb2,
			EventBasicInfo mbi, String docname) {
		// 设置第三阶段的rescuerTeam
		List<String> list = mxb2.queryDocumentOfEvent(
				"third_event_resources_rescuerTeam", docname);
		if (list != null && list.size() != 0) {
			mbi.getEri_Response().setEvent_rescurTeam(list.get(0));
		}
		// 设置第三阶段的逻辑地址
		list = mxb2.queryDocumentOfEvent(
				"third_event_resources_LogisticsPoint", docname);
		if (list != null && list.size() != 0) {
			mbi.getEri_Response().setLogisticsPoint(list.get(0));
		}
		// 设置第三阶段修复道路URL
		list = mxb2.queryDocumentOfEvent(
				"third_event_stateReport_repairedRoadsURL", docname);
		if (list != null && list.size() != 0) {
			mbi.getEsri_Response().setRepairedRoadsURL(list.get(0));
		}
		// 设置第三阶段搜寻区域URL
		list = mxb2.queryDocumentOfEvent(
				"third_event_stateReport_searchedRegionsURL", docname);
		if (list != null && list.size() != 0) {
			mbi.getEsri_Response().setSearchedRegionsURL(list.get(0));
		}
		// 设置第三阶段repairedFortificationsURL
		list = mxb2.queryDocumentOfEvent(
				"third_event_stateReport_repairedFortificationsURL", docname);
		if (list != null && list.size() != 0) {
			mbi.getEsri_Response().setRepairedFortificationsURL(list.get(0));
		}
		// 设置第三阶段factorsEstimation
		list = mxb2.queryDocumentOfEvent(
				"third_event_trendPrediction_factorsEstimation", docname);
		if (list != null && list.size() != 0) {
			mbi.getEtpt_Response().setFactorsEstimation(list.get(0));
		}
		// 设置第三阶段situationPrediction
		list = mxb2.queryDocumentOfEvent(
				"third_event_trendPrediction_situationPrediction", docname);
		if (list != null && list.size() != 0) {
			mbi.getEtpt_Response().setSituationPredication(list.get(0));
		}
	}

	/**
	 * 对第二部分
	 * 
	 * @param mxb2
	 * @param mbi
	 * @param eat
	 * @throws XMLnotFormatException
	 */
	@SuppressWarnings("unchecked")
	private static void writeSecondPart2EventInfo(EventXMLDBUtil mxb2,
			EventBasicInfo mbi, String docname) throws XMLnotFormatException {

		// 设置第二阶段的开始时间
		List<String> list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_begintime", docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation().setEvent_begintime(list.get(0));
		}
		// 设置第二阶段的结束时间
		list = mxb2.queryDocumentOfEvent("second_event_prediction_endtime",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation().setEvent_endtime(list.get(0));
		}
		// 设置事件的未知是否是固定的
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_positionfixed", docname);
		if (list != null && list.size() != 0) {
			if (list.get(0).equals("true")) {
				mbi.getEpi_Preparation().setEventlocationfixed(true);
			} else {
				mbi.getEpi_Preparation().setEventlocationfixed(false);
			}
		}
		// 设置事件的参考坐标系统
		list = mxb2
				.queryDocumentOfEvent(
						"second_event_prediction_eventlocation_referenceFrame",
						docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation()
					.setEventlocationreferenceFrame(list.get(0));
		}
		// 获取pointy的单位
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_pointy_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation().setEventposition_y_unit(list.get(0));
		}
		// 获取pointy
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_pointy", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEpi_Preparation().setEventposition_y(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 获取pointx的单位
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_pointx_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation().setEventposition_x_unit(list.get(0));
		}
		// 获取pointx
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_pointx", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEpi_Preparation().setEventposition_x(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 获取pointz的单位
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_pointz_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation().setEventposition_z_unit(list.get(0));
		}
		// 获取pointz
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_eventlocation_pointz", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEpi_Preparation().setEventposition_z(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 设置事件的可能严重性
		list = mxb2.queryDocumentOfEvent(
				"second_event_prediction_possibileSeverity", docname);
		if (list != null && list.size() != 0) {
			mbi.getEpi_Preparation().setEventpossibleSeverity(list.get(0));
		}

		// 设置事件预警的时间
		list = mxb2.queryDocumentOfEvent("second_event_alert_timeposition",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_time(list.get(0));
		}
		// 设置事件预警的状态
		list = mxb2.queryDocumentOfEvent("second_event_alert_alertStatus",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_status(list.get(0));
		}
		// 设置事件预警的类型
		list = mxb2.queryDocumentOfEvent("second_event_alert_alertMessageType",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_messageType(list.get(0));
		}
		// 设置事件预警的范围
		list = mxb2.queryDocumentOfEvent("second_event_alert_alertScope",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_scope(list.get(0));
		}
		// 设置事件预警联系详细地址
		list = mxb2.queryDocumentOfEvent(
				"second_event_alert_alertArea_deliveryPoint", docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_area_deliverypoint(list.get(0));
		}
		// 设置事件预警的行政区
		list = mxb2.queryDocumentOfEvent(
				"second_event_alert_alertArea_administrativeArea", docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_area_administrativeArea(
					list.get(0));
		}
		// 设置事件预警的城市
		list = mxb2.queryDocumentOfEvent("second_event_alert_alertArea_city",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_area_city(list.get(0));
		}
		// 设置事件预警的省份
		list = mxb2.queryDocumentOfEvent(
				"second_event_alert_alertArea_province", docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_area_province(list.get(0));
		}
		// 设置事件预警的国家
		list = mxb2.queryDocumentOfEvent(
				"second_event_alert_alertArea_country", docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_area_country(list.get(0));
		}
		// 设置事件预警的消息
		list = mxb2.queryDocumentOfEvent("second_event_alert_alertMessage",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEai_Preparation().setAlert_message(list.get(0));
		}
	}

	/**
	 * 将事件第一部分写入进来
	 * 
	 * @param mxb2
	 * @param mbi
	 * @param eat
	 * @throws XMLnotFormatException
	 */
	@SuppressWarnings("unchecked")
	private static void writeFirstPart2EventInfo(EventXMLDBUtil mxb2,
			EventBasicInfo mbi, String docname) throws XMLnotFormatException {
		// 设置事件的开始时间
		List<String> list = mxb2.queryDocumentOfEvent(
				"first_event_eventtime_begin", docname);
		if (list != null && list.size() != 0) {
			mbi.setEventBeginPosition_First(list.get(0));
		}
		// 设置事件的结束时间
		list = mxb2.queryDocumentOfEvent("first_event_eventtime_end", docname);
		if (list != null && list.size() != 0) {
			mbi.setEventEndPosition_First(list.get(0));
		}
		// 设置事件的观测数据文件
		list = mxb2.queryDocumentOfEvent("first_event_causalVector_leaf",
				docname);
		mbi.getEvent_causalVectorLeaf_First().addAll(list);
		// 设置事件的identification部分
		writeEventAttributeIdentification2EventInfo(mxb2, mbi, docname);
		// 设置事件的eventlocation部分
		writeEventAttributeLocation2EventInfo(mxb2, mbi, docname);
		// 设置事件的eventclassification部分
		writeEventClassification2EventInfo(mxb2, mbi, docname);
		// 设置事件的contact部分
		writeEventContact2EventInfo(mxb2, mbi, docname);
		writeEventService2EventInfo(mxb2, mbi, docname);
	}

	@SuppressWarnings("unchecked")
	private static void writeEventService2EventInfo(EventXMLDBUtil mxb2,
			EventBasicInfo mbi, String docname) {
		// 设置事件关联的服务的名称
		List<String> list = mxb2.queryDocumentOfEvent(
				"first_event_service_servicename", docname);
		if (list != null && list.size() != 0) {
			mbi.getEsi_First().setEventServiceName(list.get(0));
		}
		// 设置事件关联的服务的类型
		list = mxb2.queryDocumentOfEvent("first_event_service_serviceType",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEsi_First().setEventServiceType(list.get(0));
		}
		// 设置事件关联的服务的地址
		list = mxb2.queryDocumentOfEvent("first_event_service_serviceAddress",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEsi_First().setEventserviceAddress(list.get(0));
		}
		// 设置事件关联的服务的更多信息
		list = mxb2.queryDocumentOfEvent("first_event_service_moreInfo",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEsi_First().setEventMoreInfo(list.get(0));
		}
	}

	@SuppressWarnings("unchecked")
	private static void writeEventContact2EventInfo(EventXMLDBUtil mxb2,
			EventBasicInfo mbi, String docname) {
		// 设置事件联系人的个人名称
		List<String> list = mxb2.queryDocumentOfEvent(
				"first_event_contact_individualName", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setIndividualname(list.get(0));
		}
		// 设置事件联系单位名称
		list = mxb2.queryDocumentOfEvent(
				"first_event_contact_organizationName", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setOrganizationname(list.get(0));
		}
		// 设置事件联系单位电话
		list = mxb2.queryDocumentOfEvent("first_event_contact_phone_voice",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setVoice(list.get(0));
		}
		// 设置事件联系单位传真
		list = mxb2.queryDocumentOfEvent("first_event_contact_phone_fascimile",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setFacsimile(list.get(0));
		}
		// 设置事件联系单位具体地址
		list = mxb2.queryDocumentOfEvent(
				"first_event_contact_phone_address_deliverypoint", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setDeliverypoint(list.get(0));
		}
		// 设置事件联系单位所在城市
		list = mxb2.queryDocumentOfEvent(
				"first_event_contact_phone_address_city", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setCity(list.get(0));
		}
		// 设置事件联系单位所在行政区
		list = mxb2
				.queryDocumentOfEvent(
						"first_event_contact_phone_address_administrativeArea",
						docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setAdministrativeArea(list.get(0));
		}
		// 设置事件联系单位所在邮政编码
		list = mxb2.queryDocumentOfEvent(
				"first_event_contact_phone_address_postalCode", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setPostalCode(list.get(0));
		}
		// 设置事件联系单位的国家
		list = mxb2.queryDocumentOfEvent(
				"first_event_contact_phone_address_country", docname);
		if (list != null && list.size() != 0) {
			mbi.getEcontactinfo_First().setCountry(list.get(0));
		}
		// 设置事件联系单位的国家
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
		// 设置事件的分类
		List<String> list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_classification_eventcategory", docname);
		if (list != null && list.size() != 0) {
			mbi.getEci_First().setEventcategory(list.get(0));
		}
		// 设置事件的事件模式
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_classification_eventPattern", docname);
		if (list != null && list.size() != 0) {
			mbi.getEci_First().setEventPattern(list.get(0));
		}
		// 设置事件的传承问题
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_classification_eventInheritance",
				docname);
		if (list != null && list.size() != 0) {
			mbi.getEci_First().setEventInheritance(list.get(0));
		}
		// 设置事件的紧急程度
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_classification_eventUrgency", docname);
		if (list != null && list.size() != 0) {
			mbi.getEci_First().setEventUrgency(list.get(0));
		}
		// 设置事件的严重程度
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_classification_eventSeverity", docname);
		if (list != null && list.size() != 0) {
			mbi.getEci_First().setEventSeverity(list.get(0));
		}
		// 设置事件的确定性
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
		// 设置事件的未知是否是固定的
		List<String> list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_positionfixed", docname);
		if (list != null && list.size() != 0) {
			if (list.get(0).equals("true")) {
				mbi.getEli_First().setEventlocationfixed(true);
			} else {
				mbi.getEli_First().setEventlocationfixed(false);
			}
		}
		// 设置事件的参考坐标系统
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_referenceFrame", docname);
		if (list != null && list.size() != 0) {
			mbi.getEli_First().setEventlocationreferenceFrame(list.get(0));
		}
		// 获取pointy的单位
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_pointy_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEli_First().setEventposition_y_unit(list.get(0));
		}
		// 获取pointy
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_pointy", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEli_First().setEventposition_y(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 获取pointx的单位
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_pointx_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEli_First().setEventposition_x_unit(list.get(0));
		}
		// 获取pointy
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_pointx", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEli_First().setEventposition_x(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
		// 获取pointz的单位
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_pointz_unit", docname);
		if (list != null && list.size() != 0) {
			mbi.getEli_First().setEventposition_z_unit(list.get(0));
		}
		// 获取pointz
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_eventlocation_pointz", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.getEli_First().setEventposition_z(
						Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new XMLnotFormatException(
						"Event文件中的位置格式不正确，无法转化为Double类型");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void writeEventAttributeIdentification2EventInfo(
			EventXMLDBUtil mxb2, EventBasicInfo mbi, String docname) {
		// 设置事件的标识符
		List<String> list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_identification_eventid", docname);
		if (list != null && list.size() != 0) {
			mbi.getEfinfo_First().setEventID(list.get(0));
		}
		// 设置事件的名称
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_identification_name", docname);
		if (list != null && list.size() != 0) {
			mbi.getEfinfo_First().setEventname(list.get(0));
		}
		// 设置事件的period
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_identification_eventperoid", docname);
		if (list != null && list.size() != 0) {
			mbi.getEfinfo_First().setEventPeroid(list.get(0));
		}
		// 设置事件的description
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_identification_description", docname);
		if (list != null && list.size() != 0) {
			mbi.getEfinfo_First().setEventDescription(list.get(0));
		}
		// 设置事件的处理状态
		list = mxb2.queryDocumentOfEvent(
				"first_event_attribute_identification_processStatus", docname);
		if (list != null && list.size() != 0) {
			mbi.getEfinfo_First().setProcessStatus(list.get(0));
		}
	}

	/**
	 * 将信息全部写入到ModelBasicInfo中
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
	 * 将文档中全部的信息写入到modelinfo中
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2EventName() throws DirectoryNotExistException {
		EventInfoConfig.getDocnames().addAll(mxb.getAllDocumentName());
	}
}
