package com.ijava.o2oschool.web.controller;

import com.ijava.o2oschool.entity.Area;
import com.ijava.o2oschool.serviceimpl.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/controller")
public class AreaController {
    Logger logger =LoggerFactory.getLogger(AreaController.class);
    @Autowired
    private AreaService areaService;
    @RequestMapping(value = "/listArea",method = RequestMethod.GET)
//    @GetMapping
//    @PostMapping
//    @PutMapping
//    @DeleteMapping
    @ResponseBody
    private Map<String,Object> listArea(){
        logger.info("===start===");
        long startTime = System.currentTimeMillis();
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
        long endTime = System.currentTimeMillis();
        logger.info("costTime:[{}ms]",endTime-startTime);
        logger.info("===end===");

        return hashMap;
    }

}
