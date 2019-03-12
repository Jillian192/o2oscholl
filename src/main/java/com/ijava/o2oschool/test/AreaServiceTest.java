package com.ijava.o2oschool.test;

import com.ijava.o2oschool.entity.Area;
import com.ijava.o2oschool.serviceimpl.AreaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BaseTest{
    @Autowired
    private AreaService areaService;
    @Test
    public void testgetAreaList(){
        List<Area> areaList = areaService.getAreaList();
        for (Area area : areaList) {
            System.out.println("★AreaService★"+area.getAreaName());
        }
    }
}
