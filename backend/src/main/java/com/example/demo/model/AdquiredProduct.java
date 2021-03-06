package com.example.demo.model;

public class AdquiredProduct {

    private String productName;
    private String productBrand;
    private Double productPrice;
    private Integer productQuantity;

    public AdquiredProduct(String name, String brand, Double price, Integer quantity){
        this.productName = name;
        this.productBrand = brand;
        this.productPrice = price;
        this.productQuantity = quantity;
    }

    public Double price() {  return this.productPrice;  }

    public String name() { return this.productName;  }

    public String brand() { return this.productBrand; }

    public Integer quantity() {  return this.productQuantity;  }

    public Double totalPrice() { return productQuantity * productPrice;  }
}
