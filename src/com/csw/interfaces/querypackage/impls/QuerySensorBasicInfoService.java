package com.csw.interfaces.querypackage.impls;

import java.util.List;
import com.csw.exceptions.ServiceException;
import com.csw.interfaces.querypackage.QuerySensorBasicInfoInterface;
import com.csw.utils.custometypes.SensorBasicInfoType;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-11 上午08:40:14
 */
public class QuerySensorBasicInfoService implements
		QuerySensorBasicInfoInterface {

	public List<SensorBasicInfoType> GetSensorCommunicationCapabilityForMultiSensorsMethod(
			String username, String password, List<String> sensorids,
			String capability) throws ServiceException {
		return new com.csw.interfaces.querypackage.impls.GetSensorCommunicationCapabilityService()
				.GetSensorCommunicationCapabilityForMultiSensorsMethod(
						username, password, sensorids, capability);
	}

	public List<SensorBasicInfoType> GetSensorComputeCapabilityForMultiSensorsMethod(
			String username, String password, List<String> sensorids,
			String capability) throws ServiceException {
		return new GetSensorComputeCapabilityService()
				.GetSensorComputeCapabilityForMultiSensorsMethod(username,
						password, sensorids, capability);
	}

	public List<SensorBasicInfoType> GetSensorConnectionEarthPosInfoFroMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		return new GetSensorConnectionEarthPosInfoService()
				.GetSensorConnectionEarthPosInfoFroMultiSensorsMethod(username,
						password, sensorids);
	}

	public List<SensorBasicInfoType> GetSensorGetoInfoForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		return new GetSensorGeoInfoService()
				.GetSensorGetoInfoForMultiSensorsMethod(username, password,
						sensorids);
	}

	public List<SensorBasicInfoType> GetSensorIntendedApplicationForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		return new GetSensorIntendedApplicationService()
				.GetSensorIntendedApplicationForMultiSensorsMethod(username,
						password, sensorids);
	}

	public List<SensorBasicInfoType> GetSensorLongNamesMethod(String username,
			String password, List<String> sensorids) throws ServiceException {
		return new GetSensorLongNameService().GetSensorLongNamesMethod(
				username, password, sensorids);
	}

	public List<SensorBasicInfoType> GetSensorMeasureCapabilitiesForMultiSensorsMethod(
			String username, String password, List<String> sensorids,
			String capability) throws ServiceException {
		return new GetSensorMeasureCapabilityService()
				.GetSensorMeasureCapabilitiesForMultiSensorsMethod(username,
						password, sensorids, capability);
	}

	public List<SensorBasicInfoType> GetSensorOrganizationInfoForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		return new GetSensorOrganizationInfoService()
				.GetSensorOrganizationInfoForMultiSensorsMethod(username,
						password, sensorids);
	}

	public List<SensorBasicInfoType> GetSensorPhysicalPropertyForMultiSensorsMethod(
			String username, String password, List<String> sensorids,
			String physicalproperty) throws ServiceException {
		return new GetSensorPhysicalPropertyService()
				.GetSensorPhysicalPropertyForMultiSensorsMethod(username,
						password, sensorids, physicalproperty);
	}

	public List<SensorBasicInfoType> GetSensorPositionInfoForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		return new GetSensorPositionInfoService()
				.GetSensorPositionInfoForMultiSensorsMethod(username, password,
						sensorids);
	}

	public List<SensorBasicInfoType> GetSensorSensorTypeForMultiSensorMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		return new GetSensorSensorTypeService()
				.GetSensorSensorTypeForMultiSensorMethod(username, password,
						sensorids);
	}

	public List<SensorBasicInfoType> GetSensorShortNameforMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		return new GetSensorShortNameService()
				.GetSensorShortNameforMultiSensorsMethod(username, password,
						sensorids);
	}

	public List<SensorBasicInfoType> GetSensorWorkValidTimeBeginForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		return new GetSensorWorkBeginTimeService()
				.GetSensorWorkValidTimeBeginForMultiSensorsMethod(username,
						password, sensorids);
	}

	public List<SensorBasicInfoType> GetSensorWorkValidTimeEndForMultiSensorsMethod(
			String username, String password, List<String> sensorids)
			throws ServiceException {
		return new GetSensorWorkEndTimeService()
				.GetSensorWorkValidTimeEndForMultiSensorsMethod(username,
						password, sensorids);
	}

}
