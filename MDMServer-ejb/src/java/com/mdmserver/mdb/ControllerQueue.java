/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.mdb;

import com.mdmserver.config.Config;

import com.mdmserver.model.Account;
import com.mdmserver.model.ControlClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

/**
 *
 * @author avinashk
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/mdmQueue")
})
public class ControllerQueue implements MessageListener {
    
    public ControllerQueue() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        System.out.println("Control message received on the queue!!");
        try {
            String jsonData= message.getStringProperty(ControlClient.CONTROL_CLIENT_KEY);
            String cloudId= message.getStringProperty(Account.FIELD_NAME_CLOUD_ID);
            sendGCMMessge(cloudId, jsonData);
        } catch (JMSException ex) {
            Logger.getLogger(ControllerQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendGCMMessge(String cloudId, String jsonData){
        try {
//                Request.Post("http://android.googleapis.com/gcm/send").setHeader("Authorization", "key="+Config.gcmServerId)
//                        .bodyString("{\"registration_ids\":[\""+cloudId+"\"],\"data\": {\"1\":\"2\",\"3\":\"4\"}}", ContentType.APPLICATION_JSON)
//                        .execute().returnContent();
            Logger.getLogger("Avin").log(Level.INFO, "{\"registration_ids\":[\""+cloudId+"\"],\"data\":"+jsonData+"}");
            Request.Post("http://android.googleapis.com/gcm/send").setHeader("Authorization", "key="+Config.gcmServerId)
                        .bodyString("{\"registration_ids\":[\""+cloudId+"\"],\"data\":"+jsonData+"}", ContentType.APPLICATION_JSON)
                        .execute().returnContent();
                System.out.println("Sending----------------------");
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
