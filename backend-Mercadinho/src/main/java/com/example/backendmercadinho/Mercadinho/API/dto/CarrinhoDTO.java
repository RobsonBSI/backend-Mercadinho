package com.example.backendmercadinho.Mercadinho.API.dto;

import com.example.backendmercadinho.Mercadinho.model.entity.Carrinho;
import org.modelmapper.ModelMapper;

public class CarrinhoDTO {
    private Long id;
    private Float valor;
    private Float valorFrete;
    private Long idFormaPagamento;

    public static CarrinhoDTO create(Carrinho carrinho) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carrinho, CarrinhoDTO.class);
    }
}
