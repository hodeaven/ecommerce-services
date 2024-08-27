package br.com.ufape.ecommerce.stock.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    Queue estoqueQueue() {
        return new Queue("estoqueQueue", true);
    }
}
