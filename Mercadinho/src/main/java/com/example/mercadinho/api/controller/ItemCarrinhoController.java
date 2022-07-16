package com.example.mercadinho.api.controller;

import com.example.mercadinho.api.dto.ItemCarrinhoDTO;
import com.example.mercadinho.exception.RegraNegocioException;
import com.example.mercadinho.model.entity.ItemCarrinho;
import com.example.mercadinho.service.ItemCarrinhoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/API/v1/itemCarrinho")
@RequiredArgsConstructor
public class ItemCarrinhoController {
    private ModelMapper modelMapper;

    private final ItemCarrinhoService service;

    @GetMapping
    public ResponseEntity get() {
        List<ItemCarrinho> itemCarrinho = service.getItemCarrinho();
        return ResponseEntity.ok(itemCarrinho.stream().map(ItemCarrinhoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<ItemCarrinho> itemCarrinho = service.getItemCarrinhoById(id);
        if (!itemCarrinho.isPresent()) {
            return new ResponseEntity("item inesistente", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(itemCarrinho.map(ItemCarrinhoDTO::create));
    }

    @PostMapping
    public ResponseEntity post(@RequestBody ItemCarrinhoDTO dto) {
        try {
            ItemCarrinho itemCarrinhoRequisicao = modelMapper.map(dto, ItemCarrinho.class);
            ItemCarrinho itemCarrinho = service.salvar(itemCarrinhoRequisicao);

            ItemCarrinhoDTO itemCarrinhoResposta = modelMapper.map(itemCarrinho, ItemCarrinhoDTO.class);
            return new ResponseEntity(itemCarrinhoResposta, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody ItemCarrinhoDTO dto) {
        if(!service.getItemCarrinhoById(id).isPresent()) {
            return new ResponseEntity("item não encontrado", HttpStatus.NOT_FOUND);
        }

        try {
            ItemCarrinho itemCarrinhoRequisicao = modelMapper.map(dto, ItemCarrinho.class);
            itemCarrinhoRequisicao.setId(id);
            ItemCarrinho itemCarrinho = service.salvar(itemCarrinhoRequisicao);

            ItemCarrinhoDTO itemCarrinhoResposta = modelMapper.map(itemCarrinho, ItemCarrinhoDTO.class);
            return ResponseEntity.ok(itemCarrinhoResposta);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<ItemCarrinho> itemCarrinho = service.getItemCarrinhoById(id);
        if (!itemCarrinho.isPresent()) {
            return new ResponseEntity("item não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(itemCarrinho.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
