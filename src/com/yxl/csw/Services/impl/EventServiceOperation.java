package com.yxl.csw.Services.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.dom4j.DocumentException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.yxl.csw.Services.EventService;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.EventBeginException;
import com.csw.exceptions.EventParamNotFormException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.exceptions.XMLnotFormatException;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.JTSUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.event.commonutils.EventInfoConfig;
import com.event.commonutils.EventInfoWriter;
import com.event.commonutils.EventXMLDBUtil;
import com.event.customTypes.EventAndProcessStatus;
import com.event.customTypes.EventBasicInfo;
import com.event.customTypes.EventOperationTypes;
import com.event.customTypes.EventQueryParam;
import com.event.customTypes.EventQueryParamList;

public class EventServiceOperation implements EventService {
	private EventXMLDBUtil exbu = null;
	public EventServiceOperation() {
		exbu = EventXMLDBUtil.getInstance();
		File files=new File(EventXMLDBUtil.getXMLSTORE_LOCATION());
		for(File file:files.listFiles()){
			try {
				EventInfoWriter.writeDocument2EventBasicInfo(file.getName().replace(".xml", ""));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

	public List<String> getEventIdByCondition(String username, String password,
			EventQueryParamList eqParamall) throws ServiceException,
			NullZeroException, EventParamNotFormException {
		List<String> result = getEventIds(username, password);
		if (eqParamall == null || eqParamall.getEqps() == null
				&& eqParamall.getEqps().size() == 0) {
			throw new EventParamNotFormException("请求参数列表不能为空!");
		}
		List<EventQueryParam> eqParams = eqParamall.getEqps();
		if (eqParams != null) {
			for (EventQueryParam eqp : eqParams) {
				if (eqp.getEot() == null) {
					throw new EventParamNotFormException(
							"输入参数中必须包含EventOperationTypes类型的值");
				}
				if (eqp.getEot().equals(
						EventOperationTypes.EVENTID_TEXT_ONE_CONTAIN)) {
					// 查找事件标志符
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 1) {
						if (result.contains(
								eqp.getValue().get(0))) {
							result=new ArrayList<String>();
							result.add(eqp.getValue().get(0));
						}else {
							return null;
						}
					} else {
						throw new EventParamNotFormException(
								"EVENTID_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.CERTAINTY_TEXT_ONE_CONTAIN)) {
					// 查找事件紧急程度
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 1) {
						result.retainAll(getEventIdByCertainty(username,
								password, eqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"CERTAINTY_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.BBOX_POLYGON_ONE_BECONTAIN)) {
					// 空间查询
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 1) {
						result.retainAll(getEventIdsByBBOX(username,
								password, eqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"BBOX_POLYGON_ONE_BECONTAIN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.DEATH_NUM_TWO_BETWEEN)) {
					// 死亡人数
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 2) {
						Double min = null;
						Double max = null;
						try {
							min = Double.parseDouble(eqp.getValue().get(0));
							max = Double.parseDouble(eqp.getValue().get(1));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							throw new EventParamNotFormException(
									"输入参数中 存在无法转化为Double类型的值!");
						}
						Double temp = min;
						if (temp > max) {
							min = max;
							max = min;
						}
						result.retainAll(getEventIdByDeath(username,
								password, min, max));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"BBOX_POLYGON_ONE_BECONTAIN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.DIRECTLOSS_NUM_TWO_BETWEEN)) {
					// 直接损失
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 2) {
						Double min = null;
						Double max = null;
						try {
							min = Double.parseDouble(eqp.getValue().get(0));
							max = Double.parseDouble(eqp.getValue().get(1));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							throw new EventParamNotFormException(
									"输入参数中存在无法转化为Double类型的值!");
						}
						Double temp = min;
						if (temp > max) {
							min = max;
							max = min;
						}
						result.retainAll(getEventIdBydirectLossesNumber(
								username, password, min, max));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"DIRECTLOSS_NUM_TWO_BETWEEN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.ECONOMICLOCSS_NUM_TWO_BETWEEN)) {
					// 经济损失
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 2) {
						Double min = null;
						Double max = null;
						try {
							min = Double.parseDouble(eqp.getValue().get(0));
							max = Double.parseDouble(eqp.getValue().get(1));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							throw new EventParamNotFormException(
									"输入参数中存在无法转化为Double类型的值!");
						}
						Double temp = min;
						if (temp > max) {
							min = max;
							max = min;
						}
						result.retainAll(getEventIdByEconomicLossing(
								username, password, min, max));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"ECONOMICLOCSS_NUM_TWO_BETWEEN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.EVENTTIME_DATE_TWO_BETWEEN)) {
					// 日期
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 2) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");
						try {
							Date starttime = sdf.parse(eqp.getValue().get(0));
							Date endtime = sdf.parse(eqp.getValue().get(1));
							result.retainAll(getEventIdsByTime(username,
									password, starttime, endtime));
							if(result.size()==0){
								return result;
							}
						} catch (ParseException e) {
							throw new EventParamNotFormException(
									"输入参数中存在无法转化为yyyy-MM-dd hh:mm:ss格式的日期 !");
						}
					} else {
						throw new EventParamNotFormException(
								"EVENTTIME_DATE_TWO_BETWEEN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.INDIRECTLOSS_NUM_TWO_BETWEEN)) {
					// 间接损失
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 2) {
						Double min = null;
						Double max = null;
						try {
							min = Double.parseDouble(eqp.getValue().get(0));
							max = Double.parseDouble(eqp.getValue().get(1));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							throw new EventParamNotFormException(
									"输入参数中存在无法转化为Double类型的值!");
						}
						Double temp = min;
						if (temp > max) {
							min = max;
							max = min;
						}
						result.retainAll(getEventIdByIndirectLoss(username,
								password, min, max));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"INDIRECTLOSS_NUM_TWO_BETWEEN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.INHERITANCE_TEXT_ONE_CONTAIN)) {
					// 继承关系
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 1) {
						result.retainAll(getEventIdByInheritance(username,
								password, eqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"INHERITANCE_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.INJURE_NUM_TWO_BETWEEN)) {
					// 受伤人员
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 2) {
						Double min = null;
						Double max = null;
						try {
							min = Double.parseDouble(eqp.getValue().get(0));
							max = Double.parseDouble(eqp.getValue().get(1));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							throw new EventParamNotFormException(
									"输入参数中存在无法转化为Double类型的值!");
						}
						Double temp = min;
						if (temp > max) {
							min = max;
							max = min;
						}
						result.retainAll(getEventIdByInjury(username,
								password, min, max));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"INJURE_NUM_TWO_BETWEEN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.MISSING_NUM_TWO_BETWEEN)) {
					// 失踪人员
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 2) {
						Double min = null;
						Double max = null;
						try {
							min = Double.parseDouble(eqp.getValue().get(0));
							max = Double.parseDouble(eqp.getValue().get(1));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							throw new EventParamNotFormException(
									"输入参数中存在无法转化为Double类型的值!");
						}
						Double temp = min;
						if (temp > max) {
							min = max;
							max = min;
						}
						result.retainAll(getEventIdByMisssing(username,
								password, min, max));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"MISSING_NUM_TWO_BETWEEN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.MOHUQUERY_TEXT_ONE_CONTAIN)) {
					// 失踪人员
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 1) {
						result.retainAll(getEventIdByMohuQuery(username,
								password, eqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"MOHUQUERY_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.PATTERN_TEXT_ONE_CONTAIN)) {
					// 模式查询
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 1) {
						result.retainAll(getEventIdByPattern(username,
								password, eqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"PATTERN_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.SEVERITY_TEXT_ONE_CONTAIN)) {
					// 严重程度
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 1) {
						result.retainAll(getEventIdBySeverity(username,
								password, eqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"SEVERITY_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				} else if (eqp.getEot().equals(
						EventOperationTypes.URGENCY_TEXT_ONE_CONTAIN)) {
					// 紧急程度
					if (eqp.getValue() != null && eqp.getValue() != null
							&& eqp.getValue().size() == 1) {
						result.retainAll(getEventIdByUrgency(username,
								password, eqp.getValue().get(0)));
						if(result.size()==0){
							return result;
						}
					} else {
						throw new EventParamNotFormException(
								"URGENCY_TEXT_ONE_CONTAIN 输入参数不正确！");
					}
				}
			}
		}
		return result;
	}

	public List<EventBasicInfo> getAllEventBasicInfo() {
		return EventInfoConfig.getEbis();
	}

	public String getDocument(String username, String password, String docname)
			throws ServiceException, NullZeroException,
			DocumentnotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		return exbu.getDocument(docname);
	}

	public boolean uploadDocument(String username, String password,
			String xmlcontent, String docname) throws ServiceException,
			NullZeroException, DocumentnotExistException,
			XMLnotFormatException, XMLDocumentException, DocumentException,
			EventBeginException {
		UserInfoUtil.CheckUserLogin(username, password);
		return exbu.SaveEventXMLToBerkeley(docname, xmlcontent);
	}

	public boolean deleteDocument(String username, String password,
			String docname) throws ServiceException, NullZeroException,
			DocumentnotExistException, XMLDocumentNotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		exbu.deleteEventXMLFromBerkeley(docname);
		return true;
	}

	public boolean updateDocument(String username, String password,
			String xmlcontent, String docname) throws ServiceException,
			NullZeroException, DocumentnotExistException,
			XMLnotFormatException, XMLDocumentException, DocumentException,
			EventBeginException, XMLDocumentNotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		for (String document : EventInfoConfig.getDocnames()) {
			if (document.equals(docname)) {
				exbu.updateEventXMLToBerkeley(document, xmlcontent);
				break;
			}
		}
		return true;
	}

	public List<String> getEventIds(String username, String password)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> eventids = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			if (ebi.getEfinfo_First() != null) {
				eventids.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return eventids;
	}

	public List<EventAndProcessStatus> getAllEventProcessStatus(
			String username, String password) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<EventAndProcessStatus> eaps = new ArrayList<EventAndProcessStatus>();
		EventAndProcessStatus eap = null;
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			eap = new EventAndProcessStatus();
			eap.setEventid(ebi.getEfinfo_First().getEventID());
			eap.setEventprocessstatus(ebi.getEfinfo_First().getProcessStatus());
			eaps.add(eap);
		}
		return eaps;
	}

	public String getEventProcessStatusByEventId(String username,
			String password, String eventid) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			if (ebi.getEfinfo_First().getEventID().equals(eventid)) {
				return ebi.getEfinfo_First().getProcessStatus();
			}
		}
		throw new ServiceException("无指定标志符事件信息");
	}

	public boolean updateEventProcessStatus(String username, String password,
			String eventid, String processStatus) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			if (ebi.getEfinfo_First().getEventID().equals(eventid)) {
				GetRealPathUtil grp = new GetRealPathUtil();
				String systemconfigpath = grp.getWebInfPath()
						+ "systemconfig.properties";
				try {
					String docname = ebi.getDocname();
					System.out.println("事件所处阶段："
							+ ebi.getEfinfo_First().getEventPeroid());
					if (ebi.getEfinfo_First().getEventPeroid()
							.equals("Monitoring")) {
						docname = docname + "_001";
					} else if (ebi.getEfinfo_First().getEventPeroid()
							.equals("Preparation")) {
						docname = docname + "_002";
					}
					if (ebi.getEfinfo_First().getEventPeroid()
							.equals("Response")) {
						docname = docname + "_003";
					}
					String dirpath = FileOperationUtil.getPropertyValue(
							systemconfigpath, "EVENT_LOCATION")
							+ File.separatorChar + docname + ".xml";
					System.out.println("dirpath   " + dirpath);
					String st = FileOperationUtil.ReadFileContent(dirpath,
							"UTF-8");
					System.out
							.println(ebi.getEfinfo_First().getProcessStatus());
					st = st.replace("<eml:processStatus>"
							+ ebi.getEfinfo_First().getProcessStatus()
							+ "</eml:processStatus>", "<eml:processStatus>"
							+ processStatus + "</eml:processStatus>");
					System.out.println("st : " + st);
					// 写回到系统中
					FileOperationUtil.WriteToFileContent(st, dirpath, "UTF-8");
					ebi.getEfinfo_First().setProcessStatus(processStatus);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		throw new ServiceException("不存在指定标志符" + eventid + "的事件信息!");
	}

	public List<String> getEventIdByMohuQuery(String username, String password,
			String mohuword) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			if (ebi.getEfinfo_First().getEventDescription().contains(mohuword)
					|| ebi.getEfinfo_First().getEventname().contains(mohuword)
					|| ebi.getEci_First().getEventcategory().contains(mohuword)) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}

	public List<String> getEventIdsByTime(String username, String password,
			Date starttime, Date endtime) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			try {
				Date eventstarttime = sdf.parse(ebi
						.getEventBeginPosition_First());
				if (!eventstarttime.before(starttime)) {
					results.add(ebi.getEfinfo_First().getEventID());
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	public List<String> getEventIdsByBBOX(String username, String password,
			String polygonstr) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		Polygon polygon = null;
		try {
			polygon = JTSUtil.createPolygonByWKT(polygonstr);
		} catch (com.vividsolutions.jts.io.ParseException e) {
			throw new ServiceException("格式不对!");
		}
		Point p = null;
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			Double eventx = ebi.getEli_First().getEventposition_x();
			Double eventy = ebi.getEli_First().getEventposition_y();
			p = JTSUtil.createPoint(eventx, eventy);
			if (polygon.contains(p)) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}

	public List<String> getEventIdByCategory(String username, String password,
			String category) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			if (ebi.getEci_First().getEventcategory().contains(category)) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}

	public List<String> getEventIdByPattern(String username, String password,
			String pattern) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			if (ebi.getEci_First().getEventPattern().contains(pattern)) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}

	public List<String> getEventIdByUrgency(String username, String password,
			String urgency) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			if (ebi.getEci_First().getEventUrgency().contains(urgency)) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}

	public List<String> getEventIdBySeverity(String username, String password,
			String severity) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			if (ebi.getEci_First().getEventSeverity().contains(severity)) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}

	public List<String> getEventIdByCertainty(String username, String password,
			String certainty) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			if (ebi.getEci_First().getEventCertainty().contains(certainty)) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}

	public List<String> getEventIdByInheritance(String username,
			String password, String inheritance) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			if (ebi.getEci_First().getEventInheritance().contains(inheritance)) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}

	public List<String> getEventIdByDeath(String username, String password,
			double min, double max) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			Double dath = ebi.getEcli_Recovery().getDeathNumber();
			if (dath != null) {
				if (dath > min && dath < max) {
					results.add(ebi.getEfinfo_First().getEventID());
				}
			}
		}
		return results;
	}

	public List<String> getEventIdByInjury(String username, String password,
			double min, double max) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			Double dath = ebi.getEcli_Recovery().getTotalInjuredNumber();
			if (dath == null) {
				continue;
			}
			if (dath > min && dath < max) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}

	public List<String> getEventIdByMisssing(String username, String password,
			double min, double max) throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			Double dath = ebi.getEcli_Recovery().getMissingNumber();
			if (dath == null) {
				continue;
			}
			if (dath > min && dath < max) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}

	public List<String> getEventIdByEconomicLossing(String username,
			String password, double min, double max) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			Double dath = ebi.getEemli_Recovery().getTotalLossesNumber();
			if (dath == null) {
				continue;
			}
			if (dath > min && dath < max) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}

	public List<String> getEventIdBydirectLossesNumber(String username,
			String password, double min, double max) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			Double dath = ebi.getEemli_Recovery().getDirectLossesNumber();
			if (dath == null) {
				continue;
			}
			if (dath > min && dath < max) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}

	public List<String> getEventIdByIndirectLoss(String username,
			String password, double min, double max) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
			Double dath = ebi.getEemli_Recovery().getIndirectLossesNumber();
			if (dath == null) {
				continue;
			}
			if (dath > min && dath < max) {
				results.add(ebi.getEfinfo_First().getEventID());
			}
		}
		return results;
	}
}
