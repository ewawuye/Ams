/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.dao;

import com.zema.isms.domain.Asset;
import com.zema.isms.domain.AssignedAsset;
import java.util.List;

/**
 *
 * @author ewawuye
 */
public interface AssetDao {
    public void saveAsset (Asset a);

    public void updateAsset (Asset a);

    public void deleteAsset (Asset a);

    public void deleteAsset (String AssetId);

    public Asset     findByAssetId(String AssetId);
    
    public Asset     findByAssetCode(String AssetCode);

    public List<Asset> findAllAsset ();
    
    
    public List<Asset> findAsssetByCategory(String catagoryId,boolean assigned);
    public List<Asset> findByProperty(String propName);

    public List<Asset> findEmployeeAsset(String employeeId);

    public void AssignAssetToEmployee(String assetId, String employeeId);// 
    public Asset findAssetByName(String name);

    public Asset findAssetWithWarranty(String assetCode);
    public List<AssignedAsset> getAllAssignedAssets(int pageNo, int resultperpage);
    public List<AssignedAsset> getAllAssignedAssetToEmployee(String employeeId);  
    public List<AssignedAsset> findAssignedAssetByDepartment(String departmentId,int pageNo, int resultperpage);
    public List<AssignedAsset> findAssignedAssetByCategory(String catagoryId,int pageNo, int resultperpage);
    public List<AssignedAsset> findAssignedAssetByDepartmentAndCategory(String categoryId ,String departmentId,int pageNo, int resultperpage);
    public AssignedAsset getAssignedAssetWithAsset(String assignedId);
    //get total asset count
    public int getToalAssetcount();
        //get total asset count by catagory
    public int getToalAssetcountByCategory(String categoryId);
     //get total un assigned asset count 
    public int getToalUnAssignedAssetcount(boolean isAssigned);
    
 // get assigned asset count by employee
    public int getAssignedAssetCountByEmployee(String employeeId);
 // get assigned asset total count 
    public int getAssignedAssetcount();
// get assigned asset count by department
   public int getAssignedAssetByDepartment(String departmentId);
 // get assigned asset count by asset category
 public int getAssignedAssetByCategory(String categoryId);
 // get assigned asset count by asset category and department
 public int getAssignedAssetByDepartmentAndCategory(String categoryId ,String departmentId);

  
}
/*
also follow the following logic 
=> total un assigned asset =  getToalAssetcount() -getAssignedAssetcount();
=> total un assigned asset by category =  getToalAssetcountByCategory -getAssignedAssetByCategory;

*/

//get  