package com.yxl.csw.Services.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.ServiceException;
import com.datametedia.commonutil.MetadataInfoWriter;
import com.datametedia.commonutil.MetedataXMLDBUtil;
import com.datametedia.customTypes.DataSetInfoConfig;
import com.datametedia.customTypes.MetadataInfo;
import com.vividsolutions.jts.geom.Polygon;
import com.yxl.csw.Services.DataMetadataService;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.MetadataInfoException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.utils.JTSUtil;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 * 数据元数据的各种操作接口
 * @author Administrator
 *
 */
public class DataMetadataServiceOperation implements DataMetadataService {
	
	private MetedataXMLDBUtil mxb = null;
	DataMetadataServiceOperation() {
		mxb = MetedataXMLDBUtil.getInstance();
		java.io.File files=new java.io.File(MetedataXMLDBUtil.getXMLSTORE_LOCATION());
		for(File file:files.listFiles()){
			try {
				MetadataInfoWriter.write2MetadataBasicInfo(file.getName().replace(".xml", ""));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
	}

	public String getDocument(String username, String password, String docname)
			throws ServiceException, NullZeroException,
			DocumentnotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		return mxb.getDocument(docname);
	}

	public boolean uploadDocument(String username, String password,
			String xmlcontent, String docname) throws ServiceException,
			NullZeroException, XMLDocumentNotExistException,
			XMLDocumentException {
		UserInfoUtil.CheckUserLogin(username, password);
		return mxb.SaveMetadataXMLToBerkeley(docname, xmlcontent);
	}

	public boolean deleteDocument(String username, String password,
			String docname) throws ServiceException, NullZeroException,
			XMLDocumentNotExistException, DocumentnotExistException {
		UserInfoUtil.CheckUserLogin(username, password);
		mxb.deleteMetadataXMLFromBerkeley(docname);
		return true;
	}

	public boolean updateDocument(String username, String password,
			String xmlcontent, String docname) throws ServiceException,
			NullZeroException, XMLDocumentNotExistException,
			DocumentnotExistException, XMLDocumentException {
		UserInfoUtil.CheckUserLogin(username, password);
		mxb.updateMetadataXMLToBerkeley(docname, xmlcontent);
		return true;
	}

	public List<MetadataInfo> getAllMetadataInfo() {
		return DataSetInfoConfig.getMgis();
	}

	public List<String> getAllMetadataIds(String username, String password)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> titles = new ArrayList<String>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			titles.add(mi.getMbi().getMetaReference_metadataIdentifier());
		}
		return titles;
	}

	public MetadataInfo getMetadataInfo(String username, String password,
			String datameteid) throws ServiceException, NullZeroException,
			MetadataInfoException {
		UserInfoUtil.CheckUserLogin(username, password);
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			if (mi.getMbi().getMetaReference_metadataIdentifier()
					.equals(datameteid)) {
				return mi;
			}
		}
		throw new MetadataInfoException("指定的元数据[" + datameteid + "]不存在!");
	}

	public List<MetadataInfo> getMetadataInfoByCreatedTime(String username,
			String password, Date starttime, Date endtime)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String mds = mi.getMbi().getMetaReference_metadataDateStamp();
			System.out.println("mds             " + mds);
			SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdfDateFormat.parse(mds);
				if (!date.before(starttime) && !date.after(endtime)) {
					mis.add(mi);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByconnOrganization(
			String username, String password, String connOrganization)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getMbi().getMetaReference_organizationName();
			if (sbi != null) {
				if (sbi.contains(connOrganization)) {
					mis.add(mi);
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoBydatequality(String username,
			String password, String datequality) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getDataQualityInfo_statement();
			if (sbi != null) {
				if (sbi.contains(datequality)) {
					mis.add(mi);
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoBylinkage(String username,
			String password, String linkage) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getDbi()
					.getDistributionInfo_transferOptions_linkage();
			if (sbi != null) {
				if (sbi.contains(linkage)) {
					mis.add(mi);
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByprotocol(String username,
			String password, String protocol) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getDbi()
					.getDistributionInfo_transferOptions_protocol();
			if (sbi != null) {
				if (sbi.contains(protocol)) {
					mis.add(mi);
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByDistributionOrganName(
			String username, String password, String OrganName)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getDbi().getDistributionInfo_organizationName();
			if (sbi != null) {
				if (sbi.contains(OrganName)) {
					mis.add(mi);
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByDistributionFormatName(
			String username, String password, String formatname)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getDbi().getDistributionInfo_formatName();
			if (sbi != null) {
				if (sbi.contains(formatname)) {
					mis.add(mi);
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByTimePeroid(String username,
			String password, Date starttime, Date endtime)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		String start = "";
		String end = "";
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			start = mi.getDsci().getContentInfo_temporalExtension()
					.getContentInfo_temporalExtension_beginDate();
			end = mi.getDsci().getContentInfo_temporalExtension()
					.getContentInfo_temporalExtension_endDate();
			SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (start == null || end == null) {
				continue;
			}
			try {
				Date tstarttime = sdfDateFormat.parse(start);
				Date tendtime = sdfDateFormat.parse(end);
				if (!tstarttime.before(starttime) && !tendtime.after(endtime)) {
					mis.add(mi);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByExtension_Distance(
			String username, String password, double minExtension_Distance,
			double maxExtension_Distance) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getDsci().getContentInfo_spatialExtension()
					.getContentInfo_spatialExtension_distance();
			if (sbi != null) {
				try {
					Double temp = Double.parseDouble(sbi);
					if (temp >= minExtension_Distance
							&& temp <= maxExtension_Distance) {
						mis.add(mi);
					}
				} catch (NumberFormatException e) {
					// e.printStackTrace();
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByEquivalentScale(String username,
			String password, double minEquivalentScale,
			double maxEquivalentScale) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getDsci().getContentInfo_spatialExtension()
					.getContentInfo_spatialExtension_equivalentScale();
			if (sbi != null) {
				try {
					Double temp = Double.parseDouble(sbi);
					if (temp >= minEquivalentScale
							&& temp <= maxEquivalentScale) {
						mis.add(mi);
					}
				} catch (NumberFormatException e) {
					// e.printStackTrace();
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByCoordinateSystem(
			String username, String password, String coordinate)
			throws ServiceException, NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getDsci().getContentInfo_spatialExtension()
					.getContentInfo_spatialExtension_coordinateSystem();
			if (sbi != null) {
				if (sbi.contains(coordinate)) {
					mis.add(mi);
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByBoundary(String username,
			String password, String boundary) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		Polygon polygon = null;
		try {
			polygon = JTSUtil.createPolygonByWKT(boundary);
		} catch (com.vividsolutions.jts.io.ParseException e) {
			throw new ServiceException("格式不对!");
		}
		// System.out.println("boundary : " + boundary);
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String eastlon = mi.getDsci().getContentInfo_spatialExtension()
					.getContentInfo_spatialExtension_eastLongitude();
			String westlon = mi.getDsci().getContentInfo_spatialExtension()
					.getContentInfo_spatialExtension_westLongitude();
			String southlat = mi.getDsci().getContentInfo_spatialExtension()
					.getContentInfo_spatialExtension_southLatitude();
			String northlat = mi.getDsci().getContentInfo_spatialExtension()
					.getContentInfo_spatialExtension_northLatitude();
			String polygonStr = "POLYGON((" + westlon + " " + southlat + ","
					+ westlon + " " + northlat + "," + eastlon + " " + northlat
					+ "," + eastlon + " " + southlat + "," + westlon + " "
					+ southlat + "))";
			// System.out.println(polygonStr + "=================");
			try {
				Polygon polygon2 = JTSUtil.createPolygonByWKT(polygonStr);
				if (polygon.contains(polygon2)) {
					mis.add(mi);
				}
			} catch (com.vividsolutions.jts.io.ParseException e) {
				e.printStackTrace();
				continue;
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByCategory(String username,
			String password, List<String> categorys) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		if (categorys == null || categorys.size() == 0) {
			return mis;
		}
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			List<String> sbis = mi.getDsci().getContentInfo_topicCategory();
			for (String sbi : sbis) {
				for (String str : categorys) {
					if (sbi != null) {
						if (sbi.contains(str)) {
							mis.add(mi);
						}
					}
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByKeywords(String username,
			String password, List<String> keywords) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		if (keywords == null || keywords.size() == 0) {
			return mis;
		}
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			List<String> sbis = mi.getIfi().getIdentificationInfo_keyword();
			for (String sbi : sbis) {
				for (String str : keywords) {
					if (sbi != null) {
						if (sbi.contains(str)) {
							mis.add(mi);
						}
					}
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByPurpose(String username,
			String password, String purpose) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getIfi().getIdentificationInfo_purpose();
			if (sbi != null) {
				if (sbi.contains(purpose)) {
					mis.add(mi);
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByFormatName(String username,
			String password, String formatname) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getIfi().getIdentificationInfo_datasetFormatName();
			if (sbi != null) {
				if (sbi.contains(formatname)) {
					mis.add(mi);
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByOrganName(String username,
			String password, String organname) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getIfi().getIdentificationInfo_organizationName();
			if (sbi != null) {
				if (sbi.contains(organname)) {
					mis.add(mi);
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByAbstract(String username,
			String password, String contentabstract) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getIfi().getIdentificationInfo_Abstract();
			if (sbi != null) {
				if (sbi.contains(contentabstract)) {
					mis.add(mi);
				}
			}
		}
		return mis;
	}

	public List<MetadataInfo> getMetadataInfoByTitle(String username,
			String password, String title) throws ServiceException,
			NullZeroException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<MetadataInfo> mis = new ArrayList<MetadataInfo>();
		for (MetadataInfo mi : DataSetInfoConfig.getMgis()) {
			String sbi = mi.getIfi().getIdentificationInfo_title();
			if (sbi != null) {
				if (sbi.contains(title)) {
					mis.add(mi);
				}
			}
		}
		return mis;
	}
}
