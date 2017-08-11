package it.zhongxin.action.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import it.zhongxin.pojo.User;
/**
 * @564
 * @author tomLv
 *
 */
public class CustomIntercepter implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion");
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle");
		
	}

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
				request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
				return false;
			}else{
				return true;
			}
		}
	}
}












