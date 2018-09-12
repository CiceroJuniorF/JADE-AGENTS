package br.com.sma.dictionary;

import java.util.HashMap;
import java.util.List;

/**
 * Conversation
 */
public class Conversation {

    public static String talkKey(String key) {
        HashMap<String,List<String>> dictionary = Dictionary.dictionary();
        List<String> options = null;
       
        if(dictionary.containsKey(key)){
           options = dictionary.get(key);
            if(key.equals("Não sei oque responder, vamos começar denovo!"))
                return options.get(0);
            else            
                return options.get((int) ((options.size() > 1 ? (
                    Math.random()*options.size()-1) + 1 : 0
                )));
        }

        return "Não sei oque responder, vamos começar denovo!";        
    }
}