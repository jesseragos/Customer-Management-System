package com.cms.utils;

public enum ModuleCodes {

	MODC001 ("Customer Module"),
	RPT001 ("Customer List By State", "state code", "stateParam"),
	RPT002 ("Customer Payment History", "customer number", "custNoParam"),
	RPT003 ("Customer Sales History", "customer number", "custNoParam"),
	RPT004 ("Top Customer Sales");
	
	private String title, prompt, param;
	
	ModuleCodes(String title){
		this.title = title;
	}
	
	ModuleCodes(String title, String prompt, String param){
		this.title = title;
		this.prompt = prompt;
		this.param = param;
	}
	
	public String title(){
		return title;
	}
	
	public String prompt(){
		return prompt;
	}

	public String param(){
		return param;
	}
	
}
