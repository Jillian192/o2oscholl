package com.ijava.o2oschool.test;


import com.ijava.o2oschool.dao.AreaDao;
import com.ijava.o2oschool.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static net.sf.ezmorph.test.ArrayAssertions.assertEquals;

public class AreaDaoTest extends BaseTest{
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void testQueryArea(){
		List<Area> areaList = areaDao.queryArea();
		assertEquals(2, areaList.size());
	}
}
