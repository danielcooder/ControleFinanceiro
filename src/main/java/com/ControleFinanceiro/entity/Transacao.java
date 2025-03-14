package com.ControleFinanceiro.entity;


import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


///Me informe sobre este bloco 1
@Data
@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;


///Me informe sobre este bloco 2
    @Schema(example = "Compra supermercado")
    private String descricao;

    @Schema(example = "200.0")
    private double valor;


    ///Me informe sobre este bloco 3
    @Enumerated(EnumType.STRING) // Indicação do tipo de enum referenciado
    @Schema(example = "RECEITA")
    private TipoTransacao tipo;

    ///Me informe sobre este bloco 4
    @Column(name = "data")
    @JsonFormat(pattern = "dd-MM-yyyy") // Anotação para auxiliar a serialização com design definido
    @Schema(example = "23-02-2025")
    private LocalDate data;


    ///Me informe sobre este bloco 5
    @ManyToOne //Anotação de relacionamento
    @JoinColumn(name = "pessoa_id", nullable = false)
    @JsonBackReference // Evita a referência circular, serializando transações corretamente
    @Schema(hidden = true)
    private Pessoa pessoa;

    ///Me informe sobre este bloco 6
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(example = "João Silva")
    public String getPessoaNome() {
        return pessoa != null ? pessoa.getNome() : null;
    }

    ///Me informe sobre este bloco 7
    // Método personalizado para acessar o id e nome da Pessoa diretamente
    @JsonInclude(JsonInclude.Include.NON_NULL) // Inclui o valor apenas se não for nulo
    @Schema(example = "1")
    public Long getPessoaId() {
        return pessoa != null ? pessoa.getId() : null;
    }

}