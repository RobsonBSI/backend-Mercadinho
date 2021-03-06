package com.example.mercadinho.service;

import com.example.mercadinho.model.entity.ItemCarrinho;
import com.example.mercadinho.model.repository.ItemCarrinhoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemCarrinhoService {
    private ItemCarrinhoRepository repository;

    public ItemCarrinhoService(ItemCarrinhoRepository repository) {
        this.repository = repository;
    }

    public List<ItemCarrinho> getItemCarrinho() {
        return repository.findAll();
    }

    public Optional<ItemCarrinho> getItemCarrinhoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ItemCarrinho salvar(ItemCarrinho itemCarrinho) {
        return repository.save(itemCarrinho);
    }

    @Transactional
    public void excluir(ItemCarrinho itemCarrinho) {
        Objects.requireNonNull(itemCarrinho.getId());
        repository.delete(itemCarrinho);
    }
}
