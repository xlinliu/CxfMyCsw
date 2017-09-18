package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.Service.interfaces.GetSpecialPlatformSensorsInterface;
import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.SensorBasciForOracleType;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.PlatformandSensors;
import com.csw.utils.custometypes.SensorInfo;

/**
 *��Ŀ����:CxfMyCsw �������� ��ȡָ��ƽ̨��������ȫ���Ĵ��������� ������:Administrator ����ʱ��: 2013-8-23
 * ����01:56:58
 */
public class GetSpecialPlatformSensorsService implements
		GetSpecialPlatformSensorsInterface {
	/**
	 * ���Գɹ�
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		GetSpecialPlatformSensorsInterface gspfi = new GetSpecialPlatformSensorsService();
		List<String> plats = OperateSensornewUtil.GetAllPlatformIds();
		List<PlatformandSensors> pslists = gspfi.GetSepcialPlatfromSensors(
				"admin", "cswadmin", plats);
		for (PlatformandSensors ps : pslists) {
			if (ps.getPlatform() != null) {
				System.out.println("------------------");
				System.out.println(ps.getPlatform().getSensor());
				System.out.println(ps.getPlatform().getSensorname());
				System.out.println(ps.getPlatform().getSensortype());
				if (ps.getSensors() != null) {
					for (SensorInfo si : ps.getSensors()) {
						System.out.println(si.getSensor());
						System.out.println(si.getSensorname());
						System.out.println(si.getSensortype());
					}
				}
			}
		}
	}

	public List<PlatformandSensors> GetSepcialPlatfromSensors(String username,
			String password, List<String> platforms) throws ServiceException {
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);
		if (platforms == null || platforms.isEmpty()) {
			return null;
		}
		// ��ȡ���еĴ�������ƽ̨����Ϣ
		List<PlatformandSensors> ps = OperateSensornewUtil
				.GetAllPlatformsandSensorIds("all");
		List<String> templatforms = new ArrayList<String>();
		for (PlatformandSensors p : ps) {
			for (String subp : platforms) {
				if (subp.equals(p.getPlatform().getSensor())) {
					templatforms.add(subp);
					continue;
				}
			}
		}
		if (templatforms.isEmpty()) {
			return null;
		}
		// ����tmeplatforms��ȡ������ȫ������Ҫ���ص�ƽ̨����Ϣ
		List<PlatformandSensors> temList = new ArrayList<PlatformandSensors>();
		for (PlatformandSensors p : ps) {
			for (String tem : templatforms) {
				if (p.getPlatform().getSensor().equals(tem)) {
					temList.add(p);
				}
			}
		}
		List<PlatformandSensors> results = new ArrayList<PlatformandSensors>();
		// ��ȡ���д������Ļ�����Ϣ
		List<SensorBasciForOracleType> sensorbasicinfos = OperateSensornewUtil
				.GetAllSensorBasicInfo(false, false);
		//System.out.println(sensorbasicinfos.size() + "����");
		// ���ƽ̨
		for (PlatformandSensors p : temList) {
			PlatformandSensors pSensors = new PlatformandSensors();
			for (SensorBasciForOracleType sbt : sensorbasicinfos) {
				if (sbt.getSENSORID().equals(p.getPlatform().getSensor())) {
					SensorInfo platInfo = new SensorInfo();
					platInfo.setSensor(sbt.getSENSORID());
					platInfo.setSensorname(sbt.getSENSORLONGNAME());
					platInfo.setSensortype(sbt.getSENSORTYPE());
					try {
						if(sbt.getSENSORSOSURL().split(",").length==2){
							platInfo.setSensorsosurl(sbt.getSENSORSOSURL().split(
									",")[0]);
							platInfo.setSensoroffering(sbt.getSENSORSOSURL().split(
									",")[1]);
						}else if(sbt.getSENSORSOSURL().split(",").length==1){
							platInfo.setSensorsosurl(sbt.getSENSORSOSURL().split(
							",")[0]);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					pSensors.setPlatform(platInfo);
					List<SensorInfo> tempsensors = new ArrayList<SensorInfo>();
					if (p.getSensors() != null && !p.getSensors().isEmpty()) {
						for (SensorInfo si : p.getSensors()) {
							for (SensorBasciForOracleType sbt1 : sensorbasicinfos) {
								if (StringUtil.DeletePackageStr(si.getSensor())
										.equals(sbt1.getSENSORID())) {
									SensorInfo siInfo = new SensorInfo();
									siInfo.setSensor(sbt1.getSENSORID());
									siInfo.setSensorname(sbt1
											.getSENSORLONGNAME());
									siInfo.setSensortype(sbt1.getSENSORTYPE());
									try {
										System.out.println(sbt1.getSENSORSOSURL());
										if(sbt1
												.getSENSORSOSURL().split(",").length==2){
											siInfo
											.setSensorsosurl(sbt1
													.getSENSORSOSURL()
													.split(",")[0]);
									siInfo
											.setSensoroffering(sbt1
													.getSENSORSOSURL()
													.split(",")[1]);
										}else if(sbt1
												.getSENSORSOSURL().split(",").length==1){
											siInfo
											.setSensorsosurl(sbt1
													.getSENSORSOSURL()
													.split(",")[0]);
										}
										
									} catch (Exception e) {
										e.printStackTrace();
									}
									tempsensors.add(siInfo);
									break;
								}
							}
						}
						pSensors.setSensors(tempsensors);
					}
				}
			}
			results.add(pSensors);
		}
		return results;
	}
}
