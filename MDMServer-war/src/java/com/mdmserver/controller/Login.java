/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.controller;

import com.google.gson.Gson;
import com.mdmserver.config.ServletConfig;
import com.mdmserver.constant.WebResponseCodeConstants;
import com.mdmserver.managers.DatabaseManager;
import com.mdmserver.model.Account;
import com.mdmserver.web.model.LoginRequest;
import com.mdmserver.web.model.LoginResponse;
import com.mdmserver.web.model.RegisterRequest;
import com.mdmserver.web.model.RegisterResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author avinashk
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    
    @EJB
    DatabaseManager dbManager;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson= new Gson();
        LoginRequest rRequest =gson.fromJson(request.getReader(), LoginRequest.class);
        LoginResponse rResponse= new LoginResponse();
        if((null==rRequest)||(null==rRequest.getEmailId())||(rRequest.getEmailId().equals(""))||(null==rRequest.getPassword()||(rRequest.getPassword().equals("")))){
            rResponse.setResponseCode(WebResponseCodeConstants.RESP_INVALID_REQUEST);
            rResponse.setResponseMessage(WebResponseCodeConstants.RESP_INVALID_REQUEST_MSG);
        }
        else{
            Account account= dbManager.getAccountByCredentials(rRequest.getEmailId(),rRequest.getPassword());
            if(null==account){
               rResponse.setResponseCode(WebResponseCodeConstants.RESP_INVALID_CREDENTIALS);
               rResponse.setResponseMessage(WebResponseCodeConstants.RESP_INVALID_CREDENTIALS_MSG);
            }
            else{
                //valid credentials, so set a session id and save account information there
                HttpSession session= request.getSession(true);
                session.setMaxInactiveInterval(ServletConfig.MAX_SESSION_TIMEOUT);
                session.setAttribute("account", account);
                rResponse.setResponseCode(WebResponseCodeConstants.RESP_SUCCESS);
                rResponse.setResponseMessage(WebResponseCodeConstants.RESP_SUCCESS_MSG);
                
            }
        }
        response.getWriter().print(gson.toJson(rResponse));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
