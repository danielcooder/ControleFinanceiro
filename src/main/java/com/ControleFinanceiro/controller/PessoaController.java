package com.ControleFinanceiro.controller;

import com.ControleFinanceiro.controller.Interfaces.IPessoaController;
import com.ControleFinanceiro.entity.Pessoa;
import com.ControleFinanceiro.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")

public class PessoaController implements IPessoaController {

    private PessoaService pessoaService;
    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.criarPessoa(pessoa));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.atualizarPessoa(codigo, pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
}
