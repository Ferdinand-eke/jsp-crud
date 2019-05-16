/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.niit.simplewebapp.beans;

/**
 *
 * @author Zinachi Computer
 */
public class Product {
 
   private String code;
   private String name;
   private float price;
   private String filename;

    public Product() {
    }

    public Product(String code, String name, float price, String filename) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.filename = filename;
    }
    public Product(String code, String name, float price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
 
    
}
