 

 


/**
 * Write a description of class blackBag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
public class BlackBag extends Bag
{
 private final WhiteBag pair;
 
 BlackBag (String bagname ,Pebble[] pebbles, WhiteBag wb) {
    super.pebbles = pebbles;
     pair = wb;
     this.bagname = bagname;
 }
 public synchronized Pebble drawPebble()throws OutOfPebblesException {
     if (pebbles.length==0){throw new OutOfPebblesException();}
     if (pebbles.length >1){
     Random randomNumGen = new Random();
     int pebbleno = randomNumGen.nextInt(pebbles.length);
     Pebble[] newpebble= new Pebble[pebbles.length-1];
     int j = 0;
      int i=0;
    do{
        if (i==pebbleno){
        }
        else{
            newpebble[j] = pebbles[i];
            j++;
        }
        i++;
    }while(i<pebbles.length);
        Pebble output = pebbles[pebbleno];
 
        this.pebbles = newpebble;
        return output;
     }
     else {
         Pebble temppebble = this.pebbles[0];
         refill();
         return temppebble;}
        
        
}
private  void refill(){
    this.pebbles = pair.getPebbles();
}
public WhiteBag getPair(){
    return this.pair;
}
}
