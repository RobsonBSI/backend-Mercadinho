package com.example.mercadinho.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer qtdadeEstoque;
    private Integer estoqueMax;
    private Integer estoqueMin;
    private Integer pontoRessuprimento;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
