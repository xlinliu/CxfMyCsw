package com.service.commonutil;

import java.util.ArrayList;
import java.util.List;

import com.service.customTypes.ServiceChainInfo;

public class ServiceChainInfoConfig {

	private static List<String> docnames = new ArrayList<String>();
	private static List<ServiceChainInfo> scis = new ArrayList<ServiceChainInfo>();

	public static List<ServiceChainInfo> getScis() {
		return scis;
	}

	public static void setScis(List<ServiceChainInfo> scis) {
		ServiceChainInfoConfig.scis = scis;
	}

	public static List<String> getDocnames() {
		return docnames;
	}

	public static void setDocnames(List<String> docnames) {
		ServiceChainInfoConfig.docnames = docnames;
	}

}
