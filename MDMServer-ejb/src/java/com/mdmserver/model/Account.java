/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author avin
 */
@DatabaseTable(tableName = "accountv4")
public class Account {
    
    
    
    public static final String QUERY_COLUMN_EMAILID= "emailId";
    public static final String QUERY_COLUMN_PRIMARY_ID= "id";
    public static final String FIELD_NAME_ID= "id";
    public static final String FIELD_NAME_EMAIL_ID= "emailId";
    public static final String FIELD_NAME_CLOUD_ID= "cloudId";
    public static final String FIELD_NAME_FIRST_NAME= "firstName";
    public static final String FIELD_NAME_LAST_NAME= "lastName";
    public static final String FIELD_NAME_ACCOUNT_TYPE= "accounttype";
    
    public static final int RETURN_CODE_ERROR_ALREADY_EXISTS =-1;
    public static final int RETURN_CODE_ERROR_DOES_NOT_EXIST =-4;
    public static final int RETURN_CODE_SUCCESS = 0;
    public static final int RETURN_CODE_ERROR_WRONG_PARAMETERS= -2;
    public static final int RETURN_CODE_ERROR_UNSPECIFIED= -3;
    
    public static final String ACCOUNT_TYPE_ADMIN= "ADMIN";
    
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String emailId;
    @DatabaseField
    private String password;
    @DatabaseField
    private String cloudId;
    @DatabaseField
    private String firstName;
    @DatabaseField
    private String lastName;
    @DatabaseField
    private String accounttype;
//    @ForeignCollectionField
//    ForeignCollection<AppPackage> appPackages;

    
    public Account(){
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }
    
    
    
}
