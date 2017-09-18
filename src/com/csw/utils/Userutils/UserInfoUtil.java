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
 *项目名称:CxfMyCsw 类描述： 用户管理权限的控制（包括删除，更新，查询） 创建人:Administrator 创建时间: 2013-10-16
 * 上午09:50:44
 */
public class UserInfoUtil {

	/**
	 * 获取所有用户基本信息
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
	 * 删除用户
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
	 * 核实用户信息
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 返回成功为1，失败为0,查询出现异常，返回2
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
	 * 核实用户信息
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
			throw new ServiceException("请输入用户名与密码!");
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
				throw new ServiceException("用户名或密码错误!");
			}

		} else {
			return 1;
		}
	}

	public static void main(String[] args) {
		System.out.println(UserLoginMethod("admin", "cswadmin").getAddress());
	}

	/**
	 * 用户登录方法
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return 返回用户信息
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
	 * 根据用户名返回相应的级别信息
	 * 
	 * @param username
	 *            需要查询的用户名称
	 * @return -1 不存在该用户名，0 普通游客，1 超级管理员，2 数据提供者
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
			// 没有用户名
			return -1;
		}
	}

	/**
	 * 更新用户信息
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
	 * 更新用户信息
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
	 * 用于判断该用户名是否可用
	 * 
	 * @param username
	 *            需要判断的用户名
	 * @return true 可用，false 不可用
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
	 * 保存注册的用户的函数
	 * 
	 * @param lub
	 *            需要保存的用户注册的信息LoginUserBean
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
	 * 获取用户的权限级别,针对userName和password中前后包含空格的也进行了处理
	 * 
	 * @param username
	 *            用户姓名
	 * @param password
	 *            用户密码
	 * @return 如果不存在，则是其权限为0，发生异常权限为0，其他正常返回，一般返回，0，1，2
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
