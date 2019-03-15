package com.ijava.o2oschool.test;


import com.ijava.o2oschool.dao.AreaDao;
import com.ijava.o2oschool.dao.ShopCategoryDao;
import com.ijava.o2oschool.entity.Area;
import com.ijava.o2oschool.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static net.sf.ezmorph.test.ArrayAssertions.assertEquals;

public class ShopCategoryDaoTest extends BaseTest{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testShopCategory(){
		ShopCategory shopCategory = new ShopCategory();
		List<ShopCategory> areaList = shopCategoryDao.queryShopCategory(shopCategory);
		assertEquals(21, areaList.size());
	}
}
