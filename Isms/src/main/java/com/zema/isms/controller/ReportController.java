/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.controller;

import com.zema.isms.domain.Catagory;
import com.zema.isms.domain.Department;
import com.zema.isms.service.CatagoryService;
import com.zema.isms.service.DepartmentService;
import com.zema.isms.service.ReporrtService;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ewawuye
 */
@Controller
@RequestMapping("/report")
public class ReportController {
int pageNo =1;
final int  resultsPerpage = 8;
        @Autowired
    private DepartmentService departmentservice;
        
        @Autowired
    private CatagoryService catagoryservice;  
        
    @Autowired
    private ReporrtService reportservice;
    
    
    @RequestMapping(value = "/report", method = RequestMethod.GET)
	public void report(HttpServletRequest request,HttpServletResponse response) throws Exception {
//		response.setContentType("application/pdf");
//                ServletOutputStream servletOutputStream = response.getOutputStream();
//		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportservice.report(pageNo,  resultsPerpage));
//	  InputStream ReportStream = getClass().getResourceAsStream("/report.jrxml");
//               	///// //InputStream reportStream = request.getSession().getServletContext().getResourceAsStream("com/zema/isms/report/report.jrxml");
//		JasperReport jasperReport = JasperCompileManager.compileReport(ReportStream);
//                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
//                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
//		//HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
//		//exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		//exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
//		//exporter.exportReport();
//                servletOutputStream.flush();
//                servletOutputStream.close();
	}
        @RequestMapping(value = "/manage_report",method = RequestMethod.GET )
        public String getManageReport(Model m){
             List<Department> depts =departmentservice.getDepartmentList();
                     m.addAttribute("departments",depts );
              List<Catagory> catagorylist = catagoryservice.getCatagoryList();
               Department dept = new     Department();
               Catagory cat = new  Catagory();
              m.addAttribute("catagorylist", catagorylist);
              m.addAttribute("dept", dept);
              m.addAttribute("catagory", cat);
             return "report";
            
        }
          @RequestMapping(value = "/storeAssetReport",method = RequestMethod.GET )
        public String getStroeReport(Model m){
           
              List<Catagory> catagorylist = catagoryservice.getCatagoryList();
             
               Catagory cat = new  Catagory();
              m.addAttribute("catagorylist", catagorylist);
            
              m.addAttribute("catagory", cat);
             return "storeReport";
            
        }
           
    @RequestMapping(value = "/reportByDepartment", method = RequestMethod.GET)
	public void reportByDepartment(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("dept") Department dept) throws Exception {
	Department dep = departmentservice.searchByDepartmentId(dept.getDepartmentId());
               response.setContentType("application/pdf");
                ServletOutputStream servletOutputStream = response.getOutputStream();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportservice.generateReportByDepartment(dep.getDepartmentId()));
		InputStream ReportStream = getClass().getResourceAsStream("/report.jrxml");
                //InputStream reportStream = request.getSession().getServletContext().getResourceAsStream("com/zema/isms/report/report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(ReportStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		//HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		//exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		//exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		//exporter.exportReport();
                servletOutputStream.flush();
                servletOutputStream.close();
	}
          @RequestMapping(value = "/reportByCatagory")
	public void reportByAssignedCatagory(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("catagory") Catagory cat) throws Exception {
	Catagory cata = catagoryservice.searchByCatagoryId(cat.getCatagoryId());
               response.setContentType("application/pdf");
                ServletOutputStream servletOutputStream = response.getOutputStream();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportservice.generateReportByCategory(cata.getCatagoryId()));
		InputStream ReportStream = getClass().getResourceAsStream("/reportByCatagory.jrxml");
                //InputStream reportStream = request.getSession().getServletContext().getResourceAsStream("com/zema/isms/report/report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(ReportStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		//HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		//exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		//exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		//exporter.exportReport();
                servletOutputStream.flush();
                servletOutputStream.close();
	}
            @RequestMapping(value = "assetInStoreReportByCatagory")
	public void reportByAssetInStoreCatagory(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("catagory") Catagory cat) throws Exception {
	Catagory cata = catagoryservice.searchByCatagoryId(cat.getCatagoryId());
               response.setContentType("application/pdf");
                ServletOutputStream servletOutputStream = response.getOutputStream();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportservice.generateAssetInStoreReportByCategory(cata.getCatagoryId()));
		InputStream ReportStream = getClass().getResourceAsStream("/reportAssetInStore.jrxml");
                //InputStream reportStream = request.getSession().getServletContext().getResourceAsStream("com/zema/isms/report/report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(ReportStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		//HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		//exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		//exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		//exporter.exportReport();
                servletOutputStream.flush();
                servletOutputStream.close();
	}

}
