package com.staroski.cursomc.domain.enums;

import java.util.Arrays;

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int cod;
    private String descricao;

    private TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        Arrays.asList(TipoCliente.values()).forEach((cliente) -> {

        });
        for (TipoCliente cliente : TipoCliente.values()) {
            if (cod.equals(cliente.getCod())) {
                return cliente;
            }
        }
        throw new IllegalArgumentException("ID Inválido: " + cod);
    }

}
