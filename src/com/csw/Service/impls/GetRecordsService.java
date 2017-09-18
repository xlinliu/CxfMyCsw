package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import com.csw.Service.interfaces.GetRecordsServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.GetRecordsOperationUtil.GetRecordsOperation;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.TransactionsUtil.ParseInputQueryStrUtil;
import com.csw.utils.TransactionsUtil.QueryAllQueryConditionUtil;
import com.csw.utils.Userutils.UserInfoUtil;

@WebService
public class GetRecordsService implements GetRecordsServiceInterface {

	public static void main(String[] args) throws Exception {
		GetRecordsService grs = new GetRecordsService();
		grs.GetRecordsDocumentMethod("admin", "cswadmin",
				"sensorPowerType 11 | sensorLongName 1 |", false);
	}

	public String GetRecordsDocumentMethod(String username, String password,
			String queryStr, boolean allownull) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> resultSensors = new ArrayList<String>();
		// ��һ�������ַ�
		Map<String, String> maps = ParseInputQueryStrUtil
				.ParseInputQueryStrIntoListStrMethod(queryStr);
		if (maps == null || maps.size() == 0) {
			// ����ȫ���Ĵ�������id
			resultSensors = OperateSensorUtil.GetAllSensorIdsMethod();
		} else {
			// ��ȡĳ�û���ȫ���Ĵ�������ȫ���Ĺ����Ĵ�����
			List<String> initsensors = GetRegistryRegistryInfoUtils
					.GetRegistryPackageList(username, true);
			// �ڶ��������ǽ�map���󴫵ݵ���ѯ���ܵķ����У����в�ѯ
			List<String> results = QueryAllQueryConditionUtil
					.QueryAllConditionMethod(maps, initsensors, allownull);
			resultSensors = results;
		}
		if (resultSensors != null && resultSensors.size() > 0) {
			String sensorsStr = GetRecordsOperation
					.CreateSensorDescriptionDocumentMethod(resultSensors, "",
							"full");
			String resultStr = GetRecordsOperation
					.ConnectGetRecordsResponseDocument(sensorsStr, "", "full",
							resultSensors.size());
			return FormatXmlUtil.formatXml(resultStr);
		} else if (resultSensors.size() == 0) {
			return "no sensors need!";
		} else {
			throw new ServiceException("û�з���Ҫ��Ĵ�����");
		}
	}
}
