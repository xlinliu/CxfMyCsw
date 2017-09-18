package com.csw.utils.operatesensororbitandbbox;

import java.util.List;
import org.hibernate.Session;
import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.StandSatSensorPlatformPair;
import com.csw.utils.GetSessionUtil;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-18 下午03:15:11
 */
public class OperateSingleSatStandardNamePlatform {
	/**
	 * 保存
	 * 
	 * @param sspp
	 * @return
	 */
	public static Boolean SaveStandardNameAndPlatform(
			StandSatSensorPlatformPair sspp) throws ServiceException {
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			session.save(sspp);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static Boolean IsExist(String sensorname, String satplat)
			throws ServiceException {
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List list = session.createQuery(
					"from StandSatSensorPlatformPair where platform='"
							+ satplat + "' and sensorname ='" + sensorname
							+ "'").list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (list != null && list.size() != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 保存
	 * 
	 * @param sspp
	 * @return
	 * @throws ServiceException
	 */
	public static Boolean SaveStandardNameAndPlatform(
			List<StandSatSensorPlatformPair> sspp) throws ServiceException {
		try {
			if (sspp != null) {
				for (StandSatSensorPlatformPair ssp : sspp) {
					Session session = GetSessionUtil
							.GetSessionInstance(GetSessionUtil.SENSORTYPE);
					session.beginTransaction().begin();
					session.save(ssp);
					session.beginTransaction().commit();
					GetSessionUtil.CloseSessionInstance(session);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 更新
	 * 
	 * @param sspp
	 * @return
	 */
	public static Boolean UpdateStandardNameAndPlatform(
			StandSatSensorPlatformPair sspp) throws ServiceException {
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			session.update(sspp);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 更新
	 * 
	 * @param sspp
	 * @return
	 */
	public static Boolean UpdateStandardNameAndPlatforms(
			List<StandSatSensorPlatformPair> sspps) throws ServiceException {
		try {
			if (sspps != null) {
				for (StandSatSensorPlatformPair sspp : sspps) {
					Session session = GetSessionUtil
							.GetSessionInstance(GetSessionUtil.SENSORTYPE);
					session.beginTransaction().begin();
					session.update(sspp);
					session.beginTransaction().commit();
					GetSessionUtil.CloseSessionInstance(session);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 删除
	 * 
	 * @param sspp
	 * @return
	 */
	public static Boolean DeleteStandardNameAndPlatform(
			StandSatSensorPlatformPair sspp) throws ServiceException {
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			session.delete(sspp);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static List<StandSatSensorPlatformPair> ReadStandardNameAndPlatforms()
			throws ServiceException {
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List<StandSatSensorPlatformPair> ssppList = session.createQuery(
					"from StandSatSensorPlatformPair").list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return ssppList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
}
