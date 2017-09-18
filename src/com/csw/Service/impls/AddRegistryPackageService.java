package com.csw.Service.impls;

import java.util.ArrayList;
import java.util.List;

import com.csw.Service.interfaces.AddRegistryPackageServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.SensorInfoUtil.GetRegistryRegistryInfoUtils;
import com.csw.utils.Userutils.UserInfoUtil;

public class AddRegistryPackageService implements
		AddRegistryPackageServiceInterface {
	/**
	 * ��ȡ����ע������е�RegistryPackage
	 * 
	 * @param username
	 *            �û�������
	 * @param password
	 *            �û�������
	 * @param operation
	 *            �û��Ĳ���,ȡֵΪtransaction
	 * @return �����û�������ע���ȫ����RegistryPackage��idֵ������list�ķ�ʽ���أ���� û�гɹ��򷵻�null
	 */
	public List<String> GetOwnRegistryPackageMetod(String username,
			String password) throws ServiceException {
		// ��ʵ�û���Ϣ
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> rpl = new ArrayList<String>();
		try {
			rpl = GetRegistryRegistryInfoUtils.GetRegistryPackageList(username,
					true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("��ȡ�û�����ӵ�е��ĵ�����Ϣʧ�ܣ�������ϵ�����!");
		}
		return rpl;
	}
}