package br.com.banco.util;

import br.com.banco.domain.Conta;
import br.com.banco.domain.Transferencia;
import br.com.banco.dto.TransferenciaDto;

import java.time.LocalDate;

public class TransferenciaCreator {

    public static TransferenciaDto createTransferenciaDto() {
        return TransferenciaDto.builder()
                .id(1)
                .dataTransferencia(LocalDate.parse("2019-01-21"))
                .valor(2500.0)
                .tipo("Depósito")
                .nomeOperador("Fulano")
                .build();
    }

    public static Transferencia createTransferencia() {
        return Transferencia.builder()
                .id(2)
                .dataTransferencia(LocalDate.parse("2020-03-18"))
                .valor(2500.0)
                .tipo("Saque")
                .nomeOperadorTransacao("Ciclano")
                .contaId(new Conta(2, "Anonimo"))
                .build();
    }
}
