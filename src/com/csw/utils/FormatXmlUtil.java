package com.csw.utils;

import java.io.StringWriter;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class FormatXmlUtil {

	/**
	 * 格式化xml文档的内容
	 */
	public static String formatXml(String str) {
		try {
			Document document = null;
			document = DocumentHelper.parseText(str);
			// 格式化输出格式
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			StringWriter writer = new StringWriter();
			XMLWriter xmlWriter = new XMLWriter(writer, format);
			xmlWriter.write(document);
			xmlWriter.close();
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return str;
		}
	}
}
