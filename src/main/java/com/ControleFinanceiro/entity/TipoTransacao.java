package com.ControleFinanceiro.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum TipoTransacao {
    RECEITA,
    DESPESA;

    public static TipoTransacao fromString(String value) {
        if (value == null) {
            return null;
        }
        for (TipoTransacao tipo : TipoTransacao.values()) {
            if (tipo.name().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de transação inválido: " + value);
    }
}
