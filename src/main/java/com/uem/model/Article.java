package com.uem.model;

import com.uem.Calculator;

/**
 * POJO que representa un artículo dentro de un pedido.
 * El descuento se expresa como porcentaje (0 - 100).
 */
public class Article {

    private static final Calculator CALCULATOR = new Calculator();

    private String name;
    private int quantity;
    private double price;
    private double discount;

    public Article() {
    }

    public Article(String name, int quantity, double price, double discount) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    /** Importe bruto: unidades * precio unitario. */
    public double getGrossAmount() {
        return CALCULATOR.multiply((double) quantity, price);
    }

    /** Importe tras aplicar el porcentaje de descuento al importe bruto. */
    public double getDiscountedAmount() {
        return CALCULATOR.discount(getGrossAmount(), discount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
