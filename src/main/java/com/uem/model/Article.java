package com.uem.model;

import java.util.ArrayList;
/**
 * - Atributos
 *   - Id del pedido, en formato String
 *   - Lista de artículos
 * - Métodos
 *   - getters y setters de los atributos
 *   - constructor
 *   - getGrossTotal: método que calcula el total de todos los artículos
 *   - getDiscountedTotal: método que calcula el total de todos los artículos con descuento
 *   - toString
 */
public class Article {
    private String idPedido;
    private ArrayList<String> listaArticulos;
    
}
