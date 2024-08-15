package br.com.ufape.ecommerce.price.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Preco {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "O ID do produto é obrigatório.")
    private long produtoId;
    
    @NotNull(message = "O valor do produto é obrigatório.")
    private double valorDoProduto;
    
    @ManyToOne
    @NotNull(message = "A política é obrigatória.")
    private Politica politica;

}
