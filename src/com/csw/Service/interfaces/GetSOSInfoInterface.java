package com.csw.Service.interfaces;

import java.util.List;

import com.csw.utils.custometypes.SensorSOSInfo;

/**
 * ��ȡ�۲����ݷ���csw�д��еĴ�������ȫ��sos�ͻ����ţ�
 * 
 * @author Administrator
 * 
 */
public interface GetSOSInfoInterface {
	/**
	 * ��ȡ����sos�Ļ�����Ϣ,SensorSOSInfo����sos��ַ��sos�����ţ���sos�й���Ĵ�������ʶ��
	 * 
	 * @return
	 */
	public List<SensorSOSInfo> getAllSosInfos();
}
