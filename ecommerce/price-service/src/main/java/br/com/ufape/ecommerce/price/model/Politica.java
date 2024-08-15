package br.com.ufape.ecommerce.price.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Politica {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;
    
    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

}
