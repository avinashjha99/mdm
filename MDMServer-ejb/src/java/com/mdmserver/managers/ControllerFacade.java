/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.managers;

import com.google.gson.Gson;
import com.j256.ormlite.logger.Log;
import com.mdmserver.config.Config;

import com.mdmserver.model.Account;
import com.mdmserver.model.AppPackage;
import com.mdmserver.model.ControlClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import org.jboss.weld.util.BeanMethods;

/**
 *
 * @author avinashk
 */
@Stateless
@LocalBean
public class ControllerFacade {
    
    @EJB
    DatabaseManager databaseManager;

    public void uninstallApp(int accountId, String appPackageName){
         Account account= databaseManager.getAccountByAccountId(accountId);
        Context ctx;
            try {
                ctx= new InitialContext();
                ConnectionFactory     connectionFactory = (ConnectionFactory)ctx.lookup("jms/mdmConnectionFactory");
            Queue     queue = (Queue)ctx.lookup("jms/mdmQueue");
            MessageProducer messageProducer;
            System.out.println("Naming success");
              try {
              
                  javax.jms.Connection  connection = connectionFactory.createConnection();
                    javax.jms.Session        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
                    messageProducer = session.createProducer(queue);
                    TextMessage message = session.createTextMessage();
                    message.setStringProperty(Account.FIELD_NAME_CLOUD_ID, account.getCloudId());
                    ControlClient cClient= new ControlClient();
                    cClient.setCommandType(ControlClient.UNINSTALL_APP_CONTROL);
                    cClient.setJsonCommandDetails(appPackageName);
                    Gson gson= new Gson();
                    message.setStringProperty(ControlClient.CONTROL_CLIENT_KEY, gson.toJson(cClient));
                    System.out.println( "It come from Servlet:"+ message.getText());
                    messageProducer.send(message);
                     System.out.println("JMS success");
              } catch (JMSException ex) {
//                  Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("JMS exception");
              }
            
            } catch (NamingException ex) {
//                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Naming exception");
            }
    }
    
    public void installApp(int accountId, String appPackageName){
        
    }
    
    public void lockPhone(int accountId) throws IOException{
        Account account= databaseManager.getAccountByAccountId(accountId);
        Context ctx;
            try {
                ctx= new InitialContext();
                ConnectionFactory     connectionFactory = (ConnectionFactory)ctx.lookup("jms/mdmConnectionFactory");
            Queue     queue = (Queue)ctx.lookup("jms/mdmQueue");
            MessageProducer messageProducer;
            System.out.println("Naming success");
              try {
              
                  javax.jms.Connection  connection = connectionFactory.createConnection();
                    javax.jms.Session        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
                    messageProducer = session.createProducer(queue);
                    TextMessage message = session.createTextMessage();
                    message.setStringProperty(Account.FIELD_NAME_CLOUD_ID, account.getCloudId());
                    ControlClient cClient= new ControlClient();
                    cClient.setCommandType(ControlClient.LOCK_PHONE_CONTROL);
                    Gson gson= new Gson();
                    message.setStringProperty(ControlClient.CONTROL_CLIENT_KEY, gson.toJson(cClient));
                    System.out.println( "It come from Servlet:"+ message.getText());
                    messageProducer.send(message);
                     System.out.println("JMS success");
              } catch (JMSException ex) {
//                  Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("JMS exception");
              }
            
            } catch (NamingException ex) {
//                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Naming exception");
            }
    }
    
}
