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
 *项目名称:CxfMyCsw 类描述：用于管理传感器的基本的信息 创建人:Administrator 创建时间: 2013-9-16 下午10:30:40
 */
public class OperateSensorTypeRuleOperation implements
		OperateSensorTypeRuleInterface {
	public static void main(String[] args) throws Exception {
		OperateSensorTypeRuleOperation ostro = new OperateSensorTypeRuleOperation();
		// 保存规则
		SensorTypeMarkerClient stmc = new SensorTypeMarkerClient();
		stmc.setSensortype("instuplatform");
		ArrayList<String> sensorkeyword = new ArrayList<String>();
		sensorkeyword.add("原位传感器框架");
		sensorkeyword.add("原位平台");
		// stmc.setSensorkeyword(sensorkeyword);
		stmc.setSensorchinesetype("原位平台站点");
		ostro.SaveSensorTypeRuleEntry("admin", "cswadmin", stmc);

		// 删除规则
		// List<String> sensortypes = new ArrayList<String>();
		// sensortypes.add("  ");
		// sensortypes.add(null);
		// sensortypes.add("remotesensor2");
		// ostro.DeleteSensorTypeRuleEntries("admin", "cswadmin", sensortypes);

		// 更新规则
		// SensorTypeRule str = new SensorTypeRule();
		// str.setId(3);
		// str.setSensortypename("instuplatform");
		// str.setSensortypechinesename("原位平台站点");
		// str.setSensorkeywords("原位平台,原位传感器框架");
		// str.setMaker("admin");
		// SensorTypeRule str1 = new SensorTypeRule();
		// str1.setId(4);
		// str1.setSensortypename("yxlian4");
		// str1.setSensortypechinesename("杨训亮4");
		// str1.setSensorkeywords("武汉大学,严晓玲1");
		// str1.setMaker("admin");
		// List<SensorTypeRule> strs = new ArrayList<SensorTypeRule>();
		// strs.add(str);
		// strs.add(str1);
		// ostro.UpdateSensorTypeRuleWithMultiEnties("admin", "cswadmin", strs);

		// 读取
		// List<String> senosList = new ArrayList<String>();
		// senosList.add("yxlian");
		// senosList.add("ddd");
		// System.out.println(ostro.GetSensorTypeRule("admin", "cswadmin",
		// "yxlian").getSensorkeyword().toString());
		// System.out.println(ostro.GetAllSensorTypeRule("admin", "cswadmin",
		// senosList).get(0).getSensorkeyword().toString());

		// 判断是否存在
		// System.out.println(ostro.IsExistOfSensorType("admin", "cswadmin",
		// "yxliang"));
		// for (SensorTypeMarker str :
		// ostro.GetSensorKeyBelongSensorType("admin",
		// "cswadmin", "严晓玲")) {
		// System.out.println(str.getSensortype());
		// }
		// 删除全部传感器
		// SensortypeRuleOperation.DeleteAllSensorTypeRules("admin");
		System.out.println("here");
	}

	/**
	 * 删除指定的传感器类型的序列
	 */
	public List<SensorTypeMarker> DeleteSensorTypeRuleEntries(String username,
			String password, List<String> sensortypes) throws ServiceException {
		List<SensorTypeMarker> stms = new ArrayList<SensorTypeMarker>();
		UserInfoUtil.CheckUserLogin(username, password);
		// 删除指定的传感器类型的循环删除
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
					throw new ServiceException("输入的传感器类型名称错误，无法读取!");
				}
			}
			stms.add(stmMarker);
		}
		return stms;
	}

	/**
	 * 删除指定的传感器类型
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
				throw new ServiceException("输入的传感器类型名称错误，无法读取!");
			}
		}
		return stmMarker;
	}

	/**
	 * 删除所有的传感器类型规则
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
	 * 根据用户自定义的传感器的类型来查询传感器类型中包含的关键字序列
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
					throw new ServiceException("获取指定的传感器类型规则信息出现问题!");
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
	 * 根据关键字来获取所对应的传感器的类型
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
	 * 根据关键字序列来获取对应的传感器的类型序列
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
	 * 获取指定类型的传感器的关键字序列
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
	 * 保存制定的传感器类型的序列
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
	 * 保存制定的传感器类型
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
			throw new ServiceException("保存制定的传感器类型有关信息错误!");
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
			throw new ServiceException("更新规则出现问题!");
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
			throw new ServiceException("出现问题");
		}
	}
}
