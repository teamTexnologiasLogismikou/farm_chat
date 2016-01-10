/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddTest {
    
    public AddTest() {
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
    public void testProcessRequest() throws Exception {
        
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter out = new StringWriter();
        when(request.getParameter("friendId")).thenReturn("foo");
        PrintWriter writer = new PrintWriter(out);
        when(response.getWriter()).thenReturn(writer);

        new Add().doGet(request, response);
        System.out.println(out);
        
        writer.flush();
        assertTrue(out.toString().contains("Σφάλμα προσθήκης"));
    }
    @Test
    public void testProcessRequest2() throws Exception {
        
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter out = new StringWriter();
        when(request.getParameter("friendId")).thenReturn("1");
        PrintWriter writer = new PrintWriter(out);
        when(response.getWriter()).thenReturn(writer);

        new Add().doPost(request, response);
        System.out.println(out);
        
        writer.flush();
        assertTrue(out.toString().contains("Σφάλμα προσθήκης"));
    }
    
}
