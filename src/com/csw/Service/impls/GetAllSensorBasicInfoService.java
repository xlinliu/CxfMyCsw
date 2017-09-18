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
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-29 下午05:11:45
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
	 * 获取所有的传感器的基本信息(其实也包括了
	 */
	public List<SensorBasicInfoType> getAllSatelliteSensorBasicInfo(
			String username, String password, boolean all)
			throws ServiceException {
		return GetAllSensorBasicInfo(username, password, all);
	}

	/**
	 * 获取传感器的全部信息
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
				sbit.setSensorid(sb.getSENSORID());// 标识符
				sbit.setSensorlongname(sb.getSENSORLONGNAME());// 全称
				sbit.setSensorshortname(sb.getSENSORSHORTNAME());// 简称
				sbit.setSensorbegintime(sb.getSENSORBEGINTIME());// 开始工作时间
				sbit.setSenosrendtime(sb.getSENSORENDTIME());// 结束工作时间
				sbit.setSensorbbox(sb.getSENSORBBOX());// 观测范围
				sbit.setSensorpos(sb.getSENSORPOS());// 观测位置
				sbit.setSensorkeyword(sb.getSENSORKEYWORD());// 关键字
				sbit.setSensororgan(sb.getSENSORORGAN());// 所属组织单位
				sbit.setSensorintendapp(sb.getSENSORINTENDAPP());// 预期应用
				sbit.setSensortype(sb.getSENSORTYPE());// 传感器类型
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
	 * 获取单个传感器的基本信息
	 */
	public SensorBasicInfoType GetSingleSensorBasicInfo(String username,
			String password, String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		SensorBasciForOracleType sb = OperateSensornewUtil
				.getSingleBasicInfoOfTable(sensorid);
		if (sb != null) {
			SensorBasicInfoType sbit = new SensorBasicInfoType();
			sbit.setSensorid(sb.getSENSORID());// 标识符
			sbit.setSensorlongname(sb.getSENSORLONGNAME());// 全称
			sbit.setSensorshortname(sb.getSENSORSHORTNAME());// 简称
			sbit.setSensorbegintime(sb.getSENSORBEGINTIME());// 开始工作时间
			sbit.setSenosrendtime(sb.getSENSORENDTIME());// 结束工作时间
			sbit.setSensorbbox(sb.getSENSORBBOX());// 观测范围
			sbit.setSensorpos(sb.getSENSORPOS());// 观测位置
			sbit.setSensorkeyword(sb.getSENSORKEYWORD());// 关键字
			sbit.setSensororgan(sb.getSENSORORGAN());// 所属组织单位
			sbit.setSensorintendapp(sb.getSENSORINTENDAPP());// 预期应用
			sbit.setSensortype(sb.getSENSORTYPE());// 传感器类型
			if (sb.getSENSORSOSURL().split(",").length == 2) {
				sbit.setSensorsosurl(sb.getSENSORSOSURL().split(",")[0]);
				sbit.setSensoroffering(sb.getSENSORSOSURL().split(",")[1]);
			}
			return sbit;
		}
		return null;
	}
}
