/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.web.model;

import com.mdmserver.model.Account;
import com.mdmserver.model.AppPackage;
import java.util.List;

/**
 *
 * @author avinashk
 */
public class AppAnalyticsRequest {
    
    private List<AppPackage> appPackages;
    private Account account;
    
    public AppAnalyticsRequest(){
    
    }
    
    public List<AppPackage> getAppPackages() {
        return appPackages;
    }

    public void setAppPackages(List<AppPackage> appPackages) {
        this.appPackages = appPackages;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    
    
}

  
