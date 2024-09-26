package br.com.ufape.es.topicos.price_service.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import br.com.ufape.es.topicos.price_service.config.RabbitMQConfig;

@Service
public class PriceMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public PriceMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
        System.out.println("Mensagem enviada: " + message);
    }
}