/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import javax.persistence.ForeignKey;

/**
 *
 * @author avin
 */
@DatabaseTable(tableName = "app_package")
public class AppPackage {

    public static final String QUERY_FOREIGN_COLUMN_ACCOUNT_ID= "account_id";
    public static final int RETURN_CODE_ERROR_DOES_NOT_EXIST =-4;
    public static final int RETURN_CODE_SUCCESS = 0;
    public static final int RETURN_CODE_ERROR_WRONG_PARAMETERS= -2;
    
    
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String packageName;
    @DatabaseField
    private String version;
    @DatabaseField(foreign = true)
    private Account account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    public AppPackage(){
        
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    
}
