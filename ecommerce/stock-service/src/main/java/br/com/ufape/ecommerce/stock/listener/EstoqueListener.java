package br.com.ufape.ecommerce.stock.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueListener {

    @RabbitListener(queues = "estoqueQueue")
    public void receiveMessage(Long produtoId) {
        // Lógica para criar um item de estoque com o produtoId
        System.out.println("Criar estoque para o produto ID: " + produtoId);
    }
}
