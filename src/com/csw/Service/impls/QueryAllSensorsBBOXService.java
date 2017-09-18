package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.Service.interfaces.QueryAllSensorsBBOXInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorBBox;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-14 下午01:14:05
 */
public class QueryAllSensorsBBOXService implements QueryAllSensorsBBOXInterface {
	public List<SensorBBox> QuerySensorsBBOXMethod(String username,
			String password, List<String> sensors) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorBBox> sensorBBoxs = new ArrayList<SensorBBox>();
		if (sensors != null) {
			for (String sensorid : sensors) {
				SensorBBox sbBBox = new SensorBBox();
				sbBBox.setSensorid(sensorid);
				sbBBox.setBbox(OperateSensorUtil.GetSensorGeoInfo(sensorid));
				sensorBBoxs.add(sbBBox);
			}
		}
		return sensorBBoxs;
	}

	public static void main(String[] args) throws ServiceException {
		QueryAllSensorsBBOXService qabs = new QueryAllSensorsBBOXService();
		List<String> str = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			str
					.add("urn:liesmars:insitusensor:BaoxieSoilMoistureStation-LVDSC12-01");
			str
					.add("urn:liesmars:insitusensor:BaoxieSoilMoistureStation-LVDSC12-02");
			str
					.add("urn:liesmars:insitusensor:BaoxieSoilMoistureStation-LVDSC12-03");
			str
					.add("urn:liesmars:insitusensor:BaoxieSoilMoistureStation-LVDSC12-04");
			str
					.add("urn:liesmars:insitusensor:BaoxieSoilMoistureStation-LVDSC12-05");
			str
					.add("urn:liesmars:insitusensor:BaoxieSoilMoistureStation-LVDSC12-06");
			str
					.add("urn:liesmars:insitusensor:BaoxieSoilMoistureStation-LVDSC12-07");
			str
					.add("urn:liesmars:insitusensor:BaoxieSoilMoistureStation-LVDSC12-08");
			str
					.add("urn:liesmars:insitusensor:BaoxieSoilMoistureStation-LVDSC12-09");
			str
					.add("urn:liesmars:insitusensor:BaoxieSoilMoistureStation-LVDSC12-10");
			str
					.add("urn:liesmars:insitusensor:BaoxieSoilMoistureStation-LVDSC12-11");
			str
					.add("urn:liesmars:insitusensor:BaoxieSoilMoistureStation-LVDSC12-12");
		}
		java.util.Date date = new java.util.Date();
		qabs.QuerySensorsBBOXMethod("admin", "cswadmin", str);
		java.util.Date date2 = new java.util.Date();
		System.out.println(date2.getTime() - date.getTime() + "毫秒");
	}
}
