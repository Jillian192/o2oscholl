package com.ijava.o2oschool.dao;

import com.ijava.o2oschool.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryDao {
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")ShopCategory shopCategory);
}
