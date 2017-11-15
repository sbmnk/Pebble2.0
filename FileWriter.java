import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Arrays;
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
    }
