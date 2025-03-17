package com.ControleFinanceiro.controller;

import com.ControleFinanceiro.controller.Interfaces.*;
import com.ControleFinanceiro.service.TransacaoService;
import com.ControleFinanceiro.entity.Transacao;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController // Anotação que define a classe como um controlador REST (API).
@RequestMapping("/transacoes")// Define a URL base para as requisições desta classe.
public class TransacaoController implements ITransacaoController {
    private TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<List<Transacao>> listarTodasTransacoes(){ // Método para buscar todas as transações
        return ResponseEntity.ok(transacaoService.listarTransacoes());
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Transacao> listarPorId(@PathVariable Long Id){ // Método para buscar uma transação por ID
        return ResponseEntity.ok(transacaoService.listarPorId(Id));
    }
    @PostMapping("/{pessoaId}")
    public ResponseEntity<Transacao> criarTransacao(@PathVariable Long pessoaId, @RequestBody Transacao transacao) {// Método POST para criar uma transação
        return ResponseEntity.ok(transacaoService.criarTransacao(pessoaId, transacao));
    }
    @PutMapping("/{Id}")
    public ResponseEntity<Transacao> atualizarTransacao(@PathVariable Long Id, @RequestBody Transacao transacao){// Método PUT para atualizar uma transação
        return ResponseEntity.ok(transacaoService.atualizarTransacao(Id, transacao));
    }

}
