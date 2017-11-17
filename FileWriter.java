 

import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
public class FileWriter
{
    PrintWriter writer;
    String filename;
    FileWriter(){}
    public void createFile(String filename){
        this.filename = filename;
   try{ PrintStream writeToFile = new PrintStream(new FileOutputStream(filename));}
   catch(IOException e){}
    }
    public void writeToFile(String contents){
        try{ PrintStream writeToFile = new PrintStream(new FileOutputStream(filename,true));
        writeToFile.println(contents);
        writeToFile.close();}
   
        catch(IOException e){}
    
    }
     public  boolean checkFile(File a) throws InvalidFileException{
    
       if (a.exists()){
           String fileformat =getFileExtension(a); 
           if(fileformat.equals("txt")|fileformat.equals("csv")){
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
    
   public Integer[] readFile(File a,int amountofplayers) throws InsufficientAmountOfPebblesException{
        try{
        Scanner sc = new Scanner (a);
        if (sc.hasNextLine()){
               String pw = sc.nextLine();               
               String[] str = pw.split(",");
               Integer[] pbs = new Integer[str.length];
               for (int i = 0; i<str.length; i++){
                   pbs[i] = Integer.valueOf(str[i]);
                }
                if (pbs.length<(amountofplayers*Constants.MINIMUM_AMOUNT_OF_PEBBLES_PER_PLAYER.getInt())){throw new InsufficientAmountOfPebblesException("Make sure that every bag has at least 11 pebbles for each player");}
                return pbs;
            }
        } catch (java.io.FileNotFoundException |IllegalStateException e){
            System.out.println("Please enter an existing file containing comma seperated list of Integers");            
     
        }
        return null;}
           public static String getFileExtension(File a){
     String fileName = a.getName();
     if(fileName.lastIndexOf(".")!=-1&&fileName.lastIndexOf(".")!=0){
         return fileName.substring(fileName.lastIndexOf(".")+1);
     } else {return "";}
    }
       public  File[] getFiles() {
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
    
    
    }
