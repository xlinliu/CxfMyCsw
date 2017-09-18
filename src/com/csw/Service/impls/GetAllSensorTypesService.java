package com.csw.Service.impls;

import java.util.List;
import com.csw.Service.interfaces.GetAllSensorTypesInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *��Ŀ����:CxfMyCsw ����������ȡ�����������е����ͼ��� ������:Administrator ����ʱ��: 2013-8-23 ����02:24:53
 */
public class GetAllSensorTypesService implements GetAllSensorTypesInterface {
	/**
	 * ���Գɹ�!
	 * 
	 * @param args
	 * @throws ServiceException
	 */
	public static void main(String[] args) throws ServiceException {
		GetAllSensorTypesInterface gsti = new GetAllSensorTypesService();
		for (String s : gsti.getAllSensorTypes("admin", "cswadmin")) {
			System.out.println(s);
		}
	}

	public List<String> getAllSensorTypes(String username, String password)
			throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		return OperateSensorUtil.GetAllSensorTypes();
	}

}
