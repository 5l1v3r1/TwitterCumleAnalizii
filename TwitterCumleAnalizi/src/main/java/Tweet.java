
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import zemberek.morphology.TurkishMorphology;
import zemberek.morphology.analysis.SingleAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
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
public class Tweet {
private String metin;
private double precision;
private double recall;
private double fScore;
private double truePositive;
private double falseNegative;
private double falsePositive;
private double tfIdf;

    

    public String getMetin() {
        return metin;
    }

    public void setMetin(String metin) {
        this.metin = metin;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getRecall() {
        return recall;
    }

    public void setRecall(double recall) {
        this.recall = recall;
    }

    public double getfScore() {
        return fScore;
    }

    public void setfScore(double fScore) {
        this.fScore = fScore;
    }

    public double getTruePositive() {
        return truePositive;
    }

    public void setTruePositive(double truePositive) {
        this.truePositive = truePositive;
    }

    public double getFalseNegative() {
        return falseNegative;
    }

    public void setFalseNegative(double falseNegative) {
        this.falseNegative = falseNegative;
    }

    public double getFalsePositive() {
        return falsePositive;
    }

    public void setFalsePositive(double falsePositive) {
        this.falsePositive = falsePositive;
    }

    public Tweet(String metin) {
       this.metin=metin;
        
    }

    public Tweet(double precision, double recall, double fScore, double truePositive, double falseNegative, double falsePositive) {
        this.precision = precision;
        this.recall = recall;
        this.fScore = fScore;
        this.truePositive = truePositive;
        this.falseNegative = falseNegative;
        this.falsePositive = falsePositive;
    }
    static TextOkuma textOkuma=new TextOkuma();
    public double calculateTF(String kelime){
    double res=0;
        if(metin.contains(kelime)){
        res++;
            
    }
        StringTokenizer st = new StringTokenizer(metin);

        return (res/st.countTokens());
    }
   
    public double calculateIDF(List<String> cumleList,String kelime){
   double res=0;
    double total=0;
    for(String text:cumleList){
        if(text.contains(kelime)){
        res++;
        }
    }
    total=Math.log10((cumleList.size()/res)+1);
    if(total==Double.POSITIVE_INFINITY){return 0;}
    return(total);
    
    }
    private double tf;
    private double idf;

    public double getIdf(List<String> cumleList,String kelime) {
        return calculateIDF(cumleList,kelime);
    }
    

    public double getTf(String kelime) {
        return calculateTF(kelime);
    }
    
    public double getTfIdf(List<String> cumleList,String kelime) {
        return calculateTFIDF(cumleList,kelime);
    }
    
    public double calculateTFIDF(List<String> cumleList,String kelime){
        
    return(calculateIDF(cumleList,kelime)*calculateTF(kelime));}

     

}
