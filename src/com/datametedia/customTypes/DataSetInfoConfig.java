package com.datametedia.customTypes;

import java.util.ArrayList;
import java.util.List;

public class DataSetInfoConfig {
	private static List<String> docNameList = new ArrayList<String>();
	private static List<MetadataInfo> mgis = new ArrayList<MetadataInfo>();

	public static List<String> getDocNameList() {
		return docNameList;
	}
	public static List<MetadataInfo> getMgis() {
		return mgis;
	}
}
