/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pebble_app;

import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vlad1
 */
public class WhiteBagTest {
    
    public WhiteBagTest() { 
    }
    

    /**
     * Test of getPebbles method, of class WhiteBag.
     */
    @Test
    public void testGetPebbles() {
        System.out.println("getPebbles");
        WhiteBag testbag = null;
        try{ testbag = new WhiteBag("",new BlackBag("",null,testbag));}
        catch(InvalidBagException e){System.out.println("InvalidBag");return;}
        Pebble [] pebblearray= {null,null,null};
        for (Pebble i : pebblearray){testbag.discard(i);}
        assert(Arrays.equals(pebblearray, testbag.getPebbleArray()));
    }

    /**
     * Test of discard method, of class WhiteBag.
     */
    @Test
    public void testDiscard() {
        System.out.println("Discard");
        WhiteBag testbag=null;
        Pebble testpebble = null;
        try { testpebble = new Pebble(2);}
        catch(IllegalWeightException e){System.out.println(e);}
        try{testbag = new WhiteBag("testbag",new BlackBag("",null,testbag));}
        catch(InvalidBagException e){System.out.println(e);}
        if (testbag!=null){testbag.discard(testpebble);}
        else{System.out.println("testbag is null");}
        if (testbag!=null){
        assert(testbag.getPebbleArray()[0].equals(testpebble));}
        else{System.out.println("testbag is null")}
        

        
    }
    
}
