package br.com.ufape.ecommerce.catalog.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome; 

    public Categoria() {

    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos; 
    public Categoria( String nome) {
        this.nome = nome;
    }
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
