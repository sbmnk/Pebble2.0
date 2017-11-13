package Pebble_app;


/**
 * Write a description of class OutOfPebblesException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OutOfPebblesException extends Exception
{
    private String msg;
    OutOfPebblesException(){};
    OutOfPebblesException(String msg){this.msg = msg;}
}
