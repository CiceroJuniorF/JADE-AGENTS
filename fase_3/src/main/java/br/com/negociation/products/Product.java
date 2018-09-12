package br.com.negociation.products;

import java.math.BigDecimal;

/**
 * Products
 */
public class Product {

    private String name;
    private String type;
    private BigDecimal price;

    public Product(String name, String type, BigDecimal price){
        this.name = name;
        this.type = type;
        this.price = price;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{\"name\":\""+this.name+"\","
        + "\"type\":\""+this.type +"\","
        + "\"price\":\""+this.price +"\""
        +"}";
    }

}