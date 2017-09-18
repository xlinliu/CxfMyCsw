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
public interface GetAllSensorMLDocumentByIdsServiceInterface {
	/**
	 * ����id�б������е�sensorml���ĵ�������
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param ids
	 *            �û���Ҫ��ȡ�����е�sensorml��id��ֵ����
	 * @param contenttype
	 *            ���ص����ݸ�ʽ ebrim��ʽ����sensorml��ʽ������
	 * @param type
	 *            �û��Ƿ���Ҫ��ȡ���е��û����ĵ���ֵΪtrue����������ĵ���ֵΪfalse��
	 * @return �������з���������sennsorml���ĵ�����Ϣ
	 * @throws �����쳣
	 *             �������쳣��Ϣ
	 */
	@WebMethod
	public @XmlJavaTypeAdapter(MapAdapter.class)
	Map<String, String> GetAllSensorMLDocumentByIdsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "ids") List<String> ids,
			@WebParam(name = "contenttype") String contenttype,
			@WebParam(name = "type") boolean type) throws ServiceException;
}
