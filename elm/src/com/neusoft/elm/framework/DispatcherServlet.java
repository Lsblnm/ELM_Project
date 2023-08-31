package com.neusoft.elm.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ı��봦��
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		// ��ȡ�ͻ�������·��(/HelloController/say)
		String path = request.getServletPath();
		// ��������·������Controller�������ͷ�������������
		String className = path.substring(1, path.lastIndexOf("/"));
		String methodName = path.substring(path.lastIndexOf("/") + 1);
		PrintWriter out = null;
		// �ж�����·�������ݲ�ͬ�����󣬷ַ�����ͬ��ҵ������
		try {
			// ͨ��Controller��ȫ����ȡ�����������Ϣ
			Class clazz = Class.forName("com.neusoft.elm.controller." + className);
			// ����Controller��Ķ���
			Object controller = clazz.newInstance();
			// ��ȡController������еķ���
			Method method = clazz.getMethod(methodName, new Class[] { HttpServletRequest.class });
			// ���������ȡ�ķ���
			Object result = method.invoke(controller, new Object[] { request });
			// ��ȡ��ͻ�����Ӧ�������
			out = response.getWriter();
			ObjectMapper om = new ObjectMapper();
			// ��ͻ�����Ӧjson����
			out.print(om.writeValueAsString(result));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DispatcherServlet��Ϣ������url��" + path);
			System.out.println("DispatcherServlet��Ϣ��������" + className + "\t��������" + methodName);
		} finally {
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
