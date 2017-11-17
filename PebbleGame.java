 


import java.util.Scanner;
import java.io.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
public class PebbleGame implements Runnable
{
   private final  AtomicBoolean gamewon = new AtomicBoolean(); 
   private PebbleGame.Player[] players ; 
   private final  BlackBag[]blackbags = new BlackBag[Constants.AMOUNT_OF_BAGS.getInt()];
   PebbleGame(){
    }
   private void initialiseBags() throws  IllegalWeightException, InsufficientAmountOfPebblesException{
       FileWriter fw = new FileWriter();
       boolean correct ;
       File [] files ;
       String[] blackbagnames = {"X","Y","Z"};
       String[] whitebagnames = {"A","B","C"};
       correct = true;
       files = fw.getFiles();
      for (int i =0;i<files.length;i++){
          Integer[] bagpebblesvalues =fw.readFile(files[i],players.length);
          Pebble[] bagpebbles = new Pebble [bagpebblesvalues.length];
          for (int j = 0;j<bagpebblesvalues.length;j++){
              bagpebbles[j] =new Pebble(bagpebblesvalues[j]);}
          blackbags[i]= new BlackBag(blackbagnames[i],bagpebbles,new WhiteBag(whitebagnames[i],blackbags[i]));
    }}

   private  void initialisePlayers() throws IllegalAmountOfPlayersException{
        Scanner scan = new Scanner (System.in);
        boolean correct = true;
        int noofplayers=0;
        while(correct){
            try{
                System.out.println("Enter Number of Players");
                String inp = scan.next();
                if (inp.equals("E")){System.exit(0);}
                noofplayers = Integer.valueOf(inp);
                correct = false;
                if (noofplayers<1){throw new IllegalAmountOfPlayersException("Please enter a legal amount of players");}
             }catch(java.util.InputMismatchException|NumberFormatException e){
                 System.out.println("Please write an integer");
        
    }
    }
    players = new PebbleGame.Player[noofplayers];
    for (int i = 0 ; i<players.length;i++){
               players[i] = new PebbleGame.Player("Player "+i);
            }
        }
   public void startGame() throws IllegalAmountOfPlayersException,IllegalWeightException,InsufficientAmountOfPebblesException{     
       initialisePlayers();
       initialiseBags();
       run();}

   @Override
   public void run(){
        for (Thread t : players){
            t.start();
        }
       }
   private  class Player extends Thread{
       private final FileWriter fw = new FileWriter();
       private final String playername ;
       private  final Pebble[] hand = new Pebble[Constants.HAND_CAPACITY.getInt()];
       private BlackBag previousbag;
       private  int handweight=0; 
       private int emptyentries = Constants.HAND_CAPACITY.getInt();
       Player(String name){
           playername=name;
       }
       public int getHandValue(){           
           int sum =0; 
           for( int i = 0; i < Constants.HAND_CAPACITY.getInt(); i++){
               sum += hand[i].getWeight();              
            }
            return sum;
       }
       public void printHand(){String q = playername+" hand is ";
           for (Pebble i : hand){if(i!=null){q= q+i.getWeight()+",";}} fw.writeToFile(q.substring(0,q.length()-1));}
       public void drawPebble(){
           //ADD AN EXCEPTION
           BlackBag bag;
           Random randomno = new Random();
           int emptyindex = -1;
           for (int i=0;i<Constants.HAND_CAPACITY.getInt();i++){
               if (hand[i]==null){
                   emptyindex = i;
                }
            }
           boolean isempty;
           int bagindex;
           do {
           bagindex = (randomno.nextInt(Constants.AMOUNT_OF_BAGS.getInt())) ;
           bag = blackbags[bagindex];
           try{hand[emptyindex]=bag.drawPebble();
               isempty=false;}
           catch(OutOfPebblesException e){isempty = true;}}
           while(isempty);
           previousbag = bag;
           emptyentries--;
           handweight+=hand[emptyindex].getWeight();
           fw.writeToFile(playername+ " has drawn a "+ hand[emptyindex].getWeight() +" from bag "+bag.getName());
           printHand();
        }  
       public void discardPebble() {
            WhiteBag wb = previousbag.getPair();
            int pebbleindex = 0;
            int pebbleweight = 0;
            if (handweight>Constants.WINNING_WEIGHT.getInt()){
                for (int i=0;i<Constants.HAND_CAPACITY.getInt();i++){
                    if (hand[i]!=null){
                    if(hand[i].getWeight()>pebbleweight){
                        pebbleindex = i;
                        pebbleweight = hand[i].getWeight();
                    }
                }}
            }
            else{
                pebbleweight = Integer.MAX_VALUE;
                for (int i=0;i<Constants.HAND_CAPACITY.getInt();i++){
                    if (hand[i]!=null){
                    if(hand[i].getWeight()<pebbleweight){
                        pebbleindex = i;
                        pebbleweight = hand[i].getWeight();
                    }}
                }
            }
            wb.discard(hand[pebbleindex]);
            fw.writeToFile(playername + " has discarded a "+pebbleweight + " to bag "+wb.getName());
            printHand();
            handweight -=pebbleweight;
            hand[pebbleindex]=null;
            emptyentries++;
        }
       public String getPlayerName(){
           return playername;
        }
       public boolean checkWinConditions(Player player){
            return handweight ==Constants.WINNING_WEIGHT.getInt() &&emptyentries==0;
       }
       @Override
        public void run(){
            fw.createFile(playername+".txt");
            while (!gamewon.get()){
            while(emptyentries>0&&handweight<100&&!gamewon.get()){
                 drawPebble();
                }
            if (checkWinConditions(this)){gamewon.set(true);}
            else{discardPebble();}
            }
       }
        
    }
   
    public static void main (String args[]) throws IllegalFileFormatException, IllegalWeightException, InsufficientAmountOfPebblesException, OutOfPebblesException, IllegalAmountOfPlayersException{
        PebbleGame game1 = new PebbleGame();
        game1.startGame();
    }
}
