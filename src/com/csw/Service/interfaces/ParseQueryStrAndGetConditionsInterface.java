package com.csw.Service.interfaces;

import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.MapAdapter;

@WebService
public interface ParseQueryStrAndGetConditionsInterface {
	/**
	 * ��������ȡ���еĲ�ѯ����
	 * 
	 * @param queryStr
	 *            ��ѯ������
	 * @return
	 */
	@WebMethod
	public @XmlJavaTypeAdapter(MapAdapter.class)
	Map<String, String> ParseQueryStrAndConditionsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "queryStr") String queryStr)
			throws ServiceException;
}
