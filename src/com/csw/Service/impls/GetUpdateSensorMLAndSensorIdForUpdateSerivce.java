package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.csw.Service.interfaces.GetUpdateSensorMLAndSensorIdForUpdateServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class GetUpdateSensorMLAndSensorIdForUpdateSerivce implements
		GetUpdateSensorMLAndSensorIdForUpdateServiceInterface {

	public Map<String, String> GetUpdateSesnorMLAndSensorIdForUpdateByIdsMethod(
			String username, String password, List<String> sensormlids,
			String resultidType) throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		if (sensormlids == null || sensormlids.size() == 0) {
			throw new ServiceException("参数sensormlids输入错误，请核实!");
		}
		if (resultidType == null || resultidType.trim().equals("")
				|| !resultidType.trim().toLowerCase().equals("ebrim")
				|| !resultidType.trim().toLowerCase().equals("sensorml")) {
			throw new ServiceException("参数resultidType输入错误，请核实!");
		}
		Map<String, String> maps = new HashMap<String, String>();
		for (int i = 0; i < sensormlids.size(); i++) {
			if (null == StringUtil
					.checkStringIsNotNULLAndEmptyMethod(sensormlids.get(i))) {
				throw new ServiceException("参数sensormlids中包含了空元素，请核实");
			}
			String sensormlid = sensormlids.get(i).trim();
			sensormlid = StringUtil.AppendPacakgeStr(sensormlid);
			boolean bol = OperateSensorUtil
					.CheckSensorMLExistMethod(sensormlid).getIsExist();
			if (bol) {
				String sensormlcontent = OperateSensorUtil
						.ReadSensorML(sensormlid);
				if (resultidType.trim().toLowerCase().equals("sensorml")) {
					sensormlid = StringUtil.DeletePackageStr(sensormlid);
				}
				maps.put(sensormlid.trim(), FormatXmlUtil
						.formatXml(sensormlcontent));
			}
		}
		return maps;
	}

	public Map<String, String> GetUpdateSesnorMLAndSensorIdForUpdateMethod(
			String username, String password, List<String> sensorids,
			List<String> stamps, String resultidType) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (sensorids == null || sensorids.size() == 0) {
			throw new ServiceException("参数sensorids输入错误，请核实!");
		}
		if (stamps == null || stamps.size() == 0) {
			throw new ServiceException("参数stamps输入错误，请核实!");
		}
		if (resultidType == null || resultidType.trim().equals("")
				|| !resultidType.trim().toLowerCase().equals("ebrim")
				|| !resultidType.trim().toLowerCase().equals("sensorml")) {
			throw new ServiceException("参数resultidType输入错误，请核实!");
		}
		GetSensorMLNeedToUpdateService gsmtus = new GetSensorMLNeedToUpdateService();
		List<String> sensormlids = new ArrayList<String>();
		try {
			sensormlids = gsmtus.GetSensorMLNeedToUpdateMethod(username,
					password, sensorids, stamps, "ebrim");
			return GetUpdateSesnorMLAndSensorIdForUpdateByIdsMethod(username,
					password, sensormlids, resultidType);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException("获取所有的需更新传感器ID出现问题，详情请见["
					+ e.getLocalizedMessage() + "]");
		}
	}
}
