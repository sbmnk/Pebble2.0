package Pebble_app;


/**
 * Write a description of class PebbleGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.io.File;
import java.util.Random;
public class PebbleGame implements Runnable
{
   private  int noofplayers;
   private PebbleGame.Player[] players ; 
   private  BlackBag[]blackbags = new BlackBag[Constants.AMOUNT_OF_BAGS.getInt()];
   private Thread gamethread;
   PebbleGame(){}
   public void addPlayer(String name)  {
      //NEED TO ADD AN EXCEPTION
       for (int i = 0 ; i<players.length;i++){
           if(players[i]==null){
               players[i] = new PebbleGame.Player(name);
               return;
            }
        }
    }
   private static boolean checkFile(File a){
       if (a.exists()){
           String fileformat =getFileExtension(a); 
           if(fileformat.equals("txt")){
           try{
           Scanner sc = new Scanner (a);
           if (sc.hasNextLine()){
               String pw = sc.nextLine();
               return pw.matches("^[\\d+,]+[\\d+]$");
            }
        } catch (java.io.FileNotFoundException |IllegalStateException e){
            System.out.println("Please enter file containing comma seperated list of Integers");            
            return false;
        }
        }
    }
        return false;    
    }
   private static Integer[] readFile(File a){
        try{
        Scanner sc = new Scanner (a);
        if (sc.hasNextLine()){
               String pw = sc.nextLine();               
               String[] str = pw.split(",");
               Integer[] pbs = new Integer[str.length];
               for (int i = 0; i<str.length; i++){
                   pbs[i] = Integer.valueOf(str[i]);
                }
                return pbs;
            }
        } catch (java.io.FileNotFoundException |IllegalStateException e){
            System.out.println("Please enter file containing comma seperated list of Integers");            
     
        }
        return null;}
        
   private void initialiseBags(){
       boolean correct ;
       File [] files ;
       String[] blackbagnames = {"X","Y","Z"};
       String[] whitebagnames = {"A","B","C"};
       do{
       correct = true;
        files = getFiles();
       for(File f :files){if (checkFile(f)){} else{correct = false;}}
          
       
     }while(!correct);
      for (int i =0;i<files.length;i++){
          Integer[] bagpebblesvalues =readFile(files[i]);
          Pebble[] bagpebbles = new Pebble [bagpebblesvalues.length];
          for (int j = 0;j<bagpebblesvalues.length;j++){
              bagpebbles[j] =new Pebble(bagpebblesvalues[j]);}
          blackbags[i]= new BlackBag(blackbagnames[i],bagpebbles,new WhiteBag(whitebagnames[i],blackbags[i]));
        }
     
    }
   private static String getFileExtension(File a){
     String fileName = a.getName();
     if(fileName.lastIndexOf(".")!=-1&&fileName.lastIndexOf(".")!=0){
         return fileName.substring(fileName.lastIndexOf(".")+1);
        }else {return "";}
    }
   private  void getNoOfPlayers(){
        Scanner scan = new Scanner (System.in);
        boolean correct = true;
        while(correct){
            try{
        System.out.println("Enter Number of Players");
         this.noofplayers = scan.nextInt();
        correct = false;
    } catch(java.util.InputMismatchException e){
        System.out.println("Enter Legal Data");
        scan.next();
        
    }
    }
        }
   private static File[] getFiles(){
        File[] files = new File[3];
        Scanner scan = new Scanner (System.in);
        boolean correct = true;
        while (correct){
                String [] filenames = new String[3];
                for (int i=1 ;i<4;i++){
                System.out.println("Please enter the file path of file "+i);
                filenames[i-1] = scan.next();
                File a = new File(filenames[i-1]);
                files[i-1]=a;
            }
            correct = !(checkFile(files[0])&& checkFile(files[1])&& checkFile(files[2]));
                
            }
        return files;
    }
   @Override
   public void run(){
        for (Thread t : players){
            t.start();
        }
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            for (Thread t : players){
                t.interrupt();
            }
        }
       }
   private  class Player extends Thread{
       private final String playername ;
       private  final Pebble[] hand = new Pebble[10];
       private BlackBag previousbag;
       private  int handweight=0; 
       private int emptyentries = 10;
       Player(){
           playername="Player"+noofplayers;
       }
       Player(String name){
           playername=name;
       }
       public int getHandValue(){           
           int sum =0; 
           for( int i = 0; i < 10; i++){
               sum += hand[i].getWeight();              
            }
            return sum;
       }
       public void printHand(){String q = "Hand : ";for (Pebble i : hand){if(i!=null){q= q+","+i.getWeight();}} System.out.println(q);}
       public void drawPebble(){
           //ADD AN EXCEPTION
           BlackBag bag;
           Random randomno = new Random();
           int emptyindex = -1;
           for (int i=0;i<10;i++){
               if (hand[i]==null){
                   emptyindex = i;
                }
            }
            if (emptyindex <0){
                return;
            }
           boolean isempty;
           int bagindex;
           do {
           bagindex = (randomno.nextInt(3)) ;
           bag = blackbags[bagindex];
           try{hand[emptyindex]=bag.drawPebble();isempty=false;}
           catch(OutOfPebblesException e){isempty = true;}}
           while(isempty);
           previousbag = bag;
           emptyentries--;
           handweight+=hand[emptyindex].getWeight();
              
        }  
       public void discardPebble(){
            WhiteBag wb = previousbag.getPair();
            int pebbleindex = 0;
            int pebbleweight = 0;
            if (handweight>100){
                for (int i=0;i<10;i++){
                    if (hand[i]!=null){
                    if(hand[i].getWeight()>pebbleweight){
                        pebbleindex = i;
                        pebbleweight = hand[i].getWeight();
                    }
                }}
            }
            else{
                pebbleweight = Integer.MAX_VALUE;
                for (int i=0;i<10;i++){
                    if (hand[i]!=null){
                    if(hand[i].getWeight()<pebbleweight){
                        pebbleindex = i;
                        pebbleweight = hand[i].getWeight();
                    }}
                }
            }
            wb.discard(hand[pebbleindex]);
            
            handweight -=pebbleweight;
            hand[pebbleindex]=null;
            emptyentries++;
        }
       public String getPlayerName(){
           return playername;
        }
       public boolean checkWinConditions(Player player){
            return handweight ==100 &&emptyentries==0;
       }
       @Override
        public void run(){
            boolean won = false;
            while (!won&&!Thread.currentThread().isInterrupted()){
                
            while(emptyentries>0&&handweight<100&&!Thread.currentThread().isInterrupted()){
                 drawPebble();
                }
            if (checkWinConditions(this)){won=true;}
            else{discardPebble();}
            }
            String endingstr = "My name is"+getPlayerName()+ "My hand is "+hand[0].getWeight();
            for (int i =1;i<hand.length;i++){
                if (hand[i]!=null){
                endingstr = endingstr + ","+hand[i].getWeight();}
            }System.out.println(endingstr);
       }
        
    }
   
    public static void main (String args[]){
        PebbleGame game1 = new PebbleGame();
        game1.getNoOfPlayers();
        game1.gamethread = new Thread(game1);
        game1.players = new PebbleGame.Player[game1.noofplayers];
        game1.initialiseBags();
        for (int i = 0;i<game1.players.length;i++){
            game1.addPlayer("Player "+i);
        }
        
        game1.gamethread.start();
    }
}
