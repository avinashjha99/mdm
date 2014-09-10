/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.managers;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.mdmserver.config.Config;
import com.mdmserver.dao.AccountDao;
import com.mdmserver.dao.AccountDaoImpl;
import com.mdmserver.dao.AppPackageDao;
import com.mdmserver.dao.AppPackageDaoImpl;
import com.mdmserver.dao.CallRecordDao;
import com.mdmserver.dao.CallRecordDaoImpl;
import com.mdmserver.model.Account;
import com.mdmserver.model.AppPackage;
import com.mdmserver.model.CallRecord;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author avin
 */
@Stateless
@LocalBean
public class DatabaseManager {

    
    public int registerUser(Account account){
         String databaseUrl = Config.databaseUrl;
         ConnectionSource connectionSource;
        
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            return accountDao.registerUser(account);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return Account.RETURN_CODE_ERROR_UNSPECIFIED;
        }
    }
    
    public int updateAppPackagesForAccount(Account account, List<AppPackage> appPackages){
        String databaseUrl = Config.databaseUrl;
         ConnectionSource connectionSource;
        
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            Account existingAccount= accountDao.getUserByEmailId(account.getEmailId());
            if(null!=existingAccount){
                AppPackageDao appPackageDao= new AppPackageDaoImpl(connectionSource);
                return appPackageDao.updateAppPackages(existingAccount, appPackages);
            }
            else{
                return AppPackage.RETURN_CODE_ERROR_DOES_NOT_EXIST;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return Account.RETURN_CODE_ERROR_UNSPECIFIED;
        }
    }
    
      public List<AppPackage> getAppPackagesForAccount(Account account){
        String databaseUrl = Config.databaseUrl;
         ConnectionSource connectionSource;
        
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            Account existingAccount= accountDao.getUserByEmailId(account.getEmailId());
            AppPackageDao appPackageDao= new AppPackageDaoImpl(connectionSource);
            return appPackageDao.getAppPackages(existingAccount);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<CallRecord> getCallRecordsForAccount(Account account){
        String databaseUrl = Config.databaseUrl;
         ConnectionSource connectionSource;
        
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            Account existingAccount= accountDao.getUserByEmailId(account.getEmailId());
            CallRecordDao callRecoredDao= new CallRecordDaoImpl(connectionSource);
            return callRecoredDao.getCallRecords(existingAccount);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int updateCallRecordsForAccount(Account account, List<CallRecord> callRecords){
        String databaseUrl = Config.databaseUrl;
         ConnectionSource connectionSource;
        
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            Account existingAccount= accountDao.getUserByEmailId(account.getEmailId());
            if(null!=existingAccount){
                CallRecordDao callRecoredDao= new CallRecordDaoImpl(connectionSource);
                return callRecoredDao.updateCallRecords(existingAccount, callRecords);
            }
            else{
                return AppPackage.RETURN_CODE_ERROR_DOES_NOT_EXIST;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return Account.RETURN_CODE_ERROR_UNSPECIFIED;
        }
    }
    
  
}
