package com.csw.Service.impls;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jws.WebService;
import org.hibernate.Session;
import com.csw.Service.interfaces.OperateSensorMLImageServiceInterface;
import com.csw.SystemParams.SystemConfig;
import com.csw.exceptions.ServiceException;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.StringUtil;
import com.csw.utils.FileUtil.AllFileNameOfDirUtil;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.SensorInfoUtil.SensorMLAttacheMentInfoOperation;
import com.csw.utils.Userutils.UserInfoUtil;
import com.csw.utils.custometypes.SensorAttachmentMarkerType;
import com.csw.utils.custometypes.SensorAttachmentType;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-6 上午09:20:45
 */
@WebService
public class OperateSensorMLImageService implements
		OperateSensorMLImageServiceInterface {

	/**
	 * 读取传感器图片路径,存在问题返回null，否则返回file,返回的文件路径是web的(测试成功)
	 */
	public List<String> ReadSensorAttchmentPathFile(String username,
			String password, String sensorid, String sensorattachmentmarker,
			boolean bol) throws ServiceException {
		// 验证用户名与密码
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		List<SensorAttachmentMarkerType> samts = SensorMLAttacheMentInfoOperation
				.ReadSensorAttachmentPathFileWithCheckIsExistInFileSystem(
						sensorid, sensorattachmentmarker, bol);
		if (samts != null && samts.size() != 0) {
			for (SensorAttachmentMarkerType samt : samts) {
				results.add(samt.getSensorattachementpath());
			}
		}
		return results;
	}

	/**
	 * 删除附件（首先是删除文件系统中的图片，之后就是删除数据库中的相关记录）这里的删除涉及到数据库记录和文件在本地系统中的文件(测试成功）
	 */
	public int DeleteSensorMLAttchmentByWebFilePathMehtod(String username,
			String password, String sensorid, String sensorwebfilepath)
			throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			// 删除本地文件，如果没有，则不删除
			FileOperationUtil.DeleteFile(FileOperationUtil
					.TranslateWebFilepathToLocalFilePath(sensorwebfilepath));
			if (SensorMLAttacheMentInfoOperation.DeleteSensorAttachementRecord(
					sensorid, sensorwebfilepath)) {
				return 1;
			} else {
				return 2;
			}
		} else {
			throw new ServiceException("用户名或密码错误!");
		}
	}

	/**
	 * 删除附件（首先是删除文件系统中的图片，之后就是删除数据库中的相关记录）这里的删除涉及到数据库记录和文件在本地系统中的文件(测试成功）
	 */
	public int DeleteSensorMLAttachmentMethod(String username, String password,
			String sensorid, String sensorattachmentmarker)
			throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			try {
				// 获取所有的附件web路径
				List<String> imgurls = new ArrayList<String>();
				List<SensorAttachmentMarkerType> tempAttachmentMarkerTypes = SensorMLAttacheMentInfoOperation
						.ReadSensorAttachmentPathFile(sensorid,
								sensorattachmentmarker);
				if (tempAttachmentMarkerTypes != null
						&& tempAttachmentMarkerTypes.size() != 0) {
					for (SensorAttachmentMarkerType smat : tempAttachmentMarkerTypes) {
						imgurls.add(smat.getSensorattachementpath());
					}
				}
				if (imgurls != null && imgurls.size() != 0) {
					for (String imgurl : imgurls) {
						// 删除文件
						FileOperationUtil.DeleteFile(FileOperationUtil
								.TranslateWebFilepathToLocalFilePath(imgurl));
						// 在删除在系统中的文件路径记录
						SensorMLAttacheMentInfoOperation
								.DeleteSensorAttachementRecord(sensorid, imgurl);
					}
					return 1;
				} else {
					return 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return 2;
			}
		} else {
			throw new ServiceException("用户名或密码错误!");
		}
	}

	/**
	 * 保存附件（在数据库中保存附件路径记录，在文件系统中保存传感器附件）(测试成功）
	 */
	public int saveSensorMLAttachmentMethod(String username, String password,
			String sensorid, SensorAttachmentType attchment,
			String attachemarker, String owner) throws ServiceException {
		if (owner == null || owner.trim().length() == 0) {
			throw new ServiceException("附件所属者owner参数不能为空，或设置错误");
		}
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			try {
				if (attchment.getTypename() == null
						&& attchment.getTypename().equals("")) {
					throw new ServiceException("附件后缀名必须指定");
				}
				// 生成文件的名称名称
				String onlyfilename = AllFileNameOfDirUtil
						.ReplaceFileNameWith_(sensorid)
						+ "." + attchment.getTypename();
				String webfilename = SystemConfig.sensorimagesaveurl
						+ onlyfilename;
				if (SensorMLAttacheMentInfoOperation.IsSensorAttachementExist(
						sensorid, webfilename, true)) {
					try {
						// 先删除相关文件
						DeleteSensorMLAttachmentMethod(username, password,
								sensorid, attachemarker);
						// 再保存相关文件
						saveSensorMLAttachmentMethod(username, password,
								sensorid, attchment, attachemarker, owner);
						return 1;
					} catch (Exception e) {
						e.printStackTrace();
						return 2;
					}

				} else {
					// 保存传感器附件
					if (SensorMLAttacheMentInfoOperation.SaveSensorAttchment(
							sensorid, attachemarker, attchment, owner)) {
						return 1;
					} else {
						return 2;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return 2;
			}
		} else {
			throw new ServiceException("用户名或密码错误");
		}
	}

	/**
	 * 监测指定的传感器和传感器附件的web路径是否存在(测试成功）
	 */
	public boolean IsExistSensorAttchmentBySensorWebFilePathMethod(
			String username, String password, String sensorid,
			String filewebpath, boolean bol) throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			return SensorMLAttacheMentInfoOperation.IsSensorAttachementExist(
					sensorid, filewebpath, bol);
		} else {
			throw new ServiceException("用户名或密码错误");
		}
	}

	/**
	 * 判断是否存在相关的附件（仅仅是针对在地图上显示）情况有三种，数据库表中没有对应的记录，而是没有相关的存储在特定区域的传感器图片（测试成功）
	 */
	public boolean IsExistSensorAttchmentMethod(String username,
			String password, String sensorid, String attchmentmarker,
			boolean bol) throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("参数sensorid输入错误，请核实!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			try {

				if (bol) {
					// 如果为true，则检测是否在文件系统中存在
					List<String> results = new ArrayList<String>();
					List<SensorAttachmentMarkerType> temp = SensorMLAttacheMentInfoOperation
							.ReadSensorAttachmentPathFileWithCheckIsExistInFileSystem(
									sensorid, attchmentmarker, bol);
					if (temp != null && temp.size() != 0) {
						for (SensorAttachmentMarkerType samt : temp) {
							results.add(samt.getSensorattachementpath());
						}
					}
					if (results != null && results.size() != 0) {
						return true;
					} else {
						return false;
					}
				} else {
					// 只检测是否在数据文件系统中存在
					List<String> results = new ArrayList<String>();
					List<SensorAttachmentMarkerType> temp = SensorMLAttacheMentInfoOperation
							.ReadSensorAttachmentPathFile(sensorid,
									attchmentmarker);
					if (temp != null && temp.size() != 0) {
						for (SensorAttachmentMarkerType samt : temp) {
							results.add(samt.getSensorattachementpath());
						}
					}
					if (results != null && results.size() != 0) {
						return true;
					} else {
						return false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			throw new ServiceException("用户名或密码错误!");
		}
	}

	/**
	 * 删除指定了附加说明和传感器标识符的传感器附件(测试成功）
	 */
	public List<SensorAttachmentMarkerType> DeleteSomeSensorsAttachmentsWithMarkers(
			String username, String password,
			List<SensorAttachmentMarkerType> sensors) throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			// 删除指定了附加说明和传感器标识符的传感器附件
			// 步骤：判定是否存在附件说明，如果不设置，则删除全部的附件（指定的传感器）
			if (sensors != null && sensors.size() != 0) {
				for (SensorAttachmentMarkerType samt : sensors) {
					if (samt.getSensorid() != null
							&& !samt.getSensorid().trim().equals("")) {
						// 这种情况下，进行删除指定的传感器的全部的附件
						DeleteSensorMLAttachmentMethod(username, password, samt
								.getSensorid(), samt
								.getSensorattachementmarker());
					}
				}
			}

			if (sensors == null || sensors.size() == 0) {
				// 如果没有，则看作是删除成功
				return sensors;
			} else {
				List<SensorAttachmentMarkerType> results = new ArrayList<SensorAttachmentMarkerType>();
				for (SensorAttachmentMarkerType samt : sensors) {
					// 获取传感器的url地址samt.getSensorattachementpath();如果不存在，url地址肯定也不存在
					results = SensorMLAttacheMentInfoOperation
							.ReadSensorAttachmentPathFileWithCheckIsExistInFileSystem(
									samt.getSensorid(), samt
											.getSensorattachementmarker(), true);
				}
				return results;
			}

		} else {
			throw new ServiceException("用户名或密码错误");
		}

	}

	/**
	 * 获取某集合类的全部传感器和满足相关附件标识要求的全部的附件的地址(测试成功）
	 */
	public List<SensorAttachmentMarkerType> GetSomeSensorAttachementPathsForSomeSensors(
			String username, String password,
			List<SensorAttachmentMarkerType> sensors, boolean bol)
			throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			List<SensorAttachmentMarkerType> results = new ArrayList<SensorAttachmentMarkerType>();
			if (sensors != null && sensors.size() != 0) {
				for (SensorAttachmentMarkerType samkt : sensors) {
					String sensorid = samkt.getSensorid();// 获取所需传感器标识
					String attchmentmarker = samkt.getSensorattachementmarker();// 获取所需传感器附件类型
					if (sensorid != null && sensorid.trim() != "") {
						// 输入的标识符正常
						if (bol) {
							results = SensorMLAttacheMentInfoOperation
									.ReadSensorAttachmentPathFileWithCheckIsExistInFileSystem(
											sensorid, attchmentmarker, true);
						} else {
							results = SensorMLAttacheMentInfoOperation
									.ReadSensorAttachmentPathFile(sensorid,
											attchmentmarker);
						}
					}
				}
			}
			return results;
		} else {
			throw new ServiceException("用户名或密码错误");
		}
	}

	// 保存传感器的附件（多个同时保存) 测试成功
	public List<SensorAttachmentMarkerType> SaveSomeSensorAttachementsWithMarkers(
			String username, String password,
			List<SensorAttachmentMarkerType> sensors, String owner)
			throws ServiceException {
		List<SensorAttachmentMarkerType> samkts = new ArrayList<SensorAttachmentMarkerType>();
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			if (sensors != null && sensors.size() != 0) {
				for (SensorAttachmentMarkerType smakt : sensors) {
					if (smakt.getSensorid() != null
							&& smakt.getImg().getTypename() != null
							&& smakt.getImg() != null) {
						if (SensorMLAttacheMentInfoOperation
								.SaveSensorAttchment(smakt.getSensorid(), smakt
										.getSensorattachementmarker(), smakt
										.getImg(), owner)) {
							SensorAttachmentMarkerType samkt = new SensorAttachmentMarkerType();
							samkt.setSensorid(smakt.getSensorid());
							samkt
									.setSensorattachementpath(SystemConfig.sensorimagesaveurl
											+ ""
											+ AllFileNameOfDirUtil
													.ReplaceFileNameWith_(smakt
															.getSensorid())
											+ "."
											+ smakt.getImg().getTypename());
							samkts.add(samkt);
						}
					}
				}
			}
			return samkts;
		} else {
			throw new ServiceException("用户名或密码错误");
		}
	}

	/**
	 * 测试使用(成功）
	 * 
	 * @param args
	 *            使用的参数
	 */
	public static void main(String[] args) throws Exception {
		// 测试保存
		OperateSensorMLImageService osms = new OperateSensorMLImageService();
		List<SensorAttachmentMarkerType> sensors = new ArrayList<SensorAttachmentMarkerType>();
		SensorAttachmentMarkerType samt = new SensorAttachmentMarkerType();
		samt.setSensorid("1111:11:11:1:11111");
		samt.setSensorattachementmarker("picture");
		SensorAttachmentType sAttachmentType = new SensorAttachmentType();
		sAttachmentType.setTypename("png");
		sAttachmentType.setImageData(new DataHandler(new FileDataSource(
				new File("E:\\sensorweb.jpg"))));
		samt.setImg(sAttachmentType);
		sensors.add(samt);
		osms.ReadSensorAttchmentPathFile("admin", "cswadmin",
				"1111:11:11:1:11111", "picture", true);
		// osms.saveSensorMLAttachmentMethod("admin", "cswadmin",
		// "1111:111:111:11112222:package", sAttachmentType, "picture",
		// "admin");
		// osms.SaveSomeSensorAttachementsWithMarkers("admin", "cswadmin",
		// sensors, "admin");

		// System.out.println("here");
		// List<SensorAttachmentMarkerType> resultSensorAttachmentMarkerTypes =
		// osms
		// .DeleteSomeSensorsAttachmentsWithMarkers("admin", "cswadmin",
		// sensors);
		//
		// System.out.println(osms.saveSensorMLAttachmentMethod("admin",
		// "cswadmin", "1111:11:11:11111", sAttachmentType, "picture",
		// "admin"));

		// System.out.println(osms.IsExistSensorAttchmentMethod("admin",
		// "cswadmin", "1111:11:11:11111", "picture", false));
		/*
		 * osms.DeleteSensorMLAttachmentMethod("admin", "cswadmin",
		 * "1111:11:11:1", "picture");
		 */
		// osms.CleanAllSensorAttachment("admin", "cswadmin", "admin");
	}

	/**
	 * 清除某一提供者的全部的传感器附件
	 */
	public boolean CleanAllSensorAttachment(String username, String password,
			String owner) throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("请输入用户名与密码!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			List<SensorAttachmentMarkerType> samts = new ArrayList<SensorAttachmentMarkerType>();
			samts = SensorMLAttacheMentInfoOperation
					.getAllSensorAttachmentTypesByOwner(null, owner);
			// 获取全部的文件，并删除
			if (null != samts && samts.size() != 0) {
				for (SensorAttachmentMarkerType smat : samts) {
					//System.out.println(smat.getSensorattachementpath());
					FileOperationUtil.DeleteFile(FileOperationUtil
							.TranslateWebFilepathToLocalFilePath(smat
									.getSensorattachementpath()));
				}
			}
			// 删除文件在数据库中的记录
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			if (owner != null && owner.trim().length() != 0) {
				session
						.createQuery(
								"delete from SensorMLImage where owner='"
										+ owner + "'").executeUpdate();
				session.beginTransaction().commit();
				GetSessionUtil.CloseSessionInstance(session);
				return true;
			} else {
				// 如果在数据库中不存在信息，在将打开的session关闭
				// 删除信息
				GetSessionUtil.CloseSessionInstance(session);
				throw new ServiceException("输入的owner参数错误!");
			}

		} else {
			throw new ServiceException("用户名或密码错误");
		}
	}

	/**
	 * 返回所有的包含了传感器附件图片的传感器的信息
	 */
	@SuppressWarnings("unchecked")
	public List<String> GetAllSensorIdsWithSensorImage(String attachementmarker) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		List<String> ssList = session.createQuery(
				" select sensorid from SensorMLImage where attchmentmarker='"
						+ attachementmarker + "'").list();
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		return ssList;
	}
}
