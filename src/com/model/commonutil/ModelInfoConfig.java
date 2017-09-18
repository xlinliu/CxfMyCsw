package com.model.commonutil;

import java.util.ArrayList;
import java.util.List;

import com.model.customTypes.ModelBasicInfo;

/**
 * 此类存储的是整个系统中常用的查询的Model的属性项
 * 
 * @author yxliang
 * 
 */
public class ModelInfoConfig {
	private static List<ModelBasicInfo> mbInfos = new ArrayList<ModelBasicInfo>();// 整个文档中的信息
	private static List<String> docnames = new ArrayList<String>();// 保存所有的文档的名称

	/**
	 * 获取 整个文档中的信息
	 * 
	 * @return
	 */
	public static List<ModelBasicInfo> getMbInfos() {
		return mbInfos;
	}

	/**
	 * 获取所有文档的填充名称
	 * 
	 * @return
	 */
	public static List<String> getDocnames() {
		return docnames;
	}

}
