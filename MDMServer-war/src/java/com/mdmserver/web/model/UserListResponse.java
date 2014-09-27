/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.web.model;

import com.mdmserver.model.Account;
import java.util.List;

/**
 *
 * @author avinashk
 */
public class UserListResponse extends Response{
    List<Account> accounts;
    
    public UserListResponse(){
        
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    
    
}
