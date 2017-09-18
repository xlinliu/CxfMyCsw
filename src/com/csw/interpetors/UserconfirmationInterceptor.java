package com.csw.interpetors;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.csw.utils.Userutils.UserInfoUtil;

public class UserconfirmationInterceptor extends
		AbstractPhaseInterceptor<SoapMessage> {

	public UserconfirmationInterceptor(String phase) {
		super(phase);
	}

	public UserconfirmationInterceptor() {
		// 在准备发送的时候进行拦截，说明是在接收到soap之后才进行
		super(Phase.PREPARE_SEND);
	}

	public void handleMessage(SoapMessage mess) throws Fault {
		SoapMessage message = (SoapMessage) mess.getContent(SoapMessage.class);
		// 解析soap消息
		// 得到soap晓得所有header
		List<Header> headers = (List<Header>) message.getHeaders();
		for (String str : message.keySet()) {
			System.out.println(str + " : " + message.get(str));
		}
		// 如果根本没有header
		if (headers == null || headers.size() < 1) {
			throw new Fault(new IllegalArgumentException("没有相关header信息，请返回!"));
		}
		// 设置第一个header存放username与password
		Header header = headers.get(0);
		Element element = (Element) header.getObject();
		NodeList users = element.getElementsByTagName("username");
		NodeList passwords = element.getElementsByTagName("password");
		if (users.getLength() > 1) {
			System.out.println("用户名设置错误，请重新设置!");
			throw new Fault(new IllegalArgumentException("用户名设置错误，请重新输入"));
		}
		if (passwords.getLength() > 1) {
			System.out.println("密码设置错误，请重新设置!");
			throw new Fault(new IllegalArgumentException("密码设置错误，请重新输入"));
		}
		// 获取了唯一的用户名与密码之后，进行实际的处理与操作
		// if (users.item(0).getTextContent().equals("admin")
		// && passwords.item(0).getTextContent().equals("cswadmin")) {
		// System.out.println("success");
		// }
		// 验证用户名与密码是否匹配并且是否在数据库中保存
		int result = UserInfoUtil.CheckUserInfo(users.item(0).getTextContent()
				.trim(), passwords.item(0).getTextContent().trim());
		System.out.println("-------------------------");
		System.out
				.println("username: " + users.item(0).getTextContent().trim());
		System.out.println("password: "
				+ passwords.item(0).getTextContent().trim());
		System.out.println("-------------------------");

		if (result != 1) {
			throw new Fault(new IllegalArgumentException("用户名或者密码错误，请重新输入!"));
		}
	}
}
