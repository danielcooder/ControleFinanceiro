package com.ControleFinanceiro.service;

import com.ControleFinanceiro.entity.Pessoa;
import com.ControleFinanceiro.entity.Transacao;
import com.ControleFinanceiro.exception.EntidadeNaoEncontradaException;
import com.ControleFinanceiro.repository.PessoaRepository;
import com.ControleFinanceiro.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import java.util.List;


@RequiredArgsConstructor // Gera automaticamente um construtor com todos os argumentos da classe
@Service // Define a classe como um serviço gerenciado pelo Spring

//
public class TransacaoService {
    private final TransacaoRepository transacaoRepository; // Repositório para operações no banco de dados relacionadas à entidade Transacao
    private final PessoaRepository pessoaRepository; // Repositório para operações no banco de dados relacionadas à entidade Pessoa
    private final PessoaService pessoaService; // Serviço para manipular dados e regras de negócio da Pessoa


    public List<Transacao> listarTransacoes() { // Método para listar todas as transações
        return transacaoRepository.findAll();
    }

    public Transacao listarPorId(Long id) { // Método para listar as transações por Id
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Transação não encontrada para o ID: " + id));
        Hibernate.initialize(transacao.getPessoa().getTransacoes());
        return transacao;
    }
    public Transacao criarTransacao(Long pessoaId, Transacao transacao) { // Método para criar as transações
        Pessoa pessoa =  pessoaService.buscarPorId(pessoaId); // Buscar a pessoa associada à transação

        transacao.setPessoa(pessoa);
        return transacaoRepository.save(transacao);
    }
    public Transacao atualizarTransacao(Long Id, Transacao transacaoAtualizada){// Método para atualizar as transações
        Transacao transacaoExistente = listarPorId(Id);

        // Atualizar os campos da transação existente com os novos valores, se não forem nulos
        if (transacaoAtualizada.getDescricao() != null && !transacaoAtualizada.getDescricao().trim().isEmpty()) {
            transacaoExistente.setDescricao(transacaoAtualizada.getDescricao());
        }
        if (transacaoAtualizada.getValor() != 0) {
            transacaoExistente.setValor(transacaoAtualizada.getValor());
        }
        if (transacaoAtualizada.getTipo() != null) {
            transacaoExistente.setTipo(transacaoAtualizada.getTipo());
        }
        if (transacaoAtualizada.getData() != null) {
            transacaoExistente.setData(transacaoAtualizada.getData());
        }

        return transacaoRepository.save(transacaoExistente); // Salvar a transação atualizada
    }

}
