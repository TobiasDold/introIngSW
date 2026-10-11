package com.uem;

import com.uem.model.Article;
import com.uem.model.Order;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Order order = new Order("PED-001", List.of(
                new Article("Ratón", 2, 15.0, 10.0),
                new Article("Monitor", 1, 200.0, 25.0),
                new Article("Cable", 4, 2.5, 0.0)
        ));

        System.out.println("Pedido: " + order.getId());
        System.out.printf("%-10s %5s %10s %6s %10s %12s%n",
                "Artículo", "Uds", "Precio", "Dto%", "Bruto", "Con dto");
        for (Article a : order.getArticles()) {
            System.out.printf("%-10s %5d %10.2f %6.1f %10.2f %12.2f%n",
                    a.getName(), a.getQuantity(), a.getPrice(), a.getDiscount(),
                    a.getGrossAmount(), a.getDiscountedAmount());
        }
        System.out.printf("Total bruto:         %.2f%n", order.getGrossTotal());
        System.out.printf("Total con descuento: %.2f%n", order.getDiscountedTotal());
        System.out.println(order);
    }
}
