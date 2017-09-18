package com.model.commonutil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import com.model.customTypes.ModelKeyWords;

public class QueryModelKeywordsUtil implements Callable<List<ModelKeyWords>> {
	private List<String> docnames = new ArrayList<String>();
	private String propertyname;

	public QueryModelKeywordsUtil(List<String> docnames, String propertyname) {
		this.propertyname = propertyname;
		this.docnames = docnames;
	}

	@SuppressWarnings("unchecked")
	public List<ModelKeyWords> call() throws Exception {
		List<ModelKeyWords> result = new ArrayList<ModelKeyWords>();
		ModelKeyWords mkw;
		ModelXMLDBUtil mxb = ModelXMLDBUtil
				.getInstance();
		for (String docname : docnames) {
			mkw = new ModelKeyWords();
			List<String> resultStrings = mxb.queryDocumentOfModel(propertyname,
					docname);
			mkw.setModelid(docname);
			mkw.setKeywords(resultStrings);
			result.add(mkw);
		}
		return result;
	}
}
