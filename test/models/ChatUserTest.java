/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChatUserTest {
    
    public ChatUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStatus method, of class ChatUser.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        ChatUser obj = new ChatUser();
        int expResult = 0;
        int result = obj.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class ChatUser.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        int status = 1;
        ChatUser obj = new ChatUser();
        obj.setStatus(status);
        assertEquals(status, obj.getStatus());
    }

    /**
     * Test of getUid method, of class ChatUser.
     */
    @Test
    public void testGetUid() {
        System.out.println("getUid");
        ChatUser obj = new ChatUser("2","abc","abc","aaa","aaa",1);
        String expResult = "2";
        String result = obj.getUid();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getLastName method, of class ChatUser.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        ChatUser obj = new ChatUser("2","abc","abc","aaa","aaa",1);
        String expResult = "abc";
        String result = obj.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFirstName method, of class ChatUser.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        ChatUser obj = new ChatUser("2","abc","abc","aaa","aaa",1);
        String expResult = "abc";
        String result = obj.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class ChatUser.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        ChatUser obj = new ChatUser("2","abc","abc","aaa","aaa",1);
        String expResult = "aaa";
        String result = obj.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class ChatUser.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        ChatUser obj = new ChatUser("2","abc","abc","aaa","aaa",1);
        String expResult = "aaa";
        String result = obj.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUid method, of class ChatUser.
     */
    @Test
    public void testSetUid() {
        System.out.println("setUid");
        String uid = "1";
        ChatUser obj = new ChatUser();
        obj.setUid(uid);
        assertEquals(uid, obj.getUid());
    }