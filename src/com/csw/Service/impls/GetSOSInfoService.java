package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.csw.Service.interfaces.GetSOSInfoInterface;
import com.csw.utils.SensorInfoUtil.OperateSensornewUtil;
import com.csw.utils.custometypes.SensorSOSInfo;

public class GetSOSInfoService implements GetSOSInfoInterface {
	public List<SensorSOSInfo> getAllSosInfos() {
		Map<String, SensorSOSInfo> sosInfos = OperateSensornewUtil
				.getAllSensorSOSInfos();
		List<SensorSOSInfo> ssis = new ArrayList<SensorSOSInfo>();
		Iterator<String> iterator = sosInfos.keySet().iterator();
		while (iterator.hasNext()) {
			ssis.add(sosInfos.get(iterator.next()));
		}
		return ssis;
	}
}
