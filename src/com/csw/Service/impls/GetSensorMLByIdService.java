package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;
import com.csw.Service.interfaces.GetSensorMLByIdServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.MyebRIMSmlContents;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class GetSensorMLByIdService implements GetSensorMLByIdServiceInterface {
	/**
	 * ��ȡ������sensorml�ĵ������ݣ������Ҫ�ṩ�Ĳ������û��������룬��sensorml��id����ebrim��idֵ
	 */
	public List<String> GetSensorMLByIdServiceMehtod(String username,
			String password, String docId) throws ServiceException {
		// ��һ����֤�û�������ʵ��
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(docId)) {
			throw new ServiceException("����docId����������ʵ!");
		}
		List<String> lists = new ArrayList<String>();
		String ebrimid = StringUtil.AppendPacakgeStr(docId);
		// ���������ǲ������ݿ��ȡ��Ӧ��sensorml�ĵ�������
		OperateSensorUtil gsmbiu = new OperateSensorUtil();
		MyebRIMSmlContents mys = gsmbiu.GetSensorMLDocumentByIdMethod(username,
				ebrimid, false);
		// ���Ĳ����ǽ����ҵ�sensorml���ĵ������ݷ���
		if (mys != null) {
			if (mys.getFilename() == null) {
				lists.add("");
			} else if (mys.getFilename().trim().equals("")) {
				lists.add("");
			} else {
				lists.add(mys.getFilename().trim());
			}
			if (mys.getSensormlContent() == null) {
				lists.add("");
			} else if (mys.getSensormlContent().trim().equals("")) {
				lists.add("");
			} else {
				lists.add(FormatXmlUtil.formatXml(mys.getSensormlContent()
						.trim()));
			}
			return lists;
		} else {
			throw new ServiceException("�û������ϴ����ĵ�������������ĵ�[" + docId + "]");
		}
	}
}
