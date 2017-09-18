package com.csw.Service.impls;

import java.util.Date;
import javax.jws.WebService;
import com.csw.Service.interfaces.SaveSensorMLServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-7-26 ����09:58:30
 */
@WebService
public class SaveSensorMLService implements SaveSensorMLServiceInterface {
	/**
	 * ������ص�sensorml�ĵ�
	 */
	public boolean SaveSensorML(String username, String password,
			String sensorid, String sensormlcontent) throws ServiceException {
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil
				.checkStringIsNotNULLAndEmptyMethod(sensormlcontent)) {
			throw new ServiceException("����content����������ʵ!");
		}
		// ����ɾ���û�
		if (IsExistsSensorML(username, password, sensorid)) {
			OperateSensorUtil.DeleteSensorML(username, password, sensorid);
		}
		boolean bol = OperateSensorUtil.SaveSensorML(username, password,
				sensorid, sensormlcontent);
		if (!bol) {
			throw new ServiceException("�޷�sensorml���� !");
		} else {
			return bol;
		}
	}

	public static void main1(String[] args) throws Exception {
		SaveSensorMLService ssMlService = new SaveSensorMLService();
		ssMlService.DeleteSensorML("admin", "cswadmin",
				"urn:liesmars:object:feature:����̶���:�ֳ�:���ٷ��򴫸���:MWW-S:package");
	}

	/**
	 * ��ȡ��Ϣ
	 * 
	 * @param username
	 * @param password
	 * @param sensorid
	 * @return
	 * @throws ServiceException
	 */
	public String ReadSensorContent(String username, String password,
			String sensorid) throws ServiceException {
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("����sensorid����������ʵ!");
		}
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		String content = OperateSensorUtil.ReadSensorML(sensorid);
		if (content == null) {
			throw new ServiceException("��ȡsensorml�ĵ������쳣");
		} else {
			return content;
		}
	}

	public static void main(String[] args) throws ServiceException {
		SaveSensorMLService ssm = new SaveSensorMLService();
		System.out.println(ssm.ReadSensorContent("admin", "cswadmin",
				"urn:ogc:feature:insitesensor:CarAXA210-GPS:package"));
	}

	/**
	 * ��ȡָ���Ĵ�������sensorml�Ĵ洢ʱ��
	 * 
	 * @param username
	 *            ע�����ĵ�����
	 * @param password
	 *            ע�����ĵ�����
	 * @param sensorid
	 *            ע��Ĵ������ı�ʶ��
	 * @return �������µ�ʱ��
	 */
	public Date GetSensorMLSaveTime(String username, String password,
			String sensorid) throws ServiceException {
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("����sensorid����������ʵ!");
		}
		return OperateSensorUtil.GetSensorMLSavedTime(sensorid);
	}

	public boolean IsExistsSensorML(String username, String password,
			String sensorid) throws ServiceException {
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("����sensorid����������ʵ!");
		}
		return OperateSensorUtil.IsExistsSensorML(username, sensorid);
	}

	public boolean DeleteSensorML(String username, String password,
			String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("����sensorid����������ʵ!");
		}
		return OperateSensorUtil.DeleteSensorML(username, password, sensorid);
	}
}
