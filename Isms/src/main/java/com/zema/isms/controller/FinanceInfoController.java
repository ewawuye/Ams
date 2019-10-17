/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.controller;

import com.zema.isms.domain.Asset;
import com.zema.isms.domain.finance.FinanceInformation;
import com.zema.isms.service.AssetService;
import com.zema.isms.service.FinanceInfoService;
import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ewawuye
 */
@Controller
@RequestMapping("/financeInfo")
public class FinanceInfoController {
     @Autowired   
    private    AssetService    assetservice;
     @Autowired   
    private FinanceInfoService finaceinfoservice;
    
    @RequestMapping(value = "/financeInfo_page", method = RequestMethod.GET)
  public String getFinaceInfoPage(Model m){
      
    FinanceInformation finance = new FinanceInformation();
  
        m.addAttribute("command", finance);
    return "financeInfo";
            }
  @RequestMapping(value = "/financeInfopage", method = RequestMethod.GET)
  public String getFinaceInfoWIthReg(@RequestParam("assetId") String assetId,Model m){
      
    FinanceInformation finance = new FinanceInformation();
  
        m.addAttribute("command", finance);
        m.addAttribute("assetId", assetId);
    return "financeInfo";
            }
  @RequestMapping(value = "/financeInfo", method = RequestMethod.POST)
  public String postFinanceInfo(@RequestParam("assetId") String assetId,@Valid @ModelAttribute("command") FinanceInformation financeInfo,BindingResult result)
  {
      if(result.hasErrors()){
              return "financeInfo";
      }
   finaceinfoservice.registerFinanceInformation(assetId, financeInfo);
      
  return "redirect:/financeInfo/financeInfo_page";
  }
   @RequestMapping(value = "/asset_search", method = RequestMethod.GET )
    public String contactSearch(Model m, @RequestParam("freeText") String freeText) {
       FinanceInformation finance = new FinanceInformation();
  
        m.addAttribute("command", finance);
        
        Asset  asset =assetservice.searchByAssetCode(freeText);
        if(asset == null){
                 m.addAttribute("message", "asset with" +"\t \t"+ freeText+ "is not faund");
                 return "financeInfo";
        }
        m.addAttribute("asset", asset );
        //String assetId = asset.getAssetId();
        //m.addAttribute("assetId", assetId );
        return "financeInfo";
    }
}
