package com.ijava.o2oschool.serviceimpl;

import com.ijava.o2oschool.dto.ShopExecution;
import com.ijava.o2oschool.entity.Shop;
import com.ijava.o2oschool.entity.ShopCategory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ShopCategoryService {
   List<ShopCategory> getShopCategory();
}
