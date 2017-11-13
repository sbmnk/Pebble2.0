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
public class PebbleGameTest {
    
    public PebbleGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addPlayer method, of class PebbleGame.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");
        String name = "";
        PebbleGame instance = new PebbleGame();
        instance.addPlayer(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class PebbleGame.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        PebbleGame instance = new PebbleGame();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class PebbleGame.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PebbleGame.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
