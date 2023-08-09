package br.com.banco.util;

import br.com.banco.domain.Transferencia;
import br.com.banco.dto.TransferenciaDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class TransferenciaCreator {

    public static TransferenciaDto createTransferencia(){
        return TransferenciaDto.builder()
                .id(1)
                .dataTransferencia(LocalDate.parse("2019-01-21"))
                .valor(2500.0)
                .tipo("Depósito")
                .nomeOperador("Fulano")
                .build();
    }
}
