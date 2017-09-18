package com.event.customTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * 保存的事件模型
 * 
 * @author yxliang
 * 
 */
public class EventBasicInfo {
	private String docname;
	private String eventBeginPosition_First;// 时间开始时间
	private String eventEndPosition_First;// 时间结束时间
	private List<String> event_causalVectorLeaf_First = new ArrayList<String>();// 事件的causalVector
	private EventIdentificationInfo efinfo_First = new EventIdentificationInfo();
	private EventLocationInfo eli_First = new EventLocationInfo();
	private EventClassificationInfo eci_First = new EventClassificationInfo();
	private EventContactInfo econtactinfo_First = new EventContactInfo();
	private EventServiceInfo esi_First = new EventServiceInfo();
	private EventPredictionInfo epi_Preparation = new EventPredictionInfo();
	private EventAlertInfo eai_Preparation = new EventAlertInfo();
	private EventResourceInfo eri_Response = new EventResourceInfo();
	private EventstateReportInfo esri_Response = new EventstateReportInfo();
	private EventtrendPrediction etpt_Response = new EventtrendPrediction();
	private EventcasualtyInfo ecli_Recovery = new EventcasualtyInfo();
	private EventeconomicLossInfo eemli_Recovery = new EventeconomicLossInfo();
	private EventotherInfluenceInfo eifi_Recovery = new EventotherInfluenceInfo();

	public String getEventBeginPosition_First() {
		return eventBeginPosition_First;
	}

	public void setEventBeginPosition_First(String eventBeginPosition_First) {
		this.eventBeginPosition_First = eventBeginPosition_First;
	}

	public String getEventEndPosition_First() {
		return eventEndPosition_First;
	}

	public void setEventEndPosition_First(String eventEndPosition_First) {
		this.eventEndPosition_First = eventEndPosition_First;
	}

	public List<String> getEvent_causalVectorLeaf_First() {
		return event_causalVectorLeaf_First;
	}

	public void setEvent_causalVectorLeaf_First(
			List<String> event_causalVectorLeaf_First) {
		this.event_causalVectorLeaf_First = event_causalVectorLeaf_First;
	}

	public EventIdentificationInfo getEfinfo_First() {
		return efinfo_First;
	}

	public void setEfinfo_First(EventIdentificationInfo efinfo_First) {
		this.efinfo_First = efinfo_First;
	}

	public EventLocationInfo getEli_First() {
		return eli_First;
	}

	public void setEli_First(EventLocationInfo eli_First) {
		this.eli_First = eli_First;
	}

	public EventClassificationInfo getEci_First() {
		return eci_First;
	}

	public void setEci_First(EventClassificationInfo eci_First) {
		this.eci_First = eci_First;
	}

	public EventContactInfo getEcontactinfo_First() {
		return econtactinfo_First;
	}

	public void setEcontactinfo_First(EventContactInfo econtactinfo_First) {
		this.econtactinfo_First = econtactinfo_First;
	}

	public EventServiceInfo getEsi_First() {
		return esi_First;
	}

	public void setEsi_First(EventServiceInfo esi_First) {
		this.esi_First = esi_First;
	}

	public EventPredictionInfo getEpi_Preparation() {
		return epi_Preparation;
	}

	public void setEpi_Preparation(EventPredictionInfo epi_Preparation) {
		this.epi_Preparation = epi_Preparation;
	}

	public EventAlertInfo getEai_Preparation() {
		return eai_Preparation;
	}

	public void setEai_Preparation(EventAlertInfo eai_Preparation) {
		this.eai_Preparation = eai_Preparation;
	}

	public EventResourceInfo getEri_Response() {
		return eri_Response;
	}

	public void setEri_Response(EventResourceInfo eri_Response) {
		this.eri_Response = eri_Response;
	}

	public EventstateReportInfo getEsri_Response() {
		return esri_Response;
	}

	public void setEsri_Response(EventstateReportInfo esri_Response) {
		this.esri_Response = esri_Response;
	}

	public EventtrendPrediction getEtpt_Response() {
		return etpt_Response;
	}

	public void setEtpt_Response(EventtrendPrediction etpt_Response) {
		this.etpt_Response = etpt_Response;
	}

	public EventcasualtyInfo getEcli_Recovery() {
		return ecli_Recovery;
	}

	public void setEcli_Recovery(EventcasualtyInfo ecli_Recovery) {
		this.ecli_Recovery = ecli_Recovery;
	}

	public EventeconomicLossInfo getEemli_Recovery() {
		return eemli_Recovery;
	}

	public void setEemli_Recovery(EventeconomicLossInfo eemli_Recovery) {
		this.eemli_Recovery = eemli_Recovery;
	}

	public EventotherInfluenceInfo getEifi_Recovery() {
		return eifi_Recovery;
	}

	public void setEifi_Recovery(EventotherInfluenceInfo eifi_Recovery) {
		this.eifi_Recovery = eifi_Recovery;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

}
