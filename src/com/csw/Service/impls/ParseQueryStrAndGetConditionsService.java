package com.csw.Service.impls;

import java.util.Iterator;
import java.util.Map;
import javax.jws.WebService;
import com.csw.Service.interfaces.ParseQueryStrAndGetConditionsInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.TransactionsUtil.ParseInputQueryStrUtil;
import com.csw.utils.Userutils.UserInfoUtil;

@WebService
public class ParseQueryStrAndGetConditionsService implements
		ParseQueryStrAndGetConditionsInterface {
	public Map<String, String> ParseQueryStrAndConditionsMethod(
			String username, String password, String queryStr)
			throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		Map<String, String> maps = ParseInputQueryStrUtil
				.ParseInputQueryStrIntoListStrMethod(queryStr);
		return maps;
	}

	public static void main(String[] args) throws ServiceException {
		ParseQueryStrAndGetConditionsService pqscService = new ParseQueryStrAndGetConditionsService();
		Map<String, String> maps = pqscService
				.ParseQueryStrAndConditionsMethod(
						"admin",
						"cswadmin",
						"sensorBasicValidTimeBegin 2013-09-01T13:35:00Z | sensorBasicValidTimeEnd 2013-08-04T13:36:00Z | sensorBasicObservedRange 32,113,29,116 | ");
		Iterator<String> iterator = maps.keySet().iterator();
		while (iterator.hasNext()) {
			String name = iterator.next();
			System.out.println(name + " : " + maps.get(name));

		}
	}
}
