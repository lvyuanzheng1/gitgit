package cn.itcast.action.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.pojo.User;

public class CustomIntercepter1 implements HandlerInterceptor {
	
	/**
	 * 执行时机: Controller方法已经执行,ModelAndView 已经返回
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("-----------afterCompletion-----------");
	}
	/**
	 * 执行时机: Controller方法已经执行,ModelAndView没有返回
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("-----------postHandle-----------");
	}
	
	/**
	 * 返回值: 返回true放行,返回false 不放行
	 * 执行时机: Controller方法还没有执行
	 * 用途: 一般作为登录和安全验证
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("-----------preHandle-----------");
		String url = request.getRequestURI();
		if (url.contains("user/login")) {//如果是登录url,放行
			return true;
		}else {
			User user = (User)request.getSession().getAttribute("user");
			if (user ==null) {//转向登录页面
				request.getRequestDispatcher("/WEB-INF/jsps/login.jsp").forward(request, response);
				return false;
			}else{
				return true;
			}
		}
	}
	
}
