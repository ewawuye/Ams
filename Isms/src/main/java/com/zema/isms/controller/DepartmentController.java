/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.controller;

import com.zema.isms.domain.Department;
import com.zema.isms.domain.Organization;

import com.zema.isms.service.DepartmentService;
import com.zema.isms.service.OrganizationService;


import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



/**
 *
 * @author ewawuye
 */

@Controller
@RequestMapping("/dept")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentservice;
    
     @Autowired
    private OrganizationService organizationservice;
    
       @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
 
     
     @RequestMapping(value = "/dept_add", method = RequestMethod.GET)
     public String getDepartmentReg(Model m)
            
     {
      List<Organization> orgs = organizationservice.getOrganizationList();
         Department dept = new Department();
          // list depts 
       List<Department> depts = departmentservice.getDepartmentList();
       m.addAttribute("deptList", depts );
      // add to model
      m.addAttribute("department", dept);
      
         
        
          m.addAttribute("organizations",orgs);
       
        return "department";  
     }
         @RequestMapping( value ="/dept_registration", method = RequestMethod.POST)
    public String postDepartmentreg(@Valid
            @ModelAttribute("department")  Department dept,BindingResult result, Model m,HttpSession session) {
              String departmentId = (String) session.getAttribute("departmentId");
              // save task
              if(departmentId == null){
                  
         
        if(result.hasErrors()){
            return "department";
        }
        else{
       //  Organization orgb = organizationservice.searchByOrganizationId(dept.getOrganization().getOrganizationId());
       //  String organizationId = orgb.getOrganizationId();
          departmentservice.registerDepartment(dept.getOrganization().getOrganizationId(), dept);
        return "redirect:/dept/dept_add";// redirect to above maapping
        }
              }
              //update taske
              else{
                  dept.setDepartmentId(departmentId);
                  departmentservice.editDepartment(dept);
                  session.removeAttribute(departmentId);
                  return "redirect:/dept/dept_add";
              }
    }
 
      @ModelAttribute("departmentlists")
    public List<Department> getorganizations(){
        return departmentservice.getDepartmentList();
    }
    /**
     * delete department
     * @return 
     */
     
    
    @RequestMapping(value ="/deleteDepatment")
    public String  deleteDepartment(@RequestParam("departmentId") String departmentId ){
        Department dept =  departmentservice.searchByDepartmentId(departmentId);
        departmentservice.removeDepartment(dept);
     return "redirect:/dept/dept_add";// redirect to above maapping
    }
   
    
    /**
     * get edit page
     * @param departmentId
     * @param m
     * @param session
     * @return 
     */
        @RequestMapping(value  ="/deptEditGet" , method = RequestMethod.GET)
  public String geteditDepartment(@RequestParam("departmentId") String departmentId,Model m,HttpSession session){
      // set  dept id in session
      session.setAttribute("departmentId",departmentId );
         
      // retrive dept by id
      Department dept = departmentservice.searchByDepartmentId(departmentId);
             
      // add to model
      m.addAttribute("department", dept);
      
      return "department";  // redirect to deparment page 
  }

    }





























