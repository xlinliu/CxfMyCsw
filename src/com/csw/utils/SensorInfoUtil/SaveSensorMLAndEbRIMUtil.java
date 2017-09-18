package com.csw.utils.SensorInfoUtil;

import org.hibernate.Session;
import com.csw.Service.impls.TransformSensorMLToebRIMService;
import com.csw.model.ebXMLModel.MyebRIMSmlContents;
import com.csw.utils.DateOperationUtil;
import com.csw.utils.GetSessionUtil;

public class SaveSensorMLAndEbRIMUtil {
	/**
	 * 将SensorML与EbRIM同时存入到数据库中
	 * 
	 * @param username
	 *            用户名称
	 * @param sensorml
	 *            插入的sensorml文档内容 插
	 * @param filename
	 *            更新插入的filename的文件名称
	 * @return 1:成功存入;0:插入失败。
	 */
	@SuppressWarnings("null")
	public int SaveDBmyebrimsmlcontentsMethod1(String username,
			String password, String sensorml, String filename, String createtime) {
		Session session = GetSessionUtil
				.GetSessionInstance(GetSessionUtil.SENSORTYPE);
		try {
			// 第一步生成sensorml所对应的ebRIM的文档内容
			String ebrimcontent = new TransformSensorMLToebRIMService()
					.TransactionSensorMLToeEbRIMMethod(username, password,
							sensorml);
			// 第二步根据生成的ebRIM的文档内容获取相关的ebRIM的id值
			String ebrimid = GetRegistryRegistryInfoUtils
					.GetRegistryPackageIDByEbrimContent(ebrimcontent);
			// 将ebrim的id，sensorml，ebrim内容与上传sensorml的用户存入到数据库中进行保存

			// 第四步，将数据组装成一个实体类，进行保存
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
				// 获取倒数第二个的string即为senosorml的Id值，不包括后缀名
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
	 * 将SensorML与EbRIM同时存入到数据库中
	 * 
	 * @param username
	 *            用户名称
	 * @param sensorml
	 *            插入的sensorml文档内容
	 * @param ebrim
	 *            插入的ebrim的文档的内容
	 * @param ebrimid
	 *            插入的ebrim的文档的id值,该输入ebrim的id可以是ebrim的也可以使sensorml的id
	 * @param saveebrimIsNot
	 *            是否保存 ebrimcontent的文档,true为保存，false不保存
	 * @return 1:成功存入;0:插入失败。
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
				// 获取倒数第二个的string即为sensorML的Id值，不包括后缀名
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
