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
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-9-6 ����09:20:45
 */
@WebService
public class OperateSensorMLImageService implements
		OperateSensorMLImageServiceInterface {

	/**
	 * ��ȡ������ͼƬ·��,�������ⷵ��null�����򷵻�file,���ص��ļ�·����web��(���Գɹ�)
	 */
	public List<String> ReadSensorAttchmentPathFile(String username,
			String password, String sensorid, String sensorattachmentmarker,
			boolean bol) throws ServiceException {
		// ��֤�û���������
		UserInfoUtil.CheckUserLogin(username, password);
		List<String> results = new ArrayList<String>();
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(sensorid)) {
			throw new ServiceException("����sensorid����������ʵ!");
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
	 * ɾ��������������ɾ���ļ�ϵͳ�е�ͼƬ��֮�����ɾ�����ݿ��е���ؼ�¼�������ɾ���漰�����ݿ��¼���ļ��ڱ���ϵͳ�е��ļ�(���Գɹ���
	 */
	public int DeleteSensorMLAttchmentByWebFilePathMehtod(String username,
			String password, String sensorid, String sensorwebfilepath)
			throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
		}
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("����sensorid����������ʵ!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			// ɾ�������ļ������û�У���ɾ��
			FileOperationUtil.DeleteFile(FileOperationUtil
					.TranslateWebFilepathToLocalFilePath(sensorwebfilepath));
			if (SensorMLAttacheMentInfoOperation.DeleteSensorAttachementRecord(
					sensorid, sensorwebfilepath)) {
				return 1;
			} else {
				return 2;
			}
		} else {
			throw new ServiceException("�û������������!");
		}
	}

	/**
	 * ɾ��������������ɾ���ļ�ϵͳ�е�ͼƬ��֮�����ɾ�����ݿ��е���ؼ�¼�������ɾ���漰�����ݿ��¼���ļ��ڱ���ϵͳ�е��ļ�(���Գɹ���
	 */
	public int DeleteSensorMLAttachmentMethod(String username, String password,
			String sensorid, String sensorattachmentmarker)
			throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
		}
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("����sensorid����������ʵ!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			try {
				// ��ȡ���еĸ���web·��
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
						// ɾ���ļ�
						FileOperationUtil.DeleteFile(FileOperationUtil
								.TranslateWebFilepathToLocalFilePath(imgurl));
						// ��ɾ����ϵͳ�е��ļ�·����¼
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
			throw new ServiceException("�û������������!");
		}
	}

	/**
	 * ���渽���������ݿ��б��渽��·����¼�����ļ�ϵͳ�б��洫����������(���Գɹ���
	 */
	public int saveSensorMLAttachmentMethod(String username, String password,
			String sensorid, SensorAttachmentType attchment,
			String attachemarker, String owner) throws ServiceException {
		if (owner == null || owner.trim().length() == 0) {
			throw new ServiceException("����������owner��������Ϊ�գ������ô���");
		}
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			try {
				if (attchment.getTypename() == null
						&& attchment.getTypename().equals("")) {
					throw new ServiceException("������׺������ָ��");
				}
				// �����ļ�����������
				String onlyfilename = AllFileNameOfDirUtil
						.ReplaceFileNameWith_(sensorid)
						+ "." + attchment.getTypename();
				String webfilename = SystemConfig.sensorimagesaveurl
						+ onlyfilename;
				if (SensorMLAttacheMentInfoOperation.IsSensorAttachementExist(
						sensorid, webfilename, true)) {
					try {
						// ��ɾ������ļ�
						DeleteSensorMLAttachmentMethod(username, password,
								sensorid, attachemarker);
						// �ٱ�������ļ�
						saveSensorMLAttachmentMethod(username, password,
								sensorid, attchment, attachemarker, owner);
						return 1;
					} catch (Exception e) {
						e.printStackTrace();
						return 2;
					}

				} else {
					// ���洫��������
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
			throw new ServiceException("�û������������");
		}
	}

	/**
	 * ���ָ���Ĵ������ʹ�����������web·���Ƿ����(���Գɹ���
	 */
	public boolean IsExistSensorAttchmentBySensorWebFilePathMethod(
			String username, String password, String sensorid,
			String filewebpath, boolean bol) throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
		}
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("����sensorid����������ʵ!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			return SensorMLAttacheMentInfoOperation.IsSensorAttachementExist(
					sensorid, filewebpath, bol);
		} else {
			throw new ServiceException("�û������������");
		}
	}

	/**
	 * �ж��Ƿ������صĸ���������������ڵ�ͼ����ʾ����������֣����ݿ����û�ж�Ӧ�ļ�¼������û����صĴ洢���ض�����Ĵ�����ͼƬ�����Գɹ���
	 */
	public boolean IsExistSensorAttchmentMethod(String username,
			String password, String sensorid, String attchmentmarker,
			boolean bol) throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
		}
		if (sensorid == null || sensorid.trim().equals("")) {
			throw new ServiceException("����sensorid����������ʵ!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			try {

				if (bol) {
					// ���Ϊtrue�������Ƿ����ļ�ϵͳ�д���
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
					// ֻ����Ƿ��������ļ�ϵͳ�д���
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
			throw new ServiceException("�û������������!");
		}
	}

	/**
	 * ɾ��ָ���˸���˵���ʹ�������ʶ���Ĵ���������(���Գɹ���
	 */
	public List<SensorAttachmentMarkerType> DeleteSomeSensorsAttachmentsWithMarkers(
			String username, String password,
			List<SensorAttachmentMarkerType> sensors) throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			// ɾ��ָ���˸���˵���ʹ�������ʶ���Ĵ���������
			// ���裺�ж��Ƿ���ڸ���˵������������ã���ɾ��ȫ���ĸ�����ָ���Ĵ�������
			if (sensors != null && sensors.size() != 0) {
				for (SensorAttachmentMarkerType samt : sensors) {
					if (samt.getSensorid() != null
							&& !samt.getSensorid().trim().equals("")) {
						// ��������£�����ɾ��ָ���Ĵ�������ȫ���ĸ���
						DeleteSensorMLAttachmentMethod(username, password, samt
								.getSensorid(), samt
								.getSensorattachementmarker());
					}
				}
			}

			if (sensors == null || sensors.size() == 0) {
				// ���û�У�������ɾ���ɹ�
				return sensors;
			} else {
				List<SensorAttachmentMarkerType> results = new ArrayList<SensorAttachmentMarkerType>();
				for (SensorAttachmentMarkerType samt : sensors) {
					// ��ȡ��������url��ַsamt.getSensorattachementpath();��������ڣ�url��ַ�϶�Ҳ������
					results = SensorMLAttacheMentInfoOperation
							.ReadSensorAttachmentPathFileWithCheckIsExistInFileSystem(
									samt.getSensorid(), samt
											.getSensorattachementmarker(), true);
				}
				return results;
			}

		} else {
			throw new ServiceException("�û������������");
		}

	}

	/**
	 * ��ȡĳ�������ȫ����������������ظ�����ʶҪ���ȫ���ĸ����ĵ�ַ(���Գɹ���
	 */
	public List<SensorAttachmentMarkerType> GetSomeSensorAttachementPathsForSomeSensors(
			String username, String password,
			List<SensorAttachmentMarkerType> sensors, boolean bol)
			throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			List<SensorAttachmentMarkerType> results = new ArrayList<SensorAttachmentMarkerType>();
			if (sensors != null && sensors.size() != 0) {
				for (SensorAttachmentMarkerType samkt : sensors) {
					String sensorid = samkt.getSensorid();// ��ȡ���贫������ʶ
					String attchmentmarker = samkt.getSensorattachementmarker();// ��ȡ���贫������������
					if (sensorid != null && sensorid.trim() != "") {
						// ����ı�ʶ������
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
			throw new ServiceException("�û������������");
		}
	}

	// ���洫�����ĸ��������ͬʱ����) ���Գɹ�
	public List<SensorAttachmentMarkerType> SaveSomeSensorAttachementsWithMarkers(
			String username, String password,
			List<SensorAttachmentMarkerType> sensors, String owner)
			throws ServiceException {
		List<SensorAttachmentMarkerType> samkts = new ArrayList<SensorAttachmentMarkerType>();
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
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
			throw new ServiceException("�û������������");
		}
	}

	/**
	 * ����ʹ��(�ɹ���
	 * 
	 * @param args
	 *            ʹ�õĲ���
	 */
	public static void main(String[] args) throws Exception {
		// ���Ա���
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
	 * ���ĳһ�ṩ�ߵ�ȫ���Ĵ���������
	 */
	public boolean CleanAllSensorAttachment(String username, String password,
			String owner) throws ServiceException {
		if (username == null || password == null || username.trim().equals("")
				|| password.trim().equals("")) {
			throw new ServiceException("�������û���������!");
		}
		int num = UserInfoUtil.CheckUserInfo(username, password);
		if (num == 1) {
			List<SensorAttachmentMarkerType> samts = new ArrayList<SensorAttachmentMarkerType>();
			samts = SensorMLAttacheMentInfoOperation
					.getAllSensorAttachmentTypesByOwner(null, owner);
			// ��ȡȫ�����ļ�����ɾ��
			if (null != samts && samts.size() != 0) {
				for (SensorAttachmentMarkerType smat : samts) {
					//System.out.println(smat.getSensorattachementpath());
					FileOperationUtil.DeleteFile(FileOperationUtil
							.TranslateWebFilepathToLocalFilePath(smat
									.getSensorattachementpath()));
				}
			}
			// ɾ���ļ������ݿ��еļ�¼
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
				// ��������ݿ��в�������Ϣ���ڽ��򿪵�session�ر�
				// ɾ����Ϣ
				GetSessionUtil.CloseSessionInstance(session);
				throw new ServiceException("�����owner��������!");
			}

		} else {
			throw new ServiceException("�û������������");
		}
	}

	/**
	 * �������еİ����˴���������ͼƬ�Ĵ���������Ϣ
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
