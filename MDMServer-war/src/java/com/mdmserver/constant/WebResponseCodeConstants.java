/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.constant;

/**
 *
 * @author avinashk
 */
public class WebResponseCodeConstants {
    
    public static final int RESP_SUCCESS=0;
    public static final String RESP_SUCCESS_MSG="success";
    public static final int RESP_INVALID_REQUEST=-1;
    public static final String RESP_INVALID_REQUEST_MSG="invalid request";
    public static final int RESP_USER_EXISTS=-2;
    public static final String RESP_USER_EXISTS_MSG="user already exists";
    public static final int RESP_SERVER_DOWN=-3;
    public static final String RESP_SERVER_DOWN_MSG="server down";
    public static final int RESP_NO_USER_EXISTS=-4;
    public static final String RESP_NO_USER_EXISTS_MSG="user does not exist";
    public static final int RESP_INVALID_CREDENTIALS=-5;
    public static final String RESP_INVALID_CREDENTIALS_MSG="invalid credentials";
}
