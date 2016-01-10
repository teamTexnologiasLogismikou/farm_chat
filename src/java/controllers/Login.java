package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import models.ChatUser;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException  {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter pw = response.getWriter();
            ArrayList<ChatUser> myFriends = new ArrayList<ChatUser>();
            try {
                if(ChatUser.LoginUser(request.getParameter("username"),request.getParameter("password"))){
                    ChatUser user = new ChatUser();
                    user.setUsername(String.valueOf(request.getParameter("username")));
                    user.getUser();

                    HttpSession sessionUser = request.getSession();
                    sessionUser.setAttribute("username",user.getUsername());
                    sessionUser.setAttribute("uid", user.getUid());
                    myFriends = user.myFriends();
                    sessionUser.setAttribute("friendsList", myFriends);
                    request.setAttribute("myFriends", myFriends);
                    //pw.println(myFriends);
                    RequestDispatcher rd1 = request.getRequestDispatcher("/home");
                    rd1.forward(request,response);     
                }  
                else{
                    pw.println("Wrong Username Or Password!!! ");
                    pw.println("<a href=\"index.jsp\">Please try again");
                }    
            } finally {
                pw.close();
               }
        }
        
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

}   