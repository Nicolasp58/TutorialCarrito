package com.example.springboot.controllers;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductForm {

    @NotEmpty(message = "The product name is required")
    private String name;

    @NotNull(message = "The price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotEmpty(message = "The product desciption is required")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}