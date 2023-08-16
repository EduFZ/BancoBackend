package br.com.banco.util;

import br.com.banco.dto.TransferenciaDto;
import br.com.banco.exceptions.ExceptionMessage;

import java.time.LocalDate;

public class TransferenciaCreator {

    public static TransferenciaDto createTransferencia() {
        return TransferenciaDto.builder()
                .id(1)
                .dataTransferencia(LocalDate.parse("2019-01-21"))
                .valor(2500.0)
                .tipo("Depósito")
                .nomeOperador("Fulano")
                .build();
    }
}
