 


/**
 * Write a description of class whiteBag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WhiteBag extends Bag
{ private final BlackBag pair;
 
 WhiteBag ( String bagname , BlackBag bb) {
     pebbles = new Pebble[0];
     pair = bb;
     this.bagname = bagname;
 }
 public BlackBag getPair(){
  return this.pair ;  
 }
 
 public synchronized Pebble[] getPebbles(){
     Pebble[] temppebbles = pebbles;
     pebbles = new Pebble[0];
     return temppebbles;
 }
 public synchronized void  discard(Pebble pebble){
     Pebble[] newpebblearray = new Pebble[pebbles.length+1];
     newpebblearray[pebbles.length]=pebble;
    System.arraycopy(pebbles, 0, newpebblearray, 0, pebbles.length);
     pebbles = newpebblearray;
    }
    

}
