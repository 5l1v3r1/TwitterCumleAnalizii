
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
        }//okunan herbir cümleyi Tweet classına atma
         String sinif=null;
         for(int i=0;i<textOkuma.cumleList.size();i++){
              sinif = textOkuma.fileNameList.get(i);
              tweetList.get(i).setSinif(sinif); //cümlenin hangi sınıflandırmaya ait olduğunu çekildiği ana klasörün adına göre yapılması
         }
       //writeDataAtOnce(); 
       //tf-idf için istenen text dosyasını oluşturmak için fonksiyon, sadece bir kere çalıştırılmalı.
      
        
    }
      public static void writeDataAtOnce() 
{ 
    try { 
       PrintStream ps = new PrintStream(new File("src/main/java/out.txt"));
 
         String[] header = new String[textOkuma.stems.size()];
        for(Integer i=0;i<textOkuma.stems.size();i++){
        header[i]="a"+ i.toString();
            ps.print(header[i] +",");
        }
        ps.print("Class");
        ps.println("");
        int i=0;
        for(Tweet tweet:tweetList){
            i++;
            ps.print("Kayıt"+i+" ");
       for(String kelime:textOkuma.stems){
       
           ps.print(tweet.calculateTFIDF(textOkuma.cumleList,kelime)+",");
           
       }
       ps.print(tweet.getSinif());
       ps.println(" ");
       }
        
        ps.close();
    } 
    catch (IOException e) { 
        // TODO Auto-generated catch block 
        e.printStackTrace(); 
    } 
} 
    static TextOkuma textOkuma=new TextOkuma();
   
 

 

}
