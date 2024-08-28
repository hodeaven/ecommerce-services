package br.com.ufape.es.topicos.product_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do produto é obrigatório.")
    private String name;

    @NotNull(message = "A descrição do produto é obrigatória.")
    private String description;

    @NotNull(message = "O tamanho do produto é obrigatório.")
    private String size;
}
