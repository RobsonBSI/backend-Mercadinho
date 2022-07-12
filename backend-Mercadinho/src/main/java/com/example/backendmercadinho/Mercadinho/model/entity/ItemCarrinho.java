package com.example.backendmercadinho.Mercadinho.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer qtdadeProduto;
    private double valorTotalItem;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Carrinho carrinho;

    @ManyToOne
    private Produto produto;

}
