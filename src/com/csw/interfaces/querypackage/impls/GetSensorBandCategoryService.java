package com.csw.interfaces.querypackage.impls;

import java.util.List;
import org.hibernate.Session;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.GetSensorBandCategoryInterface;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class GetSensorBandCategoryService implements
		GetSensorBandCategoryInterface {

	@SuppressWarnings("unchecked")
	public String GetSensorBandCategoryMethod(String username, String password,
			String sensorid) throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
		}
		// �����û����� ���������ݿ����Ƿ���������
		int result = UserInfoUtil.CheckUserInfo(username, password);
		if (result == 1) {
			// ��ȡ�������ĵ�ȫ��
			// ���������ӵ�LocalizedString���ݱ���
			// ����idֵ�����Ӧ��valueֵ
			// ���ظ�ֵ
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			try {
				session.beginTransaction().begin();
				List<String> values = (List<String>) session
						.createQuery(
								"select values from Slot where name ='urn:ogc:def:slot:OGC-CSW-ebRIM-Sensor::measurementCapabilities:bandsCategory' and  id like '"
										+ sensorid.trim() + "%" + "'").list();
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstance(session);
				if (values == null || values.size() == 0) {
					throw new ServiceException("��������ʶ��Ϊ[" + sensorid
							+ "]�Ĳ������Ͳ�����!");
				}
				return values.get(0).substring(0, values.get(0).length() - 1);

			} catch (Exception e) {
				e.printStackTrace();
				GetSessionUtil.CloseSessionInstance(session);
				throw new ServiceException("�������������⣬����ϵ�ͷ���лл!");
			}

		} else {
			throw new ServiceException("�û������������!");
		}
	}

}
