package com.event.InnerEntities;

public class EventSpaceTime {
	private TimePeroid tp;// �¼��Ŀ�ʼʱ��ͽ���ʱ��
	private LatLongPiar llp;// �¼������ľ��Ⱥ�γ�ȶ�
	private DetailAddress da;// �¼��������������ĵ�ַ
	private Integer type = 0;// ��ַ�����ͣ�1��ʾֻ������������2��ʾֻ�о�γ�ȣ�3��ʾ����

	public TimePeroid getTp() {
		return tp;
	}

	public void setTp(TimePeroid tp) {
		this.tp = tp;
	}

	public LatLongPiar getLlp() {
		return llp;
	}

	public void setLlp(LatLongPiar llp) {
		this.llp = llp;
	}

	public DetailAddress getDa() {
		return da;
	}

	public void setDa(DetailAddress da) {
		this.da = da;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
