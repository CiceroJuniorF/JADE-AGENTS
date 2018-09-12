package br.com.negociation.services;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.negociation.products.Product;

/**
 * MerchantService
 */
public class MerchantService {
    private static  Logger logger = Logger.getLogger(MerchantService.class);
    private List<Product> getAllProduct(String merchant){
        ProductService products = new ProductService();
        return products.productByMerchant(merchant);
    }

    public Product getProduct(String productName, String merchant){
        Product product = null;
        for (Product prod : this.getAllProduct(merchant)) {
            if(prod.getName().equals(productName)){
                product = prod;
            }
        }
        return product;        
    }
}