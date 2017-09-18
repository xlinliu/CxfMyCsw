/**
 * 
 */
package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;

import com.csw.Service.interfaces.GetAllSensorBasicInfoInterface;
import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.SensorBasciForOracleType;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBasicInfoType;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-29 ����05:11:45
 */
public class GetAllSensorBasicInfoService implements
		GetAllSensorBasicInfoInterface {
	public static void main(String[] args) throws ServiceException {
		GetAllSensorBasicInfoService gs = new GetAllSensorBasicInfoService();
		// SensorBasicInfoType sbit = gs.GetSingleSensorBasicInfo("admin",
		// "cswadmin",
		// "urn:fzkcy:insitusensor:RFIDPlatform3501042014090200006171-01");
		// System.out.println(sbit.getSensorlongname());
		// List<SensorBasicInfoType> sbit = gs.getAllSatelliteSensorBasicInfo(
		// "admin", "cswadmin", true);
		// System.out.println(sbit.getSensortype());
		// System.out.println(sbit.getSensorsosurl());
		// System.out.println(sbit.getSensoroffering());
		// System.out.println("-----------------");
		List<SensorBasicInfoType> sbist = gs.GetAllSensorBasicInfo("admin",
				"cswadmin", true);
		int i = 1;
		for (SensorBasicInfoType sbi : sbist) {
			System.out.println((i++) + " : " + sbi.getSensorid() + "  :  "
					+ sbi.getSensortype());
		}
	}

	/**
	 * ��ȡ���еĴ������Ļ�����Ϣ(��ʵҲ������
	 */
	public List<SensorBasicInfoType> getAllSatelliteSensorBasicInfo(
			String username, String password, boolean all)
			throws ServiceException {
		return GetAllSensorBasicInfo(username, password, all);
	}

	/**
	 * ��ȡ��������ȫ����Ϣ
	 */
	public List<SensorBasicInfoType> GetAllSensorBasicInfo(String username,
			String password, boolean all) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBasciForOracleType> sbfts = OperateSensornewUtil
				.getAllBasicInfoOfTable();
		List<SensorBasicInfoType> sbits = new ArrayList<SensorBasicInfoType>();
		if (sbfts != null && !sbfts.isEmpty()) {
			SensorBasicInfoType sbit = null;
			for (SensorBasciForOracleType sb : sbfts) {
				sbit = new SensorBasicInfoType();
				sbit.setSensorid(sb.getSENSORID());// ��ʶ��
				sbit.setSensorlongname(sb.getSENSORLONGNAME());// ȫ��
				sbit.setSensorshortname(sb.getSENSORSHORTNAME());// ���
				sbit.setSensorbegintime(sb.getSENSORBEGINTIME());// ��ʼ����ʱ��
				sbit.setSenosrendtime(sb.getSENSORENDTIME());// ��������ʱ��
				sbit.setSensorbbox(sb.getSENSORBBOX());// �۲ⷶΧ
				sbit.setSensorpos(sb.getSENSORPOS());// �۲�λ��
				sbit.setSensorkeyword(sb.getSENSORKEYWORD());// �ؼ���
				sbit.setSensororgan(sb.getSENSORORGAN());// ������֯��λ
				sbit.setSensorintendapp(sb.getSENSORINTENDAPP());// Ԥ��Ӧ��
				sbit.setSensortype(sb.getSENSORTYPE());// ����������
				if (sb.getSENSORSOSURL() != null && sb.getSENSORSOSURL() != "") {
					if (sb.getSENSORSOSURL().split(",").length == 2) {
						sbit
								.setSensorsosurl(sb.getSENSORSOSURL()
										.split(",")[0]);
						sbit
								.setSensoroffering(sb.getSENSORSOSURL().split(
										",")[1]);
					}
				}
				if (all) {
					sbits.add(sbit);
				} else {
					if (sb.getSENSOROWNER().equals(username)) {
						sbits.add(sbit);
					}
				}
			}
		}
		return sbits;
	}

	/**
	 * ��ȡ�����������Ļ�����Ϣ
	 */
	public SensorBasicInfoType GetSingleSensorBasicInfo(String username,
			String password, String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		SensorBasciForOracleType sb = OperateSensornewUtil
				.getSingleBasicInfoOfTable(sensorid);
		if (sb != null) {
			SensorBasicInfoType sbit = new SensorBasicInfoType();
			sbit.setSensorid(sb.getSENSORID());// ��ʶ��
			sbit.setSensorlongname(sb.getSENSORLONGNAME());// ȫ��
			sbit.setSensorshortname(sb.getSENSORSHORTNAME());// ���
			sbit.setSensorbegintime(sb.getSENSORBEGINTIME());// ��ʼ����ʱ��
			sbit.setSenosrendtime(sb.getSENSORENDTIME());// ��������ʱ��
			sbit.setSensorbbox(sb.getSENSORBBOX());// �۲ⷶΧ
			sbit.setSensorpos(sb.getSENSORPOS());// �۲�λ��
			sbit.setSensorkeyword(sb.getSENSORKEYWORD());// �ؼ���
			sbit.setSensororgan(sb.getSENSORORGAN());// ������֯��λ
			sbit.setSensorintendapp(sb.getSENSORINTENDAPP());// Ԥ��Ӧ��
			sbit.setSensortype(sb.getSENSORTYPE());// ����������
			if (sb.getSENSORSOSURL().split(",").length == 2) {
				sbit.setSensorsosurl(sb.getSENSORSOSURL().split(",")[0]);
				sbit.setSensoroffering(sb.getSENSORSOSURL().split(",")[1]);
			}
			return sbit;
		}
		return null;
	}
}
