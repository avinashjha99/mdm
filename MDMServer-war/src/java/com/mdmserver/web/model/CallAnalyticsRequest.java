/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.web.model;

import com.mdmserver.model.Account;
import com.mdmserver.model.CallRecord;

import java.util.List;

/**
 *
 * @author avinashk
 */
public class CallAnalyticsRequest {
    
    private List<CallRecord> callRecords;
    private Account account;
    
    public CallAnalyticsRequest(){
    
    }
    
    public List<CallRecord> getCallRecords() {
        return callRecords;
    }

    public void setCallRecords(List<CallRecord> callRecords) {
        this.callRecords = callRecords;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    
    
}

  
