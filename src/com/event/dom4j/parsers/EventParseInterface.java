package com.event.dom4j.parsers;

import java.io.File;

import org.dom4j.DocumentException;

import com.csw.exceptions.EventMLNotFormatException;
import com.csw.exceptions.NullZeroException;
import com.event.InnerEntities.EventMLClass;

public interface EventParseInterface {
	/**
	 * ½âÎöÎÄ¼þ
	 * 
	 * @param file
	 * @return
	 * @throws NullZeroException
	 * @throws DocumentException
	 * @throws EventMLNotFormatException
	 */
	public EventMLClass ParseEventML(File file) throws NullZeroException,
			DocumentException, EventMLNotFormatException;
}
