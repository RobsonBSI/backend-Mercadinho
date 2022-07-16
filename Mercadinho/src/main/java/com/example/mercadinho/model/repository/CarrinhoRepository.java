package com.example.mercadinho.model.repository;

import com.example.mercadinho.model.entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho,Long> {
}
