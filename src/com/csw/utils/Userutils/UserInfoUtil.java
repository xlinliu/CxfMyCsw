package com.csw.utils.Userutils;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.csw.SystemParams.SystemConfig;
import com.csw.beans.LoginUserBean;
import com.csw.exceptions.ServiceException;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.StringUtil;

/**
 *��Ŀ����:CxfMyCsw �������� �û�����Ȩ�޵Ŀ��ƣ�����ɾ�������£���ѯ�� ������:Administrator ����ʱ��: 2013-10-16
 * ����09:50:44
 */
public class UserInfoUtil {

	/**
	 * ��ȡ�����û�������Ϣ
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<LoginUserBean> GetAllUserLoginBeans() {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Criteria criteria = session.createCriteria(LoginUserBean.class);
		criteria.addOrder(Order.asc("level"));
		List<LoginUserBean> users = (List<LoginUserBean>) criteria.list();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		if (users.size() > 0) {
			return users;
		} else {
			return null;
		}
	}

	/**
	 * ɾ���û�
	 * 
	 * @param username
	 * @return
	 */
	public static boolean DeleteUserByUserName(String username) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Query query = session
				.createQuery("delete LoginUserBean where username='" + username
						+ "'");
		int result = query.executeUpdate();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��ʵ�û���Ϣ
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @return ���سɹ�Ϊ1��ʧ��Ϊ0,��ѯ�����쳣������2
	 */
	@SuppressWarnings("unchecked")
	public static int CheckUserInfo(String username, String password) {
		try {
			String sqlStr = "from LoginUserBean where username='"
					+ username.trim() + "' and password='" + password.trim()
					+ "'";
			List<LoginUserBean> lubs = GetSessionUtil
					.GetSelectQueryInfo(sqlStr);
			int num = lubs.size();
			if (num > 0) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	/**
	 * ��ʵ�û���Ϣ
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static int CheckUserLogin(String username, String password)
			throws ServiceException {
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(username)
				|| null == StringUtil
						.checkStringIsNotNULLAndEmptyMethod(password)) {
			throw new ServiceException("�������û���������!");
		}
		int num;
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(SystemConfig
				.getUsername())
				|| null == StringUtil
						.checkStringIsNotNULLAndEmptyMethod(SystemConfig
								.getPassword())
				|| !SystemConfig.getUsername().equals(username)
				|| !SystemConfig.getPassword().equals(password)) {
			num = CheckUserInfo(username, password);
			if (num == 1) {
				SystemConfig.setUsername(username);
				SystemConfig.setPassword(password);
				return num;
			} else {
				throw new ServiceException("�û������������!");
			}

		} else {
			return 1;
		}
	}

	public static void main(String[] args) {
		System.out.println(UserLoginMethod("admin", "cswadmin").getAddress());
	}

	/**
	 * �û���¼����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return �����û���Ϣ
	 */
	public static LoginUserBean UserLoginMethod(String username, String password) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Criteria criteria = session.createCriteria(LoginUserBean.class);
		criteria.add(Restrictions.eq("username", username.trim()));
		criteria.add(Restrictions.eq("password", password.trim()));

		if (criteria.list().size() > 0) {
			LoginUserBean lub = (LoginUserBean) criteria.list().get(0);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return lub;
		} else {
			GetSessionUtil.CloseSessionInstance(session);
			return null;
		}
	}

	/**
	 * �����û���������Ӧ�ļ�����Ϣ
	 * 
	 * @param username
	 *            ��Ҫ��ѯ���û�����
	 * @return -1 �����ڸ��û�����0 ��ͨ�οͣ�1 ��������Ա��2 �����ṩ��
	 */
	@SuppressWarnings("unchecked")
	public static int GetUserLevel(String username) {
		if (username != null) {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			Query query = session
					.createQuery("select level from LoginUserBean where username='"
							+ username.trim() + "'");
			List<Integer> listss = (List<Integer>) query.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (listss != null) {
				return listss.get(0);
			} else {
				return -1;
			}
		} else {
			// û���û���
			return -1;
		}
	}

	/**
	 * �����û���Ϣ
	 * 
	 * @param username
	 * @param password
	 * @param address
	 * @param telephone
	 * @return
	 */
	public static boolean UpdateUserInfo(String username, String password,
			String address, String telephone) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Query query = session
				.createQuery("update  LoginUserBean set password= '" + password
						+ "',telephone= '" + telephone + "', address = '"
						+ address + "' where username ='" + username + "'");
		int result = query.executeUpdate();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �����û���Ϣ
	 * 
	 * @param sqlStr
	 * @return
	 */
	public static boolean UpdateUserInfo(String sqlStr) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		Query query = session.createQuery(sqlStr);
		int result = query.executeUpdate();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �����жϸ��û����Ƿ����
	 * 
	 * @param username
	 *            ��Ҫ�жϵ��û���
	 * @return true ���ã�false ������
	 */
	@SuppressWarnings("unchecked")
	public static boolean CheckUserNameIsExist(String username) {
		Boolean bol = false;
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		List<LoginUserBean> lists = (List<LoginUserBean>) session.createQuery(
				"from LoginUserBean where username ='" + username.trim() + "'")
				.list();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		if (lists == null || lists.size() <= 0) {
			bol = true;
		}
		return bol;
	}

	/**
	 * ����ע����û��ĺ���
	 * 
	 * @param lub
	 *            ��Ҫ������û�ע�����ϢLoginUserBean
	 */
	public static void SaveUser(LoginUserBean lub) {
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		session.beginTransaction().begin();
		session.save(lub);
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
	}

	/**
	 * ��ȡ�û���Ȩ�޼���,���userName��password��ǰ������ո��Ҳ�����˴���
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @return ��������ڣ�������Ȩ��Ϊ0�������쳣Ȩ��Ϊ0�������������أ�һ�㷵�أ�0��1��2
	 */
	@SuppressWarnings("unchecked")
	public static int GetLevelOfUser(String username, String password) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			session.beginTransaction().begin();
			List<Integer> lists = (List<Integer>) session.createQuery(
					"select level from LoginUserBean where username='"
							+ username.trim() + "' and password ='"
							+ password.trim() + "'").list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (lists != null && lists.size() > 0) {
				return lists.get(0);
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
