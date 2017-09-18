package com.model.commonutil;

import java.util.ArrayList;
import java.util.List;

import com.model.customTypes.ModelBasicInfo;

/**
 * ����洢��������ϵͳ�г��õĲ�ѯ��Model��������
 * 
 * @author yxliang
 * 
 */
public class ModelInfoConfig {
	private static List<ModelBasicInfo> mbInfos = new ArrayList<ModelBasicInfo>();// �����ĵ��е���Ϣ
	private static List<String> docnames = new ArrayList<String>();// �������е��ĵ�������

	/**
	 * ��ȡ �����ĵ��е���Ϣ
	 * 
	 * @return
	 */
	public static List<ModelBasicInfo> getMbInfos() {
		return mbInfos;
	}

	/**
	 * ��ȡ�����ĵ����������
	 * 
	 * @return
	 */
	public static List<String> getDocnames() {
		return docnames;
	}

}
