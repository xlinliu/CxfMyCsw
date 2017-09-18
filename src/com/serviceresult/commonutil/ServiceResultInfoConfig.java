package com.serviceresult.commonutil;

import java.util.ArrayList;
import java.util.List;
import com.serviceresult.customTypes.ServiceResultInfo;

public class ServiceResultInfoConfig {

	private static List<String> docnames = new ArrayList<String>();
	private static List<ServiceResultInfo> scis = new ArrayList<ServiceResultInfo>();

	public static List<ServiceResultInfo> getScis() {
		return scis;
	}

	public static void setScis(List<ServiceResultInfo> scis) {
		ServiceResultInfoConfig.scis = scis;
	}

	public static List<String> getDocnames() {
		return docnames;
	}

	public static void setDocnames(List<String> docnames) {
		ServiceResultInfoConfig.docnames = docnames;
	}

}
