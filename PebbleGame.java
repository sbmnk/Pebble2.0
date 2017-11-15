
import java.util.Scanner;
import java.io.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
public class PebbleGame implements Runnable
{
   private  AtomicBoolean gamewon = new AtomicBoolean(); 
   private PebbleGame.Player[] players ; 
   private  BlackBag[]blackbags = new BlackBag[Constants.AMOUNT_OF_BAGS.getInt()];
   PebbleGame(){
    
    }
   private static boolean checkFile(File a) throws InvalidFileException{
    
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
        throw new InvalidFileException();   
    }
    
   private Integer[] readFile(File a) throws InsufficientAmountOfPebblesException{
        try{
        Scanner sc = new Scanner (a);
        if (sc.hasNextLine()){
               String pw = sc.nextLine();               
               String[] str = pw.split(",");
               Integer[] pbs = new Integer[str.length];
               for (int i = 0; i<str.length; i++){
                   pbs[i] = Integer.valueOf(str[i]);
                }
                if (pbs.length<(players.length*11)){throw new InsufficientAmountOfPebblesException("Make sure that every bag has at least 11 pebbles for each player");}
                return pbs;
            }
        } catch (java.io.FileNotFoundException |IllegalStateException e){
            System.out.println("Please enter an existing file containing comma seperated list of Integers");            
     
        }
        return null;}
        
   private void initialiseBags() throws  IllegalWeightException, InsufficientAmountOfPebblesException{
       boolean correct ;
       File [] files ;
       String[] blackbagnames = {"X","Y","Z"};
       String[] whitebagnames = {"A","B","C"};
       correct = true;
       files = getFiles();
      for (int i =0;i<files.length;i++){
          Integer[] bagpebblesvalues =readFile(files[i]);
          Pebble[] bagpebbles = new Pebble [bagpebblesvalues.length];
          for (int j = 0;j<bagpebblesvalues.length;j++){
              bagpebbles[j] =new Pebble(bagpebblesvalues[j]);}
          blackbags[i]= new BlackBag(blackbagnames[i],bagpebbles,new WhiteBag(whitebagnames[i],blackbags[i]));
    }}
   private static String getFileExtension(File a){
     String fileName = a.getName();
     if(fileName.lastIndexOf(".")!=-1&&fileName.lastIndexOf(".")!=0){
         return fileName.substring(fileName.lastIndexOf(".")+1);
     } else {return "";}
    }
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
    public void setGameWon(){
        gamewon.set(true);
    }
   private  File[] getFiles() {
        File[] files = new File[Constants.AMOUNT_OF_BAGS.getInt()];
        Scanner scan = new Scanner (System.in);
        boolean correct = true;
        while (correct){
             correct = false;
                String [] filenames = new String[files.length];
                for (int j=0 ;j<files.length;j++){
                System.out.println("Please enter the file path of file "+(j+1));
                filenames[j] = scan.next();
                if (filenames[j].equals("E")){System.exit(0);}
                File a = new File(filenames[j]);
                files[j]=a;
                try{
                checkFile(files[j]);}
                catch(InvalidFileException e){ correct=true;System.out.println("Please, enter a correct filepath");break;}
            
        }}
    return files;
}
   @Override
   public void run(){
        for (Thread t : players){
            t.start();
        }
       }
   private  class Player extends Thread{
       private FileWriter fw = new FileWriter();
       private final String playername ;
       private  final Pebble[] hand = new Pebble[10];
       private BlackBag previousbag;
       private  int handweight=0; 
       private int emptyentries = 10;
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
       public void printHand(){String q = playername+" hand is ";
           for (Pebble i : hand){if(i!=null){q= q+i.getWeight()+",";}} fw.writeToFile(q.substring(0,q.length()-1));}
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
           boolean isempty;
           int bagindex;
           do {
           bagindex = (randomno.nextInt(3)) ;
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
            return handweight ==100 &&emptyentries==0;
       }
       @Override
        public void run(){
            fw.createFile(playername+".txt");
            while (!gamewon.get()){
            while(emptyentries>0&&handweight<100&&!gamewon.get()){
                 drawPebble();
                }
            if (checkWinConditions(this)){PebbleGame.this.setGameWon();}
            else{discardPebble();}
            }
       }
        
    }
   
    public static void main (String args[]) throws IllegalFileFormatException, IllegalWeightException, InsufficientAmountOfPebblesException, OutOfPebblesException, IllegalAmountOfPlayersException{
        PebbleGame game1 = new PebbleGame();
        game1.initialisePlayers();
        game1.initialiseBags();
        game1.run();
    }
}
