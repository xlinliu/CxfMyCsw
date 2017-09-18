package com.csw.Service.impls;

import com.csw.Service.interfaces.DeleteSensorMLByIdServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class DeleteSensorMLByIdService implements
		DeleteSensorMLByIdServiceInterface {
	public static void main(String[] args) throws ServiceException {
		DeleteSensorMLByIdService dsms=new DeleteSensorMLByIdService();
		dsms.DeleteSensorMLByIdMethod("admin", "cswadmin", "urn:liesmars:insitusensor:BusTAIYUAN-A81757-BDS", "deleteall");
		dsms.DeleteSensorMLByIdMethod("admin", "cswadmin", "urn:liesmars:insitusensor:platform:BusTAIYUAN-A81757", "deleteall");
		System.out.println("over");
	}
	public int DeleteSensorMLByIdMethod(String username, String password,
			String sensorid, String deleteType) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
//		if (deleteType == null || deleteType.trim().equals("")
//				|| !deleteType.trim().toLowerCase().equals("deleteall")
//				|| !deleteType.trim().toLowerCase().equals("deletesensorml")) {
//			throw new ServiceException("����deleteType����ȷ!");
//		}
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("��������sensor��idֵ");
		} //
		// �ڶ�����,ת����sensorml��id������Ƿ����ָ����sensorml�ĵ�
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		// �����Ӧ��sensorML��id�Ƿ����
		boolean bol = OperateSensorUtil.CheckSensorMLExistMethod(username,
				sensorid);
		// ��������ɾ���ƶ���sensorML�ĵ�
		if (bol) {
			boolean nums = OperateSensorUtil.DeleteSensorML(username, password,
					sensorid);
			if (nums) {
				return 1;
			} else {
				throw new ServiceException("ɾ��ʧ�ܣ�����������⣬����ϵ�����!");
			}
		} else {
			// û�и��ĵ����򷵻��쳣
			throw new ServiceException("���ĵ������ڻ��߸��û�û��Ȩ���޸ĸ��ĵ�");
		}
	}
}
