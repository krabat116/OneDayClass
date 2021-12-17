package com.vam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vam.mapper.AdminMapper;
import com.vam.model.AttachImageVO;
import com.vam.model.CateVO;
import com.vam.model.ClassVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminMapper adminMapper;
	
	/* 강좌 등록 */
	@Transactional
	@Override
	public void classEnroll(ClassVO oneclass) {
		
		System.out.println("(service)classEnroll.....");
		
		adminMapper.classEnroll(oneclass);
		
		if(oneclass.getImage_list() == null || oneclass.getImage_list().size() <= 0) { //이미지 업로드 안할 시 for문 수행 안함
			return;
		}
		
		for(AttachImageVO attach : oneclass.getImage_list()) {
			
			attach.setClass_id(oneclass.getClass_id());
			adminMapper.imageEnroll(attach);
			
		};
	}
	
	/* 카테고리 리스트 */
	@Override
	public List<CateVO> cateList() {
		System.out.println("(service)cateList........");
		
		return adminMapper.cateList();
	}
	
}