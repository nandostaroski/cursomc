package com.staroski.cursomc.domain.enums;

import java.util.Arrays;

public enum EstadoPagamento {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");


    private int cod;
    private String descricao;

    private EstadoPagamento(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        Arrays.asList(EstadoPagamento.values()).forEach((estado) -> {

        });
        for (EstadoPagamento estado : EstadoPagamento.values()) {
            if (cod.equals(estado.getCod())) {
                return estado;
            }
        }
        throw new IllegalArgumentException("ID Inválido: " + cod);
    }

}
