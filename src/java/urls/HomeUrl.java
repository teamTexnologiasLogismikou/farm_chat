
package urls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ChatUser;

public class HomeUrl extends HttpServlet{
    protected void home(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        PrintWriter pw = response.getWriter();
        ArrayList<ChatUser> myFriends = new ArrayList<ChatUser>();
        if(s.getAttribute("username") == null){
            pw.println("Δέν έχετε δικαίωμα πρόσβασης");
            pw.println("<a href='index'>Συνδεθείτε</a>");
        }else{
            ChatUser user = new ChatUser();
            user.setUsername(s.getAttribute("username").toString());
            user.getUser();
            myFriends = user.myFriends();
            request.setAttribute("myFriends", myFriends);
            RequestDispatcher rd1 = request.getRequestDispatcher("views/home.jsp");
            rd1.forward(request,response);
        }
    }
    
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            home(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HomeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            home(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HomeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}


