package com.event.EventOperations;

import java.util.ArrayList;
import java.util.List;

import com.csw.exceptions.DBObjectQueryException;
import com.csw.exceptions.DBObjectSaveException;
import com.csw.exceptions.DBObjectUpdateException;
import com.csw.exceptions.EventExistsException;
import com.csw.exceptions.EventNotExistsException;
import com.csw.exceptions.EventUpdatePhasesNotExistException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.PrecationExistException;
import com.csw.exceptions.PreparationExistException;
import com.csw.exceptions.RecoveryExistException;
import com.csw.exceptions.ResponseExistException;
import com.csw.exceptions.DBObjectDeleteException;
import com.event.InnerEntities.EventMLClass;

/**
 * �����¼�ע������ݣ����������֣�һ����������ĸ��£��ڶ������Ǿֲ������׶εĸ���
 * 
 * @author yxliang
 * 
 */
public class UpdateEventOperation {
	/**
	 * �����¼�ģ���ĵ���Ϣ,������Ҫע����ǣ����µ��ĵ��������ض����¼��׶�
	 * 
	 * @param emc
	 * @param userid
	 * @return
	 * @throws DBObjectDeleteException
	 * @throws DBObjectQueryException
	 * @throws DBObjectUpdateException
	 * @throws NullZeroException
	 * @throws DBObjectSaveException
	 * @throws EventExistsException
	 * @throws PrecationExistException
	 * @throws PreparationExistException
	 * @throws ResponseExistException
	 * @throws RecoveryExistException
	 * @throws EventNotExistsException
	 * @throws EventUpdatePhasesNotExistException
	 */
	public static Boolean UpdateEventMLClass(EventMLClass emc, String userid)
			throws DBObjectDeleteException, DBObjectQueryException,
			DBObjectUpdateException, NullZeroException, DBObjectSaveException,
			EventExistsException, PrecationExistException,
			PreparationExistException, ResponseExistException,
			RecoveryExistException, EventNotExistsException,
			EventUpdatePhasesNotExistException {
		String eventid = emc.getEsd().getEif().getEventid();
		// ��ѯ�Ƿ������ص��¼�
		if (!QueryEventOperation.EventIsExist(eventid)) {
			throw new EventNotExistsException("���Ϊ" + eventid + "���¼�������!");
		}
		// �жϸ��¼����ں���״̬
		List<String> results = getUpdatePhases(
				getEventPhaseOfEventMLClass(emc), QueryEventOperation
						.getEventPhases(eventid));
		if (results != null && results.size() != 0) {
			for (String str : results) {
				deleteEventPhase(eventid, str);
			}
		}
		SaveEventOperation.saveEventMLDescription(emc.getEmdi(), eventid);
		return true;
	}

	/**
	 * ɾ��ָ���¼���ָ���Ľ׶ε���Ϣ
	 * 
	 * @param eventid
	 *            ��ʾָ�����¼��ı�ʶ��
	 * @param phasecode
	 *            �¼��׶εı�ʶ�� 1:precaution,2��preparation,3��response 4:recovery
	 * @throws DBObjectDeleteException
	 */
	public static void deleteEventPhase(String eventid, String phasecode)
			throws DBObjectDeleteException {
		if (phasecode.equals("1")) {
			DeleteEventOperation.deleteEventPrecationInfo(eventid);
		} else if (phasecode.equals("2")) {
			DeleteEventOperation.deleteEventPreparationInfo(eventid);
		} else if (phasecode.equals("3")) {
			DeleteEventOperation.deleteEventDenagedRoadInfo(eventid);
			DeleteEventOperation.deleteEventResponseInfo(eventid);
		} else if (phasecode.equals("4")) {
			DeleteEventOperation.deleteEventRecoveryInfo(eventid);
		} else {
			// �����κ�����
		}
	}

	/**
	 * ��ȡ��������������¼��׶Σ��������������ֻ���Ѿ�ע��Ľ׶β��ܸ��£������ڵĲ��ܸ���
	 * 
	 * @param updatephases
	 *            �ڸ��µ��¼��ĵ��д��ڵ��¼��Ľ׶�
	 * @param savedphases
	 *            �ڱ�������ݿ����Ѿ����ڵ��¼��׶�
	 * @return �����ڱ�����¼���Ϣ�͸��µ��¼������ж����ڵ��¼��Ľ׶εĵı��룬i+""
	 * @throws EventUpdatePhasesNotExistException
	 */
	public static List<String> getUpdatePhases(String updatephases,
			String savedphases) throws EventUpdatePhasesNotExistException {
		List<String> results = new ArrayList<String>();
		char updatechar;
		char savechar;
		for (int i = 0; i < 4; i++) {
			updatechar = updatephases.charAt(i);
			savechar = updatephases.charAt(i);
			if (savechar == '1' && updatechar == '1') {
				// ����ͬʱ����
				results.add(i + 1 + "");
			} else if (savechar == '0' && updatechar == '1') {
				throw new EventUpdatePhasesNotExistException(
						"���µ��ĵ����¼��Ľ׶ε��¼������в�����!");
			}
		}
		return results;
	}

	/**
	 * �ж��¼�ģ�������ĵ����������¼���Ӧ���¼��׶���Ϣ
	 * 
	 * @param emc
	 *            ��Ҫ�����¼�ģ�Ͷ���
	 * @return �����¼��׶���Ϣ
	 */
	public static String getEventPhaseOfEventMLClass(EventMLClass emc) {
		String result = "";
		if (null != emc.getEmdi().getPrecationInfo()) {
			result += "1";
		} else {
			result += "0";
		}
		if (null != emc.getEmdi().getPreparationInfo()) {
			result += "1";
		} else {
			result += "0";
		}
		if (null != emc.getEmdi().getResponseInfo()) {
			result += "1";
		} else {
			result += "0";
		}
		if (null != emc.getEmdi().getRecoveryInfo()) {
			result += "1";
		} else {
			result += "0";
		}
		return result;
	}
}
