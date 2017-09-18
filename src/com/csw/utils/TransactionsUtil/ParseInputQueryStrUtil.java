package com.csw.utils.TransactionsUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.csw.utils.StringUtil;

public class ParseInputQueryStrUtil {
	/**
	 * 将从页面上传递过来的查询的字段解析为最小字段的列表
	 * 
	 * @param queryStr
	 *            格式如下"sensorPowerType 11 | sensorLongName 1 |"
	 * @return 返回 字段组成Map的结果
	 */
	public static Map<String, String> ParseInputQueryStrIntoListStrMethod(
			String queryStr) {
		Map<String, String> maps = new HashMap<String, String>();
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(queryStr)) {
			return null;
		}
		queryStr = queryStr.trim().substring(0, queryStr.trim().length() - 1);
		String[] strs = queryStr.split(" \\| ");
		for (int i = 0; i < strs.length; i++) {
			String rowStr = strs[i].trim();
			maps.put(rowStr.split(" ")[0].trim(), rowStr.substring(
					rowStr.split(" ")[0].length()).trim());
		}
		return maps;
	}

	/**
	 * 验证可行 {sensorPhysicalMTBF=1, sensorMeasureLightBeamMode=1}
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String queryStr = "sensorPowerType 11 | sensorLongName 1 | sensorShortName 1 | sensorKeywords 1 | sensorIntendApplication 1 | sensorValidTimeBegin 1 | sensorValidTimeEnd 1 | sensorPosition 1 | sensorObservedRange 1 | sensorPhysicalLength 1 | sensorPhysicalWidth 1 | sensorPhysicalWeight 1 | sensorPhysicalHeight 1 | sensorPhyscialCurrent 1 | sensorphysicalVoltage 1 | sensorPhysicalPower 1 | sensorPhysicalOutputPower 1 | sensorPhysicalOperatingTemperature 1 | sensorPhysicalOperatingHumidity 1 | sensorPhysicalProtectionGrade 1 | sensorPhysicalMTBF 1 | sensorPhysicaldesignedLife 1 | sensorOgranizationName 1 | sensorComputingStorage 1 | sensorDataRate 1 | sensorCommunicationDataTransmissionMode 1 | sensorCommunicationCommunicationInterface 1 | sensorCommunicationCommunicationMethod 1 | sensorCommunicationControlWaveBand 1 | sensorCommunicationDownlinkWaveBand 1 | sensorCommunicationDownlinkRate 1 | sensorMeasureBandsCategory 1 | sensorMeasureBandsNumber 1 | sensorMeasureBandswidthRange 1 | sensorMeasureFov 1 | sensorMeasureSwathRange 1 | sensorMeasureIncidentAngle 1 | sensorMeasurePolarizationMode 1 | sensorMeasureBandFrequency 1 | sensorMeasureLightBeamMode 1 | sensorMeasureSpatialResolutionRange 1 | sensorMeasureNadirSpatialResolution 1 | sensorMeasureIFOV 1 | sensorMeasureSpectralResolution 1 | sensorMeasureRangeResolutionRange 1 | sensorMeasureAzimuthResolutionRange 1 | sensorMeasureQuantizationLevel 1 | sensorMeasurePrecisionRange 1 | sensorMeasureTemporalResolutionRange 1 | sensorMeasurePositioningAccuracy 1 | sensorMeasureRadiometricAccuracy 1 | sensorMeasureDataFormat 1 | sensorMeasureDataQualityLevel 1 | sensorMeasureDataAccessLevel 1 | sensorMeasurePhotographicScale 1 | sensorMeasureFocalLength 1 | sensorMeasureFlightHeight 1 | sensorMeasureGrayScale 1 | sensorMeasureCourseOverlapRate 1 | sensorMeasureLateralOverlapRate 1 | sensorMeasureFrameRate 1 | sensorMeasureShutterSpeed 1 | sensorMeasureGeometricDistortion 1 | sensorMeasureObservationPrinciple 1 | sensorMeasureObservationRadius 1 | sensorMeasureObservationResolution 1 | sensorMeasureObservingFrequency 1 | sensorMeasureObservationAbsError 1 | sensorMeasureObservationRelativeError 1 |";
		Map<String, String> maps = ParseInputQueryStrUtil
				.ParseInputQueryStrIntoListStrMethod(queryStr);
		Iterator<String> iterator = maps.keySet().iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			System.out.println(str + " : " + maps.get(str));
		}
		List<Map<String, String>> remaps = QuerySensorInfoBasePatternUtil
				.OrderQueryFieldsUtil(maps);
		for (int i = 0; i < remaps.size(); i++) {
			System.out.println(remaps.get(i).toString());
		}
	}
}
