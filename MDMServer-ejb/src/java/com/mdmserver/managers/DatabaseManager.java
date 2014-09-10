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
import com.mdmserver.model.Account;
import java.sql.SQLException;
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
}
