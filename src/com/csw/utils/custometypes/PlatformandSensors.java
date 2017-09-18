/**
 * 
 */
package com.csw.utils.custometypes;

import java.util.List;


/**
 *项目名称:CxfMyCsw 类描述： 用于在平台和 创建人:Administrator 创建时间: 2013-8-23 上午10:13:41
 */
public class PlatformandSensors {
	private SensorInfo platform;// 平台
	private List<SensorInfo> sensors;// 传感器标识符和类型的集合

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
