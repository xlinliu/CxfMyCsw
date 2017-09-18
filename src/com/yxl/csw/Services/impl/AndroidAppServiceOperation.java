package com.yxl.csw.Services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.serviceresult.commonutil.ServiceResultInfoConfig;
import com.serviceresult.customTypes.ServiceResultInfo;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.yxl.csw.Services.AndroidAppService;
import com.yxl.mobile.common.types.EventRelatedServiceResult;
import com.yxl.mobile.common.types.MobileEventBasicInfo;
import com.csw.utils.JTSUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.event.commonutils.EventInfoConfig;
import com.event.customTypes.EventBasicInfo;

public class AndroidAppServiceOperation implements AndroidAppService {

	public List<MobileEventBasicInfo> getMobileEventBasicInfo(String username,
			String password, Date starttime, Date endTime, String polygonstr)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MobileEventBasicInfo> mebis = new ArrayList<MobileEventBasicInfo>();
		List<EventBasicInfo> eifs = EventInfoConfig.getEbis();
		List<ServiceResultInfo> scis = ServiceResultInfoConfig.getScis();
		System.out.println("startime -endtime: " + starttime + "/" + endTime);
		for (EventBasicInfo ebi : eifs) {
			try {
				MobileEventBasicInfo mebi = new MobileEventBasicInfo();
				mebi.setEventid(ebi.getEfinfo_First().getEventID());
				mebi.setEventname(ebi.getEfinfo_First().getEventname());
				mebi.setEventType(ebi.getEci_First().getEventcategory());
				mebi.setEventperiod(ebi.getEfinfo_First().getEventPeroid());
				mebi.setEventtime_begin(ebi.getEventBeginPosition_First());
				mebi.setEventtime_end(ebi.getEventEndPosition_First());
				mebi.setEventprocessStatus(ebi.getEfinfo_First()
						.getProcessStatus());
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				String tempstartime = ebi.getEventBeginPosition_First();
				System.out.println("ebi.getEventBeginPosition_First(): "
						+ ebi.getEventBeginPosition_First());
				try {
					Date starteventtime = sdf.parse(tempstartime);
					System.out.println("starteventtime: " + starteventtime);
					if (!starteventtime.before(starttime)) {
					} else {
						continue;
					}
				} catch (ParseException e) {
					continue;
				}
				Double eventx = ebi.getEli_First().getEventposition_x();
				Double eventy = ebi.getEli_First().getEventposition_y();
				Point point = JTSUtil.createPoint(eventx, eventy);
				System.out.println("point: " + eventx + " , " + eventy);
				Polygon polygon = JTSUtil.createPolygonByWKT(polygonstr);
				boolean bol = polygon.contains(point);
				System.out.println(bol + "====bol");
				if (!bol) {
					continue;
				}
				mebi.setEventposition_x(ebi.getEli_First().getEventposition_x());
				mebi.setEventposition_y(ebi.getEli_First().getEventposition_y());
				EventRelatedServiceResult ersr;
				for (ServiceResultInfo sci : scis) {
					// System.out.println(sci
					// .getServiceResult_Rela_Event_eventID()
					// + " // "
					// + ebi.getEfinfo_First().getEventID());
					if (sci.getServiceResult_Rela_Event_eventID().equals(
							ebi.getEfinfo_First().getEventID())) {
						ersr = new EventRelatedServiceResult();
						ersr.setServiceResultAddress(sci
								.getServiceResult_Address_url());
						ersr.setServiceResultCreatedTime(sci
								.getServiceResult_ResultTime_TimeInstant());
						ersr.setServiceResultFormat(sci
								.getServiceResult_ResultFormat_format());
						ersr.setServiceResultFullName(sci
								.getServiceResult_Iden_Service_FullName());
						ersr.setServiceResultId(sci
								.getServiceResult_Rela_serviceID());
						ersr.setServiceResultShortName(sci
								.getServiceResult_Iden_Service_ShortName());
						mebi.getErsrs().add(ersr);
					}
				}
				mebis.add(mebi);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mebis;
	}

	public List<MobileEventBasicInfo> getAllMobileEventBasicInfo(
			String username, String password) throws ServiceException,
			NullZeroException {
		List<MobileEventBasicInfo> mebis = new ArrayList<MobileEventBasicInfo>();
		UserInfoUtil.CheckUserLogin(username, password);
		List<EventBasicInfo> eifs = EventInfoConfig.getEbis();
		List<ServiceResultInfo> scis = ServiceResultInfoConfig.getScis();
		for (EventBasicInfo ebi : eifs) {
			try {
				MobileEventBasicInfo mebi = new MobileEventBasicInfo();
				mebi.setEventid(ebi.getEfinfo_First().getEventID());
				mebi.setEventname(ebi.getEfinfo_First().getEventname());
				mebi.setEventType(ebi.getEci_First().getEventcategory());
				mebi.setEventtime_begin(ebi.getEventBeginPosition_First());
				mebi.setEventtime_end(ebi.getEventEndPosition_First());
				mebi.setEventposition_x(ebi.getEli_First().getEventposition_x());
				mebi.setEventperiod(ebi.getEfinfo_First().getEventPeroid());
				mebi.setEventposition_y(ebi.getEli_First().getEventposition_y());
				mebi.setEventprocessStatus(ebi.getEfinfo_First()
						.getProcessStatus());
				EventRelatedServiceResult ersr;
				for (ServiceResultInfo sci : scis) {
					System.out.println(sci
							.getServiceResult_Rela_Event_eventID()
							+ "////"
							+ ebi.getEfinfo_First().getEventID());
					if (sci.getServiceResult_Rela_Event_eventID().equals(
							ebi.getEfinfo_First().getEventID())) {
						ersr = new EventRelatedServiceResult();
						ersr.setServiceResultAddress(sci
								.getServiceResult_Address_url());
						ersr.setServiceResultCreatedTime(sci
								.getServiceResult_ResultTime_TimeInstant());
						ersr.setServiceResultFormat(sci
								.getServiceResult_ResultFormat_format());
						ersr.setServiceResultFullName(sci
								.getServiceResult_Iden_Service_FullName());
						ersr.setServiceResultId(sci
								.getServiceResult_Rela_serviceID());
						ersr.setServiceResultShortName(sci
								.getServiceResult_Iden_Service_ShortName());
						mebi.getErsrs().add(ersr);
					}
				}
				mebis.add(mebi);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mebis;
	}

}
