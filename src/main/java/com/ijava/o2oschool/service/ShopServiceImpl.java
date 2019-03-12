package com.ijava.o2oschool.service;

import com.ijava.o2oschool.dao.ShopDao;
import com.ijava.o2oschool.dto.ShopExecution;
import com.ijava.o2oschool.entity.Shop;
import com.ijava.o2oschool.entity.ShopAuthMap;
import com.ijava.o2oschool.entity.ShopCategory;
import com.ijava.o2oschool.enume.ShopStateEnum;
import com.ijava.o2oschool.serviceimpl.ShopService;
import com.ijava.o2oschool.util.FileUtil;
import com.ijava.o2oschool.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Date;
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File imageShopUrl) {
        if (shop==null) {
            return  new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        shop.setEnableStatus(0);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        int insertShop = shopDao.insertShop(shop);
        if (insertShop<=0) {
            throw new RuntimeException("店铺创建失败");
        }else {
            if (imageShopUrl!=null) {
                //存储图片
                try {
                    addShopImg(shop,imageShopUrl);
                   int effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                           throw new RuntimeException("创建图片地址失败");
                       }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

//    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg)
//            throws RuntimeException {
//        if (shop == null) {
//            return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
//        }
//        try {
//            shop.setEnableStatus(0);
//            shop.setCreateTime(new Date());
//            shop.setLastEditTime(new Date());
//            if (shop.getShopCategory() != null) {
//                Long shopCategoryId = shop.getShopCategory()
//                        .getShopCategoryId();
//                ShopCategory sc = new ShopCategory();
//                sc = shopCategoryDao.queryShopCategoryById(shopCategoryId);
//                ShopCategory parentCategory = new ShopCategory();
//                parentCategory.setShopCategoryId(sc.getParentId());
//                shop.setParentCategory(parentCategory);
//            }
//            int effectedNum = shopDao.insertShop(shop);
//            if (effectedNum <= 0) {
//                throw new RuntimeException("店铺创建失败");
//            } else {
//                try {
//                    if (shopImg != null) {
//                        addShopImg(shop, shopImg);
//                        effectedNum = shopDao.updateShop(shop);
//                        if (effectedNum <= 0) {
//                            throw new RuntimeException("创建图片地址失败");
//                        }
//                    }
//                } catch (Exception e) {
//                    throw new RuntimeException("addShopImg error: "
//                            + e.getMessage());
//                }
//                // 执行增加shopAuthMap操作
//                ShopAuthMap shopAuthMap = new ShopAuthMap();
//                shopAuthMap.setEmployeeId(shop.getOwnerId());
//                shopAuthMap.setShopId(shop.getShopId());
//                shopAuthMap.setName("");
//                shopAuthMap.setTitle("Owner");
//                shopAuthMap.setTitleFlag(1);
//                shopAuthMap.setCreateTime(new Date());
//                shopAuthMap.setLastEditTime(new Date());
//                shopAuthMap.setEnableStatus(1);
//                try {
//                    effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
//                    if (effectedNum <= 0) {
//                        throw new RuntimeException("授权创建失败");
//                    } else {// 创建成功
//                        return new ShopExecution(ShopStateEnum.CHECK, shop);
//                    }
//                } catch (Exception e) {
//                    throw new RuntimeException("insertShopAuthMap error: "
//                            + e.getMessage());
//                }
//
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("insertShop error: " + e.getMessage());
//        }
//    }

    private void addShopImg(Shop shop, File shopImg) {
        String dest = FileUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(shopImgAddr);
    }

}
