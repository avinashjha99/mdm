/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.model;

/**
 *
 * @author avinashk
 */
public class ControlClient {
 
    public static final String CONTROL_CLIENT_KEY= "controlClient";
    
    public static final String COMMAND_TYPE_KEY= "commandType";
    public static final int UNINSTALL_APP_CONTROL= 1000;
    public static final int INSTALL_APP_CONTROL= 1001;
    public static final int LOCK_PHONE_CONTROL= 1002;
    public static final int WIPE_PHONE_CONTROL= 1003;
    
    private int commandType;
    private String jsonCommandDetails;

    public ControlClient(){
        
    }

    public int getCommandType() {
        return commandType;
    }

    public void setCommandType(int commandType) {
        this.commandType = commandType;
    }

    public String getJsonCommandDetails() {
        return jsonCommandDetails;
    }

    public void setJsonCommandDetails(String jsonCommandDetails) {
        this.jsonCommandDetails = jsonCommandDetails;
    }
    
   
    
        
}
