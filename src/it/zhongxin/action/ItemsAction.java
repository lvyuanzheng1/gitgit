package it.zhongxin.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.zhongxin.pojo.Items;
import it.zhongxin.pojo.QueryVo;
import it.zhongxin.service.ItemsSerivce;
@RequestMapping("/item")
@Controller
public class ItemsAction {
	@Autowired
	private ItemsSerivce itemservice;

	@RequestMapping("/itemList")
	public String itemaction(HttpServletRequest requset) {
		List<Items> list = itemservice.findItemsAll();
		requset.setAttribute("itemList", list);
		return "itemList";
	}
	@RequestMapping("/queryitem")
	public String queryItem(QueryVo queryVo, String[] ids) {
	
			System.out.println(queryVo.getItems().getName());
			System.out.println(queryVo.getItems().getPrice());
			System.out.println(ids.toString());
			
		
		return null;
	}
	/**
	 * @param item
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value="/itemEdit")
	public String itemEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		/*Items items = (Items) request.getSession().getAttribute("Items");*/
		Items items = (Items) request.getSession().getAttribute("items");
		request.getSession().setAttribute("item", items);
		return "forward:/WEB-INF/jsps/editItem.jsp";
	}
}
