 


/**
 * Enumeration class Constants - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Constants
{
    
    AMOUNT_OF_BAGS (3),
    HAND_CAPACITY(10),
    MINIMUM_AMOUNT_OF_PEBBLES_PER_PLAYER(11);
   // MAX_PEBBLE_WEIGHT(100);
    private final int returnvalue;
   // private final String[] stringvalues;
    private Constants(int value){returnvalue = value;}
   // private Constants(String[] value){stringvalues = value;}
    public int getInt(){return returnvalue;}
   // public String[] getStringArray(){return stringvalues;}
}
