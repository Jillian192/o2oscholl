package com.ijava.o2oschool.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.ijava.o2oschool.dao.ShopDao;
import com.ijava.o2oschool.entity.Area;
import com.ijava.o2oschool.entity.PersonInfo;
import com.ijava.o2oschool.entity.Shop;
import com.ijava.o2oschool.entity.ShopCategory;
import com.ijava.o2oschool.util.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;


public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	public void testQueryShopListAndCount() {
		Shop shopCondition = new Shop();
		ShopCategory childCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(12L);
		childCategory.setParent(parentCategory);
		shopCondition.setShopCategory(childCategory);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 6);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("店铺列表的大小：" + shopList.size());
		System.out.println("店铺总数：" + count);		
	}

	@Test
	public void testQueryByShopId() {
//		try {
//			String realFileName = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//			Thumbnails.of(new File(FileUtil.getImgBasePath()+"scenery.jpg")).size(200,200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(FileUtil.getImgBasePath()+"doller.png")),0.25f)
//					.outputQuality(0.8f).toFile(FileUtil.getImgBasePath()+"scenerynew.jpg");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		long shopId = 1;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaId:" + shop.getArea().getAreaId());
		System.out.println("areaName" + shop.getArea().getAreaName());
	}

	@Test
	public void testInsertShop() {
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
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("审核中");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}

	@Test
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopDesc("测试描述");
		shop.setShopName("丁杰小铺");
		shop.setShopAddr("测试地址");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
//	@Test
//	private static void setWatermark() throws IOException {
//		String realFileName = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//		Thumbnails.of(new File("G:/scenery.jpg")).size(200,200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(realFileName+"/doller.png")),0.25f)
//				.outputQuality(0.8f).toFile("G:/scenerynew.jpg");
//
//	}
}
