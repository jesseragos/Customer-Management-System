package com.cms.utils;

public enum ImageDir {

		//	IMAGE FILE DIRECTORIES
	LOGOMAINUI ("../CASE STUDY - CMS/src/com/cms/files/images/mobility_banner_cms_icon.png"),
	BANNER_CMS ("../CASE STUDY - CMS/src/com/cms/files/images/mobility_banner_cms.png"),
	BANNER ("../CASE STUDY - CMS/src/com/cms/files/images/mobility_banner.png"),
	ICON ("../CASE STUDY - CMS/src/com/cms/files/images/mobility_icon_official.png"),
	BG1 ("../CASE STUDY - CMS/src/com/cms/files/images/mobility_BG1.png"),
	BG2 ("../CASE STUDY - CMS/src/com/cms/files/images/mobility_BG2.png"),
	BG3 ("../CASE STUDY - CMS/src/com/cms/files/images/mobility_BG3.png"),
	BG4 ("../CASE STUDY - CMS/src/com/cms/files/images/mobility_BG4.png");
	
	
	private String fileDir;
	
	ImageDir(String fileDir){
		this.fileDir = fileDir;
	}
	
	public String dir(){
		return fileDir;
	}
	
}
