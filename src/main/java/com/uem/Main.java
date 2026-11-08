package com.uem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uem.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final String ORDERS_FILE = "orders.json";

    public static void main(String[] args) {
        List<Order> orders = loadOrders(ORDERS_FILE);

        for (Order order : orders) {
            log.debug("Loaded order: {}", order.getId());
        }
        log.info("Total orders loaded: {}", orders.size());
    }

    /**
     * Lee y parsea un fichero JSON del classpath (src/main/resources) a una lista de pedidos.
     *
     * @param resource nombre del fichero dentro de resources
     * @return lista de pedidos; vacía si el fichero no existe o el parseo falla
     */
    static List<Order> loadOrders(String resource) {
        ObjectMapper mapper = new ObjectMapper();

        // getResourceAsStream funciona tanto desde el IDE como empaquetado en .jar
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream(resource)) {
            if (is == null) {
                log.error("Resource not found: {}", resource);
                return List.of();
            }
            // TypeReference conserva el tipo genérico List<Order> (borrado de tipos)
            return mapper.readValue(is, new TypeReference<List<Order>>() {});
        } catch (IOException e) {
            log.error("Error parsing {}", resource, e);
            return List.of();
        }
    }
}
