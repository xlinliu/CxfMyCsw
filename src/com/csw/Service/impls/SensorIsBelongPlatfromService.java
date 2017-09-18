package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.Service.interfaces.SensorIsBelongPlatfromInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorPlatformPair;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-8-23 ����02:39:51
 */
public class SensorIsBelongPlatfromService implements
		SensorIsBelongPlatfromInterface {
	public static void main(String[] args) throws ServiceException {
		SensorIsBelongPlatfromService sisp = new SensorIsBelongPlatfromService();
		List<SensorPlatformPair> spfs = new ArrayList<SensorPlatformPair>();
		SensorPlatformPair spf = new SensorPlatformPair();
		spf.setSensor("urn:ogc:feature:insitusensor:platform:CarAXA568");
		spf.setPlatform("urn:ogc:feature:insitusensor:platform:CarAXA568");
		spfs.add(spf);
		SensorPlatformPair spf1 = new SensorPlatformPair();
		spf1.setSensor("urn:ogc:feature:insitesensor:CarAXA568-GPS");
		spf1.setPlatform("urn:ogc:feature:insitusensor:platform:CarAXA568");
		spfs.add(spf1);
		List<SensorPlatformPair> results = sisp.IsSensorsBelongToPlatforms(
				"admin", "cswadmin", spfs);
		for (SensorPlatformPair sp : results) {
			System.out.println("----------------");
			System.out.println(sp.getPlatform());
			System.out.println(sp.getSensor());
			System.out.println(sp.getBol());
		}
	}

	/**
	 * �ж�ĳһ�������Ƿ�����ĳһƽ̨
	 */
	public boolean IsSensorBelongToPlatform(String username, String password,
			String sensor, String platform) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetSensorIsBelongPlatform(sensor, platform);
	}

	/**
	 * ��ȡ�������Ƿ��봫���������� ���Գɹ�
	 */
	public List<SensorPlatformPair> IsSensorsBelongToPlatforms(String username,
			String password, List<SensorPlatformPair> spfs)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorPlatformPair> spsList = new ArrayList<SensorPlatformPair>();
		for (SensorPlatformPair sp : spfs) {
			SensorPlatformPair spf = new SensorPlatformPair();
			spf.setSensor(sp.getSensor());
			spf.setPlatform(sp.getPlatform());
			spf.setBol(OperateSensorUtil.GetSensorIsBelongPlatform(sp
					.getSensor(), sp.getPlatform()));
			spsList.add(spf);
		}
		return spsList;
	}
}
