package com.event.EventOperations.utils;

/**
 * EQAL：是指两者精确匹配 INTERSECT：时间比较之需要相交即可 CONTAIN：时间可以包含 CONTAINED：时间可以被包含
 * 
 * @author yxliang
 * 
 */
public enum EventTimeCompareEnum {
	INTERSECT, CONTAIN, CONTAINED
}
