/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ewawuye
 */
@Controller
public class PageController {
    
    
      @RequestMapping(value = "/")
    public String home(Model m) {

        return "adminmaster";//JSP
    }

    @RequestMapping("/department")
    public String departmentget() {
        return "department";
    }
   @RequestMapping("/catagory")
    public String ctagoryget() {
        return "catagory";
    }
    @RequestMapping("/organization")
    public String organizationget() {
        return "manageorganization";
    }
    @RequestMapping(value = {"/home",  "/index", "/dashboard"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("adminmaster");
        mv.addObject("title", "AdminHome");
        mv.addObject("AdminClickDashboard", true);
        return mv;
    }
     @RequestMapping(value = "/loginPage",method = RequestMethod.GET)
     public ModelAndView loginPage(){
       ModelAndView mv = new ModelAndView("loginPage");
  
        return mv;
     }
     
    	
  @RequestMapping(value = "/access-denied",method = RequestMethod.GET)
	public String accessDenied(){
		return "access-denied";
	}
}
