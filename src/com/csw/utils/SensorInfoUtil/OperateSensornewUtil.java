package com.csw.utils.SensorInfoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.joda.time.DateTime;
import com.csw.model.ebXMLModel.SensorBasciForOracleType;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.custometypes.PlatformAndSensorIdsForOracleType;
import com.csw.utils.custometypes.PlatformandSensors;
import com.csw.utils.custometypes.SensorInfo;
import com.csw.utils.custometypes.SensorIntendAppForOracleType;
import com.csw.utils.custometypes.SensorOrganAndShareLevelForOracleType;
import com.csw.utils.custometypes.SensorOrganForOracleType;
import com.csw.utils.custometypes.SensorSOSInfo;
import com.csw.utils.custometypes.SensorShareLevel;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-25 ����03:12:21
 */
public class OperateSensornewUtil {
	public static List<SensorIntendAppForOracleType> staticsinapp = new ArrayList<SensorIntendAppForOracleType>();// ��̬���洫����Ԥ��Ӧ��
	public static DateTime staticsinapp_time = null;// ��̬���洫����Ԥ��Ӧ�ò�ѯʱ��
	// public static List<SensorOrganAndShareLevelForOracleType> staticsoaslot =
	// new ArrayList<SensorOrganAndShareLevelForOracleType>();//
	// ���澲̬�Ĵ�������֯�͹�������Ϣ

	// public static List<SensorOrganAndShareLevelForOracleType> staticsoaslot =
	// null;// ���澲̬�Ĵ�������֯�͹�������Ϣ
	public static DateTime staticsoaslot_time = null;// ��̬���洫�����������֯��λ�Ĳ�ѯʱ��

	public static List<SensorBasciForOracleType> getAllSensorBasicInfo() {
		List<SensorBasciForOracleType> results = new ArrayList<SensorBasciForOracleType>();
		for (String str : OperateSensorUtil.GetAllSensorIdsMethod()) {
			results.add(GetSensorBasicInfo(str));
		}
		return results;
	}

	public static String get() {
		String queryString = "select * from slot where SUBSTR(id,0,instr(id,':ExtrinsicObject:')-1)  in (select allids.ids from (select distinct SUBSTR(id,0,instr(id,':ExtrinsicObject:')-1)  ids from slot) allids)";
		GetSessionUtil.getSelectQueryInfoByNativeSQL(queryString);
		return "";
	}

	/**
	 * ��ȡ���еĴ�������Ԥ��Ӧ�ú͹���ļ���
	 * 
	 * @return
	 */
	public static List<SensorOrganAndShareLevelForOracleType> GetSensorOrganAndShareInfos() {
		List<SensorOrganAndShareLevelForOracleType> results = new ArrayList<SensorOrganAndShareLevelForOracleType>();
		SensorOrganAndShareLevelForOracleType soafo = null;
		for (SensorBasciForOracleType sbfot : getAllBasicInfoOfTable()) {
			soafo = new SensorOrganAndShareLevelForOracleType();
			soafo.setSENSORID(sbfot.getSENSORID());
			soafo.setSENSORORGAN(sbfot.getSENSORORGAN());
			soafo.setSENSOROWNER(sbfot.getSENSOROWNER());
			soafo.setSENSORSHARELEVEL(sbfot.getSENSORPUBLIC());
			results.add(soafo);
		}
		return results;
	}

	/**
	 * ��ȡ���е�SOSinfo����Ϣ
	 * 
	 * @return ���ص���Map����keyΪsos�ĵ�ַ��value�ǰ���sos��ַ��sos�����ź����sos�����Ĵ�������ʶ������
	 */
	public static Map<String, SensorSOSInfo> getAllSensorSOSInfos() {
		Map<String, SensorSOSInfo> sssi = new HashMap<String, SensorSOSInfo>();
		List<SensorBasciForOracleType> sbfts = getAllBasicInfoOfTable();
		String str = "";
		String[] tempstrs = null;
		SensorSOSInfo ssitemp = null;
		for (SensorBasciForOracleType sbft : sbfts) {
			try {
				str = sbft.getSENSORSOSURL();
				tempstrs = str.split(",");
				if (sssi.containsKey(tempstrs[0])) {
					sssi.get(str).setSosurl(tempstrs[0]);
					if (tempstrs.length == 2) {
						sssi.get(str).setSosoffering(tempstrs[1]);
					}
					sssi.get(str).getSosinfos().add(sbft.getSENSORID());
				} else {
					ssitemp = new SensorSOSInfo();
					ssitemp.setSosurl(tempstrs[0]);
					if (tempstrs.length == 2) {
						ssitemp.setSosoffering(tempstrs[1]);
					}
					ssitemp.getSosinfos().add(sbft.getSENSORID());
					sssi.put(str, ssitemp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sssi;
	}

	/**
	 * ��ȡָ���û���ȫ���Ĵ������Ĺ�����Ϣ
	 * 
	 * @param username
	 * @return
	 */
	public static List<SensorShareLevel> GetSensorShareLevelsOfOwner(
			String username) {
		List<SensorShareLevel> ssls = new ArrayList<SensorShareLevel>();
		List<SensorBasciForOracleType> sbits = getAllBasicInfoOfTable();
		if (sbits != null && !sbits.isEmpty()) {
			for (SensorBasciForOracleType sl : sbits) {
				if (sl.getSENSOROWNER().equals(username)) {
					SensorShareLevel ssl = new SensorShareLevel();
					ssl.setSensorid(sl.getSENSORID());
					ssl.setSharelevel(sl.getSENSORPUBLIC());
					ssl.setSensorowner(sl.getSENSOROWNER());
					if (sl.getSENSORPUBLIC().equals("����")) {
						ssl.setIsShare(true);
					} else {
						ssl.setIsShare(false);
					}
					ssls.add(ssl);
				}
			}
		}
		return ssls;
	}

	/**
	 * ��ȡ���д������Ĺ����ԣ������û�
	 * 
	 * @param ��ȡ���д������Ĺ���
	 * @return
	 */
	public static List<SensorShareLevel> GetAllSensorShareLevels() {
		List<SensorShareLevel> ssls = new ArrayList<SensorShareLevel>();
		List<SensorBasciForOracleType> sbits = getAllBasicInfoOfTable();
		if (sbits != null && !sbits.isEmpty()) {
			for (SensorBasciForOracleType sl : sbits) {
				SensorShareLevel ssl = new SensorShareLevel();
				ssl.setSensorid(sl.getSENSORID());
				ssl.setSharelevel(sl.getSENSORPUBLIC());
				ssl.setSensorowner(sl.getSENSOROWNER());
				if (sl.getSENSORPUBLIC().equals("����")) {
					ssl.setIsShare(true);
				} else {
					ssl.setIsShare(false);
				}
				ssls.add(ssl);
			}
		}
		return ssls;
	}

	/**
	 * ��ȡĳ�û����ܲ鿴��ȫ���������ı�ʶ��
	 * 
	 * @param owner
	 *            ��������Ա�����Բ鿴ȫ������ͨ����Ա�����Բ鿴ȫ���Լ��ģ��Ƿ�鿴ȫ������������(bolΪtrue��ȫ����ѯ������Ϊ����ѯ������
	 *            )��������οͣ�bolΪfalse�����ؿգ����򷵻�����ȫ���Ĺ����Ĵ�����
	 * @return
	 */
	public static List<String> GetAllAllowedSensors(String owner, boolean bol) {
		return null;
	}

	/**
	 * ��ȡ���еĴ�����ƽ̨������صĴ������ı�ʶ������Ҫ˵�����ǣ����а����Ĳ��ֵĴ�������ʶ����ϵͳ���ǿ��ܲ����ڵ�
	 * 
	 * @return ���ص���Ϣ��ֻ����ƽ̨�ı�ʶ���ʹ������ı�ʶ����������ϢΪnull
	 */
	public static List<PlatformandSensors> GetAllPlatformsandSensorIds(
			String platformtype) {
		List<PlatformandSensors> pfs = new ArrayList<PlatformandSensors>();
		PlatformandSensors pSensors = null;
		String query = "";
		if (platformtype.equals("all")) {
			query = "select  to_char(replace( substr(t.valuess,0,length(t.valuess)-1) ,',',',')) SUBSENSORS,SUBSTR(id,0,instr(id,':ExtrinsicObject:')-1) PLATFORMID from Slot t  where t.name like '%associatedSensorUniqueID' ";
		} else {
			query = "select  to_char(replace( substr(t.valuess,0,length(t.valuess)-1) ,',',',')) SUBSENSORS,SUBSTR(t.id,0,instr(t.id,':ExtrinsicObject:')-1) PLATFORMID from Slot t ,Slot v  where t.name like '%associatedSensorUniqueID' and v.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::SensorType' and SUBSTR(t.id,0,instr(t.id,':ExtrinsicObject:')-1)=SUBSTR(v.id,0,instr(v.id,':ExtrinsicObject:')-1) and v.valuess= '"
					+ platformtype + ",' ";
		}
		List<PlatformAndSensorIdsForOracleType> pasift = GetSessionUtil
				.GetAllPlatformAndSensorIds(query);
		// �Ѿ�����
		List<String> existingSensors = OperateSensorUtil
				.GetAllSensorIdsMethod();
		SensorInfo sInfo = null;
		for (PlatformAndSensorIdsForOracleType str : pasift) {
			pSensors = new PlatformandSensors();
			sInfo = new SensorInfo();
			sInfo.setSensor(str.getPLATFORMID());
			pSensors.setPlatform(sInfo);
			if (str.getSUBSENSORS() != null && !str.getSUBSENSORS().isEmpty()) {
				String[] strs = str.getSUBSENSORS().split(",");
				if (strs.length > 0) {
					List<SensorInfo> sis = new ArrayList<SensorInfo>();
					for (String st : strs) {
						if (existingSensors.contains(st)) {
							sInfo = new SensorInfo();
							sInfo.setSensor(st);
							sis.add(sInfo);
						}
					}
					pSensors.setSensors(sis);
				}
			}
			pfs.add(pSensors);
		}
		return pfs;
	}

	/**
	 * ��ȡ���д�������Ԥ��Ӧ��
	 * 
	 * @return
	 */
	public static List<SensorIntendAppForOracleType> GetSensorIntendapps() {
		if (staticsinapp_time != null) {
			long p = new DateTime().getMillis()
					- (staticsinapp_time.getMillis());
			if (p < 1000 * 60) {
				return staticsinapp;
			}
		}
		List<SensorIntendAppForOracleType> ssls = new ArrayList<SensorIntendAppForOracleType>();
		List<SensorBasciForOracleType> sbits = getAllBasicInfoOfTable();
		if (sbits != null && !sbits.isEmpty()) {
			SensorIntendAppForOracleType sin = null;
			for (SensorBasciForOracleType sb : sbits) {
				sin = new SensorIntendAppForOracleType();
				sin.setSENSORID(sb.getSENSORID());
				sin.setSENSORINTEND(sb.getSENSORINTENDAPP());
				sin.setSENSOROWNER(sb.getSENSOROWNER());
				ssls.add(sin);
			}
		}
		// ��������Ĳ�ѯ����
		staticsinapp = ssls;
		staticsinapp_time = new DateTime();
		return ssls;
	}

	/**
	 * ��ȡ���еĴ���������֯��λ��Ϣ
	 * 
	 * @return
	 */
	public static List<SensorOrganForOracleType> GetSensorOrganInfos() {
		List<SensorOrganForOracleType> results = new ArrayList<SensorOrganForOracleType>();
		SensorOrganForOracleType sofot = null;
		List<SensorBasciForOracleType> sbits = getAllBasicInfoOfTable();
		for (SensorBasciForOracleType sbit : sbits) {
			sofot = new SensorOrganForOracleType();
			sofot.setSENSORID(sbit.getSENSORID());
			sofot.setSENSORORGAN(sbit.getSENSORORGAN());
			results.add(sofot);
		}

		return results;
	}

	/**
	 * ��ȡ��������������֯��λ��Ϣ
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorOrganInfo(String sensorid) {
		// long pre = System.currentTimeMillis();
		String queryStr = "select " + "organ.value  "
				+ "from LocalizedString organ" + " where " + "organ.id "
				+ "like '" + sensorid + ":org:%:Name'";
		List<String> intendapps = GetSessionUtil
				.getSelectQueryInfoByNativeSQL(queryStr);
		// System.out.println(intendapps.toString());
		// long now = System.currentTimeMillis();
		// System.out.println(now - pre + "����");
		String intendappStr = "";
		if (intendapps != null) {
			intendappStr = intendapps.get(0);
		}
		return intendappStr;
	}

	/**
	 * ��ȡԤ��Ӧ��
	 * 
	 * @param sensorid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String GetSensorIntendapp(String sensorid) {
		long pre = System.currentTimeMillis();
		String queryStr = "select to_char(replace(value,',',','))  "
				+ "intendapp from  localizedstring where id in"
				+ " (select name from  classificationnode "
				+ "where id like '"
				+ sensorid
				+ "%' and parent in (select distinct  id from ObjectRef "
				+ "where id like '"
				+ sensorid
				+ "%' and home ='urn:ogc:def:classificationScheme:OGC-CSW-ebRIM-Sensor::IntendedApplication'))";
		List<String> intendapps = GetSessionUtil
				.getSelectQueryInfoByNativeSQL(queryStr);
		long now = System.currentTimeMillis();
		System.out.println(now - pre + "����");
		String intendappStr = "";
		if (intendapps != null) {
			for (String str : intendapps) {
				intendappStr = intendappStr + "," + str;
			}
		}
		if (!intendappStr.equals("")) {
			intendappStr = intendappStr.substring(1);
		}
		return intendappStr;
	}

	/**
	 * ��ȡָ���û���ȫ���Ĵ������Ļ�����Ϣ������ƽ̨����Ϣ
	 * 
	 * @param username
	 * @return
	 */
	public static List<SensorBasciForOracleType> GetAllSensorBasicInfoOfOwner(
			String username) {
		List<SensorBasciForOracleType> results = new ArrayList<SensorBasciForOracleType>();
		List<SensorBasciForOracleType> sbits = getAllBasicInfoOfTable();
		for (SensorBasciForOracleType sb : sbits) {
			if (sb.getSENSOROWNER().equals(username)) {
				results.add(sb);
			}
		}
		return results;
	}

	//
	// /**
	// * ��ȡ�������д���������Ϣ(����Щ��û��λ�ã����Բ��ܲ�ѯ�Ĳ��֣�
	// */
	// public static List<SensorBasciForOracleType>
	// GetAllSensorBasicInfoWithSatellite(
	// boolean intendapp, boolean organandsharelevel) {
	// String queryStr = "select"
	// + " substr(t.valuess,0,length(t.valuess)-1) SENSORTYPE,"
	// + " substr(ope.valuess,0,length(ope.valuess)-1) SENSOROPERABLE,"
	// + " allids.id SENSORID,"
	// + " ls.value SENSORLONGNAME,"
	// + " substr(sn.valuess,0,length(sn.valuess)-1) SENSORSHORTNAME,"
	// + " substr(bx.valuess,2,length(bx.valuess)-3) SENSORBBOX,"
	// + " substr(kw.valuess,0,length(kw.valuess)-1) SENSORKEYWORD,"
	// + " substr(s.valuess,0,length(s.valuess)-1) SENSORBEGINTIME,"
	// + " substr(et.valuess,0,length(et.valuess)-1) SENSORENDTIME"
	// + " from"
	// + " registrypackage r,"
	// +
	// " (select distinct SUBSTR(id, 0, instr(id,':ExtrinsicObject:')-1)  id from slot)allids,"
	// + " Slot ope,"
	// + " LocalizedString ls,"
	// + " Slot sn,"
	// + " Slot bx,"
	// + " Slot kw,"
	// + " slot  s, "
	// + " slot et,"
	// + " slot t"
	// + " where "
	// + " r.id = allids.id||':package'"
	// +
	// " and ope.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::physicalProperties:property:isOperable'"
	// + " and ope.SLOT_ID= allids.id "
	// + " and ls.id =allids.ID ||':LName' "
	// + " and sn.name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::ShortName' "
	// + " and sn.id like allids.id||':%' "
	// + " and bx.name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::BoundedBy' "
	// + " and bx.id like allids.id||':%'"
	// + " and kw.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::Keywords'"
	// + " and kw.id like allids.id||':%'"
	// +
	// " and s.name like 'urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::ValidTimeBegin'"
	// + " and s.id like allids.id||':%'"
	// +
	// " and et.name like 'urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::ValidTimeEnd' "
	// + " and et.id like  allids.id||':%'"
	// + " and t.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::SensorType' "
	// + " and t.id like allids.id||':%'";
	// return GetAllSensorBasicInfoTemple(intendapp, organandsharelevel,
	// queryStr);
	// }

	/**
	 * ��ȡ���д������Ļ�����Ϣ,����Ԥ��Ӧ��(������)���ؼ��֣��۲ⷶΧ��λ�ã���֯�����ͣ���ʼʱ�䣬����ʱ�䣬ȫ�ƣ���ƣ��ɿ��ԣ�������
	 * ע�⣺�����֧�����Ǵ�������ӦΪû��λ����Ϣ�����²鲻�������
	 * 
	 * @param intendapp
	 *            �Ƿ����Ԥ��Ӧ��
	 * @param organandsharelevel�Ƿ�������������֯��λ��Ϣ
	 * @return
	 */
	public static List<SensorBasciForOracleType> GetAllSensorBasicInfo(
			boolean intendapp, boolean organandsharelevel) {
		return getAllBasicInfoOfTable();
	}


	/**
	 * ��ȡָ���������Ļ�����Ϣ����������ʼ����ʱ�䣬����ʱ�䣬ȫ�ƣ���ƣ���֯��λ��λ�ã��ռ䷶Χ���ؼ���,Ԥ��Ӧ�õ�
	 * 
	 * @param sensorid
	 *            ��Ҫ��ѯ�Ĵ������ı�ʶ��
	 * @return
	 */
	public static SensorBasciForOracleType GetSensorBasicInfo(String sensorid) {
		return OperateSensornewUtil.getSingleSensorBasicInfo(sensorid, true,
				true);

	}
	/**
	 * �����õ���Ϣ�洢��ĳһ����н��д洢����ȡȫ���Ĵ�����
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SensorBasciForOracleType> getAllBasicInfoOfTable() {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		String queryStr = "from SensorBasciForOracleType";
		session.beginTransaction().begin();
		// System.out.println(queryStr);
		List<SensorBasciForOracleType> list = session.createQuery(queryStr)
				.list();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		return list;
	}

	/**
	 * �����õ���Ϣ�洢��ĳһ����н��д洢,��ȡ��һ�Ĵ�������Ϣ
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static SensorBasciForOracleType getSingleBasicInfoOfTable(
			String sensorid) {
		SensorBasciForOracleType result = null;
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		String queryStr = "from SensorBasciForOracleType where sensorid='"
				+ sensorid + "'";
		session.beginTransaction().begin();
		// System.out.println(queryStr);
		List<SensorBasciForOracleType> list = session.createQuery(queryStr)
				.list();
		if (list == null || !list.isEmpty()) {
			result = list.get(0);
		}
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		return result;
	}

	/**
	 * ���洫������Ϣ
	 * 
	 * @param sensorid
	 */
	public static void saveSensorBasicInfo(String sensorid) {
		SensorBasciForOracleType temp = getSingleSensorBasicInfo(sensorid,
				true, true);
		if(temp==null){
			return;
		}
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		session.save(temp);
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
	}

	/**
	 * ɾ��ָ���Ĵ�������Ϣ
	 * 
	 * @param sensorid
	 */
	public static void deleteSensorBasicInfo(String sensorid) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		String queryStr = "delete from SensorBasciForOracleType where sensorid='"
				+ sensorid + "'";
		session.beginTransaction().begin();
		// System.out.println(queryStr);
		session.createQuery(queryStr).executeUpdate();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
	}

	/**
	 * �����޸Ĳ��֣�2014-10-09����10��50��
	 * ��ȡ�����������Ļ�����Ϣ������ǲ��ܻ�ȡң�д���������Ϣ�ģ���Ϊû��λ����Ϣ�����²�ѯ�����Ķ���null��
	 * 
	 * @param sensorid
	 * @param intendapp
	 * @param organandsharelevel
	 * @return
	 */
	public static SensorBasciForOracleType getSingleSensorBasicInfo(
			String sensorid, boolean intendapp, boolean organandsharelevel) {
		String queryStr = "select"
				+ " substr(t.valuess,0,length(t.valuess)-1) SENSORTYPE,"
				+ " substr(ope.valuess,0,length(ope.valuess)-1) SENSOROPERABLE,"
				+ " substr(r.id,0,instr(r.id,':package')-1) SENSORID,"
				+ " ls.value SENSORLONGNAME,"
				+ " substr(sn.valuess,0,length(sn.valuess)-1) SENSORSHORTNAME,"
				+ " substr(p.valuess,2,length(p.valuess)-3) SENSORPOS,"
				+ " substr(bx.valuess,2,length(bx.valuess)-3) SENSORBBOX,"
				+ " substr(kw.valuess,0,length(kw.valuess)-1) SENSORKEYWORD,"
				+ " substr(s.valuess,0,length(s.valuess)-1) SENSORBEGINTIME,"
				+ " substr(et.valuess,0,length(et.valuess)-1) SENSORENDTIME,"
				+ " substr(jj.valuess,0,length(jj.valuess)-1) SENSORSOSURL"
				+ " from" + " registrypackage r," + " Slot ope,"
				+ " LocalizedString ls," + " Slot sn," + " Slot p,"
				+ " Slot bx," + " Slot kw," + " slot  s, " + " slot et,"
				+ " slot t,slot jj" + " where " + " r.id =  '"
				+ sensorid
				+ ":package'"
				+ " and "
				+ " ope.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::physicalProperties:property:isOperable'"
				+ " and ope.SLOT_ID=  '"
				+ sensorid
				+ "' and ls.id = '"
				+ sensorid
				+ ":LName' "
				+ " and sn.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::ShortName' "
				+ " and sn.id like '"
				+ sensorid
				+ ":%' "
				+ " and p.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::Location'"
				+ " and  p.id like  '"
				+ sensorid
				+ ":%' "
				+ " and bx.name='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::BoundedBy' "
				+ " and bx.id like  '"
				+ sensorid
				+ ":%'"
				+ " and kw.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::Keywords'"
				+ " and kw.id like  '"
				+ sensorid
				+ ":%'"
				+ " and s.name like 'urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::ValidTimeBegin'"
				+ " and s.id like  '"
				+ sensorid
				+ ":%'"
				+ " and et.name like 'urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::ValidTimeEnd' "
				+ " and et.id like  '"
				+ sensorid
				+ ":%'"
				+ " and t.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::SensorType' "
				+ " and t.id like  '"
				+ sensorid
				+ ":%'"
				+ " and jj.name ='urn:ogc:def:interface:OGC:1.0:ServiceURL' "
				+ " and jj.slot_id = '" + sensorid + "'";
		return getSingleSensorBasicInfoTemple(sensorid, intendapp,
				organandsharelevel, queryStr);
	}

	private static SensorBasciForOracleType getSingleSensorBasicInfoTemple(
			String sensorid, boolean intendapp, boolean organandsharelevel,
			String queryStr) {
		List<SensorBasciForOracleType> str = GetSessionUtil
				.getSensorBasicInfo(queryStr);
		queryStr = null;
		if (str == null || str.size() == 0) {
			return null;
		}
		SensorBasciForOracleType temp = str.get(0);
		str = null;
		if (intendapp) {
			temp.setSENSORINTENDAPP(GetSensorIntendapp(sensorid));
		}
		if (organandsharelevel) {
			// ��ȡ�ɿ���Ϣ
			temp.setSENSORORGAN(GetSensorOrganInfo(sensorid));
			// �����sensorml�ж�ȡ��������������
			temp.setSENSOROWNER(OperateSensorUtil
					.GetSensorMLBasicInfo(sensorid).getOwner());
		}
		// ��ȡ�������ɿ���Ϣ�빫����Ϣ
		temp.setSENSOROPERABLE(""
				+ OperateSensorUtil.GetSensorOperatable(sensorid)
						.getIsOperable());
		temp.setSENSORPUBLIC(OperateSensorUtil.GetSensorShareLevel(sensorid)
				.getSharelevel());
		return temp;
	}

	// ��ȡ����ƽ̨�ı�ʶ��
	@SuppressWarnings("unchecked")
	public static List<String> GetAllPlatformIds() {
		String queryStr = "select distinct  platformid from (select distinct  SUBSTR(s.id,0,instr(s.id,':ExtrinsicObject:')-1) platformid from slot s, slot t,slot v  where SUBSTR(t.id,0,instr(t.id,':ExtrinsicObject:')-1)=SUBSTR(s.id,0,instr(s.id,':ExtrinsicObject:')-1) and s.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::SensorType' and v.name  like '%associatedSensorUniqueID' and  SUBSTR(t.id,0,instr(t.id,':ExtrinsicObject:')-1) = SUBSTR(v.id,0,instr(v.id,':ExtrinsicObject:')-1))";
		List<String> types = GetSessionUtil
				.getSelectQueryInfoByNativeSQL(queryStr);
		return types;
	}

	/**
	 * ��ȡָ�����͵�ƽ̨��ʶ��
	 * 
	 * @param platformtype
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> GetSpecialPlatformIds(String platformtype) {
		long pre = System.currentTimeMillis();
		String queryStr = "select distinct  platformid from (select distinct  SUBSTR(s.id,0,instr(s.id,':ExtrinsicObject:')-1) platformid from slot s, slot t,slot v  where SUBSTR(t.id,0,instr(t.id,':ExtrinsicObject:')-1)=SUBSTR(s.id,0,instr(s.id,':ExtrinsicObject:')-1) and s.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::SensorType' and v.name  like '%associatedSensorUniqueID' and  SUBSTR(t.id,0,instr(t.id,':ExtrinsicObject:')-1) = SUBSTR(v.id,0,instr(v.id,':ExtrinsicObject:')-1)  and  s.valuess = '"
				+ platformtype + ",')";
		List<String> list = GetSessionUtil
				.getSelectQueryInfoByNativeSQL(queryStr);
		long now = System.currentTimeMillis();
		System.out.println(now - pre + "����");
		return list;
	}

	/**
	 * ��ȡ����ƽ̨����
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> GetAllPlatformTypes() {
		long pre = System.currentTimeMillis();
		String queryStr = "select distinct substr(s.values,0,length(s.values)-1)"
				+ " from Slot s,Slot t,Slot v where"
				+ " SUBSTR(t.id,0,instr(t.id,':ExtrinsicObject:')-1)=SUBSTR(s.id,0,instr(s.id,':ExtrinsicObject:')-1) "
				+ "and s.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::SensorType'"
				+ " and v.name like '%associatedSensorUniqueID' and "
				+ "SUBSTR( v.id,0,instr( v.id,':ExtrinsicObject:')-1)=SUBSTR(s.id,0,instr(s.id,':ExtrinsicObject:')-1)";
		List<String> types = GetSessionUtil.GetSelectQueryInfo(queryStr);
		long now = System.currentTimeMillis();
		System.out.println("��ȡ����ƽ̨������ " + (now - pre) + "����");
		return types;
	}

	/**
	 * ��ȡ���еĴ��������ͣ�����ƽ̨�ʹ�����
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> GetAllPlatformandSensorTypes() {
		String queryStr = "select distinct substr(s.values,0,length(s.values)-1)"
				+ " from Slot s,Slot t where"
				+ " SUBSTR(t.id,0,instr(t.id,':ExtrinsicObject:')-1)=SUBSTR(s.id,0,instr(s.id,':ExtrinsicObject:')-1) "
				+ "and s.name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::SensorType'";
		List<String> types = GetSessionUtil.GetSelectQueryInfo(queryStr);
		for (String str : types) {
			System.out.println(str);
		}
		return types;
	}
}
