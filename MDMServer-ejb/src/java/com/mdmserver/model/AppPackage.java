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

    
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String packageName;
    @DatabaseField
    private String version;
    @DatabaseField(foreign = true)
    private Account account;
    
    
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
