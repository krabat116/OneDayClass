package com.vam.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vam.model.AttachImageVO;
import com.vam.model.ClassVO;
import com.vam.model.CreatorVO;
import com.vam.service.AdminService;
import com.vam.service.CreatorService;

import net.coobird.thumbnailator.Thumbnails;


@Controller
@RequestMapping("/admin")  // admin이라는 이름으로 요청이 들어오면 해당 메서드가 실행됨
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private CreatorService creatorService;
	
	@Autowired
	private AdminService adminService;
	
	/* 관리자 메인 페이지 이동 */
	@RequestMapping(value="main", method = RequestMethod.GET)
	public void adminMainGET() throws Exception{
		
		logger.info("관리자 페이지 이동");
	}
	

	/* 강좌 관리 페이지 접속 */
    @RequestMapping(value = "classManage", method = RequestMethod.GET)
    public void goodsManageGET() throws Exception{
        logger.info("강좌 관리 페이지 접속");
    }
    
    /* 강좌 등록 페이지 접속 */
    @RequestMapping(value = "classEnroll", method = RequestMethod.GET)
    public void goodsEnrollGET(Model model) throws Exception{
        System.out.println("강좌 등록 페이지 접속");
        
        ObjectMapper objm = new ObjectMapper();
        
        List list = adminService.cateList();
        
        String cateList = objm.writeValueAsString(list);
        
        model.addAttribute("cateList", cateList);
        
        logger.info("변경 전......" + list);
        logger.info("변경 후......" + cateList); 
    }
    
    /* 크리에이터 등록 페이지 접속 */
    @RequestMapping(value = "creatorEnroll", method = RequestMethod.GET)
    public void authorEnrollGET() throws Exception{
        logger.info("작가 등록 페이지 접속");
    }
    
    /* 크리에이터 등록 */
    @RequestMapping(value = "creatorEnroll.do", method = RequestMethod.POST)
    public String creatorEnrollPOST(CreatorVO creator, RedirectAttributes rttr) throws Exception{
    	
    	logger.info("creatorEnroll :" + creator);
    	
    	creatorService.creatorEnroll(creator);
    	
    	rttr.addFlashAttribute("enroll_result",creator.getCreator_name()); //등록성공 메세지(작가이름)
    	
    	
    	return "redirect:/admin/creatorManage";
    }
    
    
    /* 강좌 등록 */
    @PostMapping("/classEnroll")
    public String classEnrollPOST(ClassVO oneclass, RedirectAttributes rttr) {
    	
    	System.out.println("classEnrollPOST...." + oneclass);
    	
    	adminService.classEnroll(oneclass);
    	
    	rttr.addFlashAttribute("enroll_result", oneclass.getClass_name());
    	
    	return "redirect:/admin/classManage";
    }
    
    
    
    
    /* 작가 관리 페이지 접속 */
    @RequestMapping(value = "creatorManage", method = RequestMethod.GET)
    public void authorManageGET() throws Exception{
        logger.info("작가 관리 페이지 접속");
    }
    
    /* 첨부 파일 업로드 */
    @PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {
    	
    	System.out.println("uploadAjaxActionPOST..........");
    	
    	/* 이미지 파일 체크 */
    	for(MultipartFile multipartFile: uploadFile) {
    		
    		File checkfile = new File(multipartFile.getOriginalFilename());
    		String type = null;
    		
    		try {
    			type = Files.probeContentType(checkfile.toPath());	
    			System.out.println("MIME TYPE: " + type);
    		}catch(IOException e) {
    			e.printStackTrace();
    		}
    		
    		if(!type.startsWith("image")) { //올린 파일이 이미지 아닐 때 404에러 반환
    			
    				List<AttachImageVO> list = null;
    				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    			
    		}
    		
    	} //for
    	
    	String uploadFolder = "/Users/HOON/upload/";
    	
    	/* 날짜 폴더 경로 */
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	
    	Date date = new Date();
    	
    	String str = sdf.format(date);
    	
    	String datePath = str.replace("-", File.separator);
    	
    	/* 폴더 생성 */
    	File uploadPath = new File(uploadFolder, datePath);
    	
    	if(uploadPath.exists() == false) {
    		uploadPath.mkdirs();
    	}
    	
    	/* 이미지 정보 담는 객체 */
    	List<AttachImageVO> list = new ArrayList();
    	
    	//향상된 for
    	for(MultipartFile multipartFile : uploadFile) {
    		
    		/* 이미지 정보 객체 */
    		AttachImageVO vo = new AttachImageVO();
    		
    		/* 파일 이름 */
    		String uploadFileName = multipartFile.getOriginalFilename();
    		vo.setFile_name(uploadFileName);
    		vo.setUpload_path(datePath);
    		
    		/* uuid 적용 파일 이름 */
    		String uuid = UUID.randomUUID().toString();
    		vo.setUuid(uuid);
    		
    		uploadFileName = uuid + "_" + uploadFileName;
    		
    		/* 파일 위치, 파일 이름을 합친 File 객체 */
    		File saveFile = new File(uploadPath, uploadFileName);
    		
    		/* 파일 저장 */
    		try {
    			multipartFile.transferTo(saveFile);
    			/* 썸네일 이미지 생성 */
    			/*
    			File thumbnailFile = new File(uploadPath, "s_" + uploadFileName); 
    			
    			BufferedImage bo_image = ImageIO.read(saveFile);
    			
    			//비율
    			double ratio = 3;
    			//넓이 높이
    			int width = (int)(bo_image.getWidth()/ ratio);
    			int height = (int)(bo_image.getHeight()/ ratio);
    			
    			
    			
    			BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR); //넓이, 높이, 생성될 이미지 타입
    			
    			Graphics2D graphic = bt_image.createGraphics();
    			
    			graphic.drawImage(bo_image, 0, 0, width, height, null);
    			
    			ImageIO.write(bt_image, "jpg", thumbnailFile);
    			*/
    			
    			/* 방법2 */
    			File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
    			
    			
    			BufferedImage bo_image = ImageIO.read(saveFile);
    			
    			//비율
    			double ratio = 3;
    			//넓이 높이
    			int width  = (int) (bo_image.getWidth()  / ratio);
    			int height = (int) (bo_image.getHeight() / ratio);
    			
    			
    			
    			Thumbnails.of(saveFile)
    			.size(width, height)
    			.toFile(thumbnailFile);
    			
    			
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    		list.add(vo);
    	
    	}// for
    	
    	ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
    	
    	return result;
    	/*
    	기본 for
    	for(int i = 0; i< uploadFile.length; i++) {
    		
    	} 
    	*/
    	
    	
    	
    	
    	
    	
    }
    
    /* 이미지 파일 삭제 */
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String file_name){
		
		System.out.println("deletFile......." + file_name);
		
		File file = null;
		
		try {
			/* 썸네일 파일 삭제*/
			file = new File("/Users/HOON/upload/" + URLDecoder.decode(file_name,"UTF-8"));
			
			file.delete();
			
			/* 원본 파일 삭제*/
			String originFileName = file.getAbsolutePath().replace("s_", "");
			
			System.out.println("originFileName : " + originFileName);
			
			file = new File(originFileName);
			
			file.delete();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
		} //catch
		
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	

}
