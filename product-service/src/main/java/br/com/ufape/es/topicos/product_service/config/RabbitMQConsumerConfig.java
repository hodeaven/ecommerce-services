package br.com.ufape.es.topicos.product_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConsumerConfig {

    public static final String PRICE_QUEUE = "price_queue";
    public static final String INVENTORY_QUEUE = "inventory_queue";

    @Bean
    public Queue priceQueue() {
        return new Queue(PRICE_QUEUE, true);
    }

    @Bean
    public Queue inventoryQueue() {
        return new Queue(INVENTORY_QUEUE, true);
    }
}