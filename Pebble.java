 

 


/**
 * Write a description of class Pebble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pebble
{

    private final int weight;
    Pebble(int weight) throws IllegalWeightException{
        if (weight<=0){throw new IllegalWeightException("Please enter only positive integers");}
        this.weight = weight;
    }
    public int getWeight(){
        return weight;
    }
}
