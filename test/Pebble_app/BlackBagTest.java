/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pebble_app;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vlad1
 */
public class BlackBagTest {
    
    public BlackBagTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of drawPebble method, of class BlackBag.
     */
    @Test
    public void testDrawPebble() throws Exception {
        System.out.println("drawPebble");
        BlackBag instance = null;
        Pebble expResult = null;
        Pebble result = instance.drawPebble();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPair method, of class BlackBag.
     */
    @Test
    public void testGetPair() {
        System.out.println("getPair");
        BlackBag instance = null;
        WhiteBag expResult = null;
        WhiteBag result = instance.getPair();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
