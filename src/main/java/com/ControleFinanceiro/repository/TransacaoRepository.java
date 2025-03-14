package com.ControleFinanceiro.repository;

import com.ControleFinanceiro.entity.TipoTransacao;
import com.ControleFinanceiro.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t WHERE t.pessoa.id = :idPessoa AND t.tipo = :tipo")
    double somarPorPessoaETipo(@Param("idPessoa") Long idPessoa, @Param("tipo") TipoTransacao tipo);
}