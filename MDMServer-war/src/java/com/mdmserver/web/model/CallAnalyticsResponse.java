/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.web.model;


import com.mdmserver.model.CallRecord;
import java.util.List;

/**
 *
 * @author avinashk
 */
public class CallAnalyticsResponse extends Response{
    private List<CallRecord> callRecords;
    
    public CallAnalyticsResponse(){
        
    }

    public List<CallRecord> getCallRecords() {
        return callRecords;
    }

    public void setCallRecords(List<CallRecord> callRecords) {
        this.callRecords = callRecords;
    }

   
    
    
}
