/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorTypes;

/**
 *��Ŀ����:CxfMyCsw ����������ȡָ�����͵Ĵ������ļ��� ������:Administrator ����ʱ��: 2013-8-20 ����02:54:02
 */
@WebService
public interface GetSameSensorTypeGroupInterface {
	/**
	 * ��ȡָ�����͵Ĵ������ļ���
	 * 
	 * @param sensortype
	 *            ��Ҫ�ƶ�������������
	 *            ԭλ������InsituSensor��ֻ��Ҫ����,������Ƶ�ģ�����ģ�վ��ģ���ң�е�����RemoteSensorScanner
	 *            ���ƶ�������InsituSensor-Mobile����Ƶ������InsituSensor-Video
	 * @return
	 */
	@WebMethod
	public List<String> getSameSensorTypeSensorGroup(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensortype") SensorTypes sensortype)
			throws ServiceException;
}
