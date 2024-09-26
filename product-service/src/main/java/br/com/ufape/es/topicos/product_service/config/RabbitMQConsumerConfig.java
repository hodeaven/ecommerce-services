package br.com.ufape.es.topicos.product_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConsumerConfig {

    public static final String QUEUE_NAME = "price_queue"; // Nome da fila existente no RabbitMQ

    @Bean
    public Queue priceQueue() {
        // Esta fila deve ter o mesmo nome que a fila para onde o outro serviço publica
        return new Queue(QUEUE_NAME, true); // true significa que a fila é durável
    }
}