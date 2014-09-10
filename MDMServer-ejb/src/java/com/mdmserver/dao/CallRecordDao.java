/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.dao;

import com.j256.ormlite.dao.Dao;
import com.mdmserver.model.Account;
import com.mdmserver.model.AppPackage;
import com.mdmserver.model.CallRecord;
import java.util.List;

/**
 *
 * @author avin
 */
public interface CallRecordDao  extends Dao<CallRecord, Integer>{
    public int updateCallRecords(Account account, List<CallRecord> callRecords);
    public List<CallRecord> getCallRecords(Account account);
}
