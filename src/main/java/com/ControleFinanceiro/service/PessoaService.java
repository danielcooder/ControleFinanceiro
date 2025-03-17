package com.ControleFinanceiro.service;
import com.ControleFinanceiro.entity.Pessoa;
import com.ControleFinanceiro.entity.TipoTransacao;
import com.ControleFinanceiro.exception.EntidadeNaoEncontradaException;
import com.ControleFinanceiro.repository.PessoaRepository;
import com.ControleFinanceiro.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@RequiredArgsConstructor // Gera automaticamente um construtor com todos os argumentos da classe
@Service // Define a classe como um componente gerenciado pelo Spring, indicando que é um serviço

public class PessoaService {
    private final PessoaRepository pessoaRepository; // Repositório para operações no banco de dados relacionadas à entidade Pessoa
    private final TransacaoRepository transacaoRepository; // Repositório para operações no banco de dados relacionadas às transações financeiras

    public List<Pessoa> listarPessoas() { // Retorna todas as pessoas cadastradas e calcula os totais de receitas e despesas
        List<Pessoa> pessoas = pessoaRepository.findAll();
        calcularTotais(pessoas); // Atualiza os totais financeiros das pessoas
        return pessoas;
    }

    public Pessoa buscarPorId(Long id) { // Busca uma pessoa pelo ID e calcula seus totais financeiros
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada com o ID: " + id));

        List<Pessoa> pessoas = List.of(pessoa); // Cria uma lista temporária para cálculo dos totais
        calcularTotais(pessoas);
        return pessoa;
    }

    public Pessoa criarPessoa(Pessoa pessoa) { // Cria uma nova pessoa no banco de dados, validando o nome
        if (pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
            throw new EntidadeNaoEncontradaException("O nome da pessoa não pode estar vazio ou nulo");
        }
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPessoa(Long Id, Pessoa pessoaAtualizada) { // Atualiza uma pessoa existente, se os dados forem válidos
        Pessoa pessoaExistente = buscarPorId(Id);

        if (pessoaAtualizada.getNome() != null && !pessoaAtualizada.getNome().trim().isEmpty()) {
            pessoaExistente.setNome(pessoaAtualizada.getNome());
        }
        if (pessoaAtualizada.getEmail() != null) {
            pessoaExistente.setEmail(pessoaAtualizada.getEmail());
        }
        if (pessoaAtualizada.getIdade() != 0) {
            pessoaExistente.setIdade(pessoaAtualizada.getIdade());
        }

        return pessoaRepository.save(pessoaExistente); // Salva a pessoa atualizada
    }

    public void deletarPessoa(Long id) { // Remove uma pessoa do banco de dados pelo ID
        Pessoa pessoa = buscarPorId(id);
        pessoaRepository.deleteById(id);
    }

    private void calcularTotais(List<Pessoa> pessoas) { // Calcula receitas, despesas e saldo total de cada pessoa
        for (Pessoa pessoa : pessoas) {
            double totalReceitas = transacaoRepository.somarPorPessoaETipo(pessoa.getId(), TipoTransacao.RECEITA);
            double totalDespesas = transacaoRepository.somarPorPessoaETipo(pessoa.getId(), TipoTransacao.DESPESA);
            double saldoTotal = totalReceitas - totalDespesas;

            // Define os valores calculados na pessoa
            pessoa.setTotalReceitas(totalReceitas);
            pessoa.setTotalDespesas(totalDespesas);
            pessoa.setSaldoTotal(saldoTotal);
        }
    }
}
