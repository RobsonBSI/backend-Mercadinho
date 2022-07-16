package com.example.mercadinho.api.dto;

import com.example.mercadinho.model.entity.ItemCarrinho;
import org.modelmapper.ModelMapper;

public class ItemCarrinhoDTO {
    private Long id;
    private Integer qtdadeProduto;
    private double valorTotalItem;
    private Long idCliente;
    private Long idCarrinho;
    private Long idProduto;

    public static ItemCarrinhoDTO create(ItemCarrinho itemCarrinho) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(itemCarrinho, ItemCarrinhoDTO.class);
    }
}
