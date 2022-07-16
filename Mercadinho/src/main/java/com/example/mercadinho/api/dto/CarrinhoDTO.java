package com.example.mercadinho.api.dto;

import com.example.mercadinho.model.entity.Carrinho;
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
