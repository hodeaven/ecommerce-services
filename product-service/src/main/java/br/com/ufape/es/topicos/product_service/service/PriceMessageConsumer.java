package br.com.ufape.es.topicos.product_service.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import br.com.ufape.es.topicos.product_service.config.RabbitMQConsumerConfig;

@Service
public class PriceMessageConsumer {

    @RabbitListener(queues = RabbitMQConsumerConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida do outro serviço: " + message);
    }
}
