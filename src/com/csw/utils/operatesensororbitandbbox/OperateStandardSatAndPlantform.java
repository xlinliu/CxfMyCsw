package com.csw.utils.operatesensororbitandbbox;

import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.StandSatSensorPlatformPair;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.operatesensororbitandbbox.interfaces.OperateStandardSatandPlatformInterface;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-18 下午02:56:07
 */
public class OperateStandardSatAndPlantform implements
		OperateStandardSatandPlatformInterface {
	public static void main(String[] args) throws ServiceException {
		StandSatSensorPlatformPair sspp = new StandSatSensorPlatformPair();
		sspp.setFukuai(1000.0);
		sspp.setMaker("admin");
		sspp.setPlatform("yll");
		sspp.setSensorname("auqa");
		OperateSingleSatStandardNamePlatform.SaveStandardNameAndPlatform(sspp);
		List<StandSatSensorPlatformPair> sspps = OperateSingleSatStandardNamePlatform
				.ReadStandardNameAndPlatforms();
		for (StandSatSensorPlatformPair sp : sspps) {
			System.out.println(sp.getPlatform());
			System.out.println(sp.getFukuai());
		}
		System.out.println(OperateSingleSatStandardNamePlatform.IsExist("yll",
				"auqa"));
	}

	/**
	 * 删除全部标准
	 */
	public Boolean DeleteAllStandardSatAndPlatforms(String username,
			String password) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		try {
			List<StandSatSensorPlatformPair> sspps = OperateSingleSatStandardNamePlatform
					.ReadStandardNameAndPlatforms();
			if (sspps != null) {
				for (StandSatSensorPlatformPair sspp : sspps) {
					OperateSingleSatStandardNamePlatform
							.DeleteStandardNameAndPlatform(sspp);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除
	 */
	public Boolean DeleteStandardSatAndPlatform(String username,
			String password, StandSatSensorPlatformPair sspp)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSingleSatStandardNamePlatform
				.DeleteStandardNameAndPlatform(sspp);
	}

	/**
	 * 读取
	 */
	public List<StandSatSensorPlatformPair> ReadStandSatAndPlatform(
			String username, String password) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSingleSatStandardNamePlatform
				.ReadStandardNameAndPlatforms();
	}

	public Boolean SaveStandardSatAndPlatform(String username, String password,
			StandSatSensorPlatformPair sspp) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSingleSatStandardNamePlatform
				.SaveStandardNameAndPlatform(sspp);
	}

	public Boolean SaveStandardSatAndPlatforms(String username,
			String password, List<StandSatSensorPlatformPair> sspps)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSingleSatStandardNamePlatform
				.SaveStandardNameAndPlatform(sspps);
	}

	public Boolean UpdateStandardSatAndPlatform(String username,
			String password, StandSatSensorPlatformPair sspp)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSingleSatStandardNamePlatform
				.UpdateStandardNameAndPlatform(sspp);
	}

	public Boolean UpdateStandardSatAndPlatforms(String username,
			String password, List<StandSatSensorPlatformPair> sspps)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSingleSatStandardNamePlatform
				.UpdateStandardNameAndPlatforms(sspps);
	}

	public Boolean IsExist(String username, String password, String sensorname,
			String satplat) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSingleSatStandardNamePlatform
				.IsExist(sensorname, satplat);
	}
}
