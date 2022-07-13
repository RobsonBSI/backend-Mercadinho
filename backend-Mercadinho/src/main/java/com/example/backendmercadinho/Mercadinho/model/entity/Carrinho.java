package com.example.backendmercadinho.Mercadinho.model.entity;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity

@NoArgsConstructor
@AllArgsConstructor
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float valor;
    private Float valorFrete;

    @ManyToOne
    private FormaPagamento formaPagamento;

    public Object getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
}
