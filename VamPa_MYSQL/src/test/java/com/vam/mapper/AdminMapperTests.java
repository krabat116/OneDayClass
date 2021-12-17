package com.vam.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.model.AttachImageVO;
import com.vam.model.ClassVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

public class AdminMapperTests {
	@Autowired 
	private AdminMapper mapper;
	
	/* 강좌등록 */
	
	@Test
	public void classEnrollTest() throws Exception{
		ClassVO oneclass = new ClassVO();
		
		oneclass.setCate_code("2101");
		oneclass.setCreator_id(2);
		oneclass.setClass_name("mapper test");
		oneclass.setClass_price(10000);
		oneclass.setClass_info("강좌소개");
		oneclass.setClass_tag("new");
		oneclass.setClass_discount(0.2);

		System.out.println("Before ClassVO :" + oneclass);	
		mapper.classEnroll(oneclass);
		System.out.println("After ClassVO :" + oneclass);
	}
	
	
	/* 카테고리 리스트 */
//	@Test
//	public void cateListTest() throws Exception{
//		System.out.println("cateList()........"+ mapper.cateList());
//	}
	
	/* 이미지 등록 */
	/*
	@Test
	public void imageEnrollTest() {
		AttachImageVO vo = new AttachImageVO();
		
		vo.setClass_id(7);
		vo.setFile_name("test3");
		vo.setUpload_path("test3");
		vo.setUuid("test3");
		
		
		mapper.imageEnroll(vo);
	}
	*/
}
