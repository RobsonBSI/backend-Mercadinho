package com.example.backendmercadinho.Mercadinho.model.repository;

import com.example.backendmercadinho.Mercadinho.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}

