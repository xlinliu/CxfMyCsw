package com.csw.utils.OperateSensorTypeRuleUtil;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.csw.exceptions.ServiceException;
import com.csw.model.ebXMLModel.SensorTypeRule;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.OperateSensorTypeRuleUtil.Interface.OperateSensorTypeRuleInterface;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorTypeMarker;
import com.csw.utils.custometypes.SensorTypeMarkerClient;
import com.csw.utils.custometypes.SensortypeRuleOperation;

/**
 *��Ŀ����:CxfMyCsw �����������ڹ��������Ļ�������Ϣ ������:Administrator ����ʱ��: 2013-9-16 ����10:30:40
 */
public class OperateSensorTypeRuleOperation implements
		OperateSensorTypeRuleInterface {
	public static void main(String[] args) throws Exception {
		OperateSensorTypeRuleOperation ostro = new OperateSensorTypeRuleOperation();
		// �������
		SensorTypeMarkerClient stmc = new SensorTypeMarkerClient();
		stmc.setSensortype("instuplatform");
		ArrayList<String> sensorkeyword = new ArrayList<String>();
		sensorkeyword.add("ԭλ���������");
		sensorkeyword.add("ԭλƽ̨");
		// stmc.setSensorkeyword(sensorkeyword);
		stmc.setSensorchinesetype("ԭλƽ̨վ��");
		ostro.SaveSensorTypeRuleEntry("admin", "cswadmin", stmc);

		// ɾ������
		// List<String> sensortypes = new ArrayList<String>();
		// sensortypes.add("  ");
		// sensortypes.add(null);
		// sensortypes.add("remotesensor2");
		// ostro.DeleteSensorTypeRuleEntries("admin", "cswadmin", sensortypes);

		// ���¹���
		// SensorTypeRule str = new SensorTypeRule();
		// str.setId(3);
		// str.setSensortypename("instuplatform");
		// str.setSensortypechinesename("ԭλƽ̨վ��");
		// str.setSensorkeywords("ԭλƽ̨,ԭλ���������");
		// str.setMaker("admin");
		// SensorTypeRule str1 = new SensorTypeRule();
		// str1.setId(4);
		// str1.setSensortypename("yxlian4");
		// str1.setSensortypechinesename("��ѵ��4");
		// str1.setSensorkeywords("�人��ѧ,������1");
		// str1.setMaker("admin");
		// List<SensorTypeRule> strs = new ArrayList<SensorTypeRule>();
		// strs.add(str);
		// strs.add(str1);
		// ostro.UpdateSensorTypeRuleWithMultiEnties("admin", "cswadmin", strs);

		// ��ȡ
		// List<String> senosList = new ArrayList<String>();
		// senosList.add("yxlian");
		// senosList.add("ddd");
		// System.out.println(ostro.GetSensorTypeRule("admin", "cswadmin",
		// "yxlian").getSensorkeyword().toString());
		// System.out.println(ostro.GetAllSensorTypeRule("admin", "cswadmin",
		// senosList).get(0).getSensorkeyword().toString());

		// �ж��Ƿ����
		// System.out.println(ostro.IsExistOfSensorType("admin", "cswadmin",
		// "yxliang"));
		// for (SensorTypeMarker str :
		// ostro.GetSensorKeyBelongSensorType("admin",
		// "cswadmin", "������")) {
		// System.out.println(str.getSensortype());
		// }
		// ɾ��ȫ��������
		// SensortypeRuleOperation.DeleteAllSensorTypeRules("admin");
		System.out.println("here");
	}

	/**
	 * ɾ��ָ���Ĵ��������͵�����
	 */
	public List<SensorTypeMarker> DeleteSensorTypeRuleEntries(String username,
			String password, List<String> sensortypes) throws ServiceException {
		List<SensorTypeMarker> stms = new ArrayList<SensorTypeMarker>();
		UserInfoUtil.CheckUserLogin(username, password);
		// ɾ��ָ���Ĵ��������͵�ѭ��ɾ��
		for (String sensortype : sensortypes) {
			SensorTypeMarker stmMarker = new SensorTypeMarker();
			stmMarker.setSensortype(sensortype);
			try {
				SensortypeRuleOperation.DeleteSensorTypeInfo(sensortype,
						username);
				stmMarker.setIsexist(false);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					SensorTypeRule stRule = SensortypeRuleOperation
							.GetSensorTypeRuleInfo(sensortype, username);
					if (stRule != null) {
						stmMarker.setIsexist(true);
					} else {
						stmMarker.setIsexist(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					throw new ServiceException("����Ĵ������������ƴ����޷���ȡ!");
				}
			}
			stms.add(stmMarker);
		}
		return stms;
	}

	/**
	 * ɾ��ָ���Ĵ���������
	 */
	public SensorTypeMarker DeleteSensorTypeRuleEntry(String username,
			String password, String sensortype) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		SensorTypeMarker stmMarker = new SensorTypeMarker();
		stmMarker.setSensortype(sensortype);
		try {
			SensortypeRuleOperation.DeleteSensorTypeInfo(sensortype, username);
			stmMarker.setIsexist(false);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				SensorTypeRule stRule = SensortypeRuleOperation
						.GetSensorTypeRuleInfo(sensortype, username);
				if (stRule != null) {
					stmMarker.setIsexist(true);
				} else {
					stmMarker.setIsexist(false);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new ServiceException("����Ĵ������������ƴ����޷���ȡ!");
			}
		}
		return stmMarker;
	}

	/**
	 * ɾ�����еĴ��������͹���
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Boolean DeleteAllSensorTypeRule(String username, String password)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		return SensortypeRuleOperation.DeleteAllSensorTypeRules(username);
	}

	/**
	 * �����û��Զ���Ĵ���������������ѯ�����������а����Ĺؼ�������
	 */
	public List<SensorTypeMarker> GetAllSensorTypeRule(String username,
			String password, List<String> sensortypes) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorTypeMarker> strs = new ArrayList<SensorTypeMarker>();
		if (sensortypes != null) {
			for (String sensortype : sensortypes) {
				try {
					SensorTypeRule str = SensortypeRuleOperation
							.GetSensorTypeRuleInfo(sensortype, username);
					if (str != null) {
						strs
								.add(SensortypeRuleOperation
										.TranslateSensorTypeRuleToSensorTypeMarker(str));
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServiceException("��ȡָ���Ĵ��������͹�����Ϣ��������!");
				}
			}
			return strs;
		} else {
			List<SensorTypeRule> strss = SensortypeRuleOperation
					.GetAllSensorTypes(username);
			if (strss != null) {
				return SensortypeRuleOperation
						.TranslateSensorTypeRuleToSensorTypeMarkers(strss);
			} else {
				return null;
			}
		}

	}

	/**
	 * ���ݹؼ�������ȡ����Ӧ�Ĵ�����������
	 */
	public List<SensorTypeMarker> GetSensorKeyBelongSensorType(String username,
			String password, String sensorkeyword) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorTypeMarker> strs = new ArrayList<SensorTypeMarker>();
		try {
			strs
					.addAll(SensortypeRuleOperation
							.TranslateSensorTypeRuleToSensorTypeMarkers(SensortypeRuleOperation
									.getSensorTypeRuleInfoByKeywords(
											sensorkeyword, username)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	/**
	 * ���ݹؼ�����������ȡ��Ӧ�Ĵ���������������
	 */
	public List<SensorTypeMarker> GetSensorKeyBelongSensorTypes(
			String username, String password, List<String> sensorkeywords)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<SensorTypeMarker> stms = new ArrayList<SensorTypeMarker>();
		for (String sensorkeyword : sensorkeywords) {
			try {
				stms
						.addAll(SensortypeRuleOperation
								.TranslateSensorTypeRuleToSensorTypeMarkers(SensortypeRuleOperation
										.getSensorTypeRuleInfoByKeywords(
												sensorkeyword, username)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return stms;
	}

	/**
	 * ��ȡָ�����͵Ĵ������Ĺؼ�������
	 */
	public SensorTypeMarker GetSensorTypeRule(String username, String password,
			String sensortype) throws ServiceException {
		System.out.println(username + " : " + password);
		UserInfoUtil.CheckUserLogin(username, password);
		System.out.println("2");
		try {
			SensorTypeRule str = SensortypeRuleOperation.GetSensorTypeRuleInfo(
					sensortype, username);
			if (str != null) {
				return SensortypeRuleOperation
						.TranslateSensorTypeRuleToSensorTypeMarker(str);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * �����ƶ��Ĵ��������͵�����
	 */
	public List<Boolean> SaveSensorTypeRuleEntries(String username,
			String password, List<SensorTypeMarkerClient> strs)
			throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		List<Boolean> stms = new ArrayList<Boolean>();
		for (SensorTypeMarkerClient stmc : strs) {
			stms.add(SaveSensorTypeRuleEntry(username, password, stmc));
		}
		return stms;
	}

	/**
	 * �����ƶ��Ĵ���������
	 */
	public Boolean SaveSensorTypeRuleEntry(String username, String password,
			SensorTypeMarkerClient str) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			session.save(SensortypeRuleOperation
					.TranslateSensorTypeMarkerClientToSensorTypeRule(str,
							username));
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ServiceException("�����ƶ��Ĵ����������й���Ϣ����!");
		}
	}

	public Boolean UpdateSensorTypeRule(String username, String password,
			SensorTypeMarkerClient str) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		try {
			DeleteSensorTypeRuleEntry(username, password, str.getSensortype());
			SaveSensorTypeRuleEntry(username, password, str);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("���¹����������!");
		}
	}

	public List<Boolean> UpdateSensorTypeRuleWithMultiEnties(String username,
			String password, List<SensorTypeMarkerClient> str)
			throws ServiceException {
		List<Boolean> bools = new ArrayList<Boolean>();
		for (SensorTypeMarkerClient smc : str) {
			bools.add(UpdateSensorTypeRule(username, password, smc));
		}
		return bools;
	}

	public Boolean IsExistOfSensorType(String username, String password,
			String sensortype) throws ServiceException {
		try {
			if (null != GetSensorTypeRule(username, password, sensortype)) {
				return true;
			} else {
				return false;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException("��������");
		}
	}
}
