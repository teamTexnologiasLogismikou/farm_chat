package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatUser {
    
    private int status = 0;
    public static int OFFLINE = 0;
    public static int ONLINE = 1;
    private String uid;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    
    public ChatUser(){
        uid="";
        firstName="";
        lastName="";
        username="";
        password="";
    }
    
    public ChatUser(String uid, String firstName, String lastName, String username, String password, int status){
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getUid() {
        return uid;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }


    public void setUid(String uid){
        this.uid = uid;
    }
    
    public void setLastName(String lastName){
        this.lastName =lastName;
    }
    
    public void setFirstName(String firstName){
        this.firstName =firstName;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public void setPassword(String password) {
        this.password=password;
    }
    
        public ArrayList<ChatUser> friendsToAdd() throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs;
        ArrayList<ChatUser> users = new ArrayList<ChatUser>();
        DAO db=new DAO();
        
        Connection con= db.connect();
        String sqlString = "SELECT * FROM users, friends WHERE users.uid NOT IN (SELECT friends.friendId from friends WHERE friends.myId = "+"'"+uid+"'"+") AND users.uid <> "+"'"+uid+"'"+"GROUP BY users.username";
            pst = con.prepareStatement("");
            pst.executeQuery(sqlString);
            rs = pst.getResultSet();
            while(rs.next()){
                ChatUser user = new ChatUser();
                user.setUid(rs.getString("uid"));
                user.setLastName(rs.getString("last_name"));
                user.setFirstName(rs.getString("first_name"));
                user.setUsername(rs.getString("username"));
                users.add(user);
            }
            rs.close();
            pst.close();
            con.close();

        return users;
    }
    
    public static boolean LoginUser(String username,String password) {
        boolean check =false;
        try {      
            DAO dao=new DAO();
            Connection con= dao.connect();
            PreparedStatement ps =con.prepareStatement("select * from users where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs1 =ps.executeQuery();
            check = rs1.next();
            con.close();        
            }catch(Exception e){
                e.printStackTrace();
            }
        return check;    
    }

    public void getUser(){
        try {      
            DAO dao=new DAO();
            Connection con= dao.connect();
            String sqlString = "SELECT * FROM users WHERE username = '"+username+"'";
            Statement s = con.createStatement();
            ResultSet rs=s.executeQuery(sqlString);
            while(rs.next()){
                uid=rs.getString("uid");
                lastName= rs.getString("last_name");
                firstName = rs.getString("first_name");
                username= rs.getString("username");
                password = rs.getString("password");
            }
            s.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChatUser.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void register(){
        try{    
            DAO dao=new DAO();
            Connection con= dao.connect();
            String sqlString="INSERT INTO users (last_name,first_name,username,password) VALUES ('"+firstName+"','"+lastName+"','"+username+"','"+password+"')";
            Statement s = con.createStatement();
            try{    
                s.executeUpdate(sqlString);
                s.close();
                con.close();
            } catch (SQLException ex) {Logger.getLogger(ChatUser.class.getName()).log(Level.SEVERE, null, ex);}
        } catch (SQLException ex) {Logger.getLogger(ChatUser.class.getName()).log(Level.SEVERE, null, ex);}  
    }

    
    public ArrayList<ChatUser> getAllUsers() throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs;
        ArrayList<ChatUser> users = new ArrayList<ChatUser>();
        DAO dao=new DAO();
        Connection con= dao.connect();
        String sqlString = "SELECT * FROM users WHERE users.username <> "+"'"+username+"'";
            pst = con.prepareStatement("");
            pst.executeQuery(sqlString);
            rs = pst.getResultSet();
            while(rs.next()){
                ChatUser user = new ChatUser();
                user.setUid(rs.getString("uid"));
                user.setLastName(rs.getString("last_name"));
                user.setFirstName(rs.getString("first_name"));
                user.setUsername(rs.getString("username"));
                users.add(user);
            }
            rs.close();
            pst.close();
            con.close();
        return users;
    }
    
    public ArrayList<ChatUser> myFriends() throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs;
        ArrayList<ChatUser> users = new ArrayList<ChatUser>();
        DAO db=new DAO();
        
        Connection con= db.connect();
        String sql = "SELECT users.uid, users.last_name, users.first_name, users.username FROM users,friends WHERE friends.friendId  = users.uid AND friends.myId = "+"'"+uid+"'";
            pst = con.prepareStatement("");
            pst.executeQuery(sql);
            rs = pst.getResultSet();
            while(rs.next()){
                ChatUser user = new ChatUser();
                user.setUid(rs.getString("uid"));
                user.setLastName(rs.getString("last_name"));
                user.setFirstName(rs.getString("first_name"));
                user.setUsername(rs.getString("username"));
                users.add(user);
            }
            rs.close();
            pst.close();
            con.close();
        return users;
    }
}
