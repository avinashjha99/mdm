/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.mdmserver.model.Account;
import com.mdmserver.model.AppPackage;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author avin
 */
public class AppPackageDaoImpl extends BaseDaoImpl<AppPackage, Integer> implements AppPackageDao{
    
    
    List<AppPackage> appPackages;
    Account account;
    
    public AppPackageDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, AppPackage.class);
    }

    @Override
    public int updateAppPackages(Account account, List<AppPackage> appPackages) {
        
        if(null==account){
            return AppPackage.RETURN_CODE_ERROR_DOES_NOT_EXIST;
        }
        try {
            this.appPackages= appPackages;
            this.account= account;
//            for(AppPackage pckg: appPackages){
//                pckg.setAccount(account);
//                create(pckg);
//            }
            
            callBatchTasks(new Callable<Void>() {

                @Override
                public Void call() throws Exception {
                    for(AppPackage packg :AppPackageDaoImpl.this.appPackages){
                     packg.setAccount(AppPackageDaoImpl.this.account);
                     create(packg);
                    }
                    return null;
                }
            });
           
        } catch (SQLException ex) {
            Logger.getLogger(AccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Account.RETURN_CODE_ERROR_UNSPECIFIED;
        }
        return AppPackage.RETURN_CODE_SUCCESS;

    }

    @Override
    public List<AppPackage> getAppPackages(Account account) {
        
        try {
            List<AppPackage> appPackages= queryBuilder().where().eq(AppPackage.QUERY_FOREIGN_COLUMN_ACCOUNT_ID, account).query();
            return appPackages;
        } catch (SQLException ex) {
            Logger.getLogger(AppPackageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
