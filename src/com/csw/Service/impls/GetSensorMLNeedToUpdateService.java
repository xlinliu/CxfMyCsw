package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.csw.Service.interfaces.GetSensorMLNeedToUpdateServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.DateOperationUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class GetSensorMLNeedToUpdateService implements
		GetSensorMLNeedToUpdateServiceInterface {

	public List<String> GetSensorMLNeedToUpdateMethod(String username,
			String password, List<String> sensorids, List<String> stamps,
			String resulttype) throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		if (resulttype == null || resulttype.trim().equals("")
				|| !resulttype.trim().toLowerCase().equals("sensorml")
				|| !resulttype.trim().toLowerCase().equals("ebrim")) {
			throw new ServiceException("参数resulttype输入错误，请重新输入!");
		}
		if (sensorids == null || sensorids.size() == 0) {
			throw new ServiceException("参数sensorids不能为空，请核实");
		}
		if (stamps == null || stamps.size() == 0) {
			throw new ServiceException("参数stamps不能为空，请核实");
		}
		List<String> sensormlids = new ArrayList<String>();
		String str = "";
		for (int i = 0; i < sensorids.size(); i++) {
			if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorids
					.get(i))) {
				continue;
			}
			// 第二步 修改sensorml的id为pacakge的id值，检验该用户是否有该sensorml的id
			str = StringUtil.AppendPacakgeStr(sensorids.get(i).trim());
			if (OperateSensorUtil.CheckSensorMLExistMethod(username, str)) {
				// 如果存在
				Date dbdate = OperateSensorUtil.GetSensorMLSavedTime(str);
				Date currdate = DateOperationUtil.ParseStrToTime(stamps.get(i)
						.trim());
				int resultnum = DateOperationUtil
						.CompareDates(dbdate, currdate);
				// 第四步，是否放置在需要更新的sensorml的里面
				// 如果数据库中sensorml的时间戳比传递过来的sensorml的时间戳要早则加入到要更新的列表中，否则就不更新
				if (resultnum == 1) {
					if (resulttype.trim().toLowerCase().equals("sensorml")) {
						str = StringUtil.DeletePackageStr(str);
					} else if (resulttype.trim().toLowerCase().equals("ebrim")) {
						str = str.trim();
					}
					sensormlids.add(str);
				}
			}
			str = "";
		}
		return sensormlids;
	}
}
