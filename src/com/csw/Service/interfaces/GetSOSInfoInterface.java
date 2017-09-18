package com.csw.Service.interfaces;

import java.util.List;

import com.csw.utils.custometypes.SensorSOSInfo;

/**
 * 获取观测数据服务（csw中存有的传感器的全部sos和机构号）
 * 
 * @author Administrator
 * 
 */
public interface GetSOSInfoInterface {
	/**
	 * 获取所有sos的基本信息,SensorSOSInfo包括sos地址，sos机构号，和sos中管理的传感器标识符
	 * 
	 * @return
	 */
	public List<SensorSOSInfo> getAllSosInfos();
}
