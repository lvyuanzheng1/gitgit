package cn.itcast.action.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception exception) {
		//只要抛出了异常,都会将异常抛出到这里处理
		
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof CustomException) {//如果是自定义的异常
			CustomException customException = (CustomException) exception;
			modelAndView.addObject("message", customException.getMessage());
		}else{//如果是运行期的异常
			modelAndView.addObject("message", "系统繁忙中,请您稍后再试");
		}
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
