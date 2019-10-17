/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.controller;

import com.zema.isms.domain.Department;
import com.zema.isms.domain.Employee;
import com.zema.isms.service.DepartmentService;
import com.zema.isms.service.EmployeeService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import org.springframework.web.servlet.ModelAndView;



/**
 *
 * @author ewawuye
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
     
    @Autowired
    private DepartmentService departmentservice;
    
        @Autowired
    private EmployeeService empservice;
        
    // add an initbinder ... to convert trim input strings
    // remove leading and trailing whitespace
    // resolve issue for our validation
        @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

   
    
     @RequestMapping(value = "/add", method = RequestMethod.GET)
     public String getEmployeeReg(Model m)
            
     {
    List<Department> depts =departmentservice.getDepartmentList();
        Employee emp = new Employee();
        
      m.addAttribute("employee", emp);
         
         m.addAttribute("departments",depts );
      
       
        return "employee";  //jsp
     }
     @RequestMapping( value ="/emp_registration", method = RequestMethod.POST)
    public String employeereg(@Valid @ModelAttribute("employee")  Employee emp,BindingResult result, Model m) throws NullPointerException, IOException  {
           if(result.hasErrors()){
               return "employee";
           }
        empservice.registerEmployee(emp.getDepartment().getDepartmentId(),emp);
        //m.addAttribute("emp1", emp1.getCode() + "allready exist" );
       
 return "redirect:/employee/add";
    }
    @RequestMapping(value = { "/employeeImage" }, method = RequestMethod.GET)
    public void employeeImage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam("employeeId") String employeeId) throws IOException {
        
            Employee emp=  empservice.searchByEmployeeId(employeeId);
        
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
           // response.getOutputStream().write(emp.getPhoto());
        
        response.getOutputStream().close();
    }
    
 
    
    @RequestMapping(value = "/searchEmp")
    public ModelAndView getSearchemp(){
        ModelAndView mv = new ModelAndView("searchEmployee");
        return mv;
    }
     @RequestMapping(value = "/searchEmpbyName",method = RequestMethod.GET)
   
    public String searchByNameEmployee(Model m,@RequestParam("searchCode") String fname){
      
        
        Employee emp = empservice.searchByEmployeeCode(fname);
      
        m.addAttribute("emp1", emp);
       
        return "searchEmployee";
    }
    
    @RequestMapping(value ="/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") String employeeId){
        empservice.removeEmployee(employeeId);
        
        return "manageEmployee";
    }
    // get asset assigned to employee
     @RequestMapping(value = "/getAssetWithemp")
     public String searchAssetWithEmployee(@RequestParam String  employeeId,Model m){
         Employee emp =  empservice.searchEmployeeWithAsset(employeeId);
         m.addAttribute("empAsset",emp);
       
         
         return "manageAsset";
     }
     
    
  
}
