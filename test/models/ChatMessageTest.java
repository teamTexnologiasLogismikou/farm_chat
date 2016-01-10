package models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChatMessageTest {
    
    public ChatMessageTest() {
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

    @Test
    public void testToXML() {
        System.out.println("toXML");
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        String expResult = "<chat_message>";
        String result = instance.toXML();
        assertTrue(result.contains(expResult));
    }

    @Test
    public void testToMultipleValues() {
        System.out.println("toMultipleValues");
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        String expResult = "7";
        String result = instance.toMultipleValues();
        assertTrue(result.contains(expResult));
    }

    @Test
    public void testReturnMessage() {
        System.out.println("returnMessage");
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        instance.returnMessage();
        assertEquals(instance.getFrom_user(),22);
    }

    @Test
    public void testGetB64() {
        System.out.println("getB64");
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        String expResult = "foo";
        String result = instance.getImg();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetB64() {
        System.out.println("setB64");
        String img = "oof";
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        instance.setImg(img);
        assertEquals(img, instance.getImg());
    }

    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        String expResult = "foo";
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        String message = "oof";
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        instance.setMessage(message);
        assertEquals(message,instance.getMessage());
        
    }

    @Test
    public void testGetFrom_user() {
        System.out.println("getFrom_user");
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        int expResult = 7;
        int result = instance.getFrom_user();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetFrom_user() {
        System.out.println("setFrom_user");
        int from_user = 100;
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        instance.setFrom_user(from_user);
        assertEquals(from_user,instance.getFrom_user());
        
    }
    
    @Test
    public void testGetTo_user() {
        System.out.println("getTo_user");
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        int expResult = 22;
        int result = instance.getTo_user();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetTo_user() {
        System.out.println("setTo_user");
        int to_user = 100;
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        instance.setTo_user(to_user);
        assertEquals(to_user,instance.getTo_user());
    }

    @Test
    public void testGetFrom_user_name() {
        System.out.println("getFrom_user_name");
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        String expResult = "foo";
        String result = instance.getFrom_user_name();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetFrom_user_name() {
        System.out.println("setFrom_user_name");
        String from_user_name = "def";
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        instance.setFrom_user_name(from_user_name);
        assertEquals(from_user_name,instance.getFrom_user_name());
    }

    @Test
    public void testGetMessageTime() {
        System.out.println("getMessageTime");
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        String expResult = "foo";
        String result = instance.getMessageTime();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetMessageTime() {
        System.out.println("setMessageTime");
        String messageTime = "def";
        ChatMessage instance = new ChatMessage("foo", 7, 22, "foo", "foo", "foo");
        instance.setMessageTime(messageTime);
        assertEquals(messageTime, instance.getMessageTime());
    }
}
