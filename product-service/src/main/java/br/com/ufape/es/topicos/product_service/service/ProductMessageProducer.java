package br.com.ufape.es.topicos.product_service.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import br.com.ufape.es.topicos.product_service.config.RabbitMQConfig;

@Service
public class ProductMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public ProductMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
        System.out.println("Mensagem enviada: " + message);
    }
}
