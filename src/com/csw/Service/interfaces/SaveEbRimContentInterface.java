package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface SaveEbRimContentInterface {
	@WebMethod
	public boolean SaveEbRimContentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "ebrimcontent") String ebrimcontent)
			throws ServiceException;
}
