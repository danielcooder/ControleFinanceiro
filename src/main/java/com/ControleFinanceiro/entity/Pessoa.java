package com.ControleFinanceiro.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

///Me informe sobre este bloco
@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {


    ///Me informe sobre este bloco
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(example = "James Santos")
    private String nome;

    @Schema(example = "James.Santos@example.com")
    private String email;

    @Schema(example = "30")
    private int idade;

    //   ///Me informe sobre este bloco
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Evita a referência circular, serializando transações corretamente
    @Schema(description = "Lista de transações relacionadas à pessoa")
    private List<Transacao> transacoes;

    ///Me informe sobre este bloco
    // Campos calculados/transientes que não serão persistidos no banco de dados
    @Transient
    @Schema(example = "1000.0")
    private double totalReceitas;

    @Transient
    @Schema(example = "500.0")
    private double totalDespesas;

    @Transient
    @Schema(example = "500.0")
    private double saldoTotal;
}
