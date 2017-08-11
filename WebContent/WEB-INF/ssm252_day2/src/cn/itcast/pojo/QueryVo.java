package cn.itcast.pojo;

import java.util.List;

public class QueryVo {
	List<Items> itemList;//itemList[2].name
	Items items;
	
	
	
	public List<Items> getItemList() {
		return itemList;
	}

	public void setItemList(List<Items> itemList) {
		this.itemList = itemList;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}
	
}
