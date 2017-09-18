package com.csw.Service.impls;

import com.csw.Service.interfaces.CreateGetRcordByIdDocumentServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.CreateGetRecordByIdDocumentUtil;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.FormatXmlUtil;
import com.csw.utils.StringUtil;

public class CreateGetRecordByIdDocumentService implements
		CreateGetRcordByIdDocumentServiceInterface {
	/**
	 * ����record��idֵ����ȡ����records�Ĳ�ѯ����
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @param recordId
	 *            record��idֵ
	 * @return �������ɵ�getRecords�Ĳ�ѯ�ĵ������ݣ�ʧ�ܷ���null
	 * @throws �����쳣��Ϣ
	 */
	public String CreateGetRecordByIdDocumentMethod(String username,
			String password, String recordId) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(recordId)) {
			throw new ServiceException("����recordId���ݲ���ȷ!");
		}
		String getRecordIdDocument = "";
		// �ڶ����������sensorML�ĸ�ʽ��id������ת��ΪebRIM�ĸ�ʽ��id
		recordId = StringUtil.AppendPacakgeStr(recordId);
		// ������������GetRecordById�ĵ����ݣ�����ʽ�����ĵ�������
		try {
			getRecordIdDocument = FormatXmlUtil
					.formatXml(CreateGetRecordByIdDocumentUtil
							.createGetRecordByIdDocumentMehtod(recordId, "full")
							.xmlText());
			// ���Ĳ������ظ��ĵ�������
			return getRecordIdDocument;
		} catch (Exception e) {
			throw new ServiceException("getRecordById�ĵ������������ɴ�����δ�ܽ��������ϵ�����!");
		}
	}
}
