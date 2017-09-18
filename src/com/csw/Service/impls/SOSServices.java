package com.csw.Service.impls;

import org.viky.swe.webclient.sos.SOSClientLite;
import com.csw.Service.interfaces.SOSInterfaces;
import com.csw.exceptions.ServiceException;

public class SOSServices implements SOSInterfaces {
	public static void main(String[] args) throws Exception {
		SOSServices sosServices = new SOSServices();
		String string = sosServices.GetLastObservation(
				"urn:omnicon:insitusensor:personmanagsys-exapmle2", "user",
				"LIESMARS", "http://localhost:8080/SOS/sos");
		System.out.println(string);
	}

	public String GetLastObservation(String sensorid, String obervationPro,
			String offingId, String sosadd) throws ServiceException {
		SOSClientLite sosClientLite = new SOSClientLite(sosadd);
		sosClientLite.setDefaultOfferingID(offingId);
		org.viky.swe.webclient.sos.data.SensorDataPair sdp;
		try {
			sdp = sosClientLite.getObservationLatest(sensorid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return sdp.getObservedPropertyDataPairList().get(0)
				.getTimeValuePairList().get(0).getObservedTime().toString();
	}
}
