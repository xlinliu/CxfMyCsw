/**
 * 
 */
package com.csw.utils.custometypes;

import java.util.List;


/**
 *��Ŀ����:CxfMyCsw �������� ������ƽ̨�� ������:Administrator ����ʱ��: 2013-8-23 ����10:13:41
 */
public class PlatformandSensors {
	private SensorInfo platform;// ƽ̨
	private List<SensorInfo> sensors;// ��������ʶ�������͵ļ���

	public List<SensorInfo> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorInfo> sensors) {
		this.sensors = sensors;
	}

	public SensorInfo getPlatform() {
		return platform;
	}

	public void setPlatform(SensorInfo platform) {
		this.platform = platform;
	}
}
