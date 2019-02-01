package com.cms.techServ;

import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;

import com.cms.domains.Module;
import com.cms.domains.User;
import com.cms.ui.GenerateReportsUI;
import com.cms.utils.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Reports {
	
	private Connection connection;
	private boolean isAdmin;
	private User userMain;
	
	public Reports(Connection connection, User userMain){
		this.connection = connection;
		this.userMain = userMain;
		isAdmin = AdminChecker.isAdmin(userMain);
	}
	
	public HashSet<ModuleCodes> getReportsList() {
		HashSet<ModuleCodes> reportList = new HashSet<ModuleCodes>();
		
		if(isAdmin) {
			for(ModuleCodes modCodes:ModuleCodes.values())
				if(modCodes.name().contains(GenerateReportsUI.reportCodeFmt))
					reportList.add(modCodes);
			
			
		} else for(Module mod:userMain.getListModule())
				for(ModuleCodes modCodes:ModuleCodes.values())
					if(modCodes.name().equals(mod.getModuleCode()) && 
							mod.getModuleCode().contains(GenerateReportsUI.reportCodeFmt) &&
							mod.getCanView())
						reportList.add(modCodes);
		
		return reportList;
	}
	
	public void viewParamReport(String reportName, String paramData, String paramName) throws Exception{
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(paramName, paramData);
		
		processReport(reportName, parameters);
	}
	
	public void viewReport(String reportName) throws Exception{
		processReport(reportName, null);
	}
	
	private void processReport(String reportName, HashMap<String, Object> parameters) throws Exception {
			
			String pdfDir = String.format(ReportPDFDir.PDF.dir(), reportName);
			
			JasperDesign jasperDesign = JRXmlLoader.load(String.format(ReportPDFDir.JRXML.dir(), reportName));
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
			JasperExportManager.exportReportToPdfFile(jasperPrint, pdfDir);
			
			
			//	Open file in runtime
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfDir);
			
	}


}
