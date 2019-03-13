package com.ijava.o2oschool.test;

import com.ijava.o2oschool.entity.Area;
import com.ijava.o2oschool.serviceimpl.AreaService;
import com.ijava.o2oschool.util.LunarSolarConverter;
import com.ijava.o2oschool.util.LunarUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AreaServiceTest extends BaseTest{
    @Autowired
    private AreaService areaService;
    @Test
    public void testgetAreaList(){
//        Calendar calendar = Calendar.getInstance();
//        System.out.println("DATE:" + calendar.get(Calendar.DATE));
//        System.out.println("DATE:" + calendar.get(Calendar.YEAR));
//        LunarSolarConverter.Lunar lunar=LunarSolarConverter.converterDate(calendar.getTimeInMillis());
//        System.out.println("★★★★"+lunar.getDay());
//        SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String d = dateFormat.format(date);
//        Calendar today = Calendar.getInstance();
//        try {
//            today.setTime(dateFormat.parse(d));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        LunarUtil lunar = new LunarUtil(today);
//        System.out.println("北京时间：" + dateFormat.format(today.getTime())
//                + "　农历" + lunar);

        List<Area> areaList = areaService.getAreaList();
        for (Area area : areaList) {
            System.out.println("★AreaService★"+area.getAreaName());
        }
    }
}
