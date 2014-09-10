/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.web.model;

import com.mdmserver.model.AppPackage;
import java.util.List;

/**
 *
 * @author avinashk
 */
public class AppAnalyticsResponse extends Response{
    private List<AppPackage> appPackages;
    
    public AppAnalyticsResponse(){
        
    }

    public List<AppPackage> getAppPackages() {
        return appPackages;
    }

    public void setAppPackages(List<AppPackage> appPackages) {
        this.appPackages = appPackages;
    }
    
    
}
