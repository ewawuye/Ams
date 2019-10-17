/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.domain;

import com.zema.isms.domain.finance.FinanceInformation;
import com.zema.isms.domain.waranty.Warranty;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ewawuye
 */
@Entity
public class Asset implements Serializable {

    @Id
     @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String assetId;
   
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "catagoryId")
    private Catagory catagory; // fk for  asset  
    @OneToOne(mappedBy ="asset",fetch = FetchType.LAZY)
    private Warranty warranty;
    @OneToOne(mappedBy ="asset")
   private FinanceInformation financeInfo;
    @ManyToOne
   @JoinColumn(name ="employeeId")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    @Column
    
    private String assetName;
    @Column
    private String description;
    @Column
    private String manufacturer;
    @Column
    
    private String brand;
    @Column(nullable =false, unique=true)
    
    private String assetCode; // fk must be unique like barcode id
    @Column
    private String model;
    @Column
   
    private String currentCondition; // in use ,  in  store
    @Column
    
    private String qualityCondition;//  new  ,good,used,slitly used
     @Column
    private Integer price;
   // @Column(nullable = false,columnDefinition = "boolean default true")
    private boolean assigned;
     @OneToOne(mappedBy ="asset",fetch = FetchType.LAZY)
    private AssignedAsset assignedAsset;

    public AssignedAsset getAssignedAsset() {
        return assignedAsset;
    }

    public void setAssignedAsset(AssignedAsset assignedAsset) {
        this.assignedAsset = assignedAsset;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
    

    public Warranty getWarranty() {
        return warranty;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }

    public FinanceInformation getFinanceInfo() {
        return financeInfo;
    }

    public void setFinanceInfo(FinanceInformation financeInfo) {
        this.financeInfo = financeInfo;
    }

    public String getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
    }

    public String getQualityCondition() {
        return qualityCondition;
    }

    public void setQualityCondition(String qualityCondition) {
        this.qualityCondition = qualityCondition;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Catagory getCatagory() {
        return catagory;
    }

    public void setCatagory(Catagory catagory) {
        this.catagory = catagory;
    }

   
    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    

}
