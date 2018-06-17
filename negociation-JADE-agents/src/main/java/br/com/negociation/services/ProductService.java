package br.com.negociation.services;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.negociation.agents.BuyerAgent;
import br.com.negociation.products.Product;
import br.com.negociation.repositories.ProductRepository;

/**
 * ProductService
 */
public class ProductService {

    //Repositório dos produtos
    ProductRepository repository = new ProductRepository();

    private static  Logger logger = Logger.getLogger(ProductService.class);

    //Método responsável por retornar os produtos filtrados por merchant.
    public List<Product> productByMerchant(String merchant)throws RuntimeException{
        logger.debug("Start search(product)...");
        if(repository.getAllproducts().containsKey(merchant)){
            return repository.getAllproducts().get(merchant);
        }else{
            logger.error("Merchant no exist.");
            throw new RuntimeException("Merchant no exist, please try again");
        }
    }

}