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
import java.util.ArrayList;
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
         int resultCode;
         ConnectionSource connectionSource;
        
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            resultCode= accountDao.registerUser(account);
            connectionSource.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            resultCode= Account.RETURN_CODE_ERROR_UNSPECIFIED;
        }
        return resultCode;
        
    }
    
    public Account getAccountByAccountId(int accountId){
            String databaseUrl = Config.databaseUrl;
            Account resultAccount;
         ConnectionSource connectionSource;
         try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            Account existingAccount= accountDao.getUserByAccountId(accountId);
            resultAccount= existingAccount;
            connectionSource.close();
                 
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            resultAccount= null;
        }
         
         return resultAccount;
    }
    
    //This method returns null if emailId and passwords dont match
    public Account getAccountByCredentials(String emailId, String password){
         String databaseUrl = Config.databaseUrl;
         Account resultAccount;
         ConnectionSource connectionSource;
         try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            Account existingAccount= accountDao.getUserByEmailId(emailId);
            if(existingAccount.getPassword().equals(password)){
                resultAccount= existingAccount;
            }
            else{
                resultAccount= null;
            }
            connectionSource.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            resultAccount= null;
        }
         return resultAccount;
    }
    
    public List<Account> getAllAccounts(){
         String databaseUrl = Config.databaseUrl;
         List<Account> resultAccounts;
         ConnectionSource connectionSource;
         try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
           resultAccounts= accountDao.querryAllUsers();
           connectionSource.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            resultAccounts= null;
        }
         
         return resultAccounts;
    }
    
    public int updateAppPackagesForAccount(Account account, List<AppPackage> appPackages){
        String databaseUrl = Config.databaseUrl;
        int resultCode;
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
                resultCode= appPackageDao.updateAppPackages(existingAccount, appPackages);
            }
            else{
                resultCode= AppPackage.RETURN_CODE_ERROR_DOES_NOT_EXIST;
            }
            connectionSource.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            resultCode= Account.RETURN_CODE_ERROR_UNSPECIFIED;
        }
        
        return resultCode;
    }
    
      public List<AppPackage> getAppPackagesForAccount(Account account){
        String databaseUrl = Config.databaseUrl;
        List<AppPackage> resultAppPackages;
         ConnectionSource connectionSource;
        
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            Account existingAccount= accountDao.getUserByEmailId(account.getEmailId());
            AppPackageDao appPackageDao= new AppPackageDaoImpl(connectionSource);
            resultAppPackages= appPackageDao.getAppPackages(existingAccount);
            connectionSource.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            resultAppPackages= null;
        }
        return resultAppPackages;
    }
      
    public List<AppPackage> getAppPackagesForAccount(int accountid){
        String databaseUrl = Config.databaseUrl;
        List<AppPackage> resultAppPackages;
        ConnectionSource connectionSource;
        
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            Account existingAccount= accountDao.getUserByAccountId(accountid);
            AppPackageDao appPackageDao= new AppPackageDaoImpl(connectionSource);
            resultAppPackages= appPackageDao.getAppPackages(existingAccount);
            connectionSource.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            resultAppPackages= null;
        }
        return resultAppPackages;
    }
    
    public List<CallRecord> getCallRecordsForAccount(Account account){
        String databaseUrl = Config.databaseUrl;
        List<CallRecord> resultCallRecords;
         ConnectionSource connectionSource;
        
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            Account existingAccount= accountDao.getUserByEmailId(account.getEmailId());
            CallRecordDao callRecoredDao= new CallRecordDaoImpl(connectionSource);
            resultCallRecords= callRecoredDao.getCallRecords(existingAccount);
            connectionSource.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            resultCallRecords= null;
        }
        
        return resultCallRecords;
    }
    
    public List<CallRecord> getCallRecordsForAccount(int accountid){
        String databaseUrl = Config.databaseUrl;
        List<CallRecord> resultCallRecords;
         ConnectionSource connectionSource;
        
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            Account existingAccount= accountDao.getUserByAccountId(accountid);
            CallRecordDao callRecoredDao= new CallRecordDaoImpl(connectionSource);
            resultCallRecords= callRecoredDao.getCallRecords(existingAccount);
            connectionSource.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            resultCallRecords= null;
        }
        return resultCallRecords;
    }
    
    public int updateCallRecordsForAccount(Account account, List<CallRecord> callRecords){
        String databaseUrl = Config.databaseUrl;
        int resultCode;
         ConnectionSource connectionSource = null;
        
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername(Config.databaseUser);
            ((JdbcConnectionSource) connectionSource).setPassword(Config.databasePassword);
            AccountDao accountDao= new AccountDaoImpl(connectionSource);
//            acd.performDBOperations(connectionSource);
            Account existingAccount= accountDao.getUserByEmailId(account.getEmailId());
            if(null!=existingAccount){
                CallRecordDao callRecoredDao= new CallRecordDaoImpl(connectionSource);
                resultCode= callRecoredDao.updateCallRecords(existingAccount, callRecords);
            }
            else{
                resultCode= AppPackage.RETURN_CODE_ERROR_DOES_NOT_EXIST;
            }
            
            connectionSource.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            resultCode= Account.RETURN_CODE_ERROR_UNSPECIFIED;
        } 
        return resultCode;
    }
    
  
}
