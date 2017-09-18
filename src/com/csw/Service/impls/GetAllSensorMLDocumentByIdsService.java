package com.csw.Service.impls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.csw.Service.interfaces.GetAllSensorMLDocumentByIdsServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.MyebRIMSmlContents;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class GetAllSensorMLDocumentByIdsService implements
		GetAllSensorMLDocumentByIdsServiceInterface {

	public Map<String, String> GetAllSensorMLDocumentByIdsMethod(
			String username, String password, List<String> ids,
			String contenttype, boolean type) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		Map<String, String> maps = new HashMap<String, String>();
		// ��ȡ����sensorML���ĵ�������
		String errorids = "";
		if (ids == null || ids.size() <= 0) {
			throw new ServiceException("����ids����������ʵ!");
		}
		if (contenttype == null
				|| (!contenttype.toLowerCase().trim().equals("sensorml") && !contenttype
						.toLowerCase().trim().equals("ebrim"))) {
			throw new ServiceException("����contenttype����������ʵ!");
		}
		// �ڶ���������û���Ȩ��
		if (type) {
			// ��ѯ���е��û����ĵ�
			if (UserInfoUtil.GetLevelOfUser(username, password) == 1) {
				// ��ϵͳ����Ա����
				if (ids != null && ids.size() > 0) {
					for (int i = 0; i < ids.size(); i++) {
						MyebRIMSmlContents My = new OperateSensorUtil()
								.GetSensorMLDocumentByIdMethod(username, ids
										.get(i).trim(), true);
						if (My != null) {
							String str = "";
							if (contenttype == null) {
								str = My.getSensormlContent().trim();
							} else {
								if (contenttype == null) {
									str = My.getSensormlContent().trim();
								} else {
									if (contenttype.trim().toLowerCase()
											.equals("sensorml")) {
										str = My.getSensormlContent().trim();
									} else if (contenttype.trim().toLowerCase()
											.equals("ebrim")) {
										str = My.getEbrimContent().trim();
									} else {
										str = My.getSensormlContent().trim();
									}
								}
							}
							if (str != null && !str.trim().equals("")) {
								maps.put(ids.get(i).trim(), FormatXmlUtil
										.formatXml(str.trim()));
							} else {
								// ���û����id���Ӧ��sensorML�ĵ���ʱ�򣬾���null������
								maps.put(ids.get(i).trim(), "null");
							}
						} else {
							// ���˵�������Ͳ��������sensorML��id
							errorids += ids.get(i).trim() + " ";
						}
					}
					if (errorids.equals("")) {
						// ���û��id�Ǵ����������ǽ����е���Ϣ���ݹ�ȥ
						return maps;
					} else {
						// ��������⣬����Ҫ������쳣���д���
						throw new ServiceException("��Щids [" + errorids.trim()
								+ "]���������������������!");
					}
				} else {
					throw new ServiceException("�û�[" + username
							+ "]��Ҫ��ѯ��sensorml�ĵ���id�������");
				}
			}
		} else {
			// ֻ�ǲ�ѯ�Լ����ĵ�
			if (ids != null && ids.size() > 0) {
				String nullids = "";
				for (int i = 0; i < ids.size(); i++) {
					MyebRIMSmlContents My = new OperateSensorUtil()
							.GetSensorMLDocumentByIdMethod(username, ids.get(i)
									.trim(), false);
					if (My != null) {
						String str = "";
						if (contenttype == null) {
							str = My.getSensormlContent().trim();
						} else {
							if (contenttype == null) {
								str = My.getSensormlContent().trim();
							} else {
								if (contenttype.trim().toLowerCase().equals(
										"sensorml")) {
									str = My.getSensormlContent().trim();
								} else if (contenttype.trim().toLowerCase()
										.equals("ebrim")) {
									str = My.getEbrimContent().trim();
								} else {
									str = My.getSensormlContent().trim();
								}
							}
						}
						if (str != null && !str.trim().equals("")) {
							maps.put(ids.get(i).trim(), FormatXmlUtil
									.formatXml(str.trim()));
						} else {
							// �������ݿ���Ϊ�յ�ids
							maps.put(ids.get(i).trim(), "null");
						}
					} else {
						// �������ݿ���û�б����sensor��id
						nullids += ids.get(i).trim() + " ";
					}
				}
				if (nullids.equals("")) {
					// û��Ϊ�յĻ���sensorml��conent�Ĵ洢Ϊ�յļ�¼���򷵻����еĲ�ѯ����sensorml���ĵ�������
					return maps;
				} else {
					throw new ServiceException("�û�[" + username + "]�����ids["
							+ nullids + "]����!");
				}
			} else {
				throw new ServiceException("�û�[" + username
						+ "]��Ҫ��ѯ��sensorml�ĵ���id�������!");
			}
		}
		return null;
	}
}
