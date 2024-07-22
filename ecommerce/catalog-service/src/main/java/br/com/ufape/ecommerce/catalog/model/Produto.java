package br.com.ufape.ecommerce.catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao; 
    

    @ManyToOne
    private Categoria categoria;

    public Produto (){
    }

    public Produto( String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

   
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
