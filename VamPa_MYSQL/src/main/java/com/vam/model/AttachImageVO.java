package com.vam.model;

import lombok.Data;

public class AttachImageVO {
	
	/* 경로 */
	private String upload_path;
	
	/* uuid */
	private String uuid;
	
	/* 파일 이름 */
	private String file_name;
	
	/* 상품 id */
	private int class_id;

	public String getUpload_path() {
		return upload_path;
	}

	public void setUpload_path(String upload_path) {
		this.upload_path = upload_path;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	@Override
	public String toString() {
		return "AttachImageVO [upload_path=" + upload_path + ", uuid=" + uuid + ", file_name=" + file_name
				+ ", class_id=" + class_id + "]";
	}
	
	

}
