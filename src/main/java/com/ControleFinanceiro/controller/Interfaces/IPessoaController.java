package com.ControleFinanceiro.controller.Interfaces;
// Define o pacote onde a interface está localizada.

import com.ControleFinanceiro.entity.Pessoa;
// Importa a classe Pessoa, que representa a entidade a ser manipulada.

import io.swagger.v3.oas.annotations.Operation;
// Importa a anotação @Operation, usada para descrever a funcionalidade dos endpoints na documentação.

import io.swagger.v3.oas.annotations.responses.ApiResponse;
// Importa a anotação @ApiResponse, usada para documentar os possíveis códigos de resposta HTTP dos endpoints.

import io.swagger.v3.oas.annotations.responses.ApiResponses;
// Importa a anotação @ApiResponses, usada para definir múltiplos @ApiResponse em um único endpoint.

import io.swagger.v3.oas.annotations.tags.Tag;
// Importa a anotação @Tag, que agrupa os endpoints sob um mesmo nome na documentação.

import org.springframework.http.ResponseEntity;
// Importa a classe ResponseEntity, usada para encapsular a resposta HTTP dos métodos.

import org.springframework.web.bind.annotation.*;
// Importa as anotações do Spring para mapeamento de endpoints REST (@GetMapping, @PostMapping, etc.).

import java.util.List;
// Importa a classe List, usada para representar uma lista de pessoas no retorno dos métodos.

@Tag(name = "Pessoa", description = "Endpoint de Pessoa")
// Define uma tag na documentação para agrupar os endpoints relacionados a "Pessoa".

public interface IPessoaController {
// Declara a interface IPessoaController, que define os contratos dos endpoints relacionados à entidade Pessoa.

    @Operation(summary = "Listar Pessoas", description = "Lista todas as pessoas do banco de dados")
    // Documenta o método na API, indicando seu propósito e descrição.

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping // Define que este método será acessado via requisição HTTP GET.
    public ResponseEntity<List<Pessoa>> listar();
    // Declara um método para listar todas as pessoas cadastradas.

    @Operation(summary = "Listar Pessoa POR Id", description = "Lista a pessoa por Id")
    // Documenta o endpoint que busca uma pessoa pelo ID.

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna uma pessoa por Id"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}") // Define que este método será acessado via GET com um parâmetro na URL.
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id);
    // Declara um método para buscar uma pessoa pelo ID informado na URL.

    @Operation(summary = "Criar Pessoa", description = "Cria uma nova pessoa no banco de dados")
    // Documenta o endpoint responsável pela criação de uma nova pessoa.

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping // Define que este método será acessado via requisição HTTP POST.
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa);
    // Declara um método para criar uma nova pessoa recebendo um objeto Pessoa no corpo da requisição.

    @Operation(summary = "Atualizar Pessoa", description = "Atualiza os dados de uma pessoa existente")
    // Documenta o endpoint responsável por atualizar uma pessoa.

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
                    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{codigo}") // Define que este método será acessado via requisição HTTP PUT com um ID na URL.
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @RequestBody Pessoa pessoa);
    // Declara um método para atualizar uma pessoa identificada pelo código informado na URL.

    @Operation(summary = "Deletar Pessoa", description = "Deleta uma pessoa pelo ID")
    // Documenta o endpoint responsável por excluir uma pessoa do banco de dados.

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Pessoa deletada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}") // Define que este método será acessado via requisição HTTP DELETE com um ID na URL.
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id);
    // Declara um método para deletar uma pessoa identificada pelo ID informado na URL.
}
