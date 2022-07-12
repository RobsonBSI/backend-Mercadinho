package com.example.backendmercadinho.Mercadinho.model.repository;

import com.example.backendmercadinho.Mercadinho.model.entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho,Long> {
}
