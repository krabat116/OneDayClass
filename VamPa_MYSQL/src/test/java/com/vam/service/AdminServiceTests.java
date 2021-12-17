package com.vam.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.model.AttachImageVO;
import com.vam.model.ClassVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminServiceTests {
	
	@Autowired
	private AdminService service;
	
	/* 상품 등록 & 상품 이미지 등록 테스트 */
	@Test
	public void classEnrollTests() {
		
		ClassVO oneclass = new ClassVO();
		//강좌 정보
		//oneclass.setClass_id(1);
		oneclass.setCate_code("2101");
		oneclass.setCreator_id(2);
		oneclass.setClass_name("test2");
		oneclass.setClass_price(10000);
		oneclass.setClass_info("test2");
		oneclass.setClass_tag("new");
		oneclass.setClass_discount(0.3);
	
		
		//이미지 정보
		List<AttachImageVO> image_list = new ArrayList<AttachImageVO>();
		
		AttachImageVO image1 = new AttachImageVO();
		AttachImageVO image2 = new AttachImageVO();
		
		image1.setFile_name("test Image1");
		image1.setUpload_path("test Image1");
		image1.setUuid("test11111");
		
		image2.setFile_name("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		image2.setUpload_path("test Image2");
		image2.setUuid("test22222");
		
		image_list.add(image1);
		image_list.add(image2);
		
		oneclass.setImage_list(image_list);
		
		// classEnroll() 메서드 호출
		service.classEnroll(oneclass);
		
		System.out.println("등록된 VO : " + oneclass);
		
		
		
		
	}

}
