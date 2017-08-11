package cn.itcast.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.itcast.action.exceptions.CustomException;
import cn.itcast.pojo.Items;
import cn.itcast.pojo.QueryVo;
import cn.itcast.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemAction {
	@Resource
	ItemService itemService;
	
	Integer count=0;
	//@RequestMapping(method=RequestMethod.POST,value= "/itemList")
	@RequestMapping("/itemList")
	public String showItems(HttpServletRequest request,Model model,Integer id,Items items,QueryVo queryVo,Integer[] ids) {
		List<Items> list = itemService.findItemsAll();
		//request.setAttribute("itemList", list);
		/**
		 * Model接口跟request.setAttribute一样,将数据放入Request域中,但是有一些扩展,扩展明天讲
		 */
		System.out.println(++count);
		//System.out.println(1/0);
		model.addAttribute("itemList", list);
		return "itemList";
	}
	/**
	 * 
	 * 演示基础类型
	 * @throws CustomException 
	 */
	@RequestMapping("/itemEdit/{xxxx}")//   /item/itemEdit.action
	public ModelAndView to_editItem(@PathVariable("xxxx") Integer id) throws CustomException {
		Items items = itemService.findItemById(id);
		if (items == null) {
			//在这里抛出自定义的异常
			CustomException customException = new CustomException();
			customException.setMessage("您购买的商品已经到火星了");
			throw customException;
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("item", items);
		modelAndView.setViewName("editItem");
		return modelAndView;
	}
	/**
	 * 演示pojo类型
	 * @param items
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/updateitem")
	public String updateItem(Items items,MultipartFile pictureFile,Model model) throws IllegalStateException, IOException {
		//获取原图片名称
		String oldFileName = pictureFile.getOriginalFilename();
		//创建新图片名称: uuid+原图片名称的后缀名
		String newFileName= UUID.randomUUID().toString()+oldFileName.substring(oldFileName.lastIndexOf("."));
		//保存图片: E:\myImgServer\
		pictureFile.transferTo(new File("E:\\myImgServer\\"+newFileName));
		//保存到数据库里面
		items.setPic(newFileName);
		
		itemService.updateItem(items);
		//return "forward:itemEdit.action";
		model.addAttribute("id", items.getId());
		return "redirect:/item/itemEdit.action";
	}
	/**
	 * 演示pojo的包装类型
	 * @return
	 */
	@RequestMapping("/queryitem")
	public String queryitem(QueryVo queryVo) {
		System.out.println(queryVo);
		return "success";
	}
	/**
	 * 演示数组绑定参数
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteitems")
	public String deleteitems(Integer[] ids) {
		for (Integer id : ids) {
			//itemService.delteItem(id);
		}
		return "success";
	}
	@RequestMapping("/updateitems")
	public String updateitems(QueryVo queryVo,HttpServletRequest request) {
		String itemname = request.getParameter("itemList[0].name");
		System.out.println(itemname);
		List<Items> itemList = queryVo.getItemList();
		for (Items items : itemList) {
			itemService.updateItem(items);
		}
		return "success";
	}
	
	@RequestMapping("/voidTest")
	public void voidTest(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		Items items = new Items();
		items.setName("华硕");
		items.setDetail("非常好用");
		String jsonText=new ObjectMapper().writeValueAsString(items);
		System.out.println(jsonText);
		//response.getWriter().print(jsonText);
		/**
		 * 内部请求转发
		 * 		特点1: url不发生改变
		 * 		特点2: Request域中的数据可以带到被转发的方法内
		 */
		//request.getRequestDispatcher("/item/itemList.action").forward(request, response);
		
		/**
		 * 重定向
		 * 		特点1: url发生改变
		 * 		特点2: Request域中的数据不可以带到重定向的方法内
		 */
		response.sendRedirect(request.getContextPath()+"/item/itemList.action");
	}
	
	//
	@RequestMapping("/editItemSubmit_RequestJson")
	public @ResponseBody Items editItemSubmit_RequestJson(@RequestBody Items items) {
		items.setId(1);
		
		return items;
	}
	
	
}
