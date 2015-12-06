
package models;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Friend {
    String myId;
    String friendId;

    public Friend() {
        myId="";
        friendId="";
    }

    
    
    public String getMyId() {
        return myId;
    }

    public String getFriendId() {
        return friendId;
    }
    
    public void setMyId(String myId) {
        this.myId = myId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public void addFriend(int myId, int friendId){
        try{    
            DAO dao=new DAO();
            Connection con= dao.connect();
            String sqlString="INSERT INTO friends (myId,friendId) VALUES ('"+myId+"','"+friendId+"')";
            Statement s = con.createStatement();
            try{    
                s.executeUpdate(sqlString);
                s.close();
                con.close();
            } catch (SQLException ex) {Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);}
        } catch (SQLException ex) {Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);}  
    }
}
