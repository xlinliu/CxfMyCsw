package com.csw.Service.interfaces;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.MapAdapter;

@WebService
public interface QuerySensorsByConditionsInterface {
	/**
	 * ���ݲ�ѯ������ѯ��ȡ���еĴ�����
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param all
	 *            �Ƿ��ѯ�����û������Ĵ�������Ϣ
	 * @param allownull
	 *            �Ƿ����е���ѯ���ֶ�Ϊ��ʱ����Ϊ�ǿ��Է��صĴ������б�
	 * 
	 * @param conditions
	 *            ��ѯ������
	 * @return �����������������Ĵ�����������
	 */
	@WebMethod
	public List<String> QuerySensorsByConditionsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "condition") @XmlJavaTypeAdapter(MapAdapter.class) Map<String, String> conditions,
			@WebParam(name = "all") boolean all,
			@WebParam(name = "allownull") boolean allownull)
			throws ServiceException;

}
