package com.uem.model;

import com.uem.Calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO que representa un pedido compuesto por una lista de artículos.
 */
public class Order {

    private static final Calculator CALCULATOR = new Calculator();

    private String id;
    private List<Article> articles;

    public Order() {
        this.articles = new ArrayList<>();
    }

    public Order(String id, List<Article> articles) {
        this.id = id;
        // Copia defensiva; una lista nula se trata como pedido vacío
        this.articles = (articles != null) ? new ArrayList<>(articles) : new ArrayList<>();
    }

    /** Suma de los importes brutos (sin descuento) de todos los artículos. */
    public double getGrossTotal() {
        return CALCULATOR.calculateTotal(
                articles.stream().map(Article::getGrossAmount).toList());
    }

    /** Suma de los importes con descuento aplicado de todos los artículos. */
    public double getDiscountedTotal() {
        return CALCULATOR.calculateTotal(
                articles.stream().map(Article::getDiscountedAmount).toList());
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = (articles != null) ? new ArrayList<>(articles) : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", articles=" + articles +
                ", grossTotal=" + getGrossTotal() +
                ", discountedTotal=" + getDiscountedTotal() +
                '}';
    }
}
