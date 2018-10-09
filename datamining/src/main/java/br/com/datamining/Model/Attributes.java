package br.com.datamining.Model;

import java.util.List;

import com.google.gson.Gson;

/**
 * Attributes
 */
public class Attributes {

    public Attributes(String name, String type, int position, List<String> values ){
        this.name = name;
        this.type = type;
        this.position = position;
        this.values = values;
    }
    public String name;
    public String type;
    public int position;
    public List<String> values;


   public void verifyAndAddValue(String value){
        if(!this.values.contains(value)){
            System.out.println("##Valor de Atributo encontrado: \n"+
                "###Nome Atributo: "+this.name+
                "\n####Valor encontrado e setado: "+value
            );
            this.values.add(value);
        }
   }

   @Override
   public String toString() {
      return "{"+
                "name: "+this.name+
                " type: "+this.type+
                " position:"+this.position+
                " values: "+this.values+""
            +"}";
   }
}