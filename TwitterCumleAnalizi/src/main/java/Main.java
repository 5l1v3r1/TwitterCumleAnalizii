
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import zemberek.tokenization.Token;
import zemberek.tokenization.TurkishTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zeyne
 */

public class Main {
    
    static List<Tweet> tweetList = new ArrayList<Tweet>();
     public static void main(String[] args) throws IOException {
        
        textOkuma.textIslem();
         for(String metin:textOkuma.cumleList){
            Tweet tweet=new Tweet(metin);
            tweetList.add(tweet);
        }
         
          for(Tweet tweet:tweetList){
       for(String kelime:textOkuma.stems){
       
           System.out.print(tweet.calculateTFIDF(textOkuma.cumleList,kelime)+",");
           
       }
              System.out.println("");}
    
         
       //writeDataAtOnce();
      
        
    }
      public static void writeDataAtOnce() 
{ 
    try { 
       PrintStream ps = new PrintStream(new File(System.getProperty("user.home"), "out.txt"));
 
         String[] header = new String[textOkuma.stems.size()];
        for(Integer i=0;i<textOkuma.stems.size();i++){
        header[i]="a"+ i.toString();
        if(i!=(textOkuma.stems.size()-1)){
            ps.print(header[i] +",");
        }
        else ps.print(header[i]);
        }
        for(Tweet tweet:tweetList){
       for(String kelime:textOkuma.stems){
       
           ps.print(tweet.calculateTFIDF(textOkuma.cumleList,kelime)+",");
           
       }
        ps.println(" ");}
        ps.close();
    } 
    catch (IOException e) { 
        // TODO Auto-generated catch block 
        e.printStackTrace(); 
    } 
} 
     static TF_IDF tf_idf=new TF_IDF();
    static TextOkuma textOkuma=new TextOkuma();
   
 

 

}
