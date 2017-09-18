package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.Service.interfaces.GetAllPlatfromTypesInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.PlatformandSensors;

/**
 *��Ŀ����:CxfMyCsw ����������ȡ���е�ƽ̨������ ������:Administrator ����ʱ��: 2013-8-23 ����02:17:34
 */
public class GetAllPlatfromTypesService implements GetAllPlatfromTypesInterface {
	public static void main(String[] args) throws ServiceException {
		GetAllPlatfromTypesInterface gpt = new GetAllPlatfromTypesService();
		for (String s : gpt.getAllPlatfromType("admin", "cswadmin")) {
			System.out.println(s);
		}
	}

	public List<String> getAllPlatfromType(String username, String password)
			throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		List<PlatformandSensors> plants = OperateSensorUtil
				.GetPlatFormandSensorsMethod(username, password, "all");
		for (PlatformandSensors ps : plants) {
			if (!results.contains(ps.getPlatform().getSensortype())) {
				results.add(ps.getPlatform().getSensortype());
			}
		}
		return results;
	}
}
