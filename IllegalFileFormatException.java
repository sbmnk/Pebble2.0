package Pebble_app;


/**
 * Write a description of class IllegalFileFormatException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IllegalFileFormatException extends Exception
{
   String msg;
   IllegalFileFormatException(String msg){
    this.msg = msg;
    }
}
