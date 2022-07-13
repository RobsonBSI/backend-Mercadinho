package com.example.backendmercadinho.Mercadinho.service;


import com.example.backendmercadinho.Mercadinho.model.entity.FormaPagamento;
import com.example.backendmercadinho.Mercadinho.model.repository.FormaPagamentoRepository;
import com.example.backendmercadinho.Mercadinho.model.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FormaPagamentoService {
    private FormaPagamentoRepository repository;

    public FormaPagamentoService(FormaPagamentoRepository repository) {
        this.repository = repository;
    }

    public List<FormaPagamento> getFormaPagamento() {
        return repository.findAll();
    }

    public Optional<FormaPagamento> getFormaPagamentoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    @Transactional
    public void excluir(FormaPagamento formaPagamento) {
        Objects.requireNonNull(formaPagamento.getId());
        repository.delete(formaPagamento);
    }
}
