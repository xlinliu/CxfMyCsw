package com.event.commonutils;

import com.csw.exceptions.EventBBOXTypeNotFormatException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.TimePeroidNotFormatException;
import com.event.InnerEntities.EventBBOXType;
import com.event.InnerEntities.TimePeroid;

public class EventElementCheckUtil {
	/**
	 * 核实TimePeroid元素是否满足要求，开始时间必须不晚于结束时间，并且TimePeroid的属性取值不能为空
	 * 
	 * @param tp
	 * @return 满足条件则为true，否则为false
	 * 
	 * @throws NullZeroException
	 * @throws TimePeroidNotFormatException
	 */
	public static Boolean CheckTimePeroid(TimePeroid tp)
			throws NullZeroException, TimePeroidNotFormatException {
		if (tp == null) {
			throw new NullZeroException("类型TimePeroid元素不能为null");
		}
		if (tp.getStartime() != null && tp.getEndtime() != null) {
			if (tp.getStartime().after(tp.getEndtime())) {
				throw new TimePeroidNotFormatException("类型TimePeroid元素取值存在问题!");
			}
		} else {
			throw new TimePeroidNotFormatException("类型TimePeroid元素中属性不能为空");
		}
		return true;
	}

	/**
	 * 对EventBBOXType进行检查
	 * 
	 * @param ebt
	 * @throws NullZeroException
	 * @throws EventBBOXTypeNotFormatException
	 */
	public static void CheckEventBBOXType(EventBBOXType ebt)
			throws NullZeroException, EventBBOXTypeNotFormatException {
		if (ebt == null) {
			throw new NullZeroException("类型EventBBOXType元素不能为null");
		}
		if (ebt.getXhigh() < ebt.getXlow()) {
			throw new EventBBOXTypeNotFormatException(
					"类型EventBBOXType中xheigh必须不小于xlow");
		}
		if (ebt.getXhigh() < ebt.getXlow()) {
			throw new EventBBOXTypeNotFormatException(
					"类型EventBBOXType中yheigh必须不小于ylow");
		}
	}
}
