package com.ijava.o2oschool.web.controller.shop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ijava.o2oschool.entity.Area;
import com.ijava.o2oschool.entity.Shop;
import com.ijava.o2oschool.entity.ShopCategory;
import com.ijava.o2oschool.serviceimpl.AreaService;
import com.ijava.o2oschool.serviceimpl.ShopCategoryService;
import com.ijava.o2oschool.serviceimpl.ShopService;
import com.ijava.o2oschool.util.CodeUtil;
import com.ijava.o2oschool.util.HttpServletRequestUtil;
import com.ijava.o2oschool.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("shopadmin")
public class ShopManagementController {
    @Autowired
    ShopService shopService;
    @Autowired
    AreaService areaService;
    @Autowired
    ShopCategoryService shopCategoryService;
    @RequestMapping(value = "/registShop",method = RequestMethod.POST)
    @ResponseBody
    private Map<String ,Object> registerShop(HttpServletRequest request){
        Map<String, Object> hashMap = new HashMap<>();
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        System.out.println("★registerShop★"+shopStr);
        if (!CodeUtil.checkVerifyCode(request)) {
//            hashMap.put("success",false);
//            hashMap.put("errMsg","输入了错误的验证码");
//            return hashMap;
        }
        ObjectMapper mapper = new ObjectMapper();
         Shop shop;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (IOException e) {
            hashMap.put("success",false);
            hashMap.put("errMsg",e.getMessage());
            return hashMap;
        }
        CommonsMultipartFile shopImg = null;

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }else {
            hashMap.put("success",false);
            hashMap.put("errMsg","上传图片不能为空");
            return hashMap;
        }
        if (shop!=null&& shopImg!=null) {
            try {
                File file = ImageUtil.transferCommonsMultipartFile2File(shopImg);
                shopService.addShop(shop,file);
                hashMap.put("success",true);
            } catch (IOException e) {
                e.printStackTrace();
                hashMap.put("success",false);
                hashMap.put("errMsg",e.getMessage());
            }
        }else {
            hashMap.put("success",false);
            hashMap.put("errMsg","请输入店铺信息");
            return hashMap;
        }
        return hashMap;
    }
    @RequestMapping(value = "/getshopinitinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String ,Object>  getShopInfo(){
        Map<String, Object> map = new HashMap<>();
        List<ShopCategory> categories = new ArrayList<>();
        List<Area> areas = new ArrayList<>();
        try {
            categories = shopCategoryService.getShopCategory();
            areas = areaService.getAreaList();
            map.put("shopCategoryList",categories);
            map.put("areaList",areas);
            map.put("success",true);
        } catch (Exception e) {
            map.put("success",false);
            map.put("errMsg",e.getMessage());
//            map.put("success",true);
            e.printStackTrace();
//            return map;
        }
        String stringShopInfo = JSON.toJSONString(map);
        System.out.println("★stringShopInfo★"+stringShopInfo);
        return  map;

    }

}
