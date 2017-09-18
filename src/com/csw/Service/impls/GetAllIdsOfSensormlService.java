package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.csw.Service.interfaces.GetAllIdsOfSensormlServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class GetAllIdsOfSensormlService implements
		GetAllIdsOfSensormlServiceInterface {

	public List<String> GetAllIdsOfSensormlMethod(String username,
			String password, String type) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(type)) {
			throw new ServiceException("����type���ݴ���!");
		}
		// �ڶ������ȡ���ݿ�����е�ָ����username���洢��sensorml��idֵ�ü���
		List<String> lists = GetIdsOfUsers(username, password);
		// ���������ǽ��������
		if (lists != null && lists.size() > 0) {
			if (type.equals("sensorml")) {
				List<String> results = new ArrayList<String>();
				for (int i = 0; i < lists.size(); i++) {
					results.add(StringUtil.DeletePackageStr(lists.get(i)));
				}
				return results;
			}
			if (type.equals("ebrim")) {
				return lists;
			}
		} else {
			return null;
		}
		// �û�û���Լ����ĵ����򷵻�0
		return null;
	}

	/**
	 * ����ָ�����û���ӵ�е�ȫ�����ĵ���id,����֮ǰ��Ҫ�����û���������ļ���
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            �û�����
	 * @param type
	 *            ���ص�id�����ͣ����֣�sensorml��ebrim
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> GetIdsOfUsers(String username, String password) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Query query = session
				.createQuery("select id from MyebRIMSmlContents where ower='"
						+ username + "'");
		List<String> lists = (List<String>) query.list();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		return lists;
	}
}
