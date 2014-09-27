/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mdmserver.controller;

import com.google.gson.Gson;
import com.mdmserver.constant.WebResponseCodeConstants;

import com.mdmserver.managers.ControllerFacade;
import com.mdmserver.model.ControlClient;
import com.mdmserver.web.model.AppAnalyticsRequest;
import com.mdmserver.web.model.AppAnalyticsResponse;
import com.mdmserver.web.model.ControlRequest;
import com.mdmserver.web.model.ControlResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author avinashk
 */
@WebServlet(name = "Control", urlPatterns = {"/Control"})
public class Control extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @EJB
    ControllerFacade deviceController;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//          Context ctx;
//            try {
//                ctx = new InitialContext();
//                ConnectionFactory     connectionFactory = (ConnectionFactory)ctx.lookup("jms/mdmConnectionFactory");
//            Queue     queue = (Queue)ctx.lookup("jms/mdmQueue");
//            MessageProducer messageProducer;
//            out.println("Naming success");
//              try {
//                  javax.jms.Connection  connection = connectionFactory.createConnection();
//            javax.jms.Session        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//                    messageProducer = session.createProducer(queue);
//                    TextMessage message = session.createTextMessage();
//                    message.setText("Hiiiiii");
//                    System.out.println( "It come from Servlet:"+ message.getText());
//                    messageProducer.send(message);
//                     out.println("JMS success");
//              } catch (JMSException ex) {
//                  Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
//                  out.println("JMS exception");
//              }
//            
//            } catch (NamingException ex) {
//                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
//                out.println("Naming exception");
//            }
//            
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
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");
        Logger.getLogger("Avin").log(Level.INFO, "Sending");
        Gson gson= new Gson();
        ControlRequest rRequest =gson.fromJson(request.getReader(), ControlRequest.class);
        ControlResponse rResponse= new ControlResponse();
        if(null==rRequest.getAccountId()){
             rResponse.setResponseCode(WebResponseCodeConstants.RESP_INVALID_REQUEST); 
             rResponse.setResponseMessage(WebResponseCodeConstants.RESP_INVALID_REQUEST_MSG);
             response.getWriter().println(gson.toJson(rResponse));
             return;
        }
        //parse compulsory parameter accountid
        
        switch(rRequest.getCommandType()){
            case ControlClient.UNINSTALL_APP_CONTROL:
                deviceController.uninstallApp(rRequest.getAccountId(), rRequest.getData());
            break;
            case ControlClient.INSTALL_APP_CONTROL:
                deviceController.installApp(rRequest.getAccountId(), rRequest.getData());
            break;
            case ControlClient.LOCK_PHONE_CONTROL:
                deviceController.lockPhone(rRequest.getAccountId());
                      
            break;
            default:
                
            break;
        }
        rResponse.setResponseCode(WebResponseCodeConstants.RESP_SUCCESS);
        rResponse.setResponseMessage(WebResponseCodeConstants.RESP_SUCCESS_MSG);
        response.getWriter().println(gson.toJson(rResponse));
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
