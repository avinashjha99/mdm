/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdm.listeners;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mdmserver.managers.DatabaseManager;
import com.mdmserver.model.Account;
import com.mdmserver.model.AppPackage;
import com.mdmserver.model.CallRecord;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebListener;
import javax.ws.rs.core.Context;

/**
 *
 * @author avinashk
 */
@Startup
  @Singleton
public class AppInitHandler {


    @PostConstruct
    public void initDb() { 
        System.out.println("Trying to create tables first");
        String databaseUrl = "jdbc:mysql://localhost:3306/mdm1_2";
            ConnectionSource connectionSource;
        
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            ((JdbcConnectionSource) connectionSource).setUsername("root");
            ((JdbcConnectionSource) connectionSource).setPassword("nbuser");
            TableUtils.createTableIfNotExists(connectionSource, Account.class);
            TableUtils.createTableIfNotExists(connectionSource, AppPackage.class);
            TableUtils.createTableIfNotExists(connectionSource, CallRecord.class);
//            acd.performDBOperations(connectionSource);
            
        } catch (SQLException ex) {
            System.out.println("NOOt created at all");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    
    }
  
 
}
