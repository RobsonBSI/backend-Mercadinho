package com.example.mercadinho.api.dto;

import com.example.mercadinho.model.entity.FormaPagamento;
import org.modelmapper.ModelMapper;

public class FormaPagamentoDTO {
    private Long id;
    private String formaPagamento;

    public static FormaPagamentoDTO create(FormaPagamento formaPagamento) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }
}
