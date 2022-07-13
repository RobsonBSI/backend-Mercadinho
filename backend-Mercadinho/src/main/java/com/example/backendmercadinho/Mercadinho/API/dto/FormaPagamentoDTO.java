package com.example.backendmercadinho.Mercadinho.API.dto;

import com.example.backendmercadinho.Mercadinho.model.entity.FormaPagamento;
import org.modelmapper.ModelMapper;

public class FormaPagamentoDTO {
    private Long id;
    private String formaPagamento;

    public static FormaPagamentoDTO create(FormaPagamento formaPagamento) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }
}
