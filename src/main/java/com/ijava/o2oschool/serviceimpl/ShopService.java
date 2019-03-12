package com.ijava.o2oschool.serviceimpl;

import com.ijava.o2oschool.dto.ShopExecution;
import com.ijava.o2oschool.entity.Area;
import com.ijava.o2oschool.entity.Shop;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ShopService {
    ShopExecution addShop(Shop shop , File imageShopUrl) throws IOException;
}
