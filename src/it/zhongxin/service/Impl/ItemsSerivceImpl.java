package it.zhongxin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.zhongxin.dao.ItemsMapper;
import it.zhongxin.pojo.Items;
import it.zhongxin.service.ItemsSerivce;

@Service
public class ItemsSerivceImpl implements ItemsSerivce {
	@Autowired
	private ItemsMapper itemmapper;

	public List<Items> findItemsAll() {
		List<Items> list = itemmapper.selectByExampleWithBLOBs(null);
		return list;
	}

}
