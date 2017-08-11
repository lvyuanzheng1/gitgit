package cn.itcast.action;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.pojo.User;

@Controller
@RequestMapping("/user")
public class UserAction {
	@RequestMapping("/login")
	public String user(User user,HttpSession session) {
		if (user.getUsername() != null && user.getUsername().equals("123")) {
			session.setAttribute("user", user);
			return "redirect:/item/itemList";
		}else {
			return "login";
		}
	}
}
