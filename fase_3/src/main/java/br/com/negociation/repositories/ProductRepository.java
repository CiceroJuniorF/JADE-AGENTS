package br.com.negociation.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.negociation.products.Product;

/**
 * ProductRepository
 */
public class ProductRepository {

    // Simula uma busca na base de dados;
    public HashMap<String, List<Product>> getAllproducts(){
        Product product = null;
        List<Product> list = new ArrayList<>();
        HashMap<String, List<Product>> map = new HashMap<>();

        //Adicionando Valor para merchant-carrefour
        product = new Product("Arroz", "Tio Joao", new BigDecimal("15.00"));
        list.add(product);
        
        product = new Product("Feijao", "Kicaldo", new BigDecimal("10.00"));
        list.add(product);
        product =  new Product("Acucar", "Cristal", new BigDecimal("4.00"));
        list.add(product);
        //Adiciona a lista de produto para o respectivo vendedor
        map.put("merchant-carrefour", list);
        //Limpando a lista
        list = new ArrayList<>();
        
        //Adicionando Valor para merchant-carrefour
        product = new Product("Arroz", "Tio Joao", new BigDecimal("5.00"));
        list.add(product);
        product = new Product("Feijao", "Kicaldo", new BigDecimal("20.00"));
        list.add(product);
        product =  new Product("Acucar", "Cristal", new BigDecimal("10.00"));
        list.add(product);        
        //Adiciona a lista de produto para o respectivo vendedor
        map.put("merchant-extra", list);
        //Limpando a lista
        list = new ArrayList<>();
        
        //Adicionando Valor para merchant-carrefour
        product = new Product("Arroz", "Tio Joao", new BigDecimal("100.00"));
        list.add(product);
        product = new Product("Pacoca", "Pacoquita", new BigDecimal("2.00"));
        list.add(product);
        product =  new Product("Farofa", "Farofando", new BigDecimal("3.00"));
        list.add(product);
        //Adiciona a lista de produto para o respectivo vendedor
        map.put("merchant-dia", list);
        //Limpando a lista
        list = new ArrayList<>();   
            
        return map;

    }

}