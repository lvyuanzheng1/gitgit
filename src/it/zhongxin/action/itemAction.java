package it.zhongxin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")

public class itemAction {
@RequestMapping("itemList")
public void itemList(HttpServletRequest request,HttpServletResponse response) throws IOException{
	response.sendRedirect("/jsps/customer.jsp");
}
}
