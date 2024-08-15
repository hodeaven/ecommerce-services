package br.com.ufape.ecommerce.stock.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O id do produto é obrigatório.")
    private Long produtoId;

    @NotNull(message = "O armazém é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "armazem_id", nullable = false)
    private Armazem armazem;

    @Min(value = 0, message = "A quantidade deste produto não pode ser negativa.")
    private int quantidade;
}
