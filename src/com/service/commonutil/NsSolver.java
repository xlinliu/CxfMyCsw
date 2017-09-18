package com.service.commonutil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;

public class NsSolver implements NamespaceContext {

	private Map<String, String> prefix2uri;
	private Map<String, String> uri2prefix;

	public NsSolver() {
		if (prefix2uri == null) {
			prefix2uri = Collections
					.synchronizedMap(new HashMap<String, String>());
		}
		if (uri2prefix == null) {
			uri2prefix = Collections
					.synchronizedMap(new HashMap<String, String>());
		}
	}

	public NsSolver(Map<String, String> prefix2uri,
			Map<String, String> uri2prefix) {
		this.prefix2uri = Collections.synchronizedMap(prefix2uri);
		this.uri2prefix = Collections.synchronizedMap(uri2prefix);
	}

	public String getNamespaceURI(String prefix) {
		return prefix2uri.get(prefix);
	}

	public String getPrefix(String namespaceURI) {
		return uri2prefix.get(namespaceURI);
	}

	@SuppressWarnings("unchecked")
	public Iterator getPrefixes(String namespaceURI) {
		return prefix2uri.keySet().iterator();
	}

}