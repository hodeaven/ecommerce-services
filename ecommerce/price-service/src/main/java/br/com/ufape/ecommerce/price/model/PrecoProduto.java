package br.com.ufape.ecommerce.price.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class PrecoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    
    private double preco;
    private String nomeProduto;
    public long politicaId;

    @ManyToMany
    private List<Politica> politicas = new ArrayList<Politica>();

    public PrecoProduto() {
    }

    public PrecoProduto(double preco, String nomeProduto) {
        this.preco = preco;
        this.nomeProduto = nomeProduto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public List<Politica> getPoliticas() {
        return politicas;
    }

    public void setPoliticas(Politica politicas) {
        this.politicas.add(politicas);
    }

}
