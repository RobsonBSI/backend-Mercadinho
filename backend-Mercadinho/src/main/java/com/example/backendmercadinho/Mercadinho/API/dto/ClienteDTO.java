package com.example.backendmercadinho.Mercadinho.API.dto;

import com.example.backendmercadinho.Mercadinho.model.entity.Cliente;
import org.modelmapper.ModelMapper;

public class ClienteDTO {
    private Long id;
    private String nome;
    private String cep;
    private String logradouro;
    private String bairro;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String telefone;
    private String email;

    public static ClienteDTO create(Cliente cliente) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cliente, ClienteDTO.class);
    }
}
