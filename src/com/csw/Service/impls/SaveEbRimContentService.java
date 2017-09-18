package com.csw.Service.impls;

import javax.jws.WebService;

import com.csw.Service.interfaces.SaveEbRimContentInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.ParseEbRIMAndSaveInfo;
import com.csw.utils.Userutils.UserInfoUtil;

@WebService
public class SaveEbRimContentService implements SaveEbRimContentInterface {
	public boolean SaveEbRimContentMethod(String username, String password,
			String ebrimcontent) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		ParseEbRIMAndSaveInfo pe = new ParseEbRIMAndSaveInfo();
		boolean result = pe.ParseAndSaveXMLDocumentByContent(ebrimcontent,
				username, "11");
		return result;
	}

}
