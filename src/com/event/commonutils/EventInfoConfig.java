package com.event.commonutils;

import java.util.ArrayList;
import java.util.List;

import com.event.customTypes.EventBasicInfo;

/**
 * ����ȫ�����¼���Ϣ
 * 
 * @author yxliang
 * 
 */
public class EventInfoConfig {
	/**
	 * ���е�ȫ����Ϣ
	 */
	private static List<EventBasicInfo> ebis = new ArrayList<EventBasicInfo>();
	/**
	 * �����¼���ʶ�����¼��Ľ׶�
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
