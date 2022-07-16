package com.example.mercadinho.api.controller;

import com.example.mercadinho.api.dto.CarrinhoDTO;
import com.example.mercadinho.exception.RegraNegocioException;
import com.example.mercadinho.model.entity.Carrinho;
import com.example.mercadinho.service.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/API/v1/carrinho")
@RequiredArgsConstructor
public class CarrinhoController {
    private ModelMapper modelMapper;

    private final CarrinhoService service;

    @GetMapping
    public ResponseEntity get() {
        List<Carrinho> carrinho = service.getCarrinho();
        return ResponseEntity.ok(carrinho.stream().map(CarrinhoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Carrinho> carrinho = service.getCarrinhoById(id);
        if (!carrinho.isPresent()) {
            return new ResponseEntity("Carrinho  vazio", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(carrinho.map(CarrinhoDTO::create));
    }

    @PostMapping
    public ResponseEntity post(@RequestBody CarrinhoDTO dto) {
        try {
            Carrinho carrinhoRequisicao = modelMapper.map(dto, Carrinho.class);
            Carrinho carrinho= service.salvar(carrinhoRequisicao);

            CarrinhoDTO carrinhoResposta = modelMapper.map(carrinho, CarrinhoDTO.class);
            return new ResponseEntity(carrinhoResposta, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody CarrinhoDTO dto) {
        if(!service.getCarrinhoById(id).isPresent()) {
            return new ResponseEntity("Carrinho não encontrado", HttpStatus.NOT_FOUND);
        }

        try {
            Carrinho carrinhoRequisicao = modelMapper.map(dto, Carrinho.class);
            carrinhoRequisicao.setId(id);
            Carrinho  carrinho = service.salvar(carrinhoRequisicao);

            CarrinhoDTO  carrinhoResposta = modelMapper.map( carrinho,  CarrinhoDTO.class);
            return ResponseEntity.ok(carrinhoResposta);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Carrinho> carrinho = service.getCarrinhoById(id);
        if (!carrinho.isPresent()) {
            return new ResponseEntity(" Carrinho não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(carrinho.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
