/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.mdmserver.model.Account;
import com.mdmserver.model.AppPackage;
import com.mdmserver.model.CallRecord;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author avin
 */
public class CallRecordDaoImpl extends BaseDaoImpl<CallRecord, Integer> implements CallRecordDao{
    
    
   List<CallRecord> callRecords;
    Account account;
    
    public CallRecordDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, CallRecord.class);
    }

    @Override
    public int updateCallRecords(Account account, List<CallRecord> callRecords) {
        
        if(null==account){
            return AppPackage.RETURN_CODE_ERROR_DOES_NOT_EXIST;
        }
        try {
            this.callRecords= callRecords;
            this.account= account;
//            for(AppPackage pckg: appPackages){
//                pckg.setAccount(account);
//                create(pckg);
//            }
            
            callBatchTasks(new Callable<Void>() {

                @Override
                public Void call() throws Exception {
                    for(CallRecord callrecord :CallRecordDaoImpl.this.callRecords){
                     callrecord.setAccount(CallRecordDaoImpl.this.account);
                     create(callrecord);
                    }
                    return null;
                }
            });
           
        } catch (SQLException ex) {
            Logger.getLogger(AccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Account.RETURN_CODE_ERROR_UNSPECIFIED;
        }
        return CallRecord.RETURN_CODE_SUCCESS;

    }

    @Override
    public List<CallRecord> getCallRecords(Account account) {
        
        try {
            List<CallRecord> qCallRecords= queryBuilder().where().eq(CallRecord.QUERY_FOREIGN_COLUMN_ACCOUNT_ID, account).query();
            return qCallRecords;
        } catch (SQLException ex) {
            Logger.getLogger(AppPackageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
