package com.event.commonutils;

import java.util.ArrayList;
import java.util.List;

import com.event.customTypes.EventBasicInfo;

/**
 * 保存全部的事件信息
 * 
 * @author yxliang
 * 
 */
public class EventInfoConfig {
	/**
	 * 所有的全部信息
	 */
	private static List<EventBasicInfo> ebis = new ArrayList<EventBasicInfo>();
	/**
	 * 所有事件标识符和事件的阶段
	 */
	private static List<String> docnames = new ArrayList<String>();

	public static List<EventBasicInfo> getEbis() {
		return ebis;
	}

	public static void setEbis(List<EventBasicInfo> ebis) {
		EventInfoConfig.ebis = ebis;
	}

	public static List<String> getDocnames() {
		return docnames;
	}

	public static void setDocnames(List<String> docnames) {
		EventInfoConfig.docnames = docnames;
	}
}
