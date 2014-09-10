/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mdmserver.model.Account;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author avin
 */
public class AccountDaoImpl extends BaseDaoImpl<Account, Integer> implements AccountDao{

    public AccountDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Account.class);
    }
    
    @Override
    public int registerUser(Account account) {
        Account qAccount= getUserByEmailId(account.getEmailId());
        if(null!=qAccount){
            return Account.RETURN_CODE_ERROR_ALREADY_EXISTS;
        }
        try {
            create(account);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Account.RETURN_CODE_ERROR_UNSPECIFIED;
        }
        return Account.RETURN_CODE_SUCCESS;
    }

    @Override
    public int unregisterUser(Account account) {
       Account qAccount= getUserByEmailId(account.getEmailId());
        if(null!=qAccount){
           try {
               delete(qAccount);
           } catch (SQLException ex) {
               Logger.getLogger(AccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
               return Account.RETURN_CODE_ERROR_UNSPECIFIED;
           }
            return Account.RETURN_CODE_SUCCESS;
        }
        else{
            return Account.RETURN_CODE_ERROR_DOES_NOT_EXIST;
        }
        
    }

    @Override
    public boolean updateUser(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account getUserByEmailId(String emailId) {
         List<Account> accounts=null;
        try {
           accounts= queryBuilder().where().eq(Account.QUERY_COLUMN_EMAILID, emailId).query();
        } catch (SQLException ex) {
            System.out.println("Unable to query for users--------------------");
            Logger.getLogger(AccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if((null!=accounts)&&(accounts.size()>0)){
            return accounts.get(0);
        }
        
        return null;
    }

    @Override
    public int updateUserByEmailId(String emailId, String password, String fName, String lName, String cloudId) {
        Account account= getUserByEmailId(emailId);
        if(null==account){
            return Account.RETURN_CODE_ERROR_DOES_NOT_EXIST;
        }
        else{
            if(null!=fName){
                account.setFirstName(fName);
            }
            if(null!=lName){
                account.setLastName(lName);
            }
            if(null!=cloudId){
                account.setCloudId(cloudId);
            }
            if(null!=password){
                account.setPassword(password);
            }
            
            try {
                update(account);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                return Account.RETURN_CODE_ERROR_UNSPECIFIED;
            }
            return Account.RETURN_CODE_SUCCESS;
        }
        
    }

    
    
}
