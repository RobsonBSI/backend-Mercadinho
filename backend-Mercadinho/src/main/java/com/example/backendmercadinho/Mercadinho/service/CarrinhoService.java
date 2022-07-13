package com.example.backendmercadinho.Mercadinho.service;


import com.example.backendmercadinho.Mercadinho.model.entity.Carrinho;
import com.example.backendmercadinho.Mercadinho.model.repository.CarrinhoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class CarrinhoService {
    private CarrinhoRepository repository;

    public CarrinhoService(CarrinhoRepository repository) {
        this.repository = repository;
    }

    public List<Carrinho> getCarrinho() {
        return repository.findAll();
    }

    public Optional<Carrinho> getCarrinhoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Carrinho salvar(Carrinho carrinho) {
        return repository.save(carrinho);
    }

    @Transactional
    public void excluir(Carrinho carrinho) {
        Objects.requireNonNull(carrinho.getId());
        repository.delete(carrinho);
    }
}
