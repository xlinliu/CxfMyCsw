package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.beans.LoginUserBean;
import com.csw.exceptions.ServiceException;

@WebService
public interface LoginUserInterface {
	@WebMethod
	public LoginUserBean LoginUserMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
