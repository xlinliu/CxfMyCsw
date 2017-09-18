package com.csw.Service.impls;

import com.csw.Service.interfaces.DeleteManyFilesBatchServiceInterface;
import com.csw.exceptions.ServiceException;
import com.csw.utils.StringUtil;
import com.csw.utils.SensorInfoUtil.OperateSensorUtil;
import com.csw.utils.TransactionsUtil.TransactionOperation;
import com.csw.utils.Userutils.UserInfoUtil;

public class DeleteManyFilesBatchService implements
		DeleteManyFilesBatchServiceInterface {

	/**
	 * 删除所有的需要删除的文档的内容信息，需要注意的是， 先要判断所有的filenames中的文件名称必须属于Username才可以被删除
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param filenames
	 *            需要删除的文件名称,多个文件名称则必须 以空格隔开,这里的id可以使sensorML的id，也可使ebrim格式的id值
	 * @return 删除成功返回 1 ,删除失败返回0，出现异常返回2
	 */
	public int DeleteManyFileContentsMethod(String username, String password,
			String filenames) throws ServiceException {
		// 核实用户信息
		UserInfoUtil.CheckUserLogin(username, password);
		if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(filenames)) {
			throw new ServiceException("参数filenames内容不能为空!");
		}

		// 第二步，获取所需要删除的全部的file的id集合
		for (String fileid : StringUtil.GetFilesIDCollection(filenames)) {
			// 如果用户传入的是SensorML的id值，则需要将SensorML的id值加上:pacakge的值
			if (null == StringUtil.checkStringIsNotNULLAndEmptyMethod(fileid)) {
				throw new ServiceException("参数filenames中的文件名存在问题，请核实!");
			}
			fileid = StringUtil.AppendPacakgeStr(fileid);
			// 分别删除file id，如果存在则删除，并且该file是用户所需要保存的
			try {
				if (OperateSensorUtil.CheckSensorIdIsBelongOwner(username,
						fileid)) {
					TransactionOperation.DeleteRegistryPackageById(fileid
							.trim());
				}
			} catch (Exception e) {
				throw new ServiceException("在删除[" + fileid
						+ "]时失败，请检查相关信息，详细信息如下：[" + e.getMessage() + "]");
			}
		}
		return 1;
	}
}
