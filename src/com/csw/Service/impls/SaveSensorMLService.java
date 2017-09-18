package com.csw.Service.impls;

import java.util.Date;
import javax.jws.WebService;
import com.csw.Service.interfaces.SaveSensorMLServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.Userutils.UserInfoUtil;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-7-26 下午09:58:30
 */
@WebService
public class SaveSensorMLService implements SaveSensorMLServiceInterface {
	/**
	 * 保存相关的sensorml文档
	 */
	public boolean SaveSensorML(String username, String password,
			String sensorid, String sensormlcontent) throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil
				.checkStringIsNotNULLAndEmptyMethod(sensormlcontent)) {
			throw new ServiceException("参数content输入错误，请核实!");
		}
		// 首先删除用户
		if (IsExistsSensorML(username, password, sensorid)) {
			OperateSensorUtil.DeleteSensorML(username, password, sensorid);
		}
		boolean bol = OperateSensorUtil.SaveSensorML(username, password,
				sensorid, sensormlcontent);
		if (!bol) {
			throw new ServiceException("无法sensorml问题 !");
		} else {
			return bol;
		}
	}

	public static void main1(String[] args) throws Exception {
		SaveSensorMLService ssMlService = new SaveSensorMLService();
		ssMlService.DeleteSensorML("admin", "cswadmin",
				"urn:liesmars:object:feature:武昌堤东街:现场:风速风向传感器:MWW-S:package");
	}

	/**
	 * 读取信息
	 * 
	 * @param username
	 * @param password
	 * @param sensorid
	 * @return
	 * @throws ServiceException
	 */
	public String ReadSensorContent(String username, String password,
			String sensorid) throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		sensorid = StringUtil.AppendPacakgeStr(sensorid);
		String content = OperateSensorUtil.ReadSensorML(sensorid);
		if (content == null) {
			throw new ServiceException("读取sensorml文档内容异常");
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
	 * 获取指定的传感器的sensorml的存储时间
	 * 
	 * @param username
	 *            注册中心的名称
	 * @param password
	 *            注册中心的密码
	 * @param sensorid
	 *            注册的传感器的标识符
	 * @return 返回最新的时间
	 */
	public Date GetSensorMLSaveTime(String username, String password,
			String sensorid) throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		return OperateSensorUtil.GetSensorMLSavedTime(sensorid);
	}

	public boolean IsExistsSensorML(String username, String password,
			String sensorid) throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		return OperateSensorUtil.IsExistsSensorML(username, sensorid);
	}

	public boolean DeleteSensorML(String username, String password,
			String sensorid) throws ServiceException {
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		return OperateSensorUtil.DeleteSensorML(username, password, sensorid);
	}
}
