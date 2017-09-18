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
 * �����¼�ģ���ĵ�����Ϣ
 * 
 * @author yxliang
 * 
 */
public class SaveEventOperation {
	/**
	 * ���洫�����¼��ĵ���Ϣ
	 * 
	 * @param mec
	 *            ��Ҫ������¼��ĵ�����Ϣ
	 * @param userid
	 *            �û��ı��
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
			throw new NullZeroException("����mec����Ϊ��!");
		}
		// �¼�ģ�͵���������
		EventSelfDescription esd = mec.getEsd();
		// �¼�����������Ϣ�����������׶ε�������
		EventMLDescriptionInfo emdi = mec.getEmdi();
		// �¼�������Ա������
		AdministrationInfo ai = mec.getAi();
		// ��ȡ�¼�����������Ϣ
		String result = EventOperationUtil.getEventDescriptionType(emdi);
		// �¼��ı��
		String eventid = esd.getEif().getEventid();
		// �ж��¼�ģ�������Ƿ���Ҫע��,������¼�ģ�Ͳ������ڣ���ȫ���洢������ֻ�Ǵ洢���Ӧ�Ĳ���
		if (EventOperationUtil.CheckSaveEventMLFun(mec).equals("0")) {
			System.out.println("here");
			bol = saveEventSelfDescription(esd, userid, result);
			bol = saveEventMLDescription(emdi, eventid);
			bol = saveEventMLAdminInfo(ai, eventid);
		} else {
			// ֻ�洢�¼�ģ���еĵ��¼�����ģ��
			bol = saveEventMLDescription(emdi, eventid);
		}

		return bol;
	}

	/**
	 * ����ʱ��
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
	 * �����¼�ģ���ĵ�������Ϣ
	 * 
	 * @param emdi
	 *            �¼�ģ��������Ϣ
	 * 
	 * @return
	 * @throws DBObjectSaveException
	 * @throws DBObjectQueryException
	 */
	public static Boolean saveEventMLDescription(EventMLDescriptionInfo emdi,
			String eventid) throws DBObjectSaveException,
			DBObjectQueryException {
		if (emdi.getPrecationInfo() != null) {
			// ����precaution��Ϣ�Ĵ���
			saveEventMLDesc_Precation(emdi.getPrecationInfo(), eventid);
		}
		if (emdi.getRecoveryInfo() != null) {
			// ����recovery��Ϣ�Ĵ���
			saveEventMLDesc_Recovery(emdi.getRecoveryInfo(), eventid);
		}
		if (emdi.getResponseInfo() != null) {
			// ����response��Ϣ�Ĵ���
			saveEventMLDesc_Response(emdi.getResponseInfo(), eventid);
		}
		if (emdi.getPreparationInfo() != null) {
			// ����preparation��Ϣ�Ĵ���
			saveEventMLDesc_Preparation(emdi.getPreparationInfo(), eventid);
		}
		return true;
	}

	/**
	 * ����preparation��Ϣ
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
	 * ����response��Ϣ
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
	 * ����recovery��Ϣ
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
	 * ����precaution��Ϣ
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
	 * ��EventSelfDescription�е���Ϣ���浽���ݿ���
	 * 
	 * @param ei
	 * @param userid
	 *            ������û����ϴ�ʱ��
	 * @param eventPhases
	 *            �¼������к��е��¼��׶�
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
		// ����ʱ�������Ϣ
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
	 * ���沢���ش洢���¼�ʱ����Ϣ����ı��
	 * 
	 * @param est
	 *            ���ĵ��н�����õ����¼�ʱ����Ϣ
	 * @throws DBObjectSaveException
	 *             �׳����ݿ�洢�쳣��Ϣ
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
	 * ���沢���ش洢���¼�������Ϣ����
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
