/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author avin
 */
@DatabaseTable(tableName = "call_record")
public class CallRecord {
    
    public static final String QUERY_FOREIGN_COLUMN_ACCOUNT_ID= "account_id";
    public static final int RETURN_CODE_ERROR_DOES_NOT_EXIST =-4;
    public static final int RETURN_CODE_SUCCESS = 0;
    public static final int RETURN_CODE_ERROR_WRONG_PARAMETERS= -2;
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField
    private String name;
    @DatabaseField(foreign = true)
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
