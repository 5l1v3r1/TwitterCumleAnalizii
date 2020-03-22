/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import zemberek.langid.LanguageIdentifier;
import zemberek.morphology.TurkishMorphology;
import zemberek.morphology.analysis.SingleAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.normalization.TurkishSentenceNormalizer;
import zemberek.normalization.TurkishSpellChecker;
import zemberek.tokenization.Token;
import zemberek.tokenization.TurkishTokenizer;

/**
 *
 * @author zeyne
 */

public class TextOkuma {

    public TextOkuma() {
        
    }
    List<String> cumleList = new ArrayList<String>(); //cümleleri tutan liste
    List<String> stopWord = new ArrayList<String>(); //stopWordleri tutan liste
    List<String> fileNameList = new ArrayList<String>(); //cümleleri sınıflandırmak için kullanılacak
 public void textIslem() throws FileNotFoundException, IOException{
    Path lookupRoot = Paths.get("src/main/java/normalization");
     Path lmFile = Paths.get("src/main/java/lm/lm.2gram.slm");
     TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
     TurkishSentenceNormalizer normalizer = new
     TurkishSentenceNormalizer(morphology, lookupRoot, lmFile);
     TurkishSpellChecker spellChecker = new TurkishSpellChecker(morphology);
     BufferedReader reader;
     File dir = new File("src/main/java/raw_texts");
     
     File[] folder = dir.listFiles(); //Cümleleri çekme kısmı
        for(File table : folder) {
            //System.out.println(table);
            File[] filenames = table.listFiles();
                for (File file : filenames) {
                    fileNameList.add(file.getParentFile().getName());
                    //System.out.println(file);
                    reader = new BufferedReader(new FileReader(file));
                    Scanner scanner = new Scanner(file); 
                    StringBuffer buffer=new StringBuffer();
			while (scanner.hasNextLine()) {
                             buffer.append(scanner.nextLine().toLowerCase(new Locale("tr-TR")));
                               }
                        cumleList.add(normalizer.normalize(new String(buffer)));
                        
                    }
            }
        
        readStopWords();
        tokenize();
        removeStopWords();
        removeDuplicates((ArrayList<String>) tokens);
        stemmize(); //Klasör 3'teki 969.txt içi boş olduğu için bu fonksiyonda hata veriyor o yüzden o texti sildim.
 }
 
    
  public void readStopWords() throws FileNotFoundException{
     BufferedReader reader;//stopwordsleri okuma
     File file = new File("src/main/java/stop-words-turkish-github.txt");
     reader = new BufferedReader(new FileReader(file));
            Scanner scanner = new Scanner(file); 
		while (scanner.hasNextLine()) {
                    stopWord.add(scanner.nextLine().toLowerCase());
                }
               
 }

  List<String> tokens = new ArrayList<String>();
     static TurkishTokenizer tokenizer = TurkishTokenizer.DEFAULT;
 public void tokenize() throws FileNotFoundException{
     //cümleleri Zemberek yardımıyla kelime kelime ayırma işlemi
     for(String ar:cumleList){
             Iterator<Token> tokenIterator = tokenizer.getTokenIterator(ar);
    while (tokenIterator.hasNext()) {
      //Token token = tokenIterator.next();
      
           tokens.add(tokenIterator.next().getText());
    }
     }
      //System.out.println(tokens.size());
 }
 TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
 List<String> stems = new ArrayList<String>();
 LanguageIdentifier lid = LanguageIdentifier.fromInternalModelGroup("tr_group");//buradaki çalışmasına engel olmayan hata naasıl çözülecek bilemedim.
 public void stemmize(){ //köklerine göre gruplandırma ve türkçe olan kelimeleri alma
     for(String a:tokens){
         WordAnalysis result = morphology.analyze(a);
for (SingleAnalysis analysis : result) {
            if ((!stems.contains(analysis.getStem()))&&lid.containsLanguage(analysis.getStem(), "tr", 280)) { 
                stems.add(analysis.getStem()); 
            } 
}
     }
 }
public void removeStopWords(){
    tokens.removeAll(stopWord);//kelimelerin içerisinden stopwordleri çıkarma
   /* String a[]= {"0","1","2","3","4","5","6","7","8","9","!",":",";",",",".","?"};
    tokens.remove(a);*/
}
 public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) 
    { //listedeki tekrar edenleri çıkarma 
        // Create a new ArrayList 
        ArrayList<T> newList = new ArrayList<T>(); 
        // Traverse through the first list 
        for (T element : list) { 
            // If this element is not present in newList 
            // then add it 
            if (!newList.contains(element)) { 
                newList.add(element); 
            } 
        } 
        // return the new list 
        return newList; 
    } 
}
