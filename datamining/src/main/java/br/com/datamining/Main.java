package br.com.datamining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.datamining.Model.Attributes;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("HELLO WOLRD!");

        // Cria o Arquivo
        File file1 = new File("C:\\Projects\\STUDY\\datamining\\arquivos", "\\Dados - FINALLY.arff");

        BufferedReader br = new BufferedReader(
                new FileReader("C:\\Projects\\STUDY\\datamining\\arquivos\\Dados - Brutos.arff"));
        // Começa a leitura do arquivo.
        List<Attributes> atributos = new ArrayList<>();
        List<String[]> allLinhas = new ArrayList<>();
        String relacao = "";
        String linhaFormatada[] = null;
        int colunas = 0;
        while (br.ready()) {
            String linha = br.readLine();
            System.out.println("Lendo Nova Linha!");
            if (linha.isEmpty()) {

            } else {
                if (linha.contains("@")) {
                    if (linha.contains("@relation")) {
                        System.out.println("Relação Encontrada: "+linha);
                        relacao = linha;
                    }
                    if (linha.contains("@attribute")) {
                        String linhaAtributo[] = new String[3];
                        linhaAtributo = linha.split(" ");
                        Attributes atributo = new Attributes(linhaAtributo[1], linhaAtributo[2], colunas,
                                new ArrayList<>());
                        atributos.add(atributo);
                        System.out.println("Atributo encontrada: "+atributo);
                        colunas++;
                    }
                } else {
                    linhaFormatada = new String[colunas];                    
                    String[] linhaPreFormatada = linha.split(";",-1); 
                    
                    for (int x =0;linhaFormatada.length > x; x++) {
                        if(x > linhaPreFormatada.length-1){
                            linhaFormatada[x] = "";
                        }else{
                            linhaFormatada[x] = linhaPreFormatada[x];
                        }   
                    }                                   
                    String linhaSalvar[] = new String[colunas];
                    int i = 0;
                    for (String item : linhaFormatada) {
                        Attributes atributo = atributos.get(i);
                        if (atributo.type.equals("string")) {
                            if(item.isEmpty()){
                                item = "\"\"";
                            }else{
                             item = "'" + item + "'";
                            }
                        }
                        atributo.verifyAndAddValue(item);                        
                        linhaSalvar[i] = item + ",";
                        i++;
                    }
                    
                    int tamanho = linhaSalvar[colunas - 1].length();
                    linhaSalvar[colunas - 1] = linhaSalvar[colunas - 1].substring(0, tamanho - 1);
                    allLinhas.add(linhaSalvar);
                }
            }

        }
        br.close();
        System.out.println("Escrevendo novo arquivo: "+file1.getName());
        BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
        writer.write(relacao);
        writer.newLine();
        writer.newLine();
        for (Attributes atr : atributos) {
            String linhaAtributo = "@attribute ";
            linhaAtributo += atr.name + " ";
            linhaAtributo += "{";
            String values = "";
            for (String vl : atr.values) {
                values += vl + ",";
            }
            int tamanho = values.length();
            values = values.substring(0, tamanho - 1);
            linhaAtributo += values +"}";
            writer.write(linhaAtributo);
            writer.newLine();
        }
        writer.newLine();
        writer.write("@data");
        writer.newLine();
        // LINHA
        for (String[] var : allLinhas) {
            String linha = "";
            for (String item : var) {
                linha += item;
            }
            writer.write(linha);
            writer.newLine();
        }
        writer.flush();
        writer.close();
        System.out.println("Arquivo escrito: "+file1.getName());
        file1.createNewFile();
    }
}