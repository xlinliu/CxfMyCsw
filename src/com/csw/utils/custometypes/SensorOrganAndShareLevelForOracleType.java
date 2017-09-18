/**
 * 
 */
package com.csw.utils.custometypes;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-28 上午11:36:23
 */
public class SensorOrganAndShareLevelForOracleType {
	private String SENSORID;// 传感器的标识符
	private String SENSORORGAN;// 传感器的组织信息
	private String SENSORSHARELEVEL;// 传感器的共享级别
	private String SENSOROWNER;// 传感器所有者

	public String getSENSORID() {
		return SENSORID;
	}

	public void setSENSORID(String sENSORID) {
		SENSORID = sENSORID;
	}

	public String getSENSORORGAN() {
		return SENSORORGAN;
	}

	public void setSENSORORGAN(String sENSORORGAN) {
		SENSORORGAN = sENSORORGAN;
	}

	public String getSENSORSHARELEVEL() {
		return SENSORSHARELEVEL;
	}

	public void setSENSORSHARELEVEL(String sENSORSHARELEVEL) {
		SENSORSHARELEVEL = sENSORSHARELEVEL;
	}

	public String getSENSOROWNER() {
		return SENSOROWNER;
	}

	public void setSENSOROWNER(String sENSOROWNER) {
		SENSOROWNER = sENSOROWNER;
	}

}
