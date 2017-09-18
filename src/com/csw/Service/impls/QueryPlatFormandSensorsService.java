package com.csw.Service.impls;

import java.util.List;
import com.csw.Service.interfaces.QueryPlatFormandSensorsInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.PlatformandSensors;
import com.csw.utils.custometypes.SensorInfo;

/**
 *��Ŀ����:CxfMyCsw ����������ȡϵͳ��ȫ���Ĵ�������ƽ̨������صĴ��������б� ������:Administrator ����ʱ��: 2013-8-23
 * ����09:14:49
 */
public class QueryPlatFormandSensorsService implements
		QueryPlatFormandSensorsInterface {
	/**
	 * ���Գɹ�
	 * 
	 * @param args
	 * @throws ServiceException
	 */
	public static void main(String[] args) throws ServiceException {
		long pre = System.currentTimeMillis();
		QueryPlatFormandSensorsService qpfs = new QueryPlatFormandSensorsService();
		System.out.println("hee222  start");
		List<PlatformandSensors> pslists = qpfs.GetPlatFormandSensors("admin",
				"cswadmin", "all");
		for (PlatformandSensors ps : pslists) {
			System.out.println("------------------");
			System.out.println(ps.getPlatform().getSensor());
			System.out.println(ps.getPlatform().getSensortype());
			System.out.println(ps.getPlatform().getSensorsosurl());
			System.out.println(ps.getPlatform().getSensoroffering());
			System.out.println("      ------------");
			for (SensorInfo si : ps.getSensors()) {
				System.out.println(si.getSensor());
				System.out.println(si.getSensortype());
				System.out.println(si.getSensorsosurl());
				System.out.println(si.getSensoroffering());
			}
		}
		long now = System.currentTimeMillis();
		System.out.println(now - pre + "����");
	}

	public List<PlatformandSensors> GetPlatFormandSensors(String username,
			String password, String platfromtype) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetPlatFormandSensorsMethod(username,
				password, platfromtype);
	}
}
