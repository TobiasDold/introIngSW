package com.uem.model;
/**
 * - Atributos
 *   - nombre formato String
 *   - cantidad formato int. Cuantas unidades cojo del artículo
 *   - precio en formato double
 *   - descuento en formato double
 * - Métodos
 *   - getters y setters de los atributos
 *   - método getGrossAmount: devuelve la cantidad total de multiplicar las unidades por el precio. Utiliza clase Calculator
 *   - método getDiscountedAmount: devuelve el descuento aplicado al getGrossAmount anterior. Utiliza clase Calculator
 */
public class Order {
    private String nombre;
    private int cantidad;
    private double precio;
    private double descuento;
}
