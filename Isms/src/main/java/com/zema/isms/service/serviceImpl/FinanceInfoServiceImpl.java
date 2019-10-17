/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.service.serviceImpl;

import com.zema.isms.dao.AssetDao;
import com.zema.isms.dao.FinanceDao.FinanceInformationDao;
import com.zema.isms.domain.Asset;
import com.zema.isms.domain.finance.FinanceInformation;
import com.zema.isms.service.FinanceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ewawuye
 */
@Service
@Transactional
public class FinanceInfoServiceImpl implements FinanceInfoService
{
    
    @Autowired
    private FinanceInformationDao financeDao;
       @Autowired
 private  AssetDao asssetdao;
    @Override
    public void registerFinanceInformation(String assetId, FinanceInformation fi) {
       Asset asset =  asssetdao.findByAssetId(assetId);
       fi.setAsset(asset);
       financeDao.saveFinanceInformation(fi);
    }

    @Override
    public void editFinanceInformation(FinanceInformation fi) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeFinanceInformation(String valueId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FinanceInformation searchByFinanceInformationId(String valueId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
