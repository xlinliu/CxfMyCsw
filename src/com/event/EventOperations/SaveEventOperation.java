package com.event.EventOperations;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import com.csw.exceptions.DBObjectQueryException;
import com.csw.exceptions.DBObjectSaveException;
import com.csw.exceptions.EventExistsException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.PrecationExistException;
import com.csw.exceptions.PreparationExistException;
import com.csw.exceptions.RecoveryExistException;
import com.csw.exceptions.ResponseExistException;
import com.event.EventOperations.utils.EventOperationUtil;
import com.event.InnerEntities.AdministrationInfo;
import com.event.InnerEntities.EventClassification;
import com.event.InnerEntities.EventMLClass;
import com.event.InnerEntities.EventMLDescriptionInfo;
import com.event.InnerEntities.EventRecoveryInfo;
import com.event.InnerEntities.EventResponseInfo;
import com.event.InnerEntities.EventSelfDescription;
import com.event.InnerEntities.EventSpaceTime;
import com.event.InnerEntities.PrecationInfo;
import com.event.InnerEntities.PreparationInfo;
import com.event.JavaBeans.EventAdministrationInfoJBean;
import com.event.JavaBeans.EventClassificationJBean;
import com.event.JavaBeans.EventDenagedRoadJBean;
import com.event.JavaBeans.EventIdentificationJBean;
import com.event.JavaBeans.EventPrecationInfoJBean;
import com.event.JavaBeans.EventPreparationInfoJBean;
import com.event.JavaBeans.EventRecoveryInfoJBean;
import com.event.JavaBeans.EventResponseInfoJBean;
import com.event.JavaBeans.EventSpaceTimeJBean;

/**
 * 保存事件模型文档的信息
 * 
 * @author yxliang
 * 
 */
public class SaveEventOperation {
	/**
	 * 保存传感器事件文档信息
	 * 
	 * @param mec
	 *            需要保存的事件文档的信息
	 * @param userid
	 *            用户的编号
	 * @return
	 * @throws NullZeroException
	 * @throws DBObjectSaveException
	 * @throws EventExistsException
	 * @throws RecoveryExistException
	 * @throws ResponseExistException
	 * @throws PreparationExistException
	 * @throws PrecationExistException
	 * @throws DBObjectQueryException
	 */
	public static Boolean saveEventMLClass(EventMLClass mec, String userid)
			throws NullZeroException, DBObjectSaveException,
			EventExistsException, PrecationExistException,
			PreparationExistException, ResponseExistException,
			RecoveryExistException, DBObjectQueryException {
		Boolean bol = false;
		if (mec == null) {
			throw new NullZeroException("参数mec不能为空!");
		}
		// 事件模型的自我描述
		EventSelfDescription esd = mec.getEsd();
		// 事件的描述的信息（包括各个阶段的描述）
		EventMLDescriptionInfo emdi = mec.getEmdi();
		// 事件管理人员的描述
		AdministrationInfo ai = mec.getAi();
		// 获取事件描述类型信息
		String result = EventOperationUtil.getEventDescriptionType(emdi);
		// 事件的编号
		String eventid = esd.getEif().getEventid();
		// 判断事件模型类型是否需要注册,如果此事件模型并不存在，则全部存储，否则只是存储相对应的部分
		if (EventOperationUtil.CheckSaveEventMLFun(mec).equals("0")) {
			System.out.println("here");
			bol = saveEventSelfDescription(esd, userid, result);
			bol = saveEventMLDescription(emdi, eventid);
			bol = saveEventMLAdminInfo(ai, eventid);
		} else {
			// 只存储事件模型中的的事件描述模型
			bol = saveEventMLDescription(emdi, eventid);
		}

		return bol;
	}

	/**
	 * 保存时间
	 * 
	 * @param ai
	 * @return
	 * @throws DBObjectSaveException
	 */
	private static Boolean saveEventMLAdminInfo(AdministrationInfo ai,
			String eventid) throws DBObjectSaveException {
		EventAdministrationInfoJBean eaij = new EventAdministrationInfoJBean();
		eaij.setCrp(ai.getCrp());
		eaij.setSiInfo(ai.getSiInfo());
		eaij.setEventid(eventid);
		EventOperationUtil.SaveObjectToDB(eaij);
		return true;
	}

	/**
	 * 保存事件模型文档描述信息
	 * 
	 * @param emdi
	 *            事件模型描述信息
	 * 
	 * @return
	 * @throws DBObjectSaveException
	 * @throws DBObjectQueryException
	 */
	public static Boolean saveEventMLDescription(EventMLDescriptionInfo emdi,
			String eventid) throws DBObjectSaveException,
			DBObjectQueryException {
		if (emdi.getPrecationInfo() != null) {
			// 进行precaution信息的处理
			saveEventMLDesc_Precation(emdi.getPrecationInfo(), eventid);
		}
		if (emdi.getRecoveryInfo() != null) {
			// 进行recovery信息的处理
			saveEventMLDesc_Recovery(emdi.getRecoveryInfo(), eventid);
		}
		if (emdi.getResponseInfo() != null) {
			// 进行response信息的处理
			saveEventMLDesc_Response(emdi.getResponseInfo(), eventid);
		}
		if (emdi.getPreparationInfo() != null) {
			// 进行preparation信息的处理
			saveEventMLDesc_Preparation(emdi.getPreparationInfo(), eventid);
		}
		return true;
	}

	/**
	 * 保存preparation信息
	 * 
	 * @param preparationInfo
	 * @return
	 * @throws DBObjectSaveException
	 */
	private static Integer saveEventMLDesc_Preparation(
			PreparationInfo preparationInfo, String eventid)
			throws DBObjectSaveException {
		EventPreparationInfoJBean epiJBean = new EventPreparationInfoJBean();
		epiJBean.setAi(preparationInfo.getAi());
		epiJBean.setPi(preparationInfo.getPi());
		epiJBean.setpInfo(preparationInfo.getpInfo());
		epiJBean.setEventid(eventid);
		System.out.println(preparationInfo.getpInfo().getGroundData() + " : "
				+ preparationInfo.getpInfo().getSatelliteData()
				+ "............");
		return (Integer) EventOperationUtil.SaveObjectToDB(epiJBean);
	}

	/**
	 * 保存response信息
	 * 
	 * @param responseInfo
	 * @throws DBObjectSaveException
	 * @throws DBObjectQueryException
	 */
	private static Integer saveEventMLDesc_Response(
			EventResponseInfo responseInfo, String eventid)
			throws DBObjectSaveException, DBObjectQueryException {
		EventResponseInfoJBean erij = new EventResponseInfoJBean();
		erij.setEpr(responseInfo.getEpr());
		erij.setErop(responseInfo.getErop());
		if (responseInfo.getErop() != null) {

			if (responseInfo.getErop().getRodsDemagedRoads() != null) {
				Iterator<EventDenagedRoadJBean> t = responseInfo.getErop()
						.getRodsDemagedRoads().iterator();
				while (t.hasNext()) {
					EventDenagedRoadJBean edrjBean = t.next();
					edrjBean.setEventid(eventid);
					EventOperationUtil.SaveObjectToDB(edrjBean);
				}
			}
		}
		Set<EventDenagedRoadJBean> edrJBeans = QueryEventOperation
				.getEventDenagedRoadJBeans(eventid);
		responseInfo.getErop().setRodsDemagedRoads(edrJBeans);
		erij.setErsr(responseInfo.getErsr());
		erij.setErtp(responseInfo.getErtp());
		erij.setEventid(eventid);
		return (Integer) EventOperationUtil.SaveObjectToDB(erij);
	}

	/**
	 * 保存recovery信息
	 * 
	 * @param recoveryInfo
	 * @throws DBObjectSaveException
	 */
	private static Integer saveEventMLDesc_Recovery(
			EventRecoveryInfo recoveryInfo, String eventid)
			throws DBObjectSaveException {
		EventRecoveryInfoJBean erij = new EventRecoveryInfoJBean();
		erij.setEc(recoveryInfo.getEc());
		erij.setEel(recoveryInfo.getEel());
		erij.setOif(recoveryInfo.getOif());
		erij.setEventid(eventid);
		return (Integer) EventOperationUtil.SaveObjectToDB(erij);
	}

	/**
	 * 保存precaution信息
	 * 
	 * @param precationInfo
	 * @throws DBObjectSaveException
	 */
	private static Integer saveEventMLDesc_Precation(
			PrecationInfo precationInfo, String eventid)
			throws DBObjectSaveException {
		EventPrecationInfoJBean epij = new EventPrecationInfoJBean();
		epij.setGroundData(precationInfo.getGroundData());
		epij.setSatelliteData(precationInfo.getSatelliteData());
		epij.setUAVData(precationInfo.getUAVData());
		epij.setEventid(eventid);
		return (Integer) EventOperationUtil.SaveObjectToDB(epij);
	}

	/**
	 * 将EventSelfDescription中的信息保存到数据库中
	 * 
	 * @param ei
	 * @param userid
	 *            保存的用户的上传时间
	 * @param eventPhases
	 *            事件描述中含有的事件阶段
	 * @return
	 * @throws DBObjectSaveException
	 * @throws EventExistsException
	 * @throws DBObjectQueryException
	 */
	public static Boolean saveEventSelfDescription(EventSelfDescription esd,
			String userid, String eventPhases) throws DBObjectSaveException,
			EventExistsException, DBObjectQueryException {
		EventIdentificationJBean eijb = new EventIdentificationJBean();
		String eventid = esd.getEif().getEventid();
		eijb.setEventid(eventid);
		eijb.setEventname(esd.getEif().getEventname());
		eijb.setEventdesc(esd.getEif().getEventdesc());
		eijb.setEventPhases(eventPhases);
		eijb.setUserid(userid);
		eijb.setUploadtime(new Date());
		EventClassificationJBean ecjb = new EventClassificationJBean();
		// 保存时间分类信息
		ecjb = QueryEventOperation.getECByClaId(saveEventClassification(esd
				.getEcf(), eventid));
		eijb.setEcjb(ecjb);
		EventSpaceTimeJBean estj = new EventSpaceTimeJBean();
		estj = QueryEventOperation.getESTBySTId(saveEventSpaceTime(
				esd.getEst(), eventid));
		eijb.setEstj(estj);
		EventOperationUtil.SaveObjectToDB(eijb);
		return true;
	}

	/**
	 * 保存并返回存储的事件时空信息对象的编号
	 * 
	 * @param est
	 *            从文档中杰西获得到的事件时空信息
	 * @throws DBObjectSaveException
	 *             抛出数据库存储异常信息
	 */
	private static Integer saveEventSpaceTime(EventSpaceTime est, String eventid)
			throws DBObjectSaveException {
		EventSpaceTimeJBean estj = new EventSpaceTimeJBean();
		estj.setDa(est.getDa());
		estj.setLlp(est.getLlp());
		estj.setTp(est.getTp());
		estj.setType(est.getType());
		estj.setEventid(eventid);
		return (Integer) EventOperationUtil.SaveObjectToDB(estj);
	}

	/**
	 * 保存并返回存储的事件分类信息对象
	 * 
	 * @param ecf
	 * @return
	 * @throws DBObjectSaveException
	 */
	private static int saveEventClassification(EventClassification ecf,
			String eventid) throws DBObjectSaveException {
		EventClassificationJBean ecjb = new EventClassificationJBean();
		ecjb.setEventCatagory(ecf.getEventCatagory());
		ecjb.setEventCertainty(ecf.getEventCertainty());
		ecjb.setEventInheritance(ecf.getEventInheritance());
		ecjb.setEventPattern(ecf.getEventPattern());
		ecjb.setEventSeverity(ecf.getEventSeverity());
		ecjb.setEventUrgency(ecf.getEventUrgency());
		ecjb.setEventid(eventid);
		return (Integer) EventOperationUtil.SaveObjectToDB(ecjb);
	}
}
