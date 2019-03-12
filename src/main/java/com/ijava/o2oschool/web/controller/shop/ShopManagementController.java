package com.ijava.o2oschool.web.controller.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ijava.o2oschool.entity.Shop;
import com.ijava.o2oschool.serviceimpl.ShopService;
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
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("shopadmin")
public class ShopManagementController {
    @Autowired
    ShopService shopService;
    @RequestMapping(value = "/registShop",method = RequestMethod.POST)
    @ResponseBody
    private Map<String ,Object> registerShop(HttpServletRequest request){
        Map<String, Object> hashMap = new HashMap<>();
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
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
}
