/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.dao;

import com.j256.ormlite.dao.Dao;
import com.mdmserver.model.Account;
import com.mdmserver.model.AppPackage;
import java.util.List;

/**
 *
 * @author avin
 */
public interface AppPackageDao extends Dao<AppPackage, Integer>{
    
    public int updateAppPackages(Account account, List<AppPackage> appPackages);
    public List<AppPackage> getAppPackages(Account account);
    
}
