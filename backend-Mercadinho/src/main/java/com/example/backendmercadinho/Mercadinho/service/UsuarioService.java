package com.example.backendmercadinho.Mercadinho.service;

import com.example.backendmercadinho.Mercadinho.model.entity.Usuario;
import com.example.backendmercadinho.Mercadinho.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UsuarioService {

    //@Autowired
   // private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public UserDetails loadUserByUsername(String username) {
        return null;
    };
}
