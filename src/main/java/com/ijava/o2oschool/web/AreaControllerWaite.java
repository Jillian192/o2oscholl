package com.ijava.o2oschool.web;

import com.ijava.o2oschool.entity.Area;
import com.ijava.o2oschool.serviceimpl.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/controller")
public class AreaControllerWaite {
    @Autowired
    private AreaService areaService;
    @RequestMapping(value = "/controller",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listArea(){
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Area> areas = new ArrayList<>();
        try {
            areas=  areaService.getAreaList();
            hashMap.put("rows",areas);
            hashMap.put("total",areas.size());
        } catch (Exception e) {
            e.printStackTrace();
            hashMap.put("success",false);
            hashMap.put("errMsg",e.toString());
        }
        return hashMap;
    }

}
