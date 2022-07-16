package com.example.mercadinho.api.controller;

import com.example.mercadinho.api.dto.FormaPagamentoDTO;
import com.example.mercadinho.exception.RegraNegocioException;
import com.example.mercadinho.model.entity.FormaPagamento;
import com.example.mercadinho.service.FormaPagamentoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/API/v1/formaPagamentos")
@RequiredArgsConstructor
public class FormaPagamentoController {
    private ModelMapper modelMapper;

    private final FormaPagamentoService service;

    @GetMapping
    public ResponseEntity get() {
        List<FormaPagamento> formaPagamento = service.getFormaPagamento();
        return ResponseEntity.ok(formaPagamento.stream().map(FormaPagamentoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<FormaPagamento> formaPagamento = service.getFormaPagamentoById(id);
        if (!formaPagamento.isPresent()) {
            return new ResponseEntity("forma de pagamento não definida", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(formaPagamento.map(FormaPagamentoDTO::create));
    }

    @PostMapping
    public ResponseEntity post(@RequestBody FormaPagamentoDTO dto) {
        try {
            FormaPagamento formaPagamentoRequisicao = modelMapper.map(dto, FormaPagamento.class);
            FormaPagamento formaPagamento = service.salvar(formaPagamentoRequisicao);

            FormaPagamentoDTO formaPagamentoResposta = modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
            return new ResponseEntity(formaPagamentoResposta, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody FormaPagamentoDTO dto) {
        if(!service.getFormaPagamentoById(id).isPresent()) {
            return new ResponseEntity("forma de pagamento não definida", HttpStatus.NOT_FOUND);
        }

        try {
            FormaPagamento formaPagamentoRequisicao = modelMapper.map(dto, FormaPagamento.class);
            formaPagamentoRequisicao.setId(id);
            FormaPagamento formaPagamento = service.salvar(formaPagamentoRequisicao);

            FormaPagamentoDTO formaPagamentoResposta = modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
            return ResponseEntity.ok(formaPagamentoResposta);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<FormaPagamento> formaPagamento = service.getFormaPagamentoById(id);
        if (!formaPagamento.isPresent()) {
            return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(formaPagamento.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
