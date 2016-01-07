
package models;

import java.io.Serializable;

public class ChatMessage implements Serializable {
    public static int NEW_MESSAGE_TYPE=0;
    public static int RETURN_MESSAGE_TYPE=1;
    
    private String message;
    private int from_user;
    private int to_user;
    private String from_user_name;
    private String messageTime;
    private String img;
    private int messageType=0;
    
    public String toXML(){
        return "<chat_message><from_user>"+from_user+"</from_user><from_user_name>"+from_user_name+"</from_user_name><message>"+message+"</message><message_type>"+messageType+"</message_type></chat_message>";
    }
    
    public String toMultipleValues(){
        return from_user+"ø"+from_user_name+"ø"+message+"ø"+img+"ø";
    }
    public void returnMessage(){
        int temp=this.from_user;
        this.from_user=this.to_user;
        this.to_user=temp;
        this.messageType=1;
    }
    
        
    public String getImg() {
        return img;
    }
    public void setImg(String img){
        this.img = img;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
        this.message=this.message.replaceAll("<", "&lt;");
        this.message=this.message.replaceAll("ø", "");
    }


    public int getFrom_user() {
        return from_user;
    }

    public void setFrom_user(int from_user) {
        this.from_user = from_user;
    }

    public int getTo_user() {
        return to_user;
    }

    public void setTo_user(int to_user) {
        this.to_user = to_user;
    }

    public String getFrom_user_name() {
        return from_user_name;
    }

    public void setFrom_user_name(String from_user_name) {
        this.from_user_name = from_user_name;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public int getMessageType() {
        return messageType;
    }
    
    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
    
}
