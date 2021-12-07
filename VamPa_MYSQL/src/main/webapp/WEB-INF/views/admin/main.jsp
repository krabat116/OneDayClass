<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href = "../resources/css/admin/main.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>  		<!-- Jquery url -->
  
</head>
<body>
	
	<div class="wrapper">
		<div class="wrap">
		    <!-- gnv_area -->
			<div class="gnb_top_wrap">
				<ul class="list">
					<li><a href="/main">메인 페이지</a></li>
					<li><a href="/main">로그아웃</a></li>
					<li>고객센터</li>
				</ul>
			</div>
			<!-- top_subject_area -->
			<div class="admin_top_wrap">
				<span>관리자 페이지</span>
			</div>
			<!-- contents-area -->
			<div class="admn_wrap">
				<!-- 네비영역 -->
			    <div class="admin_navi_wrap">
			    	<ul>
                        <li >
                            <a class="admin_list_01">상품 등록</a>
                        </li>
                        <li>
                            <a class="admin_list_02">상품 목록</a>
                        </li>
                        <lI>
                            <a class="admin_list_03">작가 등록</a>                            
                        </lI>
                        <lI>
                            <a class="admin_list_04">작가 관리</a>                            
                        </lI>
                        <lI>
                            <a class="admin_list_05">회원 관리</a>                            
                        </lI>                                                                                             
                    </ul>
				</div>
			</div>
		</div>
	</div>
	<div class="admin_content_wrap">
		<div>관리자 페이지 입니다.</div>
	</div>
	<div class="clearfix"></div>
</body>
</html>