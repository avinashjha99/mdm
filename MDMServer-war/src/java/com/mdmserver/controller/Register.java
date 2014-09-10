/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.controller;

import com.google.gson.Gson;
import com.mdmserver.constant.WebResponseCodeConstants;
import com.mdmserver.managers.DatabaseManager;
import com.mdmserver.model.Account;
import com.mdmserver.web.model.RegisterRequest;
import com.mdmserver.web.model.RegisterResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.AcceptPendingException;
import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.mail.internet.ContentType;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author avinashk
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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
        RegisterRequest rRequest =gson.fromJson(request.getReader(), RegisterRequest.class);
        RegisterResponse rResponse= new RegisterResponse();
        if((null==rRequest)||(null==rRequest.getEmailId())||(rRequest.getEmailId().equals(""))||(null==rRequest.getPassword()||(rRequest.getPassword().equals("")))){
            rResponse.setResponseCode(WebResponseCodeConstants.RESP_INVALID_REQUEST);
            rResponse.setResponseMessage(WebResponseCodeConstants.RESP_INVALID_REQUEST_MSG);
        }
        else{
            int internalResultCode= dbManager.registerUser(rRequest);
            switch(internalResultCode){
                case Account.RETURN_CODE_SUCCESS:
                    rResponse.setResponseCode(WebResponseCodeConstants.RESP_SUCCESS);
                    rResponse.setResponseMessage(WebResponseCodeConstants.RESP_SUCCESS_MSG);
                    break;
                case Account.RETURN_CODE_ERROR_ALREADY_EXISTS:
                    rResponse.setResponseCode(WebResponseCodeConstants.RESP_USER_EXISTS);
                    rResponse.setResponseMessage(WebResponseCodeConstants.RESP_USER_EXISTS_MSG);
                    break;
                default:
                    rResponse.setResponseCode(WebResponseCodeConstants.RESP_SERVER_DOWN);
                    rResponse.setResponseMessage(WebResponseCodeConstants.RESP_SUCCESS_MSG);  
                    break;
            }
        }
        response.getWriter().print(gson.toJson(rResponse));
        
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Register</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
