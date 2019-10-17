/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.controller;


import com.zema.isms.domain.Asset;
import com.zema.isms.domain.AssignedAsset;

import com.zema.isms.domain.Catagory;
import com.zema.isms.domain.Department;
import com.zema.isms.domain.Employee;
import com.zema.isms.domain.SystemRole;
import com.zema.isms.dto.AssetDto;
import com.zema.isms.dto.UserDto;

import com.zema.isms.service.AssetService;

import com.zema.isms.service.CatagoryService;
import com.zema.isms.service.DepartmentService;
import com.zema.isms.service.EmployeeService;
import java.util.List;
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
@RequestMapping("/asset")
public class AssetController {
 public final int Assets_PER_PAGE = 4;
    @Autowired //user service interface  
    private AssetService assetservice;
    @Autowired
    private CatagoryService catagoryservice;
     @Autowired
    private EmployeeService employeeservice;
       @Autowired
    private DepartmentService deptservice;
    
    // add an initbinder ... to convert trim input strings
    // remove leading and trailing whitespace
    // resolve issue for our validation

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping(value = "/asset_reg")
    public String assetregistrationForm(Model m) {
        AssetDto a_cmd = new AssetDto();
        List<Catagory> catagorylist = catagoryservice.getCatagoryList();
        m.addAttribute("command", a_cmd);
        m.addAttribute("catagorylist", catagorylist);
        return "asset";//JSP
    }

    @RequestMapping(value = "/asset_manage")
    public String assetmanageForm(Model m) {

        return "manageAsset";//JSP
    }

    @RequestMapping(value = "/asset_warranty")
    public String assetwarratyForm(Model m) {

        return "warranty";//JSP
    }

    @RequestMapping(value = "/seacrhInfo")
    public String assetSearchForm(Model m) {

        return "search";//JSP
    }

    @RequestMapping(value = "/asset_registration")
    public String assetreg(@Valid @ModelAttribute("command") AssetDto ato, BindingResult result, Model m) {

        if (result.hasErrors()) {
            return "asset";
        } else {
            Asset asset = convertAssetDtoToAssetEntity(ato);
            assetservice.registerAsset(ato.getCatagory().getCatagoryId(),asset);
            return "redirect:asset_reg?act=asset_reg";//JSP
        }

    }

    @RequestMapping(value = "/deleteAsset")
    public String deleteAsset(@RequestParam("assetId") String assetId) {

        assetservice.removeAsset(assetId);
        return "financeInfo";
    }

    @RequestMapping(value = "/assetlist", method = RequestMethod.GET)
    public String getAssetList(Model m) {
        List<Asset> assetlist = assetservice.getAssetList();

        m.addAttribute("assetlist", assetlist);
        return "warranty"; //JSP
    }
    // get assign asset page
    @RequestMapping(value = "/assignAssetPage")
    public ModelAndView getAssignPge(@RequestParam("employeeId")String employeeId){
        ModelAndView mv = new ModelAndView("manageEmployee");
        mv.addObject("employeeId",employeeId);
        return mv;
    }
    //----------add asset toemployee ----//
    @RequestMapping(value ="/addAssetToEmployee")
    public String addAssetToEmployee(@RequestParam("assetCode")String assetCode,@RequestParam("employeeId") String employeeId, Model m){
        Asset asset = assetservice.searchByAssetCode(assetCode);
        Employee  emp = employeeservice.searchByEmployeeId(employeeId);
        boolean check =asset.isAssigned();
       if(check){
           m.addAttribute("assigndMessage", "asset is alrdy asiign");
       }
       
        assetservice.addAssetToEmp(asset.getAssetId(),emp.getEmployeeId());
   //  Employee  emp1 =  employeeservice.searchEmployeeWithAsset(employeeId);
   
        return "manageEmployee";
    }
    //--------- remove asset from employee -----//
    @RequestMapping(value = "/getEmpAssets")

    public String getAllEmployeeAsset(@RequestParam("empCode")String employeeCode,Model m){
               Employee  emp = employeeservice.searchByEmployeeCode(employeeCode);
               List<Asset> assets =  assetservice.searchEmployeeAsset(emp.getEmployeeId());
                  double sum = 0;
                 for (Asset a :assets){
                sum=sum+a.getFinanceInfo().getAssetValue();         
                    }
               m.addAttribute("currentOwner", emp);
               m.addAttribute("employeeAsset", assets);
               m.addAttribute("total", sum);
               return "employeeAsset";
    }
    
    
 @RequestMapping(value = "/removeEmpAsset")
 public String removeEmployeeAsset(@RequestParam("assetId")String assetId,Model m){
        Asset asset= assetservice.searchByAssetId(assetId);
          Employee emp = asset.getEmployee();
         assetservice.removeAsset(assetId);
         String employeeId = emp.getEmployeeId();
          List<Asset> assets =  assetservice.searchEmployeeAsset(employeeId);
          m.addAttribute("employeeAsset", assets);
       return "employeeAsset";
 }
 // for assign task
 @RequestMapping(value = "/assignPage", method = RequestMethod.GET)
 public String getAssignmentPage( Model m ,@RequestParam(value ="assetCode", required = false) String assetCode,@RequestParam(value = "code", required = false) String employeeCode){
      AssignedAsset aa = new   AssignedAsset ();
      m.addAttribute("command", aa);
      m.addAttribute("code", employeeCode);
     return "asignPage";
 }
 // assign asset to emloyee
 @RequestMapping(value = "/assignAssetToEmployee",method = RequestMethod.POST)
 public String registerAssignedAsset(Model m,@ModelAttribute("command")AssignedAsset aa,@RequestParam(value ="assetCode", required = false) String assetCode,
       @RequestParam(value = "code", required = false) String employeeCode){
     
     Asset asset =  assetservice.searchByAssetCode(assetCode);
     if(asset == null){
         AssignedAsset a = new   AssignedAsset ();
         m.addAttribute("command", a);
         m.addAttribute("message", "asset with" + "\t"+ assetCode+ "\t"+ "is not found");
         return "asignPage";
     }
 //   boolean isAssigned =asset.isAssigned();
     if( !asset.isAssigned()){
         Employee emp = employeeservice.searchByEmployeeCode(employeeCode);
         if(emp==null){
            m.addAttribute("empMessage","employee with " +"\t"+ employeeCode + "\t"+"not found");
               }
         assetservice.assignAssetToemployee(aa, emp.getEmployeeId(), asset.getAssetId());
        
     return "redirect:assignPage?act=assigned";
        
     }
     else{
         
          return "redirect:assignPage?act=error";
     }
 }
     // return asign page
    @RequestMapping(value = "/employee_search",method = RequestMethod.GET)
   public ModelAndView searchEmployee(@RequestParam("searchText")String searchText){
         ModelAndView mv =new ModelAndView("asignPage");
               Employee emps = employeeservice.searchByEmployeeCode(searchText);
               
               if(emps==null){
                               mv.addObject("empMessage","employee with " +"\t"+ searchText + "\t"+" not found");
               }
              AssignedAsset aa = new   AssignedAsset ();
              mv.addObject("command", aa);
               mv.addObject("emps",emps);
               
        return mv;  
       

   }
   //  show employee asset by employee id  
   @RequestMapping(value = "/showEmployeAsset",method = RequestMethod.GET)
   public ModelAndView  retriveEmployeeAssets(@RequestParam(value = "employeeId",required = false) String employeeId
){
        ModelAndView mv =new ModelAndView("employeeAsset");
        int assignAssetResultsCount = 0;
       int assignAssetPageCount = 0;
    
        List<AssignedAsset>  assignedAssets = assetservice.retriveAllAssignedAssetByEmployeeCode(employeeId);
   
        mv.addObject("assignedList", assignedAssets);
       assignAssetResultsCount = assetservice.searchAssignAssetCountByEmployee(employeeId);
       mv.addObject("resultsCount", assignAssetResultsCount);
      
//       assignAssetPageCount = assetservice.searchAssignedAssetPageCountByEmployee(employeeId,Assets_PER_PAGE);
//       mv.addObject("pageCount", assignAssetPageCount);
        return mv;
   }
     //  show assigned asset by department,catagory,or both 
   @RequestMapping(value = "/searchAssignedAsset",method = RequestMethod.GET)
   public ModelAndView  SerchAssignedAssets(@RequestParam(value = "deptName", required = false) String name,
           @RequestParam(value = "catName", required = false) String catname, @RequestParam(value = "pageNo", required = false)
         Integer  pageNo){
       
        ModelAndView mv =new ModelAndView("assignedAsset");
       Catagory cat = null;
       String catagoryId = null;
       Department dept = null;
       String departmentId = null;
       int assignAssetResultsCount = 0;
       int assignAssetPageCount = 0;
       
       List<AssignedAsset> assignedAssets = null;

         
             
         if(catname!=null && name!= null ){
               if(pageNo == null){
                 pageNo = 1;
             mv.addObject("pageNo", (int) pageNo);
              }
             cat = catagoryservice.searchCategoryByName(catname);
           
             catagoryId = cat.getCatagoryId();
             dept =deptservice.searchDepartmentByName(name);
             departmentId= dept.getDepartmentId();
             assignAssetResultsCount = assetservice.searchAssignedAssetCountByDepartmentAndcatagoryId(departmentId, catagoryId);
	
		mv.addObject("resultsCount", 	assignAssetResultsCount);
		assignAssetPageCount = assetservice.searchAssignedAssetPageCountByDepartmentAndcatagoryId(departmentId, catagoryId,Assets_PER_PAGE );
	           mv.addObject("pageCount", 	assignAssetPageCount );
              assignedAssets = assetservice.retriveAllAssignedAssetByCatagoryAndDEpartment(catagoryId, departmentId,pageNo,Assets_PER_PAGE );
          }
      // searh assigned asset by catagory
          if(catname!=null && name == null){
              
        cat = catagoryservice.searchCategoryByName(catname);
       
         catagoryId = cat.getCatagoryId();
         if(  catagoryId== null){
             mv.addObject("message", "No result with" +catname+ "");
            assignedAssets = null;
         }
         if (catagoryId !=null){
             
		assignAssetResultsCount = assetservice.searchAssignedAssetCountByCatagory(catagoryId);
	
		mv.addObject("resultsCount", 	assignAssetResultsCount);
		assignAssetPageCount = assetservice.searchAssignedAssetPageCountByCatagory(catagoryId,Assets_PER_PAGE);
	           mv.addObject("pageCount", 	assignAssetPageCount );
               assignedAssets  = assetservice.retriveAllAssignedAssetByCatagory(catagoryId,pageNo,Assets_PER_PAGE );
         }
         
      }
          if(name!= null && catname ==null){ 
            
             dept =deptservice.searchDepartmentByName(name);
      
           departmentId= dept.getDepartmentId();
           if(departmentId == null){
                mv.addObject("message", "No result with" +name+ "");
                assignedAssets = null;
           }
          // searh assigned asset by department
          if(departmentId!=null){
              
		assignAssetResultsCount = assetservice.searchAssignedAssetCountByDepartment(departmentId);
	
		mv.addObject("resultsCount", 	assignAssetResultsCount);
		assignAssetPageCount = assetservice.searchAssignedAssetPageCountByDepartment(departmentId,Assets_PER_PAGE);
	           mv.addObject("pageCount", 	assignAssetPageCount );
       assignedAssets = assetservice.retriveAllAssignedAssetByDepartment(departmentId,pageNo,Assets_PER_PAGE );
          }
      }
      
         // assignedAssets =  assetservice.retriveAllAssigedAsset();
         
       // int empAssetCountResult = assetservice.searchAssignedAssetCountByEmployee(employeeId);
        //mv.addObject("empAssetCount", empAssetCountResult);
        mv.addObject("assignedList", assignedAssets);
        return mv;
   }
   // show assigned assets asset
   @RequestMapping(value = "/showAssignedAssets", method = RequestMethod.GET)
   public ModelAndView  retriveAllAssignedAssets(@RequestParam(value = "pageNo", required = false)Integer pageNo){
      ModelAndView mv =new ModelAndView("assignedAsset");
        int assignAssetResultsCount = 0;
       int assignAssetPageCount = 0;
          List<AssignedAsset>  assignedAssets =null;
    
         if(pageNo == null){
             pageNo=1;
             mv.addObject("pageNo", (int) pageNo);
                    assignAssetResultsCount = assetservice.searchAssignAssetCount();
       mv.addObject("resultsCount", assignAssetResultsCount);
       assignAssetPageCount = assetservice.searchAssignedAssetPageCount(Assets_PER_PAGE);
       mv.addObject("pageCount", assignAssetPageCount);
       assignedAssets =  assetservice.retriveAllAssigedAsset(pageNo,Assets_PER_PAGE);
             
         }
         else {
        assignAssetResultsCount = assetservice.searchAssignAssetCount();
       mv.addObject("resultsCount", assignAssetResultsCount);
       assignAssetPageCount = assetservice.searchAssignedAssetPageCount(Assets_PER_PAGE);
       mv.addObject("pageCount", assignAssetPageCount);
       assignedAssets =  assetservice.retriveAllAssigedAsset(pageNo,Assets_PER_PAGE);
         }
 
      

    
     mv.addObject("assignedList",assignedAssets );
   
      return mv;
   }
      // return assigned assets asset
   @RequestMapping(value = "/returnAssignedAssets", method = RequestMethod.GET)
   public ModelAndView  returnAssignedAssets(@RequestParam(value ="assignedId")String id,@RequestParam(value ="employeeId")String employeeId){
      ModelAndView mv =new ModelAndView("employeeAsset");
        int assignAssetResultsCount = 0;
       int assignAssetPageCount = 0;
        assetservice.returnAsset(id);
   List<AssignedAsset>  assignedAssets = assetservice.retriveAllAssignedAssetByEmployeeCode(employeeId);
     
        assignAssetResultsCount = assetservice.searchAssignAssetCountByEmployee(employeeId);
       mv.addObject("resultsCount", assignAssetResultsCount);
       assignAssetPageCount = assetservice.searchAssignedAssetPageCountByEmployee(employeeId,Assets_PER_PAGE);
       mv.addObject("pageCount", assignAssetPageCount);
       mv.addObject("assignedList", assignedAssets);
  
      return mv;
   }
   
 private Asset convertAssetDtoToAssetEntity(AssetDto ao){
     Asset assetEntity = new Asset();
     assetEntity.setAssetName(ao.getAssetName());
     assetEntity.setAssetCode(ao.getAssetCode());
     assetEntity.setBrand(ao.getBrand());
     assetEntity.setCurrentCondition(ao.getCurrentCondition());
     assetEntity.setQualityCondition(ao.getQualityCondition());
     assetEntity.setModel(ao.getModel());
     assetEntity.setDescription(ao.getDescription());
     assetEntity.setManufacturer(ao.getManufacturer());
     assetEntity.setCatagory(ao.getCatagory());
      assetEntity.setPrice(ao.getPrice());
     return assetEntity;
 }
      @RequestMapping(value = "/search_emp", method = RequestMethod.GET)
    public ModelAndView Searchemployee(@RequestParam(name = "firstName",required = false)String firstName,@RequestParam(name = "middleName",required = false)String middleName,@RequestParam(name = "lastName",required = false)String lastName){
         ModelAndView mv = new ModelAndView("employeeAsset");
          List<Employee> empList = null;
         if(firstName !=null){
            empList = employeeservice.getEmployeeByFirstName(firstName);
         }
           if(middleName !=null){
               empList = employeeservice.getEmployeeByMiddleName(middleName);
           }
         if(lastName !=null){
               empList = employeeservice.getEmployeeByLastName(lastName);
           }
        
        
    
         mv.addObject("empList",empList);
       
         
       
         return mv;
    }
      @RequestMapping(value = "/search_empPage", method = RequestMethod.GET)
    public ModelAndView getEmployeeAssetPage(){
         ModelAndView mv = new ModelAndView("employeeAsset");
          return mv;
    }
}
