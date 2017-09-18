package com.event.commonutils;

import com.csw.exceptions.EventBBOXTypeNotFormatException;
import com.csw.exceptions.NullZeroException;
import com.csw.exceptions.TimePeroidNotFormatException;
import com.event.InnerEntities.EventBBOXType;
import com.event.InnerEntities.TimePeroid;

public class EventElementCheckUtil {
	/**
	 * ��ʵTimePeroidԪ���Ƿ�����Ҫ�󣬿�ʼʱ����벻���ڽ���ʱ�䣬����TimePeroid������ȡֵ����Ϊ��
	 * 
	 * @param tp
	 * @return ����������Ϊtrue������Ϊfalse
	 * 
	 * @throws NullZeroException
	 * @throws TimePeroidNotFormatException
	 */
	public static Boolean CheckTimePeroid(TimePeroid tp)
			throws NullZeroException, TimePeroidNotFormatException {
		if (tp == null) {
			throw new NullZeroException("����TimePeroidԪ�ز���Ϊnull");
		}
		if (tp.getStartime() != null && tp.getEndtime() != null) {
			if (tp.getStartime().after(tp.getEndtime())) {
				throw new TimePeroidNotFormatException("����TimePeroidԪ��ȡֵ��������!");
			}
		} else {
			throw new TimePeroidNotFormatException("����TimePeroidԪ�������Բ���Ϊ��");
		}
		return true;
	}

	/**
	 * ��EventBBOXType���м��
	 * 
	 * @param ebt
	 * @throws NullZeroException
	 * @throws EventBBOXTypeNotFormatException
	 */
	public static void CheckEventBBOXType(EventBBOXType ebt)
			throws NullZeroException, EventBBOXTypeNotFormatException {
		if (ebt == null) {
			throw new NullZeroException("����EventBBOXTypeԪ�ز���Ϊnull");
		}
		if (ebt.getXhigh() < ebt.getXlow()) {
			throw new EventBBOXTypeNotFormatException(
					"����EventBBOXType��xheigh���벻С��xlow");
		}
		if (ebt.getXhigh() < ebt.getXlow()) {
			throw new EventBBOXTypeNotFormatException(
					"����EventBBOXType��yheigh���벻С��ylow");
		}
	}
}
