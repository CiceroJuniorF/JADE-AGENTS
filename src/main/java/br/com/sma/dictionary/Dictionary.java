package br.com.sma.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Dictionary {

    //Método responsavel por gerara uma base para uma conversa.
    public static HashMap<String, List<String>> dictionary(){
        List<String> list = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        String key = "";
        key = "Não sei oque responder, vamos começar denovo!";
        list.add("Hello how are you?");
        map.put(key, list);
        //=================Inicio da Chave==============================            
        key = "Hello how are you?";        
        list.add("I'm fine!");
        list.add("I'm fine and you?");
        list.add("I'm very bad!");        
        map.put(key, list);
        //=================Fim da Chave================================= 
        list = new ArrayList<>();
        //=================Inicio da Chave==============================
        key = "I'm fine!"; 
        list.add("Nice man, how are you doing?");
        list.add("Good Very God!!");
        list.add("Oh, ok!");
        map.put(key, list);
        //=================Fim da Chave================================ 
        list = new ArrayList<>();
        //=================Inicio da Chave==============================
        key = "I'm fine and you?"; 
        list.add("I'm fine too");
        list.add("more or less");
        list.add("I'm very bad!");
        map.put(key, list);
        //=================Fim da Chave================================ 
        list = new ArrayList<>();
        //=================Inicio da Chave==============================
        key = "I'm very bad!"; 
        list.add("Why?");
        list.add("It's normal!");
        list.add("Ok, bye!");
        map.put(key, list);
        //=================Fim da Chave================================
        list = new ArrayList<>();
        //=================Inicio da Chave==============================
        key = "Why?"; 
        list.add("Problems!");
        map.put(key, list);
        //=================Fim da Chave================================ 
        list = new ArrayList<>();
        //=================Inicio da Chave==============================
        key = "It's normal!"; 
        list.add("Really?");
        map.put(key, list);
        //=================Fim da Chave================================
        list = new ArrayList<>();
        //=================Inicio da Chave==============================
        key = "Ok, bye!"; 
        list.add("Bye!");
        map.put(key, list);
        //=================Fim da Chave================================              
        return map;
    }
    
}