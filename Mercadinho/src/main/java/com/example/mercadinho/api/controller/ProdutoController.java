package com.example.mercadinho.api.controller;

import com.example.mercadinho.api.dto.ProdutoDTO;
import com.example.mercadinho.exception.RegraNegocioException;
import com.example.mercadinho.model.entity.Produto;
import com.example.mercadinho.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/API/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private ModelMapper modelMapper;

    private final ProdutoService service;

    @GetMapping
    public ResponseEntity get() {
        List<Produto> produtos = service.getProdutos();
        return ResponseEntity.ok(produtos.stream().map(ProdutoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Produto> produto = service.getProdutoById(id);
        if (!produto.isPresent()) {
            return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(produto.map(ProdutoDTO::create));
    }

    @PostMapping
    public ResponseEntity post(@RequestBody ProdutoDTO dto) {
        try {
            Produto produtoRequisicao = modelMapper.map(dto, Produto.class);
            Produto produto = service.salvar(produtoRequisicao);

            ProdutoDTO produtoResposta = modelMapper.map(produto, ProdutoDTO.class);
            return new ResponseEntity(produtoResposta, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody ProdutoDTO dto) {
        if(!service.getProdutoById(id).isPresent()) {
            return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
        }

        try {
            Produto produtoRequisicao = modelMapper.map(dto, Produto.class);
            produtoRequisicao.setId(id);
            Produto produto = service.salvar(produtoRequisicao);

            ProdutoDTO produtoResposta = modelMapper.map(produto, ProdutoDTO.class);
            return ResponseEntity.ok(produtoResposta);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Produto> produto = service.getProdutoById(id);
        if (!produto.isPresent()) {
            return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(produto.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
