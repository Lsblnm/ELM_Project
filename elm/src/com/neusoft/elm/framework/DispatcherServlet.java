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

//用servlet充当前端控制器


/* 后表后边任何servlet请求路径都交给前端控制器处理
   "/*"拦截所有的请求，包括servlet、html等
*/
@WebServlet("/")
public class DispatcherServlet extends HttpServlet{
  
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//处理中文编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//明确浏览器返回json格式，编码集也为utf8
		response.setContentType("application/json;charset=utf-8");
		
		//获取客户端请求路径,request封装了所有请求信息
		String path = request.getServletPath();
		
		//根据请求路径，将controller组件类名与方法名解析出来
		String className = path.substring(1, path.lastIndexOf("/"));
		String methodName = path.substring(path.lastIndexOf("/")+1);
		
		PrintWriter out = null;
		
		
		//运用java反射将字符串映射为类, clazz是描述类包括了该类的所有信息
		try {
			//通过lcass.forname获取controller类的信息
			Class clazz = Class.forName("com.neusoft.elm.controller."+className);
			//创建controller对象
			Object controller = clazz.newInstance();
			//获取controller的方法，第一个参数放方法名，第二个放方法的参数（因为方法可能重载，帮助定位）,如果方法需要多个参数，则可以在数组中按顺序添加
			Method method = clazz.getMethod(methodName, new Class[]{HttpServletRequest.class});
			//调用controller中的方法，第一个是哪个对象，第二个参数是方法要传的参数,如果方法需要多个参数，则可以在数组中按顺序添加
			Object result = method.invoke(controller, new Object[]{request});
			
			out = response.getWriter();
			ObjectMapper om = new ObjectMapper();
			//把java对象解析为json数据，最后向客户端响应
			out.print(om.writeValueAsString(result));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DispatcherServlet信息：Servlet请求路径："+path);
			System.out.println("DispatcherServlet信息：类名："+className+"\t方法名"+methodName);

		} finally {
			out.close();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
 
