package com.example.backendmercadinho.Mercadinho.model.repository;

import com.example.backendmercadinho.Mercadinho.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository  extends JpaRepository<Cliente,Long> {
}
