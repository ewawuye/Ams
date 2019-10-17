/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.controller;

import com.zema.isms.domain.Employee;
import com.zema.isms.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ewawuye
 */
@Controller
public class EmployeeSearchController {
        @Autowired
    private EmployeeService empservice;
           // return manage page
    @RequestMapping(value = "/empManage",method = RequestMethod.GET)
    public ModelAndView getManageEmployee(){
        ModelAndView mv =new ModelAndView("manageEmployee");
     return mv;
    }
       // return manage page
    @RequestMapping(value = "/employee_search",method = RequestMethod.POST)
   public ModelAndView searchEmployee(@RequestParam("searchText")String searchText){
         ModelAndView mv =new ModelAndView("manageEmployee");
               Employee emps = empservice.searchByEmployeeCode(searchText);
               String empid= emps.getEmployeeId();
               if(empid==null){
                               mv.addObject("emp","employee not found");
               }
              
               mv.addObject("emps",emps);
        return mv;  
       

   }
}
