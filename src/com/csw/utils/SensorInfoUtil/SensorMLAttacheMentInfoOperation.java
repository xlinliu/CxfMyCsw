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
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-9-9 ����01:50:38
 */
public class SensorMLAttacheMentInfoOperation {
	/**
	 * ��ȡ���и�����·������(���Գɹ���
	 * 
	 * @param sensorid
	 *            ��ȡĳ�������ĸ���
	 * @param attachmentmarker
	 *            ��ȡĳһ������ʾ�ĸ���������Ϊ�գ�����ָ����������ȫ���ĸ�����
	 * 
	 * @return ���ط��ϴ�������ʶ����������ʾ�ĸ�����ַ��web����������֤�Ƿ����ļ�ϵͳ�д���
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
	 * ��ȡ���и�����·������(���Գɹ�����Ҫ��֤�Ƿ����ļ�ϵͳ�д��ڣ����һ�ɾ��û�д��ڵ��ļ���¼
	 * 
	 * @param sensorid
	 *            ��ȡĳ�������ĸ���
	 * @param attachmentmarker
	 *            ��ȡĳһ������ʾ�ĸ���
	 * @param bol
	 *            �Ƿ�ɾ�����ݿ��м�¼�Ĳ����ڵ��ļ�·����¼true��ɾ����false ��ɾ��
	 * 
	 * @return ���ط��ϴ�������ʶ����������ʾ�ĸ�����ַ��web����ͬʱ��֤�Ƿ����ļ�ϵͳ�д��ڣ���������ڣ�ɾ��ָ���������ݿ�����صļ�¼
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
					// ɾ����ؼ�¼
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
	 * ɾ��ָ����������ָ�����ļ�·���еĴ������ļ�¼(���Գɹ�������Ψһ�ԣ�<h1>����ɾ�������ݿ��еļ�¼</h1>�������漰�������ļ��Ƿ����
	 * 
	 * @param sensorid
	 *            ��Ҫɾ�������Ĵ�������id
	 * @param attachmentpath
	 *            ��web·�����Ǳ���·��
	 * @return �����Ƿ����
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
	 * �û�����Ƿ������ݿ��м�¼�Ƿ������ظ�������ͬʱ����Ƿ����ļ�ϵͳ�д���
	 * 
	 * @param sensorid
	 *            �������ı�ʶ��
	 * @param attchementwebpath
	 *            �������еĸ���·����web·����
	 * @param bol
	 *            �Ƿ������ļ�ϵͳ�д���
	 * @return ����ָ���Ĵ�������·���Ǵ��� true ���ڣ�false ������
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
	 * ���洫�����ĸ���
	 * 
	 * @param sensorid
	 *            �������ı�ʶ��
	 * @param attchmentwebpath
	 *            �������ĸ�����������ʵ�ַ��
	 * @param localfilepath
	 *            �������ĸ�����ַ�����أ�
	 * @param attchmentmarker
	 *            ��������ʶ��
	 * @param attchment
	 *            ����������
	 * @param owner
	 *            �����������ϴ���
	 * @return �����Ƿ񱣴�ɹ�
	 */
	public static boolean SaveSensorAttchment(String sensorid,
			String attchmentmarker, SensorAttachmentType attchment, String owner) {
		try {
			// �����ļ�����������
			String onlyfilename = AllFileNameOfDirUtil
					.ReplaceFileNameWith_(sensorid)
					+ "_"
					+ (int) (Math.random() * 1000)
					+ "."
					+ attchment.getTypename();

			String savefilename = SystemConfig.sensorsavebasepath
					+ onlyfilename;// �����ͼƬ��λ��
			String webfilename = SystemConfig.sensorimagesaveurl + onlyfilename;
			System.out.println(sensorid + " : " + webfilename);
			// ��������ݿ������صļ�¼��ɾ����ؼ�¼
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
			smi.setSavetime(new Date());// ���õ�ǰ��ʱ��
			smi.setAttchmentmarker(attchmentmarker);// ���øø�����ʹ�õı�ע��������ͼƬ�����˵����Ӧ��˵�����쳣˵���ȡ�
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
	 * ��ȡ������ָ����sensoridȫ���ĸ�������Ϣ
	 * 
	 * @param owner
	 *            ָ����������
	 * @param sensorid
	 *            ָ���Ĵ�������ȫ���ĸ���
	 * @return �������еĸ����Ļ�����Ϣ
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
