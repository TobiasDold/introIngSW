package com.uem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uem.Searcher;
import com.uem.model.Order;
import com.uem.view.OrderView;

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    /** Factor aplicado a los importes al mostrarlos (1.0 = sin conversión). */
    private static final double DEFAULT_RATE = 1.0;

    //Atributo de clase OrderView
    private final OrderView view;
    //Lista de orders
    private final List<Order> orders;
    //Atributo de clase Searcher
    private final Searcher searcher;

    public OrderController(OrderView view, List<Order> orders) {
        //Initialize attributes
        this.view = view;
        this.orders = orders;
        this.searcher = new Searcher();

        //Adding action listener to the search button, call searchOrder on click
        this.view.getSearchButton().addActionListener(e -> searchOrder());
    }

    private void searchOrder() {
        String id = view.getSearchId();

        //... display order if found, else display "Order not found."
        Order order = searcher.searchOrderById(id, orders);
        if (order != null) {
            log.debug("Order found: {}", id);
        } else {
            log.debug("Order not found: {}", id);
        }
        // displayOrder(null, ...) muestra "Order not found."
        view.displayOrder(order, DEFAULT_RATE);
    }
}
