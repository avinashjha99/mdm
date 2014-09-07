/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.mdmserver.model.AppPackage;
import com.mdmserver.model.CallRecord;
import java.sql.SQLException;

/**
 *
 * @author avin
 */
public class CallRecordDaoImpl extends BaseDaoImpl<CallRecord, Integer> implements CallRecordDao{
    
    
    public CallRecordDaoImpl(ConnectionSource connectionSource)throws SQLException {
         super(connectionSource, CallRecord.class);
    }
   
    
}
