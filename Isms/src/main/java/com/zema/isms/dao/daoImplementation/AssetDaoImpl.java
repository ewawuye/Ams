/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.dao.daoImplementation;

import com.zema.isms.dao.AssetDao;

import com.zema.isms.domain.Asset;
import com.zema.isms.domain.AssignedAsset;

import com.zema.isms.domain.Employee;


import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
/**
 *
 * @author ewawuye
 */
@Repository
public class AssetDaoImpl  implements AssetDao{

     @Autowired
   private SessionFactory sessionFactory;
    @Override
    public void saveAsset(Asset a) {
      
       sessionFactory.getCurrentSession().save(a);
    }

    @Override
    public void updateAsset(Asset a) {
     sessionFactory.getCurrentSession().update(a);
         
    }

    @Override
    public void deleteAsset(Asset a) {
        this.deleteAsset(a.getAssetId());
    }

    @Override
    public void deleteAsset(String assetId) {
        sessionFactory.getCurrentSession().delete(this);
    }

    @Override
    public Asset findByAssetId(String assetId) {
         Session session = sessionFactory.getCurrentSession();
     Asset asset =   session.get(Asset.class, assetId);
     return asset;
         
    }

    @Override
    public Asset findByAssetCode(String assetCode) {
        
        
        Asset asset = null;
        try{
    Session session = sessionFactory.getCurrentSession();
    
    String hql="from Asset a where a.assetCode LIKE '%"+assetCode+"%'";
      Query query = session.createQuery(hql,Asset.class);
      asset = (Asset) query.getSingleResult();
     }
        catch(NoResultException nre) {
			
        }
        return asset;
    }

    @Override
    public List<Asset> findAllAsset() {
          Session session = sessionFactory.getCurrentSession();
          String hql="from Asset";
          Query<Asset> query = session.createQuery(hql, Asset.class);
          List<Asset> assetList = query.list();
          
         return assetList;
    }

    @Override
    public List<Asset> findByProperty(String propName) {
       Session session = sessionFactory.getCurrentSession();
       String hql  = "FROM Asset a WHERE a.assetName LIKE '%"+propName+"%' OR a.assetCode LIKE '%"+propName+"%' OR a.brand LIKE '%"+propName+"%')"; 
       Query query = session.createQuery(hql);
       List<Asset> assetList = query.list();
          
        return assetList;
    }
     @Override
    public List<Asset> findEmployeeAsset(String employeeId) {
     Session session = sessionFactory.getCurrentSession();
     String hql ="from Asset a LEFT JOIN a.employee e where a.employeeId =:employeeId AND e.employeeId =:employeeId";
     Query<Asset> query = session.createQuery(hql);
     query.setParameter("employeeId", employeeId);
    
      List<Asset> assets= query.getResultList();
        return assets;
     
 
    }
  
     @Override
    public void AssignAssetToEmployee(String assetId, String employeeId) {
          Session session = sessionFactory.getCurrentSession();
          Asset asset = session.get(Asset.class,assetId );
          Employee emp = session.get(Employee.class,employeeId);
          asset.setAssigned(true);
          asset.setEmployee(emp);
          emp.getAssets().add(asset);
          session.save(emp);
    }
/**
 * search asset by name or brand
 * @param name
 * @return 
 */
    @Override
    public Asset findAssetByName(String name) {
       Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<Asset> criteria = builder.createQuery(Asset.class);
    Root<Asset> from = criteria.from(Asset.class);
    criteria.where(builder.equal(from.get("name"), name));
    Query<Asset> query = session.createQuery(criteria);
    return query.uniqueResult();

    }

    @Override
    public Asset findAssetWithWarranty(String assetCode) {
        Asset asset = null;
        try{
       Session session = sessionFactory.getCurrentSession(); 
       String hql ="FROM Asset a LEFT JOIN FETCH a.warranty w LEFT JOIN FETCH w. documents d WHERE a.assetCode=:assetCode";
       Query<Asset> query = session.createQuery(hql,Asset.class);
       query.setParameter("assetCode", assetCode);
       asset = (Asset)query.uniqueResult();
        }
        catch(NoResultException ex){
            
        }
        
       return asset;
    }
/*
    retrive all assigned assets
    */
    @Override
    public List<AssignedAsset> getAllAssignedAssets(int pageNo, int resultperpage) {
         Session session = sessionFactory.getCurrentSession(); 
         String hql = "FROM AssignedAsset aa LEFT JOIN FETCH aa.asset a LEFT JOIN FETCH a.catagory c LEFT JOIN FETCH  aa.employee e LEFT JOIN FETCH e.department d";
         Query<AssignedAsset> query = session.createQuery(hql,AssignedAsset.class);
         query.setFirstResult((pageNo-1)*resultperpage);
          query.setMaxResults( resultperpage);
        List<AssignedAsset> assignedAssets = query.list();
         return assignedAssets;
    }
    /*
    retrive all assigned assets to  employee by employee code
    */

    @Override
    public List<AssignedAsset> getAllAssignedAssetToEmployee(String employeeId) {
          Session session = sessionFactory.getCurrentSession(); 
          String hql= "FROM AssignedAsset aa LEFT JOIN FETCH aa.asset a LEFT JOIN FETCH a.catagory c LEFT JOIN FETCH  "
                  + "aa.employee e LEFT JOIN FETCH e.department d WHERE e.employeeId=:employeeId";
          Query<AssignedAsset> query =session.createQuery(hql,AssignedAsset.class);
          query.setParameter("employeeId",employeeId);
          List<AssignedAsset> assignedAssets =query.list();
          return assignedAssets;
    }

    @Override
    public AssignedAsset getAssignedAssetWithAsset(String assignedId) {
        Session session = sessionFactory.getCurrentSession(); 
       String hql ="FROM AssignedAsset aa LEFT JOIN FETCH aa.asset a WHERE aa.id=:assignedId";
       Query<AssignedAsset> query = session.createQuery(hql,AssignedAsset.class);
       query.setParameter("assignedId", assignedId);
       AssignedAsset assignedAsset= (AssignedAsset)query.uniqueResult();
       return assignedAsset;
    }

    @Override
    public int getToalAssetcount() {
         Session session = sessionFactory.getCurrentSession(); 
      int totalAssetCount =0;
      try{
        
         String hql ="FROM Asset a";
          Query query = session.createQuery(hql);
            
         totalAssetCount=  query.getResultList().size();
      }
      catch(NoResultException e){
          
      }
      return totalAssetCount;
    }

    @Override
    public int getToalAssetcountByCategory(String categoryId) {
        Session session = sessionFactory.getCurrentSession(); 
       int toalAssetcount =0;
      try{
         
         String hql ="FROM Asset a  LEFT JOIN FETCH a.catagory c WHERE c.catagoryId =:categoryId";
          Query query = session.createQuery(hql);
            query.setParameter("categoryId", categoryId);
         toalAssetcount =  query.getResultList().size();
      }
      catch(NoResultException e){
          
      }
      return toalAssetcount;
    }

    @Override
    public int getToalUnAssignedAssetcount(boolean isAssigned) {
/* int count =0;
      try{
         Session session = sessionFactory.getCurrentSession(); 
         String hql ="FROM Asset a";
          Query query = session.createQuery(hql);
            
          count =  query.getResultList().size();
      }
      catch(NoResultException e){
          
      }*/
      return 0;
    }

    @Override
    public int getAssignedAssetCountByEmployee(String employeeId) {
         Session session = sessionFactory.getCurrentSession(); 
         int assignedAssetCount =0;
      try{
        
         String hql ="FROM AssignedAsset aa LEFT JOIN FETCH aa.employee e WHERE e.employeeId =:employeeId";
          Query query = session.createQuery(hql);
          query .setParameter("employeeId", employeeId);
            
          assignedAssetCount =  query.list().size();
      }
      catch(NoResultException e){
          
      }
      return assignedAssetCount;
    }

    @Override
    public int getAssignedAssetcount() {
        Session session = sessionFactory.getCurrentSession(); 
        int assignedAssetCount =0;
      try{
         
         String hql ="FROM AssignedAsset aa";
          Query query = session.createQuery(hql);
         assignedAssetCount =  query.getResultList().size();
      }
      catch(NoResultException e){
          
      }
      return  assignedAssetCount;
    }
 // check for new update this method is not work ass needed
    @Override
    public int getAssignedAssetByDepartment(String departmentId) {
         Session session = sessionFactory.getCurrentSession(); 
       int count =0;
      try{
        
         String hql ="FROM AssignedAsset aa LEFT JOIN FETCH aa.employee e LEFT JOIN FETCH e.department d "
                 + " WHERE d.departmentId =:departmentId";
          Query query = session.createQuery(hql);
          query .setParameter("departmentId", departmentId);
            
          count =  query.getResultList().size();
      }
      catch(NoResultException e){
          
      }
      return count;
    }

    @Override
    public int getAssignedAssetByCategory(String categoryId) {
         Session session = sessionFactory.getCurrentSession(); 
      int count =0;
      try{
        
         String hql ="FROM AssignedAsset aa LEFT JOIN FETCH aa.asset a LEFT JOIN FETCH a.catagory c "
                 + "WHERE c.catagoryId =:categoryId";
          Query query = session.createQuery(hql);
            query.setParameter("categoryId", categoryId);
          count =  query.getResultList().size();
      }
      catch(NoResultException e){
          
      }
      return count;
    }

    @Override
    public int getAssignedAssetByDepartmentAndCategory(String categoryId ,String departmentId) {
          Session session = sessionFactory.getCurrentSession(); 
        int count =0;
      try{
       
         String hql ="FROM AssignedAsset aa LEFT JOIN FETCH aa.employee e LEFT JOIN FETCH e.department d  LEFT JOIN FETCH aa.asset a LEFT JOIN FETCH a.catagory c "
                 + "WHERE c.catagoryId =:categoryId AND d.departmentId =:departmentId";
          Query query = session.createQuery(hql);
            query.setParameter("categoryId", categoryId);
            query.setParameter("departmentId", departmentId);
          count =  query.getResultList().size();
      }
      catch(NoResultException e){
          
      }
      return count;
    }

    @Override 
    public List<Asset> findAsssetByCategory(String catagoryId,boolean assigned) {
        
           List<Asset> assets = null;
          try{
                Session session = sessionFactory.getCurrentSession(); 
          String hql= "FROM Asset a LEFT JOIN FETCH  a.catagory c WHERE c.catagoryId=:catagoryId AND a.assigned=:assigned";
          Query<Asset> query =session.createQuery(hql,Asset.class);
          query.setParameter("catagoryId",catagoryId);
          query.setParameter("assigned",assigned);
          
         assets =query.list();
          }
          catch(NoResultException ex ){
              
          }
          return assets;
    }

    @Override
    public List<AssignedAsset> findAssignedAssetByDepartment(String departmentId,int pageNo, int resultperpage) {
       Session session = sessionFactory.getCurrentSession(); 
           List<AssignedAsset> assignedAssets = null;
           try{
          String hql= "FROM AssignedAsset aa LEFT JOIN FETCH aa.asset a LEFT JOIN FETCH a.catagory c LEFT JOIN FETCH  "
                  + "aa.employee e LEFT JOIN FETCH e.department d WHERE d.departmentId=:departmentId";
          Query<AssignedAsset> query =session.createQuery(hql,AssignedAsset.class);
          query.setParameter("departmentId",departmentId);
          query.setFirstResult((pageNo-1)*resultperpage);
          query.setMaxResults(resultperpage);
          assignedAssets =query.list();
           }
           catch(NoResultException ex){
               
           }
          return assignedAssets;
    }

    @Override
    public List<AssignedAsset> findAssignedAssetByDepartmentAndCategory(String categoryId, String departmentId,int pageNo, int resultperpage) {
        Session session = sessionFactory.getCurrentSession(); 
            List<AssignedAsset> assignedAssets = null;
            try{
          String hql= "FROM AssignedAsset aa LEFT JOIN FETCH aa.asset a LEFT JOIN FETCH a.catagory c LEFT JOIN FETCH  "
                  + "aa.employee e LEFT JOIN FETCH e.department d WHERE c.catagoryId=:categoryId AND d.departmentId =: departmentId";
          Query<AssignedAsset> query =session.createQuery(hql,AssignedAsset.class);
          query.setParameter("categoryId",categoryId);
          query.setParameter("departmentId", departmentId);
           query.setFirstResult((pageNo-1)*resultperpage);
          query.setMaxResults(resultperpage);
         assignedAssets =query.list();
            }
            catch(NoResultException ex ){
                
            }
          return assignedAssets;
    }

    @Override
    public List<AssignedAsset> findAssignedAssetByCategory(String catagoryId,int pageNo, int resultperpage) {
         Session session = sessionFactory.getCurrentSession(); 
            List<AssignedAsset> assignedAssets=null;
            try{
          String hql= "FROM AssignedAsset aa LEFT JOIN FETCH aa.asset a LEFT JOIN FETCH a.catagory c LEFT JOIN FETCH  "
                  + "aa.employee e LEFT JOIN FETCH e.department d WHERE c.catagoryId=:catagoryId";
          Query<AssignedAsset> query =session.createQuery(hql,AssignedAsset.class);
          query.setParameter("catagoryId",catagoryId);
           query.setFirstResult((pageNo-1)*resultperpage);
          query.setMaxResults(resultperpage);
         assignedAssets =query.list();
            }
            catch(NoResultException ex){
                
            }
          return assignedAssets;
    }

   

  
    
    
}
