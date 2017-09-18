package com.csw.utils.operatesensororbitandbbox;

import java.util.ArrayList;
import java.util.List;

import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.SatOrbitJiSuanType;
import com.csw.model.ebXMLModel.StandSatSensorPlatformPair;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.operatesensororbitandbbox.interfaces.OperateSatIdWithStandNameAndPlatformInterface;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-18 下午02:43:02
 */
public class OperateSensorOrbitAndBBOX implements
		OperateSatIdWithStandNameAndPlatformInterface {
	public static void main1(String[] args) throws ServiceException {
		OperateSensorOrbitAndBBOX osoabAndBBOX = new OperateSensorOrbitAndBBOX();
		SatOrbitJiSuanType sojst = new SatOrbitJiSuanType();
		sojst.setMaker("admin");
		sojst.setSatid("urn:ogc:feature:remotesensor:AVHRR3_Metop-B");
		OperateStandardSatAndPlantform andPlantform = new OperateStandardSatAndPlantform();
		List<StandSatSensorPlatformPair> ssspp = andPlantform
				.ReadStandSatAndPlatform("admin", "cswadmin");
		sojst.setSspp(ssspp.get(2));
		osoabAndBBOX.SaveSatWithStandardNameAndPlatform("admin", "cswadmin",
				sojst);
		osoabAndBBOX.GetSatStandardNameAndPlatformInfo("admin", "cswadmin",
				"urn:ogc:feature:remotesensor:AVHRR3_Metop-B");
		System.out.println("here");
	}

	public Boolean DeleteSatWithStandardNameAndPlatform(String username,
			String password, String satid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		SatOrbitJiSuanType sojst = OperateSingleSatOrbitAndBBOX
				.ReadSatWithStandardNameAndPlatform(satid);
		if (sojst != null) {
			return OperateSingleSatOrbitAndBBOX
					.DeleteSatWithStandardNameAndPlatform(sojst);
		} else {
			throw new ServiceException("需要删除的信息已不存在!");
		}
	}

	public Boolean DeleteSatWithStandardNameAndPlatforms(String username,
			String password, List<String> satids) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		if (satids != null && satids.size() != 0) {
			for (String satid : satids) {
				SatOrbitJiSuanType sojst = OperateSingleSatOrbitAndBBOX
						.ReadSatWithStandardNameAndPlatform(satid);
				if (sojst != null) {
					OperateSingleSatOrbitAndBBOX
							.DeleteSatWithStandardNameAndPlatform(sojst);
				}
			}
			return true;
		} else {
			throw new ServiceException("需要删除的信息已不存在!");
		}
	}

	public SatOrbitJiSuanType GetSatStandardNameAndPlatformInfo(
			String username, String password, String satid)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		if (satid != null && satid.length() != 0) {
			return OperateSingleSatOrbitAndBBOX
					.ReadSatWithStandardNameAndPlatform(satid);
		} else {
			return null;
		}
	}

	public List<SatOrbitJiSuanType> GetSatStandardNameAndPlatformInfos(
			String username, String password, List<String> satids)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SatOrbitJiSuanType> sojsts = new ArrayList<SatOrbitJiSuanType>();
		if (satids != null && satids.size() != 0) {
			for (String satid : satids) {
				SatOrbitJiSuanType satOrbitJiSuanType = OperateSingleSatOrbitAndBBOX
						.ReadSatWithStandardNameAndPlatform(satid);
				if (satOrbitJiSuanType != null) {
					sojsts.add(satOrbitJiSuanType);
				}
			}
		}
		return sojsts;
	}

	public Boolean IsExistWithStandNameAndPaltOfSatId(String username,
			String password, String satid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		if (satid != null && satid.length() != 0) {
			SatOrbitJiSuanType sojst = OperateSingleSatOrbitAndBBOX
					.ReadSatWithStandardNameAndPlatform(satid);
			if (null != sojst) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new ServiceException("没有正确输入相关信息");
		}
	}

	public Boolean SaveSatWithStandardNameAndPlatform(String username,
			String password, SatOrbitJiSuanType sojst) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSingleSatOrbitAndBBOX
				.SaveSatWithStandardNameAndPlatform(sojst);
	}

	public Boolean SaveSatWithStandardNameAndPlatforms(String username,
			String password, List<SatOrbitJiSuanType> sojst)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		if (sojst != null && sojst.size() != 0) {
			for (SatOrbitJiSuanType so : sojst) {
				OperateSingleSatOrbitAndBBOX
						.SaveSatWithStandardNameAndPlatform(so);
			}
			return true;
		} else {
			throw new ServiceException("请输入正确的信息");
		}

	}

	public Boolean UpdateSatWithStandardNameAndPlatform(String username,
			String password, SatOrbitJiSuanType sojst) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSingleSatOrbitAndBBOX
				.UpdateSatWithStandardNameAndPlatform(sojst);
	}

	public Boolean UpdateSatWithStandardNameAndPlatforms(String username,
			String password, List<SatOrbitJiSuanType> sojst)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		if (sojst != null && sojst.size() != 0) {
			for (SatOrbitJiSuanType so : sojst) {
				OperateSingleSatOrbitAndBBOX
						.UpdateSatWithStandardNameAndPlatform(so);
			}
			return true;
		} else {
			throw new ServiceException("请输入正确的信息");
		}

	}

	public static void main(String[] args) throws Exception {
		long pre = System.currentTimeMillis();
		OperateSensorOrbitAndBBOX operateSensorOrbitAndBBOX = new OperateSensorOrbitAndBBOX();
		List<SatOrbitJiSuanType> ssJiSuanTypes = operateSensorOrbitAndBBOX
				.getAllSatOrbitJiSuanTypes("admin", "cswadmin");

		for (SatOrbitJiSuanType s : ssJiSuanTypes) {
			System.out.println(s.getSatid());
		}
		long now = System.currentTimeMillis();
		System.out.println(now - pre + "毫秒");

	}

	public List<SatOrbitJiSuanType> getAllSatOrbitJiSuanTypes(String username,
			String password) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSingleSatOrbitAndBBOX.getAllJiSuanTypes();
	}
}
