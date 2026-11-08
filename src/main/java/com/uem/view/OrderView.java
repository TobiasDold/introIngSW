package com.uem.view;

import com.uem.model.Article;
import com.uem.model.Order;

import javax.swing.*;
import java.awt.*;

public class OrderView extends JFrame {
    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Search");
    private JTextArea resultArea = new JTextArea(10, 40);

    public OrderView() {
        setTitle("Order Management");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        resultArea.setEditable(false);
        resultArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        add(new JLabel("Order ID:"));
        add(searchField);
        add(searchButton);
        add(new JScrollPane(resultArea));

        // Pulsar Enter en el campo de texto equivale a pulsar Search
        getRootPane().setDefaultButton(searchButton);

        pack();
        setLocationRelativeTo(null); // centrar ventana
        setVisible(true);
    }

    /** @return id introducido por el usuario, sin espacios laterales */
    public String getSearchId() {
        return searchField.getText().trim();
    }

    /** El controlador lo usa para registrar su ActionListener. */
    public JButton getSearchButton() {
        return searchButton;
    }

    /**
     * Muestra el detalle del pedido en resultArea.
     *
     * @param order pedido a mostrar; si es null se muestra "Order not found."
     * @param rate  factor aplicado a los importes (1.0 = sin conversión)
     */
    public void displayOrder(Order order, double rate) {
        if (order == null) {
            resultArea.setText("Order not found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(order.getId()).append('\n');
        sb.append("--------------------------------------------\n");
        for (Article a : order.getArticles()) {
            sb.append(String.format("%-12s x%-3d %8.2f  (-%.0f%%)%n",
                    a.getName(), a.getQuantity(),
                    a.getGrossAmount() * rate, a.getDiscount()));
        }
        sb.append("--------------------------------------------\n");
        sb.append(String.format("Gross total:      %8.2f%n", order.getGrossTotal() * rate));
        sb.append(String.format("Discounted total: %8.2f%n", order.getDiscountedTotal() * rate));

        resultArea.setText(sb.toString());
        resultArea.setCaretPosition(0); // scroll al inicio
    }
}
