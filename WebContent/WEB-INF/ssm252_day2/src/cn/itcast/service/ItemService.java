package cn.itcast.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.dao.ItemsMapper;
import cn.itcast.pojo.Items;

@Service
public class ItemService {
	@Resource
	ItemsMapper itemsMapper;
	
	
	public List<Items> findItemsAll() {
		List<Items> list = itemsMapper.selectByExampleWithBLOBs(null);
		
		return list;
	}
	
	public Items findItemById(Integer id) {
		Items items = itemsMapper.selectByPrimaryKey(id);
		return items;
	}
	
	public void updateItem(Items items) {
		itemsMapper.updateByPrimaryKeySelective(items);
	}
	
	public void delteItem(Integer id) {
		itemsMapper.deleteByPrimaryKey(id);
	}
	
}
