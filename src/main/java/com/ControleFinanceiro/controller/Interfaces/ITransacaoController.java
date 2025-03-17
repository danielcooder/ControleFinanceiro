package com.ControleFinanceiro.controller.Interfaces;

import com.ControleFinanceiro.entity.Transacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Transação", description = "Endpoint de Transação")
public interface ITransacaoController {

    @Operation(summary = "Listar todas as transações", description = "Lista todas as transações do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de transações"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping // Método para listar todas as transações
    public ResponseEntity<List<Transacao>> listarTodasTransacoes();

    @Operation(summary = "Listar transação por ID", description = "Lista a transação pelo ID fornecido")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a transação por ID"),
                    @ApiResponse(responseCode = "404", description = "Transação não encontrada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}") // Método para listar uma transação pelo ID
    public ResponseEntity<Transacao> listarPorId(@PathVariable Long id);

    @Operation(summary = "Criar transação", description = "Cria uma nova transação associada a uma pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
                    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{pessoaId}") // Método para criar uma nova transação associada a uma pessoa
    public ResponseEntity<Transacao> criarTransacao(@PathVariable Long pessoaId, @RequestBody Transacao transacao);

    @Operation(summary = "Atualizar transação", description = "Atualiza os dados de uma transação existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Transação atualizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
                    @ApiResponse(responseCode = "404", description = "Transação ou Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}") // Método para atualizar uma transação
    public ResponseEntity<Transacao> atualizarTransacao(@PathVariable Long id, @RequestBody Transacao transacao);
}
