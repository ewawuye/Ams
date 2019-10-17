/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.dao.FinanceDao;

import com.zema.isms.domain.finance.FinanceInformation;
import java.util.List;

/**
 *
 * @author ewawuye
 */
public interface FinanceInformationDao {
    public void saveFinanceInformation(FinanceInformation fi);

    public void updateFinanceInformation(FinanceInformation fi);

    public void deleteFinanceInformation(FinanceInformation fi);

    public void deleteFinanceInformation(String FinanceInformationId);

    public FinanceInformation findByFinanceInformationId(String FinanceInformationId);

    public List<FinanceInformation> findAllFinanceInformation();

    public List<FinanceInformation> findByProperty(String propName, Object propValue);
}
