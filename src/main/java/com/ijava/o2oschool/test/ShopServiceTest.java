package com.ijava.o2oschool.test;

import com.ijava.o2oschool.dao.ShopDao;
import com.ijava.o2oschool.dto.ShopExecution;
import com.ijava.o2oschool.entity.Area;
import com.ijava.o2oschool.entity.PersonInfo;
import com.ijava.o2oschool.entity.Shop;
import com.ijava.o2oschool.entity.ShopCategory;
import com.ijava.o2oschool.enume.ShopStateEnum;
import com.ijava.o2oschool.serviceimpl.AreaService;
import com.ijava.o2oschool.serviceimpl.ShopService;
import com.ijava.o2oschool.util.FileUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;
    @Test
	public void testAddShop() throws Exception {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId((long) 1);
        area.setAreaId(2);
        shopCategory.setShopCategoryId((long) 11);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("te2019st");
        shop.setShopAddr("te2019st");
        shop.setPhone("te2019st");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        File file = new File(FileUtil.getImgBasePath() + "scenery.jpg");
        ShopExecution shopExecution = shopService.addShop(shop,file);
        assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());

    }

}
