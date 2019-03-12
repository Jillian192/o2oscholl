package com.ijava.o2oschool.service;

import com.ijava.o2oschool.dao.AreaDao;
import com.ijava.o2oschool.entity.Area;

import com.ijava.o2oschool.serviceimpl.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
