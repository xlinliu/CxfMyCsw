package com.csw.utils.SensorInfoUtil;

import org.hibernate.Session;
import com.csw.Service.impls.TransformSensorMLToebRIMService;
import com.csw.model.ebXMLModel.MyebRIMSmlContents;
import com.csw.utils.DateOperationUtil;
import com.csw.utils.GetSessionUtil;

public class SaveSensorMLAndEbRIMUtil {
	/**
	 * ��SensorML��EbRIMͬʱ���뵽���ݿ���
	 * 
	 * @param username
	 *            �û�����
	 * @param sensorml
	 *            �����sensorml�ĵ����� ��
	 * @param filename
	 *            ���²����filename���ļ�����
	 * @return 1:�ɹ�����;0:����ʧ�ܡ�
	 */
	@SuppressWarnings("null")
	public int SaveDBmyebrimsmlcontentsMethod1(String username,
			String password, String sensorml, String filename, String createtime) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			// ��һ������sensorml����Ӧ��ebRIM���ĵ�����
			String ebrimcontent = new TransformSensorMLToebRIMService()
					.TransactionSensorMLToeEbRIMMethod(username, password,
							sensorml);
			// �ڶ����������ɵ�ebRIM���ĵ����ݻ�ȡ��ص�ebRIM��idֵ
			String ebrimid = GetRegistryRegistryInfoUtils
					.GetRegistryPackageIDByEbrimContent(ebrimcontent);
			// ��ebrim��id��sensorml��ebrim�������ϴ�sensorml���û����뵽���ݿ��н��б���

			// ���Ĳ�����������װ��һ��ʵ���࣬���б���
			MyebRIMSmlContents myec = new MyebRIMSmlContents();
			myec.setId(ebrimid.trim());
			myec.setEbrimContent(ebrimcontent);
			myec.setSensormlContent(sensorml);
			if (createtime != null && createtime.trim().equals("")) {
				myec.setDateTime(createtime);
			} else {
				myec.setDateTime(DateOperationUtil.getSystemTime());
			}
			myec.setOwer(username);
			if (filename == null && filename.trim().length() == 0) {
				// ��ȡ�����ڶ�����string��Ϊsenosorml��Idֵ����������׺��
				filename = ebrimid.split(":")[ebrimid.split(":").length - 2];
			}
			myec.setFilename(filename);
			session.beginTransaction().begin();
			session.save(myec);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			GetSessionUtil.CloseSessionInstance(session);
			return 2;
		}
	}

	/**
	 * ��SensorML��EbRIMͬʱ���뵽���ݿ���
	 * 
	 * @param username
	 *            �û�����
	 * @param sensorml
	 *            �����sensorml�ĵ�����
	 * @param ebrim
	 *            �����ebrim���ĵ�������
	 * @param ebrimid
	 *            �����ebrim���ĵ���idֵ,������ebrim��id������ebrim��Ҳ����ʹsensorml��id
	 * @param saveebrimIsNot
	 *            �Ƿ񱣴� ebrimcontent���ĵ�,trueΪ���棬false������
	 * @return 1:�ɹ�����;0:����ʧ�ܡ�
	 */
	@SuppressWarnings("null")
	public static int SaveDBmyebrimsmlcontentsMethod(String username,
			String password, String sensorml, String ebrimcontent,
			String ebrimid, String filename, String createtime,
			boolean saveebrimIsNot) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			MyebRIMSmlContents myec = new MyebRIMSmlContents();
			ebrimid = ebrimid.trim();
			if (!ebrimid.endsWith(":package")) {
				ebrimid += ":package";
			}
			myec.setId(ebrimid.trim());
			if (saveebrimIsNot) {
				myec.setEbrimContent(ebrimcontent);
			}
			myec.setSensormlContent(sensorml);
			myec.setOwer(username);
			if (filename == null && filename.trim().equals("")) {
				// ��ȡ�����ڶ�����string��ΪsensorML��Idֵ����������׺��
				if (ebrimid != null && !ebrimid.trim().equals("")) {
					filename = ebrimid.split(":")[ebrimid.split(":").length - 2];
				}
			} else if (filename != null || !filename.trim().equals("")) {
				if (filename.endsWith("xml")) {
					filename.substring(0, filename.length() - ".xml".length());
				}
			}
			myec.setFilename(filename);
			if (createtime != null && !createtime.trim().equals("")) {
				myec.setDateTime(createtime.trim());
			} else {
				myec.setDateTime(DateOperationUtil.getSystemTime());
			}
			session.beginTransaction().begin();
			session.save(myec);
			session.beginTransaction().commit();
			GetSessionUtil.CloseSessionInstance(session);
			return 1;
		} catch (Exception e) {
			GetSessionUtil.CloseSessionInstance(session);
			e.printStackTrace();
			return 2;
		}
	}
}
