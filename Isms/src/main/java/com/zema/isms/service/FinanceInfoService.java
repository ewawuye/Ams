/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.service;

import com.zema.isms.domain.finance.FinanceInformation;

/**
 *
 * @author ewawuye
 */
public interface FinanceInfoService {
     /**
     * 
     * @param fi
     */
   public  void registerFinanceInformation(String assetId,FinanceInformation fi);
   /**
    * 
    * @param fi
    */
    public void editFinanceInformation(FinanceInformation fi);
  /**
   * 
   * @param valueId 
   */
    public void  removeFinanceInformation(String valueId );
   /**
    * 
    * @param valueId
    * @return 
    */
    public FinanceInformation searchByFinanceInformationId(String valueId );
}
