/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pebble_app;

/**
 *
 * @author Vlad1
 */
public class InvalidBagException extends Exception {

    /**
     * Creates a new instance of <code>InvalidBagException</code> without detail
     * message.
     */
    public InvalidBagException() {
    }

    /**
     * Constructs an instance of <code>InvalidBagException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidBagException(String msg) {
        super(msg);
    }
}
