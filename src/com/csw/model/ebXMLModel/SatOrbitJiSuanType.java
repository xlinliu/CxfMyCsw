package com.csw.model.ebXMLModel;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-9-18 ����02:33:51
 */
public class SatOrbitJiSuanType {
	private int id;// ��ʶ��
	private String satid;// �������ı�ʶ��
	private String maker;// �û�
	private StandSatSensorPlatformPair sspp;// �󶨵Ĵ�������ƽ̨

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSatid() {
		return satid;
	}

	public void setSatid(String satid) {
		this.satid = satid;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public StandSatSensorPlatformPair getSspp() {
		return sspp;
	}

	public void setSspp(StandSatSensorPlatformPair sspp) {
		this.sspp = sspp;
	}

}
