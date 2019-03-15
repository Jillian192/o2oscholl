package com.ijava.o2oschool.service;

import com.ijava.o2oschool.dao.ShopCategoryDao;
import com.ijava.o2oschool.entity.ShopCategory;
import com.ijava.o2oschool.serviceimpl.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategory() {
        return shopCategoryDao.queryShopCategory(new ShopCategory());
    }
}
