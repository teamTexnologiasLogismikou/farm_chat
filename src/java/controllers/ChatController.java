/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import models.ChatMessage;
import models.ChatUser;

public class ChatController implements Runnable{
    private static ChatController chatHelper=new ChatController();
    private static Hashtable<Integer,Date> onlineUsers=new Hashtable<Integer, Date>();
    private static Hashtable<Integer,Queue<ChatMessage>> chatMessages=new Hashtable<Integer,Queue<ChatMessage>>();
    private Thread cleanThread;
    private static ArrayList<ChatUser> usersList;
    
    
    public static ChatController getChatHelper(){
        return chatHelper;
    }
    
    private ChatController(){
            cleanThread=new Thread(this);
            cleanThread.start();
        }    
    public void logOffUser(int userId) throws SQLException{
        ChatUser cu = new ChatUser();
        usersList = cu.getAllUsers();
        onlineUsers.remove(userId);
        System.out.println("User removed");
        for(int i=0;i<usersList.size();i++){
            if(usersList.get(i).getUid().equals(String.valueOf(userId))){
                usersList.get(i).setStatus(ChatUser.OFFLINE);
                break;
            }
        }
        returnMessagesOfUser(userId);
    }

    public void loginUser(int user_id) throws SQLException{
        ChatUser cu = new ChatUser();
        usersList = cu.getAllUsers();
        onlineUsers.put(user_id, new Date());
        for(int i=0;i<usersList.size();i++){
            if(usersList.get(i).getUid().equals(String.valueOf(user_id))){
                usersList.get(i).setStatus(ChatUser.ONLINE);
                break;
            }
        }
    }

    public boolean isUserOnline(int userId){
        if(onlineUsers.get(userId)!=null){
            return true;
        }else{
            return false;
        }
    }

    public int getUserStatus(int userId){
        if(isUserOnline(userId)) return ChatUser.ONLINE;
        else return ChatUser.OFFLINE;
    }
    
    public void recievedMessage(ChatMessage chatMessage){
        if(onlineUsers.get(chatMessage.getTo_user())!=null){
            if(chatMessages.get(chatMessage.getTo_user())!=null){
                chatMessages.get(chatMessage.getTo_user()).add(chatMessage);
            }else{
                Queue<ChatMessage> newQueue=new ConcurrentLinkedQueue<ChatMessage>();
                newQueue.add(chatMessage);
                chatMessages.put(chatMessage.getTo_user(), newQueue);
            }
        }else{
            chatMessage.returnMessage();
            if(onlineUsers.get(chatMessage.getTo_user())!=null){
                if(chatMessages.get(chatMessage.getTo_user())!=null){
                    chatMessages.get(chatMessage.getTo_user()).add(chatMessage);
                }else{
                    Queue<ChatMessage> newQueue=new ConcurrentLinkedQueue<ChatMessage>();
                    newQueue.add(chatMessage);
                    chatMessages.put(chatMessage.getTo_user(), newQueue);
                }
            }
        }        
    }

    private void returnMessagesOfUser(Integer current) {
        Queue messages=chatMessages.get(current);
        if(chatMessages.get(current)!=null){
            if(messages.size()>0){
                while(messages.size()>0){
                    ChatMessage message=(ChatMessage)messages.poll();
                    message.returnMessage();
                    if(onlineUsers.get(message.getTo_user())!=null) {
                        recievedMessage(message);
                    }
                }
            }
            chatMessages.remove(current);
        }
    }

    public ChatMessage[] getUserMessages(int user_id,int count) {
        Queue messages=chatMessages.get(user_id);
        if(chatMessages.get(user_id)!=null){
            ArrayList<ChatMessage> messagesList=new ArrayList<ChatMessage>();
            if(messages.size()>0){
                while(count>0){
                    ChatMessage message=(ChatMessage)messages.poll();
                    if(message==null){
                        break;
                    }
                    messagesList.add(message);
                    count--;
                }
            }
            return messagesList.toArray(new ChatMessage[0]);
        }
        return null;
    }

    @Override
    public void run() {
        try {
            cleanThread.sleep(1*45*1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        while(true){
            try{
                if(onlineUsers==null){
                    onlineUsers=new Hashtable<Integer, Date>();
                }
                if(onlineUsers.size()>0){
                    for(int i=0;i<onlineUsers.size();i++){
                        Enumeration<Integer> keys = onlineUsers.keys() ;
                        while(keys.hasMoreElements()) {
                            Integer current=keys.nextElement();
                            if(onlineUsers.get(current).getTime()+ChatSettings.expireLoginAfter<new Date().getTime()){
                                logOffUser(current);
                            }
                        }
                    }
                }
                cleanThread.sleep(1*45*1000);
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }

}

