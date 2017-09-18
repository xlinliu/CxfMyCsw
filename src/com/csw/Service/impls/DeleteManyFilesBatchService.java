package com.csw.Service.impls;

import com.csw.Service.interfaces.DeleteManyFilesBatchServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;

public class DeleteManyFilesBatchService implements
		DeleteManyFilesBatchServiceInterface {

	/**
	 * ɾ�����е���Ҫɾ�����ĵ���������Ϣ����Ҫע����ǣ� ��Ҫ�ж����е�filenames�е��ļ����Ʊ�������Username�ſ��Ա�ɾ��
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param filenames
	 *            ��Ҫɾ�����ļ�����,����ļ���������� �Կո����,�����id����ʹsensorML��id��Ҳ��ʹebrim��ʽ��idֵ
	 * @return ɾ���ɹ����� 1 ,ɾ��ʧ�ܷ���0�������쳣����2
	 */
	public int DeleteManyFileContentsMethod(String username, String password,
			String filenames) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(filenames)) {
			throw new ServiceException("����filenames���ݲ���Ϊ��!");
		}

		// �ڶ�������ȡ����Ҫɾ����ȫ����file��id����
		for (String fileid : StringUtil.GetFilesIDCollection(filenames)) {
			// ����û��������SensorML��idֵ������Ҫ��SensorML��idֵ����:pacakge��ֵ
			if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(fileid)) {
				throw new ServiceException("����filenames�е��ļ����������⣬���ʵ!");
			}
			fileid = StringUtil.AppendPacakgeStr(fileid);
			// �ֱ�ɾ��file id�����������ɾ�������Ҹ�file���û�����Ҫ�����
			try {
				if (OperateSensorUtil.CheckSensorIdIsBelongOwner(username,
						fileid)) {
					TransactionOperation.DeleteRegistryPackageById(fileid
							.trim());
				}
			} catch (Exception e) {
				throw new ServiceException("��ɾ��[" + fileid
						+ "]ʱʧ�ܣ����������Ϣ����ϸ��Ϣ���£�[" + e.getMessage() + "]");
			}
		}
		return 1;
	}
}
