package com.event.EventOperations.utils;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.csw.exceptions.DBObjectQueryException;
import com.csw.exceptions.DBObjectSaveException;
import com.csw.exceptions.EventExistsException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.PrecationExistException;
import com.csw.exceptions.PreparationExistException;
import com.csw.exceptions.RecoveryExistException;
import com.csw.exceptions.ResponseExistException;
import com.csw.utils.GetSessionUtil;
import com.event.EventOperations.QueryEventOperation;
import com.event.InnerEntities.EventMLClass;
import com.event.InnerEntities.EventMLDescriptionInfo;
import com.event.JavaBeans.EventIdentificationJBean;

public class EventOperationUtil {
	/**
	 * 判断是否需要保存事件模型
	 * 
	 * @param eml
	 *            事件模型Java对象
	 * @return
	 * @throws EventExistsException
	 * @throws PrecationExistException
	 * @throws PreparationExistException
	 * @throws ResponseExistException
	 * @throws RecoveryExistException
	 * @throws DBObjectSaveException
	 * @throws DBObjectQueryException
	 */
	public static String CheckSaveEventMLFun(EventMLClass eml)
			throws PrecationExistException, PreparationExistException,
			ResponseExistException, RecoveryExistException,
			DBObjectQueryException {
		String result = "0";
		String eventid = eml.getEsd().getEif().getEventid();
		// 是否已经注册了该事件（这是不对的）
		EventIdentificationJBean emc = QueryEventOperation
				.getEventIdentificationById(eventid);
		if (emc != null) {
			result = "1";
		} else {
			return "0";
		}
		// 获取已注册的事件描述阶段
		String phases = emc.getEventPhases();
		if (eml.getEmdi().getPrecationInfo() != null) {
			if (phases.charAt(0) == '1') {
				throw new PrecationExistException("编号为:" + eventid
						+ "的事件已经存在precation描述信息");
			}
		}
		if (eml.getEmdi().getPreparationInfo() != null) {
			if (phases.charAt(1) == '1') {
				throw new PreparationExistException("编号为:" + eventid
						+ "的事件已经存在preparation描述信息 ");
			}
		}
		if (eml.getEmdi().getResponseInfo() != null) {
			if (phases.charAt(2) == '1') {
				throw new ResponseExistException("编号为:" + eventid
						+ "的事件已经存在response描述信息");
			}
		}
		if (eml.getEmdi().getRecoveryInfo() != null) {
			if (phases.charAt(3) == '1') {
				throw new RecoveryExistException("编号为:" + eventid
						+ "的事件已经存在recovery描述信息");
			}
		}
		return result;
	}

	/**
	 * 保存对象至数据库中，并返回生成的主建值
	 * 
	 * @param obj
	 * @return
	 * @throws DBObjectSaveException
	 */
	public static Serializable SaveObjectToDB(Object obj)
			throws DBObjectSaveException {
		System.out.println("保存对象为:" + obj);
		Serializable resultObject;
		Session session = null;
		try {
			session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			resultObject = session.save(obj);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DBObjectSaveException("保存对象错误!");
		} finally {
			if (session != null && session.isOpen()) {
				GetSessionUtil.CloseSessionInstance(session);
			}
		}
		return resultObject;
	}

	/**
	 * 获取事件描述信息所描述内容类型，如precation，preparation，response或recovery等
	 * 
	 * @param emi
	 *            存储了各种事件描述信息的实体对象
	 * @return 返回具有相应描述信息的字符串列表
	 * @throws NullZeroException
	 */
	public static String getEventDescriptionType(EventMLDescriptionInfo emdi)
			throws NullZeroException {
		if (emdi == null) {
			throw new NullZeroException("参数emdi不能为空!");
		}
		String result = "";
		if (emdi.getPrecationInfo() == null) {
			result += "0";
		} else {
			result += "1";
		}
		if (emdi.getPreparationInfo() == null) {
			result += "0";
		} else {
			result += "1";
		}
		if (emdi.getResponseInfo() == null) {
			result += "0";
		} else {
			result += "1";
		}
		if (emdi.getRecoveryInfo() == null) {
			result += "0";
		} else {
			result += "1";
		}
		return result;
	}
}
