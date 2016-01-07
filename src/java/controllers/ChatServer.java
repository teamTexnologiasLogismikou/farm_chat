/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ChatMessage;
import models.ChatUser;

public class ChatServer extends HttpServlet{
    private static ChatController chatHelper=ChatController.getChatHelper();
    
    protected void afterSelection(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException{
        
        String selection=(String)request.getParameter("selection");
        if(selection.equals("1")){
            String from=request.getParameter("from");
            String fromName=request.getParameter("fromName");
            String to=request.getParameter("to");
            String message=request.getParameter("message");
            String img = request.getReader().readLine();
            
            ChatMessage chatMessage=new ChatMessage();
            chatMessage.setFrom_user(Integer.parseInt(from));
            chatMessage.setTo_user(Integer.parseInt(to));
            chatMessage.setFrom_user_name(fromName);
            chatMessage.setMessage(message);
            chatMessage.setImg(img);
            Calendar cal=Calendar.getInstance();
            chatMessage.setMessageTime(cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND));
            chatMessage.setMessageType(ChatMessage.NEW_MESSAGE_TYPE);
            chatHelper.loginUser(chatMessage.getFrom_user());
            chatHelper.recievedMessage(chatMessage);
        }
        else if(selection.equals("2")){
            String uid=request.getParameter("uid");
            chatHelper.loginUser(Integer.parseInt(uid));
            ChatMessage[] messages=chatHelper.getUserMessages(Integer.parseInt(uid), 1000);
            if(messages!=null && messages.length>0){
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("message_count", ""+messages.length);
                PrintWriter out = response.getWriter();
                //δεν το χρισιμοποιοθμε
                try{
                    if(ChatSettings.responseType==ChatSettings.XML){
                        out.println("<chat_messages>");
                        for(int i=0;i<messages.length;i++){
                            String messageAsXML=messages[i].toXML();
                            out.println(messageAsXML);
                        }
                        out.println("</chat_messages>");
                    }else{
                        for(int i=0;i<messages.length;i++){
                            String messageAsString=messages[i].toMultipleValues();
                            out.print(messageAsString);
                        }
                    }
                }finally{
                    out.flush();
                    out.close();
                }
            }else{
                return;
            }
        }
        else if(selection.equals("4")){
            String uid=request.getParameter("uid");
            chatHelper.logOffUser(Integer.parseInt(uid));
            Logout l = new Logout();
            l.doLogout(request, response);
        }
        else if(selection.equals("3")){
            String statusList="";
            ArrayList<ChatUser> contacts =null;
            HttpSession s = request.getSession();
            contacts = (ArrayList<ChatUser>)s.getAttribute("friendsList");
            if(contacts!=null && contacts.size()>0){
                for(int i=0;i<contacts.size();i++){
                    int status=chatHelper.getUserStatus(Integer.parseInt(contacts.get(i).getUid()));
                    contacts.get(i).setStatus(status);
                    statusList+=contacts.get(i).getUid()+"ø"+contacts.get(i).getUsername()+"ø"+status+"ø";
                }
                s.setAttribute("friendsList", contacts);
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                response.setHeader("contact_count", ""+contacts.size());
                try{
                    out.print(statusList);
                }catch(Exception e){
                    System.out.println("Error: "+e.getMessage());
                }
                finally{
                    out.flush();
                    out.close();
                }
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            afterSelection(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            afterSelection(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

