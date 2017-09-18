package com.event.InnerEntities;

public class EventSpaceTime {
	private TimePeroid tp;// 事件的开始时间和结束时间
	private LatLongPiar llp;// 事件发生的经度和纬度对
	private DetailAddress da;// 事件发生的行政区的地址
	private Integer type = 0;// 地址的类型，1表示只有行政区划，2表示只有经纬度，3表示具有

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
