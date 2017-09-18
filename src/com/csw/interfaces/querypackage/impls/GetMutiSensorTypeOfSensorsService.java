/**
 * 
 */
package com.csw.interfaces.querypackage.impls;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetMutiSensorTypeOfSensorsInterface;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-8-22 ����09:30:09
 */
public class GetMutiSensorTypeOfSensorsService implements
		GetMutiSensorTypeOfSensorsInterface {
	@SuppressWarnings("unchecked")
	public List<String> GetMutiSensorTypeOfSensors(String username,
			String password, String sensors) throws ServiceException {
		List<String> sensortypes = new ArrayList<String>();
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
		}
		// �����û����� ���������ݿ����Ƿ���������
		int result = UserInfoUtil.CheckUserInfo(username, password);
		if (result == 1) {
			// ��ȡ�������ĵĴ�����������
			// ���������ӵ�LocalizedString���ݱ���
			// ����idֵ�����Ӧ��valueֵ
			// ���ظ�ֵ
			String[] tempsensors = sensors.trim().split(",");
			for (String sensorid : tempsensors) {
				Session session = GetSessionUtil
						.GetSessionInstance(GetSessionUtil.SENSORTYPE);
				try {
					session.beginTransaction().begin();
					List<String> values = (List<String>) session
							.createQuery(
									"select values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::SensorType' and id like '"
											+ sensorid.trim() + "%" + "'")
							.list();
					session.beginTransaction().commit();
					GetSessionUtil.CloseSessionInstance(session);
					if (values == null || values.size() == 0) {
						new ServiceException("��������ʶ��Ϊ[" + sensorid
								+ "]��������Ϣ������!");
					}
					sensortypes.add(values.get(0).substring(0,
							values.get(0).length() - 1));

				} catch (Exception e) {
					GetSessionUtil.CloseSessionInstance(session);
					e.printStackTrace();
					throw new ServiceException("�������������⣬����ϵ�ͷ���лл!");
				}
			}
		}
		return sensortypes;
	}

}
