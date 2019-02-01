package com.cms.utils;

public enum ReportPDFDir {

		//	REPORT-RELATED FILE DIRECTORIES
	JRXML ("../CASE STUDY - CMS/src/com/cms/files/jrxml/%s.jrxml"),
	PDF ("C:/Users/hpp/Jkiel Workspaces/2BSCS/CASE STUDY - CMS/bin/com/cms/files/pdf/%s.pdf");
	// NOTE: Doesn't support direct directory of project
	
	private String fileDir;
	
	ReportPDFDir(String fileDir){
		this.fileDir = fileDir;
	}
	
	public String dir(){
		return fileDir;
	}
	
}
