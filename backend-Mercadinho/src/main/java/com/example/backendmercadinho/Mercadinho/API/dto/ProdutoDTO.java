package com.example.backendmercadinho.Mercadinho.API.dto;

import com.example.backendmercadinho.Mercadinho.model.entity.Produto;
import org.modelmapper.ModelMapper;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private Integer qtdadeEstoque;
    private Integer estoqueMax;
    private Integer estoqueMin;
    private Integer pontoRessuprimento;

    public static ProdutoDTO create(Produto produto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(produto, ProdutoDTO.class);
    }
}
