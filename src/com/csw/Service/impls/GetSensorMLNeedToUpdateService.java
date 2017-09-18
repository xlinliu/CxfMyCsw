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
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);
		if (resulttype == null || resulttype.trim().equals("")
				|| !resulttype.trim().toLowerCase().equals("sensorml")
				|| !resulttype.trim().toLowerCase().equals("ebrim")) {
			throw new ServiceException("����resulttype�����������������!");
		}
		if (sensorids == null || sensorids.size() == 0) {
			throw new ServiceException("����sensorids����Ϊ�գ����ʵ");
		}
		if (stamps == null || stamps.size() == 0) {
			throw new ServiceException("����stamps����Ϊ�գ����ʵ");
		}
		List<String> sensormlids = new ArrayList<String>();
		String str = "";
		for (int i = 0; i < sensorids.size(); i++) {
			if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorids
					.get(i))) {
				continue;
			}
			// �ڶ��� �޸�sensorml��idΪpacakge��idֵ��������û��Ƿ��и�sensorml��id
			str = StringUtil.AppendPacakgeStr(sensorids.get(i).trim());
			if (OperateSensorUtil.CheckSensorMLExistMethod(username, str)) {
				// �������
				Date dbdate = OperateSensorUtil.GetSensorMLSavedTime(str);
				Date currdate = DateOperationUtil.ParseStrToTime(stamps.get(i)
						.trim());
				int resultnum = DateOperationUtil
						.CompareDates(dbdate, currdate);
				// ���Ĳ����Ƿ��������Ҫ���µ�sensorml������
				// ������ݿ���sensorml��ʱ����ȴ��ݹ�����sensorml��ʱ���Ҫ������뵽Ҫ���µ��б��У�����Ͳ�����
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
