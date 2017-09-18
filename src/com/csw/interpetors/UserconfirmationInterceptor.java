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
		// ��׼�����͵�ʱ��������أ�˵�����ڽ��յ�soap֮��Ž���
		super(Phase.PREPARE_SEND);
	}

	public void handleMessage(SoapMessage mess) throws Fault {
		SoapMessage message = (SoapMessage) mess.getContent(SoapMessage.class);
		// ����soap��Ϣ
		// �õ�soap��������header
		List<Header> headers = (List<Header>) message.getHeaders();
		for (String str : message.keySet()) {
			System.out.println(str + " : " + message.get(str));
		}
		// �������û��header
		if (headers == null || headers.size() < 1) {
			throw new Fault(new IllegalArgumentException("û�����header��Ϣ���뷵��!"));
		}
		// ���õ�һ��header���username��password
		Header header = headers.get(0);
		Element element = (Element) header.getObject();
		NodeList users = element.getElementsByTagName("username");
		NodeList passwords = element.getElementsByTagName("password");
		if (users.getLength() > 1) {
			System.out.println("�û������ô�������������!");
			throw new Fault(new IllegalArgumentException("�û������ô�������������"));
		}
		if (passwords.getLength() > 1) {
			System.out.println("�������ô�������������!");
			throw new Fault(new IllegalArgumentException("�������ô�������������"));
		}
		// ��ȡ��Ψһ���û���������֮�󣬽���ʵ�ʵĴ��������
		// if (users.item(0).getTextContent().equals("admin")
		// && passwords.item(0).getTextContent().equals("cswadmin")) {
		// System.out.println("success");
		// }
		// ��֤�û����������Ƿ�ƥ�䲢���Ƿ������ݿ��б���
		int result = UserInfoUtil.CheckUserInfo(users.item(0).getTextContent()
				.trim(), passwords.item(0).getTextContent().trim());
		System.out.println("-------------------------");
		System.out
				.println("username: " + users.item(0).getTextContent().trim());
		System.out.println("password: "
				+ passwords.item(0).getTextContent().trim());
		System.out.println("-------------------------");

		if (result != 1) {
			throw new Fault(new IllegalArgumentException("�û������������������������!"));
		}
	}
}
