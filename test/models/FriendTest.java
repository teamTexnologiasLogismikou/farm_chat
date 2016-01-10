/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FriendTest {
    
    public FriendTest() {
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
     * Test of getMyId method, of class Friend.
     */
    @Test
    public void testGetMyId() {
        System.out.println("getMyId");
        Friend instance = new Friend("1","2");
        String expResult = "1";
        String result = instance.getMyId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFriendId method, of class Friend.
     */
    @Test
    public void testGetFriendId() {
        System.out.println("getFriendId");
        Friend instance = new Friend("1","2");
        String expResult = "2";
        String result = instance.getFriendId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMyId method, of class Friend.
     */
    @Test
    public void testSetMyId() {
        System.out.println("setMyId");
        String myId = "1";
        Friend instance = new Friend();
        instance.setMyId(myId);
        assertEquals(myId, instance.getMyId());
    }

    @Test
    public void testSetFriendId() {
        System.out.println("setFriendId");
        String friendId = "2";
        Friend instance = new Friend();
        instance.setFriendId(friendId);
        assertEquals(friendId, instance.getFriendId());
    }

    /**
     * Test of addFriend method, of class Friend.
     */
    @Test
    public void testAddFriend() {
        System.out.println("addFriend");
        int myId = 0;
        int friendId = 0;
        Friend instance = new Friend();
        instance.addFriend(myId, friendId);
    }
    
}
