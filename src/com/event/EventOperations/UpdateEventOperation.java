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
 * 更新事件注册的内容，包括两部分，一部分是整体的更新，第二部分是局部各个阶段的更新
 * 
 * @author yxliang
 * 
 */
public class UpdateEventOperation {
	/**
	 * 更新事件模型文档信息,这里需要注意的是，更新的文档可能是特定的事件阶段
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
		// 查询是否存在相关的事件
		if (!QueryEventOperation.EventIsExist(eventid)) {
			throw new EventNotExistsException("编号为" + eventid + "的事件不存在!");
		}
		// 判断该事件处于何种状态
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
	 * 删除指定事件的指定的阶段的信息
	 * 
	 * @param eventid
	 *            表示指定的事件的标识符
	 * @param phasecode
	 *            事件阶段的标识符 1:precaution,2：preparation,3：response 4:recovery
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
			// 不做任何事情
		}
	}

	/**
	 * 获取满足更新条件的事件阶段，这里的条件就是只有已经注册的阶段才能更新，不存在的不能更新
	 * 
	 * @param updatephases
	 *            在更新的事件文档中存在的事件的阶段
	 * @param savedphases
	 *            在保存的数据库中已经存在的事件阶段
	 * @return 返回在保存的事件信息和更新的事件类型中都存在的事件的阶段的的编码，i+""
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
				// 两者同时存在
				results.add(i + 1 + "");
			} else if (savechar == '0' && updatechar == '1') {
				throw new EventUpdatePhasesNotExistException(
						"更新的文档的事件的阶段的事件内容中不存在!");
			}
		}
		return results;
	}

	/**
	 * 判断事件模型描述文档所描述的事件对应的事件阶段信息
	 * 
	 * @param emc
	 *            需要检测的事件模型对象
	 * @return 返回事件阶段信息
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
