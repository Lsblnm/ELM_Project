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
import com.neusoft.elm.controller.FoodController;
import com.neusoft.elm.controller.UserController;

//��servlet�䵱ǰ�˿�����


/* ������κ�servlet����·��������ǰ�˿���������
   "/*"�������е����󣬰���servlet��html��
*/
@WebServlet("/")
public class DispatcherServlet extends HttpServlet{
  
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�������ı���
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//��ȷ���������json��ʽ�����뼯ҲΪutf8
		response.setContentType("application/json;charset=utf-8");
		
		//��ȡ�ͻ�������·��,request��װ������������Ϣ
		String path = request.getServletPath();
		
		//��������·������controller��������뷽������������
		String className = path.substring(1, path.lastIndexOf("/"));
		String methodName = path.substring(path.lastIndexOf("/")+1);
		
		PrintWriter out = null;
		
		
		//����java���佫�ַ���ӳ��Ϊ��, clazz������������˸����������Ϣ
		try {
			//ͨ��lcass.forname��ȡcontroller�����Ϣ
			Class clazz = Class.forName("com.neusoft.elm.controller."+className);
			//����controller����
			Object controller = clazz.newInstance();
			//��ȡcontroller�ķ�������һ�������ŷ��������ڶ����ŷ����Ĳ�������Ϊ�����������أ�������λ��,���������Ҫ���������������������а�˳�����
			Method method = clazz.getMethod(methodName, new Class[]{HttpServletRequest.class});
			//����controller�еķ�������һ�����ĸ����󣬵ڶ��������Ƿ���Ҫ���Ĳ���,���������Ҫ���������������������а�˳�����
			Object result = method.invoke(controller, new Object[]{request});
			
			out = response.getWriter();
			ObjectMapper om = new ObjectMapper();
			//��java�������Ϊjson���ݣ������ͻ�����Ӧ
			out.print(om.writeValueAsString(result));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DispatcherServlet��Ϣ��Servlet����·����"+path);
			System.out.println("DispatcherServlet��Ϣ��������"+className+"\t������"+methodName);

		} finally {
			out.close();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
 
