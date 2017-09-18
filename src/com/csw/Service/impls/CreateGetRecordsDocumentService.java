package com.csw.Service.impls;

import com.csw.Service.interfaces.CreateGetRecordsDocumentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.GetRealPathUtil;
import com.csw.utils.GetRecordsOperationUtil.GetRecordsUtil;
import com.csw.utils.Userutils.UserInfoUtil;

public class CreateGetRecordsDocumentService implements
		CreateGetRecordsDocumentServiceInterface {
	/**
	 * �����û���д����Ϣ���ɼ򵥵�getRecords���ĵ�
	 * 
	 * @param username
	 *            �Ñ���
	 * @param password
	 *            ����
	 * @param startRecord
	 *            ��ʼ�Ĳ�ѯ��¼
	 * @param maximumRecord
	 *            ���ķ��صĲ�ѯ��¼��
	 * @param west
	 *            ���ߵ�ֵ
	 * @param east
	 *            ���ߵ�ֵ
	 * @param south
	 *            �ϱߵ�ֵ
	 * @param north
	 *            ���ߵ�ֵ
	 * @param startTime
	 *            ��ʼ��ֵ
	 * @param endTime
	 *            ������ʱ��
	 * @param requestType
	 *            ���������
	 * @param profileType
	 *            profile������
	 * @param title
	 *            ��ѯ�Ĺؼ� ����
	 * @param keyword
	 *            ��ѯ�Ĺؼ���
	 * @return ���ص�������getRecords���ĵ�������,���򷵻�null;
	 */
	public String CreateGetRecofdsDocumentMethod(String username,
			String password, int startRecord, int maximumRecord, String west,
			String east, String south, String north, String startTime,
			String endTime, String requestType, String profileType,
			String title, String keyword) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		// �ڶ�������ȡgetRecords��ѯ�ĵ���ģ��
		try {
			String basepath = new GetRealPathUtil().getWebInfPath();
			String getRecordModelFilePath = basepath
					+ "\\operations\\getRecords.xml";
			// �������������������Ӧ��getRecords�Ĳ�ѯ�ĵ���������
			String result = GetRecordsUtil.CreateGetRecordsDocument(
					getRecordModelFilePath, maximumRecord, startRecord,
					requestType, startTime, endTime, keyword, title, east,
					north, south, west);
			result = FormatXmlUtil.formatXml(result);
			return result;
		} catch (Exception e) {
			throw new ServiceException(
					"����getRecords.xml�ĵ�������û�����������ɣ���δ�ܽ��������ϵ�����!");
		}
	}
}
