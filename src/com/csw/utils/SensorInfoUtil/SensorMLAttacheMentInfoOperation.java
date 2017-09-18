package com.csw.utils.SensorInfoUtil;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

import com.csw.SystemParams.SystemConfig;
import com.csw.model.ebXMLModel.SensorMLImage;
import com.csw.utils.GetSessionUtil;
import com.csw.utils.FileUtil.AllFileNameOfDirUtil;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.utils.custometypes.SensorAttachmentMarkerType;
import com.csw.utils.custometypes.SensorAttachmentType;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-9 下午01:50:38
 */
public class SensorMLAttacheMentInfoOperation {
	/**
	 * 获取传感附件的路径名称(测试成功）
	 * 
	 * @param sensorid
	 *            读取某传感器的附件
	 * @param attachmentmarker
	 *            读取某一特征表示的附件可设置为空，返回指定传感器的全部的附件）
	 * 
	 * @return 返回符合传感器标识符和特征表示的附件地址（web），并不验证是否在文件系统中存在
	 */
	@SuppressWarnings("unchecked")
	public static List<SensorAttachmentMarkerType> ReadSensorAttachmentPathFile(
			String sensorid, String attachmentmarker) {
		List<SensorAttachmentMarkerType> samts = new ArrayList<SensorAttachmentMarkerType>();
		try {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List<SensorMLImage> imgurls = new ArrayList<SensorMLImage>();
			if (attachmentmarker == null || attachmentmarker.trim().equals("")) {
				imgurls = (List<SensorMLImage>) session.createQuery(
						"from SensorMLImage where sensorid='" + sensorid.trim()
								+ "'").list();
			} else {
				imgurls = (List<SensorMLImage>) session.createQuery(
						"from SensorMLImage where sensorid='" + sensorid.trim()
								+ "' and attchmentmarker ='" + attachmentmarker
								+ "'").list();
			}
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (imgurls != null && imgurls.size() != 0) {
				for (SensorMLImage smi : imgurls) {
					SensorAttachmentMarkerType samt = new SensorAttachmentMarkerType();
					samt.setSensorattachementmarker(smi.getAttchmentmarker());
					samt.setSensorattachementpath(smi.getSensorimgurl());
					samt.setSensorid(smi.getSensorid());
					samt.setSensormlsaveDate(smi.getSavetime());
					samts.add(samt);
				}
			}
			return samts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取传感附件的路径名称(测试成功）需要验证是否在文件系统中存在，并且会删除没有存在的文件记录
	 * 
	 * @param sensorid
	 *            读取某传感器的附件
	 * @param attachmentmarker
	 *            读取某一特征表示的附件
	 * @param bol
	 *            是否删除数据库中记录的不存在的文件路径记录true，删除，false 不删除
	 * 
	 * @return 返回符合传感器标识符和特征表示的附件地址（web），同时验证是否在文件系统中存在，如果不存在，删除指定的在数据库中相关的记录
	 */
	public static List<SensorAttachmentMarkerType> ReadSensorAttachmentPathFileWithCheckIsExistInFileSystem(
			String sensorid, String attachmentmarker, boolean bol) {
		List<SensorAttachmentMarkerType> results = new ArrayList<SensorAttachmentMarkerType>();
		List<SensorAttachmentMarkerType> samts = ReadSensorAttachmentPathFile(
				sensorid, attachmentmarker);
		if (samts == null || samts.size() == 0) {
			return null;
		} else {
			for (SensorAttachmentMarkerType samt : samts) {
				if (FileOperationUtil.IsExistOfFile(FileOperationUtil
						.TranslateWebFilepathToLocalFilePath(samt
								.getSensorattachementpath()))) {
					results.add(samt);
				} else {
					// 删除相关记录
					if (bol) {
						DeleteSensorAttachementRecord(sensorid, samt
								.getSensorattachementpath());
					}
				}
			}
			return results;
		}
	}

	/**
	 * 删除指定传感器中指定的文件路径中的传感器的记录(测试成功）具有唯一性，<h1>这是删除在数据库中的记录</h1>，并不涉及到本地文件是否存在
	 * 
	 * @param sensorid
	 *            需要删除附件的传感器的id
	 * @param attachmentpath
	 *            是web路径，非本地路径
	 * @return 返回是否存在
	 */
	public static boolean DeleteSensorAttachementRecord(String sensorid,
			String attachmentpath) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		session.beginTransaction().begin();
		if (attachmentpath != null && !attachmentpath.trim().equals("")) {
			session.createQuery(
					"delete from SensorMLImage where sensorid='"
							+ sensorid.trim() + "' and sensorimgurl='"
							+ attachmentpath + "'").executeUpdate();
		}
		session.beginTransaction().commit();
		GetSessionUtil.CloseSessionInstance(session);
		if (ReadSensorAttachmentPathFile(sensorid, null).contains(
				attachmentpath)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 用户监测是否在数据库中记录是否存在相关附件，或同时检测是否在文件系统中存在
	 * 
	 * @param sensorid
	 *            传感器的标识符
	 * @param attchementwebpath
	 *            传感器中的附件路径（web路径）
	 * @param bol
	 *            是否检测在文件系统中存在
	 * @return 返回指定的传感器的路径是存在 true 存在，false 不存在
	 */
	public static boolean IsSensorAttachementExist(String sensorid,
			String attchementwebpath, boolean bol) {
		if (bol) {
			List<SensorAttachmentMarkerType> result = ReadSensorAttachmentPathFileWithCheckIsExistInFileSystem(
					sensorid, null, true);
			if (result != null) {
				for (SensorAttachmentMarkerType str : result) {
					if (str.getSensorattachementpath()
							.equals(attchementwebpath)) {
						return true;
					}
				}
				return false;
			} else {
				return false;
			}
		} else {
			List<SensorAttachmentMarkerType> result = ReadSensorAttachmentPathFile(
					sensorid, null);
			if (result != null) {
				for (SensorAttachmentMarkerType str : result) {
					if (str.getSensorattachementpath()
							.equals(attchementwebpath)) {
						return true;
					}
				}
				return false;
			} else {
				return false;
			}
		}
	}

	/**
	 * 保存传感器的附件
	 * 
	 * @param sensorid
	 *            传感器的标识符
	 * @param attchmentwebpath
	 *            传感器的附件（网络访问地址）
	 * @param localfilepath
	 *            创案件的附件地址（本地）
	 * @param attchmentmarker
	 *            （附件标识）
	 * @param attchment
	 *            传感器附件
	 * @param owner
	 *            传感器附件上传者
	 * @return 返回是否保存成功
	 */
	public static boolean SaveSensorAttchment(String sensorid,
			String attchmentmarker, SensorAttachmentType attchment, String owner) {
		try {
			// 生成文件的名称名称
			String onlyfilename = AllFileNameOfDirUtil
					.ReplaceFileNameWith_(sensorid)
					+ "_"
					+ (int) (Math.random() * 1000)
					+ "."
					+ attchment.getTypename();

			String savefilename = SystemConfig.sensorsavebasepath
					+ onlyfilename;// 保存的图片的位置
			String webfilename = SystemConfig.sensorimagesaveurl + onlyfilename;
			System.out.println(sensorid + " : " + webfilename);
			// 如果在数据库存在相关的记录则删除相关记录
			if (IsSensorAttachementExist(sensorid, webfilename, true)) {
				DeleteSensorAttachementRecord(sensorid, webfilename);
			}
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			SensorMLImage smi = new SensorMLImage();
			smi.setSensorid(sensorid);
			smi.setSensorimgurl(webfilename);
			smi.setOwner(owner);
			smi.setSavetime(new Date());// 设置当前的时间
			smi.setAttchmentmarker(attchmentmarker);// 设置该附件所使用的标注，例如是图片，规格说明，应用说明，异常说明等。
			session.save(smi);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			FileOutputStream fos;
			InputStream is = attchment.getImageData().getInputStream();
			fos = new FileOutputStream(savefilename);
			byte[] bytes = new byte[1024];
			int len = 0;
			while ((len = is.read(bytes)) != -1) {
				fos.write(bytes, 0, len);
			}
			fos.flush();
			fos.close();
			is.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 获取所有者指定的sensorid全部的附件的信息
	 * 
	 * @param owner
	 *            指定的所有者
	 * @param sensorid
	 *            指定的创安琪的全部的附件
	 * @return 返回所有的附件的基本信息
	 */
	@SuppressWarnings("unchecked")
	public static List<SensorAttachmentMarkerType> getAllSensorAttachmentTypesByOwner(
			String sensorid, String owner) {
		List<SensorAttachmentMarkerType> sats = new ArrayList<SensorAttachmentMarkerType>();
		if (owner != null && owner.trim().length() != 0) {
			Session session = GetSessionUtil
					.GetSessionInstance(GetSessionUtil.SENSORTYPE);
			session.beginTransaction().begin();
			List<SensorMLImage> smis = (List<SensorMLImage>) session
					.createQuery(
							"from SensorMLImage where owner='" + owner + "'")
					.list();
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			if (sensorid != null && !sensorid.trim().equals("")) {
				if (smis != null && smis.size() != 0) {
					List<SensorMLImage> tempImages = new ArrayList<SensorMLImage>();
					for (SensorMLImage smi : smis) {
						if (smi.getSensorid().equals(sensorid)) {
							tempImages.add(smi);
						}
					}
					smis.removeAll(tempImages);
				}
			}
			if (smis != null && !smis.isEmpty()) {
				for (SensorMLImage smi : smis) {
					SensorAttachmentMarkerType sat = new SensorAttachmentMarkerType();
					sat.setSensorid(sensorid);
					sat.setSensorattachementmarker(smi.getAttchmentmarker());
					sat.setSensorattachementpath(smi.getSensorimgurl());
					sats.add(sat);
				}
				return sats;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	public static void main(String[] args) {
		// boolean bol = DeleteSensorAttachementRecord("11",
		// "http:\\202.114.118.197:8080/CxfMyCsw/files/ddd.jpg");
	}
}
