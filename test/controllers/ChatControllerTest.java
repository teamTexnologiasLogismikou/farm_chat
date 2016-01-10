/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import models.ChatMessage;
import models.ChatUser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChatControllerTest {
    
    public ChatControllerTest() {
    }
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static ArrayList<ChatUser> usersList;
    ChatUser cu = new ChatUser("1","randomData","randomData","randomData","randomData",5);
    
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void testGetChatHelper() {
        System.out.println("getChatHelper");
        ChatController result = ChatController.getChatHelper();
        assertNotEquals(null, result);
    }
    
    /**
     * Test of logOffUser method, of class ChatHelper.
     */
    @Test
    public void testLogOffUser() throws Exception {
        ArrayList<ChatUser> cuList = new ArrayList<>();
        ChatController mockCu = mock(ChatController.class);
        ChatMessage[] cm = null;
        when(mockCu.getUserMessages(0, 0)).thenReturn(cm);
        int userId = 1;
        ChatController ch = new ChatController();
        ch.logOffUser(userId);
        assertTrue(outContent.toString().contains("χρηστης αποσυνδεθηκε"));
    }

    /**
     * Test of loginUser method, of class ChatHelper.
     */
    @Test
    public void testLoginUser() throws Exception {
        ArrayList<ChatUser> cuList = new ArrayList<>();
        ChatController mockCu = mock(ChatController.class);
        ChatMessage[] cm = null;
        int userId = 1;
        ChatController ch = new ChatController();
        ch.loginUser(userId);
        assertTrue(outContent.toString().contains("χρηστης συνδέθηκε"));        
    }

    /**
     * Test of isUserOnline method, of class ChatHelper.
     */
    @Test
    public void testIsUserOnline() {
        System.out.println("isUserOnline");
        int userId = 1;
        ChatController instance = new ChatController();
        boolean expResult = false;
        boolean result = instance.isUserOnline(userId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserStatus method, of class ChatHelper.
     */
    @Test
    public void testGetUserStatus() {
        System.out.println("getUserStatus");
        int userId = 0;
        ChatController instance = new ChatController();
        int expResult = 0;
        int result = instance.getUserStatus(userId);
        assertEquals(expResult, result);
    }

    /**
     * Test of recievedMessage method, of class ChatHelper.
     */
    @Test
    public void testRecievedMessage() {
        System.out.println("recievedMessage");
        ChatMessage chatMessage = new ChatMessage("abc",1,2,"abc","abc","abc");
        ChatController instance = new ChatController();
        instance.recievedMessage(chatMessage);
        assertNotNull(instance);
    }

    /**
     * Test of getUserMessages method, of class ChatHelper.
     */
    @Test
    public void testGetUserMessages() {
        System.out.println("getUserMessages");
        int user_id = 4;
        //me 0 count perimenoume null apotelesma
        int count = 0;
        ChatController instance = new ChatController();
        ChatMessage[] result = instance.getUserMessages(user_id, count);
        assertNull(result);
    }
}
