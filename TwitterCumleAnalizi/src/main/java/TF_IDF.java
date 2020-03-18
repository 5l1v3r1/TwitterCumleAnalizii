
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
public class TF_IDF {
    List<Double> tfResult=new ArrayList<Double>();
    public void calculateTF(List<String> belge,List<String> tokens){
    double res=0;
    int i=0;
    for(String cumle:belge){
        for(String token:tokens){
        
        if(token.equalsIgnoreCase(cumle)){
        res++;
        }
        tfResult.add(res/belge.size());
            }
    
    }
    }
    List<Double> idfResult=new ArrayList<Double>();
    public void calculateIDF(List<String> belge,List<String> tokens){
    double res=0;
    int i=0;
    for(String cumle:belge){
        for(String token:tokens){
        
        if(token.equalsIgnoreCase(cumle)){
        res++;
        }
        idfResult.add(Math.log(belge.size() / res));
            }
    
    }
    }
     List<Double> tfidfResult=new ArrayList<Double>();
    public void calculateTFIDF(List<String> belge,List<String> tokens){
        calculateIDF(belge, tokens);
        calculateTF(belge, tokens);
        for(Double idf:idfResult){
        for(Double tf:tfResult){
        
    tfidfResult.add(idf*tf);
        }
        }
    }
}
