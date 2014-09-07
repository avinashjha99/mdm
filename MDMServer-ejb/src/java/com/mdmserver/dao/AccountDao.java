/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.dao;

import com.j256.ormlite.dao.Dao;
import com.mdmserver.model.Account;

/**
 *
 * @author avin
 */
public interface AccountDao extends Dao<Account, Integer>{
    
    
    public int registerUser(Account account);
    public int unregisterUser(Account account);
    public boolean updateUser(Account account);
    public Account getUserByEmailId(String emailId);
    public int updateUserByEmailId(String emailId,String password, String fName, String lName, String cloudId);
}
